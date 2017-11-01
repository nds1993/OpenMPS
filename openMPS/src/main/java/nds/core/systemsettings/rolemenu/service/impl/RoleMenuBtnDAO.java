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
import nds.core.systemsettings.rolemenu.service.RoleMenuBtnVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("roleMenuBtnDAO")
public class RoleMenuBtnDAO extends EgovAbstractDAO {

    public RoleMenuBtnDAO() {
        super();
    }

    /**
     * 역할별 컨텐츠에 대한 모든 버튼 삭제
     * @param key
     * @return
     */
    public int deleteRoleMenuBtn(RoleMenuBtnVO roleMenuBtnVO) {
        return (Integer)delete("RoleMenuBtn.delete", roleMenuBtnVO);
    } 
    
    public void insert(RoleMenuBtnVO roleMenuBtnVO) {
        insert("RoleMenuBtn.insert", roleMenuBtnVO);
    }    

    /**
     * 역할 사용버튼 목록 조회
     * @param helper
     * @return
     */
    public List selectRoleUseButton(BtnVO btnVO) {
        return list("RoleMenuBtn.selectRoleUseButton", btnVO);
    }

	public List selectDownContsId(RoleMenuBtnVO roleMenuBtnVO) {
		return list("RoleMenuBtn.selectDownContsId", roleMenuBtnVO);
	}
}