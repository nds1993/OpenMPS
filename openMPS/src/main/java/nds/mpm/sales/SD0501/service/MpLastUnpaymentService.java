package nds.mpm.sales.SD0501.service;

import java.util.List;
import nds.mpm.sales.SD0501.vo.MpLastUnpaymentDefaultVO;
import nds.mpm.sales.SD0501.vo.MpLastUnpaymentVO;

/**
 * @Class Name : MpLastUnpaymentService.java
 * @Description : MpLastUnpayment Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpLastUnpaymentService {
	
	/**
	 * mp_last_unpayment을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpLastUnpaymentVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpLastUnpayment(MpLastUnpaymentVO vo) throws Exception;
    
    /**
	 * mp_last_unpayment을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpLastUnpaymentVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpLastUnpayment(MpLastUnpaymentVO vo) throws Exception;
    
    /**
	 * mp_last_unpayment을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpLastUnpaymentVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpLastUnpayment(MpLastUnpaymentVO vo) throws Exception;
    
    /**
	 * mp_last_unpayment을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpLastUnpaymentVO
	 * @return 조회한 mp_last_unpayment
	 * @exception Exception
	 */
    MpLastUnpaymentVO selectMpLastUnpayment(MpLastUnpaymentVO vo) throws Exception;
    
    /**
	 * mp_last_unpayment 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_last_unpayment 목록
	 * @exception Exception
	 */
    List selectMpLastUnpaymentList(MpLastUnpaymentDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_last_unpayment 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_last_unpayment 총 갯수
	 * @exception
	 */
    int selectMpLastUnpaymentListTotCnt(MpLastUnpaymentDefaultVO searchVO);
    
}
