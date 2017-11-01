/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-7-3 10:21:43
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-7-3 10:21:43
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.operation.voccnsltype.service;

import nds.core.common.common.service.CommonObject;

public class VocCnslTypeVO extends CommonObject {
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

    private String lcls;

    private String mcls;

    private String scls;
    
    private String lvl;

    private String lclsYn;
    
    private String cnslCatNm;
    
    private String lclsCnslCatNm;
    
    private String mclsCnslCatNm;
    
    private String sclsCnslCatNm;

    private String useYn;

    private String tsrUseYn;

    private String csrUseYn;

    private String cnsl01;

    private String cnsl02;

    private String cnsl03;

    private String cnsl04;

    private String cnsl05;

    private String regUser;

    private String regDttm;

    private String updtUser;

    private String updtDttm;
    
    private String ord;

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

    public String getLcls() {
		return lcls;
	}

	public void setLcls(String lcls) {
		this.lcls = lcls;
	}

	public String getMcls() {
		return mcls;
	}

	public void setMcls(String mcls) {
		this.mcls = mcls;
	}

	public String getScls() {
		return scls;
	}

	public void setScls(String scls) {
		this.scls = scls;
	}

	public String getLvl() {
        return lvl;
    }

    public void setLvl(String lvl) {
        this.lvl = lvl;
    }

    public String getLclsYn() {
		return lclsYn;
	}

	public void setLclsYn(String lclsYn) {
		this.lclsYn = lclsYn;
	}

	public String getCnslCatNm() {
        return cnslCatNm;
    }

    public void setCnslCatNm(String cnslCatNm) {
        this.cnslCatNm = cnslCatNm;
    }
    
    public String getLclsCnslCatNm() {
        return lclsCnslCatNm;
    }
    
    public void setLclsCnslCatNm(String lclsCnslCatNm) {
        this.lclsCnslCatNm = lclsCnslCatNm;
    }
    
    public String getMclsCnslCatNm() {
        return mclsCnslCatNm;
    }
    
    public void setMclsCnslCatNm(String mclsCnslCatNm) {
        this.mclsCnslCatNm = mclsCnslCatNm;
    }
    
    public String getSclsCnslCatNm() {
        return sclsCnslCatNm;
    }

    public void setSclsCnslCatNm(String sclsCnslCatNm) {
        this.sclsCnslCatNm = sclsCnslCatNm;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getTsrUseYn() {
        return tsrUseYn;
    }

    public void setTsrUseYn(String tsrUseYn) {
        this.tsrUseYn = tsrUseYn;
    }

    public String getCsrUseYn() {
        return csrUseYn;
    }

    public void setCsrUseYn(String csrUseYn) {
        this.csrUseYn = csrUseYn;
    }

    public String getCnsl01() {
        return cnsl01;
    }

    public void setCnsl01(String cnsl01) {
        this.cnsl01 = cnsl01;
    }

    public String getCnsl02() {
        return cnsl02;
    }

    public void setCnsl02(String cnsl02) {
        this.cnsl02 = cnsl02;
    }

    public String getCnsl03() {
        return cnsl03;
    }

    public void setCnsl03(String cnsl03) {
        this.cnsl03 = cnsl03;
    }

    public String getCnsl04() {
        return cnsl04;
    }

    public void setCnsl04(String cnsl04) {
        this.cnsl04 = cnsl04;
    }

    public String getCnsl05() {
        return cnsl05;
    }

    public void setCnsl05(String cnsl05) {
        this.cnsl05 = cnsl05;
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

	public String getOrd() {
		return ord;
	}

	public void setOrd(String ord) {
		this.ord = ord;
	}
    
}