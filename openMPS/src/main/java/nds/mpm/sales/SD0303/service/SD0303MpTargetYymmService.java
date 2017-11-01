package nds.mpm.sales.SD0303.service;

import java.util.List;

import nds.mpm.sales.SD0303.vo.MpTargetYymmDefaultVO;
import nds.mpm.sales.SD0303.vo.MpTargetYymmVO;

/**
 * @Class Name : MpTargetYymmService.java
 * @Description : MpTargetYymm Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface SD0303MpTargetYymmService {
    /**
	 * mp_target_yymm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_target_yymm 목록
	 * @exception Exception
	 */
    List selectMpTargetYymmList(MpTargetYymmDefaultVO searchVO) throws Exception;
    
    
    /**
	 * mp_target_yymm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_target_yymm 목록
	 * @exception Exception
	 */
    List selectMpTargetYymmListD(MpTargetYymmDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_target_yymm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_target_yymm 총 갯수
	 * @exception
	 */
    int selectMpTargetYymmListTotCnt(MpTargetYymmDefaultVO searchVO);
    
}
