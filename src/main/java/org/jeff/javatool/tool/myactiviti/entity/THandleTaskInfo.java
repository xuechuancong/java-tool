package org.jeff.javatool.tool.myactiviti.entity;

import java.io.Serializable;

/**
 * 待处理任务信息，包括：加签、委托
 * Created by weijianfu on 2016/12/8.
 */
public class THandleTaskInfo implements Serializable {

    /** 加签任务的用户ID */
    private String userId;
    /** 加签任务的名称 */
    private String taskName;

    public THandleTaskInfo(){

    }

    public THandleTaskInfo(String userId, String taskName){
        this.userId = userId;
        this.taskName = taskName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
