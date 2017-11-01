package nds.mpm.sales.SD0803.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0405.vo.MpOrderHVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpOrderDService.java
 * @Description : MpOrderD Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface SD0803MpOrderDService {
	
    public ResultEx insertMpOrderH(MpOrderHVO mpOrderHVO) throws Exception ;

    /**
	 * mp_order_d 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    List selectMpOrderDList(MpOrderHVO searchVO) throws Exception;
    
    /**
	 * mp_order_d 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 총 갯수
	 * @exception
	 */
    int selectMpOrderDListTotCnt(MpOrderHVO searchVO);
    
}
