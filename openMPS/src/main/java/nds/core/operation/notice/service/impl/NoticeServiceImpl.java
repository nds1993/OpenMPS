package nds.core.operation.notice.service.impl;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.core.operation.notice.service.NoticeService;
import nds.core.operation.notice.service.NoticeVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;



/**
 * <b>class : NoticeServiceImpl </b>
 * <b>Class Description</b><br>
 * <b>History</b><br>
 * <pre>      : 2013.12.26 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
@Service("noticeService")
public class NoticeServiceImpl extends AbstractServiceImpl implements NoticeService {
    
	@Resource(name="noticeDAO")
    private NoticeDAO noticeDAO;
	
    /**
     * Top 공지사항 조회
     * @param noticeVO
     * @return List
     * @throws Exception
     */
	public ArrayList<NoticeVO> selectTopNotiInfo(NoticeVO noticeVO) throws Exception{
		ArrayList<NoticeVO> list = null;
        try{
            list = noticeDAO.selectTopNotiInfo(noticeVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectTopNotiInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"Top 공지사항 조회",ex.getMessage()} 
            );
        }       

        return list;
    }
	
    /**
     * 게시물 조회
     * @param noticeVO
     * @return List
     * @throws Exception
     */
    public List<NoticeVO> selectDocuList(NoticeVO noticeVO) throws Exception{
        List<NoticeVO> list = null;
        try{
            list = noticeDAO.selectByHelper(noticeVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectDocuList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"게시물 조회",ex.getMessage()} 
            );
        }       

        return list;
    }
    
    /**
     * 게시물 총건수 조회
     * @param noticeVO
     * @return int
     * @throws Exception
     */
    public int selectDocuCnt(NoticeVO noticeVO) throws Exception{
        int intTotalCount = 0;
        try{
            intTotalCount = noticeDAO.selectCountByHelper(noticeVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectDocuCnt() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"게시물 총건수 조회",ex.getMessage()} 
            );
        }        

        return intTotalCount;
    }
    
    /**
     * 게시물 상세
     * @param noticeVO
     * @return NoticeVO
     * @throws Exception
     */
    public NoticeVO selectDbrdDocuInfo(NoticeVO noticeVO) throws Exception{
        NoticeVO tbvcblbd = null;
        try{
            tbvcblbd = noticeDAO.selectByPrimaryKey(noticeVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectDbrdDocuInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"게시물 상세조회",ex.getMessage()} 
            );
        }       

        return tbvcblbd;
    }
    
    /**
     * 삭제할 게시물 리스트 조회
     * @param noticeVO
     * @return List
     * @throws Exception
     */
    public List<NoticeVO> selectDeleteDbrdDocuList(NoticeVO noticeVO) throws Exception{
        List<NoticeVO> list = null;
        try{
            list = noticeDAO.selectDeleteByRecord(noticeVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectDeleteDbrdDocuList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"게시물 조회",ex.getMessage()} 
            );
        }       

        return list;
    }
    
    /**
     * 게시물 생성
     * @param noticeVO
     * @return String
     * @throws Exception
     */
    public String insertDocu(NoticeVO noticeVO) throws Exception{
        String docNo    =   "";
        try {       
            docNo   =   (String)noticeDAO.insert(noticeVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertDocu() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"게시판 등록중",ex.getMessage()} 
            );
        }
        
        return docNo;
    }
    
    /**
     * 자료조회수 수정
     * @param noticeVO
     * @throws Exception
     */
    public void updateDocuCnt(NoticeVO tbvcblbd) throws Exception {
        try {       
        	noticeDAO.updateDocuCnt(tbvcblbd);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateDocuCnt() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"자료 조회수 수정중",ex.getMessage()} 
            );
        }           
    } 
    
    /**
     * 게시물 수정
     * @param noticeVO
     * @return void
     * @throws Exception
     */
    public void updateDocu(NoticeVO noticeVO) throws Exception{
        try {      
        	noticeDAO.updateByPrimaryKey(noticeVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateDocu() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"게시물 수정",ex.getMessage()} 
            );
        }
    }
    
    /**
     * 게시물 삭제
     * @param noticeVO
     * @return void
     * @throws Exception
     */
    public void deleteDocu(NoticeVO noticeVO) throws Exception{
        try {      
            noticeDAO.deleteByPrimaryKey(noticeVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteDocu() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"게시물 삭제",ex.getMessage()} 
            );
        }
    }
    
    /**
     * 코멘트 조회
     * @param noticeVO
     * @throws Exception 
     */    
    public List<NoticeVO> seleteDbrdDocuCommentList(NoticeVO noticeVO) throws Exception{
        List<NoticeVO> list = null;
        try{
            list = noticeDAO.selectByHelper(noticeVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "seleteDbrdDocuCommentList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"코멘트  리스트조회중",ex.getMessage()} 
            );
        }         

        return list;        
    }
    
}