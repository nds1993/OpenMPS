package nds.mpm.prod.PP0106.web;

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
import nds.mpm.prod.PP0105.service.MpBomHService;
import nds.mpm.prod.PP0105.vo.MpBomHVO;

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
 * @Class Name :  MpBomH
 *
 * @author MPM TEAM
 * @since 2017.06.07
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : BOM 조회
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.08.28	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/pp0106/mpbomh")
public class PP0106MpBomHController extends TMMBaseController{

    @Resource(name = "mpBomHService")
    private MpBomHService mpBomHService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_bom_h 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpBomHDefaultVO
	 * @return "/mpBomH/MpBomHList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{proCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpBomHList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("proCode") String proCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpBomHVO searchVO = new MpBomHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setProCode(proCode);
    	
        List<?> userInfoList = mpBomHService.selectPP0106MpBomDList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(userInfoList!=null)
    	pageSet.setTotalRecordCount(userInfoList.size());
    	pageSet.setResult(userInfoList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/{proCode}/export",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectExMpBomHList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("proCode") String proCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpBomHVO searchVO = new MpBomHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setProCode(proCode);
    	
        List<EgovMap> userInfoList = mpBomHService.selectPP0106MpBomDList(searchVO);
        String[] columns = PP.PP0106;
        String sheetName = PP.PP0106_NM;
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, userInfoList, columns, PP.PP0106_TYPE, sheetName);
		
    	return null;
    }
    /*@RequestMapping("/mpBomH/addMpBomHView.do")
    public String addMpBomHView(
            @ModelAttribute("searchVO") MpBomHDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpBomHVO", new MpBomHVO());
        return "/mpBomH/MpBomHRegister";
    }
    
    @RequestMapping("/mpBomH/addMpBomH.do")
    public String addMpBomH(
            MpBomHVO mpBomHVO,
            @ModelAttribute("searchVO") MpBomHDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpBomHService.insertMpBomH(mpBomHVO);
        status.setComplete();
        return "forward:/mpBomH/MpBomHList.do";
    }
    
    @RequestMapping("/mpBomH/updateMpBomHView.do")
    public String updateMpBomHView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("plantCode") java.lang.String plantCode ,
            @RequestParam("bomCode") java.lang.String bomCode ,
            @RequestParam("bomVer") java.math.BigDecimal bomVer ,
            @RequestParam("proCode") java.lang.String proCode ,
            @ModelAttribute("searchVO") MpBomHDefaultVO searchVO, Model model)
            throws Exception {
        MpBomHVO mpBomHVO = new MpBomHVO();
        mpBomHVO.setCorpCode(corpCode);
        mpBomHVO.setPlantCode(plantCode);
        mpBomHVO.setBomCode(bomCode);
        mpBomHVO.setBomVer(bomVer);
        mpBomHVO.setProCode(proCode);
        // 변수명은 CoC 에 따라 mpBomHVO
        model.addAttribute(selectMpBomH(mpBomHVO, searchVO));
        return "/mpBomH/MpBomHRegister";
    }

    @RequestMapping("/mpBomH/selectMpBomH.do")
    public @ModelAttribute("mpBomHVO")
    MpBomHVO selectMpBomH(
            MpBomHVO mpBomHVO,
            @ModelAttribute("searchVO") MpBomHDefaultVO searchVO) throws Exception {
        return mpBomHService.selectMpBomH(mpBomHVO);
    }

    @RequestMapping("/mpBomH/updateMpBomH.do")
    public String updateMpBomH(
            MpBomHVO mpBomHVO,
            @ModelAttribute("searchVO") MpBomHDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpBomHService.updateMpBomH(mpBomHVO);
        status.setComplete();
        return "forward:/mpBomH/MpBomHList.do";
    }
    
    @RequestMapping("/mpBomH/deleteMpBomH.do")
    public String deleteMpBomH(
            MpBomHVO mpBomHVO,
            @ModelAttribute("searchVO") MpBomHDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpBomHService.deleteMpBomH(mpBomHVO);
        status.setComplete();
        return "forward:/mpBomH/MpBomHList.do";
    }*/

}
