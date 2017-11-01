package nds.mpm.sales.SD0205.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0205.service.MpDiscPriceTitleService;
import nds.mpm.sales.SD0205.vo.MpDiscPriceTitleDefaultVO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceTitleVO;
import nds.mpm.sales.SD0205.service.MpDiscPriceTitleDAO;

/**
 * @Class Name : MpDiscPriceTitleServiceImpl.java
 * @Description : MpDiscPriceTitle Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpDiscPriceTitleService")
public class MpDiscPriceTitleServiceImpl extends EgovAbstractServiceImpl implements
        MpDiscPriceTitleService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpDiscPriceTitleServiceImpl.class);

    @Resource(name="mpDiscPriceTitleDAO")
    private MpDiscPriceTitleDAO mpDiscPriceTitleDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpDiscPriceTitleIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_disc_price_title을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDiscPriceTitleVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpDiscPriceTitle(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	List<EgovMap> returnList = new ArrayList();
    	String newKey = null;
    	int nCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		
    		if("C".equals((String)reqVo.get("dsType"))){
    			
    			mpDiscPriceTitleDAO.insertMpDiscPriceTitle(reqVo);
    			if(newKey == null)
    			{
    				throw new Exception("update fail!!");
    			}
    		}
    		else if("U".equals((String)reqVo.get("dsType"))){
    			
    			mpDiscPriceTitleDAO.updateMpDiscPriceTitle(reqVo);
    			if(nCnt == 0)
    			{
    				throw new Exception("update fail!!");
    			}
    		}
    		else if("D".equals((String)reqVo.get("dsType"))){
    			mpDiscPriceTitleDAO.deleteMpDiscPriceTitle(reqVo);
    			if(nCnt == 0)
    			{
    				throw new Exception("update fail!!");
    			}
    		}
    			
    		
    		returnList.add(reqVo);
    	}
    	//TODO 해당 테이블 정보에 맞게 수정    
    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(returnList.size());
    	pageSet.setResult(returnList);
        
    	result.setExtraData(pageSet);
    	
    	return result;	
    }

    /**
	 * mp_disc_price_title을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDiscPriceTitleVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpDiscPriceTitle(MpDiscPriceTitleVO vo) throws Exception {
        //mpDiscPriceTitleDAO.updateMpDiscPriceTitle(vo);
    }

    /**
	 * mp_disc_price_title을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDiscPriceTitleVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpDiscPriceTitle(MpDiscPriceTitleVO vo) throws Exception {
    }

    /**
	 * mp_disc_price_title을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDiscPriceTitleVO
	 * @return 조회한 mp_disc_price_title
	 * @exception Exception
	 */
    public EgovMap selectMpDiscPriceTitle(EgovMap vo) throws Exception {
    	EgovMap resultVO = mpDiscPriceTitleDAO.selectMpDiscPriceTitle(vo);
        return resultVO;
    }

    /**
	 * mp_disc_price_title 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_disc_price_title 목록
	 * @exception Exception
	 */
    public List<?> selectMpDiscPriceTitleList(MpDiscPriceTitleDefaultVO searchVO) throws Exception {
        return mpDiscPriceTitleDAO.selectMpDiscPriceTitleList(searchVO);
    }

    /**
	 * mp_disc_price_title 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_disc_price_title 총 갯수
	 * @exception
	 */
    public int selectMpDiscPriceTitleListTotCnt(MpDiscPriceTitleDefaultVO searchVO) {
		return mpDiscPriceTitleDAO.selectMpDiscPriceTitleListTotCnt(searchVO);
	}
    
}
