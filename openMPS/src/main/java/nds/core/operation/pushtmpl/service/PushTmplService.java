package nds.core.operation.pushtmpl.service;

import java.util.List;

/**
 * <b>class : PushTmplService </b>
 * <b>Class Description</b><br>
 * <b>History</b><br>
 * <pre>      : 2014.07.30 초기작성(오예솔)</pre>
 * @author <a href="mailto:ysoh@nds.co.kr">오예솔</a>
 * @version 1.0
 */
public interface PushTmplService {    

    /**
     * 템플릿 조회
     * @param smsVO
     * @return List
     * @throws Exception
     */
    List<PushTmplVO> selectPushTmplList(PushTmplVO vo) throws Exception;
    /**
     * 템플릿 총건수 조회
     * @param smsVO
     * @return int
     * @throws Exception
     */
    int selectPushTmplCnt(PushTmplVO vo) throws Exception;
    
    /**
     * 템플릿 상세 조회
     * @param smsVO
     * @return PushTmplVO
     * @throws Exception
     */
    PushTmplVO selectByPrimaryKey(PushTmplVO vo) throws Exception;
    
    /**
     * 템플릿 상세 조회
     * @param smsVO
     * @return PushTmplVO
     * @throws Exception
     */
    PushTmplVO selectByPushTmplInfo(PushTmplVO vo) throws Exception;
    
    /**
     * 템플릿 상세 조회
     * @param smsVO
     * @return PushTmplVO
     * @throws Exception
     */
    PushTmplVO selectByMessingerTmplInfo(PushTmplVO vo) throws Exception;
    
    /**
     * 템플릿 생성
     * @param smsVO
     * @return
     * @throws Exception
     */
    String insertPushTmpl(PushTmplVO vo) throws Exception;
    
    /**
     * 템플릿 수정
     * @param smsVO
     * @return
     * @throws Exception
     */
    void updatePushTmpl(PushTmplVO vo) throws Exception;
    
    /**
     * 템플릿 삭제
     * @param smsVO
     * @return
     * @throws Exception
     */
    void deletePushTmpl(PushTmplVO vo) throws Exception;

    /**
     * 템플릿 신규등록 전 등록가능여부 조회
     * @param smsVO
     * @return
     * @throws Exception
     */
    String selectInsertTmpl(PushTmplVO vo) throws Exception;
    
}