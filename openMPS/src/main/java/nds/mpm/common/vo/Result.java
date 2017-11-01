/**
 * Project : Review Space
 *
 * Copyright (c) 2012 FREECORE, Inc. All rights reserved.
 */
package nds.mpm.common.vo;

import nds.mpm.common.web.Consts.ResultCode;

/**
 * 처리 결과를 담는 POJO 클래스.
 * @author 
 *
 */
public class Result 
{
	/**
	 * 
	 */
	public Result() 
	{
	}
	
	public Result(ResultCode eCode) 
	{
		nResultCode = eCode.getCode();
		szMsg = eCode.getDesc();
	}

	public final int getResultCode() {
		return nResultCode;
	}
	public final void setResultCode(int nResultCode) {
		this.nResultCode = nResultCode;
	}
	public final String getMsg() {
		return szMsg;
	}
	public final void setMsg(String szMsg) {
		this.szMsg = szMsg;
	}

	private int			nResultCode;
	private String		szMsg;
}
