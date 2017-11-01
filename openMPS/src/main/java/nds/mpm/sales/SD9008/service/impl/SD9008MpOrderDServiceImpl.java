package nds.mpm.sales.SD9008.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.sales.SD0405.vo.MpOrderHVO;
import nds.mpm.sales.SD9008.service.SD9008MpOrderDDAO;
import nds.mpm.sales.SD9008.service.SD9008MpOrderDService;

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

@Service("SD9008MpOrderDService")
public class SD9008MpOrderDServiceImpl extends EgovAbstractServiceImpl implements
SD9008MpOrderDService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD9008MpOrderDServiceImpl.class);

    @Resource(name="SD9008MpOrderDDAO")
    private SD9008MpOrderDDAO mpOrderDDAO;

    public List<?> selectMpOrderHList(EgovMap searchVO) throws Exception {
    	return mpOrderDDAO.selectMpOrderHList(searchVO);
    }
    /**
	 * mp_order_d 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderDList(EgovMap searchVO) throws Exception {
        return mpOrderDDAO.selectMpOrderDList(searchVO);
    }
    
}
