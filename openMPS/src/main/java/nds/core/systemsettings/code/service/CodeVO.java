/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-6-21 13:38:39
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-6-21 13:38:39
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.systemsettings.code.service;

import java.util.List;

import nds.core.common.common.service.CommonObject;

public class CodeVO extends CommonObject {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String cdId;

    private String cdTp;
    
    private String schACdKnm;
    private String schBCdKnm;
    
    private String upCdKnm;
    
    /**
     * PageNavigator : start Page
     */
    private int startNo;

    /**
     * PageNavigator : end Page
     */
    private int endNo;
    
    private String depth;
    
    private List<String> cdList;
    
    private String deptcode;

    public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	private String cdKnm;

    private String cdEnnm;

    private String cdDtlKnm;

    private String cdDtlEnnm;

    private String cdOrd;

    private String mgItem1;

    private String mgItem2;

    private String mgItem3;

    private String mgItem4;

    private String endYmd;

    private String useYn;

    private String upCdId;

    private String upCdTp;

    private String dataMvo;

    private String regUser;

    private String regDttm;

    private String updtUser;

    private String updtDttm;

    
    public String getCdId() {
		return cdId;
	}

	public void setCdId(String cdId) {
		this.cdId = cdId;
	}

	public String getCdTp() {
		return cdTp;
	}

	public void setCdTp(String cdTp) {
		this.cdTp = cdTp;
	}

	public String getSchACdKnm() {
        return schACdKnm;
    }
    
    public void setSchACdKnm(String schACdKnm) {
        this.schACdKnm = schACdKnm;
    }
    
    public String getSchBCdKnm() {
        return schBCdKnm;
    }
    
    public void setSchBCdKnm(String schBCdKnm) {
        this.schBCdKnm = schBCdKnm;
    }

    public String getUpCdKnm() {
        return upCdKnm;
    }

    public void setUpCdKnm(String upCdKnm) {
        this.upCdKnm = upCdKnm;
    }
    
    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }
    
    public List<String> getCdList() {
        return cdList;
    }
    
    public void setCdList(List<String> cdList) {
        this.cdList = cdList;
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

    public String getCdKnm() {
        return cdKnm;
    }

    public void setCdKnm(String cdKnm) {
        this.cdKnm = cdKnm;
    }

    public String getCdEnnm() {
        return cdEnnm;
    }

    public void setCdEnnm(String cdEnnm) {
        this.cdEnnm = cdEnnm;
    }

    public String getCdDtlKnm() {
        return cdDtlKnm;
    }

    public void setCdDtlKnm(String cdDtlKnm) {
        this.cdDtlKnm = cdDtlKnm;
    }

    public String getCdDtlEnnm() {
        return cdDtlEnnm;
    }

    public void setCdDtlEnnm(String cdDtlEnnm) {
        this.cdDtlEnnm = cdDtlEnnm;
    }

    public String getCdOrd() {
        return cdOrd;
    }

    public void setCdOrd(String cdOrd) {
        this.cdOrd = cdOrd;
    }

    public String getMgItem1() {
        return mgItem1;
    }

    public void setMgItem1(String mgItem1) {
        this.mgItem1 = mgItem1;
    }

    public String getMgItem2() {
        return mgItem2;
    }

    public void setMgItem2(String mgItem2) {
        this.mgItem2 = mgItem2;
    }

    public String getMgItem3() {
        return mgItem3;
    }

    public void setMgItem3(String mgItem3) {
        this.mgItem3 = mgItem3;
    }

    public String getMgItem4() {
        return mgItem4;
    }

    public void setMgItem4(String mgItem4) {
        this.mgItem4 = mgItem4;
    }

    public String getEndYmd() {
        return endYmd;
    }

    public void setEndYmd(String endYmd) {
        this.endYmd = endYmd;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getUpCdId() {
        return upCdId;
    }

    public void setUpCdId(String upCdId) {
        this.upCdId = upCdId;
    }

    public String getUpCdTp() {
        return upCdTp;
    }

    public void setUpCdTp(String upCdTp) {
        this.upCdTp = upCdTp;
    }

    public String getDataMvo() {
        return dataMvo;
    }

    public void setDataMvo(String dataMvo) {
        this.dataMvo = dataMvo;
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