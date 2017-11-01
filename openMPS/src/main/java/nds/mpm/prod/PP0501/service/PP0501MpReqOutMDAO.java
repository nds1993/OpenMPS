package nds.mpm.prod.PP0501.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.prod.PP0501.vo.MpReqOutMVO;
import nds.mpm.prod.PP0501.vo.MpReqOutMDefaultVO;

/**
 * @Class Name : MpReqOutMDAO.java
 * @Description : MpReqOutM DAO Class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PP0501mpReqOutMDAO")
public class PP0501MpReqOutMDAO extends EgovAbstractDAO {

	
    /**
	 * mp_req_out_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_req_out_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpReqOutMList(MpReqOutMDefaultVO searchVO) throws Exception {
        return list("PP0501mpReqOutMDAO.selectMpReqOutMList_D", searchVO);
    }

    /**
	 * mp_req_out_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_req_out_m 총 갯수
	 * @exception
	 */
    public int selectMpReqOutMListTotCnt(MpReqOutMDefaultVO searchVO) {
        return (Integer)select("PP0501mpReqOutMDAO.selectMpReqOutMListTotCnt_S", searchVO);
    }

}
