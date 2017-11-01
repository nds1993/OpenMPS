/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-6-25 10:44:1
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-6-25 10:44:1
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.common.popup.service;

import nds.core.common.common.service.CommonObject;


public class PopupPropChngHistVO extends CommonObject {
    
    private static final long serialVersionUID = 1L;
    
    /** 
     * 변경이력 resultVO
     */
    private String seqno;
    private String propId;
    private String chngDepCd;
    private String chngUserId;
    private String chngDepNm;
    private String chngUserNm;
    private String dealCntn;
    private String planDt;
    private String chngDttm;
    private String regUser;
    private String regDttm;
    private String updtUser;
    private String updtDttm;
    
	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	public String getPropId() {
		return propId;
	}
	public void setPropId(String propId) {
		this.propId = propId;
	}
	public String getChngDepCd() {
		return chngDepCd;
	}
	public void setChngDepCd(String chngDepCd) {
		this.chngDepCd = chngDepCd;
	}
	public String getChngUserId() {
		return chngUserId;
	}
	public void setChngUserId(String chngUserId) {
		this.chngUserId = chngUserId;
	}
	public String getChngDepNm() {
		return chngDepNm;
	}
	public void setChngDepNm(String chngDepNm) {
		this.chngDepNm = chngDepNm;
	}
	public String getChngUserNm() {
		return chngUserNm;
	}
	public void setChngUserNm(String chngUserNm) {
		this.chngUserNm = chngUserNm;
	}
	public String getDealCntn() {
		return dealCntn;
	}
	public void setDealCntn(String dealCntn) {
		this.dealCntn = dealCntn;
	}
	public String getPlanDt() {
		return planDt;
	}
	public void setPlanDt(String planDt) {
		this.planDt = planDt;
	}
	public String getChngDttm() {
		return chngDttm;
	}
	public void setChngDttm(String chngDttm) {
		this.chngDttm = chngDttm;
	}
	public String getRegUser() {
		return regUser;
	}
	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getUpdtUser() {
		return updtUser;
	}
	public void setUpdtUser(String updtUser) {
		this.updtUser = updtUser;
	}
	public String getUpdtDttm() {
		return updtDttm;
	}
	public void setUpdtDttm(String updtDttm) {
		this.updtDttm = updtDttm;
	}
    
}