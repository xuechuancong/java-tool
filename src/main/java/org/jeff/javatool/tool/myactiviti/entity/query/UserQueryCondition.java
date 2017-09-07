package org.jeff.javatool.tool.myactiviti.entity.query;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserQueryCondition implements Serializable {
	
	private String roleId;
	private String orgId;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
