package com.stroe.util;

import java.util.List;

/**
 * 返回结果工具类
 * @author zengjintao
 * 2017年2月26日 14:21
 */
public class Result {

	private ResultCode resultCode;
	
	private List<Object> list;

	public ResultCode getResultCode() {
		return resultCode;
	}

	public void setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}
}
