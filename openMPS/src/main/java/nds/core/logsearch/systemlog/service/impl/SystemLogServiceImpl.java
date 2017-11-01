package nds.core.logsearch.systemlog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.core.logsearch.systemlog.service.SystemLogService;
import nds.core.logsearch.systemlog.service.SystemLogVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("systemLogService")
public class SystemLogServiceImpl extends AbstractServiceImpl implements SystemLogService{

	@Resource(name="systemLogDAO")
    private SystemLogDAO systemLogDAO;
	
	/**
	 * 시스템로그 조회
	 * @param systemLogVO
	 * @return List<SystemLogVO>
	 */
	public List<SystemLogVO> selectSystemLogList(SystemLogVO systemLogVO) throws Exception{
		List<SystemLogVO> list = null;
		try{
            list = systemLogDAO.selectSystemLogList(systemLogVO); 
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectSystemLogList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"시스템로그 조회",ex.getMessage()} 
            );
        }    
		return list;
	}
	
	/**
	 * 시스템로그 카운트 조회
	 * @param systemLogVO
	 * @return Integer
	 */
	public int selectSystemLogCount(SystemLogVO systemLogVO) throws Exception{
		int count = 0;
		try{
            count = systemLogDAO.selectSystemLogCount(systemLogVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectSystemLogCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"시스템로그 카운트 조회",ex.getMessage()} 
            );
        }      
		return count;
	}
	
	/**
	 * 시스템로그 정보 입력
	 * @param systemLogVO
	 * @return
	 * @throws Exception
	 */
	public String useLogWrite(SystemLogVO systemLogVO) throws Exception{
		String result = "";
		try{
            result = systemLogDAO.useLogWrite(systemLogVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "useLogWrite() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"시스템로그 정보 입력",ex.getMessage()} 
            );
        }   
		return result;
	}
}
