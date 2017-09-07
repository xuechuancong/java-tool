package org.jeff.javatool.tool.myactiviti.tool;

import com.google.common.collect.Lists;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.Condition;
import org.activiti.engine.impl.bpmn.helper.SkipExpressionUtil;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.SuspensionState;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.task.Task;
import org.jeff.javatool.tool.myactiviti.WFHistoryProviderImpl;
import org.jeff.javatool.tool.myactiviti.WFProcessProviderImpl;
import org.jeff.javatool.tool.myactiviti.config.*;
import org.jeff.javatool.tool.myactiviti.entity.*;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryHistoricInstanceCondition;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryHistoricTaskCondition;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryProcessDefinitionCondition;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryTaskCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;
import org.jeff.javatool.tool.myactiviti.intfc.IWFManagementProvider;
import org.jeff.javatool.tool.myactiviti.intfc.IWFTaskProvider;
import org.jeff.javatool.tool.myactiviti.utils.FlowElementUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.*;

/**
 * 测试流程基本功能,包括加签、会签、委托、并签
 * Created by weijianfu on 2016/12/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLinkType {

    @Autowired
    private WFProcessProviderImpl processProvider;
    @Autowired
    private IWFTaskProvider taskProvider;
    @Autowired
    private WFHistoryProviderImpl historyProvider;
    @Autowired
    private IWFManagementProvider managementProvider;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;


    @Test
    public void testWFManagementProviderImpl() {
        String taskId = "1117509";
        String userId = "test";
        String processInstanceId = "1117505";
        Result<Void> result = managementProvider.suspendProcess(processInstanceId, null);

        System.out.println("!!");
    }

    /**
     * 正常流程定义
     */
    @Test
    public void generate() {
        Collection<Process> processColl = new ArrayList<>();
        Process process = new Process();
        String PROCESSID = "测试流程基本功能PROCESSID";
        String PROCESSNAME = "测试流程基本功能PROCESSNAME";
        String processDefinitionCategory = "测试流程基本功能";
        String deploymentName = "测试流程基本功能deploymentName";

        String taskKey1 = "taskDefinitionKey1";
        String taskName1 = "第一步";
        String taskKey2 = "taskDefinitionKey2";
        String taskName2 = "第二步";
        String taskKey3 = "taskDefinitionKey3";
        String taskName3 = "第三步";
        String taskKey4 = "taskDefinitionKey4";
        String taskName4 = "第四步";
        String taskKey5 = "taskDefinitionKey5";
        String taskName5 = "第五步";
        String taskKey6 = "taskDefinitionKey6";
        String taskName6 = "第六步";

        String operation1 = TaskOperation.AGREE.getOperation();
        String operationName1 = "同意";
        String operation2 = TaskOperation.DISAGREE.getOperation();
        String operationName2 = "不同意，结束流程";
        String operation3 = TaskOperation.RETURN.getOperation();
        String operationName3 = "退回修改";

        process.setId(PROCESSID);
        process.setName(PROCESSNAME);

        process.addFlowElement(FlowElementUtil.createStartEvent());
        process.addFlowElement(FlowElementUtil.createServiceTask(taskKey1, taskName1, "com.caih.erp.service.process.taskservice.TSDemo"));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey2, taskName2, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey3, taskName3, null, null, TaskCategory.COUNTER_SIGN));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey4, taskName4, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey5, taskName5, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey6, taskName6, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createEndEvent());


        process.addFlowElement(FlowElementUtil.createSequenceFlow("startEvent", taskKey1, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey1, taskKey2, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey2, taskKey3, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey3, taskKey4, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey4, taskKey5, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey5, taskKey6, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey6, "endEvent", "", ""));


        processColl.add(process);

        Result<Void> result = processProvider.generateProcessDefinition(processDefinitionCategory, deploymentName, processColl, null);

        System.out.println("PAUSE");
    }

    /**
     * 正常流程定义
     */
    @Test
    public void generateInclusiveGateway() {
        Collection<Process> processColl = new ArrayList<>();
        Process process = new Process();
        String PROCESSID = "测试流程基本功能PROCESSID";
        String PROCESSNAME = "测试流程基本功能PROCESSNAME";
        String processDefinitionCategory = "测试流程基本功能";
        String deploymentName = "测试流程基本功能deploymentName";

        String taskKey1 = "taskDefinitionKey1";
        String taskName1 = "第一步";
        String taskKey2 = "taskDefinitionKey2";
        String taskName2 = "第二步";
        String taskKey3 = "taskDefinitionKey3";
        String taskName3 = "第三步";
        String taskKey4 = "taskDefinitionKey4";
        String taskName4 = "第四步";
        String taskKey5 = "taskDefinitionKey5";
        String taskName5 = "第五步";
        String taskKey6 = "taskDefinitionKey6";
        String taskName6 = "第六步";

        String operation1 = TaskOperation.AGREE.getOperation();
        String operationName1 = "同意";
        String operation2 = TaskOperation.DISAGREE.getOperation();
        String operationName2 = "不同意，结束流程";
        String operation3 = TaskOperation.RETURN.getOperation();
        String operationName3 = "退回修改";

        process.setId(PROCESSID);
        process.setName(PROCESSNAME);

        process.addFlowElement(FlowElementUtil.createStartEvent());
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey1, taskName1, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey2, taskName2, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey3, taskName3, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey4, taskName4, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey5, taskName5, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey6, taskName6, null, null, TaskCategory.COUNTER_SIGN));
        process.addFlowElement(FlowElementUtil.createEndEvent());


        process.addFlowElement(FlowElementUtil.createInclusiveGateway("createInclusiveGateway1"));
        process.addFlowElement(FlowElementUtil.createInclusiveGateway("createInclusiveGateway2"));

        process.addFlowElement(FlowElementUtil.createExclusiveGateway("createExclusiveGateway1"));


        process.addFlowElement(FlowElementUtil.createSequenceFlow("startEvent", taskKey1, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey1, "createInclusiveGateway1", "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createInclusiveGateway1", taskKey2, "", "${money==1 || money==3}"));
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createInclusiveGateway1", taskKey3, "", "${money==2}"));
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createInclusiveGateway1", taskKey4, "", "${money==3}"));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey2, "createInclusiveGateway2", "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey3, "createInclusiveGateway2", "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey4, "createExclusiveGateway1", "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createExclusiveGateway1", "createInclusiveGateway2", "", "${money==1}"));
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createExclusiveGateway1", taskKey6, "", "${money==3}"));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey6, "createInclusiveGateway2", "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createInclusiveGateway2", taskKey5, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey5, "endEvent", "", ""));


        processColl.add(process);

        Result<Void> result = processProvider.generateProcessDefinition(processDefinitionCategory, deploymentName, processColl, null);

        System.out.println("PAUSE");
    }

    /**
     * 并行审签定义
     */
    @Test
    public void generateRuZhiShenPi() {
        Collection<Process> processColl = new ArrayList<>();
        Process process = new Process();
        String PROCESSID = "测试流程基本功能PROCESSID";
        String PROCESSNAME = "测试流程基本功能PROCESSNAME";
        String processDefinitionCategory = "测试流程基本功能";
        String deploymentName = "测试流程基本功能deploymentName";

        String taskKey1 = "taskDefinitionKey1";
        String taskName1 = "第一步";
        String taskKey2 = "taskDefinitionKey2";
        String taskName2 = "第二步";
        String taskKey3 = "taskDefinitionKey3";
        String taskName3 = "第三步";

        String operation1 = TaskOperation.AGREE.getOperation();
        String operationName1 = "同意";
        String operation2 = TaskOperation.DISAGREE.getOperation();
        String operationName2 = "不同意，结束流程";
        String operation3 = TaskOperation.RETURN.getOperation();
        String operationName3 = "退回修改";

        process.setId(PROCESSID);
        process.setName(PROCESSNAME);

        process.addFlowElement(FlowElementUtil.createStartEvent());
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey1, taskName1, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey2, taskName2, null, null, TaskCategory.PARALLEL_SIGN));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey3, taskName3, null, null, TaskCategory.PARALLEL_SIGN));
        process.addFlowElement(FlowElementUtil.createEndEvent());

        process.addFlowElement(FlowElementUtil.createExclusiveGateway("createExclusiveGateway1"));
        process.addFlowElement(FlowElementUtil.createExclusiveGateway("createExclusiveGateway2"));

        process.addFlowElement(FlowElementUtil.createSequenceFlow("startEvent", taskKey1, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey1, taskKey2, "", ""));

        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey2, "createExclusiveGateway1", "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createExclusiveGateway1", taskKey1, "", "${selfOfCompletedInstances!=selfOfAgreeInstances}"));
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createExclusiveGateway1", taskKey3, "", "${selfOfCompletedInstances==selfOfAgreeInstances}"));

        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey3, "createExclusiveGateway2", "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createExclusiveGateway2", taskKey1, "", "${selfOfCompletedInstances!=selfOfAgreeInstances}"));
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createExclusiveGateway2", "endEvent", "", "${selfOfCompletedInstances==selfOfAgreeInstances}"));


        processColl.add(process);
        Result<Void> result = processProvider.generateProcessDefinition(processDefinitionCategory, deploymentName, processColl, null);

        System.out.println("PAUSE");
    }

    /**
     * 并行任务定义
     */
    @Test
    public void generateBingXingRenWu() {
        Collection<Process> processColl = new ArrayList<>();
        Process process = new Process();
        String PROCESSID = "测试流程基本功能PROCESSID";
        String PROCESSNAME = "测试流程基本功能PROCESSNAME";
        String processDefinitionCategory = "测试流程基本功能";
        String deploymentName = "测试流程基本功能deploymentName";

        String taskKey1 = "taskDefinitionKey1";
        String taskName1 = "1-第一步";
        String taskKey2 = "taskDefinitionKey2";
        String taskName2 = "1-第二步";
        String taskKey3 = "taskDefinitionKey3";
        String taskName3 = "2-第一步";
        String taskKey4 = "taskDefinitionKey4";
        String taskName4 = "2-第二步";

        String operation1 = TaskOperation.AGREE.getOperation();
        String operationName1 = "同意";
        String operation2 = TaskOperation.DISAGREE.getOperation();
        String operationName2 = "不同意，结束流程";
        String operation3 = TaskOperation.RETURN.getOperation();
        String operationName3 = "退回修改";

        process.setId(PROCESSID);
        process.setName(PROCESSNAME);

        process.addFlowElement(FlowElementUtil.createStartEvent());
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey1, taskName1, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey2, taskName2, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey3, taskName3, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey4, taskName4, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createEndEvent());

        process.addFlowElement(FlowElementUtil.createParallelGateway("createParallelGateway1"));
        process.addFlowElement(FlowElementUtil.createParallelGateway("createParallelGateway2"));


        process.addFlowElement(FlowElementUtil.createSequenceFlow("startEvent", "createParallelGateway1", "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createParallelGateway1", taskKey1, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createParallelGateway1", taskKey3, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey1, taskKey2, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey3, taskKey4, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey2, "createParallelGateway2", "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey4, "createParallelGateway2", "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createParallelGateway2", "endEvent", "", ""));

        processColl.add(process);
        Result<Void> result = processProvider.generateProcessDefinition(processDefinitionCategory, deploymentName, processColl, null);

        System.out.println("PAUSE");
    }

    /**
     * 会签定义
     */
    @Test
    public void generateHuiQian() {
        Collection<Process> processColl = new ArrayList<>();
        Process process = new Process();
        String PROCESSID = "测试流程基本功能PROCESSID";
        String PROCESSNAME = "测试流程基本功能PROCESSNAME";
        String processDefinitionCategory = "测试流程基本功能";
        String deploymentName = "测试流程基本功能deploymentName";

        String taskKey1 = "taskDefinitionKey1";
        String taskName1 = "第一步";
        String taskKey2 = "taskDefinitionKey2";
        String taskName2 = "第二步";
        String taskKey3 = "taskDefinitionKey3";
        String taskName3 = "第三步";

        String operation1 = TaskOperation.AGREE.getOperation();
        String operationName1 = "同意";
        String operation2 = TaskOperation.DISAGREE.getOperation();
        String operationName2 = "不同意，结束流程";
        String operation3 = TaskOperation.RETURN.getOperation();
        String operationName3 = "退回修改";

        process.setId(PROCESSID);
        process.setName(PROCESSNAME);

        process.addFlowElement(FlowElementUtil.createStartEvent());
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey1, taskName1, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey2, taskName2, null, null, TaskCategory.COUNTER_SIGN));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey3, taskName3, null, null, TaskCategory.COUNTER_SIGN));
        process.addFlowElement(FlowElementUtil.createEndEvent());

        process.addFlowElement(FlowElementUtil.createSequenceFlow("startEvent", taskKey1, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey1, taskKey2, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey2, taskKey3, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey3, "endEvent", "", ""));

        processColl.add(process);
        Result<Void> result = processProvider.generateProcessDefinition(processDefinitionCategory, deploymentName, processColl, null);

        System.out.println("PAUSE");
    }

    /**
     * 多任务实例并存定义
     */
    @Test
    public void generateBingCun() {
        Collection<Process> processColl = new ArrayList<>();
        Process process = new Process();
        String PROCESSID = "测试流程基本功能PROCESSID";
        String PROCESSNAME = "测试流程基本功能PROCESSNAME";
        String processDefinitionCategory = "测试流程基本功能";
        String deploymentName = "测试流程基本功能deploymentName";

        String taskKey1 = "taskDefinitionKey1";
        String taskName1 = "第一步";
        String taskKey2 = "taskDefinitionKey2";
        String taskName2 = "第二步";
        String taskKey3 = "taskDefinitionKey3";
        String taskName3 = "第三步";
        String taskKey4 = "taskDefinitionKey4";
        String taskName4 = "第四步";
        String taskKey5 = "taskDefinitionKey5";
        String taskName5 = "第五步";

        String operation1 = TaskOperation.AGREE.getOperation();
        String operationName1 = "同意";
        String operation2 = TaskOperation.DISAGREE.getOperation();
        String operationName2 = "不同意，结束流程";
        String operation3 = TaskOperation.RETURN.getOperation();
        String operationName3 = "退回修改";
        String operation4 = TaskOperation.COUNTER_SIGN.getOperation();


        process.setId(PROCESSID);
        process.setName(PROCESSNAME);

        process.addFlowElement(FlowElementUtil.createStartEvent());
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey1, taskName1, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey2, taskName2, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey3, taskName3, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey4, taskName4, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createUserTask(taskKey5, taskName5, null, null, TaskCategory.NORMAL));
        process.addFlowElement(FlowElementUtil.createEndEvent());


        process.addFlowElement(FlowElementUtil.createParallelGateway("createParallelGateway1"));
        process.addFlowElement(FlowElementUtil.createParallelGateway("createParallelGateway2"));
