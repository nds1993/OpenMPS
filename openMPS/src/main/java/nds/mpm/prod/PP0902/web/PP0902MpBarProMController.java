package nds.mpm.prod.PP0902.web;

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
import nds.mpm.prod.PP0504.vo.MpBarProMVO;
import nds.mpm.prod.PP0902.service.MpDailySumMService;
import nds.mpm.prod.PP0902.service.PP0902MpBarProMService;
import nds.mpm.prod.PP0902.vo.MpDailySumMVO;

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
 * @Class Name : MpBarProMController.java
 * @Description : MpBarProM Controller class
 * @Modification Information
 *
 * @author 3333
 * @since 3333
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/pp0902/mpbarprom")
public class PP0902MpBarProMController extends TMMBaseController {

    @Resource(name = "PP0902mpBarProMService")
    private PP0902MpBarProMService mpBarProMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_bar_pro_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpBarProMDefaultVO
	 * @return "/mpBarProM/MpBarProMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpBarProMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
     	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
     	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
     	
     	String plantCode = req.getParameter("plantCode");
     	
     	MpBarProMVO searchVO = new MpBarProMVO();
     	searchVO.setCorpCode(corpCode);
     	
     	searchVO.setStrtDate(strtDate);
     	searchVO.setLastDate(lastDate);
     	searchVO.setPlantCode(plantCode);
		
        List<?> mpBarProMList = mpBarProMService.selectMpBarProMList(searchVO);
        
        PageSet pageSet = new PageSet();
        
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	
    	if(mpBarProMList != null)
    		pageSet.setTotalRecordCount(mpBarProMList.size());
    	pageSet.setResult(mpBarProMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
     * 실적집계실행
     * 
     * */
    @RequestMapping(value="/{strtDate}/{lastDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpBarProM(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		MpBarProMVO searchVO,
            HttpServletRequest req
            )
            throws Exception {
    	
    	String plantCode = req.getParameter("plantCode");
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	searchVO.setCorpCode(corpCode);
    	searchVO.setStrtDate(strtDate);
    	searchVO.setCrUser(sess.getUser().getId());
    	
    	result = mpBarProMService.insertDaliySumMpBarProM(searchVO);
    	
        return _filter.makeCORSEntity( result );
    }
    
    /*@RequestMapping("/mpBarProM/addMpBarProMView.do")
    public String addMpBarProMView(
            @ModelAttribute("searchVO") MpBarProMDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpBarProMVO", new MpBarProMVO());
        return "/mpBarProM/MpBarProMRegister";
    }
    
    @RequestMapping("/mpBarProM/addMpBarProM.do")
    public String addMpBarProM(
            MpBarProMVO mpBarProMVO,
            @ModelAttribute("searchVO") MpBarProMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpBarProMService.insertMpBarProM(mpBarProMVO);
        status.setComplete();
        return "forward:/mpBarProM/MpBarProMList.do";
    }
    
    @RequestMapping("/mpBarProM/updateMpBarProMView.do")
    public String updateMpBarProMView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("plantCode") java.lang.String plantCode ,
            @RequestParam("barCode") java.lang.String barCode ,
            @ModelAttribute("searchVO") MpBarProMDefaultVO searchVO, Model model)
            throws Exception {
        MpBarProMVO mpBarProMVO = new MpBarProMVO();
        mpBarProMVO.setCorpCode(corpCode);
        mpBarProMVO.setPlantCode(plantCode);
        mpBarProMVO.setBarCode(barCode);
        // 변수명은 CoC 에 따라 mpBarProMVO
        model.addAttribute(selectMpBarProM(mpBarProMVO, searchVO));
        return "/mpBarProM/MpBarProMRegister";
    }

    @RequestMapping("/mpBarProM/selectMpBarProM.do")
    public @ModelAttribute("mpBarProMVO")
    MpBarProMVO selectMpBarProM(
            MpBarProMVO mpBarProMVO,
            @ModelAttribute("searchVO") MpBarProMDefaultVO searchVO) throws Exception {
        return mpBarProMService.selectMpBarProM(mpBarProMVO);
    }

    @RequestMapping("/mpBarProM/updateMpBarProM.do")
    public String updateMpBarProM(
            MpBarProMVO mpBarProMVO,
            @ModelAttribute("searchVO") MpBarProMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpBarProMService.updateMpBarProM(mpBarProMVO);
        status.setComplete();
        return "forward:/mpBarProM/MpBarProMList.do";
    }
    
    @RequestMapping("/mpBarProM/deleteMpBarProM.do")
    public String deleteMpBarProM(
            MpBarProMVO mpBarProMVO,
            @ModelAttribute("searchVO") MpBarProMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpBarProMService.deleteMpBarProM(mpBarProMVO);
        status.setComplete();
        return "forward:/mpBarProM/MpBarProMList.do";
    }
*/
}
