package nds.mpm.prod.PP0503.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.prod.PP0504.vo.MpBarProMDefaultVO;
import nds.mpm.prod.PP0504.vo.MpBarProMVO;
import nds.mpm.prod.PP0503.service.PP0503MpBarProMService;
import nds.mpm.prod.PP0503.service.PP0503MpBarProMDAO;

/**
 * @Class Name : MpBarProMServiceImpl.java
 * @Description : MpBarProM Business Implement class
 * @Modification Information
 *
 * @author 생산 vs 입고현황 조회
 * @since 생산 vs 입고현황 조회
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PP0503mpBarProMService")
public class PP0503MpBarProMServiceImpl extends EgovAbstractServiceImpl implements
        PP0503MpBarProMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PP0503MpBarProMServiceImpl.class);

    @Resource(name="PP0503mpBarProMDAO")
    private PP0503MpBarProMDAO mpBarProMDAO;
    
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
