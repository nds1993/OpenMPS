package nds.mpm.sales.SD0203.vo;

/**
 * @Class Name : MpSalePriceVO.java
 * @Description : MpSalePrice VO class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpSalePriceVO extends MpSalePriceDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** strt_date */
    private java.lang.String strtDate;
    
    /** last_date */
    private java.lang.String lastDate;
    
    /** cust_code */
    private java.lang.String custCode;
    
    /** pro_code */
    private java.lang.String proCode;
    
    /** sale_price */
    private java.math.BigDecimal salePrice;
    
    /** appro_yn */
    private java.lang.String approYn;
    
    private java.lang.String status;
    
    private java.lang.String salesman;
    
    /** cr_user */
    private java.lang.String crUser;
    
    /** cr_date */
    private java.sql.Timestamp crDate;
    
    /** md_user */
    private java.lang.String mdUser;
    
    /** md_date */
    private java.sql.Timestamp mdDate;
    
    private java.lang.String priceDay;
    
    public java.lang.String getCorpCode() {
        return this.corpCode;
    }
    
    public void setCorpCode(java.lang.String corpCode) {
        this.corpCode = corpCode;
    }
    
    public java.lang.String getStrtDate() {
        return this.strtDate;
    }
    
    public void setStrtDate(java.lang.String strtDate) {
        this.strtDate = strtDate;
    }
    
    public java.lang.String getLastDate() {
        return this.lastDate;
    }
    
    public void setLastDate(java.lang.String lastDate) {
        this.lastDate = lastDate;
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
    
    public java.math.BigDecimal getSalePrice() {
        return this.salePrice;
    }
    
    public void setSalePrice(java.math.BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
    
    public java.lang.String getApproYn() {
        return this.approYn;
    }
    
    public void setApproYn(java.lang.String approYn) {
        this.approYn = approYn;
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

	public java.lang.String getSalesman() {
		return salesman;
	}

	public void setSalesman(java.lang.String salesman) {
		this.salesman = salesman;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getPriceDay() {
		return priceDay;
	}

	public void setPriceDay(java.lang.String priceDay) {
		this.priceDay = priceDay;
	}
    
}
