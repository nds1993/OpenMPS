package nds.mpm.prod.PP0105.vo;

/**
 * @Class Name : MpBomDVO.java
 * @Description : MpBomD VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpBomDVO extends MpBomDDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** plant_code */
    private java.lang.String plantCode;
    
    /** bom_code */
    private java.lang.String bomCode;
    
    /** bom_ver */
    private int bomVer;
    
    /** line_no */
    private java.math.BigDecimal lineNo;
    
    /** pro_type */
    private java.lang.String proType;
    
    /** pro_code */
    private java.lang.String proCode;

    /** pro_code */
    private java.lang.String buProCode;
    
    /** qty */
    private int qty;
    
    /** pro_unit */
    private java.lang.String proUnit;
    
    /** memo */
    private java.lang.String memo;
    
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
    
    public java.lang.String getPlantCode() {
        return this.plantCode;
    }
    
    public void setPlantCode(java.lang.String plantCode) {
        this.plantCode = plantCode;
    }
    
    public java.lang.String getBomCode() {
        return this.bomCode;
    }
    
    public void setBomCode(java.lang.String bomCode) {
        this.bomCode = bomCode;
    }
    
    public int getBomVer() {
        return this.bomVer;
    }
    
    public void setBomVer(int bomVer) {
        this.bomVer = bomVer;
    }
    
    public java.math.BigDecimal getLineNo() {
        return this.lineNo;
    }
    
    public void setLineNo(java.math.BigDecimal lineNo) {
        this.lineNo = lineNo;
    }
    
    public java.lang.String getProType() {
        return this.proType;
    }
    
    public void setProType(java.lang.String proType) {
        this.proType = proType;
    }
    
    public java.lang.String getProCode() {
        return this.proCode;
    }
    
    public void setProCode(java.lang.String proCode) {
        this.proCode = proCode;
    }
    
    
    public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public java.lang.String getProUnit() {
        return this.proUnit;
    }
    
    public void setProUnit(java.lang.String proUnit) {
        this.proUnit = proUnit;
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

	public java.lang.String getBuProCode() {
		return buProCode;
	}

	public void setBuProCode(java.lang.String buProCode) {
		this.buProCode = buProCode;
	}
    
}
