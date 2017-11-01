package nds.mpm.prod.PP0702.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.prod.PP0101.vo.TmPlatxmDefaultVO;
import nds.mpm.prod.PP0101.vo.TmPlatxmVO;
import nds.mpm.prod.PP0504.vo.MpBarProMDefaultVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TmPlatxmDAO.java
 * @Description : TmPlatxm DAO Class
 * @Modification Information
 *
 * @author MPM
 * @since 2017
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PP0702tmPlatxmDAO")
public class PP0702TmPlatxmDAO extends TMMPPBaseDAO {

    /**
	 * tm_platxm 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return tm_platxm 총 갯수
	 * @exception
	 */
	public List<?> selectTmPlatxmList(TmPlatxmDefaultVO searchVO) throws Exception {
	    return list("PP0702tmPlatxmDAO.selectTmPlatxmList_D", searchVO);
    }
	
    public int selectTmPlatxmListTotCnt(TmPlatxmDefaultVO searchVO) {
        return (Integer)select("PP0702tmPlatxmDAO.selectTmPlatxmListTotCnt_S", searchVO);
    }
}
