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
package nds.core.systemsettings.roleuser.service;

import nds.core.common.common.service.CommonObject;

public class RoleUserVO extends CommonObject {

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
    
    private String roleCd;
    private String userId;
    private String userNm;
    private String userDvn;
    private String userDvnNm;
    private String depCd;
    private String depNm;
    private String fullDepNm;
    private String pstNm;
    private String email;
    private String innrNo;
    private String totalCnt;
    private String regUser;
    private String regDttm;
    private String updtUser;
    private String updtDttm;

    public String getRoleCd() {
        return roleCd;
    }

    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd;
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

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getUserDvn() {
		return userDvn;
	}

	public void setUserDvn(String userDvn) {
		this.userDvn = userDvn;
	}

	public String getUserDvnNm() {
		return userDvnNm;
	}

	public void setUserDvnNm(String userDvnNm) {
		this.userDvnNm = userDvnNm;
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

	public String getFullDepNm() {
		return fullDepNm;
	}

	public void setFullDepNm(String fullDepNm) {
		this.fullDepNm = fullDepNm;
	}

	public String getPstNm() {
		return pstNm;
	}

	public void setPstNm(String pstNm) {
		this.pstNm = pstNm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInnrNo() {
		return innrNo;
	}

	public void setInnrNo(String innrNo) {
		this.innrNo = innrNo;
	}

	public String getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(String totalCnt) {
		this.totalCnt = totalCnt;
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