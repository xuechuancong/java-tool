package org.jeff.javatool.tool.myactiviti.entity.query;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询流程定义条件，变量为null则不加入筛选条件，可组合查询
 * Created by weijianfu on 2016/12/2.
 */
public class QueryProcessDefinitionCondition implements Serializable {

    /** 流程定义名称，模糊查询 */
    private String processDefinitionNameLike;
    /** 流程定义Key */
    private String processDefinitionKey;
    /** 是否只查询出最新一个版本 */
    private Boolean isLatestVersion;
    /** 流程类型 */
    private String processDefinitionCategory;
    /** 更新时间最小值 */
    private Date updateMinTime;
    /** 更新时间最大值 */
    private Date updateMaxTime;
    /**
    * 高级搜索，流程类型、流程定义名称模糊
    */
    private String advancedSearch;





    /**
     * 排序关键字类型{@link org.jeff.javatool.tool.myactiviti.config.QueryProcessDefinitionOrderKeyType}
     */
    private String orderKeyType;
    /**
     * 排序类型{@link org.jeff.javatool.tool.myactiviti.config.QueryOrderType}
     * 必须设置orderKeyType，方可设置orderType
     */
    private String orderType;

    public QueryProcessDefinitionCondition() {

    }

    public String getProcessDefinitionNameLike() {
        return processDefinitionNameLike;
    }

    public void setProcessDefinitionNameLike(String processDefinitionNameLike) {
        if (processDefinitionNameLike != null && processDefinitionNameLike.length() > 0) {
            this.processDefinitionNameLike = "%" + processDefinitionNameLike + "%";
        }
    }

    public Boolean getIsLatestVersion() {
        return isLatestVersion;
    }

    public void setIsLatestVersion(Boolean isLatestVersion) {
        this.isLatestVersion = isLatestVersion;
    }

    public String getOrderKeyType() {
        return orderKeyType;
    }

    public void setOrderKeyType(String orderKeyType) {
        this.orderKeyType = orderKeyType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getProcessDefinitionCategory() {
        return processDefinitionCategory;
    }

    public void setProcessDefinitionCategory(String processDefinitionCategory) {
        this.processDefinitionCategory = processDefinitionCategory;
    }

    public Date getUpdateMinTime() {
        return updateMinTime;
    }

    public void setUpdateMinTime(Date updateMinTime) {
        this.updateMinTime = updateMinTime;
    }

    public Date getUpdateMaxTime() {
        return updateMaxTime;
    }

    public void setUpdateMaxTime(Date updateMaxTime) {
        this.updateMaxTime = updateMaxTime;
    }

    public String getAdvancedSearch() {
        return advancedSearch;
    }

    public void setAdvancedSearch(String advancedSearch) {
        this.advancedSearch = advancedSearch;
    }
}
