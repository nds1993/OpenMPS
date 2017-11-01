package nds.mpm.sales.credit.vo;

import java.io.Serializable;

import nds.mpm.sales.cust.vo.CustInfoVO;

/**
 * @Class Name : CreditInfoVO.java
 * @Description : CreditInfo VO class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.12
 * @version 1.0
 * @see
 * 
 * 잔액조회서
 * /src/main/resources/nds/sqlmap/mpm/sales/credit/outputInfo/OutputInfo_PostgreSQL.xml
 * outputInfoDAO.selectOutputInfoList_Misu
 *  
 *  Copyright (C)  All right reserved.
 */
public class MisuAmtVO  implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chk;
	private String endDate;
	private String custName;
	private long misuAmt;
	private String ownerName;
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public long getMisuAmt() {
		return misuAmt;
	}
	public void setMisuAmt(long misuAmt) {
		this.misuAmt = misuAmt;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
    
	
}
