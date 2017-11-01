package nds.mpm.sales.SD0405.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.sales.SD0405.service.MpOrderDService;
import nds.mpm.sales.SD0405.vo.MpOrderDDefaultVO;
import nds.mpm.sales.SD0405.vo.MpOrderDVO;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpOrderDController.java
 * @Description : MpOrderD Controller class
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
@RequestMapping("/mpm/{corpCode}/sd0405/mporderd")
public class MpOrderDController extends TMMBaseController{

    @Resource(name = "mpOrderDService")
    private MpOrderDService mpOrderDService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    @RequestMapping("/mpOrderD/addMpOrderDView.do")
    public String addMpOrderDView(
            @ModelAttribute("searchVO") MpOrderDDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpOrderDVO", new MpOrderDVO());
        return "/mpOrderD/MpOrderDRegister";
    }
    
    @RequestMapping("/mpOrderD/addMpOrderD.do")
    public String addMpOrderD(
    		EgovMap mpOrderDVO,
            @ModelAttribute("searchVO") MpOrderDDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpOrderDService.insertMpOrderD(mpOrderDVO);
        status.setComplete();
        return "forward:/mpOrderD/MpOrderDList.do";
    }

    @RequestMapping("/mpOrderD/updateMpOrderD.do")
    public String updateMpOrderD(
    		EgovMap mpOrderDVO,
            @ModelAttribute("searchVO") MpOrderDDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpOrderDService.updateMpOrderD(mpOrderDVO);
        status.setComplete();
        return "forward:/mpOrderD/MpOrderDList.do";
    }
    
    @RequestMapping("/mpOrderD/deleteMpOrderD.do")
    public String deleteMpOrderD(
    		EgovMap mpOrderDVO,
            @ModelAttribute("searchVO") MpOrderDDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpOrderDService.deleteMpOrderD(mpOrderDVO);
        status.setComplete();
        return "forward:/mpOrderD/MpOrderDList.do";
    }

}
