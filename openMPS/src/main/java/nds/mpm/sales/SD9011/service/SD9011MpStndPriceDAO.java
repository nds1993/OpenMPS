package nds.mpm.sales.SD9011.service;

import java.util.List;

import nds.mpm.common.vo.SearchCommonVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @Class Name : MpStndPriceDAO.java
 * @Description : MpStndPrice DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("SD9011mpStndPriceDAO")
public class SD9011MpStndPriceDAO extends EgovAbstractDAO {

    /**
	 * mp_stnd_price 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_stnd_price 목록
	 * @exception Exception
	 */
    public List<?> selectMpStndPriceList(SearchCommonVO searchVO) throws Exception {
        return list("SD9011mpStndPriceDAO.selectMpStndPriceList_D", searchVO);
    }

    /**
	 * mp_stnd_price 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_stnd_price 총 갯수
	 * @exception
	 */
    public int selectMpStndPriceListTotCnt(SearchCommonVO searchVO) {
        return (Integer)select("SD9011mpStndPriceDAO.selectMpStndPriceListTotCnt_S", searchVO);
    }
    
    public List<?> selectProdMpStndPriceList(SearchCommonVO searchVO) throws Exception {
        return list("SD9011mpStndPriceDAO.selectProdMpStndPriceList_D", searchVO);
    }

}
