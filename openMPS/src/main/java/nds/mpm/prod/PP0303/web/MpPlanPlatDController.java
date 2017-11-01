package nds.mpm.prod.PP0303.web;

import java.util.ArrayList;
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
import nds.mpm.prod.PP0302.service.MpPlanCmHService;
import nds.mpm.prod.PP0302.vo.MpPlanCmHVO;
import nds.mpm.prod.PP0303.service.MpPlanPlatDService;
import nds.mpm.prod.PP0303.vo.MpPlanPlatDVO;

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
 * @Class Name :  MpPlanPlatDController
 *
 * @author MPM TEAM
 * @since 2017.08.23
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 생산계획서 조회/발행( PP0306 )
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.06.07	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/pp0303")
public class MpPlanPlatDController extends TMMBaseController{

	@Resource(name = "mpPlanCmHService")
	private MpPlanCmHService mpPlanCmHService;
	
    @Resource(name = "mpPlanPlatDService")
    private MpPlanPlatDService mpPlanPlatDService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
   	 * PP0306 mp_plan_cm_h 합계를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 MpPlanPlatDDefaultVO
   	 * @return "/mpPlanPlatD/MpPlanPlatDList"
   	 * @exception Exception
   	 */
    @RequestMapping(value="/mpplancmd/total/{plantCode}/{workDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPlanCmHTotal(
   		@PathVariable("corpCode") String corpCode,
   		@PathVariable("plantCode") String plantCode,
   		@PathVariable("workDate") String workDate,
   		HttpServletRequest req,
   		HttpServletResponse res,
   		ModelMap model)
           throws Exception {
	   	
	    ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
	   	
	   	MpPlanCmHVO searchVO = new MpPlanCmHVO();
	   	searchVO.setCorpCode(corpCode);
	   	searchVO.setPlantCode(plantCode);
	   	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
	   	
	   	List resultList = new ArrayList();
	   	EgovMap resultMap = mpPlanCmHService.selectMpPlanCmHDoosuSum(searchVO);
	   	resultList.add(resultMap);
	   	
	   	PageSet pageSet = new PageSet();
        
    	pageSet.setResult(resultList);
		
    	result.setExtraData(pageSet);
	   	
	   	return _filter.makeCORSEntity( result );
	 } 
    /**
	 * PP0306 mp_plan_plat_d 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPlanPlatDDefaultVO
	 * @return "/mpPlanPlatD/MpPlanPlatDList"
	 * @exception Exception
	 */
    @RequestMapping(value="/mpplanplatd/{plantCode}/{workDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPlanPlatDList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("workDate") String workDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpPlanPlatDVO searchVO = new MpPlanPlatDVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPlantCode(plantCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> mpPlanSetupMList = mpPlanPlatDService.selectMpPlanPlatDList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpPlanPlatDService.selectMpPlanPlatDListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpPlanSetupMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/mpplanplatd/{plantCode}/{workDate}/export",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectExMpPlanPlatDList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("workDate") String workDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpPlanPlatDVO searchVO = new MpPlanPlatDVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPlantCode(plantCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	
        List<EgovMap> mpPlanSetupMList = mpPlanPlatDService.selectMpPlanPlatDList(searchVO);
        String[] columns = PP.PP0303;
        String sheetName = PP.PP0303_NM;
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpPlanSetupMList, columns, PP.PP0303_TYPE, sheetName);
		
    	return null;
    }
    
    /**
    @RequestMapping("/mpPlanPlatD/addMpPlanPlatDView.do")
    public String addMpPlanPlatDView(
            @ModelAttribute("searchVO") MpPlanPlatDDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpPlanPlatDVO", new MpPlanPlatDVO());
        return "/mpPlanPlatD/MpPlanPlatDRegister";
    }
    
    @RequestMapping("/mpPlanPlatD/addMpPlanPlatD.do")
    public String addMpPlanPlatD(
            MpPlanPlatDVO mpPlanPlatDVO,
            @ModelAttribute("searchVO") MpPlanPlatDDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPlanPlatDService.insertMpPlanPlatD(mpPlanPlatDVO);
        status.setComplete();
        return "forward:/mpPlanPlatD/MpPlanPlatDList.do";
    }
    
    @RequestMapping("/mpPlanPlatD/selectMpPlanPlatD.do")
    public @ModelAttribute("mpPlanPlatDVO")
    MpPlanPlatDVO selectMpPlanPlatD(
            MpPlanPlatDVO mpPlanPlatDVO,
            @ModelAttribute("searchVO") MpPlanPlatDDefaultVO searchVO) throws Exception {
        return mpPlanPlatDService.selectMpPlanPlatD(mpPlanPlatDVO);
    }

    @RequestMapping("/mpPlanPlatD/updateMpPlanPlatD.do")
    public String updateMpPlanPlatD(
            MpPlanPlatDVO mpPlanPlatDVO,
            @ModelAttribute("searchVO") MpPlanPlatDDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPlanPlatDService.updateMpPlanPlatD(mpPlanPlatDVO);
        status.setComplete();
        return "forward:/mpPlanPlatD/MpPlanPlatDList.do";
    }
    
    @RequestMapping("/mpPlanPlatD/deleteMpPlanPlatD.do")
    public String deleteMpPlanPlatD(
            MpPlanPlatDVO mpPlanPlatDVO,
            @ModelAttribute("searchVO") MpPlanPlatDDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPlanPlatDService.deleteMpPlanPlatD(mpPlanPlatDVO);
        status.setComplete();
        return "forward:/mpPlanPlatD/MpPlanPlatDList.do";
    }
    */

}
