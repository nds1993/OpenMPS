package nds.mpm.prod.PP0202.service;

import java.util.List;
import nds.mpm.prod.PP0201.vo.MpYieldInfoMDefaultVO;
import nds.mpm.prod.PP0201.vo.MpYieldInfoMVO;

/**
 * @Class Name : MpYieldInfoMService.java
 * @Description : MpYieldInfoM Business class
 * @Modification Information
 *
 * @author 22222
 * @since 22222
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PP0202MpYieldInfoMService {
	
	/**
	 * mp_yield_info_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpYieldInfoMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpYieldInfoM(MpYieldInfoMVO vo) throws Exception;
    
    /**
	 * mp_yield_info_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpYieldInfoMVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpYieldInfoM(MpYieldInfoMVO vo) throws Exception;
    
    /**
	 * mp_yield_info_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpYieldInfoMVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpYieldInfoM(MpYieldInfoMVO vo) throws Exception;
    
    /**
	 * mp_yield_info_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpYieldInfoMVO
	 * @return 조회한 mp_yield_info_m
	 * @exception Exception
	 */
    MpYieldInfoMVO selectMpYieldInfoM(MpYieldInfoMVO vo) throws Exception;
    
    /**
	 * mp_yield_info_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_yield_info_m 목록
	 * @exception Exception
	 */
    List selectMpYieldInfoMCmTopList(MpYieldInfoMDefaultVO searchVO) throws Exception;
    /**
	 * mp_yield_info_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_yield_info_m 목록
	 * @exception Exception
	 */
    List selectMpYieldInfoMCmLDetailList(MpYieldInfoMDefaultVO searchVO) throws Exception;
    /**
	 * mp_yield_info_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_yield_info_m 목록
	 * @exception Exception
	 */
    List selectMpYieldInfoMCmSDetailList(MpYieldInfoMDefaultVO searchVO) throws Exception;
    /**
	 * mp_yield_info_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_yield_info_m 총 갯수
	 * @exception
	 */
    int selectMpYieldInfoMListTotCnt(MpYieldInfoMDefaultVO searchVO);
    
    /**
	 * mp_yield_info_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_yield_info_m 목록
	 * @exception Exception
	 */
    List selectMpYieldInfoMPmList(MpYieldInfoMDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_yield_info_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_yield_info_m 총 갯수
	 * @exception
	 */
    int selectMpYieldInfoMPmListTotCnt(MpYieldInfoMDefaultVO searchVO);
    
}
