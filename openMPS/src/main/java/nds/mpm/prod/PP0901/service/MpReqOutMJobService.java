package nds.mpm.prod.PP0901.service;

import java.util.ArrayList;
import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.prod.PP0901.service.impl.MpReqOutMServiceImpl;
import nds.mpm.prod.PP0901.vo.MpReqOutMVO;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpReqOutMService.java
 * @Description : MpReqOutM Business class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpReqOutMJobService extends QuartzJobBean {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(MpReqOutMJobService.class);

    private MpReqOutMDAO mpReqOutMDAO;
	
    /**
     * src/main/resources/nds/spring/context-scheduled.xml
     * 매일 오후 23:50분에 Batch Job 수행
     * 당일 생산실적을 기준으로 BOM과 Join하여 부자재 사용량(출고 요청량)을 산출  (MP_REQ_OUT_M 테이블 Insert 처리)
           Insert시 default 값 규칙
             -. ODERNO (출고번호) : *
             -. ODERSN (출고순번) : 0
             -. DC (물류센터) : 해당 공장에 대한 창고
             -. OUT_STATUS (출고상태) : 0

       ② 출고 요청량 형성후 물류시스템(WMS) 서비스 Call
             서비스명 : 미정   (WMS 확인 요)

       ③ 출고결과를 Update
             -. ODERNO (출고번호) :  WMS출고번호
             -. ODERSN (출고순번)  :  WMS출고순번
             -. OUT_STATUS (출고상태) : SUCCESS or ERROR
             -. ERROR_MSG (출고오류 내역) : ERROR 내역에 대한 메시지 
             
     * **/
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		
		try{
			
			//insertBatchJobMpReqOutM(null);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	public ResultEx insertBatchJobMpReqOutM(MpReqOutMVO vo) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );

    	List<EgovMap> batchList =  mpReqOutMDAO.selectBomCurDayWorkList(vo);
    	
    	for(EgovMap inMap : batchList)
    	{
    		inMap.put("workDate", inMap.get("workDate"));
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
    	
    		/**
			 * 출고요청량형성 성공. WMS 출고 
			 * batch insert success
			 * call WMS service
			 * 
			 * */
    		EgovMap firstRow = batchList.get(0);
    		EgovMap wmsResult = mpReqOutMDAO.callWMSSetMtrlOder(firstRow);
    		
    		if("Y".equals((String)wmsResult.get("resultCdoe")))
    		{
    			
    		}
    		else
    		{
    			LOGGER.error("PP0901 wmsResult=N :: " + wmsResult.get("recultMsg"));
    			throw new Exception("PP0901 insertMpReqOutM ERROR  wmsResult=N  :: " + wmsResult.get("recultMsg") );
    		}
	    	
	    	result.setExtraData(wmsResult);
	    	
	    }
    	else
    	{
    		result  = new ResultEx( Consts.ResultCode.RC_FIND_NOT_FOUND );
    	}
    	
    	return result;
    }
	
	public void setMpReqOutMDAO(MpReqOutMDAO mpReqOutMDAO){
		this.mpReqOutMDAO = mpReqOutMDAO;
	}
	
}
