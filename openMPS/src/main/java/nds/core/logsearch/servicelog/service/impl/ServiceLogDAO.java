package nds.core.logsearch.servicelog.service.impl;

import java.util.List;

import nds.core.logsearch.servicelog.service.ServiceLogVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("serviceLogDAO")
public class ServiceLogDAO extends EgovAbstractDAO{

	public ServiceLogDAO() {
		super();
	}
	
	/**
	 * 서비스로그 조회
	 * @param serviceLogVO
	 * @return List<ServiceLogVO>
	 */
	public List selectServiceLogList(ServiceLogVO serviceLogVO){
		return list("ServiceLog.selectServiceLogList", serviceLogVO);
	}
	
	/**
	 * 서비스로그 카운트 조회
	 * @param serviceLogVO
	 * @return Integer
	 */
	public int selectServiceLogCount(ServiceLogVO serviceLogVO){
		return (Integer)selectByPk("ServiceLog.selectServiceLogCount", serviceLogVO);
	}
}
