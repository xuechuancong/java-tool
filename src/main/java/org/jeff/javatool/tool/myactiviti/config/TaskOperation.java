package org.jeff.javatool.tool.myactiviti.config;

/**
 * 任务操作类型
 * Created by weijianfu on 2016/12/14.
 */
public enum TaskOperation {

    AGREE1("0"),//同意1，用于同一步骤多种不同的同意操作
    AGREE("1"),//同意
    DISAGREE("2"),//不同意
    RETURN("3"),//退回
    COUNTER_SIGN("4"),//会签
    COUNTER_SIGN1("5"),//会签1，用于同一步骤多种不同的会签操作
    TRANSMIT_SHOU_WEN("6");//自动转收文


    private String operation;

    TaskOperation(String operation){
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
