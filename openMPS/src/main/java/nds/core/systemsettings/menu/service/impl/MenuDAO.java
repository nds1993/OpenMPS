/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-6-20 16:23:7
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-6-20 16:23:7
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.systemsettings.menu.service.impl;

import java.util.List;

import nds.core.common.common.service.UserMenu;
import nds.core.systemsettings.button.service.BtnVO;
import nds.core.systemsettings.menu.service.MenuBtnVO;
import nds.core.systemsettings.menu.service.MenuVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("menuDAO")
public class MenuDAO extends EgovAbstractDAO {

    public MenuDAO() {
        super();
    }
    
    public void insert(MenuVO menuVO) {
        insert("Menu.insert", menuVO);
    }

    public int updateByPrimaryKey(MenuVO menuVO) {
        return (Integer)update("Menu.updateByPrimaryKey", menuVO);
    }

    public int selectCountByHelper(MenuVO helper) {
        return (Integer)selectByPk("Menu.selectCountByHelper", helper);
    }

    public int deleteByPrimaryKey(String contsId) {
    	MenuVO key = new MenuVO();
        key.setContsId(contsId);
        return (Integer)delete("Menu.deleteByPrimaryKey", key);
    }
    
    /**
     * 전체 컨텐츠 조회
     * @return
     */
    public List select() {
    	System.out.println("Dao 컨텐츠 조회");
        return list("Menu.select", null);
    }
    public List selectHelp() {
    	System.out.println("Help 컨텐츠 조회");
        return list("Menu.selectHelp", null);
    }
    
    public List selectMenuInfo(UserMenu key) {
    	System.out.println("MenuInfo 컨텐츠 조회");
        return list("Menu.selectMenuInfo", key);
    }

	public List selectMenuAuthority(UserMenu key) {
		System.out.println("selectMenuAuthority 컨텐츠 조회");
    	return list("Menu.selectMenuAuthority", key);
    }

    public List selectCstContentRoleInfo(UserMenu key) {
    	
    	System.out.println("selectCstContentRoleInfo 컨텐츠 조회");
        return list("Menu.selectCstContentRoleInfo", key);
    }
    
    public void insertBtn(MenuBtnVO menuBtnVO) {
        insert("Menu.insertBtn", menuBtnVO);
    }
    
    public int deleteBtn(MenuBtnVO menuBtnVO) {
        return (Integer) delete("Menu.deleteBtn", menuBtnVO);
    }
    
    public List selectContsUseButton(BtnVO btnVO) {
        return list("Menu.selectContsUseButton", btnVO);
    }
    
    public List selectContsButton(BtnVO btnVO) {
        return list("Menu.selectContsButton", btnVO);
    }

	@SuppressWarnings("unchecked")
	public List selectDownContsId(MenuBtnVO menuBtnVO) {
		return list("Menu.selectDownContsId", menuBtnVO);
	}
	
	public List selectPageList(MenuVO menuVO){
		return list("Menu.selectPageList", menuVO);
	}
	
	public List selectPageGubn(MenuVO menuVO){
		return list("Menu.selectPageGubn", menuVO);
	}
	public List getAgentWaitCount(MenuVO menuVO){
		return list("Menu.selectAgentWaitCount",menuVO);
	}
	public List selectLocalMenuInfo(UserMenu key) {
		return list("Menu.selectLocalMenuInfo", key);
	}
	
}