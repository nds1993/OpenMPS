package nds.mpm.prod.PP0902.service;

import java.util.List;
import nds.mpm.prod.PP0902.vo.MpDailySumLogMDefaultVO;
import nds.mpm.prod.PP0902.vo.MpDailySumLogMVO;

/**
 * @Class Name : MpDailySumLogMService.java
 * @Description : MpDailySumLogM Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpDailySumLogMService {
	
	/**
	 * mp_daily_sum_log_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDailySumLogMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpDailySumLogM(MpDailySumLogMVO vo) throws Exception;
    
    /**
	 * mp_daily_sum_log_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDailySumLogMVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpDailySumLogM(MpDailySumLogMVO vo) throws Exception;
    
    /**
	 * mp_daily_sum_log_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDailySumLogMVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpDailySumLogM(MpDailySumLogMVO vo) throws Exception;
    
    /**
	 * mp_daily_sum_log_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDailySumLogMVO
	 * @return 조회한 mp_daily_sum_log_m
	 * @exception Exception
	 */
    MpDailySumLogMVO selectMpDailySumLogM(MpDailySumLogMVO vo) throws Exception;
    
    /**
	 * mp_daily_sum_log_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_daily_sum_log_m 목록
	 * @exception Exception
	 */
    List selectMpDailySumLogMList(MpDailySumLogMDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_daily_sum_log_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_daily_sum_log_m 총 갯수
	 * @exception
	 */
    int selectMpDailySumLogMListTotCnt(MpDailySumLogMDefaultVO searchVO);
    
}
