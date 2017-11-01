package nds.mpm.buy.PO0201.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.buy.PO0102.service.MpPigdxmDAO;
import nds.mpm.buy.PO0102.vo.MpPigdxmDefaultVO;
import nds.mpm.buy.PO0201.service.PO0201MpPigdxmDAO;
import nds.mpm.buy.PO0201.service.PO0201MpPigdxmService;
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
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PO0201MpPigdxmService")
public class PO0201MpPigdxmServiceImpl extends EgovAbstractServiceImpl implements
PO0201MpPigdxmService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PO0201MpPigdxmServiceImpl.class);

    @Resource(name="PO0201MpPigdxmDAO")
    private PO0201MpPigdxmDAO pO0201MpPigdxmpPigdxmDAO;
    
    @Resource(name="mpPigdxmDAO")
    private MpPigdxmDAO mpPigdxmDAO;
    
    /**
	 * PO0201 생돈구매세부 신규없음.
	 * @exception Exception
	 */
    public ResultEx insertMpPigdxm(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int nCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		if("U".equals((String)reqVo.get("dsType"))){
    			nCnt = pO0201MpPigdxmpPigdxmDAO.updateMpPigdxm(reqVo);
    			if(nCnt == 0)
        		{
        			throw new Exception("수정이 실패하였습니다.");
        		}
    			else
    			{
    				// 세부 내역의 대표 농장 변경
    				pO0201MpPigdxmpPigdxmDAO.updateMpPigdxmRepCust(reqVo);
    			}
    		}
    			
    		else if("D".equals((String)reqVo.get("dsType"))){
    			nCnt = mpPigdxmDAO.deleteMpPigdxm(reqVo);
    			if(nCnt == 0)
        		{
        			throw new Exception("삭제가 실패하였습니다.");
        		}
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
        return pO0201MpPigdxmpPigdxmDAO.selectMpPigdxmList(searchVO);
    }
    
    public List<?> selectMpPigdxmDetailList(MpPigdxmDefaultVO searchVO) throws Exception {
        return pO0201MpPigdxmpPigdxmDAO.selectMpPigdxmDetailList(searchVO);

    }

}
