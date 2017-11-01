package nds.mpm.sales.SD0101.vo;

/**
 * @Class Name : MpCreditLimitVO.java
 * @Description : MpCreditLimit VO class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpCreditLimitVO extends MpCreditLimitDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** cust_code */
    private java.lang.String custCode;
    
    /** seq_no */
    private java.lang.String seqNo;
    
    /** start_date */
    private java.sql.Timestamp startDate;
    
    /** last_date */
    private java.sql.Timestamp lastDate;
    
    /** base_limit */
    private java.math.BigDecimal baseLimit;
    
    /** one_limit */
    private java.math.BigDecimal oneLimit;
    
    /** temp_limit */
    private java.math.BigDecimal tempLimit;
    
    /** cr_user */
    private java.lang.String crUser;
    
    /** cr_time */
    private java.sql.Timestamp crDate;
    
    /** md_user */
    private java.lang.String mdUser;
    
    /** md_time */
    private java.sql.Timestamp mdDate;
    
    public java.lang.String getCorpCode() {
        return this.corpCode;
    }
    
    public void setCorpCode(java.lang.String corpCode) {
        this.corpCode = corpCode;
    }
    
    public java.lang.String getCustCode() {
        return this.custCode;
    }
    
    public void setCustCode(java.lang.String custCode) {
        this.custCode = custCode;
    }
    
    public java.lang.String getSeqNo() {
        return this.seqNo;
    }
    
    public void setSeqNo(java.lang.String seqNo) {
        this.seqNo = seqNo;
    }
    
    public java.sql.Timestamp getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(java.sql.Timestamp startDate) {
        this.startDate = startDate;
    }
    
    public java.sql.Timestamp getLastDate() {
        return this.lastDate;
    }
    
    public void setLastDate(java.sql.Timestamp lastDate) {
        this.lastDate = lastDate;
    }
    
    public java.math.BigDecimal getBaseLimit() {
        return this.baseLimit;
    }
    
    public void setBaseLimit(java.math.BigDecimal baseLimit) {
        this.baseLimit = baseLimit;
    }
    
    public java.math.BigDecimal getOneLimit() {
        return this.oneLimit;
    }
    
    public void setOneLimit(java.math.BigDecimal oneLimit) {
        this.oneLimit = oneLimit;
    }
    
    public java.math.BigDecimal getTempLimit() {
        return this.tempLimit;
    }
    
    public void setTempLimit(java.math.BigDecimal tempLimit) {
        this.tempLimit = tempLimit;
    }
    
    public java.lang.String getCrUser() {
        return this.crUser;
    }
    
    public void setCrUser(java.lang.String crUser) {
        this.crUser = crUser;
    }
    
    
    public java.sql.Timestamp getCrDate() {
		return crDate;
	}

	public void setCrDate(java.sql.Timestamp crDate) {
		this.crDate = crDate;
	}

	public java.sql.Timestamp getMdDate() {
		return mdDate;
	}

	public void setMdDate(java.sql.Timestamp mdDate) {
		this.mdDate = mdDate;
	}

	public java.lang.String getMdUser() {
        return this.mdUser;
    }
    
    public void setMdUser(java.lang.String mdUser) {
        this.mdUser = mdUser;
    }
    
    
    
}
