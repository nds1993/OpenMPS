package nds.core.logsearch.systemlog.service;

import nds.core.common.common.service.CommonObject;

public class SystemLogVO extends CommonObject{
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
    
    private String contsId;
    
    private String btnId;
    
    private String useCntn;
    
    private String ipPath;
    
    private String regUser;
    
    private String regDttm;
    
    private String schStartDd;
    
    private String schEndDd;
    
    private String sidx;

    private String sord;

    private String schContsId;
    
    private String userEmpNo;
    
    private String depNo;
    
    private String useLogNo;
    
    private String contsNm;
    
    
	public String getContsNm() {
		return contsNm;
	}

	public void setContsNm(String contsNm) {
		this.contsNm = contsNm;
	}

	public String getUseLogNo() {
		return useLogNo;
	}

	public void setUseLogNo(String useLogNo) {
		this.useLogNo = useLogNo;
	}

	public String getDepNo() {
		return depNo;
	}

	public void setDepNo(String depNo) {
		this.depNo = depNo;
	}

	public String getUserEmpNo() {
		return userEmpNo;
	}

	public void setUserEmpNo(String userEmpNo) {
		this.userEmpNo = userEmpNo;
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

	public String getSchContsId() {
		return schContsId;
	}

	public void setSchContsId(String schContsId) {
		this.schContsId = schContsId;
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
