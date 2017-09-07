package org.jeff.javatool.tool.myactiviti.entity.result;

/**
 * Created by weijianfu on 2016/11/22.
 */
public class ResultFactory {
    private static final Result<Object> SUCCESS = new Result<>(ResultCodes.SUCCESS);
    private static final Result<Object> ERROR = new Result<Object>(ResultCodes.ERROR);
    private static final Result<Object> EXCEPTION = new Result<Object>(ResultCodes.EXCEPTION);


    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> build(int code, T data) {
        return new Result<>(code, data);
    }

    public static <T> Result<T> build(int code) {
        return new Result<>(code);
    }

    public static <T> Result<T> build(ResultCode resultCode) {
        return new Result<>(resultCode);
    }

    public static <T> Result<T> error(String detail) {
        return new Result<>(ERROR.getCode(), detail, null);
    }

    public static <T> Result<T> exception(String detail) {
        return new Result<>(EXCEPTION.getCode(), detail, null);
    }

    public static <T> Result<T> error() {
        return (Result<T>) ERROR;
    }

    public static <T> Result<T> success() {
        return (Result<T>) SUCCESS;
    }
}
