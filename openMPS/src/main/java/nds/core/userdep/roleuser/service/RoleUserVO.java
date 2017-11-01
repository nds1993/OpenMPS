/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-7-3 10:21:43
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-7-3 10:21:43
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.userdep.roleuser.service;

import nds.core.common.common.service.CommonObject;

public class RoleUserVO extends CommonObject {
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

    private String userId;
    
    private String userNm;
    
    private String roleCd;

    private String mstSub;
    
    private String chnlCd;

    private String regUser;

    private String regDttm;

    private String updtUser;
    
    private String updtDttm;

    private String branch;
    
    private String generaluser;
    
    private String dealuser;
    
    private String depmanager;
    
    private String depmanagerMstSub;
    
    private String sugdstruser;
    
    private String sugdstruserMstSub;
    
    private String sugvocuser;
    
    private String sugvocuserMstSub;
    
    private String sugmanager;
    
    private String sugmanagerMstSub;
    
    private String manager;
    
    private String depCd;
    
    private String depNm;
    
    private String cnfmYn;
    
    private String pstNm;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getRoleCd() {
		return roleCd;
	}

	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}

	public String getMstSub() {
		return mstSub;
	}

	public void setMstSub(String mstSub) {
		this.mstSub = mstSub;
	}

	public String getChnlCd() {
		return chnlCd;
	}

	public void setChnlCd(String chnlCd) {
		this.chnlCd = chnlCd;
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

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getGeneraluser() {
		return generaluser;
	}

	public void setGeneraluser(String generaluser) {
		this.generaluser = generaluser;
	}

	public String getDealuser() {
		return dealuser;
	}

	public void setDealuser(String dealuser) {
		this.dealuser = dealuser;
	}

	public String getDepmanager() {
		return depmanager;
	}

	public void setDepmanager(String depmanager) {
		this.depmanager = depmanager;
	}

	public String getDepmanagerMstSub() {
		return depmanagerMstSub;
	}

	public void setDepmanagerMstSub(String depmanagerMstSub) {
		this.depmanagerMstSub = depmanagerMstSub;
	}

	public String getSugdstruser() {
		return sugdstruser;
	}

	public void setSugdstruser(String sugdstruser) {
		this.sugdstruser = sugdstruser;
	}

	public String getSugdstruserMstSub() {
		return sugdstruserMstSub;
	}

	public void setSugdstruserMstSub(String sugdstruserMstSub) {
		this.sugdstruserMstSub = sugdstruserMstSub;
	}

	public String getSugvocuser() {
		return sugvocuser;
	}

	public void setSugvocuser(String sugvocuser) {
		this.sugvocuser = sugvocuser;
	}

	public String getSugvocuserMstSub() {
		return sugvocuserMstSub;
	}

	public void setSugvocuserMstSub(String sugvocuserMstSub) {
		this.sugvocuserMstSub = sugvocuserMstSub;
	}

	public String getSugmanager() {
		return sugmanager;
	}

	public void setSugmanager(String sugmanager) {
		this.sugmanager = sugmanager;
	}

	public String getSugmanagerMstSub() {
		return sugmanagerMstSub;
	}

	public void setSugmanagerMstSub(String sugmanagerMstSub) {
		this.sugmanagerMstSub = sugmanagerMstSub;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
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

	public String getCnfmYn() {
		return cnfmYn;
	}

	public void setCnfmYn(String cnfmYn) {
		this.cnfmYn = cnfmYn;
	}

	public String getPstNm() {
		return pstNm;
	}

	public void setPstNm(String pstNm) {
		this.pstNm = pstNm;
	}

}