package nds.mpm.sales.SD0802.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0802.vo.MpDelvLimitDefaultVO;
import nds.mpm.sales.SD0802.vo.MpDelvLimitVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpDelvLimitService.java
 * @Description : MpDelvLimit Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpDelvLimitService {
	
	/**
	 * mp_delv_limit을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDelvLimitVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertMpDelvLimit(List<EgovMap> vo) throws Exception;
    
    /**
	 * mp_delv_limit을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDelvLimitVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpDelvLimit(MpDelvLimitVO vo) throws Exception;
    
    /**
	 * mp_delv_limit을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDelvLimitVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpDelvLimit(MpDelvLimitVO vo) throws Exception;
    
    /**
	 * mp_delv_limit을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDelvLimitVO
	 * @return 조회한 mp_delv_limit
	 * @exception Exception
	 */
    MpDelvLimitVO selectMpDelvLimit(MpDelvLimitVO vo) throws Exception;
    
    /**
	 * mp_delv_limit 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_delv_limit 목록
	 * @exception Exception
	 */
    List selectMpDelvLimitList(MpDelvLimitDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_delv_limit 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_delv_limit 총 갯수
	 * @exception
	 */
    int selectMpDelvLimitListTotCnt(MpDelvLimitDefaultVO searchVO);
    
}
