package nds.mpm.prod.PP0501.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.prod.PP0501.service.PP0501MpReqOutMService;
import nds.mpm.prod.PP0501.vo.MpReqOutMDefaultVO;
import nds.mpm.prod.PP0501.vo.MpReqOutMVO;
import nds.mpm.prod.PP0501.service.PP0501MpReqOutMDAO;

/**
 * @Class Name : MpReqOutMServiceImpl.java
 * @Description : MpReqOutM Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PP0501mpReqOutMService")
public class PP0501MpReqOutMServiceImpl extends EgovAbstractServiceImpl implements
        PP0501MpReqOutMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PP0501MpReqOutMServiceImpl.class);

    @Resource(name="PP0501mpReqOutMDAO")
    private PP0501MpReqOutMDAO mpReqOutMDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpReqOutMIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

    /**
	 * mp_req_out_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_req_out_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpReqOutMList(MpReqOutMDefaultVO searchVO) throws Exception {
        return mpReqOutMDAO.selectMpReqOutMList(searchVO);
    }

    /**
	 * mp_req_out_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_req_out_m 총 갯수
	 * @exception
	 */
    public int selectMpReqOutMListTotCnt(MpReqOutMDefaultVO searchVO) {
		return mpReqOutMDAO.selectMpReqOutMListTotCnt(searchVO);
	}
    
}
