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
package nds.core.common.popup.service.impl;

import java.util.List;

import nds.core.common.common.service.NeedsMstVO;
import nds.core.common.popup.service.CnslTypeVO;
import nds.core.common.popup.service.PopupAcepnoVO;
import nds.core.common.popup.service.PopupApprovalVO;
import nds.core.common.popup.service.PopupCnslTypeVO;
import nds.core.common.popup.service.PopupCustInfoVO;
import nds.core.common.popup.service.PopupCustMemoVO;
import nds.core.common.popup.service.PopupHelpReqInfoVO;
import nds.core.common.popup.service.PopupNeedsChngHistVO;
import nds.core.common.popup.service.PopupPrductVO;
import nds.core.common.popup.service.PopupPropAsgnHistVO;
import nds.core.common.popup.service.PopupPropChngHistVO;
import nds.core.common.popup.service.PopupVendorVO;
import nds.core.common.popup.service.PopupWidgetSetVO;
import nds.core.userdep.department.service.DepartMentVO;
import nds.core.userdep.user.service.UserVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("popupDAO")
public class PopupDAO extends EgovAbstractDAO {

    public PopupDAO() {
        super();
    }
    
    @SuppressWarnings("unchecked")
	public List selectPrductType(PopupPrductVO popupPrductVO) {
		return list("Popup.selectPrductType", popupPrductVO);	
	}
	
	@SuppressWarnings("unchecked")
	public List selectPrductList(PopupPrductVO popupPrductVO) {
		return list("Popup.selectPrductList", popupPrductVO);
	}

	@SuppressWarnings("unchecked")
	public List selectPrductList_pType(PopupPrductVO popupPrductVO) {
		return list("Popup.selectPrductList_pType", popupPrductVO);
	}
	@SuppressWarnings("unchecked")
	public int selectPrductList_pTypeCount(PopupPrductVO popupPrductVO) {
			return (Integer)selectByPk("Popup.selectPrductList_pTypeCount", popupPrductVO);
	}
	
	@SuppressWarnings("unchecked")
	public List selectPrductList_Halb(PopupPrductVO popupPrductVO) {
		
		return list("Popup.selectPrductList_Halb", popupPrductVO);
	}
	
	public List selectProdBkmkList(PopupPrductVO popupPrductVO){
		return list("Popup.selectProdBkmkList", popupPrductVO);
	}

	public List selectVendorList(PopupVendorVO popupVendorVO){
		return list("Popup.selectVendorList", popupVendorVO);
	}
	public int  selectVendorCount(PopupVendorVO popupVendorVO){
		return (Integer)selectByPk("Popup.selectVendorCount", popupVendorVO);
	}
	
	public int selectPrductListCount(PopupPrductVO popupPrductVO) {
		return (Integer)selectByPk("Popup.selectPrductListCount", popupPrductVO);
	}
	
	public int selectPrductList_HalbCount(PopupPrductVO popupPrductVO) {
		return (Integer)selectByPk("Popup.selectPrductList_HalbCount", popupPrductVO);
	}
	
	 @SuppressWarnings("unchecked")
		public List selectPrductBossType(PopupPrductVO popupPrductVO){	
	    	return list("Popup.selectPrductBossType", popupPrductVO);
	}
    
    public int updateApproVal(String vocId) {
        return (Integer) update("Popup.updateApproVal", vocId);
    }
    
    public void insertMaWidgets(PopupWidgetSetVO key) {
    	insert("Popup.insertMaWidgets", key);
    }
    
    public void deleteMaWidgets(String key) {
        delete("Popup.deleteMaWidgets", key);
    }
    
    @SuppressWarnings("unchecked")
    public List selectMaWidgetsList(String key) {
    	return list("Popup.selectMaWidgetsList", key);
    }  

