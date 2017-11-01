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
package nds.core.systemsettings.button.service.impl;

import java.util.List;

import nds.core.systemsettings.button.service.BtnVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("buttonDAO")
public class ButtonDAO extends EgovAbstractDAO {

    public ButtonDAO() {
        super();
    }
    
    public void insertButton(BtnVO btnVO) {
        insert("Button.insertButton", btnVO);
    }
    
    public int updateButton(BtnVO btnVO) {
        return (Integer) update("Button.updateButton", btnVO);
    }
    
    public int deleteButton(String btnId) {
        BtnVO key = new BtnVO();
        key.setBtnId(btnId);
        return (Integer) delete("Button.deleteButton", key);
    }
    
    @SuppressWarnings("unchecked")
    public List selectButton(BtnVO btnVO) {
        return list("Button.selectButton", btnVO);
    }
    public int selectButtonCount(BtnVO btnVO) {
        return (Integer) selectByPk("Button.selectButtonCount", btnVO);
    }
    
    public int selectButtonIdCount(BtnVO btnVO) {
        return (Integer) selectByPk("Button.selectButtonIdCount", btnVO);
    }
    
    
}