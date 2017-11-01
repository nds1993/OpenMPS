package nds.mpm.sales.SD0303.vo;

/**
 * @Class Name : MpTargetYymmVO.java
 * @Description : MpTargetYymm VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpTargetYymmVO extends MpTargetYymmDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** target_yyyy */
    private java.lang.String targetYyyy;
    
    /** target_yymm */
    private java.lang.String targetYymm;
    
    /** head_code */
    private java.lang.String headCode;
    
    /** part_code */
    private java.lang.String partCode;
    
    /** salesman */
    private java.lang.String salesman;
    
    /** sale_group1 */
    private java.lang.String saleGroup1;
    
    /** sale_group2 */
    private java.lang.String saleGroup2;
    
    /** target_weight */
    private java.math.BigDecimal targetWeight;
    
    /** md_user */
    private java.lang.String mdUser;
    
    /** md_date */
    private java.sql.Timestamp mdDate;
    
    /** cr_user */
    private java.lang.String crUser;
    
    /** cr_date */
    private java.sql.Timestamp crDate;
    
    public java.lang.String getCorpCode() {
        return this.corpCode;
    }
    
    public void setCorpCode(java.lang.String corpCode) {
        this.corpCode = corpCode;
    }
    
    public java.lang.String getTargetYyyy() {
        return this.targetYyyy;
    }
    
    public void setTargetYyyy(java.lang.String targetYyyy) {
        this.targetYyyy = targetYyyy;
    }
    
    public java.lang.String getTargetYymm() {
        return this.targetYymm;
    }
    
    public void setTargetYymm(java.lang.String targetYymm) {
        this.targetYymm = targetYymm;
    }
    
    public java.lang.String getHeadCode() {
        return this.headCode;
    }
    
    public void setHeadCode(java.lang.String headCode) {
        this.headCode = headCode;
    }
    
    public java.lang.String getPartCode() {
        return this.partCode;
    }
    
    public void setPartCode(java.lang.String partCode) {
        this.partCode = partCode;
    }
    
    public java.lang.String getSalesman() {
        return this.salesman;
    }
    
    public void setSalesman(java.lang.String salesman) {
        this.salesman = salesman;
    }
    
    public java.lang.String getSaleGroup1() {
        return this.saleGroup1;
    }
    
    public void setSaleGroup1(java.lang.String saleGroup1) {
        this.saleGroup1 = saleGroup1;
    }
    
    public java.lang.String getSaleGroup2() {
        return this.saleGroup2;
    }
    
    public void setSaleGroup2(java.lang.String saleGroup2) {
        this.saleGroup2 = saleGroup2;
    }
    
    public java.math.BigDecimal getTargetWeight() {
        return this.targetWeight;
    }
    
    public void setTargetWeight(java.math.BigDecimal targetWeight) {
        this.targetWeight = targetWeight;
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
