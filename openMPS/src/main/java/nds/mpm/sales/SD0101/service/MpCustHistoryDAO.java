package nds.mpm.sales.SD0101.service;

import java.util.List;

import nds.mpm.sales.SD0101.vo.MpCustHistoryDefaultVO;
import nds.mpm.sales.SD0101.vo.MpCustHistoryVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpCustHistoryDAO.java
 * @Description : MpCustHistory DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpCustHistoryDAO")
public class MpCustHistoryDAO extends EgovAbstractDAO {

	/**
	 * mp_cust_history을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustHistoryVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpCustHistory(EgovMap vo) throws Exception {
        return (String)insert("mpCustHistoryDAO.insertMpCustHistory", vo);
    }

    /**
	 * mp_cust_history을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustHistoryVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpCustHistory(EgovMap vo) throws Exception {
        return update("mpCustHistoryDAO.updateMpCustHistory", vo);
    }

    /**
	 * mp_cust_history을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustHistoryVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpCustHistory(EgovMap vo) throws Exception {
    	return delete("mpCustHistoryDAO.deleteMpCustHistory", vo);
    }

    /**
	 * mp_cust_history을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustHistoryVO
	 * @return 조회한 mp_cust_history
	 * @exception Exception
	 */
    public MpCustHistoryVO selectMpCustHistory(MpCustHistoryVO vo) throws Exception {
        return (MpCustHistoryVO) select("mpCustHistoryDAO.selectMpCustHistory_S", vo);
    }

    /**
	 * mp_cust_history 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_history 목록
	 * @exception Exception
	 */
    public List<?> selectMpCustHistoryList(MpCustHistoryDefaultVO searchVO) throws Exception {
        return list("mpCustHistoryDAO.selectMpCustHistoryList_D", searchVO);
    }

    /**
	 * mp_cust_history 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_history 총 갯수
	 * @exception
	 */
    public int selectMpCustHistoryListTotCnt(MpCustHistoryDefaultVO searchVO) {
        return (Integer)select("mpCustHistoryDAO.selectMpCustHistoryListTotCnt_S", searchVO);
    }

}
