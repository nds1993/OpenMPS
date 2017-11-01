package nds.core.request.cooperation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.core.operation.approval.service.ApprovalVO;
import nds.core.request.cooperation.service.CooperationService;
import nds.core.request.cooperation.service.CooperationVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

/**
 * <p>Title: CooperationServiceImpl</p>
 * <p>Description: Service Implements Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2015.07.30 초기작성(김세희)</pre>
 * @author <a href="mailto:shkim@nds.co.kr">김세희</a>
 * @version 1.0
 */

@Service("cooperationService")
public class CooperationServiceImpl implements CooperationService{

	@Resource(name="cooperationDAO")
    private CooperationDAO cooperationDAO;
	
	 /**
     * 의견/검토 카운트 조회
     * 
     * @param cooperationVO
     * @return int
     * @throws Exception
     */
    public int selectCooListCount(CooperationVO cooperationVO) throws Exception {
        int count = 0;
        try {
            count = cooperationDAO.selectCooListCount(cooperationVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCooListCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"의견/검토 카운트 조회",ex.getMessage()} 
            );
        }
        return count;
    }

    /**
     * 의견/검토 조회
     * 
     * @param cooperationVO
     * @return List
     * @throws Exception
     */
    public List<CooperationVO> selectCooList(CooperationVO cooperationVO) throws Exception {
        List<CooperationVO> list = null;
        try {
            list = cooperationDAO.selectCooList(cooperationVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCooList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"의견/검토 조회",ex.getMessage()} 
            );
        }
        return list;
    }

    
    /**
     *의견/검토 VOC 상세조회
     * 
     * @param cooperationVO
     * @return CooperationVO
     * @throws Exception
     */
    public CooperationVO selectReqVoc(CooperationVO cooperationVO) throws Exception {
    	CooperationVO result = null;
        try{
        	result = cooperationDAO.selectReqVoc(cooperationVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectReqVoc() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"의견/검토 VOC 상세조회",ex.getMessage()} 
            );
        }       
        
        return result;
    }
    
    /**
     *의견/검토 상세조회
     * 
     * @param cooperationVO
     * @return CooperationVO
     * @throws Exception
     */
    public CooperationVO selectReq(CooperationVO cooperationVO) throws Exception {
    	CooperationVO result = null;
        try{
        	result = cooperationDAO.selectReq(cooperationVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectReq() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"의견/검토 상세조회",ex.getMessage()} 
            );
        }       
        
        return result;
    }
    
    
    
    /**
     *의견/검토 임시저장
     * 
     * @param cooperationVO
     * @return void
     * @throws Exception
     */
   public void updateTemVoc(CooperationVO cooperationVO) throws Exception{
	    try {
	    	cooperationDAO.updateTemVoc(cooperationVO);
	    }catch (Exception ex) {
	        throw ExceptionHelper.getException(ex
	                , this.getClass().getName() + " : " + "updateTemVoc() 에러 발생"
	                ,"SYS001"
	                ,new Object[] {"의견/검토 임시저장 취소",ex.getMessage()} 
	        );
	    }
   }
    
    /**
     *의견/검토 검토저장
     * 
     * @param cooperationVO
     * @return void
     * @throws Exception
     */
    public void updateReqVoc(CooperationVO cooperationVO) throws Exception{
        try {
        	cooperationDAO.updateReqVoc(cooperationVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateReqVoc() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"의견/검토 검토저장",ex.getMessage()} 
            );
        }
    }
}
