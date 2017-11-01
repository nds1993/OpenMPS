package nds.core.common.common.service;



import java.util.List;

//import nds.clm.claimmanageinfo.tyregist.service.TyRegistVO;


import nds.core.logsearch.loginlog.service.LoginLogVO;
import nds.core.systemsettings.code.service.CodeVO;
import nds.core.userdep.user.service.UserVO;


/**
 * <b>class : CommonService </b>
 * <b>Class Description</b><br>
 * 공통 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public interface CommonService extends Service{
    
    /**
     * 스킨정보 조회
     * @param 
     * @return SkinMstVO
     * @throws Exception
     */
	SkinMstVO getSkinInfo() throws Exception;
    /**
     * 스킨정보 조회
     * @param skinNo
     * @return SkinMstVO
     * @throws Exception
     */
	SkinMstVO getSkinInfo(String skinNo) throws Exception;
    
    /**
     * 사용자 정보 Update(로그인 여부)
     * @param loginLogVO
     * @return String
     * @throws Exception
     */
    String updateUserLogin(LoginLogVO loginLogVO) throws Exception;    
    
    /**
     * 사용자 정보 Update(로그인 여부)
     * @param loginLogVO
     * @return void
     * @throws Exception
     */
    void updateUserLogout(LoginLogVO loginLogVO) throws Exception;   
    
    /**
     * 메뉴에 대한 상세정보를 가져온다.
     * @param userMenu
     * @return List
     * @throws Exception
     */
    List selectMenuInfo(UserMenu userMenu) throws Exception;

    /**
     * 메뉴에대한 사용자역할을 조회한다.
     * @param userMenu
     * @return List
     * @throws Exception
     */
    List selectCstContentRoleInfo(UserMenu userMenu) throws Exception;
    
    /**
     * 사용로그 기록
     * @param loginLogVO
     * @return void
     * @throws Exception
     */
//    void useLogWrite(Tbcr9910 tbcr9910) throws Exception;   
    
    /**
     * 사용자 정보 조회
     * @param userVO, mode
     * @return UserVO
     * @throws Exception
     */
    UserVO getUserInfo(UserVO userVO, boolean mode) throws Exception;
    
    
    /**
     * 내 메뉴 컬럼 저장
     * @param myVocStateVO
     * @return MyVocStateVO
     * @throws Exception
     */
    void insertMenuColumn(MyMenuColumnVO myMenuColumnVO) throws Exception;
    
	/**
     * 내 메뉴 컬럼을 조회한다.
     * @param myMenuColumnVO
     * @return MyMenuColumnVO
     * @throws Exception
     */
   MyMenuColumnVO selectMenuColumn(MyMenuColumnVO myMenuColumnVO) throws Exception;
   
   /**
    * 공통코드 조회
    * @param dbuser, codeVO
    * @return List
    * @throws Exception
    */
   List<CodeVO> selectCommonCode(String dbuser, CodeVO codeVO) throws Exception;
   
   /**
    * 제안종류 중 코드 조회
    * @param propTypeVO
    * @return List
    * @throws Exception
    */
   List<PropTypeVO> selectPropTypeMcls(PropTypeVO propTypeVO) throws Exception;
   
   /**
    * 제안결과 중 코드 조회
    * @param propTypeVO
    * @return List
    * @throws Exception
    */
   List<PropTypeVO> selectPropResultTypeMcls(PropTypeVO propTypeVO) throws Exception;

   /**
    * LOCAL 메뉴에대한 상세정보를 가져온다.
    * @param key
    * @return
    * @throws Exception
    */
   List selectLocalMenuInfo(UserMenu key) throws Exception;
   
   /**
    * 그룹웨어 기안
    * @param key
    * @return
    * @throws Exception
    */
   void updateApprovalState(String tableNm, GwApprovalVO approval) throws Exception;
   

   /**
    * 알림 대상자 및 내용 조회
    * @param tyRegistVO
    * @return
    * @throws Exception
    */
	List<NotiUserInfoVO> selectNotiUserList(NotiUserInfoVO key) throws Exception ;
   
}
