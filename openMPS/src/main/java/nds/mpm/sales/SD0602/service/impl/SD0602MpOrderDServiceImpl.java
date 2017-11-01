package nds.mpm.sales.SD0602.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.sales.SD0602.service.SD0602MpOrderDService;
import nds.mpm.sales.SD0602.vo.SD0602MpOrderDVO;
import nds.mpm.sales.SD0602.service.SD0602MpOrderDDAO;

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

@Service("SD0602mpOrderDService")
public class SD0602MpOrderDServiceImpl extends EgovAbstractServiceImpl implements
        SD0602MpOrderDService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0602MpOrderDServiceImpl.class);

    @Resource(name="SD0602mpOrderDDAO")
    private SD0602MpOrderDDAO SD602mpOrderDDAO;

    /**
	 * mp_order_d 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_d 목록
	 * @exception Exception
	 */
    public List<?> selectSD0602MpOrderList_1(SD0602MpOrderDVO searchVO) throws Exception {
        return SD602mpOrderDDAO.selectSD0602MpOrderList_1(searchVO);
    }
    
    public List<?> selectSD0602MpOrderList_2(SD0602MpOrderDVO searchVO) throws Exception {
        return SD602mpOrderDDAO.selectSD0602MpOrderList_2(searchVO);
    }
    
    public List<?> selectSD0602MpOrderList_3(SD0602MpOrderDVO searchVO) throws Exception {
        return SD602mpOrderDDAO.selectSD0602MpOrderList_3(searchVO);
    }
    
    public List<?> selectSD0602MpOrderList_4(SD0602MpOrderDVO searchVO) throws Exception {
        return SD602mpOrderDDAO.selectSD0602MpOrderList_4(searchVO);
    }
    
    public List<?> selectSD0602MpOrderList_5(SD0602MpOrderDVO searchVO) throws Exception {
        return SD602mpOrderDDAO.selectSD0602MpOrderList_5(searchVO);
    }

   
    
}
