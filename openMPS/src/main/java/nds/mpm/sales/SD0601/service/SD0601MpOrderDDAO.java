package nds.mpm.sales.SD0601.service;

import java.util.List;

import nds.mpm.sales.SD0601.vo.MpOrderDVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

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

@Repository("SD0601mpOrderDDAO")
public class SD0601MpOrderDDAO extends EgovAbstractDAO {

    /**
	 * mp_order_d 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderDList(MpOrderDVO searchVO) throws Exception {
        return list("SD0601mpOrderDDAO.selectMpOrderDList_D", searchVO);    }

    /**
	 * mp_order_d 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_d 총 갯수
	 * @exception
	 */
    public int selectMpOrderDListTotCnt(MpOrderDVO searchVO) {
        return (Integer)select("SD0601mpOrderDDAO.selectMpOrderDListTotCnt_S", searchVO);
    }

}
