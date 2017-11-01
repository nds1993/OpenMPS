package nds.core.operation.smstmpl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.core.operation.smstmpl.service.SmsTmplService;
import nds.core.operation.smstmpl.service.SmsTmplVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * <b>class : SmsTmplServiceImpl </b> <b>Class Description</b><br>
 * <b>History</b><br>
 * 
 * <pre>
 * : 2014.07.21 초기작성(오예솔)
 * </pre>
 * 
 * @author <a href="mailto:ysoh@nds.co.kr">오예솔</a>
 * @version 1.0
 */
@Service("smstmplService")
public class SmsTmplServiceImpl extends AbstractServiceImpl implements SmsTmplService {

	@Resource(name = "smstmplDAO")
	private SmsTmplDAO smstmplDAO;

	/**
     * 템플릿 조회
     * @param smsVO
     * @return List
     * @throws Exception
     */
    public List<SmsTmplVO> selectSmsTmplList(SmsTmplVO smsVO) throws Exception {
		List<SmsTmplVO> list = null;
		try {
            list = smstmplDAO.selectByHelper(smsVO);
            return list;
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectSmsTmplList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"템플릿 조회 중",ex.getMessage()} 
            );
        }
	}

    /**
     * 템플릿 총건수 조회
     * @param smsVO
     * @return int
     * @throws Exception
     */
    public int selectSmsTmplCnt(SmsTmplVO smsVO) throws Exception {
		int intTotalCount = 0;

        try {
        	intTotalCount = smstmplDAO.selectCountByHelper(smsVO);
            return intTotalCount;
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectSmsTmplCnt() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"템플릿 총건수 조회 중",ex.getMessage()} 
            );
        }
	}

    /**
     * 템플릿 상세
     * @param smsVO
     * @return SmsTmplVO
     * @throws Exception
     */
    public SmsTmplVO selectByPrimaryKey(SmsTmplVO smsVO) throws Exception {
    	SmsTmplVO	record = new SmsTmplVO();
        try {
        	record = smstmplDAO.selectByPrimaryKey(smsVO);
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
     * @param smsVO
     * @return SmsTmplVO
     * @throws Exception
     */
    public SmsTmplVO selectBySmsTmplInfo(SmsTmplVO smsVO) throws Exception {
    	
    	SmsTmplVO result = null;
    	
		try{
			result = smstmplDAO.selectBySmsTmplInfo(smsVO);
			
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectBySmsTmplInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"템플릿 상세 조회",ex.getMessage()} 
            );
        }    
    	
    	return result;
    	
    }
    
    /**
     * 템플릿 상세
     * @param smsVO
     * @return SmsTmplVO
     * @throws Exception
     */
    public SmsTmplVO selectByMessingerTmplInfo(SmsTmplVO smsVO) throws Exception {
    	
    	SmsTmplVO result = null;
    	
    	try{
    		result = smstmplDAO.selectByMessingerTmplInfo(smsVO);
    		
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
     * @param smsVO
     * @return
     * @throws Exception
     */
    public String insertSmsTmpl(SmsTmplVO smsVO) throws Exception {
    		String docNo = "";
	        try {
	            docNo = (String) smstmplDAO.insert(smsVO);
	            return docNo;
	        } catch (Exception ex) {
	            throw ExceptionHelper.getException(ex
	                    , this.getClass().getName() + " : " + "insertSmsTmpl() 에러 발생"
	                    ,"SYS001"
	                    ,new Object[] {"템플릿 생성 중",ex.getMessage()} 
	            );
	        }
	}

    /**
     * 템플릿 수정
     * @param smsVO
     * @return
     * @throws Exception
     */
    public void updateSmsTmpl(SmsTmplVO smsVO) throws Exception {
		
		 try {
	            smstmplDAO.updateByPrimaryKey(smsVO);
	            return;
	        } catch (Exception ex) {
	            throw ExceptionHelper.getException(ex
	                    , this.getClass().getName() + " : " + "updateSmsTmpl() 에러 발생"
	                    ,"SYS001"
	                    ,new Object[] {"템플릿 수정 중",ex.getMessage()} 
	            );
	        }
	}

    /**
     * 템플릿 삭제
     * @param smsVO
     * @return
     * @throws Exception
     */
    public void deleteSmsTmpl(SmsTmplVO smsVO) throws Exception {
		 try {
	            smstmplDAO.deleteByPrimaryKey(smsVO);
	            return;
	        } catch (Exception ex) {
	            throw ExceptionHelper.getException(ex
	                    , this.getClass().getName() + " : " + "deleteSmsTmpl() 에러 발생"
	                    ,"SYS001"
	                    ,new Object[] {"템플릿 삭제 중",ex.getMessage()} 
	            );
	        }
	}

    /**
     * 템플릿 신규등록 전 등록가능여부 조회
     * @param smsVO
     * @return
     * @throws Exception
     */
    public String selectInsertTmpl(SmsTmplVO smsVO) throws Exception {
    	 String result = "";
		 try {
		        result = smstmplDAO.selectInsertTmpl(smsVO);
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