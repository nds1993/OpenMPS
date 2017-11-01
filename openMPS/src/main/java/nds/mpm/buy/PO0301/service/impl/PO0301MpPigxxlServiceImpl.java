package nds.mpm.buy.PO0301.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.buy.PO0301.service.PO0301MpPigxxlDAO;
import nds.mpm.buy.PO0301.service.PO0301MpPigxxlService;
import nds.mpm.buy.PO0301.vo.MpPigxxlDefaultVO;
import nds.mpm.buy.PO0301.vo.MpPigxxlVO;
import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPigxxlServiceImpl.java
 * @Description : MpPigxxl Business Implement class
 * @Modification Information
 *
 * @author ㅌ
 * @since ㅌ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PO0301mpPigxxlService")
public class PO0301MpPigxxlServiceImpl extends TMMPPBaseService implements
        PO0301MpPigxxlService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PO0301MpPigxxlServiceImpl.class);

    @Resource(name="PO0301mpPigxxlDAO")
    private PO0301MpPigxxlDAO mpPigxxlDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpPigxxlIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_pigxxl을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPigxxlVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpPigxxl(List<EgovMap> vos) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );

    	if(vos == null)
    	{
    		return new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}
    	if(vos.size() == 0)
    	{
    		return new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}

    	String newKey = null;
    	int iCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			newKey = mpPigxxlDAO.insertMpPigxxl(reqVo);
    			
    			LOGGER.debug("newKey or upKey :: " + newKey);
    			
    			if(newKey == null)
    				throw new Exception("insert key fail!!");
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
    			iCnt = mpPigxxlDAO.updateMpPigxxl(reqVo);
    			
    			LOGGER.debug("newKey or upKey :: " + newKey);
    			
    			if(iCnt == 0)
    				throw new Exception("update key fail!!");
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			iCnt = mpPigxxlDAO.deleteMpPigxxl(reqVo);
    			if(iCnt == 0)
    				throw new Exception("update key fail!!");
    		}
    		
    		iCnt++;
    	}
    	
    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(vos.size());
    	pageSet.setResult(vos);
    	
    	return result;
    }

    /**
	 * mp_pigxxl을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPigxxlVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPigxxl(EgovMap vo) throws Exception {
        mpPigxxlDAO.updateMpPigxxl(vo);
    }

    /**
	 * mp_pigxxl을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpPigxxlVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpPigxxl(EgovMap vo) throws Exception {
        mpPigxxlDAO.deleteMpPigxxl(vo);
    }

    /**
	 * mp_pigxxl을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPigxxlVO
	 * @return 조회한 mp_pigxxl
	 * @exception Exception
	 */
    public MpPigxxlVO selectMpPigxxl(MpPigxxlVO vo) throws Exception {
        MpPigxxlVO resultVO = mpPigxxlDAO.selectMpPigxxl(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_pigxxl 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigxxl 목록
	 * @exception Exception
	 */
    public List<?> selectMpPigxxlList(MpPigxxlDefaultVO searchVO) throws Exception {
        return mpPigxxlDAO.selectMpPigxxlList(searchVO);
    }

    /**
	 * mp_pigxxl 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_pigxxl 총 갯수
	 * @exception
	 */
    public int selectMpPigxxlListTotCnt(MpPigxxlDefaultVO searchVO) {
		return mpPigxxlDAO.selectMpPigxxlListTotCnt(searchVO);
	}
    
}
