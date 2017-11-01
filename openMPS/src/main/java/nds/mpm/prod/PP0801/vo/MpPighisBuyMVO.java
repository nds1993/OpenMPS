package nds.mpm.prod.PP0801.vo;

/**
 * @Class Name : MpPighisBuyMVO.java
 * @Description : MpPighisBuyM VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpPighisBuyMVO extends MpPighisBuyMDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** buy_date */
    private java.lang.String buyDate;
    
    /** buy_date */
    private java.lang.String buyDateM;
    
    /** buy_date */
    private java.lang.String buyDateD;
    
    /** his_no */
    private java.lang.String hisNo;
    
    /** doch_no */
    private java.lang.String dochNo;
    
    /** sum_weig */
    private double sumWeig;
    
    /** grade_gubun1 */
    private java.lang.String gradeGubun1;
    
    /** grade_gubun2 */
    private java.lang.String gradeGubun2;
    
    /** buy_type */
    private java.lang.String buyType;

    /** buy_type */
    private java.lang.String buyTypename;
    
    /** base_part_code */
    private java.lang.String basePartCode;
    
    /** base_part_name */
    private java.lang.String basePartName;
    
    /** comp_ssno */
    private java.lang.String compSsno;
    
    /** comp_name */
    private java.lang.String compName;
    
    /** comp_owner */
    private java.lang.String compOwner;
    
    /** comp_tel */
    private java.lang.String compTel;
    
    /** comp_addr */
    private java.lang.String compAddr;
    
    /** cust_name */
    private java.lang.String custName;
    
    /** gita_pan */
    private java.lang.String gitaPan;
    
    /** gita_type */
    private java.lang.String gitaType;
    
    /** gita_sex */
    private java.lang.String gitaSex;
    
    /** gita_weig */
    private double gitaWeig;
    
    /** gita_fat_thick */
    private double gitaFatThick;
    
    /** api_time */
    private java.sql.Timestamp apiTime;
    
    /** memo */
    private java.lang.String memo;
    
    /** dele_yn */
    private java.lang.String deleYn;
    
    /** md_user */
    private java.lang.String mdUser;
    
    /** md_date */
    private java.sql.Timestamp mdDate;
    
    /** cr_user */
    private java.lang.String crUser;
    
    /** cr_date */
    private java.sql.Timestamp crDate;
    

	String sendType;
    
    public java.lang.String getCorpCode() {
        return this.corpCode;
    }
    
    public void setCorpCode(java.lang.String corpCode) {
        this.corpCode = corpCode;
    }
    
    public java.lang.String getBuyDate() {
        return this.buyDate;
    }
    
    public void setBuyDate(java.lang.String buyDate) {
        this.buyDate = buyDate;
    }
    
    public java.lang.String getHisNo() {
        return this.hisNo;
    }
    
    public void setHisNo(java.lang.String hisNo) {
        this.hisNo = hisNo;
    }
    
    public java.lang.String getDochNo() {
        return this.dochNo;
    }
    
    public void setDochNo(java.lang.String dochNo) {
        this.dochNo = dochNo;
    }
    
    
    public java.lang.String getGradeGubun1() {
        return this.gradeGubun1;
    }
    
    public void setGradeGubun1(java.lang.String gradeGubun1) {
        this.gradeGubun1 = gradeGubun1;
    }
    
    public java.lang.String getGradeGubun2() {
        return this.gradeGubun2;
    }
    
    public void setGradeGubun2(java.lang.String gradeGubun2) {
        this.gradeGubun2 = gradeGubun2;
    }
    
    public java.lang.String getBuyType() {
        return this.buyType;
    }
    
    public void setBuyType(java.lang.String buyType) {
        this.buyType = buyType;
    }
    
    public java.lang.String getBasePartCode() {
        return this.basePartCode;
    }
    
    public void setBasePartCode(java.lang.String basePartCode) {
        this.basePartCode = basePartCode;
    }
    
    public java.lang.String getBasePartName() {
        return this.basePartName;
    }
    
    public void setBasePartName(java.lang.String basePartName) {
        this.basePartName = basePartName;
    }
    
    public java.lang.String getCompSsno() {
        return this.compSsno;
    }
    
    public void setCompSsno(java.lang.String compSsno) {
        this.compSsno = compSsno;
    }
    
    public java.lang.String getCompName() {
        return this.compName;
    }
    
    public void setCompName(java.lang.String compName) {
        this.compName = compName;
    }
    
    public java.lang.String getCompOwner() {
        return this.compOwner;
    }
    
    public void setCompOwner(java.lang.String compOwner) {
        this.compOwner = compOwner;
    }
    
    public java.lang.String getCompTel() {
        return this.compTel;
    }
    
    public void setCompTel(java.lang.String compTel) {
        this.compTel = compTel;
    }
    
    public java.lang.String getCompAddr() {
        return this.compAddr;
    }
    
    public void setCompAddr(java.lang.String compAddr) {
        this.compAddr = compAddr;
    }
    
    public java.lang.String getCustName() {
        return this.custName;
    }
    
    public void setCustName(java.lang.String custName) {
        this.custName = custName;
    }
    
    public java.lang.String getGitaPan() {
        return this.gitaPan;
    }
    
    public void setGitaPan(java.lang.String gitaPan) {
        this.gitaPan = gitaPan;
    }
    
    public java.lang.String getGitaType() {
        return this.gitaType;
    }
    
    public void setGitaType(java.lang.String gitaType) {
        this.gitaType = gitaType;
    }
    
    public java.lang.String getGitaSex() {
        return this.gitaSex;
    }
    
    public void setGitaSex(java.lang.String gitaSex) {
        this.gitaSex = gitaSex;
    }
    
    
    public double getSumWeig() {
		return sumWeig;
	}

	public void setSumWeig(double sumWeig) {
		this.sumWeig = sumWeig;
	}

	public double getGitaWeig() {
		return gitaWeig;
	}

	public void setGitaWeig(double gitaWeig) {
		this.gitaWeig = gitaWeig;
	}

	public double getGitaFatThick() {
		return gitaFatThick;
	}

	public void setGitaFatThick(double gitaFatThick) {
		this.gitaFatThick = gitaFatThick;
	}

	public java.sql.Timestamp getApiTime() {
        return this.apiTime;
    }
    
    public void setApiTime(java.sql.Timestamp apiTime) {
        this.apiTime = apiTime;
    }
    
    public java.lang.String getMemo() {
        return this.memo;
    }
    
    public void setMemo(java.lang.String memo) {
        this.memo = memo;
    }
    
    public java.lang.String getDeleYn() {
        return this.deleYn;
    }
    
    public void setDeleYn(java.lang.String deleYn) {
        this.deleYn = deleYn;
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

	public java.lang.String getBuyTypename() {
		return buyTypename;
	}

	public void setBuyTypename(java.lang.String buyTypename) {
		this.buyTypename = buyTypename;
	}

	public java.lang.String getBuyDateM() {
		return buyDateM;
	}

	public void setBuyDateM(java.lang.String buyDateM) {
		this.buyDateM = buyDateM;
	}

	public java.lang.String getBuyDateD() {
		return buyDateD;
	}

	public void setBuyDateD(java.lang.String buyDateD) {
		this.buyDateD = buyDateD;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
    
}
