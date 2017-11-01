package nds.mpm.buy.PO0203.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.buy.PO0102.vo.MpPigdxmDefaultVO;
import nds.mpm.buy.PO0203.service.PO0203MpPigdxmDAO;
import nds.mpm.buy.PO0203.service.PO0203MpPigdxmService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : MpPigdxmServiceImpl.java
 * @Description : MpPigdxm Business Implement class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PO0203MpPigdxmService")
public class PO0203MpPigdxmServiceImpl extends EgovAbstractServiceImpl implements
        PO0203MpPigdxmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PO0203MpPigdxmServiceImpl.class);

    @Resource(name="PO0203MpPigdxmDAO")
    private PO0203MpPigdxmDAO mpPigdxmDAO;
    
    /**
	 * mp_pigdxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigdxm 목록
	 * @exception Exception
	 */
    public List<?> selectMpPigdxmList(MpPigdxmDefaultVO searchVO) throws Exception {
        return mpPigdxmDAO.selectMpPigdxmList(searchVO);
    }
    
    public List<?> selectMpPigdxmListGroupDoch(MpPigdxmDefaultVO searchVO) throws Exception {
    	return mpPigdxmDAO.selectMpPigdxmListGroupDoch(searchVO);
    }

    /**
	 * mp_pigdxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigdxm 총 갯수
	 * @exception
	 */
    public int selectMpPigdxmListTotCnt(MpPigdxmDefaultVO searchVO) {
		return mpPigdxmDAO.selectMpPigdxmListTotCnt(searchVO);
	}
    
}
