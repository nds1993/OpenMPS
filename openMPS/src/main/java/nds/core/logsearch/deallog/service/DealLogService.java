package nds.core.logsearch.deallog.service;



import java.util.List;

import nds.core.common.common.service.Service;


/**
 * <b>class : DealLogService </b>
 * <b>Class Description</b><br>
 * 부서 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public interface DealLogService extends Service {
    
	   /**
	    * 예외로그조회
	    * @param dealLogVO
	    * @return List
	    * @throws Exception
	    */
	   List selectExcpLogInfoList(DealLogVO dealLogVO) throws Exception;  
	   
	   /**
	    * 예외로그조회  총건수
	    * @param dealLogVO
	    * @return List
	    * @throws Exception
	    */
	   int selectExcpLogInfoListCount(DealLogVO dealLogVO) throws Exception;
}
