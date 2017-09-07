package org.jeff.javatool.tool.myactiviti.entity.query;

import java.io.Serializable;
import java.util.Date;

/**
 * 历史实例列表查询条件，变量为null则不加入筛选条件，可组合查询
 * Created by weijianfu on 2016/12/2.
 */
public class QueryHistoricInstanceCondition implements Serializable {
    /** 流程定义Key */
    private String processDefinitionKey;
    /** 涉及的UserId */
    private String involvedUser;
    /** 涉及的UserId，不包括正在执行的任务 */
    private String involvedUserExceptDoing;
    /** 流程是否结束 */
    private Boolean isProcFinished;
    /** 流程类型 */
    private String procCategory;
    /** 流程实例名称模糊 */
    private String procDefNameLike;
    /** 流程实例ID */
    private String procInstanceId;
    /** 任务标题模糊 */
    private String taskTitleLike;
    /** 申请时间最小值 */
    private Date applyMinTime;
    /** 申请时间最大值 */
    private Date applyMaxTime;
    /** 申请人ID */
    private String applyAssigneeId;
    /** 申请人名称模糊 */
    private String applyAssigneeLike;
    /** 申请人部门名称模糊 */
    private String applyAssigneeDepartmentLike;
    /** 是否是已删除 */
    private Boolean deleted;

    /**
     * 高级搜索，包含流程类型、流程名称模糊、流程ID、任务标题模糊、申请人名称模糊、申请人部门名称模糊的或条件搜索
     */
    private String advancedSearch;


    /**
     * 排序关键字类型{@link org.jeff.javatool.tool.myactiviti.config.QueryHistoricInstanceOrderKeyType}
     */
    private String orderKeyType;
    /**
     * 排序类型{@link org.jeff.javatool.tool.myactiviti.config.QueryOrderType}
     * 必须设置orderKeyType，方可设置orderType
     */
    private String orderType;

    public QueryHistoricInstanceCondition() {

    }

    public String getProcCategory() {
        return procCategory;
    }

    public void setProcCategory(String procCategory) {
        this.procCategory = procCategory;
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


    public String getAdvancedSearch() {
        return advancedSearch;
    }

    public void setAdvancedSearch(String advancedSearch) {
        this.advancedSearch = advancedSearch;
    }


    public String getProcDefNameLike() {
        return procDefNameLike;
    }

    public void setProcDefNameLike(String procDefNameLike) {
        if(procDefNameLike != null && procDefNameLike.length() > 0){
            this.procDefNameLike = "%" + procDefNameLike + "%";
        }
    }

    public String getProcInstanceId() {
        return procInstanceId;
    }

    public void setProcInstanceId(String procInstanceId) {
        this.procInstanceId = procInstanceId;
    }

    public String getTaskTitleLike() {
        return taskTitleLike;
    }

    public void setTaskTitleLike(String taskTitleLike) {
        if(taskTitleLike != null && taskTitleLike.length() > 0){
            this.taskTitleLike = "%" + taskTitleLike + "%";
        }
    }

    public String getApplyAssigneeLike() {
        return applyAssigneeLike;
    }

    public void setApplyAssigneeLike(String applyAssigneeLike) {
        if(applyAssigneeLike != null && applyAssigneeLike.length() > 0){
            this.applyAssigneeLike = "%" + applyAssigneeLike + "%";
        }
    }

    public Date getApplyMinTime() {
        return applyMinTime;
    }

    public void setApplyMinTime(Date applyMinTime) {
        this.applyMinTime = applyMinTime;
    }

    public Date getApplyMaxTime() {
        return applyMaxTime;
    }

    public void setApplyMaxTime(Date applyMaxTime) {
        this.applyMaxTime = applyMaxTime;
    }

    public String getApplyAssigneeDepartmentLike() {
        return applyAssigneeDepartmentLike;
    }

    public void setApplyAssigneeDepartmentLike(String applyAssigneeDepartmentLike) {
        if(applyAssigneeDepartmentLike != null && applyAssigneeDepartmentLike.length() > 0){
            this.applyAssigneeDepartmentLike = "%" + applyAssigneeDepartmentLike + "%";
        }
    }

    public Boolean getIsProcFinished() {
        return isProcFinished;
    }

    public void setIsProcFinished(Boolean isProcFinished) {
        this.isProcFinished = isProcFinished;
    }

    public String getApplyAssigneeId() {
        return applyAssigneeId;
    }

    public void setApplyAssigneeId(String applyAssigneeId) {
        this.applyAssigneeId = applyAssigneeId;
    }

    public String getInvolvedUser() {
        return involvedUser;
    }

    public void setInvolvedUser(String involvedUser) {
        this.involvedUser = involvedUser;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getInvolvedUserExceptDoing() {
        return involvedUserExceptDoing;
    }

    public void setInvolvedUserExceptDoing(String involvedUserExceptDoing) {
        if(involvedUserExceptDoing != null && involvedUserExceptDoing.length() > 0){
            this.involvedUserExceptDoing = "%" + involvedUserExceptDoing + "%";
        }
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
