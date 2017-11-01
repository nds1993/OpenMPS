package nds.core.logsearch.servicelog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.core.logsearch.servicelog.service.ServiceLogService;
import nds.core.logsearch.servicelog.service.ServiceLogVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("serviceLogService")
public class ServiceLogServiceImpl extends AbstractServiceImpl implements ServiceLogService{

	@Resource(name="serviceLogDAO")
    private ServiceLogDAO serviceLogDAO;
	
	/**
	 * 서비스로그 조회
	 * @param serviceLogVO
	 * @return List<ServiceLogVO>
	 */
	public List<ServiceLogVO> selectServiceLogList(ServiceLogVO serviceLogVO) throws Exception{
		List list = null;
        try{
            list = serviceLogDAO.selectServiceLogList(serviceLogVO); 
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectServiceLogList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"서비스로그 조회",ex.getMessage()} 
            );
        }        
        return list;
	}
	
	/**
	 * 서비스로그 카운트 조회
	 * @param serviceLogVO
	 * @return Integer
	 */
	public int selectServiceLogCount(ServiceLogVO serviceLogVO) throws Exception{
		int count = 0;
        try{
        	count = serviceLogDAO.selectServiceLogCount(serviceLogVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectServiceLogCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"서비스로그 카운트 조회",ex.getMessage()} 
            );
        }        
        return count;
	}
}
