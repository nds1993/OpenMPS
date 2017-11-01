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
package nds.core.messageModule.email.service.impl;



import nds.core.messageModule.email.service.DBEmailVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("interfaceEmailDAO")
public class DBEmailDAO extends EgovAbstractDAO {
    
	public String sendEmail(DBEmailVO vo) {
		return (String)insert("EMAIL.sendEmail", vo);
	}
}