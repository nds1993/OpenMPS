package nds.mpm.prod.PP0701.web;

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
import nds.mpm.prod.PP0701.service.PP0701OdOderhdService;
import nds.mpm.prod.PP0701.vo.OdOderhdVO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : OdOderhdController.java
 * @Description : OdOderhd Controller class
 * @Modification Information
 *
 * @author  
 * @since  
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/pp0701/ododerhd")
public class PP0701OdOderhdController extends TMMBaseController{

    @Resource(name = "PP0701odOderhdService")
    private PP0701OdOderhdService odOderhdService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * od_oderhd 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 OdOderhdDefaultVO
	 * @return "/odOderhd/OdOderhdList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectOdOderhdList(
			@PathVariable("strtDate") String strtDate,
			@PathVariable("lastDate") String lastDate,
			OdOderhdVO searchVO, 
			HttpServletRequest req,
			HttpServletResponse res,
			ModelMap model)
	        throws Exception {
		
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
		String plantCode = req.getParameter("plantCode");
		String proCode = req.getParameter("proCode");
		
		searchVO.setPlantCode(plantCode);
		searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
		searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
		searchVO.setProCode(proCode);
		
	    List<?> userInfoList = odOderhdService.selectOdOderhdList(searchVO);
	
		PageSet pageSet = new PageSet();
	    
	    int totCnt = odOderhdService.selectOdOderhdListTotCnt(searchVO);
		pageSet.setTotalRecordCount(totCnt);
		pageSet.setResult(userInfoList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/{strtDate}/{lastDate}/export",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectExOdOderhdList(
			@PathVariable("strtDate") String strtDate,
			@PathVariable("lastDate") String lastDate,
			OdOderhdVO searchVO, 
			HttpServletRequest req,
			HttpServletResponse res,
			HttpSession jsession,
			ModelMap model)
	        throws Exception {
		
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
		String plantCode = req.getParameter("plantCode");
		String proCode = req.getParameter("proCode");
		
		searchVO.setPlantCode(plantCode);
		searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
		searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
		searchVO.setProCode(proCode);
		
	    List<EgovMap> userInfoList = odOderhdService.selectOdOderhdList(searchVO);
	
	    String[] columns = PP.PP0701;
        String sheetName = PP.PP0701_NM;
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, userInfoList, columns, PP.PP0701_TYPE, sheetName);
    	
		return null;
    } 
}
