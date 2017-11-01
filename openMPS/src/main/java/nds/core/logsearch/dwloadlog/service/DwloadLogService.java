package nds.core.logsearch.dwloadlog.service;

import java.util.List;

import nds.core.common.common.service.Service;


/**
 * <b>class : DwloadLogService </b>
 * <b>Class Description</b><br>
 * 다운로드정보 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2015.05.20 초기작성(김세희)</pre>
 * @author <a href="mailto:shkim@nds.co.kr">김세희</a>
 * @version 1.0
 */
public interface DwloadLogService extends Service{
	/**
	 * 다운로드로그조회
	 * @param dwlogLogVO
	 * @return List
	 * @throws Exception
	 */
	List selectDwInfoLogList(DwloadLogVO dwlogLogVO) throws Exception;  
   
	/**
	 * 다운로드로그조회  총건수
	 * @param dwlogLogVO
	 * @return List
	 * @throws Exception
	 */
	int selectDwInfoLogListCnt(DwloadLogVO dwlogLogVO) throws Exception;

	/**
	 * 다운로드로그조회 로그
	 * @param dwlogLogVO
	 * @return void
	 * @throws Exception
	 */
	void insertDwLogInfo(DwloadLogVO dwlogLogVO) throws Exception;
	   
}
