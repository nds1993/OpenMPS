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

public class NeedsCompByMonVO extends CommonObject {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    /**
     * 월별 처리건수 searchVO
     */
    private String userId;
    
    private String depCd;
    
    private String dvnYM;
    
    
    /**
     * 월별 처리건수 resultVO
     */
    private String yyyymm;
    
    private String needs01;
    
    private String needs02;

    private String needs03;

    private String needsTypeCd;
    
    private String needsTypeNm;
    

    
    public String getYyyymm() {
        return yyyymm;
    }

    
    public void setYyyymm(String yyyymm) {
        this.yyyymm = yyyymm;
    }

    
    public String getNeeds01() {
        return needs01;
    }

    
    public void setNeeds01(String needs01) {
        this.needs01 = needs01;
    }

    
    public String getNeeds02() {
        return needs02;
    }

    
    public void setNeeds02(String needs02) {
        this.needs02 = needs02;
    }

    
    public String getNeeds03() {
        return needs03;
    }

    
    public void setNeeds03(String needs03) {
        this.needs03 = needs03;
    }



    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }


    
    public String getDepCd() {
        return depCd;
    }


    
    public void setDepCd(String depCd) {
        this.depCd = depCd;
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

    
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public void setDvnYM(String dvnYM) {
        this.dvnYM = dvnYM;
    }


    public String getDvnYM() {
        return dvnYM;
    }




}