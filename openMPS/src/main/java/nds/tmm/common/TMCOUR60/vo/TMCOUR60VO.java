package nds.tmm.common.TMCOUR60.vo;

/**
 * @Class Name : TMCOUR60VO.java
 * @Description : TMCOUR60 VO class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class TMCOUR60VO extends TMCOUR60DefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** gorup_code */
    private java.lang.String groupCode;
    
    /** user_code */
    private java.lang.String userCode;
    
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
        return this.groupCode;
    }
    
    public void setGroupCode(java.lang.String groupCode) {
        this.groupCode = groupCode;
    }
    
    public java.lang.String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(java.lang.String userCode) {
        this.userCode = userCode;
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
