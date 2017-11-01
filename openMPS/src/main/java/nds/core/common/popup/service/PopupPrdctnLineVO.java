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


public class PopupPrdctnLineVO extends CommonObject {
    
    private static final long serialVersionUID = 1L;
    
    /** 
     * 공장/생산라인 resultVO
     */
    private String code;
    
    private String codeName;
    
    private String upCode;
    
    private String lvl;
    
    private String factCd;
    
    private String deptCd;
    
    private String lineCd;
    
    private String sortSeq;
    
    private String hogy;
    
    private String prodCd;
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getUpCode() {
		return upCode;
	}

	public void setUpCode(String upCode) {
		this.upCode = upCode;
	}

	public String getLvl() {
		return lvl;
	}

	public void setLvl(String lvl) {
		this.lvl = lvl;
	}

	public String getFactCd() {
		return factCd;
	}

	public void setFactCd(String factCd) {
		this.factCd = factCd;
	}

	public String getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	public String getLineCd() {
		return lineCd;
	}

	public void setLineCd(String lineCd) {
		this.lineCd = lineCd;
	}

	public String getSortSeq() {
		return sortSeq;
	}

	public void setSortSeq(String sortSeq) {
		this.sortSeq = sortSeq;
	}

	public String getHogy() {
		return hogy;
	}

	public void setHogy(String hogy) {
		this.hogy = hogy;
	}

	public String getProdCd() {
		return prodCd;
	}

	public void setProdCd(String prodCd) {
		this.prodCd = prodCd;
	}

    
    
}