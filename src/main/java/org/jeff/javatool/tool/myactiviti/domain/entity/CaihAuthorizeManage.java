package org.jeff.javatool.tool.myactiviti.domain.entity;

import java.util.Date;

public class CaihAuthorizeManage {
    private Long id;

    private String authStaffId;

    private String authStaffName;

    private String authedStaffId;

    private String authedStaffName;

    private String processType;

    private String processDefKey;

    private String processName;

    private String taskDefId;

    private String taskName;

    private Date authStartTime;

    private Date authEndTime;

    private String status;

    private Date createTime;

    private Date modifyTime;

    private String spareField1;

    private String spareField2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthStaffId() {
        return authStaffId;
    }

    public void setAuthStaffId(String authStaffId) {
        this.authStaffId = authStaffId == null ? null : authStaffId.trim();
    }

    public String getAuthStaffName() {
        return authStaffName;
    }

    public void setAuthStaffName(String authStaffName) {
        this.authStaffName = authStaffName == null ? null : authStaffName.trim();
    }

    public String getAuthedStaffId() {
        return authedStaffId;
    }

    public void setAuthedStaffId(String authedStaffId) {
        this.authedStaffId = authedStaffId == null ? null : authedStaffId.trim();
    }

    public String getAuthedStaffName() {
        return authedStaffName;
    }

    public void setAuthedStaffName(String authedStaffName) {
        this.authedStaffName = authedStaffName == null ? null : authedStaffName.trim();
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType == null ? null : processType.trim();
    }

    public String getProcessDefKey() {
        return processDefKey;
    }

    public void setProcessDefKey(String processDefKey) {
        this.processDefKey = processDefKey == null ? null : processDefKey.trim();
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName == null ? null : processName.trim();
    }

    public String getTaskDefId() {
        return taskDefId;
    }

    public void setTaskDefId(String taskDefId) {
        this.taskDefId = taskDefId == null ? null : taskDefId.trim();
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public Date getAuthStartTime() {
        return authStartTime;
    }

    public void setAuthStartTime(Date authStartTime) {
        this.authStartTime = authStartTime;
    }

    public Date getAuthEndTime() {
        return authEndTime;
    }

    public void setAuthEndTime(Date authEndTime) {
        this.authEndTime = authEndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getSpareField1() {
        return spareField1;
    }

    public void setSpareField1(String spareField1) {
        this.spareField1 = spareField1 == null ? null : spareField1.trim();
    }

    public String getSpareField2() {
        return spareField2;
    }

    public void setSpareField2(String spareField2) {
        this.spareField2 = spareField2 == null ? null : spareField2.trim();
    }
}