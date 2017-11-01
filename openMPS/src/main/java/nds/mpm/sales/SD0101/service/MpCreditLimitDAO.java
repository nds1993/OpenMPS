package nds.mpm.sales.SD0101.service;

import java.util.List;

import nds.mpm.sales.SD0101.vo.MpCreditLimitDefaultVO;
import nds.mpm.sales.SD0101.vo.MpCreditLimitVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpCreditLimitDAO.java
 * @Description : MpCreditLimit DAO Class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpCreditLimitDAO")
public class MpCreditLimitDAO extends EgovAbstractDAO {

	/**
	 * mp_credit_limit을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCreditLimitVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpCreditLimit(EgovMap vo) throws Exception {
        return (String)insert("mpCreditLimitDAO.insertMpCreditLimit", vo);
    }

    /**
	 * mp_credit_limit을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCreditLimitVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpCreditLimit(EgovMap vo) throws Exception {
       return update("mpCreditLimitDAO.updateMpCreditLimit", vo);
    }

    /**
	 * mp_credit_limit을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCreditLimitVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpCreditLimit(EgovMap vo) throws Exception {
    	return delete("mpCreditLimitDAO.deleteMpCreditLimit", vo);
    }

    /**
	 * mp_credit_limit을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCreditLimitVO
	 * @return 조회한 mp_credit_limit
	 * @exception Exception
	 */
    public MpCreditLimitVO selectMpCreditLimit(MpCreditLimitVO vo) throws Exception {
        return (MpCreditLimitVO) select("mpCreditLimitDAO.selectMpCreditLimit_S", vo);
    }

    /**
	 * mp_credit_limit 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_credit_limit 목록
	 * @exception Exception
	 */
    public List<?> selectMpCreditLimitList(MpCreditLimitDefaultVO searchVO) throws Exception {
        return list("mpCreditLimitDAO.selectMpCreditLimitList_D", searchVO);
    }

    /**
	 * mp_credit_limit 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_credit_limit 총 갯수
	 * @exception
	 */
    public int selectMpCreditLimitListTotCnt(MpCreditLimitDefaultVO searchVO) {
        return (Integer)select("mpCreditLimitDAO.selectMpCreditLimitListTotCnt_S", searchVO);
    }

    /**
     * 한도승인
     * 
     * **/
    public int updateApproMpCreditLimit(EgovMap vo) throws Exception {
        return update("mpCreditLimitDAO.updateApproMpCreditLimit", vo);
     }
}
