package org.jeff.javatool.tool.myactiviti.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 任务列表信息传输对象
 * Created by weijianfu on 2016/11/30.
 */
public class TTaskList implements Serializable {
    private Task task;
    private String processDefinitionKey;

    /** 变量 */
    private Map<String, Object> variables;
    /** 历史流程定义 */
    private ProcessDefinition processDefinition;

    private static final Logger LOG = LoggerFactory.getLogger(TTaskList.class);

    public TTaskList(){

    }

    public TTaskList(Task task, ProcessDefinition processDefinition, Map<String, Object> variables){
        this.task = task;
        if(this.task.getProcessDefinitionId() != null){
            String[] split = this.task.getProcessDefinitionId().split(":");
            this.processDefinitionKey = split[0];
        }else{
            LOG.error("[TTaskList.TTaskList]ProcessDefinitionId id null!taskId:" + task.getId());
        }
        this.processDefinition = processDefinition;
        this.variables = variables;
    }

    public static List<TTaskList> parseList(List<Task> tasks
            ,  List<ProcessDefinition> processDefinitionList
            , Map<String, Map<String, Object>> mapTaskId2Variables){
        List<TTaskList> tTaskLists = Lists.newArrayList();
        if(tasks == null || tasks.size() <= 0){
            return tTaskLists;
        }

        Map<String, ProcessDefinition> mapProcessDefinitionId2ProcessDefinition = getMapProcessDefinitionId2ProcessDefinition(processDefinitionList);


        for (Task task : tasks) {
            tTaskLists.add(new TTaskList(task
                    , mapProcessDefinitionId2ProcessDefinition == null ? null : mapProcessDefinitionId2ProcessDefinition.get(task.getProcessDefinitionId())
                    , mapTaskId2Variables == null ? null : mapTaskId2Variables.get(task.getId())));
        }
        return tTaskLists;
    }

    private static Map<String,ProcessDefinition> getMapProcessDefinitionId2ProcessDefinition(List<ProcessDefinition> processDefinitionList) {
        Map<String, ProcessDefinition> mapProcessDefinitionId2ProcessDefinition = Maps.newHashMap();
        if (processDefinitionList == null || processDefinitionList.size() <= 0) {
            return mapProcessDefinitionId2ProcessDefinition;
        }
        for (ProcessDefinition processDefinition : processDefinitionList) {
            mapProcessDefinitionId2ProcessDefinition.put(processDefinition.getId(), processDefinition);
        }
        return mapProcessDefinitionId2ProcessDefinition;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }


    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public ProcessDefinition getProcessDefinition() {
        return processDefinition;
    }

    public void setProcessDefinition(ProcessDefinition processDefinition) {
        this.processDefinition = processDefinition;
    }

}
