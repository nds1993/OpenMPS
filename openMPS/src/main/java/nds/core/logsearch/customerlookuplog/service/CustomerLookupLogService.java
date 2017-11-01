package nds.core.logsearch.customerlookuplog.service;



import java.util.List;

import nds.core.common.common.service.Service;


/**
 * <b>class : CustomerLookupLogService </b>
 * <b>Class Description</b><br>
 * 부서 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public interface CustomerLookupLogService extends Service {
    
	/**
	 * 고객정보조회로그조회
	 * @param customerLookupLogVO
	 * @return List
	 * @throws Exception
	 */
	List selectCstInfoLogList(CustomerLookupLogVO customerLookupLogVO) throws Exception;  
   
	/**
	 * 고객정보조회로그조회  총건수
	 * @param customerLookupLogVO
	 * @return List
	 * @throws Exception
	 */
	int selectCstInfoLogListCnt(CustomerLookupLogVO customerLookupLogVO) throws Exception;

	/**
	 * 화면명 조회
	 * @param key
	 * @return CustomerLookupLogVO
	 * @throws Exception
	 */
	CustomerLookupLogVO selectMenuNm(String key) throws Exception;
	
	/**
	 * 회원조회 로그
	 * @param customerLookupLogVO
	 * @return void
	 * @throws Exception
	 */
	void insertCstLogInfo(CustomerLookupLogVO customerLookupLogVO) throws Exception;
	   
}
