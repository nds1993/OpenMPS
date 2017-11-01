/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-6-25 17:57:59
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-6-25 17:57:59
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.userdep.chmanager.service;

import nds.core.common.common.service.CommonObject;

public class ChManagerVO extends CommonObject {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String chnlCd;
    
    private String chnlNm;
    
    private String userId;
    
    private String userNm;
    
    private String mstSub;
    
    private String depNm;
    
    private String regUser;

    private String regDttm;

    private String updtUser;
    
    private String updtDttm;

    
	public String getChnlCd() {
		return chnlCd;
	}

	public void setChnlCd(String chnlCd) {
		this.chnlCd = chnlCd;
	}

	public String getChnlNm() {
		return chnlNm;
	}

	public void setChnlNm(String chnlNm) {
		this.chnlNm = chnlNm;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getMstSub() {
		return mstSub;
	}

	public void setMstSub(String mstSub) {
		this.mstSub = mstSub;
	}

	public String getDepNm() {
		return depNm;
	}

	public void setDepNm(String depNm) {
		this.depNm = depNm;
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