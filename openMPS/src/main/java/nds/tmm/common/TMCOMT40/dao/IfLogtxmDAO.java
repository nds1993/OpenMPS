package nds.tmm.common.TMCOMT40.dao;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.tmm.common.TMCOCD10.vo.TMCOCD10DefaultVO;
import nds.tmm.common.TMCOCD30.vo.TmMesgxmDefaultVO;
import nds.tmm.common.TMCOCD30.vo.TmMesgxmVO;
import nds.tmm.common.TMCOMT40.vo.IfLogtxmDefaultVO;
import nds.tmm.common.TMCOMT40.vo.IfLogtxmVO;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : IflogtxmDAO.java
 * @Description : Iflogtxm DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.08.14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("ifLogtxmDAO")
public class IfLogtxmDAO extends TMMPPBaseDAO {

    /**
	 * if_logtxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 IfLogtxmVO
	 * @return 조회한 if_logtxm
	 * @exception Exception
	 */
    public IfLogtxmVO selectIfLogtxm(IfLogtxmVO vo) throws Exception {
        return (IfLogtxmVO) select("ifLogtxmDAO.selectIfLogtxm_S", vo);
    }

    /**
	 * if_logtxm 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return if_logtxm 목록
	 * @exception Exception
	 */
    public List<?> selectIfLogtxmList(IfLogtxmDefaultVO searchVO) throws Exception {
        return list("ifLogtxmDAO.selectIfLogtxmList_D", searchVO);
    }

    /**
	 * if_logtxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return if_logtxm 총 갯수
	 * @exception
	 */
    public int selectIfLogtxmListTotCnt(IfLogtxmDefaultVO searchVO) {
        return (Integer)select("ifLogtxmDAO.selectIfLogtxmListTotCnt_S", searchVO);
    }
    
}
