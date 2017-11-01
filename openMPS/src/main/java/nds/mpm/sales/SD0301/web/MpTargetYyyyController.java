package nds.mpm.sales.SD0301.web;

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
import nds.mpm.sales.SD0301.service.MpTargetYyyyService;
import nds.mpm.sales.SD0301.vo.MpTargetYyyyDefaultVO;
import nds.mpm.sales.SD0301.vo.MpTargetYyyyVO;
import nds.mpm.sales.SD0503.vo.MpLastUnpaymentVO;

/**
 * @Class Name : MpTargetYyyyController.java
 * @Description : MpTargetYyyy Controller class
 * @Modification Information
 *
 * @author 333333
 * @since 333333
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/sd0301/mptargetyyyy")
public class MpTargetYyyyController extends TMMBaseController {

    @Resource(name = "mpTargetYyyyService")
    private MpTargetYyyyService mpTargetYyyyService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_target_yyyy 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpTargetYyyyDefaultVO
	 * @return "/mpTargetYyyy/MpTargetYyyyList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{target_yyyy}/{partCode}/{salesMan}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpTargetYyyyList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("target_yyyy") String target_yyyy,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
     	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
     	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
     	
    	String partCode = req.getParameter("partCode");
    	String salesMan = req.getParameter("salesMan");
    	
    	MpTargetYyyyVO searchVO = new MpTargetYyyyVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setTargetYyyy(target_yyyy);
    	searchVO.setPartCode(partCode);
    	searchVO.setSalesman(salesMan);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
		
        List<?> mpTargetYyyyList = mpTargetYyyyService.selectMpTargetYyyyList(searchVO);
        
        PageSet pageSet = new PageSet();
        
        int totCnt = 0;
        /*int totCnt = mpTargetYyyyService.selectMpTargetYyyyListTotCnt(searchVO);*/
        if( mpTargetYyyyList != null )
        {
        	totCnt = mpTargetYyyyList.size();
        }

    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpTargetYyyyList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );	
    } 
    
    /*@RequestMapping("/mpTargetYyyy/addMpTargetYyyyView.do")
    public String addMpTargetYyyyView(
            @ModelAttribute("searchVO") MpTargetYyyyDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpTargetYyyyVO", new MpTargetYyyyVO());
        return "/mpTargetYyyy/MpTargetYyyyRegister";
    }
    
    @RequestMapping("/mpTargetYyyy/addMpTargetYyyy.do")
    public String addMpTargetYyyy(
            MpTargetYyyyVO mpTargetYyyyVO,
            @ModelAttribute("searchVO") MpTargetYyyyDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpTargetYyyyService.insertMpTargetYyyy(mpTargetYyyyVO);
        status.setComplete();
        return "forward:/mpTargetYyyy/MpTargetYyyyList.do";
    }
    
   @RequestMapping("/mpTargetYyyy/updateMpTargetYyyyView.do")
    public String updateMpTargetYyyyView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("targetYyyy") java.lang.String targetYyyy ,
            @RequestParam("headCode") java.lang.String headCode ,
            @RequestParam("partCode") java.lang.String partCode ,
            @RequestParam("salesman") java.lang.String salesman ,
            @RequestParam("saleGroup1") java.lang.String saleGroup1 ,
            @RequestParam("saleGroup2") java.lang.String saleGroup2 ,
            @ModelAttribute("searchVO") MpTargetYyyyDefaultVO searchVO, Model model)
            throws Exception {
        MpTargetYyyyVO mpTargetYyyyVO = new MpTargetYyyyVO();
        mpTargetYyyyVO.setCorpCode(corpCode);
        mpTargetYyyyVO.setTargetYyyy(targetYyyy);
        mpTargetYyyyVO.setHeadCode(headCode);
        mpTargetYyyyVO.setPartCode(partCode);
        mpTargetYyyyVO.setSalesman(salesman);
        mpTargetYyyyVO.setSaleGroup1(saleGroup1);
        mpTargetYyyyVO.setSaleGroup2(saleGroup2);
        // 변수명은 CoC 에 따라 mpTargetYyyyVO
        model.addAttribute(selectMpTargetYyyy(mpTargetYyyyVO, searchVO));
        return "/mpTargetYyyy/MpTargetYyyyRegister";
    }

    @RequestMapping("/mpTargetYyyy/selectMpTargetYyyy.do")
    public @ModelAttribute("mpTargetYyyyVO")
    MpTargetYyyyVO selectMpTargetYyyy(
            MpTargetYyyyVO mpTargetYyyyVO,
            @ModelAttribute("searchVO") MpTargetYyyyDefaultVO searchVO) throws Exception {
        return mpTargetYyyyService.selectMpTargetYyyy(mpTargetYyyyVO);
    }

    @RequestMapping("/mpTargetYyyy/updateMpTargetYyyy.do")
    public String updateMpTargetYyyy(
            MpTargetYyyyVO mpTargetYyyyVO,
            @ModelAttribute("searchVO") MpTargetYyyyDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpTargetYyyyService.updateMpTargetYyyy(mpTargetYyyyVO);
        status.setComplete();
        return "forward:/mpTargetYyyy/MpTargetYyyyList.do";
    }
    
    @RequestMapping("/mpTargetYyyy/deleteMpTargetYyyy.do")
    public String deleteMpTargetYyyy(
            MpTargetYyyyVO mpTargetYyyyVO,
            @ModelAttribute("searchVO") MpTargetYyyyDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpTargetYyyyService.deleteMpTargetYyyy(mpTargetYyyyVO);
        status.setComplete();
        return "forward:/mpTargetYyyy/MpTargetYyyyList.do";
    }
*/
}
