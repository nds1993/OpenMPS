package nds.core.common.message.service;

import nds.core.common.common.service.Service;
import nds.core.common.message.MessageBean;

public interface BaseSmsService extends Service {

	public String sendSms(MessageBean vo) throws Exception ;
	
}