//        "${pass=='" + operation1 + "' && test.contains('1')}"
//        "${pass=='" + operation1 + "' && test.contains('2')}"

        process.addFlowElement(FlowElementUtil.createSequenceFlow("startEvent", taskKey1, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey1, "createParallelGateway1", "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createParallelGateway1", taskKey2, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createParallelGateway1", taskKey3, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey2, taskKey4, "", "${pass=='" + operation1 + "'}"));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey2, "createParallelGateway2", "", "${pass=='" + operation2 + "'}"));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey4, "createParallelGateway2", "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey3, "createParallelGateway2", "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow("createParallelGateway2", taskKey5, "", ""));
        process.addFlowElement(FlowElementUtil.createSequenceFlow(taskKey5, "endEvent", "", ""));

        processColl.add(process);
        Result<Void> result = processProvider.generateProcessDefinition(processDefinitionCategory, deploymentName, processColl, null);

        System.out.println("PAUSE");
    }

    @Test
    public void testJump() {
        String processInstanceId = "1782501";
        String targetTaskDefinitionKey = "taskDefinitionKey1";
        managementProvider.jump(processInstanceId, targetTaskDefinitionKey, null, null, null);
    }

    @Test
    public void testQueryTaskDefinitionList() {
        QueryProcessDefinitionCondition condition = new QueryProcessDefinitionCondition();
        condition.setIsLatestVersion(true);
        condition.setProcessDefinitionKey("CUSTOM_PROC_10365");
        List<TProcessDefinitionList> datas = processProvider.queryProcessDefinitionList(condition, 0, Integer.MAX_VALUE, null).getValue().getDatas();
        System.out.println("!!");
//        for (TProcessDefinitionList data : datas) {
//            Result<List<TTaskDefinitionList>> cgzy = processProvider.queryTaskDefinitionList(data.getProcessDefinition().getId(), Lists.newArrayList("CGZY"), null);
//            System.out.println("!");
//        }

    }

    /**
     * 开始任务
     */
    @Test
    public void startTask() {
        //流程定义的key
        String processDefinitionKey = "测试流程基本功能PROCESSID";
        //用户Id
        String userId = "zsId";
        //自定义变量，与该任务关联
        Map<String, Object> variables = new HashMap<>();
//        variables.put(WFConfig.V_WAITTING_DEAL_NAME, "任务fnns标题");
//        variables.put(WFConfig.V_PROCESS_START_STAFF, "申请人fnns名称");
//        variables.put(WFConfig.V_ORG_NAME, "申请人fnns部门名称");
//        variables.put("qjsq_roleId", "qjsq_FZC");
        Result<TProcessInstance> result = taskProvider.generateTask(processDefinitionKey, userId, variables, null);
        System.out.println("PAUSE");
    }

    /**
     * 完成任务
     */
    @Test
    public void completeTask() {
        //任务流程实例ID
        String processInstanceId = "1877508";

        String operation1 = TaskOperation.AGREE.getOperation();
        String operation2 = TaskOperation.DISAGREE.getOperation();
        String operation3 = TaskOperation.RETURN.getOperation();
        String operation4 = TaskOperation.COUNTER_SIGN.getOperation();

        String operation = operation1;

        //下一环节执行信息
//        Collection<NextTaskInfo> nextTaskInfos =
//                Lists.newArrayList(new NextTaskInfo("taskDefinitionKey3", "test1", TaskCategory.COUNTER_SIGN.getCategory(), operation)
//                        ,new NextTaskInfo("taskDefinitionKey3", "test2", TaskCategory.COUNTER_SIGN.getCategory(), operation));
//        Collection<NextTaskInfo> nextTaskInfos =
//                Lists.newArrayList(new NextTaskInfo("taskDefinitionKey5", "ttt", TaskCategory.NORMAL.getCategory(), operation));
//        Collection<NextTaskInfo> nextTaskInfos =
//                Lists.newArrayList(new NextTaskInfo("taskDefinitionKey2", "zqId", TaskCategory.COUNTER_SIGN.getCategory(), operation)
//                        , new NextTaskInfo("taskDefinitionKey2", "qbId", TaskCategory.COUNTER_SIGN.getCategory(), operation));
        Collection<NextTaskInfo> nextTaskInfos = null;
        //任务ID
        String taskId = "1877508";


        //自定义变量，与该任务关联
        Map<String, Object> variables = new HashMap<>();
//        variables.put("qjsq_roleId", "qjsq_FZC");
//        variables.put("haveOther", "0");

//        variables.put("sqsp_minjob", "5");
//        variables.put("sqsp_maxjob", "5");

        Result<List<Task>> result = taskProvider.completeTask(processInstanceId, nextTaskInfos, taskId, operation, variables, null);

        System.out.println("PAUSE");
    }

    @Test
    public void deleteTask() {
//        taskService.deleteTasks(Lists.newArrayList("1722502", "1715002"), true);

        runtimeService.deleteProcessInstance("1877501", "流程监控");
    }


    /**
     * 完成任务-会签
     */
    @Test
    public void completeTaskForCounterSign() {
        String operation1 = TaskOperation.AGREE.getOperation();
        String operation2 = TaskOperation.DISAGREE.getOperation();
        String operation3 = TaskOperation.RETURN.getOperation();
        String operation = null;

        //任务流程实例ID
        String processInstanceId = "1877501";
        //下一环节执行信息
        Collection<NextTaskInfo> nextTaskInfos = Lists.newArrayList(
                new NextTaskInfo("taskDefinitionKey2", "lsId", TaskCategory.NORMAL.getCategory(), null),
                new NextTaskInfo("taskDefinitionKey2", "wwId", TaskCategory.NORMAL.getCategory(), null));
//        Collection<NextTaskInfo> nextTaskInfos = Lists.newArrayList(
//                new NextTaskInfo("taskDefinitionKey3", "lsId", TaskCategory.COUNTER_SIGN.getCategory(), null));
        //任务ID
        String taskId = "1877508";
        //自定义变量，与该任务关联
        Map<String, Object> variables = new HashMap<>();
//        variables.put(WFConfig.V_COUNTER_SIGN_ACTIVITI_COLLECTION, Lists.newArrayList("lsId", "wwId"));

        Result<List<Task>> result = taskProvider.completeTask(processInstanceId, nextTaskInfos, taskId, null, variables, null);
        System.out.println("PAUSE");
    }

    /**
     * 委派任务
     */
    @Test
    public void testDelegateTask() {
        Collection<THandleTaskInfo> delegateTaskInfos = Lists.newArrayList();
        delegateTaskInfos.add(new THandleTaskInfo("userId1", "代理1"));
//        delegateTaskInfos.add(new THandleTaskInfo("userId2", "第二步-代理2"));


        Result<List<Task>> delegate = taskProvider.delegate("1360005", delegateTaskInfos, null, null);
        System.out.println("Succ");
    }

    /**
     * 加签
     */
    @Test
    public void testAddSign() {
        String taskId = "3357507";
        Collection<THandleTaskInfo> addSignTaskInfos = Lists.newArrayList();
        addSignTaskInfos.add(new THandleTaskInfo("hlId", "测试加签任务3"));
        addSignTaskInfos.add(new THandleTaskInfo("zqId", "测试加签任务4"));
        Result<List<Task>> result = taskProvider.addSign(taskId, addSignTaskInfos, null, null);

        System.out.println("Succ!");
    }

    @Test
    public void testHisQuery() throws ParseException {
        QueryHistoricTaskCondition condition = new QueryHistoricTaskCondition();

//        condition.setIsProcFinished(false);
//        condition.setProcCategoryList(Lists.newArrayList("测试流程基本功能-查询"));
//        condition.setProcNameLike("程基本功能PRO");
        condition.setProcInstanceId("1117505");
//        condition.setTaskTitleLike("务fnns标");
//        condition.setApplyAssigneeId("0001T110000000002UCF");
//        condition.setApplyAssigneeLike("请人fnns名");
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        condition.setApplyMinTime(dateFormat.parse("2016-12-27"));
//        condition.setApplyMaxTime(dateFormat.parse("2016-12-29"));
//        condition.setApplyAssigneeDepartmentLike("请人fnns部门");

//        condition.setAdvancedSearch("1");

        Result<Pager<THistoricTaskList>> result = historyProvider.queryHistoricTaskList(condition, 0, 1000, null);
        List<THistoricTaskList> datas = result.getValue() == null ? null : result.getValue().getDatas();
        if (datas != null) {
            for (THistoricTaskList data : datas) {
                System.out.println("taskId:" + data.getHistoricTaskInstance().getId());
            }
        }
        QueryHistoricInstanceCondition condition1 = new QueryHistoricInstanceCondition();
//        condition1.setInvolvedUser("0001T110000000002H2A");
//        condition1.setProcInstanceId("2340665");
//        condition1.setApplyAssigneeId("0001T110000000002UCF");
//        condition1.setAdvancedSearch("神经病");
        condition1.setInvolvedUser("0001T110000000002H2A");
//        condition1.setProcDefNameLike("神经病");
        Result<Pager<THistoricInstanceList>> pagerResult = historyProvider.queryHistoricInstanceList(condition1, 0, 100000, null);
        List<THistoricInstanceList> datas1 = pagerResult.getValue().getDatas();
        if (datas1 != null) {
            for (THistoricInstanceList tHistoricInstanceList : datas1) {
                if (tHistoricInstanceList.getHistoricProcessInstance().getId().equals("2340665")) {
                    System.out.println("!!");
                }
            }
        }

        System.out.println("SUCC");
    }

    /**
     * 查询已部署流程列表
     */
    @Test
    public void queryProcessDefinitionList() throws ParseException {
        QueryProcessDefinitionCondition condition = new QueryProcessDefinitionCondition();
//        condition.setProcessDefinitionNameLike(null);
//        condition.setIsLatestVersion(true);
//        condition.setProcessDefinitionNameLike("请假申请PROCESSNAME");
//        condition.setProcessDefinitionKey("LEAVEADDPROCESSID");
        condition.setProcessDefinitionCategory("4");
        condition.setIsLatestVersion(true);
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        condition.setUpdateMinTime(format.parse("2016-12-11 00:00:00"));
//        condition.setUpdateMaxTime(format.parse("2016-12-13 00:00:00"));
        condition.setOrderKeyType(QueryProcessDefinitionOrderKeyType.ID);
        condition.setOrderType(QueryOrderType.DESC);
        List<TProcessDefinitionList> datas = processProvider.queryProcessDefinitionList(condition, 0, Integer.MAX_VALUE, null).getValue().getDatas();
        if (datas != null) {
            for (TProcessDefinitionList data : datas) {
                System.out.println("Id:" + data.getProcessDefinition().getId());
                System.out.println("Key:" + data.getProcessDefinition().getKey());
                System.out.println("Name:" + data.getProcessDefinition().getName());
                System.out.println("===============================================");
            }
        }

        System.out.println("PAUSE");
    }

    @Test
    public void testDetail() {
        Result<TTaskDetail> tTaskDetailResult = taskProvider.queryTaskDetail("3522508", null);

//        taskProvider.fillBpmnVariables("2600001", "POSTTCPROCESSID:1:2577504");
        System.out.println("");
    }


    @Test
    public void queryTask() {
        QueryTaskCondition condition = new QueryTaskCondition();
//        condition.setProcessDefinitionKey("测试流程基本功能PROCESSID");
        condition.setProcessInstanceId("1117505");
        condition.setSuspensionState(SuspensionState.SUSPENDED);
        condition.setOrderKeyType(QueryTaskOrderKeyType.CREATE_TIME);
        condition.setOrderType(QueryOrderType.DESC);
        Result<Pager<TTaskList>> pagerResult = taskProvider.queryTaskList(condition, 0, Integer.MAX_VALUE, null);
        List<TTaskList> datas = pagerResult.getValue().getDatas();
        if (datas != null && datas.size() > 0) {
            for (TTaskList data : datas) {
                System.out.println("Id:" + data.getTask().getId());
                System.out.println("Name:" + data.getTask().getName());
                System.out.println("Assignee:" + data.getTask().getAssignee());
                System.out.println("ProcessInstanceId:" + data.getTask().getProcessInstanceId());
                System.out.println("ParentTaskId:" + data.getTask().getParentTaskId());
                System.out.println("ProcessDefinitionKey:" + data.getProcessDefinitionKey());

//                List<Task> subTasks = engine.getTaskService().getSubTasks(data.getTask().getId());
//                if(subTasks == null || subTasks.size() <= 0){
//                    continue;
//                }
//                for (Task subTask : subTasks) {
//                    System.out.println("Sub Task-------------------------------------------");
//                    System.out.println("Id:" + subTask.getId());
//                    System.out.println("Name:" + subTask.getName());
//                    System.out.println("Assignee:" + subTask.getAssignee());
//                    System.out.println("ProcessInstanceId:" + subTask.getProcessInstanceId());
//                    System.out.println("ParentTaskId:" + subTask.getParentTaskId());
//                }

                System.out.println("+++++++++++++++++++++++++++++++++++++++++");
            }
        }
        System.out.println("PAUSE");
    }

    //TODO 下一步任务的判断，没完成
    @Test
    public void testSys() {

//        List<Execution> execution = runtimeService.createExecutionQuery()
//                .processInstanceId("4822501")
//                .list();

        String procDefId = "测试流程基本功能PROCESSID:60:4820004";
        String curTaskDefinitionKey = "taskDefinitionKey1";
        String procInstId = "4822501";

//        BpmnModel processBpmnModel = repositoryService.getBpmnModel(procDefId);
//        List<Process> processList = processBpmnModel.getProcesses();
//        Process process = processList.get(0);
//        Collection<FlowElement> flowElementColl = process.getFlowElements();


        ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().processInstanceId(procInstId).singleResult();
//        List<Execution> list = runtimeService.createExecutionQuery().activityId(execution.getActivityId()).list();
        String defaultSequenceFlow = null;//(String) execution.getActivity().getProperty("default");ExecutionEntity

        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(procDefId);
        ActivityImpl curAct = processDefinition.findActivity(curTaskDefinitionKey);
        List<PvmTransition> outgoingTransitions = curAct.getOutgoingTransitions();
        //1.扫描outgoingTransitions，得出下一步所有可能的路径
        //2.路径中选择满足条件的所有路径
        //3.如果路径中有非UserTaskActivityBehavior的对象，则继续递归查询
        for (PvmTransition outgoingTransition : outgoingTransitions) {
            Expression skipExpression = outgoingTransition.getSkipExpression();
            if (!SkipExpressionUtil.isSkipExpressionEnabled(execution, skipExpression)) {
                if (defaultSequenceFlow == null || !outgoingTransition.getId().equals(defaultSequenceFlow)) {
                    Condition condition = (Condition) outgoingTransition.getProperty(BpmnParse.PROPERTYNAME_CONDITION);
                    if (condition == null || condition.evaluate(outgoingTransition.getId(), execution)) {
//                        transitionsToTake.add(outgoingTransition);
                        System.out.println("!!");
                    }
                }
            } else if (SkipExpressionUtil.shouldSkipFlowElement(execution, skipExpression)) {
//                transitionsToTake.add(outgoingTransition);
                System.out.println("!!");
            }
        }

        System.out.println("");
    }

}
