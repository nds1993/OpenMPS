/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-7-20 1:7:47
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-7-20 1:7:47
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.operation.approval.service;

import nds.core.userdep.user.service.UserVO;

public class ApprovalVO extends UserVO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 승인단계 관리 searchVO & resultVO
     */
    private String apvStag;
    private String mndtYn;
    private String apvuserEmpno;
    private String apvuserNm;
    
    /**
     * VOC 결재 searchVO & resultVO
     */
    
    private String vocId;      // searchVO
    private String apvId;
    private String apvStat;
    private String cmplYn;
    private String rqstuserEmpno;
    private String apvuserPstNm;
    private String chrgUserNm;
    private String receDttm;
    private String cstNm;
    private String regChnl;
    private String qstnTit;
    private String qstnCntn;
    private String answCntn;
    private String answSumm;
    private String rqstuserNm;
    private String rqstuserDepNm;
    private String needsType;
    private String answDttm;
    private String apvStatStag;
    private String rqstDttm;
    private String apvDttm;
    private String apvuserOpi;
    private String apvDealCd;
    
    private String outApvStag;
    
    private String apvType;
    private String apvTypeNm;
    private String seqno;

    private String qstnEffect;
    private String putDepNm;
    private String putUserNm;
    private String putDttm;
    
    private String schStartDd;
    private String schEndDd;
    
	private String rqstuserEmpnoNm;
	private String regChnlNm;
	private String cancelYn;
    private String conapvEmpno;
    private String imprWay;
    
    public String getApvTypeNm() {
		return apvTypeNm;
	}
	public void setApvTypeNm(String apvTypeNm) {
		this.apvTypeNm = apvTypeNm;
	}
	public String getSchStartDd() {
		return schStartDd;
	}
	public void setSchStartDd(String schStartDd) {
		this.schStartDd = schStartDd;
	}
	public String getSchEndDd() {
		return schEndDd;
	}
	public void setSchEndDd(String schEndDd) {
		this.schEndDd = schEndDd;
	}
	public String getPutDttm() {
		return putDttm;
	}
	public void setPutDttm(String putDttm) {
		this.putDttm = putDttm;
	}
	public String getQstnEffect() {
		return qstnEffect;
	}
	public void setQstnEffect(String qstnEffect) {
		this.qstnEffect = qstnEffect;
	}
	public String getPutDepNm() {
		return putDepNm;
	}
	public void setPutDepNm(String putDepNm) {
		this.putDepNm = putDepNm;
	}
	public String getPutUserNm() {
		return putUserNm;
	}
	public void setPutUserNm(String putUserNm) {
		this.putUserNm = putUserNm;
	}

	
	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	public String getApvType() {
		return apvType;
	}
	public void setApvType(String apvType) {
		this.apvType = apvType;
	}
	public String getOutApvStag() {
		return outApvStag;
	}
	public void setOutApvStag(String outApvStag) {
		this.outApvStag = outApvStag;
	}
	public String getApvStat() {
		return apvStat;
	}
	public void setApvStat(String apvStat) {
		this.apvStat = apvStat;
	}
	public String getMndtYn() {
		return mndtYn;
	}
	public void setMndtYn(String mndtYn) {
		this.mndtYn = mndtYn;
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

	public String getVocId() {
		return vocId;
	}
	public void setVocId(String vocId) {
		this.vocId = vocId;
	}
	public String getApvId() {
		return apvId;
	}
	public void setApvId(String apvId) {
		this.apvId = apvId;
	}
	public String getApvStag() {
		return apvStag;
	}
	public void setApvStag(String apvStag) {
		this.apvStag = apvStag;
	}
	public String getCmplYn() {
		return cmplYn;
	}
	public void setCmplYn(String cmplYn) {
		this.cmplYn = cmplYn;
	}
	public String getRqstuserEmpno() {
		return rqstuserEmpno;
	}
	public void setRqstuserEmpno(String rqstuserEmpno) {
		this.rqstuserEmpno = rqstuserEmpno;
	}
	public String getApvuserPstNm() {
		return apvuserPstNm;
	}
	public void setApvuserPstNm(String apvuserPstNm) {
		this.apvuserPstNm = apvuserPstNm;
	}
	public String getChrgUserNm() {
		return chrgUserNm;
	}
	public void setChrgUserNm(String chrgUserNm) {
		this.chrgUserNm = chrgUserNm;
	}
	public String getReceDttm() {
		return receDttm;
	}
	public void setReceDttm(String receDttm) {
		this.receDttm = receDttm;
	}
	public String getCstNm() {
		return cstNm;
	}
	public void setCstNm(String cstNm) {
		this.cstNm = cstNm;
	}
	public String getRegChnl() {
		return regChnl;
	}
	public void setRegChnl(String regChnl) {
		this.regChnl = regChnl;
	}
	public String getQstnTit() {
		return qstnTit;
	}
	public void setQstnTit(String qstnTit) {
		this.qstnTit = qstnTit;
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
	public String getAnswSumm() {
		return answSumm;
	}
	public void setAnswSumm(String answSumm) {
		this.answSumm = answSumm;
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
	public String getNeedsType() {
		return needsType;
	}
	public void setNeedsType(String needsType) {
		this.needsType = needsType;
	}
	public String getAnswDttm() {
		return answDttm;
	}
	public void setAnswDttm(String answDttm) {
		this.answDttm = answDttm;
	}
	public String getApvStatStag() {
		return apvStatStag;
	}
	public void setApvStatStag(String apvStatStag) {
		this.apvStatStag = apvStatStag;
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
	public String getApvDealCd() {
		return apvDealCd;
	}
	public void setApvDealCd(String apvDealCd) {
		this.apvDealCd = apvDealCd;
	}
	public String getRqstuserEmpnoNm() {
		return rqstuserEmpnoNm;
	}
	public void setRqstuserEmpnoNm(String rqstuserEmpnoNm) {
		this.rqstuserEmpnoNm = rqstuserEmpnoNm;
	}
	public String getRegChnlNm() {
		return regChnlNm;
	}
	public void setRegChnlNm(String regChnlNm) {
		this.regChnlNm = regChnlNm;
	}
	public String getCancelYn() {
		return cancelYn;
	}
	public void setCancelYn(String cancelYn) {
		this.cancelYn = cancelYn;
	}
	public String getConapvEmpno() {
		return conapvEmpno;
	}
	public void setConapvEmpno(String conapvEmpno) {
		this.conapvEmpno = conapvEmpno;
	}
	public String getImprWay() {
		return imprWay;
	}
	public void setImprWay(String imprWay) {
		this.imprWay = imprWay;
	}


}