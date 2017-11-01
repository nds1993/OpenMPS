package nds.tmm.common.TMCOCD10.vo;

/**
 * @Class Name : TmCodexmVO.java
 * @Description : TmCodexm VO class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class TMCOCD10VO extends TMCOCD10DefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** group_code */
    private java.lang.String groupCode;
    
    /** group_cdnm */
    private java.lang.String groupCdnm;
    
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
        return this.groupCode;
    }
    
    public void setGroupCode(java.lang.String groupCode) {
        this.groupCode = groupCode;
    }
    
    public java.lang.String getGroupCdnm() {
        return this.groupCdnm;
    }
    
    public void setGroupCdnm(java.lang.String groupCdnm) {
        this.groupCdnm = groupCdnm;
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
