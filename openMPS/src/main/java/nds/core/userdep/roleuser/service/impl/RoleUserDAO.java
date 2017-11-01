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
package nds.core.userdep.roleuser.service.impl;

import java.util.List;

import nds.core.userdep.roleuser.service.RoleUserVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("roleUserDAO")
public class RoleUserDAO extends EgovAbstractDAO {

    public RoleUserDAO() {
        super();
    }

    @SuppressWarnings("unchecked")
    public List selectRoleUserList(RoleUserVO roleUserVO) {
        return list("RoleUser.selectRoleUserList", roleUserVO);
    }
    
    public void insertRoleUser(RoleUserVO roleUserVO) {
    	insert("RoleUser.insertRoleUser", roleUserVO);
    }
    
    public void deleteRoleUser(RoleUserVO roleUserVO) {
    	delete("RoleUser.deleteRoleUser", roleUserVO);
    }
    
    public int selectDepMstCheck(RoleUserVO roleUserVO) {
        return (Integer) selectByPk("RoleUser.selectDepMstCheck", roleUserVO);
    }
    
    public int selectRoleMstCheck(RoleUserVO roleUserVO) {
        return (Integer) selectByPk("RoleUser.selectRoleMstCheck", roleUserVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectRoleUserInfo(RoleUserVO roleUserVO) {
        return list("RoleUser.selectRoleUserInfo", roleUserVO);
    }
    
    @SuppressWarnings("unchecked")
	public List selectRoleDepUser(RoleUserVO roleUserVO){
    	return list("RoleUser.selectRoleDepUser", roleUserVO);
    }
    public void updateUserCnfmYn(RoleUserVO roleUserVO) {
    	update("RoleUser.updateUserCnfmYn", roleUserVO);
    }
    
    
}