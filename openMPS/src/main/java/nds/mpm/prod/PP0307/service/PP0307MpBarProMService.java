package nds.mpm.prod.PP0307.service;

import java.util.List;

import nds.mpm.prod.PP0504.vo.MpBarProMDefaultVO;

/**
 * @Class Name : MpBarProMService.java
 * @Description : MpBarProM Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PP0307MpBarProMService {
	
    /**
	 * mp_bar_pro_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bar_pro_m 목록
	 * @exception Exception
	 */
    List selectMpBarProMList(MpBarProMDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_bar_pro_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bar_pro_m 총 갯수
	 * @exception
	 */
    int selectMpBarProMListTotCnt(MpBarProMDefaultVO searchVO);
    
    public List<?> selectPopMpBarProMList(MpBarProMDefaultVO searchVO) throws Exception ;

    
}
