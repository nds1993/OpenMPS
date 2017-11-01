package nds.mpm.prod.PP0305.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0103.service.MpItemMasterMDAO;
import nds.mpm.prod.PP0103.vo.MpItemMasterMVO;
import nds.mpm.prod.PP0304.service.MpAcceptOrdrPmMDAO;
import nds.mpm.prod.PP0305.service.MpPlanPmMDAO;
import nds.mpm.prod.PP0305.service.MpPlanPmMService;
import nds.mpm.prod.PP0305.vo.MpPlanPmMDefaultVO;
import nds.mpm.prod.PP0305.vo.MpPlanPmMVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPlanPmMServiceImpl.java
 * @Description : MpPlanPmM Business Implement class
 * @Modification Information
 *
 * @author 생산의뢰 접수 (PM주문)
 * @since 생산의뢰 접수 (PM주문)
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpPlanPmMService")
public class MpPlanPmMServiceImpl extends TMMPPBaseService implements
        MpPlanPmMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpPlanPmMServiceImpl.class);

    @Resource(name="mpItemMasterMDAO")
    private MpItemMasterMDAO mpItemMasterMDAO;
    
    @Resource(name="mpAcceptOrdrPmMDAO")
    private MpAcceptOrdrPmMDAO mpAcceptOrdrPmMDAO;
    
    @Resource(name="mpPlanPmMDAO")
    private MpPlanPmMDAO mpPlanPmMDAO;
    
	/**
	 * mp_plan_pm_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpPlanPmMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertMpPlanPmM(List<EgovMap> vos, String status) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );

    	List<EgovMap> returnList = new ArrayList();
    	
    	if(vos == null)
    	{
    		return new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}
    	if(vos.size() == 0)
    	{
    		return new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}
    	
    	EgovMap firstMap = vos.get(0);
    	
    	for(int i = 0; i<vos.size(); i++)
    	{
    		EgovMap inMap = vos.get(i);
    		
    		// PM 생산계획 신규 new pro_code, insert mp_accept_ordr_pm_m
    		if("D".equals((String)inMap.get("dsType")))
    			deletePlanMpPlanPmM(inMap, status);
    		else
    		{
    			if(mpPlanPmMDAO.selectMpPlanPmM(inMap) != null)
        		{
    				if("0".equals(status))
    					inMap.put("status","1");
        			mpPlanPmMDAO.updateMpPlanPmM(inMap);
        			
        			if(mpPlanPmMDAO.updateMpAcceptOrdrPmMStatus(inMap) < 1)
        	    	{
        	    		throw new Exception(inMap.get("ordr_no") + " status up fail!!");
        	    	}
        		}
    			else
        		{
    				
    				// 계획진행상태에서 신규 제품 추가시
    				if("C".equals((String)inMap.get("dsType")))
    				{
    					String newPlanNo = mpAcceptOrdrPmMDAO.selectMpAcceptOrdrPmPMlanNo(firstMap); 
        		    	
        		    	LOGGER.debug("newPlanNo :: " + newPlanNo);
        				
        				// PM 생산계획 신규 new pro_code plan_no create
            			inMap.put("planNo", newPlanNo);
    					
	    				EgovMap sProd = new EgovMap();
	        			sProd.put("corpCode",(String)inMap.get("corpCode"));
	        			sProd.put("proCode",(String)inMap.get("proCode"));
	        			
	        			MpItemMasterMVO findProd = mpItemMasterMDAO.selectMpItemMasterM(sProd);
	        			
	        			// PM 생산계획 신규 new pro_code fix.
	        			inMap.put("ordrNo", "99999");
	        			
	        			String newOrdrSeq = mpAcceptOrdrPmMDAO.selectMpPlanPmMAddProdSeq(inMap);
	        			inMap.put("ordrSeq", newOrdrSeq);
	        			inMap.put("ordrBox", inMap.get("workQty"));
	        			inMap.put("ordrWeight", "0");
	        			//안성 PM 공장코드 
	        			inMap.put("plantCode", "14");
	        			inMap.put("proUnit", findProd.getProUkind());
	        			inMap.put("status", "0");
	        			String returnPlanNo = mpAcceptOrdrPmMDAO.insertMpAcceptOrdrPmM(inMap);
	        			
	        		}

    				insertPlanMpPlanPmM(inMap);
        		}
    		}
    		
    		returnList.add(inMap);
    	}
    	
    	PageSet pageSet = new PageSet();
    	pageSet.setTotalRecordCount(returnList.size());
    	pageSet.setResult(returnList);
    	result.setExtraData(pageSet);
    	return result;
    }
    
    /**
     * 추가, 수정을 저장
     * 접수 , 계획상태에서만 가능 
     * if status = 0 or 1 OK
     * 
     * */
    public EgovMap insertPlanMpPlanPmM(EgovMap inMap) throws Exception {
    	
    	/**
    	 * status = plan
    	 * */
    	inMap.put("status" , "1");

    	mpPlanPmMDAO.insertMpPlanPmM(inMap);
    	
    	if(mpPlanPmMDAO.updateMpAcceptOrdrPmMStatus(inMap) < 1)
    	{
    		throw new Exception(inMap.get("ordr_no") + " status up fail!!");
    	}
    	
    	return inMap;
    }
    
    /**
	 * 삭제 계획진행, 확정의 경우만 가능.
	 * @exception Exception
	 */
    public EgovMap deletePlanMpPlanPmM(EgovMap inMap, String status) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );

    	if("3".equals(status))
    	{
    		/**
    		 * 계획확정분 
        	 * */
    		inMap.put("status" , "1");
        	
    		if(mpPlanPmMDAO.updateMpPlanPmMStatus(inMap) < 1)
        	{
        		throw new Exception(inMap.get("plan_no") + " status confirm fail!!");
        	}
    	}
    	else if("1".equals(status))
    	{
    		/**
    		 * 계획진행분 
        	 * */
    		inMap.put("status" , "0");
    		
    		mpPlanPmMDAO.updateMpAcceptOrdrPmMStatus(inMap);

    		if(mpPlanPmMDAO.updateMpPlanPmMStatus(inMap) < 1)
        	{
        		throw new Exception(inMap.get("plan_no") + " status confirm fail!!");
        	}
    	}
    	else if("0".equals(status))
    	{
    		/**
    		 * 접수분 
        	 * */
    		inMap.put("status", "0");
    		if(mpAcceptOrdrPmMDAO.deleteMpAcceptOrdrPmM(inMap) < 1)
        	{
        		throw new Exception(inMap.get("plan_no") + " status confirm fail!!");
        	}
    	}
    	
    	return inMap;
    }
    
    /**
     * 계획확정 status = complete
     * 
     * */
    public ResultEx updateMpPlanPmMConfirm(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );

    	/**
    	 * */
    	for(EgovMap vo: vos)
    	{
    		vo.put("status" , "3");
    	}
    	
    	int iCnt = 0;
    	int uCnt = 0;
    	for(int i = 0; i<vos.size(); i++)
    	{
    		EgovMap inMap = vos.get(i);
    		iCnt = mpPlanPmMDAO.updateMpPlanPmMStatus(inMap);

    		if(iCnt < 1)
        	{
        		throw new Exception(inMap.get("plan_no") + " status confirm fail!!");
        	}
    	}
    	/***
    	 * 확정시 주문접수테이블 상태 변경안함.
    	for(int i = 0; i<vos.size(); i++)
    	{
    		EgovMap inMap = vos.get(i);
    		uCnt = mpPlanPmMDAO.updateMpAcceptOrdrPmMStatus(inMap);
    		
    		if(uCnt < 1)
        	{
        		throw new Exception(inMap.get("plan_no") + " status confirm fail!!");
        	}
    	}
    	**/
    	return result;
    }

    /**
	 * mp_plan_pm_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpPlanPmMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpPlanPmM(MpPlanPmMVO vo) throws Exception {
        //mpPlanPmMDAO.updateMpPlanPmM(vo);
    }
    
    public void deleteMpPlanPmM(MpPlanPmMVO vo) throws Exception {
    }

    /**
	 * mp_plan_pm_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpPlanPmMVO
	 * @return 조회한 mp_plan_pm_m
	 * @exception Exception
	 */
    public MpPlanPmMVO selectMpPlanPmM(EgovMap vo) throws Exception {
        MpPlanPmMVO resultVO = mpPlanPmMDAO.selectMpPlanPmM(vo);
        return resultVO;
    }

    /**
	 * mp_plan_pm_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_pm_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpPlanPmMList(MpPlanPmMDefaultVO searchVO) throws Exception {
        return mpPlanPmMDAO.selectMpPlanPmMList(searchVO);
    }

    /**
	 * mp_plan_pm_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_plan_pm_m 총 갯수
	 * @exception
	 */
    public int selectMpPlanPmMListTotCnt(MpPlanPmMDefaultVO searchVO) {
		return mpPlanPmMDAO.selectMpPlanPmMListTotCnt(searchVO);
	}
    
    public List<?> selectMpAcceptOrdrPmMList(MpPlanPmMDefaultVO searchVO) throws Exception {
        return mpPlanPmMDAO.selectMpAcceptOrdrPmMList(searchVO);
    }
    /**
	 * 접수분 조회 MpAcceptOrdrPmM 총 갯수
	 * @exception
	 */
    public int selectMpAcceptOrdrPmMListTotCnt(MpPlanPmMDefaultVO searchVO) {
		return mpPlanPmMDAO.selectMpAcceptOrdrPmMListTotCnt(searchVO);
	}
    
    public List<?> selectMpPlanPmMListPlanTime(MpPlanPmMDefaultVO searchVO) throws Exception {
    	return mpPlanPmMDAO.selectMpPlanPmMListPlanTime(searchVO);
    }
    public List<?> selectPP0306MpPlanPmMList(MpPlanPmMDefaultVO searchVO) throws Exception {
    	return mpPlanPmMDAO.selectPP0306MpPlanPmMList(searchVO);
    }
}
