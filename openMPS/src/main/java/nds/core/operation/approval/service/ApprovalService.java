package nds.core.operation.approval.service;



import java.util.List;

import nds.core.common.common.service.Service;



/**
 * <p>Title: ApprovalService</p>
 * <p>Description: Service Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.26 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
public interface ApprovalService extends Service {

	/**
     * 승인단계 등록
     * @param approvalVO
     * @return String
     * @throws Exception
     */
    void insertApvMg(List<ApprovalVO> approvalVO) throws Exception;
    
    /**
     * 승인단계 수정
     * @param approvalVO
     * @return void
     * @throws Exception
     */
    void updateApvMg(ApprovalVO approvalVO) throws Exception;
    
    /**
     * 전체 승인단계 삭제
     * @param approvalVO
     * @return void
     * @throws Exception
     */
    void deleteAllApvMg(String userEmpno) throws Exception;
    
    /**
     * 단일 승인단계 삭제
     * @param approvalVO
     * @return void
     * @throws Exception
     */
    void deleteApvMg(ApprovalVO approvalVO) throws Exception;

    /**
     * 승인단계 목록조회
     * @param approvalVO
     * @return List
     * @throws Exception
     */
    List<ApprovalVO> selectApvMgList(String userEmpno) throws Exception;
    
    /**
     * 승인단계 목록조회 cnt
     * @param approvalVO
     * @return int
     * @throws Exception
     */
    int selectApvStagListCount(String userEmpno) throws Exception;
    
    /**
     * 승인단계 목록조회
     * @param approvalVO
     * @return List
     * @throws Exception
     */
    List<ApprovalVO> selectApvStagList(String userEmpno) throws Exception;
    
    /**
     * 승인단계 목록조회 cnt
     * @param approvalVO
     * @return int
     * @throws Exception
     */
    int selectApvMgListCount(String userEmpno) throws Exception;
    
    /**
     * 단계별 승인단계 조회
     * @param approvalVO
     * @return List
     * @throws Exception
     */
    List<ApprovalVO> selectByApvMgList(ApprovalVO approvalVO) throws Exception;
    
    /**
     * 대상 사용자 조회
     * @param approvalVO
     * @return List
     * @throws Exception
     */
    List<ApprovalVO> selectUserList(ApprovalVO approvalVO) throws Exception;
    
    /**
     * 대상 사용자 조회 CNT
     * @param approvalVO
     * @return int
     * @throws Exception
     */
    int selectUserListCount(ApprovalVO approvalVO) throws Exception;
}
