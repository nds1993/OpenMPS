package nds.mpm.sales.SD0202.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.sales.SD0202.vo.MpStndPriceDefaultVO;
import nds.mpm.sales.SD0202.vo.MpStndPriceVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpStndPriceDAO.java
 * @Description : MpStndPrice DAO Class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("mpStndPriceDAO")
public class MpStndPriceDAO extends TMMPPBaseDAO {

	/**
	 * mp_stnd_price을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpStndPriceVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpStndPrice(EgovMap vo) throws Exception {
        return (String)insert("MpStndPriceDAO.insertMpStndPrice", vo);
    }

    /**
	 * mp_stnd_price을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpStndPriceVO
	 * @return void형
	 * @exception Exception
	 */
    public int updateMpStndPrice(EgovMap vo) throws Exception {
    	return update("MpStndPriceDAO.updateMpStndPrice", vo);
    }

    /**
	 * mp_stnd_price을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpStndPriceVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpStndPrice(EgovMap vo) throws Exception {
        return delete("MpStndPriceDAO.deleteMpStndPrice", vo);
    }

    /**
	 * mp_stnd_price을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpStndPriceVO
	 * @return 조회한 mp_stnd_price
	 * @exception Exception
	 */
    public MpStndPriceVO selectMpStndPrice(EgovMap vo) throws Exception {
        return (MpStndPriceVO) select("MpStndPriceDAO.selectMpStndPrice_S", vo);
    }

    /**
	 * mp_stnd_price 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_stnd_price 목록
	 * @exception Exception
	 */
    public List<?> selectMpStndPriceList(MpStndPriceDefaultVO searchVO) throws Exception {
        return list("MpStndPriceDAO.selectMpStndPriceList_D", searchVO);
    }

    /**
	 * mp_stnd_price 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_stnd_price 총 갯수
	 * @exception
	 */
    public int selectMpStndPriceListTotCnt(MpStndPriceDefaultVO searchVO) {
        return (Integer)select("MpStndPriceDAO.selectMpStndPriceListTotCnt_S", searchVO);
    }
    
    public List<?> selectMpStndPricePeriodList(MpStndPriceDefaultVO searchVO) throws Exception {
        return list("MpStndPriceDAO.selectMpStndPricePeriodList", searchVO);
    }
    
    /**
	 * 단가기간 중복체크
	 * @exception
	 */
    public int checkDupPeriodMpStndPriceTotCnt(EgovMap searchVO) {
        return (Integer)select("MpStndPriceDAO.checkDupPeriodMpStndPriceTotCnt", searchVO);
    }
}
