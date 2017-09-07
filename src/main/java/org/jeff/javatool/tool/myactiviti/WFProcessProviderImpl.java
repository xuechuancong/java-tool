package org.jeff.javatool.tool.myactiviti;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.NativeProcessDefinitionQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeff.javatool.tool.myactiviti.config.QueryOrderType;
import org.jeff.javatool.tool.myactiviti.config.QueryProcessDefinitionOrderKeyType;
import org.jeff.javatool.tool.myactiviti.config.SeqConfig;
import org.jeff.javatool.tool.myactiviti.config.WFConfig;
import org.jeff.javatool.tool.myactiviti.entity.TProcessDefinitionDetail;
import org.jeff.javatool.tool.myactiviti.entity.TProcessDefinitionList;
import org.jeff.javatool.tool.myactiviti.entity.TTaskDefinitionList;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryProcessDefinitionCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;
import org.jeff.javatool.tool.myactiviti.entity.result.ResultCodes;
import org.jeff.javatool.tool.myactiviti.entity.result.ResultFactory;
import org.jeff.javatool.tool.myactiviti.intfc.IWFProcessProvider;
import org.jeff.javatool.tool.myactiviti.utils.BpmnVariableUtil;
import org.jeff.javatool.tool.myactiviti.utils.JsonUtil;
import org.jeff.javatool.tool.myactiviti.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 工作流-流程相关服务
 * Created by weijianfu on 2016/11/22.
 */
@Service
public class WFProcessProviderImpl implements IWFProcessProvider {

    @Autowired
    private WFBaseCommonService commonService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ManagementService managementService;


    @Value("${activiti.relative-path}")
    private String activitiRelativePath;

    private Log log = LogFactory.getLog(this.getClass());

    /**
     * 生成流程定义并部署
     *
     * @param category
     * @param deploymentName
     * @param processColl
     * @param ex
     * @return 流程定义ID
     */
    public String generateProcessDefinitionWithProcDefId(String category, String deploymentName, Collection<Process> processColl, Map<String, Object> ex) throws Exception {
        if (processColl == null || processColl.size() <= 0) {
            throw new Exception("参数为空");
        }
        try {
            BpmnModel model = new BpmnModel();
            for (Process process : processColl) {
                model.addProcess(process);
            }
            //生成BPMN自动布局
            BpmnAutoLayout bpmnAutoLayout = new BpmnAutoLayout(model);
            bpmnAutoLayout.execute();

            //bpmnModel 转换为标准的bpmnxml文件
            BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
            byte[] convertToXML = bpmnXMLConverter.convertToXML(model);
            InputStream inputStream = new ByteArrayInputStream(convertToXML);

            //获取BPMN文件名称
            long seq = commonService.getSeqByName(SeqConfig.SEQ_PROC_BPMN);
            String fileName = String.valueOf(seq) + ".bpmn";

            Deployment deployment = repositoryService
                    .createDeployment()//创建一个部署对象
                    .name(deploymentName)//添加部署的名称
                    .addInputStream(fileName, inputStream)
                    .category(category)
                    .deploy();//完成部署
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                    .deploymentId(deployment.getId()).latestVersion().singleResult();
            repositoryService.setProcessDefinitionCategory(processDefinition.getId(), category);
            return processDefinition.getId();
        } catch (Exception e) {
            log.error("[WFProcessProviderImpl.generateProcessDefinition] Error!入参"
                    + "category:" + category + "; "
                    + "deploymentName:" + deploymentName + "; "
                    + "processColl:" + JsonUtil.serialize(processColl) + "; "
                    + "ex:" + JsonUtil.serialize(ex) + "; ", e);
            throw new Exception(e);
        }
    }

    /**
     * 生成流程定义并部署
     *
     * @param category
     * @param deploymentName
     * @param processColl
     * @param ex
     * @return 流程定义ID
     */
    public Result<Void> generateProcessDefinition(String category, String deploymentName, Collection<Process> processColl, Map<String, Object> ex) {
        try {
            generateProcessDefinitionWithProcDefId(category, deploymentName, processColl, ex);
            return ResultFactory.success();
        } catch (Exception e) {
            log.error("失败", e);
            return ResultFactory.error(e.getMessage());
        }
    }

