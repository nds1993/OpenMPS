/**
*
* Copyright (C) 2007 HumanIT. All Rights
*
* Created on   : 2009-9-10 16:36:34
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* CHANGE REVISION
* ------------------------------
* AUTHOR   : HumanIT
* DATE     : 2009-9-10 16:36:34
* REVISION : 1.0   First release.
* -------------------------------
* CLASS DESCRIPTION
* -------------------------------
*
*/
package nds.core.operation.surveytmpl.service;

import nds.core.common.common.service.CommonObject;

public class SurveyVocVO extends CommonObject{
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
     * Survey VOC Result VO
     */
    private String vocId;
    
    private String receDttm;
    
    private String needsType;
    
    private String cstNm;
    
    private String regChnl;
    
    private String chrgUserNm;
    
    private String chrgDepNm;
    
    private String answDttm;
    
    private String qstnTit;
    
    
    /**
     * Survey VOC Search VO
     */
    private String tmplNo;
    
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

	public String getVocId() {
		return vocId;
	}

	public void setVocId(String vocId) {
		this.vocId = vocId;
	}

	public String getReceDttm() {
		return receDttm;
	}

	public void setReceDttm(String receDttm) {
		this.receDttm = receDttm;
	}

	public String getNeedsType() {
		return needsType;
	}

	public void setNeedsType(String needsType) {
		this.needsType = needsType;
	}

	public String getCstNm() {
		return cstNm;
	}

	public void setCstNm(String cstNm) {
		this.cstNm = cstNm;
	}

	public String getRegChnl() {
		return regChnl;
	}

	public void setRegChnl(String regChnl) {
		this.regChnl = regChnl;
	}

	public String getChrgUserNm() {
		return chrgUserNm;
	}

	public void setChrgUserNm(String chrgUserNm) {
		this.chrgUserNm = chrgUserNm;
	}

	public String getChrgDepNm() {
		return chrgDepNm;
	}

	public void setChrgDepNm(String chrgDepNm) {
		this.chrgDepNm = chrgDepNm;
	}

	public String getAnswDttm() {
		return answDttm;
	}

	public void setAnswDttm(String answDttm) {
		this.answDttm = answDttm;
	}

	public String getQstnTit() {
		return qstnTit;
	}

	public void setQstnTit(String qstnTit) {
		this.qstnTit = qstnTit;
	}

	public String getTmplNo() {
		return tmplNo;
	}

	public void setTmplNo(String tmplNo) {
		this.tmplNo = tmplNo;
	}

}