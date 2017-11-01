package nds.mpm.sales.SD0101.vo;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpCustInfoVO.java
 * @Description : MpCustInfo VO class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpCustInfoMultiVO {
    private static final long serialVersionUID = 1L;
    
    EgovMap custinfo;
    List<EgovMap> custhist;
    List<EgovMap> credit;
    List<EgovMap> dambo;
    List<EgovMap> custhistory;
    
    String dsType ;
	public EgovMap getCustinfo() {
		return custinfo;
	}
	public void setCustinfo(EgovMap custinfo) {
		this.custinfo = custinfo;
	}
	public String getDsType() {
		return dsType;
	}
	public void setDsType(String dsType) {
		this.dsType = dsType;
	}
	public List<EgovMap> getCusthist() {
		return custhist;
	}
	public void setCusthist(List<EgovMap> custhist) {
		this.custhist = custhist;
	}
	public List<EgovMap> getCredit() {
		return credit;
	}
	public void setCredit(List<EgovMap> credit) {
		this.credit = credit;
	}
	public List<EgovMap> getDambo() {
		return dambo;
	}
	public void setDambo(List<EgovMap> dambo) {
		this.dambo = dambo;
	}
	public List<EgovMap> getCusthistory() {
		return custhistory;
	}
	public void setCusthistory(List<EgovMap> custhistory) {
		this.custhistory = custhistory;
	}
    
}
