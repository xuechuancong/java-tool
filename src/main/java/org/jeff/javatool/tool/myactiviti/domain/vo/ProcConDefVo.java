package org.jeff.javatool.tool.myactiviti.domain.vo;

import java.io.Serializable;

public class ProcConDefVo implements Serializable {

	private String id;

	/**值or显示的名字**/
	private String val;

	/**状态：0-无效，1-有效**/
	private String state;

	/**数据类型：流程定义类型-procDefCategory，任务定义类型-defCategory，流向类型-flowTypev
	 * {@link org.jeff.javatool.tool.myactiviti.config.ProcConDefType}
	 **/
	private String dataType;

	/**显示顺序**/
	private Long showOrder;

	/**创建时间**/
	private java.util.Date createTime;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setVal(String val){
		this.val = val;
	}

	public String getVal(){
		return this.val;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return this.state;
	}

	public void setDataType(String dataType){
		this.dataType = dataType;
	}

	public String getDataType(){
		return this.dataType;
	}

	public void setShowOrder(Long showOrder){
		this.showOrder = showOrder;
	}

	public Long getShowOrder(){
		return this.showOrder;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

}
