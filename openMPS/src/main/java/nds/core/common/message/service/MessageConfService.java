package nds.core.common.message.service;

import java.util.List;

import nds.core.common.message.MessageConfBean;

public interface MessageConfService {

	public List<MessageConfBean> selectMessageConfList(MessageConfBean msgConfBean ) throws Exception;
	
}
