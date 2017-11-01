package nds.mpm.sales.SD0501.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.sales.SD0501.vo.MpCustReceiptVO;
import nds.mpm.sales.SD0501.vo.MpCustReceiptDefaultVO;

/**
 * @Class Name : MpCustReceiptDAO.java
 * @Description : MpCustReceipt DAO Class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpCustReceiptDAO")
public class MpCustReceiptDAO extends EgovAbstractDAO {

	/**
	 * mp_cust_receipt을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustReceiptVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpCustReceipt(MpCustReceiptVO vo) throws Exception {
        return (String)insert("mpCustReceiptDAO.insertMpCustReceipt", vo);
    }
    
    public String insertUpdateMpCustReceipt(MpCustReceiptVO vo) throws Exception {
        return (String)insert("mpCustReceiptDAO.insertMpCustReceipt", vo);
    }

    /**
	 * mp_cust_receipt을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustReceiptVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpCustReceipt(MpCustReceiptVO vo) throws Exception {
        return update("mpCustReceiptDAO.updateMpCustReceipt", vo);
    }
    
    public int selectMpCustReceiptMonthSum(MpCustReceiptVO vo) throws Exception {
       return (Integer)select("mpCustReceiptDAO.selectMpCustReceiptMonthSum", vo);
    }

    /**
	 * mp_cust_receipt을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustReceiptVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpCustReceipt(MpCustReceiptVO vo) throws Exception {
    	return delete("mpCustReceiptDAO.deleteMpCustReceipt", vo);
    }

    /**
	 * mp_cust_receipt을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustReceiptVO
	 * @return 조회한 mp_cust_receipt
	 * @exception Exception
	 */
    public MpCustReceiptVO selectMpCustReceipt(MpCustReceiptVO vo) throws Exception {
        return (MpCustReceiptVO) select("mpCustReceiptDAO.selectMpCustReceipt_S", vo);
    }

    /**
	 * mp_cust_receipt 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_receipt 목록
	 * @exception Exception
	 */
    public List<?> selectMpCustReceiptList(MpCustReceiptDefaultVO searchVO) throws Exception {
        return list("mpCustReceiptDAO.selectMpCustReceiptList_D", searchVO);
    }

    /**
	 * mp_cust_receipt 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_receipt 총 갯수
	 * @exception
	 */
    public int selectMpCustReceiptListTotCnt(MpCustReceiptDefaultVO searchVO) {
        return (Integer)select("mpCustReceiptDAO.selectMpCustReceiptListTotCnt_S", searchVO);
    }

}
