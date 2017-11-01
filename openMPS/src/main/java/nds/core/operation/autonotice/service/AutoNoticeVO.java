package nds.core.operation.autonotice.service;

import nds.core.common.common.service.CommonObject;


public class AutoNoticeVO extends CommonObject{
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
    
    private String procStat;
    private String inoutDvn;
    private String smsUseYn;
    private String emailUseYn;
    private String msgrUseYn;
    private String useYn;
    private String regUser;
    private String regDttm;
    private String updtUser;
    private String updtDttm;
    private String tgtUser;
    
    private String confNo;
    private String procStatCd;
    private String inoutDvnCd;
    private String tgtUserCd;
    
    private String typeDvn;
    
	public String getProcStatCd() {
		return procStatCd;
	}
	public void setProcStatCd(String procStatCd) {
		this.procStatCd = procStatCd;
	}
	public String getInoutDvnCd() {
		return inoutDvnCd;
	}
	public void setInoutDvnCd(String inoutDvnCd) {
		this.inoutDvnCd = inoutDvnCd;
	}
	public String getTgtUserCd() {
		return tgtUserCd;
	}
	public void setTgtUserCd(String tgtUserCd) {
		this.tgtUserCd = tgtUserCd;
	}
	public String getConfNo() {
		return confNo;
	}
	public void setConfNo(String confNo) {
		this.confNo = confNo;
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
	public String getProcStat() {
		return procStat;
	}
	public void setProcStat(String procStat) {
		this.procStat = procStat;
	}
	public String getInoutDvn() {
		return inoutDvn;
	}
	public void setInoutDvn(String inoutDvn) {
		this.inoutDvn = inoutDvn;
	}
	public String getSmsUseYn() {
		return smsUseYn;
	}
	public void setSmsUseYn(String smsUseYn) {
		this.smsUseYn = smsUseYn;
	}
	public String getEmailUseYn() {
		return emailUseYn;
	}
	public void setEmailUseYn(String emailUseYn) {
		this.emailUseYn = emailUseYn;
	}
	public String getMsgrUseYn() {
		return msgrUseYn;
	}
	public void setMsgrUseYn(String msgrUseYn) {
		this.msgrUseYn = msgrUseYn;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	public String getTgtUser() {
		return tgtUser;
	}
	public void setTgtUser(String tgtUser) {
		this.tgtUser = tgtUser;
	}
	public String getTypeDvn() {
		return typeDvn;
	}
	public void setTypeDvn(String typeDvn) {
		this.typeDvn = typeDvn;
	}
    
    
    
}
