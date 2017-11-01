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
package nds.core.operation.ccmtmpl.service.impl;

import java.util.List;

import nds.core.operation.ccmtmpl.service.CcmTmplVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("ccmtmplDAO")
public class CcmTmplDAO extends EgovAbstractDAO {

    public CcmTmplDAO() {
        super();
    }

    public Object insert(CcmTmplVO vo) {
    	return insert("CcmTmpl_SqlMap.insert", vo);
    }

    public int updateByPrimaryKey(CcmTmplVO vo) {
        return (Integer)update("CcmTmpl_SqlMap.updateByPrimaryKey", vo);
    }
    @SuppressWarnings("unchecked")
    public List selectByHelper(CcmTmplVO vo) {
        return list("CcmTmpl_SqlMap.selectByHelper", vo);
    }
    
    public int selectCountByHelper(CcmTmplVO vo) {
        return (Integer)selectByPk("CcmTmpl_SqlMap.selectCountByHelper", vo);
    }

    public int deleteByPrimaryKey(CcmTmplVO vo) {
        return (Integer)delete("CcmTmpl_SqlMap.deleteByPrimaryKey", vo);
    }
    
    public String selectInsertTmpl(CcmTmplVO vo){
        return (String)selectByPk("CcmTmpl_SqlMap.selectInsertTmpl", vo);
    }
    
    public CcmTmplVO selectByPrimaryKey(CcmTmplVO vo){
        return (CcmTmplVO)selectByPk("CcmTmpl_SqlMap.selectByPrimaryKey", vo);
    }
    

    public CcmTmplVO selectByCcmTmplInfo(CcmTmplVO vo){
        return (CcmTmplVO)selectByPk("CcmTmpl_SqlMap.selectByCcmTmplInfo", vo);
    }
    
    public CcmTmplVO selectByMessingerTmplInfo(CcmTmplVO vo){
    	return (CcmTmplVO)selectByPk("CcmTmpl_SqlMap.selectByMessingerTmplInfo", vo);
    }
    
    
}