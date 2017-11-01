package nds.mpm.prod.PP0604.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.mpm.common.util.ExcelUtil;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.excel.PP;
import nds.mpm.prod.PP0604.service.MpBarinProMService;
import nds.mpm.prod.PP0604.vo.MpBarinProMVO;

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
 * @Class Name :  MpBarProMController
 *
 * @author MPM TEAM
 * @since 2017.08.29
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 속라벨 발행현황 조회( PP0604 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.08.29	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/pp0604/mpbarinprom")
public class MpBarinProMController extends TMMBaseController{

    @Resource(name = "mpBarinProMService")
    private MpBarinProMService mpBarinProMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_barin_pro_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpBarinProMDefaultVO
	 * @return "/mpBarinProM/MpBarinProMList"
	 * @exception Exception
	 */
    @RequestMapping(value="{strtDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpBarinProMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        String plantCode = req.getParameter("plantCode");
        String proCode = req.getParameter("proCode");
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpBarinProMVO searchVO = new MpBarinProMVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setPlantCode(plantCode);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setProCode(proCode);
    	
        List<?> mpBarinProMList = mpBarinProMService.selectMpBarinProMList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(mpBarinProMList != null)
    		pageSet.setTotalRecordCount(mpBarinProMList.size());
    	pageSet.setResult(mpBarinProMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    /**
	 * mp_barin_pro_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpBarinProMDefaultVO
	 * @return "/mpBarinProM/MpBarinProMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/export",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectExMpBarinProMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        String plantCode = req.getParameter("plantCode");
        String proCode = req.getParameter("proCode");
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpBarinProMVO searchVO = new MpBarinProMVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setPlantCode(plantCode);
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setProCode(proCode);
    	
        List<EgovMap> mpBarinProMList = mpBarinProMService.selectMpBarinProMList(searchVO);

        String[] columns = PP.PP0701;
        String sheetName = PP.PP0701_NM;
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpBarinProMList, columns, PP.PP0701_TYPE, sheetName);
    	
    	return null;
    }
    
    /*@RequestMapping("/mpBarinProM/addMpBarinProMView.do")
    public String addMpBarinProMView(
            @ModelAttribute("searchVO") MpBarinProMDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpBarinProMVO", new MpBarinProMVO());
        return "/mpBarinProM/MpBarinProMRegister";
    }
    
    @RequestMapping("/mpBarinProM/addMpBarinProM.do")
    public String addMpBarinProM(
            MpBarinProMVO mpBarinProMVO,
            @ModelAttribute("searchVO") MpBarinProMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpBarinProMService.insertMpBarinProM(mpBarinProMVO);
        status.setComplete();
        return "forward:/mpBarinProM/MpBarinProMList.do";
    }
    
    @RequestMapping("/mpBarinProM/updateMpBarinProMView.do")
    public String updateMpBarinProMView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("plantCode") java.lang.String plantCode ,
            @RequestParam("barinCode") java.lang.String barinCode ,
            @RequestParam("proCode") java.lang.String proCode ,
            @ModelAttribute("searchVO") MpBarinProMDefaultVO searchVO, Model model)
            throws Exception {
        MpBarinProMVO mpBarinProMVO = new MpBarinProMVO();
        mpBarinProMVO.setCorpCode(corpCode);
        mpBarinProMVO.setPlantCode(plantCode);
        mpBarinProMVO.setBarinCode(barinCode);
        mpBarinProMVO.setProCode(proCode);
        // 변수명은 CoC 에 따라 mpBarinProMVO
        model.addAttribute(selectMpBarinProM(mpBarinProMVO, searchVO));
        return "/mpBarinProM/MpBarinProMRegister";
    }

    @RequestMapping("/mpBarinProM/selectMpBarinProM.do")
    public @ModelAttribute("mpBarinProMVO")
    MpBarinProMVO selectMpBarinProM(
            MpBarinProMVO mpBarinProMVO,
            @ModelAttribute("searchVO") MpBarinProMDefaultVO searchVO) throws Exception {
        return mpBarinProMService.selectMpBarinProM(mpBarinProMVO);
    }

    @RequestMapping("/mpBarinProM/updateMpBarinProM.do")
    public String updateMpBarinProM(
            MpBarinProMVO mpBarinProMVO,
            @ModelAttribute("searchVO") MpBarinProMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpBarinProMService.updateMpBarinProM(mpBarinProMVO);
        status.setComplete();
        return "forward:/mpBarinProM/MpBarinProMList.do";
    }
    
    @RequestMapping("/mpBarinProM/deleteMpBarinProM.do")
    public String deleteMpBarinProM(
            MpBarinProMVO mpBarinProMVO,
            @ModelAttribute("searchVO") MpBarinProMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpBarinProMService.deleteMpBarinProM(mpBarinProMVO);
        status.setComplete();
        return "forward:/mpBarinProM/MpBarinProMList.do";
    }*/

}
