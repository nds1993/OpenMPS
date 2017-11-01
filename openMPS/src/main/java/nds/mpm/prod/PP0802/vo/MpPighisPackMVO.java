package nds.mpm.prod.PP0802.vo;

/**
 * @Class Name : MpPighisPackMVO.java
 * @Description : MpPighisPackM VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpPighisPackMVO extends MpPighisPackMDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** pack_date */
    private java.lang.String packDate;
    
    /** bunch_no */
    private java.lang.String bunchNo;
    
    /** base_part_code */
    private java.lang.String basePartCode;
    
    /** base_part_name */
    private java.lang.String basePartName;
    
    /** pack_weig */
    private java.math.BigDecimal packWeig;
    
    /** memo */
    private java.lang.String memo;
    
    /** api_time */
    private java.sql.Timestamp apiTime;
    
    /** dele_yn */
    private java.lang.String deleYn;
    
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
    
    public java.lang.String getPackDate() {
        return this.packDate;
    }
    
    public void setPackDate(java.lang.String packDate) {
        this.packDate = packDate;
    }
    
    public java.lang.String getBunchNo() {
        return this.bunchNo;
    }
    
    public void setBunchNo(java.lang.String bunchNo) {
        this.bunchNo = bunchNo;
    }
    
    public java.lang.String getBasePartCode() {
        return this.basePartCode;
    }
    
    public void setBasePartCode(java.lang.String basePartCode) {
        this.basePartCode = basePartCode;
    }
    
    public java.lang.String getBasePartName() {
        return this.basePartName;
    }
    
    public void setBasePartName(java.lang.String basePartName) {
        this.basePartName = basePartName;
    }
    
    public java.math.BigDecimal getPackWeig() {
        return this.packWeig;
    }
    
    public void setPackWeig(java.math.BigDecimal packWeig) {
        this.packWeig = packWeig;
    }
    
    public java.lang.String getMemo() {
        return this.memo;
    }
    
    public void setMemo(java.lang.String memo) {
        this.memo = memo;
    }
    
    public java.sql.Timestamp getApiTime() {
        return this.apiTime;
    }
    
    public void setApiTime(java.sql.Timestamp apiTime) {
        this.apiTime = apiTime;
    }
    
    public java.lang.String getDeleYn() {
        return this.deleYn;
    }
    
    public void setDeleYn(java.lang.String deleYn) {
        this.deleYn = deleYn;
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
