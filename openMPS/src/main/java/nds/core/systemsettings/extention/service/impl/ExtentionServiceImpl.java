package nds.core.systemsettings.extention.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.core.systemsettings.extention.service.ExtentionService;
import nds.core.systemsettings.extention.service.ExtentionVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : ExtentionServiceImpl </b>
 * <b>Class Description</b><br>
 * 파일 확장자관리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2015.09.09 초기작성(김세희)</pre>
 * @author <a href="mailto:shkim@nds.co.kr">김세희</a>
 * @version 1.0
 */
@Service("extentionService")
public class ExtentionServiceImpl extends AbstractServiceImpl implements ExtentionService {

	@Resource(name="extentionDAO")
	private ExtentionDAO extentionDAO;
    
	  /**
     * 파일확장자 등록
     * @param extentionVO
     * @throws Exception
     */
    public void insertExtention(ExtentionVO extentionVO) throws Exception {
        try{
        	extentionDAO.insertExtention(extentionVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertExtention() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"파일확장자 등록",ex.getMessage()} 
            );
        }        
    }
    
    /**
     * 파일확장자 수정
     * @param extentionVO
     * @throws Exception
     */
    public void updateExtention(ExtentionVO extentionVO) throws Exception {
        try{
        	extentionDAO.updateExtention(extentionVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteExtention() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"파일확장자 수정",ex.getMessage()} 
            );
        }        
    }
    
    /**
     * 파일확장자 삭제
     * @param extentionVO
     * @throws Exception
     */
    public void deleteExtention(ExtentionVO extentionVO) throws Exception {
        try{
        	extentionDAO.deleteExtention(extentionVO.getFileExt());
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteExtention() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"파일확장자 삭제",ex.getMessage()} 
            );
        }        
    }
    
    /**
     * 파일확장자 조회
     * @param extentionVO
     * @return List
     * @throws Exception
     */
    public List selectExtention(ExtentionVO extentionVO) throws Exception {
        List list = null;
        try{
            list = extentionDAO.selectExtention(extentionVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectExtention() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"파일확장자 조회",ex.getMessage()} 
            );
        }        
        return list;
    }
    
    /**
     * 파일확장자 건수조회
     * @param extentionVO
     * @return int
     * @throws Exception
     */
    public int selectExtentionCount(ExtentionVO extentionVO) throws Exception {
        int intTotalCount = 0;
        try{
            intTotalCount = extentionDAO.selectExtentionCount(extentionVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectExtentionCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"파일확장자 건수조회",ex.getMessage()} 
            );
        }        
        return intTotalCount;
    }
    
    
    /**
     * 파일확장자 중복건수
     * @param extentionVO
     * @return int
     * @throws Exception
     */
    public int selectExtentionIdCount(ExtentionVO extentionVO) throws Exception {
        int intTotalCount = 0;
        try{
            intTotalCount = extentionDAO.selectExtentionIdCount(extentionVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectExtentionIdCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"파일확장자 중복건수조회",ex.getMessage()} 
            );
        }        
        return intTotalCount;
    }
}
