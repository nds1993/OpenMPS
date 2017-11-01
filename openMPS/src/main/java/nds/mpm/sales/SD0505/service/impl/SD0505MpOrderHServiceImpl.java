package nds.mpm.sales.SD0505.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.sales.SD0505.service.SD0505MpOrderHService;
import nds.mpm.sales.SD0505.vo.MpOrderHDefaultVO;
import nds.mpm.sales.SD0505.vo.MpOrderHVO;
import nds.mpm.sales.SD0505.service.SD0505MpOrderHDAO;

/**
 * @Class Name : MpOrderHServiceImpl.java
 * @Description : MpOrderH Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("SD0505mpOrderHService")
public class SD0505MpOrderHServiceImpl extends EgovAbstractServiceImpl implements
        SD0505MpOrderHService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0505MpOrderHServiceImpl.class);

    @Resource(name="SD0505mpOrderHDAO")
    private SD0505MpOrderHDAO mpOrderHDAO;

    /**
	 * mp_order_h 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_h 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderHList(MpOrderHDefaultVO searchVO) throws Exception {
        return mpOrderHDAO.selectMpOrderHList(searchVO);
    }

    /**
	 * mp_order_h 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_h 총 갯수
	 * @exception
	 */
    public int selectMpOrderHListTotCnt(MpOrderHDefaultVO searchVO) {
		return mpOrderHDAO.selectMpOrderHListTotCnt(searchVO);
	}
    public List<?> selectDetailMpOrderHList(MpOrderHDefaultVO searchVO) throws Exception {
        return mpOrderHDAO.selectDetailMpOrderHList(searchVO);
    }

}
