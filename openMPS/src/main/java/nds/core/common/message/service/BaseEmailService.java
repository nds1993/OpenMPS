package nds.core.common.message.service;

import nds.core.common.common.service.Service;
import nds.core.common.message.BaseEmailVO;

public interface BaseEmailService extends Service {

	public String sendEmail(BaseEmailVO vo) throws Exception ;
	
}
