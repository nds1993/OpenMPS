package nds.mpm.sales.SD0805.vo;

/**
 * @Class Name : MpNotifyVO.java
 * @Description : MpNotify VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpNotifyVO extends MpNotifyDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** start_date */
    private java.lang.String startDate;
    
    /** last_date */
    private java.lang.String lastDate;
    
    /** seq_no */
    private java.lang.String seqNo;
    
    /** title */
    private java.lang.String title;
    
    /** open_gubn */
    private java.lang.String openGubn;
    
    /** notify */
    private java.lang.String notify;
    
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
    
    public java.lang.String getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(java.lang.String startDate) {
        this.startDate = startDate;
    }
    
    public java.lang.String getLastDate() {
        return this.lastDate;
    }
    
    public void setLastDate(java.lang.String lastDate) {
        this.lastDate = lastDate;
    }
    
    public java.lang.String getSeqNo() {
        return this.seqNo;
    }
    
    public void setSeqNo(java.lang.String seqNo) {
        this.seqNo = seqNo;
    }
    
    public java.lang.String getTitle() {
        return this.title;
    }
    
    public void setTitle(java.lang.String title) {
        this.title = title;
    }
    
    public java.lang.String getOpenGubn() {
        return this.openGubn;
    }
    
    public void setOpenGubn(java.lang.String openGubn) {
        this.openGubn = openGubn;
    }
    
    public java.lang.String getNotify() {
        return this.notify;
    }
    
    public void setNotify(java.lang.String notify) {
        this.notify = notify;
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
