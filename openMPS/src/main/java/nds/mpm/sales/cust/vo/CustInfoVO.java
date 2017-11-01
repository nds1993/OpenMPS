package nds.mpm.sales.cust.vo;

import java.util.List;


/**
 * @Class Name : CustInfoVO.java
 * @Description : CustInfo VO class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.12
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class CustInfoVO extends CustInfoDefaultVO{
	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** CUST_CODE. */
	private String custCode;
	private List<String> custCodeList;

	/** CUST_KIND1. */
	private String custKind1;

	/** CUST_KIND2. */
	private String custKind2;

	/** CUST_KIND3. */
	private String custKind3;

	/** CUST_KIND4. */
	private String custKind4;

	/** CUST_KIND5. */
	private String custKind5;

	/** FAC_KIND. */
	private String facKind;

	/** FAC_CODE. */
	private String facCode;

	/** PRICE_CODE. */
	private String priceCode;

	/** DIST_ROUTE. */
	private String distRoute;

	/** CAR_CODE. */
	private String carCode;

	/** CUST_NAME. */
	private String custName;

	/** SHORT_NAME. */
	private String shortName;

	/** BEFORE_USER. */
	private String beforeUser;

	/** AFTER_USER. */
	private String afterUser;

	/** AFTER_DATE. */
	private String afterDate;

	/** HEAD_CODE. */
	private String headCode;

	/** TEAM_CODE. */
	private String teamCode;

	/** DEPT_CODE. */
	private String deptCode;

	/** GITA_CODE1. */
	private String gitaCode1;

	/** GITA_CODE2. */
	private String gitaCode2;

	/** LARGE_YN. */
	private String largeYn;

	/** LARGE_CODE. */
	private String largeCode;

	/** MIDD_YN. */
	private String middYn;

	/** MIDD_CODE. */
	private String middCode;

	/** RECE_YN. */
	private String receYn;

	/** RECE_CODE. */
	private String receCode;

	/** RECE_KIND. */
	private String receKind;

	/** RECE_DATE1. */
	private long receDate1;

	/** RECE_DATE2. */
	private long receDate2;

	/** ZIPC1. */
	private String zipc1;

	/** CITY1. */
	private String city1;

	/** TOWN1. */
	private String town1;

	/** DONG1. */
	private String dong1;

	/** ADDR1. */
	private String addr1;

	/** ADDR11. */
	private String addr11;

	/** ZIPC2. */
	private String zipc2;

	/** CITY2. */
	private String city2;

	/** TOWN2. */
	private String town2;

	/** DONG2. */
	private String dong2;

	/** ADDR2. */
	private String addr2;

	/** ADDR22. */
	private String addr22;

	/** OWNER. */
	private String owner;
	private String ownerCol;
	
	/** SSNO. */
	private String ssno;

	/** JUMIN. */
	private String jumin;

	/** UPTAE. */
	private String uptae;

	/** JONG. */
	private String jong;

	/** PHONE. */
	private String phone;

	/** MOBILE. */
	private String mobile;

	/** FAX. */
	private String fax;

	/** EMAIL. */
	private String email;

	/** AREA_CODE. */
	private String areaCode;

	/** AREA_NAME. */
	private String areaName;

	/** CREDIT. */
	private long credit;

	/** CREDIT_YN. */
	private String creditYn;

	/** TEMP_CREDIT. */
	private long tempCredit;

	/** TEMP_DATE. */
	private String tempDate;

	/** ONE_CREDIT. */
	private long oneCredit;

	/** BANK_NAME. */
	private String bankName;

	/** BANK_NO. */
	private String bankNo;

	/** BANK_USER. */
	private String bankUser;

	/** MISU_AMT. */
	private long misuAmt;

	/** DAM_CKIND. */
	private String damCkind;

	/** DAM_NKIND. */
	private String damNkind;

	/** DAM_PRICE. */
	private long damPrice;

	/** DAM_LIST. */
	private String damList;

	/** DAM_SDATE. */
	private String damSdate;

	/** DAM_EDATE. */
	private String damEdate;

	/** DAM_HDATE. */
	private String damHdate;

	/** DAM_COMP. */
	private String damComp;

	/** DAM_TEL. */
	private String damTel;

	/** DAM_BUIL. */
	private String damBuil;

	/** DAM_SRANK. */
	private String damSrank;

	/** DAM_SKUM. */
	private long damSkum;

	/** DAM_MEMO. */
	private String damMemo;

	/** CUST_BAD. */
	private String custBad;

	/** VAT_YN. */
	private String vatYn;

	/** ACC_YN. */
	private String accYn;

	/** EDIT_MEMO. */
	private String editMemo;

	/** LOG_YN. */
	private String logYn;

	/** LOG_PW. */
	private String logPw;

	/** ERP_KIND. */
	private String erpKind;

	/** ERP_CODE. */
	private String erpCode;

	/** PRINT_SER. */
	private long printSer;

	/** BRAND_YN. */
	private String brandYn;

	/** TRANS_YN. */
	private String transYn;

	/** MEMO. */
	private String memo;

	/** MD_DATE. */
	private String mdDate;

	/** MD_USER. */
	private String mdUser;

	/** DELE_YN. */
	private String deleYn;

	/** CR_USER. */
	private String crUser;

	/** CR_DATE. */
	private String crDate;

	/** GITA_CODE3. */
	private String gitaCode3;

	/** GITA_NAME3. */
	private String gitaName3;

	/** GITA_CODE4. */
	private String gitaCode4;

	/** GITA_NAME4. */
	private String gitaName4;

	/** GITA_CODE5. */
	private String gitaCode5;

	/** GITA_NAME5. */
	private String gitaName5;

	/**
	 * 생성자.
	 */
	public CustInfoVO() {
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

	public List<String> getCustCodeList() {
		return custCodeList;
	}

	public void setCustCodeList(List<String> custCodeList) {
		this.custCodeList = custCodeList;
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
	 * CUST_KIND1을 설정합니다..
	 * 
	 * @param custKind1
	 *            CUST_KIND1
	 */
	public void setCustKind1(String custKind1) {
		this.custKind1 = custKind1;
	}

	/**
	 * CUST_KIND1을 가져옵니다..
	 * 
	 * @return CUST_KIND1
	 */
	public String getCustKind1() {
		return this.custKind1;
	}

	/**
	 * CUST_KIND2을 설정합니다..
	 * 
	 * @param custKind2
	 *            CUST_KIND2
	 */
	public void setCustKind2(String custKind2) {
		this.custKind2 = custKind2;
	}

	/**
	 * CUST_KIND2을 가져옵니다..
	 * 
	 * @return CUST_KIND2
	 */
	public String getCustKind2() {
		return this.custKind2;
	}

	/**
	 * CUST_KIND3을 설정합니다..
	 * 
	 * @param custKind3
	 *            CUST_KIND3
	 */
	public void setCustKind3(String custKind3) {
		this.custKind3 = custKind3;
	}

	/**
	 * CUST_KIND3을 가져옵니다..
	 * 
	 * @return CUST_KIND3
	 */
	public String getCustKind3() {
		return this.custKind3;
	}

	/**
	 * CUST_KIND4을 설정합니다..
	 * 
	 * @param custKind4
	 *            CUST_KIND4
	 */
	public void setCustKind4(String custKind4) {
		this.custKind4 = custKind4;
	}

	/**
	 * CUST_KIND4을 가져옵니다..
	 * 
	 * @return CUST_KIND4
	 */
	public String getCustKind4() {
		return this.custKind4;
	}

	/**
	 * CUST_KIND5을 설정합니다..
	 * 
	 * @param custKind5
	 *            CUST_KIND5
	 */
	public void setCustKind5(String custKind5) {
		this.custKind5 = custKind5;
	}

	/**
	 * CUST_KIND5을 가져옵니다..
	 * 
	 * @return CUST_KIND5
	 */
	public String getCustKind5() {
		return this.custKind5;
	}

	/**
	 * FAC_KIND을 설정합니다..
	 * 
	 * @param facKind
	 *            FAC_KIND
	 */
	public void setFacKind(String facKind) {
		this.facKind = facKind;
	}

	/**
	 * FAC_KIND을 가져옵니다..
	 * 
	 * @return FAC_KIND
	 */
	public String getFacKind() {
		return this.facKind;
	}

	/**
	 * FAC_CODE을 설정합니다..
	 * 
	 * @param facCode
	 *            FAC_CODE
	 */
	public void setFacCode(String facCode) {
		this.facCode = facCode;
	}

	/**
	 * FAC_CODE을 가져옵니다..
	 * 
	 * @return FAC_CODE
	 */
	public String getFacCode() {
		return this.facCode;
	}

	/**
	 * PRICE_CODE을 설정합니다..
	 * 
	 * @param priceCode
	 *            PRICE_CODE
	 */
	public void setPriceCode(String priceCode) {
		this.priceCode = priceCode;
	}

	/**
	 * PRICE_CODE을 가져옵니다..
	 * 
	 * @return PRICE_CODE
	 */
	public String getPriceCode() {
		return this.priceCode;
	}

	/**
	 * DIST_ROUTE을 설정합니다..
	 * 
	 * @param distRoute
	 *            DIST_ROUTE
	 */
	public void setDistRoute(String distRoute) {
		this.distRoute = distRoute;
	}

	/**
	 * DIST_ROUTE을 가져옵니다..
	 * 
	 * @return DIST_ROUTE
	 */
	public String getDistRoute() {
		return this.distRoute;
	}

	/**
	 * CAR_CODE을 설정합니다..
	 * 
	 * @param carCode
	 *            CAR_CODE
	 */
	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	/**
	 * CAR_CODE을 가져옵니다..
	 * 
	 * @return CAR_CODE
	 */
	public String getCarCode() {
		return this.carCode;
	}

	/**
	 * CUST_NAME을 설정합니다..
	 * 
	 * @param custName
	 *            CUST_NAME
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * CUST_NAME을 가져옵니다..
	 * 
	 * @return CUST_NAME
	 */
	public String getCustName() {
		return this.custName;
	}

	/**
	 * SHORT_NAME을 설정합니다..
	 * 
	 * @param shortName
	 *            SHORT_NAME
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * SHORT_NAME을 가져옵니다..
	 * 
	 * @return SHORT_NAME
	 */
	public String getShortName() {
		return this.shortName;
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
	 * AFTER_DATE을 설정합니다..
	 * 
	 * @param afterDate
	 *            AFTER_DATE
	 */
	public void setAfterDate(String afterDate) {
		this.afterDate = afterDate;
	}

	/**
	 * AFTER_DATE을 가져옵니다..
	 * 
	 * @return AFTER_DATE
	 */
	public String getAfterDate() {
		return this.afterDate;
	}

	/**
	 * HEAD_CODE을 설정합니다..
	 * 
	 * @param headCode
	 *            HEAD_CODE
	 */
	public void setHeadCode(String headCode) {
		this.headCode = headCode;
	}

	/**
	 * HEAD_CODE을 가져옵니다..
	 * 
	 * @return HEAD_CODE
	 */
	public String getHeadCode() {
		return this.headCode;
	}

	/**
	 * TEAM_CODE을 설정합니다..
	 * 
	 * @param teamCode
	 *            TEAM_CODE
	 */
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	/**
	 * TEAM_CODE을 가져옵니다..
	 * 
	 * @return TEAM_CODE
	 */
	public String getTeamCode() {
		return this.teamCode;
	}

	/**
	 * DEPT_CODE을 설정합니다..
	 * 
	 * @param deptCode
	 *            DEPT_CODE
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
	 * DEPT_CODE을 가져옵니다..
	 * 
	 * @return DEPT_CODE
	 */
	public String getDeptCode() {
		return this.deptCode;
	}

	/**
	 * GITA_CODE1을 설정합니다..
	 * 
	 * @param gitaCode1
	 *            GITA_CODE1
	 */
	public void setGitaCode1(String gitaCode1) {
		this.gitaCode1 = gitaCode1;
	}

	/**
	 * GITA_CODE1을 가져옵니다..
	 * 
	 * @return GITA_CODE1
	 */
	public String getGitaCode1() {
		return this.gitaCode1;
	}

	/**
	 * GITA_CODE2을 설정합니다..
	 * 
	 * @param gitaCode2
	 *            GITA_CODE2
	 */
	public void setGitaCode2(String gitaCode2) {
		this.gitaCode2 = gitaCode2;
	}

	/**
	 * GITA_CODE2을 가져옵니다..
	 * 
	 * @return GITA_CODE2
	 */
	public String getGitaCode2() {
		return this.gitaCode2;
	}

	/**
	 * LARGE_YN을 설정합니다..
	 * 
	 * @param largeYn
	 *            LARGE_YN
	 */
	public void setLargeYn(String largeYn) {
		this.largeYn = largeYn;
	}

	/**
	 * LARGE_YN을 가져옵니다..
	 * 
	 * @return LARGE_YN
	 */
	public String getLargeYn() {
		return this.largeYn;
	}

	/**
	 * LARGE_CODE을 설정합니다..
	 * 
	 * @param largeCode
	 *            LARGE_CODE
	 */
	public void setLargeCode(String largeCode) {
		this.largeCode = largeCode;
	}

	/**
	 * LARGE_CODE을 가져옵니다..
	 * 
	 * @return LARGE_CODE
	 */
	public String getLargeCode() {
		return this.largeCode;
	}

	/**
	 * MIDD_YN을 설정합니다..
	 * 
	 * @param middYn
	 *            MIDD_YN
	 */
	public void setMiddYn(String middYn) {
		this.middYn = middYn;
	}

	/**
	 * MIDD_YN을 가져옵니다..
	 * 
	 * @return MIDD_YN
	 */
	public String getMiddYn() {
		return this.middYn;
	}

	/**
	 * MIDD_CODE을 설정합니다..
	 * 
	 * @param middCode
	 *            MIDD_CODE
	 */
	public void setMiddCode(String middCode) {
		this.middCode = middCode;
	}

	/**
	 * MIDD_CODE을 가져옵니다..
	 * 
	 * @return MIDD_CODE
	 */
	public String getMiddCode() {
		return this.middCode;
	}

	/**
	 * RECE_YN을 설정합니다..
	 * 
	 * @param receYn
	 *            RECE_YN
	 */
	public void setReceYn(String receYn) {
		this.receYn = receYn;
	}

	/**
	 * RECE_YN을 가져옵니다..
	 * 
	 * @return RECE_YN
	 */
	public String getReceYn() {
		return this.receYn;
	}

	/**
	 * RECE_CODE을 설정합니다..
	 * 
	 * @param receCode
	 *            RECE_CODE
	 */
	public void setReceCode(String receCode) {
		this.receCode = receCode;
	}

	/**
	 * RECE_CODE을 가져옵니다..
	 * 
	 * @return RECE_CODE
	 */
	public String getReceCode() {
		return this.receCode;
	}

	/**
	 * RECE_KIND을 설정합니다..
	 * 
	 * @param receKind
	 *            RECE_KIND
	 */
	public void setReceKind(String receKind) {
		this.receKind = receKind;
	}

	/**
	 * RECE_KIND을 가져옵니다..
	 * 
	 * @return RECE_KIND
	 */
	public String getReceKind() {
		return this.receKind;
	}

	/**
	 * RECE_DATE1을 설정합니다..
	 * 
	 * @param receDate1
	 *            RECE_DATE1
	 */
	public void setReceDate1(long receDate1) {
		this.receDate1 = receDate1;
	}

	/**
	 * RECE_DATE1을 가져옵니다..
	 * 
	 * @return RECE_DATE1
	 */
	public long getReceDate1() {
		return this.receDate1;
	}

	/**
	 * RECE_DATE2을 설정합니다..
	 * 
	 * @param receDate2
	 *            RECE_DATE2
	 */
	public void setReceDate2(long receDate2) {
		this.receDate2 = receDate2;
	}

	/**
	 * RECE_DATE2을 가져옵니다..
	 * 
	 * @return RECE_DATE2
	 */
	public long getReceDate2() {
		return this.receDate2;
	}

	/**
	 * ZIPC1을 설정합니다..
	 * 
	 * @param zipc1
	 *            ZIPC1
	 */
	public void setZipc1(String zipc1) {
		this.zipc1 = zipc1;
	}

	/**
	 * ZIPC1을 가져옵니다..
	 * 
	 * @return ZIPC1
	 */
	public String getZipc1() {
		return this.zipc1;
	}

	/**
	 * CITY1을 설정합니다..
	 * 
	 * @param city1
	 *            CITY1
	 */
	public void setCity1(String city1) {
		this.city1 = city1;
	}

	/**
	 * CITY1을 가져옵니다..
	 * 
	 * @return CITY1
	 */
	public String getCity1() {
		return this.city1;
	}

	/**
	 * TOWN1을 설정합니다..
	 * 
	 * @param town1
	 *            TOWN1
	 */
	public void setTown1(String town1) {
		this.town1 = town1;
	}

	/**
	 * TOWN1을 가져옵니다..
	 * 
	 * @return TOWN1
	 */
	public String getTown1() {
		return this.town1;
	}

	/**
	 * DONG1을 설정합니다..
	 * 
	 * @param dong1
	 *            DONG1
	 */
	public void setDong1(String dong1) {
		this.dong1 = dong1;
	}

	/**
	 * DONG1을 가져옵니다..
	 * 
	 * @return DONG1
	 */
	public String getDong1() {
		return this.dong1;
	}

	/**
	 * ADDR1을 설정합니다..
	 * 
	 * @param addr1
	 *            ADDR1
	 */
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	/**
	 * ADDR1을 가져옵니다..
	 * 
	 * @return ADDR1
	 */
	public String getAddr1() {
		return this.addr1;
	}

	/**
	 * ADDR11을 설정합니다..
	 * 
	 * @param addr11
	 *            ADDR11
	 */
	public void setAddr11(String addr11) {
		this.addr11 = addr11;
	}

	/**
	 * ADDR11을 가져옵니다..
	 * 
	 * @return ADDR11
	 */
	public String getAddr11() {
		return this.addr11;
	}

	/**
	 * ZIPC2을 설정합니다..
	 * 
	 * @param zipc2
	 *            ZIPC2
	 */
	public void setZipc2(String zipc2) {
		this.zipc2 = zipc2;
	}

	/**
	 * ZIPC2을 가져옵니다..
	 * 
	 * @return ZIPC2
	 */
	public String getZipc2() {
		return this.zipc2;
	}

	/**
	 * CITY2을 설정합니다..
	 * 
	 * @param city2
	 *            CITY2
	 */
	public void setCity2(String city2) {
		this.city2 = city2;
	}

	/**
	 * CITY2을 가져옵니다..
	 * 
	 * @return CITY2
	 */
	public String getCity2() {
		return this.city2;
	}

	/**
	 * TOWN2을 설정합니다..
	 * 
	 * @param town2
	 *            TOWN2
	 */
	public void setTown2(String town2) {
		this.town2 = town2;
	}

	/**
	 * TOWN2을 가져옵니다..
	 * 
	 * @return TOWN2
	 */
	public String getTown2() {
		return this.town2;
	}

	/**
	 * DONG2을 설정합니다..
	 * 
	 * @param dong2
	 *            DONG2
	 */
	public void setDong2(String dong2) {
		this.dong2 = dong2;
	}

	/**
	 * DONG2을 가져옵니다..
	 * 
	 * @return DONG2
	 */
	public String getDong2() {
		return this.dong2;
	}

	/**
	 * ADDR2을 설정합니다..
	 * 
	 * @param addr2
	 *            ADDR2
	 */
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	/**
	 * ADDR2을 가져옵니다..
	 * 
	 * @return ADDR2
	 */
	public String getAddr2() {
		return this.addr2;
	}

	/**
	 * ADDR22을 설정합니다..
	 * 
	 * @param addr22
	 *            ADDR22
	 */
	public void setAddr22(String addr22) {
		this.addr22 = addr22;
	}

	/**
	 * ADDR22을 가져옵니다..
	 * 
	 * @return ADDR22
	 */
	public String getAddr22() {
		return this.addr22;
	}

	/**
	 * OWNER을 설정합니다..
	 * 
	 * @param owner
	 *            OWNER
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * OWNER을 가져옵니다..
	 * 
	 * @return OWNER
	 */
	public String getOwner() {
		return this.owner;
	}

	
	public String getOwnerCol() {
		return ownerCol;
	}

	public void setOwnerCol(String ownerCol) {
		this.ownerCol = ownerCol;
	}

	/**
	 * SSNO을 설정합니다..
	 * 
	 * @param ssno
	 *            SSNO
	 */
	public void setSsno(String ssno) {
		this.ssno = ssno;
	}

	/**
	 * SSNO을 가져옵니다..
	 * 
	 * @return SSNO
	 */
	public String getSsno() {
		return this.ssno;
	}

	/**
	 * JUMIN을 설정합니다..
	 * 
	 * @param jumin
	 *            JUMIN
	 */
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}

	/**
	 * JUMIN을 가져옵니다..
	 * 
	 * @return JUMIN
	 */
	public String getJumin() {
		return this.jumin;
	}

	/**
	 * UPTAE을 설정합니다..
	 * 
	 * @param uptae
	 *            UPTAE
	 */
	public void setUptae(String uptae) {
		this.uptae = uptae;
	}

	/**
	 * UPTAE을 가져옵니다..
	 * 
	 * @return UPTAE
	 */
	public String getUptae() {
		return this.uptae;
	}

	/**
	 * JONG을 설정합니다..
	 * 
	 * @param jong
	 *            JONG
	 */
	public void setJong(String jong) {
		this.jong = jong;
	}

	/**
	 * JONG을 가져옵니다..
	 * 
	 * @return JONG
	 */
	public String getJong() {
		return this.jong;
	}

	/**
	 * PHONE을 설정합니다..
	 * 
	 * @param phone
	 *            PHONE
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * PHONE을 가져옵니다..
	 * 
	 * @return PHONE
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * MOBILE을 설정합니다..
	 * 
	 * @param mobile
	 *            MOBILE
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * MOBILE을 가져옵니다..
	 * 
	 * @return MOBILE
	 */
	public String getMobile() {
		return this.mobile;
	}

	/**
	 * FAX을 설정합니다..
	 * 
	 * @param fax
	 *            FAX
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * FAX을 가져옵니다..
	 * 
	 * @return FAX
	 */
	public String getFax() {
		return this.fax;
	}

	/**
	 * EMAIL을 설정합니다..
	 * 
	 * @param email
	 *            EMAIL
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * EMAIL을 가져옵니다..
	 * 
	 * @return EMAIL
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * AREA_CODE을 설정합니다..
	 * 
	 * @param areaCode
	 *            AREA_CODE
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * AREA_CODE을 가져옵니다..
	 * 
	 * @return AREA_CODE
	 */
	public String getAreaCode() {
		return this.areaCode;
	}

	/**
	 * AREA_NAME을 설정합니다..
	 * 
	 * @param areaName
	 *            AREA_NAME
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * AREA_NAME을 가져옵니다..
	 * 
	 * @return AREA_NAME
	 */
	public String getAreaName() {
		return this.areaName;
	}

	/**
	 * CREDIT을 설정합니다..
	 * 
	 * @param credit
	 *            CREDIT
	 */
	public void setCredit(long credit) {
		this.credit = credit;
	}

	/**
	 * CREDIT을 가져옵니다..
	 * 
	 * @return CREDIT
	 */
	public long getCredit() {
		return this.credit;
	}

	/**
	 * CREDIT_YN을 설정합니다..
	 * 
	 * @param creditYn
	 *            CREDIT_YN
	 */
	public void setCreditYn(String creditYn) {
		this.creditYn = creditYn;
	}

	/**
	 * CREDIT_YN을 가져옵니다..
	 * 
	 * @return CREDIT_YN
	 */
	public String getCreditYn() {
		return this.creditYn;
	}

	/**
	 * TEMP_CREDIT을 설정합니다..
	 * 
	 * @param tempCredit
	 *            TEMP_CREDIT
	 */
	public void setTempCredit(long tempCredit) {
		this.tempCredit = tempCredit;
	}

	/**
	 * TEMP_CREDIT을 가져옵니다..
	 * 
	 * @return TEMP_CREDIT
	 */
	public long getTempCredit() {
		return this.tempCredit;
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
	 * ONE_CREDIT을 설정합니다..
	 * 
	 * @param oneCredit
	 *            ONE_CREDIT
	 */
	public void setOneCredit(long oneCredit) {
		this.oneCredit = oneCredit;
	}

	/**
	 * ONE_CREDIT을 가져옵니다..
	 * 
	 * @return ONE_CREDIT
	 */
	public long getOneCredit() {
		return this.oneCredit;
	}

	/**
	 * BANK_NAME을 설정합니다..
	 * 
	 * @param bankName
	 *            BANK_NAME
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * BANK_NAME을 가져옵니다..
	 * 
	 * @return BANK_NAME
	 */
	public String getBankName() {
		return this.bankName;
	}

	/**
	 * BANK_NO을 설정합니다..
	 * 
	 * @param bankNo
	 *            BANK_NO
	 */
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	/**
	 * BANK_NO을 가져옵니다..
	 * 
	 * @return BANK_NO
	 */
	public String getBankNo() {
		return this.bankNo;
	}

	/**
	 * BANK_USER을 설정합니다..
	 * 
	 * @param bankUser
	 *            BANK_USER
	 */
	public void setBankUser(String bankUser) {
		this.bankUser = bankUser;
	}

	/**
	 * BANK_USER을 가져옵니다..
	 * 
	 * @return BANK_USER
	 */
	public String getBankUser() {
		return this.bankUser;
	}

	/**
	 * MISU_AMT을 설정합니다..
	 * 
	 * @param misuAmt
	 *            MISU_AMT
	 */
	public void setMisuAmt(long misuAmt) {
		this.misuAmt = misuAmt;
	}

	/**
	 * MISU_AMT을 가져옵니다..
	 * 
	 * @return MISU_AMT
	 */
	public long getMisuAmt() {
		return this.misuAmt;
	}

	/**
	 * DAM_CKIND을 설정합니다..
	 * 
	 * @param damCkind
	 *            DAM_CKIND
	 */
	public void setDamCkind(String damCkind) {
		this.damCkind = damCkind;
	}

	/**
	 * DAM_CKIND을 가져옵니다..
	 * 
	 * @return DAM_CKIND
	 */
	public String getDamCkind() {
		return this.damCkind;
	}

	/**
	 * DAM_NKIND을 설정합니다..
	 * 
	 * @param damNkind
	 *            DAM_NKIND
	 */
	public void setDamNkind(String damNkind) {
		this.damNkind = damNkind;
	}

	/**
	 * DAM_NKIND을 가져옵니다..
	 * 
	 * @return DAM_NKIND
	 */
	public String getDamNkind() {
		return this.damNkind;
	}

	/**
	 * DAM_PRICE을 설정합니다..
	 * 
	 * @param damPrice
	 *            DAM_PRICE
	 */
	public void setDamPrice(long damPrice) {
		this.damPrice = damPrice;
	}

	/**
	 * DAM_PRICE을 가져옵니다..
	 * 
	 * @return DAM_PRICE
	 */
	public long getDamPrice() {
		return this.damPrice;
	}

	/**
	 * DAM_LIST을 설정합니다..
	 * 
	 * @param damList
	 *            DAM_LIST
	 */
	public void setDamList(String damList) {
		this.damList = damList;
	}

	/**
	 * DAM_LIST을 가져옵니다..
	 * 
	 * @return DAM_LIST
	 */
	public String getDamList() {
		return this.damList;
	}

	/**
	 * DAM_SDATE을 설정합니다..
	 * 
	 * @param damSdate
	 *            DAM_SDATE
	 */
	public void setDamSdate(String damSdate) {
		this.damSdate = damSdate;
	}

	/**
	 * DAM_SDATE을 가져옵니다..
	 * 
	 * @return DAM_SDATE
	 */
	public String getDamSdate() {
		return this.damSdate;
	}

	/**
	 * DAM_EDATE을 설정합니다..
	 * 
	 * @param damEdate
	 *            DAM_EDATE
	 */
	public void setDamEdate(String damEdate) {
		this.damEdate = damEdate;
	}

	/**
	 * DAM_EDATE을 가져옵니다..
	 * 
	 * @return DAM_EDATE
	 */
	public String getDamEdate() {
		return this.damEdate;
	}

	/**
	 * DAM_HDATE을 설정합니다..
	 * 
	 * @param damHdate
	 *            DAM_HDATE
	 */
	public void setDamHdate(String damHdate) {
		this.damHdate = damHdate;
	}

	/**
	 * DAM_HDATE을 가져옵니다..
	 * 
	 * @return DAM_HDATE
	 */
	public String getDamHdate() {
		return this.damHdate;
	}

	/**
	 * DAM_COMP을 설정합니다..
	 * 
	 * @param damComp
	 *            DAM_COMP
	 */
	public void setDamComp(String damComp) {
		this.damComp = damComp;
	}

	/**
	 * DAM_COMP을 가져옵니다..
	 * 
	 * @return DAM_COMP
	 */
	public String getDamComp() {
		return this.damComp;
	}

	/**
	 * DAM_TEL을 설정합니다..
	 * 
	 * @param damTel
	 *            DAM_TEL
	 */
	public void setDamTel(String damTel) {
		this.damTel = damTel;
	}

	/**
	 * DAM_TEL을 가져옵니다..
	 * 
	 * @return DAM_TEL
	 */
	public String getDamTel() {
		return this.damTel;
	}

	/**
	 * DAM_BUIL을 설정합니다..
	 * 
	 * @param damBuil
	 *            DAM_BUIL
	 */
	public void setDamBuil(String damBuil) {
		this.damBuil = damBuil;
	}

	/**
	 * DAM_BUIL을 가져옵니다..
	 * 
	 * @return DAM_BUIL
	 */
	public String getDamBuil() {
		return this.damBuil;
	}

	/**
	 * DAM_SRANK을 설정합니다..
	 * 
	 * @param damSrank
	 *            DAM_SRANK
	 */
	public void setDamSrank(String damSrank) {
		this.damSrank = damSrank;
	}

	/**
	 * DAM_SRANK을 가져옵니다..
	 * 
	 * @return DAM_SRANK
	 */
	public String getDamSrank() {
		return this.damSrank;
	}

	/**
	 * DAM_SKUM을 설정합니다..
	 * 
	 * @param damSkum
	 *            DAM_SKUM
	 */
	public void setDamSkum(long damSkum) {
		this.damSkum = damSkum;
	}

	/**
	 * DAM_SKUM을 가져옵니다..
	 * 
	 * @return DAM_SKUM
	 */
	public long getDamSkum() {
		return this.damSkum;
	}

	/**
	 * DAM_MEMO을 설정합니다..
	 * 
	 * @param damMemo
	 *            DAM_MEMO
	 */
	public void setDamMemo(String damMemo) {
		this.damMemo = damMemo;
	}

	/**
	 * DAM_MEMO을 가져옵니다..
	 * 
	 * @return DAM_MEMO
	 */
	public String getDamMemo() {
		return this.damMemo;
	}

	/**
	 * CUST_BAD을 설정합니다..
	 * 
	 * @param custBad
	 *            CUST_BAD
	 */
	public void setCustBad(String custBad) {
		this.custBad = custBad;
	}

	/**
	 * CUST_BAD을 가져옵니다..
	 * 
	 * @return CUST_BAD
	 */
	public String getCustBad() {
		return this.custBad;
	}

	/**
	 * VAT_YN을 설정합니다..
	 * 
	 * @param vatYn
	 *            VAT_YN
	 */
	public void setVatYn(String vatYn) {
		this.vatYn = vatYn;
	}

	/**
	 * VAT_YN을 가져옵니다..
	 * 
	 * @return VAT_YN
	 */
	public String getVatYn() {
		return this.vatYn;
	}

	/**
	 * ACC_YN을 설정합니다..
	 * 
	 * @param accYn
	 *            ACC_YN
	 */
	public void setAccYn(String accYn) {
		this.accYn = accYn;
	}

	/**
	 * ACC_YN을 가져옵니다..
	 * 
	 * @return ACC_YN
	 */
	public String getAccYn() {
		return this.accYn;
	}

	/**
	 * EDIT_MEMO을 설정합니다..
	 * 
	 * @param editMemo
	 *            EDIT_MEMO
	 */
	public void setEditMemo(String editMemo) {
		this.editMemo = editMemo;
	}

	/**
	 * EDIT_MEMO을 가져옵니다..
	 * 
	 * @return EDIT_MEMO
	 */
	public String getEditMemo() {
		return this.editMemo;
	}

	/**
	 * LOG_YN을 설정합니다..
	 * 
	 * @param logYn
	 *            LOG_YN
	 */
	public void setLogYn(String logYn) {
		this.logYn = logYn;
	}

	/**
	 * LOG_YN을 가져옵니다..
	 * 
	 * @return LOG_YN
	 */
	public String getLogYn() {
		return this.logYn;
	}

	/**
	 * LOG_PW을 설정합니다..
	 * 
	 * @param logPw
	 *            LOG_PW
	 */
	public void setLogPw(String logPw) {
		this.logPw = logPw;
	}

	/**
	 * LOG_PW을 가져옵니다..
	 * 
	 * @return LOG_PW
	 */
	public String getLogPw() {
		return this.logPw;
	}

	/**
	 * ERP_KIND을 설정합니다..
	 * 
	 * @param erpKind
	 *            ERP_KIND
	 */
	public void setErpKind(String erpKind) {
		this.erpKind = erpKind;
	}

	/**
	 * ERP_KIND을 가져옵니다..
	 * 
	 * @return ERP_KIND
	 */
	public String getErpKind() {
		return this.erpKind;
	}

	/**
	 * ERP_CODE을 설정합니다..
	 * 
	 * @param erpCode
	 *            ERP_CODE
	 */
	public void setErpCode(String erpCode) {
		this.erpCode = erpCode;
	}

	/**
	 * ERP_CODE을 가져옵니다..
	 * 
	 * @return ERP_CODE
	 */
	public String getErpCode() {
		return this.erpCode;
	}

	/**
	 * PRINT_SER을 설정합니다..
	 * 
	 * @param printSer
	 *            PRINT_SER
	 */
	public void setPrintSer(long printSer) {
		this.printSer = printSer;
	}

	/**
	 * PRINT_SER을 가져옵니다..
	 * 
	 * @return PRINT_SER
	 */
	public long getPrintSer() {
		return this.printSer;
	}

	/**
	 * BRAND_YN을 설정합니다..
	 * 
	 * @param brandYn
	 *            BRAND_YN
	 */
	public void setBrandYn(String brandYn) {
		this.brandYn = brandYn;
	}

	/**
	 * BRAND_YN을 가져옵니다..
	 * 
	 * @return BRAND_YN
	 */
	public String getBrandYn() {
		return this.brandYn;
	}

	/**
	 * TRANS_YN을 설정합니다..
	 * 
	 * @param transYn
	 *            TRANS_YN
	 */
	public void setTransYn(String transYn) {
		this.transYn = transYn;
	}

	/**
	 * TRANS_YN을 가져옵니다..
	 * 
	 * @return TRANS_YN
	 */
	public String getTransYn() {
		return this.transYn;
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

	/**
	 * GITA_CODE3을 설정합니다..
	 * 
	 * @param gitaCode3
	 *            GITA_CODE3
	 */
	public void setGitaCode3(String gitaCode3) {
		this.gitaCode3 = gitaCode3;
	}

	/**
	 * GITA_CODE3을 가져옵니다..
	 * 
	 * @return GITA_CODE3
	 */
	public String getGitaCode3() {
		return this.gitaCode3;
	}

	/**
	 * GITA_NAME3을 설정합니다..
	 * 
	 * @param gitaName3
	 *            GITA_NAME3
	 */
	public void setGitaName3(String gitaName3) {
		this.gitaName3 = gitaName3;
	}

	/**
	 * GITA_NAME3을 가져옵니다..
	 * 
	 * @return GITA_NAME3
	 */
	public String getGitaName3() {
		return this.gitaName3;
	}

	/**
	 * GITA_CODE4을 설정합니다..
	 * 
	 * @param gitaCode4
	 *            GITA_CODE4
	 */
	public void setGitaCode4(String gitaCode4) {
		this.gitaCode4 = gitaCode4;
	}

	/**
	 * GITA_CODE4을 가져옵니다..
	 * 
	 * @return GITA_CODE4
	 */
	public String getGitaCode4() {
		return this.gitaCode4;
	}

	/**
	 * GITA_NAME4을 설정합니다..
	 * 
	 * @param gitaName4
	 *            GITA_NAME4
	 */
	public void setGitaName4(String gitaName4) {
		this.gitaName4 = gitaName4;
	}

	/**
	 * GITA_NAME4을 가져옵니다..
	 * 
	 * @return GITA_NAME4
	 */
	public String getGitaName4() {
		return this.gitaName4;
	}

	/**
	 * GITA_CODE5을 설정합니다..
	 * 
	 * @param gitaCode5
	 *            GITA_CODE5
	 */
	public void setGitaCode5(String gitaCode5) {
		this.gitaCode5 = gitaCode5;
	}

	/**
	 * GITA_CODE5을 가져옵니다..
	 * 
	 * @return GITA_CODE5
	 */
	public String getGitaCode5() {
		return this.gitaCode5;
	}

	/**
	 * GITA_NAME5을 설정합니다..
	 * 
	 * @param gitaName5
	 *            GITA_NAME5
	 */
	public void setGitaName5(String gitaName5) {
		this.gitaName5 = gitaName5;
	}

	/**
	 * GITA_NAME5을 가져옵니다..
	 * 
	 * @return GITA_NAME5
	 */
	public String getGitaName5() {
		return this.gitaName5;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custCode == null) ? 0 : custCode.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CustInfoVO other = (CustInfoVO) obj;
		if (custCode == null) {
			if (other.custCode != null) {
				return false;
			}
		} else if (!custCode.equals(other.custCode)) {
			return false;
		}
		return true;
	}
    
}
