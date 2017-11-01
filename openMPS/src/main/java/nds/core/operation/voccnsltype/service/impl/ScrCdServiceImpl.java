package nds.core.operation.voccnsltype.service.impl;


import java.util.List;

import javax.annotation.Resource;

import nds.core.operation.voccnsltype.service.ScrCdService;
import nds.core.operation.voccnsltype.service.ScrCdVO;
import nds.frm.exception.ExceptionHelper;
import nds.frm.exception.MainException;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : ScrCdServiceImpl </b>
 * <b>Class Description</b><br>
 * 상세화면 처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2015.09.30 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
@Service("scrCdService")
public class ScrCdServiceImpl extends AbstractServiceImpl implements ScrCdService {
    
	@Resource(name="scrCdDAO")
    private ScrCdDAO scrCdDAO;
    
    /**
     * 화면코드 등록
     * @param scrCdVO
     * @throws Exception
     */    
    public void insertScrCd(ScrCdVO scrCdVO) throws Exception{
        try {       
        	scrCdDAO.insertScrCd(scrCdVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertScrCd() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"화면코드 등록중",ex.getMessage()} 
            );
        }
    }
    /**
     * 화면코드 수정
     * @param scrCdVO
     * @throws Exception
     */  
    public void updateScrCdCode(ScrCdVO scrCdVO) throws Exception {
        try {       
        	scrCdDAO.updateByPrimaryKeySelective(scrCdVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateScrCdCode() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"화면코드 수정중",ex.getMessage()} 
            );
        }         
    }
    
    /**
     * 화면코드 삭제
     * @param scrCdVO
     * @throws Exception
     */  
    public void deleteScrCdTree(ScrCdVO scrCdVO) throws Exception {
        try {       
        	scrCdDAO.deleteByPrimaryKey(scrCdVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteScrCdTree() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"화면코드 삭제중",ex.getMessage()} 
            );
        }         
    }
    
    /**
     * 화면코드 총건수 조회
     * @param key
     * @return int
     * @throws MainException
     */    
    public int selectScrCdCnt(ScrCdVO scrCdVO) throws Exception {
        int intTotalCount = 0;
        try{
            intTotalCount = scrCdDAO.selectByPrimaryKeyCount(scrCdVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectScrCdCnt() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"화면코드 총건수조회중",ex.getMessage()} 
            );
        }

        return intTotalCount;
    }
    
    /**
     * 화면코드 조회
     * @param scrCdVO
     * @return List
     * @throws Exception
     */
    public List<ScrCdVO> selectScrCdList(ScrCdVO scrCdVO) throws Exception{
        List<ScrCdVO> list = null;
        try{
            list = scrCdDAO.selectCnslTypeList(scrCdVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectScrCdList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"화면코드 조회",ex.getMessage()} 
            );
        }        
        return list;
    }
}