package nds.core.userdep.user.service;



import java.util.List;

import nds.core.common.common.service.Service;


/**
 * <b>class : DepartMentService </b>
 * <b>Class Description</b><br>
 * 부서 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public interface UserService extends Service {
    

	   /**
	    * 사용자 조회
	    * @return List
	    * @throws Exception
	    */
	   List selectCustList(UserVO userVO) throws Exception;
	   /**
	    * 사용자 건수조회
	    * @param helper
	    * @return int
	    * @throws Exception
	    */
	   int selectCustListCount(UserVO userVO) throws Exception;
	   /**
	    * 사용자상세조회
	    * @param helper
	    * @return int
	    * @throws Exception
	    */
	   UserVO selectByPrimaryKey(String userId) throws Exception; 
	    
	   /**
	     * 조직사용자 등록
	     * @param userVO
	     * @return List
	     * @throws Exception
	     */  
	    void insertUsr(UserVO userVO) throws Exception;
	    
	    /**
	     * 조직사용자 수정
	     * @param userVO
	     * @return List
	     * @throws Exception
	     */
	    void updateUsr(UserVO userVO) throws Exception;
	    
	   /**
	    * ID 건수조회
	    * @param helper
	    * @return int
	    * @throws Exception
	    */
	   int selectUserIdCount(UserVO userVO) throws Exception;
	   
	   /**
	    * 사용자상세조회
	    * @param helper
	    * @return int
	    * @throws Exception
	    */
	   List<UserVO> selectByUserInfoByRole(UserVO userVO) throws Exception; 
	   
	   /**
	    * 사용자상세조회
	    * @param helper
	    * @return int
	    * @throws Exception
	    */
	   List<UserVO> selectByUserInfoByDepMngRole(UserVO userVO) throws Exception; 
}
