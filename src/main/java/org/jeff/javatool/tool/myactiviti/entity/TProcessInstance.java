package org.jeff.javatool.tool.myactiviti.entity;

import org.activiti.engine.impl.persistence.entity.ExecutionEntity;

import java.io.Serializable;

/**
 * 流程实例信息传输对象
 * Created by weijianfu on 2016/11/30.
 */
public class TProcessInstance implements Serializable {

    private ExecutionEntity executionEntity;

    public TProcessInstance() {

    }

    public TProcessInstance(ExecutionEntity executionEntity) {
        this.executionEntity = executionEntity;
    }

    public ExecutionEntity getExecutionEntity() {
        return executionEntity;
    }

    public void setExecutionEntity(ExecutionEntity executionEntity) {
        this.executionEntity = executionEntity;
    }
}
