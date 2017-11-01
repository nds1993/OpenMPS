package nds.mpm.sales.SD1001.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.sales.SD0405.vo.MpOrderHVO;
import nds.mpm.sales.SD1001.service.SD1001MpDiscPriceDAO;
import nds.mpm.sales.SD1001.service.SD1001MpDiscPriceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : MpDiscPriceServiceImpl.java
 * @Description : MpDiscPrice Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("SD1001MpOrderHService")
public class SD1001MpDiscPriceServiceImpl extends EgovAbstractServiceImpl implements
SD1001MpDiscPriceService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD1001MpDiscPriceServiceImpl.class);

    @Resource(name="SD1001MpOrderHDAO")
    private SD1001MpDiscPriceDAO mpDiscPriceDAO;

    /**
	 * mp_disc_price 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_disc_price 목록
	 * @exception Exception
	 */
    public List<?> selectMpDiscPriceList(MpOrderHVO searchVO) throws Exception {
        return mpDiscPriceDAO.selectMpDiscPriceList(searchVO);
    }

    /**
	 * mp_disc_price 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_disc_price 총 갯수
	 * @exception
	 */
    public int selectMpDiscPriceListTotCnt(MpOrderHVO searchVO) {
		return mpDiscPriceDAO.selectMpDiscPriceListTotCnt(searchVO);
	}
    
    public List<?> selectMpOrderDetailList(MpOrderHVO searchVO) throws Exception {
        return mpDiscPriceDAO.selectMpOrderDetailList(searchVO);
    }
}
