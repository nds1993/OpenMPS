package nds.mpm.sales.SD0101.service;

import java.util.List;
import nds.mpm.sales.SD0101.vo.MpCustHistoryDefaultVO;
import nds.mpm.sales.SD0101.vo.MpCustHistoryVO;

/**
 * @Class Name : MpCustHistoryService.java
 * @Description : MpCustHistory Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpCustHistoryService {
	
	/**
	 * mp_cust_history을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustHistoryVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpCustHistory(MpCustHistoryVO vo) throws Exception;
    
    /**
	 * mp_cust_history을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustHistoryVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpCustHistory(MpCustHistoryVO vo) throws Exception;
    
    /**
	 * mp_cust_history을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustHistoryVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpCustHistory(MpCustHistoryVO vo) throws Exception;
    
    /**
	 * mp_cust_history을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustHistoryVO
	 * @return 조회한 mp_cust_history
	 * @exception Exception
	 */
    MpCustHistoryVO selectMpCustHistory(MpCustHistoryVO vo) throws Exception;
    
    /**
	 * mp_cust_history 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_history 목록
	 * @exception Exception
	 */
    List selectMpCustHistoryList(MpCustHistoryDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_cust_history 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_history 총 갯수
	 * @exception
	 */
    int selectMpCustHistoryListTotCnt(MpCustHistoryDefaultVO searchVO);
    
}
