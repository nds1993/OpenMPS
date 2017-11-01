package nds.mpm.prod.PP0303.vo;

/**
 * @Class Name : MpPlanPlatDVO.java
 * @Description : MpPlanPlatD VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpPlanPlatDVO extends MpPlanPlatDDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** plant_code */
    private java.lang.String plantCode;
    
    /** work_date */
    private java.lang.String workDate;
    
    /** pro_lcode */
    private java.lang.String proLcode;
    
    /** pro_lname */
    private java.lang.String proLname;
    
    /** pro_code */
    private java.lang.String proCode;
    
    /** pro_name */
    private java.lang.String proName;
    
    /** san_qty */
    private java.math.BigDecimal sanQty;
    
    /** san_weig */
    private java.math.BigDecimal sanWeig;
    
    /** san_dosu */
    private java.math.BigDecimal sanDosu;
    
    /** work_cnt */
    private java.math.BigDecimal workCnt;
    
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
    
    
    
    public java.lang.String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(java.lang.String workDate) {
		this.workDate = workDate;
	}

	public java.lang.String getProLcode() {
        return this.proLcode;
    }
    
    public void setProLcode(java.lang.String proLcode) {
        this.proLcode = proLcode;
    }
    
    public java.lang.String getProLname() {
        return this.proLname;
    }
    
    public void setProLname(java.lang.String proLname) {
        this.proLname = proLname;
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
    
    public java.math.BigDecimal getSanQty() {
        return this.sanQty;
    }
    
    public void setSanQty(java.math.BigDecimal sanQty) {
        this.sanQty = sanQty;
    }
    
    public java.math.BigDecimal getSanWeig() {
        return this.sanWeig;
    }
    
    public void setSanWeig(java.math.BigDecimal sanWeig) {
        this.sanWeig = sanWeig;
    }
    
    public java.math.BigDecimal getSanDosu() {
        return this.sanDosu;
    }
    
    public void setSanDosu(java.math.BigDecimal sanDosu) {
        this.sanDosu = sanDosu;
    }
    
    public java.math.BigDecimal getWorkCnt() {
        return this.workCnt;
    }
    
    public void setWorkCnt(java.math.BigDecimal workCnt) {
        this.workCnt = workCnt;
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
