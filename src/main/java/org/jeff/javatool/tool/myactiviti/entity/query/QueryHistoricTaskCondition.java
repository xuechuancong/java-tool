package org.jeff.javatool.tool.myactiviti.entity.query;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 历史任务列表查询条件，变量为null则不加入筛选条件，可组合查询
 * Created by weijianfu on 2016/12/2.
 */
public class QueryHistoricTaskCondition implements Serializable {

    /** 任务ID */
    private String taskId;
    /** 执行者的userId */
    private String assignee;


    /** 流程是否结束 */
    private Boolean isProcFinished;
    /** 流程类型集合 */
    private List<String> procCategoryList;
    /** 流程名称模糊 */
    private String procNameLike;
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
    /**
     * 高级搜索，包含流程类型、流程名称模糊、流程ID、任务标题模糊、申请人名称模糊、申请人部门名称模糊的或条件搜索
     */
    private String advancedSearch;


    /**
     * 排序关键字类型{@link org.jeff.javatool.tool.myactiviti.config.QueryHistoricTaskOrderKeyType}
     */
    private String orderKeyType;
    /**
     * 排序类型{@link org.jeff.javatool.tool.myactiviti.config.QueryOrderType}
     * 必须设置orderKeyType，方可设置orderType
     */
    private String orderType;

    public QueryHistoricTaskCondition() {

    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getAdvancedSearch() {
        return advancedSearch;
    }

    public void setAdvancedSearch(String advancedSearch) {
        this.advancedSearch = advancedSearch;
    }

    public List<String> getProcCategoryList() {
        return procCategoryList;
    }

    public void setProcCategoryList(List<String> procCategoryList) {
        if(this.procCategoryList != null && this.procCategoryList.size() > 0){
            this.procCategoryList = procCategoryList;
        }
    }

    public String getProcNameLike() {
        return procNameLike;
    }

    public void setProcNameLike(String procNameLike) {
        if(procNameLike != null && procNameLike.length() > 0){
            this.procNameLike = "%" + procNameLike + "%";
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
}
