package nds.mpm.sales.SD0101.service;

import java.util.List;
import nds.mpm.sales.SD0101.vo.MpCustProLimitDefaultVO;
import nds.mpm.sales.SD0101.vo.MpCustProLimitVO;

/**
 * @Class Name : MpCustProLimitService.java
 * @Description : MpCustProLimit Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpCustProLimitService {
	
	/**
	 * mp_cust_pro_limit을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustProLimitVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpCustProLimit(MpCustProLimitVO vo) throws Exception;
    
    /**
	 * mp_cust_pro_limit을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustProLimitVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpCustProLimit(MpCustProLimitVO vo) throws Exception;
    
    /**
	 * mp_cust_pro_limit을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustProLimitVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpCustProLimit(MpCustProLimitVO vo) throws Exception;
    
    /**
	 * mp_cust_pro_limit을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustProLimitVO
	 * @return 조회한 mp_cust_pro_limit
	 * @exception Exception
	 */
    MpCustProLimitVO selectMpCustProLimit(MpCustProLimitVO vo) throws Exception;
    
    /**
	 * mp_cust_pro_limit 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_pro_limit 목록
	 * @exception Exception
	 */
    List selectMpCustProLimitList(MpCustProLimitDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_cust_pro_limit 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_pro_limit 총 갯수
	 * @exception
	 */
    int selectMpCustProLimitListTotCnt(MpCustProLimitDefaultVO searchVO);
    
}
