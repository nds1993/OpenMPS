package nds.mpm.sales.SD0602.service;

import java.util.List;

import nds.mpm.sales.SD0602.vo.SD0602MpOrderDVO;

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

@Repository("SD0602mpOrderDDAO")
public class SD0602MpOrderDDAO extends EgovAbstractDAO {

    /**
	 * mp_order_d 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    public List<?> selectSD0602MpOrderList_1(SD0602MpOrderDVO searchVO) throws Exception {
    	System.out.println("DAO 호출");
    	return list("SD0602mpOrderDDAO.selectSD0602MpOrderList_1", searchVO);
    }
    
    public List<?> selectSD0602MpOrderList_2(SD0602MpOrderDVO searchVO) throws Exception {
        return list("SD0602mpOrderDDAO.selectSD0602MpOrderList_2", searchVO);
    }
    
    public List<?> selectSD0602MpOrderList_3(SD0602MpOrderDVO searchVO) throws Exception {
        return list("SD0602mpOrderDDAO.selectSD0602MpOrderList_3", searchVO);
    }
    
    public List<?> selectSD0602MpOrderList_4(SD0602MpOrderDVO searchVO) throws Exception {
        return list("SD0602mpOrderDDAO.selectSD0602MpOrderList_4", searchVO);
    }
    
    public List<?> selectSD0602MpOrderList_5(SD0602MpOrderDVO searchVO) throws Exception {
        return list("SD0602mpOrderDDAO.selectSD0602MpOrderList_5", searchVO);
    }
    /**
	 * mp_order_d 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_d 총 갯수
	 * @exception
	 */
//    public int selectMpOrderDListTotCnt(SD0602MpOrderDDefaultVO searchVO) {
//        return (Integer)select("SD0602mpOrderDDAO.selectMpOrderDListTotCnt_S", searchVO);
//    }

}
