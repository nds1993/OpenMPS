package nds.mpm.sales.SD0205.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0205.service.MpDiscPriceDAO;
import nds.mpm.sales.SD0205.service.MpDiscPriceService;
import nds.mpm.sales.SD0205.service.MpDiscPriceTitleDAO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceDefaultVO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceTitleVO;
import nds.mpm.sales.SD0205.vo.MpDiscPriceVO;
import nds.mpm.sales.SD0205.vo.MultiMpDiscPriceVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpDiscPriceServiceImpl.java
 * @Description : MpDiscPrice Business Implement class
 * @Modification Information
 *
 * @author n
 * @since n
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpDiscPriceService")
public class MpDiscPriceServiceImpl extends TMMPPBaseService implements
        MpDiscPriceService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpDiscPriceServiceImpl.class);

    @Resource(name="mpDiscPriceDAO")
    private MpDiscPriceDAO mpDiscPriceDAO;
    
    @Resource(name="mpDiscPriceTitleDAO")
    private MpDiscPriceTitleDAO mpDiscPriceTitleDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpDiscPriceIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_disc_price을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpDiscPriceVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpDiscPrice(MultiMpDiscPriceVO multiMpDiscPriceVO) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	List<EgovMap> returnList = null;
    	String newKey = null;
    	int nCnt = 0;
    	List<EgovMap> heads = multiMpDiscPriceVO.getHead();
    	List<EgovMap> vos = multiMpDiscPriceVO.getDetail();
    	
    	if(heads != null && heads.size() > 0)
    	{
    		EgovMap headmap = heads.get(0);
    		
	    		if(!"D".equals(multiMpDiscPriceVO.getDsType())){
	    			
	    			if(
	    				"C".equals((String)headmap.get("dsType"))
	    			){
	    			
		    			MpDiscPriceTitleVO mpDiscPriceTitleVO = new MpDiscPriceTitleVO();
		    	    	mpDiscPriceTitleVO.setCorpCode((String)headmap.get("corpCode"));
		    			mpDiscPriceTitleVO.setCustCode((String)headmap.get("custCode"));
		    			mpDiscPriceTitleVO.setStrtDate((String)headmap.get("strtDate"));
		    			mpDiscPriceTitleVO.setLastDate((String)headmap.get("lastDate"));
		    			MpDiscPriceTitleVO findAmt = mpDiscPriceTitleDAO.selectMpDiscPriceTitlePeriodAmt(mpDiscPriceTitleVO);
		    			if(findAmt != null)
		    			{
		    				headmap.put("monSale1", findAmt.getMonSale1());
		    				headmap.put("monRece1", findAmt.getMonRece1());
		    				headmap.put("monMisu1", findAmt.getMonMisu1());
		    				headmap.put("monTurnover1", findAmt.getMonTurnover1());
		    				headmap.put("monAgo1", findAmt.getMonAgo1());
		    				headmap.put("monSale2", findAmt.getMonSale2());
		    				headmap.put("monRece2", findAmt.getMonRece2());
		    				headmap.put("monMisu2", findAmt.getMonMisu2());
		    				headmap.put("monTurnover2", findAmt.getMonTurnover2());
		    				headmap.put("monAgo2", findAmt.getMonAgo2());
		    				headmap.put("monSale3", findAmt.getMonSale3());
		    				headmap.put("monRece3", findAmt.getMonRece3());
		    				headmap.put("monMisu3", findAmt.getMonMisu3());
		    				headmap.put("monTurnover3", findAmt.getMonTurnover3());
		    				headmap.put("monAgo3", findAmt.getMonAgo3());
		    			}
		    			
		    			newKey = mpDiscPriceTitleDAO.insertMpDiscPriceTitle(headmap);
		    			if(newKey == null)
		    			{
		    				throw new Exception("update fail!!");
		    			}
		    			
		    			returnList = transactionDetailList(vos);
	    			
	    			}
	    			else if( 
	    					"U".equals((String)headmap.get("dsType"))
		    			){
	    				
		    				//strt_date, last_date 가 변경되었으면 이전 정보를 가져와서
		    				// 변경 일자로 재생성해줌.
			    			if(
			    					StringUtils.isNotEmpty((String)headmap.get("oldStrtDate"))
			    					&& StringUtils.isNotEmpty((String)headmap.get("oldLastDate"))
			    			)
			    			{
			    				EgovMap upDateMap = new EgovMap();
			    				upDateMap.put("corpCode", headmap.get("corpCode"));
			    				upDateMap.put("strtDate", headmap.get("oldStrtDate"));
			    				upDateMap.put("lastDate", headmap.get("oldLastDate"));
			    				upDateMap.put("custCode", headmap.get("custCode"));
			    				upDateMap.put("oldStrtDate", headmap.get("oldStrtDate"));
			    				upDateMap.put("oldLastDate", headmap.get("oldLastDate"));
			    				
			    				EgovMap upMap = mpDiscPriceTitleDAO.selectMpDiscPriceTitle(upDateMap);
			    				List<EgovMap> upDtList = mpDiscPriceDAO.selectMpDiscPriceOldList(upDateMap);
			    				
			    				mpDiscPriceTitleDAO.deleteMpDiscPriceOldTitle(upDateMap);
			    				mpDiscPriceDAO.deleteMpDiscOldPrice(upDateMap);
			    				
			    				for(EgovMap upDtMap : upDtList)
			    				{
			    					upDtMap.put("strtDate", headmap.get("strtDate"));
			    					upDtMap.put("lastDate", headmap.get("lastDate"));
			    				}
			    				
			    				returnList = transactionDetailList(upDtList);
			    				
			    				upDateMap = null;
			    			}
		    				
			    			newKey = mpDiscPriceTitleDAO.insertMpDiscPriceTitle(headmap);
			    			if(newKey == null)
			    			{
			    				throw new Exception("update fail!!");
			    			}
			    			
		    			}
    			
    		}
			else if("D".equals(multiMpDiscPriceVO.getDsType())){
				nCnt = mpDiscPriceTitleDAO.deleteMpDiscPriceTitle(headmap);
				if(nCnt == 0)
    			{
    				throw new Exception("update fail!!");
    			}
		    	
		    	returnList = transactionDetailList(vos);
    		}
    	}
    	
    	//TODO 해당 테이블 정보에 맞게 수정    
    	PageSet pageSet = new PageSet();
        
    	pageSet.setResult(returnList);
        
    	result.setExtraData(pageSet);
    	
    	return result;
    }
    
    public List<EgovMap> transactionDetailList(List<EgovMap> details) throws Exception {
    	
    	String newKey = null;
    	int nCnt = 0;
    	List<EgovMap> returnList = new ArrayList();
    	
    	if(details != null)
    	{
    		for(EgovMap reqVo : details)
        	{
        		if(!"D".equals((String)reqVo.get("dsType"))){
        			
        			newKey = mpDiscPriceDAO.insertMpDiscPrice(reqVo);
        			if(newKey == null)
        			{
        				throw new Exception("update fail!!");
        			}
        		}
        		else if("D".equals((String)reqVo.get("dsType"))){
        			nCnt = mpDiscPriceDAO.deleteMpDiscPrice(reqVo);
        			if(nCnt == 0)
        			{
        				throw new Exception("update fail!!");
        			}
        		}
        		returnList.add(reqVo);
        	}
    	}
    	
    	return returnList;
    }
    
    /**
     * SD0205 승인요청시 
     * 거래처별 매출액, 입금액,잔액을 조회하여 할인헤더테이블에 업데이트 한다.
     * SD0207 승인처리화면에서 조회시 사용..
     * 
     * */
    public ResultEx updateMpDiscPriceApproRequest(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	for(EgovMap vo : vos)
    	{
    		MpDiscPriceTitleVO mpDiscPriceTitleVO = new MpDiscPriceTitleVO();
    		mpDiscPriceTitleVO.setCorpCode((String)vo.get("corpCode"));
    		mpDiscPriceTitleVO.setCustCode((String)vo.get("custCode"));
    		mpDiscPriceTitleVO.setStrtDate((String)vo.get("strtDate"));
    		mpDiscPriceTitleVO.setLastDate((String)vo.get("lastDate"));
    		/***
    		MpDiscPriceTitleVO findAmt = mpDiscPriceTitleDAO.selectMpDiscPriceTitlePeriodAmt(mpDiscPriceTitleVO);
        	
        	if(findAmt == null)
        		throw new Exception("cust amt select fail!!");
        	
        	findAmt.setStrtDate((String)vo.get("strtDate"));
        	findAmt.setLastDate((String)vo.get("lastDate"));
        	*/
        	if( mpDiscPriceTitleDAO.updateMpDiscPriceApproRequest(mpDiscPriceTitleVO) == 0)
        		throw new Exception("request fail!!");
    	}
    	
    	return result;
    }

    /**
	 * mp_disc_price을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpDiscPriceVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpDiscPrice(MpDiscPriceVO vo) throws Exception {
        mpDiscPriceDAO.updateMpDiscPrice(vo);
    }

    /**
	 * mp_disc_price을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpDiscPriceVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpDiscPrice(MpDiscPriceVO vo) throws Exception {
    }

    /**
	 * mp_disc_price을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpDiscPriceVO
	 * @return 조회한 mp_disc_price
	 * @exception Exception
	 */
    public MpDiscPriceVO selectMpDiscPrice(EgovMap vo) throws Exception {
        MpDiscPriceVO resultVO = mpDiscPriceDAO.selectMpDiscPrice(vo);
        return resultVO;
    }

    /**
	 * mp_disc_price 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_disc_price 목록
	 * @exception Exception
	 */
    public List<?> selectMpDiscPriceList(MpDiscPriceDefaultVO searchVO) throws Exception {
        return mpDiscPriceDAO.selectMpDiscPriceList(searchVO);
    }

    /**
	 * mp_disc_price 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_disc_price 총 갯수
	 * @exception
	 */
    public int selectMpDiscPriceListTotCnt(MpDiscPriceDefaultVO searchVO) {
		return mpDiscPriceDAO.selectMpDiscPriceListTotCnt(searchVO);
	}

    public List<?> selectMpDiscPriceDetailList(MpDiscPriceDefaultVO searchVO) throws Exception {
        return mpDiscPriceDAO.selectMpDiscPriceDetailList(searchVO);
    }
    public int selectMpDiscPriceListDetailCnt(MpDiscPriceDefaultVO searchVO) {
    	return mpDiscPriceDAO.selectMpDiscPriceListDetailCnt(searchVO);
	}
}
