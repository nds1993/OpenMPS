package nds.core.logsearch.servicelog.service;

import nds.core.common.common.service.CommonObject;

public class ServiceLogVO extends CommonObject{
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
    
    private String strtDttm;
    
    private String endDttm;
    
    private String scsYn;
    
    private String wrkDvn;
    
    private String procNm;
    
    private String procDescrpt;
    
    private String errCd;
    
    private String errCntn;
    
    private String rmrk;
    
    private String regUser;
    
    private String regDd;
    
    private String schStartDd;
    
    private String schEndDd;
    
    private String userEmpNo;
    
    private String depNo;
    
    private String schProcNm;
    
    private String sidx;
    
    private String sord;
    
    private String userNm;
    
    
    
	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

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

	public String getStrtDttm() {
		return strtDttm;
	}

	public void setStrtDttm(String strtDttm) {
		this.strtDttm = strtDttm;
	}

	public String getEndDttm() {
		return endDttm;
	}

	public void setEndDttm(String endDttm) {
		this.endDttm = endDttm;
	}

	public String getScsYn() {
		return scsYn;
	}

	public void setScsYn(String scsYn) {
		this.scsYn = scsYn;
	}

	public String getWrkDvn() {
		return wrkDvn;
	}

	public void setWrkDvn(String wrkDvn) {
		this.wrkDvn = wrkDvn;
	}

	public String getProcNm() {
		return procNm;
	}

	public void setProcNm(String procNm) {
		this.procNm = procNm;
	}

	public String getProcDescrpt() {
		return procDescrpt;
	}

	public void setProcDescrpt(String procDescrpt) {
		this.procDescrpt = procDescrpt;
	}

	public String getErrCd() {
		return errCd;
	}

	public void setErrCd(String errCd) {
		this.errCd = errCd;
	}

	public String getErrCntn() {
		return errCntn;
	}

	public void setErrCntn(String errCntn) {
		this.errCntn = errCntn;
	}

	public String getRmrk() {
		return rmrk;
	}

	public void setRmrk(String rmrk) {
		this.rmrk = rmrk;
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

	public String getUserEmpNo() {
		return userEmpNo;
	}

	public void setUserEmpNo(String userEmpNo) {
		this.userEmpNo = userEmpNo;
	}

	public String getDepNo() {
		return depNo;
	}

	public void setDepNo(String depNo) {
		this.depNo = depNo;
	}

	public String getSchProcNm() {
		return schProcNm;
	}

	public void setSchProcNm(String schProcNm) {
		this.schProcNm = schProcNm;
	}
}
