package nds.core.request.cooperation.service;

import nds.core.common.common.service.NeedsMstVO;

public class CooperationVO extends NeedsMstVO{

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
    
    private String reqId;
    private String reqType;
    private String tretDeptCd;
    private String tretEmpno;
    private String tretText;
    private String tretDttm;
    private String tretCmpltYn;
    private String reqDeptCd;
    private String reqEmpno;
    private String reqEmpnm;
    private String reqText;
    private String reqDttm;
    private String regUser;
    private String regDttm;
    private String updtUser;
    private String updtDttm;
    
    private String reqTypeNm;
    private String reqUserNm;
    private String reqDepNm;
    
    private String schStartDd;
    private String schEndDd;
    
    private String schTretGubun;

    private String propId;
	private String propTit;
	private String propCntn;
	private String propEffect;
	private String acceType;
   
	private String putDepNm;
	private String acceTypeNm;                                                                                                         
	private String rtTypeNm;
	private String imprWay;
	
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

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public String getTretDeptCd() {
		return tretDeptCd;
	}

	public void setTretDeptCd(String tretDeptCd) {
		this.tretDeptCd = tretDeptCd;
	}


	public String getTretEmpno() {
		return tretEmpno;
	}

	public void setTretEmpno(String tretEmpno) {
		this.tretEmpno = tretEmpno;
	}

	public String getTretText() {
		return tretText;
	}

	public void setTretText(String tretText) {
		this.tretText = tretText;
	}

	public String getTretDttm() {
		return tretDttm;
	}

	public void setTretDttm(String tretDttm) {
		this.tretDttm = tretDttm;
	}

	public String getTretCmpltYn() {
		return tretCmpltYn;
	}

	public void setTretCmpltYn(String tretCmpltYn) {
		this.tretCmpltYn = tretCmpltYn;
	}

	public String getReqDeptCd() {
		return reqDeptCd;
	}

	public void setReqDeptCd(String reqDeptCd) {
		this.reqDeptCd = reqDeptCd;
	}

	public String getReqEmpno() {
		return reqEmpno;
	}

	public void setReqEmpno(String reqEmpno) {
		this.reqEmpno = reqEmpno;
	}

	public String getReqEmpnm() {
		return reqEmpnm;
	}

	public void setReqEmpnm(String reqEmpnm) {
		this.reqEmpnm = reqEmpnm;
	}

	public String getReqText() {
		return reqText;
	}

	public void setReqText(String reqText) {
		this.reqText = reqText;
	}

	public String getReqDttm() {
		return reqDttm;
	}

	public void setReqDttm(String reqDttm) {
		this.reqDttm = reqDttm;
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

	public String getReqTypeNm() {
		return reqTypeNm;
	}

	public void setReqTypeNm(String reqTypeNm) {
		this.reqTypeNm = reqTypeNm;
	}

	public String getReqUserNm() {
		return reqUserNm;
	}

	public void setReqUserNm(String reqUserNm) {
		this.reqUserNm = reqUserNm;
	}

	public String getReqDepNm() {
		return reqDepNm;
	}

	public void setReqDepNm(String reqDepNm) {
		this.reqDepNm = reqDepNm;
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

	public String getPropId() {
		return propId;
	}

	public void setPropId(String propId) {
		this.propId = propId;
	}

	public String getPropTit() {
		return propTit;
	}

	public void setPropTit(String propTit) {
		this.propTit = propTit;
	}

	public String getPropCntn() {
		return propCntn;
	}

	public void setPropCntn(String propCntn) {
		this.propCntn = propCntn;
	}

	public String getPropEffect() {
		return propEffect;
	}

	public void setPropEffect(String propEffect) {
		this.propEffect = propEffect;
	}

	public String getAcceType() {
		return acceType;
	}

	public void setAcceType(String acceType) {
		this.acceType = acceType;
	}

	public String getPutDepNm() {
		return putDepNm;
	}

	public void setPutDepNm(String putDepNm) {
		this.putDepNm = putDepNm;
	}

	public String getAcceTypeNm() {
		return acceTypeNm;
	}

	public void setAcceTypeNm(String acceTypeNm) {
		this.acceTypeNm = acceTypeNm;
	}

	public String getRtTypeNm() {
		return rtTypeNm;
	}

	public void setRtTypeNm(String rtTypeNm) {
		this.rtTypeNm = rtTypeNm;
	}

	public String getImprWay() {
		return imprWay;
	}

	public void setImprWay(String imprWay) {
		this.imprWay = imprWay;
	}

	public String getSchTretGubun() {
		return schTretGubun;
	}

	public void setSchTretGubun(String schTretGubun) {
		this.schTretGubun = schTretGubun;
	}
    
    
}
