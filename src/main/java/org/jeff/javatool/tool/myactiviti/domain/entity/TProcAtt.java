package org.jeff.javatool.tool.myactiviti.domain.entity;

import java.util.Date;

public class TProcAtt extends TProcAttKey {
    private String state;

    private Date createTime;

    private Date updateTime;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}