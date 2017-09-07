package org.jeff.javatool.tool.myactiviti.entity.result;

import java.io.Serializable;

/**
 * 类名: JsonData.java
 * 描述: json 交互对象
 * 版本: 1.0.0
 * 创建时间: 2016/11/29 16:24
 */
public class JsonData<T> implements Serializable {

    private static final long serialVersionUID = -7500071601125905415L;
    T data; //接口返回数据
    Object extData; //接口返回额外数据
    Result result;  //接口调用是否成功
    String message;


    public enum Result {
    	SUCCESS("success"),
    	FAILED("failed");

        private final String text;
        private Result(final String text){
            this.text=text;
        }
        @Override
        public String toString(){
            return text;
        }
    }

    public JsonData() {
    }

    public JsonData(T data, Object extData, Result result, String message) {
        this.data = data;
        this.extData = extData;
        this.result = result;
        this.message = message;
    }

    /**
     * 成功结果封装   weijianfu
     * @param message
     * @param <T>
     * @return
     */
    public static <T> JsonData<T> success(String message) {
        return new JsonData<>(null, null, Result.SUCCESS, message);
    }

    /**
     * 成功结果封装   weijianfu
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> JsonData<T> success(T data, String message) {
        return new JsonData<>(data, null, Result.SUCCESS, message);
    }

    /**
     * 成功结果封装   weijianfu
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> JsonData<T> success(T data, Object extData, String message) {
        return new JsonData<>(data, extData, Result.SUCCESS, message);
    }

    /**
     * 失败结果封装   weijianfu
     * @param message
     * @param <T>
     * @return
     */
    public static <T> JsonData<T> fail(String message) {
        return new JsonData<>(null, null, Result.FAILED, message);
    }

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Object getExtData() {
        return extData;
    }

    public void setExtData(Object extData) {
        this.extData = extData;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}


}
