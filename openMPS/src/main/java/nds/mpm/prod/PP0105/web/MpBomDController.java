package nds.mpm.prod.PP0105.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.prod.PP0105.service.MpBomDService;
import nds.mpm.prod.PP0105.vo.MpBomDDefaultVO;
import nds.mpm.prod.PP0105.vo.MpBomDVO;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name : MpBomDController.java
 * @Description : MpBomD Controller class
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
@RequestMapping("/mpm/{corpCode}/pp0105/mpbomd")
public class MpBomDController extends TMMBaseController{

    @Resource(name = "mpBomDService")
    private MpBomDService mpBomDService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * bom 상세내역 조회.
	 * @exception Exception
	 */
    @RequestMapping(value="/{plantCode}/{bomCode}/{bomVer}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpBomDList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("bomCode") String bomCode,
    		@PathVariable("bomVer") int bomVer,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
     	
     	String grupPlant = req.getParameter("grupPlant");
     	
     	MpBomDVO searchVO = new MpBomDVO();
     	searchVO.setCorpCode(corpCode);
     	searchVO.setPlantCode(plantCode);
     	searchVO.setBomCode(bomCode);
     	searchVO.setBomVer(bomVer);
     	
        List<?> mpBomDList = mpBomDService.selectMpBomDList(searchVO);

     	PageSet pageSet = new PageSet();
         
        int totCnt = mpBomDService.selectMpBomDListTotCnt(searchVO);
     	pageSet.setTotalRecordCount(totCnt);
     	pageSet.setResult(mpBomDList);
 		
     	result.setExtraData(pageSet);
     	
     	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping("/mpBomD/addMpBomDView.do")
    public String addMpBomDView(
            @ModelAttribute("searchVO") MpBomDDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpBomDVO", new MpBomDVO());
        return "/mpBomD/MpBomDRegister";
    }
}
