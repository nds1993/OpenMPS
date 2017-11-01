package nds.mpm.sales.SD0608.service;

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

@Repository("SD0608mpOrderDDAO")
public class SD0608MpOrderDDAO extends EgovAbstractDAO {

    /**
	 * mp_order_d 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderDList(EgovMap searchVO) throws Exception {
        return list("SD0608mpOrderDDAO.selectMpOrderDList_D", searchVO);
    }
    
    /**
	 * mp_order_d 목록 탭을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderDTab1List(EgovMap searchVO) throws Exception {
        return list("SD0608mpOrderDDAO.selectMpOrderDTab1List_D", searchVO);
    }
    
    /**
   	 * mp_order_d 목록 탭을 조회한다.
   	 * @param searchMap - 조회할 정보가 담긴 Map
   	 * @return mp_order_d 목록
   	 * @exception Exception
   	 */
	public List<?> selectMpOrderDTab2List(EgovMap searchVO) throws Exception {
	    return list("SD0608mpOrderDDAO.selectMpOrderDTab2List_D", searchVO);
	}
	
	/**
   	 * mp_order_d 목록 탭을 조회한다.
   	 * @param searchMap - 조회할 정보가 담긴 Map
   	 * @return mp_order_d 목록
   	 * @exception Exception
   	 */
	public List<?> selectMpOrderDTab3List(EgovMap searchVO) throws Exception {
	    return list("SD0608mpOrderDDAO.selectMpOrderDTab3List_D", searchVO);
	}
    
}
