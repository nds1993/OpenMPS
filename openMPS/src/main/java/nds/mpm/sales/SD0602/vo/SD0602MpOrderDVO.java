package nds.mpm.sales.SD0602.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Class Name : MpOrderDVO.java
 * @Description : MpOrderD VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class SD0602MpOrderDVO extends SD0602MpOrderDDefaultVO{
    private static final long serialVersionUID = 1L;
    
    private String	chgoGubn;
    
    private String	partGubn;    
    
	private String	manGubn;
    
    private String	prodGubn;
    
    private String	custGubn;
    
    private String[] chgoGubnList;		//출고구분 조회값
    
    private String[] partGubnList;		//영업파트 조회값
    
    private String[] manGubnList;		//영업사원 조회값
    
    private String[] prodGubnList;		//제품코드 조회값
    
    private String[] custGubnList;		//거래처코드 조회값

	/** corp_code	회사 구분 */
    private java.lang.String corpCode;
    
    /** delv_date 	일자 */
    private java.lang.String delvDate;
    
    /** part_code 	영업파트 */
    private java.lang.String partCode;
    
    /** salesman 	영업사원 */
    private java.lang.String salesMan;
    
    
    /** ordr_cust 	거래처코드 */
    private java.lang.String ordrCust;
    
    /** cust_name 	거래처명 */
    private java.lang.String custName;
    
    /** sale_group2 품목 */
    private java.lang.String saleGroup2;
    
    /** pro_code 	제품코드 */
    private java.lang.String proCode;
    
    /** pro_name 	제품명 */
    private java.lang.String proName;
    
    /** delv_box 	수량*/
    private java.math.BigDecimal delvBox;
    
    /** delv_weight 중량*/
    private java.math.BigDecimal delvWeight;
    
    /** unit_price 	단가*/
    private java.math.BigDecimal unitPrice;
    
    /** delv_amt 	금액*/
    private java.math.BigDecimal delvAmt;
    
    /** delv_vat 	부가세*/
    private java.math.BigDecimal delvVat;
    
    /** delv_tot 	합계금액*/
    private java.math.BigDecimal delvTot;
    
    /** dist_type 		유통경로*/
    private java.lang.String distType;
    
    /** sale_comb_cust 	판매취합*/
    private java.lang.String salecombCust;

	public java.lang.String getCorpCode() {
		return corpCode;
	}

	public void setCorpCode(java.lang.String corpCode) {
		this.corpCode = corpCode;
	}

	public java.lang.String getDelvDate() {
		return delvDate;
	}

	public void setDelvDate(java.lang.String delvDate) {
		this.delvDate = delvDate;
	}

	public java.lang.String getPartCode() {
		return partCode;
	}

	public void setPartCode(java.lang.String partCode) {
		this.partCode = partCode;
	}

	public java.lang.String getSalesMan() {
		return salesMan;
	}

	public void setSalesMan(java.lang.String salesMan) {
		this.salesMan = salesMan;
	}

	public java.lang.String getOrdrCust() {
		return ordrCust;
	}

	public void setOrdrCust(java.lang.String ordrCust) {
		this.ordrCust = ordrCust;
	}

	public java.lang.String getCustName() {
		return custName;
	}

	public void setCustName(java.lang.String custName) {
		this.custName = custName;
	}

	public java.lang.String getSaleGroup2() {
		return saleGroup2;
	}

	public void setSaleGroup2(java.lang.String saleGroup2) {
		this.saleGroup2 = saleGroup2;
	}

	public java.lang.String getProCode() {
		return proCode;
	}

	public void setProCode(java.lang.String proCode) {
		this.proCode = proCode;
	}

	public java.lang.String getProName() {
		return proName;
	}

	public void setProName(java.lang.String proName) {
		this.proName = proName;
	}

	public java.math.BigDecimal getDelvBox() {
		return delvBox;
	}

	public void setDelvBox(java.math.BigDecimal delvBox) {
		this.delvBox = delvBox;
	}

	public java.math.BigDecimal getDelvWeight() {
		return delvWeight;
	}

	public void setDelvWeight(java.math.BigDecimal delvWeight) {
		this.delvWeight = delvWeight;
	}

	public java.math.BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(java.math.BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public java.math.BigDecimal getDelvAmt() {
		return delvAmt;
	}

	public void setDelvAmt(java.math.BigDecimal delvAmt) {
		this.delvAmt = delvAmt;
	}

	public java.math.BigDecimal getDelvVat() {
		return delvVat;
	}

	public void setDelvVat(java.math.BigDecimal delvVat) {
		this.delvVat = delvVat;
	}

	public java.math.BigDecimal getDelvTot() {
		return delvTot;
	}

	public void setDelvTot(java.math.BigDecimal delvTot) {
		this.delvTot = delvTot;
	}

	public java.lang.String getDistType() {
		return distType;
	}

	public void setDistType(java.lang.String distType) {
		this.distType = distType;
	}

	public java.lang.String getSalecombCust() {
		return salecombCust;
	}

	public void setSalecombCust(java.lang.String salecombCust) {
		this.salecombCust = salecombCust;
	}
    
	public String getChgoGubn() {
		return chgoGubn;
	}

	public void setChgoGubn(String chgoGubn) {
		this.chgoGubn = chgoGubn;
	}

	public String getPartGubn() {
		return partGubn;
	}

	public void setPartGubn(String partGubn) {
		this.partGubn = partGubn;
	}

	public String getManGubn() {
		return manGubn;
	}

	public void setManGubn(String manGubn) {
		this.manGubn = manGubn;
	}

	public String getProdGubn() {
		return prodGubn;
	}

	public void setProdGubn(String prodGubn) {
		this.prodGubn = prodGubn;
	}

	public String getCustGubn() {
		return custGubn;
	}

	public void setCustGubn(String custGubn) {
		this.custGubn = custGubn;
	}

	public String[] getChgoGubnList() {
		return chgoGubnList;
	}

	public void setChgoGubnList(String[] chgoGubnList) {
		this.chgoGubnList = chgoGubnList;
	}

	public String[] getPartGubnList() {
		return partGubnList;
	}

	public void setPartGubnList(String[] partGubnList) {
		this.partGubnList = partGubnList;
	}

	public String[] getManGubnList() {
		return manGubnList;
	}

	public void setManGubnList(String[] manGubnList) {
		this.manGubnList = manGubnList;
	}

	public String[] getProdGubnList() {
		return prodGubnList;
	}

	public void setProdGubnList(String[] prodGubnList) {
		this.prodGubnList = prodGubnList;
	}

	public String[] getCustGubnList() {
		return custGubnList;
	}

	public void setCustGubnList(String[] custGubnList) {
		this.custGubnList = custGubnList;
	}
}
