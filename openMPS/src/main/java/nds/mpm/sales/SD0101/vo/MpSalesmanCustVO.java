package nds.mpm.sales.SD0101.vo;

/**
 * @Class Name : MpSalesmanCustVO.java
 * @Description : MpSalesmanCust VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpSalesmanCustVO extends MpSalesmanCustDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** salesman */
    private java.lang.String salesman;
    
    /** salesman_cust */
    private java.lang.String salesmanCust;
    
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
    
    public java.lang.String getSalesman() {
        return this.salesman;
    }
    
    public void setSalesman(java.lang.String salesman) {
        this.salesman = salesman;
    }
    
    public java.lang.String getSalesmanCust() {
        return this.salesmanCust;
    }
    
    public void setSalesmanCust(java.lang.String salesmanCust) {
        this.salesmanCust = salesmanCust;
    }

	public java.lang.String getMdUser() {
		return mdUser;
	}

	public void setMdUser(java.lang.String mdUser) {
		this.mdUser = mdUser;
	}

	public java.sql.Timestamp getMdDate() {
		return mdDate;
	}

	public void setMdDate(java.sql.Timestamp mdDate) {
		this.mdDate = mdDate;
	}

	public java.lang.String getCrUser() {
		return crUser;
	}

	public void setCrUser(java.lang.String crUser) {
		this.crUser = crUser;
	}

	public java.sql.Timestamp getCrDate() {
		return crDate;
	}

	public void setCrDate(java.sql.Timestamp crDate) {
		this.crDate = crDate;
	}
    
}
