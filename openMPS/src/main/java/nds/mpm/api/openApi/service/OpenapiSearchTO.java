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
package nds.mpm.api.openApi.service;


public class OpenapiSearchTO{
 
    private String startYmd;
    private String endYmd;
    private String skinYn;

    private String strtDate;
    private String lastDate;

    /* 이력제 */
    private String traceNo;
    private String optionNo;
    
    /* 등급판정결과 */
    private String issueNo;
	private String issueDate;
	
	/* 축산물 실시간 돼지도체 등급별경매현황정보 */
	private String auctDate;
    
	/* 돼지 권역별 경락가격현황 */
	private String baseYmd;
	

	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
	public String getOptionNo() {
		return optionNo;
	}
	public void setOptionNo(String optionNo) {
		this.optionNo = optionNo;
	}
	public String getStartYmd() {
        return startYmd;
    }
    public void setStartYmd(String startYmd) {
        this.startYmd = startYmd;
    }
    public String getEndYmd() {
        return endYmd;
    }
    public void setEndYmd(String endYmd) {
        this.endYmd = endYmd;
    }

    public String getSkinYn() {
        return skinYn;
    }
    public void setSkinYn(String skinYn) {
        this.skinYn = skinYn;
    }

    public String getIssueNo() {
		return issueNo;
	}
	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	/* 축산물 실시간 돼지도체 등급별경매현황정보 */
	public String getAuctDate() {
		return auctDate;
	}
	public void setAuctDate(String auctDate) {
		this.auctDate = auctDate;
	}
	
    public String getBaseYmd() {
		return baseYmd;
	}
	public void setBaseYmd(String baseYmd) {
		this.baseYmd = baseYmd;
	}
	public String getStrtDate() {
		return strtDate;
	}
	public void setStrtDate(String strtDate) {
		this.strtDate = strtDate;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

}