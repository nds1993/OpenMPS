package nds.core.operation.msgtmpl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.core.operation.emailTmpl.service.EmailTmplVO;
import nds.core.operation.msgtmpl.service.MsgTmplService;
import nds.core.operation.msgtmpl.service.MsgTmplVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("msgTmplService")
public class MsgTmplServiceImpl extends AbstractServiceImpl implements MsgTmplService{
	@Resource(name = "msgTmplDAO")
	private MsgTmplDAO msgTmplDAO;
	
	/**
	 * Msg 템플릿 조회
	 * @param msgTmplVO
	 * @return
	 * @throws Exception
	 */
	public List<MsgTmplVO> selectMsgTmplList(MsgTmplVO msgTmplVO) throws Exception{
		List<MsgTmplVO> list = null;
		try{
        	list = msgTmplDAO.selectMsgTmplList(msgTmplVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMsgTmplList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"Msg 템플릿 조회 중",ex.getMessage()} 
            );
        }
		return list;
	}
	
	/**
     * 템플릿 전체 갯수 조회
     * @param msgTmplVO
     * @return
     * @throws Exception
     */
    public int selectMsgTmplListCount(MsgTmplVO msgTmplVO) throws Exception{
    	int count = 0;
		try{
        	count = msgTmplDAO.selectMsgTmplListCount(msgTmplVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMsgTmplListCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"Msg 템플릿 전체 갯수 조회 중",ex.getMessage()} 
            );
        }
		return count;
    }
    
    
    /**
     * 템플릿 상세조회
     * @param msgTmplVO
     * @return
     * @throws Exception
     */
    public MsgTmplVO selectMsgTmplInfo(MsgTmplVO msgTmplVO) throws Exception{
    	MsgTmplVO vo = null;
		try{
			vo = msgTmplDAO.selectMsgTmplInfo(msgTmplVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMsgTmplInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"Msg 템플릿 상세조회 중",ex.getMessage()} 
            );
        }
		return vo;
    }
    
    
    /**
     * 템플릿 생성
     * @param msgTmplVO
     * @return
     * @throws Exception
     */
    public String insertMsgTmpl(MsgTmplVO msgTmplVO) throws Exception{
    	String result = "";
		try{
			result = (String)msgTmplDAO.insertMsgTmpl(msgTmplVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertMsgTmpl() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"Msg 템플릿 생성 중",ex.getMessage()} 
            );
        }
		return result;
    }
    
    /**
     * 템플릿 수정
     * @param msgTmplVO
     * @return
     * @throws Exception
     */
    public void updateMsgTmpl(MsgTmplVO msgTmplVO) throws Exception{
		try{
        	msgTmplDAO.updateMsgTmpl(msgTmplVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateMsgTmpl() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"Msg 템플릿 수정 중",ex.getMessage()} 
            );
        }
    }
    
    /**
     * 템플릿 삭제
     * @param msgTmplVO
     * @return
     * @throws Exception
     */
    public void deleteMsgTmpl(MsgTmplVO msgTmplVO) throws Exception{
		try{
        	msgTmplDAO.deleteMsgTmpl(msgTmplVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteMsgTmpl() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"Msg 템플릿 삭제 중",ex.getMessage()} 
            );
        }
    }

    /**
     * 템플릿 신규등록 전 등록가능여부 조회
     * @param msgTmplVO
     * @return
     * @throws Exception
     */
    public String checkInsertYn(MsgTmplVO msgTmplVO) throws Exception{
    	String result = "";
		try{
        	result = msgTmplDAO.checkInsertYn(msgTmplVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "checkInsertYn() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"Msg 템플릿 신규등록 전 등록가능여부 조회 중",ex.getMessage()} 
            );
        }
		return result;
    }
}