    /**
     * 查询流程定义列表
     *
     * @param offset 分页offset
     * @param limit  分页limit
     * @param ex     预留字段
     * @return
     */
    @Override
    public Result<Pager<TProcessDefinitionList>> queryProcessDefinitionList(QueryProcessDefinitionCondition condition, int offset, int limit, Map<String, Object> ex) {
        try {
            NativeProcessDefinitionQuery query = repositoryService.createNativeProcessDefinitionQuery();
            StringBuffer whereSql = new StringBuffer();
            whereSql.append(" where 1=1 and T1.suspension_state_ = 1");//未挂起状态
            StringBuffer orderSql = new StringBuffer();

            /**
             * 拼装查询条件
             */
            if (condition != null) {
                if (condition.getProcessDefinitionNameLike() != null) {//流程定义名称，模糊查询
                    whereSql.append(" and T1.name_ like #{ProcessDefinitionNameLike} ");
                    query.parameter("ProcessDefinitionNameLike", condition.getProcessDefinitionNameLike());
                }
                if (condition.getProcessDefinitionKey() != null) {//流程定义Key
                    whereSql.append(" and T1.key_ = #{ProcessDefinitionKey} ");
                    query.parameter("ProcessDefinitionKey", condition.getProcessDefinitionKey());
                }
                if (condition.getIsLatestVersion() != null && condition.getIsLatestVersion()) {//是否只查询出最新一个版本
                    whereSql.append(" and T1.VERSION_ = " +
                            "(select max(VERSION_) from ACT_RE_PROCDEF " +
                            "where KEY_ = T1.KEY_ and ((TENANT_ID_ IS NOT NULL and TENANT_ID_ = T1.TENANT_ID_) or (TENANT_ID_ IS NULL and T1.TENANT_ID_ IS NULL)))");
                }
                if (condition.getProcessDefinitionCategory() != null) {//流程类型
                    whereSql.append(" and T1.category_ = #{ProcessDefinitionCategory} ");
                    query.parameter("ProcessDefinitionCategory", condition.getProcessDefinitionCategory());
                }
                if (condition.getUpdateMinTime() != null) {//更新时间最小值
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    whereSql.append(" AND (T2.deploy_time_ >= to_date(#{UpdateMinTime},'yyyy-mm-dd HH24:mi:ss')) ");
                    query.parameter("UpdateMinTime", format.format(condition.getUpdateMinTime()));
                }
                if (condition.getUpdateMaxTime() != null) {//更新时间最大值
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    whereSql.append(" AND (T2.deploy_time_ <= to_date(#{UpdateMaxTime},'yyyy-mm-dd HH24:mi:ss')) ");
                    query.parameter("UpdateMaxTime", format.format(condition.getUpdateMaxTime()));
                }
                if (condition.getAdvancedSearch() != null) {//高级搜索，流程类型、流程定义名称模糊
                    String like = "%" + condition.getAdvancedSearch() + "%";
                    whereSql.append(" and (T1.category_ = #{ProcessDefinitionCategory} or T1.name_ like #{ProcessDefinitionNameLike}) ");
                    query.parameter("ProcessDefinitionCategory", condition.getAdvancedSearch());
                    query.parameter("ProcessDefinitionNameLike", like);
                }

                if (condition.getOrderKeyType() != null) {//排序关键字类型
                    if (QueryProcessDefinitionOrderKeyType.CATEGORY.equals(condition.getOrderKeyType())) {
                        whereSql.append(" order by T1.category_ ");
                    } else if (QueryProcessDefinitionOrderKeyType.ID.equals(condition.getOrderKeyType())) {
                        whereSql.append(" order by T1.id_ ");
                    } else if (QueryProcessDefinitionOrderKeyType.KEY.equals(condition.getOrderKeyType())) {
                        whereSql.append(" order by T1.key_ ");
                    } else if (QueryProcessDefinitionOrderKeyType.NAME.equals(condition.getOrderKeyType())) {
                        whereSql.append(" order by T1.name_ ");
                    } else if (QueryProcessDefinitionOrderKeyType.VERSION.equals(condition.getOrderKeyType())) {
                        whereSql.append(" order by T1.version_ ");
                    } else if (QueryProcessDefinitionOrderKeyType.DEPLOYMENT_ID.equals(condition.getOrderKeyType())) {
                        whereSql.append(" order by T1.deployment_id_ ");
                    } else if (QueryProcessDefinitionOrderKeyType.TENANT_ID.equals(condition.getOrderKeyType())) {
                        whereSql.append(" order by T1.tenant_id_ ");
                    }

                    if (condition.getOrderType() != null) {//排序类型
                        if (QueryOrderType.ASC.equals(condition.getOrderType())) {
                            whereSql.append(" asc ");
                        } else if (QueryOrderType.DESC.equals(condition.getOrderType())) {
                            whereSql.append(" desc ");
                        }
                    }
                }
            }

            List<ProcessDefinition> processDefinitionList =
                    query.sql("SELECT * FROM " + managementService.getTableName(ProcessDefinition.class) + " T1 " +
                            "left join " + managementService.getTableName(Deployment.class) + " T2 on T1.deployment_id_ = T2.id_ " +
                            whereSql.toString() + orderSql.toString())
                            .listPage(offset, limit);

            //查询流程定义对应的Deployment
            Map<String, Deployment> mapProcessDefinition2Deployment = Maps.newHashMap();
            if (processDefinitionList != null && processDefinitionList.size() > 0) {
                for (ProcessDefinition processDefinition : processDefinitionList) {
                    Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(processDefinition.getDeploymentId()).singleResult();
                    mapProcessDefinition2Deployment.put(processDefinition.getId(), deployment);
                }
            }
            List<TProcessDefinitionList> tProcessDefinitionListList = TProcessDefinitionList.parseList(processDefinitionList, mapProcessDefinition2Deployment);

            List<ProcessDefinition> processDefinitionListForCount = query.listPage(0, Integer.MAX_VALUE);
            int count = processDefinitionListForCount == null ? 0 : processDefinitionListForCount.size();
            return ResultFactory.success(new Pager<>(offset, limit, count, tProcessDefinitionListList));
        } catch (Throwable e) {
            log.error("[WFProcessProviderImpl.queryProcessDefinitionList] Error!入参"
                    + "condition:" + JsonUtil.serialize(condition)
                    + "offset:" + offset + "; "
                    + "limit:" + limit + "; "
                    + "ex:" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    public Result<TProcessDefinitionDetail> queryProcessDefinitionDetail(String processDefinitionId, Map<String, Object> ex) {
        if (processDefinitionId == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        List<ProcessDefinition> processDefinitionList = repositoryService
                .createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).active().list();
        ProcessDefinition processDefinition = processDefinitionList.get(0);
        if (processDefinition == null) {
            return ResultFactory.build(ResultCodes.PARAM_ILLEGAL);
        }
        Map<String, Object> allTaskVariables = commonService.getAllTaskVariables(processDefinitionId);
        TProcessDefinitionDetail definitionDetail = new TProcessDefinitionDetail(processDefinition, allTaskVariables);
        return ResultFactory.success(definitionDetail);
    }

    @Override
    public Result<List<TTaskDefinitionList>> queryTaskDefinitionList(String processDefinitionId, Collection<String> roleIds, Map<String, Object> ex) {
        if (processDefinitionId == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        try {
            List<TTaskDefinitionList> resultList = Lists.newArrayList();
            BpmnModel model = repositoryService.getBpmnModel(processDefinitionId);
            if (model != null) {
                Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
                for (FlowElement flowElement : flowElements) {
                    //如果是UserTask类型
                    if (flowElement instanceof UserTask) {
                        UserTask curUserTask = (UserTask) flowElement;
                        Map<String, List<ExtensionElement>> extensionElementMap = curUserTask.getExtensionElements();
                        List<String> curRoleIds = BpmnVariableUtil.codeRoleIds(extensionElementMap);

                        if (containConditionRoleIds(curRoleIds, roleIds)) {//是否包含条件中的roleIds
                            TTaskDefinitionList newTaskDefinitionList = new TTaskDefinitionList();
                            newTaskDefinitionList.setTaskDefinitionKey(curUserTask.getId());
                            newTaskDefinitionList.setTaskName(curUserTask.getName());
                            newTaskDefinitionList.setRoleIds(curRoleIds);
                            resultList.add(newTaskDefinitionList);
                        }
                    }
                }
            }
            return ResultFactory.success(resultList);
        } catch (Throwable e) {
            log.error("[WFProcessProviderImpl.queryTaskDefinitionList] Error!入参"
                    + "processDefinitionId:" + processDefinitionId + "; "
                    + "roleIds:" + JsonUtil.serialize(roleIds) + "; "
                    + "ex:" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    public Result<String> queryFirstTaskDefinitionKey(String procDefId, Map<String, Object> ex) {
        if (StringUtil.isBlank(procDefId)) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        try {
            BpmnModel processBpmnModel = repositoryService.getBpmnModel(procDefId);
            List<Process> processList = processBpmnModel.getProcesses();
            Process process = processList.get(0);
            Collection<FlowElement> flowElementColl = process.getFlowElements();
            if (flowElementColl == null || flowElementColl.size() <= 0) {
                return ResultFactory.error("flowElementColl中没有元素");
            }
            for (FlowElement flowElement : flowElementColl) {
                if (flowElement instanceof SequenceFlow) {//流向
                    SequenceFlow sequenceFlow = (SequenceFlow) flowElement;
                    if (WFConfig.START_EVE_DEF_KEY.equals(sequenceFlow.getSourceRef())) {//如果是开始节点
                        return ResultFactory.success(sequenceFlow.getTargetRef());
                    }
                }
            }
            return ResultFactory.error("找不到第一个任务定义KEY");
        } catch (Throwable e) {
            log.error("[WFProcessProviderImpl.queryFirstTaskDefinitionKey] Error!入参"
                    + "procDefId:" + procDefId + "; "
                    + "ex:" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    private Map<String, Object> getVariableMap(List<HistoricVariableInstance> hisVariableList) {
        Map<String, Object> variableMap = Maps.newHashMap();
        if (hisVariableList == null || hisVariableList.size() <= 0) {
            return variableMap;
        }
        for (HistoricVariableInstance historicVariableInstance : hisVariableList) {
            variableMap.put(historicVariableInstance.getVariableName(), historicVariableInstance.getValue());
        }
        return variableMap;
    }


    private boolean containConditionRoleIds(List<String> curRoleIds, Collection<String> conditionRoleIds) {
        if (conditionRoleIds == null || conditionRoleIds.size() <= 0) {
            return true;
        }
        for (String curRoleId : curRoleIds) {
            if (conditionRoleIds.contains(curRoleId)) {
                return true;
            }
        }
        return false;
    }
}
