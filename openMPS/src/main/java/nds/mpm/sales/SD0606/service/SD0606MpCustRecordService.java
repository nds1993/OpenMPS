package nds.mpm.sales.SD0606.service;

import java.util.List;

import nds.mpm.sales.SD0606.vo.MpCustRecordVO;

/**
 * @Class Name : MpCustRecordService.java
 * @Description : MpCustRecord Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017. 9. 20.  JAR        수정
 *  Copyright (C)  All right reserved.
 */
public interface SD0606MpCustRecordService {
    /**
	 * mp_cust_record 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_record 목록
	 * @exception Exception
	 */
    List selectMpCustRecordList(MpCustRecordVO searchVO) throws Exception;
    
    List selectChartMpCustRecordList(MpCustRecordVO searchVO) throws Exception;

}
