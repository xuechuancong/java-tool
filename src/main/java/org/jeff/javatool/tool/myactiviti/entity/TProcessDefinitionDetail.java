package org.jeff.javatool.tool.myactiviti.entity;

import com.google.common.collect.Maps;
import org.activiti.engine.repository.ProcessDefinition;
import org.jeff.javatool.tool.myactiviti.utils.BpmnVariableUtil;

import java.util.Collection;
import java.util.Map;

/**
 * 流程定义详情传输对象
 * Created by weijianfu on 2016/12/1.
 */
public class TProcessDefinitionDetail {

    /**
     * 流程定义主体
     */
    private ProcessDefinition processDefinition;

    /**
     * 该流程定义包含的角色信息
     */
    private Map<String, Collection<String>> mapTaskDefinitionKey2RoleIds;

    /**
     * 该流程定义包含的下一步任务信息
     */
    private Map<String, Collection<TNextTaskInfo>> mapTaskDefinitionKey2TNextTaskInfos;


    public TProcessDefinitionDetail() {

    }

    public TProcessDefinitionDetail(ProcessDefinition processDefinition, Map<String, Object> allTaskVariables) {
        this.processDefinition = processDefinition;
        parseTaskVariables(allTaskVariables);
    }

    private void parseTaskVariables(Map<String, Object> allTaskVariables) {
        this.mapTaskDefinitionKey2RoleIds = Maps.newHashMap();
        this.mapTaskDefinitionKey2TNextTaskInfos = Maps.newHashMap();
        if (allTaskVariables == null) {
            return;
        }
        for (Map.Entry<String, Object> entry : allTaskVariables.entrySet()) {
            String taskDefinitionKey = entry.getKey();
            Map<String, Object> taskVariables = (Map<String, Object>) entry.getValue();
            Collection<String> roleIds = BpmnVariableUtil.encodeRoleIds(taskVariables);
            Collection<TNextTaskInfo> tNextTaskInfos = BpmnVariableUtil.encodeTNextTaskInfos(taskVariables);
            mapTaskDefinitionKey2RoleIds.put(taskDefinitionKey, roleIds);
            mapTaskDefinitionKey2TNextTaskInfos.put(taskDefinitionKey, tNextTaskInfos);
        }
    }

    public ProcessDefinition getProcessDefinition() {
        return processDefinition;
    }

    public void setProcessDefinition(ProcessDefinition processDefinition) {
        this.processDefinition = processDefinition;
    }

    public Map<String, Collection<String>> getMapTaskDefinitionKey2RoleIds() {
        return mapTaskDefinitionKey2RoleIds;
    }

    public void setMapTaskDefinitionKey2RoleIds(Map<String, Collection<String>> mapTaskDefinitionKey2RoleIds) {
        this.mapTaskDefinitionKey2RoleIds = mapTaskDefinitionKey2RoleIds;
    }

    public Map<String, Collection<TNextTaskInfo>> getMapTaskDefinitionKey2TNextTaskInfos() {
        return mapTaskDefinitionKey2TNextTaskInfos;
    }

    public void setMapTaskDefinitionKey2TNextTaskInfos(Map<String, Collection<TNextTaskInfo>> mapTaskDefinitionKey2TNextTaskInfos) {
        this.mapTaskDefinitionKey2TNextTaskInfos = mapTaskDefinitionKey2TNextTaskInfos;
    }
}
