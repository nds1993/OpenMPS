package nds.mpm.sales.SD0402.vo;

/**
 * @Class Name : MpOrderHVO.java
 * @Description : MpOrderH VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class SD0402VO extends SD0402DefaultVO{
private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** delv_date */
    private java.lang.String delvDate;
    
    /** oedr_no */
    private java.lang.String ordrNo;
    
    /** oedr_no */
    private java.lang.String ordrCust;
    
    /** credit_price */
    private java.math.BigDecimal creditPrice;
    
    /** ordr_amt */
    private java.math.BigDecimal ordrAmt;
    
    /** credit_over */
    private java.math.BigDecimal creditOver;
    
    /** salesman */
    private java.lang.String salesman;
    
    /** appro_request */
    private java.sql.Timestamp approRequest;
    
    /** appro_yn */
    private java.lang.String approYn;
    
    /** appro_memo */
    private java.lang.String approMemo;
    
    /** part_code */
    private java.lang.String partCode;
    
    /** part_appro */
    private java.lang.String partAppro;
    
    /** part_date */
    private java.sql.Timestamp partDate;
    
    /** head_code */
    private java.lang.String headCode;
    
    /** head_appro */
    private java.lang.String headAppro;
    
    /** head_date */
    private java.sql.Timestamp headDate;
    
    /** ceo_appro */
    private java.lang.String ceoAppro;
    
    /** ceo_date */
    private java.sql.Timestamp ceoDate;
    
    /** cr_user */
    private java.lang.String crUser;
    
    /** cr_date */
    private java.sql.Timestamp crDate;
    
    /** md_user */
    private java.lang.String mdUser;
    
    /** md_date */
    private java.sql.Timestamp mdDate;
    
    public java.lang.String getCorpCode() {
        return this.corpCode;
    }
    
    public void setCorpCode(java.lang.String corpCode) {
        this.corpCode = corpCode;
    }
    
    public java.lang.String getDelvDate() {
        return this.delvDate;
    }
    
    public void setDelvDate(java.lang.String delvDate) {
        this.delvDate = delvDate;
    }
    
    
    
    public java.lang.String getOrdrNo() {
		return ordrNo;
	}

	public void setOrdrNo(java.lang.String ordrNo) {
		this.ordrNo = ordrNo;
	}

	public java.lang.String getOrdrCust() {
		return ordrCust;
	}

	public void setOrdrCust(java.lang.String ordrCust) {
		this.ordrCust = ordrCust;
	}

	public java.math.BigDecimal getCreditPrice() {
        return this.creditPrice;
    }
    
    public void setCreditPrice(java.math.BigDecimal creditPrice) {
        this.creditPrice = creditPrice;
    }
    
    public java.math.BigDecimal getOrdrAmt() {
        return this.ordrAmt;
    }
    
    public void setOrdrAmt(java.math.BigDecimal ordrAmt) {
        this.ordrAmt = ordrAmt;
    }
    
    public java.math.BigDecimal getCreditOver() {
        return this.creditOver;
    }
    
    public void setCreditOver(java.math.BigDecimal creditOver) {
        this.creditOver = creditOver;
    }
    
    public java.lang.String getSalesman() {
        return this.salesman;
    }
    
    public void setSalesman(java.lang.String salesman) {
        this.salesman = salesman;
    }
    
    public java.sql.Timestamp getApproRequest() {
        return this.approRequest;
    }
    
    public void setApproRequest(java.sql.Timestamp approRequest) {
        this.approRequest = approRequest;
    }
    
    public java.lang.String getApproYn() {
        return this.approYn;
    }
    
    public void setApproYn(java.lang.String approYn) {
        this.approYn = approYn;
    }
    
    public java.lang.String getApproMemo() {
        return this.approMemo;
    }
    
    public void setApproMemo(java.lang.String approMemo) {
        this.approMemo = approMemo;
    }
    
    public java.lang.String getPartCode() {
        return this.partCode;
    }
    
    public void setPartCode(java.lang.String partCode) {
        this.partCode = partCode;
    }
    
    public java.lang.String getPartAppro() {
        return this.partAppro;
    }
    
    public void setPartAppro(java.lang.String partAppro) {
        this.partAppro = partAppro;
    }
    
    public java.sql.Timestamp getPartDate() {
        return this.partDate;
    }
    
    public void setPartDate(java.sql.Timestamp partDate) {
        this.partDate = partDate;
    }
    
    public java.lang.String getHeadCode() {
        return this.headCode;
    }
    
    public void setHeadCode(java.lang.String headCode) {
        this.headCode = headCode;
    }
    
    public java.lang.String getHeadAppro() {
        return this.headAppro;
    }
    
    public void setHeadAppro(java.lang.String headAppro) {
        this.headAppro = headAppro;
    }
    
    public java.sql.Timestamp getHeadDate() {
        return this.headDate;
    }
    
    public void setHeadDate(java.sql.Timestamp headDate) {
        this.headDate = headDate;
    }
    
    public java.lang.String getCeoAppro() {
        return this.ceoAppro;
    }
    
    public void setCeoAppro(java.lang.String ceoAppro) {
        this.ceoAppro = ceoAppro;
    }
    
    public java.sql.Timestamp getCeoDate() {
        return this.ceoDate;
    }
    
    public void setCeoDate(java.sql.Timestamp ceoDate) {
        this.ceoDate = ceoDate;
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
}
