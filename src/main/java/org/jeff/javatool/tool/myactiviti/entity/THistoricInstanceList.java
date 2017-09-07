package org.jeff.javatool.tool.myactiviti.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 历史流程列表信息
 * Created by weijianfu on 2016/12/2.
 */
public class THistoricInstanceList implements Serializable {

    /**
     * 历史流程实例
     */
    private HistoricProcessInstance historicProcessInstance;
    /**
     * 历史变量
     */
    private Map<String, Object> historicVariables;
    /**
     * 当前任务所属的流程中，正在进行的任务集合
     */
    private List<Task> curTaskList;
    /**
     * 历史流程定义
     */
    private ProcessDefinition processDefinition;

    public THistoricInstanceList() {

    }

    public THistoricInstanceList(HistoricProcessInstance historicProcessInstance
            , Map<String, Object> historicVariables
            , List<Task> curTaskList
            , ProcessDefinition processDefinition) {
        this.historicProcessInstance = historicProcessInstance;
        this.historicVariables = historicVariables;
        this.curTaskList = curTaskList;
        this.processDefinition = processDefinition;
    }

    public static List<THistoricInstanceList> parseList(List<HistoricVariableInstance> historicVariableInstances
            , List<Task> curTaskList
            , List<HistoricProcessInstance> processInstanceList
            , List<ProcessDefinition> processDefinitionList) {
        List<THistoricInstanceList> tHistoricInstanceList = Lists.newArrayList();
        if (processInstanceList == null || processInstanceList.size() <= 0) {
            return tHistoricInstanceList;
        }

        Map<String, Map<String, Object>> mapProcessInstanceId2Variables = getMapProcessInstanceId2Variables(historicVariableInstances);
        Map<String, List<Task>> mapProcessInstanceId2CurTaskList = getMapProcessInstanceId2CurTaskList(curTaskList);
        Map<String, ProcessDefinition> mapProcessDefinitionId2ProcessDefinition = getMapProcessDefinitionId2ProcessDefinition(processDefinitionList);

        for (HistoricProcessInstance historicProcessInstance : processInstanceList) {
            tHistoricInstanceList.add(new THistoricInstanceList(historicProcessInstance
                    , mapProcessInstanceId2Variables == null ? null : mapProcessInstanceId2Variables.get(historicProcessInstance.getId())
                    , mapProcessInstanceId2CurTaskList == null ? null : mapProcessInstanceId2CurTaskList.get(historicProcessInstance.getId())
                    , mapProcessDefinitionId2ProcessDefinition == null ? null : mapProcessDefinitionId2ProcessDefinition.get(historicProcessInstance.getProcessDefinitionId())));
        }
        return tHistoricInstanceList;
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

    private static Map<String, Map<String, Object>> getMapProcessInstanceId2Variables(List<HistoricVariableInstance> historicVariableInstances) {
        Map<String, Map<String, Object>> mapProcessInstanceId2Variables = Maps.newHashMap();
        if (historicVariableInstances == null || historicVariableInstances.size() <= 0) {
            return mapProcessInstanceId2Variables;
        }
        for (HistoricVariableInstance historicVariableInstance : historicVariableInstances) {
            Map<String, Object> curVariables = mapProcessInstanceId2Variables.get(historicVariableInstance.getProcessInstanceId());
            if (curVariables == null) {
                curVariables = Maps.newHashMap();
            }
            curVariables.put(historicVariableInstance.getVariableName(), historicVariableInstance.getValue());
            mapProcessInstanceId2Variables.put(historicVariableInstance.getProcessInstanceId(), curVariables);
        }
        return mapProcessInstanceId2Variables;
    }

    private static Map<String, List<Task>> getMapProcessInstanceId2CurTaskList(List<Task> curTaskList) {
        Map<String, List<Task>> mapProcessInstanceId2CurTaskList = Maps.newHashMap();
        if (curTaskList == null || curTaskList.size() <= 0) {
            return mapProcessInstanceId2CurTaskList;
        }
        for (Task task : curTaskList) {
            List<Task> tasks = mapProcessInstanceId2CurTaskList.get(task.getProcessInstanceId());
            if (tasks == null) {
                tasks = Lists.newArrayList();
            }
            tasks.add(task);
            mapProcessInstanceId2CurTaskList.put(task.getProcessInstanceId(), tasks);
        }
        return mapProcessInstanceId2CurTaskList;
    }

    public HistoricProcessInstance getHistoricProcessInstance() {
        return historicProcessInstance;
    }

    public void setHistoricProcessInstance(HistoricProcessInstance historicProcessInstance) {
        this.historicProcessInstance = historicProcessInstance;
    }

    public Map<String, Object> getHistoricVariables() {
        return historicVariables;
    }

    public void setHistoricVariables(Map<String, Object> historicVariables) {
        this.historicVariables = historicVariables;
    }

    public List<Task> getCurTaskList() {
        return curTaskList;
    }

    public void setCurTaskList(List<Task> curTaskList) {
        this.curTaskList = curTaskList;
    }

    public ProcessDefinition getProcessDefinition() {
        return processDefinition;
    }

    public void setProcessDefinition(ProcessDefinition processDefinition) {
        this.processDefinition = processDefinition;
    }
}
