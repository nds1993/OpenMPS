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
package nds.core.operation.approval.service.impl;

import java.util.List;

import nds.core.operation.approval.service.ApprovalVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("approvalDAO")
public class ApprovalDAO extends EgovAbstractDAO {

    public ApprovalDAO() {
        super();
    }
    
    public String insertApvMg(ApprovalVO approvalVO) {
    	return (String)insert("Approval.insertApvMg", approvalVO);
    }

    public int updateApvMg(ApprovalVO approvalVO) {
        return (Integer)update("Approval.updateApvMg", approvalVO);
    }

    @SuppressWarnings("unchecked")
    public List selectApvMgList(String userEmpno) {
        return list("Approval.selectApvMgList", userEmpno);
    }

    public int selectApvMgListCount(String userEmpno) {
        return (Integer)selectByPk("Approval.selectApvMgListCount", userEmpno);
    }
    
    @SuppressWarnings("unchecked")
    public List selectApvStagList(String userEmpno) {
        return list("Approval.selectApvStagList", userEmpno);
    }

    public int selectApvStagListCount(String userEmpno) {
        return (Integer)selectByPk("Approval.selectApvStagListCount", userEmpno);
    }

    public int deleteAllApvMg(String userEmpno) {
        return (Integer)delete("Approval.deleteAllApvMg", userEmpno);
    }
    
    public int deleteApvMg(ApprovalVO approvalVO) {
        return (Integer)delete("Approval.deleteApvMg", approvalVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectByApvMgList(ApprovalVO approvalVO) {
        return list("Approval.selectByApvMgList", approvalVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectUserList(ApprovalVO approvalVO) {
        return list("Approval.selectUserList", approvalVO);
    }

    public int selectUserListCount(ApprovalVO approvalVO) {
        return (Integer)selectByPk("Approval.selectUserListCount", approvalVO);
    }
}