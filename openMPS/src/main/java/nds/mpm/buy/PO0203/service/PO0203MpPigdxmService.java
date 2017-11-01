package nds.mpm.buy.PO0203.service;

import java.util.List;

import nds.mpm.buy.PO0102.vo.MpPigdxmDefaultVO;
import nds.mpm.buy.PO0102.vo.MpPigdxmVO;

/**
 * @Class Name : MpPigdxmService.java
 * @Description : MpPigdxm Business class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PO0203MpPigdxmService {
	
    /**
	 * mp_pigdxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigdxm 목록
	 * @exception Exception
	 */
    List selectMpPigdxmList(MpPigdxmDefaultVO searchVO) throws Exception;
    
    public List selectMpPigdxmListGroupDoch(MpPigdxmDefaultVO searchVO) throws Exception ;

    
    /**
	 * mp_pigdxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigdxm 총 갯수
	 * @exception
	 */
    int selectMpPigdxmListTotCnt(MpPigdxmDefaultVO searchVO);
    
}
