package org.jeff.javatool.tool.myactiviti.entity.query;

import java.io.Serializable;

/**
 * 自定义流程组合查询条件
 * Created by weijianfu on 2017/3/27.
 */
public class QueryCustomProcessCondition implements Serializable {

    /**
     * 流程定义KEY
     */
    private String procDefKey;
    /**
     * 版本号
     */
    private String version;
    /**
     * 流程定义类型
     */
    private String procDefCategory;
    /**
     * 自定义流程状态，常量请查阅{@link org.jeff.javatool.tool.myactiviti.config.CustomProcessState}
     */
    private String state;
    /**
     * 流程定义名称模糊
     */
    private String procDefNameLike;

    /**
     * 分页-起始下标
     */
    private Integer offset;
    /**
     * 分页-每页数量
     */
    private Integer limit;

    public String getProcDefKey() {
        return procDefKey;
    }

    public void setProcDefKey(String procDefKey) {
        this.procDefKey = procDefKey;
    }

    public String getProcDefCategory() {
        return procDefCategory;
    }

    public void setProcDefCategory(String procDefCategory) {
        this.procDefCategory = procDefCategory;
    }

    public String getProcDefNameLike() {
        if (procDefNameLike != null && procDefNameLike.length() > 0) {
            return "%" + procDefNameLike + "%";
        }
        return procDefNameLike;
    }

    public void setProcDefNameLike(String procDefNameLike) {
        this.procDefNameLike = procDefNameLike;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
