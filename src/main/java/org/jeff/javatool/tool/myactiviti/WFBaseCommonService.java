package org.jeff.javatool.tool.myactiviti;

import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.jeff.javatool.tool.myactiviti.config.CustomProcessState;
import org.jeff.javatool.tool.myactiviti.config.SeqConfig;
import org.jeff.javatool.tool.myactiviti.config.WFConfig;
import org.jeff.javatool.tool.myactiviti.domain.ActivitiMapper;
import org.jeff.javatool.tool.myactiviti.domain.CommonSeqMapper;
import org.jeff.javatool.tool.myactiviti.domain.TProcCustomMapper;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcCustom;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcCustomExample;
import org.jeff.javatool.tool.myactiviti.domain.entity.TProcCustomKey;
import org.jeff.javatool.tool.myactiviti.entity.TCustomProcessState;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryCustomProcessCondition;
import org.jeff.javatool.tool.myactiviti.utils.BpmnVariableUtil;
import org.jeff.javatool.tool.myactiviti.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 供所有其它service调用的公共service，其中DB处理、公共代码等
 * Created by weijianfu on 2016/12/1.
 */
@Service
public class WFBaseCommonService {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private CommonSeqMapper commonSeqMapper;
    @Autowired
    private TProcCustomMapper tProcCustomMapper;
    @Autowired
    private ActivitiMapper activitiMapper;

    private static final Logger LOG = LoggerFactory.getLogger(WFBaseCommonService.class);


    /**
     * 获取序列
     *
     * @param name
     * @return
     */
    public long getSeqByName(String name) throws Exception {
        return commonSeqMapper.selectSeqByName(name);
    }

    public void updateHistoricTaskInstance(String processDefinitionId, String processInstanceId, Collection<String> taskIds) {
        if (taskIds == null || taskIds.size() <= 0) {
            return;
        }
        StringBuilder idsStr = new StringBuilder();
        for (String taskId : taskIds) {
            idsStr.append(taskId).append(",");
        }
        if (idsStr.length() > 0) {
            activitiMapper.updateHiTaskInst(processDefinitionId, processInstanceId, idsStr.substring(0, idsStr.length() - 1));
        }
    }

    public int countTProcCustomList(QueryCustomProcessCondition condition) {
        return tProcCustomMapper.countList(condition);
    }

    public List<TProcCustom> queryTProcCustomList(QueryCustomProcessCondition condition) {
        return tProcCustomMapper.queryList(condition);
    }

    public TProcCustom selectTProcCustomByPrimaryKey(TProcCustomKey key) {
        return tProcCustomMapper.selectByPrimaryKey(key);
    }

    public int insertTProcCustom(TProcCustom procCustom) throws Exception {
        if (!StringUtil.isBlank(procCustom.getProcDefKey()) || procCustom.getVersion() != null) {
            throw new Exception("录入时，流程定义KEY、版本号需均为空");
        }
        procCustom.setProcDefKey(WFConfig.CUSTOM_PROC_HEAD_OF_KEY + String.valueOf(commonSeqMapper.selectSeqByName(SeqConfig.SEQ_PROC_CUST_KEY)));
        procCustom.setVersion(1);
        return tProcCustomMapper.insertSelective(procCustom);
    }

    public int updateTProcCustom(TProcCustom procCustom) throws Exception {
        if (StringUtil.isBlank(procCustom.getProcDefKey()) || procCustom.getVersion() == null) {
            throw new Exception("更新时，流程定义KEY、版本号均不能为空");
        }
        procCustom.setState(null);//该方法不允许更新激活状态
        return tProcCustomMapper.updateByPrimaryKeySelective(procCustom);
    }

    public int updateTProcCustomState(TCustomProcessState processState) throws Exception {
        Integer version = processState.getVersion();
        if (CustomProcessState.INVALID.getState().equals(processState.getState())) {//停用
            //新增一个版本号
            TProcCustomExample example = new TProcCustomExample();
            example.createCriteria().andProcDefKeyEqualTo(processState.getProcDefKey());
            example.setOrderByClause(" version desc ");

            List<TProcCustom> procCustomList = tProcCustomMapper.selectByExample(example);
            if (procCustomList == null || procCustomList.size() <= 0) {
                throw new Exception("通过主键查不到任何记录");
            }
            version = procCustomList.size() + 1;//新版本号
            TProcCustom procCustom = procCustomList.get(0);//上一个版本的内所有内容
            procCustom.setVersion(version);
            processState.setVersion(version);//用于接口返回参数
            procCustom.setState(null);
            procCustom.setCreateTime(new Date());
            procCustom.setCreator(processState.getOperUserName());
            return tProcCustomMapper.insertSelective(procCustom);
        }
        TProcCustom procCustom = new TProcCustom();
        procCustom.setProcDefKey(processState.getProcDefKey());
        procCustom.setVersion(version);
        procCustom.setState(processState.getState());
        return tProcCustomMapper.updateByPrimaryKeySelective(procCustom);
    }

