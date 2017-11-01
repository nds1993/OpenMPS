package nds.mpm.sales.SD0401.vo;

/**
 * @Class Name : MpOrderHVO.java
 * @Description : MpOrderH VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class SD0401VO extends SD0401DefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** delv_date */
    private java.lang.String delvDate;
    
    /** ordr_no */
    private java.lang.String ordrNo;
    
    /** ordr_type */
    private java.lang.String ordrType;
    
    /** ordr_cust */
    private java.lang.String ordrCust;
    
    /** delv_cust */
    private java.lang.String delvCust;
    
    /** sale_cust */
    private java.lang.String saleCust;
    
    /** aggre_gubn */
    private java.lang.String aggreGubn;
    
    /** delv_type */
    private java.lang.String delvType;
    
    /** delv_dc */
    private java.lang.String delvDc;
    
    /** amt_display */
    private java.lang.String amtDisplay;
    
    /** limit_yn */
    private java.lang.String limitYn;
    
    /** occur_src */
    private java.lang.String occurSrc;
    
    /** appro_yn */
    private java.lang.String approYn;
    
    /** ordr_box */
    private java.math.BigDecimal ordrWeight;
    
    /** ordr_amt */
    private java.math.BigDecimal ordrAmt;
    
    /** delv_box */
    private java.math.BigDecimal delvWeight;
    
    /** delv_amt */
    private java.math.BigDecimal delvAmt;
    
    /** phone_no */
    private java.lang.String phoneNo;
    
    /** arrival_time */
    private java.sql.Timestamp arrivalTime;
    
    /** memo */
    private java.lang.String memo;
    
    /** status */
    private java.lang.String status;
    
    /** cr_user */
    private java.lang.String crUser;
    
    /** cr_date */
    private java.sql.Timestamp crDate;
    
    /** md_user */
    private java.lang.String mdUser;
    
    /** md_date */
    private java.sql.Timestamp mdDate;
    
    public java.lang.String getCorpCode() {
        return this.corpCode;
    }
    
    public void setCorpCode(java.lang.String corpCode) {
        this.corpCode = corpCode;
    }
    
    public java.lang.String getDelvDate() {
        return this.delvDate;
    }
    
    public void setDelvDate(java.lang.String delvDate) {
        this.delvDate = delvDate;
    }
    
    public java.lang.String getOrdrNo() {
        return this.ordrNo;
    }
    
    public void setOrdrNo(java.lang.String ordrNo) {
        this.ordrNo = ordrNo;
    }
    
    public java.lang.String getOrdrType() {
        return this.ordrType;
    }
    
    public void setOrdrType(java.lang.String ordrType) {
        this.ordrType = ordrType;
    }
    
    public java.lang.String getOrdrCust() {
        return this.ordrCust;
    }
    
    public void setOrdrCust(java.lang.String ordrCust) {
        this.ordrCust = ordrCust;
    }
    
    public java.lang.String getDelvCust() {
        return this.delvCust;
    }
    
    public void setDelvCust(java.lang.String delvCust) {
        this.delvCust = delvCust;
    }
    
    public java.lang.String getSaleCust() {
        return this.saleCust;
    }
    
    public void setSaleCust(java.lang.String saleCust) {
        this.saleCust = saleCust;
    }
    
    public java.lang.String getAggreGubn() {
        return this.aggreGubn;
    }
    
    public void setAggreGubn(java.lang.String aggreGubn) {
        this.aggreGubn = aggreGubn;
    }
    
    public java.lang.String getDelvType() {
        return this.delvType;
    }
    
    public void setDelvType(java.lang.String delvType) {
        this.delvType = delvType;
    }
    
    public java.lang.String getDelvDc() {
        return this.delvDc;
    }
    
    public void setDelvDc(java.lang.String delvDc) {
        this.delvDc = delvDc;
    }
    
    public java.lang.String getAmtDisplay() {
        return this.amtDisplay;
    }
    
    public void setAmtDisplay(java.lang.String amtDisplay) {
        this.amtDisplay = amtDisplay;
    }
    
    public java.lang.String getLimitYn() {
        return this.limitYn;
    }
    
    public void setLimitYn(java.lang.String limitYn) {
        this.limitYn = limitYn;
    }
    
    public java.lang.String getOccurSrc() {
        return this.occurSrc;
    }
    
    public void setOccurSrc(java.lang.String occurSrc) {
        this.occurSrc = occurSrc;
    }
    
    public java.lang.String getApproYn() {
        return this.approYn;
    }
    
    public void setApproYn(java.lang.String approYn) {
        this.approYn = approYn;
    }
    
    
    public java.math.BigDecimal getOrdrAmt() {
        return this.ordrAmt;
    }
    
    public void setOrdrAmt(java.math.BigDecimal ordrAmt) {
        this.ordrAmt = ordrAmt;
    }
    
    
    public java.math.BigDecimal getOrdrWeight() {
		return ordrWeight;
	}

	public void setOrdrWeight(java.math.BigDecimal ordrWeight) {
		this.ordrWeight = ordrWeight;
	}

	public java.math.BigDecimal getDelvWeight() {
		return delvWeight;
	}

	public void setDelvWeight(java.math.BigDecimal delvWeight) {
		this.delvWeight = delvWeight;
	}

	public java.math.BigDecimal getDelvAmt() {
        return this.delvAmt;
    }
    
    public void setDelvAmt(java.math.BigDecimal delvAmt) {
        this.delvAmt = delvAmt;
    }
    
    public java.lang.String getPhoneNo() {
        return this.phoneNo;
    }
    
    public void setPhoneNo(java.lang.String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    public java.sql.Timestamp getArrivalTime() {
        return this.arrivalTime;
    }
    
    public void setArrivalTime(java.sql.Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    
    public java.lang.String getMemo() {
        return this.memo;
    }
    
    public void setMemo(java.lang.String memo) {
        this.memo = memo;
    }
    
    public java.lang.String getStatus() {
        return this.status;
    }
    
    public void setStatus(java.lang.String status) {
        this.status = status;
    }
    
    public java.lang.String getCrUser() {
        return this.crUser;
    }
    
    public void setCrUser(java.lang.String crUser) {
        this.crUser = crUser;
    }
    
    public java.sql.Timestamp getCrDate() {
        return this.crDate;
    }
    
    public void setCrDate(java.sql.Timestamp crDate) {
        this.crDate = crDate;
    }
    
    public java.lang.String getMdUser() {
        return this.mdUser;
    }
    
    public void setMdUser(java.lang.String mdUser) {
        this.mdUser = mdUser;
    }
    
    public java.sql.Timestamp getMdDate() {
        return this.mdDate;
    }
    
    public void setMdDate(java.sql.Timestamp mdDate) {
        this.mdDate = mdDate;
    }

}
