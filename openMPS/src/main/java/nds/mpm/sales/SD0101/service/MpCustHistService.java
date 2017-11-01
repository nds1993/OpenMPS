package nds.mpm.sales.SD0101.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0101.vo.MpCustHistDefaultVO;
import nds.mpm.sales.SD0101.vo.MpCustHistVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpCustHistService.java
 * @Description : MpCustHist Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpCustHistService {
	
	/**
	 * mp_cust_hist을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustHistVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertMpCustHist(EgovMap vo) throws Exception;
    
    /**
	 * mp_cust_hist을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustHistVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpCustHist(MpCustHistVO vo) throws Exception;
    
    /**
	 * mp_cust_hist을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustHistVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpCustHist(MpCustHistVO vo) throws Exception;
    
    /**
	 * mp_cust_hist을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustHistVO
	 * @return 조회한 mp_cust_hist
	 * @exception Exception
	 */
    MpCustHistVO selectMpCustHist(MpCustHistVO vo) throws Exception;
    
    /**
	 * mp_cust_hist 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_hist 목록
	 * @exception Exception
	 */
    List selectMpCustHistList(MpCustHistDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_cust_hist 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_hist 총 갯수
	 * @exception
	 */
    int selectMpCustHistListTotCnt(MpCustHistDefaultVO searchVO);
    
}
