package nds.mpm.prod.PP0401.service;

import java.util.List;

import nds.mpm.prod.PP0401.vo.MpReqMtrlMDefaultVO;
import nds.mpm.prod.PP0401.vo.MpReqMtrlMVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpReqMtrlMDAO.java
 * @Description : MpReqMtrlM DAO Class
 * @Modification Information
 *
 * @author 자재 소요량 산출
 * @since 자재 소요량 산출
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpReqMtrlMDAO")
public class MpReqMtrlMDAO extends EgovAbstractDAO {

	/**
	 * mp_req_mtrl_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpReqMtrlMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpReqMtrlM(EgovMap vo) throws Exception {
        return (String)insert("mpReqMtrlMDAO.insertMpReqMtrlM", vo);
    }

    /**
	 * mp_req_mtrl_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpReqMtrlMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpReqMtrlM(EgovMap vo) throws Exception {
        update("mpReqMtrlMDAO.updateMpReqMtrlM", vo);
    }

    /**
	 * mp_req_mtrl_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpReqMtrlMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpReqMtrlM(EgovMap vo) throws Exception {
        delete("mpReqMtrlMDAO.deleteMpReqMtrlM", vo);
    }

    /**
	 * mp_req_mtrl_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpReqMtrlMVO
	 * @return 조회한 mp_req_mtrl_m
	 * @exception Exception
	 */
    public MpReqMtrlMVO selectMpReqMtrlM(MpReqMtrlMVO vo) throws Exception {
        return (MpReqMtrlMVO) select("mpReqMtrlMDAO.selectMpReqMtrlM_S", vo);
    }

    /**
	 * mp_req_mtrl_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_req_mtrl_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpReqMtrlMList(MpReqMtrlMDefaultVO searchVO) throws Exception {
        return list("mpReqMtrlMDAO.selectMpReqMtrlMList_D", searchVO);
    }

    /**
	 * mp_req_mtrl_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_req_mtrl_m 총 갯수
	 * @exception
	 */
    public int selectMpReqMtrlMListTotCnt(MpReqMtrlMDefaultVO searchVO) {
        return (Integer)select("mpReqMtrlMDAO.selectMpReqMtrlMListTotCnt_S", searchVO);
    }
    
    public int checkMpReqMtrlMCnt(MpReqMtrlMDefaultVO searchVO) {
        return (Integer)select("mpReqMtrlMDAO.checkMpReqMtrlMCnt", searchVO);
    }
    
    public int deleteCalcWorkDateMpReqMtrlM(MpReqMtrlMDefaultVO searchVO) {
        return delete("mpReqMtrlMDAO.deleteCalcWorkDateMpReqMtrlM", searchVO);
    }
    
    public List<EgovMap> selectCalculateMpReqMtrlMList(MpReqMtrlMDefaultVO searchVO) throws Exception {
        return (List<EgovMap>)list("mpReqMtrlMDAO.selectCalculateMpReqMtrlMList", searchVO);
    }

}
