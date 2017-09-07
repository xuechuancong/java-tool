package org.jeff.javatool.tool.myactiviti;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.jeff.javatool.tool.myactiviti.config.QueryHistoricInstanceOrderKeyType;
import org.jeff.javatool.tool.myactiviti.config.QueryHistoricTaskOrderKeyType;
import org.jeff.javatool.tool.myactiviti.config.QueryOrderType;
import org.jeff.javatool.tool.myactiviti.config.WFConfig;
import org.jeff.javatool.tool.myactiviti.entity.THistoricInstanceList;
import org.jeff.javatool.tool.myactiviti.entity.THistoricTaskList;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryHistoricInstanceCondition;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryHistoricTaskCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;
import org.jeff.javatool.tool.myactiviti.entity.result.ResultFactory;
import org.jeff.javatool.tool.myactiviti.intfc.IWFHistoryProvider;
import org.jeff.javatool.tool.myactiviti.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 工作流-历史数据查询服务
 * Created by weijianfu on 2016/11/25.
 */
@Service
public class WFHistoryProviderImpl implements IWFHistoryProvider {

    @Autowired
    private HistoryService historyService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;

    private static final Logger LOG = LoggerFactory.getLogger(WFHistoryProviderImpl.class);


    @Override
    public Result<Pager<THistoricTaskList>> queryHistoricTaskList(QueryHistoricTaskCondition condition, int offset, int limit, Map<String, Object> ex) {
        try {
            /**
             * 拼装查询条件
             */
            HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery();
            if (condition != null) {
                if (condition.getTaskId() != null) {//任务ID
                    query = query.taskId(condition.getTaskId());
                }
                if (condition.getAssignee() != null) {//执行者的userId
                    query.taskAssignee(condition.getAssignee());
                }

                if (condition.getIsProcFinished() != null) {//流程是否结束
                    if (condition.getIsProcFinished()) {
                        query.processFinished();
                    } else {
                        query.processUnfinished();
                    }
                }
                if (condition.getProcCategoryList() != null) {//流程类型
                    query.processCategoryIn(condition.getProcCategoryList());
                }
                if (condition.getProcNameLike() != null) {//流程名称模糊
                    query.processDefinitionNameLike(condition.getProcNameLike());
                }
                if (condition.getProcInstanceId() != null) {//流程实例ID
                    query.processInstanceId(condition.getProcInstanceId());
                }
                if (condition.getTaskTitleLike() != null) {//任务标题模糊
                    query.processVariableValueLike(WFConfig.V_WAITTING_DEAL_NAME, condition.getTaskTitleLike());
                }
                if (condition.getApplyMinTime() != null) {//申请时间最小值
                    query.processVariableValueGreaterThan(WFConfig.V_START_TIME, condition.getApplyMinTime().getTime());
                }
                if (condition.getApplyMaxTime() != null) {//申请时间最大值
                    query.processVariableValueLessThanOrEqual(WFConfig.V_START_TIME, condition.getApplyMaxTime().getTime());
                }
                if (condition.getApplyAssigneeId() != null) {//申请人ID
                    query.processVariableValueEquals(WFConfig.V_PROCESS_START_STAFF_ID, condition.getApplyAssigneeId());
                }
                if (condition.getApplyAssigneeLike() != null) {//申请人名称模糊
                    query.processVariableValueLike(WFConfig.V_PROCESS_START_STAFF, condition.getApplyAssigneeLike());
                }
                if (condition.getApplyAssigneeDepartmentLike() != null) {//申请人部门名称模糊
                    query.processVariableValueLike(WFConfig.V_ORG_NAME, condition.getApplyAssigneeDepartmentLike());
                }
                if (condition.getAdvancedSearch() != null) {//高级搜索
                    String advancedSearchLike = "%" + condition.getAdvancedSearch() + "%";
                    query.or().processCategoryIn(Lists.newArrayList(condition.getAdvancedSearch()))//流程类型
                            .processDefinitionNameLike(advancedSearchLike)//流程名称模糊
                            .processInstanceId(condition.getAdvancedSearch())//流程ID
                            .processVariableValueLike(WFConfig.V_WAITTING_DEAL_NAME, advancedSearchLike)//任务标题模糊
                            .processVariableValueLike(WFConfig.V_PROCESS_START_STAFF, advancedSearchLike)//申请人名称模糊
                            .processVariableValueEquals(WFConfig.V_PROCESS_START_STAFF_ID, condition.getAdvancedSearch())
                            .processVariableValueLike(WFConfig.V_ORG_NAME, advancedSearchLike).endOr();//申请人部门名称模糊
                }


                if (condition.getOrderKeyType() != null) {//排序关键字类型
                    if (QueryHistoricTaskOrderKeyType.TASK_ID.equals(condition.getOrderKeyType())) {
                        query = query.orderByTaskId();
                    } else if (QueryHistoricTaskOrderKeyType.PROCESS_INSTANCE_ID.equals(condition.getOrderKeyType())) {
                        query = query.orderByProcessInstanceId();
                    } else if (QueryHistoricTaskOrderKeyType.TASK_CREATE_TIME.equals(condition.getOrderKeyType())) {
                        query = query.orderByTaskCreateTime();
                    } else if (QueryHistoricTaskOrderKeyType.TASK_END_TIME.equals(condition.getOrderKeyType())) {
                        query = query.orderByHistoricTaskInstanceEndTime();
                    }

                    if (condition.getOrderType() != null) {//排序类型
                        if (QueryOrderType.ASC.equals(condition.getOrderType())) {
                            query = query.asc();
                        } else if (QueryOrderType.DESC.equals(condition.getOrderType())) {
                            query = query.desc();
                        }
                    }
                }
            }
            //获取任务历史
            List<HistoricTaskInstance> historicTaskInstanceList = query.listPage(offset, limit);
            //获取所有的流程实例Id
            Set<String> processInstanceIds = getProcessInstanceIds(historicTaskInstanceList);

            List<HistoricVariableInstance> historicVariableInstances = Lists.newArrayList();//历史流程变量集合
            List<HistoricProcessInstance> processInstanceList = Lists.newArrayList();//所属的流程实例集合
            if (processInstanceIds != null && processInstanceIds.size() > 0) {
                processInstanceList = historyService.createHistoricProcessInstanceQuery().processInstanceIds(processInstanceIds).list();
                for (String processInstanceId : processInstanceIds) {
                    //获取历史流程变量
                    List<HistoricVariableInstance> curHis = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
                    historicVariableInstances.addAll(curHis);
                }
            }

            //拼装信息
            List<THistoricTaskList> tHistoricTaskLists =
                    THistoricTaskList.parseList(historicTaskInstanceList, historicVariableInstances, processInstanceList);

            List<HistoricTaskInstance> tHistoricTaskListForCount = query.listPage(0, Integer.MAX_VALUE);
            int count = tHistoricTaskListForCount == null ? 0 : tHistoricTaskListForCount.size();
            return ResultFactory.success(new Pager<>(offset, limit, count, tHistoricTaskLists));
        } catch (Throwable e) {
            LOG.error("[WFHistoryProviderImpl.queryHistoricTaskList] Error!入参"
                    + "condition:" + JsonUtil.serialize(condition)
                    + "offset:" + offset + "; "
                    + "limit:" + limit + "; "
                    + "ex:" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    public Result<Pager<THistoricInstanceList>> queryHistoricInstanceList(QueryHistoricInstanceCondition condition, int offset, int limit, Map<String, Object> ex) {
        try {
            /**
             * 拼装查询条件
             */
            HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
            if (condition != null) {

                if (condition.getIsProcFinished() != null) {//流程是否结束
                    if (condition.getIsProcFinished()) {
                        query.finished();
                    } else {
                        query.unfinished();
                    }
                }
                if (condition.getInvolvedUserExceptDoing() != null) {
                    query.variableValueLike(WFConfig.V_FINISHED_TASK_USER_LIST, condition.getInvolvedUserExceptDoing());
                }
                if (condition.getProcessDefinitionKey() != null) {//涉及的UserId
                    query.processDefinitionKey(condition.getProcessDefinitionKey());
                }
                if (condition.getInvolvedUser() != null) {//涉及的UserId
                    query.involvedUser(condition.getInvolvedUser());
                }
                if (condition.getProcCategory() != null) {//流程类型
                    query.processDefinitionCategory(condition.getProcCategory());
                }
                if (condition.getProcDefNameLike() != null) {//流程定义名称模糊，其实是流程实例名称
                    query.processInstanceNameLike(condition.getProcDefNameLike());
                }
                if (condition.getProcInstanceId() != null) {//流程实例ID
                    query.processInstanceId(condition.getProcInstanceId());
                }
                if (condition.getTaskTitleLike() != null) {//任务标题模糊
                    query.variableValueLike(WFConfig.V_WAITTING_DEAL_NAME, condition.getTaskTitleLike());
                }
                if (condition.getApplyMinTime() != null) {//申请时间最小值
                    query.variableValueGreaterThanOrEqual(WFConfig.V_START_TIME, condition.getApplyMinTime().getTime());
                }
                if (condition.getApplyMaxTime() != null) {//申请时间最大值
                    query.variableValueLessThanOrEqual(WFConfig.V_START_TIME, condition.getApplyMaxTime().getTime());
                }
                if (condition.getApplyAssigneeId() != null) {//申请人ID模糊
                    query.variableValueEquals(WFConfig.V_PROCESS_START_STAFF_ID, condition.getApplyAssigneeId());
                }
                if (condition.getApplyAssigneeLike() != null) {//申请人名称模糊
                    query.variableValueLike(WFConfig.V_PROCESS_START_STAFF, condition.getApplyAssigneeLike());
                }
                if (condition.getApplyAssigneeDepartmentLike() != null) {//申请人部门名称模糊
                    query.variableValueLike(WFConfig.V_ORG_NAME, condition.getApplyAssigneeDepartmentLike());
                }
                if (condition.getDeleted() != null) {//是否是已删除
                    if (condition.getDeleted()) {
                        query.deleted();
                    } else {
                        query.notDeleted();
                    }
                }
                if (condition.getAdvancedSearch() != null) {//高级搜索
                    String advancedSearchLike = "%" + condition.getAdvancedSearch() + "%";
                    query.or().processDefinitionCategory(condition.getAdvancedSearch())//流程类型
                            .processInstanceNameLike(advancedSearchLike)//流程名称
                            .processInstanceId(condition.getAdvancedSearch())//流程ID
                            .variableValueLike(WFConfig.V_WAITTING_DEAL_NAME, advancedSearchLike)//任务标题模糊
                            .variableValueLike(WFConfig.V_PROCESS_START_STAFF, advancedSearchLike)//申请人名称模糊
                            .variableValueEquals(WFConfig.V_PROCESS_START_STAFF_ID, condition.getAdvancedSearch())//申请人ID
                            .variableValueLike(WFConfig.V_ORG_NAME, advancedSearchLike).endOr();//申请人部门名称模糊
                }


                if (condition.getOrderKeyType() != null) {//排序关键字类型
                    if (QueryHistoricInstanceOrderKeyType.PROCESS_INSTANCE_ID.equals(condition.getOrderKeyType())) {
                        query = query.orderByProcessInstanceId();
                    } else if (QueryHistoricInstanceOrderKeyType.PROCESS_INSTANCE_START_TIME.equals(condition.getOrderKeyType())) {
                        query = query.orderByProcessInstanceStartTime();
                    } else if (QueryHistoricInstanceOrderKeyType.PROCESS_INSTANCE_END_TIME.equals(condition.getOrderKeyType())) {
                        query = query.orderByProcessInstanceEndTime();
                    }

                    if (condition.getOrderType() != null) {//排序类型
                        if (QueryOrderType.ASC.equals(condition.getOrderType())) {
                            query = query.asc();
                        } else if (QueryOrderType.DESC.equals(condition.getOrderType())) {
                            query = query.desc();
                        }
                    }
                }
            }
            //获取任务历史
            List<HistoricProcessInstance> historicProcessInstanceList = query.listPage(offset, limit);
            //获取所有的流程实例Id
            Set<String> processInstanceIds = getProcessInstanceIdsByInstance(historicProcessInstanceList);
            //获取所有的流程定义Id
            Set<String> processDefinitionIds = getProcessDefinitionIds(historicProcessInstanceList);

            List<HistoricVariableInstance> historicVariableInstances = Lists.newArrayList();//历史流程变量集合
            List<Task> curTaskList = Lists.newArrayList();//每一个实例正在进行的任务集合
            List<ProcessDefinition> processDefinitionList = Lists.newArrayList();//流程定义集合
            if (processInstanceIds != null && processInstanceIds.size() > 0) {
                processDefinitionList = repositoryService.createProcessDefinitionQuery().processDefinitionIds(processDefinitionIds).list();
                for (String processInstanceId : processInstanceIds) {
                    //获取历史流程变量
                    List<HistoricVariableInstance> curHis = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
                    historicVariableInstances.addAll(curHis);
                    //获取每个流程实例正在进行的任务
                    if (condition.getIsProcFinished() == null || !condition.getIsProcFinished()) {//流程未完成的情况下才需要填充curTasks
                        List<Task> list = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
                        if (list != null && list.size() > 0) {
                            curTaskList.addAll(list);
                        }
                    }
                }
            }

            //拼装信息
            List<THistoricInstanceList> historicInstanceLists =
                    THistoricInstanceList.parseList(historicVariableInstances, curTaskList, historicProcessInstanceList, processDefinitionList);
            List<HistoricProcessInstance> historicInstanceListForCount = query.listPage(0, Integer.MAX_VALUE);
            int count = historicInstanceListForCount == null ? 0 : historicInstanceListForCount.size();
            return ResultFactory.success(new Pager<>(offset, limit, count, historicInstanceLists));
        } catch (Throwable e) {
            LOG.error("[WFHistoryProviderImpl.queryHistoricInstanceList] Error!入参"
                    + "condition:" + JsonUtil.serialize(condition)
                    + "offset:" + offset + "; "
                    + "limit:" + limit + "; "
                    + "ex:" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    private Set<String> getProcessDefinitionIds(List<HistoricProcessInstance> historicProcessInstanceList) {
        Set<String> processDefinitionIds = Sets.newHashSet();
        if (historicProcessInstanceList == null || historicProcessInstanceList.size() <= 0) {
            return processDefinitionIds;
        }
        for (HistoricProcessInstance historicProcessInstance : historicProcessInstanceList) {
            processDefinitionIds.add(historicProcessInstance.getProcessDefinitionId());
        }
        return processDefinitionIds;
    }

    private Set<String> getProcessInstanceIds(List<HistoricTaskInstance> historicTaskInstanceList) {
        Set<String> taskIds = Sets.newHashSet();
        if (historicTaskInstanceList == null || historicTaskInstanceList.size() <= 0) {
            return taskIds;
        }
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstanceList) {
            taskIds.add(historicTaskInstance.getProcessInstanceId());
        }
        return taskIds;
    }

    private Set<String> getProcessInstanceIdsByInstance(List<HistoricProcessInstance> historicProcessInstanceList) {
        Set<String> processInstanceIds = Sets.newHashSet();
        if (historicProcessInstanceList == null || historicProcessInstanceList.size() <= 0) {
            return processInstanceIds;
        }
        for (HistoricProcessInstance historicProcessInstance : historicProcessInstanceList) {
            processInstanceIds.add(historicProcessInstance.getId());
        }
        return processInstanceIds;
    }
}
