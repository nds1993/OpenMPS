package nds.mpm.sales.SD0203.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0203.service.MpSalePriceDAO;
import nds.mpm.sales.SD0203.service.MpSalePriceService;
import nds.mpm.sales.SD0203.vo.MpSalePriceDefaultVO;
import nds.mpm.sales.SD0203.vo.MpSalePriceVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpSalePriceServiceImpl.java
 * @Description : MpSalePrice Business Implement class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpSalePriceService")
public class MpSalePriceServiceImpl extends TMMPPBaseService implements
        MpSalePriceService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpSalePriceServiceImpl.class);

    @Resource(name="mpSalePriceDAO")
    private MpSalePriceDAO mpSalePriceDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpSalePriceIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_sale_price을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpSalePriceVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpSalePrice(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	String newKey = null;
        int nCnt = 0;
        
    	/**
    	for(EgovMap reqVo : vos)
    	{
    		if( "C".equals((String)reqVo.get("dsType"))
    			&& mpSalePriceDAO.checkNewStrtDateCount(reqVo) > 0 )
        	{
        		result.setResultCode(Consts.ResultCode.RC_DUPLICATE.getCode());
        		return result ;
        	}
    	}
    	*/
        
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			newKey = mpSalePriceDAO.insertMpSalePrice(reqVo);
    			if(newKey == null)
    			{
    				throw new Exception("insert fail!!");
    			}
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
    			newKey = mpSalePriceDAO.insertMpSalePrice(reqVo);
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			nCnt = mpSalePriceDAO.deleteMpSalePrice(reqVo);
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
	 * mp_sale_price을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpSalePriceVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpSalePrice(MpSalePriceVO vo) throws Exception {
        //mpSalePriceDAO.updateMpSalePrice(vo);
    }

    /**
	 * mp_sale_price을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpSalePriceVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpSalePrice(MpSalePriceVO vo) throws Exception {
       // mpSalePriceDAO.deleteMpSalePrice(vo);
    }

    /**
	 * mp_sale_price을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpSalePriceVO
	 * @return 조회한 mp_sale_price
	 * @exception Exception
	 */
    public EgovMap selectMpSalePrice(EgovMap vo) throws Exception {
    	EgovMap resultVO = mpSalePriceDAO.selectMpSalePrice(vo);
        return resultVO;
    }

    /**
	 * mp_sale_price 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_sale_price 목록
	 * @exception Exception
	 */
    public List<?> selectMpSalePriceList(MpSalePriceDefaultVO searchVO) throws Exception {
        return mpSalePriceDAO.selectMpSalePriceList(searchVO);
    }
    
    public List<?> selectNewMpSalePriceList(MpSalePriceDefaultVO searchVO) throws Exception {
    	return mpSalePriceDAO.selectNewMpSalePriceList(searchVO);
    }

    /**
	 * mp_sale_price 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_sale_price 총 갯수
	 * @exception
	 */
    public int selectMpSalePriceListTotCnt(MpSalePriceDefaultVO searchVO) {
		return mpSalePriceDAO.selectMpSalePriceListTotCnt(searchVO);
	}
    
    /**
     * SD0203 판매단가승인요청 
	 * mp_sale_price을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpSalePriceVO
	 * @return void형
	 * @exception Exception
	 */
    public ResultEx updateReqApproMpSalePrice(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	for(EgovMap reqVo : vos)
    	{
    		if(mpSalePriceDAO.updateReqApproMpSalePrice(reqVo) == 0)
    		{
    			throw new Exception("update fail!!");
    		}
    	}
    	
    	result.setExtraData(vos);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return result;
    }

    /**
     * SD0206 판매단가승인처리
	 * mp_sale_price을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpSalePriceVO
	 * @return void형
	 * @exception Exception
	 */
    public ResultEx updateSD0206MpSalePrice(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	List<EgovMap> retList = new ArrayList();
    	for(EgovMap reqVo : vos)
    	{
    		if(mpSalePriceDAO.updateSD0206MpSalePrice(reqVo) == 0)
    		{
    			throw new Exception("update fail!!");
    		}

    		retList.add(mpSalePriceDAO.selectMpSalePrice(reqVo));
    	}
    	
    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(retList.size());
    	pageSet.setResult(retList);
    	result.setExtraData(pageSet);  	
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return result;
    }
    
    /**
     * SD0206 판매단가승인취소처리
	 * mp_sale_price을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpSalePriceVO
	 * @return void형
	 * @exception Exception
	 */
    public ResultEx updateSD0206ApproRejectMpSalePrice(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	List<EgovMap> retList = new ArrayList();
    	for(EgovMap reqVo : vos)
    	{
    		if(mpSalePriceDAO.updateSD0206ApproRejectMpSalePrice(reqVo) == 0)
    		{
    			throw new Exception("update fail!!");
    		}

    		retList.add(mpSalePriceDAO.selectMpSalePrice(reqVo));
    	}
    	
    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(retList.size());
    	pageSet.setResult(retList);
    	result.setExtraData(pageSet);  	
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return result;
    }
    
    public List<?> selectSD0206MpSalePriceList(MpSalePriceDefaultVO searchVO) throws Exception {
    	return mpSalePriceDAO.selectSD0206MpSalePriceList(searchVO);
    }

    /**
	 * mp_sale_price 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_sale_price 총 갯수
	 * @exception
	 */
    public int selectSD0206MpSalePriceListTotCnt(MpSalePriceDefaultVO searchVO) {
    	return mpSalePriceDAO.selectSD0206MpSalePriceListTotCnt(searchVO);
    }
    
    public int checkNewStrtDateCount(EgovMap searchVO) throws Exception {
    	return mpSalePriceDAO.checkNewStrtDateCount(searchVO);
    }
    
    public ResultEx updateCancelApproMpSalePrice(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	for(EgovMap reqVo : vos)
    	{
    		if(mpSalePriceDAO.updateCancelApproMpSalePrice(reqVo) == 0)
    		{
    			throw new Exception("update fail!!");
    		}
    	}
        return result;
    }

}
