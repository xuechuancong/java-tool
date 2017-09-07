package org.jeff.javatool.tool.myactiviti.entity.vo;

import java.util.List;
import java.util.Map;

/**
 * 完成流程环节需要的信息
 */
public class CompleteInfo {
	private List<AssigneeInfo> assList;//下一步处理人信息
	private String userId;//提交人
	private String userName;//提交人姓名
	private String orgId;//提交人所在组织
	private String processInst;//流程实例id
	private String taskDefId;//环节定义id
	private String operContan;//操作备注
	private String operType;//本环节的操作类型
	private String needSendSms;//本环节的操作类型
	private String taskId;//本次任务实例id
	private Map<String, Object> variables;//传给流程引擎的自定义变量
	
	public List<AssigneeInfo> getAssList() {
		return assList;
	}

	public void setAssList(List<AssigneeInfo> assList) {
		this.assList = assList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getProcessInst() {
		return processInst;
	}

	public void setProcessInst(String processInst) {
		this.processInst = processInst;
	}

	public String getOperContan() {
		return operContan;
	}

	public void setOperContan(String operContan) {
		this.operContan = operContan;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public String getTaskDefId() {
		return taskDefId;
	}

	public void setTaskDefId(String taskDefId) {
		this.taskDefId = taskDefId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getNeedSendSms() {
		return needSendSms;
	}

	public void setNeedSendSms(String needSendSms) {
		this.needSendSms = needSendSms;
	}

}
