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
package nds.core.common.approvalhandling.service;

import nds.core.common.common.service.CommonObject;

public class ApprovalHandlingVO extends CommonObject {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * main searchVO & resultVO
     */
    private String userId;
    
    private String widgetsId;   // searchVO
    
    private String widgetsNm;
    
    private String conCd;
    
    private String conNm;
    
    private String graphBaseCd; // searchVO
    
    private String graphRang;   // searchVO
    
    private String yn;
    
    private String rangValue;
    
    private String baseValue;

    private String regUser;

    private String regDttm;

    private String updtUser;

    private String updtDttm;
    
    private String sortNo;
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWidgetsId() {
        return widgetsId;
    }
    
    public void setWidgetsId(String widgetsId) {
        this.widgetsId = widgetsId;
    }
    
    
    public String getWidgetsNm() {
        return widgetsNm;
    }

    
    public void setWidgetsNm(String widgetsNm) {
        this.widgetsNm = widgetsNm;
    }
    
    public String getConCd() {
        return conCd;
    }
    
    public void setConCd(String conCd) {
        this.conCd = conCd;
    }

    public String getConNm() {
        return conNm;
    }

    
    public void setConNm(String conNm) {
        this.conNm = conNm;
    }

    public String getGraphBaseCd() {
        return graphBaseCd;
    }

    public void setGraphBaseCd(String graphBaseCd) {
        this.graphBaseCd = graphBaseCd;
    }

    public String getGraphRang() {
        return graphRang;
    }

    public void setGraphRang(String graphRang) {
        this.graphRang = graphRang;
    }

    
    public String getYn() {
        return yn;
    }

    
    public void setYn(String yn) {
        this.yn = yn;
    }

    
    public String getRangValue() {
        return rangValue;
    }

    
    public void setRangValue(String rangValue) {
        this.rangValue = rangValue;
    }

    
    public String getBaseValue() {
        return baseValue;
    }

    
    public void setBaseValue(String baseValue) {
        this.baseValue = baseValue;
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

    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
    }

    public String getSortNo() {
        return sortNo;
    }
    
}