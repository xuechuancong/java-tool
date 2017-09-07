package org.jeff.javatool.tool.myactiviti.entity;

import java.io.Serializable;

/**
 * 自定义流程复制参数
 * Created by weijianfu on 2017/4/26.
 */
public class TCustomProcessCopy implements Serializable {

    /** 流程定义Key，not null */
    private String procDefKey;
    /** 版本号，not null */
    private String version;

    public String getProcDefKey() {
        return procDefKey;
    }

    public void setProcDefKey(String procDefKey) {
        this.procDefKey = procDefKey;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
