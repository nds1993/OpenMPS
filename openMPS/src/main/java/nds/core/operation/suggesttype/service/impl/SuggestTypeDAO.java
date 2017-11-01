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
package nds.core.operation.suggesttype.service.impl;

import java.util.List;

import nds.core.operation.suggesttype.service.SuggestTypeVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("suggestTypeDAO")
public class SuggestTypeDAO extends EgovAbstractDAO {

    public SuggestTypeDAO() {
        super();
    }

    public void insertPropType(SuggestTypeVO suggestTypeVO) {
        insert("SuggestType.insertPropType", suggestTypeVO);
    }
    
    public void insertPropResultType(SuggestTypeVO suggestTypeVO) {
        insert("SuggestType.insertPropResultType", suggestTypeVO);
    }
    
    public void insertCommonCode(SuggestTypeVO suggestTypeVO) {
        insert("SuggestType.insertCommonCode", suggestTypeVO);
    }
    
    public int updatePropType(SuggestTypeVO suggestTypeVO) {
        return (Integer)update("SuggestType.updatePropType", suggestTypeVO);
    }
    
    public int updatePropResultType(SuggestTypeVO suggestTypeVO) {
        return (Integer)update("SuggestType.updatePropResultType", suggestTypeVO);
    }
    
    public int updateCommonCode(SuggestTypeVO suggestTypeVO) {
        return (Integer)update("SuggestType.updateCommonCode", suggestTypeVO);
    }
    
    public int deletePropType(SuggestTypeVO suggestTypeVO) {
        return (Integer)delete("SuggestType.deletePropType", suggestTypeVO);
    }
    
    public int deletePropResultType(SuggestTypeVO suggestTypeVO) {
        return (Integer)delete("SuggestType.deletePropResultType", suggestTypeVO);
    }
    
    public int deleteCommonCode(SuggestTypeVO suggestTypeVO) {
        return (Integer)delete("SuggestType.deleteCommonCode", suggestTypeVO);
    }
    
    public int selectByPrimaryKeyCount(SuggestTypeVO suggestTypeVO) {
        return (Integer)selectByPk("SuggestType.selectByPrimaryKeyCount", suggestTypeVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectPropTypeList(SuggestTypeVO suggestTypeVO){
        return list("SuggestType.selectPropTypeList", suggestTypeVO);
    }
    
}