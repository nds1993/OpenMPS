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
package nds.core.operation.pushtmpl.service;

import nds.core.common.common.service.CommonObject;
import nds.frm.util.StringUtil;

public class PushTmplVO extends CommonObject {
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
    
    //검색조건
    private String schTmplNm;
    private String schUseYn;
    private String schChnlCat;
    private String schProcStat;
    private String schInoutDvn;    
    
    private String typeDvn; 		// SMS유형구분
    
    //등록채널
    private String chnlCat;
    private String chnlCatNm;
    
    //처리상태
    private String procStat;
    private String procStatNm;
    
    private String tmplNm;
    private String cntn;
    private String inoutDvn;
    
    //사용여부
    private String useYn;
    private String useYnNm;
    private String regUser;
    private String regUserNm;
    private String regDd;
    private String updtUser;
    private String updtUserNm;
    private String updtDd;
    private String rowNo;
    
    private String tmplDvn;		
    private String tmplDescrpt;

    public String getSchChnlCat() {
		return schChnlCat;
	}
	public void setSchChnlCat(String schChnlCat) {
		this.schChnlCat = schChnlCat;
	}
	public String getChnlCat() {
		return chnlCat;
	}
	public void setChnlCat(String chnlCat) {
		this.chnlCat = chnlCat;
	}
	public String getChnlCatNm() {
		return chnlCatNm;
	}
	public void setChnlCatNm(String chnlCatNm) {
		this.chnlCatNm = chnlCatNm;
	}
	public String getTmplDescrpt() {
		return tmplDescrpt;
	}
	public void setTmplDescrpt(String tmplDescrpt) {
		this.tmplDescrpt = tmplDescrpt;
	}
	public String getTmplDvn() {
		return tmplDvn;
	}
	public void setTmplDvn(String tmplDvn) {
		this.tmplDvn = tmplDvn;
	}
	public String getSchUseYn() {
		return schUseYn;
	}
	public void setSchUseYn(String schUseYn) {
		this.schUseYn = schUseYn;
	}

	public String getSchProcStat() {
		return schProcStat;
	}
	public void setSchProcStat(String schProcStat) {
		this.schProcStat = schProcStat;
	}
	public String getSchInoutDvn() {
		return schInoutDvn;
	}
	public void setSchInoutDvn(String schInoutDvn) {
		this.schInoutDvn = schInoutDvn;
	}
	public String getInoutDvn() {
		return inoutDvn;
	}
	public void setInoutDvn(String inoutDvn) {
		this.inoutDvn = inoutDvn;
	}
	public String getTypeDvn() {
		return typeDvn;
	}
	public void setTypeDvn(String typeDvn) {
		this.typeDvn = typeDvn;
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
	public String getRegDd() {
		return regDd;
	}
	public void setRegDd(String regDd) {
		this.regDd = regDd;
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
	public String getUpdtDd() {
		return updtDd;
	}
	public void setUpdtDd(String updtDd) {
		this.updtDd = updtDd;
	}
	public String getSchTmplNm() {
		return schTmplNm;
	}
	public void setSchTmplNm(String schTmplNm) {
		this.schTmplNm = schTmplNm;
	}
    
}