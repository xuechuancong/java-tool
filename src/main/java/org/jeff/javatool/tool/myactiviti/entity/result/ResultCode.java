package org.jeff.javatool.tool.myactiviti.entity.result;

import java.io.Serializable;

/**
 * Created by weijianfu on 2016/11/22.
 */
public class ResultCode implements Serializable {

    public static final ResultCode SUCCESS = ResultCodes.SUCCESS;

    protected final int code;
    protected String detail;

    public ResultCode(int code) {
        this.code = code;
    }

    public ResultCode(int code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public int getCode() {
        return code;
    }

    public boolean success() {
        return code >= 0;
    }

    public boolean error() {
        return code < 0;
    }

    public boolean isSameCode(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof ResultCode) {
            return code == ((ResultCode) obj).getCode();
        }
        return false;
    }

    public ResultCode setDetail(String detail) {
        return new ResultCode(this.code, detail);
    }
}
