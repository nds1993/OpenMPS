package nds.mpm.sales.SD0305.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.sales.SD0305.service.MpTargetYymmService;
import nds.mpm.sales.SD0305.vo.MpTargetYymmVO;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name : MpTargetYymmController.java
 * @Description : MpTargetYymm Controller class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/sd0305/mptargetyymm")
public class MpTargetYymmController extends TMMBaseController{

    @Resource(name = "mpTargetYymmService")
    private MpTargetYymmService mpTargetYymmService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_target_yymm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpTargetYymmDefaultVO
	 * @return "/mpTargetYymm/MpTargetYymmList"
	 * @exception Exception
	 */
    @RequestMapping(value="/head/{targetYyyy}/{partCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpTargetYymmList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("partCode") String partCode,
    		@PathVariable("targetYyyy") String targetYyyy,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
     	
    	MpTargetYymmVO searchVO = new MpTargetYymmVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setTargetYyyy(targetYyyy);
    	searchVO.setPartCode(partCode);
    	
    	if("0".equals(partCode)){
    		 searchVO.setPartCode("");
	   	}else{
	   	     searchVO.setPartCode(partCode);
	   	}
    	
    	System.out.println("###############################");
		
        List<?> mpTargetYymmList = mpTargetYymmService.selectMpTargetYymmList(searchVO);
        
        PageSet pageSet = new PageSet();
        
        int totCnt = 0;
        //int totCnt = mpTargetYymmService.selectMpTargetYymmListTotCnt(searchVO);
        if( mpTargetYymmList != null )
        {
        	totCnt = mpTargetYymmList.size();
        }
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpTargetYymmList);
		
    	result.setExtraData(pageSet);
    	return _filter.makeCORSEntity( result );	
    } 
    
    @RequestMapping(value="/detail/{targetYyyy}/{partCode}/{salesman}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectDetailMpTargetYymmList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("partCode") String partCode,
    		@PathVariable("targetYyyy") String targetYyyy,
    		@PathVariable("salesman") String salesman,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
     	
    	MpTargetYymmVO searchVO = new MpTargetYymmVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setTargetYyyy(targetYyyy);
    	searchVO.setPartCode(partCode);
    	searchVO.setSalesman(salesman);
    	
    	if("0".equals(partCode)){
   		 	 searchVO.setPartCode("");
	   	}else{
	   	     searchVO.setPartCode(partCode);
	   	}
		
        List<?> mpTargetYymmList = mpTargetYymmService.selectMpTargetYymmListD(searchVO);
        
        PageSet pageSet = new PageSet();
        
        int totCnt = 0;
        //int totCnt = mpTargetYymmService.selectMpTargetYymmListTotCnt(searchVO);
        if( mpTargetYymmList != null )
        {
        	totCnt = mpTargetYymmList.size();
        }
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpTargetYymmList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );	
    } 

}
