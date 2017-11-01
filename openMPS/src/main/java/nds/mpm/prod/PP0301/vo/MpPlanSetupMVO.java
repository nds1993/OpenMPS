package nds.mpm.prod.PP0301.vo;

/**
 * @Class Name : MpPlanSetupMVO.java
 * @Description : MpPlanSetupM VO class
 * @Modification Information
 *
 * @author M
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpPlanSetupMVO extends MpPlanSetupMDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** plant_code */
    private java.lang.String plantCode;
    
    /** pro_lcode */
    private java.lang.String proLcode;
    
    /** rate_type */
    private java.lang.String rateType;
    
    /** rate_qty */
    private java.math.BigDecimal rateQty;
    
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
    
    public java.lang.String getProLcode() {
        return this.proLcode;
    }
    
    public void setProLcode(java.lang.String proLcode) {
        this.proLcode = proLcode;
    }
    
    public java.lang.String getRateType() {
        return this.rateType;
    }
    
    public void setRateType(java.lang.String rateType) {
        this.rateType = rateType;
    }
    
    public java.math.BigDecimal getRateQty() {
        return this.rateQty;
    }
    
    public void setRateQty(java.math.BigDecimal rateQty) {
        this.rateQty = rateQty;
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
