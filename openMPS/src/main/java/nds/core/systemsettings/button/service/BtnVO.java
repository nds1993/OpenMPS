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
package nds.core.systemsettings.button.service;

import nds.core.common.common.service.CommonObject;


public class BtnVO extends CommonObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int startNo;
	private int endNo;
	
	//조회조건
	private String schBtnNm;
	
	private String contsId;
    private String roleCd;
    
    private String btnId;

    private String btnNm;

    private String imgeCd;

    private String btnColr;

    private String btnDescrpt;

    private String sortOrd;

    private String shrtKey;

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

	
    public String getSchBtnNm() {
        return schBtnNm;
    }

    
    public void setSchBtnNm(String schBtnNm) {
        this.schBtnNm = schBtnNm;
    }

    public void setUpdtDttm(String updtDttm) {
        this.updtDttm = updtDttm;
    }

    public String getBtnId() {
        return btnId;
    }

    public void setBtnId(String btnId) {
        this.btnId = btnId;
    }

    public String getBtnNm() {
        return btnNm;
    }

    public void setBtnNm(String btnNm) {
        this.btnNm = btnNm;
    }

    public String getImgeCd() {
		return imgeCd;
	}

	public void setImgeCd(String imgeCd) {
		this.imgeCd = imgeCd;
	}

	public String getBtnColr() {
		return btnColr;
	}

	public void setBtnColr(String btnColr) {
		this.btnColr = btnColr;
	}

	public String getBtnDescrpt() {
        return btnDescrpt;
    }

    public void setBtnDescrpt(String btnDescrpt) {
        this.btnDescrpt = btnDescrpt;
    }

    public String getSortOrd() {
        return sortOrd;
    }

    public void setSortOrd(String sortOrd) {
        this.sortOrd = sortOrd;
    }

    public String getShrtKey() {
        return shrtKey;
    }

    public void setShrtKey(String shrtKey) {
        this.shrtKey = shrtKey;
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

}