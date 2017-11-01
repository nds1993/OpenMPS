package nds.mpm.sales.SD0801.web;

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
import nds.mpm.sales.SD0801.service.MpClosingDateService;
import nds.mpm.sales.SD0801.vo.MpClosingDateDefaultVO;
import nds.mpm.sales.SD0801.vo.MpClosingDateVO;

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
 * @Class Name :  MpClosingDateController
 *
 * @author MPM TEAM
 * @since 2017. 10. 5.
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 마감일자
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017. 10. 5.	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
@RequestMapping("/mpm/{corpCode}/sd0801/mpclosingdate")
public class MpClosingDateController extends TMMBaseController{

    @Resource(name = "mpClosingDateService")
    private MpClosingDateService mpClosingDateService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_closing_date 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpClosingDateDefaultVO
	 * @return "/mpClosingDate/MpClosingDateList"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpClosingDateList(
    		@PathVariable("corpCode") String corpCode,
			@PathVariable("startDate") String startDate,
			@PathVariable("lastDate") String lastDate,
			HttpServletRequest req,
			HttpServletResponse res, 
			ModelMap model)
	        throws Exception {
		
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
	    MPUserSession sess = getSession(req);
		
	    int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
		int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
		
		MpClosingDateVO searchVO = new MpClosingDateVO();
		searchVO.setCorpCode(corpCode);
		
		searchVO.setPageIndex(pageIndex);
		searchVO.setPageSize(pageSize);
		
        List<?> mpClosingDateList = mpClosingDateService.selectMpClosingDateList(searchVO);
	
		PageSet pageSet = new PageSet();
		
		if(mpClosingDateList!= null)
			pageSet.setTotalRecordCount(mpClosingDateList.size());
		pageSet.setResult(mpClosingDateList);
		
		result.setExtraData(pageSet);
		
		return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping("/mpClosingDate/addMpClosingDateView.do")
    public String addMpClosingDateView(
            @ModelAttribute("searchVO") MpClosingDateDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpClosingDateVO", new MpClosingDateVO());
        return "/mpClosingDate/MpClosingDateRegister";
    }
    
    @RequestMapping(value="/{applyYyyy}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpClosingDate(
	    	@PathVariable("corpCode") String corpCode,
			@PathVariable("applyYyyy") String applyYyyy,
			@RequestBody List<EgovMap> mpClosingDateVOs,
			HttpServletRequest req)
	        throws Exception {
		
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		MPUserSession sess = getSession(req);
		int nRet = 0;
		
		List ilist = new ArrayList();
		//중복검색
		if(mpClosingDateVOs != null)
		{
			for(EgovMap vo : mpClosingDateVOs)
	    	{
				vo.put("corpCode",corpCode);
				vo.put("applyMonth",StringUtils.remove((String)vo.get("applyMonth"),"-"));
				if(sess != null)
				{
					vo.put("crUser", sess.getUser().getId());
					vo.put("mdUser", sess.getUser().getId());
				}
	    		ilist.add(vo);
	    	}
		}
		result = mpClosingDateService.insertMpClosingDate(ilist);
	    return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/mpClosingDate/updateMpClosingDateView.do")
    public String updateMpClosingDateView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("applyMonth") java.lang.String applyMonth ,
            @ModelAttribute("searchVO") MpClosingDateDefaultVO searchVO, Model model)
            throws Exception {
        MpClosingDateVO mpClosingDateVO = new MpClosingDateVO();
        mpClosingDateVO.setCorpCode(corpCode);
        mpClosingDateVO.setApplyMonth(applyMonth);
        // 변수명은 CoC 에 따라 mpClosingDateVO
        model.addAttribute(selectMpClosingDate(mpClosingDateVO, searchVO));
        return "/mpClosingDate/MpClosingDateRegister";
    }

    @RequestMapping("/mpClosingDate/selectMpClosingDate.do")
    public @ModelAttribute("mpClosingDateVO")
    MpClosingDateVO selectMpClosingDate(
            MpClosingDateVO mpClosingDateVO,
            @ModelAttribute("searchVO") MpClosingDateDefaultVO searchVO) throws Exception {
        return mpClosingDateService.selectMpClosingDate(mpClosingDateVO);
    }

    @RequestMapping("/mpClosingDate/updateMpClosingDate.do")
    public String updateMpClosingDate(
            MpClosingDateVO mpClosingDateVO,
            @ModelAttribute("searchVO") MpClosingDateDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpClosingDateService.updateMpClosingDate(mpClosingDateVO);
        status.setComplete();
        return "forward:/mpClosingDate/MpClosingDateList.do";
    }
    
    @RequestMapping("/mpClosingDate/deleteMpClosingDate.do")
    public String deleteMpClosingDate(
            MpClosingDateVO mpClosingDateVO,
            @ModelAttribute("searchVO") MpClosingDateDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpClosingDateService.deleteMpClosingDate(mpClosingDateVO);
        status.setComplete();
        return "forward:/mpClosingDate/MpClosingDateList.do";
    }

}
