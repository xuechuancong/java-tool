package org.jeff.javatool.tool.myactiviti.config;

/**
 * Created by weijianfu on 2017/3/28.
 */
public class SeqConfig {

	/** 流程引擎-BPMN文件名序列 **/
	public final static String SEQ_PROC_BPMN = "SEQ_BPMN";
	/** 流程引擎-自定义流程序列 **/
	public final static String SEQ_PROC_CUST_KEY = "SEQ_PROC_CUST_KEY";

	/** 通用表单序列开始 **/
	public final static class GENERAL_FORM {
		/** 通用表单-字节表序列 **/
		public final static String SEQ_CAIH_GE_BYTEARRAY = "SEQ_CAIH_GE_BYTEARRAY";
		/** 通用表单-字典表序列 **/
		public final static String SEQ_CAIH_RE_DICT = "SEQ_CAIH_RE_DICT";
		/** 通用表单-字段定义表序列 **/
		public final static String SEQ_CAIH_RE_FIELD = "SEQ_CAIH_RE_FIELD";
		/** 通用表单-字段属性表 序列 **/
		public final static String SEQ_CAIH_RE_FIELD_ATTRIBUTE = "SEQ_CAIH_RE_FIELD_ATTRIBUTE";
		/** 通用表单-表单配置表序列 **/
		public final static String SEQ_CAIH_RE_FORM = "SEQ_CAIH_RE_FORM";
		/** 通用表单-表单字段表序列 **/
		public final static String SEQ_CAIH_RE_FORM_FIELD = "SEQ_CAIH_RE_FORM_FIELD";

		/** 通用表单-表单流程关系表序列 **/
		public final static String SEQ_CAIH_RE_FORM_PROCESS = "SEQ_CAIH_RE_FORM_PROCESS";
		/** 通用表单-表单实例表序列 **/
		public final static String SEQ_CAIH_RU_FORM = "SEQ_CAIH_RU_FORM";
		/** 通用表单-字段实例表序列 **/
		public final static String SEQ_CAIH_RU_FORM_FIELD = "SEQ_CAIH_RU_FORM_FIELD";
		/** 通用表单-表单与表单表序列 **/
		public final static String SEQ_CAIH_RE_FORM_FORM = "SEQ_CAIH_RE_FORM_FORM";
		/** 通用表单-数据源**/
		public final static String SEQ_CAIH_RE_DATA_SOURCE = "SEQ_CAIH_RE_DATA_SOURCE";
		/** 通用表单-草稿**/
		public final static String SEQ_CAIH_DRAFT_INFO = "SEQ_CAIH_DRAFT_INFO";
		/** 通用表单-收藏**/
		public final static String SEQ_CAIH_COLLECT_INFO = "SEQ_CAIH_COLLECT_INFO";

	}
	/** 通用表单序列结束 **/
    /** 流程常量定义序列 **/
    public final static String SEQ_PROC_CON_DEF = "SEQ_PROC_CON_DEF";
    /** 流程定时任务配置序列 **/
    public final static String SEQ_PROC_JOB = "SEQ_PROC_JOB";
    
}
