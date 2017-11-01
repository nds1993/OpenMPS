package nds.mpm.sales.base.vo;

/**
 * @Class Name : TeamInfoVO.java
 * @Description : TeamInfo VO class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class TeamInfoVO extends TeamInfoDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** head_code */
    private java.lang.String headCode;
    
    /** team_code */
    private java.lang.String teamCode;
    
    /** team_name */
    private java.lang.String teamName;
    
    /** memo */
    private java.lang.String memo;
    
    /** dele_yn */
    private java.lang.String deleYn;
    
    /** md_user */
    private java.lang.String mdUser;
    
    /** md_date */
    private java.lang.String mdDate;
    
    /** cr_user */
    private java.lang.String crUser;
    
    /** cr_date */
    private java.lang.String crDate;
    
    public java.lang.String getHeadCode() {
        return this.headCode;
    }
    
    public void setHeadCode(java.lang.String headCode) {
        this.headCode = headCode;
    }
    
    public java.lang.String getTeamCode() {
        return this.teamCode;
    }
    
    public void setTeamCode(java.lang.String teamCode) {
        this.teamCode = teamCode;
    }
    
    public java.lang.String getTeamName() {
        return this.teamName;
    }
    
    public void setTeamName(java.lang.String teamName) {
        this.teamName = teamName;
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
    
    public java.lang.String getMdDate() {
        return this.mdDate;
    }
    
    public void setMdDate(java.lang.String mdDate) {
        this.mdDate = mdDate;
    }
    
    public java.lang.String getCrUser() {
        return this.crUser;
    }
    
    public void setCrUser(java.lang.String crUser) {
        this.crUser = crUser;
    }
    
    public java.lang.String getCrDate() {
        return this.crDate;
    }
    
    public void setCrDate(java.lang.String crDate) {
        this.crDate = crDate;
    }
    
}
