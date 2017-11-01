package nds.mpm.prod.PP0203.service;

import java.util.List;
import nds.mpm.prod.PP0201.vo.MpYieldInfoMDefaultVO;
import nds.mpm.prod.PP0201.vo.MpYieldInfoMVO;

/**
 * @Class Name : MpYieldInfoMService.java
 * @Description : MpYieldInfoM Business class
 * @Modification Information
 *
 * @author 3333333
 * @since 3333333
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PP0203MpYieldInfoMService {
	
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
    List selectMpYieldInfoMTopList(MpYieldInfoMVO searchVO) throws Exception;
    
    /**
	 * mp_yield_info_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_yield_info_m 목록
	 * @exception Exception
	 */
    List selectMpYieldInfoMLDetailList(MpYieldInfoMVO searchVO) throws Exception;
    
    /**
	 * mp_yield_info_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_yield_info_m 목록
	 * @exception Exception
	 */
    List selectMpYieldInfoMSDetailList(MpYieldInfoMVO searchVO) throws Exception;
    
    /**
	 * mp_yield_info_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_yield_info_m 총 갯수
	 * @exception
	 */
    int selectMpYieldInfoMListTotCnt(MpYieldInfoMVO searchVO);
    
}
