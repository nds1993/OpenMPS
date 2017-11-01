package nds.mpm.prod.base.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.prod.base.vo.MpPlanxhVO;
import nds.mpm.prod.base.vo.MpPlanxhDefaultVO;

/**
 * @Class Name : MpPlanxhDAO.java
 * @Description : MpPlanxh DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.28
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpPlanxhDAO")
public class MpPlanxhDAO extends EgovAbstractDAO {

	/**
	 * MP_PLANXH을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanxhVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPlanxh(MpPlanxhVO vo) throws Exception {
        return (String)insert("mpPlanxhDAO.insertMpPlanxh_S", vo);
    }

    /**
	 * MP_PLANXH을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanxhVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPlanxh(MpPlanxhVO vo) throws Exception {
        update("mpPlanxhDAO.updateMpPlanxh_S", vo);
    }

    /**
	 * MP_PLANXH을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanxhVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPlanxh(MpPlanxhVO vo) throws Exception {
        delete("mpPlanxhDAO.deleteMpPlanxh_S", vo);
    }

    /**
	 * MP_PLANXH을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanxhVO
	 * @return 조회한 MP_PLANXH
	 * @exception Exception
	 */
    public MpPlanxhVO selectMpPlanxh(MpPlanxhVO vo) throws Exception {
        return (MpPlanxhVO) select("mpPlanxhDAO.selectMpPlanxh_S", vo);
    }

    /**
	 * MP_PLANXH 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return MP_PLANXH 목록
	 * @exception Exception
	 */
    public List<?> selectMpPlanxhList(MpPlanxhDefaultVO searchVO) throws Exception {
        return list("mpPlanxhDAO.selectMpPlanxhList_D", searchVO);
    }

    /**
	 * MP_PLANXH 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return MP_PLANXH 총 갯수
	 * @exception
	 */
    public int selectMpPlanxhListTotCnt(MpPlanxhDefaultVO searchVO) {
        return (Integer)select("mpPlanxhDAO.selectMpPlanxhListTotCnt_S", searchVO);
    }

}
