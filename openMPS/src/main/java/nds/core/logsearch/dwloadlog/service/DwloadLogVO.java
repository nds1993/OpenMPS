package nds.core.logsearch.dwloadlog.service;

import nds.core.common.common.service.CommonObject;

public class DwloadLogVO extends CommonObject{
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
     * 조회조건
     */
    private String schStartDd;
    private String schEndDd;
    private String userNm;
    
    private String pid;

    private String dwloadLogNo;
    private String contsId;
    private String btnId;
    private String dwCnt;
    private String useCntn;
    private String ipPath;
    private String regUser;
    private String regDttm;
    
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
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getDwloadLogNo() {
		return dwloadLogNo;
	}
	public void setDwloadLogNo(String dwloadLogNo) {
		this.dwloadLogNo = dwloadLogNo;
	}
	public String getContsId() {
		return contsId;
	}
	public void setContsId(String contsId) {
		this.contsId = contsId;
	}
	public String getBtnId() {
		return btnId;
	}
	public void setBtnId(String btnId) {
		this.btnId = btnId;
	}
	public String getDwCnt() {
		return dwCnt;
	}
	public void setDwCnt(String dwCnt) {
		this.dwCnt = dwCnt;
	}
	public String getUseCntn() {
		return useCntn;
	}
	public void setUseCntn(String useCntn) {
		this.useCntn = useCntn;
	}
	public String getIpPath() {
		return ipPath;
	}
	public void setIpPath(String ipPath) {
		this.ipPath = ipPath;
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
    
}
