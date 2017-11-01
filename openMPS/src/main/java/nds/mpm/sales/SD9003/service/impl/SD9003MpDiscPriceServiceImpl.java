package nds.mpm.sales.SD9003.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.sales.SD0205.vo.MpDiscPriceDefaultVO;
import nds.mpm.sales.SD9003.service.SD9003MpDiscPriceDAO;
import nds.mpm.sales.SD9003.service.SD9003MpDiscPriceService;

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

@Service("SD9003MpDiscPriceService")
public class SD9003MpDiscPriceServiceImpl extends EgovAbstractServiceImpl implements
SD9003MpDiscPriceService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD9003MpDiscPriceServiceImpl.class);

    @Resource(name="SD9003MpDiscPriceDAO")
    private SD9003MpDiscPriceDAO mpDiscPriceDAO;

    /**
	 * mp_disc_price 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_disc_price 목록
	 * @exception Exception
	 */
    public List<?> selectMpDiscPriceList(MpDiscPriceDefaultVO searchVO) throws Exception {
        return mpDiscPriceDAO.selectMpDiscPriceList(searchVO);
    }

    /**
	 * mp_disc_price 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_disc_price 총 갯수
	 * @exception
	 */
    public int selectMpDiscPriceListTotCnt(MpDiscPriceDefaultVO searchVO) {
		return mpDiscPriceDAO.selectMpDiscPriceListTotCnt(searchVO);
	}
}
