package nds.mpm.sales.SD0505.service;

import java.util.List;

import nds.mpm.sales.SD0505.vo.MpOrderHDefaultVO;
import nds.mpm.sales.SD0505.vo.MpOrderHVO;

/**
 * @Class Name : MpOrderHService.java
 * @Description : MpOrderH Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface SD0505MpOrderHService {

	/**
	 * mp_order_h 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_h 목록
	 * @exception Exception
	 */
    List selectMpOrderHList(MpOrderHDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_order_h 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_h 총 갯수
	 * @exception
	 */
    int selectMpOrderHListTotCnt(MpOrderHDefaultVO searchVO);
    
    public List<?> selectDetailMpOrderHList(MpOrderHDefaultVO searchVO) throws Exception ;

}
