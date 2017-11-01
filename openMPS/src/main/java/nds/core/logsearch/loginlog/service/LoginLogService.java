package nds.core.logsearch.loginlog.service;



import java.util.List;

import nds.core.common.common.service.Service;


/**
 * <b>class : LoginLogService </b>
 * <b>Class Description</b><br>
 * 로그인로그정보 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public interface LoginLogService extends Service {
    
	   /**
	    * 로그인정보조회 
	    * @param loginLogVO
	    * @return List
	    * @throws Exception
	    */
	   List selectLoginLogList(LoginLogVO loginLogVO) throws Exception;  
	   
	   
	   /**
	    * 로기은정보조회  총건수
	    * @param loginLogVO
	    * @return List
	    * @throws Exception
	    */
	   int selectLoginLogListCount(LoginLogVO loginLogVO) throws Exception;
	   
	   /**
	    * 접속자  총건수
	    * @param loginLogVO
	    * @return List
	    * @throws Exception
	    */
	   int selectLoginConnectCount(LoginLogVO loginLogVO) throws Exception;
}
