package nds.mpm.buy.PO0201.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.buy.PO0102.vo.MpPigdxmDefaultVO;
import nds.mpm.buy.PO0102.vo.MpPigdxmVO;
import nds.mpm.common.vo.ResultEx;

/**
 * @Class Name : MpPigdxmService.java
 * @Description : MpPigdxm Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PO0201MpPigdxmService {
	
    /**
	 * mp_pigdxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigdxm 목록
	 * @exception Exception
	 */
    List selectMpPigdxmList(MpPigdxmDefaultVO searchVO) throws Exception;
    
    public ResultEx insertMpPigdxm(List<EgovMap> vos) throws Exception ;
    
    public List selectMpPigdxmDetailList(MpPigdxmDefaultVO searchVO) throws Exception ;


}
