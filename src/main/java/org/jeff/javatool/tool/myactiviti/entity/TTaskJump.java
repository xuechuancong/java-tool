package org.jeff.javatool.tool.myactiviti.entity;

import java.io.Serializable;

/**
 * 流程跳转需要的任务定义信息
 * Created by weijianfu on 2017/8/22.
 */
public class TTaskJump implements Serializable {

    /**
     * 任务定义Key
     */
    private String defKey;
    /**
     * 任务定义名称
     */
    private String defName;
    /**
     * 任务定义类型
     */
    private String defCategory;

    public String getDefKey() {
        return defKey;
    }

    public void setDefKey(String defKey) {
        this.defKey = defKey;
    }

    public String getDefName() {
        return defName;
    }

    public void setDefName(String defName) {
        this.defName = defName;
    }

    public String getDefCategory() {
        return defCategory;
    }

    public void setDefCategory(String defCategory) {
        this.defCategory = defCategory;
    }
}
