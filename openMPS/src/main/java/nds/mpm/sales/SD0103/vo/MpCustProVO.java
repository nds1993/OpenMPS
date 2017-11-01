package nds.mpm.sales.SD0103.vo;

/**
 * @Class Name : MpCustProVO.java
 * @Description : MpCustPro VO class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpCustProVO extends MpCustProDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** salesman */
    private java.lang.String salesman;
    
    /** salesman_cust */
    private java.lang.String salesmanCust;
    
    /** salesman_prod */
    private java.lang.String salesmanProd;
    
    /** price_type */
    private java.lang.String priceType;
    
    /** price_class */
    private java.lang.String priceClass;
    
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
    
    public java.lang.String getSalesmanProd() {
        return this.salesmanProd;
    }
    
    public void setSalesmanProd(java.lang.String salesmanProd) {
        this.salesmanProd = salesmanProd;
    }
    
    public java.lang.String getPriceType() {
        return this.priceType;
    }
    
    public void setPriceType(java.lang.String priceType) {
        this.priceType = priceType;
    }
    
    public java.lang.String getPriceClass() {
        return this.priceClass;
    }
    
    public void setPriceClass(java.lang.String priceClass) {
        this.priceClass = priceClass;
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
