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

import java.util.List;

import nds.core.common.common.service.CommonObject;


public class PopupApprovalVO extends CommonObject {
    
    private static final long serialVersionUID = 1L;
    
    /** 
     * 승인단계 searchVO & resultVO
     */
    private String apvId;
    private String vocId;         // searchVO
    private String apvDvn;        // searchVO
    private String apvStag;
    private String dealCmplYn;
    private String apvStat;
    private String rqstuserEmpno;
    private String apvuserEmpno;
    private String apvuserNm;
    private String apvuserPstNm;
    private String rqstDttm;
    private String apvDttm;
    private String apvuserOpi;
    private String regUser;
    private String regDttm;
    private String updtUser;
    private String updtDttm;
    private String apvDealCd;
    
    //승인 리스트
    private String apvuserDepNm;
    private String needsType;
    private String cstNm;
    private String tit;
    private String answRegDd;
    private String rqstuserNm;
    private String rqstuserDepNm;
    private String apvStatStag;
    
    //승인 상세
    private String chrgUserNm;
    private String receDd;
    private String regChnl;
    private String qstnCntn;
    private String answCntn;
    


	public String getApvId() {
		return apvId;
	}
	public void setApvId(String apvId) {
		this.apvId = apvId;
	}
	public String getVocId() {
		return vocId;
	}
	public void setVocId(String vocId) {
		this.vocId = vocId;
	}
	public String getApvDvn() {
		return apvDvn;
	}
	public void setApvDvn(String apvDvn) {
		this.apvDvn = apvDvn;
	}
	public String getApvStag() {
		return apvStag;
	}
	public void setApvStag(String apvStag) {
		this.apvStag = apvStag;
	}
	public String getDealCmplYn() {
		return dealCmplYn;
	}
	public void setDealCmplYn(String dealCmplYn) {
		this.dealCmplYn = dealCmplYn;
	}
	public String getApvStat() {
		return apvStat;
	}
	public void setApvStat(String apvStat) {
		this.apvStat = apvStat;
	}
	public String getRqstuserEmpno() {
		return rqstuserEmpno;
	}
	public void setRqstuserEmpno(String rqstuserEmpno) {
		this.rqstuserEmpno = rqstuserEmpno;
	}
	public String getApvuserEmpno() {
		return apvuserEmpno;
	}
	public void setApvuserEmpno(String apvuserEmpno) {
		this.apvuserEmpno = apvuserEmpno;
	}
	public String getApvuserNm() {
		return apvuserNm;
	}
	public void setApvuserNm(String apvuserNm) {
		this.apvuserNm = apvuserNm;
	}
	public String getApvuserPstNm() {
		return apvuserPstNm;
	}
	public void setApvuserPstNm(String apvuserPstNm) {
		this.apvuserPstNm = apvuserPstNm;
	}
	public String getRqstDttm() {
		return rqstDttm;
	}
	public void setRqstDttm(String rqstDttm) {
		this.rqstDttm = rqstDttm;
	}
	public String getApvDttm() {
		return apvDttm;
	}
	public void setApvDttm(String apvDttm) {
		this.apvDttm = apvDttm;
	}
	public String getApvuserOpi() {
		return apvuserOpi;
	}
	public void setApvuserOpi(String apvuserOpi) {
		this.apvuserOpi = apvuserOpi;
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
	public String getApvDealCd() {
		return apvDealCd;
	}
	public void setApvDealCd(String apvDealCd) {
		this.apvDealCd = apvDealCd;
	}
	public String getApvuserDepNm() {
		return apvuserDepNm;
	}
	public void setApvuserDepNm(String apvuserDepNm) {
		this.apvuserDepNm = apvuserDepNm;
	}
	public String getNeedsType() {
		return needsType;
	}
	public void setNeedsType(String needsType) {
		this.needsType = needsType;
	}
	public String getCstNm() {
		return cstNm;
	}
	public void setCstNm(String cstNm) {
		this.cstNm = cstNm;
	}
	public String getTit() {
		return tit;
	}
	public void setTit(String tit) {
		this.tit = tit;
	}
	public String getAnswRegDd() {
		return answRegDd;
	}
	public void setAnswRegDd(String answRegDd) {
		this.answRegDd = answRegDd;
	}
	public String getRqstuserNm() {
		return rqstuserNm;
	}
	public void setRqstuserNm(String rqstuserNm) {
		this.rqstuserNm = rqstuserNm;
	}
	public String getRqstuserDepNm() {
		return rqstuserDepNm;
	}
	public void setRqstuserDepNm(String rqstuserDepNm) {
		this.rqstuserDepNm = rqstuserDepNm;
	}
	public String getApvStatStag() {
		return apvStatStag;
	}
	public void setApvStatStag(String apvStatStag) {
		this.apvStatStag = apvStatStag;
	}
	public String getChrgUserNm() {
		return chrgUserNm;
	}
	public void setChrgUserNm(String chrgUserNm) {
		this.chrgUserNm = chrgUserNm;
	}
	public String getReceDd() {
		return receDd;
	}
	public void setReceDd(String receDd) {
		this.receDd = receDd;
	}
	public String getRegChnl() {
		return regChnl;
	}
	public void setRegChnl(String regChnl) {
		this.regChnl = regChnl;
	}
	public String getQstnCntn() {
		return qstnCntn;
	}
	public void setQstnCntn(String qstnCntn) {
		this.qstnCntn = qstnCntn;
	}
	public String getAnswCntn() {
		return answCntn;
	}
	public void setAnswCntn(String answCntn) {
		this.answCntn = answCntn;
	}
    
}