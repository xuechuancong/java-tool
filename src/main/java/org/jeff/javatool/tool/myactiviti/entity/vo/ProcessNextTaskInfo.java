package org.jeff.javatool.tool.myactiviti.entity.vo;


import java.io.Serializable;

public class ProcessNextTaskInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2062491807760111814L;
	private String operation;
	private String operationName;
	private String taskDefKey;
	private String taskName;
	private Boolean isCanSendMsg;
	private Boolean isCanSendOSMsg;
	private String nextUserId;
	private String nextUserName;
	private String taskCategory;
	private String isEnd;

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

	public String getTaskDefKey() {
		return taskDefKey;
	}

	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Boolean getIsCanSendMsg() {
		return isCanSendMsg;
	}

	public void setIsCanSendMsg(Boolean isCanSendMsg) {
		this.isCanSendMsg = isCanSendMsg;
	}

	public Boolean getIsCanSendOSMsg() {
		return isCanSendOSMsg;
	}

	public void setIsCanSendOSMsg(Boolean isCanSendOSMsg) {
		this.isCanSendOSMsg = isCanSendOSMsg;
	}

	public String getNextUserId() {
		return nextUserId;
	}

	public void setNextUserId(String nextUserId) {
		this.nextUserId = nextUserId;
	}

	public String getTaskCategory() {
		return taskCategory;
	}

	public void setTaskCategory(String taskCategory) {
		this.taskCategory = taskCategory;
	}

	public String getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
	}

	public String getNextUserName() {
		return nextUserName;
	}

	public void setNextUserName(String nextUserName) {
		this.nextUserName = nextUserName;
	}

}
