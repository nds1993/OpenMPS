package nds.mpm.sales.SD0601.service;

import java.util.List;

import nds.mpm.sales.SD0601.vo.MpOrderDVO;

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
public interface SD0601MpOrderDService {

	/**
	 * mp_order_d 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
	public List selectMpOrderDList(MpOrderDVO searchVO) throws Exception;

	/**
	 * mp_order_d 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 총 갯수
	 * @exception
	 */
	public int selectMpOrderDListTotCnt(MpOrderDVO searchVO);
    
}
