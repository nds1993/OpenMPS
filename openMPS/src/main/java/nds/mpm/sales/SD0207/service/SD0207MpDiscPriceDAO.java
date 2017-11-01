package nds.mpm.sales.SD0207.service;

import java.util.List;

import nds.mpm.sales.SD0205.vo.MpDiscPriceDefaultVO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceTitleVO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpDiscPriceDAO.java
 * @Description : MpDiscPrice DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("SD0207MpDiscPriceDAO")
public class SD0207MpDiscPriceDAO extends EgovAbstractDAO {

	/**
	 * mp_disc_price을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDiscPriceVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpDiscPrice(MpDiscPriceVO vo) throws Exception {
        return (String)insert("SD0207MpDiscPriceDAO.insertMpDiscPrice_S", vo);
    }

    /**
	 * mp_disc_price을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDiscPriceVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpDiscPrice(MpDiscPriceVO vo) throws Exception {
        update("SD0207MpDiscPriceDAO.updateMpDiscPrice_S", vo);
    }

    /**
	 * mp_disc_price을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDiscPriceVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpDiscPrice(MpDiscPriceVO vo) throws Exception {
        delete("SD0207MpDiscPriceDAO.deleteMpDiscPrice_S", vo);
    }

    /**
	 * mp_disc_price을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDiscPriceVO
	 * @return 조회한 mp_disc_price
	 * @exception Exception
	 */
    public MpDiscPriceVO selectMpDiscPrice(MpDiscPriceVO vo) throws Exception {
        return (MpDiscPriceVO) select("SD0207MpDiscPriceDAO.selectMpDiscPrice_S", vo);
    }

    /**
	 * mp_disc_price 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_disc_price 목록
	 * @exception Exception
	 */
    public List<?> selectMpDiscPriceTitleList(MpDiscPriceTitleVO searchVO) throws Exception {
        return list("SD0207MpDiscPriceDAO.selectMpDiscPriceTitleList", searchVO);
    }

    /**
	 * mp_disc_price 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_disc_price 총 갯수
	 * @exception
	 */
    public int selectMpDiscPriceListTotCnt(MpDiscPriceDefaultVO searchVO) {
        return (Integer)select("SD0207MpDiscPriceDAO.selectMpDiscPriceListTotCnt", searchVO);
    }

    public int updateMpDiscPriceConfirm(EgovMap vo) throws Exception {
        return update("SD0207MpDiscPriceDAO.updateMpDiscPriceConfirm", vo);
    }
    
    public List<?> selectMpDiscPriceDetailList(MpDiscPriceTitleVO searchVO) throws Exception {
        return list("SD0207MpDiscPriceDAO.selectMpDiscPriceDetailList", searchVO);
    }
    
}
