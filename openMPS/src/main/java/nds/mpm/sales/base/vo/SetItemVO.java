package nds.mpm.sales.base.vo;

/**
 * @Class Name : SetItemVO.java
 * @Description : SetItem VO class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.09
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class SetItemVO extends SetItemDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** set_code */
    private java.lang.String setCode;
    
    /** pro_code */
    private java.lang.String proCode;
    
    /** set_qty */
    private java.lang.Integer setQty;
    
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
    
    public java.lang.Integer getSetQty() {
        return this.setQty;
    }
    
    public void setSetQty(java.lang.Integer setQty) {
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
