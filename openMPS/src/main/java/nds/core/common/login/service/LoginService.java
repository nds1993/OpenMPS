package nds.core.common.login.service;



import nds.core.common.common.service.Service;
import nds.core.logsearch.loginlog.service.LoginLogVO;
import nds.core.userdep.user.service.UserVO;


/**
 * <b>class : LoginService </b>
 * <b>Class Description</b><br>
 * 로그인 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public interface LoginService extends Service {
    
    /**
     * 사용자 정보 조회
     * @param userVO
     * @return UserVO
     */
    UserVO getUserInfo(UserVO userVO, boolean mode) throws Exception;
    
    /**
     * 사용자 정보 Update(로그인 여부)
     * @param loginLogVO
     * @return String
     */
    String updateUserLogin(LoginLogVO loginLogVO) throws Exception;    
    
}
