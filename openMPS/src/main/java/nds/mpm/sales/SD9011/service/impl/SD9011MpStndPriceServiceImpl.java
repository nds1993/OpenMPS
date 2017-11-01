package nds.mpm.sales.SD9011.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.SearchCommonVO;
import nds.mpm.sales.SD9011.service.SD9011MpStndPriceDAO;
import nds.mpm.sales.SD9011.service.SD9011MpStndPriceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : MpStndPriceServiceImpl.java
 * @Description : MpStndPrice Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("SD9011mpStndPriceService")
public class SD9011MpStndPriceServiceImpl extends EgovAbstractServiceImpl implements
	SD9011MpStndPriceService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD9011MpStndPriceServiceImpl.class);

    @Resource(name="SD9011mpStndPriceDAO")
    private SD9011MpStndPriceDAO mpStndPriceDAO;
    /**
	 * mp_stnd_price 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_stnd_price 목록
	 * @exception Exception
	 */
    public List<?> selectMpStndPriceList(SearchCommonVO searchVO) throws Exception {
        return mpStndPriceDAO.selectMpStndPriceList(searchVO);
    }

    /**
	 * mp_stnd_price 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_stnd_price 총 갯수
	 * @exception
	 */
    public int selectMpStndPriceListTotCnt(SearchCommonVO searchVO) {
		return mpStndPriceDAO.selectMpStndPriceListTotCnt(searchVO);
	}
    public List<?> selectProdMpStndPriceList(SearchCommonVO searchVO) throws Exception {
        return mpStndPriceDAO.selectProdMpStndPriceList(searchVO);
    }
    
}
