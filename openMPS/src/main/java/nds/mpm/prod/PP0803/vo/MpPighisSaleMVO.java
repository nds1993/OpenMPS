package nds.mpm.prod.PP0803.vo;

/**
 * @Class Name : MpPighisSaleMVO.java
 * @Description : MpPighisSaleM VO class
 * @Modification Information
 *
 * @author 거래내역실적신고(이력제)
 * @since 거래내역실적신고(이력제)
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpPighisSaleMVO extends MpPighisSaleMDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** out_code */
    private java.lang.String outCode;
    
    /** sale_date */
    private java.lang.String saleDate;
    
    /** his_bunch_no */
    private java.lang.String hisBunchNo;
    
    /** cust_code */
    private java.lang.String custCode;
    
    /** cust_name */
    private java.lang.String custName;
    
    /** cust_regno */
    private java.lang.String custRegno;
    
    /** cust_owner */
    private java.lang.String custOwner;
    
    /** cust_phone */
    private java.lang.String custPhone;
    
    /** cust_addr */
    private java.lang.String custAddr;
    
    /** no_gubun */
    private java.lang.String noGubun;
    
    /** gagong_no */
    private java.lang.String gagongNo;
    
    /** base_part_code */
    private java.lang.String basePartCode;
    
    /** base_part_name */
    private java.lang.String basePartName;
    
    /** sale_weig */
    private java.math.BigDecimal saleWeig;
    
    /** sale_qty */
    private java.math.BigDecimal saleQty;
    
    /** lc_yn */
    private java.lang.String lcYn;
    
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
    
    public java.lang.String getOutCode() {
        return this.outCode;
    }
    
    public void setOutCode(java.lang.String outCode) {
        this.outCode = outCode;
    }
    
    public java.lang.String getSaleDate() {
        return this.saleDate;
    }
    
    public void setSaleDate(java.lang.String saleDate) {
        this.saleDate = saleDate;
    }
    
    public java.lang.String getHisBunchNo() {
        return this.hisBunchNo;
    }
    
    public void setHisBunchNo(java.lang.String hisBunchNo) {
        this.hisBunchNo = hisBunchNo;
    }
    
    public java.lang.String getCustCode() {
        return this.custCode;
    }
    
    public void setCustCode(java.lang.String custCode) {
        this.custCode = custCode;
    }
    
    public java.lang.String getCustName() {
        return this.custName;
    }
    
    public void setCustName(java.lang.String custName) {
        this.custName = custName;
    }
    
    public java.lang.String getCustRegno() {
        return this.custRegno;
    }
    
    public void setCustRegno(java.lang.String custRegno) {
        this.custRegno = custRegno;
    }
    
    public java.lang.String getCustOwner() {
        return this.custOwner;
    }
    
    public void setCustOwner(java.lang.String custOwner) {
        this.custOwner = custOwner;
    }
    
    public java.lang.String getCustPhone() {
        return this.custPhone;
    }
    
    public void setCustPhone(java.lang.String custPhone) {
        this.custPhone = custPhone;
    }
    
    public java.lang.String getCustAddr() {
        return this.custAddr;
    }
    
    public void setCustAddr(java.lang.String custAddr) {
        this.custAddr = custAddr;
    }
    
    public java.lang.String getNoGubun() {
        return this.noGubun;
    }
    
    public void setNoGubun(java.lang.String noGubun) {
        this.noGubun = noGubun;
    }
    
    public java.lang.String getGagongNo() {
        return this.gagongNo;
    }
    
    public void setGagongNo(java.lang.String gagongNo) {
        this.gagongNo = gagongNo;
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
    
    public java.math.BigDecimal getSaleWeig() {
        return this.saleWeig;
    }
    
    public void setSaleWeig(java.math.BigDecimal saleWeig) {
        this.saleWeig = saleWeig;
    }
    
    public java.math.BigDecimal getSaleQty() {
        return this.saleQty;
    }
    
    public void setSaleQty(java.math.BigDecimal saleQty) {
        this.saleQty = saleQty;
    }
    
    public java.lang.String getLcYn() {
        return this.lcYn;
    }
    
    public void setLcYn(java.lang.String lcYn) {
        this.lcYn = lcYn;
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
