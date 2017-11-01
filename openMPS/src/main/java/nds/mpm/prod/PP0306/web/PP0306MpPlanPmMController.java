package nds.mpm.prod.PP0306.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.mpm.common.util.ExcelUtil;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.excel.PP;
import nds.mpm.prod.PP0305.service.MpPlanPmMService;
import nds.mpm.prod.PP0305.vo.MpPlanPmMVO;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPlanPmMController.java
 * @Description : MpPlanPmM Controller class
 * @Modification Information
 *
 * @author 생산계획서 조회 및 발행(PM)
 * @since 생산계획서 조회 및 발행(PM)
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/pp0306/mpplanpmm")
public class PP0306MpPlanPmMController extends TMMBaseController {

    @Resource(name = "mpPlanPmMService")
    private MpPlanPmMService mpPlanPmMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @RequestMapping(value="/{plantCode}/{workDate}/time",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPlanTimePmMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("workDate") String workDate,
    		HttpServletRequest req,
    		HttpServletResponse res,  
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
    	MpPlanPmMVO searchVO = new MpPlanPmMVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPlantCode(plantCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	
    	List<?> mpLpcInfoMList = mpPlanPmMService.selectMpPlanPmMListPlanTime(searchVO);
        
    	PageSet pageSet = new PageSet();
    	if(mpLpcInfoMList != null)
    		pageSet.setTotalRecordCount(mpLpcInfoMList.size());
    	pageSet.setResult(mpLpcInfoMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
	
    /**
	 * mp_plan_pm_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPlanPmMDefaultVO
	 * @return "/mpPlanPmM/MpPlanPmMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{plantCode}/{workDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPlanPmMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("workDate") String workDate,
    		HttpServletRequest req,
    		HttpServletResponse res,  
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String planNo = req.getParameter("planTime");
    	
    	MpPlanPmMVO searchVO = new MpPlanPmMVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPlantCode(plantCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	
    	//확정분만 조회
    	searchVO.setStatus("3");
    	searchVO.setPlanNo(planNo);
    	
    	List<?> mpLpcInfoMList = mpPlanPmMService.selectPP0306MpPlanPmMList(searchVO);
        
    	PageSet pageSet = new PageSet();
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	
    	if(mpLpcInfoMList!=null)
    	pageSet.setTotalRecordCount(mpLpcInfoMList.size());
    	pageSet.setResult(mpLpcInfoMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    /**
	 * mp_plan_pm_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPlanPmMDefaultVO
	 * @return "/mpPlanPmM/MpPlanPmMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{plantCode}/{workDate}/export",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectExMpPlanPmMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("workDate") String workDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
    	MpPlanPmMVO searchVO = new MpPlanPmMVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPlantCode(plantCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	searchVO.setStatus("3");
    	
    	List<EgovMap> mpLpcInfoMList = mpPlanPmMService.selectPP0306MpPlanPmMList(searchVO);
        
    	String[] columns = PP.PP0306;
        String sheetName = PP.PP0306_NM;
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpLpcInfoMList, columns, PP.PP0306_TYPE, sheetName);
    	return null;
    } 
}
