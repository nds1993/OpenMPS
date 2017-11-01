package nds.mpm.prod.PP0902.service;

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
 * @author 3333
 * @since 3333
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PP0902mpBarProMDAO")
public class PP0902MpBarProMDAO extends EgovAbstractDAO {

	/**
	 * mp_bar_pro_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpBarProMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpBarProM(MpBarProMVO vo) throws Exception {
        return (String)insert("PP0902mpBarProMDAO.insertMpBarProM_S", vo);
    }

    /**
	 * mp_bar_pro_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpBarProMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpBarProM(MpBarProMVO vo) throws Exception {
        update("PP0902mpBarProMDAO.updateMpBarProM_S", vo);
    }

    /**
	 * mp_bar_pro_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpBarProMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpBarProM(MpBarProMVO vo) throws Exception {
        delete("PP0902mpBarProMDAO.deleteMpBarProM_S", vo);
    }

    /**
	 * mp_bar_pro_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpBarProMVO
	 * @return 조회한 mp_bar_pro_m
	 * @exception Exception
	 */
    public MpBarProMVO selectMpBarProM(MpBarProMVO vo) throws Exception {
        return (MpBarProMVO) select("PP0902mpBarProMDAO.selectMpBarProM_S", vo);
    }

    /**
	 * mp_bar_pro_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_bar_pro_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpBarProMList(MpBarProMDefaultVO searchVO) throws Exception {
        return list("PP0902mpBarProMDAO.selectMpBarProMList_D", searchVO);
    }
    
    public List selectCollectDailyMpBarProM(MpBarProMDefaultVO searchVO) throws Exception {
        return list("PP0902mpBarProMDAO.selectCollectDailyMpBarProM", searchVO);
    }
    /**
	 * mp_bar_pro_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_bar_pro_m 총 갯수
	 * @exception
	 */
    public int selectMpBarProMListTotCnt(MpBarProMDefaultVO searchVO) {
        return (Integer)select("PP0902mpBarProMDAO.selectMpBarProMListTotCnt_S", searchVO);
    }
    
    public int checkProdDataClose(MpBarProMDefaultVO searchVO) {
        return (Integer)select("PP0902mpBarProMDAO.checkProdDataClose", searchVO);
    }
}
