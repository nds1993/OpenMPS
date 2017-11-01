package nds.mpm.prod.PP0204.service;

import java.util.List;

import nds.mpm.prod.PP0204.vo.MpYieldInfoMDefaultVO;
import nds.mpm.prod.PP0204.vo.MpYieldInfoMVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpYieldInfoMService.java
 * @Description : MpYieldInfoM Business class
 * @Modification Information
 *
 * @author mm
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PP0204MpYieldInfoMService {
    /**
	 * mp_yield_info_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpYieldInfoMVO
	 * @return 조회한 mp_yield_info_m
	 * @exception Exception
	 */
	List selectTotalMpYieldInfoM_S(MpYieldInfoMVO vo) throws Exception;
    
    /**
	 * mp_yield_info_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_yield_info_m 목록
	 * @exception Exception
	 */
    List selectMpYieldInfoMList(MpYieldInfoMVO searchVO) throws Exception;
}
