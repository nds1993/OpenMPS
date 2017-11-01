package nds.tmm.common.TMCOMT60.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.tmm.common.TMCOMT60.vo.UdsMsgDefaultVO;
import nds.tmm.common.TMCOMT60.vo.UdsMsgVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : UdsMsgService.java
 * @Description : UdsMsg Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.08.14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface UdsMsgService {
	
	/**
	 * udS_msg을 등록한다.
	 * @param vo - 등록할 정보가 담긴 UdsMsgVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertUdsMsg(List<EgovMap> vo) throws Exception;

    /**
	 * udS_msg을 조회한다.
	 * @param vo - 조회할 정보가 담긴 UdsMsgVO
	 * @return 조회한 udS_msg
	 * @exception Exception
	 */
    UdsMsgVO selectUdsMsg(EgovMap vo) throws Exception;
    
    /**
	 * udS_msg 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return udS_msg 목록
	 * @exception Exception
	 */
    List selectUdsMsgList(UdsMsgDefaultVO searchVO) throws Exception;
    
    /**
	 * uds_msg 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return uds_msg 총 갯수
	 * @exception
	 */
    int selectUdsMsgListTotCnt(UdsMsgDefaultVO searchVO);
    
}
