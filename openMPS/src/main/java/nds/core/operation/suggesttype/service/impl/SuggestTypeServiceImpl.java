package nds.core.operation.suggesttype.service.impl;


import java.util.List;

import javax.annotation.Resource;

import nds.core.operation.suggesttype.service.SuggestTypeService;
import nds.core.operation.suggesttype.service.SuggestTypeVO;
import nds.frm.exception.ExceptionHelper;
import nds.frm.exception.MainException;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : LoginServiceImpl </b>
 * <b>Class Description</b><br>
 * 공통 처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2012.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Service("suggestTypeService")
public class SuggestTypeServiceImpl extends AbstractServiceImpl implements SuggestTypeService {
    
	@Resource(name="suggestTypeDAO")
    private SuggestTypeDAO suggestTypeDAO;
    
    /**
     * 제안유형코드 등록
     * @param suggestTypeVO
     * @throws Exception
     */    
    public void insertSuggestType(SuggestTypeVO suggestTypeVO) throws Exception{
        try {
        	if("PT".equals(suggestTypeVO.getType())) {
        		suggestTypeDAO.insertPropType(suggestTypeVO);
        	} else if("PR".equals(suggestTypeVO.getType())) {
        		suggestTypeDAO.insertPropResultType(suggestTypeVO);
        	} else if("C".equals(suggestTypeVO.getType())) {
        		suggestTypeDAO.insertCommonCode(suggestTypeVO);
        	}
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertSuggestType() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"제안유형코드 등록중",ex.getMessage()} 
            );
        }
    }
    /**
     * 제안유형코드 수정
     * @param suggestTypeVO
     * @throws Exception
     */  
    public void updateSuggestType(SuggestTypeVO suggestTypeVO) throws Exception {
        try {
        	if("PT".equals(suggestTypeVO.getType())) {
        		suggestTypeDAO.updatePropType(suggestTypeVO);
        	} else if("PR".equals(suggestTypeVO.getType())) {
        		suggestTypeDAO.updatePropResultType(suggestTypeVO);
        	} else if("C".equals(suggestTypeVO.getType())) {
        		suggestTypeDAO.updateCommonCode(suggestTypeVO);
        	}
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateSuggestType() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"제안유형코드 수정중",ex.getMessage()} 
            );
        }         
    }
    
    /**
     * 제안유형코드 삭제
     * @param suggestTypeVO
     * @throws Exception
     */  
    public void deleteSuggestType(SuggestTypeVO suggestTypeVO) throws Exception {
        try {
        	if("PT".equals(suggestTypeVO.getType())) {
        		suggestTypeDAO.deletePropType(suggestTypeVO);
        	} else if("PR".equals(suggestTypeVO.getType())) {
        		suggestTypeDAO.deletePropResultType(suggestTypeVO);
        	} else if("C".equals(suggestTypeVO.getType())) {
        		suggestTypeDAO.deleteCommonCode(suggestTypeVO);
        	}
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteSuggestType() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"제안유형코드 삭제중",ex.getMessage()} 
            );
        }         
    }
    
    /**
     * 제안유형코드 총건수 조회
     * @param key
     * @return int
     * @throws MainException
     */    
    public int selectCnslCodeCnt(SuggestTypeVO suggestTypeVO) throws Exception {
        int intTotalCount = 0;
        try{
            intTotalCount = suggestTypeDAO.selectByPrimaryKeyCount(suggestTypeVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCnslCodeCnt() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"제안유형코드 총건수조회중",ex.getMessage()} 
            );
        }

        return intTotalCount;
    }
    
    /**
     * 제안유형코드 조회
     * @param suggestTypeVO
     * @return List
     * @throws Exception
     */
    public List<SuggestTypeVO> selectPropTypeList(SuggestTypeVO suggestTypeVO) throws Exception{
        List<SuggestTypeVO> list = null;
        try{
            list = suggestTypeDAO.selectPropTypeList(suggestTypeVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCnslTypeList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"제안유형코드 조회",ex.getMessage()} 
            );
        }        
        return list;
    }
}