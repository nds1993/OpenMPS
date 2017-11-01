package nds.mpm.prod.PP0305.vo;

/**
 * @Class Name : MpPlanPmMVO.java
 * @Description : MpPlanPmM VO class
 * @Modification Information
 *
 * @author 생산의뢰 접수 (PM주문)
 * @since 생산의뢰 접수 (PM주문)
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpPlanPmMVO extends MpPlanPmMDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** plant_code */
    private java.lang.String plantCode;
    
    /** plan_no */
    private java.lang.String planNo;
    
    /** pro_code */
    private java.lang.String proCode;
    
    /** work_date */
    private java.lang.String workDate;
    
    /** work_line */
    private java.lang.String workLine;
    
    /** work_seq */
    private java.math.BigDecimal workSeq;
    
    /** work_qty */
    private java.math.BigDecimal workQty;
    
    /** pro_unit */
    private java.lang.String proUnit;
    
    /** status */
    private java.lang.String status;
    
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
    
    public java.lang.String getPlanNo() {
        return this.planNo;
    }
    
    public void setPlanNo(java.lang.String planNo) {
        this.planNo = planNo;
    }
    
    public java.lang.String getProCode() {
        return this.proCode;
    }
    
    public void setProCode(java.lang.String proCode) {
        this.proCode = proCode;
    }
    
    public java.lang.String getWorkDate() {
        return this.workDate;
    }
    
    public void setWorkDate(java.lang.String workDate) {
        this.workDate = workDate;
    }
    
    public java.lang.String getWorkLine() {
        return this.workLine;
    }
    
    public void setWorkLine(java.lang.String workLine) {
        this.workLine = workLine;
    }
    
    public java.math.BigDecimal getWorkSeq() {
        return this.workSeq;
    }
    
    public void setWorkSeq(java.math.BigDecimal workSeq) {
        this.workSeq = workSeq;
    }
    
    public java.math.BigDecimal getWorkQty() {
        return this.workQty;
    }
    
    public void setWorkQty(java.math.BigDecimal workQty) {
        this.workQty = workQty;
    }
    
    public java.lang.String getProUnit() {
        return this.proUnit;
    }
    
    public void setProUnit(java.lang.String proUnit) {
        this.proUnit = proUnit;
    }
    
    public java.lang.String getStatus() {
        return this.status;
    }
    
    public void setStatus(java.lang.String status) {
        this.status = status;
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
    
}
