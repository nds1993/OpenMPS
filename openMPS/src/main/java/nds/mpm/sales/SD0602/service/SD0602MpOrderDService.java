package nds.mpm.sales.SD0602.service;

import java.util.List;

import nds.mpm.sales.SD0602.vo.SD0602MpOrderDDefaultVO;
import nds.mpm.sales.SD0602.vo.SD0602MpOrderDVO;

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
public interface SD0602MpOrderDService {
    /**
	 * mp_order_d 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    List selectSD0602MpOrderList_1(SD0602MpOrderDVO searchVO) throws Exception;
    
    List selectSD0602MpOrderList_2(SD0602MpOrderDVO searchVO) throws Exception;
    
    List selectSD0602MpOrderList_3(SD0602MpOrderDVO searchVO) throws Exception;
    
    List selectSD0602MpOrderList_4(SD0602MpOrderDVO searchVO) throws Exception;
    
    List selectSD0602MpOrderList_5(SD0602MpOrderDVO searchVO) throws Exception;
    

    
}
