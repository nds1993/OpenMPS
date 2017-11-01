package nds.core.messageModule.email.service.impl;

import javax.annotation.Resource;

import nds.core.common.message.BaseEmailVO;
import nds.core.common.message.service.BaseEmailService;
import nds.core.messageModule.email.service.DBEmailVO;
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

// SERVICE명은 수정하지 마시오. //
@Service("interfaceEmailService")
public class DBEmailServiceImpl extends AbstractServiceImpl implements BaseEmailService {

	@Resource(name="interfaceEmailDAO")
    private DBEmailDAO interfaceEmailDAO;

	/**
	 * Email발송
	 */
	public String sendEmail(BaseEmailVO vo) throws Exception  {
		
		String result = null;
		
		try{
			
			// EMAIL 발송
			result = interfaceEmailDAO.sendEmail((DBEmailVO)vo);
			
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "sendEmail() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"SMS발송",ex.getMessage()} 
            );
        }    
		
		return result;
	}

}
