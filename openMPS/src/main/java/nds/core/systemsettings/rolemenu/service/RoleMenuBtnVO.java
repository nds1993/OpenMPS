/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-6-20 15:49:42
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-6-20 15:49:42
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.systemsettings.rolemenu.service;

import nds.core.common.common.service.CommonObject;

public class RoleMenuBtnVO extends CommonObject {
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

    private String btnId;

    private String contsId;

    private String roleCd;
    
    private String regUser;

    private String regDttm;

    private String updtUser;

    private String updtDttm;

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

    public String getBtnId() {
		return btnId;
	}

	public void setBtnId(String btnId) {
		this.btnId = btnId;
	}

	public String getContsId() {
		return contsId;
	}

	public void setContsId(String contsId) {
		this.contsId = contsId;
	}

	public String getRoleCd() {
		return roleCd;
	}

	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
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