package org.jeff.javatool.tool.myactiviti.entity;

import org.jeff.javatool.tool.myactiviti.utils.StringUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 删除表单实例与附件的关系
 * Created by weijianfu on 2017/4/27.
 */
public class TProcAttDelete implements Serializable {

    private Long formInstId;// 表单实例Id

    private String attIds;//  附件ID集合，以[,]分割，例如11,22,33

    public Long getFormInstId() {
        return formInstId;
    }

    public void setFormInstId(Long formInstId) {
        this.formInstId = formInstId;
    }

    public String getAttIds() {
        return attIds;
    }

    public void setAttIds(String attIds) {
        this.attIds = attIds;
    }

    public List<String> getAttIdList() {
        return StringUtil.split(this.attIds, ",");
    }
}
