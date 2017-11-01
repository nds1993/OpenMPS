package nds.tmm.common.TMCOUR40.vo;

/**
 * @Class Name : TMCOUR40VO.java
 * @Description : TMCOUR40 VO class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.19
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class TMCOUR40VO extends TMCOUR40DefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** gorup_code */
    private java.lang.String groupCode;
    
    /** gorup_name */
    private java.lang.String groupName;
    
    /** up_code */
    private java.lang.String upCode;
    
    /** sort_order */
    private java.lang.Integer sortOrder;
    
    /** memo */
    private java.lang.String memo;
    
    /** use_yn */
    private java.lang.String useYn;
    
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
    
    
    public java.lang.String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(java.lang.String groupCode) {
		this.groupCode = groupCode;
	}

	public java.lang.String getGroupName() {
		return groupName;
	}

	public void setGroupName(java.lang.String groupName) {
		this.groupName = groupName;
	}

	public java.lang.String getUpCode() {
        return this.upCode;
    }
    
    public void setUpCode(java.lang.String upCode) {
        this.upCode = upCode;
    }
    
    public java.lang.Integer getSortOrder() {
        return this.sortOrder;
    }
    
    public void setSortOrder(java.lang.Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
    
    public java.lang.String getMemo() {
        return this.memo;
    }
    
    public void setMemo(java.lang.String memo) {
        this.memo = memo;
    }
    
    public java.lang.String getUseYn() {
        return this.useYn;
    }
    
    public void setUseYn(java.lang.String useYn) {
        this.useYn = useYn;
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
