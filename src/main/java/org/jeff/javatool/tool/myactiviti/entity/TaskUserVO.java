package org.jeff.javatool.tool.myactiviti.entity;

import java.io.Serializable;

public class TaskUserVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1385456259434943568L;

	private String taskId;// 环节实例id
	private String taskDefId;// 环节定义id
	private String taskName;// 环节名称
	private String userId;// 环节处理人ID
	private String userName;// 环节处理人
	private String processId;// 流程实例id
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskDefId() {
		return taskDefId;
	}
	public void setTaskDefId(String taskDefId) {
		this.taskDefId = taskDefId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	
}
