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
package nds.core.operation.surveytmpl.service;

import nds.core.common.common.service.CommonObject;

public class SurveyTemplateVO extends CommonObject {
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
    private String imageNm;
    //조회조건    
    private String schTmplDvn;
    private String schTmplNm;
    private String schUseYn;
    private String schTmplNo;
    
    private String probOrd;

    private String probNm;

    private String viewOrd;

    private String viewNm;

    private String viewScr;
    
    
    private String tmplNo;

    private String tmplDvn;

    private String tmplNm;

    private String tmplDescrpt;

    private String useYn;
    
    private String regUser;
    
    private String regUserNm;

    private String regDttm;

    private String updtUser;
    
    private String updtDttm;

    private String cntn;
    
    private String inoutDvn;
    
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

    public String getTmplNo() {
        return tmplNo;
    }

    public void setTmplNo(String tmplNo) {
        this.tmplNo = tmplNo;
    }

    public String getTmplDvn() {
        return tmplDvn;
    }

    public void setTmplDvn(String tmplDvn) {
        this.tmplDvn = tmplDvn;
    }

    public String getTmplNm() {
        return tmplNm;
    }

    public void setTmplNm(String tmplNm) {
        this.tmplNm = tmplNm;
    }

    public String getTmplDescrpt() {
        return tmplDescrpt;
    }

    public void setTmplDescrpt(String tmplDescrpt) {
        this.tmplDescrpt = tmplDescrpt;
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

    public String getRegUserNm() {
        return regUserNm;
    }

    public void setRegUserNm(String regUserNm) {
        this.regUserNm = regUserNm;
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

    public String getCntn() {
        return cntn;
    }

    public void setCntn(String cntn) {
        this.cntn = cntn;
    }

	public String getInoutDvn() {
		return inoutDvn;
	}

	public void setInoutDvn(String inoutDvn) {
		this.inoutDvn = inoutDvn;
	}

	public String getSchTmplNm() {
		return schTmplNm;
	}

	public void setSchTmplNm(String schTmplNm) {
		this.schTmplNm = schTmplNm;
	}

	public String getSchTmplDvn() {
		return schTmplDvn;
	}

	public void setSchTmplDvn(String schTmplDvn) {
		this.schTmplDvn = schTmplDvn;
	}

    public String getContsId() {
        return contsId;
    }

    public void setContsId(String contsId) {
        this.contsId = contsId;
    }

    public String getImageNm() {
        return imageNm;
    }

    public void setImageNm(String imageNm) {
        this.imageNm = imageNm;
    }

	public String getSchUseYn() {
		return schUseYn;
	}

	public void setSchUseYn(String schUseYn) {
		this.schUseYn = schUseYn;
	}

    public String getSchTmplNo() {
        return schTmplNo;
    }

    public void setSchTmplNo(String schTmplNo) {
        this.schTmplNo = schTmplNo;
    }

    
    public String getProbOrd() {
        return probOrd;
    }

    
    public void setProbOrd(String probOrd) {
        this.probOrd = probOrd;
    }

    public String getProbNm() {
        return probNm;
    }

    
    public void setProbNm(String probNm) {
        this.probNm = probNm;
    }

    
    public String getViewOrd() {
        return viewOrd;
    }

    
    public void setViewOrd(String viewOrd) {
        this.viewOrd = viewOrd;
    }

    
    public String getViewNm() {
        return viewNm;
    }

    
    public void setViewNm(String viewNm) {
        this.viewNm = viewNm;
    }

    
    public String getViewScr() {
        return viewScr;
    }

    
    public void setViewScr(String viewScr) {
        this.viewScr = viewScr;
    }
    
}