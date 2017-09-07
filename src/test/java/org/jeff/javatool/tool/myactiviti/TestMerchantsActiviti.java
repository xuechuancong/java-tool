package org.jeff.javatool.tool.myactiviti;

import com.google.common.collect.Lists;
import org.activiti.engine.task.Task;
import org.jeff.javatool.tool.myactiviti.config.*;
import org.jeff.javatool.tool.myactiviti.entity.*;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryHistoricTaskCondition;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryProcessDefinitionCondition;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryTaskCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;
import org.jeff.javatool.tool.myactiviti.intfc.IWFHistoryProvider;
import org.jeff.javatool.tool.myactiviti.intfc.IWFProcessProvider;
import org.jeff.javatool.tool.myactiviti.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 以客商管理为demo，展示各接口用法
 * Created by weijianfu on 2016/11/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMerchantsActiviti {

    @Autowired
    private IWFHistoryProvider historyProvider;
    @Autowired
    private IWFProcessProvider processProvider;
    @Autowired
    private WFTaskProviderImpl taskProvider;

    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    /**
     * 查询已部署流程列表
     */
    @Test
    public void queryProcessDefinitionList() {
        QueryProcessDefinitionCondition condition = new QueryProcessDefinitionCondition();
        condition.setProcessDefinitionNameLike(null);
        condition.setIsLatestVersion(true);
        condition.setOrderKeyType(QueryProcessDefinitionOrderKeyType.DEPLOYMENT_ID);
        condition.setOrderType(QueryOrderType.ASC);
        List<TProcessDefinitionList> datas = processProvider.queryProcessDefinitionList(condition, 0, Integer.MAX_VALUE, null).getValue().getDatas();
        if (datas != null) {
            for (TProcessDefinitionList data : datas) {
                System.out.println("Id:" + data.getProcessDefinition().getId());
                System.out.println("Key:" + data.getProcessDefinition().getKey());
                System.out.println("Name:" + data.getProcessDefinition().getName());
                System.out.println("ResourceName:" + data.getProcessDefinition().getResourceName());
                System.out.println("===============================================");
                logger.debug("++++++++++++++");
            }
        }

        System.out.println("PAUSE");
    }

    /**
     * 查询已部署流程详情
     */
    @Test
    public void queryProcessDefinitionDetail() {
        TProcessDefinitionDetail data = processProvider.queryProcessDefinitionDetail("客商申请PROCESSID:20:342503", null).getValue();
        if (data != null) {
            System.out.println("Id:" + data.getProcessDefinition().getId());
            System.out.println("Key:" + data.getProcessDefinition().getKey());
            System.out.println("Name:" + data.getProcessDefinition().getName());
            System.out.println("MapTaskDefinitionKey2RoleIds:" + JsonUtil.serialize(data.getMapTaskDefinitionKey2RoleIds()));
            System.out.println("MapTaskDefinitionKey2TNextTaskInfos:" + JsonUtil.serialize(data.getMapTaskDefinitionKey2TNextTaskInfos()));
            System.out.println("===============================================");
        }

        System.out.println("PAUSE");
    }

    /**
     * 开始任务
     */
    @Test
    public void startTask() {
        //流程定义的key
        String processDefinitionKey = "客商申请PROCESSID";
        //用户Id
        String userId = "zsId";
        //自定义变量，与该任务关联
        Map<String, Object> variables = new HashMap<>();
        Result<TProcessInstance> result = taskProvider.generateTask(processDefinitionKey, userId, variables, null);
        System.out.println("PAUSE");
    }

    /**
     * 查询任务列表
     */
    @Test
    public void findZSTask() {//282505
        QueryTaskCondition condition = new QueryTaskCondition();
        condition.setAssignee("zsId");
        List<TTaskList> list = taskProvider.queryTaskList(condition, 0, Integer.MAX_VALUE, null).getValue().getDatas();
        showTaskList(list);
        System.out.println("@@@@@@@@@@@@@@@@@@ 以上zsId @@@@@@@@@@@@@@@@@@@@@@@@@");

        condition.setAssignee("lsId");
        List<TTaskList> list1 = taskProvider.queryTaskList(condition, 0, Integer.MAX_VALUE, null).getValue().getDatas();
        showTaskList(list1);
        System.out.println("@@@@@@@@@@@@@@@@@@ 以上lsId @@@@@@@@@@@@@@@@@@@@@@@@@");

        condition.setAssignee("wwId");
        List<TTaskList> list2 = taskProvider.queryTaskList(condition, 0, Integer.MAX_VALUE, null).getValue().getDatas();
        showTaskList(list2);
        System.out.println("@@@@@@@@@@@@@@@@@@ 以上wwId @@@@@@@@@@@@@@@@@@@@@@@@@");

        condition.setAssignee("hlId");
        List<TTaskList> list3 = taskProvider.queryTaskList(condition, 0, Integer.MAX_VALUE, null).getValue().getDatas();
        showTaskList(list3);
        System.out.println("@@@@@@@@@@@@@@@@@@ 以上hlId @@@@@@@@@@@@@@@@@@@@@@@@@");

        condition.setAssignee("zqId");
        List<TTaskList> list4 = taskProvider.queryTaskList(condition, 0, Integer.MAX_VALUE, null).getValue().getDatas();
        showTaskList(list4);
        System.out.println("@@@@@@@@@@@@@@@@@@ 以上zqId @@@@@@@@@@@@@@@@@@@@@@@@@");

        condition.setAssignee("qbId");
        List<TTaskList> list5 = taskProvider.queryTaskList(condition, 0, Integer.MAX_VALUE, null).getValue().getDatas();
        showTaskList(list5);
        System.out.println("@@@@@@@@@@@@@@@@@@ 以上qbId @@@@@@@@@@@@@@@@@@@@@@@@@");

    }

    @Test
    public void testString() {
        String test = "taskDefinitionKey2@@@团队负责人审核@@@tuanDuiFuZeRenId@@@null";
        String[] split = test.split("@@@");
        System.out.println("");
    }

    @Test
    public void testDetail() {
        Result<TTaskDetail> tTaskDetailResult = taskProvider.queryTaskDetail("2665005", null);

//        taskProvider.fillBpmnVariables("2600001", "POSTTCPROCESSID:1:2577504");
        System.out.println("");
    }


    /**
     * 完成任务
     */
    @Test
    public void completeZSTask() {
        //任务流程实例ID
        String processInstanceId = "282501";
        String pass = TaskOperation.AGREE.getOperation();//1同意 2不同意,结束流程

        //下一环节执行信息
        Collection<NextTaskInfo> nextTaskInfos = Lists.newArrayList(new NextTaskInfo("taskDefinitionKey2", "lsId", TaskCategory.NORMAL.getCategory(), pass));
        //任务ID
        String taskId = "282505";
        //自定义变量，与该任务关联
        Map<String, Object> variables = new HashMap<>();
        variables.put("completeZSTask", "completeZSTask");
        Result<List<Task>> result = taskProvider.completeTask(processInstanceId, nextTaskInfos, taskId, pass, variables, null);
        System.out.println("PAUSE");
    }

    /**
     * 查询历史任务
     */
    @Test
    public void queryHistory() {
        QueryHistoricTaskCondition condition = new QueryHistoricTaskCondition();
        condition.setProcInstanceId("230001");
//        condition.setTaskId("");
        condition.setOrderKeyType(QueryHistoricTaskOrderKeyType.TASK_CREATE_TIME);
        condition.setOrderType(QueryOrderType.DESC);
        Result<Pager<THistoricTaskList>> pagerResult = historyProvider.queryHistoricTaskList(condition, 0, Integer.MAX_VALUE, null);
//        List<THistoricTaskList> datas = pagerResult.getValue() == null ? null : pagerResult.getValue().getDatas();
//        if(datas != null && datas.size() > 0){
//            for (THistoricTaskList data : datas) {
//                Map<String, Object> processVariables = data.getHistoricProcessInstance().getProcessVariables();
//                System.out.println("processVariables:" + JsonUtil.serialize(processVariables));
//            }
//        }

        System.out.println("");
    }


    /**
     * 显示任务详情
     */
    private void showTaskList(Collection<TTaskList> list) {
        if (list != null && list.size() > 0) {
            for (TTaskList task : list) {
                /**
                 * 查询任务详细信息
                 */
                Result<TTaskDetail> tTaskDetailResult = taskProvider.queryTaskDetail(task.getTask().getId(), null);
                TTaskDetail tTaskDetail = tTaskDetailResult.getValue();
                System.out.println("流程实例ID：" + tTaskDetail.getTask().getProcessInstanceId());
                System.out.println("执行定义Key:" + tTaskDetail.getProcessDefinitionKey());
                System.out.println("执行对象ID:" + tTaskDetail.getTask().getExecutionId());
                System.out.println("任务ID:" + tTaskDetail.getTask().getId());
                System.out.println("任务名称:" + tTaskDetail.getTask().getName());
                System.out.println("Assignee:" + tTaskDetail.getTask().getAssignee());
                System.out.println("Owner:" + tTaskDetail.getTask().getOwner());
                System.out.println("任务的创建时间:" + tTaskDetail.getTask().getCreateTime());
                System.out.println("流程定义ID:" + tTaskDetail.getTask().getProcessDefinitionId());
                System.out.println("CurRoleIds:" + JsonUtil.serialize(tTaskDetail.getCurRoleIds()));
                System.out.println("NextTaskInfos:" + JsonUtil.serialize(tTaskDetail.getNextTaskInfos()));
                System.out.println("getVariables:" + JsonUtil.serialize(tTaskDetail.getVariables()));
                System.out.println("getTaskLocalVariables:" + JsonUtil.serialize(tTaskDetail.getTask().getTaskLocalVariables()));
                System.out.println("getProcessVariables:" + JsonUtil.serialize(tTaskDetail.getTask().getProcessVariables()));
                System.out.println("########################################################");
            }
        }
    }


}
