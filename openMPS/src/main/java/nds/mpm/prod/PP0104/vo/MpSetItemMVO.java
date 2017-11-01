package nds.mpm.prod.PP0104.vo;

/**
 * @Class Name : MpSetItemMVO.java
 * @Description : MpSetItemM VO class
 * @Modification Information
 *
 * @author MPM
 * @since 2017
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpSetItemMVO extends MpSetItemMDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** set_code */
    private java.lang.String setCode;
    
    private java.lang.String proName;
    
    /** pro_code */
    private java.lang.String proCode;
    
    /** set_qty */
    private java.math.BigDecimal setQty;
    
    /** memo */
    private java.lang.String memo;
    
    /** dele_yn */
    private java.lang.String deleYn;
    
    private java.lang.String setYn;
    
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
    
    public java.lang.String getSetCode() {
        return this.setCode;
    }
    
    public void setSetCode(java.lang.String setCode) {
        this.setCode = setCode;
    }
    
    public java.lang.String getProCode() {
        return this.proCode;
    }
    
    public void setProCode(java.lang.String proCode) {
        this.proCode = proCode;
    }
    
    public java.math.BigDecimal getSetQty() {
        return this.setQty;
    }
    
    public void setSetQty(java.math.BigDecimal setQty) {
        this.setQty = setQty;
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

	public java.lang.String getSetYn() {
		return setYn;
	}

	public void setSetYn(java.lang.String setYn) {
		this.setYn = setYn;
	}

	public java.lang.String getProName() {
		return proName;
	}

	public void setProName(java.lang.String proName) {
		this.proName = proName;
	}
    
}
