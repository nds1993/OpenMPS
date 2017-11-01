/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-6-20 10:22:58
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-6-20 10:22:58
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.userdep.user.service;

import java.util.HashMap;
import java.util.Map;

import nds.core.common.common.service.CommonObject;

public class UserVO extends CommonObject {
	
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

    private String requestIp;
    
    private String mode; //예외로그인 모드(Z:예외로그인처리)
    
    private String duMode; //DUMMY로그인 모드(가상로그인처리)
    
    private String checkEmpno; //자신 제외
    
    private String userId;

    private String userEmpno;

    private String upDepCd;
    
    private String upDepNm;
    
    private String depCd;

    private String depNm;

    private String pwd;

    private String userNm;

    private String innrNo;

    private String cntcplc;

    private String wrkInfo;
    
    private String adviserYn;
    
    private String mgYn;
    
    private String cnfmYn;

    private String regUser;

    private String regDttm;

    private String updtUser;

    private String updtDttm;

    private String rtmYn;
    
    private String pstCd;
    
    private String pstNm;
    
    private String pwdChangYmd;
    private String pwdChangYn;
    private String before;
    
    private String InnerPhone;
    
    private String sidx;
    
    private String sord;
    

    private Map<String, String> auth = new HashMap<String, String>();
    
    private String branch;
    
    private String email;
    
    private String phoneNum;
    
    private String roleCd;
    
    private String userRole;
    
    private String mstSub;
    
    private String chmanagerChnlCd;

    private String siteCode;
    private String siteName;

    private String manager;
    
    private String chmanager;
    
    private String chmanagerMs;
    
    private String[] chmanagerMsArr;
    
    private String[] chmanagerChnlCdArr;
    
    private String depmanager;
    
    private String depmanagerMs;
    
    private String dstruser;
    
    private String dstruserMs;
    
    private String dealuser;
    
    private String developer;
    
    private String generaluser;
    
    private String sugdstruser;
    
    private String sugdstruserMs;
    
    private String sugvocuser;
    
    private String sugvocuserMs;
    
    private String sugmanager;
    
    private String sugmanagerMs;
    
    
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getChmanager() {
		return chmanager;
	}

	public void setChmanager(String chmanager) {
		this.chmanager = chmanager;
	}

	public String getChmanagerMs() {
		return chmanagerMs;
	}

	public void setChmanagerMs(String chmanagerMs) {
		this.chmanagerMs = chmanagerMs;
	}

	public String[] getChmanagerMsArr() {
		return chmanagerMsArr;
	}

	public void setChmanagerMsArr(String[] chmanagerMsArr) {
		this.chmanagerMsArr = chmanagerMsArr;
	}

	public String[] getChmanagerChnlCdArr() {
		return chmanagerChnlCdArr;
	}

	public void setChmanagerChnlCdArr(String[] chmanagerChnlCdArr) {
		this.chmanagerChnlCdArr = chmanagerChnlCdArr;
	}

	public String getDepmanager() {
		return depmanager;
	}

	public void setDepmanager(String depmanager) {
		this.depmanager = depmanager;
	}

	public String getDepmanagerMs() {
		return depmanagerMs;
	}

	public void setDepmanagerMs(String depmanagerMs) {
		this.depmanagerMs = depmanagerMs;
	}

	public String getDstruser() {
		return dstruser;
	}

	public void setDstruser(String dstruser) {
		this.dstruser = dstruser;
	}

	public String getDstruserMs() {
		return dstruserMs;
	}

	public void setDstruserMs(String dstruserMs) {
		this.dstruserMs = dstruserMs;
	}

	public String getDealuser() {
		return dealuser;
	}

