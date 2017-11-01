package nds.mpm.sales.SD9008.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpOrderDDAO.java
 * @Description : MpOrderD DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("SD9008MpOrderDDAO")
public class SD9008MpOrderDDAO extends EgovAbstractDAO {

	public List<?> selectMpOrderHList(EgovMap searchVO) throws Exception {
        return list("SD9008MpOrderDDAO.selectMpOrderHList_D", searchVO);
    }
    /**
	 * mp_order_d 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderDList(EgovMap searchVO) throws Exception {
        return list("SD9008MpOrderDDAO.selectMpOrderDList_D", searchVO);
    }

}
