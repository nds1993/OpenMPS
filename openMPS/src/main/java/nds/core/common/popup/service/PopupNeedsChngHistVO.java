/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-6-25 10:44:1
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-6-25 10:44:1
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.common.popup.service;

import nds.core.common.common.service.CommonObject;

public class PopupNeedsChngHistVO extends CommonObject{
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
     * Needs 변경 이력 searchVO & resultVO
     */
    private String cstNm;
    
    private String tit;
    
    private String receDd;

    private String seqNo;

    private String befVocId;

    private String befType;

    private String aftVocId;      // searchVO

    private String aftType;

    private String regUser;

    private String regDttm;

    private String updtUser;

    private String updtDttm;
    
    private String vocId;
    
    private String newVocId;
    
    private String needsType;
    
    private String befor;
    
    private String after;
    		

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

    
    public String getCstNm() {
        return cstNm;
    }

    
    public void setCstNm(String cstNm) {
        this.cstNm = cstNm;
    }

    
    public String getTit() {
        return tit;
    }

    
    public void setTit(String tit) {
        this.tit = tit;
    }

    
    public String getReceDd() {
        return receDd;
    }

    
    public void setReceDd(String receDd) {
        this.receDd = receDd;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getBefVocId() {
        return befVocId;
    }

    public void setBefVocId(String befVocId) {
        this.befVocId = befVocId;
    }

    public String getBefType() {
        return befType;
    }

    public void setBefType(String befType) {
        this.befType = befType;
    }

    public String getAftVocId() {
        return aftVocId;
    }

    public void setAftVocId(String aftVocId) {
        this.aftVocId = aftVocId;
    }

    public String getAftType() {
        return aftType;
    }

    public void setAftType(String aftType) {
        this.aftType = aftType;
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

	public String getVocId() {
		return vocId;
	}

	public void setVocId(String vocId) {
		this.vocId = vocId;
	}

	public String getNewVocId() {
		return newVocId;
	}

	public void setNewVocId(String newVocId) {
		this.newVocId = newVocId;
	}

	public String getNeedsType() {
		return needsType;
	}

	public void setNeedsType(String needsType) {
		this.needsType = needsType;
	}

	public String getBefor() {
		return befor;
	}

	public void setBefor(String befor) {
		this.befor = befor;
	}

	public String getAfter() {
		return after;
	}

	public void setAfter(String after) {
		this.after = after;
	}
    
    
}