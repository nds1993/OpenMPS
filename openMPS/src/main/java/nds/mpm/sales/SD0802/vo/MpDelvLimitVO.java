package nds.mpm.sales.SD0802.vo;

/**
 * @Class Name : MpDelvLimitVO.java
 * @Description : MpDelvLimit VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpDelvLimitVO extends MpDelvLimitDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** start_date */
    private java.lang.String startDate;
    
    /** last_date */
    private java.lang.String lastDate;
    
    /** dc_code */
    private java.lang.String dcCode;
    
    /** pro_code */
    private java.lang.String proCode;
    
    /** limit_box */
    private java.math.BigDecimal limitBox;
    
    /** md_user */
    private java.lang.String mdUser;
    
    /** md_date */
    private java.sql.Timestamp mdDate;
    
    /** cr_user */
    private java.lang.String crUser;
    
    /** cr_date */
    private java.sql.Timestamp crDate;
    
    private String applyMonth;
    
    
    
    public String getApplyMonth() {
		return applyMonth;
	}

	public void setApplyMonth(String applyMonth) {
		this.applyMonth = applyMonth;
	}

	public java.lang.String getCorpCode() {
        return this.corpCode;
    }
    
    public void setCorpCode(java.lang.String corpCode) {
        this.corpCode = corpCode;
    }
    
    public java.lang.String getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(java.lang.String startDate) {
        this.startDate = startDate;
    }
    
    public java.lang.String getLastDate() {
        return this.lastDate;
    }
    
    public void setLastDate(java.lang.String lastDate) {
        this.lastDate = lastDate;
    }
    
    public java.lang.String getDcCode() {
        return this.dcCode;
    }
    
    public void setDcCode(java.lang.String dcCode) {
        this.dcCode = dcCode;
    }
    
    public java.lang.String getProCode() {
        return this.proCode;
    }
    
    public void setProCode(java.lang.String proCode) {
        this.proCode = proCode;
    }
    
    public java.math.BigDecimal getLimitBox() {
        return this.limitBox;
    }
    
    public void setLimitBox(java.math.BigDecimal limitBox) {
        this.limitBox = limitBox;
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
    
}
