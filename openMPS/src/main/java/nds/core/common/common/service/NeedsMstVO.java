package nds.core.common.common.service;

import java.io.Serializable;
import java.util.List;

import nds.core.operation.approval.service.ApprovalVO;

/**
 * <b>class : UseContents </b>
 * <b>Class Description</b><br>
 * 
 * <b>History</b><br>
 * <pre>      : 2012.06.25 초기작성(소건)</pre>
 * @author <a href="mailto:gso@nds.co.kr">소건</a>
 * @version 1.0
 */
public class NeedsMstVO extends CommonObject implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    private String vocId;
    private String cmpxId;
    private String cmpxYn;
    private String chfYn;
    private String bndlNo;
    private String cstNm;
    private String cstNo;
    private String cstLvl;
    private String account;
    private String homepageId;
    private String birdt;
    private String age;
    private String gen;
    private String hphon;
    private String hphon1;
    private String hphon2;
    private String hphon3;
    private String telno;
    private String email;
    private String email1;
    private String email2;
    private String faxNo;
    private String postNo;
    private String postNm;
    private String addr;
    private String procStat;
    private String procStatNm;
    private String regChnl;
    private String chnlCat;
    private String needsType;
    private String gencaus;
    private String lcls;
    private String mcls;
    private String scls;
    private String putUserId;
    private String putDepCd;
    private String putDttm;
    private String putDttmFull;
    private String putUserOpi;
    private String regAtchFileType;
    private String regAtchFileId;
    private String receNo;
    private String receUserId;
    private String receDepCd;
    private String receDttm;
    private String firstAsgaDttm;
    private String chrgUserId;
    private String chrgDepCd;
    private String dealDday;
    private String mgItem1;
    private String ddayExtCnt;
    private String qstnTit;
    private String qstnCntn;
    private String answTit;
    private String answCntn;
    private String answSumm;
    private String answDttm;
    private String answAtchFileType;
    private String answAtchFileId;
    private String rtType;
    private String apvStat;
    private String smsNotiYn;
    private String emailNotiYn;
    private String statNotiChnl;
    private String satiScr;
    private String satiYn;
    private String rcmdCnt;
    private String diffvocYn;
    private String openYn;
    private String mavocYn;
    private String imprvocYn;
    private String delYn;
    private String delDttm;
    private String dstrDvn;
    private String answType;
    private String regUser;
    private String regDttm;
    private String updtUser;
    private String updtDttm;
    private String afterChrgYn;
    private String certiYn;
    
    private String dvn;
    private String userIp;
    private String useMed;
    private String imprWay;
    private String pushYn;
    private String secretYn;
    private String helpReqCnt;
    private String transmitYn;
    private String elCivilType;
    private String addAnswCnt;
    private String viewCnt;
    private String plustarYn;
    private String blackYn;
    private String vocType;
    
    private List<ApprovalVO> apvUserList;
    
    private String seqno;
    private String asgnDepCd;
    private String asgnDepNm;
    private String asgnUserId;
    private String asgnUserNm;
    private String chmnDepCd;
    private String chmnDepNm;
    private String chmnUserId;
    private String chmnUserNm;
    private String dealDvn;
    private String dealDttm;
    private String apvType;
    
    
    
    /** 
     * 승인선 resultVO
     */
    private String apvDvn;
    private String cmplYn;
    private String rqstuserEmpno;
    private String apvuserEmpno;
    private String apvuserNm;
    private String apvuserPstNm;
    private String rqstDttm;
    private String apvuserOpi;
    private String apvDealCd;


    private String receDd;
    private String receTm;
    private String cstCoun;
    
    private String rtReplyTel;
    private String rtReplyEmail;
    
    private String answRegDd;
    private String answRegTm;
    
    
    private String issuvocYn;
    private String rcmdvoc;
    private String imprprojInclYn;
    private String delYmd;
    private String tmpSaveYn;
    private String satiSrchchnl;
    private String dealTm;
    private String ddayExtResn;
    private String cstRes;
    private String takeManOpi;
    private String mvoDvnCd;
    private String mvoResn;
    private String mvoYmd;
    private String mvoUser;
    private String mvoCounHo;
    private String hapcaTgtYn;
    private String homepageOpenYn;
    private String homepagePwd;
    private String regDep;




    /**
     * voc resultVO
     */
    private String chgChrgYn;  //관리자변경 여부
    
    private String notNeedsType;
    private String blngorgNm;
    private String reporgNm;
    private String tit;
    private String apvUserId;
    private String apvDttm;
    private String atchfileContsId;
    private String atchfileId;
    private String choose;
    private String apvStag;
    private String apvStags;
    private String apvStats;
    private String apvuserEmpnos;
    private String apvuserPstNms;
    private String userNm;
    private String depNm;
    private String receUserNm;
    private String receDepNm;
    private String apvUserNm;
    private String chrgUserNm;
    private String chrgDepNm;
    private String lclsNm;
    private String mclsNm;
    private String sclsNm;


