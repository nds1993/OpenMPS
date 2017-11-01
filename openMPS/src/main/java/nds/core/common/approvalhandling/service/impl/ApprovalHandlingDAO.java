/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2015-05-12 09:47:05
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* CHANGE REVISION
* ------------------------------
* AUTHOR   : NDS
* DATE     : 2015-05-12 09:47:05
* REVISION : 1.0   First release.
* -------------------------------
* CLASS DESCRIPTION
* -------------------------------
*
*/
package nds.core.common.approvalhandling.service.impl;



import java.util.List;

import nds.core.operation.approval.service.ApprovalVO;
import nds.core.userdep.user.service.UserVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("approvalHandlingDAO")
public class ApprovalHandlingDAO extends EgovAbstractDAO {
    

    @SuppressWarnings("unchecked")
    public List selectChargeVocList(ApprovalVO approvalVO) {
    	return list("ApprovalHandling.selectChargeVocList", approvalVO);
    }
    
    public int selectChargeVocListCount(ApprovalVO approvalVO) {
    	 return (Integer) selectByPk("ApprovalHandling.selectChargeVocListCount", approvalVO);
    }
    
    public ApprovalVO selectChargeVoc(ApprovalVO approvalVO){
        return (ApprovalVO) selectByPk("ApprovalHandling.selectChargeVoc", approvalVO);
    }    
    
    public ApprovalVO selectChargeSug(ApprovalVO approvalVO){
        return (ApprovalVO) selectByPk("ApprovalHandling.selectChargeSug", approvalVO);
    }  
    
    @SuppressWarnings("unchecked")
    public List selectApvStag(ApprovalVO approvalVO) {
    	return list("ApprovalHandling.selectApvStag", approvalVO);
    }
    
    public void callApvInfoProc(ApprovalVO approvalVO){
    	update("ApprovalHandling.callApvInfoProc", approvalVO);
    }
    public void updateProp(ApprovalVO approvalVO){
        update("ApprovalHandling.updateProp", approvalVO);
    }
    public void updateApv(ApprovalVO approvalVO){
        update("ApprovalHandling.updateApv", approvalVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectApvAlramList(ApprovalVO approvalVO) {
    	return list("ApprovalHandling.selectApvAlramList", approvalVO);
    }
    
    public void insertPropApvInfo(ApprovalVO approvalVO){
        insert("ApprovalHandling.insertPropApvInfo", approvalVO);
    }
    
    public void deletePropApvInfo(String apvId){
    	ApprovalVO approvalVO = new ApprovalVO();
    	approvalVO.setApvId(apvId);
    	
        delete("ApprovalHandling.deletePropApvInfo", approvalVO);
    }
}