package nds.mpm.sales.SD0404.service;

import java.util.List;
import java.util.Map;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0405.vo.MpOrderDVO;
import nds.mpm.sales.SD0405.vo.MpOrderHDefaultVO;
import nds.mpm.sales.SD0405.vo.MpOrderHVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

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
public interface SD0404Service {
	
    public ResultEx insertMpOrderHExcel(List<EgovMap> vos, MpOrderDVO mpOrderDVO) throws Exception ;

	
	/**
	 * mp_order_h을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpOrderHVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	
	List selectSD0404List(MpOrderHDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_order_h 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_h 총 갯수
	 * @exception
	 */
    int selectSD0404ListTotCnt(MpOrderHDefaultVO searchVO);
    
}
