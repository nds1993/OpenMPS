package nds.mpm.prod.PP0302.service;

import java.util.List;

import nds.mpm.prod.PP0302.vo.MpPlanCmDVO;
import nds.mpm.prod.PP0302.vo.MpPlanCmHDefaultVO;
import nds.mpm.prod.PP0302.vo.MpPlanCmHVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPlanCmHDAO.java
 * @Description : MpPlanCmH DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpPlanCmHDAO")
public class MpPlanCmHDAO extends EgovAbstractDAO {

	/**
	 * mp_plan_cm_h을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanCmHVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPlanCmH(MpPlanCmHVO vo) throws Exception {
        return (String)insert("MpPlanCmHDAO.insertMpPlanCmH", vo);
    }

    /**
	 * mp_plan_cm_h을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanCmHVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPlanCmH(MpPlanCmHVO vo) throws Exception {
        update("MpPlanCmHDAO.updateMpPlanCmH", vo);
    }

    /**
	 * mp_plan_cm_h을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanCmHVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpPlanCmH(EgovMap vo) throws Exception {
        return delete("MpPlanCmHDAO.deleteMpPlanCmH", vo);
    }

    /**
	 * mp_plan_cm_h을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanCmHVO
	 * @return 조회한 mp_plan_cm_h
	 * @exception Exception
	 */
    public MpPlanCmHVO selectMpPlanCmH(MpPlanCmHVO vo) throws Exception {
        return (MpPlanCmHVO) select("MpPlanCmHDAO.selectMpPlanCmH_S", vo);
    }

    /**
	 * mp_plan_cm_h 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_plan_cm_h 목록
	 * @exception Exception
	 */
    public List<?> selectMpPlanCmHList(MpPlanCmHDefaultVO searchVO) throws Exception {
        return list("MpPlanCmHDAO.selectMpPlanCmHList_D", searchVO);
    }

    /**
	 * mp_plan_cm_h 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_plan_cm_h 총 갯수
	 * @exception
	 */
    public int selectMpPlanCmHListTotCnt(MpPlanCmHDefaultVO searchVO) {
        return (Integer)select("MpPlanCmHDAO.selectMpPlanCmHListTotCnt_S", searchVO);
    }
    
    /**
	 * 신규생산계획 생성 가능여부체크
	 * return 'Y' 가능.
	 */
    public String selectMpPlanCmHCanNewCmPlan(MpPlanCmHDefaultVO searchVO) {
        return (String)select("MpPlanCmHDAO.selectMpPlanCmHCanNewCmPlan", searchVO);
    }
    
    /**
     * 신규입력 대상 제품목록 
	 * mp_plan_cm_h 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_plan_cm_h 목록
	 * @exception Exception
	 */
    public List selectNewMpPlanCmHList(MpPlanCmHDefaultVO searchVO) throws Exception {
        return list("MpPlanCmHDAO.selectNewMpPlanCmHList", searchVO);
    }
    
    /**
     * 조회 합계 탭 - 공장별 합계수 
	 * mp_plan_cm_h 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_plan_cm_h 목록
	 * @exception Exception
	 */
    public EgovMap selectNewMpPlanCmHSum(MpPlanCmHDefaultVO searchVO) throws Exception {
        return (EgovMap)select("MpPlanCmHDAO.selectMpPlanCmHList_Sum", searchVO);
    }
    
    /**
     * 조회 합계 탭 - 목록 
	 * mp_plan_cm_h 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_plan_cm_h 목록
	 * @exception Exception
	 */
    public List<EgovMap> selectNewMpPlanCmHSumList(MpPlanCmHDefaultVO searchVO) throws Exception {
        return (List<EgovMap>)list("MpPlanCmHDAO.selectMpPlanCmHList_SumList", searchVO);
    }
    
    /**
     * 제품추가 재고정보 조회
	 * @exception Exception
	 */
    public EgovMap selectNewMpPlanCmHProCode(MpPlanCmHDefaultVO searchVO) throws Exception {
        return (EgovMap)select("MpPlanCmHDAO.selectNewMpPlanCmHProCode", searchVO);
    }
    
    /**
     * 조회 합계 탭 - 공장별 합계수 
	 * mp_plan_cm_h 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_plan_cm_h 목록
	 * @exception Exception
	 */
    public EgovMap selectMpPlanCmHDoosuSum(MpPlanCmHDefaultVO searchVO) throws Exception {
        return (EgovMap)select("MpPlanCmHDAO.selectMpPlanCmHDoosuSum", searchVO);
    }
    
    /**
   	 * mp_plan_cm_h을 수정한다.
   	 * @param vo - 수정할 정보가 담긴 MpPlanCmHVO
   	 * @return void형
   	 * @exception Exception
   	 */
	public int updateMpPlanCmHStatus(MpPlanCmHVO vo) throws Exception {
	   return update("MpPlanCmHDAO.updateMpPlanCmHStatus", vo);
	}
	
	public String insertNewMpPlanCmHeader(MpPlanCmHDefaultVO vo) throws Exception {
        return (String)insert("MpPlanCmHDAO.insertNewMpPlanCmHeader", vo);
    }
	
	public EgovMap selectCompTimeMpPlanCmH(MpPlanCmHDefaultVO vo) throws Exception {
        return (EgovMap) select("MpPlanCmHDAO.selectCompTimeMpPlanCmH", vo);
    }
}	
