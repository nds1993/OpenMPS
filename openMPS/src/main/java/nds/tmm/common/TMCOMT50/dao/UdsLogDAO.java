package nds.tmm.common.TMCOMT50.dao;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.tmm.common.TMCOMT50.vo.UdsLogDefaultVO;
import nds.tmm.common.TMCOMT50.vo.UdsLogVO;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : UdsLogDAO.java
 * @Description : UdsLog DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.08.18
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("udsLogDAO")
public class UdsLogDAO extends TMMPPBaseDAO {

    /**
	 * udS_log을 조회한다.
	 * @param vo - 조회할 정보가 담긴 UdsLogVO
	 * @return 조회한 udS_log
	 * @exception Exception
	 */
    public UdsLogVO selectUdsLog(UdsLogVO vo) throws Exception {
        return (UdsLogVO) select("udsLogDAO.selectUdsLog_S", vo);
    }

    /**
	 * udS_log 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return udS_log 목록
	 * @exception Exception
	 */
    public List<?> selectUdsLogList(UdsLogDefaultVO searchVO) throws Exception {
        return list("udsLogDAO.selectUdsLogList_D", searchVO);
    }

    /**
	 * uds_log 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return uds_log 총 갯수
	 * @exception
	 */
    public int selectUdsLogListTotCnt(UdsLogDefaultVO searchVO) {
        return (Integer)select("udsLogDAO.selectUdsLogListTotCnt_S", searchVO);
    }
    
}
