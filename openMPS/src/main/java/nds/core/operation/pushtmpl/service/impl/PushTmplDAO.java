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
package nds.core.operation.pushtmpl.service.impl;

import java.util.List;

import nds.core.operation.pushtmpl.service.PushTmplVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("pushtmplDAO")
public class PushTmplDAO extends EgovAbstractDAO {

    public PushTmplDAO() {
        super();
    }

    public Object insert(PushTmplVO vo) {
    	return insert("PushTmpl_SqlMap.insert", vo);
    }

    public int updateByPrimaryKey(PushTmplVO vo) {
        return (Integer)update("PushTmpl_SqlMap.updateByPrimaryKey", vo);
    }
    @SuppressWarnings("unchecked")
    public List selectByHelper(PushTmplVO vo) {
        return list("PushTmpl_SqlMap.selectByHelper", vo);
    }
    
    public int selectCountByHelper(PushTmplVO vo) {
        return (Integer)selectByPk("PushTmpl_SqlMap.selectCountByHelper", vo);
    }

    public int deleteByPrimaryKey(PushTmplVO vo) {
        return (Integer)delete("PushTmpl_SqlMap.deleteByPrimaryKey", vo);
    }
    
    public String selectInsertTmpl(PushTmplVO vo){
        return (String)selectByPk("PushTmpl_SqlMap.selectInsertTmpl", vo);
    }
    
    public PushTmplVO selectByPrimaryKey(PushTmplVO vo){
        return (PushTmplVO)selectByPk("PushTmpl_SqlMap.selectByPrimaryKey", vo);
    }
    

    public PushTmplVO selectByPushTmplInfo(PushTmplVO vo){
        return (PushTmplVO)selectByPk("PushTmpl_SqlMap.selectByPushTmplInfo", vo);
    }
    
    public PushTmplVO selectByMessingerTmplInfo(PushTmplVO vo){
    	return (PushTmplVO)selectByPk("PushTmpl_SqlMap.selectByMessingerTmplInfo", vo);
    }
    
    
}