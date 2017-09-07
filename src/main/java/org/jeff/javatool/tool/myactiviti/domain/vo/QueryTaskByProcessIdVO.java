package org.jeff.javatool.tool.myactiviti.domain.vo;

import java.io.Serializable;

/**
 * Created by weijianfu on 2017/8/21.
 */
public class QueryTaskByProcessIdVO implements Serializable {

    private String processId;

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }
}
