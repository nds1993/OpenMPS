package nds.mpm.buy.PO0301.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.buy.PO0301.service.PO0301MpPigxxdService;
import nds.mpm.buy.PO0301.vo.MpPigxxdDefaultVO;
import nds.mpm.buy.PO0301.vo.MpPigxxdVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;

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
 * @Class Name : MpPigxxdController.java
 * @Description : MpPigxxd Controller class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/po0301/mppigxxd")
public class PO0301MpPigxxdController extends TMMBaseController{

    @Resource(name = "PO0301MpPigxxdService")
    private PO0301MpPigxxdService mpPigxxdService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_pigxxd 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPigxxdDefaultVO
	 * @return "/mpPigxxd/MpPigxxdList"
	 * @exception Exception
	 */
    @RequestMapping(value="/tab3/{dochDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPigxxdList(
    	@PathVariable("corpCode") String corpCode,
		@PathVariable("dochDate") String dochDate,
		HttpServletRequest req,
		HttpServletResponse res, 
		ModelMap model)
        throws Exception {
	
	    ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
	    int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
		int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
	
		String custCode = req.getParameter("custCode");
		String brandCode = req.getParameter("brandCode");
		
		MpPigxxdVO searchVO = new MpPigxxdVO();
		searchVO.setCorpCode(corpCode);
		
		/*searchVO.setBrandCode(brandCode);*/
		searchVO.setCustCode(custCode);
		searchVO.setDochDate(dochDate);
		searchVO.setBrandCode(brandCode);
		
		searchVO.setPageIndex(pageIndex);
		searchVO.setPageSize(pageSize);
		
        List<?> mpPigxxdList = mpPigxxdService.selectMpPigxxdList(searchVO);
		PageSet pageSet = new PageSet();
	    
        int totCnt = mpPigxxdService.selectMpPigxxdListTotCnt(searchVO);
	    pageSet.setPageIndex(pageIndex);
		pageSet.setPageSize(pageSize);
		
		pageSet.setTotalRecordCount(totCnt);
		pageSet.setResult(mpPigxxdList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping("/mpPigxxd/addMpPigxxdView.do")
    public String addMpPigxxdView(
            @ModelAttribute("searchVO") MpPigxxdDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpPigxxdVO", new MpPigxxdVO());
        return "/mpPigxxd/MpPigxxdRegister";
    }
    
    /**
     * TAB3  저장
	 * @param searchVO - 조회할 정보가 담긴 PO0301MpPigoxmDefaultVO
	 * @exception Exception
	 */
    @RequestMapping(value="/tab3/{dochDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addPO0301Tab2MpPigoxm(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("dochDate") String dochDate,
            @RequestBody List<EgovMap> PO0301MpPigoxmVOs,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
            	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(PO0301MpPigoxmVOs != null)
    	{
    		for(EgovMap vo : PO0301MpPigoxmVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("dochDate",StringUtils.remove(dochDate,"-"));
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	result.setExtraData(mpPigxxdService.insertMpPigxxd(ilist));
    	
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/mpPigxxd/updateMpPigxxdView.do")
    public String updateMpPigxxdView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("dochDate") java.lang.String dochDate ,
            @RequestParam("procDate") java.lang.String procDate ,
            @RequestParam("custCode") java.lang.String custCode ,
            @RequestParam("partGubn") java.lang.String partGubn ,
            @ModelAttribute("searchVO") MpPigxxdDefaultVO searchVO, Model model)
            throws Exception {
        MpPigxxdVO mpPigxxdVO = new MpPigxxdVO();
        mpPigxxdVO.setCorpCode(corpCode);
        mpPigxxdVO.setDochDate(dochDate);
        mpPigxxdVO.setProcDate(procDate);
        mpPigxxdVO.setCustCode(custCode);
        mpPigxxdVO.setPartGubn(partGubn);
        // 변수명은 CoC 에 따라 mpPigxxdVO
        model.addAttribute(selectMpPigxxd(mpPigxxdVO, searchVO));
        return "/mpPigxxd/MpPigxxdRegister";
    }

    @RequestMapping("/mpPigxxd/selectMpPigxxd.do")
    public @ModelAttribute("mpPigxxdVO")
    MpPigxxdVO selectMpPigxxd(
            MpPigxxdVO mpPigxxdVO,
            @ModelAttribute("searchVO") MpPigxxdDefaultVO searchVO) throws Exception {
        return mpPigxxdService.selectMpPigxxd(mpPigxxdVO);
    }

    @RequestMapping("/mpPigxxd/updateMpPigxxd.do")
    public String updateMpPigxxd(
            MpPigxxdVO mpPigxxdVO,
            @ModelAttribute("searchVO") MpPigxxdDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPigxxdService.updateMpPigxxd(mpPigxxdVO);
        status.setComplete();
        return "forward:/mpPigxxd/MpPigxxdList.do";
    }
    
    @RequestMapping("/mpPigxxd/deleteMpPigxxd.do")
    public String deleteMpPigxxd(
            MpPigxxdVO mpPigxxdVO,
            @ModelAttribute("searchVO") MpPigxxdDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPigxxdService.deleteMpPigxxd(mpPigxxdVO);
        status.setComplete();
        return "forward:/mpPigxxd/MpPigxxdList.do";
    }

}
