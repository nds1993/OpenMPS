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
package nds.core.operation.smstmpl.service.impl;

import java.util.List;

import nds.core.operation.smstmpl.service.SmsTmplVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("smstmplDAO")
public class SmsTmplDAO extends EgovAbstractDAO {

    public SmsTmplDAO() {
        super();
    }

    public Object insert(SmsTmplVO smsVO) {
    	return insert("SmsTmpl_SqlMap.insert", smsVO);
    }

    public int updateByPrimaryKey(SmsTmplVO smsVO) {
        return (Integer)update("SmsTmpl_SqlMap.updateByPrimaryKey", smsVO);
    }
    @SuppressWarnings("unchecked")
    public List selectByHelper(SmsTmplVO smsVO) {
        return list("SmsTmpl_SqlMap.selectByHelper", smsVO);
    }
    
    public int selectCountByHelper(SmsTmplVO smsVO) {
        return (Integer)selectByPk("SmsTmpl_SqlMap.selectCountByHelper", smsVO);
    }

    public int deleteByPrimaryKey(SmsTmplVO smsVO) {
        return (Integer)delete("SmsTmpl_SqlMap.deleteByPrimaryKey", smsVO);
    }
    
    public String selectInsertTmpl(SmsTmplVO smsVO){
        return (String)selectByPk("SmsTmpl_SqlMap.selectInsertTmpl", smsVO);
    }
    
    public SmsTmplVO selectByPrimaryKey(SmsTmplVO smsVO){
        return (SmsTmplVO)selectByPk("SmsTmpl_SqlMap.selectByPrimaryKey", smsVO);
    }
    

    public SmsTmplVO selectBySmsTmplInfo(SmsTmplVO smsVO){
        return (SmsTmplVO)selectByPk("SmsTmpl_SqlMap.selectBySmsTmplInfo", smsVO);
    }
    
    public SmsTmplVO selectByMessingerTmplInfo(SmsTmplVO smsVO){
    	return (SmsTmplVO)selectByPk("SmsTmpl_SqlMap.selectByMessingerTmplInfo", smsVO);
    }
    
    
}