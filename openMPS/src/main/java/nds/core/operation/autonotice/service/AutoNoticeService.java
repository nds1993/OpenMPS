package nds.core.operation.autonotice.service;

import java.util.List;

import nds.core.common.common.service.Service;


/**
 * <b>class : AutoNoticeService </b>
 * <b>Class Description</b><br>
 * 자동알림설정를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2015.04.22 초기작성(김세희)</pre>
 * @author <a href="mailto:shkim@nds.co.kr">김세희</a>
 * @version 1.0
 */
public interface AutoNoticeService extends Service {

	/**
     * 자동알림설정 등록 건수 조회
     * @param autoNoticeVO
     * @return void
     * @throws Exception
     */
	int selectAutoListCount(AutoNoticeVO autoNoticeVO) throws Exception;

	/**
     * 자동알림설정 목록 조회
     * @param autoNoticeVO
     * @return void
     * @throws Exception
     */
	List<AutoNoticeVO> selectAutoList(AutoNoticeVO autoNoticeVO) throws Exception;

	/**
     * 자동알림설정 신규등록 전 등록가능여부 조회
     * @param autoNoticeVO
     * @return
     * @throws Exception
     */
	String selectInsertAuto(AutoNoticeVO autoNoticeVO) throws Exception;

	/**
	 * 자동알림설정 생성
	 * @param autoInsertList
	 * @return
	 * @throws Exception
	 */
	void insertAutoNotice(List<AutoNoticeVO> autoNoticeVO) throws Exception;
	
    
    /**
     * 자동알림설정 수정
     * @param autoNoticeVO
     * @return
     * @throws Exception
     */
//    void updateSmsTmpl(AutoNoticeVO autoNoticeVO) throws Exception;
    
    /**
     * 자동알림설정 삭제
     * @param autoNoticeVO
     * @return
     * @throws Exception
     */
    void deleteAuto(AutoNoticeVO autoNoticeVO) throws Exception;

    

}
