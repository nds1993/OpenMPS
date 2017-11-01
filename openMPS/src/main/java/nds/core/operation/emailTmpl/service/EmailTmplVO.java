/**
* Created on   : 2014-8-14
* Target OS    : Java VM 1.7.0
* ------------------------------
* CHANGE REVISION
* ------------------------------
* AUTHOR   : NDS
* DATE     : 2014-8-14
* REVISION : 1.0   First release.
* -------------------------------
* CLASS DESCRIPTION
* -------------------------------
*/
package nds.core.operation.emailTmpl.service;

import nds.core.common.common.service.CommonObject;

public class EmailTmplVO extends CommonObject {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * PageNavigator : start Page
     */
    private int startNo;

    /**
     * PageNavigator : end Page
     */
    private int endNo;
    
    
    private String tmplNo;
    
    private String inoutDvn;
    
    private String tmplDvn;
    
    //등록채널
    private String chnlCat;
    private String chnlCatNm;
    
    //처리상태
    private String procStat;
    private String procStatNm;
    
    private String tmplNm;
    
    private String tmplDescrpt;

    private String cntn;
    
    //사용여부
    private String useYn;
    private String useYnNm;
    
    private String regUser;
    
    private String regUserNm;

    private String regDttm;

    private String updtUser;
    
    private String updtUserNm;
    
    private String updtDttm;
    
    private String rowNo;
    
    private String totalCnt;
    
    private String sidx;
    
    private String sord;

    
    
    
    public String getTmplDvn() {
		return tmplDvn;
	}
	public void setTmplDvn(String tmplDvn) {
		this.tmplDvn = tmplDvn;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(String totalCnt) {
		this.totalCnt = totalCnt;
	}
	public String getRowNo() {
		return rowNo;
	}
	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}
	public int getStartNo() {
        return startNo;
    }
    public void setStartNo(int startNo) {
        this.startNo = startNo;
    }
    public int getEndNo() {
        return endNo;
    }
    public void setEndNo(int endNo) {
        this.endNo = endNo;
    }
	public String getTmplNo() {
		return tmplNo;
	}
	public void setTmplNo(String tmplNo) {
		this.tmplNo = tmplNo;
	}
	public String getInoutDvn() {
		return inoutDvn;
	}
	public void setInoutDvn(String inoutDvn) {
		this.inoutDvn = inoutDvn;
	}
	
	public String getChnlCat() {
		return chnlCat;
	}
	public void setChnlCat(String chnlCat) {
		this.chnlCat = chnlCat;
	}
	public String getchnlCatNm() {
		return chnlCatNm;
	}
	public void setchnlCatNm(String chnlCatNm) {
		this.chnlCatNm = chnlCatNm;
	}
	public String getProcStat() {
		return procStat;
	}
	public void setProcStat(String procStat) {
		this.procStat = procStat;
	}
	public String getProcStatNm() {
		return procStatNm;
	}
	public void setProcStatNm(String procStatNm) {
		this.procStatNm = procStatNm;
	}
	public String getTmplNm() {
		return tmplNm;
	}
	public void setTmplNm(String tmplNm) {
		this.tmplNm = tmplNm;
	}
	public String getTmplDescrpt() {
		return tmplDescrpt;
	}
	public void setTmplDescrpt(String tmplDescrpt) {
		this.tmplDescrpt = tmplDescrpt;
	}
	public String getCntn() {
		return cntn;
	}
	public void setCntn(String cntn) {
		this.cntn = cntn;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getUseYnNm() {
		return useYnNm;
	}
	public void setUseYnNm(String useYnNm) {
		this.useYnNm = useYnNm;
	}
	public String getRegUser() {
		return regUser;
	}
	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}
	public String getRegUserNm() {
		return regUserNm;
	}
	public void setRegUserNm(String regUserNm) {
		this.regUserNm = regUserNm;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDd) {
		this.regDttm = regDd;
	}
	public String getUpdtUser() {
		return updtUser;
	}
	public void setUpdtUser(String updtUser) {
		this.updtUser = updtUser;
	}
	public String getUpdtUserNm() {
		return updtUserNm;
	}
	public void setUpdtUserNm(String updtUserNm) {
		this.updtUserNm = updtUserNm;
	}
	public String getUpdtDttm() {
		return updtDttm;
	}
	public void setUpdtDttm(String updtDd) {
		this.updtDttm = updtDd;
	}
    
}