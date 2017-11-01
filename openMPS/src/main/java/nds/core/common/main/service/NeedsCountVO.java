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

public class NeedsCountVO extends CommonObject {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    /**
     * VOC 현황 searchVO
     */
    private String UserId;
    
    private String DepCd;
    
    private String dvnYM;

    /**
     * VOC 현황 resultVO
     */
    private String needsTypeCd;
    
    private String needsTypeNm;
    
    private int needsCnt;
    
    private int needsNotCnt;
    
    private String needsTotCnt;
    
    
    /**
     * 웨젯 공통 설정VO
     */
    private String graphColor;

    
    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getDepCd() {
        return DepCd;
    }
    
    public void setDepCd(String depCd) {
        DepCd = depCd;
    }

    public String getNeedsTypeCd() {
        return needsTypeCd;
    }
    
    public void setNeedsTypeCd(String needsTypeCd) {
        this.needsTypeCd = needsTypeCd;
    }
    
    public String getNeedsTypeNm() {
        return needsTypeNm;
    }
    
    public void setNeedsTypeNm(String needsTypeNm) {
        this.needsTypeNm = needsTypeNm;
    }
    
    public int getNeedsCnt() {
        return needsCnt;
    }
    
    public void setNeedsCnt(int needsCnt) {
        this.needsCnt = needsCnt;
    }

    public void setNeedsNotCnt(int needsNotCnt) {
        this.needsNotCnt = needsNotCnt;
    }

    public int getNeedsNotCnt() {
        return needsNotCnt;
    }

    public void setGraphColor(String graphColor) {
        this.graphColor = graphColor;
    }

    public String getGraphColor() {
        return graphColor;
    }

    public void setNeedsTotCnt(String needsTotCnt) {
        this.needsTotCnt = needsTotCnt;
    }

    public String getNeedsTotCnt() {
        return needsTotCnt;
    }

    public String getDvnYM() {
        return dvnYM;
    }
    
    public void setDvnYM(String dvnYM) {
        this.dvnYM = dvnYM;
    }

}