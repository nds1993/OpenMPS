package nds.mpm.prod.PP0103.vo;

import java.util.List;

/**
 * @Class Name : ProductInfoVO.java
 * @Description : ProductInfo VO class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.08
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpItemMasterMVO extends MpItemMasterMDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** pro_code */
    private java.lang.String proCode;
    
    /** pro_name */
    private java.lang.String proName;
    
    /** animal_kind */
    private java.lang.String animalKind;
    
    /** large_code */
    private java.lang.String largeCode;
    
    /** ssale_group1 */
    private java.lang.String ssaleGroup1;
    
    /** sale_group2 */
    private java.lang.String saleGroup2;
    
    /** pro_kind */
    private java.lang.String proKind;
    
    /** meat_type */
    private java.lang.String meatType;
    
    /** prdt_type */
    private java.lang.String prdtType;
    
    /** pm_type */
    private java.lang.String pmType;
    
    /** code_88 */
    private java.lang.String code88;
    
    /** pro_ukind */
    private java.lang.String proUkind;
    
    /** pro_unit */
    private java.lang.String proUnit;
    
    /** pro_weig */
    private java.lang.String proWeig;
    
    /** pro_ea */
    private java.lang.String proEa;
    
    /** pro_ipsu */
    private java.math.BigDecimal proIpsu;
    
    /** pro_bong */
    private java.math.BigDecimal proBong;
    
    /** pro_box */
    private java.math.BigDecimal proBox;
    
    /** price_calc */
    private java.lang.String priceCalc;
    
    /** doo_conv */
    private java.math.BigDecimal dooConv;
    
    /** bar_hide_yn */
    private java.lang.String barHideYn;
    
    /** vat_code */
    private java.lang.String vatCode;
    
    /** frozen_type */
    private java.lang.String frozenType;
    
    /** set_yn */
    private java.lang.String setYn;
    
    /** shelf_life */
    private java.lang.String shelfLife;
    
    /** r_temperature */
    private java.lang.String RTemperature;
    
    /** pack_matr */
    private java.lang.String packMatr;
    
    /** pack_type */
    private java.lang.String packType;
    
    /** packunit */
    private java.lang.String packunit;
    
    /** pallet_cnt */
    private java.math.BigDecimal palletCnt;
    
    /** short_code */
    private java.lang.String shortCode;
    
    /** emart_bar1 */
    private java.lang.String emartBar1;
    
    /** emart_bar2 */
    private java.lang.String emartBar2;
    
    /** emart_bar31 */
    private java.lang.String emartBar31;
    
    /** emart_bar32 */
    private java.lang.String emartBar32;
    
    /** lotte_code */
    private java.lang.String lotteCode;
    
    /** memo */
    private java.lang.String memo;
    
    /** std_code */
    private java.lang.String stdCode;
    
    /** std_name */
    private java.lang.String stdName;
    
    /** brand_name */
    private java.lang.String brandName;
    
    /** ipsu_cnt */
    private java.math.BigDecimal ipsuCnt;
    
    /** work_type1 */
    private java.lang.String workType1;
    
    /** work_type2 */
    private java.lang.String workType2;
    
    /** work_type3 */
    private java.lang.String workType3;
    
    /** work_line */
    private java.lang.String workLine;
    
    /** print_sheet */
    private java.lang.String printSheet;
    
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
    
    public java.lang.String getProCode() {
        return this.proCode;
    }
    
    public void setProCode(java.lang.String proCode) {
        this.proCode = proCode;
    }
    
    public java.lang.String getProName() {
        return this.proName;
    }
    
    public void setProName(java.lang.String proName) {
        this.proName = proName;
    }
    
    public java.lang.String getAnimalKind() {
        return this.animalKind;
    }
    
    public void setAnimalKind(java.lang.String animalKind) {
        this.animalKind = animalKind;
    }
    
    public java.lang.String getLargeCode() {
        return this.largeCode;
    }
    
    public void setLargeCode(java.lang.String largeCode) {
        this.largeCode = largeCode;
    }
    
    public java.lang.String getSsaleGroup1() {
        return this.ssaleGroup1;
    }
    
    public void setSsaleGroup1(java.lang.String ssaleGroup1) {
        this.ssaleGroup1 = ssaleGroup1;
    }
    
    public java.lang.String getSaleGroup2() {
        return this.saleGroup2;
    }
    
    public void setSaleGroup2(java.lang.String saleGroup2) {
        this.saleGroup2 = saleGroup2;
    }
    
    public java.lang.String getProKind() {
        return this.proKind;
    }
    
    public void setProKind(java.lang.String proKind) {
        this.proKind = proKind;
    }
    
    public java.lang.String getMeatType() {
        return this.meatType;
    }
    
    public void setMeatType(java.lang.String meatType) {
        this.meatType = meatType;
    }
    
    public java.lang.String getPrdtType() {
        return this.prdtType;
    }
    
    public void setPrdtType(java.lang.String prdtType) {
        this.prdtType = prdtType;
    }
    
    public java.lang.String getPmType() {
        return this.pmType;
    }
    
    public void setPmType(java.lang.String pmType) {
        this.pmType = pmType;
    }
    
    public java.lang.String getCode88() {
        return this.code88;
    }
    
    public void setCode88(java.lang.String code88) {
        this.code88 = code88;
    }
    
    public java.lang.String getProUkind() {
        return this.proUkind;
    }
    
    public void setProUkind(java.lang.String proUkind) {
        this.proUkind = proUkind;
    }
    
    public java.lang.String getProUnit() {
        return this.proUnit;
    }
    
    public void setProUnit(java.lang.String proUnit) {
        this.proUnit = proUnit;
    }
    
    

	public java.lang.String getProWeig() {
		return proWeig;
	}

	public void setProWeig(java.lang.String proWeig) {
		this.proWeig = proWeig;
	}

	public java.lang.String getProEa() {
        return this.proEa;
    }
    
    public void setProEa(java.lang.String proEa) {
        this.proEa = proEa;
    }
    
    public java.math.BigDecimal getProIpsu() {
        return this.proIpsu;
    }
    
    public void setProIpsu(java.math.BigDecimal proIpsu) {
        this.proIpsu = proIpsu;
    }
    
    public java.math.BigDecimal getProBong() {
        return this.proBong;
    }
    
    public void setProBong(java.math.BigDecimal proBong) {
        this.proBong = proBong;
    }
    
    public java.math.BigDecimal getProBox() {
        return this.proBox;
    }
    
    public void setProBox(java.math.BigDecimal proBox) {
        this.proBox = proBox;
    }
    
    public java.lang.String getPriceCalc() {
        return this.priceCalc;
    }
    
    public void setPriceCalc(java.lang.String priceCalc) {
        this.priceCalc = priceCalc;
    }
    
    public java.math.BigDecimal getDooConv() {
        return this.dooConv;
    }
    
    public void setDooConv(java.math.BigDecimal dooConv) {
        this.dooConv = dooConv;
    }
    
    public java.lang.String getBarHideYn() {
        return this.barHideYn;
    }
    
    public void setBarHideYn(java.lang.String barHideYn) {
        this.barHideYn = barHideYn;
    }
    
    public java.lang.String getVatCode() {
        return this.vatCode;
    }
    
    public void setVatCode(java.lang.String vatCode) {
        this.vatCode = vatCode;
    }
    
    public java.lang.String getFrozenType() {
        return this.frozenType;
    }
    
    public void setFrozenType(java.lang.String frozenType) {
        this.frozenType = frozenType;
    }
    
    public java.lang.String getSetYn() {
        return this.setYn;
    }
    
    public void setSetYn(java.lang.String setYn) {
        this.setYn = setYn;
    }
    
    public java.lang.String getShelfLife() {
        return this.shelfLife;
    }
    
    public void setShelfLife(java.lang.String shelfLife) {
        this.shelfLife = shelfLife;
    }
    
    public java.lang.String getRTemperature() {
        return this.RTemperature;
    }
    
    public void setRTemperature(java.lang.String RTemperature) {
        this.RTemperature = RTemperature;
    }
    
    public java.lang.String getPackMatr() {
        return this.packMatr;
    }
    
    public void setPackMatr(java.lang.String packMatr) {
        this.packMatr = packMatr;
    }
    
    public java.lang.String getPackType() {
        return this.packType;
    }
    
    public void setPackType(java.lang.String packType) {
        this.packType = packType;
    }
    
    public java.lang.String getPackunit() {
        return this.packunit;
    }
    
    public void setPackunit(java.lang.String packunit) {
        this.packunit = packunit;
    }
    
    public java.math.BigDecimal getPalletCnt() {
        return this.palletCnt;
    }
    
    public void setPalletCnt(java.math.BigDecimal palletCnt) {
        this.palletCnt = palletCnt;
    }
    
    public java.lang.String getShortCode() {
        return this.shortCode;
    }
    
    public void setShortCode(java.lang.String shortCode) {
        this.shortCode = shortCode;
    }
    
    public java.lang.String getEmartBar1() {
        return this.emartBar1;
    }
    
    public void setEmartBar1(java.lang.String emartBar1) {
        this.emartBar1 = emartBar1;
    }
    
    public java.lang.String getEmartBar2() {
        return this.emartBar2;
    }
    
    public void setEmartBar2(java.lang.String emartBar2) {
        this.emartBar2 = emartBar2;
    }
    
    public java.lang.String getEmartBar31() {
        return this.emartBar31;
    }
    
    public void setEmartBar31(java.lang.String emartBar31) {
        this.emartBar31 = emartBar31;
    }
    
    public java.lang.String getEmartBar32() {
        return this.emartBar32;
    }
    
    public void setEmartBar32(java.lang.String emartBar32) {
        this.emartBar32 = emartBar32;
    }
    
    public java.lang.String getLotteCode() {
        return this.lotteCode;
    }
    
    public void setLotteCode(java.lang.String lotteCode) {
        this.lotteCode = lotteCode;
    }
    
    public java.lang.String getMemo() {
        return this.memo;
    }
    
    public void setMemo(java.lang.String memo) {
        this.memo = memo;
    }
    
    public java.lang.String getStdCode() {
        return this.stdCode;
    }
    
    public void setStdCode(java.lang.String stdCode) {
        this.stdCode = stdCode;
    }
    
    public java.lang.String getStdName() {
        return this.stdName;
    }
    
    public void setStdName(java.lang.String stdName) {
        this.stdName = stdName;
    }
    
    public java.lang.String getBrandName() {
        return this.brandName;
    }
    
    public void setBrandName(java.lang.String brandName) {
        this.brandName = brandName;
    }
    
    public java.math.BigDecimal getIpsuCnt() {
        return this.ipsuCnt;
    }
    
    public void setIpsuCnt(java.math.BigDecimal ipsuCnt) {
        this.ipsuCnt = ipsuCnt;
    }
    
    public java.lang.String getWorkType1() {
        return this.workType1;
    }
    
    public void setWorkType1(java.lang.String workType1) {
        this.workType1 = workType1;
    }
    
    public java.lang.String getWorkType2() {
        return this.workType2;
    }
    
    public void setWorkType2(java.lang.String workType2) {
        this.workType2 = workType2;
    }
    
    public java.lang.String getWorkType3() {
        return this.workType3;
    }
    
    public void setWorkType3(java.lang.String workType3) {
        this.workType3 = workType3;
    }
    
    public java.lang.String getWorkLine() {
        return this.workLine;
    }
    
    public void setWorkLine(java.lang.String workLine) {
        this.workLine = workLine;
    }
    
    public java.lang.String getPrintSheet() {
        return this.printSheet;
    }
    
    public void setPrintSheet(java.lang.String printSheet) {
        this.printSheet = printSheet;
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
