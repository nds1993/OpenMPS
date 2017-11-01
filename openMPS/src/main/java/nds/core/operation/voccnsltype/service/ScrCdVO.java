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
package nds.core.operation.voccnsltype.service;

import nds.core.common.common.service.CommonObject;

public class ScrCdVO extends CommonObject {
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

    private String scrId;
    
    private String upScrId;
    
    private String scrKnm;
    
    private String lvl;
    
    private String useYn;
    
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

	public String getScrId() {
		return scrId;
	}

	public void setScrId(String scrId) {
		this.scrId = scrId;
	}

	public String getUpScrId() {
		return upScrId;
	}

	public void setUpScrId(String upScrId) {
		this.upScrId = upScrId;
	}

	public String getScrKnm() {
		return scrKnm;
	}

	public void setScrKnm(String scrKnm) {
		this.scrKnm = scrKnm;
	}

	public String getLvl() {
		return lvl;
	}

	public void setLvl(String lvl) {
		this.lvl = lvl;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
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