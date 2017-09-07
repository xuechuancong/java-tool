package org.jeff.javatool.tool.myactiviti.entity;

import java.io.Serializable;

/**
 * Created by weijianfu on 2016/11/30.
 */
public class NextTaskInfo implements Serializable {
    /**
     * 任务定义ID，查询任务详情中包含
     */
    private String taskDefinitionKey;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 任务类型 {@link org.jeff.javatool.tool.myactiviti.config.TaskCategory}
     */
    private String taskCategory;
    /**
     * 操作类型值 {@link org.jeff.javatool.tool.myactiviti.config.TaskOperation}
     */
    private String operation;

    /**
     * 是否是异常结束流程，默认正常
     */
    private boolean abnormalProc;


    public NextTaskInfo() {
        this.abnormalProc = false;
    }

    public NextTaskInfo(String taskDefinitionKey, String userId, String taskCategory, String operation) {
        this.taskDefinitionKey = taskDefinitionKey;
        this.userId = userId;
        this.taskCategory = taskCategory;
        this.operation = operation;
        this.abnormalProc = false;
    }


    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public boolean isAbnormalProc() {
        return abnormalProc;
    }

    public void setAbnormalProc(boolean abnormalProc) {
        this.abnormalProc = abnormalProc;
    }
}
