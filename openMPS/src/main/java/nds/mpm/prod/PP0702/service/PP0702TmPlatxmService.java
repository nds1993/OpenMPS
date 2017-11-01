package nds.mpm.prod.PP0702.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.prod.PP0101.vo.TmPlatxmDefaultVO;
import nds.mpm.prod.PP0101.vo.TmPlatxmVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TmPlatxmService.java
 * @Description : TmPlatxm Business class
 * @Modification Information
 *
 * @author MPM
 * @since 2017
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PP0702TmPlatxmService {
    /**
	 * tm_platxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_platxm 목록
	 * @exception Exception
	 */
    List selectTmPlatxmList(TmPlatxmDefaultVO searchVO) throws Exception;
    
    /**
	 * tm_platxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_platxm 총 갯수
	 * @exception
	 */
    int selectTmPlatxmListTotCnt(TmPlatxmDefaultVO searchVO);

}
