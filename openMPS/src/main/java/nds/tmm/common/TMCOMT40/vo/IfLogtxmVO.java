package nds.tmm.common.TMCOMT40.vo;

/**
 * @Class Name : IfLogtxmVO.java
 * @Description : IfLogtxm VO class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.08.14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class IfLogtxmVO extends IfLogtxmDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;

    /** frst_date */
    private java.lang.String frstDate;

    /** last_date */
    private java.lang.String lastDate;
    
    /** logt_seqq */
    private java.lang.Integer logtSeqq;
    
    /** offi_syst */
    private java.lang.String offiSyst;
    
    /** intr_usid */
    private java.lang.String intrUsid;
    
    /** intr_name */
    private java.lang.String intrName;

    /** result_code */
    private java.lang.String resultCode;
    
    /** result_msg */
//    private java.lang.String result_msg;
    private java.lang.String resultMsg;
    
    /** intr_date */
//    private java.sql.Timestamp intr_date;
    private java.sql.Timestamp intrDate;

	public java.lang.Integer getLogtSeqq() {
		return logtSeqq;
	}

	public void setLogtSeqq(java.lang.Integer logtSeqq) {
		this.logtSeqq = logtSeqq;
	}

	public java.lang.String getOffiSyst() {
		return offiSyst;
	}

	public void setOffiSyst(java.lang.String offiSyst) {
		this.offiSyst = offiSyst;
	}

	public java.lang.String getIntrUsid() {
		return intrUsid;
	}

	public void setIntrUsid(java.lang.String intrUsid) {
		this.intrUsid = intrUsid;
	}

	public java.lang.String getIntrName() {
		return intrName;
	}

	public void setIntrName(java.lang.String intrName) {
		this.intrName = intrName;
	}

	public java.lang.String getResultCode() {
		return resultCode;
	}

	public void setResultCode(java.lang.String resultCode) {
		this.resultCode = resultCode;
	}

	public java.lang.String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(java.lang.String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public java.sql.Timestamp getIntrDate() {
		return intrDate;
	}

	public void setIntrDate(java.sql.Timestamp intrDate) {
		this.intrDate = intrDate;
	}

	public java.lang.String getCorpCode() {
		return corpCode;
	}

	public void setCorpCode(java.lang.String corpCode) {
		this.corpCode = corpCode;
	}

	public java.lang.String getFrstDate() {
		return frstDate;
	}

	public void setFrstDate(java.lang.String frstDate) {
		this.frstDate = frstDate;
	}

	public java.lang.String getLastDate() {
		return lastDate;
	}

	public void setLastDate(java.lang.String lastDate) {
		this.lastDate = lastDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
