package org.jeff.javatool.tool.myactiviti.entity;

import com.google.common.collect.Lists;
import org.jeff.javatool.tool.myactiviti.config.WFConfig;
import org.jeff.javatool.tool.myactiviti.utils.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 下一任务的指派信息传输对象
 * Created by weijianfu on 2016/11/30.
 */
public class TNextTaskInfo implements Serializable {

    /**
     * 任务定义ID，查询任务详情中包含
     */
    private String taskDefinitionKey;
    /**
     * 任务名称，查询任务详情中包含
     */
    private String taskName;
    /**
     * 角色ID集合
     */
    private Collection<String> roleIdList;
    /**
     * 组织ID集合
     */
    private Collection<String> organizationIdList;
    /**
     * 任务类型 {@link org.jeff.javatool.tool.myactiviti.config.TaskCategory}
     */
    private String taskCategory;
    /**
     * 操作类型值，使用于{@link org.jeff.javatool.tool.myactiviti.intfc.IWFTaskProvider#completeTask}
     */
    private String operation;
    /**
     * 操作名称
     */
    private String operationName;
    /**
     * 是否发短信
     */
    private boolean canSendMsg;
    /**
     * 是否发系统消息提醒
     */
    private boolean canSendOSMsg;

    /**
     * 是否是异常结束流程，默认正常。为null、false均为正常结束，为true非正常结束
     */
    private boolean abnormalProc;

    public String getSimpleValue() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getTaskDefinitionKey()).append("@@@")
                .append(this.getTaskName()).append("@@@")
                .append(StringUtil.concat(this.getRoleIdList(), ",")).append("@@@")
                .append(StringUtil.concat(this.getOrganizationIdList(), ",")).append("@@@")
                .append(this.getOperation()).append("@@@")
                .append(this.getOperationName()).append("@@@")
                .append(this.isCanSendMsg() ? WFConfig.TRUE_STRING : WFConfig.FALSE_STRING).append("@@@")
                .append(this.isCanSendOSMsg() ? WFConfig.TRUE_STRING : WFConfig.FALSE_STRING).append("@@@")
                .append(this.getTaskCategory()).append("@@@")
                .append(this.getAbnormalProc() ? WFConfig.TRUE_STRING : WFConfig.FALSE_STRING);
        return sb.toString();
    }

    public static TNextTaskInfo parse(String simpleValue) {
        ArrayList<String> strings = Lists.newArrayList(simpleValue.split("@@@"));
        TNextTaskInfo tNextTaskInfo = new TNextTaskInfo();

        if (strings == null || strings.size() <= 0) {
            return tNextTaskInfo;
        }

        if (strings.size() == 8) {//旧逻辑
            return new TNextTaskInfo(strings.get(0), strings.get(1), strings.get(2), strings.get(3), strings.get(4),
                    WFConfig.TRUE_STRING.equals(strings.get(5)),
                    WFConfig.TRUE_STRING.equals(strings.get(6)), strings.get(7));
        } else if (strings.size() == 10) {
            tNextTaskInfo.setTaskDefinitionKey(strings.get(0));
            tNextTaskInfo.setTaskName(strings.get(1));
            tNextTaskInfo.setRoleIdList(StringUtil.split(strings.get(2), ","));
            tNextTaskInfo.setOrganizationIdList(StringUtil.split(strings.get(3), ","));
            tNextTaskInfo.setOperation(strings.get(4));
            tNextTaskInfo.setOperationName(strings.get(5));
            tNextTaskInfo.setCanSendMsg(WFConfig.TRUE_STRING.equals(strings.get(6)));
            tNextTaskInfo.setCanSendOSMsg(WFConfig.TRUE_STRING.equals(strings.get(7)));
            tNextTaskInfo.setTaskCategory(strings.get(8));
            tNextTaskInfo.setAbnormalProc(WFConfig.TRUE_STRING.equals(strings.get(9)));
        }


        return tNextTaskInfo;
    }

    public TNextTaskInfo() {

    }

    //兼容旧数据用
    public TNextTaskInfo(String taskDefinitionKey, String taskName, String roleId, String operation, String operationName, boolean canSendMsg, boolean canSendOSMsg, String taskCategory) {
        this.taskDefinitionKey = taskDefinitionKey;
        this.taskName = taskName;
        this.roleIdList = Lists.newArrayList(roleId);
        this.organizationIdList = Lists.newArrayList();
        this.operation = operation;
        this.operationName = operationName;
        this.canSendMsg = canSendMsg;
        this.canSendOSMsg = canSendOSMsg;
        this.taskCategory = taskCategory;
        this.abnormalProc = false;
    }

    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public boolean isCanSendMsg() {
        return canSendMsg;
    }

    public void setCanSendMsg(boolean canSendMsg) {
        this.canSendMsg = canSendMsg;
    }

    public boolean isCanSendOSMsg() {
        return canSendOSMsg;
    }

    public void setCanSendOSMsg(boolean canSendOSMsg) {
        this.canSendOSMsg = canSendOSMsg;
    }

    public String getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    public Collection<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(Collection<String> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public Collection<String> getOrganizationIdList() {
        return organizationIdList;
    }

    public void setOrganizationIdList(Collection<String> organizationIdList) {
        this.organizationIdList = organizationIdList;
    }

    public boolean getAbnormalProc() {
        return abnormalProc;
    }

    public void setAbnormalProc(boolean abnormalProc) {
        this.abnormalProc = abnormalProc;
    }
}
