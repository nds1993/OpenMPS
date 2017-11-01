package nds.mpm.sales.SD0101.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0101.vo.MpCreditLimitDefaultVO;
import nds.mpm.sales.SD0101.vo.MpCreditLimitVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpCreditLimitService.java
 * @Description : MpCreditLimit Business class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpCreditLimitService {
	
	/**
	 * mp_credit_limit을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCreditLimitVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertMpCreditLimit(EgovMap vo) throws Exception;
    
    /**
	 * mp_credit_limit을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCreditLimitVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpCreditLimit(MpCreditLimitVO vo) throws Exception;
    
    /**
	 * mp_credit_limit을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCreditLimitVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpCreditLimit(MpCreditLimitVO vo) throws Exception;
    
    /**
	 * mp_credit_limit을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCreditLimitVO
	 * @return 조회한 mp_credit_limit
	 * @exception Exception
	 */
    MpCreditLimitVO selectMpCreditLimit(MpCreditLimitVO vo) throws Exception;
    
    /**
	 * mp_credit_limit 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_credit_limit 목록
	 * @exception Exception
	 */
    List selectMpCreditLimitList(MpCreditLimitDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_credit_limit 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_credit_limit 총 갯수
	 * @exception
	 */
    int selectMpCreditLimitListTotCnt(MpCreditLimitDefaultVO searchVO);
    
    public ResultEx updateApproMpCreditLimit(List<EgovMap> vos) throws Exception ;

}
