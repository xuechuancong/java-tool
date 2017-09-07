package org.jeff.javatool.tool.myactiviti.domain.entity;

public class TProcCustomKey {
    private String procDefKey;

    private Integer version;

    public String getProcDefKey() {
        return procDefKey;
    }

    public void setProcDefKey(String procDefKey) {
        this.procDefKey = procDefKey == null ? null : procDefKey.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version == null ? null : version;
    }
}