package nds.mpm.sales.SD0101.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.sales.SD0101.service.MpCreditLimitService;
import nds.mpm.sales.SD0101.vo.MpCreditLimitDefaultVO;
import nds.mpm.sales.SD0101.vo.MpCreditLimitVO;

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
 * @Class Name : MpCreditLimitController.java
 * @Description : MpCreditLimit Controller class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/sd0101/mpcreditlimit")
public class MpCreditLimitController extends TMMBaseController{
	
	private static final Logger _logger = LoggerFactory.getLogger(MpCreditLimitController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "mpCreditLimitService")
    private MpCreditLimitService mpCreditLimitService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_credit_limit 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpCreditLimitDefaultVO
	 * @return "/mpCreditLimit/MpCreditLimitList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{custCode}")
    public ResponseEntity<ResultEx> selectMpCreditLimitList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("custCode") String custCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpCreditLimitVO searchVO = new MpCreditLimitVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setCustCode(custCode);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> mpCreditLimitList = mpCreditLimitService.selectMpCreditLimitList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpCreditLimitService.selectMpCreditLimitListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpCreditLimitList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping("/mpCreditLimit/addMpCreditLimitView.do")
    public String addMpCreditLimitView(
            @ModelAttribute("searchVO") MpCreditLimitDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpCreditLimitVO", new MpCreditLimitVO());
        return "/mpCreditLimit/MpCreditLimitRegister";
    }
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpCreditLimit(
            @PathVariable("corpCode") String corpCode,
    		@RequestBody EgovMap mpCreditLimitVO,
    		HttpServletRequest req
            )
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	result = mpCreditLimitService.insertMpCreditLimit(mpCreditLimitVO);
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{custCode}/appro/{approYn}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> updateApproMpCreditLimit(
            @PathVariable("corpCode") String corpCode,
            @PathVariable("approYn") String approYn,
    		@RequestBody List<EgovMap> mpCreditLimitVOs,
    		HttpServletRequest req
            )
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	int nRet = 0;
    	
    	String approYnCode=null;
    	
    	if("yappro".equals(approYn))
    		approYnCode = "Y";
    	else
    		approYnCode = "N";
    	
    	List ilist = new ArrayList();
    	//중복검색
    	if(mpCreditLimitVOs != null)
    	{
    		for(EgovMap vo : mpCreditLimitVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("approYn",approYnCode);
    			if(sess != null)
    			{
    				vo.put("crUser", sess.getUser().getId());
    				vo.put("mdUser", sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	result = mpCreditLimitService.updateApproMpCreditLimit(ilist);
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/mpCreditLimit/updateMpCreditLimitView.do")
    public String updateMpCreditLimitView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("custCode") java.lang.String custCode ,
            @ModelAttribute("searchVO") MpCreditLimitDefaultVO searchVO, Model model)
            throws Exception {
        MpCreditLimitVO mpCreditLimitVO = new MpCreditLimitVO();
        mpCreditLimitVO.setCorpCode(corpCode);
        mpCreditLimitVO.setCustCode(custCode);
        // 변수명은 CoC 에 따라 mpCreditLimitVO
        model.addAttribute(selectMpCreditLimit(mpCreditLimitVO, searchVO));
        return "/mpCreditLimit/MpCreditLimitRegister";
    }

    @RequestMapping("/mpCreditLimit/selectMpCreditLimit.do")
    public @ModelAttribute("mpCreditLimitVO")
    MpCreditLimitVO selectMpCreditLimit(
            MpCreditLimitVO mpCreditLimitVO,
            @ModelAttribute("searchVO") MpCreditLimitDefaultVO searchVO) throws Exception {
        return mpCreditLimitService.selectMpCreditLimit(mpCreditLimitVO);
    }

    @RequestMapping("/mpCreditLimit/updateMpCreditLimit.do")
    public String updateMpCreditLimit(
            MpCreditLimitVO mpCreditLimitVO,
            @ModelAttribute("searchVO") MpCreditLimitDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpCreditLimitService.updateMpCreditLimit(mpCreditLimitVO);
        status.setComplete();
        return "forward:/mpCreditLimit/MpCreditLimitList.do";
    }
    
    @RequestMapping("/mpCreditLimit/deleteMpCreditLimit.do")
    public String deleteMpCreditLimit(
            MpCreditLimitVO mpCreditLimitVO,
            @ModelAttribute("searchVO") MpCreditLimitDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpCreditLimitService.deleteMpCreditLimit(mpCreditLimitVO);
        status.setComplete();
        return "forward:/mpCreditLimit/MpCreditLimitList.do";
    }

}
