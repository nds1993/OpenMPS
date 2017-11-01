package nds.mpm.prod.PP0901.service;

import java.util.List;

import nds.mpm.prod.PP0901.vo.MpReqOutMDefaultVO;
import nds.mpm.prod.PP0901.vo.MpReqOutMVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpReqOutMDAO.java
 * @Description : MpReqOutM DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PP0901mpReqOutMDAO")
public class MpReqOutMDAO extends EgovAbstractDAO {

	/**
	 * mp_req_out_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpReqOutMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpReqOutM(EgovMap vo) throws Exception {
        return (String)insert("mpReqOutMDAO.insertMpReqOutM", vo);
    }

    /**
	 * mp_req_out_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpReqOutMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpReqOutM(MpReqOutMVO vo) throws Exception {
        update("mpReqOutMDAO.updateMpReqOutM", vo);
    }

    /**
	 * mp_req_out_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpReqOutMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpReqOutM(MpReqOutMVO vo) throws Exception {
        delete("mpReqOutMDAO.deleteMpReqOutM", vo);
    }

    /**
	 * mp_req_out_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpReqOutMVO
	 * @return 조회한 mp_req_out_m
	 * @exception Exception
	 */
    public MpReqOutMVO selectMpReqOutM(MpReqOutMVO vo) throws Exception {
        return (MpReqOutMVO) select("mpReqOutMDAO.selectMpReqOutM_S", vo);
    }

    /**
	 * mp_req_out_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_req_out_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpReqOutMList(MpReqOutMDefaultVO searchVO) throws Exception {
        return list("mpReqOutMDAO.selectMpReqOutMList_D", searchVO);
    }

    /**
	 * mp_req_out_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_req_out_m 총 갯수
	 * @exception
	 */
    public int selectMpReqOutMListTotCnt(MpReqOutMDefaultVO searchVO) {
        return (Integer)select("mpReqOutMDAO.selectMpReqOutMListTotCnt_S", searchVO);
    }

    /**
	 * mp_req_out_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_req_out_m 목록
	 * @exception Exception
	 */
    public List<EgovMap> selectBomCurDayWorkList(MpReqOutMDefaultVO searchVO) throws Exception {
        return (List<EgovMap>)list("mpReqOutMDAO.selectBomCurDayWorkList", searchVO);
    }
    
    public EgovMap callWMSSetMtrlOder(EgovMap searchVO) {
        return (EgovMap)select("mpReqOutMDAO.callWMSSetMtrlOder", searchVO);
    }
    
}
