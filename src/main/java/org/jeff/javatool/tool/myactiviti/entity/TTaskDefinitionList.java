package org.jeff.javatool.tool.myactiviti.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by weijianfu on 2017/2/28.
 */
public class TTaskDefinitionList implements Serializable {

    private String taskDefinitionKey;

    private String taskName;

    private List<String> roleIds;


    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }
}
