package nds.mpm.sales.SD0101.vo;

/**
 * @Class Name : MpCustProLimitVO.java
 * @Description : MpCustProLimit VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpCustProLimitVO extends MpCustProLimitDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** cust_code */
    private java.lang.String custCode;
    
    /** sale_group2 */
    private java.lang.String saleGroup2;
    
    /** limit_day */
    private java.math.BigDecimal limitDay;
    
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
    
    public java.lang.String getCustCode() {
        return this.custCode;
    }
    
    public void setCustCode(java.lang.String custCode) {
        this.custCode = custCode;
    }
    
    public java.lang.String getSaleGroup2() {
        return this.saleGroup2;
    }
    
    public void setSaleGroup2(java.lang.String saleGroup2) {
        this.saleGroup2 = saleGroup2;
    }
    
    public java.math.BigDecimal getLimitDay() {
        return this.limitDay;
    }
    
    public void setLimitDay(java.math.BigDecimal limitDay) {
        this.limitDay = limitDay;
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
