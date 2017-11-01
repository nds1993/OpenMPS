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
package nds.core.logsearch.loginlog.service;

import nds.core.common.common.service.CommonObject;

public class LoginLogVO extends CommonObject {
    
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
    
    
    private String preCnctYn;

    private String cnctLogNo;

    private String userId;

    private String cnctPath;

    private String cnctStrtDd;

    private String cnctEndDd;

    private String ipPath;

    private String cnctYn;
    
    private String loginYn;
    
    private String schStartDd;
    
    private String schEndDd;
    
    private String userNm;
    
    private String sidx;
    
    private String sord;
    
    private String depCd;
    
    private String depNm;
    
    
    public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getDepCd() {
		return depCd;
	}

	public void setDepCd(String depCd) {
		this.depCd = depCd;
	}

	public String getDepNm() {
		return depNm;
	}

	public void setDepNm(String depNm) {
		this.depNm = depNm;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
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

	public String getLoginYn() {
		return loginYn;
	}

	public void setLoginYn(String loginYn) {
		this.loginYn = loginYn;
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

    public String getCnctLogNo() {
        return cnctLogNo;
    }

    public void setCnctLogNo(String cnctLogNo) {
        this.cnctLogNo = cnctLogNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCnctPath() {
        return cnctPath;
    }

    public void setCnctPath(String cnctPath) {
        this.cnctPath = cnctPath;
    }

    public String getCnctStrtDd() {
        return cnctStrtDd;
    }

    public void setCnctStrtDd(String cnctStrtDd) {
        this.cnctStrtDd = cnctStrtDd;
    }

    public String getCnctEndDd() {
        return cnctEndDd;
    }

    public void setCnctEndDd(String cnctEndDd) {
        this.cnctEndDd = cnctEndDd;
    }

    public String getIpPath() {
        return ipPath;
    }

    public void setIpPath(String ipPath) {
        this.ipPath = ipPath;
    }

    public String getCnctYn() {
        return cnctYn;
    }

    public void setCnctYn(String cnctYn) {
        this.cnctYn = cnctYn;
    }

    public String getPreCnctYn() {
        return preCnctYn;
    }

    public void setPreCnctYn(String preCnctYn) {
        this.preCnctYn = preCnctYn;
    }
}