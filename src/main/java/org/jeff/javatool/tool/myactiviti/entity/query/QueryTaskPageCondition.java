package org.jeff.javatool.tool.myactiviti.entity.query;

@SuppressWarnings("serial")
public class QueryTaskPageCondition extends QueryTaskCondition {
	private Integer offset;
	private Integer limit;
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
