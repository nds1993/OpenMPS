package nds.mpm.auth.service;

import java.util.List;

import nds.mpm.auth.vo.AuthVO;

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
public interface AuthService {
	
    public List selectDeptList(AuthVO searchVO) throws Exception ;
    public List selectUserList(AuthVO searchVO) throws Exception ;

}
