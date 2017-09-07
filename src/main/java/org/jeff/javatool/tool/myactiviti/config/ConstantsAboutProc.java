package org.jeff.javatool.tool.myactiviti.config;

public class ConstantsAboutProc {
	public static class AUTHORIZE_STATUS {
		public static String VALID = "1"; // 有效
		public static String INVALID = "0"; // 无效

	}

	// 页面展示列表类型
		public static class TASK_LIST_TYPE {
			public static String WAIT_DO = "1"; // 待办
			public static String AREADY_DO = "2"; // 已办
			public static String WAIT_READ = "3"; // 待阅
			public static String AREADY_READ = "4"; // 已阅
			public static String MY_AREADY_DO = "5"; // 我的在途申请
			public static String MY_AREADY_DONE = "6"; // 我的审毕
			public static String PROCESS_REQUEST = "7"; // 新建申请
			public static String MY_DRAFT = "8"; // 我的草稿
			public static String READ_PASS = "9"; // 发送传阅相关
			public static String ALL_AREADY_DO = "areadyDo"; // 所有已办
			public static String AREADY_DO_NO_BODY = "99"; // 不带人的查询
		}
		/*
		 * 流程状态
		 */
		public static class PROCESS_STATUS {
			public static String AUDITING = "1"; // 审核中
			public static String END = "0"; // 结束
			public static String HANG_UP = "2"; // 挂起

		}
}
