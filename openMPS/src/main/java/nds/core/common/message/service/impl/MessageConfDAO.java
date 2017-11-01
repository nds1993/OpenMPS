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



import java.util.List;

import nds.core.common.message.MessageConfBean;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("messageConfDAO")
public class MessageConfDAO extends EgovAbstractDAO {

	@SuppressWarnings("unchecked")
	public List<MessageConfBean> selectMessageConfList(MessageConfBean bean) {
		return (List<MessageConfBean>)list("MessageConf.selectMessageInfo", bean);
	}
	
}