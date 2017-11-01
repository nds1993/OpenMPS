package nds.mpm.prod.PP0501.service;

import java.util.List;
import nds.mpm.prod.PP0501.vo.MpReqOutMDefaultVO;
import nds.mpm.prod.PP0501.vo.MpReqOutMVO;

/**
 * @Class Name : MpReqOutMService.java
 * @Description : MpReqOutM Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PP0501MpReqOutMService {
	
    /**
	 * mp_req_out_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_req_out_m 목록
	 * @exception Exception
	 */
    List selectMpReqOutMList(MpReqOutMDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_req_out_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_req_out_m 총 갯수
	 * @exception
	 */
    int selectMpReqOutMListTotCnt(MpReqOutMDefaultVO searchVO);
    
}
