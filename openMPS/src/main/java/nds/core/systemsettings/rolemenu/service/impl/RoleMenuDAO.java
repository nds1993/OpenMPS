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
package nds.core.systemsettings.rolemenu.service.impl;

import java.util.List;

import nds.core.systemsettings.button.service.BtnVO;
import nds.core.systemsettings.menu.service.MenuVO;
import nds.core.systemsettings.role.service.RoleVO;
import nds.core.systemsettings.rolemenu.service.RoleMenuVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("roleMenuDAO")
public class RoleMenuDAO extends EgovAbstractDAO {

    public RoleMenuDAO() {
        super();
    }

    
    @SuppressWarnings("unchecked")
    public List selectRoleList(RoleVO roleVO) {
        return list("RoleMenu.selectRoleList", roleVO);
    }
    public int selectRoleListCount(RoleVO roleVO) {
        return (Integer)selectByPk("RoleMenu.selectRoleListCount", roleVO) ;
    }

    public int deleteByPrimaryKey(RoleMenuVO roleMenuVO) {
        return (Integer)delete("RoleMenu.deleteByPrimaryKey", roleMenuVO);
    }
    
    /**
     * 역할별컨텐츠 트리
     * @return
     */
    public List selectRoleMenuTree(MenuVO menuVO) {
        return list("RoleMenu.selectRoleMenuTree", menuVO);
    }
    
    public int updateByPrimaryKeySelective(RoleMenuVO roleMenuVO) {
        return (Integer)update("RoleMenu.updateByPrimaryKeySelective", roleMenuVO);
    }
    
    public void insert(RoleMenuVO roleMenuVO) {
        insert("RoleMenu.insert", roleMenuVO);
    }
    
    /**
     * 역할별 미등록 컨텐츠 조회
     * @return
     */
    public List selectRoleTarget(RoleMenuVO roleMenuVO) {
        return list("RoleMenu.selectByContents", roleMenuVO);
    }
    
    /**
     * 역할 미사용버튼 목록 조회
     * @param key
     * @return
     */
    public List selectRoleButton(BtnVO btnVO){
        return list("RoleMenu.selectRoleButton", btnVO);
    }
    
}