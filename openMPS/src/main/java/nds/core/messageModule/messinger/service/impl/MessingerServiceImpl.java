package nds.core.messageModule.messinger.service.impl;

import javax.annotation.Resource;

import nds.core.common.message.BaseMessingerVO;
import nds.core.common.message.service.BaseMessingerService;
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


@Service("interfaceMassingerService")
public class MessingerServiceImpl extends AbstractServiceImpl implements BaseMessingerService {

	@Resource(name="interfaceMessingerDAO")
    private MessingerDAO interfaceMessingerDAO;

	/**
	 * 메신저발송
	 */
	public String sendMessinger(BaseMessingerVO vo) throws Exception  {
		
		String result = null;
		
		try{
			
			result = interfaceMessingerDAO.sendMessinger(vo);
			
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "sendMessinger() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"메신저발송",ex.getMessage()} 
            );
        }    
		
		return result;
	}
}
