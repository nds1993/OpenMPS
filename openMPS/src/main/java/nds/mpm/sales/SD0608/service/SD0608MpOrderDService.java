package nds.mpm.sales.SD0608.service;

import java.util.List;

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
public interface SD0608MpOrderDService {
    /**
	 * mp_order_d 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    List selectMpOrderDList(EgovMap searchVO) throws Exception;
    
    /**
	 * mp_order_d 목록을 탭을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    List selectMpOrderDTab1List(EgovMap searchVO) throws Exception;
    
    /**
	 * mp_order_d 목록을 탭을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    List selectMpOrderDTab2List(EgovMap searchVO) throws Exception;
    
    /**
	 * mp_order_d 목록을 탭을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    List selectMpOrderDTab3List(EgovMap searchVO) throws Exception;
    
}
