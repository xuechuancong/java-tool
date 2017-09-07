package org.jeff.javatool.tool.myactiviti.domain.vo;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by weijianfu on 2017/5/31.
 */
public class DelegateProductVO implements Serializable {

    private String taskId;
    private String userIds;
    private String taskDefId;
    private String procDefKey;
    private Map<String, Object> taskShow;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getTaskDefId() {
        return taskDefId;
    }

    public void setTaskDefId(String taskDefId) {
        this.taskDefId = taskDefId;
    }

    public String getProcDefKey() {
        return procDefKey;
    }

    public void setProcDefKey(String procDefKey) {
        this.procDefKey = procDefKey;
    }

    public Map<String, Object> getTaskShow() {
        return taskShow;
    }

    public void setTaskShow(Map<String, Object> taskShow) {
        this.taskShow = taskShow;
    }
}
