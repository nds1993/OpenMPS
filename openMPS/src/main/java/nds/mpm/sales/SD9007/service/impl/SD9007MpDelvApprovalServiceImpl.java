package nds.mpm.sales.SD9007.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.sales.SD0402.vo.SD0402DefaultVO;
import nds.mpm.sales.SD9007.service.SD9007MpDelvApprovalDAO;
import nds.mpm.sales.SD9007.service.SD9007MpDelvApprovalService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : SD0402ServiceImpl.java
 * @Description : SD0402 Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("SD9007Service")
public class SD9007MpDelvApprovalServiceImpl extends TMMPPBaseService implements
SD9007MpDelvApprovalService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD9007MpDelvApprovalServiceImpl.class);

    @Resource(name="SD9007DAO")
    private SD9007MpDelvApprovalDAO SD0402DAO;
    
    public List<?> selectSD0403List(SD0402DefaultVO searchVO) throws Exception{
        return SD0402DAO.selectSD0403List(searchVO);
    }

    /**
	 * mp_delv_approval 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_delv_approval 총 갯수
	 * @exception
	 */
    public int selectSD0403ListTotCnt(SD0402DefaultVO searchVO){
        return SD0402DAO.selectSD0403ListTotCnt(searchVO);
    }
    
    public List<?> selectSD9007HistList(SD0402DefaultVO searchVO) throws Exception {
    	return SD0402DAO.selectSD9007HistList(searchVO);
    }

}
