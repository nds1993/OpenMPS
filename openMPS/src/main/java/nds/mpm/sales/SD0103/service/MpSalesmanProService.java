package nds.mpm.sales.SD0103.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0103.vo.MpSalesmanProDefaultVO;
import nds.mpm.sales.SD0103.vo.MpSalesmanProVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpSalesmanProService.java
 * @Description : MpSalesmanPro Business class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpSalesmanProService {
	
	/**
	 * mp_salesman_pro을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpSalesmanProVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertMpSalesmanPro(List<EgovMap> vo) throws Exception;
    
    /**
	 * mp_salesman_pro을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpSalesmanProVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpSalesmanPro(MpSalesmanProVO vo) throws Exception;
    
    /**
	 * mp_salesman_pro을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpSalesmanProVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpSalesmanPro(MpSalesmanProVO vo) throws Exception;
    
    /**
	 * mp_salesman_pro을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpSalesmanProVO
	 * @return 조회한 mp_salesman_pro
	 * @exception Exception
	 */
    MpSalesmanProVO selectMpSalesmanPro(EgovMap vo) throws Exception;
    
    /**
	 * mp_salesman_pro 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_salesman_pro 목록
	 * @exception Exception
	 */
    List selectMpSalesmanProList(MpSalesmanProDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_salesman_pro 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_salesman_pro 총 갯수
	 * @exception
	 */
    int selectMpSalesmanProListTotCnt(MpSalesmanProDefaultVO searchVO);
    
}
