package nds.mpm.prod.PP0105.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.prod.PP0105.vo.MpBomHDefaultVO;
import nds.mpm.prod.PP0105.vo.MpBomHVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpBomHDAO.java
 * @Description : MpBomH DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpBomHDAO")
public class MpBomHDAO extends TMMPPBaseDAO {

	/**
	 * mp_bom_h을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpBomHVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpBomH(EgovMap vo) throws Exception {
        return (String)insert("MpBomHDAO.insertMpBomH", vo);
    }

    /**
	 * mp_bom_h을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpBomHVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpBomH(MpBomHVO vo) throws Exception {
        update("MpBomHDAO.updateMpBomH", vo);
    }

    /**
	 * mp_bom_h을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpBomHVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpBomH(MpBomHVO vo) throws Exception {
        delete("MpBomHDAO.deleteMpBomH", vo);
    }

    /**
	 * mp_bom_h을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpBomHVO
	 * @return 조회한 mp_bom_h
	 * @exception Exception
	 */
    public EgovMap selectMpBomH(EgovMap vo) throws Exception {
        return (EgovMap) select("MpBomHDAO.selectMpBomH_S", vo);
    }

    /**
	 * mp_bom_h 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_bom_h 목록
	 * @exception Exception
	 */
    public List<?> selectMpBomHList(MpBomHDefaultVO searchVO) throws Exception {
        return list("MpBomHDAO.selectMpBomHList_D", searchVO);
    }

    /**
	 * mp_bom_h 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_bom_h 총 갯수
	 * @exception
	 */
    public int selectMpBomHListTotCnt(MpBomHDefaultVO searchVO) {
        return (Integer)select("MpBomHDAO.selectMpBomHListTotCnt_S", searchVO);
    }

    public int selectLastBomVer(EgovMap searchVO) {
        return (Integer)select("MpBomHDAO.selectLastBomVer", searchVO);
    }
    
    public int updateMpBomHVerNotUse(EgovMap vo) throws Exception {
        return update("MpBomHDAO.updateMpBomHVerNotUse", vo);
    }
    

    public List<?> selectPP0106MpBomDList(MpBomHDefaultVO searchVO) throws Exception {
        return list("MpBomHDAO.selectPP0106MpBomHList_D", searchVO);
    }

    /**
	 * mp_bom_d 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_bom_d 총 갯수
	 * @exception
	 */
    public int selectPP0106MpBomHListTotCnt(MpBomHDefaultVO searchVO) {
        return (Integer)select("MpBomHDAO.selectPP0106MpBomHListTotCnt_S", searchVO);
    }
    
    public int checkDupMpBomHCnt(MpBomHDefaultVO searchVO) {
        return (Integer)select("MpBomHDAO.checkDupMpBomHCnt", searchVO);
    }
}
