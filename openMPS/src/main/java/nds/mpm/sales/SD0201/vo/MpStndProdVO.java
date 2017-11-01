package nds.mpm.sales.SD0201.vo;

/**
 * @Class Name : MpStndProdVO.java
 * @Description : MpStndProd VO class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpStndProdVO extends MpStndProdDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** prod_code */
    private java.lang.String prodCode;
    
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
    
    public java.lang.String getProdCode() {
        return this.prodCode;
    }
    
    public void setProdCode(java.lang.String prodCode) {
        this.prodCode = prodCode;
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
