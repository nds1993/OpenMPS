package nds.mpm.sales.SD0305.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.sales.SD0305.service.MpTargetYymmDAO;
import nds.mpm.sales.SD0305.service.MpTargetYymmService;
import nds.mpm.sales.SD0305.vo.MpTargetYymmDefaultVO;
import nds.mpm.sales.SD0305.vo.MpTargetYymmVO;

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

@Service("mpTargetYymmService")
public class MpTargetYymmServiceImpl extends EgovAbstractServiceImpl implements
        MpTargetYymmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpTargetYymmServiceImpl.class);

    @Resource(name="mpTargetYymmDAO")
    private MpTargetYymmDAO mpTargetYymmDAO;

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
	 * mp_target_yymm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_target_yymm 총 갯수
	 * @exception
	 */
    public int selectMpTargetYymmListTotCnt(MpTargetYymmDefaultVO searchVO) {
		return mpTargetYymmDAO.selectMpTargetYymmListTotCnt(searchVO);
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
}
