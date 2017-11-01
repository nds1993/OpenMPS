package nds.mpm.buy.PO0302.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.buy.PO0302.service.PO0302MpPigoxmDAO;
import nds.mpm.buy.PO0302.service.PO0302MpPigoxmService;
import nds.mpm.buy.PO0302.vo.MpPigoxmVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : PO0302MpPigoxmServiceImpl.java
 * @Description : MpPigoxm Implement class
 * @Modification Information
 *
 * @author 123
 * @since 123
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PO0302MpPigoxmService")
public class PO0302MpPigoxmServiceImpl extends EgovAbstractServiceImpl implements
	PO0302MpPigoxmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PO0302MpPigoxmServiceImpl.class);

    @Resource(name="PO0302MpPigoxmDAO")
    private PO0302MpPigoxmDAO PO0302MpPigoxmDAO;
    
    /**
	 * mp_pigoxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigoxm 목록
	 * @exception Exception
	 */
    public List<?> selectPO0302MpPigoxmList_tab1(MpPigoxmVO searchVO) throws Exception {
        return PO0302MpPigoxmDAO.selectPO0302MpPigoxmList_tab1(searchVO);
    }

    /**
	 * mp_pigoxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigoxm 총 갯수
	 * @exception
	 */
    public int selectPO0302MpPigoxmListTotCnt_tabl(MpPigoxmVO searchVO) {
		return PO0302MpPigoxmDAO.selectPO0302MpPigoxmListTotCnt_tab1(searchVO);
	}
    
    /**
	 * mp_pigoxm 목록을 조회한다.tab2
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigoxm 목록
	 * @exception Exception
	 */
    public List<?> selectPO0302MpPigoxmList_tab2(MpPigoxmVO searchVO) throws Exception {
        return PO0302MpPigoxmDAO.selectPO0302MpPigoxmList_tab2(searchVO);
    }

    /**
	 * mp_pigoxm 총 갯수를 조회한다.tab2
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigoxm 총 갯수
	 * @exception
	 */
    public int selectPO0302MpPigoxmListTotCnt_tab2(MpPigoxmVO searchVO) {
		return PO0302MpPigoxmDAO.selectPO0302MpPigoxmListTotCnt_tab2(searchVO);
	}
    
}
