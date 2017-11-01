package nds.mpm.prod.PP0504.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.prod.PP0504.vo.MpBarProMDefaultVO;
import nds.mpm.prod.PP0504.vo.MpBarProMVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpBarProMDAO.java
 * @Description : MpBarProM DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpBarProMDAO")
public class MpBarProMDAO extends TMMPPBaseDAO {

	/**
	 * mp_bar_pro_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpBarProMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpBarProM(MpBarProMVO vo) throws Exception {
        return (String)insert("MpBarProMDAO.insertMpBarProM", vo);
    }

    /**
	 * mp_bar_pro_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpBarProMVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpBarProM(EgovMap vo) throws Exception {
    	return update("MpBarProMDAO.updateMpBarProM", vo);
    }

    /**
	 * mp_bar_pro_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpBarProMVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpBarProM(EgovMap vo) throws Exception {
       return delete("MpBarProMDAO.deleteMpBarProM", vo);
    }

    /**
	 * mp_bar_pro_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpBarProMVO
	 * @return 조회한 mp_bar_pro_m
	 * @exception Exception
	 */
    public MpBarProMVO selectMpBarProM(MpBarProMVO vo) throws Exception {
        return (MpBarProMVO) select("MpBarProMDAO.selectMpBarProM_S", vo);
    }

    /**
	 * mp_bar_pro_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_bar_pro_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpBarProMList(MpBarProMDefaultVO searchVO) throws Exception {
        return list("MpBarProMDAO.selectMpBarProMList_D", searchVO);
    }

    /**
	 * mp_bar_pro_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_bar_pro_m 총 갯수
	 * @exception
	 */
    public int selectMpBarProMListTotCnt(MpBarProMDefaultVO searchVO) {
        return (Integer)select("MpBarProMDAO.selectMpBarProMListTotCnt_S", searchVO);
    }

}
