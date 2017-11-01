package nds.core.messageModule.sms.service.impl;

import javax.annotation.Resource;

import nds.core.common.message.BaseSmsVO;
import nds.core.common.message.MessageBean;
import nds.core.common.message.service.BaseSmsService;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : MainChartServiceImpl </b>
 * <b>Class Description</b><br>
 * 메인화면을 처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Service("interfaceSmsService")
public class SmsServiceImpl extends AbstractServiceImpl implements BaseSmsService {

	@Resource(name="interfaceSmsDAO")
    private SmsDAO interfaceSmsDAO;

	/**
	 * SMS발송
	 */
	public String sendSms(MessageBean vo) throws Exception  {
		String result = null;
		
		try{
			// sms발송
			result = interfaceSmsDAO.sendSms(vo);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "sendSms() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"SMS발송",ex.getMessage()} 
            );
        }    
		
		return result;
	}

}
