package nds.core.common.common.service.impl;



import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import nds.core.common.common.service.AttachFileVO;
import nds.core.common.common.service.AttachfileService;
import nds.frm.exception.ExceptionHelper;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;



/**
 * <b>class : AttachfileServiceImpl </b>
 * <b>Class Description</b><br>
 * 첨부파일 처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2013.12.24 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Service("attachfileService")
public class AttachfileServiceImpl extends AbstractServiceImpl implements AttachfileService {
	
	@Resource(name="attachFileDAO")
    private AttachFileDAO attachFileDAO;
	@Resource(name="attachFileTmpDAO")
    private AttachFileTmpDAO attachFileTmpDAO;
	
    /**
     * 첨부파일정보 등록
     * @param attachFileVO
     */
    public void insertAttachFile(AttachFileVO attachFileVO) throws Exception {
        try{
        	attachFileDAO.insert(attachFileVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertAttachFile() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"첨부파일정보  등록",ex.getMessage()} 
            );
        }          
    }
    
    /**
     * 첨부파일정보 등록
     * @param attachFileVO
     */
    public void insertHomepageAttachFile(AttachFileVO attachFileVO) throws Exception {
        try{
        	attachFileDAO.insertHomepage(attachFileVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertHomepageAttachFile() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"첨부파일정보  등록",ex.getMessage()} 
            );
        }          
    }
    
    /**
     * 첨부파일정보 삭제
     * @param attachFileVO
     */
    public void deleteAttachFile(AttachFileVO attachFileVO) throws Exception {
        try{
        	attachFileDAO.deleteByDocRegNo(attachFileVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteAttachFile() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"첨부파일정보  삭제",ex.getMessage()}
            );
        }
    }
    /**
     * 첨부파일일괄 삭제
     * @param list
     */
    public void deleteAttachFile(List list) throws Exception {
        try{
            Iterator iter = list.iterator();
            while(iter.hasNext()){
                AttachFileVO attachFileVO = (AttachFileVO) iter.next();
                attachFileDAO.deleteByPrimaryKey(attachFileVO);
            }
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteAttachFile() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"첨부파일일괄 삭제",ex.getMessage()} 
            );
        }   
    }
    
    /**
     * 첨부파일정보 조회
     * @param srNo
     * @param cntn
     * @return
     */
    public List getAttachFileList(String docRegNo, String contsId) throws Exception {
        List list = null;
        try{
            AttachFileVO attachFileVO = new AttachFileVO();
            attachFileVO.setDocRegNo(StringUtil.null2String(docRegNo, " "));
            attachFileVO.setContsId(StringUtil.null2String(contsId, " "));
             
            list = attachFileDAO.selectByHelper(attachFileVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "getAttachFileList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"첨부파일정보  조회",ex.getMessage()} 
            );
        } 
        return list;
    }
    
    /**
     * 첨부파일정보 조회 - pis용
     * @param srNo
     * @param cntn
     * @return
     */
        List list = null;
        public List getAttachFileList(String keyVal01,String keyVal02,String keyVal03,String keyVal04) throws Exception {
        try{
            AttachFileVO attachFileVO = new AttachFileVO();
            attachFileVO.setKeyVal01(StringUtil.null2String(keyVal01, " "));
            attachFileVO.setKeyVal02(StringUtil.null2String(keyVal02, " "));
            attachFileVO.setKeyVal03(StringUtil.null2String(keyVal03, " "));
            attachFileVO.setKeyVal04(StringUtil.null2String(keyVal04, " "));
            
            
    
            list = attachFileDAO.selectPisAttachedFile(attachFileVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "getAttachFileList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"첨부파일정보  조회",ex.getMessage()} 
            );
        } 
        return list;
    }
    /**
     * 첨부파일 임시정보 삭제
     * @param attachFileVO
     */
    public void deleteTempFile(String attachFileVO) throws Exception {
        try{
            attachFileTmpDAO.deleteByPrimaryKey(attachFileVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteTempFile() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"첨부파일 임시정보  삭제",ex.getMessage()} 
            );
        }
    }
    
    
    /**
     * 파일 리스트 조회
     * @param helper
     * @return List
     * @throws Exception
     */    
    public List selectFileList(AttachFileVO attachFileVO) throws Exception {
        List list = null;
        try{
            list = attachFileDAO.selectFileList(attachFileVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectFileList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"파일 리스트조회중",ex.getMessage()} 
            );
        }       

        return list;
    }   
    
    /**
     * 시험성적 농심 파일 리스트 조회
     * @param helper
     * @return List
     * @throws Exception
     */    
    public List selectNsTestFileList(AttachFileVO attachFileVO) throws Exception {
        List list = null;
        try{
            list = attachFileDAO.selectNsTestFileList(attachFileVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectNsTestFileList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"시험성적 농심파일 리스트조회중",ex.getMessage()} 
            );
        }       

        return list;
    }
    

    /**
     * 첨부파일정보 조회
     * @param srNo
     * @param cntn
     * @return
     */
    public List selectByRegNo(String cntn, String contsId) throws Exception {
        List list = null;
        try{
            AttachFileVO attachFileVO = new AttachFileVO();
            attachFileVO.setDocRegNo(StringUtil.null2String(cntn, " "));
            attachFileVO.setContsId(StringUtil.null2String(contsId, " "));
    
            list = attachFileDAO.selectByRegNo(attachFileVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectByRegNo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"첨부파일정보  조회",ex.getMessage()} 
            );
        } 
        return list;
    }
    

    
    /**
     * 파일 개별 조회
     * @param helper
     * @return AttachFileVO
     * @throws Exception
     */    
    public AttachFileVO selectByPk(AttachFileVO attachFileVO) throws Exception {
    	AttachFileVO result = null;
        try{
        	result = attachFileDAO.selectByPk(attachFileVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectByPk() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"파일 개별 조회",ex.getMessage()} 
            );
        }       

        return result;
    }

	@Override
	public List getPotoAttachFileList(String docRegNo, String contsId) throws Exception {
        List list = null;
        try{
            AttachFileVO attachFileVO = new AttachFileVO();
            attachFileVO.setDocRegNo(StringUtil.null2String(docRegNo, " "));
            attachFileVO.setContsId(StringUtil.null2String(contsId, " "));
    
            list = attachFileDAO.getPotoAttachFileList(attachFileVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "getAttachFileList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"첨부파일정보  조회",ex.getMessage()} 
            );
        } 
        return list;
	}    
	
	
    
}
