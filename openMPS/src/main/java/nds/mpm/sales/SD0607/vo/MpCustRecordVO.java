package nds.mpm.sales.SD0607.vo;

import nds.mpm.sales.SD0607.vo.MpCustRecordDefaultVO;

/**
 * @Class Name : MpCustRecordVO.java
 * @Description : MpCustRecordVO VO class
 * @Modification Information
 *
 * @author JAR
 * @since 2017.09.20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpCustRecordVO extends MpCustRecordDefaultVO{

	private static final long serialVersionUID = 1L;
	
	 /** corp_code */
    private java.lang.String corpCode;
    
    /** strtYyyy */
    private java.lang.String strtYyyy;
    
    /** strtLastYyyy */
    private java.lang.String strtPreYyyy;
    
    /** teamCode */
    private java.lang.String teamCode;
    
    /** salesman */
    private java.lang.String salesman; 
    
    /** custCode */
    private java.lang.String custCode;

	public java.lang.String getCorpCode() {
		return corpCode;
	}

	public void setCorpCode(java.lang.String corpCode) {
		this.corpCode = corpCode;
	}

	public java.lang.String getStrtYyyy() {
		return strtYyyy;
	}

	public void setStrtYyyy(java.lang.String strtYyyy) {
		this.strtYyyy = strtYyyy;
	}

	public java.lang.String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(java.lang.String teamCode) {
		this.teamCode = teamCode;
	}

	public java.lang.String getSalesman() {
		return salesman;
	}

	public void setSalesman(java.lang.String salesman) {
		this.salesman = salesman;
	}

	public java.lang.String getCustCode() {
		return custCode;
	}

	public void setCustCode(java.lang.String custCode) {
		this.custCode = custCode;
	}

	public java.lang.String getStrtPreYyyy() {
		return strtPreYyyy;
	}

	public void setStrtPreYyyy(java.lang.String strtPreYyyy) {
		this.strtPreYyyy = strtPreYyyy;
	}
}
