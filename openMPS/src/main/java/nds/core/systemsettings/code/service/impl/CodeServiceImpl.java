package nds.core.systemsettings.code.service.impl;



import java.util.List;

import javax.annotation.Resource;

import nds.core.systemsettings.code.service.CodeService;
import nds.core.systemsettings.code.service.CodeVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;



/**
 * <b>class : CodeServiceImpl </b>
 * <b>Class Description</b><br>
 * 코드처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2012.06.25 초기작성(소건)</pre>
 * @author <a href="mailto:gso@nds.co.kr">소건</a>
 * @version 1.0
 */
@Service("codeService")
public class CodeServiceImpl extends AbstractServiceImpl implements CodeService {
    
	@Resource(name="codeDAO")
    private CodeDAO codeDAO;
	

    /**
     * 공통코드 조회
     * @param helper
     * @return List
     * @throws Exception
     */
    public List selectCode2(CodeVO codeVO) throws Exception {
        List list = null;
        try{
            list = codeDAO.selectByRecord(codeVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCode() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"공통코드 조회",ex.getMessage()} 
            );
        }        
        return list;
    }
    /**
     * 공통코드 조회
     * @param codeVO
     * @return List
     * @throws Exception
     */
    public List<CodeVO> selectCode(CodeVO codeVO) throws Exception {
        List<CodeVO> list = null;
        try{
            list = codeDAO.selectByCodeList(codeVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCode() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"공통코드 조회",ex.getMessage()} 
            );
        }        
        return list;
    }
    /**
     * 공통코드 건수조회
     * @param helper
     * @return int
     * @throws Exception
     */    
    public int selectCodeCount(CodeVO codeVO) throws Exception {
        int intTotalCount = 0;
        try{
            intTotalCount = codeDAO.selectByCodeListCount(codeVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCodeCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"공통코드 건수조회",ex.getMessage()} 
            );
        }        

        return intTotalCount;
    }      
    /**
     * 공통코드 조회(Tree)
     * @param helper
     * @return List
     * @throws Exception
     */
    public List<CodeVO> selectCodeTree(CodeVO codeVO) throws Exception {
        List<CodeVO> list = null;
        try{
            list = codeDAO.selectTreeByHelper(codeVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCodeTree() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"공통코드 조회",ex.getMessage()} 
            );
        }        
        return list;    	
    }
	/**
	 * 코드 등록
	 * @param codeVO
	 * @throws Exception
	 */
    public void insertCode(CodeVO codeVO) throws Exception {
    	try {    	
    	    codeDAO.insert(codeVO);
		}catch (Exception ex) {
			throw ExceptionHelper.getException(ex
					, this.getClass().getName() + " : " + "insertCode() 에러 발생"
					,"SYS001"
					,new Object[] {"코드 등록중",ex.getMessage()} 
			);
		}    		
    }

	/**
	 * 코드 수정
	 * @param codeVO
	 * @throws Exception
	 */  
    public void updateCode(CodeVO codeVO) throws Exception {
    	try {     	
    	    codeDAO.updateByPrimaryKeySelective(codeVO);
    	}catch (Exception ex) {
			throw ExceptionHelper.getException(ex
					, this.getClass().getName() + " : " + "updateCode() 에러 발생"
					,"SYS001"
					,new Object[] {"코드 수정중",ex.getMessage()} 
			);
		}         
    }

    /**
     * 코드(트리) 삭제
     * @param codeVO
     * @throws Exception
     */
    public void deleteCodeTree(CodeVO codeVO) throws Exception {
    	try {      	
    	    codeDAO.deleteTree(codeVO);
    	}catch (Exception ex) {
			throw ExceptionHelper.getException(ex
					, this.getClass().getName() + " : " + "deleteCodeTree() 에러 발생"
					,"SYS001"
					,new Object[] {"코드(트리) 삭제중",ex.getMessage()} 
			);
		} 	        
    }
    
    /**
     * 코드 총건수 조회
     * @param helper
     * @return int
     * @throws Exception
     */    
    public int selectCodeCnt(CodeVO codeVO) throws Exception {
    	int intTotalCount = 0;
        try{
        	intTotalCount = codeDAO.selectCountByDomain(codeVO);
    	}catch (Exception ex) {
			throw ExceptionHelper.getException(ex
					, this.getClass().getName() + " : " + "selectCodeCnt() 에러 발생"
					,"SYS001"
					,new Object[] {"코드 총건수조회중",ex.getMessage()} 
			);
		}        

        return intTotalCount;
    }   
        
}