    @SuppressWarnings("unchecked")
    public List selectCnslTypeCdList(PopupCnslTypeVO popupCnclTypeVO){
        return list("Popup.selectCnslTypeCdList", popupCnclTypeVO);
    }
    public int selectCnslTypeCdCount(PopupCnslTypeVO popupCnclTypeVO) {
        return (Integer)selectByPk("Popup.selectCnslTypeCdCount", popupCnclTypeVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectCnslTypeList(CnslTypeVO cnslTypeVO){
        return list("Popup.selectCnslTypeList", cnslTypeVO);
    }
    public int selectCnslTypeListCount(CnslTypeVO cnslTypeVO){
        return (Integer)selectByPk("Popup.selectCnslTypeListCount", cnslTypeVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectMyCnslType(CnslTypeVO cnslTypeVO){
        return list("Popup.selectMyCnslType", cnslTypeVO);
    }
    public int selectMyCnslTypeCount(CnslTypeVO cnslTypeVO){
        return (Integer)selectByPk("Popup.selectMyCnslTypeCount", cnslTypeVO);
    } 
    
    public List selectCnslType(CnslTypeVO cnslTypeVO){
    	return list("Popup.selectCnslType", cnslTypeVO);
    }
    public List selectByTree(CnslTypeVO cnslTypeVO){
    	return list("Popup.selectByTree", cnslTypeVO);
    }

    @SuppressWarnings("unchecked")
    public List selectCstList(PopupCustInfoVO popupCustInfoVO) {
        return list("Popup.selectCstList", popupCustInfoVO);
    }
    
    public int selectCstListCount(PopupCustInfoVO popupCustInfoVO) {
    	return (Integer)selectByPk("Popup.selectCstListCount", popupCustInfoVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectCstDetail(PopupCustInfoVO popupCustInfoVO) {
        return list("Popup.selectCstDetail", popupCustInfoVO);
    }
    
    
    public Object insertCstMemo(PopupCustMemoVO popupCustMemoVO) {
        return insert("Popup.insertCstMemo", popupCustMemoVO);        
    }

    public void updateCstMemo(PopupCustMemoVO popupCustMemoVO) {
        update("Popup.updateCstMemo", popupCustMemoVO);       
        
    }
    
    public PopupCustMemoVO selectCstMemoLast(PopupCustMemoVO popupCustMemoVO) {
        return (PopupCustMemoVO) selectByPk("Popup.selectCstMemoLast", popupCustMemoVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectCstMemo(PopupCustMemoVO popupCustMemoVO) {
        return list("Popup.selectCstMemo", popupCustMemoVO);
    }

    public PopupCustMemoVO selectCstMemoInfo(PopupCustMemoVO popupCustMemoVO) {
        return (PopupCustMemoVO) selectByPk("Popup.selectCstMemoInfo", popupCustMemoVO);
    }
    
    
    @SuppressWarnings("unchecked")
    public List selectCstMemoDetail(PopupCustMemoVO popupCustMemoVO) {
        return list("Popup.selectCstMemoDetail", popupCustMemoVO);
    }
    
    public PopupCustMemoVO selectCstMemoInfo(String cstNo) {
    	PopupCustMemoVO key = new PopupCustMemoVO();
        key.setCstNo(cstNo);
        return (PopupCustMemoVO) selectByPk("Popup.selectCstMemoMarkInfo", key);
    }

    public int updateUsrWorkInfo(UserVO record) {
        return (Integer) update("Popup.updateUsrWorkInfo", record);
    }

    public UserVO selectUsrWorkInfo(String userId) {
    	UserVO key = new UserVO();
        key.setUserId(userId);
        return (UserVO) selectByPk("Popup.selectUsrWorkInfo", key);
    }
    
    @SuppressWarnings("unchecked")
	public List selectApvHistList(PopupApprovalVO popupApprovalVO) {
        return list("Popup.selectApvHistList", popupApprovalVO);
    }
    
    public int selectApvHistListCount(PopupApprovalVO popupApprovalVO) {
    	return (Integer)selectByPk("Popup.selectApvHistListCount", popupApprovalVO);
    }
    
    
    @SuppressWarnings("unchecked")
    public List selectDstrHist(NeedsMstVO needsMstVO) {
    	return list("Popup.selectDstrHist", needsMstVO);
    }
    
    public int selectDstrHistCount(NeedsMstVO needsMstVO) {
    	return (Integer)selectByPk("Popup.selectDstrHistCount", needsMstVO);
    }
    
    public void insertReTbvcApvInfo(PopupApprovalVO popupApprovalVO) {
        insert("Popup.insertReTbvcApvInfo", popupApprovalVO);
    }
    
    
    @SuppressWarnings("unchecked")
	public List selectTbvcNeedsChngHist(PopupNeedsChngHistVO popupNeedsChngHistVO) {
        return list("Popup.selectTbvcNeedsChngHist", popupNeedsChngHistVO);
    }
    
    public String insertNeedsChange(PopupNeedsChngHistVO popupNeedsChngHistVO){
        return (String) insert("Popup.insertNeedsChange", popupNeedsChngHistVO);
    }

    public void updateTbvcAtchfile(PopupNeedsChngHistVO popupNeedsChngHistVO){
        update("Popup.updateTbvcAtchfile", popupNeedsChngHistVO);
    }
    
    public void insertTbvcNeedsChngHist(PopupNeedsChngHistVO popupNeedsChngHistVO){
        insert("Popup.insertTbvcNeedsChngHist", popupNeedsChngHistVO);
    }
    
    @SuppressWarnings("unchecked")
	public List selectCnslTypeAutoComplete(PopupCnslTypeVO popupCnslTypeVO){
    	return list("Popup.selectCnslTypeAutoComplete", popupCnslTypeVO);
    }
    
    public void insertPropAsgnHist(PopupPropAsgnHistVO popupPropAsgnHistVO){
        insert("Popup.insertPropAsgnHist", popupPropAsgnHistVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectPropAsgnHistList(PopupPropAsgnHistVO popupPropAsgnHistVO) {
    	return list("Popup.selectPropAsgnHistList", popupPropAsgnHistVO);
    }
    
    public int selectPropAsgnHistListCount(PopupPropAsgnHistVO popupPropAsgnHistVO) {
    	return (Integer)selectByPk("Popup.selectPropAsgnHistListCount", popupPropAsgnHistVO);
    }
    
    public void insertHelpReqInfo(PopupHelpReqInfoVO popupHelpReqInfoVO) {
    	insert("Popup.insertHelpReqInfo", popupHelpReqInfoVO);
    }
    
    @SuppressWarnings("unchecked")
	public List selectPropChngHistList(PopupPropChngHistVO popupPropChngHistVO) {
        return list("Popup.selectPropChngHistList", popupPropChngHistVO);
    }

    public void insertPropChngHist(PopupPropChngHistVO popupPropChngHistVO){
        insert("Popup.insertPropChngHist", popupPropChngHistVO);
    }
    
    @SuppressWarnings("unchecked")
	public List selectClaimList(PopupAcepnoVO popupAcepnoVO){
    	return list("Popup.selectClaimList", popupAcepnoVO);
    }
    
    public int selectClaimListCount(PopupAcepnoVO popupAcepnoVO){
    	return (Integer) selectByPk("Popup.selectClaimListCount", popupAcepnoVO);
    }
    
}