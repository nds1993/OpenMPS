package nds.mpm.prod.PP0702.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.prod.PP0101.vo.TmPlatxmDefaultVO;
import nds.mpm.prod.PP0702.service.PP0702TmPlatxmDAO;
import nds.mpm.prod.PP0702.service.PP0702TmPlatxmService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : TmPlatxmServiceImpl.java
 * @Description : TmPlatxm Business Implement class
 * @Modification Information
 *
 * @author MPM
 * @since 2017
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PP0702tmPlatxmService")
public class PP0702TmPlatxmServiceImpl extends TMMPPBaseService implements
PP0702TmPlatxmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PP0702TmPlatxmServiceImpl.class);

    @Resource(name="PP0702tmPlatxmDAO")
    private PP0702TmPlatxmDAO tmPlatxmDAO;

    /**
	 * tm_platxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_platxm 총 갯수
	 * @exception
	 */
    public List selectTmPlatxmList(TmPlatxmDefaultVO searchVO) throws Exception{
    	return tmPlatxmDAO.selectTmPlatxmList(searchVO);
    }
    
    public int selectTmPlatxmListTotCnt(TmPlatxmDefaultVO searchVO) {
		return tmPlatxmDAO.selectTmPlatxmListTotCnt(searchVO);
	}
    
}
