package nds.mpm.sales.SD0101.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.sales.SD0101.service.MpCustHistService;
import nds.mpm.sales.SD0101.vo.MpCustHistDefaultVO;
import nds.mpm.sales.SD0101.vo.MpCustHistVO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpCustHistController.java
 * @Description : MpCustHist Controller class
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
@RequestMapping("/mpm/{corpCode}/sd0101/mpcusthist")
public class MpCustHistController {
	
	private static final Logger _logger = LoggerFactory.getLogger(MpCustHistController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "mpCustHistService")
    private MpCustHistService mpCustHistService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_cust_hist 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpCustHistDefaultVO
	 * @return "/mpCustHist/MpCustHistList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{custCode}")
    public ResponseEntity<ResultEx> selectMpCustHistList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("custCode") String custCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpCustHistVO searchVO = new MpCustHistVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setCustCode(custCode);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> mpCustHistList = mpCustHistService.selectMpCustHistList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpCustHistService.selectMpCustHistListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpCustHistList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping("/mpCustHist/addMpCustHistView.do")
    public String addMpCustHistView(
            @ModelAttribute("searchVO") MpCustHistDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpCustHistVO", new MpCustHistVO());
        return "/mpCustHist/MpCustHistRegister";
    }
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpCustHist(
    		@PathVariable("corpCode") String corpCode,
    		@RequestBody EgovMap mpCustHistVO,
    		HttpServletRequest req
            )
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	result = mpCustHistService.insertMpCustHist(mpCustHistVO);
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/mpCustHist/updateMpCustHistView.do")
    public String updateMpCustHistView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("custCode") java.lang.String custCode ,
            @RequestParam("seqNo") java.lang.String seqNo ,
            @ModelAttribute("searchVO") MpCustHistDefaultVO searchVO, Model model)
            throws Exception {
        MpCustHistVO mpCustHistVO = new MpCustHistVO();
        mpCustHistVO.setCorpCode(corpCode);
        mpCustHistVO.setCustCode(custCode);
        mpCustHistVO.setSeqNo(seqNo);
        // 변수명은 CoC 에 따라 mpCustHistVO
        model.addAttribute(selectMpCustHist(mpCustHistVO, searchVO));
        return "/mpCustHist/MpCustHistRegister";
    }

    @RequestMapping("/mpCustHist/selectMpCustHist.do")
    public @ModelAttribute("mpCustHistVO")
    MpCustHistVO selectMpCustHist(
            MpCustHistVO mpCustHistVO,
            @ModelAttribute("searchVO") MpCustHistDefaultVO searchVO) throws Exception {
        return mpCustHistService.selectMpCustHist(mpCustHistVO);
    }

    @RequestMapping("/mpCustHist/updateMpCustHist.do")
    public String updateMpCustHist(
            MpCustHistVO mpCustHistVO,
            @ModelAttribute("searchVO") MpCustHistDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpCustHistService.updateMpCustHist(mpCustHistVO);
        status.setComplete();
        return "forward:/mpCustHist/MpCustHistList.do";
    }
    
    @RequestMapping(value="",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteMpCustHist(
            MpCustHistVO mpCustHistVO
            )
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        mpCustHistService.deleteMpCustHist(mpCustHistVO);
        return _filter.makeCORSEntity( result );
    }

}
