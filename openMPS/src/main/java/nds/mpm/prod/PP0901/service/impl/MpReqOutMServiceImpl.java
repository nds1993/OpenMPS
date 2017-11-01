package nds.mpm.prod.PP0901.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0901.service.MpReqOutMDAO;
import nds.mpm.prod.PP0901.service.MpReqOutMService;
import nds.mpm.prod.PP0901.vo.MpReqOutMDefaultVO;
import nds.mpm.prod.PP0901.vo.MpReqOutMVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpReqOutMServiceImpl.java
 * @Description : MpReqOutM Business Implement class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PP0901mpReqOutMService")
public class MpReqOutMServiceImpl extends EgovAbstractServiceImpl implements
        MpReqOutMService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(MpReqOutMServiceImpl.class);

    @Resource(name="PP0901mpReqOutMDAO")
    private MpReqOutMDAO mpReqOutMDAO;
    
    /** ID Generation */
    //@Resource(name="{egovMpReqOutMIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * mp_req_out_m을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpReqOutMVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertMpReqOutM(EgovMap vo) throws Exception {
    	
    	mpReqOutMDAO.insertMpReqOutM(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * mp_req_out_m을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpReqOutMVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateMpReqOutM(MpReqOutMVO vo) throws Exception {
        mpReqOutMDAO.updateMpReqOutM(vo);
    }

    /**
	 * mp_req_out_m을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpReqOutMVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteMpReqOutM(MpReqOutMVO vo) throws Exception {
        mpReqOutMDAO.deleteMpReqOutM(vo);
    }

    /**
	 * mp_req_out_m을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpReqOutMVO
	 * @return 조회한 mp_req_out_m
	 * @exception Exception
	 */
    public MpReqOutMVO selectMpReqOutM(MpReqOutMVO vo) throws Exception {
        MpReqOutMVO resultVO = mpReqOutMDAO.selectMpReqOutM(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * mp_req_out_m 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_req_out_m 목록
	 * @exception Exception
	 */
    public List<?> selectMpReqOutMList(MpReqOutMDefaultVO searchVO) throws Exception {
        return mpReqOutMDAO.selectMpReqOutMList(searchVO);
    }

    /**
	 * mp_req_out_m 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_req_out_m 총 갯수
	 * @exception
	 */
    public int selectMpReqOutMListTotCnt(MpReqOutMDefaultVO searchVO) {
		return mpReqOutMDAO.selectMpReqOutMListTotCnt(searchVO);
	}
    
    public List<?> selectBomCurDayWorkList(MpReqOutMDefaultVO searchVO) throws Exception {
        return mpReqOutMDAO.selectBomCurDayWorkList(searchVO);
    }
    
    public ResultEx insertBatchJobSample() throws Exception {
    	
    	LOGGER.debug("insertBatchJobSample run :: " + new Date());
    	
    	return null;
    }

    public ResultEx insertBatchJobMpReqOutM(MpReqOutMVO vo) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	EgovMap plantCodeMap = new EgovMap();
    	List<EgovMap> batchList =  mpReqOutMDAO.selectBomCurDayWorkList(vo);
    	String workDate = null;
    	
    	for(EgovMap inMap : batchList)
    	{
    		plantCodeMap.put(inMap.get("plantCode"), inMap.get("workDate"));
    		workDate = (String)inMap.get("workDate");
    		
    		inMap.put("workDate", workDate);
    		inMap.put("oderno", "*");
    		inMap.put("odersn", 0);
    		inMap.put("dc", inMap.get("dc"));
    		inMap.put("outStatus", "0");
    		inMap.put("crUser", "SYSTEM");
    	}
    	
    	String newKey = null;
    	for(EgovMap inMap : batchList)
    	{
    		newKey = mpReqOutMDAO.insertMpReqOutM(inMap);
    		if(newKey == null)
    		{
    			LOGGER.error("PP0901 ERROR :: " + vo.getWorkDate() + "  Batch Insert error");
    			throw new Exception("PP0901 insertMpReqOutM ERROR :: " + vo.getWorkDate());
    		}
    	}
    	
    	result.setExtraData(batchList);
    	
    	if(batchList != null && batchList.size() > 0)
    	{
    		List<EgovMap> plantList = new ArrayList();
    		Iterator plantCodes = plantCodeMap.keySet().iterator();
			
			while (plantCodes.hasNext()) {
				String plantCode = (String) plantCodes.next();
				
				plantCodeMap = new EgovMap();
				plantCodeMap.put("plantCode", plantCode);
				plantCodeMap.put("workDate", workDate);
				plantList.add(plantCodeMap);
			}
    	
    		/**
			 * 출고요청량형성 성공. WMS 출고 
			 * batch insert success
			 * call WMS service
			 * 
			 * */
    		EgovMap wmsResult = null;
    		for(EgovMap inMap : plantList)
        	{
    			wmsResult = mpReqOutMDAO.callWMSSetMtrlOder(inMap);
        		
        		if("Y".equals((String)wmsResult.get("resultCdoe")))
        		{
        			
        		}
        		else
        		{
        			LOGGER.error("PP0901 wmsResult=N :: " + wmsResult.get("recultMsg"));
        			LOGGER.error("PP0901 insertMpReqOutM ERROR  wmsResult=N  :: " + wmsResult.get("recultMsg") );
        		}
        	}
    		
    		plantList = null;
	    	
	    	result.setExtraData(wmsResult);
	    }
    	else
    	{
    		result  = new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}
    	
    	batchList = null;
    	
    	return result;
    }
}
