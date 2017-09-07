package org.jeff.javatool.tool.myactiviti.domain.entity;

public class TProcAttKey {
    private Long formInstId;

    private String attId;

    public Long getFormInstId() {
        return formInstId;
    }

    public void setFormInstId(Long formInstId) {
        this.formInstId = formInstId;
    }

    public String getAttId() {
        return attId;
    }

    public void setAttId(String attId) {
        this.attId = attId == null ? null : attId.trim();
    }
}