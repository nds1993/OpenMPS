package nds.mpm.prod.PP0304.vo;

/**
 * @Class Name : MpAcceptOrdrPmMVO.java
 * @Description : MpAcceptOrdrPmM VO class
 * @Modification Information
 *
 * @author 생산계획입력(PM)
 * @since 생산계획입력(PM)
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpAcceptOrdrPmMVO extends MpAcceptOrdrPmMDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** plant_code */
    private java.lang.String plantCode;
    
    /** plan_no */
    private java.lang.String planNo;
    
    /** delv_date */
    private java.lang.String delvDate;
    
    /** ordr_no */
    private java.lang.String ordrNo;
    
    private java.lang.String ordrCust;
    
    /** prod_code */
    private java.lang.String proCode;
    
    /** status */
    private java.lang.String status;
    
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
    
    public java.lang.String getDelvDate() {
        return this.delvDate;
    }
    
    public void setDelvDate(java.lang.String delvDate) {
        this.delvDate = delvDate;
    }
    
    public java.lang.String getOrdrNo() {
        return this.ordrNo;
    }
    
    public void setOrdrNo(java.lang.String ordrNo) {
        this.ordrNo = ordrNo;
    }
    
    
    public java.lang.String getProCode() {
		return proCode;
	}

	public void setProCode(java.lang.String proCode) {
		this.proCode = proCode;
	}

	public java.lang.String getStatus() {
        return this.status;
    }
    
    public void setStatus(java.lang.String status) {
        this.status = status;
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

	public java.lang.String getOrdrCust() {
		return ordrCust;
	}

	public void setOrdrCust(java.lang.String ordrCust) {
		this.ordrCust = ordrCust;
	}
    
}
