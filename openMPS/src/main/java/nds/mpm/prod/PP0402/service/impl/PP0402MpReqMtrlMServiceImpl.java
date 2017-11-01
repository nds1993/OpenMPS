package nds.mpm.prod.PP0402.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import nds.mpm.prod.PP0402.service.PP0402MpReqMtrlMService;
import nds.mpm.prod.PP0401.vo.MpReqMtrlMDefaultVO;
import nds.mpm.prod.PP0401.vo.MpReqMtrlMVO;
import nds.mpm.prod.PP0402.service.PP0402MpReqMtrlMDAO;

/**
 * @Class Name : MpReqMtrlMServiceImpl.java
 * @Description : MpReqMtrlM Business Implement class
 * @Modification Information
 *
 * @author 123
 * @since 123
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PP0402MpReqMtrlMService")
public class PP0402MpReqMtrlMServiceImpl extends EgovAbstractServiceImpl implements
        PP0402MpReqMtrlMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PP0402MpReqMtrlMServiceImpl.class);

    @Resource(name="PP0402mpReqMtrlMDAO")
    private PP0402MpReqMtrlMDAO mpReqMtrlMDAO;
    
    /**
	 * mp_req_mtrl_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_req_mtrl_m 목록
	 * @exception Exception
	 */
    public List<?> selectProcodeMtrlCodeMpReqMtrlMList_D(MpReqMtrlMDefaultVO searchVO) throws Exception {
        return mpReqMtrlMDAO.selectProcodeMtrlCodeMpReqMtrlMList_D(searchVO);
    }

    /**
	 * mp_req_mtrl_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_req_mtrl_m 총 갯수
	 * @exception
	 */
    public int selectProcodeMtrlCodeMpReqMtrlMListTotCnt_S(MpReqMtrlMDefaultVO searchVO) {
		return mpReqMtrlMDAO.selectProcodeMtrlCodeMpReqMtrlMListTotCnt_S(searchVO);
	}
    
    public List<?> selectMtrlCodeMpReqMtrlMList_D(MpReqMtrlMDefaultVO searchVO) throws Exception {
        return mpReqMtrlMDAO.selectMtrlCodeMpReqMtrlMList_D(searchVO);
    }

    /**
	 * mp_req_mtrl_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_req_mtrl_m 총 갯수
	 * @exception
	 */
    public int selectMtrlCodeMpReqMtrlMListTotCnt_S(MpReqMtrlMDefaultVO searchVO) {
		return mpReqMtrlMDAO.selectMtrlCodeMpReqMtrlMListTotCnt_S(searchVO);
	}

}
