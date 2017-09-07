package org.jeff.javatool.tool.myactiviti.entity.query;

public class QueryProcJobCondition {
	private String id;

	private String procDefKey;

	private String taskDefKey;

	private Long autoTime;

	private String state;

	/** 分页-起始下标 */
	private Integer offset;
	/** 分页-每页数量 */
	private Integer limit;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getProcDefKey() {
		return procDefKey;
	}

	public void setProcDefKey(String procDefKey) {
		this.procDefKey = procDefKey == null ? null : procDefKey.trim();
	}

	public String getTaskDefKey() {
		return taskDefKey;
	}

	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey == null ? null : taskDefKey.trim();
	}

	public Long getAutoTime() {
		return autoTime;
	}

	public void setAutoTime(Long autoTime) {
		this.autoTime = autoTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state == null ? null : state.trim();
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
		
}
