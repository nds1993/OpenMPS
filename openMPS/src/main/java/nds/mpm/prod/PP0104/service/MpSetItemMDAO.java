package nds.mpm.prod.PP0104.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.prod.PP0103.vo.MpItemMasterMVO;
import nds.mpm.prod.PP0104.vo.MpSetItemMDefaultVO;
import nds.mpm.prod.PP0104.vo.MpSetItemMVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpSetItemMDAO.java
 * @Description : MpSetItemM DAO Class
 * @Modification Information
 *
 * @author MPM
 * @since 2017
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpSetItemMDAO")
public class MpSetItemMDAO extends TMMPPBaseDAO {

	/**
	 * mp_set_item_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpSetItemMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpSetItemM(EgovMap vo) throws Exception {
        return (String)insert("mpSetItemMDAO.insertMpSetItemM", vo);
    }

    /**
	 * mp_set_item_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpSetItemMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpSetItemM(EgovMap vo) throws Exception {
        update("mpSetItemMDAO.updateMpSetItemM", vo);
    }

    /**
	 * mp_set_item_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpSetItemMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpSetItemM(EgovMap vo) throws Exception {
        delete("mpSetItemMDAO.deleteMpSetItemM", vo);
    }

    /**
	 * mp_set_item_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpSetItemMVO
	 * @return 조회한 mp_set_item_m
	 * @exception Exception
	 */
    public MpSetItemMVO selectMpSetItemM(EgovMap vo) throws Exception {
        return (MpSetItemMVO) select("mpSetItemMDAO.selectMpSetItemM_S", vo);
    }

    /**
	 * mp_set_item_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_set_item_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpSetItemMList(MpSetItemMDefaultVO searchVO) throws Exception {
        return list("mpSetItemMDAO.selectMpSetItemMList_D", searchVO);
    }

    /**
	 * mp_set_item_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_set_item_m 총 갯수
	 * @exception
	 */
    public int selectMpSetItemMListTotCnt(MpSetItemMDefaultVO searchVO) {
        return (Integer)select("mpSetItemMDAO.selectMpSetItemMListTotCnt_S", searchVO);
    }
    
    public List<?> selectMpItemMasterMList(MpItemMasterMVO searchVO) throws Exception {
        return list("mpSetItemMDAO.selectMpItemMasterMList_D", searchVO);
    }
}