/**
 * 홈페이지 첨부파일
 */
    private String atchFilePath1;            // 첨부파일경로1
    private String atchOtxtFileName1;       // 실제 파일명 1 
    private String atchFileChngName1;        // 변경 파일명1 
    private String atchFilePath2;            // 첨부파일경로2
    private String atchOtxtFileName2;        // 실제 파일명2 
    private String atchFileChngName2;       // 변경 파일명2  
    private String atchFilePath3;            // 첨부파일경로3
    private String atchOtxtFileName3;        // 실제 파일명3 
    private String atchFileChngName3;        // 변경 파일명3 


	public String getVocId() {
		return vocId;
	}
	public void setVocId(String vocId) {
		this.vocId = vocId;
	}
	public String getCmpxId() {
		return cmpxId;
	}
	public void setCmpxId(String cmpxId) {
		this.cmpxId = cmpxId;
	}
	public String getCmpxYn() {
		return cmpxYn;
	}
	public void setCmpxYn(String cmpxYn) {
		this.cmpxYn = cmpxYn;
	}
	public String getChfYn() {
		return chfYn;
	}
	public void setChfYn(String chfYn) {
		this.chfYn = chfYn;
	}
	public String getBndlNo() {
		return bndlNo;
	}
	public void setBndlNo(String bndlNo) {
		this.bndlNo = bndlNo;
	}
	public String getCstNm() {
		return cstNm;
	}
	public void setCstNm(String cstNm) {
		this.cstNm = cstNm;
	}
	public String getCstNo() {
		return cstNo;
	}
	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
	}
	public String getCstLvl() {
		return cstLvl;
	}
	public void setCstLvl(String cstLvl) {
		this.cstLvl = cstLvl;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getHomepageId() {
		return homepageId;
	}
	public void setHomepageId(String homepageId) {
		this.homepageId = homepageId;
	}
	public String getBirdt() {
		return birdt;
	}
	public void setBirdt(String birdt) {
		this.birdt = birdt;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGen() {
		return gen;
	}
	public void setGen(String gen) {
		this.gen = gen;
	}
	public String getHphon() {
		return hphon;
	}
	public void setHphon(String hphon) {
		this.hphon = hphon;
	}
	public String getHphon1() {
		return hphon1;
	}
	public void setHphon1(String hphon1) {
		this.hphon1 = hphon1;
	}
	public String getHphon2() {
		return hphon2;
	}
	public void setHphon2(String hphon2) {
		this.hphon2 = hphon2;
	}
	public String getHphon3() {
		return hphon3;
	}
	public void setHphon3(String hphon3) {
		this.hphon3 = hphon3;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	public String getPostNm() {
		return postNm;
	}
	public void setPostNm(String postNm) {
		this.postNm = postNm;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
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
	public String getRegChnl() {
		return regChnl;
	}
	public void setRegChnl(String regChnl) {
		this.regChnl = regChnl;
	}
	public String getChnlCat() {
		return chnlCat;
	}
	public void setChnlCat(String chnlCat) {
		this.chnlCat = chnlCat;
	}
	public String getNeedsType() {
		return needsType;
	}
	public void setNeedsType(String needsType) {
		this.needsType = needsType;
	}
	public String getGencaus() {
		return gencaus;
	}
	public void setGencaus(String gencaus) {
		this.gencaus = gencaus;
	}
	public String getLcls() {
		return lcls;
	}
	public void setLcls(String lcls) {
		this.lcls = lcls;
	}
	public String getMcls() {
		return mcls;
	}
	public void setMcls(String mcls) {
		this.mcls = mcls;
	}
	public String getScls() {
		return scls;
	}
	public void setScls(String scls) {
		this.scls = scls;
	}
	public String getPutUserId() {
		return putUserId;
	}
	public void setPutUserId(String putUserId) {
		this.putUserId = putUserId;
	}
	public String getPutDepCd() {
		return putDepCd;
	}
	public void setPutDepCd(String putDepCd) {
		this.putDepCd = putDepCd;
	}
	public String getPutDttm() {
		return putDttm;
	}
	public void setPutDttm(String putDttm) {
		this.putDttm = putDttm;
	}
	public String getPutDttmFull() {
		return putDttmFull;
	}
	public void setPutDttmFull(String putDttmFull) {
		this.putDttmFull = putDttmFull;
	}
	public String getPutUserOpi() {
		return putUserOpi;
	}
	public void setPutUserOpi(String putUserOpi) {
		this.putUserOpi = putUserOpi;
	}
	public String getRegAtchFileType() {
		return regAtchFileType;
	}
	public void setRegAtchFileType(String regAtchFileType) {
		this.regAtchFileType = regAtchFileType;
	}
	public String getRegAtchFileId() {
		return regAtchFileId;
	}
	public void setRegAtchFileId(String regAtchFileId) {
		this.regAtchFileId = regAtchFileId;
	}
	public String getReceNo() {
		return receNo;
	}
	public void setReceNo(String receNo) {
		this.receNo = receNo;
	}
	public String getReceUserId() {
		return receUserId;
	}
	public void setReceUserId(String receUserId) {
		this.receUserId = receUserId;
	}
	public String getReceDepCd() {
		return receDepCd;
	}
	public void setReceDepCd(String receDepCd) {
		this.receDepCd = receDepCd;
	}
	public String getReceDttm() {
		return receDttm;
	}
	public void setReceDttm(String receDttm) {
		this.receDttm = receDttm;
	}
	public String getFirstAsgaDttm() {
		return firstAsgaDttm;
	}
	public void setFirstAsgaDttm(String firstAsgaDttm) {
		this.firstAsgaDttm = firstAsgaDttm;
	}
	public String getChrgUserId() {
		return chrgUserId;
	}
	public void setChrgUserId(String chrgUserId) {
		this.chrgUserId = chrgUserId;
	}
	public String getChrgDepCd() {
		return chrgDepCd;
	}
	public void setChrgDepCd(String chrgDepCd) {
		this.chrgDepCd = chrgDepCd;
	}
	public String getDealDday() {
		return dealDday;
	}
	public void setDealDday(String dealDday) {
		this.dealDday = dealDday;
	}
	public String getMgItem1() {
		return mgItem1;
	}
	public void setMgItem1(String mgItem1) {
		this.mgItem1 = mgItem1;
	}
	public String getDdayExtCnt() {
		return ddayExtCnt;
	}
	public void setDdayExtCnt(String ddayExtCnt) {
		this.ddayExtCnt = ddayExtCnt;
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
	public String getAnswTit() {
		return answTit;
	}
	public void setAnswTit(String answTit) {
		this.answTit = answTit;
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
	public String getAnswDttm() {
		return answDttm;
	}
	public void setAnswDttm(String answDttm) {
		this.answDttm = answDttm;
	}
	public String getAnswAtchFileType() {
		return answAtchFileType;
	}
	public void setAnswAtchFileType(String answAtchFileType) {
		this.answAtchFileType = answAtchFileType;
	}
	public String getAnswAtchFileId() {
		return answAtchFileId;
	}
	public void setAnswAtchFileId(String answAtchFileId) {
		this.answAtchFileId = answAtchFileId;
	}
	public String getRtType() {
		return rtType;
	}
	public void setRtType(String rtType) {
		this.rtType = rtType;
	}
	public String getApvStat() {
		return apvStat;
	}
	public void setApvStat(String apvStat) {
		this.apvStat = apvStat;
	}
	public String getSmsNotiYn() {
		return smsNotiYn;
	}
	public void setSmsNotiYn(String smsNotiYn) {
		this.smsNotiYn = smsNotiYn;
	}
	public String getEmailNotiYn() {
		return emailNotiYn;
	}
	public void setEmailNotiYn(String emailNotiYn) {
		this.emailNotiYn = emailNotiYn;
	}
	public String getStatNotiChnl() {
		return statNotiChnl;
	}
	public void setStatNotiChnl(String statNotiChnl) {
		this.statNotiChnl = statNotiChnl;
	}
	public String getSatiScr() {
		return satiScr;
	}
	public void setSatiScr(String satiScr) {
		this.satiScr = satiScr;
	}
	public String getSatiYn() {
		return satiYn;
	}
	public void setSatiYn(String satiYn) {
		this.satiYn = satiYn;
	}
	public String getRcmdCnt() {
		return rcmdCnt;
	}
	public void setRcmdCnt(String rcmdCnt) {
		this.rcmdCnt = rcmdCnt;
	}

	public String getOpenYn() {
		return openYn;
	}
	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}

	public String getDiffvocYn() {
		return diffvocYn;
	}
	public void setDiffvocYn(String diffvocYn) {
		this.diffvocYn = diffvocYn;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getDelDttm() {
		return delDttm;
	}
	public void setDelDttm(String delDttm) {
		this.delDttm = delDttm;
	}
	public String getDstrDvn() {
		return dstrDvn;
	}
	public void setDstrDvn(String dstrDvn) {
		this.dstrDvn = dstrDvn;
	}
	public String getAnswType() {
		return answType;
	}
	public void setAnswType(String answType) {
		this.answType = answType;
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
	public String getAfterChrgYn() {
		return afterChrgYn;
	}
	public void setAfterChrgYn(String afterChrgYn) {
		this.afterChrgYn = afterChrgYn;
	}
	public String getCertiYn() {
		return certiYn;
	}
	public void setCertiYn(String certiYn) {
		this.certiYn = certiYn;
	}
	public String getDvn() {
		return dvn;
	}
	public void setDvn(String dvn) {
		this.dvn = dvn;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getUseMed() {
		return useMed;
	}
	public void setUseMed(String useMed) {
		this.useMed = useMed;
	}
	public String getImprWay() {
		return imprWay;
	}
	public void setImprWay(String imprWay) {
		this.imprWay = imprWay;
	}
	public String getPushYn() {
		return pushYn;
	}
	public void setPushYn(String pushYn) {
		this.pushYn = pushYn;
	}
	public String getSecretYn() {
		return secretYn;
	}
	public void setSecretYn(String secretYn) {
		this.secretYn = secretYn;
	}
	public String getHelpReqCnt() {
		return helpReqCnt;
	}
	public void setHelpReqCnt(String helpReqCnt) {
		this.helpReqCnt = helpReqCnt;
	}
	public String getTransmitYn() {
		return transmitYn;
	}
	public void setTransmitYn(String transmitYn) {
		this.transmitYn = transmitYn;
	}
	public String getElCivilType() {
		return elCivilType;
	}
	public void setElCivilType(String elCivilType) {
		this.elCivilType = elCivilType;
	}
	public String getAddAnswCnt() {
		return addAnswCnt;
	}
	public void setAddAnswCnt(String addAnswCnt) {
		this.addAnswCnt = addAnswCnt;
	}
	public String getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(String viewCnt) {
		this.viewCnt = viewCnt;
	}
	public String getPlustarYn() {
		return plustarYn;
	}
	public void setPlustarYn(String plustarYn) {
		this.plustarYn = plustarYn;
	}
	public String getBlackYn() {
		return blackYn;
	}
	public void setBlackYn(String blackYn) {
		this.blackYn = blackYn;
	}
	public String getVocType() {
		return vocType;
	}
	public void setVocType(String vocType) {
		this.vocType = vocType;
	}
	public List<ApprovalVO> getApvUserList() {
		return apvUserList;
	}
	public void setApvUserList(List<ApprovalVO> apvUserList) {
		this.apvUserList = apvUserList;
	}
	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	public String getAsgnDepCd() {
		return asgnDepCd;
	}
	public void setAsgnDepCd(String asgnDepCd) {
		this.asgnDepCd = asgnDepCd;
	}
	public String getAsgnDepNm() {
		return asgnDepNm;
	}
	public void setAsgnDepNm(String asgnDepNm) {
		this.asgnDepNm = asgnDepNm;
	}
	public String getAsgnUserId() {
		return asgnUserId;
	}
	public void setAsgnUserId(String asgnUserId) {
		this.asgnUserId = asgnUserId;
	}
	public String getAsgnUserNm() {
		return asgnUserNm;
	}
	public void setAsgnUserNm(String asgnUserNm) {
		this.asgnUserNm = asgnUserNm;
	}
	public String getChmnDepCd() {
		return chmnDepCd;
	}
	public void setChmnDepCd(String chmnDepCd) {
		this.chmnDepCd = chmnDepCd;
	}
	public String getChmnDepNm() {
		return chmnDepNm;
	}
	public void setChmnDepNm(String chmnDepNm) {
		this.chmnDepNm = chmnDepNm;
	}
	public String getChmnUserId() {
		return chmnUserId;
	}
	public void setChmnUserId(String chmnUserId) {
		this.chmnUserId = chmnUserId;
	}
	public String getChmnUserNm() {
		return chmnUserNm;
	}
	public void setChmnUserNm(String chmnUserNm) {
		this.chmnUserNm = chmnUserNm;
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
	
	public String getApvType() {
		return apvType;
	}
	public void setApvType(String apvType) {
		this.apvType = apvType;
	}
	public String getApvDvn() {
		return apvDvn;
	}
	public void setApvDvn(String apvDvn) {
		this.apvDvn = apvDvn;
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
	public String getReceDd() {
		return receDd;
	}
	public void setReceDd(String receDd) {
		this.receDd = receDd;
	}
	public String getReceTm() {
		return receTm;
	}
	public void setReceTm(String receTm) {
		this.receTm = receTm;
	}
	public String getCstCoun() {
		return cstCoun;
	}
	public void setCstCoun(String cstCoun) {
		this.cstCoun = cstCoun;
	}
	public String getRtReplyTel() {
		return rtReplyTel;
	}
	public void setRtReplyTel(String rtReplyTel) {
		this.rtReplyTel = rtReplyTel;
	}
	public String getRtReplyEmail() {
		return rtReplyEmail;
	}
	public void setRtReplyEmail(String rtReplyEmail) {
		this.rtReplyEmail = rtReplyEmail;
	}
	public String getAnswRegDd() {
		return answRegDd;
	}
	public void setAnswRegDd(String answRegDd) {
		this.answRegDd = answRegDd;
	}
	public String getAnswRegTm() {
		return answRegTm;
	}
	public void setAnswRegTm(String answRegTm) {
		this.answRegTm = answRegTm;
	}
	public String getIssuvocYn() {
		return issuvocYn;
	}
	public void setIssuvocYn(String issuvocYn) {
		this.issuvocYn = issuvocYn;
	}
	public String getImprvocYn() {
		return imprvocYn;
	}
	public void setImprvocYn(String imprvocYn) {
		this.imprvocYn = imprvocYn;
	}
	public String getRcmdvoc() {
		return rcmdvoc;
	}
	public void setRcmdvoc(String rcmdvoc) {
		this.rcmdvoc = rcmdvoc;
	}
	public String getImprprojInclYn() {
		return imprprojInclYn;
	}
	public void setImprprojInclYn(String imprprojInclYn) {
		this.imprprojInclYn = imprprojInclYn;
	}
	public String getDelYmd() {
		return delYmd;
	}
	public void setDelYmd(String delYmd) {
		this.delYmd = delYmd;
	}
	public String getTmpSaveYn() {
		return tmpSaveYn;
	}
	public void setTmpSaveYn(String tmpSaveYn) {
		this.tmpSaveYn = tmpSaveYn;
	}
	public String getSatiSrchchnl() {
		return satiSrchchnl;
	}
	public void setSatiSrchchnl(String satiSrchchnl) {
		this.satiSrchchnl = satiSrchchnl;
	}
	public String getDealTm() {
		return dealTm;
	}
	public void setDealTm(String dealTm) {
		this.dealTm = dealTm;
	}
	public String getDdayExtResn() {
		return ddayExtResn;
	}
	public void setDdayExtResn(String ddayExtResn) {
		this.ddayExtResn = ddayExtResn;
	}
	public String getCstRes() {
		return cstRes;
	}
	public void setCstRes(String cstRes) {
		this.cstRes = cstRes;
	}
	public String getTakeManOpi() {
		return takeManOpi;
	}
	public void setTakeManOpi(String takeManOpi) {
		this.takeManOpi = takeManOpi;
	}

	public String getMvoDvnCd() {
		return mvoDvnCd;
	}
	public void setMvoDvnCd(String mvoDvnCd) {
		this.mvoDvnCd = mvoDvnCd;
	}
	public String getMvoResn() {
		return mvoResn;
	}
	public void setMvoResn(String mvoResn) {
		this.mvoResn = mvoResn;
	}
	public String getMvoYmd() {
		return mvoYmd;
	}
	public void setMvoYmd(String mvoYmd) {
		this.mvoYmd = mvoYmd;
	}
	public String getMvoUser() {
		return mvoUser;
	}
	public void setMvoUser(String mvoUser) {
		this.mvoUser = mvoUser;
	}
	public String getMvoCounHo() {
		return mvoCounHo;
	}
	public void setMvoCounHo(String mvoCounHo) {
		this.mvoCounHo = mvoCounHo;
	}
	public String getHapcaTgtYn() {
		return hapcaTgtYn;
	}
	public void setHapcaTgtYn(String hapcaTgtYn) {
		this.hapcaTgtYn = hapcaTgtYn;
	}
	public String getHomepageOpenYn() {
		return homepageOpenYn;
	}
	public void setHomepageOpenYn(String homepageOpenYn) {
		this.homepageOpenYn = homepageOpenYn;
	}
	public String getHomepagePwd() {
		return homepagePwd;
	}
	public void setHomepagePwd(String homepagePwd) {
		this.homepagePwd = homepagePwd;
	}
	public String getRegDep() {
		return regDep;
	}
	public void setRegDep(String regDep) {
		this.regDep = regDep;
	}
	public String getChgChrgYn() {
		return chgChrgYn;
	}
	public void setChgChrgYn(String chgChrgYn) {
		this.chgChrgYn = chgChrgYn;
	}
	public String getNotNeedsType() {
		return notNeedsType;
	}
	public void setNotNeedsType(String notNeedsType) {
		this.notNeedsType = notNeedsType;
	}
	public String getBlngorgNm() {
		return blngorgNm;
	}
	public void setBlngorgNm(String blngorgNm) {
		this.blngorgNm = blngorgNm;
	}
	public String getReporgNm() {
		return reporgNm;
	}
	public void setReporgNm(String reporgNm) {
		this.reporgNm = reporgNm;
	}
	public String getTit() {
		return tit;
	}
	public void setTit(String tit) {
		this.tit = tit;
	}
	public String getApvUserId() {
		return apvUserId;
	}
	public void setApvUserId(String apvUserId) {
		this.apvUserId = apvUserId;
	}
	public String getApvDttm() {
		return apvDttm;
	}
	public void setApvDttm(String apvDttm) {
		this.apvDttm = apvDttm;
	}
	public String getMavocYn() {
		return mavocYn;
	}
	public void setMavocYn(String mavocYn) {
		this.mavocYn = mavocYn;
	}
	public String getAtchfileContsId() {
		return atchfileContsId;
	}
	public void setAtchfileContsId(String atchfileContsId) {
		this.atchfileContsId = atchfileContsId;
	}
	public String getAtchfileId() {
		return atchfileId;
	}
	public void setAtchfileId(String atchfileId) {
		this.atchfileId = atchfileId;
	}
	public String getChoose() {
		return choose;
	}
	public void setChoose(String choose) {
		this.choose = choose;
	}
	public String getApvStag() {
		return apvStag;
	}
	public void setApvStag(String apvStag) {
		this.apvStag = apvStag;
	}
	public String getApvStags() {
		return apvStags;
	}
	public void setApvStags(String apvStags) {
		this.apvStags = apvStags;
	}
	public String getApvStats() {
		return apvStats;
	}
	public void setApvStats(String apvStats) {
		this.apvStats = apvStats;
	}
	public String getApvuserEmpnos() {
		return apvuserEmpnos;
	}
	public void setApvuserEmpnos(String apvuserEmpnos) {
		this.apvuserEmpnos = apvuserEmpnos;
	}
	public String getApvuserPstNms() {
		return apvuserPstNms;
	}
	public void setApvuserPstNms(String apvuserPstNms) {
		this.apvuserPstNms = apvuserPstNms;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getDepNm() {
		return depNm;
	}
	public void setDepNm(String depNm) {
		this.depNm = depNm;
	}
	public String getReceUserNm() {
		return receUserNm;
	}
	public void setReceUserNm(String receUserNm) {
		this.receUserNm = receUserNm;
	}
	public String getReceDepNm() {
		return receDepNm;
	}
	public void setReceDepNm(String receDepNm) {
		this.receDepNm = receDepNm;
	}
	public String getApvUserNm() {
		return apvUserNm;
	}
	public void setApvUserNm(String apvUserNm) {
		this.apvUserNm = apvUserNm;
	}
	public String getChrgUserNm() {
		return chrgUserNm;
	}
	public void setChrgUserNm(String chrgUserNm) {
		this.chrgUserNm = chrgUserNm;
	}
	public String getChrgDepNm() {
		return chrgDepNm;
	}
	public void setChrgDepNm(String chrgDepNm) {
		this.chrgDepNm = chrgDepNm;
	}
	public String getLclsNm() {
		return lclsNm;
	}
	public void setLclsNm(String lclsNm) {
		this.lclsNm = lclsNm;
	}
	public String getMclsNm() {
		return mclsNm;
	}
	public void setMclsNm(String mclsNm) {
		this.mclsNm = mclsNm;
	}
	public String getSclsNm() {
		return sclsNm;
	}
	public void setSclsNm(String sclsNm) {
		this.sclsNm = sclsNm;
	}
	public String getAtchFilePath1() {
		return atchFilePath1;
	}
	public void setAtchFilePath1(String atchFilePath1) {
		this.atchFilePath1 = atchFilePath1;
	}
	public String getAtchOtxtFileName1() {
		return atchOtxtFileName1;
	}
	public void setAtchOtxtFileName1(String atchOtxtFileName1) {
		this.atchOtxtFileName1 = atchOtxtFileName1;
	}
	public String getAtchFileChngName1() {
		return atchFileChngName1;
	}
	public void setAtchFileChngName1(String atchFileChngName1) {
		this.atchFileChngName1 = atchFileChngName1;
	}
	public String getAtchFilePath2() {
		return atchFilePath2;
	}
	public void setAtchFilePath2(String atchFilePath2) {
		this.atchFilePath2 = atchFilePath2;
	}
	public String getAtchOtxtFileName2() {
		return atchOtxtFileName2;
	}
	public void setAtchOtxtFileName2(String atchOtxtFileName2) {
		this.atchOtxtFileName2 = atchOtxtFileName2;
	}
	public String getAtchFileChngName2() {
		return atchFileChngName2;
	}
	public void setAtchFileChngName2(String atchFileChngName2) {
		this.atchFileChngName2 = atchFileChngName2;
	}
	public String getAtchFilePath3() {
		return atchFilePath3;
	}
	public void setAtchFilePath3(String atchFilePath3) {
		this.atchFilePath3 = atchFilePath3;
	}
	public String getAtchOtxtFileName3() {
		return atchOtxtFileName3;
	}
	public void setAtchOtxtFileName3(String atchOtxtFileName3) {
		this.atchOtxtFileName3 = atchOtxtFileName3;
	}
	public String getAtchFileChngName3() {
		return atchFileChngName3;
	}
	public void setAtchFileChngName3(String atchFileChngName3) {
		this.atchFileChngName3 = atchFileChngName3;
	}
}