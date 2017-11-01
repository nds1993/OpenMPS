package nds.mpm.prod.PP0902.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.prod.PP0902.vo.MpDailySumLogMVO;
import nds.mpm.prod.PP0902.vo.MpDailySumLogMDefaultVO;

/**
 * @Class Name : MpDailySumLogMDAO.java
 * @Description : MpDailySumLogM DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpDailySumLogMDAO")
public class MpDailySumLogMDAO extends EgovAbstractDAO {

	/**
	 * mp_daily_sum_log_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDailySumLogMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpDailySumLogM(MpDailySumLogMVO vo) throws Exception {
        return (String)insert("mpDailySumLogMDAO.insertMpDailySumLogM_S", vo);
    }

    /**
	 * mp_daily_sum_log_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDailySumLogMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpDailySumLogM(MpDailySumLogMVO vo) throws Exception {
        update("mpDailySumLogMDAO.updateMpDailySumLogM_S", vo);
    }

    /**
	 * mp_daily_sum_log_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDailySumLogMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpDailySumLogM(MpDailySumLogMVO vo) throws Exception {
        delete("mpDailySumLogMDAO.deleteMpDailySumLogM_S", vo);
    }

    /**
	 * mp_daily_sum_log_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDailySumLogMVO
	 * @return 조회한 mp_daily_sum_log_m
	 * @exception Exception
	 */
    public MpDailySumLogMVO selectMpDailySumLogM(MpDailySumLogMVO vo) throws Exception {
        return (MpDailySumLogMVO) select("mpDailySumLogMDAO.selectMpDailySumLogM_S", vo);
    }

    /**
	 * mp_daily_sum_log_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_daily_sum_log_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpDailySumLogMList(MpDailySumLogMDefaultVO searchVO) throws Exception {
        return list("mpDailySumLogMDAO.selectMpDailySumLogMList_D", searchVO);
    }

    /**
	 * mp_daily_sum_log_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_daily_sum_log_m 총 갯수
	 * @exception
	 */
    public int selectMpDailySumLogMListTotCnt(MpDailySumLogMDefaultVO searchVO) {
        return (Integer)select("mpDailySumLogMDAO.selectMpDailySumLogMListTotCnt_S", searchVO);
    }

}
