package nds.mpm.sales.SD0501.vo;

/**
 * @Class Name : MpLastUnpaymentVO.java
 * @Description : MpLastUnpayment VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpLastUnpaymentVO extends MpLastUnpaymentDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** unpay_yymm */
    private java.lang.String unpayYymm;
    
    /** cust_code */
    private java.lang.String custCode;
    
    /** last_unpay */
    private java.math.BigDecimal lastUnpay;
    
    /** current_unpay */
    private int currentUnpay;
    
    /** head_cust */
    private java.lang.String headCust;
    
    public java.lang.String getCorpCode() {
        return this.corpCode;
    }
    
    public void setCorpCode(java.lang.String corpCode) {
        this.corpCode = corpCode;
    }
    
    public java.lang.String getUnpayYymm() {
        return this.unpayYymm;
    }
    
    public void setUnpayYymm(java.lang.String unpayYymm) {
        this.unpayYymm = unpayYymm;
    }
    
    public java.lang.String getCustCode() {
        return this.custCode;
    }
    
    public void setCustCode(java.lang.String custCode) {
        this.custCode = custCode;
    }
    
    public java.math.BigDecimal getLastUnpay() {
        return this.lastUnpay;
    }
    
    public void setLastUnpay(java.math.BigDecimal lastUnpay) {
        this.lastUnpay = lastUnpay;
    }
    
    public int getCurrentUnpay() {
        return this.currentUnpay;
    }
    
    public void setCurrentUnpay(int currentUnpay) {
        this.currentUnpay = currentUnpay;
    }
    
    public java.lang.String getHeadCust() {
        return this.headCust;
    }
    
    public void setHeadCust(java.lang.String headCust) {
        this.headCust = headCust;
    }
    
}
