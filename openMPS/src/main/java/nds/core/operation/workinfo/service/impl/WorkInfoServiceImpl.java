package nds.core.operation.workinfo.service.impl;



import java.util.List;

import javax.annotation.Resource;

import nds.core.operation.workinfo.service.WorkInfoService;
import nds.core.operation.workinfo.service.WorkInfoVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;



/**
 * <p>Title: WorkInfoServiceImpl</p>
 * <p>Description: Service Implements Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.26 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
@Service("workInfoService")
public class WorkInfoServiceImpl  extends AbstractServiceImpl implements WorkInfoService {
    
	@Resource(name="workInfoDAO")
    private WorkInfoDAO workInfoDAO;

    /**
     * 담당업무정보 조회
     * @param workInfoVO
     * @return List
     * @throws Exception
     */
    public List<WorkInfoVO> selectUsrWrkInfo(WorkInfoVO workInfoVO) throws Exception{
            List<WorkInfoVO> list = null;
            try{
                list = workInfoDAO.selectUsrWrkInfo(workInfoVO);
            }catch(Exception ex){
                throw ExceptionHelper.getException(ex
                        , this.getClass().getName() + " : " + "selectUsrWrkInfo() 에러 발생"
                        ,"SYS001"
                        ,new Object[] {"담당업무정보 조회",ex.getMessage()} 
                );
            }
            return list;         
    }

    /**
     * 담당업무정보 조회 총건수
     * @param workInfoVO
     * @return int
     * @throws Exception
     */
    public int selectUsrWrkInfoCount(WorkInfoVO workInfoVO) throws Exception{
        int count = 0;
        try{
            count = workInfoDAO.selectUsrWrkInfoCount(workInfoVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectUsrWrkInfoCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"담당업무정보 총건수",ex.getMessage()} 
            );
        }
        return count;         
    }


    /**
     * 담당업무정보 상세조회
     * @param workInfoVO
     * @return WorkInfoVO
     * @throws Exception
     */
    public WorkInfoVO selectUsrWrkInfoDetail(WorkInfoVO workInfoVO) throws Exception{
        WorkInfoVO record = null;
        try{
            record = workInfoDAO.selectUsrWrkInfoDetail(workInfoVO.getUserId());
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectUsrWrkInfoDetail() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"담당업무정보 상세조회",ex.getMessage()} 
            );
        }
        return record;         
    }

    
    /**
     * 담당업무정보 업무정보 등록
     * @param workInfoVO
     * @return void
     * @throws Exception
     */
    public void updateUsrWrkInfo(WorkInfoVO workInfoVO) throws Exception{
        try{
        	workInfoDAO.updateUsrWrkInfo(workInfoVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateUsrWrkInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"담당업무정보 업무정보 등록",ex.getMessage()} 
            );
        }     
    }
    
}