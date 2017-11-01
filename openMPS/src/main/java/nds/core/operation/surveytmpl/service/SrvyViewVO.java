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

public class SrvyViewVO extends CommonObject {
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

    private String probNo;

    private String tmplNo;
    
    private String lineNo;

    private String viewNo;
    
    //처리구분
    private String crudGbn;
    
    //VOC번호
    private String mwNo;
    
    //대관/견학번호
    private String rantNo;
    
    //view답변
    private String viewRspn;
    //서술응답
    private String descRspn;
    
    private String ord;

    private String viewNm;

    private String scr;
    
    private String rnk;
    
    private String regUser;

    private String regDttm;

    private String updtUser;

    private String updtDttm;
    
    private String cstNo;
    
    private String cnt;
    
    private String percnt;
    
    private String vocId;
    
    //응답여부
    private String answYn;
    
    private String rn;
    
    private String lev;
    
    private String probNm;
    
    private String preOrd;
    
    private String schTmplNo;
    private String schProbNo;
    private String schViewType;
    
    private String src;
    
    private String allcnt;
    
    private String schGubun;
    
    private String schStartDd;
    
    private String schEndDd;

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

	public String getProbNo() {
		return probNo;
	}

	public void setProbNo(String probNo) {
		this.probNo = probNo;
	}

	public String getTmplNo() {
		return tmplNo;
	}

	public void setTmplNo(String tmplNo) {
		this.tmplNo = tmplNo;
	}

	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public String getViewNo() {
		return viewNo;
	}

	public void setViewNo(String viewNo) {
		this.viewNo = viewNo;
	}

	public String getCrudGbn() {
		return crudGbn;
	}

	public void setCrudGbn(String crudGbn) {
		this.crudGbn = crudGbn;
	}

	public String getMwNo() {
		return mwNo;
	}

	public void setMwNo(String mwNo) {
		this.mwNo = mwNo;
	}

	public String getRantNo() {
		return rantNo;
	}

	public void setRantNo(String rantNo) {
		this.rantNo = rantNo;
	}

	public String getViewRspn() {
		return viewRspn;
	}

	public void setViewRspn(String viewRspn) {
		this.viewRspn = viewRspn;
	}

	public String getDescRspn() {
		return descRspn;
	}

	public void setDescRspn(String descRspn) {
		this.descRspn = descRspn;
	}

	public String getOrd() {
		return ord;
	}

	public void setOrd(String ord) {
		this.ord = ord;
	}

	public String getViewNm() {
		return viewNm;
	}

	public void setViewNm(String viewNm) {
		this.viewNm = viewNm;
	}

	public String getScr() {
		return scr;
	}

	public void setScr(String scr) {
		this.scr = scr;
	}

	public String getRnk() {
		return rnk;
	}

	public void setRnk(String rnk) {
		this.rnk = rnk;
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

	public String getCstNo() {
		return cstNo;
	}

	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public String getPercnt() {
		return percnt;
	}

	public void setPercnt(String percnt) {
		this.percnt = percnt;
	}

	public String getVocId() {
		return vocId;
	}

	public void setVocId(String vocId) {
		this.vocId = vocId;
	}

	public String getAnswYn() {
		return answYn;
	}

	public void setAnswYn(String answYn) {
		this.answYn = answYn;
	}

	public String getRn() {
		return rn;
	}

	public void setRn(String rn) {
		this.rn = rn;
	}

	public String getLev() {
		return lev;
	}

	public void setLev(String lev) {
		this.lev = lev;
	}

	public String getProbNm() {
		return probNm;
	}

	public void setProbNm(String probNm) {
		this.probNm = probNm;
	}

	public String getPreOrd() {
		return preOrd;
	}

	public void setPreOrd(String preOrd) {
		this.preOrd = preOrd;
	}

	public String getSchTmplNo() {
		return schTmplNo;
	}

	public void setSchTmplNo(String schTmplNo) {
		this.schTmplNo = schTmplNo;
	}

	public String getSchProbNo() {
		return schProbNo;
	}

	public void setSchProbNo(String schProbNo) {
		this.schProbNo = schProbNo;
	}

	public String getSchViewType() {
		return schViewType;
	}

	public void setSchViewType(String schViewType) {
		this.schViewType = schViewType;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getAllcnt() {
		return allcnt;
	}

	public void setAllcnt(String allcnt) {
		this.allcnt = allcnt;
	}

	public String getSchGubun() {
		return schGubun;
	}

	public void setSchGubun(String schGubun) {
		this.schGubun = schGubun;
	}

	public String getSchStartDd() {
		return schStartDd;
	}

	public void setSchStartDd(String schStartDd) {
		this.schStartDd = schStartDd;
	}

	public String getSchEndDd() {
		return schEndDd;
	}

	public void setSchEndDd(String schEndDd) {
		this.schEndDd = schEndDd;
	}
    
}