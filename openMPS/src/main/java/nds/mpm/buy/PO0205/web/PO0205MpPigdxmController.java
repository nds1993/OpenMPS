package nds.mpm.buy.PO0205.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.mpm.buy.PO0102.vo.MpPigdxmVO;
import nds.mpm.buy.PO0205.service.PO0205MpPigdxmService;
import nds.mpm.common.util.ExcelUtil;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.excel.PO;
import nds.mpm.login.vo.MPUserSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  mpPigdxmController
 *
 * @author MPM TEAM
 * @since 2017.09.01
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 더느림지급액조회( PO0205 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.09.01	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/po0205/mppigdxm")
public class PO0205MpPigdxmController extends TMMBaseController {

    @Resource(name = "PO0205mpPigdxmService")
    private PO0205MpPigdxmService mpPigdxmService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_pigdxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPigdxmDefaultVO
	 * @return "/mpPigdxm/MpPigdxmList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{buyDateM}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPigdxmList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("buyDateM") String buyDateM,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String buyType = req.getParameter("buyType");
    	String facKind = req.getParameter("facKind");
    	String brandCode = req.getParameter("brandCode");
    	String custCode = req.getParameter("custCode");
    	
    	MpPigdxmVO searchVO = new MpPigdxmVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setBuyDateM(StringUtils.remove(buyDateM,"-")); 
    	
    	searchVO.setFacKind(facKind);
    	searchVO.setBuyType(buyType);
    	searchVO.setBrandCode(brandCode);
    	searchVO.setCustCode(custCode);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> mpPigdxmList = mpPigdxmService.selectMpPigdxmList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(mpPigdxmList != null)
    		pageSet.setTotalRecordCount(mpPigdxmList.size());
    	pageSet.setResult(mpPigdxmList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    
    @RequestMapping(value="/{buyDateM}/export",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectExMpPigdxmList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("buyDateM") String buyDateM,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	String buyType = req.getParameter("buyType");
    	String facKind = req.getParameter("facKind");
    	String brandCode = req.getParameter("brandCode");
    	String custCode = req.getParameter("custCode");
    	
    	MpPigdxmVO searchVO = new MpPigdxmVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setBuyDateM(StringUtils.remove(buyDateM,"-")); 
    	
    	searchVO.setFacKind(facKind);
    	searchVO.setBuyType(buyType);
    	searchVO.setBrandCode(brandCode);
    	searchVO.setCustCode(custCode);
    	
        List<EgovMap> mpPigdxmList = mpPigdxmService.selectMpPigdxmList(searchVO);
		
        ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpPigdxmList, PO.PP0205, PO.PP0205_TYPE, PO.PP0205_NM);
        
		return null;
    } 
    
    @RequestMapping(value="/{buyDateM}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpPigdxm(
        @PathVariable("corpCode") String corpCode,
		@PathVariable("custCode") String custCode,
		@PathVariable("buyDateM") String buyDateM,
        List<EgovMap> mpPigdxmVOs,
        HttpServletRequest req,
		HttpServletResponse res)
        throws Exception {
	
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		MPUserSession sess = getSession(req);
		
		List ilist = new ArrayList();
		//중복검색tmPlatxmVOs
		if(mpPigdxmVOs != null)
		{
			for(EgovMap vo : mpPigdxmVOs)
	    	{
				vo.put("corpCode",corpCode);
				vo.put("custCode",custCode);
				vo.put("buyDateM",StringUtils.remove(buyDateM,"-"));
				if(sess != null)
				{
					vo.put("crUser",sess.getUser().getId());
				}
	    		ilist.add(vo);
	    	}
		}
		
		mpPigdxmService.updateMpPigdxm(ilist);
	    return _filter.makeCORSEntity( result );
    }
    
    /*@RequestMapping("/mpPigdxm/addMpPigdxmView.do")
    public String addMpPigdxmView(
            @ModelAttribute("searchVO") MpPigdxmDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpPigdxmVO", new MpPigdxmVO());
        return "/mpPigdxm/MpPigdxmRegister";
    }
    
    @RequestMapping("/mpPigdxm/updateMpPigdxmView.do")
    public String updateMpPigdxmView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("dochCode") java.lang.String dochCode ,
            @RequestParam("hisNo") java.lang.String hisNo ,
            @ModelAttribute("searchVO") MpPigdxmDefaultVO searchVO, Model model)
            throws Exception {
        MpPigdxmVO mpPigdxmVO = new MpPigdxmVO();
        mpPigdxmVO.setCorpCode(corpCode);
        mpPigdxmVO.setDochCode(dochCode);
        mpPigdxmVO.setHisNo(hisNo);
        // 변수명은 CoC 에 따라 mpPigdxmVO
        model.addAttribute(selectMpPigdxm(mpPigdxmVO, searchVO));
        return "/mpPigdxm/MpPigdxmRegister";
    }

    @RequestMapping("/mpPigdxm/selectMpPigdxm.do")
    public @ModelAttribute("mpPigdxmVO")
    MpPigdxmVO selectMpPigdxm(
            MpPigdxmVO mpPigdxmVO,
            @ModelAttribute("searchVO") MpPigdxmDefaultVO searchVO) throws Exception {
        return mpPigdxmService.selectMpPigdxm(mpPigdxmVO);
    }

    @RequestMapping("/mpPigdxm/updateMpPigdxm.do")
    public String updateMpPigdxm(
            MpPigdxmVO mpPigdxmVO,
            @ModelAttribute("searchVO") MpPigdxmDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPigdxmService.updateMpPigdxm(mpPigdxmVO);
        status.setComplete();
        return "forward:/mpPigdxm/MpPigdxmList.do";
    }
    
    @RequestMapping("/mpPigdxm/deleteMpPigdxm.do")
    public String deleteMpPigdxm(
            MpPigdxmVO mpPigdxmVO,
            @ModelAttribute("searchVO") MpPigdxmDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPigdxmService.deleteMpPigdxm(mpPigdxmVO);
        status.setComplete();
        return "forward:/mpPigdxm/MpPigdxmList.do";
    }*/

}
