package nds.mpm.prod.PP0401.vo;

/**
 * @Class Name : MpReqMtrlMVO.java
 * @Description : MpReqMtrlM VO class
 * @Modification Information
 *
 * @author 자재 소요량 산출
 * @since 자재 소요량 산출
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpReqMtrlMVO extends MpReqMtrlMDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** plant_code */
    private java.lang.String grupPlant;
    private java.lang.String plantCode;
    
    /** work_date */
    private java.lang.String workDate;
    
    /** mtrl_code */
    private java.lang.String mtrlCode;
    
    /** req_source */
    private java.lang.String reqSource;
    
    /** pro_code */
    private java.lang.String proCode;
    
    /** req_mtrl_qty */
    private java.math.BigDecimal reqMtrlQty;
    
    /** inv_qty */
    private java.math.BigDecimal invQty;
    
    /** safe_qty */
    private java.math.BigDecimal safeQty;
    
    /** req_pur_qty */
    private java.math.BigDecimal reqPurQty;
    
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
    
    
    public java.lang.String getGrupPlant() {
		return grupPlant;
	}

	public void setGrupPlant(java.lang.String grupPlant) {
		this.grupPlant = grupPlant;
	}

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
    
    public java.lang.String getReqSource() {
        return this.reqSource;
    }
    
    public void setReqSource(java.lang.String reqSource) {
        this.reqSource = reqSource;
    }
    
    public java.lang.String getProCode() {
        return this.proCode;
    }
    
    public void setProCode(java.lang.String proCode) {
        this.proCode = proCode;
    }
    
    public java.math.BigDecimal getReqMtrlQty() {
        return this.reqMtrlQty;
    }
    
    public void setReqMtrlQty(java.math.BigDecimal reqMtrlQty) {
        this.reqMtrlQty = reqMtrlQty;
    }
    
    public java.math.BigDecimal getInvQty() {
        return this.invQty;
    }
    
    public void setInvQty(java.math.BigDecimal invQty) {
        this.invQty = invQty;
    }
    
    public java.math.BigDecimal getSafeQty() {
        return this.safeQty;
    }
    
    public void setSafeQty(java.math.BigDecimal safeQty) {
        this.safeQty = safeQty;
    }
    
    public java.math.BigDecimal getReqPurQty() {
        return this.reqPurQty;
    }
    
    public void setReqPurQty(java.math.BigDecimal reqPurQty) {
        this.reqPurQty = reqPurQty;
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
    
}
