package nds.mpm.sales.SD9011.service;

import java.util.List;

import nds.mpm.common.vo.SearchCommonVO;

/**
 * @Class Name : MpStndPriceService.java
 * @Description : MpStndPrice Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface SD9011MpStndPriceService {
	
    /**
	 * mp_stnd_price 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_stnd_price 목록
	 * @exception Exception
	 */
    List selectMpStndPriceList(SearchCommonVO searchVO) throws Exception;
    
    /**
	 * mp_stnd_price 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_stnd_price 총 갯수
	 * @exception
	 */
    int selectMpStndPriceListTotCnt(SearchCommonVO searchVO);
    
    public List<?> selectProdMpStndPriceList(SearchCommonVO searchVO) throws Exception ;

}
