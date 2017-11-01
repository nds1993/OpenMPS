package nds.mpm.sales.SD0202.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0202.service.MpStndPriceDAO;
import nds.mpm.sales.SD0202.service.MpStndPriceService;
import nds.mpm.sales.SD0202.vo.MpStndPriceDefaultVO;
import nds.mpm.sales.SD0202.vo.MpStndPriceVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpStndPriceServiceImpl.java
 * @Description : MpStndPrice Business Implement class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpStndPriceService")
public class MpStndPriceServiceImpl extends TMMPPBaseService implements
        MpStndPriceService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpStndPriceServiceImpl.class);

    @Resource(name="mpStndPriceDAO")
    private MpStndPriceDAO mpStndPriceDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpStndPriceIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_stnd_price을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpStndPriceVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpStndPrice(List<EgovMap> vos) throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        String newKey = null;
        int nCnt = 0;
        
        for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			if(selectMpStndPrice(reqVo) != null)
        		{
        			if(mpStndPriceDAO.checkDupPeriodMpStndPriceTotCnt(reqVo) > 0)
        			{
        				result.setMsg("이미등록된 데이터와 일부또는 전체가 중복되는 기간입니다.");
        				result.setResultCode(Consts.ResultCode.RC_DUPLICATE.getCode());
        				return result;
        			}
        		}
    		}
    	}	
        
    	for(EgovMap reqVo : vos)
    	{
    		
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			if(selectMpStndPrice(reqVo) != null)
        		{
    				nCnt = mpStndPriceDAO.updateMpStndPrice(reqVo);
        			if(nCnt == 0)
        			{
        				throw new Exception("update fail!!");
        			}
        		}
        		else
        		{
        			newKey = mpStndPriceDAO.insertMpStndPrice(reqVo);
        			if(newKey == null)
        			{
        				throw new Exception("insert fail!!");
        			}
        		}
    			
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
    			
    			if(selectMpStndPrice(reqVo) != null)
        		{
    				nCnt = mpStndPriceDAO.updateMpStndPrice(reqVo);
        			if(nCnt == 0)
        			{
        				throw new Exception("update fail!!");
        			}
        		}
    			else
        		{
        			newKey = mpStndPriceDAO.insertMpStndPrice(reqVo);
        			if(newKey == null)
        			{
        				throw new Exception("insert fail!!");
        			}
        		}
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			nCnt = mpStndPriceDAO.deleteMpStndPrice(reqVo);
    			if(nCnt == 0)
    			{
    				throw new Exception("delete fail!!");
    			}
    		}
    	}
    	
    	LOGGER.debug("nCnt :: " + nCnt);
    	
    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(vos.size());
    	pageSet.setResult(vos);
    	result.setExtraData(pageSet);
        return result;
    }

    /**
	 * mp_stnd_price을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpStndPriceVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpStndPrice(MpStndPriceVO vo) throws Exception {
        //mpStndPriceDAO.updateMpStndPrice(vo);
    }

    /**
	 * mp_stnd_price을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpStndPriceVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpStndPrice(MpStndPriceVO vo) throws Exception {
        //mpStndPriceDAO.deleteMpStndPrice(vo);
    }

    /**
	 * mp_stnd_price을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpStndPriceVO
	 * @return 조회한 mp_stnd_price
	 * @exception Exception
	 */
    public MpStndPriceVO selectMpStndPrice(EgovMap vo) throws Exception {
        MpStndPriceVO resultVO = mpStndPriceDAO.selectMpStndPrice(vo);
        return resultVO;
    }

    /**
	 * mp_stnd_price 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_stnd_price 목록
	 * @exception Exception
	 */
    public List<?> selectMpStndPriceList(MpStndPriceDefaultVO searchVO) throws Exception {
        return mpStndPriceDAO.selectMpStndPriceList(searchVO);
    }

    /**
	 * mp_stnd_price 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_stnd_price 총 갯수
	 * @exception
	 */
    public int selectMpStndPriceListTotCnt(MpStndPriceDefaultVO searchVO) {
		return mpStndPriceDAO.selectMpStndPriceListTotCnt(searchVO);
	}
    
    public List<?> selectMpStndPricePeriodList(MpStndPriceDefaultVO searchVO) throws Exception {
        return mpStndPriceDAO.selectMpStndPricePeriodList(searchVO);
    }
    
}
