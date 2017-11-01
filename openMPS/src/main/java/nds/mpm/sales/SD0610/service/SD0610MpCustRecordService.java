package nds.mpm.sales.SD0610.service;

import java.util.List;

import nds.mpm.sales.SD0610.vo.MpCustRecordDefaultVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;


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
 *  Copyright (C)  All right reserved.
 */
public interface SD0610MpCustRecordService {
    /**
	 * mp_cust_record 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_record 목록
	 * @exception Exception
	 */
    List selectMpCustRecordList(EgovMap searchVO) throws Exception;
    
    /**
	 * mp_cust_record 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_record 총 갯수
	 * @exception
	 */
    int selectMpCustRecordListTotCnt(EgovMap searchVO);
    
    public List<?> selectDetailMpCustRecordList(EgovMap searchVO) throws Exception ;

    
}
