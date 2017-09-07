package org.jeff.javatool.tool.myactiviti;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeff.javatool.tool.myactiviti.config.CustomProcessState;
import org.jeff.javatool.tool.myactiviti.config.TaskCategory;
import org.jeff.javatool.tool.myactiviti.domain.entity.*;
import org.jeff.javatool.tool.myactiviti.entity.TCustomFlow;
import org.jeff.javatool.tool.myactiviti.entity.TCustomProcess;
import org.jeff.javatool.tool.myactiviti.entity.TCustomProcessState;
import org.jeff.javatool.tool.myactiviti.entity.TCustomTask;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryCustomProcessCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;
import org.jeff.javatool.tool.myactiviti.entity.result.ResultCodes;
import org.jeff.javatool.tool.myactiviti.entity.result.ResultFactory;
import org.jeff.javatool.tool.myactiviti.intfc.IProcAutoCompleteJobService;
import org.jeff.javatool.tool.myactiviti.intfc.IProcTimeoutService;
import org.jeff.javatool.tool.myactiviti.intfc.IWFProcCustomService;
import org.jeff.javatool.tool.myactiviti.utils.JsonUtil;
import org.jeff.javatool.tool.myactiviti.utils.ProcJsonUtil;
import org.jeff.javatool.tool.myactiviti.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 自定义流程服务
 * Created by weijianfu on 2017/3/28.
 */
@Service
public class WFProcCustomServiceImpl implements IWFProcCustomService {


    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private WFProcessProviderImpl processProvider;
    @Autowired
    private IProcAutoCompleteJobService procJobService;
    @Autowired
    private WFBaseCommonService baseCommonService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private IProcTimeoutService procTimeoutService;

    private Log log = LogFactory.getLog(this.getClass());


