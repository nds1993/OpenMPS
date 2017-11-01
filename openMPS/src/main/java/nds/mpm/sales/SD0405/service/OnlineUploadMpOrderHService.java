package nds.mpm.sales.SD0405.service;

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
public interface OnlineUploadMpOrderHService {
	
	/**
	 * mp_order_h을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpOrderHVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertMpOrderHExcel(List<EgovMap> vos,MpOrderDVO mpOrderDVO) throws Exception;
    public ResultEx deleteMpOrderH(List<EgovMap> vos) throws Exception ;

}
