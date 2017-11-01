/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2009-6-11 10:23:05
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* CHANGE REVISION
* ------------------------------
* AUTHOR   : NDS
* DATE     : 2009-6-11 10:23:05
* REVISION : 1.0   First release.
* -------------------------------
* CLASS DESCRIPTION
* -------------------------------
*
*/
package nds.core.logsearch.deallog.service;

import nds.core.common.common.service.CommonObject;

public class DealLogVO extends CommonObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * PageNavigator : start Page
     */
    private int startNo;

    /**
     * PageNavigator : end Page
     */
    private int endNo;

	/**
     * 조회조건
     */
    private String schStartDd;
    
    private String schEndDd;
    
    private String userEmpNo;
    
    private String depNo;
    
    private String schContsNm;
    
    private String pid;
    
    private String bid;
    
    private String excpLogNo;
    
    private String contsId;
    
    private String btnId;
    
    private String excpCd;
    
    private String excpCntn;
    
    private String regUser;
    
    private String regDttm;
    
    private String userNm;
    
    private String depNm;
    
    private String sidx;
    
    private String sord;
    
    
	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}

	public String getSchStartDd() {
		return schStartDd;
	}

	public void setSchStartDd(String schStartDd) {
		this.schStartDd = schStartDd;
	}

	public String getSchEndDd() {
		return schEndDd;
	}

	public void setSchEndDd(String schEndDd) {
		this.schEndDd = schEndDd;
	}

	public String getUserEmpNo() {
		return userEmpNo;
	}

	public void setUserEmpNo(String userEmpNo) {
		this.userEmpNo = userEmpNo;
	}

	public String getDepNo() {
		return depNo;
	}

	public void setDepNo(String depNo) {
		this.depNo = depNo;
	}

	public String getSchContsNm() {
		return schContsNm;
	}

	public void setSchContsNm(String schContsNm) {
		this.schContsNm = schContsNm;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getExcpLogNo() {
		return excpLogNo;
	}

	public void setExcpLogNo(String excpLogNo) {
		this.excpLogNo = excpLogNo;
	}

	public String getContsId() {
		return contsId;
	}

	public void setContsId(String contsId) {
		this.contsId = contsId;
	}

	public String getBtnId() {
		return btnId;
	}

	public void setBtnId(String btnId) {
		this.btnId = btnId;
	}

	public String getExcpCd() {
		return excpCd;
	}

	public void setExcpCd(String excpCd) {
		this.excpCd = excpCd;
	}

	public String getExcpCntn() {
		return excpCntn;
	}

	public void setExcpCntn(String excpCntn) {
		this.excpCntn = excpCntn;
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

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getDepNm() {
		return depNm;
	}

	public void setDepNm(String depNm) {
		this.depNm = depNm;
	}
}