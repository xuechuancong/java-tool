package org.jeff.javatool.tool.myactiviti.entity.vo;

/**
 * 下一个环节的用户信息
 */
public class AssigneeInfo {
	private String userId;//下一个环节的用户名信息
	private String userName;//下一环节用户名

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

}
