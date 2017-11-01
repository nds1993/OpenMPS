package nds.mpm.sales.SD0205.service;

import java.util.List;

import nds.mpm.sales.SD0205.vo.MpDiscPriceTitleDefaultVO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceTitleVO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpDiscPriceTitleDAO.java
 * @Description : MpDiscPriceTitle DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpDiscPriceTitleDAO")
public class MpDiscPriceTitleDAO extends EgovAbstractDAO {

	/**
	 * mp_disc_price_title을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDiscPriceTitleVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpDiscPriceTitle(EgovMap vo) throws Exception {
        return (String)insert("mpDiscPriceTitleDAO.insertMpDiscPriceTitle", vo);
    }

    /**
	 * mp_disc_price_title을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDiscPriceTitleVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpDiscPriceTitle(EgovMap vo) throws Exception {
        return update("mpDiscPriceTitleDAO.updateMpDiscPriceTitle", vo);
    }

    /**
	 * mp_disc_price_title을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDiscPriceTitleVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpDiscPriceTitle(EgovMap vo) throws Exception {
    	return delete("mpDiscPriceTitleDAO.deleteMpDiscPriceTitle", vo);
    }

    /**
	 * mp_disc_price_title을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDiscPriceTitleVO
	 * @return 조회한 mp_disc_price_title
	 * @exception Exception
	 */
    public EgovMap selectMpDiscPriceTitle(EgovMap vo) throws Exception {
        return (EgovMap) select("mpDiscPriceTitleDAO.selectMpDiscPriceTitle_S", vo);
    }

    /**
	 * mp_disc_price_title 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_disc_price_title 목록
	 * @exception Exception
	 */
    public List<?> selectMpDiscPriceTitleList(MpDiscPriceTitleDefaultVO searchVO) throws Exception {
        return list("mpDiscPriceTitleDAO.selectMpDiscPriceTitleList_D", searchVO);
    }

    /**
	 * mp_disc_price_title 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_disc_price_title 총 갯수
	 * @exception
	 */
    public int selectMpDiscPriceTitleListTotCnt(MpDiscPriceTitleDefaultVO searchVO) {
        return (Integer)select("mpDiscPriceTitleDAO.selectMpDiscPriceTitleListTotCnt_S", searchVO);
    }

    /**
     * 승인요청시 
     * 거래처별 매출액, 입금액,잔액을 업데이트 한다.
     * 
     * */
    public MpDiscPriceTitleVO selectMpDiscPriceTitlePeriodAmt(MpDiscPriceTitleVO vo) throws Exception {
        return (MpDiscPriceTitleVO)select("mpDiscPriceTitleDAO.selectMpDiscPriceTitlePeriodAmt", vo);
    }
    public int updateMpDiscPriceApproRequest(MpDiscPriceTitleVO vo) throws Exception {
        return update("mpDiscPriceTitleDAO.updateMpDiscPriceApproRequest", vo);
    }
    
    public int deleteMpDiscPriceOldTitle(EgovMap vo) throws Exception {
    	return delete("mpDiscPriceTitleDAO.deleteMpDiscPriceOldTitle", vo);
    }
}
