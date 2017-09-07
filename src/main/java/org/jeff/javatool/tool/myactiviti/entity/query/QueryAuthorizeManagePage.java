package org.jeff.javatool.tool.myactiviti.entity.query;



public class QueryAuthorizeManagePage {
	private String authStaffId;
	private String authedStaffId;
	private String startTime;
	private String endTime;
	private Integer limit;
	private Integer offset;
	
	public String getAuthStaffId() {
		return authStaffId;
	}
	public void setAuthStaffId(String authStaffId) {
		this.authStaffId = authStaffId;
	}
	public String getAuthedStaffId() {
		return authedStaffId;
	}
	public void setAuthedStaffId(String authedStaffId) {
		this.authedStaffId = authedStaffId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
}
