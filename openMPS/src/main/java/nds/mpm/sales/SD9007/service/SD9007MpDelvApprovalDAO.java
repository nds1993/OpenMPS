package nds.mpm.sales.SD9007.service;

import java.util.List;

import nds.mpm.common.service.TMMPPBaseDAO;
import nds.mpm.sales.SD0402.vo.SD0402DefaultVO;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : SD9007DAO.java
 * @Description : SD9007 DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("SD9007DAO")
public class SD9007MpDelvApprovalDAO extends TMMPPBaseDAO {

    /**
	 * mp_delv_approval 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_delv_approval 목록
	 * @exception Exception
	 */
    public List<?> selectSD0403List(SD0402DefaultVO searchVO) throws Exception {
        return list("SD9007DAO.selectSD9007List_D", searchVO);
    }

    /**
	 * mp_delv_approval 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_delv_approval 총 갯수
	 * @exception
	 */
    public int selectSD0403ListTotCnt(SD0402DefaultVO searchVO) {
        return (Integer)select("SD9007DAO.selectSD9007ListTotCnt_S", searchVO);
    }
    
    public List<?> selectSD9007HistList(SD0402DefaultVO searchVO) throws Exception {
        return list("SD9007DAO.selectSD9007HistList", searchVO);
    }
}
