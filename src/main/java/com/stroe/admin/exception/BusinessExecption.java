/**
 * 
 */
package com.stroe.admin.exception;

import com.stroe.admin.util.ResultCode;

/**
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年9月30日下午3:00:15
 */
public class BusinessExecption extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private ResultCode resultCode;
	

	public BusinessExecption(ResultCode resultCode){
		this.resultCode = resultCode;
	}
}
