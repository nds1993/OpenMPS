package nds.mpm.prod.PP0204.service;

import java.util.List;

import nds.mpm.prod.PP0204.vo.MpYieldInfoMDefaultVO;
import nds.mpm.prod.PP0204.vo.MpYieldInfoMVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpYieldInfoMDAO.java
 * @Description : MpYieldInfoM DAO Class
 * @Modification Information
 *
 * @author mm
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PP0204mpYieldInfoMDAO")
public class PP0204MpYieldInfoMDAO extends EgovAbstractDAO {

    /**
	 * mp_yield_info_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpYieldInfoMVO
	 * @return 조회한 mp_yield_info_m
	 * @exception Exception
	 */
    public List<?> selectMpYieldInfoM(MpYieldInfoMVO vo) throws Exception {
        return list("PP0204mpYieldInfoMDAO.selectTotalMpYieldInfoM_S", vo);
    }

    /**
	 * mp_yield_info_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_yield_info_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpYieldInfoMList(MpYieldInfoMVO searchVO) throws Exception {
        return list("PP0204mpYieldInfoMDAO.selectMpYieldInfoMList_D", searchVO);
    }


}
