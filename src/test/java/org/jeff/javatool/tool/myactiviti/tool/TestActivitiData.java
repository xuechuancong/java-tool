package org.jeff.javatool.tool.myactiviti.tool;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.jeff.javatool.tool.myactiviti.WFHistoryProviderImpl;
import org.jeff.javatool.tool.myactiviti.entity.THistoricInstanceList;
import org.jeff.javatool.tool.myactiviti.entity.TTaskList;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryHistoricInstanceCondition;
import org.jeff.javatool.tool.myactiviti.entity.query.QueryTaskCondition;
import org.jeff.javatool.tool.myactiviti.entity.result.Pager;
import org.jeff.javatool.tool.myactiviti.entity.result.Result;
import org.jeff.javatool.tool.myactiviti.intfc.IWFProcessProvider;
import org.jeff.javatool.tool.myactiviti.intfc.IWFTaskProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;


/**
 * activiti数据管理 Created by weijianfu on 2016/11/30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestActivitiData {

    @Autowired
    private IWFProcessProvider processProvider;
    @Autowired
    private IWFTaskProvider taskProvider;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private WFHistoryProviderImpl historyProvider;

    /**
     * 删除流程实例相关的所有数据，会记录在历史表
     */
    @Test
    public void deleteProcessInstance1() {
        QueryTaskCondition condition = new QueryTaskCondition();
        condition.setProcessDefinitionKey("CUSTSUPUPDATEPROCESSID");
        Result<Pager<TTaskList>> TTaskLists = taskProvider.queryTaskList(condition, 0, Integer.MAX_VALUE, null);
        Pager<TTaskList> TTaskList = TTaskLists.getValue();
        List<org.jeff.javatool.tool.myactiviti.entity.TTaskList> TaskList = TTaskList.getDatas();
        for (org.jeff.javatool.tool.myactiviti.entity.TTaskList task : TaskList) {

            runtimeService.deleteProcessInstance(task.getTask().getProcessInstanceId(), "test");
        }


        System.out.println("Succ!");
    }

    /**
     * 删除流程实例相关的所有数据，会记录在历史表
     */
    @Test
    public void deleteProcessInstances() {
        List<String> processInstanceIdList = Lists.newArrayList("1307501");

        for (String processInstanceId : processInstanceIdList) {
            deleteData(processInstanceId);
        }
    }

    public void deleteData(String processInstanceIdDelete) {
        //删除正在执行数据
        QueryTaskCondition condition = new QueryTaskCondition();
        condition.setProcessInstanceId(processInstanceIdDelete);
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
                for (int i = 0; i < taskList.size(); i++) {
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
        condition.setProcessInstanceId(processInstanceIdDelete);
        Result<Pager<THistoricInstanceList>> pagerResult = historyProvider.queryHistoricInstanceList(conditionHis, 0, Integer.MAX_VALUE, null);
        Pager<THistoricInstanceList> value = pagerResult.getValue();
        if (value != null && value.getDatas() != null) {
            List<THistoricInstanceList> datas = value.getDatas();
            for (THistoricInstanceList data : datas) {
                historyService.deleteHistoricProcessInstance(data.getHistoricProcessInstance().getId());
            }
        }
        //删除定义数据
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().processDefinitionId(processInstanceIdDelete).list();
        if (list != null && list.size() > 0) {
            for (ProcessDefinition processDefinition : list) {
                repositoryService.deleteDeployment(processDefinition.getDeploymentId());
            }
        }
    }

    /**
     * 完成任务时，处理代理任务逻辑:略过当前任务,删除所有taskDefinitionKey相同的任务
     */
    private void dealDelegate(TaskEntity curTask) {
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


    @Test
    public void deleteHis() {
        String processInstanceId = "3337501";
        historyService.deleteHistoricProcessInstance(processInstanceId);
        System.out.println("Succ!");
    }


    /**
     * 根据流程定义Key删除所有版本的定义信息
     */
    @Test
    public void deleteProcessDefinition() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId("QUITLIPROCESSID:1:62504").list();
        for (ProcessDefinition processDefinition : list) {
            repositoryService.deleteDeployment(processDefinition.getDeploymentId());
        }
    }

    public void deleteProcessDefinition(String processDefinitionKey) {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).list();
        if (list != null && list.size() > 0) {
            for (ProcessDefinition processDefinition : list) {
            }
        }
    }


    @Test
    public void generateKeShang() {
        /**
         * 请移步：
         * {@link com.dx.portal.processmgmt.definition.finance.TestKeShangShenQing#generateKeShangShenQing}
         */
    }

}
