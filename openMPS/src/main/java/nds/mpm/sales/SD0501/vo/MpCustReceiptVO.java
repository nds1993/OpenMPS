package nds.mpm.sales.SD0501.vo;

/**
 * @Class Name : MpCustReceiptVO.java
 * @Description : MpCustReceipt VO class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpCustReceiptVO extends MpCustReceiptDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** rece_date */
    private java.lang.String receDate;
    
    /** cust_code */
    private java.lang.String custCode;
    
    /** rece_type */
    private java.lang.String receType;
    
    /** rece_amt */
    private int receAmt;
    
    /** head_cust */
    private java.lang.String headCust;
    
    public java.lang.String getCorpCode() {
        return this.corpCode;
    }
    
    public void setCorpCode(java.lang.String corpCode) {
        this.corpCode = corpCode;
    }
    
    public java.lang.String getReceDate() {
        return this.receDate;
    }
    
    public void setReceDate(java.lang.String receDate) {
        this.receDate = receDate;
    }
    
    public java.lang.String getCustCode() {
        return this.custCode;
    }
    
    public void setCustCode(java.lang.String custCode) {
        this.custCode = custCode;
    }
    
    public java.lang.String getReceType() {
        return this.receType;
    }
    
    public void setReceType(java.lang.String receType) {
        this.receType = receType;
    }
    
    public int getReceAmt() {
        return this.receAmt;
    }
    
    public void setReceAmt(int receAmt) {
        this.receAmt = receAmt;
    }
    
    public java.lang.String getHeadCust() {
        return this.headCust;
    }
    
    public void setHeadCust(java.lang.String headCust) {
        this.headCust = headCust;
    }
    
}
