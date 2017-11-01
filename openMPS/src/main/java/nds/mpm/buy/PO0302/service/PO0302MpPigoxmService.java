package nds.mpm.buy.PO0302.service;

import java.util.List;

import nds.mpm.buy.PO0302.vo.MpPigoxmVO;
import nds.mpm.common.vo.ResultEx;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : PO0302MpPigoxmService.java
 * @Description : PO0302MpPigoxmService Business class
 * @Modification Information
 *
 * @author 123
 * @since 123
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PO0302MpPigoxmService {
	
    /**
	 * mp_pigoxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 PO0302MpPigoxmVO
	 * @return 조회한 mp_pigoxm
	 * @exception Exception
	 */

	public List selectPO0302MpPigoxmList_tab1(MpPigoxmVO searchVO) throws Exception;

    public int selectPO0302MpPigoxmListTotCnt_tabl(MpPigoxmVO searchVO) ;

	public List selectPO0302MpPigoxmList_tab2(MpPigoxmVO searchVO) throws Exception;

    public int selectPO0302MpPigoxmListTotCnt_tab2(MpPigoxmVO searchVO) ;

    
}