    /**
     * 获取定义中，所有任务环节自定的参数
     *
     * @param processDefinitionId
     * @return Map<taskKey, <WFConfigKey, value>>
     */
    public Map<String, Object> getAllTaskVariables(String processDefinitionId) {
        Map<String, Object> variables = new HashMap<>();

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();
        if (processDefinition == null) {
            LOG.info("[ActivitiBaseCommonService.getAllTaskVariables]processDefinition is null！processDefinitionId：" + processDefinitionId);
            return variables;
        }

        BpmnModel processBpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
        if (processBpmnModel == null) {
            LOG.error("[ActivitiBaseCommonService.getAllTaskVariables]BpmnModel为null！processDefinitionId：" + processDefinitionId);
            return variables;
        }

        List<Process> processes = processBpmnModel.getProcesses();
        if (processes == null || processes.size() <= 0) {
            LOG.error("[ActivitiBaseCommonService.getAllTaskVariables]processes为null！processDefinitionId：" + processDefinitionId);
            return variables;
        }

        for (Process process : processes) {
            Collection<FlowElement> flowElements = process.getFlowElements();
            if (flowElements == null || flowElements.size() <= 0) {
                LOG.error("[ActivitiBaseCommonService.getAllTaskVariables]flowElements为null！processDefinitionId：" + processDefinitionId);
                return variables;
            }

            for (FlowElement flowElement : flowElements) {
                //如果是UserTask类型
                if (flowElement instanceof UserTask) {
                    Map<String, Object> mapValue = new HashMap<>();
                    Map<String, List<ExtensionElement>> extensionElementMap = ((UserTask) flowElement).getExtensionElements();

                    mapValue.put(WFConfig.CUSTOM_PROPERTY_CUR_ROLE_ID, BpmnVariableUtil.codeRoleIds(extensionElementMap));
                    mapValue.put(WFConfig.CUSTOM_PROPERTY_CUR_ORGANIZATION_ID, BpmnVariableUtil.codeOrganizationIds(extensionElementMap));
                    mapValue.put(WFConfig.CUSTOM_PROPERTY_NEXT_TASK_INFO, BpmnVariableUtil.codeTNextTaskInfos(extensionElementMap));

                    mapValue.put(WFConfig.CUSTOM_PROPERTY_CAN_UPDATE_ATT, BpmnVariableUtil.codeCanUpdateAtt(extensionElementMap));
                    mapValue.put(WFConfig.CUSTOM_PROPERTY_CAN_DOWNLOAD_ATT, BpmnVariableUtil.codeCanDownloadAtt(extensionElementMap));
                    mapValue.put(WFConfig.CUSTOM_PROPERTY_CAN_DELETE_ATT, BpmnVariableUtil.codeCanDeleteAtt(extensionElementMap));
                    mapValue.put(WFConfig.CUSTOM_PROPERTY_CAN_READ_ATT, BpmnVariableUtil.codeCanReadAtt(extensionElementMap));
                    mapValue.put(WFConfig.CUSTOM_PROPERTY_CAN_EDIT_PAGE, BpmnVariableUtil.codeCanEditPage(extensionElementMap));
                    mapValue.put(WFConfig.CUSTOM_PROPERTY_CAN_UPLOAD_ATT, BpmnVariableUtil.codeCanUploadAtt(extensionElementMap));
                    mapValue.put(WFConfig.CUSTOM_PROPERTY_CAN_UPDATE_OTHER_ATT, BpmnVariableUtil.codeCanUpdateOtherAtt(extensionElementMap));
                    mapValue.put(WFConfig.CUSTOM_PROPERTY_CAN_DELETE_OTHER_ATT, BpmnVariableUtil.codeCanDeleteOtherAtt(extensionElementMap));
                    mapValue.put(WFConfig.CUSTOM_PROPERTY_HAVE_OPINION_TEXT, BpmnVariableUtil.codeHaveOpinionText(extensionElementMap));
                    mapValue.put(WFConfig.CUSTOM_PROPERTY_HAVE_ADD_SIGN, BpmnVariableUtil.codeHaveAddSign(extensionElementMap));
                    mapValue.put(WFConfig.CUSTOM_PROPERTY_HAVE_DELEGATE, BpmnVariableUtil.codeHaveDelegate(extensionElementMap));
                    mapValue.put(WFConfig.CUSTOM_PROPERTY_CAN_APP_EDIT, BpmnVariableUtil.codeCanAppEdit(extensionElementMap));


                    variables.put(((UserTask) flowElement).getId(), mapValue);
                }
            }
        }

        return variables;
    }

}
