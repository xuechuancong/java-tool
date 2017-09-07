package org.jeff.javatool.tool.myactiviti.entity;

import org.activiti.engine.impl.persistence.entity.SuspensionState;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 待办前端查询条件
 * Created by weijianfu on 2017/5/16.
 */
public class TProcWaitDealCondition implements Serializable {

    /** 任务ID */
    private String taskId;
    /** 办理人userId */
    private String assignee;
    /** 流程类型集合 */
    private List<String> processCategoryList;
    /** 流程实例ID */
    private String processInstanceId;
    /** 流程定义KEY */
    private String processDefinitionKey;
    /** 任务定义KEY */
    private String taskDefinitionKey;
    /** 流程挂起状态，默认为不挂起
     * 不挂起 : {@link org.activiti.engine.impl.persistence.entity.SuspensionState#ACTIVE}
     * 挂起 : {@link org.activiti.engine.impl.persistence.entity.SuspensionState#SUSPENDED}
     * */
    private SuspensionState suspensionState;


    /** 流程定义名称 */
    private String procDefNameLike;
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
     * 高级搜索，包含流程类型、流程定义名称、流程ID、任务标题模糊、申请人名称模糊、申请人部门名称模糊的或条件搜索
     */
    private String advancedSearch;



    /**
     * 排序关键字类型{@link org.jeff.javatool.tool.myactiviti.config.QueryTaskOrderKeyType}
     */
    private String orderKeyType;
    /**
     * 排序类型{@link org.jeff.javatool.tool.myactiviti.config.QueryOrderType}
     * 必须设置orderKeyType，方可设置orderType
     */
    private String orderType;

    private Integer limit;

    private Integer offset;

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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public List<String> getProcessCategoryList() {
        return processCategoryList;
    }

    public void setProcessCategoryList(List<String> processCategoryList) {
        this.processCategoryList = processCategoryList;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public SuspensionState getSuspensionState() {
        return suspensionState;
    }

    public void setSuspensionState(SuspensionState suspensionState) {
        this.suspensionState = suspensionState;
    }

    public String getProcDefNameLike() {
        return procDefNameLike;
    }

    public void setProcDefNameLike(String procDefNameLike) {
        this.procDefNameLike = procDefNameLike;
    }

    public String getTaskTitleLike() {
        return taskTitleLike;
    }

    public void setTaskTitleLike(String taskTitleLike) {
        this.taskTitleLike = taskTitleLike;
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

    public String getApplyAssigneeId() {
        return applyAssigneeId;
    }

    public void setApplyAssigneeId(String applyAssigneeId) {
        this.applyAssigneeId = applyAssigneeId;
    }

    public String getApplyAssigneeLike() {
        return applyAssigneeLike;
    }

    public void setApplyAssigneeLike(String applyAssigneeLike) {
        this.applyAssigneeLike = applyAssigneeLike;
    }

    public String getApplyAssigneeDepartmentLike() {
        return applyAssigneeDepartmentLike;
    }

    public void setApplyAssigneeDepartmentLike(String applyAssigneeDepartmentLike) {
        this.applyAssigneeDepartmentLike = applyAssigneeDepartmentLike;
    }

    public String getAdvancedSearch() {
        return advancedSearch;
    }

    public void setAdvancedSearch(String advancedSearch) {
        this.advancedSearch = advancedSearch;
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
}
