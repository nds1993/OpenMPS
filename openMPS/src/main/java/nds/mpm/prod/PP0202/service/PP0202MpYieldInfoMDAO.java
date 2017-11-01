package nds.mpm.prod.PP0202.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.prod.PP0201.vo.MpYieldInfoMVO;
import nds.mpm.prod.PP0201.vo.MpYieldInfoMDefaultVO;

/**
 * @Class Name : MpYieldInfoMDAO.java
 * @Description : MpYieldInfoM DAO Class
 * @Modification Information
 *
 * @author 22222
 * @since 22222
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PP0202mpYieldInfoMDAO")
public class PP0202MpYieldInfoMDAO extends EgovAbstractDAO {

	/**
	 * mp_yield_info_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpYieldInfoMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpYieldInfoM(MpYieldInfoMVO vo) throws Exception {
        return (String)insert("PP0202mpYieldInfoMDAO.insertMpYieldInfoM_S", vo);
    }

    /**
	 * mp_yield_info_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpYieldInfoMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpYieldInfoM(MpYieldInfoMVO vo) throws Exception {
        update("PP0202mpYieldInfoMDAO.updateMpYieldInfoM_S", vo);
    }

    /**
	 * mp_yield_info_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpYieldInfoMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpYieldInfoM(MpYieldInfoMVO vo) throws Exception {
        delete("PP0202mpYieldInfoMDAO.deleteMpYieldInfoM_S", vo);
    }

    /**
	 * mp_yield_info_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpYieldInfoMVO
	 * @return 조회한 mp_yield_info_m
	 * @exception Exception
	 */
    public MpYieldInfoMVO selectMpYieldInfoM(MpYieldInfoMVO vo) throws Exception {
        return (MpYieldInfoMVO) select("PP0202mpYieldInfoMDAO.selectMpYieldInfoM_S", vo);
    }

    /**
	 * mp_yield_info_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_yield_info_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpYieldInfoMCmTopList(MpYieldInfoMDefaultVO searchVO) throws Exception {
        return list("PP0202mpYieldInfoMDAO.selectMpYieldInfoMList_T", searchVO);
    }
    /**
	 * mp_yield_info_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_yield_info_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpYieldInfoMCmLDetailList(MpYieldInfoMDefaultVO searchVO) throws Exception {
        return list("PP0202mpYieldInfoMDAO.selectMpYieldInfoMList_L", searchVO);
    }
    /**
	 * mp_yield_info_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_yield_info_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpYieldInfoMCmSDetailList(MpYieldInfoMDefaultVO searchVO) throws Exception {
        return list("PP0202mpYieldInfoMDAO.selectMpYieldInfoMList_S", searchVO);
    }
    
    /**
	 * mp_yield_info_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_yield_info_m 총 갯수
	 * @exception
	 */
    public int selectMpYieldInfoMListTotCnt(MpYieldInfoMDefaultVO searchVO) {
        return (Integer)select("PP0202mpYieldInfoMDAO.selectMpYieldInfoMListTotCnt_S", searchVO);
    }
    
    /**
	 * mp_yield_info_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_yield_info_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpYieldInfoMPmList(MpYieldInfoMDefaultVO searchVO) throws Exception {
        return list("PP0202mpYieldInfoMDAO.selectMpYieldInfoMPmList_D", searchVO);
    }

    /**
	 * mp_yield_info_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_yield_info_m 총 갯수
	 * @exception
	 */
    public int selectMpYieldInfoMPmListTotCnt(MpYieldInfoMDefaultVO searchVO) {
        return (Integer)select("PP0202mpYieldInfoMDAO.selectMpYieldInfoMPmListTotCnt_S", searchVO);
    }

}
