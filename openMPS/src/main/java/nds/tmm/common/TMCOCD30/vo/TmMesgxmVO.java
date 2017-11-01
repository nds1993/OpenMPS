package nds.tmm.common.TMCOCD30.vo;

/**
 * @Class Name : TmMesgxmVO.java
 * @Description : TmMesgxm VO class
 * @Modification Information
 *
 * @author OpenMPS Team
 * @since 2017. 7. 18
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class TmMesgxmVO extends TmMesgxmDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** mesg_code */
    private java.lang.String mesgCode;
    
    /** msge_text */
    private java.lang.String msgeText;
    
    /** code_kind */
    private java.lang.String codeKind;
    
    /** msge_gubn */
    private java.lang.String msgeGubn;
    
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
    
    public java.lang.String getMesgCode() {
        return this.mesgCode;
    }
    
    public void setMesgCode(java.lang.String mesgCode) {
        this.mesgCode = mesgCode;
    }
    
    public java.lang.String getMsgeText() {
        return this.msgeText;
    }
    
    public void setMsgeText(java.lang.String msgeText) {
        this.msgeText = msgeText;
    }
    
    public java.lang.String getCodeKind() {
        return this.codeKind;
    }
    
    public void setCodeKind(java.lang.String codeKind) {
        this.codeKind = codeKind;
    }
    
    public java.lang.String getMsgeGubn() {
        return this.msgeGubn;
    }
    
    public void setMsgeGubn(java.lang.String msgeGubn) {
        this.msgeGubn = msgeGubn;
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
