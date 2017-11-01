package nds.mpm.sales.SD0101.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0101.vo.MpDamboInfoDefaultVO;
import nds.mpm.sales.SD0101.vo.MpDamboInfoVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpDamboInfoService.java
 * @Description : MpDamboInfo Business class
 * @Modification Information
 *
 * @author b
 * @since b
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpDamboInfoService {
	
	/**
	 * mp_dambo_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDamboInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertMpDamboInfo(EgovMap vo) throws Exception;
    
    /**
	 * mp_dambo_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDamboInfoVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpDamboInfo(EgovMap vo) throws Exception;
    
    /**
	 * mp_dambo_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDamboInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpDamboInfo(MpDamboInfoVO vo) throws Exception;
    
    /**
	 * mp_dambo_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDamboInfoVO
	 * @return 조회한 mp_dambo_info
	 * @exception Exception
	 */
    MpDamboInfoVO selectMpDamboInfo(MpDamboInfoVO vo) throws Exception;
    
    /**
	 * mp_dambo_info 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_dambo_info 목록
	 * @exception Exception
	 */
    List selectMpDamboInfoList(MpDamboInfoDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_dambo_info 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_dambo_info 총 갯수
	 * @exception
	 */
    int selectMpDamboInfoListTotCnt(MpDamboInfoDefaultVO searchVO);
    
}
