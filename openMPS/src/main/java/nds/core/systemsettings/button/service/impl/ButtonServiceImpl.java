package nds.core.systemsettings.button.service.impl;


import java.util.List;

import javax.annotation.Resource;

import nds.core.systemsettings.button.service.BtnVO;
import nds.core.systemsettings.button.service.ButtonService;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : ButtonServiceImpl </b>
 * <b>Class Description</b><br>
 * 메뉴관리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2012.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Service("buttonService")
public class ButtonServiceImpl extends AbstractServiceImpl implements ButtonService {
    
	@Resource(name="buttonDAO")
    private ButtonDAO buttonDAO;
    
    /**
     * 버튼코드 등록
     * @param btnVO
     * @throws Exception
     */
    public void insertButton(BtnVO btnVO) throws Exception {
        try{
        	buttonDAO.insertButton(btnVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertButton() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"버튼코드 등록",ex.getMessage()} 
            );
        }        
    }
    
    /**
     * 버튼코드 수정
     * @param btnVO
     * @throws Exception
     */
    public void updateButton(BtnVO btnVO) throws Exception {
        try{
        	buttonDAO.updateButton(btnVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteButton() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"버튼코드 수정",ex.getMessage()} 
            );
        }        
    }
    
    /**
     * 버튼코드 삭제
     * @param btnVO
     * @throws Exception
     */
    public void deleteButton(BtnVO btnVO) throws Exception {
        try{
        	buttonDAO.deleteButton(btnVO.getBtnId());
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteButton() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"버튼코드 삭제",ex.getMessage()} 
            );
        }        
    }
    
    /**
     * 버튼코드 조회
     * @param btnVO
     * @return List
     * @throws Exception
     */
    public List selectButton(BtnVO btnVO) throws Exception {
        List list = null;
        try{
            list = buttonDAO.selectButton(btnVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectButton() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"버튼코드 조회",ex.getMessage()} 
            );
        }        
        return list;
    }
    
    /**
     * 버튼코드 건수조회
     * @param btnVO
     * @return int
     * @throws Exception
     */
    public int selectButtonCount(BtnVO btnVO) throws Exception {
        int intTotalCount = 0;
        try{
            intTotalCount = buttonDAO.selectButtonCount(btnVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectButtonCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"버튼정보  건수조회",ex.getMessage()} 
            );
        }        
        return intTotalCount;
    }
    
    /**
     * 버튼코드 중복건수
     * @param btnVO
     * @return int
     * @throws Exception
     */
    public int selectButtonIdCount(BtnVO btnVO) throws Exception {
        int intTotalCount = 0;
        try{
            intTotalCount = buttonDAO.selectButtonIdCount(btnVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectButtonIdCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"버튼코드 중복건수조회",ex.getMessage()} 
            );
        }        
        return intTotalCount;
    }


    
}