package nds.core.common.logout.service;



import nds.core.common.common.service.Service;
import nds.core.logsearch.loginlog.service.LoginLogVO;


/**
 * <b>class : LogoutService </b>
 * <b>Class Description</b><br>
 * 공통 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public interface LogoutService extends Service{
    
    /**
     * 사용자 정보 Update(로그인 여부)
     * @param loginLogVO
     * @return 
     */
    void updateUserLogout(LoginLogVO loginLogVO) throws Exception;   
   
}