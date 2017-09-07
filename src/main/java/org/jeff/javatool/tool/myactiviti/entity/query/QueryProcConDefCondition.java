package org.jeff.javatool.tool.myactiviti.entity.query;

import java.io.Serializable;

public class QueryProcConDefCondition implements Serializable {

	private String id;

	/** 状态：0-无效，1-有效 **/
	private String state;

	/** 数据类型：流程定义类型-procDefCategory，任务定义类型-taskDefCategory，流向类型-flowType
	 *  {@link org.jeff.javatool.tool.myactiviti.config.ProcConDefType}
	 **/
	private String dataType;
	/** 流程定义名称模糊 */
	private String valLike;
	
	/**显示顺序**/
	private Long showOrder;
	
	/**创建时间**/
	private java.util.Date createTime;

	/** 分页-起始下标 */
	private Integer offset;
	/** 分页-每页数量 */
	private Integer limit;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getValLike() {
		return valLike;
	}

	public void setValLike(String valLike) {
		if (valLike != null && valLike.length() > 0) {
			this.valLike = "%" + valLike + "%";
		}
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
	public Long getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(Long showOrder) {
		this.showOrder = showOrder;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
		
}
