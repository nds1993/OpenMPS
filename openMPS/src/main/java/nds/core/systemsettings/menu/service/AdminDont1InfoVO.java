/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-6-20 16:23:7
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-6-20 16:23:7
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.systemsettings.menu.service;

import nds.core.common.common.service.CommonObject;

public class AdminDont1InfoVO extends CommonObject {
	
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

    private String contsId;

    private String upContsId;

    private String contsNm;

    private String menuInclYn;

    private String openYn;

    private String imgePath;

    private String urlPath;

    private String hlpPath;

    private String menuOrd;

    private String contsDescrpt;

    private String regUser;

    private String regDttm;

    private String updtUser;

    private String updtDttm;

    private String contsLvl;
    
    private String roleCd; 
    
    private String viewMin;
    
    private String viewSec;
    
    private String viewOrd;
    
    private String inOutDvn;
    
    
    
    public String getViewMin() {
		return viewMin;
	}

	public void setViewMin(String viewMin) {
		this.viewMin = viewMin;
	}

	public String getViewSec() {
		return viewSec;
	}

	public void setViewSec(String viewSec) {
		this.viewSec = viewSec;
	}

	public String getInOutDvn() {
		return inOutDvn;
	}

	public void setInOutDvn(String inOutDvn) {
		this.inOutDvn = inOutDvn;
	}

	public String getViewOrd() {
		return viewOrd;
	}

	public void setViewOrd(String viewOrd) {
		this.viewOrd = viewOrd;
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

    public String getContsId() {
        return contsId;
    }

    public void setContsId(String contsId) {
        this.contsId = contsId;
    }

    public String getUpContsId() {
        return upContsId;
    }

    public void setUpContsId(String upContsId) {
        this.upContsId = upContsId;
    }

    public String getContsNm() {
        return contsNm;
    }

    public void setContsNm(String contsNm) {
        this.contsNm = contsNm;
    }

    public String getMenuInclYn() {
        return menuInclYn;
    }

    public void setMenuInclYn(String menuInclYn) {
        this.menuInclYn = menuInclYn;
    }

    public String getOpenYn() {
        return openYn;
    }

    public void setOpenYn(String openYn) {
        this.openYn = openYn;
    }

    public String getImgePath() {
        return imgePath;
    }

    public void setImgePath(String imgePath) {
        this.imgePath = imgePath;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getHlpPath() {
        return hlpPath;
    }

    public void setHlpPath(String hlpPath) {
        this.hlpPath = hlpPath;
    }

    public String getMenuOrd() {
        return menuOrd;
    }

    public void setMenuOrd(String menuOrd) {
        this.menuOrd = menuOrd;
    }

    public String getContsDescrpt() {
        return contsDescrpt;
    }

    public void setContsDescrpt(String contsDescrpt) {
        this.contsDescrpt = contsDescrpt;
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

    public String getContsLvl() {
        return contsLvl;
    }
    
    public void setContsLvl(String contsLvl) {
        this.contsLvl = contsLvl;
    }

	public String getRoleCd() {
		return roleCd;
	}

	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}
    
}