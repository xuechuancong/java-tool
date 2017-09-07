package org.jeff.javatool.tool.myactiviti.domain.vo;

import java.io.Serializable;

/**
 * Created by weijianfu on 2017/8/22.
 */
public class QueryProcTaskDefListVO implements Serializable {

    /**
     * 流程定义ID
     */
    private String procDefId;

    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }
}
