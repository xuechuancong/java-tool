package org.jeff.javatool.tool.myactiviti.domain.entity;
import java.io.Serializable;


/**
 * 我的收藏信息表
 **/
@SuppressWarnings("serial")
public class CaihCollectInfo implements Serializable {

	/**记录标识**/
	private Long id;

	/**用户ID**/
	private String userId;

	/**用户名**/
	private String userName;

	/**类型（归属于哪个模块：合同、财务....）**/
	private String collectType;

	/**收藏标题**/
	private String title;

	/**收藏主体id，例如流程id**/
	private String collectId;

	/**草稿建立时间**/
	private java.util.Date collectTime;

	/**流程名称**/
	private String processName;

	/**申请人**/
	private String requestStaff;

	/**申请人部门**/
	private String requestOrg;

	/**申请时间**/
	private java.util.Date requestTime;

	/**扩展字段1**/
	private String spareField1;

	/**扩展字段2**/
	private String spareField2;



	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return this.userName;
	}

	public void setCollectType(String collectType){
		this.collectType = collectType;
	}

	public String getCollectType(){
		return this.collectType;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return this.title;
	}

	public void setCollectId(String collectId){
		this.collectId = collectId;
	}

	public String getCollectId(){
		return this.collectId;
	}

	public void setCollectTime(java.util.Date collectTime){
		this.collectTime = collectTime;
	}

	public java.util.Date getCollectTime(){
		return this.collectTime;
	}

	public void setProcessName(String processName){
		this.processName = processName;
	}

	public String getProcessName(){
		return this.processName;
	}

	public void setRequestStaff(String requestStaff){
		this.requestStaff = requestStaff;
	}

	public String getRequestStaff(){
		return this.requestStaff;
	}

	public void setRequestOrg(String requestOrg){
		this.requestOrg = requestOrg;
	}

	public String getRequestOrg(){
		return this.requestOrg;
	}

	public void setRequestTime(java.util.Date requestTime){
		this.requestTime = requestTime;
	}

	public java.util.Date getRequestTime(){
		return this.requestTime;
	}

	public void setSpareField1(String spareField1){
		this.spareField1 = spareField1;
	}

	public String getSpareField1(){
		return this.spareField1;
	}

	public void setSpareField2(String spareField2){
		this.spareField2 = spareField2;
	}

	public String getSpareField2(){
		return this.spareField2;
	}

}
