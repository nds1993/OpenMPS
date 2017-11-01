package nds.mpm.prod.PP0603.web;

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
import nds.mpm.prod.PP0603.service.PP0603MpBarProMService;
import nds.mpm.prod.PP0504.vo.MpBarProMDefaultVO;
import nds.mpm.prod.PP0504.vo.MpBarProMVO;

/**
 * @Class Name : PP0603MpBarProMController
 *
 * @author MPM TEAM
 * @since 2017.08.30
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 공장별 생산현황 조회(개체별) ( PP0603 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.08.30	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/pp0603/mpbarprom")
public class PP0603MpBarProMController extends TMMBaseController {

    @Resource(name = "PP0603mpBarProMService")
    private PP0603MpBarProMService pP0603mpBarProMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_bar_pro_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpBarProMDefaultVO
	 * @return "/mpBarProM/MpBarProMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}/{plantCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpBarProMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		@PathVariable("plantCode") String plantCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        String searchCondition = req.getParameter("searchCondition");
        String searchCondition2 = req.getParameter("searchCondition2");
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpBarProMVO searchVO = new MpBarProMVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	searchVO.setPlantCode(plantCode);
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setSearchCondition2(searchCondition2);
    	
        List<?> mpBarProMList = pP0603mpBarProMService.selectMpBarProMList(searchVO);

    	PageSet pageSet = new PageSet();
        
        //int totCnt = pP0603mpBarProMService.selectMpBarProMListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	//pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpBarProMList);
		
    	result.setExtraData(pageSet);
    	
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
    }*/

}
