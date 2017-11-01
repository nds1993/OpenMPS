package nds.mpm.prod.PP0301.service;

import java.util.List;

import nds.mpm.prod.PP0301.vo.MpPlanSetupMDefaultVO;
import nds.mpm.prod.PP0301.vo.MpPlanSetupMVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPlanSetupMDAO.java
 * @Description : MpPlanSetupM DAO Class
 * @Modification Information
 *
 * @author M
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpPlanSetupMDAO")
public class MpPlanSetupMDAO extends EgovAbstractDAO {

	/**
	 * mp_plan_setup_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanSetupMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPlanSetupM(EgovMap vo) throws Exception {
        return (String)insert("mpPlanSetupMDAO.insertMpPlanSetupM", vo);
    }

    /**
	 * mp_plan_setup_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanSetupMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPlanSetupM(MpPlanSetupMVO vo) throws Exception {
        update("mpPlanSetupMDAO.updateMpPlanSetupM_S", vo);
    }

    /**
	 * mp_plan_setup_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanSetupMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPlanSetupM(EgovMap vo) throws Exception {
        delete("mpPlanSetupMDAO.deleteMpPlanSetupM", vo);
    }

    /**
	 * mp_plan_setup_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanSetupMVO
	 * @return 조회한 mp_plan_setup_m
	 * @exception Exception
	 */
    public MpPlanSetupMVO selectMpPlanSetupM(MpPlanSetupMVO vo) throws Exception {
        return (MpPlanSetupMVO) select("mpPlanSetupMDAO.selectMpPlanSetupM_S", vo);
    }

    /**
	 * mp_plan_setup_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_plan_setup_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpPlanSetupMList(MpPlanSetupMDefaultVO searchVO) throws Exception {
        return list("mpPlanSetupMDAO.selectMpPlanSetupMList_D", searchVO);
    }

    /**
	 * mp_plan_setup_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_plan_setup_m 총 갯수
	 * @exception
	 */
    public int selectMpPlanSetupMListTotCnt(MpPlanSetupMDefaultVO searchVO) {
        return (Integer)select("mpPlanSetupMDAO.selectMpPlanSetupMListTotCnt_S", searchVO);
    }

}
