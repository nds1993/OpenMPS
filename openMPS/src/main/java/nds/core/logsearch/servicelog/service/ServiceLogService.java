package nds.core.logsearch.servicelog.service;

import java.util.List;

import nds.core.common.common.service.Service;

/**
 * <b>class : ServiceLogService </b>
 * <b>Class Description</b><br>
 * 서비스로그 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2015.05.21 초기작성(최찬형)</pre>
 * @author <a href="mailto:chchoi@nds.co.kr">최찬형</a>
 * @version 1.0
 */
public interface ServiceLogService extends Service{

	/**
	 * 서비스로그 조회
	 * @param serviceLogVO
	 * @return List<ServiceLogVO>
	 */
	List<ServiceLogVO> selectServiceLogList(ServiceLogVO serviceLogVO) throws Exception;
	
	/**
	 * 서비스로그 카운트 조회
	 * @param serviceLogVO
	 * @return Integer
	 */
	int selectServiceLogCount(ServiceLogVO serviceLogVO) throws Exception;
}
