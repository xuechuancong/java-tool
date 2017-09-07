package org.jeff.javatool.tool.myactiviti.process;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.jeff.javatool.tool.myactiviti.WFHistoryProviderImpl;
import org.jeff.javatool.tool.myactiviti.WFProcessProviderImpl;
import org.jeff.javatool.tool.myactiviti.WFTaskProviderImpl;
import org.jeff.javatool.tool.myactiviti.config.TaskCategory;
import org.jeff.javatool.tool.myactiviti.config.TaskOperation;
import org.jeff.javatool.tool.myactiviti.config.WFConfig;
import org.jeff.javatool.tool.myactiviti.entity.*;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryHistoricInstanceCondition;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryTaskCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;
import org.jeff.javatool.tool.myactiviti.utils.FlowElementUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 测试所有process-management的重要功能点
 * Created by weijianfu on 2017/1/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestProc {
    @Autowired
    private WFProcessProviderImpl processProvider;
    @Autowired
    private WFTaskProviderImpl taskProvider;
    @Autowired
    private WFHistoryProviderImpl historyProvider;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;

    @Test
    @Rollback(true)
    public void testAll() throws Exception {
        String processDefinitionKey = "测试流程重要功能点PROCESSID";

        generateDefinition();//生成定义
        testTask(processDefinitionKey);//测试功能点
        deleteData(processDefinitionKey);//删除测试数据
    }

    public void generateDefinition() {
        Collection<Process> processColl = new ArrayList<>();
        Process process = new Process();
        String PROCESSID = "测试流程重要功能点PROCESSID";
        String PROCESSNAME = "测试流程重要功能点PROCESSNAME";
        String processDefinitionCategory = "测试流程重要功能点Category";
        String deploymentName = "测试流程重要功能点deploymentName";

        String taskKey1 = "taskDefinitionKey1";
        String taskName1 = "第一步";
        List<String> taskRoles1 = Lists.newArrayList("test1");

        String taskKey2 = "taskDefinitionKey2";
        String taskName2 = "第二步";
        List<String> taskRoles2 = Lists.newArrayList("test2");

        String taskKey3 = "taskDefinitionKey3";
        String taskName3 = "第三步";
        List<String> taskRoles3 = Lists.newArrayList("test3");


        String operation1 = TaskOperation.AGREE.getOperation();
        String operationName1 = "同意";
        String operation2 = TaskOperation.DISAGREE.getOperation();
        String operationName2 = "不同意，结束流程";
        String operation3 = TaskOperation.RETURN.getOperation();
        String operationName3 = "退回修改";
        String operation4 = TaskOperation.COUNTER_SIGN.getOperation();
        String operationName4 = "会签";

        process.setId(PROCESSID);
        process.setName(PROCESSNAME);

        process.addFlowElement(FlowElementUtil.createStartEvent());
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey1, taskName1, null,
                FlowElementUtil.createCustomProperties(taskRoles1,
                        Lists.newArrayList(new TNextTaskInfo(taskKey2, taskName2, taskRoles2.get(0), operation4, operationName4, true, true, TaskCategory.COUNTER_SIGN.getCategory())
                                , new TNextTaskInfo(taskKey3, taskName3, taskRoles3.get(0), operation1, operationName1, true, true, TaskCategory.PARALLEL_SIGN.getCategory())),
                        WFConfig.TRUE_STRING, WFConfig.TRUE_STRING, WFConfig.TRUE_STRING, WFConfig.TRUE_STRING, WFConfig.TRUE_STRING)
                , TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey2, taskName2, null,
                FlowElementUtil.createCustomProperties(taskRoles1,
                        Lists.newArrayList(new TNextTaskInfo(taskKey1, taskName1, taskRoles1.get(0), null, null, false, false, TaskCategory.NORMAL.getCategory())),
                        WFConfig.FALSE_STRING, WFConfig.FALSE_STRING, WFConfig.FALSE_STRING, WFConfig.FALSE_STRING, WFConfig.FALSE_STRING)
                , TaskCategory.COUNTER_SIGN));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey3, taskName3, null,
                FlowElementUtil.createCustomProperties(taskRoles1,
                        Lists.newArrayList(new TNextTaskInfo(taskKey1, taskName1, taskRoles1.get(0), null, null, false, false, TaskCategory.NORMAL.getCategory())
                                , new TNextTaskInfo("endEvent", "结束", null, null, null, false, false, null)),
                        WFConfig.FALSE_STRING, WFConfig.FALSE_STRING, WFConfig.FALSE_STRING, WFConfig.FALSE_STRING, WFConfig.FALSE_STRING)
                , TaskCategory.PARALLEL_SIGN));
        process.addFlowElement(FlowElementUtil.createEndEvent());


        process.addFlowElement(FlowElementUtil.createExclusiveGateway("createExclusiveGateway1"));
        process.addFlowElement(FlowElementUtil.createExclusiveGateway("createExclusiveGateway2"));

        process.addFlowElement(FlowElementUtil.createSequenceFlow("startEvent", taskKey1, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey1, "createExclusiveGateway1", "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createExclusiveGateway1", taskKey2, operationName4, "${pass=='" + operation4 + "'}"));
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createExclusiveGateway1", taskKey3, operationName1, "${pass=='" + operation1 + "'}"));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey2, taskKey1, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey3, "createExclusiveGateway2", "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createExclusiveGateway2", taskKey1, "", "${selfOfCompletedInstances!=selfOfAgreeInstances}"));//有一个或者一个以上不同意
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createExclusiveGateway2", "endEvent", "", "${selfOfCompletedInstances==selfOfAgreeInstances}"));//全部都同意

        processColl.add(process);

        Result<Void> result = processProvider.generateProcessDefinition(processDefinitionCategory, deploymentName, processColl, null);

        Assert.assertTrue(result.success());
    }

    public void testTask(String processDefinitionKey) throws Exception {
        String userId = "test1";
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("testVariables", "testVariables");
        Map<String, Object> ex = null;
        /**开启任务**/
        Result<TProcessInstance> result = taskProvider.generateTask(processDefinitionKey, userId, variables, ex);
        Assert.assertTrue(result.success());

        /**
         * 第一个任务
         */
        String processInstanceId = result.getValue().getExecutionEntity().getId();
        QueryTaskCondition condition = new QueryTaskCondition();
        condition.setProcessInstanceId(processInstanceId);
        Result<Pager<TTaskList>> resultTaskList = taskProvider.queryTaskList(condition, 0, Integer.MAX_VALUE, null);
        Assert.assertTrue(result.success());
        TTaskList firstTaskList = resultTaskList.getValue().getDatas().get(0);
        Map<String, Object> variablesFirstTask = firstTaskList.getVariables();
        Assert.assertTrue(variablesFirstTask.get("testVariables").equals("testVariables"));
        Assert.assertTrue(firstTaskList.getTask().getTaskDefinitionKey().equals("taskDefinitionKey1"));
        Assert.assertTrue(firstTaskList.getProcessDefinitionKey().equals(processDefinitionKey));

        /**校对任务详情查询接口**/
        Result<TTaskDetail> tTaskDetailResult = taskProvider.queryTaskDetail(firstTaskList.getTask().getId(), null);
        TTaskDetail firstTaskDetail = tTaskDetailResult.getValue();
        Assert.assertTrue(firstTaskDetail.getTask().getTaskDefinitionKey().equals("taskDefinitionKey1"));
        Assert.assertTrue(Lists.newArrayList(firstTaskDetail.getCurRoleIds()).get(0).equals("test1"));
        Collection<TNextTaskInfo> firstTaskDetailNextTaskInfos = firstTaskDetail.getNextTaskInfos();
        Assert.assertTrue(firstTaskDetailNextTaskInfos.size() == 2);
        Map<String, Object> firstTaskDetailVariables = firstTaskDetail.getVariables();
        Assert.assertTrue(firstTaskDetailVariables.get("testVariables").equals("testVariables"));


        /**校对加签**/
        Collection<THandleTaskInfo> addSignTaskInfos = Lists.newArrayList();
        addSignTaskInfos.add(new THandleTaskInfo("test-jiaqian1", "加签1"));
        addSignTaskInfos.add(new THandleTaskInfo("test-jiaqian2", "加签2"));
        Result<List<Task>> addSignResult = taskProvider.addSign(firstTaskList.getTask().getId(), addSignTaskInfos, null, null);
        List<Task> addSignResultValue = addSignResult.getValue();
        Assert.assertTrue(addSignResultValue.size() == 2);
        for (Task task : addSignResultValue) {
            if (!(task.getAssignee().equals("test-jiaqian1") && task.getName().equals("加签1"))
                    && !(task.getAssignee().equals("test-jiaqian2") && task.getName().equals("加签2"))) {
                throw new Exception("加签返回结果不正确！");
            }
        }
        //完成加签任务
        for (Task task : addSignResultValue) {
            taskProvider.completeTask(task.getProcessInstanceId(), null, task.getId(), null, null, null);
        }
        QueryTaskCondition condition1 = new QueryTaskCondition();
        condition1.setProcessInstanceId(processInstanceId);
        Result<Pager<TTaskList>> resultTaskList1 = taskProvider.queryTaskList(condition, 0, Integer.MAX_VALUE, null);
        List<TTaskList> datas = resultTaskList1.getValue().getDatas();
        Assert.assertTrue(datas.size() == 1);
        TTaskList tTaskList = datas.get(0);
        Assert.assertTrue(tTaskList.getTask().getTaskDefinitionKey().equals("taskDefinitionKey1"));//证明只剩下【第一步】


        /**校对代理**/
        Collection<THandleTaskInfo> delegateTaskInfos = Lists.newArrayList();
        delegateTaskInfos.add(new THandleTaskInfo("test-weituo1", "委托1"));
        delegateTaskInfos.add(new THandleTaskInfo("test-weituo2", "委托2"));
        Result<List<Task>> delegateResult = taskProvider.delegate(firstTaskList.getTask().getId(), delegateTaskInfos, null, null);
        List<Task> delegateResultValue = delegateResult.getValue();
        Assert.assertTrue(delegateResultValue.size() == 2);
        for (Task task : delegateResultValue) {
            if (!(task.getAssignee().equals("test-weituo1") && task.getName().equals("委托1"))
                    && !(task.getAssignee().equals("test-weituo2") && task.getName().equals("委托2"))) {
                throw new Exception("委托返回结果不正确！");
            }
        }
        Task delegeteTask = delegateResultValue.get(0);//随机完成一个代理任务
        Collection<NextTaskInfo> nextTaskInfos2 =
                Lists.newArrayList(new NextTaskInfo("taskDefinitionKey2", "test2-1", TaskCategory.COUNTER_SIGN.getCategory(), TaskOperation.COUNTER_SIGN.getOperation())
                        , new NextTaskInfo("taskDefinitionKey2", "test2-2", TaskCategory.COUNTER_SIGN.getCategory(), TaskOperation.COUNTER_SIGN.getOperation()));
        Result<List<Task>> completeTask2 = taskProvider.completeTask(delegeteTask.getProcessInstanceId(), nextTaskInfos2, delegeteTask.getId(), TaskOperation.COUNTER_SIGN.getOperation(), null, null);
        Assert.assertTrue(completeTask2.success());


        /**
         * 第二个任务
         */
        Result<Pager<TTaskList>> resultTaskList2 = taskProvider.queryTaskList(condition, 0, Integer.MAX_VALUE, null);
        List<TTaskList> datas2 = resultTaskList2.getValue().getDatas();
        Assert.assertTrue(datas2.size() == 2);//只剩下第二步一个任务
        TTaskList secondTaskList1 = datas2.get(0);//其中一个会签任务
        TTaskList secondTaskList2 = datas2.get(1);//其中一个会签任务
        Assert.assertTrue(secondTaskList1.getTask().getTaskDefinitionKey().equals("taskDefinitionKey2"));
        Assert.assertTrue(secondTaskList2.getTask().getTaskDefinitionKey().equals("taskDefinitionKey2"));
        Result<List<Task>> completeTask3 = taskProvider.completeTask(secondTaskList1.getTask().getProcessInstanceId(), null, secondTaskList1.getTask().getId(), TaskOperation.AGREE.getOperation(), null, null);
        Result<Pager<TTaskList>> resultTaskList3 = taskProvider.queryTaskList(condition, 0, Integer.MAX_VALUE, null);
        List<TTaskList> datas3 = resultTaskList3.getValue().getDatas();
        Assert.assertTrue(datas3.size() == 1);//只剩另一个会签任务
        Assert.assertTrue(datas3.get(0).getTask().getTaskDefinitionKey().equals("taskDefinitionKey2"));
        Result<List<Task>> completeTask4 = taskProvider.completeTask(secondTaskList2.getTask().getProcessInstanceId(), null, secondTaskList2.getTask().getId(), TaskOperation.AGREE.getOperation(), null, null);
        Result<Pager<TTaskList>> resultTaskList4 = taskProvider.queryTaskList(condition, 0, Integer.MAX_VALUE, null);
        List<TTaskList> datas4 = resultTaskList4.getValue().getDatas();
        Assert.assertTrue(datas4.size() == 1);//退回第一步
        Assert.assertTrue(datas4.get(0).getTask().getTaskDefinitionKey().equals("taskDefinitionKey1"));

        Collection<NextTaskInfo> nextTaskInfos5 =//完成第一步，到第三步，并行审签
                Lists.newArrayList(new NextTaskInfo("taskDefinitionKey3", "test3-1", TaskCategory.PARALLEL_SIGN.getCategory(), TaskOperation.AGREE.getOperation())
                        , new NextTaskInfo("taskDefinitionKey3", "test3-2", TaskCategory.PARALLEL_SIGN.getCategory(), TaskOperation.AGREE.getOperation()));
        Result<List<Task>> completeTask5 = taskProvider.completeTask(datas4.get(0).getTask().getProcessInstanceId(), nextTaskInfos5, datas4.get(0).getTask().getId(), TaskOperation.AGREE.getOperation(), null, null);
        Assert.assertTrue(completeTask5.success());

        /**
         * 第三个任务
         */
        Result<Pager<TTaskList>> resultTaskList5 = taskProvider.queryTaskList(condition, 0, Integer.MAX_VALUE, null);
        List<TTaskList> datas5 = resultTaskList5.getValue().getDatas();
        Assert.assertTrue(datas5.size() == 2);//第三步
        Assert.assertTrue(datas5.get(0).getTask().getTaskDefinitionKey().equals("taskDefinitionKey3"));
        Assert.assertTrue(datas5.get(1).getTask().getTaskDefinitionKey().equals("taskDefinitionKey3"));
        //一个同意、一个不同意，退回第一步
        Collection<NextTaskInfo> nextTaskInfos6 = null;
        Result<List<Task>> completeTask6 = taskProvider.completeTask(datas5.get(0).getTask().getProcessInstanceId(), nextTaskInfos6, datas5.get(0).getTask().getId(), TaskOperation.AGREE.getOperation(), null, null);
        Assert.assertTrue(completeTask6.success());
        Collection<NextTaskInfo> nextTaskInfos7 = null;
        Result<List<Task>> completeTask7 = taskProvider.completeTask(datas5.get(1).getTask().getProcessInstanceId(), nextTaskInfos7, datas5.get(1).getTask().getId(), TaskOperation.DISAGREE.getOperation(), null, null);
        Assert.assertTrue(completeTask7.success());
        Result<Pager<TTaskList>> resultTaskList6 = taskProvider.queryTaskList(condition, 0, Integer.MAX_VALUE, null);
        List<TTaskList> datas6 = resultTaskList6.getValue().getDatas();
        Assert.assertTrue(datas6.size() == 1);//第一步
        Assert.assertTrue(datas6.get(0).getTask().getTaskDefinitionKey().equals("taskDefinitionKey1"));
        //都同意，结束
        Collection<NextTaskInfo> nextTaskInfos8 =//完成第一步，到第三步，并行审签
                Lists.newArrayList(new NextTaskInfo("taskDefinitionKey3", "test3-1", TaskCategory.PARALLEL_SIGN.getCategory(), TaskOperation.AGREE.getOperation())
                        , new NextTaskInfo("taskDefinitionKey3", "test3-2", TaskCategory.PARALLEL_SIGN.getCategory(), TaskOperation.AGREE.getOperation()));
        Result<List<Task>> completeTask8 = taskProvider.completeTask(datas6.get(0).getTask().getProcessInstanceId(), nextTaskInfos8, datas6.get(0).getTask().getId(), TaskOperation.AGREE.getOperation(), null, null);
        Assert.assertTrue(completeTask8.success());
        List<Task> datas8 = completeTask8.getValue();

        Collection<NextTaskInfo> nextTaskInfos9 = null;
        Result<List<Task>> completeTask9 = taskProvider.completeTask(datas8.get(0).getProcessInstanceId(), nextTaskInfos9, datas8.get(0).getId(), TaskOperation.AGREE.getOperation(), null, null);
        Assert.assertTrue(completeTask9.success());
        Collection<NextTaskInfo> nextTaskInfos10 = null;
        Result<List<Task>> completeTask10 = taskProvider.completeTask(datas8.get(1).getProcessInstanceId(), nextTaskInfos10, datas8.get(1).getId(), TaskOperation.AGREE.getOperation(), null, null);
        Assert.assertTrue(completeTask10.success());

        Result<Pager<TTaskList>> resultTaskList7 = taskProvider.queryTaskList(condition, 0, Integer.MAX_VALUE, null);
        List<TTaskList> datas7 = resultTaskList7.getValue().getDatas();
        Assert.assertTrue(datas7.size() == 0);//结束

    }

    public void deleteData(String processDefinitionKey) {
        //删除正在执行数据
        QueryTaskCondition condition = new QueryTaskCondition();
        condition.setProcessDefinitionKey(processDefinitionKey);
        Result<Pager<TTaskList>> TTaskLists = taskProvider.queryTaskList(condition, 0, Integer.MAX_VALUE, null);
        Pager<TTaskList> TTaskList = TTaskLists.getValue();
        List<org.jeff.javatool.tool.myactiviti.entity.TTaskList> TaskList = TTaskList.getDatas();
        Set<String> processInstanceIdSet = Sets.newHashSet();
        for (org.jeff.javatool.tool.myactiviti.entity.TTaskList task : TaskList) {
            processInstanceIdSet.add(task.getTask().getProcessInstanceId());
        }


        for (String processInstanceId : processInstanceIdSet) {
            List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
            if (taskList != null && taskList.size() > 0) {
                for (int i = 1; i < taskList.size(); i++) {
                    TaskEntity taskNew = (TaskEntity) taskList.get(i);
                    taskNew.setExecutionId(null);
                    taskService.saveTask(taskNew);
                    taskService.deleteTask(taskNew.getId());
                }
            }
            runtimeService.deleteProcessInstance(processInstanceId, "test");
        }
        //删除历史数据
        QueryHistoricInstanceCondition conditionHis = new QueryHistoricInstanceCondition();
        conditionHis.setProcessDefinitionKey(processDefinitionKey);
        Result<Pager<THistoricInstanceList>> pagerResult = historyProvider.queryHistoricInstanceList(conditionHis, 0, Integer.MAX_VALUE, null);
        Pager<THistoricInstanceList> value = pagerResult.getValue();
        if (value != null && value.getDatas() != null) {
            List<THistoricInstanceList> datas = value.getDatas();
            for (THistoricInstanceList data : datas) {
                historyService.deleteHistoricProcessInstance(data.getHistoricProcessInstance().getId());
            }
        }
        //删除定义数据
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).list();
        if (list != null && list.size() > 0) {
            for (ProcessDefinition processDefinition : list) {
                repositoryService.deleteDeployment(processDefinition.getDeploymentId());
            }
        }
    }
}
