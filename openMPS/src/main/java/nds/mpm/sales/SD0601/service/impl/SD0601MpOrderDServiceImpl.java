package nds.mpm.sales.SD0601.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.sales.SD0601.service.SD0601MpOrderDDAO;
import nds.mpm.sales.SD0601.service.SD0601MpOrderDService;
import nds.mpm.sales.SD0601.vo.MpOrderDVO;

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

@Service("SD0601mpOrderDService")
public class SD0601MpOrderDServiceImpl extends EgovAbstractServiceImpl implements
        SD0601MpOrderDService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0601MpOrderDServiceImpl.class);

    @Resource(name="SD0601mpOrderDDAO")
    private SD0601MpOrderDDAO mpOrderDDAO;

    /**
	 * mp_order_d 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderDList(MpOrderDVO searchVO) throws Exception {
        return mpOrderDDAO.selectMpOrderDList(searchVO);
    }

    /**
	 * mp_order_d 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 총 갯수
	 * @exception
	 */
    public int selectMpOrderDListTotCnt(MpOrderDVO searchVO) {
		return mpOrderDDAO.selectMpOrderDListTotCnt(searchVO);
	}
    
}
