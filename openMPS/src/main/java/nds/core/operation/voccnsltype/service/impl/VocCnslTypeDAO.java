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
package nds.core.operation.voccnsltype.service.impl;

import java.util.List;

import nds.core.operation.voccnsltype.service.VocCnslTypeVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("vocCnslTypeDAO")
public class VocCnslTypeDAO extends EgovAbstractDAO {

    public VocCnslTypeDAO() {
        super();
    }

    public void insertCnslCode(VocCnslTypeVO vocCnslTypeVO) {
        insert("VocCnslType.insertCnslCode", vocCnslTypeVO);
    }
    
    public int updateByPrimaryKeySelective(VocCnslTypeVO vocCnslTypeVO) {
        return (Integer)update("VocCnslType.updateByPrimaryKeySelective", vocCnslTypeVO);
    }
    
    public int deleteByPrimaryKey(VocCnslTypeVO vocCnslTypeVO) {
        return (Integer)delete("VocCnslType.deleteByPrimaryKey", vocCnslTypeVO);
    }
    
    public int selectByPrimaryKeyCount(VocCnslTypeVO vocCnslTypeVO) {
        return (Integer)selectByPk("VocCnslType.selectByPrimaryKeyCount", vocCnslTypeVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectCnslTypeList(VocCnslTypeVO vocCnslTypeVO){
        return list("VocCnslType.selectCnslTypeList", vocCnslTypeVO);
    }
    
}