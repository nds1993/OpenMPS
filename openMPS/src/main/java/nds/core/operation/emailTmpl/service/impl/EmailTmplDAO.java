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
package nds.core.operation.emailTmpl.service.impl;

import java.util.List;

import nds.core.operation.emailTmpl.service.EmailTmplVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("emailTmplDAO")
public class EmailTmplDAO extends EgovAbstractDAO {

    public EmailTmplDAO() {
        super();
    }

    public Object insertEmailTmpl(EmailTmplVO emailTmplVO) {
    	return insert("EmailTmpl.insertEmailTmpl", emailTmplVO);
    }

    public int updateEmailTmpl(EmailTmplVO emailTmplVO) {
        return (Integer)update("EmailTmpl.updateEmailTmpl", emailTmplVO);
    }
    @SuppressWarnings("unchecked")
    public List selectEmailTmplList(EmailTmplVO emailTmplVO) {
        return list("EmailTmpl.selectEmailTmplList", emailTmplVO);
    }
    
    public int selectEmailTmplListCount(EmailTmplVO emailTmplVO) {
        return ((Integer)selectByPk("EmailTmpl.selectEmailTmplListCount", emailTmplVO)).intValue();
    }
    

    public EmailTmplVO selectEmailTmplInfo(EmailTmplVO emailTmplVO) {
        return (EmailTmplVO)selectByPk("EmailTmpl.selectEmailTmplInfo", emailTmplVO);
    }
    
    public EmailTmplVO selectEmailTmplInfoByMessage(EmailTmplVO emailTmplVO) {
    	return (EmailTmplVO)selectByPk("EmailTmpl.selectEmailTmplInfoByMessage", emailTmplVO);
    }
    

    public int deleteEmailTmpl(EmailTmplVO emailTmplVO) {
        return (Integer)delete("EmailTmpl.deleteEmailTmpl", emailTmplVO);
    }
    
    public String selectEmailTmplNo(){
        return (String)selectByPk("EmailTmpl.selectEmailTmplNo", null);
    }
    
    public String selectInsertTmpl(EmailTmplVO emailTmplVO){
        return (String)selectByPk("EmailTmpl.selectInsertTmpl", emailTmplVO);
    }
    
    public EmailTmplVO selectTmplInfoVoc(EmailTmplVO emailTmplVO) {
        return (EmailTmplVO)selectByPk("EmailTmpl.selectTmplInfoVoc", emailTmplVO);
    }
    
}