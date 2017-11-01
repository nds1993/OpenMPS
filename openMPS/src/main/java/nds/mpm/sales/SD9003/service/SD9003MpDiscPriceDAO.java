package nds.mpm.sales.SD9003.service;

import java.util.List;

import nds.mpm.sales.SD0205.vo.MpDiscPriceDefaultVO;
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

@Repository("SD9003MpDiscPriceDAO")
public class SD9003MpDiscPriceDAO extends EgovAbstractDAO {

	public List<?> selectMpDiscPriceList(MpDiscPriceDefaultVO searchVO) throws Exception {
        return list("SD9003MpDiscPriceDAO.selectMpDiscPriceList", searchVO);
    }
    /**
	 * mp_disc_price 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_disc_price 총 갯수
	 * @exception
	 */
    public int selectMpDiscPriceListTotCnt(MpDiscPriceDefaultVO searchVO) {
        return (Integer)select("SD9003MpDiscPriceDAO.selectMpDiscPriceListTotCnt", searchVO);
    }

}
