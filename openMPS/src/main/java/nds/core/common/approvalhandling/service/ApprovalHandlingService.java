package nds.core.common.approvalhandling.service;


import java.util.List;

import nds.core.common.common.service.Service;
import nds.core.operation.approval.service.ApprovalVO;
import nds.core.userdep.user.service.UserVO;


/**
 * <b>class : ApprovalHandlingService </b>
 * <b>Class Description</b><br>
 * 승인처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2015.05.12 초기작성(김선범)</pre>
 * @author <a href="mailto:sbkim@nds.co.kr">김선범</a>
 * @version 1.0
 */
public interface ApprovalHandlingService extends Service {

    
    /**
     * 승인대기건 VOC 카운트 조회
     * 
     * @param approvalVO
     * @return int
     * @throws Exception
     */
    int selectChargeVocCount(ApprovalVO approvalVO) throws Exception;
	
    /**
     * 승인대기건 조회
     * 
     * @param approvalVO
     * @return List
     * @throws Exception
     */
    List<ApprovalVO> selectChargeVocList(ApprovalVO approvalVO) throws Exception;
    
    /**
     * 승인대기건 카운트 조회
     * 
     * @param approvalVO
     * @return int
     * @throws Exception
     */
    int selectChargeVocListCount(ApprovalVO approvalVO) throws Exception;
    
    /**
     * 승인대기 상세조회
     * 
     * @param approvalVO
     * @return ApprovalVO
     * @throws Exception
     */
    ApprovalVO selectChargeVoc(ApprovalVO approvalVO) throws Exception;
    
    /**
     * 승인대기 제안상세조회
     * 
     * @param approvalVO
     * @return ApprovalVO
     * @throws Exception
     */
    ApprovalVO selectChargeSug(ApprovalVO approvalVO) throws Exception;
    
    /**
     * 승인대기 단계조회
     * 
     * @param approvalVO
     * @return List
     * @throws Exception
     */
    List<ApprovalVO> selectApvStag(ApprovalVO approvalVO) throws Exception;
    
    /**
     * 승인대기 제안승인
     * 
     * @param approvalVO
     * @return void
     * @throws Exception
     */
    void callApvInfoProc(ApprovalVO approvalVO) throws Exception; 
    
    /**
     * 승인대상자 목록 조회 (알림메시지 발송용)
     * 
     * @param approvalVO
     * @return List<UserVO>
     * @throws Exception
     */
    List<UserVO> selectApvAlramList(ApprovalVO approvalVO) throws Exception;

    /**
     * 제안 승인 요청
     * @param ApprovalVO
     * @return void
     * @throws Exception
     */
    void insertPropApvInfo(ApprovalVO approvalVO) throws Exception;
}