package nds.mpm.login.service;

import nds.mpm.login.vo.LoginVO;
import nds.tmm.common.TMCOUR10.vo.TMCOUR10VO;

/**
 * @Class Name : TMCOUR10Service.java
 * @Description : TMCOUR10 Business class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface LoginService {
    /**
	 * tm_userxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOUR10VO
	 * @return 조회한 tm_userxm
	 * @exception Exception
	 */
	LoginVO selectTMCOUR10(LoginVO vo) throws Exception;
}
