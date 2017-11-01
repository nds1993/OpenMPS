package nds.core.operation.ccmtmpl.service;

import java.util.List;

/**
 * <b>class : CcmTmplService </b>
 * <b>Class Description</b><br>
 * <b>History</b><br>
 * <pre>      : 2014.07.30 초기작성(오예솔)</pre>
 * @author <a href="mailto:ysoh@nds.co.kr">오예솔</a>
 * @version 1.0
 */
public interface CcmTmplService {    

    /**
     * 템플릿 조회
     * @param smsVO
     * @return List
     * @throws Exception
     */
    List<CcmTmplVO> selectCcmTmplList(CcmTmplVO vo) throws Exception;
    /**
     * 템플릿 총건수 조회
     * @param smsVO
     * @return int
     * @throws Exception
     */
    int selectCcmTmplCnt(CcmTmplVO vo) throws Exception;
    
    /**
     * 템플릿 상세 조회
     * @param smsVO
     * @return CcmTmplVO
     * @throws Exception
     */
    CcmTmplVO selectByPrimaryKey(CcmTmplVO vo) throws Exception;
    
    /**
     * 템플릿 상세 조회
     * @param smsVO
     * @return CcmTmplVO
     * @throws Exception
     */
    CcmTmplVO selectByCcmTmplInfo(CcmTmplVO vo) throws Exception;
    
    /**
     * 템플릿 상세 조회
     * @param smsVO
     * @return CcmTmplVO
     * @throws Exception
     */
    CcmTmplVO selectByMessingerTmplInfo(CcmTmplVO vo) throws Exception;
    
    /**
     * 템플릿 생성
     * @param smsVO
     * @return
     * @throws Exception
     */
    String insertCcmTmpl(CcmTmplVO vo) throws Exception;
    
    /**
     * 템플릿 수정
     * @param smsVO
     * @return
     * @throws Exception
     */
    void updateCcmTmpl(CcmTmplVO vo) throws Exception;
    
    /**
     * 템플릿 삭제
     * @param smsVO
     * @return
     * @throws Exception
     */
    void deleteCcmTmpl(CcmTmplVO vo) throws Exception;

    /**
     * 템플릿 신규등록 전 등록가능여부 조회
     * @param smsVO
     * @return
     * @throws Exception
     */
    String selectInsertTmpl(CcmTmplVO vo) throws Exception;
    
}