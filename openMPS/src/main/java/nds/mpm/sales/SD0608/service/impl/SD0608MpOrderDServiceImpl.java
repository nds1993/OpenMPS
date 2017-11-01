package nds.mpm.sales.SD0608.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.sales.SD0608.service.SD0608MpOrderDDAO;
import nds.mpm.sales.SD0608.service.SD0608MpOrderDService;

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

@Service("SD0608mpOrderDService")
public class SD0608MpOrderDServiceImpl extends EgovAbstractServiceImpl implements
        SD0608MpOrderDService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0608MpOrderDServiceImpl.class);

    @Resource(name="SD0608mpOrderDDAO")
    private SD0608MpOrderDDAO mpOrderDDAO;

    /**
	 * mp_order_d 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderDList(EgovMap searchVO) throws Exception {
        return mpOrderDDAO.selectMpOrderDList(searchVO);
    }
    
    /**
	 * mp_order_d 목록 탭을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderDTab1List(EgovMap searchVO) throws Exception {
        return mpOrderDDAO.selectMpOrderDTab1List(searchVO);
    }
    
    /**
	 * mp_order_d 목록 탭을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderDTab2List(EgovMap searchVO) throws Exception {
        return mpOrderDDAO.selectMpOrderDTab2List(searchVO);
    }
    
    /**
	 * mp_order_d 목록 탭을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderDTab3List(EgovMap searchVO) throws Exception {
        return mpOrderDDAO.selectMpOrderDTab3List(searchVO);
    }

}
