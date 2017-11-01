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
package nds.core.common.message.service.impl;

 

import nds.core.common.message.MessageBean;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("messageDAO")
public class MessageDAO extends EgovAbstractDAO {

	public String insertMessage(MessageBean bean) {
		
		return (String)insert("Message.insertMessage", bean);
	}
	
	public int updateStatMessage(MessageBean bean) {
		
		return update("Message.updateStatMessage", bean);
	}
	
	
	public MessageBean selectMessageInfo(String msgNo) {
		
		return (MessageBean)selectByPk("Message.selectMessageInfo", msgNo);
	}
	
}