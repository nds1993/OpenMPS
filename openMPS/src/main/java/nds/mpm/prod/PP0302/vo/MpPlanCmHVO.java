package nds.mpm.prod.PP0302.vo;

/**
 * @Class Name : MpPlanCmHVO.java
 * @Description : MpPlanCmH VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpPlanCmHVO extends MpPlanCmHDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** plant_code */
    private java.lang.String plantCode;
    
    /** work_date */
    private String workDate;
    
    /** doosu1 */
    private double doosu1;
    
    /** doosu2 */
    private double doosu2;
    
    /** doosu3 */
    private double doosu3;
    
    /** doosu4 */
    private double doosu4;
    
    /** doosu5 */
    private double doosu5;
    
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
    
    public String getWorkDate() {
        return this.workDate;
    }
    
    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }
    
    public double getDoosu1() {
        return this.doosu1;
    }
    
    public void setDoosu1(double doosu1) {
        this.doosu1 = doosu1;
    }
    
    public double getDoosu2() {
        return this.doosu2;
    }
    
    public void setDoosu2(double doosu2) {
        this.doosu2 = doosu2;
    }
    
    public double getDoosu3() {
        return this.doosu3;
    }
    
    public void setDoosu3(double doosu3) {
        this.doosu3 = doosu3;
    }
    
    public double getDoosu4() {
        return this.doosu4;
    }
    
    public void setDoosu4(double doosu4) {
        this.doosu4 = doosu4;
    }
    
    public double getDoosu5() {
        return this.doosu5;
    }
    
    public void setDoosu5(double doosu5) {
        this.doosu5 = doosu5;
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
