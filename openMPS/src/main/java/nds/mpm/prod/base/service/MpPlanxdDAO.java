package nds.mpm.prod.base.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.prod.base.vo.MpPlanxdVO;
import nds.mpm.prod.base.vo.MpPlanxdDefaultVO;

/**
 * @Class Name : MpPlanxdDAO.java
 * @Description : MpPlanxd DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.28
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpPlanxdDAO")
public class MpPlanxdDAO extends EgovAbstractDAO {

	/**
	 * MP_PLANXD을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanxdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPlanxd(MpPlanxdVO vo) throws Exception {
        return (String)insert("mpPlanxdDAO.insertMpPlanxd_S", vo);
    }

    /**
	 * MP_PLANXD을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanxdVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPlanxd(MpPlanxdVO vo) throws Exception {
        update("mpPlanxdDAO.updateMpPlanxd_S", vo);
    }

    /**
	 * MP_PLANXD을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPlanxdVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPlanxd(MpPlanxdVO vo) throws Exception {
        delete("mpPlanxdDAO.deleteMpPlanxd_S", vo);
    }

    /**
	 * MP_PLANXD을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanxdVO
	 * @return 조회한 MP_PLANXD
	 * @exception Exception
	 */
    public MpPlanxdVO selectMpPlanxd(MpPlanxdVO vo) throws Exception {
        return (MpPlanxdVO) select("mpPlanxdDAO.selectMpPlanxd_S", vo);
    }

    /**
	 * MP_PLANXD 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return MP_PLANXD 목록
	 * @exception Exception
	 */
    public List<?> selectMpPlanxdList(MpPlanxdDefaultVO searchVO) throws Exception {
        return list("mpPlanxdDAO.selectMpPlanxdList_D", searchVO);
    }

    /**
	 * MP_PLANXD 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return MP_PLANXD 총 갯수
	 * @exception
	 */
    public int selectMpPlanxdListTotCnt(MpPlanxdDefaultVO searchVO) {
        return (Integer)select("mpPlanxdDAO.selectMpPlanxdListTotCnt_S", searchVO);
    }

}
