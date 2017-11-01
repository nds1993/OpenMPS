package nds.mpm.buy.PO0205.service;

import java.util.List;

import nds.mpm.buy.PO0102.vo.MpPigdxmDefaultVO;
import nds.mpm.common.vo.ResultEx;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPigdxmService.java
 * @Description : MpPigdxm Business class
 * @Modification Information
 *
 * @author 더느림지급액조회
 * @since 더느림지급액조회
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PO0205MpPigdxmService {
	
    /**
	 * mp_pigdxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigdxmVO
	 * @return void형
	 * @exception Exception
	 */
	ResultEx updateMpPigdxm(List<EgovMap> vo) throws Exception;
    
    /**
	 * mp_pigdxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigdxm 목록
	 * @exception Exception
	 */
    List selectMpPigdxmList(MpPigdxmDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_pigdxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigdxm 총 갯수
	 * @exception
	 */
    int selectMpPigdxmListTotCnt(MpPigdxmDefaultVO searchVO);
    
}
