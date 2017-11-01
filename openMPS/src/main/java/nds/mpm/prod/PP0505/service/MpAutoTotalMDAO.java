package nds.mpm.prod.PP0505.service;

import java.util.List;

import nds.mpm.prod.PP0505.vo.MpAutoTotalMDefaultVO;
import nds.mpm.prod.PP0505.vo.MpAutoTotalMVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpAutoTotalMDAO.java
 * @Description : MpAutoTotalM DAO Class
 * @Modification Information
 *
 * @author PM라벨실적 입고 요청(이시다)
 * @since PM라벨실적 입고 요청(이시다)
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpAutoTotalMDAO")
public class MpAutoTotalMDAO extends EgovAbstractDAO {

	/**
	 * mp_auto_total_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpAutoTotalMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpAutoTotalM(MpAutoTotalMVO vo) throws Exception {
        return (String)insert("mpAutoTotalMDAO.insertMpAutoTotalM_S", vo);
    }

    /**
	 * mp_auto_total_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpAutoTotalMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpAutoTotalM(MpAutoTotalMVO vo) throws Exception {
        update("mpAutoTotalMDAO.updateMpAutoTotalM_S", vo);
    }

    /**
	 * mp_auto_total_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpAutoTotalMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpAutoTotalM(MpAutoTotalMVO vo) throws Exception {
        delete("mpAutoTotalMDAO.deleteMpAutoTotalM_S", vo);
    }

    /**
	 * mp_auto_total_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpAutoTotalMVO
	 * @return 조회한 mp_auto_total_m
	 * @exception Exception
	 */
    public MpAutoTotalMVO selectMpAutoTotalM(MpAutoTotalMVO vo) throws Exception {
        return (MpAutoTotalMVO) select("mpAutoTotalMDAO.selectMpAutoTotalM_S", vo);
    }

    /**
	 * mp_auto_total_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_auto_total_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpAutoTotalMList(MpAutoTotalMDefaultVO searchVO) throws Exception {
        return list("mpAutoTotalMDAO.selectMpAutoTotalMList_D", searchVO);
    }

    /**
	 * mp_auto_total_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_auto_total_m 총 갯수
	 * @exception
	 */
    public int selectMpAutoTotalMListTotCnt(MpAutoTotalMDefaultVO searchVO) {
        return (Integer)select("mpAutoTotalMDAO.selectMpAutoTotalMListTotCnt_S", searchVO);
    }
    
    /**
	 * detail popup
	 * @exception Exception
	 */
    public List<?> selectMpAutoTotalMDetail(MpAutoTotalMDefaultVO searchVO) throws Exception {
        return list("mpAutoTotalMDAO.selectMpAutoTotalMDetail", searchVO);
    }

    public int checkIpgoClosing(MpAutoTotalMDefaultVO searchVO) {
        return (Integer)select("mpAutoTotalMDAO.checkIpgoClosing", searchVO);
    }
    
    public EgovMap callWMSIpgo(MpAutoTotalMDefaultVO searchVO) {
        return (EgovMap)select("mpAutoTotalMDAO.callWMSIpgo", searchVO);
    }
}
