/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2009-8-30 12:25:7
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2009-8-30 12:25:7
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.common.common.service;

public class SkinMstVO extends CommonObject {
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
    
    
    
    private String baseSkin;
    
    

    private String skinNo;

    private String skinName;

    private String skinDescrpt;

    private String siteTit;

    private String siteDomn;

    private String coName;

    private String chmnName;

    private String chmnTel;

    private String pathView;

    private String pathImge;

    private String pathCss;

    private String regUser;

    private String regDd;

    private String updtUser;

    private String updtDd;

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

    public String getSkinNo() {
        return skinNo;
    }

    public void setSkinNo(String skinNo) {
        this.skinNo = skinNo;
    }

    public String getSkinName() {
        return skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    public String getSkinDescrpt() {
        return skinDescrpt;
    }

    public void setSkinDescrpt(String skinDescrpt) {
        this.skinDescrpt = skinDescrpt;
    }

    public String getSiteTit() {
        return siteTit;
    }

    public void setSiteTit(String siteTit) {
        this.siteTit = siteTit;
    }

    public String getSiteDomn() {
        return siteDomn;
    }

    public void setSiteDomn(String siteDomn) {
        this.siteDomn = siteDomn;
    }

    public String getCoName() {
        return coName;
    }

    public void setCoName(String coName) {
        this.coName = coName;
    }

    public String getChmnName() {
        return chmnName;
    }

    public void setChmnName(String chmnName) {
        this.chmnName = chmnName;
    }

    public String getChmnTel() {
        return chmnTel;
    }

    public void setChmnTel(String chmnTel) {
        this.chmnTel = chmnTel;
    }

    public String getPathView() {
        return pathView;
    }

    public void setPathView(String pathView) {
        this.pathView = pathView;
    }

    public String getPathImge() {
        return pathImge;
    }

    public void setPathImge(String pathImge) {
        this.pathImge = pathImge;
    }

    public String getPathCss() {
        return pathCss;
    }

    public void setPathCss(String pathCss) {
        this.pathCss = pathCss;
    }

    public String getRegUser() {
        return regUser;
    }

    public void setRegUser(String regUser) {
        this.regUser = regUser;
    }

    public String getRegDd() {
        return regDd;
    }

    public void setRegDd(String regDd) {
        this.regDd = regDd;
    }

    public String getUpdtUser() {
        return updtUser;
    }

    public void setUpdtUser(String updtUser) {
        this.updtUser = updtUser;
    }

    public String getUpdtDd() {
        return updtDd;
    }

    public void setUpdtDd(String updtDd) {
        this.updtDd = updtDd;
    }
    
    
    public String getBaseSkin() {
        return baseSkin;
    }

    public void setBaseSkin(String baseSkin) {
        this.baseSkin = baseSkin;
    }
}