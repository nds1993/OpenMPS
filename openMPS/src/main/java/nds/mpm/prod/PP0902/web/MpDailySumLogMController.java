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

import nds.mpm.prod.PP0902.service.MpDailySumLogMService;
import nds.mpm.prod.PP0902.vo.MpDailySumLogMDefaultVO;
import nds.mpm.prod.PP0902.vo.MpDailySumLogMVO;

/**
 * @Class Name : MpDailySumLogMController.java
 * @Description : MpDailySumLogM Controller class
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
//@SessionAttributes(types=MpDailySumLogMVO.class)
public class MpDailySumLogMController {

    @Resource(name = "mpDailySumLogMService")
    private MpDailySumLogMService mpDailySumLogMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_daily_sum_log_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpDailySumLogMDefaultVO
	 * @return "/mpDailySumLogM/MpDailySumLogMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/mpDailySumLogM/MpDailySumLogMList.do")
    public String selectMpDailySumLogMList(@ModelAttribute("searchVO") MpDailySumLogMDefaultVO searchVO, 
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
		
        List<?> mpDailySumLogMList = mpDailySumLogMService.selectMpDailySumLogMList(searchVO);
        model.addAttribute("resultList", mpDailySumLogMList);
        
        int totCnt = mpDailySumLogMService.selectMpDailySumLogMListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "/mpDailySumLogM/MpDailySumLogMList";
    } 
    
    @RequestMapping("/mpDailySumLogM/addMpDailySumLogMView.do")
    public String addMpDailySumLogMView(
            @ModelAttribute("searchVO") MpDailySumLogMDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpDailySumLogMVO", new MpDailySumLogMVO());
        return "/mpDailySumLogM/MpDailySumLogMRegister";
    }
    
    @RequestMapping("/mpDailySumLogM/addMpDailySumLogM.do")
    public String addMpDailySumLogM(
            MpDailySumLogMVO mpDailySumLogMVO,
            @ModelAttribute("searchVO") MpDailySumLogMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpDailySumLogMService.insertMpDailySumLogM(mpDailySumLogMVO);
        status.setComplete();
        return "forward:/mpDailySumLogM/MpDailySumLogMList.do";
    }
    
    @RequestMapping("/mpDailySumLogM/updateMpDailySumLogMView.do")
    public String updateMpDailySumLogMView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("plantCode") java.lang.String plantCode ,
            @RequestParam("cmPmType") java.lang.String cmPmType ,
            @RequestParam("batchJobDate") java.lang.String batchJobDate ,
            @RequestParam("batchJobSeq") java.math.BigDecimal batchJobSeq ,
            @ModelAttribute("searchVO") MpDailySumLogMDefaultVO searchVO, Model model)
            throws Exception {
        MpDailySumLogMVO mpDailySumLogMVO = new MpDailySumLogMVO();
        mpDailySumLogMVO.setCorpCode(corpCode);
        mpDailySumLogMVO.setPlantCode(plantCode);
        mpDailySumLogMVO.setCmPmType(cmPmType);
        mpDailySumLogMVO.setBatchJobDate(batchJobDate);
        mpDailySumLogMVO.setBatchJobSeq(batchJobSeq);
        // 변수명은 CoC 에 따라 mpDailySumLogMVO
        model.addAttribute(selectMpDailySumLogM(mpDailySumLogMVO, searchVO));
        return "/mpDailySumLogM/MpDailySumLogMRegister";
    }

    @RequestMapping("/mpDailySumLogM/selectMpDailySumLogM.do")
    public @ModelAttribute("mpDailySumLogMVO")
    MpDailySumLogMVO selectMpDailySumLogM(
            MpDailySumLogMVO mpDailySumLogMVO,
            @ModelAttribute("searchVO") MpDailySumLogMDefaultVO searchVO) throws Exception {
        return mpDailySumLogMService.selectMpDailySumLogM(mpDailySumLogMVO);
    }

    @RequestMapping("/mpDailySumLogM/updateMpDailySumLogM.do")
    public String updateMpDailySumLogM(
            MpDailySumLogMVO mpDailySumLogMVO,
            @ModelAttribute("searchVO") MpDailySumLogMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpDailySumLogMService.updateMpDailySumLogM(mpDailySumLogMVO);
        status.setComplete();
        return "forward:/mpDailySumLogM/MpDailySumLogMList.do";
    }
    
    @RequestMapping("/mpDailySumLogM/deleteMpDailySumLogM.do")
    public String deleteMpDailySumLogM(
            MpDailySumLogMVO mpDailySumLogMVO,
            @ModelAttribute("searchVO") MpDailySumLogMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpDailySumLogMService.deleteMpDailySumLogM(mpDailySumLogMVO);
        status.setComplete();
        return "forward:/mpDailySumLogM/MpDailySumLogMList.do";
    }

}
