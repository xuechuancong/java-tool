package org.jeff.javatool.tool.myactiviti.entity.query;

public class QueryProcessListPage {
	private Integer limit;
	private Integer offset;
	/**当前用户id**/
	private String userId;
	/**流程类型**/
	private String processType;
	/**流程名称**/
	private String processName;
	/**标题**/
	private String processTitle;
	/**申请人名称**/
	private String requestStaff;
	/**申请人部门名称**/
	private String requestOrgName;
	/**时间下标**/
	private String minTime;
	/**时间上标**/
	private String maxTime;
	/**流程实例id**/
	private String processId;
	/**发送人名称**/
	private String sendStaff;
	/**发送人部门名称**/
	private String sendOrgName;
	/**列表类型**/
	private String taskListType;
	/**模糊查询**/
	private String keyWord;
	
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
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getProcessTitle() {
		return processTitle;
	}
	public void setProcessTitle(String processTitle) {
		this.processTitle = processTitle;
	}
	public String getRequestStaff() {
		return requestStaff;
	}
	public void setRequestStaff(String requestStaff) {
		this.requestStaff = requestStaff;
	}
	public String getRequestOrgName() {
		return requestOrgName;
	}
	public void setRequestOrgName(String requestOrgName) {
		this.requestOrgName = requestOrgName;
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
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getSendStaff() {
		return sendStaff;
	}
	public void setSendStaff(String sendStaff) {
		this.sendStaff = sendStaff;
	}
	public String getSendOrgName() {
		return sendOrgName;
	}
	public void setSendOrgName(String sendOrgName) {
		this.sendOrgName = sendOrgName;
	}
	public String getTaskListType() {
		return taskListType;
	}
	public void setTaskListType(String taskListType) {
		this.taskListType = taskListType;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

}
