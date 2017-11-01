package nds.mpm.sales.SD0101.service;

import java.util.List;

import nds.mpm.sales.SD0101.vo.MpSalesmanCustDefaultVO;
import nds.mpm.sales.SD0101.vo.MpSalesmanCustVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpSalesmanCustDAO.java
 * @Description : MpSalesmanCust DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpSalesmanCustDAO")
public class MpSalesmanCustDAO extends EgovAbstractDAO {

	/**
	 * mp_salesman_cust을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpSalesmanCustVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpSalesmanCust(EgovMap vo) throws Exception {
        return (String)insert("mpSalesmanCustDAO.insertMpSalesmanCust", vo);
    }

    /**
	 * mp_salesman_cust을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpSalesmanCustVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpSalesmanCust(EgovMap vo) throws Exception {
        update("mpSalesmanCustDAO.updateMpSalesmanCust", vo);
    }

    /**
	 * mp_salesman_cust을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpSalesmanCustVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpSalesmanCust(EgovMap vo) throws Exception {
        return delete("mpSalesmanCustDAO.deleteMpSalesmanCust", vo);
    }

    /**
	 * mp_salesman_cust을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpSalesmanCustVO
	 * @return 조회한 mp_salesman_cust
	 * @exception Exception
	 */
    public MpSalesmanCustVO selectMpSalesmanCust(MpSalesmanCustVO vo) throws Exception {
        return (MpSalesmanCustVO) select("mpSalesmanCustDAO.selectMpSalesmanCust_S", vo);
    }

    /**
	 * mp_salesman_cust 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_salesman_cust 목록
	 * @exception Exception
	 */
    public List<?> selectMpSalesmanCustList(MpSalesmanCustDefaultVO searchVO) throws Exception {
        return list("mpSalesmanCustDAO.selectMpSalesmanCustList_D", searchVO);
    }

    /**
	 * mp_salesman_cust 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_salesman_cust 총 갯수
	 * @exception
	 */
    public int selectMpSalesmanCustListTotCnt(MpSalesmanCustDefaultVO searchVO) {
        return (Integer)select("mpSalesmanCustDAO.selectMpSalesmanCustListTotCnt_S", searchVO);
    }

}
