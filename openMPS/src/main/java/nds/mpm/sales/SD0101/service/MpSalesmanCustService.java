package nds.mpm.sales.SD0101.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0101.vo.MpSalesmanCustDefaultVO;
import nds.mpm.sales.SD0101.vo.MpSalesmanCustVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpSalesmanCustService.java
 * @Description : MpSalesmanCust Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpSalesmanCustService {
	
	/**
	 * mp_salesman_cust을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpSalesmanCustVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpSalesmanCust(List<EgovMap> vos) throws Exception ;
    
    /**
	 * mp_salesman_cust을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpSalesmanCustVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpSalesmanCust(MpSalesmanCustVO vo) throws Exception;
    
    /**
	 * mp_salesman_cust을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpSalesmanCustVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpSalesmanCust(EgovMap vo) throws Exception;
    
    /**
	 * mp_salesman_cust을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpSalesmanCustVO
	 * @return 조회한 mp_salesman_cust
	 * @exception Exception
	 */
    MpSalesmanCustVO selectMpSalesmanCust(MpSalesmanCustVO vo) throws Exception;
    
    /**
	 * mp_salesman_cust 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_salesman_cust 목록
	 * @exception Exception
	 */
    List selectMpSalesmanCustList(MpSalesmanCustDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_salesman_cust 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_salesman_cust 총 갯수
	 * @exception
	 */
    int selectMpSalesmanCustListTotCnt(MpSalesmanCustDefaultVO searchVO);
    
}
