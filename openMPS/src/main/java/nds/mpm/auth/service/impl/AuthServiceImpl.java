package nds.mpm.auth.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.auth.service.AuthDAO;
import nds.mpm.auth.service.AuthService;
import nds.mpm.auth.vo.AuthVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : UserInfoServiceImpl.java
 * @Description : UserInfo Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("authService")
public class AuthServiceImpl extends EgovAbstractServiceImpl implements
        AuthService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Resource(name="authDAO")
    private AuthDAO authDAO;
    
    public List<?> selectDeptList(AuthVO searchVO) throws Exception {
    	return authDAO.selectDeptList(searchVO);
    }
 
    public List<?> selectUserList(AuthVO searchVO) throws Exception {
    	return authDAO.selectUserList(searchVO);
    }
}
