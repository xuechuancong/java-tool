package org.jeff.javatool.tool.myactiviti.domain.vo;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by weijianfu on 2017/8/22.
 */
public class JumpVO implements Serializable {

    private String processInstanceId;
    private String targetTaskDefinitionKey;
    private String userId;
    private String taskId;//当前任务id
    private Map<String, Object> taskShow;
    private Map<String, Object> ex;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getTargetTaskDefinitionKey() {
        return targetTaskDefinitionKey;
    }

    public void setTargetTaskDefinitionKey(String targetTaskDefinitionKey) {
        this.targetTaskDefinitionKey = targetTaskDefinitionKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Object> getTaskShow() {
        return taskShow;
    }

    public void setTaskShow(Map<String, Object> taskShow) {
        this.taskShow = taskShow;
    }

    public Map<String, Object> getEx() {
        return ex;
    }

    public void setEx(Map<String, Object> ex) {
        this.ex = ex;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
