package nds.core.logsearch.loginlog.service.impl;


import java.util.List;

import javax.annotation.Resource;

import nds.core.logsearch.loginlog.service.LoginLogService;
import nds.core.logsearch.loginlog.service.LoginLogVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : LoginLogServiceImpl </b>
 * <b>Class Description</b><br>
 * 공통 처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2012.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Service("loginLogService")
public class LoginLogServiceImpl extends AbstractServiceImpl implements LoginLogService {
    
	@Resource(name="loginLogDAO")
    private LoginLogDAO loginLogDAO;
    

    /**
     *  로그인로그조회
     * @param loginLogVO
     * @return List
     * @throws Exception
     */
    public List selectLoginLogList(LoginLogVO loginLogVO) throws Exception {
        List list = null;
        try{
            list = loginLogDAO.selectLoginLogList(loginLogVO); 
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectLoginLogList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"로그인로그조회",ex.getMessage()} 
            );
        }        
        return list;
    } 
            
    /**
     * 로그인로그조회  총건수
     * @param loginLogVO
     * @return int
     * @throws Exception
     */    
    public int selectLoginLogListCount(LoginLogVO loginLogVO) throws Exception {
        int intTotalCount = 0;
        try{
            intTotalCount = loginLogDAO.selectLoginLogListCount(loginLogVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectLoginLogListCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"로그인로그조회 총건수 ",ex.getMessage()} 
            );
        }        

        return intTotalCount;
    }
    
    /**
     * 접속자  총건수
     * @param loginLogVO
     * @return int
     * @throws Exception
     */    
    public int selectLoginConnectCount(LoginLogVO loginLogVO) throws Exception {
        int intConnectCount = 0;
        try{
        	intConnectCount = loginLogDAO.selectConnectCount(loginLogVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectConnectCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"접속자 총건수 ",ex.getMessage()} 
            );
        }        

        return intConnectCount;
    }

}