package nds.mpm.prod.PP0605.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.prod.PP0605.service.PP0605MpBarProMService;
import nds.mpm.prod.PP0504.vo.MpBarProMDefaultVO;
import nds.mpm.prod.PP0504.vo.MpBarProMVO;


/**
 * @Class Name : MpBarProMController.java
 * @Description : MpBarProM Controller class
 * @Modification Information
 *
 * @author 44444
 * @since 44444
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/pp0605/mpbarprom")
public class PP0605MpBarProMController extends TMMBaseController {

    @Resource(name = "PP0605mpBarProMService")
    private PP0605MpBarProMService mpBarProMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_bar_pro_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpBarProMDefaultVO
	 * @return "/mpBarProM/MpBarProMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/head/{strtDate}/{searchKeyword}/{plantCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpBarProMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("searchKeyword") String searchKeyword,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );

        String plantCode = req.getParameter("plantCode");

        String searchCondition = req.getParameter("searchCondition");
        String searchCondition2 = req.getParameter("searchCondition2");
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpBarProMVO searchVO = new MpBarProMVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setPlantCode(plantCode);
    	searchVO.setStrtDate(strtDate);
    	searchVO.setSearchKeyword(searchKeyword);
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setSearchCondition2(searchCondition2);
    	
    	if("0".equals(plantCode)){
       		searchVO.setPlantCode("");
       	}else{
       		searchVO.setPlantCode(plantCode);
       	}
    	
        List<?> mpBarProMList = mpBarProMService.selectMpBarProMListHead(searchVO);

    	PageSet pageSet = new PageSet();
        
        //int totCnt = mpBarProMService.selectMpBarProMListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	//pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpBarProMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/detail/{strtDate}/{searchKeyword}/{plantCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectDetailMpBarProMList(
    		@PathVariable("corpCode") String corpCode,    		
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("searchKeyword") String searchKeyword,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
		String plantCode = req.getParameter("plantCode");
		
        String searchCondition = req.getParameter("searchCondition");
        String searchCondition2 = req.getParameter("searchCondition2");
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpBarProMVO searchVO = new MpBarProMVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setPlantCode(plantCode);
    	searchVO.setStrtDate(strtDate);
    	searchVO.setSearchKeyword(searchKeyword);
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setSearchCondition2(searchCondition2);
    	
    	if("0".equals(plantCode)){
       		searchVO.setPlantCode("");
       	}else{
       		searchVO.setPlantCode(plantCode);
       	}
    	
        List<?> mpBarProMList = mpBarProMService.selectMpBarProMList(searchVO);

    	PageSet pageSet = new PageSet();
        
        //int totCnt = mpBarProMService.selectMpBarProMListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	//pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpBarProMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
}
