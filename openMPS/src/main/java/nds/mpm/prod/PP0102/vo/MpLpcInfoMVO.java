package nds.mpm.prod.PP0102.vo;

/**
 * @Class Name : MpLpcInfoMVO.java
 * @Description : MpLpcInfoM VO class
 * @Modification Information
 *
 * @author MPM
 * @since 2017
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpLpcInfoMVO extends MpLpcInfoMDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** lpc_code */
    private java.lang.String lpcCode;
    
    /** lpc_name */
    private java.lang.String lpcName;
    
    /** sumae_code */
    private java.lang.String sumaeCode;
    
    /** sumae_name */
    private java.lang.String lpcType;
    
    /** garlic_chk */
    private java.lang.String etc01;
    
    /** chk_gu */
    private java.lang.String etc02;
    
    /** gita_code1 */
    private java.lang.String etc03;
    
    /** gita_name1 */
    private java.lang.String etc04;
    
    /** gita_code2 */
    private java.lang.String etc05;
    
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
    
    public java.lang.String getCorpCode() {
        return this.corpCode;
    }
    
    public void setCorpCode(java.lang.String corpCode) {
        this.corpCode = corpCode;
    }
    
    public java.lang.String getLpcCode() {
        return this.lpcCode;
    }
    
    public void setLpcCode(java.lang.String lpcCode) {
        this.lpcCode = lpcCode;
    }
    
    public java.lang.String getLpcName() {
        return this.lpcName;
    }
    
    public void setLpcName(java.lang.String lpcName) {
        this.lpcName = lpcName;
    }
    
    public java.lang.String getSumaeCode() {
        return this.sumaeCode;
    }
    
    public void setSumaeCode(java.lang.String sumaeCode) {
        this.sumaeCode = sumaeCode;
    }
    
    
    
    public java.lang.String getLpcType() {
		return lpcType;
	}

	public void setLpcType(java.lang.String lpcType) {
		this.lpcType = lpcType;
	}

	public java.lang.String getEtc01() {
		return etc01;
	}

	public void setEtc01(java.lang.String etc01) {
		this.etc01 = etc01;
	}

	public java.lang.String getEtc02() {
		return etc02;
	}

	public void setEtc02(java.lang.String etc02) {
		this.etc02 = etc02;
	}

	public java.lang.String getEtc03() {
		return etc03;
	}

	public void setEtc03(java.lang.String etc03) {
		this.etc03 = etc03;
	}

	public java.lang.String getEtc04() {
		return etc04;
	}

	public void setEtc04(java.lang.String etc04) {
		this.etc04 = etc04;
	}

	public java.lang.String getEtc05() {
		return etc05;
	}

	public void setEtc05(java.lang.String etc05) {
		this.etc05 = etc05;
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
    
}
