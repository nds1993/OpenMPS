package nds.mpm.prod.PP0901.vo;

/**
 * @Class Name : MpReqOutMVO.java
 * @Description : MpReqOutM VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpReqOutMVO extends MpReqOutMDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** plant_code */
    private java.lang.String plantCode;
    
    /** work_date */
    private java.lang.String workDate;
    
    /** mtrl_code */
    private java.lang.String mtrlCode;
    
    /** used_qty */
    private java.math.BigDecimal usedQty;
    
    /** unit */
    private java.lang.String unit;
    
    /** oderno */
    private java.lang.String oderno;
    
    /** odersn */
    private java.math.BigDecimal odersn;
    
    /** dc */
    private java.lang.String dc;
    
    /** out_status */
    private java.lang.String outStatus;
    
    /** error_msg */
    private java.lang.String errorMsg;
    
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
    
    public java.lang.String getWorkDate() {
        return this.workDate;
    }
    
    public void setWorkDate(java.lang.String workDate) {
        this.workDate = workDate;
    }
    
    public java.lang.String getMtrlCode() {
        return this.mtrlCode;
    }
    
    public void setMtrlCode(java.lang.String mtrlCode) {
        this.mtrlCode = mtrlCode;
    }
    
    public java.math.BigDecimal getUsedQty() {
        return this.usedQty;
    }
    
    public void setUsedQty(java.math.BigDecimal usedQty) {
        this.usedQty = usedQty;
    }
    
    public java.lang.String getUnit() {
        return this.unit;
    }
    
    public void setUnit(java.lang.String unit) {
        this.unit = unit;
    }
    
    public java.lang.String getOderno() {
        return this.oderno;
    }
    
    public void setOderno(java.lang.String oderno) {
        this.oderno = oderno;
    }
    
    public java.math.BigDecimal getOdersn() {
        return this.odersn;
    }
    
    public void setOdersn(java.math.BigDecimal odersn) {
        this.odersn = odersn;
    }
    
    public java.lang.String getDc() {
        return this.dc;
    }
    
    public void setDc(java.lang.String dc) {
        this.dc = dc;
    }
    
    public java.lang.String getOutStatus() {
        return this.outStatus;
    }
    
    public void setOutStatus(java.lang.String outStatus) {
        this.outStatus = outStatus;
    }
    
    public java.lang.String getErrorMsg() {
        return this.errorMsg;
    }
    
    public void setErrorMsg(java.lang.String errorMsg) {
        this.errorMsg = errorMsg;
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
