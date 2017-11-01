package nds.mpm.prod.PP0103.vo;

/**
 * @Class Name : MpItemPlntgMVO.java
 * @Description : MpItemPlntgM VO class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpItemPlntgMVO extends MpItemPlntgMDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** grup_plant */
    private java.lang.String grupPlant;
    
    /** pro_code */
    private java.lang.String proCode;
    
    /** safety_stock */
    private java.math.BigDecimal safetyStock;
    
    /** etc01 */
    private java.lang.String etc01;
    
    /** etc02 */
    private java.lang.String etc02;
    
    /** etc03 */
    private java.lang.String etc03;
    
    /** etc04 */
    private java.lang.String etc04;
    
    /** etc05 */
    private java.lang.String etc05;
    
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
    
    public java.lang.String getGrupPlant() {
        return this.grupPlant;
    }
    
    public void setGrupPlant(java.lang.String grupPlant) {
        this.grupPlant = grupPlant;
    }
    
    public java.lang.String getProCode() {
        return this.proCode;
    }
    
    public void setProCode(java.lang.String proCode) {
        this.proCode = proCode;
    }
    
    public java.math.BigDecimal getSafetyStock() {
        return this.safetyStock;
    }
    
    public void setSafetyStock(java.math.BigDecimal safetyStock) {
        this.safetyStock = safetyStock;
    }
    
    public java.lang.String getEtc01() {
        return this.etc01;
    }
    
    public void setEtc01(java.lang.String etc01) {
        this.etc01 = etc01;
    }
    
    public java.lang.String getEtc02() {
        return this.etc02;
    }
    
    public void setEtc02(java.lang.String etc02) {
        this.etc02 = etc02;
    }
    
    public java.lang.String getEtc03() {
        return this.etc03;
    }
    
    public void setEtc03(java.lang.String etc03) {
        this.etc03 = etc03;
    }
    
    public java.lang.String getEtc04() {
        return this.etc04;
    }
    
    public void setEtc04(java.lang.String etc04) {
        this.etc04 = etc04;
    }
    
    public java.lang.String getEtc05() {
        return this.etc05;
    }
    
    public void setEtc05(java.lang.String etc05) {
        this.etc05 = etc05;
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
