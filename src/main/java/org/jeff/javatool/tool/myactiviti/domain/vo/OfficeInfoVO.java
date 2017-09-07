package org.jeff.javatool.tool.myactiviti.domain.vo;

import java.util.List;

public class OfficeInfoVO {
	private String id; //组织ID
	private String parentId; //父组织ID
	private String name; //组织名称
	private String sort; //组织序号
	private List<OfficeInfoVO> children;//下属组织
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public List<OfficeInfoVO> getChildren() {
		return children;
	}
	public void setChildren(List<OfficeInfoVO> children) {
		this.children = children;
	}
		
}
