package nds.core.common.message.service.impl;

import javax.annotation.Resource;

import nds.core.common.message.MessageBean;
import nds.core.common.message.service.MessageService;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("messageService")
public class MessageServiceImpl extends AbstractServiceImpl implements MessageService {

	 
	@Resource(name="messageDAO")
    private MessageDAO messageDAO;

    
    public String insertMessage(MessageBean bean) throws Exception {
    	
    	String result="";
    	try{
    		result = messageDAO.insertMessage(bean);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertMessage() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"위젯 count 조회",ex.getMessage()} 
            );
        }     
    	
    	return result;
    }
	
    
    
    public MessageBean selectMessageInfo(String msgNo) throws Exception {
    	
    	MessageBean result = null;
    	try{
    		result = messageDAO.selectMessageInfo(msgNo);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMessageInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"위젯 count 조회",ex.getMessage()} 
            );
        }     
    	
    	return result;
    }
    
    public int updateStatMessage(MessageBean bean) throws Exception {
    	
    	int result;
    	try{
    		result = messageDAO.updateStatMessage(bean);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateStatMessage() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"위젯 count 조회",ex.getMessage()} 
            );
        }     
    	
    	return result;
    }
	
}