    @Override
    public Result<Pager<TProcCustom>> queryList(QueryCustomProcessCondition condition, Map<String, Object> ex) {
        if (condition == null || condition.getLimit() == null || condition.getOffset() == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        try {
            int totalCount = baseCommonService.countTProcCustomList(condition);
            List<TProcCustom> procCustomList = baseCommonService.queryTProcCustomList(condition);

            //获取form信息
//            List<Long> formDefKeyList = getFormDefKeyList(procCustomList);
//            Map<Long, CaihReForm> mapFormKey2CaihReForm = caihReFormService.selectMapFormKey2CaihReFormByKeyColl(formDefKeyList);
            //填充form信息
//            List<TProcCustom> procCustomListResult = parseTProcCustomList(procCustomList, mapFormKey2CaihReForm);

//            return ResultFactory.success(new Pager<>(condition.getOffset(), condition.getLimit(), totalCount, procCustomListResult));
            return ResultFactory.success(new Pager<>(condition.getOffset(), condition.getLimit(), totalCount, procCustomList));
        } catch (Throwable e) {
            log.error("[ProcCustomServiceImpl.queryList]Error!入参 condition:" + JsonUtil.serialize(condition)
                    + "; ex:" + JsonUtil.serialize(ex), e);
            return ResultFactory.exception(e.getMessage());
        }
    }

//    private List<TProcCustom> parseTProcCustomList(List<TProcCustom> procCustomList, Map<Long, CaihReForm> mapFormKey2CaihReForm) {
//        if (CollectionUtils.isEmpty(procCustomList)) {
//            return procCustomList;
//        }
//        List<TProcCustom> procCustomListResult = Lists.newArrayList();
//        for (TProcCustom tProcCustom : procCustomList) {
//            if (!StringUtil.isBlank(tProcCustom.getFormDefKey())) {
//                CaihReForm caihReForm = mapFormKey2CaihReForm.get(Long.valueOf(tProcCustom.getFormDefKey()));
//                if (caihReForm != null) {
//                    tProcCustom.setFormName(caihReForm.getFormName());
//                    tProcCustom.setFormId(caihReForm.getFormDefId());
//                    tProcCustom.setFormDefKey(caihReForm.getFormDefKey() == null ? null : caihReForm.getFormDefKey().toString());
//                }
//            }
//            procCustomListResult.add(tProcCustom);
//        }
//        return procCustomListResult;
//    }

//    private List<Long> getFormDefKeyList(List<TProcCustom> procCustomList) {
//        List<Long> formDefKeyList = Lists.newArrayList();
//        if (procCustomList == null || procCustomList.size() <= 0) {
//            return formDefKeyList;
//        }
//        for (TProcCustom tProcCustom : procCustomList) {
//            if (!StringUtil.isBlank(tProcCustom.getFormDefKey())) {
//                formDefKeyList.add(Long.valueOf(tProcCustom.getFormDefKey()));
//            }
//        }
//        return formDefKeyList;
//    }

    /**
     * 添加
     */
    @Override
    public Result<Integer> insert(TProcCustom procCustom, Map<String, Object> ex) {
        if (procCustom == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        try {
            int insert = baseCommonService.insertTProcCustom(procCustom);
            return ResultFactory.success(insert);
        } catch (Throwable e) {
            log.error("[ProcCustomServiceImpl.insert] Error!" +
                    "procCustom:" + JsonUtil.serialize(procCustom) + "; " +
                    "ex:" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    /**
     * 修改（根据主键ID修改）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Integer> update(TProcCustom procCustom, Map<String, Object> ex) {
        if (procCustom == null
                || procCustom.getProcDefKey() == null
                || procCustom.getProcDefKey().length() <= 0
                || procCustom.getVersion() == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }

        TProcCustomKey key = new TProcCustomKey();
        key.setProcDefKey(procCustom.getProcDefKey());
        key.setVersion(procCustom.getVersion());
        TProcCustom tProcCustom = baseCommonService.selectTProcCustomByPrimaryKey(key);
        if (CustomProcessState.EFFECTIVE.getState().equals(tProcCustom.getState())) {
            return ResultFactory.error("该流程为启用状态，无法修改");
        } else if (CustomProcessState.DELETED.getState().equals(tProcCustom.getState())) {
            return ResultFactory.error("该流程为删除状态，无法修改");
        }


        try {
            int update = baseCommonService.updateTProcCustom(procCustom);
            return ResultFactory.success(update);
        } catch (Throwable e) {
            log.error("[ProcCustomServiceImpl.update] Error!" +
                    "procCustom:" + JsonUtil.serialize(procCustom) + "; " +
                    "ex:" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Integer> updateState(TCustomProcessState processState, Map<String, Object> ex) {
        if (processState == null
                || processState.getProcDefKey() == null
                || processState.getProcDefKey().length() <= 0
                || processState.getState() == null
                || processState.getState().length() <= 0) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }

        TProcCustomKey key = new TProcCustomKey();
        key.setProcDefKey(processState.getProcDefKey());
        key.setVersion(processState.getVersion());
        TProcCustom tProcCustom = baseCommonService.selectTProcCustomByPrimaryKey(key);
        if (processState.getState().equals(tProcCustom.getState())) {
            return ResultFactory.error("修改状态与现有状态一致，无需修改");
        }

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processState.getProcDefKey())
                .latestVersion().singleResult();

        try {
            if (CustomProcessState.EFFECTIVE.getState().equals(processState.getState())) {//启用
                TProcCustomJson tProcCustomJson = JsonUtil.deserialize(tProcCustom.getJson(), TProcCustomJson.class);
                if (!StringUtil.isBlank(tProcCustom.getJson()) && tProcCustomJson == null) {
                    return ResultFactory.error("该记录的json字段内容，反序列化失败");
                }
                TCustomProcess tCustomProcess = TCustomProcess.build(tProcCustom, tProcCustomJson);
                List<org.activiti.bpmn.model.Process> processList = ProcJsonUtil.getProcessList(tCustomProcess);
                if (processList == null || processList.size() <= 0) {
                    return ResultFactory.error("该记录的json字段内容错误，processList解析失败");
                }
                if (StringUtils.isBlank(tProcCustom.getFormDefKey())) {
                    return ResultFactory.error("该记录没有关联表单，请关联表单后再激活");
                }

                String procDefId = processProvider.generateProcessDefinitionWithProcDefId(tCustomProcess.getProcDefCategory()
                        , tCustomProcess.getProcDefName()//部署名和定义名一致
                        , processList
                        , null);

                //保存定时任务信息
                saveJob(tCustomProcess, procDefId);
                //保存任务定时标记信息
                saveTaskTimeout(tCustomProcess, procDefId);
                //表单与流程Id的关联
//                saveForm(tProcCustom, tCustomProcess, procDefId);


            } else if (CustomProcessState.INVALID.getState().equals(processState.getState())) {//停用
                if (processDefinition != null) {
                    if (processDefinition.isSuspended()) {
                        return ResultFactory.error("该流程定义已经是停用状态");
                    }
                    //失效流程定义
                    repositoryService.suspendProcessDefinitionById(processDefinition.getId());
                    //失效关联
//                    CaihReFormProcessExample example = new CaihReFormProcessExample();
//                    example.createCriteria().andProcDefIdEqualTo(processDefinition.getId())
//                            .andStatusEqualTo(CaihReDictConstant.COMMON_STATUS.VALID);
//                    List<CaihReFormProcess> caihReFormProcesses = caihReFormProcessService.selectByExample(example);
//                    if (caihReFormProcesses != null && caihReFormProcesses.size() > 0) {
//                        CaihReFormProcess caihReFormProcess = caihReFormProcesses.get(0);
//                        caihReFormProcess.setStatus(CaihReDictConstant.COMMON_STATUS.INVALID);
//                        caihReFormProcessService.updateByPrimaryKeySelective(caihReFormProcess);
//                    }
                } else {
                    return ResultFactory.error("不存在对应的流程定义，无法执行停用操作");
                }
            } else if (CustomProcessState.DELETED.getState().equals(processState.getState())) {//删除
                if (processDefinition != null) {
                    return ResultFactory.error("已生成流程的自定义数据不允许删除");
                }
            }
            baseCommonService.updateTProcCustomState(processState);

            return ResultFactory.success(processState.getVersion());
        } catch (Throwable e) {
            log.error("[ProcCustomServiceImpl.update] Error!" +
                    "processState:" + JsonUtil.serialize(processState) + "; " +
                    "ex:" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }

    }

    @Override
    public Result<TCustomProcess> queryStaticProcJson(String procDefId, Map<String, Object> ex) {
        if (procDefId == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        try {
            BpmnModel processBpmnModel = repositoryService.getBpmnModel(procDefId);
            List<Process> processes = processBpmnModel.getProcesses();
            TCustomProcess tCustomProcess = ProcJsonUtil.getTCustomProcess(processes);

            return ResultFactory.success(tCustomProcess);
        } catch (Throwable e) {
            log.error("[ProcCustomServiceImpl.queryStaticProcJson] Error!入参"
                    + "procDefId:" + procDefId + "; "
                    + "ex:" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }


    @Override
    public Result<TCustomProcess> queryDynamicProcJson(String procInstId, Map<String, Object> ex) {
        if (procInstId == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        try {
            List<HistoricTaskInstance> hisTaskListQuery = historyService.createHistoricTaskInstanceQuery()
                    .processInstanceId(procInstId).orderByTaskCreateTime().asc().list();
            List<HistoricTaskInstance> hisTaskList = getProcTaskList(hisTaskListQuery);//获取流程定义中的任务
            if (hisTaskList == null || hisTaskList.size() <= 0) {
                return ResultFactory.error("该流程实例【" + procInstId + "】没有查询到流程定义中的任务信息");

            }
            HistoricTaskInstance historicTaskInstance = hisTaskList.get(0);
            String processDefinitionId = historicTaskInstance.getProcessDefinitionId();

            //获取基础json对象
            BpmnModel processBpmnModel = repositoryService.getBpmnModel(processDefinitionId);
            List<Process> processes = processBpmnModel.getProcesses();
            TCustomProcess tCustomProcess = ProcJsonUtil.getTCustomProcess(processes);

            //填充customVariable参数
            List<HistoricVariableInstance> hisVariableList = historyService.createHistoricVariableInstanceQuery()
                    .processInstanceId(procInstId).list();
            Map<String, Object> variableMap = getVariableMap(hisVariableList);
//            Map<String, Map<String, Object>> customVariable = Maps.newHashMap();
//            for (HistoricTaskInstance taskInstance : hisTaskList) {
//                customVariable.put(taskInstance.getTaskDefinitionKey(), processVariableManageService.chooseAllShowVariable(procInstId, taskInstance.getId(), variableMap));
//            }
//            tCustomProcess.setCustomVariable(customVariable);

            //填充已完成流向信息
            tCustomProcess.setFlowMark(hisTaskList, variableMap);

            return ResultFactory.success(tCustomProcess);
        } catch (Throwable e) {
            log.error("[ProcCustomServiceImpl.queryDynamicProcJson] Error!入参"
                    + "procInstId:" + procInstId + "; "
                    + "ex:" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    private List<HistoricTaskInstance> getProcTaskList(List<HistoricTaskInstance> hisTaskListQuery) {
        List<HistoricTaskInstance> hisTaskList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(hisTaskListQuery)) {
            return hisTaskList;
        }
        for (HistoricTaskInstance historicTaskInstance : hisTaskListQuery) {
            if (TaskCategory.isProcTask(historicTaskInstance)) {
                hisTaskList.add(historicTaskInstance);
            }
        }
        return hisTaskList;
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

    private void saveJob(TCustomProcess tCustomProcess, String procDefId) {
        List<ProcJob> jobList = new ArrayList<>();
        String procDefKey = tCustomProcess.getProcDefKey();
        //更新原来流程实例对应的Job的状态为无效
        ProcJob record = new ProcJob();
        record.setProcDefKey(procDefKey);
        record.setState("0");
        procJobService.update(record, null);
        Map<String, TCustomFlow> flows = tCustomProcess.getFlows();
        for (Map.Entry<String, TCustomFlow> entry : flows.entrySet()) {
            TCustomFlow flow = entry.getValue();
            if (flow.getCanAuto()) {
                ProcJob job = new ProcJob();
                jobList.add(job);
                job.setState("1");
                job.setProcDefId(procDefId);
                job.setProcDefKey(procDefKey);
                job.setTaskDefKey(flow.getCurTaskDefKey());
                job.setFlowType(flow.getFlowType());
                job.setFlowName(flow.getFlowName());
                job.setAutoTime(flow.getAutoTime());
            }
        }
        procJobService.saveOrUpdate(jobList, null);
    }

    private void saveTaskTimeout(TCustomProcess tCustomProcess, String procDefId) {
        if (tCustomProcess == null || tCustomProcess.getTasks() == null || tCustomProcess.getTasks().isEmpty()) {
            return;
        }
        Collection<TCustomTask> customTaskColl = tCustomProcess.getTasks().values();
        for (TCustomTask tCustomTask : customTaskColl) {
            if (tCustomTask.getTaskTimeout() == null || tCustomTask.getTaskTimeout() <= 0L) {
                continue;
            }
            TProcTimeout procTimeout = new TProcTimeout();
            procTimeout.setProcDefId(procDefId);
            procTimeout.setTaskDefKey(tCustomTask.getDefKey());
            procTimeout.setTime(tCustomTask.getTaskTimeout());
            procTimeoutService.insert(procTimeout, null);
        }

    }

//    private void saveForm(TProcCustom tProcCustom, TCustomProcess tCustomProcess, String procDefId) throws Exception {
//        CaihReForm caihReForm = caihReFormService.selectCaihReFormByKey(Long.valueOf(tProcCustom.getFormDefKey()));
//        if (caihReForm != null) {
//            CaihReFormProcess caihReFormProcess = new CaihReFormProcess();
//            caihReFormProcess.setStatus(CaihReDictConstant.COMMON_STATUS.INVALID);
//            caihReFormProcess.setFormDefKey(caihReForm.getFormDefKey());
//            caihReFormProcess.setFormName(caihReForm.getFormName());
//            caihReFormProcess.setFormDefId(caihReForm.getFormDefId());
//            caihReFormProcess.setProcDefKey(tCustomProcess.getProcDefKey());
//            caihReFormProcess.setProcName(tCustomProcess.getProcDefName());
//            caihReFormProcess.setProcDefId(procDefId);
//            caihReFormProcessService.changeFormProcessRela(caihReFormProcess);
//        } else {
//            throw new Exception("通过表单KEY查询不到有效状态的表单信息");
//        }
//    }
}
