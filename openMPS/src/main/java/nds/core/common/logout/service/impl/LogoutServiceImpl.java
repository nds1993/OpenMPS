package nds.core.common.logout.service.impl;


import javax.annotation.Resource;

import nds.core.common.logout.service.LogoutService;
import nds.core.logsearch.loginlog.service.LoginLogVO;
import nds.core.logsearch.loginlog.service.impl.LoginLogDAO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : LoginServiceImpl </b>
 * <b>Class Description</b><br>
 * 공통 처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2013.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Service("logoutService")
public class LogoutServiceImpl extends AbstractServiceImpl implements LogoutService {
    
	@Resource(name="loginLogDAO")
    private LoginLogDAO loginLogDAO;	
	
    
    /**
     * 사용자 로그아웃시 로그아웃 여부 업데이트
     * @param loginLogVO
     * @return 
     */
    public synchronized void updateUserLogout(LoginLogVO loginLogVO) throws Exception{
        try
        {
        	loginLogDAO.updateByPrimaryKeySelective(loginLogVO);          
        }catch(Exception ex){
            ex.printStackTrace();
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateUserLogout() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"사용자 정보 Login 업데이트중",ex.getMessage()} 
            );
        }           
    }
    
}