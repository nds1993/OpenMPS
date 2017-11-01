package nds.mpm.sales.SD1001.service;

import java.util.List;

import nds.mpm.sales.SD0405.vo.MpOrderHVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

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

@Repository("SD1001MpOrderHDAO")
public class SD1001MpDiscPriceDAO extends EgovAbstractDAO {

	public List<?> selectMpDiscPriceList(MpOrderHVO searchVO) throws Exception {
        return list("SD1001MpOrderDDAO.selectMpOrderHeadList_D", searchVO);
    }
    /**
	 * mp_disc_price 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_disc_price 총 갯수
	 * @exception
	 */
    public int selectMpDiscPriceListTotCnt(MpOrderHVO searchVO) {
        return (Integer)select("SD1001MpOrderDDAO.selectMpOrderHeadListTotCnt_S", searchVO);
    }

    public List<?> selectMpOrderDetailList(MpOrderHVO searchVO) throws Exception {
        return list("SD1001MpOrderDDAO.selectMpOrderDetailList_D", searchVO);
    }
}
