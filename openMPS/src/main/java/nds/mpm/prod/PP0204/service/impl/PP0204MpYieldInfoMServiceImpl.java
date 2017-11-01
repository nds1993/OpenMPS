package nds.mpm.prod.PP0204.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.prod.PP0204.service.PP0204MpYieldInfoMDAO;
import nds.mpm.prod.PP0204.service.PP0204MpYieldInfoMService;
import nds.mpm.prod.PP0204.vo.MpYieldInfoMDefaultVO;
import nds.mpm.prod.PP0204.vo.MpYieldInfoMVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpYieldInfoMServiceImpl.java
 * @Description : MpYieldInfoM Business Implement class
 * @Modification Information
 *
 * @author mm
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PP0204mpYieldInfoMService")
public class PP0204MpYieldInfoMServiceImpl extends EgovAbstractServiceImpl implements
        PP0204MpYieldInfoMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PP0204MpYieldInfoMServiceImpl.class);

    @Resource(name="PP0204mpYieldInfoMDAO")
    private PP0204MpYieldInfoMDAO mpYieldInfoMDAO;

    /**
	 * mp_yield_info_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpYieldInfoMVO
	 * @return 조회한 mp_yield_info_m
	 * @exception Exception
	 */
    public List<?> selectTotalMpYieldInfoM_S(MpYieldInfoMVO vo) throws Exception {    	
        return mpYieldInfoMDAO.selectMpYieldInfoM(vo);
    }

    /**
	 * mp_yield_info_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_yield_info_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpYieldInfoMList(MpYieldInfoMVO searchVO) throws Exception {
        return mpYieldInfoMDAO.selectMpYieldInfoMList(searchVO);
    }

}
