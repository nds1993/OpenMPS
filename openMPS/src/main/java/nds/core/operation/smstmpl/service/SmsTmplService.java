package nds.core.operation.smstmpl.service;

import java.util.List;

/**
 * <b>class : SmsTmplService </b>
 * <b>Class Description</b><br>
 * <b>History</b><br>
 * <pre>      : 2014.07.30 초기작성(오예솔)</pre>
 * @author <a href="mailto:ysoh@nds.co.kr">오예솔</a>
 * @version 1.0
 */
public interface SmsTmplService {    

    /**
     * 템플릿 조회
     * @param smsVO
     * @return List
     * @throws Exception
     */
    List<SmsTmplVO> selectSmsTmplList(SmsTmplVO smsVO) throws Exception;
    /**
     * 템플릿 총건수 조회
     * @param smsVO
     * @return int
     * @throws Exception
     */
    int selectSmsTmplCnt(SmsTmplVO smsVO) throws Exception;
    
    /**
     * 템플릿 상세 조회
     * @param smsVO
     * @return SmsTmplVO
     * @throws Exception
     */
    SmsTmplVO selectByPrimaryKey(SmsTmplVO smsVO) throws Exception;
    
    /**
     * 템플릿 상세 조회
     * @param smsVO
     * @return SmsTmplVO
     * @throws Exception
     */
    SmsTmplVO selectBySmsTmplInfo(SmsTmplVO smsVO) throws Exception;
    
    /**
     * 템플릿 상세 조회
     * @param smsVO
     * @return SmsTmplVO
     * @throws Exception
     */
    SmsTmplVO selectByMessingerTmplInfo(SmsTmplVO smsVO) throws Exception;
    
    /**
     * 템플릿 생성
     * @param smsVO
     * @return
     * @throws Exception
     */
    String insertSmsTmpl(SmsTmplVO smsVO) throws Exception;
    
    /**
     * 템플릿 수정
     * @param smsVO
     * @return
     * @throws Exception
     */
    void updateSmsTmpl(SmsTmplVO smsVO) throws Exception;
    
    /**
     * 템플릿 삭제
     * @param smsVO
     * @return
     * @throws Exception
     */
    void deleteSmsTmpl(SmsTmplVO smsVO) throws Exception;

    /**
     * 템플릿 신규등록 전 등록가능여부 조회
     * @param smsVO
     * @return
     * @throws Exception
     */
    String selectInsertTmpl(SmsTmplVO smsVO) throws Exception;
    
}