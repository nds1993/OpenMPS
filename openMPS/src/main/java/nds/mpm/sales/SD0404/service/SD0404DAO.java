package nds.mpm.sales.SD0404.service;

import java.util.List;
import java.util.Map;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.sales.SD0405.vo.MpOrderHDefaultVO;
import nds.mpm.sales.SD0405.vo.MpOrderHVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

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

@Repository("SD0404DAO")
public class SD0404DAO extends TMMPPBaseDAO {
	
    /**
	 * mp_order_h 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_h 목록
	 * @exception Exception
	 */
    public List<?> selectSD0404List(MpOrderHDefaultVO searchVO) throws Exception {
        return list("SD0404DAO.selectSD0404List", searchVO);
    }

    /**
	 * mp_order_h 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_h 총 갯수
	 * @exception
	 */
    public int selectSD0404ListTotCnt(MpOrderHDefaultVO searchVO) {
        return (Integer)select("SD0404DAO.selectSD0404ListTotCnt", searchVO);
    }
    
}
