package nds.mpm.prod.PP0902.vo;

/**
 * @Class Name : MpDailySumLogMVO.java
 * @Description : MpDailySumLogM VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpDailySumLogMVO extends MpDailySumLogMDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** plant_code */
    private java.lang.String plantCode;
    
    /** cm_pm_type */
    private java.lang.String cmPmType;
    
    /** batch_job_date */
    private java.lang.String batchJobDate;
    
    /** batch_job_seq */
    private java.math.BigDecimal batchJobSeq;
    
    /** from_date */
    private java.lang.String fromDate;
    
    /** to_date */
    private java.lang.String toDate;
    
    /** from_time */
    private java.sql.Timestamp fromTime;
    
    /** to_time */
    private java.sql.Timestamp toTime;
    
    /** result_type */
    private java.lang.String resultType;
    
    /** result_msg */
    private java.lang.String resultMsg;
    
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
    
    public java.lang.String getCmPmType() {
        return this.cmPmType;
    }
    
    public void setCmPmType(java.lang.String cmPmType) {
        this.cmPmType = cmPmType;
    }
    
    public java.lang.String getBatchJobDate() {
        return this.batchJobDate;
    }
    
    public void setBatchJobDate(java.lang.String batchJobDate) {
        this.batchJobDate = batchJobDate;
    }
    
    public java.math.BigDecimal getBatchJobSeq() {
        return this.batchJobSeq;
    }
    
    public void setBatchJobSeq(java.math.BigDecimal batchJobSeq) {
        this.batchJobSeq = batchJobSeq;
    }
    
    public java.lang.String getFromDate() {
        return this.fromDate;
    }
    
    public void setFromDate(java.lang.String fromDate) {
        this.fromDate = fromDate;
    }
    
    public java.lang.String getToDate() {
        return this.toDate;
    }
    
    public void setToDate(java.lang.String toDate) {
        this.toDate = toDate;
    }
    
    public java.sql.Timestamp getFromTime() {
        return this.fromTime;
    }
    
    public void setFromTime(java.sql.Timestamp fromTime) {
        this.fromTime = fromTime;
    }
    
    public java.sql.Timestamp getToTime() {
        return this.toTime;
    }
    
    public void setToTime(java.sql.Timestamp toTime) {
        this.toTime = toTime;
    }
    
    public java.lang.String getResultType() {
        return this.resultType;
    }
    
    public void setResultType(java.lang.String resultType) {
        this.resultType = resultType;
    }
    
    public java.lang.String getResultMsg() {
        return this.resultMsg;
    }
    
    public void setResultMsg(java.lang.String resultMsg) {
        this.resultMsg = resultMsg;
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
