package org.jeff.javatool.tool.myactiviti.entity;

import java.io.Serializable;

/**
 * 【自定义】任务流转主体
 * Created by weijianfu on 2017/2/28.
 */
public class TCustomFlow implements Serializable {

    /** 当前任务定义KEY，not null */
    private String curTaskDefKey;
    /** 目标任务定义KEY，not null */
    private String tarTaskDefKey;
    /** 流向名称，对应OperationName */
    private String flowName;
    /** 流向类型，对应Operation */
    private String flowType;

    /** 是否发短信，not null **/
    private Boolean canSendMsg;
    /** 是否发系统消息提醒，not null **/
    private Boolean canSendOSMsg;
    /** 是否自动执行，not null **/
    private Boolean canAuto;
    /** 自动执行时间，单位为毫秒 **/
    private Long autoTime;

    /** 是否是异常结束流程，默认正常。为null、false均为正常结束，为true非正常结束 */
    private Boolean abnormalProc;
    /** 是否被标红，是的话将其本身、起始、结束节点全部标红 */
    private Boolean mark;


    /**
     * 条件表达式
     *
     *      a.expression值可以为null，表示无条件流转
     *      b.expression中的内容，可使用的符号为==、>=、<=、&&、||、(、)等java运算符
     *
     *      例如：
     *          ${pass=='1' && money>=1000.0}    变量pass等于'1'且变量money大于等于1000.0
     *          ${pass=='1' && money>=1000.0}    变量pass等于'1'或变量money大于等于1000.0
     *
     */
    private String expression;

    public static String getFlowKey(String curTaskDefKey, String tarTaskDefKey, String operation){
        return curTaskDefKey + "_" + tarTaskDefKey + "_" + (operation == null ? "" : operation);
    }

    public String getCurTaskDefKey() {
        return curTaskDefKey;
    }

    public void setCurTaskDefKey(String curTaskDefKey) {
        this.curTaskDefKey = curTaskDefKey;
    }

    public String getTarTaskDefKey() {
        return tarTaskDefKey;
    }

    public void setTarTaskDefKey(String tarTaskDefKey) {
        this.tarTaskDefKey = tarTaskDefKey;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public Boolean getCanSendMsg() {
        return canSendMsg;
    }

    public void setCanSendMsg(Boolean canSendMsg) {
        this.canSendMsg = canSendMsg;
    }

    public Boolean getCanSendOSMsg() {
        return canSendOSMsg;
    }

    public void setCanSendOSMsg(Boolean canSendOSMsg) {
        this.canSendOSMsg = canSendOSMsg;
    }

    public Boolean getCanAuto() {
        return canAuto;
    }

    public void setCanAuto(Boolean canAuto) {
        this.canAuto = canAuto;
    }

    public Long getAutoTime() {
        return autoTime;
    }

    public void setAutoTime(Long autoTime) {
        this.autoTime = autoTime;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Boolean getAbnormalProc() {
        if(this.abnormalProc == null){
            return false;
        }
        return abnormalProc;
    }

    public void setAbnormalProc(Boolean abnormalProc) {
        this.abnormalProc = abnormalProc;
    }

    public Boolean getMark() {
        return mark;
    }

    public void setMark(Boolean mark) {
        this.mark = mark;
    }
}
