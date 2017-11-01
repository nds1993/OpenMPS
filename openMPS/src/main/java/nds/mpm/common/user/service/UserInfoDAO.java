package nds.mpm.common.user.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.common.user.vo.UserInfoVO;
import nds.mpm.common.user.vo.UserInfoDefaultVO;

/**
 * @Class Name : UserInfoDAO.java
 * @Description : UserInfo DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("userInfoDAO")
public class UserInfoDAO extends EgovAbstractDAO {

	/**
	 * user_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 UserInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertUserInfo(UserInfoVO vo) throws Exception {
        return (String)insert("userInfoDAO.insertUserInfo_S", vo);
    }

    /**
	 * user_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 UserInfoVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateUserInfo(UserInfoVO vo) throws Exception {
        update("userInfoDAO.updateUserInfo_S", vo);
    }

    /**
	 * user_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 UserInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteUserInfo(UserInfoVO vo) throws Exception {
        delete("userInfoDAO.deleteUserInfo_S", vo);
    }

    /**
	 * user_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 UserInfoVO
	 * @return 조회한 user_info
	 * @exception Exception
	 */
    public UserInfoVO selectUserInfo(UserInfoVO vo) throws Exception {
        return (UserInfoVO) select("userInfoDAO.selectUserInfo_S", vo);
    }

    /**
	 * user_info 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return user_info 목록
	 * @exception Exception
	 */
    public List<?> selectUserInfoList(UserInfoDefaultVO searchVO) throws Exception {
        return list("userInfoDAO.selectUserInfoList_D", searchVO);
    }

    /**
	 * user_info 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return user_info 총 갯수
	 * @exception
	 */
    public int selectUserInfoListTotCnt(UserInfoDefaultVO searchVO) {
        return (Integer)select("userInfoDAO.selectUserInfoListTotCnt_S", searchVO);
    }

}
