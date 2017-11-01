package nds.mpm.sales.SD9003.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0205.vo.MpDiscPriceDefaultVO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

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
public interface SD9003MpDiscPriceService {
    /**
	 * mp_disc_price 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_disc_price 총 갯수
	 * @exception
	 */
    public List<?> selectMpDiscPriceList(MpDiscPriceDefaultVO searchVO) throws Exception ;
    
    public int selectMpDiscPriceListTotCnt(MpDiscPriceDefaultVO searchVO) ;
    
}
