package nds.tmm.common.TMCOCD20.vo;

/**
 * @Class Name : TmLablxmVO.java
 * @Description : TmLablxm VO class
 * @Modification Information
 *
 * @author OpenMPS Team
 * @since 2017. 7. 18
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class TmLablxmVO extends TmLablxmDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** menu_code */
    private java.lang.String menuCode;
    
    /** label_code */
    private java.lang.String labelCode;
    
    /** label_name */
    private java.lang.String labelName;
    
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
    
    public java.lang.String getMenuCode() {
        return this.menuCode;
    }
    
    public void setMenuCode(java.lang.String menuCode) {
        this.menuCode = menuCode;
    }
    
    public java.lang.String getLabelCode() {
        return this.labelCode;
    }
    
    public void setLabelCode(java.lang.String labelCode) {
        this.labelCode = labelCode;
    }
    
    public java.lang.String getLabelName() {
        return this.labelName;
    }
    
    public void setLabelName(java.lang.String labelName) {
        this.labelName = labelName;
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
