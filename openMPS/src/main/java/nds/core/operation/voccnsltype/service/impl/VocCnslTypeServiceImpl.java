package nds.core.operation.voccnsltype.service.impl;


import java.util.List;

import javax.annotation.Resource;

import nds.core.operation.voccnsltype.service.VocCnslTypeService;
import nds.core.operation.voccnsltype.service.VocCnslTypeVO;
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
@Service("vocCnslTypeService")
public class VocCnslTypeServiceImpl extends AbstractServiceImpl implements VocCnslTypeService {
    
	@Resource(name="vocCnslTypeDAO")
    private VocCnslTypeDAO vocCnslTypeDAO;
    
    /**
     * VOC유형코드 등록
     * @param vocCnslTypeVO
     * @throws Exception
     */    
    public void insertCnslCode(VocCnslTypeVO vocCnslTypeVO) throws Exception{
        try {       
        	vocCnslTypeDAO.insertCnslCode(vocCnslTypeVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertCnslCode() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"VOC유형코드 등록중",ex.getMessage()} 
            );
        }
    }
    /**
     * VOC유형코드 수정
     * @param vocCnslTypeVO
     * @throws Exception
     */  
    public void updateCnslTypeCode(VocCnslTypeVO vocCnslTypeVO) throws Exception {
        try {       
        	vocCnslTypeDAO.updateByPrimaryKeySelective(vocCnslTypeVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateCnslTypeCode() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"VOC유형코드 수정중",ex.getMessage()} 
            );
        }         
    }
    
    /**
     * VOC유형코드 삭제
     * @param vocCnslTypeVO
     * @throws Exception
     */  
    public void deleteCnslTypeTree(VocCnslTypeVO vocCnslTypeVO) throws Exception {
        try {       
        	vocCnslTypeDAO.deleteByPrimaryKey(vocCnslTypeVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteCnslTypeTree() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"VOC유형코드 삭제중",ex.getMessage()} 
            );
        }         
    }
    
    /**
     * VOC유형코드 총건수 조회
     * @param key
     * @return int
     * @throws MainException
     */    
    public int selectCnslCodeCnt(VocCnslTypeVO vocCnslTypeVO) throws Exception {
        int intTotalCount = 0;
        try{
            intTotalCount = vocCnslTypeDAO.selectByPrimaryKeyCount(vocCnslTypeVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCnslCodeCnt() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"VOC유형코드 총건수조회중",ex.getMessage()} 
            );
        }

        return intTotalCount;
    }
    
    /**
     * VOC유형코드 조회
     * @param vocCnslTypeVO
     * @return List
     * @throws Exception
     */
    public List<VocCnslTypeVO> selectCnslTypeList(VocCnslTypeVO vocCnslTypeVO) throws Exception{
        List<VocCnslTypeVO> list = null;
        try{
            list = vocCnslTypeDAO.selectCnslTypeList(vocCnslTypeVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCnslTypeList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"VOC유형코드 조회",ex.getMessage()} 
            );
        }        
        return list;
    }
}