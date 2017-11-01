package nds.mpm.prod.PP0103.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0103.service.MpItemMasterMDAO;
import nds.mpm.prod.PP0103.service.MpItemMasterMService;
import nds.mpm.prod.PP0103.service.MpItemPlntgMDAO;
import nds.mpm.prod.PP0103.vo.MpItemMasterMDefaultVO;
import nds.mpm.prod.PP0103.vo.MpItemMasterMVO;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpItemMasterMServiceImpl.java
 * @Description : MpItemMasterM Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.08
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpItemMasterMService")
public class MpItemMasterMServiceImpl extends TMMPPBaseService implements
MpItemMasterMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpItemMasterMServiceImpl.class);

    @Resource(name="mpItemMasterMDAO")
    private MpItemMasterMDAO mpItemMasterMDAO;
    
    @Resource(name="mpItemPlntgMDAO")
    private MpItemPlntgMDAO mpItemPlntgMDAO;
    
	/**
	 * product_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpItemMasterMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpItemMasterM(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int iCnt = 0;
    	
    	EgovMap resultWMS = null;
    	
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			if(StringUtils.isEmpty((String)reqVo.get("animalKind")))
    			{
    				result.setMsg("animal kind null");
    				result.setResultCode(Consts.ResultCode.RC_INVALID_PARAMS.getCode());
    				return result;
    			}
    			if(StringUtils.isEmpty((String)reqVo.get("proName")))
    			{
    				result.setMsg("product name kind null");
    				result.setResultCode(Consts.ResultCode.RC_INVALID_PARAMS.getCode());
    				return result;
    			}
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
    			if(mpItemMasterMDAO.checkDupMpItemMasterM(reqVo) > 0)
    			{
    				return new ResultEx(  Consts.ResultCode.RC_DUPLICATE );
    			}
    		}
    		
    	}
    	List ret = new ArrayList();
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			String animalSeq = null;
				/**
				 * 축종구분 시퀀스 조회.
				 * 
				 * **/
				String animalKind = (String)reqVo.get("animalKind");
				//축종 기타코드 미셋팅 셋팅후 코드로 셋팅.
				if("1".equals(animalKind))
					animalSeq = mpItemMasterMDAO.selectSeqPigMpItemMasterMg(reqVo);
				else if("5".equals(animalKind))
					animalSeq = mpItemMasterMDAO.selectSeqCowMpItemMasterMg(reqVo);
				else if("9".equals(animalKind))
					animalSeq = mpItemMasterMDAO.selectSeqEtcMpItemMasterMg(reqVo);
				
				if(animalSeq == null)
				{
					throw new Exception("select animal seq create error !!");
				}
				
				reqVo.put("proCode", animalSeq);
				
				String proCode = (String)reqVo.get("proCode");
    			String proName = (String)reqVo.get("proName");
    		
    			reqVo.put("proCode", null);
    			reqVo.put("proName", proName);
    			if(mpItemMasterMDAO.checkDupMpItemMasterM(reqVo) > 0)
    			{
    				return new ResultEx(  Consts.ResultCode.RC_DUPLICATE );
    			}
    			
    			reqVo.put("proCode", proCode);
    			reqVo.put("proName", proName);
    			
    			String newProCode = mpItemMasterMDAO.insertMpItemMasterM(reqVo);
    			
    			if(newProCode == null){
    				
    				throw new Exception("new procode insert fail!!");
    			}
    			
    			/**
    			 * WMS new procode call
    			*/
    			resultWMS = mpItemMasterMDAO.callWMSFNSetDcsku(reqVo);
    			
    			LOGGER.debug("resultWMS :: " + new ObjectMapper().writeValueAsString(resultWMS));
    			
    			if(!"Y".equals((String)resultWMS.get("resultCdoe")))
				{
    				String returnMsg = (String)resultWMS.get("resultMsg");
    				throw new Exception("WMS 서비스 호출이 실패하였습니다.!!["+returnMsg+"]");
				}
    			
    			insertUpSafetyStock(reqVo);
    			 
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
        		if( mpItemMasterMDAO.updateMpItemMasterM(reqVo) > 0 )
        		{
        			/**
        			 * WMS new procode call
        			 */
        			resultWMS = mpItemMasterMDAO.callWMSFNSetDcsku(reqVo);
        			
        			LOGGER.debug("resultWMS :: " + new ObjectMapper().writeValueAsString(resultWMS));
        			
        			if(!"Y".equals((String)resultWMS.get("resultCdoe"))
        			&& !"N".equals((String)resultWMS.get("resultCdoe")))
    				{
        				String returnMsg = (String)resultWMS.get("resultMsg");
        				throw new Exception("WMS 서비스 호출이 실패하였습니다.!!["+returnMsg+"]");
    				} 
        			
        			insertUpSafetyStock(reqVo);
        		}
        		else
        		{
        			throw new Exception("update procode insert fail!!");
        		}
        		
    		}
        	else if("D".equals((String)reqVo.get("dsType")))
    		{
        		if( mpItemMasterMDAO.deleteMpItemMasterM(reqVo) > 0 )
        		{
        			/**
        			 * WMS new procode call
        			 */
        			resultWMS = mpItemMasterMDAO.callWMSFNSetDcsku(reqVo);
        			
        			LOGGER.debug("resultWMS :: " + new ObjectMapper().writeValueAsString(resultWMS));
        			
        			if(!"Y".equals((String)resultWMS.get("resultCdoe"))
                			&& !"N".equals((String)resultWMS.get("resultCdoe")))
    				{
        				String returnMsg = (String)resultWMS.get("resultMsg");
        				throw new Exception("WMS 서비스 호출이 실패하였습니다.!!["+returnMsg+"]");
    				}
        			
        			insertUpSafetyStock(reqVo);
        		}
        		else
        		{
        			throw new Exception("delete procode insert fail!!");
        		}
    		}
    		
    		ret.add(reqVo);
    	}
    	
    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(ret.size());
    	pageSet.setResult(ret);
    	result.setExtraData(pageSet);	
    	
        return result;
    }
    
    /**
     * 부자재 안전재고량 저장
     * 
     * */
    public void insertUpSafetyStock(EgovMap prodMap) throws Exception{
    	
    	List<EgovMap> safetyStocks = new ArrayList();
    	
    	Iterator keys = prodMap.keySet().iterator();
		String key_name = null;
		
		while (keys.hasNext()) {
			key_name = (String) keys.next();
			if (
				key_name.indexOf("safetyStock") >= 0
				) {
				EgovMap safetyMap = new EgovMap();
				
				safetyMap.put("corpCode", prodMap.get("corpCode"));
				safetyMap.put("grup_plant", StringUtils.remove(key_name,"safetyStock"));
				safetyMap.put("proCode", prodMap.get("proCode"));
				safetyMap.put("safetyStock", prodMap.get(key_name));
				safetyMap.put("crUser", prodMap.get("crUser"));
				
				safetyStocks.add(safetyMap);
			}
		}
    	
    	for(EgovMap reqVo : safetyStocks)
    	{
    		if("C".equals((String)prodMap.get("dsType"))
    				|| "U".equals((String)prodMap.get("dsType")))
    		{
    			mpItemPlntgMDAO.insertMpItemPlntgM(reqVo);
    		}
        	else if("D".equals((String)prodMap.get("dsType")))
    		{
        		mpItemPlntgMDAO.deleteMpItemPlntgM(reqVo);
    		}
    	}
    	
    	safetyStocks = null;
    	
    }
   
    /**
	 * product_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpItemMasterMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpItemMasterM(MpItemMasterMVO vo) throws Exception {
        //mpItemMasterMDAO.updateMpItemMasterM(vo);
    }

    /**
	 * product_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpItemMasterMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpItemMasterM(MpItemMasterMVO vo) throws Exception {
        //mpItemMasterMDAO.deleteMpItemMasterM(vo);
    }

    /**
	 * product_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpItemMasterMVO
	 * @return 조회한 product_info
	 * @exception Exception
	 */
    public MpItemMasterMVO selectMpItemMasterM(EgovMap vo) throws Exception {
        MpItemMasterMVO resultVO = mpItemMasterMDAO.selectMpItemMasterM(vo);
        return resultVO;
    }

    /**
	 * product_info 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return product_info 목록
	 * @exception Exception
	 */
    public List<?> selectMpItemMasterMList(MpItemMasterMDefaultVO searchVO) throws Exception {
        return mpItemMasterMDAO.selectMpItemMasterMList(searchVO);
    }

    /**
	 * product_info 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return product_info 총 갯수
	 * @exception
	 */
    public int selectMpItemMasterMListTotCnt(MpItemMasterMDefaultVO searchVO) {
		return mpItemMasterMDAO.selectMpItemMasterMListTotCnt(searchVO);
	}
    
    public List<?> selectMpItemMasterMCodeList(MpItemMasterMVO searchVO) throws Exception {
        return mpItemMasterMDAO.selectMpItemMasterMCodeList(searchVO);
    }
}
