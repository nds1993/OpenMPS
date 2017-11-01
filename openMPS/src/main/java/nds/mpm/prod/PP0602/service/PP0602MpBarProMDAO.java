package nds.mpm.prod.PP0602.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.prod.PP0504.vo.MpBarProMVO;
import nds.mpm.prod.PP0504.vo.MpBarProMDefaultVO;

/**
 * @Class Name : MpBarProMDAO.java
 * @Description : MpBarProM DAO Class
 * @Modification Information
 *
 * @author 공장별 생산현황 조회(집계)
 * @since 공장별 생산현황 조회(집계)
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PP0602mpBarProMDAO")
public class PP0602MpBarProMDAO extends EgovAbstractDAO {

	/**
	 * mp_bar_pro_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpBarProMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpBarProM(MpBarProMVO vo) throws Exception {
        return (String)insert("PP0602mpBarProMDAO.insertMpBarProM_S", vo);
    }

    /**
	 * mp_bar_pro_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpBarProMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpBarProM(MpBarProMVO vo) throws Exception {
        update("PP0602mpBarProMDAO.updateMpBarProM_S", vo);
    }

    /**
	 * mp_bar_pro_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpBarProMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpBarProM(MpBarProMVO vo) throws Exception {
        delete("PP0602mpBarProMDAO.deleteMpBarProM_S", vo);
    }

    /**
	 * mp_bar_pro_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpBarProMVO
	 * @return 조회한 mp_bar_pro_m
	 * @exception Exception
	 */
    public MpBarProMVO selectMpBarProM(MpBarProMVO vo) throws Exception {
        return (MpBarProMVO) select("PP0602mpBarProMDAO.selectMpBarProM_S", vo);
    }

    /**
	 * mp_bar_pro_m 목록을 조회한다.(품목별)
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_bar_pro_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpBarProMListTab1(MpBarProMVO searchVO) throws Exception {
        return list("PP0602mpBarProMDAO.selectMpBarProMList_T1", searchVO);
    }
    
    /**
	 * mp_bar_pro_m 목록을 조회한다.(제품별)
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_bar_pro_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpBarProMListTab2(MpBarProMVO searchVO) throws Exception {
        return list("PP0602mpBarProMDAO.selectMpBarProMList_T2", searchVO);
    }

    /**
	 * mp_bar_pro_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_bar_pro_m 총 갯수
	 * @exception
	 */
    public int selectMpBarProMListTotCnt(MpBarProMDefaultVO searchVO) {
        return (Integer)select("PP0602mpBarProMDAO.selectMpBarProMListTotCnt_S", searchVO);
    }

}
