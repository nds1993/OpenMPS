package nds.mpm.sales.cust.vo;


/**
 * @Class Name : CustMhisVO.java
 * @Description : CustMhis VO class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.12
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class CustMhisVO extends CustMhisDefaultVO{
	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** MHIS_NO. */
	private long mhisNo;

	/** MHIS_DATE. */
	private String mhisDate;

	/** BEFORE_USER. */
	private String beforeUser;

	/** AFTER_USER. */
	private String afterUser;

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

	/**
	 * 생성자.
	 */
	public CustMhisVO() {
	}

	/**
	 * MHIS_NO을 설정합니다..
	 * 
	 * @param mhisNo
	 *            MHIS_NO
	 */
	public void setMhisNo(long mhisNo) {
		this.mhisNo = mhisNo;
	}

	/**
	 * MHIS_NO을 가져옵니다..
	 * 
	 * @return MHIS_NO
	 */
	public long getMhisNo() {
		return this.mhisNo;
	}

	/**
	 * MHIS_DATE을 설정합니다..
	 * 
	 * @param mhisDate
	 *            MHIS_DATE
	 */
	public void setMhisDate(String mhisDate) {
		this.mhisDate = mhisDate;
	}

	/**
	 * MHIS_DATE을 가져옵니다..
	 * 
	 * @return MHIS_DATE
	 */
	public String getMhisDate() {
		return this.mhisDate;
	}

	/**
	 * BEFORE_USER을 설정합니다..
	 * 
	 * @param beforeUser
	 *            BEFORE_USER
	 */
	public void setBeforeUser(String beforeUser) {
		this.beforeUser = beforeUser;
	}

	/**
	 * BEFORE_USER을 가져옵니다..
	 * 
	 * @return BEFORE_USER
	 */
	public String getBeforeUser() {
		return this.beforeUser;
	}

	/**
	 * AFTER_USER을 설정합니다..
	 * 
	 * @param afterUser
	 *            AFTER_USER
	 */
	public void setAfterUser(String afterUser) {
		this.afterUser = afterUser;
	}

	/**
	 * AFTER_USER을 가져옵니다..
	 * 
	 * @return AFTER_USER
	 */
	public String getAfterUser() {
		return this.afterUser;
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
    
}
