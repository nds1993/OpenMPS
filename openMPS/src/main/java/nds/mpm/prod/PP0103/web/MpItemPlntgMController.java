package nds.mpm.prod.PP0103.web;

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

import nds.mpm.prod.PP0103.service.MpItemPlntgMService;
import nds.mpm.prod.PP0103.vo.MpItemPlntgMDefaultVO;
import nds.mpm.prod.PP0103.vo.MpItemPlntgMVO;

/**
 * @Class Name : MpItemPlntgMController.java
 * @Description : MpItemPlntgM Controller class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

//@Controller
@SessionAttributes(types=MpItemPlntgMVO.class)
public class MpItemPlntgMController {

    @Resource(name = "mpItemPlntgMService")
    private MpItemPlntgMService mpItemPlntgMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_item_plntg_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpItemPlntgMDefaultVO
	 * @return "/mpItemPlntgM/MpItemPlntgMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/mpItemPlntgM/MpItemPlntgMList.do")
    public String selectMpItemPlntgMList(@ModelAttribute("searchVO") MpItemPlntgMDefaultVO searchVO, 
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
		
        List<?> mpItemPlntgMList = mpItemPlntgMService.selectMpItemPlntgMList(searchVO);
        model.addAttribute("resultList", mpItemPlntgMList);
        
        int totCnt = mpItemPlntgMService.selectMpItemPlntgMListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "/mpItemPlntgM/MpItemPlntgMList";
    } 
    
    @RequestMapping("/mpItemPlntgM/addMpItemPlntgMView.do")
    public String addMpItemPlntgMView(
            @ModelAttribute("searchVO") MpItemPlntgMDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpItemPlntgMVO", new MpItemPlntgMVO());
        return "/mpItemPlntgM/MpItemPlntgMRegister";
    }
    
    @RequestMapping("/mpItemPlntgM/addMpItemPlntgM.do")
    public String addMpItemPlntgM(
            MpItemPlntgMVO mpItemPlntgMVO,
            @ModelAttribute("searchVO") MpItemPlntgMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpItemPlntgMService.insertMpItemPlntgM(mpItemPlntgMVO);
        status.setComplete();
        return "forward:/mpItemPlntgM/MpItemPlntgMList.do";
    }
    
    @RequestMapping("/mpItemPlntgM/updateMpItemPlntgMView.do")
    public String updateMpItemPlntgMView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("grupPlant") java.lang.String grupPlant ,
            @RequestParam("proCode") java.lang.String proCode ,
            @ModelAttribute("searchVO") MpItemPlntgMDefaultVO searchVO, Model model)
            throws Exception {
        MpItemPlntgMVO mpItemPlntgMVO = new MpItemPlntgMVO();
        mpItemPlntgMVO.setCorpCode(corpCode);
        mpItemPlntgMVO.setGrupPlant(grupPlant);
        mpItemPlntgMVO.setProCode(proCode);
        // 변수명은 CoC 에 따라 mpItemPlntgMVO
        model.addAttribute(selectMpItemPlntgM(mpItemPlntgMVO, searchVO));
        return "/mpItemPlntgM/MpItemPlntgMRegister";
    }

    @RequestMapping("/mpItemPlntgM/selectMpItemPlntgM.do")
    public @ModelAttribute("mpItemPlntgMVO")
    MpItemPlntgMVO selectMpItemPlntgM(
            MpItemPlntgMVO mpItemPlntgMVO,
            @ModelAttribute("searchVO") MpItemPlntgMDefaultVO searchVO) throws Exception {
        return mpItemPlntgMService.selectMpItemPlntgM(mpItemPlntgMVO);
    }

    @RequestMapping("/mpItemPlntgM/updateMpItemPlntgM.do")
    public String updateMpItemPlntgM(
            MpItemPlntgMVO mpItemPlntgMVO,
            @ModelAttribute("searchVO") MpItemPlntgMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpItemPlntgMService.updateMpItemPlntgM(mpItemPlntgMVO);
        status.setComplete();
        return "forward:/mpItemPlntgM/MpItemPlntgMList.do";
    }
    
    @RequestMapping("/mpItemPlntgM/deleteMpItemPlntgM.do")
    public String deleteMpItemPlntgM(
            MpItemPlntgMVO mpItemPlntgMVO,
            @ModelAttribute("searchVO") MpItemPlntgMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpItemPlntgMService.deleteMpItemPlntgM(mpItemPlntgMVO);
        status.setComplete();
        return "forward:/mpItemPlntgM/MpItemPlntgMList.do";
    }

}
