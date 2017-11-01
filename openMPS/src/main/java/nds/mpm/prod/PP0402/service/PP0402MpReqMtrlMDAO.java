package nds.mpm.prod.PP0402.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.prod.PP0401.vo.MpReqMtrlMVO;
import nds.mpm.prod.PP0401.vo.MpReqMtrlMDefaultVO;

/**
 * @Class Name : MpReqMtrlMDAO.java
 * @Description : MpReqMtrlM DAO Class
 * @Modification Information
 *
 * @author 123
 * @since 123
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PP0402mpReqMtrlMDAO")
public class PP0402MpReqMtrlMDAO extends EgovAbstractDAO {

	 /**
	 * mp_req_mtrl_m 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_req_mtrl_m 목록
	 * @exception Exception
	 */
    public List<?> selectProcodeMtrlCodeMpReqMtrlMList_D(MpReqMtrlMDefaultVO searchVO) throws Exception {
        return list("PP0402mpReqMtrlMDAO.selectProcodeMtrlCodeMpReqMtrlMList_D", searchVO);
    }

    /**
	 * mp_req_mtrl_m 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_req_mtrl_m 총 갯수
	 * @exception
	 */
    public int selectProcodeMtrlCodeMpReqMtrlMListTotCnt_S(MpReqMtrlMDefaultVO searchVO) {
        return (Integer)select("PP0402mpReqMtrlMDAO.selectProcodeMtrlCodeMpReqMtrlMListTotCnt_S", searchVO);
    }

    public List<?> selectMtrlCodeMpReqMtrlMList_D(MpReqMtrlMDefaultVO searchVO) throws Exception {
        return list("PP0402mpReqMtrlMDAO.selectMtrlCodeMpReqMtrlMList_D", searchVO);
    }
    public int selectMtrlCodeMpReqMtrlMListTotCnt_S(MpReqMtrlMDefaultVO searchVO) {
        return (Integer)select("PP0402mpReqMtrlMDAO.selectMtrlCodeMpReqMtrlMListTotCnt_S", searchVO);
    }
}
