package org.jeff.javatool.tool.myactiviti.config;

/**
 * 流程常量定义类型
 * Created by weijianfu on 2017/5/2.
 */
public enum ProcConDefType {

    PROC_DEF_CATEGORY("procDefCategory"),//流程定义类型，可新增、可修改、条件删除
    TASK_DEF_CATEGORY("taskDefCategory"),//任务定义类型，不可新增、不可修改、不可删除
    FLOW_TYPE("flowType");//流向类型，可新增、可修改(新增项)、可删除(新增项)

    private String type;

    ProcConDefType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
