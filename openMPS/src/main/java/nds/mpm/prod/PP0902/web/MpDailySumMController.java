package nds.mpm.prod.PP0902.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import nds.mpm.prod.PP0902.service.MpDailySumMService;
import nds.mpm.prod.PP0902.vo.MpDailySumMDefaultVO;
import nds.mpm.prod.PP0902.vo.MpDailySumMVO;

/**
 * @Class Name : MpDailySumMController.java
 * @Description : MpDailySumM Controller class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

//@Controller
//@SessionAttributes(types=MpDailySumMVO.class)
public class MpDailySumMController {

    @Resource(name = "mpDailySumMService")
    private MpDailySumMService mpDailySumMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_daily_sum_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpDailySumMDefaultVO
	 * @return "/mpDailySumM/MpDailySumMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/mpDailySumM/MpDailySumMList.do")
    public String selectMpDailySumMList(@ModelAttribute("searchVO") MpDailySumMDefaultVO searchVO, 
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
		
        List<?> mpDailySumMList = mpDailySumMService.selectMpDailySumMList(searchVO);
        model.addAttribute("resultList", mpDailySumMList);
        
        int totCnt = mpDailySumMService.selectMpDailySumMListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "/mpDailySumM/MpDailySumMList";
    } 
    
    @RequestMapping("/mpDailySumM/addMpDailySumMView.do")
    public String addMpDailySumMView(
            @ModelAttribute("searchVO") MpDailySumMDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpDailySumMVO", new MpDailySumMVO());
        return "/mpDailySumM/MpDailySumMRegister";
    }
    
    @RequestMapping("/mpDailySumM/addMpDailySumM.do")
    public String addMpDailySumM(
            MpDailySumMVO mpDailySumMVO,
            @ModelAttribute("searchVO") MpDailySumMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpDailySumMService.insertMpDailySumM(mpDailySumMVO);
        status.setComplete();
        return "forward:/mpDailySumM/MpDailySumMList.do";
    }
    
    @RequestMapping("/mpDailySumM/updateMpDailySumMView.do")
    public String updateMpDailySumMView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("plantCode") java.lang.String plantCode ,
            @RequestParam("workDate") java.lang.String workDate ,
            @RequestParam("cmPmType") java.lang.String cmPmType ,
            @RequestParam("proCode") java.lang.String proCode ,
            @RequestParam("lpcCode") java.lang.String lpcCode ,
            @RequestParam("qtyType") java.lang.String qtyType ,
            @ModelAttribute("searchVO") MpDailySumMDefaultVO searchVO, Model model)
            throws Exception {
        MpDailySumMVO mpDailySumMVO = new MpDailySumMVO();
        mpDailySumMVO.setCorpCode(corpCode);
        mpDailySumMVO.setPlantCode(plantCode);
        mpDailySumMVO.setWorkDate(workDate);
        mpDailySumMVO.setCmPmType(cmPmType);
        mpDailySumMVO.setProCode(proCode);
        mpDailySumMVO.setLpcCode(lpcCode);
        mpDailySumMVO.setQtyType(qtyType);
        // 변수명은 CoC 에 따라 mpDailySumMVO
        model.addAttribute(selectMpDailySumM(mpDailySumMVO, searchVO));
        return "/mpDailySumM/MpDailySumMRegister";
    }

    @RequestMapping("/mpDailySumM/selectMpDailySumM.do")
    public @ModelAttribute("mpDailySumMVO")
    MpDailySumMVO selectMpDailySumM(
            MpDailySumMVO mpDailySumMVO,
            @ModelAttribute("searchVO") MpDailySumMDefaultVO searchVO) throws Exception {
        return mpDailySumMService.selectMpDailySumM(mpDailySumMVO);
    }

    @RequestMapping("/mpDailySumM/updateMpDailySumM.do")
    public String updateMpDailySumM(
            MpDailySumMVO mpDailySumMVO,
            @ModelAttribute("searchVO") MpDailySumMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpDailySumMService.updateMpDailySumM(mpDailySumMVO);
        status.setComplete();
        return "forward:/mpDailySumM/MpDailySumMList.do";
    }
    
    @RequestMapping("/mpDailySumM/deleteMpDailySumM.do")
    public String deleteMpDailySumM(
            MpDailySumMVO mpDailySumMVO,
            @ModelAttribute("searchVO") MpDailySumMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpDailySumMService.deleteMpDailySumM(mpDailySumMVO);
        status.setComplete();
        return "forward:/mpDailySumM/MpDailySumMList.do";
    }

}
