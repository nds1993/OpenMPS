package nds.mpm.sales.SD0804.service;

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
public interface SD0804MpOrderDService {
	
    public ResultEx insertMpOrderH(MpOrderHVO mpOrderHVO, List<EgovMap> mpOrderDVOs) throws Exception ;
    
}
