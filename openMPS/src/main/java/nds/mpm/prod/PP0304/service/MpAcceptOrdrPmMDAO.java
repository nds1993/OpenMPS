package nds.mpm.prod.PP0304.service;

import java.util.List;

import nds.mpm.prod.PP0304.vo.MpAcceptOrdrPmMDefaultVO;
import nds.mpm.prod.PP0304.vo.MpAcceptOrdrPmMVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpAcceptOrdrPmMDAO.java
 * @Description : MpAcceptOrdrPmM DAO Class
 * @Modification Information
 *
 * @author 생산계획입력(PM)
 * @since 생산계획입력(PM)
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpAcceptOrdrPmMDAO")
public class MpAcceptOrdrPmMDAO extends EgovAbstractDAO {

	/**
	 * mp_accept_ordr_pm_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpAcceptOrdrPmMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpAcceptOrdrPmM(EgovMap vo) throws Exception {
        return (String)insert("mpAcceptOrdrPmMDAO.insertMpAcceptOrdrPmM", vo);
    }

    /**
	 * 접수취소처리 mp_order_d
	 * @exception Exception
	 */
    public int updateMpOrderD(EgovMap vo) throws Exception {
        return update("mpAcceptOrdrPmMDAO.updateMpOrderD", vo);
    }
    
    /**
	 * @exception Exception
	 */
    public int updateMpAcceptOrdrPmMStatus(EgovMap vo) throws Exception {
        return update("mpAcceptOrdrPmMDAO.updateMpAcceptOrdrPmMStatus", vo);
    }
    
    /**
	 * mp_accept_ordr_pm_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpAcceptOrdrPmMVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpAcceptOrdrPmM(EgovMap vo) throws Exception {
        return delete("mpAcceptOrdrPmMDAO.deleteMpAcceptOrdrPmM", vo);
    }

    /**
	 * mp_accept_ordr_pm_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpAcceptOrdrPmMVO
	 * @return 조회한 mp_accept_ordr_pm_m
	 * @exception Exception
	 */
    public MpAcceptOrdrPmMVO selectMpAcceptOrdrPmM(EgovMap vo) throws Exception {
        return (MpAcceptOrdrPmMVO) select("mpAcceptOrdrPmMDAO.selectMpAcceptOrdrPmM_S", vo);
    }
    
    /**
     * 미접수분 조회 mp_order_d
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @exception Exception
	 */
    public List<?> selectMpOrderDList(MpAcceptOrdrPmMDefaultVO searchVO) throws Exception {
        return list("mpAcceptOrdrPmMDAO.selectMpOrderDList_D", searchVO);
    }

    /**
	 * 미접수분 조회 mp_order_d
	 * @exception
	 */
    public int selectMpOrderDListTotCnt(MpAcceptOrdrPmMDefaultVO searchVO) {
        return (Integer)select("mpAcceptOrdrPmMDAO.selectMpOrderDListTotCnt_S", searchVO);
    }

    /**
	 * 접수분 조회 mp_order_d
	 * @exception Exception
	 */
    public List<?> selectMpAcceptOrdrPmMList(MpAcceptOrdrPmMDefaultVO searchVO) throws Exception {
        return list("mpAcceptOrdrPmMDAO.selectMpAcceptOrdrPmMList_D", searchVO);
    }

    /**
	 * 접수분 조회 mp_order_d
	 * @exception
	 */
    public int selectMpAcceptOrdrPmMListTotCnt(MpAcceptOrdrPmMDefaultVO searchVO) {
        return (Integer)select("mpAcceptOrdrPmMDAO.selectMpAcceptOrdrPmMListTotCnt_S", searchVO);
    }
    /**
	 * 계획번호 생성 기준 거래처 + 출고일자 + 주문번호
	 * @exception
	 */
    public String selectMpAcceptOrdrPmPMlanNo(EgovMap vo) throws Exception {
        return (String) select("mpAcceptOrdrPmMDAO.selectMpAcceptOrdrPMPlanNo", vo);
    }
    
    public String selectMpPlanPmMAddProdSeq(EgovMap searchVO) {
        return (String)select("mpAcceptOrdrPmMDAO.selectMpPlanPmMAddProdSeq", searchVO);
    }
    
}
