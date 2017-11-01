package nds.mpm.sales.SD0103.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0103.vo.MpCustProDefaultVO;
import nds.mpm.sales.SD0103.vo.MpCustProVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpCustProService.java
 * @Description : MpCustPro Business class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpCustProService {
	
	/**
	 * mp_cust_pro을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustProVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertMpCustPro(List<EgovMap> vo) throws Exception;
    
    /**
	 * mp_cust_pro을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustProVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpCustPro(MpCustProVO vo) throws Exception;
    
    /**
	 * mp_cust_pro을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustProVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpCustPro(MpCustProVO vo) throws Exception;
    
    /**
	 * mp_cust_pro을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustProVO
	 * @return 조회한 mp_cust_pro
	 * @exception Exception
	 */
    MpCustProVO selectMpCustPro(EgovMap vo) throws Exception;
    
    /**
	 * mp_cust_pro 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_pro 목록
	 * @exception Exception
	 */
    List selectMpCustProList(MpCustProDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_cust_pro 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_pro 총 갯수
	 * @exception
	 */
    int selectMpCustProListTotCnt(MpCustProDefaultVO searchVO);
    
}