	public void setDealuser(String dealuser) {
		this.dealuser = dealuser;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getGeneraluser() {
		return generaluser;
	}

	public void setGeneraluser(String generaluser) {
		this.generaluser = generaluser;
	}

	public String getSugdstruser() {
		return sugdstruser;
	}

	public void setSugdstruser(String sugdstruser) {
		this.sugdstruser = sugdstruser;
	}

	public String getSugdstruserMs() {
		return sugdstruserMs;
	}

	public void setSugdstruserMs(String sugdstruserMs) {
		this.sugdstruserMs = sugdstruserMs;
	}

	public String getSugvocuser() {
		return sugvocuser;
	}

	public void setSugvocuser(String sugvocuser) {
		this.sugvocuser = sugvocuser;
	}

	public String getSugvocuserMs() {
		return sugvocuserMs;
	}

	public void setSugvocuserMs(String sugvocuserMs) {
		this.sugvocuserMs = sugvocuserMs;
	}

	public String getSugmanager() {
		return sugmanager;
	}

	public void setSugmanager(String sugmanager) {
		this.sugmanager = sugmanager;
	}

	public String getSugmanagerMs() {
		return sugmanagerMs;
	}

	public void setSugmanagerMs(String sugmanagerMs) {
		this.sugmanagerMs = sugmanagerMs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
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

	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getDuMode() {
		return duMode;
	}

	public void setDuMode(String duMode) {
		this.duMode = duMode;
	}

	public String getCheckEmpno() {
		return checkEmpno;
	}

	public void setCheckEmpno(String checkEmpno) {
		this.checkEmpno = checkEmpno;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserEmpno() {
		return userEmpno;
	}

	public void setUserEmpno(String userEmpno) {
		this.userEmpno = userEmpno;
	}

	public String getUpDepCd() {
		return upDepCd;
	}

	public void setUpDepCd(String upDepCd) {
		this.upDepCd = upDepCd;
	}

	public String getUpDepNm() {
		return upDepNm;
	}

	public void setUpDepNm(String upDepNm) {
		this.upDepNm = upDepNm;
	}

	public String getDepCd() {
		return depCd;
	}

	public void setDepCd(String depCd) {
		this.depCd = depCd;
	}

	public String getDepNm() {
		return depNm;
	}

	public void setDepNm(String depNm) {
		this.depNm = depNm;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getInnrNo() {
		return innrNo;
	}

	public void setInnrNo(String innrNo) {
		this.innrNo = innrNo;
	}

	public String getCntcplc() {
		return cntcplc;
	}

	public void setCntcplc(String cntcplc) {
		this.cntcplc = cntcplc;
	}

	public String getWrkInfo() {
		return wrkInfo;
	}

	public void setWrkInfo(String wrkInfo) {
		this.wrkInfo = wrkInfo;
	}

	public String getAdviserYn() {
		return adviserYn;
	}

	public void setAdviserYn(String adviserYn) {
		this.adviserYn = adviserYn;
	}

	public String getMgYn() {
		return mgYn;
	}

	public void setMgYn(String mgYn) {
		this.mgYn = mgYn;
	}

	public String getCnfmYn() {
		return cnfmYn;
	}

	public void setCnfmYn(String cnfmYn) {
		this.cnfmYn = cnfmYn;
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

	public String getRtmYn() {
		return rtmYn;
	}

	public void setRtmYn(String rtmYn) {
		this.rtmYn = rtmYn;
	}

	public String getPstCd() {
		return pstCd;
	}

	public void setPstCd(String pstCd) {
		this.pstCd = pstCd;
	}

	public String getPstNm() {
		return pstNm;
	}

	public void setPstNm(String pstNm) {
		this.pstNm = pstNm;
	}

	public String getPwdChangYmd() {
		return pwdChangYmd;
	}

	public void setPwdChangYmd(String pwdChangYmd) {
		this.pwdChangYmd = pwdChangYmd;
	}

	public String getPwdChangYn() {
		return pwdChangYn;
	}

	public void setPwdChangYn(String pwdChangYn) {
		this.pwdChangYn = pwdChangYn;
	}

	public String getBefore() {
		return before;
	}

	public void setBefore(String before) {
		this.before = before;
	}

	public String getInnerPhone() {
		return InnerPhone;
	}

	public void setInnerPhone(String innerPhone) {
		InnerPhone = innerPhone;
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

	public Map<String, String> getAuth() {
		return auth;
	}

	public void setAuth(Map<String, String> auth) {
		this.auth = auth;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getRoleCd() {
		return roleCd;
	}

	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getMstSub() {
		return mstSub;
	}

	public void setMstSub(String mstSub) {
		this.mstSub = mstSub;
	}

	public String getChmanagerChnlCd() {
		return chmanagerChnlCd;
	}

	public void setChmanagerChnlCd(String chmanagerChnlCd) {
		this.chmanagerChnlCd = chmanagerChnlCd;
	}
	
}