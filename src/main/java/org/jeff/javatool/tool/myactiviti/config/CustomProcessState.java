package org.jeff.javatool.tool.myactiviti.config;

/**
 * 自定义流程状态
 * Created by weijianfu on 2017/3/27.
 */
public enum CustomProcessState {

    INVALID("0"),//停用
    EFFECTIVE("1"),//启用
    DELETED("2");//删除

    private String state;

    CustomProcessState(String state){
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
