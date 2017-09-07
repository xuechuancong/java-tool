package org.jeff.javatool.tool.myactiviti.entity;

import java.io.Serializable;

/**
 * 自定义流程状态修改参数
 * Created by weijianfu on 2017/3/27.
 */
public class TCustomProcessState implements Serializable {

    /** 流程定义Key，not null */
    private String procDefKey;
    /** 自定义流程状态，not null
     *    常量请查阅{@link org.jeff.javatool.tool.myactiviti.config.CustomProcessState}
     */
    private String state;
    /** 版本号，not null */
    private Integer version;
    /** 操作人名称 */
    private String operUserName;


    public String getProcDefKey() {
        return procDefKey;
    }

    public void setProcDefKey(String procDefKey) {
        this.procDefKey = procDefKey;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
    }
}
