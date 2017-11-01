package nds.mpm.sales.SD0101.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.sales.SD0101.service.MpCustHistoryService;
import nds.mpm.sales.SD0101.vo.MpCustHistVO;
import nds.mpm.sales.SD0101.vo.MpCustHistoryDefaultVO;
import nds.mpm.sales.SD0101.vo.MpCustHistoryVO;

/**
 * @Class Name : MpCustHistoryController.java
 * @Description : MpCustHistory Controller class
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
@RequestMapping("/mpm/{corpCode}/sd0101/mpcusthistory")
public class MpCustHistoryController extends TMMBaseController{

    @Resource(name = "mpCustHistoryService")
    private MpCustHistoryService mpCustHistoryService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_cust_history 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpCustHistoryDefaultVO
	 * @return "/mpCustHistory/MpCustHistoryList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{custCode}")
    public ResponseEntity<ResultEx> selectMpCustHistoryList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("custCode") String custCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpCustHistoryVO searchVO = new MpCustHistoryVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setCustCode(custCode);
    	
        List<?> mpCustHistoryList = mpCustHistoryService.selectMpCustHistoryList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(mpCustHistoryList != null)
    		pageSet.setTotalRecordCount(mpCustHistoryList.size());
    	pageSet.setResult(mpCustHistoryList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping("/mpCustHistory/addMpCustHistoryView.do")
    public String addMpCustHistoryView(
            @ModelAttribute("searchVO") MpCustHistoryDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpCustHistoryVO", new MpCustHistoryVO());
        return "/mpCustHistory/MpCustHistoryRegister";
    }
    
    @RequestMapping("/mpCustHistory/addMpCustHistory.do")
    public String addMpCustHistory(
            MpCustHistoryVO mpCustHistoryVO,
            @ModelAttribute("searchVO") MpCustHistoryDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpCustHistoryService.insertMpCustHistory(mpCustHistoryVO);
        status.setComplete();
        return "forward:/mpCustHistory/MpCustHistoryList.do";
    }
    
    @RequestMapping("/mpCustHistory/updateMpCustHistoryView.do")
    public String updateMpCustHistoryView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("custCode") java.lang.String custCode ,
            @RequestParam("crDate") java.sql.Timestamp crDate ,
            @ModelAttribute("searchVO") MpCustHistoryDefaultVO searchVO, Model model)
            throws Exception {
        MpCustHistoryVO mpCustHistoryVO = new MpCustHistoryVO();
        mpCustHistoryVO.setCorpCode(corpCode);
        mpCustHistoryVO.setCustCode(custCode);
        mpCustHistoryVO.setCrDate(crDate);
        // 변수명은 CoC 에 따라 mpCustHistoryVO
        model.addAttribute(selectMpCustHistory(mpCustHistoryVO, searchVO));
        return "/mpCustHistory/MpCustHistoryRegister";
    }

    @RequestMapping("/mpCustHistory/selectMpCustHistory.do")
    public @ModelAttribute("mpCustHistoryVO")
    MpCustHistoryVO selectMpCustHistory(
            MpCustHistoryVO mpCustHistoryVO,
            @ModelAttribute("searchVO") MpCustHistoryDefaultVO searchVO) throws Exception {
        return mpCustHistoryService.selectMpCustHistory(mpCustHistoryVO);
    }

    @RequestMapping("/mpCustHistory/updateMpCustHistory.do")
    public String updateMpCustHistory(
            MpCustHistoryVO mpCustHistoryVO,
            @ModelAttribute("searchVO") MpCustHistoryDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpCustHistoryService.updateMpCustHistory(mpCustHistoryVO);
        status.setComplete();
        return "forward:/mpCustHistory/MpCustHistoryList.do";
    }
    
    @RequestMapping("/mpCustHistory/deleteMpCustHistory.do")
    public String deleteMpCustHistory(
            MpCustHistoryVO mpCustHistoryVO,
            @ModelAttribute("searchVO") MpCustHistoryDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpCustHistoryService.deleteMpCustHistory(mpCustHistoryVO);
        status.setComplete();
        return "forward:/mpCustHistory/MpCustHistoryList.do";
    }

}
