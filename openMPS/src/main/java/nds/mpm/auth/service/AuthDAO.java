package nds.mpm.auth.service;

import java.util.List;

import nds.mpm.auth.vo.AuthVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

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

@Repository("authDAO")
public class AuthDAO extends EgovAbstractDAO {


    /**
	 * user_info 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return user_info 목록
	 * @exception Exception
	 */
    public List<?> selectDeptList(AuthVO searchVO) throws Exception {
        return list("authDAO.selectDeptList", searchVO);
    }
    /**
	 * user_info 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return user_info 목록
	 * @exception Exception
	 */
    public List<?> selectUserList(AuthVO searchVO) throws Exception {
        return list("authDAO.selectUserList", searchVO);
    }
}
