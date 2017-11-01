package nds.tmm.common.TMCOMT50.service;

import java.util.List;

import nds.tmm.common.TMCOMT50.vo.UdsLogDefaultVO;
import nds.tmm.common.TMCOMT50.vo.UdsLogVO;

/**
 * @Class Name : UdsLogService.java
 * @Description : UdsLogtxm Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.08.14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface UdsLogService {
	
    /**
	 * uds_log을 조회한다.
	 * @param vo - 조회할 정보가 담긴 UdsLogVO
	 * @return 조회한 uds_log
	 * @exception Exception
	 */
    UdsLogVO selectUdsLog(UdsLogVO vo) throws Exception;
    
    /**
	 * uds_log 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return uds_log 목록
	 * @exception Exception
	 */
    List selectUdsLogList(UdsLogDefaultVO searchVO) throws Exception;
    
    /**
	 * uds_log 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return uds_log 총 갯수
	 * @exception
	 */
    int selectUdsLogListTotCnt(UdsLogDefaultVO searchVO);
    
}
