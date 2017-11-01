package nds.mpm.sales.SD0204.service;

import java.util.List;

import nds.mpm.sales.SD0204.vo.MpCustPriClassDefaultVO;
import nds.mpm.sales.SD0204.vo.MpCustPriClassVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpCustPriClassDAO.java
 * @Description : MpCustPriClass DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpCustPriClassDAO")
public class MpCustPriClassDAO extends EgovAbstractDAO {

	/**
	 * mp_cust_pri_class을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpCustPriClassVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpCustPriClass(EgovMap vo) throws Exception {
        return (String)insert("mpCustPriClassDAO.insertMpCustPriClass_S", vo);
    }

    /**
	 * mp_cust_pri_class을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpCustPriClassVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpCustPriClass(MpCustPriClassVO vo) throws Exception {
        update("mpCustPriClassDAO.updateMpCustPriClass_S", vo);
    }

    /**
	 * mp_cust_pri_class을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpCustPriClassVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpCustPriClass(EgovMap vo) throws Exception {
        return delete("mpCustPriClassDAO.deleteMpCustPriClass_S", vo);
    }

    /**
	 * mp_cust_pri_class을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpCustPriClassVO
	 * @return 조회한 mp_cust_pri_class
	 * @exception Exception
	 */
    public MpCustPriClassVO selectMpCustPriClass(MpCustPriClassVO vo) throws Exception {
        return (MpCustPriClassVO) select("mpCustPriClassDAO.selectMpCustPriClass_S", vo);
    }

    /**
	 * mp_cust_pri_class 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_pri_class 목록
	 * @exception Exception
	 */
    public List<?> selectMpSalesmanCustList(MpCustPriClassDefaultVO searchVO) throws Exception {
        return list("mpCustPriClassDAO.selectMpSalesmanCustList_D", searchVO);
    }

    /**
	 * mp_cust_pri_class 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_cust_pri_class 총 갯수
	 * @exception
	 */
    public int selectMpSalesmanCustListTotCnt(MpCustPriClassDefaultVO searchVO) {
        return (Integer)select("mpCustPriClassDAO.selectMpSalesmanCustListTotCnt_S", searchVO);
    }
    
    public List<?> selectMpCustPriClassList(MpCustPriClassDefaultVO searchVO) throws Exception {
        return list("mpCustPriClassDAO.selectMpCustPriClassList_D", searchVO);
    }
}
