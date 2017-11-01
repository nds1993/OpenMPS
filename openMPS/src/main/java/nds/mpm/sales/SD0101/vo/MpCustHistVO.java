package nds.mpm.sales.SD0101.vo;

/**
 * @Class Name : MpCustHistVO.java
 * @Description : MpCustHist VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpCustHistVO extends MpCustHistDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** cust_code */
    private java.lang.String custCode;
    
    /** seq_no */
    private java.lang.String seqNo;
    
    /** start_date */
    private java.lang.String startDate;
    
    /** last_date */
    private java.lang.String lastDate;
    
    /** sale_head */
    private java.lang.String saleHead;
    
    /** sale_part */
    private java.lang.String salePart;
    
    /** salesman */
    private java.lang.String salesman;
    
    /** cr_user */
    private java.lang.String crUser;
    
    /** cr_time */
    private java.sql.Timestamp crDate;
    
    /** md_user */
    private java.lang.String mdUser;
    
    /** md_time */
    private java.sql.Timestamp mdDate;
    
    public java.lang.String getCorpCode() {
        return this.corpCode;
    }
    
    public void setCorpCode(java.lang.String corpCode) {
        this.corpCode = corpCode;
    }
    
    public java.lang.String getCustCode() {
        return this.custCode;
    }
    
    public void setCustCode(java.lang.String custCode) {
        this.custCode = custCode;
    }
    
    public java.lang.String getSeqNo() {
        return this.seqNo;
    }
    
    public void setSeqNo(java.lang.String seqNo) {
        this.seqNo = seqNo;
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
    
    public java.lang.String getSaleHead() {
        return this.saleHead;
    }
    
    public void setSaleHead(java.lang.String saleHead) {
        this.saleHead = saleHead;
    }
    
    public java.lang.String getSalePart() {
        return this.salePart;
    }
    
    public void setSalePart(java.lang.String salePart) {
        this.salePart = salePart;
    }
    
    public java.lang.String getSalesman() {
        return this.salesman;
    }
    
    public void setSalesman(java.lang.String salesman) {
        this.salesman = salesman;
    }
    
    public java.lang.String getCrUser() {
        return this.crUser;
    }
    
    public void setCrUser(java.lang.String crUser) {
        this.crUser = crUser;
    }
    
    
    public java.lang.String getMdUser() {
        return this.mdUser;
    }
    
    public void setMdUser(java.lang.String mdUser) {
        this.mdUser = mdUser;
    }

	public java.sql.Timestamp getCrDate() {
		return crDate;
	}

	public void setCrDate(java.sql.Timestamp crDate) {
		this.crDate = crDate;
	}

	public java.sql.Timestamp getMdDate() {
		return mdDate;
	}

	public void setMdDate(java.sql.Timestamp mdDate) {
		this.mdDate = mdDate;
	}
    
    
}
