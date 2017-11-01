package nds.core.operation.emailTmpl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.core.operation.emailTmpl.service.EmailTmplService;
import nds.core.operation.emailTmpl.service.EmailTmplVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * <b>class : EmailTmplServiceImpl </b>
 * <b>Class Description</b><br>
 * <b>History</b><br>
 * <pre>      : 2014.08.14 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
@Service("emailTmplService")
public class EmailTmplServiceImpl extends AbstractServiceImpl implements EmailTmplService {

	@Resource(name = "emailTmplDAO")
	private EmailTmplDAO emailTmplDAO;

	/**
     * 템플릿 조회
     * @param emailTmplVO
     * @return List
     * @throws Exception
     */
    public List<EmailTmplVO> selectEmailTmplList(EmailTmplVO emailTmplVO) throws Exception {
		List<EmailTmplVO> list = null;
		try{
        	list = emailTmplDAO.selectEmailTmplList(emailTmplVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectEmailTmplList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"템플릿 조회 중",ex.getMessage()} 
            );
        }
		return list;
	}
    
    /**
     * 템플릿 전체 갯수 조회
     * @param emailTmplVO
     * @return
     * @throws Exception
     */
    public int selectEmailTmplListCount(EmailTmplVO emailTmplVO) throws Exception {
    	int result = 0;
    	try{
        	result = emailTmplDAO.selectEmailTmplListCount(emailTmplVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectEmailTmplListCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"템플릿 전체 갯수 조회",ex.getMessage()} 
            );
        }
		return result;
	}

    /**
     * 템플릿 상세조회
     * @param emailTmplVO
     * @return
     * @throws Exception
     */
    public EmailTmplVO selectEmailTmplInfo(EmailTmplVO emailTmplVO) throws Exception {

    	EmailTmplVO result = null;
        try{
        	result = emailTmplDAO.selectEmailTmplInfo(emailTmplVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectEmailTmplInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"템플릿 상세조회 중",ex.getMessage()} 
            );
        }
    	
		return result;

	}
    
    /**
     * 템플릿 상세조회
     * @param emailTmplVO
     * @return
     * @throws Exception
     */
    public EmailTmplVO selectEmailTmplInfoByMessage(EmailTmplVO emailTmplVO) throws Exception {

    	EmailTmplVO result = null;
        try{
        	result = emailTmplDAO.selectEmailTmplInfoByMessage(emailTmplVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectEmailTmplInfoByMessage() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"템플릿 상세조회 중",ex.getMessage()} 
            );
        }
    	
		return result;

	}
    
    
    /**
     * 템플릿 생성
     * @param emailTmplVO
     * @return
     * @throws Exception
     */
    public String insertEmailTmpl(EmailTmplVO emailTmplVO) throws Exception {
		String docNo = "";
		 try{
	        	docNo = (String) emailTmplDAO.insertEmailTmpl(emailTmplVO);
	        }catch(Exception ex){
	            throw ExceptionHelper.getException(ex
	                    , this.getClass().getName() + " : " + "insertEmailTmpl() 에러 발생"
	                    ,"SYS001"
	                    ,new Object[] {"템플릿 생성 중",ex.getMessage()} 
	            );
	        }

		return docNo;
	}

    /**
     * 템플릿 수정
     * @param emailTmplVO
     * @return
     * @throws Exception
     */
    public void updateEmailTmpl(EmailTmplVO emailTmplVO) throws Exception {
		try{
			emailTmplDAO.updateEmailTmpl(emailTmplVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateEmailTmpl() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"템플릿 수정 중",ex.getMessage()} 
            );
        }
	}

    /**
     * 템플릿 삭제
     * @param emailTmplVO
     * @return
     * @throws Exception
     */
    public void deleteEmailTmpl(EmailTmplVO emailTmplVO) throws Exception {

		try{
			emailTmplDAO.deleteEmailTmpl(emailTmplVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteEmailTmpl() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"템플릿 삭제 중",ex.getMessage()} 
            );
        }
	}

    /**
     * 템플릿 신규 tmplNo 조회
     * @param emailTmplVO
     * @return
     * @throws Exception
     */
    public String selectEmailTmplNo() throws Exception {
		String emailTmplVO = "";
		
		try{
			emailTmplVO = emailTmplDAO.selectEmailTmplNo();
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectEmailTmplNo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"템플릿 신규 tmplNo 조회 중",ex.getMessage()} 
            );
        }
		return emailTmplVO;
	}
    
    /**
     * 템플릿 신규등록 전 등록가능여부 조회
     * @param smsVO
     * @return
     * @throws Exception
     */
    public String selectInsertTmpl(EmailTmplVO emailTmplVO) throws Exception {
		String result = "";
		try{
			result = emailTmplDAO.selectInsertTmpl(emailTmplVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectInsertTmpl() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"템플릿 신규등록 전 등록가능여부 조회 중",ex.getMessage()} 
            );
        }
		return result;
	}

    /**
     * VOC처리완료 단건발송 템플릿 상세조회
     * @param emailTmplVO
     * @return
     * @throws Exception
     */
    public EmailTmplVO selectTmplInfoVoc(EmailTmplVO emailTmplVO) throws Exception {

    	EmailTmplVO result = null;
        try{
        	result = emailTmplDAO.selectTmplInfoVoc(emailTmplVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectEmailTmplInfoVoc() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"VOC처리완료 단건발송 템플릿 상세조회 중",ex.getMessage()} 
            );
        }
    	
		return result;

	}
}