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
package nds.core.systemsettings.role.service.impl;

import java.util.List;

import nds.core.systemsettings.role.service.RoleVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("roleDAO")
public class RoleDAO extends EgovAbstractDAO {

    public RoleDAO() {
        super();
    }

    public void insert(RoleVO roleVO) {
        insert("Role.insert", roleVO);
    }

    public int updateByPrimaryKeySelective(RoleVO roleVO) {
        return (Integer)update("Role.updateByPrimaryKeySelective", roleVO);
    }

    @SuppressWarnings("unchecked")
    public List selectByHelper(RoleVO roleVO) {
        return list("Role.selectByHelper", roleVO);
    }
    
    public int selectCountByHelper(RoleVO roleVO) {
        return (Integer)selectByPk("Role.selectCountByHelper", roleVO);
    }

    public int deleteByPrimaryKey(String roleCd) {
        RoleVO key = new RoleVO();
        key.setRoleCd(roleCd);
        return (Integer)delete("Role.deleteByPrimaryKey", key);
    }
}