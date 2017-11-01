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
package nds.core.systemsettings.role.service;

import nds.core.common.common.service.CommonObject;

public class RoleVO extends CommonObject {
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

    private String roleNm;

    private String roleDescrpt;

    private String strtYmd;

    private String endYmd;

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

    public String getRoleCd() {
        return roleCd;
    }

    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd;
    }

    public String getRoleNm() {
        return roleNm;
    }

    public void setRoleNm(String roleNm) {
        this.roleNm = roleNm;
    }

    public String getRoleDescrpt() {
        return roleDescrpt;
    }

    public void setRoleDescrpt(String roleDescrpt) {
        this.roleDescrpt = roleDescrpt;
    }

    public String getStrtYmd() {
        return strtYmd;
    }

    public void setStrtYmd(String strtYmd) {
        this.strtYmd = strtYmd;
    }

    public String getEndYmd() {
        return endYmd;
    }

    public void setEndYmd(String endYmd) {
        this.endYmd = endYmd;
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