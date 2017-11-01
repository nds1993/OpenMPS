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

public class SrvyProbVO extends CommonObject {
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
    
    //처리구분
    private String crudGbn;
    
    private String ord;
    
    private String viewType;
    
    private String viewCnt;
    
    private String answYn;
    
    private String upLineNo;

    private String probNm;

    private String regUser;

    private String regDttm;

    private String updtUser;

    private String updtDttm;

    private String cnt;
    
    private String lev;
    
    private String schTmplNo;
    private String schProbNo;
    private String orderBy;
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
	public String getCrudGbn() {
		return crudGbn;
	}
	public void setCrudGbn(String crudGbn) {
		this.crudGbn = crudGbn;
	}
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
	}
	public String getViewType() {
		return viewType;
	}
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	public String getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(String viewCnt) {
		this.viewCnt = viewCnt;
	}
	public String getAnswYn() {
		return answYn;
	}
	public void setAnswYn(String answYn) {
		this.answYn = answYn;
	}
	public String getUpLineNo() {
		return upLineNo;
	}
	public void setUpLineNo(String upLineNo) {
		this.upLineNo = upLineNo;
	}
	public String getProbNm() {
		return probNm;
	}
	public void setProbNm(String probNm) {
		this.probNm = probNm;
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
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public String getLev() {
		return lev;
	}
	public void setLev(String lev) {
		this.lev = lev;
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
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}