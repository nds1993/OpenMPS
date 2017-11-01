package nds.mpm.prod.PP0305.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.prod.PP0305.vo.MpPlanPmMDefaultVO;
import nds.mpm.prod.PP0305.vo.MpPlanPmMVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPlanPmMDAO.java
 * @Description : MpPlanPmM DAO Class
 * @Modification Information
 *
 * @author 생산의뢰 접수 (PM주문)
 * @since 생산의뢰 접수 (PM주문)
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpPlanPmMDAO")
public class MpPlanPmMDAO extends TMMPPBaseDAO {

	
	
	/**
	 * mp_plan_pm_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanPmMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPlanPmM(MpPlanPmMVO vo) throws Exception {
        return (String)insert("mpPlanPmMDAO.insertMpPlanPmM", vo);
    }
    public String insertMpPlanPmM(EgovMap vo) throws Exception {
        return (String)insert("mpPlanPmMDAO.insertMpPlanPmM", vo);
    }

    /**
	 * mp_plan_pm_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanPmMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPlanPmM(EgovMap vo) throws Exception {
        update("mpPlanPmMDAO.updateMpPlanPmM", vo);
    }
    
    /**
	 * 상태변경.
	 * @exception Exception
	 */
    public int updateMpPlanPmMStatus(EgovMap vo) throws Exception {
        return update("mpPlanPmMDAO.updateMpPlanPmMStatus", vo);
    }
    public int updateMpAcceptOrdrPmMStatus(EgovMap vo) throws Exception {
        return update("mpPlanPmMDAO.updateMpAcceptOrdrPmMStatus", vo);
    }

    /**
	 * mp_plan_pm_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanPmMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPlanPmM(MpPlanPmMVO vo) throws Exception {
        delete("mpPlanPmMDAO.deleteMpPlanPmM", vo);
    }
    public int deleteMpPlanPmM(EgovMap vo) throws Exception {
    	return delete("mpPlanPmMDAO.deleteMpPlanPmM", vo);
    }

    /**
	 * mp_plan_pm_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanPmMVO
	 * @return 조회한 mp_plan_pm_m
	 * @exception Exception
	 */
    public MpPlanPmMVO selectMpPlanPmM(EgovMap vo) throws Exception {
        return (MpPlanPmMVO) select("mpPlanPmMDAO.selectMpPlanPmM_S", vo);
    }

    /**
	 * mp_plan_pm_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_plan_pm_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpPlanPmMList(MpPlanPmMDefaultVO searchVO) throws Exception {
        return list("mpPlanPmMDAO.selectMpPlanPmMList_D", searchVO);
    }

    /**
	 * mp_plan_pm_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_plan_pm_m 총 갯수
	 * @exception
	 */
    public int selectMpPlanPmMListTotCnt(MpPlanPmMDefaultVO searchVO) {
        return (Integer)select("mpPlanPmMDAO.selectMpPlanPmMListTotCnt_S", searchVO);
    }
    
    /**
	 * 접수분 조회 MpAcceptOrdrPmM
	 * @exception Exception
	 */
    public List<?> selectMpAcceptOrdrPmMList(MpPlanPmMDefaultVO searchVO) throws Exception {
        return list("mpPlanPmMDAO.selectMpAcceptOrdrPmMList_D", searchVO);
    }

    /**
	 * 접수분 조회 MpAcceptOrdrPmM 총 갯수
	 * @exception
	 */
    public int selectMpAcceptOrdrPmMListTotCnt(MpPlanPmMDefaultVO searchVO) {
        return (Integer)select("mpPlanPmMDAO.selectMpAcceptOrdrPmMListTotCnt_S", searchVO);
    }
    
    public String selectMpPlanPmMPlanNo(EgovMap vo) throws Exception {
        return (String) select("mpPlanPmMDAO.selectMpPlanPmMPlanNo", vo);
    }
    
    public List<?> selectMpPlanPmMListPlanTime(MpPlanPmMDefaultVO searchVO) throws Exception {
        return list("mpPlanPmMDAO.selectMpPlanPmMListPlanTime", searchVO);
    }
    
    public List<?> selectPP0306MpPlanPmMList(MpPlanPmMDefaultVO searchVO) throws Exception {
        return list("mpPlanPmMDAO.selectPP0306MpPlanPmMList_D", searchVO);
    }
}
