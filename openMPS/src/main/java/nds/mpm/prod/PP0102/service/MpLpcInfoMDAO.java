package nds.mpm.prod.PP0102.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.prod.PP0102.vo.MpLpcInfoMDefaultVO;
import nds.mpm.prod.PP0102.vo.MpLpcInfoMVO;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : MpLpcInfoMDAO.java
 * @Description : MpLpcInfoM DAO Class
 * @Modification Information
 *
 * @author MPM
 * @since 2017
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpLpcInfoMDAO")
public class MpLpcInfoMDAO extends TMMPPBaseDAO {

	/**
	 * mp_lpc_info_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpLpcInfoMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpLpcInfoM(MpLpcInfoMVO vo) throws Exception {
        return (String)insert("mpLpcInfoMDAO.insertMpLpcInfoM_S", vo);
    }

    /**
	 * mp_lpc_info_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpLpcInfoMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpLpcInfoM(MpLpcInfoMVO vo) throws Exception {
        update("mpLpcInfoMDAO.updateMpLpcInfoM_S", vo);
    }

    /**
	 * mp_lpc_info_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpLpcInfoMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpLpcInfoM(MpLpcInfoMVO vo) throws Exception {
        delete("mpLpcInfoMDAO.deleteMpLpcInfoM_S", vo);
    }

    /**
	 * mp_lpc_info_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpLpcInfoMVO
	 * @return 조회한 mp_lpc_info_m
	 * @exception Exception
	 */
    public MpLpcInfoMVO selectMpLpcInfoM(MpLpcInfoMVO vo) throws Exception {
        return (MpLpcInfoMVO) select("mpLpcInfoMDAO.selectMpLpcInfoM_S", vo);
    }

    /**
	 * mp_lpc_info_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_lpc_info_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpLpcInfoMList(MpLpcInfoMDefaultVO searchVO) throws Exception {
        return list("mpLpcInfoMDAO.selectMpLpcInfoMList_D", searchVO);
    }

    /**
	 * mp_lpc_info_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_lpc_info_m 총 갯수
	 * @exception
	 */
    public int selectMpLpcInfoMListTotCnt(MpLpcInfoMDefaultVO searchVO) {
        return (Integer)select("mpLpcInfoMDAO.selectMpLpcInfoMListTotCnt_S", searchVO);
    }

}
