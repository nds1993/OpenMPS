package nds.mpm.sales.SD0205.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceDefaultVO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpDiscPriceDAO.java
 * @Description : MpDiscPrice DAO Class
 * @Modification Information
 *
 * @author n
 * @since n
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpDiscPriceDAO")
public class MpDiscPriceDAO extends TMMPPBaseDAO {

	/**
	 * mp_disc_price을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDiscPriceVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpDiscPrice(EgovMap vo) throws Exception {
        return (String)insert("MpDiscPriceDAO.insertMpDiscPrice", vo);
    }

    /**
	 * mp_disc_price을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDiscPriceVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpDiscPrice(MpDiscPriceVO vo) throws Exception {
    	return update("MpDiscPriceDAO.updateMpDiscPrice", vo);
    }

    /**
	 * mp_disc_price을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDiscPriceVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpDiscPrice(EgovMap vo) throws Exception {
    	return delete("MpDiscPriceDAO.deleteMpDiscPrice", vo);
    }

    /**
	 * mp_disc_price을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDiscPriceVO
	 * @return 조회한 mp_disc_price
	 * @exception Exception
	 */
    public MpDiscPriceVO selectMpDiscPrice(EgovMap vo) throws Exception {
        return (MpDiscPriceVO) select("MpDiscPriceDAO.selectMpDiscPrice_S", vo);
    }

    /**
	 * mp_disc_price 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_disc_price 목록
	 * @exception Exception
	 */
    public List<?> selectMpDiscPriceList(MpDiscPriceDefaultVO searchVO) throws Exception {
        return list("MpDiscPriceDAO.selectMpDiscPriceList_M", searchVO);
    }
    
    /**
	 * mp_disc_price 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_disc_price 총 갯수
	 * @exception
	 */
    public int selectMpDiscPriceListTotCnt(MpDiscPriceDefaultVO searchVO) {
        return (Integer)select("MpDiscPriceDAO.selectMpDiscPriceListTotCnt_S", searchVO);
    }
    
    public List<?> selectMpDiscPriceDetailList(MpDiscPriceDefaultVO searchVO) throws Exception {
        return list("MpDiscPriceDAO.selectMpDiscPriceDetailList", searchVO);
    }
    public int selectMpDiscPriceListDetailCnt(MpDiscPriceDefaultVO searchVO) {
        return (Integer)select("MpDiscPriceDAO.selectMpDiscPriceDetailListTotCnt", searchVO);
    }
    
    public List selectMpDiscPriceOldList(EgovMap searchVO) throws Exception {
        return list("MpDiscPriceDAO.selectMpDiscPriceOldList", searchVO);
    }
    
    public int deleteMpDiscOldPrice(EgovMap vo) throws Exception {
    	return delete("MpDiscPriceDAO.deleteMpDiscOldPrice", vo);
    }
    
    
}
