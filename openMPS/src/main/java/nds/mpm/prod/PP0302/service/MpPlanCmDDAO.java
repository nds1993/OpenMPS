package nds.mpm.prod.PP0302.service;

import java.util.List;

import nds.mpm.prod.PP0302.vo.MpPlanCmDDefaultVO;
import nds.mpm.prod.PP0302.vo.MpPlanCmDVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPlanCmDDAO.java
 * @Description : MpPlanCmD DAO Class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpPlanCmDDAO")
public class MpPlanCmDDAO extends EgovAbstractDAO {

	/**
	 * mp_plan_cm_d을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanCmDVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPlanCmD(EgovMap vo) throws Exception {
        return (String)insert("mpPlanCmDDAO.insertMpPlanCmD", vo);
    }

    /**
	 * mp_plan_cm_d을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanCmDVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPlanCmD(MpPlanCmDVO vo) throws Exception {
        update("mpPlanCmDDAO.updateMpPlanCmD", vo);
    }

    /**
	 * mp_plan_cm_d을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanCmDVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpPlanCmD(EgovMap vo) throws Exception {
        return delete("mpPlanCmDDAO.deleteMpPlanCmD", vo);
    }

    /**
	 * mp_plan_cm_d을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanCmDVO
	 * @return 조회한 mp_plan_cm_d
	 * @exception Exception
	 */
    public MpPlanCmDVO selectMpPlanCmD(MpPlanCmDVO vo) throws Exception {
        return (MpPlanCmDVO) select("mpPlanCmDDAO.selectMpPlanCmD_S", vo);
    }

    /**
	 * mp_plan_cm_d 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_plan_cm_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpPlanCmDList(MpPlanCmDDefaultVO searchVO) throws Exception {
        return list("mpPlanCmDDAO.selectMpPlanCmDList_D", searchVO);
    }

    /**
	 * mp_plan_cm_d 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_plan_cm_d 총 갯수
	 * @exception
	 */
    public int selectMpPlanCmDListTotCnt(MpPlanCmDDefaultVO searchVO) {
        return (Integer)select("mpPlanCmDDAO.selectMpPlanCmDListTotCnt_S", searchVO);
    }
    
    public int checkDupProCodeMpPlanCmD(EgovMap searchVO) {
        return (Integer)select("mpPlanCmDDAO.checkDupProCodeMpPlanCmD", searchVO);
    }
}
