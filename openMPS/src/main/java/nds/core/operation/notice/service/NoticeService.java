package nds.core.operation.notice.service;



import java.util.ArrayList;
import java.util.List;

import nds.core.common.common.service.Service;


/**
 * <b>class : NoticeService </b>
 * <b>Class Description</b><br>
 * <b>History</b><br>
 * <pre>      : 2013.12.26 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
public interface NoticeService extends Service{    

    /**
     * Top 공지사항 조회
     * @param noticeVO
     * @return List
     * @throws Exception
     */
	ArrayList<NoticeVO> selectTopNotiInfo(NoticeVO noticeVO) throws Exception;
	
    /**
     * 게시물 조회
     * @param noticeVO
     * @return List
     * @throws Exception
     */
    List<NoticeVO> selectDocuList(NoticeVO noticeVO) throws Exception;
    /**
     * 게시물 총건수 조회
     * @param noticeVO
     * @return int
     * @throws Exception
     */
    int selectDocuCnt(NoticeVO noticeVO) throws Exception;
    
    /**
     * 게시물 상세
     * @param noticeVO
     * @return List
     * @throws Exception
     */
    NoticeVO selectDbrdDocuInfo(NoticeVO noticeVO) throws Exception;
    
    /**
     * 삭제할 게시물 리스트 조회
     * @param noticeVO
     * @return
     * @throws Exception
     */
    List<NoticeVO> selectDeleteDbrdDocuList(NoticeVO noticeVO) throws Exception;
    
    /**
     * 게시물 생성
     * @param noticeVO
     * @return
     * @throws Exception
     */
    String insertDocu(NoticeVO noticeVO) throws Exception;
    
    /**
     * 자료조회수 수정
     * @param tbcr8810
     * @throws Exception
     */
    void updateDocuCnt(NoticeVO tbvcblbd) throws Exception;
    
    /**
     * 게시물 수정
     * @param noticeVO
     * @return
     * @throws Exception
     */
    void updateDocu(NoticeVO noticeVO) throws Exception;
    
    /**
     * 게시물 삭제
     * @param noticeVO
     * @return
     * @throws Exception
     */
    void deleteDocu(NoticeVO noticeVO) throws Exception;

    /**
     * 코멘트 조회
     * @param tbcr5320
     * @throws Exception 
     */    
    List<NoticeVO> seleteDbrdDocuCommentList(NoticeVO noticeVO) throws Exception;   
    
}
