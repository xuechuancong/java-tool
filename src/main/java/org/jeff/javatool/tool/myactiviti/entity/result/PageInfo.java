package org.jeff.javatool.tool.myactiviti.entity.result;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PageInfo implements Serializable{
	/**当前页**/
	private Integer currPage;
	/**每页条数**/
	private Integer pageSize;
	/**总条数**/
	private Integer totalCount;
	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	
}
