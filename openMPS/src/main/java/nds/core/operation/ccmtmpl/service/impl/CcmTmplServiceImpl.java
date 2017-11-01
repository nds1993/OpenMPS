package nds.core.operation.ccmtmpl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.core.operation.ccmtmpl.service.CcmTmplService;
import nds.core.operation.ccmtmpl.service.CcmTmplVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * <b>class : CcmTmplServiceImpl </b> <b>Class Description</b><br>
 * <b>History</b><br>
 * 
 * <pre>
 * : 2014.07.21 초기작성(오예솔)
 * </pre>
 * 
 * @author <a href="mailto:ysoh@nds.co.kr">오예솔</a>
 * @version 1.0
 */
@Service("ccmtmplService")
public class CcmTmplServiceImpl extends AbstractServiceImpl implements CcmTmplService {

	@Resource(name = "ccmtmplDAO")
	private CcmTmplDAO ccmtmplDAO;

	/**
     * 템플릿 조회
     * @param vo
     * @return List
     * @throws Exception
     */
    public List<CcmTmplVO> selectCcmTmplList(CcmTmplVO vo) throws Exception {
		List<CcmTmplVO> list = null;
		try {
            list = ccmtmplDAO.selectByHelper(vo);
            return list;
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCcmTmplList() 에러 발생"
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
    public int selectCcmTmplCnt(CcmTmplVO vo) throws Exception {
		int intTotalCount = 0;

        try {
        	intTotalCount = ccmtmplDAO.selectCountByHelper(vo);
            return intTotalCount;
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCcmTmplCnt() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"템플릿 총건수 조회 중",ex.getMessage()} 
            );
        }
	}

    /**
     * 템플릿 상세
     * @param vo
     * @return CcmTmplVO
     * @throws Exception
     */
    public CcmTmplVO selectByPrimaryKey(CcmTmplVO vo) throws Exception {
    	CcmTmplVO	record = new CcmTmplVO();
        try {
        	record = ccmtmplDAO.selectByPrimaryKey(vo);
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
     * @return CcmTmplVO
     * @throws Exception
     */
    public CcmTmplVO selectByCcmTmplInfo(CcmTmplVO vo) throws Exception {
    	
    	CcmTmplVO result = null;
    	
		try{
			result = ccmtmplDAO.selectByCcmTmplInfo(vo);
			
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectByCcmTmplInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"템플릿 상세 조회",ex.getMessage()} 
            );
        }    
    	
    	return result;
    	
    }
    
    /**
     * 템플릿 상세
     * @param vo
     * @return CcmTmplVO
     * @throws Exception
     */
    public CcmTmplVO selectByMessingerTmplInfo(CcmTmplVO vo) throws Exception {
    	
    	CcmTmplVO result = null;
    	
    	try{
    		result = ccmtmplDAO.selectByMessingerTmplInfo(vo);
    		
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
    public String insertCcmTmpl(CcmTmplVO vo) throws Exception {
    		String docNo = "";
	        try {
	            docNo = (String) ccmtmplDAO.insert(vo);
	            return docNo;
	        } catch (Exception ex) {
	            throw ExceptionHelper.getException(ex
	                    , this.getClass().getName() + " : " + "insertCcmTmpl() 에러 발생"
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
    public void updateCcmTmpl(CcmTmplVO vo) throws Exception {
		
		 try {
	            ccmtmplDAO.updateByPrimaryKey(vo);
	            return;
	        } catch (Exception ex) {
	            throw ExceptionHelper.getException(ex
	                    , this.getClass().getName() + " : " + "updateCcmTmpl() 에러 발생"
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
    public void deleteCcmTmpl(CcmTmplVO vo) throws Exception {
		 try {
	            ccmtmplDAO.deleteByPrimaryKey(vo);
	            return;
	        } catch (Exception ex) {
	            throw ExceptionHelper.getException(ex
	                    , this.getClass().getName() + " : " + "deleteCcmTmpl() 에러 발생"
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
    public String selectInsertTmpl(CcmTmplVO vo) throws Exception {
    	 String result = "";
		 try {
		        result = ccmtmplDAO.selectInsertTmpl(vo);
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