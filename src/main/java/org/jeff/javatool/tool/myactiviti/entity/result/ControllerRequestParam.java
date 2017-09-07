package org.jeff.javatool.tool.myactiviti.entity.result;

public class ControllerRequestParam<T>   {
	private T data;//如果前端传过来的数据是明文，则直接存在此字段；如果是密文，则存在cipherText里，验签解密后会被拦截器自动填充（覆盖）到data里。
	private String sign;//如果是明文数据，则是对data进行的签名；如果是密文数据，则是对cipherText进行签名
	private String createTime;//报文在前端生成的时间，格式是"yyyyMMddHHmmss"，用于防报文重放
	private String cipherText;//如果要加密的话密文放在cipherText里，验签解密后会被拦截器自动填充到data里
	

	@SuppressWarnings("unchecked")
	public Class<?> getDataClass() {
		return (data == null ? ((T)new Object()).getClass() : data.getClass());
	}
	public String getCipherText() {
		return cipherText;
	}
	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
}
