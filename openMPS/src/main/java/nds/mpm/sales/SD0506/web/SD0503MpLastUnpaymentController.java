package nds.mpm.sales.SD0506.web;

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
import nds.mpm.sales.SD0503.service.SD0503MpLastUnpaymentService;
import nds.mpm.sales.SD0501.vo.MpLastUnpaymentDefaultVO;
import nds.mpm.sales.SD0503.vo.MpLastUnpaymentVO;

/**
 * @Class Name :  SD0503MpLastUnpaymentController
 *
 * @author MPM TEAM
 * @since 2017.09.11
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 잔액조회서( SD0503 )
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.06.07	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/sd0506/mplastunpayment")
public class SD0503MpLastUnpaymentController extends TMMBaseController {

    @Resource(name = "SD0503mpLastUnpaymentService")
    private SD0503MpLastUnpaymentService mpLastUnpaymentService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_last_unpayment 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpLastUnpaymentDefaultVO
	 * @return "/mpLastUnpayment/MpLastUnpaymentList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{saleDate}")
    public ResponseEntity<ResultEx> selectMpLastUnpaymentList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("saleDate") String receDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
     	
    	String searchCondition = req.getParameter("searchCondition");
        String searchCondition2 = req.getParameter("searchCondition2");
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
     	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
     	
    	String custCode = req.getParameter("custCode");
    	String headCode = req.getParameter("headCode");
    	String teamCode = req.getParameter("teamCode");
		
    	MpLastUnpaymentVO searchVO = new MpLastUnpaymentVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setCustCode(custCode);
    	searchVO.setHeadCode(headCode);
    	searchVO.setTeamCode(teamCode);
    	searchVO.setReceDate(StringUtils.remove(receDate,"-"));
    	
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setSearchCondition2(searchCondition2);
    	
     	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
     	
        List<?> mpReceInfoList = mpLastUnpaymentService.selectMpLastUnpaymentList(searchVO);
        
        PageSet pageSet = new PageSet();
        
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	
    	if(mpReceInfoList != null)
    		pageSet.setTotalRecordCount(mpReceInfoList.size());
    	pageSet.setResult(mpReceInfoList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );	
    } 
    
   /* @RequestMapping("/mpLastUnpayment/addMpLastUnpaymentView.do")
    public String addMpLastUnpaymentView(
            @ModelAttribute("searchVO") MpLastUnpaymentDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpLastUnpaymentVO", new MpLastUnpaymentVO());
        return "/mpLastUnpayment/MpLastUnpaymentRegister";
    }
    
    @RequestMapping("/mpLastUnpayment/addMpLastUnpayment.do")
    public String addMpLastUnpayment(
            MpLastUnpaymentVO mpLastUnpaymentVO,
            @ModelAttribute("searchVO") MpLastUnpaymentDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpLastUnpaymentService.insertMpLastUnpayment(mpLastUnpaymentVO);
        status.setComplete();
        return "forward:/mpLastUnpayment/MpLastUnpaymentList.do";
    }
    
    @RequestMapping("/mpLastUnpayment/updateMpLastUnpaymentView.do")
    public String updateMpLastUnpaymentView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("unpayYymm") java.lang.String unpayYymm ,
            @RequestParam("custCode") java.lang.String custCode ,
            @ModelAttribute("searchVO") MpLastUnpaymentDefaultVO searchVO, Model model)
            throws Exception {
        MpLastUnpaymentVO mpLastUnpaymentVO = new MpLastUnpaymentVO();
        mpLastUnpaymentVO.setCorpCode(corpCode);
        mpLastUnpaymentVO.setUnpayYymm(unpayYymm);
        mpLastUnpaymentVO.setCustCode(custCode);
        // 변수명은 CoC 에 따라 mpLastUnpaymentVO
        model.addAttribute(selectMpLastUnpayment(mpLastUnpaymentVO, searchVO));
        return "/mpLastUnpayment/MpLastUnpaymentRegister";
    }

    @RequestMapping("/mpLastUnpayment/selectMpLastUnpayment.do")
    public @ModelAttribute("mpLastUnpaymentVO")
    MpLastUnpaymentVO selectMpLastUnpayment(
            MpLastUnpaymentVO mpLastUnpaymentVO,
            @ModelAttribute("searchVO") MpLastUnpaymentDefaultVO searchVO) throws Exception {
        return mpLastUnpaymentService.selectMpLastUnpayment(mpLastUnpaymentVO);
    }

    @RequestMapping("/mpLastUnpayment/updateMpLastUnpayment.do")
    public String updateMpLastUnpayment(
            MpLastUnpaymentVO mpLastUnpaymentVO,
            @ModelAttribute("searchVO") MpLastUnpaymentDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpLastUnpaymentService.updateMpLastUnpayment(mpLastUnpaymentVO);
        status.setComplete();
        return "forward:/mpLastUnpayment/MpLastUnpaymentList.do";
    }
    
    @RequestMapping("/mpLastUnpayment/deleteMpLastUnpayment.do")
    public String deleteMpLastUnpayment(
            MpLastUnpaymentVO mpLastUnpaymentVO,
            @ModelAttribute("searchVO") MpLastUnpaymentDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpLastUnpaymentService.deleteMpLastUnpayment(mpLastUnpaymentVO);
        status.setComplete();
        return "forward:/mpLastUnpayment/MpLastUnpaymentList.do";
    }
*/
}
