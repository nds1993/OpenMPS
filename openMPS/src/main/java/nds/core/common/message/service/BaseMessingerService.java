package nds.core.common.message.service;

import nds.core.common.common.service.Service;
import nds.core.common.message.BaseMessingerVO;

public interface BaseMessingerService extends Service {

	public String sendMessinger(BaseMessingerVO vo) throws Exception ;
	
}
