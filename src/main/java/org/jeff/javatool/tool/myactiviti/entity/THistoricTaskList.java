package org.jeff.javatool.tool.myactiviti.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 历史任务列表信息
 * Created by weijianfu on 2016/12/2.
 */
public class THistoricTaskList implements Serializable {

    /**
     * 历史任务实例
     */
    private HistoricTaskInstance historicTaskInstance;
    /**
     * 历史任务变量
     */
    private Map<String, Object> historicVariables;
    /**
     * 当前任务所属的流程实例
     */
    private HistoricProcessInstance processInstance;

    public THistoricTaskList() {

    }

    public THistoricTaskList(HistoricTaskInstance historicTaskInstance, Map<String, Object> historicVariables, HistoricProcessInstance processInstance) {
        this.historicTaskInstance = historicTaskInstance;
        this.historicVariables = historicVariables;
        this.processInstance = processInstance;
    }

    public static List<THistoricTaskList> parseList(List<HistoricTaskInstance> historicTaskInstanceList
            , List<HistoricVariableInstance> historicVariableInstances
            , List<HistoricProcessInstance> processInstanceList) {
        List<THistoricTaskList> tHistoricTaskList = Lists.newArrayList();
        if (historicTaskInstanceList == null || historicTaskInstanceList.size() <= 0) {
            return tHistoricTaskList;
        }
        Map<String, Map<String, Object>> mapProcessInstanceId2Variables = getMapProcessInstanceId2Variables(historicVariableInstances);
        Map<String, HistoricProcessInstance> mapProcessInstanceId2Instance = getMapProcessInstanceId2Instance(processInstanceList);

        for (HistoricTaskInstance historicTaskInstance : historicTaskInstanceList) {
            tHistoricTaskList.add(new THistoricTaskList(historicTaskInstance
                    , mapProcessInstanceId2Variables == null ? null : mapProcessInstanceId2Variables.get(historicTaskInstance.getProcessInstanceId())
                    , mapProcessInstanceId2Instance == null ? null : mapProcessInstanceId2Instance.get(historicTaskInstance.getProcessInstanceId())));
        }
        return tHistoricTaskList;
    }

    private static Map<String,HistoricProcessInstance> getMapProcessInstanceId2Instance(List<HistoricProcessInstance> processInstanceList) {
        Map<String, HistoricProcessInstance> mapProcessInstanceId2Instance = Maps.newHashMap();
        if (processInstanceList == null || processInstanceList.size() <= 0) {
            return mapProcessInstanceId2Instance;
        }
        for (HistoricProcessInstance instance : processInstanceList) {
            mapProcessInstanceId2Instance.put(instance.getId(), instance);
        }
        return mapProcessInstanceId2Instance;
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

    public HistoricTaskInstance getHistoricTaskInstance() {
        return historicTaskInstance;
    }

    public Map<String, Object> getHistoricVariables() {
        return historicVariables;
    }
    public HistoricProcessInstance getProcessInstance() {
        return processInstance;
    }
}
