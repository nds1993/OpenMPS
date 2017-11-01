package nds.mpm.sales.SD0101.service;

import java.util.List;

import nds.mpm.sales.SD0101.vo.MpCustHistDefaultVO;
import nds.mpm.sales.SD0101.vo.MpCustHistVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpCustHistDAO.java
 * @Description : MpCustHist DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpCustHistDAO")
public class MpCustHistDAO extends EgovAbstractDAO {

	/**
	 * mp_cust_hist을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustHistVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public int insertMpCustHist(EgovMap vo) throws Exception {
        return (Integer)insert("mpCustHistDAO.insertMpCustHist", vo);
    }

    /**
	 * mp_cust_hist을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustHistVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpCustHist(EgovMap vo) throws Exception {
        return update("mpCustHistDAO.updateMpCustHist", vo);
    }

    /**
	 * mp_cust_hist을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustHistVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpCustHist(EgovMap vo) throws Exception {
    	return delete("mpCustHistDAO.deleteMpCustHist", vo);
    }

    /**
	 * mp_cust_hist을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustHistVO
	 * @return 조회한 mp_cust_hist
	 * @exception Exception
	 */
    public MpCustHistVO selectMpCustHist(MpCustHistVO vo) throws Exception {
        return (MpCustHistVO) select("mpCustHistDAO.selectMpCustHist_S", vo);
    }

    /**
	 * mp_cust_hist 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_hist 목록
	 * @exception Exception
	 */
    public List<?> selectMpCustHistList(MpCustHistDefaultVO searchVO) throws Exception {
        return list("mpCustHistDAO.selectMpCustHistList_D", searchVO);
    }

    /**
	 * mp_cust_hist 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_hist 총 갯수
	 * @exception
	 */
    public int selectMpCustHistListTotCnt(MpCustHistDefaultVO searchVO) {
        return (Integer)select("mpCustHistDAO.selectMpCustHistListTotCnt_S", searchVO);
    }

}
