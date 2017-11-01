package nds.mpm.sales.SD9009.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.sales.SD9009.service.SD9009MpOrderDDAO;
import nds.mpm.sales.SD9009.service.SD9009MpOrderDService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpOrderDServiceImpl.java
 * @Description : MpOrderD Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("SD9009MpOrderDService")
public class SD9009MpOrderDServiceImpl extends EgovAbstractServiceImpl implements
SD9009MpOrderDService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD9009MpOrderDServiceImpl.class);

    @Resource(name="SD9009MpOrderDDAO")
    private SD9009MpOrderDDAO mpOrderDDAO;

    /**
	 * mp_order_d 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderDList(EgovMap searchVO) throws Exception {
        return mpOrderDDAO.selectMpOrderDList(searchVO);
    }
    
    public List<?> selectProcodeMpOrderDList(EgovMap searchVO) throws Exception {
    	 return mpOrderDDAO.selectProcodeMpOrderDList(searchVO);
    }
    
}
