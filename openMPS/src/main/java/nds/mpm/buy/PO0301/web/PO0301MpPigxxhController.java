package nds.mpm.buy.PO0301.web;

import java.util.List;

import javax.annotation.Resource;

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
import nds.mpm.buy.PO0301.service.PO0301MpPigxxhService;
import nds.mpm.buy.PO0301.vo.MpPigxxhDefaultVO;
import nds.mpm.buy.PO0301.vo.MpPigxxhVO;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;

/**
 * @Class Name : MpPigxxhController.java
 * @Description : MpPigxxh Controller class
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
@RequestMapping("/mpm/{corpCode}/po0301/mppigxxh")
public class PO0301MpPigxxhController extends TMMBaseController {

    @Resource(name = "PO0301MpPigxxhService")
    private PO0301MpPigxxhService mpPigxxhService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_pigxxh 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPigxxhDefaultVO
	 * @return "/mpPigxxh/MpPigxxhList"
	 * @exception Exception
	 */
    @RequestMapping(value="/mpPigxxh/MpPigxxhList.do")
    public String selectMpPigxxhList(@ModelAttribute("searchVO") MpPigxxhDefaultVO searchVO, 
    		ModelMap model)
            throws Exception {
    	
    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List<?> mpPigxxhList = mpPigxxhService.selectMpPigxxhList(searchVO);
        model.addAttribute("resultList", mpPigxxhList);
        
        int totCnt = mpPigxxhService.selectMpPigxxhListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "/mpPigxxh/MpPigxxhList";
    } 
    
    @RequestMapping("/mpPigxxh/addMpPigxxhView.do")
    public String addMpPigxxhView(
            @ModelAttribute("searchVO") MpPigxxhDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpPigxxhVO", new MpPigxxhVO());
        return "/mpPigxxh/MpPigxxhRegister";
    }
    
    @RequestMapping("/mpPigxxh/addMpPigxxh.do")
    public String addMpPigxxh(
            MpPigxxhVO mpPigxxhVO,
            @ModelAttribute("searchVO") MpPigxxhDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPigxxhService.insertMpPigxxh(mpPigxxhVO);
        status.setComplete();
        return "forward:/mpPigxxh/MpPigxxhList.do";
    }
    
    @RequestMapping("/mpPigxxh/updateMpPigxxhView.do")
    public String updateMpPigxxhView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("procDate") java.lang.String procDate ,
            @ModelAttribute("searchVO") MpPigxxhDefaultVO searchVO, Model model)
            throws Exception {
        MpPigxxhVO mpPigxxhVO = new MpPigxxhVO();
        mpPigxxhVO.setCorpCode(corpCode);
        mpPigxxhVO.setProcDate(procDate);
        // 변수명은 CoC 에 따라 mpPigxxhVO
        return "/mpPigxxh/MpPigxxhRegister";
    }

    @RequestMapping(value="/{procDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPigxxh(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("procDate") String procDate,
            MpPigxxhVO mpPigxxhVO,
            @ModelAttribute("searchVO") MpPigxxhDefaultVO searchVO) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	mpPigxxhVO.setCorpCode(corpCode);
    	mpPigxxhVO.setProcDate(procDate);
    	
    	result.setExtraData(mpPigxxhService.selectMpPigxxh(mpPigxxhVO));
    	return _filter.makeCORSEntity( result );
    }

    @RequestMapping("/mpPigxxh/updateMpPigxxh.do")
    public String updateMpPigxxh(
            MpPigxxhVO mpPigxxhVO,
            @ModelAttribute("searchVO") MpPigxxhDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPigxxhService.updateMpPigxxh(mpPigxxhVO);
        status.setComplete();
        return "forward:/mpPigxxh/MpPigxxhList.do";
    }
    
    @RequestMapping("/mpPigxxh/deleteMpPigxxh.do")
    public String deleteMpPigxxh(
            MpPigxxhVO mpPigxxhVO,
            @ModelAttribute("searchVO") MpPigxxhDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPigxxhService.deleteMpPigxxh(mpPigxxhVO);
        status.setComplete();
        return "forward:/mpPigxxh/MpPigxxhList.do";
    }

}
