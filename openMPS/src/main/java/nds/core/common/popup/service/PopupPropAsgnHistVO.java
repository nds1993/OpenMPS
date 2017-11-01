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


public class PopupPropAsgnHistVO extends CommonObject {
    
    private static final long serialVersionUID = 1L;
    
    /** 
     * 제안 배정이력 resultVO
     */
    private String seqno;
    private String propId;
    private String fDepCd;
    private String fUserId;
    private String fDepNm;
    private String fUserNm;
    private String tDepCd;
    private String tUserId;
    private String tDepNm;
    private String tUserNm;
    private String dealDvn;
    private String dealDttm;
    private String etcCntn;
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
	public String getfDepCd() {
		return fDepCd;
	}
	public void setfDepCd(String fDepCd) {
		this.fDepCd = fDepCd;
	}
	public String getfUserId() {
		return fUserId;
	}
	public void setfUserId(String fUserId) {
		this.fUserId = fUserId;
	}
	public String getfDepNm() {
		return fDepNm;
	}
	public void setfDepNm(String fDepNm) {
		this.fDepNm = fDepNm;
	}
	public String getfUserNm() {
		return fUserNm;
	}
	public void setfUserNm(String fUserNm) {
		this.fUserNm = fUserNm;
	}
	public String gettDepCd() {
		return tDepCd;
	}
	public void settDepCd(String tDepCd) {
		this.tDepCd = tDepCd;
	}
	public String gettUserId() {
		return tUserId;
	}
	public void settUserId(String tUserId) {
		this.tUserId = tUserId;
	}
	public String gettDepNm() {
		return tDepNm;
	}
	public void settDepNm(String tDepNm) {
		this.tDepNm = tDepNm;
	}
	public String gettUserNm() {
		return tUserNm;
	}
	public void settUserNm(String tUserNm) {
		this.tUserNm = tUserNm;
	}
	public String getDealDvn() {
		return dealDvn;
	}
	public void setDealDvn(String dealDvn) {
		this.dealDvn = dealDvn;
	}
	public String getDealDttm() {
		return dealDttm;
	}
	public void setDealDttm(String dealDttm) {
		this.dealDttm = dealDttm;
	}
	public String getEtcCntn() {
		return etcCntn;
	}
	public void setEtcCntn(String etcCntn) {
		this.etcCntn = etcCntn;
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