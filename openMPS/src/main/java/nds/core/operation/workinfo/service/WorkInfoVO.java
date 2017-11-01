/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-7-20 1:7:47
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-7-20 1:7:47
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.operation.workinfo.service;

import nds.core.common.common.service.CommonObject;
import nds.frm.util.StringUtil;

public class WorkInfoVO extends CommonObject {
	
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
     * 업무정보 searchVO & resultVO 
     */
    private String userId;        // searchVO

    private String userEmpno;

    private String upDepCd;
    
    private String upDepNm;
    
    private String depCd;

    private String depNm;

    private String pwd;

    private String userNm;

    private String innrNo;

    private String cntcplc;

    private String wrkInfo;
    
    private String adviserYn;
    
    private String mgYn;
    
    private String cnfmYn;       // searchVO

    private String regUser;

    private String regDttm;

    private String updtUser;

    private String updtDttm;

    private String rtmYn;
    
    private String pstCd;
    
    private String pstNm;
    
    private String pwdChangYmd;
    
    private String pwdChangYn;
    
    private String before;
    

	public String getBefore() {
		return before;
	}

	public void setBefore(String before) {
		this.before = before;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserEmpno() {
		return userEmpno;
	}

	public void setUserEmpno(String userEmpno) {
		this.userEmpno = userEmpno;
	}

	public String getUpDepCd() {
		return upDepCd;
	}

	public void setUpDepCd(String upDepCd) {
		this.upDepCd = upDepCd;
	}

	public String getUpDepNm() {
		return upDepNm;
	}

	public void setUpDepNm(String upDepNm) {
		this.upDepNm = upDepNm;
	}

	public String getDepCd() {
		return depCd;
	}

	public void setDepCd(String depCd) {
		this.depCd = depCd;
	}

	public String getDepNm() {
		return depNm;
	}

	public void setDepNm(String depNm) {
		this.depNm = depNm;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getInnrNo() {
		return innrNo;
	}

	public void setInnrNo(String innrNo) {
		this.innrNo = innrNo;
	}

	public String getCntcplc() {
		return cntcplc;
	}

	public void setCntcplc(String cntcplc) {
		this.cntcplc = cntcplc;
	}

	public String getWrkInfo() {
		return wrkInfo;
	}

	public void setWrkInfo(String wrkInfo) {
		this.wrkInfo = wrkInfo;
	}

	public String getAdviserYn() {
		return adviserYn;
	}

	public void setAdviserYn(String adviserYn) {
		this.adviserYn = adviserYn;
	}

	public String getMgYn() {
		return mgYn;
	}

	public void setMgYn(String mgYn) {
		this.mgYn = mgYn;
	}

	public String getCnfmYn() {
		return cnfmYn;
	}

	public void setCnfmYn(String cnfmYn) {
		this.cnfmYn = cnfmYn;
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

	public String getRtmYn() {
		return rtmYn;
	}

	public void setRtmYn(String rtmYn) {
		this.rtmYn = rtmYn;
	}

	public String getPstCd() {
		return pstCd;
	}

	public void setPstCd(String pstCd) {
		this.pstCd = pstCd;
	}

	public String getPstNm() {
		return pstNm;
	}

	public void setPstNm(String pstNm) {
		this.pstNm = pstNm;
	}

	public String getPwdChangYmd() {
		return pwdChangYmd;
	}

	public void setPwdChangYmd(String pwdChangYmd) {
		this.pwdChangYmd = pwdChangYmd;
	}

	public String getPwdChangYn() {
		return pwdChangYn;
	}

	public void setPwdChangYn(String pwdChangYn) {
		this.pwdChangYn = pwdChangYn;
	}
    
}