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
package nds.core.common.main.service;

import nds.core.common.common.service.CommonObject;



public class MaNeedsWidgetsVO extends CommonObject {
    
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
     * 이슈VOC resultVO
     */
    private String vocId;
    
    private String qstnTit;
    
    private String asgnDd;
    
    private String dvnId;
    
    public String getQstnTit() {
		return qstnTit;
	}

	public void setQstnTit(String qstnTit) {
		this.qstnTit = qstnTit;
	}

	public String getAsgnDd() {
        return asgnDd;
    }

    
    public void setAsgnDd(String asgnDd) {
        this.asgnDd = asgnDd;
    }

    
    public String getDvnId() {
        return dvnId;
    }

    
    public void setDvnId(String dvnId) {
        this.dvnId = dvnId;
    }

    
    public static long getSerialVersionUID() {
        return serialVersionUID;
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


    public void setVocId(String vocId) {
        this.vocId = vocId;
    }


    public String getVocId() {
        return vocId;
    }


}