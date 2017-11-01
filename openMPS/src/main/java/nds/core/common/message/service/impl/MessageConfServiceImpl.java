package nds.core.common.message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.core.common.message.MessageConfBean;
import nds.core.common.message.service.MessageConfService;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("messageConfService")
public class MessageConfServiceImpl extends AbstractServiceImpl implements MessageConfService {

	
	@Resource(name="messageConfDAO")
    private MessageConfDAO messageConfDAO;

	/**
	 * 메시지 설정 조회
	 */
	public List<MessageConfBean> selectMessageConfList(MessageConfBean msgConfBean ) throws Exception {
		
		List<MessageConfBean> result=null;
    	try{
    		result = messageConfDAO.selectMessageConfList(msgConfBean);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMessageConfList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"메시지 설정 조회",ex.getMessage()} 
            );
        }     
    	
    	return result;
		
	}
	
}
