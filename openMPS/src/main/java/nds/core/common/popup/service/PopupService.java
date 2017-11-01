package nds.core.common.popup.service;



import java.util.List;

import nds.core.common.common.service.AttachFileVO;
import nds.core.common.common.service.NeedsMstVO;
import nds.core.common.common.service.Service;
import nds.core.operation.approval.service.ApprovalVO;
import nds.core.userdep.department.service.DepartMentVO;
import nds.core.userdep.user.service.UserVO;


/**
 * <b>class : PopupService </b>
 * <b>Class Description</b><br>
 * 로그인 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public interface PopupService extends Service {


    /**
     * 메인 위젯 설정
     * @param key
     * @return void
     * @throws Exception
     */
    void insertMaWidgets(PopupWidgetSetVO popupWidgetSetVO) throws Exception;
    
    /**
     * 메인 위젯 설정
     * @param key
     * @return void
     * @throws Exception
     */
    void deleteMaWidgets(String key) throws Exception;
    
    /**
     * 개인별 메인위젯설정 목록
     * @param key
     * @return List
     * @throws Exception
     */
    List<PopupWidgetSetVO> selectMaWidgetsList(String key) throws Exception;
    
    /**
     * 조직정보조회
     * @param depCd
     * @return List
     * @throws Exception
     */
    List selectOrgTree(String depCd) throws Exception;
    
    /**
     * 조직정보조회(트리)
     * @param departMentVO
     * @return List<DepartMentVO>
     * @throws Exception
     */
    List<DepartMentVO> selectDepTree(DepartMentVO departMentVO) throws Exception;
    
    /**
	 * 제품분류 조회
	 * @param popupPrductVO
	 * @return
	 * @throws Exception
	 */
	List<PopupPrductVO> selectPrductType(PopupPrductVO popupPrductVO) throws Exception;
	
	/**
	 * 제품 조회
	 * @param popupPrductVO
	 * @return
	 * @throws Exception
	 */
	List<PopupPrductVO> selectPrductList(PopupPrductVO popupPrductVO) throws Exception;
	
	/**
	 *  자재유형 별 자재조회
	 * @param popupPrductVO
	 * @return
	 * @throws Exception
	 */
	List<PopupPrductVO> selectPrductList_pType(PopupPrductVO popupPrductVO) throws Exception;
	int selectPrductList_pTypeCount(PopupPrductVO popupPrductVO) throws Exception;
	
	List<PopupPrductVO> selectPrductList_Halb(PopupPrductVO popupPrductVO) throws Exception;
	
	/**
	 * 클레임 접수번호 조회(팝업)
	 * @param popupAcepnoVO
	 * @return
	 */
	List<PopupAcepnoVO> selectClaimList(PopupAcepnoVO popupAcepnoVO) throws Exception;
	
	/**
	 * 클레임 접수번호 건수 조회(팝업)
	 * @param popupAcepnoVO
	 * @return
	 */
	int selectClaimListCount(PopupAcepnoVO popupAcepnoVO) throws Exception;
	
	/**
	 * 대표제품 검색
	 * @param popupPrductVO
	 * @return
	 * @throws Exception
	 */
	List<PopupPrductVO> selectPrductBossType(PopupPrductVO popupPrductVO)throws Exception;
	
    
    /**
     * 내부조직조회
     * @param departMentVO
     * @return List
     * @throws Exception
     */
    List<DepartMentVO> selectDepList(DepartMentVO departMentVO) throws Exception;
    
    /**
     * 내부조직조회
     * @param departMentVO
     * @return int
     * @throws Exception
     */
    int selectDepListCount(DepartMentVO departMentVO) throws Exception;
    
    /**
     * 사용자찾기(Ajax)
     * @param userVO
     * @return List
     * @throws Exception
     */
    List selectCustList(UserVO userVO) throws Exception;
    /**
     * 사용자 건수조회
     * @param userVO
     * @return int
     * @throws Exception
     */
    int selectCustListCount(UserVO userVO) throws Exception;
	   
    /**
     * 승인처리
     * @param vocId
     * @return int
     * @throws Exception
     */
    int updateApproVal(String vocId) throws Exception;
    
    /**
     * 상담유형코드조회
     * @param popupCnclTypeVO
     * @return List
     * @throws Exception
     */
    List<PopupCnslTypeVO> selectCnslTypeCdList(PopupCnslTypeVO popupCnclTypeVO) throws Exception;
    
    /**
     * 상담유형코드 건수조회
     * @param popupCnclTypeVO
     * @return int
     * @throws Exception
     */
    int selectCnslTypeCdCount(PopupCnslTypeVO popupCnclTypeVO) throws Exception;
    
    /**
     * VOC유형코드 트리 조회
     * @param CnslTypeVO
     * @return List
     * @throws Exception
     */
    public List<CnslTypeVO> selectByTree(CnslTypeVO cnslTypeVO) throws Exception;
    /**
     * VOC유형코드 단건 조회
     * @param CnslTypeVO
     * @return List
     * @throws Exception
     */
    public List<CnslTypeVO> selectCnslType (CnslTypeVO cnslTypeVO) throws Exception;
    	
    
    /**
     * 전체VOC유형코드조회
     * @param CnslTypeVO
     * @return List
     * @throws Exception
     */
    List<CnslTypeVO> selectCnslTypeList(CnslTypeVO cnslTypeVO) throws Exception;
    
    /**
     * 전체VOC유형코드 count 조회
     * @param CnslTypeVO
     * @return int
     * @throws Exception
     */
    int selectCnslTypeListCount(CnslTypeVO cnslTypeVO) throws Exception;
    
    /**
     * VOC유형코드조회
     * @param CnslTypeVO
     * @return List
     * @throws Exception
     */
    List<CnslTypeVO> selectMyCnslType(CnslTypeVO cnslTypeVO) throws Exception;
    
    /**
     * VOC유형코드 건수조회
     * @param CnslTypeVO
     * @return int
     * @throws Exception
     */
    int selectMyCnslTypeCount(CnslTypeVO cnslTypeVO) throws Exception;
    
    /**
     * 고객 조회
     * @param popupCustInfoVO
     * @return List
     * @throws Exception
     */
    List<PopupCustInfoVO> selectCstList(PopupCustInfoVO popupCustInfoVO) throws Exception;
  
    /**
     * 고객건수 조회
     * @param popupCustInfoVO
     * @return int
     * @throws Exception
     */
    int selectCstListCount(PopupCustInfoVO popupCustInfoVO) throws Exception;
    
    /**
     * 고객 상세조회
     * @param popupCustInfoVO
     * @return List
     * @throws Exception
     */
    List<PopupCustInfoVO> selectCstDetail(PopupCustInfoVO popupCustInfoVO) throws Exception;
    
    /**
     * 고객메모 등록
     * @param popupCustMemoVO
     * @return void
     * @throws Exception
     */
    String insertCstMemo(PopupCustMemoVO popupCustMemoVO) throws Exception;
    
    /**
     * 고객메모 수정
     * @param popupCustMemoVO
     * @return void
     * @throws Exception
     */
    void updateCstMemo(PopupCustMemoVO popupCustMemoVO) throws Exception;
    
    /**
     * 고객메모 최근 표시값 조회
     * @param popupCustMemoVO
     * @return PopupCustMemoVO
     * @throws Exception
     */
    PopupCustMemoVO selectCstMemoLast(PopupCustMemoVO popupCustMemoVO) throws Exception;
    
    /**
     * 고객메모 조회
     * @param popupCustMemoVO
     * @return List
     * @throws Exception
     */
    List<PopupCustMemoVO> selectCstMemo(PopupCustMemoVO popupCustMemoVO) throws Exception;
    
    /**
     * 고객메모 상세
     * @param popupCustMemoVO
     * @return PopupCustMemoVO
     * @throws Exception
     */
    PopupCustMemoVO selectCstMemoInfo(PopupCustMemoVO popupCustMemoVO) throws Exception;
    
    /**
     * 고객메모 상세조회(등록용)
     * @param popupCustMemoVO
     * @return List
     * @throws Exception
     */
    List<PopupCustMemoVO> selectCstMemoDetail(PopupCustMemoVO popupCustMemoVO) throws Exception;
    
    /**
     * 고객메모 상세조회(조회용)
     * @param cstNo
     * @return PopupCustMemoVO
     * @throws Exception
     */
    PopupCustMemoVO selectCstMemoInfo(String cstNo) throws Exception;

    /**
     * 업무정보 조회
     * @param userId
     * @return List
     * @throws Exception
     */
    UserVO selectUsrWorkInfo(String userId) throws Exception;
    
    /**
     * 업무정보 등록
     * @param userVO
     * @return List
     * @throws Exception
     */
    int updateUsrWorkInfo(UserVO userVO) throws Exception;
    
    /**
     * 승인단계 등록
     * @param approvalVO
     * @return String
     * @throws Exception
     */
    public String insertApvMg(ApprovalVO approvalVO) throws Exception;
    
    /**
     * 승인단계 수정
     * @param approvalVO
     * @return void
     * @throws Exception
     */
    void updateApvMg(ApprovalVO approvalVO) throws Exception;
    
    /**
     * 승인단계 삭제
     * @param approvalVO
     * @throws Exception
     */
    void deleteAllApvMg(String userEmpno) throws Exception;

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
    int selectApvMgListCount(String userEmpno) throws Exception;
    
    /**
     * 승인이력 조회
     * @param popupApprovalVO
     * @return List
     * @throws Exception
     */
    List<PopupApprovalVO> selectApvHistList(PopupApprovalVO popupApprovalVO) throws Exception;
    
    /**
     * 승인이력 카운트 조회
     * @param popupApprovalVO
     * @return int
     * @throws Exception
     */
    int selectApvHistListCount(PopupApprovalVO popupApprovalVO) throws Exception;
    
    
	
    /**
     * 배분이력 조회
     * @param NeedsMstVO
     * @return List
     * @throws Exception
     */
    List<NeedsMstVO> selectDstrHist(NeedsMstVO needsMstVO) throws Exception;
    
    /**
     * 배분이력 카운트 조회
     * @param NeedsMstVO
     * @return int
     * @throws Exception
     */
    int selectDstrHistCount(NeedsMstVO needsMstVO) throws Exception;
    
    
	/**
     * 승인반려 재승인 요청
     * @param popupApprovalVO
     * @return void
     * @throws Exception
     */
    void insertReTbvcApvInfo(PopupApprovalVO popupApprovalVO) throws Exception;
    
    /**
     * 고객니즈변경
     * @param popupNeedsChngHistVO
     * @return String
     * @throws Exception
     */
    String insertNeedsChange(PopupNeedsChngHistVO popupNeedsChngHistVO)  throws Exception;
    
    /**
     * 고객니즈변경 이력조회
     * @param popupNeedsChngHistVO
     * @return List
     * @throws Exception
     */
    List<PopupNeedsChngHistVO> selectTbvcNeedsChngHist(PopupNeedsChngHistVO popupNeedsChngHistVO)  throws Exception;
   
    /**
     * 상담유형 코드 자동완성 조회
     * @param CnslTypeVO
     * @return List
     * @throws Exception
     */
    List<PopupCnslTypeVO> selectCnslTypeAutoComplete(PopupCnslTypeVO popupCnslTypeVO)  throws Exception;

    /**
     * 배정이력 등록
     * @param popupPropAsgnHistVO
     * @return void
     * @throws Exception
     */
    void insertPropAsgnHist(PopupPropAsgnHistVO popupPropAsgnHistVO) throws Exception;

    /**
     * 배정이력 조회
     * @param popupPropAsgnHistVO
     * @return List
     * @throws Exception
     */
	List<PopupPropAsgnHistVO> selectPropAsgnHistList(PopupPropAsgnHistVO popupPropAsgnHistVO) throws Exception;
    
    /**
     * 배정이력 카운트 조회
     * @param popupPropAsgnHistVO
     * @return int
     * @throws Exception
     */
	int selectPropAsgnHistListCount(PopupPropAsgnHistVO popupPropAsgnHistVO) throws Exception;

    /**
     * 검토요청 등록
     * @param popupHelpReqInfoVO
     * @return void
     * @throws Exception
     */
    void insertHelpReqInfo(PopupHelpReqInfoVO popupHelpReqInfoVO) throws Exception;
    
    /**
     * 변경이력
     * @param popupPropChngHistVO
     * @return List
     * @throws Exception
     */
    List<PopupPropChngHistVO> selectPropChngHistList(PopupPropChngHistVO popupPropChngHistVO) throws Exception;
    
    /**
     * 변경이력 등록
     * @param popupPropAsgnHistVO
     * @return void
     * @throws Exception
     */
    void insertPropChngHist(PopupPropChngHistVO popupPropChngHistVO) throws Exception;
    
	/**
	 * 클레임 파일 정보 update
	 * @param String
	 * @return
	 * @throws Exception
	 */
	void updateClaimFileInfo(AttachFileVO key) throws Exception;
	
	
	/**
	 * 제품 즐겨찾기 조회
	 * @param popupPrductVO
	 * @return
	 * @throws Exception
	 */
	List<PopupPrductVO> selectProdBkmkList(PopupPrductVO popupPrductVO) throws Exception;
	
	/**
	 * 제품 건수 조회
	 * @param popupPrductVO
	 * @return
	 * @throws Exception
	 */
	int selectPrductListCount(PopupPrductVO popupPrductVO) throws Exception;
	  /**
     * 조직정보조회(트리)
     * @param departMentVO
     * @return List<DepartMentVO>
     * @throws Exception
     */
    List<PopupVendorVO> selectVendorList(PopupVendorVO popupVendorVO) throws Exception;
    int selectVendorCount(PopupVendorVO popupVendorVO) throws Exception;
	int selectPrductList_HalbCount(PopupPrductVO popupPrductVO) throws Exception;

}

