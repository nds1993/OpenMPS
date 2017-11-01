package nds.mpm.sales.SD0101.vo;

/**
 * @Class Name : MpCustHistoryVO.java
 * @Description : MpCustHistory VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpCustHistoryVO extends MpCustHistoryDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** cust_code */
    private java.lang.String custCode;
    
    /** cr_date */
    private java.sql.Timestamp crDate;
    
    /** cust_document */
    private java.lang.String custDocument;
    
    /** cr_user */
    private java.lang.String crUser;
    
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
    
    public java.sql.Timestamp getCrDate() {
        return this.crDate;
    }
    
    public void setCrDate(java.sql.Timestamp crDate) {
        this.crDate = crDate;
    }
    
    public java.lang.String getCustDocument() {
        return this.custDocument;
    }
    
    public void setCustDocument(java.lang.String custDocument) {
        this.custDocument = custDocument;
    }
    
    public java.lang.String getCrUser() {
        return this.crUser;
    }
    
    public void setCrUser(java.lang.String crUser) {
        this.crUser = crUser;
    }
    
}
