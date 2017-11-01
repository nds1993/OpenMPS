package nds.mpm.login.service.impl;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.login.service.LoginDAO;
import nds.mpm.login.service.LoginService;
import nds.mpm.login.vo.LoginVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : TMCOUR10ServiceImpl.java
 * @Description : TMCOUR10 Business Implement class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("MPMLOGINService")
public class LoginServiceImpl extends TMMPPBaseService implements
        LoginService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Resource(name="MPMLOGINDAO")
    private LoginDAO MPMLOGINDAO;

    /**
	 * tm_userxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOUR10VO
	 * @return 조회한 tm_userxm
	 * @exception Exception
	 */
    public LoginVO selectTMCOUR10(LoginVO vo) throws Exception {
        LoginVO resultVO = MPMLOGINDAO.selectTMCOUR10(vo);
        return resultVO;
    }
}
