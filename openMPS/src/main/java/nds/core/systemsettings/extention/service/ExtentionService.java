package nds.core.systemsettings.extention.service;

import java.util.List;

import nds.core.common.common.service.Service;

/**
 * <b>class : ExtentionService </b>
 * <b>Class Description</b><br>
 * 파일 확장자 관리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2015.09.09 초기작성(김세희)</pre>
 * @author <a href="mailto:shkim@nds.co.kr">김세희</a>
 * @version 1.0
 */
public interface ExtentionService extends Service{

	  /**
     * 파일확장자 등록
     * @param extentionVO
     * @throws Exception
     */
    void insertExtention(ExtentionVO extentionVO) throws Exception;
    /**
     * 파일확장자 수정
     * @param extentionVO
     * @throws Exception
     */
    void updateExtention(ExtentionVO extentionVO) throws Exception;
    /**
     * 파일확장자 삭제
     * @param extentionVO
     * @throws Exception
     */
    void deleteExtention(ExtentionVO extentionVO) throws Exception;
    
    /**
     * 파일확장자 조회
     * @param extentionVO
     * @return List
     * @throws Exception
     */
    List selectExtention(ExtentionVO extentionVO) throws Exception;
    
    /**
     * 파일확장자 건수조회
     * @param extentionVO
     * @return int
     * @throws Exception
     */
    int selectExtentionCount(ExtentionVO extentionVO) throws Exception;
    
    /**
     * 파일확장자 중복건수
     * @param extentionVO
     * @return int
     * @throws Exception
     */
    int selectExtentionIdCount(ExtentionVO extentionVO) throws Exception;
}
