package nds.mpm.prod.PP0304.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0304.service.MpAcceptOrdrPmMDAO;
import nds.mpm.prod.PP0304.service.MpAcceptOrdrPmMService;
import nds.mpm.prod.PP0304.vo.MpAcceptOrdrPmMDefaultVO;
import nds.mpm.prod.PP0304.vo.MpAcceptOrdrPmMVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpAcceptOrdrPmMServiceImpl.java
 * @Description : MpAcceptOrdrPmM Business Implement class
 * @Modification Information
 *
 * @author 생산계획입력(PM)
 * @since 생산계획입력(PM)
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("mpAcceptOrdrPmMService")
public class MpAcceptOrdrPmMServiceImpl extends EgovAbstractServiceImpl implements
        MpAcceptOrdrPmMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpAcceptOrdrPmMServiceImpl.class);

    @Resource(name="mpAcceptOrdrPmMDAO")
    private MpAcceptOrdrPmMDAO mpAcceptOrdrPmMDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpAcceptOrdrPmMIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

    /**
	 * 접수
	 * 
	 * ACCEPT SAVE
	 * @exception Exception
	 */
    public ResultEx insertMpAcceptOrdrPmM(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );

    	/**
    	 * UPDATE mp_order_d
			SET pm_accept_yn = 'Y'
    	 * */
    	for(EgovMap vo: vos)
    	{
    		vo.put("pmAcceptYn" , "Y");
    	}
    	
    	List<EgovMap> returnList = new ArrayList();
    	
    	int iCnt = 0;
    	int uCnt = 0;
    	for(int i = 0; i<vos.size(); i++)
    	{
    		EgovMap inMap = vos.get(i);
    		String planNo = null;
    				
    		planNo = mpAcceptOrdrPmMDAO.selectMpAcceptOrdrPmPMlanNo(inMap);
			inMap.put("planNo" , planNo);
			inMap.put("status" , "0");
					
    		mpAcceptOrdrPmMDAO.insertMpAcceptOrdrPmM(inMap);
    		
    		iCnt++;
    		
    		uCnt = mpAcceptOrdrPmMDAO.updateMpOrderD(inMap);
    		
    		if(uCnt < 1)
        	{
    			LOGGER.error("uCnt :: " + uCnt);
    			
        		throw new Exception(inMap.get("ordr_no") + " accept_yn up fail!!");
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
	 * 접수취소
	 * 
	 * ACCEPT CANCEL
	 * @exception Exception
	 */
    public ResultEx updateMpCancel(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );

    	/**
    	 * UPDATE mp_order_d
			SET pm_accept_yn = 'Y'
    	 * */
    	for(EgovMap vo: vos)
    	{
    		vo.put("pmAcceptYn" , "N");
    	}
    	
    	int iCnt = 0;
    	int uCnt = 0;
    	
    	int notCanceled = 0;
    	int intSuccess = 0;
    	
    	for(int i = 0; i<vos.size(); i++)
    	{
    		EgovMap inMap = vos.get(i);
    		
    		MpAcceptOrdrPmMVO checkVo = mpAcceptOrdrPmMDAO.selectMpAcceptOrdrPmM(inMap);
    		if("0".equals(checkVo.getStatus()))
    		{
	    		iCnt = mpAcceptOrdrPmMDAO.deleteMpAcceptOrdrPmM(inMap);
	
	    		if(iCnt < 1)
	        	{
	        		//throw new Exception(inMap.get("ordr_no") + " remove fail!!");
	    			LOGGER.debug(inMap.get("ordrNum") + " deleteMpAcceptOrdrPmM iCnt :: " + iCnt);
	        	}
	    		
	    		uCnt = mpAcceptOrdrPmMDAO.updateMpOrderD(inMap);
	    		
	    		if(uCnt < 1)
	        	{
	        		//throw new Exception(inMap.get("ordr_no") + " accept_yn up fail!!");
	    			LOGGER.debug(inMap.get("ordrNum") + " updateMpOrderD uCnt :: " + uCnt);
	        	}
	    		
	    		intSuccess++;
    		}
    		else{
    			
    			notCanceled++;
    		}
    	}
    	
    	result.setExtraData(intSuccess);
    	
    	if(notCanceled > 0)
    	{
        	result.setResultCode(notCanceled);
    	}
    	
    	return result;
    }

    /**
	 * mp_accept_ordr_pm_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpAcceptOrdrPmMVO
	 * @return void형 
	 * @exception Exception
	 */
    public int deleteMpAcceptOrdrPmM(EgovMap vo) throws Exception {
        return mpAcceptOrdrPmMDAO.deleteMpAcceptOrdrPmM(vo);
    }

    /**
	 * mp_accept_ordr_pm_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpAcceptOrdrPmMVO
	 * @return 조회한 mp_accept_ordr_pm_m
	 * @exception Exception
	 */
    public MpAcceptOrdrPmMVO selectMpAcceptOrdrPmM(EgovMap vo) throws Exception {
        MpAcceptOrdrPmMVO resultVO = mpAcceptOrdrPmMDAO.selectMpAcceptOrdrPmM(vo);
        return resultVO;
    }
    
    /**
	 * 미접수분 조회 mp_order_d
	 * @exception Exception
	 */
    public List<?> selectMpOrderDList(MpAcceptOrdrPmMDefaultVO searchVO) throws Exception {
        return mpAcceptOrdrPmMDAO.selectMpOrderDList(searchVO);
    }

    /**
	 * 미접수분 조회 mp_order_d
	 * @return mp_order_d 총 갯수
	 * @exception
	 */
    public int selectMpOrderDListTotCnt(MpAcceptOrdrPmMDefaultVO searchVO) {
		return mpAcceptOrdrPmMDAO.selectMpOrderDListTotCnt(searchVO);
	}
    

    /**
	 * mp_accept_ordr_pm_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_accept_ordr_pm_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpAcceptOrdrPmMList(MpAcceptOrdrPmMDefaultVO searchVO) throws Exception {
        return mpAcceptOrdrPmMDAO.selectMpAcceptOrdrPmMList(searchVO);
    }

    /**
	 * mp_accept_ordr_pm_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_accept_ordr_pm_m 총 갯수
	 * @exception
	 */
    public int selectMpAcceptOrdrPmMListTotCnt(MpAcceptOrdrPmMDefaultVO searchVO) {
		return mpAcceptOrdrPmMDAO.selectMpAcceptOrdrPmMListTotCnt(searchVO);
	}
    
}
