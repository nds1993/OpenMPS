package nds.mpm.prod.PP0303.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.prod.PP0303.vo.MpPlanPlatDDefaultVO;
import nds.mpm.prod.PP0303.vo.MpPlanPlatDVO;

/**
 * @Class Name : MpPlanPlatDDAO.java
 * @Description : MpPlanPlatD DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpPlanPlatDDAO")
public class MpPlanPlatDDAO extends EgovAbstractDAO {

	/**
	 * mp_plan_plat_d을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanPlatDVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPlanPlatD(MpPlanPlatDVO vo) throws Exception {
        return (String)insert("mpPlanPlatDDAO.insertMpPlanPlatD_S", vo);
    }

    /**
	 * mp_plan_plat_d을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanPlatDVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPlanPlatD(MpPlanPlatDVO vo) throws Exception {
        update("mpPlanPlatDDAO.updateMpPlanPlatD_S", vo);
    }

    /**
	 * mp_plan_plat_d을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanPlatDVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPlanPlatD(MpPlanPlatDVO vo) throws Exception {
        delete("mpPlanPlatDDAO.deleteMpPlanPlatD_S", vo);
    }

    /**
	 * mp_plan_plat_d을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanPlatDVO
	 * @return 조회한 mp_plan_plat_d
	 * @exception Exception
	 */
    public MpPlanPlatDVO selectMpPlanPlatD(MpPlanPlatDVO vo) throws Exception {
        return (MpPlanPlatDVO) select("mpPlanPlatDDAO.selectMpPlanPlatD_S", vo);
    }

    /**
	 * mp_plan_plat_d 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_plan_plat_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpPlanPlatDList(MpPlanPlatDDefaultVO searchVO) throws Exception {
        return list("mpPlanPlatDDAO.selectMpPlanPlatDList_D", searchVO);
    }

    /**
	 * mp_plan_plat_d 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_plan_plat_d 총 갯수
	 * @exception
	 */
    public int selectMpPlanPlatDListTotCnt(MpPlanPlatDDefaultVO searchVO) {
        return (Integer)select("mpPlanPlatDDAO.selectMpPlanPlatDListTotCnt_S", searchVO);
    }

}
