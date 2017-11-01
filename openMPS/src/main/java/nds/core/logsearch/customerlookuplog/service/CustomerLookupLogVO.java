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
package nds.core.logsearch.customerlookuplog.service;

import nds.core.common.common.service.CommonObject;

public class CustomerLookupLogVO extends CommonObject {
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
    private String userNm;
    
    private String pid;
    private String logNo;
    private String userEmpNo;
    private String scrnNm;
    private String btnNm;
    private String cstNm;
    private String inqObj;
    private String inqCond;
    private String inqDttm;
    private String inqCnt;
    private String cnctPath;
    private String ipAddr;
    private String sysId;
    private String regUser;
    private String regDttm;
    private String updtUser;
    private String updtDttm;
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

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getLogNo() {
		return logNo;
	}

	public void setLogNo(String logNo) {
		this.logNo = logNo;
	}

	public String getUserEmpNo() {
		return userEmpNo;
	}


	public void setUserEmpNo(String userEmpNo) {
		this.userEmpNo = userEmpNo;
	}


	public String getScrnNm() {
		return scrnNm;
	}


	public void setScrnNm(String scrnNm) {
		this.scrnNm = scrnNm;
	}


	public String getBtnNm() {
		return btnNm;
	}


	public void setBtnNm(String btnNm) {
		this.btnNm = btnNm;
	}


	public String getCstNm() {
		return cstNm;
	}


	public void setCstNm(String cstNm) {
		this.cstNm = cstNm;
	}


	public String getInqObj() {
		return inqObj;
	}


	public void setInqObj(String inqObj) {
		this.inqObj = inqObj;
	}


	public String getInqCond() {
		return inqCond;
	}


	public void setInqCond(String inqCond) {
		this.inqCond = inqCond;
	}


	public String getInqDttm() {
		return inqDttm;
	}


	public void setInqDttm(String inqDttm) {
		this.inqDttm = inqDttm;
	}


	public String getInqCnt() {
		return inqCnt;
	}


	public void setInqCnt(String inqCnt) {
		this.inqCnt = inqCnt;
	}


	public String getCnctPath() {
		return cnctPath;
	}


	public void setCnctPath(String cnctPath) {
		this.cnctPath = cnctPath;
	}


	public String getIpAddr() {
		return ipAddr;
	}


	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}


	public String getSysId() {
		return sysId;
	}


	public void setSysId(String sysId) {
		this.sysId = sysId;
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