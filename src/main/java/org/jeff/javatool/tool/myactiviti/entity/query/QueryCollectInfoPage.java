package org.jeff.javatool.tool.myactiviti.entity.query;


public class QueryCollectInfoPage{
	private String processType;
	private String title;
	private String requestStaff;
	private String requestOrg;
	private String processId;
	private String minTime;
	private String maxTime;
	private Integer limit;
	private Integer offset;
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRequestStaff() {
		return requestStaff;
	}
	public void setRequestStaff(String requestStaff) {
		this.requestStaff = requestStaff;
	}
	public String getRequestOrg() {
		return requestOrg;
	}
	public void setRequestOrg(String requestOrg) {
		this.requestOrg = requestOrg;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getMinTime() {
		return minTime;
	}
	public void setMinTime(String minTime) {
		this.minTime = minTime;
	}
	public String getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(String maxTime) {
		this.maxTime = maxTime;
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
