package org.jeff.javatool.tool.myactiviti;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.SuspensionState;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.jeff.javatool.tool.myactiviti.config.*;
import org.jeff.javatool.tool.myactiviti.entity.*;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryTaskCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;
import org.jeff.javatool.tool.myactiviti.entity.result.ResultCodes;
import org.jeff.javatool.tool.myactiviti.entity.result.ResultFactory;
import org.jeff.javatool.tool.myactiviti.intfc.IWFTaskProvider;
import org.jeff.javatool.tool.myactiviti.utils.JsonUtil;
import org.jeff.javatool.tool.myactiviti.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 工作流-任务相关服务
 * Created by weijianfu on 2016/11/22.
 */
@Service
public class WFTaskProviderImpl implements IWFTaskProvider {

    @Autowired
    private WFBaseCommonService baseCommonService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;

    private static final Logger LOG = LoggerFactory.getLogger(WFTaskProviderImpl.class);

    @Override
    public Result<TProcessInstance> generateTask(String processDefinitionKey, String userId, Map<String, Object> variables, Map<String, Object> ex) {
        LOG.info("[WFTaskProviderImpl.generateTask]入参"
                + "processDefinitionKey:" + processDefinitionKey + "; "
                + "userId:" + userId + "; "
                + "variables:" + JsonUtil.serialize(variables) + "; "
                + "ex:" + JsonUtil.serialize(ex) + ";");
        try {
            if (variables == null) {
                variables = new HashMap<>();
            }
            variables.put(WFConfig.V_START_TIME, new Date().getTime());//任务开启时间（毫秒数,long）

            /**启动流程实例的同时，设置流程变量，使用流程变量用来指定任务的办理人，对应task.pbmn文件中#{userID}*/

            //执行者集合，因为NORMAL任务也是多实例任务
            variables.put(WFConfig.V_COUNTER_SIGN_ACTIVITI_COLLECTION, Lists.newArrayList(userId));

            //使用流程定义的key启动流程实例，key对应helloworld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
            ExecutionEntity pi = (ExecutionEntity) runtimeService
                    .startProcessInstanceByKey(processDefinitionKey, variables);
            runtimeService.setProcessInstanceName(pi.getId(), pi.getProcessDefinition() == null ? "未找到流程定义" : pi.getProcessDefinition().getName());
            //填充Bpmn文件中包含的参数，否则无法生成参数
            fillBpmnVariables(pi.getProcessInstanceId(), pi.getProcessDefinitionId());
            //开启任务
            List<Task> list = taskService
                    .createTaskQuery().processInstanceId(pi.getProcessInstanceId()).list();
            Task task = list.get(0);
            taskService.setAssignee(task.getId(), userId);
            return ResultFactory.success(new TProcessInstance(pi));
        } catch (Throwable e) {
            LOG.error("[WFTaskProviderImpl.generateTask] Error!入参"
                    + "processDefinitionKey:" + processDefinitionKey + "; "
                    + "userId:" + userId + "; "
                    + "variables:" + JsonUtil.serialize(variables) + "; "
                    + "ex:" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    public Result<List<Task>> completeTask(String processInstanceId,
                                           Collection<NextTaskInfo> nextTaskInfos,
                                           String taskId,
                                           String userOperation,
                                           Map<String, Object> variablesAdd,
                                           Map<String, Object> ex) {
        LOG.info("[WFTaskProviderImpl.completeTask]入参"
                + "taskId:" + taskId + "; "
                + "processInstanceId:" + processInstanceId + "; "
                + "userOperation:" + userOperation + "; "
                + "nextTaskInfos:" + JsonUtil.serialize(nextTaskInfos) + "; "
                + "variablesAdd:" + JsonUtil.serialize(variablesAdd) + "; "
                + "ex:" + JsonUtil.serialize(ex) + "; ");
        if (processInstanceId == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        if (variablesAdd == null) {
            variablesAdd = new HashMap<>();
        }

        try {
            TaskEntity curTask = (TaskEntity) taskService.createTaskQuery().taskId(taskId).singleResult();
            if (curTask == null) {
                return ResultFactory.build(ResultCodes.PARAM_ILLEGAL);
            }
            /** 当前任务为委托任务 */
            if (TaskCategory.DELEGATE.getCategory().equals(curTask.getCategory())
                    || TaskCategory.NORMAL.getCategory().equals(curTask.getCategory())) {
                dealParallelTask(curTask);
            }
            //获取后文需要的所有变量集
            Map<String, Object> variablesNR = taskService.getVariables(taskId
                    , Lists.newArrayList(WFConfig.V_NR_OF_COMPLETED_INSTANCES
                    , WFConfig.V_SELF_AGREE_INSTANCES
                    , WFConfig.V_SELF_COMPLETED_INSTANCES
                    , WFConfig.V_MARK_TIMEOUT));


            //处理多实例任务逻辑:会签、并行审签、加签、委托
            dealMulInstancesTask(nextTaskInfos, taskId, userOperation, variablesAdd, variablesNR);

            //校对是否为异常结束，并处理
            boolean abnormalProc = isAbnormalProc(nextTaskInfos, userOperation);

            //处理流程其它变量
            dealVariable(variablesAdd, curTask, userOperation, abnormalProc, variablesNR);

            //完成任务
            taskService.complete(taskId, variablesAdd);

            //根据processInstanceId查询出正在执行的任务
//            List<Task> taskList = taskService
//                    .createTaskQuery().processInstanceId(processInstanceId).list();

            //如果流程实例结束，则调用自定义表单接口记录流程实例结束状态
//            if (taskList == null || taskList.size() <= 0) {
//                if (abnormalProc) {//异常结束
//                    caihRuFormService.abnormalComplete(processInstanceId);
//                } else {//正常结束
//                    caihRuFormService.normalComplete(processInstanceId);
//                }
//            }

            List<Task> newTasks = taskService.createTaskQuery().processInstanceId(curTask.getProcessInstanceId()).list();
            //设置任务的执行者，并返回新开启的任务
//            List<Task> newTasks = setAssignee(taskList, nextTaskInfos, userOperation, curTask);
            return ResultFactory.success(newTasks);
        } catch (Throwable e) {
            LOG.error("[WFTaskProviderImpl.completeTask] Error!入参"
                    + "taskId:" + taskId + "; "
                    + "processInstanceId:" + processInstanceId + "; "
                    + "userOperation:" + userOperation + "; "
                    + "nextTaskInfos:" + JsonUtil.serialize(nextTaskInfos) + "; "
                    + "variablesAdd:" + JsonUtil.serialize(variablesAdd) + "; "
                    + "ex:" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    public Result<Void> setAssignee(String taskId, String userId, Map<String, Object> ex) {
        if (StringUtil.isBlank(taskId) || StringUtil.isBlank(userId)) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        try {
            taskService.setAssignee(taskId, userId);
            return ResultFactory.success();
        } catch (Throwable e) {
            LOG.error("[WFTaskProviderImpl.setAssignee] Error!入参"
                    + "taskId:" + taskId + "; "
                    + "userId:" + userId + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    private void dealVariable(Map<String, Object> variablesAdd, TaskEntity curTask, String userOperation, boolean abnormalProc, Map<String, Object> variablesNR) {
        //加入操作类型
        variablesAdd.put(WFConfig.V_TASK_OPERATION_KEY, userOperation);

        /**记录流向历史、操作值，用于显示动态流程图
         *  使用处参见 {@link org.jeff.javatool.tool.myactiviti.entity.TCustomProcess#setFlowMark} **/
        Map<String, Object> variables = getVariables(curTask);
        String hisFlowOperation = (String) variables.get(WFConfig.V_HIS_TASK_OPERATION);
        if (hisFlowOperation == null) {
            hisFlowOperation = "";
        }
        StringBuilder sb = new StringBuilder();
        hisFlowOperation = sb.append(hisFlowOperation).append(',')
                .append(curTask.getTaskDefinitionKey()).append("_")
                .append(userOperation).toString();
        variablesAdd.put(WFConfig.V_HIS_TASK_OPERATION, hisFlowOperation);


        //加入已完成任务的处理人，用于搜索
        variablesAdd.put(WFConfig.V_FINISHED_TASK_USER_LIST, getFinishedTaskUserList(curTask));

        if (abnormalProc) {
            variablesAdd.put(WFConfig.V_ABNORMAL_PROC, WFConfig.V_ABNORMAL_PROC);//流程实例中加入标记变量，用于搜索
        }

        //流程如果超时完成,则在备注中添加超时内容
//        int timeOut = (int) variablesNR.get(WFConfig.V_MARK_TIMEOUT);
//        if (timeOut < 0) {
//            String showVariableKey = processVariableManageService.getShowVariableKey(curTask.getProcessInstanceId(), curTask.getId(), InnerCommonConstant.REMARK);
//            String content = "本任务处理时限为X小时，实际处理时间" + Math.abs(timeOut) + "小时";
//            variablesAdd.put(showVariableKey, content);
//        }
    }

    private boolean isAbnormalProc(Collection<NextTaskInfo> nextTaskInfos, String userOperation) {
        boolean abnormalProc = false;//默认不是异常结束流程
        if (nextTaskInfos != null && nextTaskInfos.size() > 0) {
            for (NextTaskInfo nextTaskInfo : nextTaskInfos) {
                if (WFConfig.END_EVE_DEF_KEY.equals(nextTaskInfo.getTaskDefinitionKey())//下一个任务为结束
                        && equalOperation(userOperation, nextTaskInfo.getOperation())) {//操作值要一致
                    abnormalProc = nextTaskInfo.isAbnormalProc();
                }
            }
        }
        return abnormalProc;
    }


    @Override
    public Result<Void> setTaskDueDate(String taskId, Date dueDate) {
        LOG.info("[WFTaskProviderImpl.setTaskDueDate] 入参"
                + "taskId:" + taskId + "; "
                + "dueDate:" + dueDate);
        if (taskId == null || dueDate == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        try {
            taskService.setDueDate(taskId, dueDate);
            return ResultFactory.success();
        } catch (Exception e) {
            LOG.error("[WFTaskProviderImpl.setTaskDueDate] Error!入参"
                    + "taskId:" + taskId + "; "
                    + "dueDate:" + dueDate, e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    public Result<List<Task>> addSign(String taskId, Collection<THandleTaskInfo> addSignTaskInfos, Map<String, Object> variables, Map<String, Object> ex) {
        LOG.info("[WFTaskProviderImpl.addSign]入参"
                + "taskId:" + taskId + "; "
                + "addSignTaskInfos:" + JsonUtil.serialize(addSignTaskInfos) + "; "
                + "ex:" + JsonUtil.serialize(ex) + "; ");
        if (taskId == null || addSignTaskInfos == null || addSignTaskInfos.size() <= 0) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        List<Task> list = taskService.createTaskQuery().taskId(taskId).list();
        if (list == null || list.size() <= 0) {
            return ResultFactory.build(ResultCodes.PARAM_ILLEGAL);
        }
        List<Task> newTasks = Lists.newArrayList();
        TaskEntity curTask = (TaskEntity) list.get(0);
        try {
            for (THandleTaskInfo taskInfo : addSignTaskInfos) {
                TaskEntity task = (TaskEntity) taskService.newTask();

                task.setAssignee(taskInfo.getUserId());
                task.setName(taskInfo.getTaskName());
                task.setParentTaskId(curTask.getId());
                task.setCategory(TaskCategory.ADD_SIGN.getCategory());
                task.setProcessDefinitionId(curTask.getProcessDefinitionId());
                task.setTaskDefinitionKey(curTask.getTaskDefinitionKey() + TaskCategory.ADD_SIGN.getCategory());
                task.setProcessInstanceId(curTask.getProcessInstanceId());

                taskService.saveTask(task);
                //将参与者加入流程历史中
                taskService.addUserIdentityLink(task.getId(), taskInfo.getUserId(), IdentityLinkType.PARTICIPANT);

                newTasks.add(task);
            }

            //更新历史记录，因为历史中信息不正确，所以需要重新设置
            baseCommonService.updateHistoricTaskInstance(curTask.getProcessDefinitionId(), curTask.getProcessInstanceId(), getIds(newTasks));

            //新增/修改流程变量
            if (variables != null && !variables.isEmpty()) {
                taskService.setVariables(taskId, variables);
            }
            return ResultFactory.success(newTasks);
        } catch (Exception e) {
            LOG.error("[WFTaskProviderImpl.addSign] Error!入参"
                    + "taskId:" + taskId + "; "
                    + "addSignTaskInfos:" + JsonUtil.serialize(addSignTaskInfos) + "; "
                    + "ex:" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    public Result<List<Task>> delegate(String taskId, Collection<THandleTaskInfo> delegateTaskInfos, Map<String, Object> variables, Map<String, Object> ex) {
        LOG.info("[WFTaskProviderImpl.delegate]入参"
                + "taskId:" + taskId + "; "
                + "delegateTaskInfos:" + JsonUtil.serialize(delegateTaskInfos) + "; "
                + "ex:" + JsonUtil.serialize(ex) + "; ");
        if (taskId == null || delegateTaskInfos == null || delegateTaskInfos.size() <= 0) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        TaskEntity curTask = (TaskEntity) taskService.createTaskQuery().taskId(taskId).singleResult();
        if (curTask == null) {
            return ResultFactory.build(ResultCodes.PARAM_ILLEGAL);
        }

        try {
            List<Task> newTasks = Lists.newArrayList();
            for (THandleTaskInfo taskInfo : delegateTaskInfos) {
                TaskEntity taskNew = (TaskEntity) taskService.newTask();

                taskNew.setAssignee(taskInfo.getUserId());//代理者Id
                taskNew.setOwner(curTask.getAssignee());//被代理者Id
                taskNew.setName(taskInfo.getTaskName());
                taskNew.setExecutionId(curTask.getExecutionId());
                taskNew.setCategory(TaskCategory.DELEGATE.getCategory());
                taskNew.setProcessDefinitionId(curTask.getProcessDefinitionId());
                taskNew.setTaskDefinitionKey(curTask.getTaskDefinitionKey());
                taskNew.setProcessInstanceId(curTask.getProcessInstanceId());

                //保存新增的代理任务
                taskService.saveTask(taskNew);
                //将参与者加入流程历史中
                taskService.addUserIdentityLink(taskNew.getId(), taskInfo.getUserId(), IdentityLinkType.PARTICIPANT);

                newTasks.add(taskNew);
            }
            //用新任务的id，加入已完成任务的处理人，用于搜索
            if (newTasks != null && newTasks.size() > 0) {
                TaskEntity thsOneNewtask = (TaskEntity) newTasks.get(0);//其中一个新代理任务
                Map<String, Object> variablesAdd = new HashMap<>();
                variablesAdd.put(WFConfig.V_FINISHED_TASK_USER_LIST, getFinishedTaskUserList(curTask));//被代理任务的执行者
                taskService.setVariables(thsOneNewtask.getId(), variablesAdd);
            }

            //更新历史记录，保证和被代理任务一致性
            baseCommonService.updateHistoricTaskInstance(curTask.getProcessDefinitionId(), curTask.getProcessInstanceId(), getIds(newTasks));

            //将原任务完成
            curTask.setExecutionId(null);
            taskService.saveTask(curTask);
            taskService.complete(curTask.getId());

            //新增/修改流程变量
            if (variables != null && !variables.isEmpty()) {
                taskService.setVariables(taskId, variables);
            }
            return ResultFactory.success(newTasks);
        } catch (Exception e) {
            LOG.error("[WFTaskProviderImpl.delegate] Error!入参"
                    + "taskId:" + taskId + "; "
                    + "delegateTaskInfos:" + JsonUtil.serialize(delegateTaskInfos) + "; "
                    + "ex:" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }


    @Override
    public Result<Pager<TTaskList>> queryTaskList(QueryTaskCondition condition, int offset, int limit, Map<String, Object> ex) {
        List<Task> taskList = null;
        try {
            TaskQuery query = taskService.createTaskQuery();
            if (condition != null) {
                if (condition.getTaskId() != null) {//任务ID
                    query = query.taskId(condition.getTaskId());
                }
                if (condition.getAssignee() != null) {//办理人userId
                    query = query.taskCandidateOrAssigned(condition.getAssignee());
                }
                if (condition.getProcessCategoryList() != null) {//流程类型集合
                    query = query.processCategoryIn(condition.getProcessCategoryList());
                }
                if (condition.getProcessInstanceId() != null) {//流程实例ID
                    query = query.processInstanceId(condition.getProcessInstanceId());
                }
                if (condition.getProcessDefinitionKey() != null) {//流程定义KEY
                    query = query.processDefinitionKey(condition.getProcessDefinitionKey());
                }
                if (condition.getTaskDefinitionKey() != null) {//任务定义KEY
                    query = query.taskDefinitionKey(condition.getTaskDefinitionKey());
                }
                if (condition.getSuspensionState() != null) {//流程挂起状态，默认为不挂起
                    if (SuspensionState.ACTIVE.getStateCode() == condition.getSuspensionState().getStateCode()) {
                        query.active();
                    } else if (SuspensionState.SUSPENDED.getStateCode() == condition.getSuspensionState().getStateCode()) {
                        query.suspended();
                    }
                }
                if (condition.getProcDefNameLike() != null) {//流程定义名称模糊
                    query.processDefinitionNameLike(condition.getProcDefNameLike());
                }
                if (condition.getTaskTitleLike() != null) {//任务标题模糊
                    query.processVariableValueLike(WFConfig.V_WAITTING_DEAL_NAME, condition.getTaskTitleLike());
                }
                if (condition.getApplyMinTime() != null) {//申请时间最小值
                    query.processVariableValueGreaterThanOrEqual(WFConfig.V_START_TIME, condition.getApplyMinTime().getTime());
                }
                if (condition.getApplyMaxTime() != null) {//申请时间最大值
                    query.processVariableValueLessThanOrEqual(WFConfig.V_START_TIME, condition.getApplyMaxTime().getTime());
                }
                if (condition.getApplyAssigneeId() != null) {//申请人ID模糊
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
                            .processDefinitionNameLike(advancedSearchLike)//流程定义名称
                            .processInstanceId(condition.getAdvancedSearch())//流程ID
                            .processVariableValueLike(WFConfig.V_WAITTING_DEAL_NAME, advancedSearchLike)//任务标题模糊
                            .processVariableValueLike(WFConfig.V_PROCESS_START_STAFF, advancedSearchLike)//申请人名称模糊
                            .processVariableValueEquals(WFConfig.V_PROCESS_START_STAFF_ID, condition.getAdvancedSearch())//申请人ID
                            .processVariableValueLike(WFConfig.V_ORG_NAME, advancedSearchLike).endOr();//申请人部门名称模糊
                }


                if (condition.getOrderKeyType() != null) {//排序关键字类型
                    if (QueryTaskOrderKeyType.ASSIGNEE.equals(condition.getOrderKeyType())) {
                        query = query.orderByTaskAssignee();
                    } else if (QueryTaskOrderKeyType.CREATE_TIME.equals(condition.getOrderKeyType())) {
                        query = query.orderByTaskCreateTime();
                    } else if (QueryTaskOrderKeyType.DEFINITION_KEY.equals(condition.getOrderKeyType())) {
                        query = query.orderByTaskDefinitionKey();
                    } else if (QueryTaskOrderKeyType.ID.equals(condition.getOrderKeyType())) {
                        query = query.orderByTaskId();
                    } else if (QueryTaskOrderKeyType.PROCESS_DEFINITION_ID.equals(condition.getOrderKeyType())) {
                        query = query.orderByProcessDefinitionId();
                    } else if (QueryTaskOrderKeyType.PROCESS_INSTANCE_ID.equals(condition.getOrderKeyType())) {
                        query = query.orderByProcessInstanceId();
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
            taskList = query.listPage(offset, limit);

            //获取所有的流程实例Id
            Set<String> processDefinitionIds = getProcessDefinitionIds(taskList);

            List<ProcessDefinition> processDefinitionList = Lists.newArrayList();//流程定义集合
            Map<String, Map<String, Object>> mapTaskId2Variables = Maps.newHashMap();//任务对应的自定义变量
            if (processDefinitionIds != null && processDefinitionIds.size() > 0) {
                processDefinitionList = repositoryService.createProcessDefinitionQuery().processDefinitionIds(processDefinitionIds).list();
            }
            if (taskList != null && taskList.size() > 0) {
                for (Task task : taskList) {
                    Map<String, Object> variables = getVariables(task);
                    mapTaskId2Variables.put(task.getId(), variables);
                }
            }

            List<Task> taskListForCount = query.listPage(0, Integer.MAX_VALUE);
            int count = taskListForCount == null ? 0 : taskListForCount.size();
            return ResultFactory.success(new Pager<>(offset, limit, count, TTaskList.parseList(taskList, processDefinitionList, mapTaskId2Variables)));
        } catch (Throwable e) {
            LOG.error("[WFTaskProviderImpl.queryTaskList] Error!入参"
                    + "condition:" + JsonUtil.serialize(condition) + "; "
                    + "offset:" + offset + "; "
                    + "limit:" + limit + "; "
                    + "ex:" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    public Result<TTaskDetail> queryTaskDetail(String taskId, Map<String, Object> ex) {
        if (taskId == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            return ResultFactory.build(ResultCodes.PARAM_ILLEGAL);
        }
        Map<String, Object> variables = getVariables(task);
        return ResultFactory.success(new TTaskDetail(task, variables));
    }

    @Override
    public Result<Void> setVariable(String taskId, Map<String, ? extends Object> variables, Map<String, Object> ex) {
        if (StringUtils.isBlank(taskId) || variables == null || variables.isEmpty()) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        try {
            taskService.setVariables(taskId, variables);
            return ResultFactory.success();
        } catch (Exception e) {
            LOG.error("[WFTaskProviderImpl.setVariable] Error!入参"
                    + "taskId:" + taskId + "; "
                    + "variables:" + JsonUtil.serialize(variables) + "; "
                    + "ex:" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    public Result<Task> addSameNormalTask(String taskIdRef, String newUserId, Map<String, Object> ex) {
        if (StringUtils.isBlank(taskIdRef)) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(taskIdRef).singleResult();
        if (!TaskCategory.NORMAL.getCategory().equals(task.getCategory())) {
            return ResultFactory.build(ResultCodes.PARAM_ILLEGAL);
        }
        try {
            Task newTask = addNormalTask(task, newUserId);
            return ResultFactory.success(newTask);
        } catch (Exception e) {
            LOG.error("[WFTaskProviderImpl.addSameNormalTask] Error!入参"
                    + "taskIdRef:" + taskIdRef + "; "
                    + "newUserId:" + newUserId + "; "
                    + "ex:" + JsonUtil.serialize(ex) + "; ", e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    private Map<String, Object> getVariables(Task task) {
        //TODO query方法中可以直接调用includeTaskLocalVariables().includeProcessVariables()即可同时查询出变量集合
        if (TaskCategory.ADD_SIGN.getCategory().equals(task.getCategory())) {//填充原始节点的variables，因为加签节点没有executionId
            List<Task> allCurTaskList = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).list();
            Map<String, Task> mapId2Task = getMapId2Task(allCurTaskList);
            Task parentTask = getParentTask(task.getParentTaskId(), mapId2Task);//获取原始节点:最顶层的父任务
            return taskService.getVariables(parentTask.getId());
        } else {
            return taskService.getVariables(task.getId());
        }
    }

    /**
     * 填充定义中，所有任务环节自定的参数
     *
     * @param processInstanceId
     * @param processDefinitionId
     */
    public void fillBpmnVariables(String processInstanceId, String processDefinitionId) {
        Map<String, Object> allTaskVariables = baseCommonService.getAllTaskVariables(processDefinitionId);
        if (allTaskVariables != null) {
            runtimeService.setVariables(processInstanceId, allTaskVariables);
        }
    }

    private List<String> getIds(Collection<Task> tasks) {
        List<String> ids = Lists.newArrayList();
        if (tasks == null || tasks.size() <= 0) {
            return ids;
        }
        for (Task task : tasks) {
            ids.add(task.getId());
        }
        return ids;
    }

    private List<String> getUserIdsByTaskCategory(Collection<NextTaskInfo> nextTaskInfos, String userOperation) {
        List<String> userIds = Lists.newArrayList();
        if (nextTaskInfos == null || nextTaskInfos.size() <= 0) {
            return userIds;
        }
        for (NextTaskInfo nextTaskInfo : nextTaskInfos) {
            if (equalOperation(userOperation, nextTaskInfo.getOperation())) {
                if (TaskCategory.PARALLEL_SIGN.getCategory().equals(nextTaskInfo.getTaskCategory())
                        || TaskCategory.COUNTER_SIGN.getCategory().equals(nextTaskInfo.getTaskCategory())
                        || TaskCategory.ADD_SIGN.getCategory().equals(nextTaskInfo.getTaskCategory())
                        || TaskCategory.DELEGATE.getCategory().equals(nextTaskInfo.getTaskCategory())) {

                    userIds.add(nextTaskInfo.getUserId());
                }
            }
        }
        return userIds;
    }

    /**
     * 处理多实例任务逻辑
     *
     * @param nextTaskInfos
     * @param taskId
     * @param operation
     * @param variablesAdd
     */
    private void dealMulInstancesTask(Collection<NextTaskInfo> nextTaskInfos, String taskId, String operation, Map<String, Object> variablesAdd, Map<String, Object> variablesNR) {
        //变量加入多实例-会签、并行审签、加签、委托使用的执行者集合
        variablesAdd.put(WFConfig.V_COUNTER_SIGN_ACTIVITI_COLLECTION, getUserIdsByTaskCategory(nextTaskInfos, operation));

        //并行审签逻辑：变量加入同意的实例数
        if (variablesNR != null && variablesNR.get(WFConfig.V_NR_OF_COMPLETED_INSTANCES) != null) {
            Integer agreeInstances = variablesNR.get(WFConfig.V_SELF_AGREE_INSTANCES) == null ? 0 : (Integer) variablesNR.get(WFConfig.V_SELF_AGREE_INSTANCES);
            Integer completedInstances = variablesNR.get(WFConfig.V_SELF_COMPLETED_INSTANCES) == null ? 0 : (Integer) variablesNR.get(WFConfig.V_SELF_COMPLETED_INSTANCES);
            if (new Integer(0).equals(variablesNR.get(WFConfig.V_NR_OF_COMPLETED_INSTANCES))) {//如果当前已完成任务为0，则同意实例数清零
                agreeInstances = 0;
                completedInstances = 0;
            }
            if (TaskOperation.AGREE.getOperation().equals(operation) || TaskOperation.AGREE1.getOperation().equals(operation)) {
                ++agreeInstances;
            }
            ++completedInstances;

            variablesAdd.put(WFConfig.V_SELF_AGREE_INSTANCES, agreeInstances);
            variablesAdd.put(WFConfig.V_SELF_COMPLETED_INSTANCES, completedInstances);
        }
    }

    private List<Task> setAssignee(List<Task> taskList, Collection<NextTaskInfo> nextTaskInfos, String userOperation, TaskEntity curTask) {
        List<Task> newTasks = Lists.newArrayList();
        if (nextTaskInfos != null && nextTaskInfos.size() > 0) {
            for (NextTaskInfo nextTaskInfo : nextTaskInfos) {
                for (Task task : taskList) {
                    if (nextTaskInfo.getTaskDefinitionKey() == null//任务定义Key，与当前任务不一致
                            || !nextTaskInfo.getTaskDefinitionKey().equals(task.getTaskDefinitionKey())) {
                        continue;
                    }
                    //如果是相同的task key
                    if ((task.getAssignee() == null || "".equals(task.getAssignee()))
                            && equalOperation(userOperation, nextTaskInfo.getOperation())) {//操作值要一致
                        //设置任务的执行者
                        taskService.setAssignee(task.getId(), nextTaskInfo.getUserId());
                        task.setAssignee(nextTaskInfo.getUserId());
                        newTasks.add(task);
                        break;
                    } else if (TaskCategory.NORMAL.getCategory().equals(task.getCategory())//当前任务是正常任务
                            && TaskCategory.NORMAL.getCategory().equals(nextTaskInfo.getTaskCategory())//下一步任务也是正常任务
                            && equalOperation(userOperation, nextTaskInfo.getOperation())//操作值要一致
                            && task.getAssignee() != null && task.getAssignee().length() > 0) {//当前任务已经有执行者
                        //给正常任务增加多实例
                        Task newTask = addNormalTask((TaskEntity) task, nextTaskInfo.getUserId());
                        newTasks.add(newTask);
                        break;
                    } else if ((task.getAssignee() == null || "".equals(task.getAssignee()))
                            && TaskCategory.PARALLEL_SIGN.getCategory().equals(curTask.getCategory())) {//并行审签特殊处理
                        //设置任务的执行者
                        taskService.setAssignee(task.getId(), nextTaskInfo.getUserId());
                        task.setAssignee(nextTaskInfo.getUserId());
                        newTasks.add(task);
                    }
                }
            }
        }

        return newTasks;
    }

    private boolean equalOperation(String userOperation, String nextTaskOperation) {
        return StringUtils.isEmpty(userOperation) && (StringUtils.isEmpty(nextTaskOperation) || nextTaskOperation.toLowerCase().equals("null"))
                || !StringUtils.isEmpty(userOperation) && userOperation.equals(nextTaskOperation);
    }

    private Task addNormalTask(TaskEntity curTask, String newUserId) {
        TaskEntity taskNew = (TaskEntity) taskService.newTask();

        taskNew.setAssignee(newUserId);
        taskNew.setName(curTask.getName());
        taskNew.setExecutionId(curTask.getExecutionId());
        taskNew.setCategory(TaskCategory.NORMAL.getCategory());
        taskNew.setProcessDefinitionId(curTask.getProcessDefinitionId());
        taskNew.setTaskDefinitionKey(curTask.getTaskDefinitionKey());
        taskNew.setProcessInstanceId(curTask.getProcessInstanceId());

        //保存新增的代理任务
        taskService.saveTask(taskNew);
        //将参与者加入流程历史中
        taskService.addUserIdentityLink(taskNew.getId(), newUserId, IdentityLinkType.PARTICIPANT);
        //更新历史记录，保证和被代理任务一致性
        baseCommonService.updateHistoricTaskInstance(curTask.getProcessDefinitionId(), curTask.getProcessInstanceId(), Lists.newArrayList(taskNew.getId()));

        return taskNew;
    }

    private Task getParentTask(String curParentTaskId, Map<String, Task> mapId2Task) {
        if (curParentTaskId == null || mapId2Task == null || mapId2Task.isEmpty()) {
            return null;
        }
        Task task = mapId2Task.get(curParentTaskId);
        if (task.getParentTaskId() != null && task.getParentTaskId() != "") {
            return getParentTask(task.getParentTaskId(), mapId2Task);
        }
        return task;
    }

    private Map<String, Task> getMapId2Task(List<Task> allCurTaskList) {
        Map<String, Task> mapId2Task = Maps.newHashMap();
        if (allCurTaskList == null || allCurTaskList.size() <= 0) {
            return mapId2Task;
        }
        for (Task task : allCurTaskList) {
            mapId2Task.put(task.getId(), task);
        }
        return mapId2Task;
    }

    private Set<String> getProcessDefinitionIds(List<Task> taskList) {
        Set<String> processDefinitionIds = Sets.newHashSet();
        if (taskList == null || taskList.size() <= 0) {
            return processDefinitionIds;
        }
        for (Task task : taskList) {
            processDefinitionIds.add(task.getProcessDefinitionId());
        }
        return processDefinitionIds;
    }

    /**
     * 处理并行任务逻辑，同一个任务，多个实例，并且每一个实例操作后，其它实例自动结束
     * 略过当前任务,删除所有taskDefinitionKey相同的任务
     */
    private void dealParallelTask(TaskEntity curTask) {
        if (curTask == null) {
            return;
        }
        List<Task> list = taskService.createTaskQuery()
                .processInstanceId(curTask.getProcessInstanceId()).taskDefinitionKey(curTask.getTaskDefinitionKey()).list();
        List<String> deleteTaskIds = Lists.newArrayList();
        for (Task task : list) {
            TaskEntity delegateTask = (TaskEntity) task;
            if (!delegateTask.getId().equals(curTask.getId())//不是当前即将完成的代理任务
                    && delegateTask.getExecutionId() != null && delegateTask.getExecutionId().equals(curTask.getExecutionId())) {//与当前即将完成的代理任务属于相同的组（Execution）
                delegateTask.setExecutionId(null);
                taskService.saveTask(delegateTask);
                deleteTaskIds.add(delegateTask.getId());
            }
        }
        if (deleteTaskIds.size() > 0) {
            taskService.deleteTasks(deleteTaskIds);
        }
    }

    public String getFinishedTaskUserList(TaskEntity curTask) {
        Map<String, Object> variablesOld = getVariables(curTask);
        String finishedTaskUserList = variablesOld == null ? null : (String) variablesOld.get(WFConfig.V_FINISHED_TASK_USER_LIST);
        if (finishedTaskUserList == null || finishedTaskUserList.length() <= 0) {
            return curTask.getAssignee();
        } else {
            return finishedTaskUserList + "," + curTask.getAssignee();
        }
    }


}
