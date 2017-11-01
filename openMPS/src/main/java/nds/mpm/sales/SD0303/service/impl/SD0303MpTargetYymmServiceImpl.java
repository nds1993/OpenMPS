package nds.mpm.sales.SD0303.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.sales.SD0303.service.SD0303MpTargetYymmDAO;
import nds.mpm.sales.SD0303.service.SD0303MpTargetYymmService;
import nds.mpm.sales.SD0303.vo.MpTargetYymmDefaultVO;
import nds.mpm.sales.SD0303.vo.MpTargetYymmVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : MpTargetYymmServiceImpl.java
 * @Description : MpTargetYymm Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("SD0303mpTargetYymmService")
public class SD0303MpTargetYymmServiceImpl extends EgovAbstractServiceImpl implements
SD0303MpTargetYymmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0303MpTargetYymmServiceImpl.class);

    @Resource(name="SD0303mpTargetYymmDAO")
    private SD0303MpTargetYymmDAO mpTargetYymmDAO;

    /**
	 * mp_target_yymm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_target_yymm 목록
	 * @exception Exception
	 */
    public List<?> selectMpTargetYymmList(MpTargetYymmDefaultVO searchVO) throws Exception {
        return mpTargetYymmDAO.selectMpTargetYymmList(searchVO);
    }
    
    /**
	 * mp_target_yymm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_target_yymm 목록
	 * @exception Exception
	 */
    public List<?> selectMpTargetYymmListD(MpTargetYymmDefaultVO searchVO) throws Exception {
        return mpTargetYymmDAO.selectMpTargetYymmListD(searchVO);
    }

    /**
	 * mp_target_yymm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_target_yymm 총 갯수
	 * @exception
	 */
    public int selectMpTargetYymmListTotCnt(MpTargetYymmDefaultVO searchVO) {
		return mpTargetYymmDAO.selectMpTargetYymmListTotCnt(searchVO);
	}
    
}
