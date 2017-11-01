package nds.mpm.sales.SD0204.vo;

/**
 * @Class Name : MpCustPriClassVO.java
 * @Description : MpCustPriClass VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpCustPriClassVO extends MpCustPriClassDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** cust_code */
    private java.lang.String custCode;
    
    /** pro_code */
    private java.lang.String proCode;
    
    /** price_class */
    private java.lang.String priceClass;
    
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
    
    public java.lang.String getProCode() {
        return this.proCode;
    }
    
    public void setProCode(java.lang.String proCode) {
        this.proCode = proCode;
    }
    
    public java.lang.String getPriceClass() {
        return this.priceClass;
    }
    
    public void setPriceClass(java.lang.String priceClass) {
        this.priceClass = priceClass;
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
