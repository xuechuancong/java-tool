package org.jeff.javatool.tool.myactiviti;

import com.google.common.collect.Lists;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.task.Task;
import org.jeff.javatool.tool.myactiviti.config.TaskCategory;
import org.jeff.javatool.tool.myactiviti.entity.TTaskJump;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;
import org.jeff.javatool.tool.myactiviti.entity.result.ResultCodes;
import org.jeff.javatool.tool.myactiviti.entity.result.ResultFactory;
import org.jeff.javatool.tool.myactiviti.intfc.IWFManagementProvider;
import org.jeff.javatool.tool.myactiviti.utils.CloneUtil;
import org.jeff.javatool.tool.myactiviti.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 工作流-人为管理服务，属于流程监控模块
 * Created by weijianfu on 2017/2/22.
 */
@Service
public class WFManagementProviderImpl implements IWFManagementProvider {

    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;

    private Logger log = LoggerFactory.getLogger(WFManagementProviderImpl.class);

    @Override
    @Transactional
    public Result<Void> resetAssignee(String taskId, String userId, Map<String, Object> variables, Map<String, Object> ex) {
        log.info("[WFManagementProviderImpl.resetAssignee] Info!入参:"
                + "\ntaskId:" + taskId
                + "\nuserId:" + userId
                + "\nex:" + ex);
        if (taskId == null || userId == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        try {
            taskService.setAssignee(taskId, userId);
            if (variables != null && !variables.isEmpty()) {
                taskService.setVariables(taskId, variables);
            }
            return ResultFactory.success();
        } catch (Throwable e) {
            log.error("[WFManagementProviderImpl.resetAssignee] Error!入参:"
                    + "\ntaskId:" + taskId
                    + "\nuserId:" + userId
                    + "\nex:" + ex, e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Result<List<Task>> jump(String processInstanceId, String targetTaskDefinitionKey, String userId, Map<String, Object> variables, Map<String, Object> ex) {
        log.info("[WFManagementProviderImpl.jump] Info!入参:"
                + "\nprocessInstanceId:" + processInstanceId
                + "\ntargetTaskDefinitionKey:" + targetTaskDefinitionKey
                + "\nex:" + ex);
        if (processInstanceId == null || targetTaskDefinitionKey == null || userId == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        List<Task> taskList = taskService.createTaskQuery()
                .processInstanceId(processInstanceId).list();
        if (taskList == null || taskList.size() <= 0) {
            return ResultFactory.build(ResultCodes.PARAM_ILLEGAL);
        }

        TaskEntity task = (TaskEntity) taskList.get(0);
        Map<String, Object> activityMap = new HashMap<>();
        try {
            //删除非选中的所有任务
            for (Task tempTask : taskList) {
                if (tempTask.getId().equals(task.getId())) {
                    continue;
                }
                if (tempTask.getTaskDefinitionKey().equals(task.getTaskDefinitionKey())) {
                    taskService.complete(tempTask.getId());
                } else {
                    TaskEntity tempTaskEntity = (TaskEntity) tempTask;
                    tempTaskEntity.setExecutionId(null);
                    taskService.saveTask(tempTask);
                    taskService.deleteTask(tempTask.getId(), true);
                }
            }

            String curTaskDefinitionKey = task.getTaskDefinitionKey();
            String actDefId = task.getProcessDefinitionId();

            activityMap = prepare(actDefId, curTaskDefinitionKey, targetTaskDefinitionKey);

            taskService.complete(task.getId());


            //根据processInstanceId查询出正在执行的任务
            List<Task> list = taskService
                    .createTaskQuery().processInstanceId(processInstanceId).list();
            List<Task> newTasks = Lists.newArrayList();
            Task newTask = list.get(0);
            taskService.setAssignee(newTask.getId(), userId);
            newTasks.add(newTask);

            if (variables != null && !variables.isEmpty()) {
                taskService.setVariables(newTask.getId(), variables);
            }

            return ResultFactory.success(newTasks);
        } catch (Throwable e) {
            log.error("[WFManagementProviderImpl.jump] Error!入参:"
                    + "\nprocessInstanceId:" + processInstanceId
                    + "\ntargetTaskDefinitionKey:" + targetTaskDefinitionKey
                    + "\nex:" + ex, e);
            return ResultFactory.exception(e.getMessage());
        } finally {
            //恢复
            restore(activityMap);
        }
    }

    @Override
    public Result<Void> suspendProcess(String processInstanceId, Map<String, Object> ex) {
        log.info("[WFManagementProviderImpl.suspendProcess] Info!入参:"
                + "\nprocessInstanceId:" + processInstanceId
                + "\nex:" + ex);
        if (processInstanceId == null) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        try {
            runtimeService.suspendProcessInstanceById(processInstanceId);
            return ResultFactory.success();
        } catch (Throwable e) {
            log.error("[WFManagementProviderImpl.suspendProcess] Error!入参:"
                    + "\nprocessInstanceId:" + processInstanceId
                    + "\nex:" + ex, e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    public Result<Void> deleteProcess(String processInstanceId, String deleteReason, Map<String, Object> ex) {
        log.info("[WFManagementProviderImpl.deleteProcess] Info!入参:"
                + "\nprocessInstanceId:" + processInstanceId
                + "\nex:" + ex);
        if (processInstanceId == null || deleteReason == null || deleteReason.length() <= 0) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        try {
            runtimeService.deleteProcessInstance(processInstanceId, deleteReason);
            return ResultFactory.success();
        } catch (Throwable e) {
            log.error("[WFManagementProviderImpl.deleteProcess] Error!入参:"
                    + "\nprocessInstanceId:" + processInstanceId
                    + "\nex:" + ex, e);
            return ResultFactory.exception(e.getMessage());
        }
    }

    @Override
    public Result<List<TTaskJump>> queryProcTaskDefList(String procDefId, Map<String, Object> ex) {
        if (StringUtil.isBlank(procDefId)) {
            return ResultFactory.build(ResultCodes.PARAM_EMPTY);
        }
        try {
            BpmnModel model = repositoryService.getBpmnModel(procDefId);
            if (model == null) {
                return ResultFactory.build(ResultCodes.PARAM_ILLEGAL);
            }
            List<TTaskJump> tTaskJumpList = Lists.newArrayList();
            if (model != null) {
                Collection<FlowElement> flowElementColl = model.getMainProcess().getFlowElements();
                for (FlowElement e : flowElementColl) {
                    if (e instanceof UserTask) {
                        UserTask userTask = (UserTask) e;
                        if (!TaskCategory.NORMAL.getCategory().equals(userTask.getCategory())) {//非正常任务不加入列表
                            continue;
                        }
                        TTaskJump tTaskJump = new TTaskJump();
                        tTaskJump.setDefKey(userTask.getId());
                        tTaskJump.setDefName(userTask.getName());
                        tTaskJump.setDefCategory(userTask.getCategory());

                        tTaskJumpList.add(tTaskJump);
                    }
                }
            }

            return ResultFactory.success(tTaskJumpList);
        } catch (Throwable e) {
            log.error("[WFManagementProviderImpl.queryProcTaskDefList] Error!入参:"
                    + "\nprocDefId:" + procDefId
                    + "\nex:" + ex, e);
            return ResultFactory.exception(e.getMessage());
        }

    }

    /**
     * 将节点之后的节点删除然后指向新的节点。
     *
     * @param actDefId                流程定义ID
     * @param curTaskDefinitionKey    当前任务定义KEY
     * @param targetTaskDefinitionKey 目标任务定义KEY
     * @return Map<String,Object> 返回节点和需要恢复节点的集合。
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> prepare(String actDefId, String curTaskDefinitionKey, String targetTaskDefinitionKey) {
        Map<String, Object> map = new HashMap<>();

        //修改流程定义
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(actDefId);

        ActivityImpl curAct = processDefinition.findActivity(curTaskDefinitionKey);
        List<PvmTransition> outTrans = curAct.getOutgoingTransitions();
        try {
            List<PvmTransition> cloneOutTrans = (List<PvmTransition>) CloneUtil.deepClone(outTrans);
            map.put("outTrans", cloneOutTrans);
        } catch (Exception ex) {

        }

        /**
         * 解决通过选择自由跳转指向同步节点导致的流程终止的问题。
         * 在目标节点中删除指向自己的流转。
         */
        for (Iterator<PvmTransition> it = outTrans.iterator(); it.hasNext(); ) {
            PvmTransition transition = it.next();
            PvmActivity activity = transition.getDestination();
            List<PvmTransition> inTrans = activity.getIncomingTransitions();
            for (Iterator<PvmTransition> itIn = inTrans.iterator(); itIn.hasNext(); ) {
                PvmTransition inTransition = itIn.next();
                if (inTransition.getSource().getId().equals(curAct.getId())) {
                    itIn.remove();
                }
            }
        }

        curAct.getOutgoingTransitions().clear();

        if (targetTaskDefinitionKey != null && targetTaskDefinitionKey.length() > 0) {
            //创建一个连接
            ActivityImpl destAct = processDefinition.findActivity(targetTaskDefinitionKey);
            TransitionImpl transitionImpl = curAct.createOutgoingTransition();
            transitionImpl.setDestination(destAct);
        }
        map.put("activity", curAct);

        return map;

    }

    /**
     * 将临时节点清除掉，加回原来的节点。
     *
     * @param map void
     */
    @SuppressWarnings("unchecked")
    private void restore(Map<String, Object> map) {
        ActivityImpl curAct = (ActivityImpl) map.get("activity");
        List<PvmTransition> outTrans = (List<PvmTransition>) map.get("outTrans");
        curAct.getOutgoingTransitions().clear();
        curAct.getOutgoingTransitions().addAll(outTrans);
    }
}
