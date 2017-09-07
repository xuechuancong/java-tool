package org.jeff.javatool.tool.myactiviti.domain.entity;

import org.jeff.javatool.tool.myactiviti.entity.TCustomProcess;

import java.util.Date;

public class TProcCustom extends TProcCustomKey {
    private String procDefName;

    private String procDescription;

    private String procDefCategory;

    private String formId;

    private String formName;

    private String formDefKey;
    /** 包含tasks、flows信息 {@link TProcCustomJson}*/
    private String json;

    private String state;

    private Date createTime;

    private String creator;


    public static TProcCustom build(TCustomProcess tCustomProcess, String procJson) {
        if(tCustomProcess == null){
            return null;
        }
        TProcCustom procCustom = new TProcCustom();
        procCustom.setProcDefKey(tCustomProcess.getProcDefKey());
        procCustom.setProcDefName(tCustomProcess.getProcDefName());
        procCustom.setProcDefCategory(tCustomProcess.getProcDefCategory());
        procCustom.setFormId(tCustomProcess.getFormId());
        procCustom.setJson(procJson);
        procCustom.setFormName(tCustomProcess.getFormName());
        procCustom.setProcDescription(tCustomProcess.getProcDescription());
        procCustom.setFormDefKey(tCustomProcess.getFormDefKey());
        procCustom.setVersion(tCustomProcess.getVersion());

        return procCustom;
    }

    public String getProcDefName() {
        return procDefName;
    }

    public void setProcDefName(String procDefName) {
        this.procDefName = procDefName == null ? null : procDefName.trim();
    }

    public String getProcDefCategory() {
        return procDefCategory;
    }

    public void setProcDefCategory(String procDefCategory) {
        this.procDefCategory = procDefCategory == null ? null : procDefCategory.trim();
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId == null ? null : formId.trim();
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json == null ? null : json.trim();
    }

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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName == null ? null : formName.trim();
    }

    public String getProcDescription() {
        return procDescription;
    }

    public void setProcDescription(String procDescription) {
        this.procDescription = procDescription;
    }

    public String getFormDefKey() {
        return formDefKey;
    }

    public void setFormDefKey(String formDefKey) {
        this.formDefKey = formDefKey;
    }
}