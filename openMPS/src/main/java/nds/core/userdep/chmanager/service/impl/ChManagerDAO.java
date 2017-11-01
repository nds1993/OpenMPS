/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-6-25 17:57:59
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-6-25 17:57:59
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.userdep.chmanager.service.impl;

import java.util.List;

import nds.core.userdep.chmanager.service.ChManagerVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("chManagerDAO")
public class ChManagerDAO extends EgovAbstractDAO {

    public ChManagerDAO() {
        super();
    }

    public void insertChnlManager(ChManagerVO chManagerVO) {
    	update("ChManager.insertChnlManager", chManagerVO);
    }

    public void updateChnlManager(ChManagerVO chManagerVO) {
    	update("ChManager.updateChnlManager", chManagerVO);
    }
    
    public void deleteChnlManager(ChManagerVO chManagerVO) {
    	delete("ChManager.deleteChnlManager", chManagerVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectChnlList(ChManagerVO chManagerVO) {
        return list("ChManager.selectChnlList", chManagerVO);
    }
	
    @SuppressWarnings("unchecked")
    public List selectChnlManagerList(ChManagerVO chManagerVO) {
        return list("ChManager.selectChnlManagerList", chManagerVO);
    }
    
    public int selectChnlMstCount(ChManagerVO chManagerVO) {
        return (Integer) selectByPk("ChManager.selectChnlMstCount", chManagerVO);
    }

    public int selectChnlUserCount(ChManagerVO chManagerVO) {
        return (Integer) selectByPk("ChManager.selectChnlUserCount", chManagerVO);
    }
    
    public ChManagerVO selectChnlManagerUserList(ChManagerVO chManagerVO) {
        return (ChManagerVO) selectByPk("ChManager.selectChnlManagerUserList", chManagerVO);
    }
}