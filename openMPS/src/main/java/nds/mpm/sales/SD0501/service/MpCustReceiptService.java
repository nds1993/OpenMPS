package nds.mpm.sales.SD0501.service;

import java.util.List;
import nds.mpm.sales.SD0501.vo.MpCustReceiptDefaultVO;
import nds.mpm.sales.SD0501.vo.MpCustReceiptVO;

/**
 * @Class Name : MpCustReceiptService.java
 * @Description : MpCustReceipt Business class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpCustReceiptService {
	
	/**
	 * mp_cust_receipt을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustReceiptVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertMpCustReceipt(MpCustReceiptVO vo) throws Exception;
    
    /**
	 * mp_cust_receipt을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustReceiptVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpCustReceipt(MpCustReceiptVO vo) throws Exception;
    
    /**
	 * mp_cust_receipt을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustReceiptVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpCustReceipt(MpCustReceiptVO vo) throws Exception;
    
    /**
	 * mp_cust_receipt을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustReceiptVO
	 * @return 조회한 mp_cust_receipt
	 * @exception Exception
	 */
    MpCustReceiptVO selectMpCustReceipt(MpCustReceiptVO vo) throws Exception;
    
    /**
	 * mp_cust_receipt 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_receipt 목록
	 * @exception Exception
	 */
    List selectMpCustReceiptList(MpCustReceiptDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_cust_receipt 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_cust_receipt 총 갯수
	 * @exception
	 */
    int selectMpCustReceiptListTotCnt(MpCustReceiptDefaultVO searchVO);
    
}
