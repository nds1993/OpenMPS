package nds.core.operation.pushtmpl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.core.operation.pushtmpl.service.PushTmplService;
import nds.core.operation.pushtmpl.service.PushTmplVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * <b>class : PushTmplServiceImpl </b> <b>Class Description</b><br>
 * <b>History</b><br>
 * 
 * <pre>
 * : 2014.07.21 초기작성(오예솔)
 * </pre>
 * 
 * @author <a href="mailto:ysoh@nds.co.kr">오예솔</a>
 * @version 1.0
 */
@Service("pushtmplService")
public class PushTmplServiceImpl extends AbstractServiceImpl implements PushTmplService {

	@Resource(name = "pushtmplDAO")
	private PushTmplDAO pushtmplDAO;

	/**
     * 템플릿 조회
     * @param vo
     * @return List
     * @throws Exception
     */
    public List<PushTmplVO> selectPushTmplList(PushTmplVO vo) throws Exception {
		List<PushTmplVO> list = null;
		try {
            list = pushtmplDAO.selectByHelper(vo);
            return list;
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectPushTmplList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"템플릿 조회 중",ex.getMessage()} 
            );
        }
	}

    /**
     * 템플릿 총건수 조회
     * @param vo
     * @return int
     * @throws Exception
     */
    public int selectPushTmplCnt(PushTmplVO vo) throws Exception {
		int intTotalCount = 0;

        try {
        	intTotalCount = pushtmplDAO.selectCountByHelper(vo);
            return intTotalCount;
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectPushTmplCnt() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"템플릿 총건수 조회 중",ex.getMessage()} 
            );
        }
	}

    /**
     * 템플릿 상세
     * @param vo
     * @return PushTmplVO
     * @throws Exception
     */
    public PushTmplVO selectByPrimaryKey(PushTmplVO vo) throws Exception {
    	PushTmplVO	record = new PushTmplVO();
        try {
        	record = pushtmplDAO.selectByPrimaryKey(vo);
            return record;
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectByPrimaryKey() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"템플릿 상세 중",ex.getMessage()} 
            );
        }
	}
    
    /**
     * 템플릿 상세
     * @param vo
     * @return PushTmplVO
     * @throws Exception
     */
    public PushTmplVO selectByPushTmplInfo(PushTmplVO vo) throws Exception {
    	
    	PushTmplVO result = null;
    	
		try{
			result = pushtmplDAO.selectByPushTmplInfo(vo);
			
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectByPushTmplInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"템플릿 상세 조회",ex.getMessage()} 
            );
        }    
    	
    	return result;
    	
    }
    
    /**
     * 템플릿 상세
     * @param vo
     * @return PushTmplVO
     * @throws Exception
     */
    public PushTmplVO selectByMessingerTmplInfo(PushTmplVO vo) throws Exception {
    	
    	PushTmplVO result = null;
    	
    	try{
    		result = pushtmplDAO.selectByMessingerTmplInfo(vo);
    		
    	}catch(Exception ex){
    		throw ExceptionHelper.getException(ex
    				, this.getClass().getName() + " : " + "selectByMessingerTmplInfo() 에러 발생"
    						,"SYS001"
    						,new Object[] {"템플릿 상세 조회",ex.getMessage()} 
    				);
    	}    
    	
    	return result;
    	
    }
    
    /**
     * 템플릿 생성
     * @param vo
     * @return
     * @throws Exception
     */
    public String insertPushTmpl(PushTmplVO vo) throws Exception {
    		String docNo = "";
	        try {
	            docNo = (String) pushtmplDAO.insert(vo);
	            return docNo;
	        } catch (Exception ex) {
	            throw ExceptionHelper.getException(ex
	                    , this.getClass().getName() + " : " + "insertPushTmpl() 에러 발생"
	                    ,"SYS001"
	                    ,new Object[] {"템플릿 생성 중",ex.getMessage()} 
	            );
	        }
	}

    /**
     * 템플릿 수정
     * @param vo
     * @return
     * @throws Exception
     */
    public void updatePushTmpl(PushTmplVO vo) throws Exception {
		
		 try {
	            pushtmplDAO.updateByPrimaryKey(vo);
	            return;
	        } catch (Exception ex) {
	            throw ExceptionHelper.getException(ex
	                    , this.getClass().getName() + " : " + "updatePushTmpl() 에러 발생"
	                    ,"SYS001"
	                    ,new Object[] {"템플릿 수정 중",ex.getMessage()} 
	            );
	        }
	}

    /**
     * 템플릿 삭제
     * @param vo
     * @return
     * @throws Exception
     */
    public void deletePushTmpl(PushTmplVO vo) throws Exception {
		 try {
	            pushtmplDAO.deleteByPrimaryKey(vo);
	            return;
	        } catch (Exception ex) {
	            throw ExceptionHelper.getException(ex
	                    , this.getClass().getName() + " : " + "deletePushTmpl() 에러 발생"
	                    ,"SYS001"
	                    ,new Object[] {"템플릿 삭제 중",ex.getMessage()} 
	            );
	        }
	}

    /**
     * 템플릿 신규등록 전 등록가능여부 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public String selectInsertTmpl(PushTmplVO vo) throws Exception {
    	 String result = "";
		 try {
		        result = pushtmplDAO.selectInsertTmpl(vo);
		        return result;
		    } catch (Exception ex) {
		        throw ExceptionHelper.getException(ex
		                , this.getClass().getName() + " : " + "selectInsertTmpl() 에러 발생"
		                ,"SYS001"
		                ,new Object[] {"템플릿 신규등록 전 등록가능여부 조회 중",ex.getMessage()} 
		        );
		    }
		
	}

}