package nds.mpm.prod.PP0105.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0103.service.MpItemMasterMDAO;
import nds.mpm.prod.PP0103.vo.MpItemMasterMVO;
import nds.mpm.prod.PP0105.service.MpBomDDAO;
import nds.mpm.prod.PP0105.service.MpBomHDAO;
import nds.mpm.prod.PP0105.service.MpBomHService;
import nds.mpm.prod.PP0105.vo.MpBomDVO;
import nds.mpm.prod.PP0105.vo.MpBomHDefaultVO;
import nds.mpm.prod.PP0105.vo.MpBomHVO;
import nds.mpm.prod.PP0105.vo.MultiMpBomVO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpBomHServiceImpl.java
 * @Description : MpBomH Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpBomHService")
public class MpBomHServiceImpl extends TMMPPBaseService implements
        MpBomHService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpBomHServiceImpl.class);

    @Resource(name="mpBomHDAO")
    private MpBomHDAO mpBomHDAO;
    
    @Resource(name="mpBomDDAO")
    private MpBomDDAO mpBomDDAO;
    
    @Resource(name="mpItemMasterMDAO")
    private MpItemMasterMDAO mpItemMasterMDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpBomHIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_bom_h을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpBomHVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpBomH(MultiMpBomVO reqVo) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	if(reqVo == null)
    	{
    		return new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}
    	
    	MpBomHVO head = reqVo.getHead();
    	List<EgovMap> details = reqVo.getDetail();

    	head.setDsType((String)reqVo.getDsType());
    	
    	int uCnt = 0;
    	
    	if("C".equals(head.getDsType()))
		{
    		if( mpBomHDAO.checkDupMpBomHCnt(head) > 0)
    		{
    			result.setMsg("해당 제품은 이미 BOM이 존재 합니다. 확인후 등록하십시오");
    			result.setResultCode(Consts.ResultCode.RC_DUPLICATE.getCode());
    			return result;
    		}
		}
    	
    	//head value check
    	if("D".equals(head.getDsType()))
    	{
    		mpBomHDAO.deleteMpBomH(head);
    		for(EgovMap vo: details)
        	{
    			mpBomDDAO.deleteMpBomD(vo);
        	}
    	}
    	else if("C".equals(head.getDsType()) || "U".equals(head.getDsType()))
    	{
    		/**
    		 * 화면에서 전달된 detail 이 없으면 head만 수정이므로 기존 bom detail 조회해서 버전업그레이드저장.
    		 * */
    		if(details.size() == 0)
        	{
    			MpBomDVO searchVO = new MpBomDVO();
    	     	searchVO.setCorpCode(head.getCorpCode());
    	     	searchVO.setPlantCode(head.getPlantCode());
    	     	searchVO.setProCode(head.getProCode());
    	     	searchVO.setBomCode(head.getBomCode());
    	     	searchVO.setBomVer(head.getBomVer());
    	     	
    	     	details = mpBomDDAO.selectMpBomDList(searchVO);
    	     	
    	     	reqVo.setDetail(details);
        	}
    		
    		if(!setItemValues(reqVo))
    		{
    			return new ResultEx( Consts.ResultCode.RC_INVALID_PARAMS );
    		}
    		
    		EgovMap recentSearchMap = new EgovMap();
    		recentSearchMap.put("corpCode", head.getCorpCode());
    		recentSearchMap.put("plantCode", head.getPlantCode());
    		recentSearchMap.put("proCode", head.getProCode());
    		recentSearchMap.put("bomCode", head.getBomCode());
    		
    		int maxVer = mpBomHDAO.selectLastBomVer(recentSearchMap);
    		
    		recentSearchMap.put("bomVer", maxVer);
    		recentSearchMap.put("filmWeig", head.getFilmWeig());
    		recentSearchMap.put("boxWeig", head.getBoxWeig());
    		recentSearchMap.put("etcWeig", head.getEtcWeig());
    		recentSearchMap.put("totWeig", head.getTotWeig());
    		recentSearchMap.put("crUser", head.getCrUser());
    		recentSearchMap.put("changeList", head.getChangeList());
    		recentSearchMap.put("memo", head.getMemo());
    		
    		head.setBomVer(maxVer);
    		
    		String newKey = null;
    		if("C".equals(head.getDsType()))
    		{
    			for(int i = 0; i< details.size(); i++)
            	{
            		EgovMap inMap = details.get(i);
            		inMap.put("bomVer" , maxVer);
            		
            		newKey = mpBomDDAO.insertMpBomD(inMap);
            		if(newKey == null)
        			{
        				throw new Exception("insert bomD falil!!");
        			}
            	}
    			
            	mpBomHDAO.insertMpBomH(recentSearchMap);
    		}
    		else if("U".equals(head.getDsType()))
    		{
    			LOGGER.debug("changeList :: " + head.getChangeList());
    			
        		uCnt = mpBomHDAO.updateMpBomHVerNotUse(recentSearchMap);
        		if(uCnt == 0)
    			{
    				throw new Exception("old ver header not use falil!!");
    			}
        		
        		for(EgovMap vo: details)
            	{
        			if(!"D".equals((String)vo.get("dsType")))
        			{
        				vo.put("bomVer" , maxVer);
                		vo.put("memo" , vo.get("bumemo"));
                		
                		newKey = mpBomDDAO.insertMpBomD(vo);
            			if(newKey == null)
            			{
            				throw new Exception("upgrade bomD falil!!");
            			}
        			}
            	}
        		
        		mpBomHDAO.insertMpBomH(recentSearchMap);
    		}
        	
    	}
    	
    	result.setExtraData(reqVo);
        return result;
    }
    
    public boolean setItemValues(MultiMpBomVO reqVo) throws Exception{
    	
    	int checkFlag = 0;
    	int lineNo = 1;
    	double intOrdrWeight = 0;
    	
    	MpItemMasterMVO findProd = null;
    	
    	MpBomHVO head = reqVo.getHead();
    	List<EgovMap> details = reqVo.getDetail();
    	
    	/**
		 * value check
		 * change item check
		 * 
		 * */
    	StringBuffer changeItems = new StringBuffer();
    	boolean isChanged = false;
    	for(EgovMap dt: details)
    	{
    		checkFlag = 0;
    		intOrdrWeight = 0;
    		
    		if(StringUtils.isEmpty((String)dt.get("proType"))) checkFlag = -1;
    		if(StringUtils.isEmpty((String)dt.get("buProCode"))) checkFlag = -1;
    		
    		if(checkFlag == -1) break;
    		
    		String proType = StringUtils.trim((String)dt.get("proType"));
    		String buProCode = StringUtils.trim((String)dt.get("buProCode"));
    		String qty = StringUtils.defaultIfEmpty((String)dt.get("qty"),"0");
    		String dsType = (String)dt.get("dsType");
    		String memo = StringUtils.trim((String)dt.get("bumemo"));
    		String proUnit = StringUtils.trim((String)dt.get("proUnit"));
    		
    		double intQty = Double.parseDouble(qty);
    		
    		//head에만 셋팅된 value copy
    		dt.put("corpCode",head.getCorpCode());
    		dt.put("bomCode" , head.getProCode());
    		dt.put("bomVer" , head.getBomVer());
    		dt.put("lineNo" , lineNo);
    		dt.put("crUser" , head.getCrUser());
    		dt.put("mdUser" , head.getCrUser());
    		
    		dt.put("proType",proType);
    		dt.put("proCode",buProCode);
    		dt.put("memo",memo);
    		dt.put("plantCode" , head.getPlantCode());
    		
    		//findProd = mpItemMasterMDAO.selectMpItemMasterM(dt);

    		//업데이트면 계산값 셋팅.
    		if("C".equals((String)dt.get("dsType")))
			{
				changeItems.append("No" + lineNo  + ".new procode[" + buProCode + "],");
				isChanged = true;
			}
			else if("U".equals((String)dt.get("dsType")))
			{
				MpBomDVO oldvo = mpBomDDAO.selectMpBomD(dt);
				
				if(oldvo != null)
				{
					String chkString = chkChangeItem(oldvo, dt);
    				if(StringUtils.isNotEmpty(chkString))
					{
    					changeItems.append(chkString);
    					isChanged = true;
					}
				}
				else
				{
					changeItems.append("No" + lineNo  + ".new procode[" + buProCode + "],");
					isChanged = true;
				}
				
			}else if("D".equals((String)dt.get("dsType")))
			{
				changeItems.append("No" + lineNo  + ".del procode[" + buProCode + "],");
				isChanged = true;
			}
    		
    		/** 화면에서 계산하는것으로 변경.
    		etcWeig = intOrdrWeight/1000 * head.getEtcWeig();
    		etcWeig = Math.round(etcWeig*100d) / 100d;
    		
    		//item_master pro_ukind setting
    		dt.put("proUnit" , "proUnit");
    		dt.put("etcWeig" , etcWeig);
    		dt.put("totWeig" , totWeig);
    		*/
    		
    		lineNo++;
    	}
    	
    	if(isChanged)
    	{
    		head.setChangeList(changeItems.toString());
    	}
    	
    	double dboxWeig = head.getBoxWeig();
    	double dfilmWeig = head.getFilmWeig();
    	double detcWeig = head.getEtcWeig();
    	double dtotWeig = head.getTotWeig();
    	
    	head.setBoxWeig(dboxWeig);
    	head.setFilmWeig(dfilmWeig);
    	head.setEtcWeig(detcWeig);
		head.setTotWeig( dtotWeig );
		
		LOGGER.debug("totWeig :: " + head.getTotWeig());
    	
    	return checkFlag == 0;
    	
    }
    
    /***
     * change memo memo character varying(200), -- 비고
     * 
     * pro_type character varying(1) NOT NULL, -- 부자재구분 : TM_CODEXD-부자재구분
	  pro_code character varying(20) NOT NULL, -- 부자재코드 : MP_ITEM_MASTER_M
	  qty numeric(15,2) NOT NULL, -- 수량
	  pro_unit character varyi
     * */
    public String chkChangeItem(MpBomDVO oldvo, EgovMap upvo) throws Exception {
        
    	StringBuffer changeMemo = new StringBuffer();
    	
        if(oldvo.getProType() != null 
        	&& oldvo.getProType().compareTo((String)upvo.get("proType")) != 0)
        {
        	changeMemo.append("proType::" + oldvo.getProType()+ "->" + upvo.get("proType") +"," );
        }
        if(oldvo.getBuProCode() != null 
            	&& oldvo.getBuProCode().compareTo((String)upvo.get("buProCode")) != 0)
        {
        	changeMemo.append("proCode::" + oldvo.getBuProCode()+ "->" + upvo.get("buProCode") +"," );
        }
        if(oldvo.getQty() != Integer.parseInt(StringUtils.defaultIfEmpty((String)upvo.get("qty"),"0")) )
        {
        	changeMemo.append("qty::" + oldvo.getQty()+ "->" + upvo.get("qty") +"," );
        }
        if(oldvo.getProUnit() != null 
            	&& oldvo.getProUnit().compareTo((String)upvo.get("proUnit")) != 0)
        {
        	changeMemo.append(oldvo.getProUnit()+ "->" + upvo.get("proUnit") +"," );
        }
        
        String upmemo = changeMemo.toString();
        
        if(StringUtils.isNotEmpty(upmemo))
        {
        	upmemo = "NO" + upvo.get("lineNo") + "[" + upmemo + "]";
        }
        
        if(upmemo.length() > 200)
        {
        	upmemo = upmemo.substring(0, 200);
        }
        return upmemo;
    }

    /**
	 * mp_bom_h을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpBomHVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpBomH(MpBomHVO vo) throws Exception {
        mpBomHDAO.updateMpBomH(vo);
    }

    /**
	 * mp_bom_h을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpBomHVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpBomH(MpBomHVO vo) throws Exception {
       //.deleteMpBomH(vo);
    }

    /**
	 * mp_bom_h을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpBomHVO
	 * @return 조회한 mp_bom_h
	 * @exception Exception
	 */
    public EgovMap selectMpBomH(EgovMap vo) throws Exception {
        return mpBomHDAO.selectMpBomH(vo);
    }

    /**
	 * mp_bom_h 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bom_h 목록
	 * @exception Exception
	 */
    public List<?> selectMpBomHList(MpBomHDefaultVO searchVO) throws Exception {
        return mpBomHDAO.selectMpBomHList(searchVO);
    }

    /**
	 * mp_bom_h 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bom_h 총 갯수
	 * @exception
	 */
    public int selectMpBomHListTotCnt(MpBomHDefaultVO searchVO) {
		return mpBomHDAO.selectMpBomHListTotCnt(searchVO);
	}
    
    /**
	 * mp_bom_h 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bom_h 목록
	 * @exception Exception
	 */
    public List<?> selectPP0106MpBomDList(MpBomHDefaultVO searchVO) throws Exception {
        return mpBomHDAO.selectPP0106MpBomDList(searchVO);
    }

    /**
	 * mp_bom_h 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_bom_h 총 갯수
	 * @exception
	 */
    public int selectPP0106MpBomHListTotCnt(MpBomHDefaultVO searchVO) {
		return mpBomHDAO.selectPP0106MpBomHListTotCnt(searchVO);
	}
    
    public int checkDupMpBomHCnt(MpBomHDefaultVO searchVO) {
    	return mpBomHDAO.checkDupMpBomHCnt(searchVO);
    }

}
