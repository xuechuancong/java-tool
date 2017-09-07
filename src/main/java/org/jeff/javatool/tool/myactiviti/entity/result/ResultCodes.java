package org.jeff.javatool.tool.myactiviti.entity.result;

/**
 * Created by weijianfu on 2016/11/22.
 */

public class ResultCodes {
    public static final ResultCode SUCCESS = new ResultCode(0, "成功");
    public static final ResultCode EXCEPTION = new ResultCode(-1, "系统错误");
    public static final ResultCode PARAM_EMPTY = new ResultCode(-2, "缺少参数");
    public static final ResultCode PARAM_ILLEGAL = new ResultCode(-3, "参数非法");
    public static final ResultCode ERROR = new ResultCode(-4, "操作失败");

}

