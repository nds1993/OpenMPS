package nds.core.common.message.service;

import nds.core.common.message.MessageBean;



public interface MessageService {

	public String insertMessage(MessageBean bean) throws Exception;
	
	public MessageBean selectMessageInfo(String msgNo) throws Exception;
	
	public int updateStatMessage(MessageBean bean) throws Exception;
	
}