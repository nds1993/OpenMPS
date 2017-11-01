package nds.mpm.sales.SD0505.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.sales.SD0505.vo.MpOrderHVO;
import nds.mpm.sales.SD0505.vo.MpOrderHDefaultVO;

/**
 * @Class Name : MpOrderHDAO.java
 * @Description : MpOrderH DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("SD0505mpOrderHDAO")
public class SD0505MpOrderHDAO extends EgovAbstractDAO {

    /**
	 * mp_order_h 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_h 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderHList(MpOrderHDefaultVO searchVO) throws Exception {
        return list("SD0505mpOrderHDAO.selectMpOrderHList_D", searchVO);
    }

    /**
	 * mp_order_h 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_h 총 갯수
	 * @exception
	 */
    public int selectMpOrderHListTotCnt(MpOrderHDefaultVO searchVO) {
        return (Integer)select("SD0505mpOrderHDAO.selectMpOrderHListTotCnt_S", searchVO);
    }
    
    public List<?> selectDetailMpOrderHList(MpOrderHDefaultVO searchVO) throws Exception {
        return list("SD0505mpOrderHDAO.selectDetailMpOrderHList_D", searchVO);
    }

}
