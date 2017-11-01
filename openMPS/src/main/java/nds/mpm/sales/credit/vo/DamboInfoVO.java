package nds.mpm.sales.credit.vo;

import nds.mpm.sales.cust.vo.CustInfoVO;

/**
 * @Class Name : DamboInfoVO.java
 * @Description : DamboInfo VO class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.12
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class DamboInfoVO extends DamboInfoDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** dam_code */
    private long damCode;
    
    /** cust_code */
    private java.lang.String custCode;
    
    /** dam_date */
    private java.lang.String damDate;
    
    /** dam_ckind */
    private java.lang.String damCkind;
    
    /** dam_nkind */
    private java.lang.String damNkind;
    
    /** dam_price */
    private java.math.BigDecimal damPrice;
    
    /** dam_list */
    private java.lang.String damList;
    
    /** dam_sdate */
    private java.lang.String damSdate;
    
    /** dam_edate */
    private java.lang.String damEdate;
    
    /** dam_hdate */
    private java.lang.String damHdate;
    
    /** dam_comp */
    private java.lang.String damComp;
    
    /** dam_tel */
    private java.lang.String damTel;
    
    /** dam_buil */
    private java.lang.String damBuil;
    
    /** dam_srank */
    private java.lang.String damSrank;
    
    /** dam_skum */
    private java.math.BigDecimal damSkum;
    
    /** dam_memo */
    private java.lang.String damMemo;
    
    /** memo */
    private java.lang.String memo;
    
    /** dele_yn */
    private java.lang.String deleYn;
    
    /** md_user */
    private java.lang.String mdUser;
    
    /** md_date */
    private java.lang.String mdDate;
    
    /** cr_user */
    private java.lang.String crUser;
    
    /** cr_date */
    private java.lang.String crDate;
    
    private CustInfoVO custInfo;
    
    public long getDamCode() {
        return this.damCode;
    }
    
    public void setDamCode(long damCode) {
        this.damCode = damCode;
    }
    
    public java.lang.String getCustCode() {
        return this.custCode;
    }
    
    public void setCustCode(java.lang.String custCode) {
        this.custCode = custCode;
    }
    
    public java.lang.String getDamDate() {
        return this.damDate;
    }
    
    public void setDamDate(java.lang.String damDate) {
        this.damDate = damDate;
    }
    
    public java.lang.String getDamCkind() {
        return this.damCkind;
    }
    
    public void setDamCkind(java.lang.String damCkind) {
        this.damCkind = damCkind;
    }
    
    public java.lang.String getDamNkind() {
        return this.damNkind;
    }
    
    public void setDamNkind(java.lang.String damNkind) {
        this.damNkind = damNkind;
    }
    
    public java.math.BigDecimal getDamPrice() {
        return this.damPrice;
    }
    
    public void setDamPrice(java.math.BigDecimal damPrice) {
        this.damPrice = damPrice;
    }
    
    public java.lang.String getDamList() {
        return this.damList;
    }
    
    public void setDamList(java.lang.String damList) {
        this.damList = damList;
    }
    
    public java.lang.String getDamSdate() {
        return this.damSdate;
    }
    
    public void setDamSdate(java.lang.String damSdate) {
        this.damSdate = damSdate;
    }
    
    public java.lang.String getDamEdate() {
        return this.damEdate;
    }
    
    public void setDamEdate(java.lang.String damEdate) {
        this.damEdate = damEdate;
    }
    
    public java.lang.String getDamHdate() {
        return this.damHdate;
    }
    
    public void setDamHdate(java.lang.String damHdate) {
        this.damHdate = damHdate;
    }
    
    public java.lang.String getDamComp() {
        return this.damComp;
    }
    
    public void setDamComp(java.lang.String damComp) {
        this.damComp = damComp;
    }
    
    public java.lang.String getDamTel() {
        return this.damTel;
    }
    
    public void setDamTel(java.lang.String damTel) {
        this.damTel = damTel;
    }
    
    public java.lang.String getDamBuil() {
        return this.damBuil;
    }
    
    public void setDamBuil(java.lang.String damBuil) {
        this.damBuil = damBuil;
    }
    
    public java.lang.String getDamSrank() {
        return this.damSrank;
    }
    
    public void setDamSrank(java.lang.String damSrank) {
        this.damSrank = damSrank;
    }
    
    public java.math.BigDecimal getDamSkum() {
        return this.damSkum;
    }
    
    public void setDamSkum(java.math.BigDecimal damSkum) {
        this.damSkum = damSkum;
    }
    
    public java.lang.String getDamMemo() {
        return this.damMemo;
    }
    
    public void setDamMemo(java.lang.String damMemo) {
        this.damMemo = damMemo;
    }
    
    public java.lang.String getMemo() {
        return this.memo;
    }
    
    public void setMemo(java.lang.String memo) {
        this.memo = memo;
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
    
    public java.lang.String getMdDate() {
        return this.mdDate;
    }
    
    public void setMdDate(java.lang.String mdDate) {
        this.mdDate = mdDate;
    }
    
    public java.lang.String getCrUser() {
        return this.crUser;
    }
    
    public void setCrUser(java.lang.String crUser) {
        this.crUser = crUser;
    }
    
    public java.lang.String getCrDate() {
        return this.crDate;
    }
    
    public void setCrDate(java.lang.String crDate) {
        this.crDate = crDate;
    }

	public CustInfoVO getCustInfo() {
		return custInfo;
	}

	public void setCustInfo(CustInfoVO custInfo) {
		this.custInfo = custInfo;
	}
    
}
