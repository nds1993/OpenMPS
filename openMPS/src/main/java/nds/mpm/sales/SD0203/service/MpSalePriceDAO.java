package nds.mpm.sales.SD0203.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.sales.SD0203.vo.MpSalePriceDefaultVO;
import nds.mpm.sales.SD0203.vo.MpSalePriceVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpSalePriceDAO.java
 * @Description : MpSalePrice DAO Class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpSalePriceDAO")
public class MpSalePriceDAO extends TMMPPBaseDAO {

	/**
	 * mp_sale_price을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpSalePriceVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpSalePrice(EgovMap vo) throws Exception {
        return (String)insert("MpSalePriceDAO.insertMpSalePrice", vo);
    }

    /**
	 * mp_sale_price을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpSalePriceVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpSalePrice(EgovMap vo) throws Exception {
        return update("MpSalePriceDAO.updateMpSalePrice", vo);
    }
    
    public int updateCancelApproMpSalePrice(EgovMap vo) throws Exception {
        return update("MpSalePriceDAO.updateCancelApproMpSalePrice", vo);
    }

    /**
	 * mp_sale_price을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpSalePriceVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpSalePrice(EgovMap vo) throws Exception {
    	return delete("MpSalePriceDAO.deleteMpSalePrice", vo);
    }

    /**
	 * mp_sale_price을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpSalePriceVO
	 * @return 조회한 mp_sale_price
	 * @exception Exception
	 */
    public EgovMap selectMpSalePrice(EgovMap vo) throws Exception {
        return (EgovMap) select("MpSalePriceDAO.selectMpSalePrice_S", vo);
    }

    /**
	 * mp_sale_price 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_sale_price 목록
	 * @exception Exception
	 */
    public List<?> selectMpSalePriceList(MpSalePriceDefaultVO searchVO) throws Exception {
        return list("MpSalePriceDAO.selectMpSalePriceList_D", searchVO);
    }
    
    public List<?> selectNewMpSalePriceList(MpSalePriceDefaultVO searchVO) throws Exception {
        return list("MpSalePriceDAO.selectNewMpSalePriceList_D", searchVO);
    }

    /**
	 * mp_sale_price 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_sale_price 총 갯수
	 * @exception
	 */
    public int selectMpSalePriceListTotCnt(MpSalePriceDefaultVO searchVO) {
        return (Integer)select("MpSalePriceDAO.selectMpSalePriceListTotCnt_S", searchVO);
    }
    
    /**
     * SD0203
     * 승인요청
	 * mp_sale_price을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpSalePriceVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateReqApproMpSalePrice(EgovMap vo) throws Exception {
        return update("MpSalePriceDAO.updateReqApproMpSalePrice", vo);
    }
    
    /**
     * SD0206
	 * 승인결재처리.
	 * @param vo - 수정할 정보가 담긴 MpSalePriceVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateSD0206MpSalePrice(EgovMap vo) throws Exception {
        return update("MpSalePriceDAO.updateSD0206MpSalePrice", vo);
    }
    
    public List<?> selectSD0206MpSalePriceList(MpSalePriceDefaultVO searchVO) throws Exception {
        return list("MpSalePriceDAO.selectSD0206MpSalePriceList_D", searchVO);
    }
    
    /**
	 * mp_sale_price 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_sale_price 총 갯수
	 * @exception
	 */
    public int selectSD0206MpSalePriceListTotCnt(MpSalePriceDefaultVO searchVO) {
        return (Integer)select("MpSalePriceDAO.selectSD0206MpSalePriceListTotCnt_S", searchVO);
    }
    
    public int checkNewStrtDateCount(EgovMap vo) throws Exception {
        return (Integer)select("MpSalePriceDAO.checkNewStrtDateCount", vo);
    }
    
    public int updateSD0206ApproRejectMpSalePrice(EgovMap vo) throws Exception {
        return update("MpSalePriceDAO.updateSD0206ApproRejectMpSalePrice", vo);
    }
}
