package nds.mpm.prod.PP0103.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.prod.PP0103.vo.MpItemMasterMDefaultVO;
import nds.mpm.prod.PP0103.vo.MpItemMasterMVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpItemMasterMDAO.java
 * @Description : MpItemMaster DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.08
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpItemMasterMDAO")
public class MpItemMasterMDAO extends TMMPPBaseDAO {

	/**
	 * select
	 * sequence
	 */
	public String selectSeqPigMpItemMasterMg(EgovMap searchVo) {
        return (String)select("MpItemMasterMDAO.selectSeqPigMpItemMasterM", searchVo);
    }
	public String selectSeqCowMpItemMasterMg(EgovMap searchVo) {
        return (String)select("MpItemMasterMDAO.selectSeqCowMpItemMasterM", searchVo);
    }
	public String selectSeqEtcMpItemMasterMg(EgovMap searchVo) {
        return (String)select("MpItemMasterMDAO.selectSeqEtcMpItemMasterM", searchVo);
    }
	/**
	 * product_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpItemMasterMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpItemMasterM(EgovMap vo) throws Exception {
        return (String)insert("MpItemMasterMDAO.insertMpItemMasterM", vo);
    }

    /**
	 * product_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpItemMasterMVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpItemMasterM(EgovMap vo) throws Exception {
        return update("MpItemMasterMDAO.updateMpItemMasterM", vo);
    }

    /**
	 * product_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpItemMasterMVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpItemMasterM(EgovMap vo) throws Exception {
    	return delete("MpItemMasterMDAO.deleteMpItemMasterM", vo);
    }

    /**
	 * product_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpItemMasterMVO
	 * @return 조회한 product_info
	 * @exception Exception
	 */
    public MpItemMasterMVO selectMpItemMasterM(EgovMap vo) throws Exception {
        return (MpItemMasterMVO) select("MpItemMasterMDAO.selectMpItemMasterM_S", vo);
    }

    /**
	 * product_info 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return product_info 목록
	 * @exception Exception
	 */
    public List<?> selectMpItemMasterMList(MpItemMasterMDefaultVO searchVO) throws Exception {
        return list("MpItemMasterMDAO.selectMpItemMasterMList_D", searchVO);
    }

    /**
	 * product_info 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return product_info 총 갯수
	 * @exception
	 */
    public int selectMpItemMasterMListTotCnt(MpItemMasterMDefaultVO searchVO) {
        return (Integer)select("MpItemMasterMDAO.selectMpItemMasterMListTotCnt_S", searchVO);
    }
    
    /**
   	 * product_info 목록을 조회한다.
   	 * @param searchMap - 조회할 정보가 담긴 Map
   	 * @return product_info 목록
   	 * @exception Exception
   	 */
   public List<?> selectMpItemMasterMCodeList(MpItemMasterMVO searchVO) throws Exception {
       return list("MpItemMasterMDAO.selectMpItemMasterMCodeList_D", searchVO);
   }
   
   public EgovMap callWMSFNSetDcsku(EgovMap searchVO) throws Exception {
       return (EgovMap)select("MpItemMasterMDAO.callWMSFNSetDcsku", searchVO);
   }
   
   public int checkDupMpItemMasterM(EgovMap searchVO) {
       return (Integer)select("MpItemMasterMDAO.checkDupMpItemMasterM", searchVO);
   }
   
}
