package nds.core.operation.holiday.service.impl;


import java.util.List;

import javax.annotation.Resource;

import nds.core.operation.holiday.service.HolidayService;
import nds.core.operation.holiday.service.HolidayVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : HolidayServiceImpl </b>
 * <b>Class Description</b><br>
 * 휴일 처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2012.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Service("holidayService")
public class HolidayServiceImpl extends AbstractServiceImpl implements HolidayService {
    
	@Resource(name="holidayDAO")
    private HolidayDAO holidayDAO;
    
    /**
     * 휴일 수정
     * @param HolidayVO
     * @throws Exception
     */  
    public void updateHday(HolidayVO holidayVO) throws Exception {
        try {       
        	holidayDAO.updateHday(holidayVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateHday() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"휴일 수정중",ex.getMessage()} 
            );
        }         
    } 
    
    /**
     * 휴일 월전체수정
     * @param HolidayVO
     * @return 
     * @throws Exception
     */
    public void updateHdayAll(HolidayVO holidayVO) throws Exception {
    	try{
	    	holidayDAO.updateHdayAll(holidayVO);
    	}catch (Exception ex) {
			throw ExceptionHelper.getException(ex
					, this.getClass().getName() + " : " + "updateHdayAll() 에러 발생"
					,"SYS001"
					,new Object[] {"휴일 월전체수정",ex.getMessage()} 
			);
		} 
    }

    /**
     * 휴일 생성
     * @param holidayVO
     * @throws Exception
     */  
    public void callSpMakeHday(HolidayVO holidayVO) throws Exception {
        try {       
        	holidayDAO.callSpMakeHday(holidayVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "callSPMakeHday() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"휴일 생성중",ex.getMessage()} 
            );
        }         
    }
    
    /**
     * 휴일 총건수 조회
     * @param holidayVO
     * @return int
     * @throws Exception
     */    
    public int selectHdayCount(HolidayVO holidayVO) throws Exception {
        int intTotalCount = 0;
        try{
            intTotalCount = holidayDAO.selectCountByHelper(holidayVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectHdayCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"휴일 총건수조회중",ex.getMessage()} 
            );
        }        

        return intTotalCount;
    }
    
    /**
     * 휴일 리스트 조회
     * @param holidayVO
     * @return List
     * @throws Exception
     */    
    public List selectHdayList(HolidayVO holidayVO) throws Exception {
        List list = null;
        try{
            list = holidayDAO.selectByHelper(holidayVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectHdayList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"휴일 리스트조회중",ex.getMessage()} 
            );
        }       

        return list;
    }

    /**
     * 사용자 휴일 조회
     * @param holidayVO
     * @return List
     * @throws Exception
     */    
    public List selectUserWorkHdayResult(HolidayVO holidayVO) throws Exception {
        List list = null;
        try{
            list = holidayDAO.selectUserWorkHdayResult(holidayVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectUserWorkHdayResult() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"휴사용자 휴일 조회중",ex.getMessage()} 
            );
        }       

        return list;
    }
    
    /**
     * 사용자 휴일 조회
     * @param holidayVO
     * @return int
     * @throws Exception
     */    
    public int selectUserWorkHdayResultCount(HolidayVO holidayVO) throws Exception {
        int count = 0;
        try{
        	count = holidayDAO.selectUserWorkHdayResultCount(holidayVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectUserWorkHdayResultCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"휴사용자 휴일 조회중",ex.getMessage()} 
            );
        }       

        return count;
    }
    /**
     * 휴일 시간조회
     * @param 
     * @return 
     * @throws Exception
     */
    public HolidayVO selectHdayTime(HolidayVO holidayVO) throws Exception {
        HolidayVO resultVO = new HolidayVO();
        try{
            resultVO = holidayDAO.selectHdayTime(holidayVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectHdayTime() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"휴일시간조회",ex.getMessage()} 
            );
        } 
        return resultVO;
    }
    
    /**
     * 휴일사유입력
     * @param holidayVO
     * @throws Exception
     */    
    public void updateHdayResn(HolidayVO holidayVO) throws Exception{
    	try{
    		holidayDAO.updateHdayResn(holidayVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateHdayResn() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"휴일사유입력",ex.getMessage()} 
            );
        } 
    }

    /**
     * 휴일등록
     * @param holidayVO
     * @throws Exception
     */    
    public void insertHdayResn(HolidayVO holidayVO) throws Exception {
    	try{
    		holidayDAO.insertHdayResn(holidayVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertHdayResn() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"휴일등록",ex.getMessage()} 
            );
        } 
    }

    /**
     * 휴일삭제
     * @param holidayVO
     * @throws Exception
     */    
    public void deleteHdayResn(HolidayVO holidayVO) throws Exception {
    	try{
    		holidayDAO.deleteHdayResn(holidayVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteHdayResn() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"휴일삭제",ex.getMessage()} 
            );
        } 
    }
}