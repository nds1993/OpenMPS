package nds.core.logsearch.systemlog.service.impl;

import java.util.List;

import nds.core.logsearch.systemlog.service.SystemLogVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("systemLogDAO")
public class SystemLogDAO extends EgovAbstractDAO{

	public SystemLogDAO() {
		super();
	}
	
	/**
	 * 시스템로그 조회
	 * @param systemLogVO
	 * @return List<SystemLogVO>
	 */
	public List selectSystemLogList(SystemLogVO systemLogVO){
		return list("SystemLog.selectSystemLogList", systemLogVO);
	}
	
	/**
	 * 시스템로그 카운트 조회
	 * @param systemLogVO
	 * @return Integer
	 */
	public int selectSystemLogCount(SystemLogVO systemLogVO){
		return (Integer)selectByPk("SystemLog.selectSystemLogCount", systemLogVO);
	}

	/**
	 * 시스템로그 정보 입력
	 * @param systemLogVO
	 * @return
	 * @throws Exception
	 */
	public String useLogWrite(SystemLogVO systemLogVO) throws Exception{
		return (String)insert("SystemLog.useLogWrite", systemLogVO);
	}
}
