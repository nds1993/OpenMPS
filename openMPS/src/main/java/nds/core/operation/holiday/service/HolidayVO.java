/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-7-3 10:21:43
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-7-3 10:21:43
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.operation.holiday.service;

import nds.core.common.common.service.CommonObject;

public class HolidayVO extends CommonObject {
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

    private String depCd;
    
    private String userId;

    private String depNm;
    
    private String userNm;
    
    private String medDvn;

    private String yymmdd;
    
    private String updateDvn;
    
    private String dowCd;

    private String hdayYn;

    private String wrkStrtTm;

    private String wrkEndTm;
    
    private String hdayResn;

    private String regUser;

    private String regDttm;

    private String updtUser;

    private String updtDttm;
    
    private String otherUserCnt;

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

	public String getDepCd() {
		return depCd;
	}

	public void setDepCd(String depCd) {
		this.depCd = depCd;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDepNm() {
		return depNm;
	}

	public void setDepNm(String depNm) {
		this.depNm = depNm;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getMedDvn() {
		return medDvn;
	}

	public void setMedDvn(String medDvn) {
		this.medDvn = medDvn;
	}

	public String getYymmdd() {
		return yymmdd;
	}

	public void setYymmdd(String yymmdd) {
		this.yymmdd = yymmdd;
	}

	public String getUpdateDvn() {
		return updateDvn;
	}

	public void setUpdateDvn(String updateDvn) {
		this.updateDvn = updateDvn;
	}

	public String getDowCd() {
		return dowCd;
	}

	public void setDowCd(String dowCd) {
		this.dowCd = dowCd;
	}

	public String getHdayYn() {
		return hdayYn;
	}

	public void setHdayYn(String hdayYn) {
		this.hdayYn = hdayYn;
	}

	public String getWrkStrtTm() {
		return wrkStrtTm;
	}

	public void setWrkStrtTm(String wrkStrtTm) {
		this.wrkStrtTm = wrkStrtTm;
	}

	public String getWrkEndTm() {
		return wrkEndTm;
	}

	public void setWrkEndTm(String wrkEndTm) {
		this.wrkEndTm = wrkEndTm;
	}

	public String getHdayResn() {
		return hdayResn;
	}

	public void setHdayResn(String hdayResn) {
		this.hdayResn = hdayResn;
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

	public String getOtherUserCnt() {
		return otherUserCnt;
	}

	public void setOtherUserCnt(String otherUserCnt) {
		this.otherUserCnt = otherUserCnt;
	}


}