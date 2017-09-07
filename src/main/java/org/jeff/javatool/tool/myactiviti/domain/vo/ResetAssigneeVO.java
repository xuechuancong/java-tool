package org.jeff.javatool.tool.myactiviti.domain.vo;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by weijianfu on 2017/5/31.
 */
public class ResetAssigneeVO implements Serializable {

    private String processId;
    private String taskId;
    private String resetUserId;
    private Map<String, Object> taskShow;


    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getResetUserId() {
        return resetUserId;
    }

    public void setResetUserId(String resetUserId) {
        this.resetUserId = resetUserId;
    }

    public Map<String, Object> getTaskShow() {
        return taskShow;
    }

    public void setTaskShow(Map<String, Object> taskShow) {
        this.taskShow = taskShow;
    }
}
