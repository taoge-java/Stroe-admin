package com.stroe.util;

/**
 * ajax校验工具类
 * @author zengjintao
 * 2017年2月26日 14:21
 */
public class ResultCode {

	private static final int  SUCCESS=1;
	
	@SuppressWarnings("unused")
	private static final int FAIL=-1;
	
	private String message="";

	private int code=SUCCESS;
	
	
	public ResultCode(int code,String message) {
		this.code=code;
		this.message = message;
	}


	public String getMessage() {
		return message;
	}


	public int getCode() {
		return code;
	}
}
