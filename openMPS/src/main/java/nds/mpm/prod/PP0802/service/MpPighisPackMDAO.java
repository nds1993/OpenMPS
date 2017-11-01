package nds.mpm.prod.PP0802.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.prod.PP0802.vo.MpPighisPackMDefaultVO;
import nds.mpm.prod.PP0802.vo.MpPighisPackMVO;

/**
 * @Class Name : MpPighisPackMDAO.java
 * @Description : MpPighisPackM DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpPighisPackMDAO")
public class MpPighisPackMDAO extends EgovAbstractDAO {

	/**
	 * mp_pighis_pack_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPighisPackMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPighisPackM(EgovMap vo) throws Exception {
        return (String)insert("mpPighisPackMDAO.insertMpPighisPackM", vo);
    }

    /**
	 * mp_pighis_pack_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPighisPackMVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpPighisPackM(EgovMap vo) throws Exception {
        return update("mpPighisPackMDAO.updateMpPighisPackM", vo);
    }

    /**
	 * mp_pighis_pack_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPighisPackMVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpPighisPackM(MpPighisPackMVO vo) throws Exception {
    	return delete("mpPighisPackMDAO.deleteMpPighisPackM", vo);
    }

    /**
	 * mp_pighis_pack_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPighisPackMVO
	 * @return 조회한 mp_pighis_pack_m
	 * @exception Exception
	 */
    public MpPighisPackMVO selectMpPighisPackM(MpPighisPackMVO vo) throws Exception {
        return (MpPighisPackMVO) select("mpPighisPackMDAO.selectMpPighisPackM_S", vo);
    }

    /**
	 * mp_pighis_pack_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pighis_pack_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpPighisPackMList(MpPighisPackMDefaultVO searchVO) throws Exception {
        return list("mpPighisPackMDAO.selectMpPighisPackMList_D", searchVO);
    }

    /**
	 * mp_pighis_pack_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pighis_pack_m 총 갯수
	 * @exception
	 */
    public int selectMpPighisPackMListTotCnt(MpPighisPackMDefaultVO searchVO) {
        return (Integer)select("mpPighisPackMDAO.selectMpPighisPackMListTotCnt_S", searchVO);
    }

    public List<EgovMap> selectMpPighisPackMSendFormatList(MpPighisPackMVO searchVO) throws Exception {
        return (List<EgovMap>)list("mpPighisPackMDAO.selectMpPighisPackMSendFormatList", searchVO);
    }
    
    /**
	 * mp_pighis_pack_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPighisPackMVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateApiTimeMpPighisPackM(EgovMap vo) throws Exception {
        return update("mpPighisPackMDAO.updateApiTimeMpPighisPackM", vo);
    }
    
    /**
	 * @exception Exception
	 */
    public List<?> selectMpBarProMList(MpPighisPackMDefaultVO searchVO) throws Exception {
        return list("mpPighisPackMDAO.selectMpBarProMList_D", searchVO);
    }
    public int selectMpBarProMListTotCnt(MpPighisPackMDefaultVO searchVO) {
        return (Integer)select("mpPighisPackMDAO.selectMpBarProMListTotCnt_S", searchVO);
    }
    /**
     * 포장처리일자기준 중복체크 
     * */
    public int checkDupPackDateMpPighisPackM(MpPighisPackMDefaultVO searchVO) {
        return (Integer)select("mpPighisPackMDAO.checkDupPackDateMpPighisPackM", searchVO);
    }
    
}
