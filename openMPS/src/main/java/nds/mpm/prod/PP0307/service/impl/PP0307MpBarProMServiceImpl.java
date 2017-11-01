package nds.mpm.prod.PP0307.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.prod.PP0307.service.PP0307MpBarProMDAO;
import nds.mpm.prod.PP0307.service.PP0307MpBarProMService;
import nds.mpm.prod.PP0504.vo.MpBarProMDefaultVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : MpBarProMServiceImpl.java
 * @Description : MpBarProM Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PP0307mpBarProMService")
public class PP0307MpBarProMServiceImpl extends TMMPPBaseService implements
PP0307MpBarProMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PP0307MpBarProMServiceImpl.class);

    @Resource(name="PP0307mpBarProMDAO")
    private PP0307MpBarProMDAO mpBarProMDAO;
    
    /**
	 * mp_bar_pro_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bar_pro_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpBarProMList(MpBarProMDefaultVO searchVO) throws Exception {
        return mpBarProMDAO.selectMpBarProMList(searchVO);
    }

    /**
	 * mp_bar_pro_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bar_pro_m 총 갯수
	 * @exception
	 */
    public int selectMpBarProMListTotCnt(MpBarProMDefaultVO searchVO) {
		return mpBarProMDAO.selectMpBarProMListTotCnt(searchVO);
	}
    
    public List<?> selectPopMpBarProMList(MpBarProMDefaultVO searchVO) throws Exception {
        return mpBarProMDAO.selectPopMpBarProMList(searchVO);
    }
}
