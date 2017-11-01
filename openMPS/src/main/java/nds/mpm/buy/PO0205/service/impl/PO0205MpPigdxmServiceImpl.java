package nds.mpm.buy.PO0205.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.buy.PO0102.vo.MpPigdxmDefaultVO;
import nds.mpm.buy.PO0205.service.PO0205MpPigdxmDAO;
import nds.mpm.buy.PO0205.service.PO0205MpPigdxmService;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPigdxmServiceImpl.java
 * @Description : MpPigdxm Business Implement class
 * @Modification Information
 *
 * @author 더느림지급액조회
 * @since 더느림지급액조회
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PO0205mpPigdxmService")
public class PO0205MpPigdxmServiceImpl extends EgovAbstractServiceImpl implements
        PO0205MpPigdxmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PO0205MpPigdxmServiceImpl.class);

    @Resource(name="PO0205mpPigdxmDAO")
    private PO0205MpPigdxmDAO pO0205mpPigdxmDAO;
    
    /**
	 * mp_pigdxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigdxmVO
	 * @return void형
	 * @exception Exception
	 */
    public ResultEx updateMpPigdxm(List<EgovMap> vos) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	for(EgovMap reqVo : vos)
    	{
    		if( pO0205mpPigdxmDAO.updateMpPigdxm(reqVo) == 0 )
    		{
    			throw new Exception("update fail!!");
    		}
    	}
    	
    	result.setExtraData(vos);
    	return result;
    }

    /**
	 * mp_pigdxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigdxm 목록
	 * @exception Exception
	 */
    public List<?> selectMpPigdxmList(MpPigdxmDefaultVO searchVO) throws Exception {
        return pO0205mpPigdxmDAO.selectMpPigdxmList(searchVO);
    }

    /**
	 * mp_pigdxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigdxm 총 갯수
	 * @exception
	 */
    public int selectMpPigdxmListTotCnt(MpPigdxmDefaultVO searchVO) {
		return pO0205mpPigdxmDAO.selectMpPigdxmListTotCnt(searchVO);
	}
    
}
