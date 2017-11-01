/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2009-6-11 10:23:05
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* CHANGE REVISION
* ------------------------------
* AUTHOR   : NDS
* DATE     : 2009-6-11 10:23:05
* REVISION : 1.0   First release.
* -------------------------------
* CLASS DESCRIPTION
* -------------------------------
*
*/
package nds.core.systemsettings.roleuser.service.impl;

import java.util.List;

import nds.core.systemsettings.roleuser.service.RoleUserVO;
import nds.core.userdep.department.service.DepartMentVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("roleUserManageDAO")
public class RoleUserManageDAO extends EgovAbstractDAO {

    public RoleUserManageDAO() {
        super();
    }

    @SuppressWarnings("unchecked")
    public List selectRoleUserList(RoleUserVO vo) {
        return list("RoleUserManage.selectRoleUserList", vo);
    }

	@SuppressWarnings("unchecked")
	public List selectCustList(RoleUserVO roleUserVO) {
		return list("RoleUserManage.selectCustList", roleUserVO);
	}

	public int selectCustListCount(RoleUserVO roleUserVO) {
		return (Integer) selectByPk("RoleUserManage.selectCustListCount", roleUserVO);
	}

	public void insertRoleUser(RoleUserVO vo) {
		update("RoleUserManage.insertRoleUser", vo);
		
	}

	public void deleteRoleUser(RoleUserVO vo) {
		delete("RoleUserManage.deleteRoleUser", vo);		
	}

	public List selectOrganization(DepartMentVO org) {
		return list("RoleUserManage.selectOrganization", org);
	}

}