package nds.mpm.sales.credit.vo;

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
 * 한도이력관리
 * src/main/resources/nds/sqlmap/mpm/sales/credit/creditInfo/CreditInfo_PostgreSQL.xml
 *  
 *  Copyright (C)  All right reserved.
 */
public class CreditInfoVO extends CreditInfoDefaultVO{
	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** CREDIT_CODE. */
	private long creditCode;

	/** CUST_CODE. */
	private String custCode;

	/** CREDIT_DATE. */
	private String creditDate;

	/** CREDIT_PRICE. */
	private long creditPrice;

	/** TEMP_PRICE. */
	private long tempPrice;

	/** TEMP_DATE. */
	private String tempDate;

	/** ONE_PRICE. */
	private long onePrice;

	/** MEMO. */
	private String memo;

	/** DELE_YN. */
	private String deleYn;

	/** MD_USER. */
	private String mdUser;

	/** MD_DATE. */
	private String mdDate;

	/** CR_USER. */
	private String crUser;

	/** CR_DATE. */
	private String crDate;
	
	private CustInfoVO custInfo;

	/**
	 * 생성자.
	 */
	public CreditInfoVO() {
	}

	/**
	 * CREDIT_CODE을 설정합니다..
	 * 
	 * @param creditCode
	 *            CREDIT_CODE
	 */
	public void setCreditCode(long creditCode) {
		this.creditCode = creditCode;
	}

	/**
	 * CREDIT_CODE을 가져옵니다..
	 * 
	 * @return CREDIT_CODE
	 */
	public long getCreditCode() {
		return this.creditCode;
	}

	/**
	 * CUST_CODE을 설정합니다..
	 * 
	 * @param custCode
	 *            CUST_CODE
	 */
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	/**
	 * CUST_CODE을 가져옵니다..
	 * 
	 * @return CUST_CODE
	 */
	public String getCustCode() {
		return this.custCode;
	}

	/**
	 * CREDIT_DATE을 설정합니다..
	 * 
	 * @param creditDate
	 *            CREDIT_DATE
	 */
	public void setCreditDate(String creditDate) {
		this.creditDate = creditDate;
	}

	/**
	 * CREDIT_DATE을 가져옵니다..
	 * 
	 * @return CREDIT_DATE
	 */
	public String getCreditDate() {
		return this.creditDate;
	}

	/**
	 * CREDIT_PRICE을 설정합니다..
	 * 
	 * @param creditPrice
	 *            CREDIT_PRICE
	 */
	public void setCreditPrice(long creditPrice) {
		this.creditPrice = creditPrice;
	}

	/**
	 * CREDIT_PRICE을 가져옵니다..
	 * 
	 * @return CREDIT_PRICE
	 */
	public long getCreditPrice() {
		return this.creditPrice;
	}

	/**
	 * TEMP_PRICE을 설정합니다..
	 * 
	 * @param tempPrice
	 *            TEMP_PRICE
	 */
	public void setTempPrice(long tempPrice) {
		this.tempPrice = tempPrice;
	}

	/**
	 * TEMP_PRICE을 가져옵니다..
	 * 
	 * @return TEMP_PRICE
	 */
	public long getTempPrice() {
		return this.tempPrice;
	}

	/**
	 * TEMP_DATE을 설정합니다..
	 * 
	 * @param tempDate
	 *            TEMP_DATE
	 */
	public void setTempDate(String tempDate) {
		this.tempDate = tempDate;
	}

	/**
	 * TEMP_DATE을 가져옵니다..
	 * 
	 * @return TEMP_DATE
	 */
	public String getTempDate() {
		return this.tempDate;
	}

	/**
	 * ONE_PRICE을 설정합니다..
	 * 
	 * @param onePrice
	 *            ONE_PRICE
	 */
	public void setOnePrice(long onePrice) {
		this.onePrice = onePrice;
	}

	/**
	 * ONE_PRICE을 가져옵니다..
	 * 
	 * @return ONE_PRICE
	 */
	public long getOnePrice() {
		return this.onePrice;
	}

	/**
	 * MEMO을 설정합니다..
	 * 
	 * @param memo
	 *            MEMO
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * MEMO을 가져옵니다..
	 * 
	 * @return MEMO
	 */
	public String getMemo() {
		return this.memo;
	}

	/**
	 * DELE_YN을 설정합니다..
	 * 
	 * @param deleYn
	 *            DELE_YN
	 */
	public void setDeleYn(String deleYn) {
		this.deleYn = deleYn;
	}

	/**
	 * DELE_YN을 가져옵니다..
	 * 
	 * @return DELE_YN
	 */
	public String getDeleYn() {
		return this.deleYn;
	}

	/**
	 * MD_USER을 설정합니다..
	 * 
	 * @param mdUser
	 *            MD_USER
	 */
	public void setMdUser(String mdUser) {
		this.mdUser = mdUser;
	}

	/**
	 * MD_USER을 가져옵니다..
	 * 
	 * @return MD_USER
	 */
	public String getMdUser() {
		return this.mdUser;
	}

	/**
	 * MD_DATE을 설정합니다..
	 * 
	 * @param mdDate
	 *            MD_DATE
	 */
	public void setMdDate(String mdDate) {
		this.mdDate = mdDate;
	}

	/**
	 * MD_DATE을 가져옵니다..
	 * 
	 * @return MD_DATE
	 */
	public String getMdDate() {
		return this.mdDate;
	}

	/**
	 * CR_USER을 설정합니다..
	 * 
	 * @param crUser
	 *            CR_USER
	 */
	public void setCrUser(String crUser) {
		this.crUser = crUser;
	}

	/**
	 * CR_USER을 가져옵니다..
	 * 
	 * @return CR_USER
	 */
	public String getCrUser() {
		return this.crUser;
	}

	/**
	 * CR_DATE을 설정합니다..
	 * 
	 * @param crDate
	 *            CR_DATE
	 */
	public void setCrDate(String crDate) {
		this.crDate = crDate;
	}

	/**
	 * CR_DATE을 가져옵니다..
	 * 
	 * @return CR_DATE
	 */
	public String getCrDate() {
		return this.crDate;
	}

	public CustInfoVO getCustInfo() {
		return custInfo;
	}

	public void setCustInfo(CustInfoVO custInfo) {
		this.custInfo = custInfo;
	}

    
}
