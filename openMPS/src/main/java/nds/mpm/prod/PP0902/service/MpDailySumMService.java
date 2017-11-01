package nds.mpm.prod.PP0902.service;

import java.util.List;
import nds.mpm.prod.PP0902.vo.MpDailySumMDefaultVO;
import nds.mpm.prod.PP0902.vo.MpDailySumMVO;

/**
 * @Class Name : MpDailySumMService.java
 * @Description : MpDailySumM Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpDailySumMService {
	
	/**
	 * mp_daily_sum_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDailySumMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpDailySumM(MpDailySumMVO vo) throws Exception;
    
    /**
	 * mp_daily_sum_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDailySumMVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpDailySumM(MpDailySumMVO vo) throws Exception;
    
    /**
	 * mp_daily_sum_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDailySumMVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpDailySumM(MpDailySumMVO vo) throws Exception;
    
    /**
	 * mp_daily_sum_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDailySumMVO
	 * @return 조회한 mp_daily_sum_m
	 * @exception Exception
	 */
    MpDailySumMVO selectMpDailySumM(MpDailySumMVO vo) throws Exception;
    
    /**
	 * mp_daily_sum_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_daily_sum_m 목록
	 * @exception Exception
	 */
    List selectMpDailySumMList(MpDailySumMDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_daily_sum_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_daily_sum_m 총 갯수
	 * @exception
	 */
    int selectMpDailySumMListTotCnt(MpDailySumMDefaultVO searchVO);
    
}
