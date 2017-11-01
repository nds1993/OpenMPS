/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-6-25 10:44:1
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-6-25 10:44:1
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.common.popup.service;

import nds.core.common.common.service.CommonObject;


public class PopupHelpReqInfoVO extends CommonObject {
    
    private static final long serialVersionUID = 1L;
    
    /** 
     * 협조요청 resultVO
     */
    private String reqId;
    private String reqType;
    private String tretDeptCd;
    private String tretEmpno;
    private String tretText;
    private String tretDttm;
    private String tretCmpltYn;
    private String reqDeptCd;
    private String reqEmpno;
    private String reqText;
    private String reqDttm;
    private String regUser;
    private String regDttm;
    private String updtUser;
    private String updtDttm;
    
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public String getTretDeptCd() {
		return tretDeptCd;
	}
	public void setTretDeptCd(String tretDeptCd) {
		this.tretDeptCd = tretDeptCd;
	}
	public String getTretEmpno() {
		return tretEmpno;
	}
	public void setTretEmpno(String tretEmpno) {
		this.tretEmpno = tretEmpno;
	}
	public String getTretText() {
		return tretText;
	}
	public void setTretText(String tretText) {
		this.tretText = tretText;
	}
	public String getTretDttm() {
		return tretDttm;
	}
	public void setTretDttm(String tretDttm) {
		this.tretDttm = tretDttm;
	}
	public String getTretCmpltYn() {
		return tretCmpltYn;
	}
	public void setTretCmpltYn(String tretCmpltYn) {
		this.tretCmpltYn = tretCmpltYn;
	}
	public String getReqDeptCd() {
		return reqDeptCd;
	}
	public void setReqDeptCd(String reqDeptCd) {
		this.reqDeptCd = reqDeptCd;
	}
	public String getReqEmpno() {
		return reqEmpno;
	}
	public void setReqEmpno(String reqEmpno) {
		this.reqEmpno = reqEmpno;
	}
	public String getReqText() {
		return reqText;
	}
	public void setReqText(String reqText) {
		this.reqText = reqText;
	}
	public String getReqDttm() {
		return reqDttm;
	}
	public void setReqDttm(String reqDttm) {
		this.reqDttm = reqDttm;
	}
	public String getRegUser() {
		return regUser;
	}
	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getUpdtUser() {
		return updtUser;
	}
	public void setUpdtUser(String updtUser) {
		this.updtUser = updtUser;
	}
	public String getUpdtDttm() {
		return updtDttm;
	}
	public void setUpdtDttm(String updtDttm) {
		this.updtDttm = updtDttm;
	}
    
}