package org.jeff.javatool.tool.myactiviti.entity;

import java.io.Serializable;

/**
 * Created by weijianfu on 2017/5/16.
 */
public class TProcDefListCondition implements Serializable {

    /** 流程定义Key */
    private String processDefinitionKey;
    /** 流程类型 */
    private String processDefinitionCategory;
    /** 分页每页记录数量 */
    private Integer limit;
    /** 分页下标，起始为0 */
    private Integer offset;

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getProcessDefinitionCategory() {
        return processDefinitionCategory;
    }

    public void setProcessDefinitionCategory(String processDefinitionCategory) {
        this.processDefinitionCategory = processDefinitionCategory;
    }
}
