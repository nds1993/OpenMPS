package nds.mpm.prod.PP0801.service;

import java.util.List;

import nds.mpm.prod.PP0801.vo.MpPighisBuyMDefaultVO;
import nds.mpm.prod.PP0801.vo.MpPighisBuyMVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPighisBuyMDAO.java
 * @Description : MpPighisBuyM DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpPighisBuyMDAO")
public class MpPighisBuyMDAO extends EgovAbstractDAO {

	/**
	 * mp_pighis_buy_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPighisBuyMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpPighisBuyM(EgovMap vo) throws Exception {
        return (String)insert("mpPighisBuyMDAO.insertMpPighisBuyM", vo);
    }

    /**
	 * mp_pighis_buy_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPighisBuyMVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpPighisBuyM(EgovMap vo) throws Exception {
    	return update("mpPighisBuyMDAO.updateMpPighisBuyM", vo);
    }

    /**
	 * mp_pighis_buy_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPighisBuyMVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpPighisBuyM(EgovMap vo) throws Exception {
        return delete("mpPighisBuyMDAO.deleteMpPighisBuyM", vo);
    }

    /**
	 * mp_pighis_buy_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPighisBuyMVO
	 * @return 조회한 mp_pighis_buy_m
	 * @exception Exception
	 */
    public MpPighisBuyMVO selectMpPighisBuyM(EgovMap vo) throws Exception {
        return (MpPighisBuyMVO) select("mpPighisBuyMDAO.selectMpPighisBuyM_S", vo);
    }

    /**
	 * mp_pighis_buy_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pighis_buy_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpPighisBuyMList(MpPighisBuyMVO searchVO) throws Exception {
        return list("mpPighisBuyMDAO.selectMpPighisBuyMList_D", searchVO);
    }

    /**
	 * mp_pighis_buy_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pighis_buy_m 총 갯수
	 * @exception
	 */
    public int selectMpPighisBuyMListTotCnt(MpPighisBuyMVO searchVO) {
        return (Integer)select("mpPighisBuyMDAO.selectMpPighisBuyMListTotCnt_S", searchVO);
    }

    /**
	 * mp_pighis_buy_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPighisBuyMVO
	 * @return 조회한 mp_pighis_buy_m
	 * @exception Exception
	 */
    public EgovMap selectMpPighisBuyMBuyTypeInfo(EgovMap vo) throws Exception {
        return (EgovMap) select("mpPighisBuyMDAO.selectMpPighisBuyMBuyTypeInfo", vo);
    }
    
    /**
	 * mp_pighis_buy_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_pighis_buy_m 목록
	 * @exception Exception
	 */
    public List<EgovMap> selectMpPighisBuyMSendFormatList(MpPighisBuyMVO searchVO) throws Exception {
        return (List<EgovMap>)list("mpPighisBuyMDAO.selectMpPighisBuyMSendFormatList", searchVO);
    }
    
    /**
	 * mp_pighis_buy_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPighisBuyMVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateApiTimeMpPighisBuyM(EgovMap vo) throws Exception {
        return update("mpPighisBuyMDAO.updateApiTimeMpPighisBuyM", vo);
    }
    
    public int checkDupBuyDateMpPighisBuyM(MpPighisBuyMVO searchVO) {
        return (Integer)select("mpPighisBuyMDAO.checkDupBuyDateMpPighisBuyM_S", searchVO);
    }
    public int deleteDupMpPighisBuyM(MpPighisBuyMVO searchVO) {
        return delete("mpPighisBuyMDAO.deleteDupMpPighisBuyM", searchVO);
    }
    public String selectBuyTypeMpPighisBuyM_S(MpPighisBuyMVO searchVO) {
        return (String)select("mpPighisBuyMDAO.selectBuyTypeMpPighisBuyM_S", searchVO);
    }
}
