package nds.tmm.common.TMCOMT40.service;

import java.util.List;

import nds.tmm.common.TMCOMT40.vo.IfLogtxmDefaultVO;
import nds.tmm.common.TMCOMT40.vo.IfLogtxmVO;

/**
 * @Class Name : IfLogtxmService.java
 * @Description : IfLogtxm Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.08.14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface IfLogtxmService {
	
    /**
	 * if_logtxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 IfLogtxmVO
	 * @return 조회한 tm_mesgxm
	 * @exception Exception
	 */
    IfLogtxmVO selectIfLogtxm(IfLogtxmVO vo) throws Exception;
    
    /**
	 * if_logtxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return if_logtxm 목록
	 * @exception Exception
	 */
    List selectIfLogtxmList(IfLogtxmDefaultVO searchVO) throws Exception;
    
    /**
	 * if_logtxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return if_logtxm 총 갯수
	 * @exception
	 */
    int selectIfLogtxmListTotCnt(IfLogtxmDefaultVO searchVO);
    
}
