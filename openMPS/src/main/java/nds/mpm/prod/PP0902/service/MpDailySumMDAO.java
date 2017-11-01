package nds.mpm.prod.PP0902.service;

import java.util.List;

import nds.mpm.prod.PP0902.vo.MpDailySumMDefaultVO;
import nds.mpm.prod.PP0902.vo.MpDailySumMVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpDailySumMDAO.java
 * @Description : MpDailySumM DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpDailySumMDAO")
public class MpDailySumMDAO extends EgovAbstractDAO {

	/**
	 * mp_daily_sum_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDailySumMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpDailySumM(MpDailySumMVO vo) throws Exception {
        return (String)insert("mpDailySumMDAO.insertMpDailySumM_S", vo);
    }
    
    public String insertMpDailySumM(EgovMap vo) throws Exception {
        return (String)insert("mpDailySumMDAO.insertMpDailySumM_S", vo);
    }

    /**
	 * mp_daily_sum_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDailySumMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpDailySumM(MpDailySumMVO vo) throws Exception {
        update("mpDailySumMDAO.updateMpDailySumM_S", vo);
    }

    /**
	 * mp_daily_sum_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDailySumMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpDailySumM(MpDailySumMVO vo) throws Exception {
        delete("mpDailySumMDAO.deleteMpDailySumM_S", vo);
    }

    /**
	 * mp_daily_sum_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDailySumMVO
	 * @return 조회한 mp_daily_sum_m
	 * @exception Exception
	 */
    public MpDailySumMVO selectMpDailySumM(MpDailySumMVO vo) throws Exception {
        return (MpDailySumMVO) select("mpDailySumMDAO.selectMpDailySumM_S", vo);
    }

    /**
	 * mp_daily_sum_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_daily_sum_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpDailySumMList(MpDailySumMDefaultVO searchVO) throws Exception {
        return list("mpDailySumMDAO.selectMpDailySumMList_D", searchVO);
    }

    /**
	 * mp_daily_sum_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_daily_sum_m 총 갯수
	 * @exception
	 */
    public int selectMpDailySumMListTotCnt(MpDailySumMDefaultVO searchVO) {
        return (Integer)select("mpDailySumMDAO.selectMpDailySumMListTotCnt_S", searchVO);
    }

}
