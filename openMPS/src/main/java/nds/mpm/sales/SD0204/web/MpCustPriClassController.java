package nds.mpm.sales.SD0204.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.sales.SD0204.service.MpCustPriClassService;
import nds.mpm.sales.SD0204.vo.MpCustPriClassDefaultVO;
import nds.mpm.sales.SD0204.vo.MpCustPriClassVO;

import org.apache.commons.lang.StringUtils;
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
 * @Class Name :  MpCustPriClassController
 *
 * @author MPM TEAM
 * @since 2017. 9. 18.
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 거래처단가등급( SD0204 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017. 9. 18.	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/sd0204/mppriclass")
public class MpCustPriClassController extends TMMBaseController{

    @Resource(name = "mpCustPriClassService")
    private MpCustPriClassService mpCustPriClassService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_cust_pri_class 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpCustPriClassDefaultVO
	 * @return "/mpCustPriClass/MpCustPriClassList"
	 * @exception Exception
	 */
    @RequestMapping(value="", method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpSalesmanCustList(
	    	@PathVariable("corpCode") String corpCode,
			HttpServletRequest req,
			HttpServletResponse res, 
			ModelMap model)
	        throws Exception {
		
	    ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
	    MPUserSession sess = getSession(req);
		
		String salesman = req.getParameter("salesman");
		String partCode = req.getParameter("partCode");
		
		MpCustPriClassVO searchVO = new MpCustPriClassVO();
		searchVO.setCorpCode(corpCode);
		
		searchVO.setPartCode(partCode);
		searchVO.setSalesman(salesman);
		
	    List<?> mpSalePriceList = mpCustPriClassService.selectMpSalesmanCustList(searchVO);
	
		PageSet pageSet = new PageSet();
	    
	    int totCnt = mpCustPriClassService.selectMpSalesmanCustListTotCnt(searchVO);
		pageSet.setTotalRecordCount(totCnt);
		pageSet.setResult(mpSalePriceList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/{custCode}", method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpCustPriClassList(
	    	@PathVariable("corpCode") String corpCode,
	    	@PathVariable("custCode") String custCode,
			HttpServletRequest req,
			HttpServletResponse res, 
			ModelMap model)
	        throws Exception {
		
	    ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
	    MPUserSession sess = getSession(req);
		
		MpCustPriClassVO searchVO = new MpCustPriClassVO();
		searchVO.setCorpCode(corpCode);
		
		searchVO.setCustCode(custCode);
		searchVO.setSalesman(sess.getUser().getId());
		
	    List<?> mpSalePriceList = mpCustPriClassService.selectMpCustPriClassList(searchVO);
	
		PageSet pageSet = new PageSet();
		if(mpSalePriceList!=null)
			pageSet.setTotalRecordCount(mpSalePriceList.size());
		pageSet.setResult(mpSalePriceList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/{custCode}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpSalePrice(
            @PathVariable("corpCode") String corpCode,
            @PathVariable("custCode") String custCode,
            @RequestBody List<EgovMap> mpSalePriceVO,
            HttpServletRequest req)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	int nRet = 0;
    	
    	//중복검색
    	if(mpSalePriceVO != null)
    	{
    		for(EgovMap vo : mpSalePriceVO)
        	{
    			vo.put("corpCode",corpCode);
    			if(sess != null)
    			{
    				vo.put("crUser", sess.getUser().getId());
    				vo.put("mdUser", sess.getUser().getId());
    			}
    			vo.put("corpCode", corpCode);
    			vo.put("custCode", custCode);
        	}
    	}
        
        result = mpCustPriClassService.insertMpCustPriClass(mpSalePriceVO);
        return _filter.makeCORSEntity( result );
    }
    
    /**
    @RequestMapping("/mpCustPriClass/addMpCustPriClassView.do")
    public String addMpCustPriClassView(
            @ModelAttribute("searchVO") MpCustPriClassDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpCustPriClassVO", new MpCustPriClassVO());
        return "/mpCustPriClass/MpCustPriClassRegister";
    }
    
    @RequestMapping("/mpCustPriClass/addMpCustPriClass.do")
    public String addMpCustPriClass(
            MpCustPriClassVO mpCustPriClassVO,
            @ModelAttribute("searchVO") MpCustPriClassDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpCustPriClassService.insertMpCustPriClass(mpCustPriClassVO);
        status.setComplete();
        return "forward:/mpCustPriClass/MpCustPriClassList.do";
    }
    
    @RequestMapping("/mpCustPriClass/updateMpCustPriClassView.do")
    public String updateMpCustPriClassView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("custCode") java.lang.String custCode ,
            @RequestParam("proCode") java.lang.String proCode ,
            @ModelAttribute("searchVO") MpCustPriClassDefaultVO searchVO, Model model)
            throws Exception {
        MpCustPriClassVO mpCustPriClassVO = new MpCustPriClassVO();
        mpCustPriClassVO.setCorpCode(corpCode);
        mpCustPriClassVO.setCustCode(custCode);
        mpCustPriClassVO.setProCode(proCode);
        // 변수명은 CoC 에 따라 mpCustPriClassVO
        model.addAttribute(selectMpCustPriClass(mpCustPriClassVO, searchVO));
        return "/mpCustPriClass/MpCustPriClassRegister";
    }

    @RequestMapping("/mpCustPriClass/selectMpCustPriClass.do")
    public @ModelAttribute("mpCustPriClassVO")
    MpCustPriClassVO selectMpCustPriClass(
            MpCustPriClassVO mpCustPriClassVO,
            @ModelAttribute("searchVO") MpCustPriClassDefaultVO searchVO) throws Exception {
        return mpCustPriClassService.selectMpCustPriClass(mpCustPriClassVO);
    }

    @RequestMapping("/mpCustPriClass/updateMpCustPriClass.do")
    public String updateMpCustPriClass(
            MpCustPriClassVO mpCustPriClassVO,
            @ModelAttribute("searchVO") MpCustPriClassDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpCustPriClassService.updateMpCustPriClass(mpCustPriClassVO);
        status.setComplete();
        return "forward:/mpCustPriClass/MpCustPriClassList.do";
    }
    
    @RequestMapping("/mpCustPriClass/deleteMpCustPriClass.do")
    public String deleteMpCustPriClass(
            MpCustPriClassVO mpCustPriClassVO,
            @ModelAttribute("searchVO") MpCustPriClassDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpCustPriClassService.deleteMpCustPriClass(mpCustPriClassVO);
        status.setComplete();
        return "forward:/mpCustPriClass/MpCustPriClassList.do";
    }
     */
}
