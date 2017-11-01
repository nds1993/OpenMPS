package nds.mpm.sales.SD9004.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.sales.SD0405.vo.MpOrderHVO;
import nds.mpm.sales.SD9004.service.SD9004MpOrderDDAO;
import nds.mpm.sales.SD9004.service.SD9004MpOrderDService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

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

@Service("SD9004MpOrderDService")
public class SD9004MpOrderDServiceImpl extends EgovAbstractServiceImpl implements
SD9004MpOrderDService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD9004MpOrderDServiceImpl.class);

    @Resource(name="SD9004MpOrderDDAO")
    private SD9004MpOrderDDAO mpOrderDDAO;

    /**
	 * mp_order_d 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderDList(MpOrderHVO searchVO) throws Exception {
        return mpOrderDDAO.selectMpOrderDList(searchVO);
    }

    /**
	 * mp_order_d 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 총 갯수
	 * @exception
	 */
    public int selectMpOrderDListTotCnt(MpOrderHVO searchVO) {
		return mpOrderDDAO.selectMpOrderDListTotCnt(searchVO);
	}
    
    
    
}
