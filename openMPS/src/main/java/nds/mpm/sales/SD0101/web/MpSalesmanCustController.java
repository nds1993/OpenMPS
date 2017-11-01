package nds.mpm.sales.SD0101.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.sales.SD0101.service.MpSalesmanCustService;
import nds.mpm.sales.SD0101.vo.MpCustInfoVO;
import nds.mpm.sales.SD0101.vo.MpSalesmanCustDefaultVO;
import nds.mpm.sales.SD0101.vo.MpSalesmanCustVO;

/**
 * @Class Name : MpSalesmanCustController.java
 * @Description : MpSalesmanCust Controller class
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
@RequestMapping("/mpm/{corpCode}/sd0103/mpsalesmancust")
public class MpSalesmanCustController extends TMMBaseController {
	
	private static final Logger _logger = LoggerFactory.getLogger(MpSalesmanCustController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "mpSalesmanCustService")
    private MpSalesmanCustService mpSalesmanCustService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_salesman_cust 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpSalesmanCustDefaultVO
	 * @return "/mpSalesmanCust/MpSalesmanCustList"
	 * @exception Exception
	 */
    @RequestMapping(value="")
    public ResponseEntity<ResultEx> selectMpSalesmanCustList(
    		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {

    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String salesman = req.getParameter("salesman");
    	String searchKeyword = req.getParameter("searchKeyword");
    	
    	MpSalesmanCustVO searchVO = new MpSalesmanCustVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setSalesman(salesman);
    	searchVO.setSearchKeyword(searchKeyword);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> mpSalesmanCustList = mpSalesmanCustService.selectMpSalesmanCustList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpSalesmanCustService.selectMpSalesmanCustListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpSalesmanCustList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping("/mpSalesmanCust/addMpSalesmanCustView.do")
    public String addMpSalesmanCustView(
            @ModelAttribute("searchVO") MpSalesmanCustDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpSalesmanCustVO", new MpSalesmanCustVO());
        return "/mpSalesmanCust/MpSalesmanCustRegister";
    }
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpSalesmanCust(
            @PathVariable("corpCode") String corpCode,
    		@RequestBody List<EgovMap> mpSalesmanCustVOs,
    		HttpServletRequest req)
            throws Exception {

    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	int nRet = 0;
    	
    	List ilist = new ArrayList();
    	//중복검색
    	if(mpSalesmanCustVOs != null)
    	{
    		for(EgovMap vo : mpSalesmanCustVOs)
        	{
    			vo.put("corpCode",corpCode);
    			if(sess != null)
    			{
    				vo.put("crUser", sess.getUser().getId());
    				vo.put("mdUser", sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
        result = mpSalesmanCustService.insertMpSalesmanCust(mpSalesmanCustVOs);
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/mpSalesmanCust/updateMpSalesmanCustView.do")
    public String updateMpSalesmanCustView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("salesman") java.lang.String salesman ,
            @RequestParam("salesmanCust") java.lang.String salesmanCust ,
            @ModelAttribute("searchVO") MpSalesmanCustDefaultVO searchVO, Model model)
            throws Exception {
        MpSalesmanCustVO mpSalesmanCustVO = new MpSalesmanCustVO();
        mpSalesmanCustVO.setCorpCode(corpCode);
        mpSalesmanCustVO.setSalesman(salesman);
        mpSalesmanCustVO.setSalesmanCust(salesmanCust);
        // 변수명은 CoC 에 따라 mpSalesmanCustVO
        model.addAttribute(selectMpSalesmanCust(mpSalesmanCustVO, searchVO));
        return "/mpSalesmanCust/MpSalesmanCustRegister";
    }

    @RequestMapping("/mpSalesmanCust/selectMpSalesmanCust.do")
    public @ModelAttribute("mpSalesmanCustVO")
    MpSalesmanCustVO selectMpSalesmanCust(
            MpSalesmanCustVO mpSalesmanCustVO,
            @ModelAttribute("searchVO") MpSalesmanCustDefaultVO searchVO) throws Exception {
        return mpSalesmanCustService.selectMpSalesmanCust(mpSalesmanCustVO);
    }

    @RequestMapping("/mpSalesmanCust/updateMpSalesmanCust.do")
    public String updateMpSalesmanCust(
            MpSalesmanCustVO mpSalesmanCustVO,
            @ModelAttribute("searchVO") MpSalesmanCustDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpSalesmanCustService.updateMpSalesmanCust(mpSalesmanCustVO);
        status.setComplete();
        return "forward:/mpSalesmanCust/MpSalesmanCustList.do";
    }
    
    @RequestMapping("/mpSalesmanCust/deleteMpSalesmanCust.do")
    public String deleteMpSalesmanCust(
    		EgovMap mpSalesmanCustVO,
            @ModelAttribute("searchVO") MpSalesmanCustDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpSalesmanCustService.deleteMpSalesmanCust(mpSalesmanCustVO);
        status.setComplete();
        return "forward:/mpSalesmanCust/MpSalesmanCustList.do";
    }

}
