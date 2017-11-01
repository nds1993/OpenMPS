package nds.mpm.sales.SD1001.service;

import java.util.List;

import nds.mpm.sales.SD0405.vo.MpOrderHVO;

/**
 * @Class Name : MpDiscPriceService.java
 * @Description : MpDiscPrice Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface SD1001MpDiscPriceService {
    /**
	 * mp_disc_price 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_disc_price 총 갯수
	 * @exception
	 */
    public List<?> selectMpDiscPriceList(MpOrderHVO searchVO) throws Exception ;
    
    public int selectMpDiscPriceListTotCnt(MpOrderHVO searchVO) ;
    
    public List<?> selectMpOrderDetailList(MpOrderHVO searchVO) throws Exception ;

}
