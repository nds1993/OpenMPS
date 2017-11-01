package nds.mpm.prod.PP0701.service;

import java.util.List;

import nds.mpm.prod.PP0701.vo.OdOderhdVO;


/**
 * @Class Name : OdOderhdService.java
 * @Description : OdOderhd Business class
 * @Modification Information
 *
 * @author  
 * @since  
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface PP0701OdOderhdService {
    /**
	 * od_oderhd 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return od_oderhd 목록
	 * @exception Exception
	 */
    List selectOdOderhdList(OdOderhdVO searchVO) throws Exception;
    
    /**
	 * od_oderhd 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return od_oderhd 총 갯수
	 * @exception
	 */
    int selectOdOderhdListTotCnt(OdOderhdVO searchVO);
    
}
