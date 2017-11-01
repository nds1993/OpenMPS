package nds.mpm.sales.SD0501.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.sales.SD0501.vo.MpLastUnpaymentVO;
import nds.mpm.sales.SD0501.vo.MpLastUnpaymentDefaultVO;

/**
 * @Class Name : MpLastUnpaymentDAO.java
 * @Description : MpLastUnpayment DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpLastUnpaymentDAO")
public class MpLastUnpaymentDAO extends EgovAbstractDAO {

	/**
	 * mp_last_unpayment을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpLastUnpaymentVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpLastUnpayment(MpLastUnpaymentVO vo) throws Exception {
        return (String)insert("mpLastUnpaymentDAO.insertMpLastUnpayment", vo);
    }

    /**
	 * mp_last_unpayment을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpLastUnpaymentVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpLastUnpayment(MpLastUnpaymentVO vo) throws Exception {
        update("mpLastUnpaymentDAO.updateMpLastUnpayment", vo);
    }
    
    /**
	 * mp_last_unpayment을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpLastUnpaymentVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpLastCurrentUnpayment(MpLastUnpaymentVO vo) throws Exception {
        return update("mpLastUnpaymentDAO.updateMpLastCurrentUnpayment", vo);
    }

    /**
	 * mp_last_unpayment을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpLastUnpaymentVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpLastUnpayment(MpLastUnpaymentVO vo) throws Exception {
        delete("mpLastUnpaymentDAO.deleteMpLastUnpayment", vo);
    }

    /**
	 * mp_last_unpayment을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpLastUnpaymentVO
	 * @return 조회한 mp_last_unpayment
	 * @exception Exception
	 */
    public MpLastUnpaymentVO selectMpLastUnpayment(MpLastUnpaymentVO vo) throws Exception {
        return (MpLastUnpaymentVO) select("mpLastUnpaymentDAO.selectMpLastUnpayment_S", vo);
    }

    /**
	 * mp_last_unpayment 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_last_unpayment 목록
	 * @exception Exception
	 */
    public List<?> selectMpLastUnpaymentList(MpLastUnpaymentDefaultVO searchVO) throws Exception {
        return list("mpLastUnpaymentDAO.selectMpLastUnpaymentList_D", searchVO);
    }

    /**
	 * mp_last_unpayment 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_last_unpayment 총 갯수
	 * @exception
	 */
    public int selectMpLastUnpaymentListTotCnt(MpLastUnpaymentDefaultVO searchVO) {
        return (Integer)select("mpLastUnpaymentDAO.selectMpLastUnpaymentListTotCnt_S", searchVO);
    }

}
