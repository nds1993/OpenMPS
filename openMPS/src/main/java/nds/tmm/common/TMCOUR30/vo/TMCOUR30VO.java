package nds.tmm.common.TMCOUR30.vo;

/**
 * @Class Name : TmMenuxmVO.java
 * @Description : TmMenuxm VO class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.18
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class TMCOUR30VO extends TMCOUR30DefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** menu_code */
    private java.lang.String menuCode;
    
    /** menu_name */
    private java.lang.String menuName;
    
    /** up_code */
    private java.lang.String upCode;
    private java.lang.String upName;
    
    /** sort_order */
    private java.lang.Integer sortOrder;
    
    private java.lang.String programYn;
    
    /** use_yn */
    private java.lang.String useYn;
    
    /** dele_yn */
    private java.lang.String deleYn;
    
    private String systemCode;
    
    /** md_user */
    private java.lang.String mdUser;
    
    /** md_date */
    private java.sql.Timestamp mdDate;
    
    /** cr_user */
    private java.lang.String crUser;
    
    /** cr_date */
    private java.sql.Timestamp crDate;
    
    public java.lang.String getMenuCode() {
        return this.menuCode;
    }
    
    public void setMenuCode(java.lang.String menuCode) {
        this.menuCode = menuCode;
    }
    
    public java.lang.String getMenuName() {
        return this.menuName;
    }
    
    public void setMenuName(java.lang.String menuName) {
        this.menuName = menuName;
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

	public java.lang.String getUpName() {
		return upName;
	}

	public void setUpName(java.lang.String upName) {
		this.upName = upName;
	}

	public java.lang.String getProgramYn() {
		return programYn;
	}

	public void setProgramYn(java.lang.String programYn) {
		this.programYn = programYn;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
    
}
