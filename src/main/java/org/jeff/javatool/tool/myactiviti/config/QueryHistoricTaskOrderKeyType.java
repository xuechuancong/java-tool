package org.jeff.javatool.tool.myactiviti.config;

import java.io.Serializable;

/**
 * 历史任务列表查询,排序关键字类型
 * Created by weijianfu on 2016/12/2.
 */
public class QueryHistoricTaskOrderKeyType implements Serializable {

    public static final String TASK_ID = "TASK_ID";
    public static final String PROCESS_INSTANCE_ID = "PROCESS_INSTANCE_ID";
    public static final String TASK_CREATE_TIME = "TASK_CREATE_TIME";
    public static final String TASK_END_TIME = "TASK_END_TIME";


}
