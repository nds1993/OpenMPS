package nds.mpm.common.user.service;

import java.util.List;
import nds.mpm.common.user.vo.UserInfoDefaultVO;
import nds.mpm.common.user.vo.UserInfoVO;

/**
 * @Class Name : UserInfoService.java
 * @Description : UserInfo Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface UserInfoService {
	
	/**
	 * USER_INFO을 등록한다.
	 * @param vo - 등록할 정보가 담긴 UserInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertUserInfo(UserInfoVO vo) throws Exception;
    
    /**
	 * USER_INFO을 수정한다.
	 * @param vo - 수정할 정보가 담긴 UserInfoVO
	 * @return void형
	 * @exception Exception
	 */
    void updateUserInfo(UserInfoVO vo) throws Exception;
    
    /**
	 * USER_INFO을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 UserInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteUserInfo(UserInfoVO vo) throws Exception;
    
    /**
	 * USER_INFO을 조회한다.
	 * @param vo - 조회할 정보가 담긴 UserInfoVO
	 * @return 조회한 USER_INFO
	 * @exception Exception
	 */
    UserInfoVO selectUserInfo(UserInfoVO vo) throws Exception;
    
    /**
	 * USER_INFO 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return USER_INFO 목록
	 * @exception Exception
	 */
    List selectUserInfoList(UserInfoDefaultVO searchVO) throws Exception;
    
    /**
	 * USER_INFO 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return USER_INFO 총 갯수
	 * @exception
	 */
    int selectUserInfoListTotCnt(UserInfoDefaultVO searchVO);
    
}
