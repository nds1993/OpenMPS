package nds.mpm.login.service;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.login.vo.LoginVO;
import nds.tmm.common.TMCOUR10.vo.TMCOUR10VO;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : TMCOUR10DAO.java
 * @Description : TMCOUR10 DAO Class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("MPMLOGINDAO")
public class LoginDAO extends TMMPPBaseDAO {

    /**
	 * tm_userxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOUR10VO
	 * @return 조회한 tm_userxm
	 * @exception Exception
	 */
    public LoginVO selectTMCOUR10(LoginVO vo) throws Exception {
        return (LoginVO) select("MPMLOGINDAO.selectTMCOUR10_S", vo);
    }

}
