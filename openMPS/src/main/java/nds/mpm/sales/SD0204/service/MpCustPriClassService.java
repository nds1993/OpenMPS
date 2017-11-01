package nds.mpm.sales.SD0204.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0204.vo.MpCustPriClassDefaultVO;
import nds.mpm.sales.SD0204.vo.MpCustPriClassVO;

/**
 * @Class Name : MpCustPriClassService.java
 * @Description : MpCustPriClass Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpCustPriClassService {
	
	/**
	 * mp_cust_pri_class을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustPriClassVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpCustPriClass(List<EgovMap> vos) throws Exception ;
    
    /**
	 * mp_cust_pri_class을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustPriClassVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpCustPriClass(MpCustPriClassVO vo) throws Exception;
    
    /**
	 * mp_cust_pri_class을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustPriClassVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpCustPriClass(MpCustPriClassVO vo) throws Exception;
    
    /**
	 * mp_cust_pri_class을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustPriClassVO
	 * @return 조회한 mp_cust_pri_class
	 * @exception Exception
	 */
    MpCustPriClassVO selectMpCustPriClass(MpCustPriClassVO vo) throws Exception;
    
    /**
	 * mp_cust_pri_class 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_pri_class 목록
	 * @exception Exception
	 */
    public List<?> selectMpSalesmanCustList(MpCustPriClassDefaultVO searchVO) throws Exception ;

    /**
	 * mp_cust_pri_class 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_pri_class 총 갯수
	 * @exception
	 */
    public int selectMpSalesmanCustListTotCnt(MpCustPriClassDefaultVO searchVO) ;
    
    public List<?> selectMpCustPriClassList(MpCustPriClassDefaultVO searchVO) throws Exception ;
    
}
