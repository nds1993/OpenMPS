package nds.core.logsearch.systemlog.service;

import java.util.List;

import nds.core.common.common.service.Service;

public interface SystemLogService extends Service{
	
	/**
	 * 시스템로그 조회
	 * @param systemLogVO
	 * @return List<SystemLogVO>
	 */
	List<SystemLogVO> selectSystemLogList(SystemLogVO systemLogVO) throws Exception;
	
	/**
	 * 시스템로그 카운트 조회
	 * @param systemLogVO
	 * @return Integer
	 */
	int selectSystemLogCount(SystemLogVO systemLogVO) throws Exception;
	
	/**
	 * 시스템로그 정보 입력
	 * @param systemLogVO
	 * @return
	 * @throws Exception
	 */
	String useLogWrite(SystemLogVO systemLogVO) throws Exception;
}
