/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2016-10-14 17:57:59
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : 
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.userdep.department.service;

import nds.core.common.common.service.CommonObject;

public class DepartMentVO extends CommonObject {
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

    private String depLvl;
    
    private String depCd;

    private String depNm;

    private String depSortOrd;

    private String upDepCd;
    
    private String upDepNm;

    private String hoPlcnotDvnCd;

    private String regUser;

    private String regDttm;

    private String updtUser;

    private String updtDttm;
    
    private String leap;

    private String chld;
    
    private String sortKey;
    
    

    public String getSortKey() {
		return sortKey;
	}

	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
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

    
    public String getDepLvl() {
        return depLvl;
    }

    
    public void setDepLvl(String depLvl) {
        this.depLvl = depLvl;
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

    public String getDepSortOrd() {
        return depSortOrd;
    }

    public void setDepSortOrd(String depSortOrd) {
        this.depSortOrd = depSortOrd;
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

    public String getHoPlcnotDvnCd() {
        return hoPlcnotDvnCd;
    }

    public void setHoPlcnotDvnCd(String hoPlcnotDvnCd) {
        this.hoPlcnotDvnCd = hoPlcnotDvnCd;
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

    
    public String getLeap() {
        return leap;
    }

    
    public void setLeap(String leap) {
        this.leap = leap;
    }

    
    public String getChld() {
        return chld;
    }

    
    public void setChld(String chld) {
        this.chld = chld;
    }
    
}