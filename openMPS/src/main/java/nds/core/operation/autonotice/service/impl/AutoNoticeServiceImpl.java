package nds.core.operation.autonotice.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.core.operation.autonotice.service.AutoNoticeService;
import nds.core.operation.autonotice.service.AutoNoticeVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <p>Title: AutoNoticeServiceImpl</p>
 * <p>Description: Service Implements Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2015.04.22 초기작성(김세희)</pre>
 * @author <a href="mailto:shkim@nds.co.kr">김세희</a>
 * @version 1.0
 */
@Service("autoNoticeService")
public class AutoNoticeServiceImpl extends AbstractServiceImpl implements AutoNoticeService{

	@Resource(name="autoNoticeDAO")
    private AutoNoticeDAO autoNoticeDAO;

	 /**
     * 자동알림설정 건수 조회
     * @param autoNoticeVO
     * @return void
     * @throws Exception
     */
	public int selectAutoListCount(AutoNoticeVO autoNoticeVO) throws Exception{
	   int count = 0;
        try {
            count = autoNoticeDAO.selectAutoListCount(autoNoticeVO);
            return count;
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectAutoListCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"자동알림설정 건수 조회 중",ex.getMessage()} 
            );
        }
    }  

	 /**
	 * 자동알림설정 목록 조회
	 * @param autoNoticeVO
	 * @return void
	 * @throws Exception
	 */
	public  List<AutoNoticeVO> selectAutoList(AutoNoticeVO autoNoticeVO) throws Exception{
		List<AutoNoticeVO> list = null;
	        
        try {
            list = autoNoticeDAO.selectAutoList(autoNoticeVO);
            return list;
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectAutoList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"자동알림설정 목록 조회 중",ex.getMessage()} 
            );
        }
	}

	
	 /**
     * 자동알림설정 신규등록 전 등록가능여부 조회
     * @param autoNoticeVO
     * @return
     * @throws Exception
     */
    public String selectInsertAuto(AutoNoticeVO autoNoticeVO) throws Exception {
    	 String result = "";
		 try {
		        result = autoNoticeDAO.selectInsertAuto(autoNoticeVO);
		        return result;
		    } catch (Exception ex) {
		        throw ExceptionHelper.getException(ex
		                , this.getClass().getName() + " : " + "selectInsertAuto() 에러 발생"
		                ,"SYS001"
		                ,new Object[] {"자동알림설정 신규등록 전 등록가능여부 조회 중",ex.getMessage()} 
		        );
		    }
		
	}
	
	/**
     * 자동알림설정 생성
     * @param autoNoticeVO
     * @throws Exception
     */
    public void insertAutoNotice(List<AutoNoticeVO> autoNoticeVO) throws Exception {
	        try {
	        	
	        	for(int i = 0; i < autoNoticeVO.size(); i++) {
	        		autoNoticeDAO.mergeIntoAuto(autoNoticeVO.get(i));
				}
	        
	        } catch (Exception ex) {
	            throw ExceptionHelper.getException(ex
	                    , this.getClass().getName() + " : " + "insertAutoNotice() 에러 발생"
	                    ,"SYS001"
	                    ,new Object[] {"자동알림설정 생성 중",ex.getMessage()} 
	            );
	        }
	}
    
    /**
     * 자동알림설정 삭제
     * @param autoNoticeVO
     * @return
     * @throws Exception
     */
    public void deleteAuto(AutoNoticeVO autoNoticeVO) throws Exception {
		 try {
	            autoNoticeDAO.updateByPrimaryKey(autoNoticeVO);
	            return;
	        } catch (Exception ex) {
	            throw ExceptionHelper.getException(ex
	                    , this.getClass().getName() + " : " + "deleteAuto() 에러 발생"
	                    ,"SYS001"
	                    ,new Object[] {"자동알림설정 삭제 중",ex.getMessage()} 
	            );
	        }
	}

}
