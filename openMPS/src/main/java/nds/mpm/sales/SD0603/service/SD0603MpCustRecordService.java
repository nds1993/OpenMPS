package nds.mpm.sales.SD0603.service;

import java.util.List;

import nds.mpm.sales.SD0604.vo.MpCustRecordDefaultVO;
import nds.mpm.sales.SD0604.vo.MpCustRecordVO;

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
public interface SD0603MpCustRecordService {
    /**
	 * mp_cust_record 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_record 목록
	 * @exception Exception
	 */
    List selectMpCustRecordList(MpCustRecordDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_cust_record 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_record 총 갯수
	 * @exception
	 */
    int selectMpCustRecordListTotCnt(MpCustRecordDefaultVO searchVO);
    public List<?> selectDetailTab1MpCustRecordList(MpCustRecordDefaultVO searchVO) throws Exception ;

    public List<?> selectDetailTab2MpCustRecordList(MpCustRecordDefaultVO searchVO) throws Exception ;

    
}
