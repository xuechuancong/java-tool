package org.jeff.javatool.tool.myactiviti.entity;

import org.apache.commons.lang3.StringUtils;

/**
 * 流程类型定义
 * @author hjc 2017年1月10日
 */
public enum ProcessDefinitionEnum {
	
	FINANCEREIMB("FINANCEREIMB","财务-员工报账-付款申请","1","FINANCEREIMB"),
	FINANCE_REIMB_BUSI_TRAVEL("FINANCE_REIMB_BUSI_TRAVEL","财务-员工报账-差旅费报销申请","1","FINANCE_REIMB_BUSI_TRAVEL"),
	FINANCE_REIMB_STAFF_LOAN("FINANCE_REIMB_STAFF_LOAN","财务-员工报账-借款申请","1","FINANCE_REIMB_STAFF_LOAN"),
	FINANCE_REIMB_STAFF_REPAY("FINANCE_REIMB_STAFF_REPAY","财务-员工报账-还款申请","1","FINANCE_REIMB_STAFF_REPAY"),
	
	FINANCE_BUSI_REIMB_YWFK("FINANCE_BUSI_REIMB_YWFK","财务-业务报账-付款申请","1","FINANCE_BUSI_REIMB_YWFK"),
	FINANCE_BUSI_REIMB_YWJK("FINANCE_BUSI_REIMB_YWJK","财务-业务报账-借款申请","1","FINANCE_BUSI_REIMB_YWJK"),
	FINANCE_BUSI_REIMB_YLZZF("FINANCE_BUSI_REIMB_YLZZF","财务-业务报账-已列账支付申请","1","FINANCE_BUSI_REIMB_YLZZF"),
	FINANCE_BUSI_REIMB_YWZSK_SK("FINANCE_BUSI_REIMB_YWZSK_SK","财务-业务报账-暂收款-收款申请","1","FINANCE_BUSI_REIMB_YWZSK_SK"),
	FINANCE_BUSI_REIMB_YWZSK_TK("FINANCE_BUSI_REIMB_YWZSK_TK","财务-业务报账-暂收款-退款申请","1","FINANCE_BUSI_REIMB_YWZSK_TK"),

	FINANCE_GENERAL_LEDGER("FINANCE_GENERAL_LEDGER","财务-账务调整-总账会计核算申请","1","FINANCE_GENERAL_LEDGER"),
	FINANCE_EXPENSE_ACCRUAL_CREATE("FINANCE_EXPENSE_ACCRUAL_CREATE","财务-账务调整-费用预提申请(新增)","1","FINANCE_EXPENSE_ACCRUAL_CREATE"),
	FINANCE_EXPENSE_ACCRUAL_WRITEOFF("FINANCE_EXPENSE_ACCRUAL_WRITEOFF","财务-账务调整-费用预提申请(核销)","1","FINANCE_EXPENSE_ACCRUAL_WRITEOFF"),
	FINANCE_INCOME_CONFIRM("FINANCE_INCOME_CONFIRM","财务-收入报账-收入确认申请","1","FINANCE_INCOME_CONFIRM"),
	FINANCE_INCOME_BILLING("FINANCE_INCOME_BILLING","财务-收入报账-收入开票申请","1","FINANCE_INCOME_BILLING"),
	FINANCE_INCOME_RECEIVED("FINANCE_INCOME_RECEIVED","财务-收入报账-收入到账核销申请","1","FINANCE_INCOME_RECEIVED"),
	;
	
	
	private String processId;
	private String processName;
	private String processDefinitionCategory;
	private String deploymentName;
	
	ProcessDefinitionEnum(String processId,String processName,String processDefinitionCategory,String deploymentName){
		this.processId = processId;
		this.processName = processName;
		this.processDefinitionCategory = processDefinitionCategory;
		this.deploymentName = deploymentName;
	}

	/**
	 * @return the processId
	 */
	public String getProcessId() {
		return processId;
	}

	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}

	/**
	 * @return the processName
	 */
	public String getProcessName() {
		return processName;
	}

	/**
	 * @param processName the processName to set
	 */
	public void setProcessName(String processName) {
		this.processName = processName;
	}

	/**
	 * @return the processDefinitionCategory
	 */
	public String getProcessDefinitionCategory() {
		return processDefinitionCategory;
	}

	/**
	 * @param processDefinitionCategory the processDefinitionCategory to set
	 */
	public void setProcessDefinitionCategory(String processDefinitionCategory) {
		this.processDefinitionCategory = processDefinitionCategory;
	}

	/**
	 * @return the deploymentName
	 */
	public String getDeploymentName() {
		return deploymentName;
	}

	/**
	 * @param deploymentName the deploymentName to set
	 */
	public void setDeploymentName(String deploymentName) {
		this.deploymentName = deploymentName;
	}
	
	
	public static ProcessDefinitionEnum getByProcessId(String processId){
		
		for( ProcessDefinitionEnum em : ProcessDefinitionEnum.values()){
			if(StringUtils.equals(em.getProcessId(), processId)){
				return em;
			}
		}
		return null;
		
	}
}
