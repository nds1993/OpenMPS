package nds.mpm.prod.PP0501.web;

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
import nds.mpm.prod.PP0501.service.PP0501MpReqOutMService;
import nds.mpm.prod.PP0501.vo.MpReqOutMVO;

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
 * @Class Name : MpReqOutMController.java
 * @Description : MpReqOutM Controller class
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
@RequestMapping("/mpm/{corpCode}/pp0501/mpreqoutm")
public class PP0501MpReqOutMController extends TMMBaseController{

    @Resource(name = "PP0501mpReqOutMService")
    private PP0501MpReqOutMService mpReqOutMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_req_out_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpReqOutMDefaultVO
	 * @return "/mpReqOutM/MpReqOutMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpReqOutMList(
	    	@PathVariable("corpCode") String corpCode,
	    	@PathVariable("strtDate") String strtDate,
	    	@PathVariable("lastDate") String lastDate,
			HttpServletRequest req,
			HttpServletResponse res,
			ModelMap model)
	        throws Exception {
		
	    ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
	    int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
		int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
		
		String plantCode = req.getParameter("plantCode");
		
		MpReqOutMVO searchVO = new MpReqOutMVO();
		searchVO.setCorpCode(corpCode);
		searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
		searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
		searchVO.setPlantCode(plantCode);
		
        List<?> mpReqOutMList = mpReqOutMService.selectMpReqOutMList(searchVO);
	
		PageSet pageSet = new PageSet();
	    
        int totCnt = mpReqOutMService.selectMpReqOutMListTotCnt(searchVO);
	    pageSet.setPageIndex(pageIndex);
		pageSet.setPageSize(pageSize);
		pageSet.setTotalRecordCount(totCnt);
		pageSet.setResult(mpReqOutMList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );
    } 
    
    /**
     * excel
     * */
    @RequestMapping(value="/{strtDate}/{lastDate}/export",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectExMpReqOutMList(
	    	@PathVariable("corpCode") String corpCode,
	    	@PathVariable("strtDate") String strtDate,
	    	@PathVariable("lastDate") String lastDate,
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession jsession,
			ModelMap model)
	        throws Exception {
		
	    ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
		String plantCode = req.getParameter("plantCode");
		
		MpReqOutMVO searchVO = new MpReqOutMVO();
		searchVO.setCorpCode(corpCode);
		searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
		searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
		searchVO.setPlantCode(plantCode);
		
        List<EgovMap> mpReqOutMList = mpReqOutMService.selectMpReqOutMList(searchVO);
	
        String[] columns = PP.PP0501;
        String sheetName = PP.PP0501_NM;
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpReqOutMList, columns, PP.PP0501_TYPE, sheetName);
		
		return _filter.makeCORSEntity( result );
    } 
}
