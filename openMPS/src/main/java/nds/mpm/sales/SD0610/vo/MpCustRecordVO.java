package nds.mpm.sales.SD0610.vo;

/**
 * @Class Name : MpCustRecordVO.java
 * @Description : MpCustRecord VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpCustRecordVO extends MpCustRecordDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** sale_date */
    private java.lang.String saleDate;
    
    /** cust_code */
    private java.lang.String custCode;
    
    /** pro_code */
    private java.lang.String proCode;
    
    /** sale_box */
    private java.math.BigDecimal saleBox;
    
    /** sale_weight */
    private java.math.BigDecimal saleWeight;
    
    /** sale_amt */
    private java.math.BigDecimal saleAmt;
    
    /** sale_vat */
    private java.math.BigDecimal saleVat;
    
    /** head_cust */
    private java.lang.String headCust;
    
    /** dist_code */
    private java.lang.String distCode;

    /** searchCondition */
    private java.lang.String searchCondition;

	public java.lang.String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(java.lang.String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public java.lang.String getCorpCode() {
        return this.corpCode;
    }
    
    public void setCorpCode(java.lang.String corpCode) {
        this.corpCode = corpCode;
    }
    
    public java.lang.String getSaleDate() {
        return this.saleDate;
    }
    
    public void setSaleDate(java.lang.String saleDate) {
        this.saleDate = saleDate;
    }
    
    public java.lang.String getCustCode() {
        return this.custCode;
    }
    
    public void setCustCode(java.lang.String custCode) {
        this.custCode = custCode;
    }
    
    public java.lang.String getProCode() {
        return this.proCode;
    }
    
    public void setProCode(java.lang.String proCode) {
        this.proCode = proCode;
    }
    
    public java.math.BigDecimal getSaleBox() {
        return this.saleBox;
    }
    
    public void setSaleBox(java.math.BigDecimal saleBox) {
        this.saleBox = saleBox;
    }
    
    public java.math.BigDecimal getSaleWeight() {
        return this.saleWeight;
    }
    
    public void setSaleWeight(java.math.BigDecimal saleWeight) {
        this.saleWeight = saleWeight;
    }
    
    public java.math.BigDecimal getSaleAmt() {
        return this.saleAmt;
    }
    
    public void setSaleAmt(java.math.BigDecimal saleAmt) {
        this.saleAmt = saleAmt;
    }
    
    public java.math.BigDecimal getSaleVat() {
        return this.saleVat;
    }
    
    public void setSaleVat(java.math.BigDecimal saleVat) {
        this.saleVat = saleVat;
    }
    
    public java.lang.String getHeadCust() {
        return this.headCust;
    }
    
    public void setHeadCust(java.lang.String headCust) {
        this.headCust = headCust;
    }
    
    
    public java.lang.String getDistCode() {
		return distCode;
	}

	public void setDistCode(java.lang.String distCode) {
		this.distCode = distCode;
	}

}
