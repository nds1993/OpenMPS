package nds.mpm.prod.PP0201.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.prod.PP0201.vo.MpYieldInfoMDefaultVO;
import nds.mpm.prod.PP0201.vo.MpYieldInfoMVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpYieldInfoMDAO.java
 * @Description : MpYieldInfoM DAO Class
 * @Modification Information
 *
 * @author M
 * @since M
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpYieldInfoMDAO")
public class MpYieldInfoMDAO extends TMMPPBaseDAO {

	/**
	 * mp_yield_info_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpYieldInfoMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpYieldInfoM(EgovMap vo) throws Exception {
        return (String)insert("MpYieldInfoMDAO.insertMpYieldInfoM", vo);
    }

    /**
	 * mp_yield_info_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpYieldInfoMVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpYieldInfoM(EgovMap vo) throws Exception {
       return update("MpYieldInfoMDAO.updateMpYieldInfoM", vo);
    }

    /**
	 * mp_yield_info_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpYieldInfoMVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpYieldInfoM(EgovMap vo) throws Exception {
       return delete("MpYieldInfoMDAO.deleteMpYieldInfoM", vo);
    }

    /**
	 * mp_yield_info_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpYieldInfoMVO
	 * @return 조회한 mp_yield_info_m
	 * @exception Exception
	 */
    public MpYieldInfoMVO selectMpYieldInfoM(EgovMap vo) throws Exception {
        return (MpYieldInfoMVO) select("MpYieldInfoMDAO.selectMpYieldInfoM_S", vo);
    }

    /**
	 * mp_yield_info_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_yield_info_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpYieldInfoMList(MpYieldInfoMDefaultVO searchVO) throws Exception {
        return list("MpYieldInfoMDAO.selectMpYieldInfoMList_D", searchVO);
    }

    /**
	 * mp_yield_info_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_yield_info_m 총 갯수
	 * @exception
	 */
    public int selectMpYieldInfoMListTotCnt(MpYieldInfoMDefaultVO searchVO) {
        return (Integer)select("MpYieldInfoMDAO.selectMpYieldInfoMListTotCnt_S", searchVO);
    }
    
    public int checkDupMpYieldInfoM_S(EgovMap vo) throws Exception {
        return (Integer)select("MpYieldInfoMDAO.checkDupMpYieldInfoM_S", vo);
     }

    public int checkProdClosing(EgovMap searchVO) {
        return (Integer)select("MpYieldInfoMDAO.checkProdClosing", searchVO);
    }
}
