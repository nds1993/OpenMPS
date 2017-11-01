package nds.mpm.prod.PP0305.web;

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
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.prod.PP0305.service.MpPlanPmMService;
import nds.mpm.prod.PP0305.vo.MpPlanPmMDefaultVO;
import nds.mpm.prod.PP0305.vo.MpPlanPmMVO;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpPlanPmMController.java
 * @Description : MpPlanPmM Controller class
 * @Modification Information
 *
 * @author 생산의뢰 접수 (PM주문)
 * @since 생산의뢰 접수 (PM주문)
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/pp0305/mpplanpmm")
public class MpPlanPmMController extends TMMBaseController {

    @Resource(name = "mpPlanPmMService")
    private MpPlanPmMService mpPlanPmMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_plan_pm_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPlanPmMDefaultVO
	 * @return "/mpPlanPmM/MpPlanPmMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{plantCode}/{workDate}/{status}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPlanPmMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("workDate") String workDate,
    		@PathVariable("status") String status,
    		HttpServletRequest req,
    		HttpServletResponse res,  
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpPlanPmMVO searchVO = new MpPlanPmMVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPlantCode(plantCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	
    	List<?> mpLpcInfoMList = null;
        int totCnt = 0;
        //접수분  
    	if("accept".equals(status))
        {
    		searchVO.setStatus("0");
    		mpLpcInfoMList = mpPlanPmMService.selectMpAcceptOrdrPmMList(searchVO);
            totCnt = mpLpcInfoMList.size();
        }
    	//계획진행분 
        else if("plan".equals(status))
        {
        	searchVO.setStatus("1");
        	mpLpcInfoMList = mpPlanPmMService.selectMpPlanPmMList(searchVO);
            totCnt = mpPlanPmMService.selectMpPlanPmMListTotCnt(searchVO);
        }
    	//확정분
        else if("complete".equals(status))
        {
        	searchVO.setStatus("3");
        	mpLpcInfoMList = mpPlanPmMService.selectMpPlanPmMList(searchVO);
            totCnt = mpPlanPmMService.selectMpPlanPmMListTotCnt(searchVO);
        }
        else{
        	return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_INVALID_PARAMS ) );
        }
        
    	PageSet pageSet = new PageSet();
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpLpcInfoMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/{plantCode}/{workDate}/{status}/export",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectExMpPlanPmMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("workDate") String workDate,
    		@PathVariable("status") String status,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
    	MpPlanPmMVO searchVO = new MpPlanPmMVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPlantCode(plantCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	
    	List<EgovMap> mpLpcInfoMList = null;
        int totCnt = 0;
        //접수분  
    	if("accept".equals(status))
        {
    		mpLpcInfoMList = mpPlanPmMService.selectMpAcceptOrdrPmMList(searchVO);
        }
    	//계획진행분 
        else if("plan".equals(status))
        {
        	searchVO.setStatus("1");
        	mpLpcInfoMList = mpPlanPmMService.selectMpPlanPmMList(searchVO);
        }
    	//확정분
        else if("complete".equals(status))
        {
        	searchVO.setStatus("3");
        	mpLpcInfoMList = mpPlanPmMService.selectMpPlanPmMList(searchVO);
        }
        else{
        	return null;
        }
    	
    	String[] columns = PP.PP0305;
        String sheetName = PP.PP0305_NM;
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpLpcInfoMList, columns, PP.PP0305_TYPE, sheetName);
    	return null;
    }
    
//    @RequestMapping("/mpPlanPmM/addMpPlanPmMView.do")
//    public String addMpPlanPmMView(
//            @ModelAttribute("searchVO") MpPlanPmMDefaultVO searchVO, Model model)
//            throws Exception {
//        model.addAttribute("mpPlanPmMVO", new MpPlanPmMVO());
//        return "/mpPlanPmM/MpPlanPmMRegister";
//    }
    
    @RequestMapping(value="/{plantCode}/{workDate}/{status}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpPlanPmM(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("workDate") String workDate,
    		@PathVariable("status") String status,
    		@RequestBody List<EgovMap> MpPlanPmMVOs,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	String statusCode = null;
    	//접수분  
    	if("accept".equals(status))
        {
    		statusCode = "0";
        }
    	//계획진행분 
        else if("plan".equals(status))
        {
        	statusCode = "1";
        }
    	//확정분
        else if("complete".equals(status))
        {
        	statusCode = "3";
        }
        else{
        	return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_INVALID_PARAMS ) );
        }
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(MpPlanPmMVOs != null)
    	{
    		for(EgovMap vo : MpPlanPmMVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("workDate",StringUtils.remove(workDate,"-"));
    			vo.put("delvDate",StringUtils.remove((String)vo.get("delvDate"),"-"));
    			vo.put("status",statusCode);
    			if(sess != null)
    			{
    				vo.put("mdUser",sess.getUser().getId());
    				vo.put("crUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    		
    		result = mpPlanPmMService.insertMpPlanPmM(ilist, statusCode);
    	}
    	
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/complete/{plantCode}/{workDate}/{status}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpPlanPmMComplete(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("workDate") String workDate,
    		@PathVariable("status") String status,
    		@RequestBody List<EgovMap> MpPlanPmMVOs,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(MpPlanPmMVOs != null)
    	{
    		for(EgovMap vo : MpPlanPmMVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("workDate",StringUtils.remove((String)vo.get("workDate"),"-"));
    			vo.put("delvDate",StringUtils.remove((String)vo.get("delvDate"),"-"));
    			if(sess != null)
    			{
    				vo.put("mdUser",sess.getUser().getId());
    				vo.put("crUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    		
    		result = mpPlanPmMService.updateMpPlanPmMConfirm(ilist);
    	}
    	
        return _filter.makeCORSEntity( result );
    }
    
    /**
    @RequestMapping("/mpPlanPmM/updateMpPlanPmMView.do")
    public String updateMpPlanPmMView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("plantCode") java.lang.String plantCode ,
            @RequestParam("planNo") java.lang.String planNo ,
            @RequestParam("proCode") java.lang.String proCode ,
            @ModelAttribute("searchVO") MpPlanPmMDefaultVO searchVO, Model model)
            throws Exception {
        MpPlanPmMVO mpPlanPmMVO = new MpPlanPmMVO();
        mpPlanPmMVO.setCorpCode(corpCode);
        mpPlanPmMVO.setPlantCode(plantCode);
        mpPlanPmMVO.setPlanNo(planNo);
        mpPlanPmMVO.setProCode(proCode);
        // 변수명은 CoC 에 따라 mpPlanPmMVO
        model.addAttribute(selectMpPlanPmM(mpPlanPmMVO, searchVO));
        return "/mpPlanPmM/MpPlanPmMRegister";
    }

    @RequestMapping("/mpPlanPmM/selectMpPlanPmM.do")
    public @ModelAttribute("mpPlanPmMVO")
    MpPlanPmMVO selectMpPlanPmM(
            MpPlanPmMVO mpPlanPmMVO,
            @ModelAttribute("searchVO") MpPlanPmMDefaultVO searchVO) throws Exception {
        return mpPlanPmMService.selectMpPlanPmM(mpPlanPmMVO);
    }

    @RequestMapping("/mpPlanPmM/updateMpPlanPmM.do")
    public String updateMpPlanPmM(
            MpPlanPmMVO mpPlanPmMVO,
            @ModelAttribute("searchVO") MpPlanPmMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPlanPmMService.updateMpPlanPmM(mpPlanPmMVO);
        status.setComplete();
        return "forward:/mpPlanPmM/MpPlanPmMList.do";
    }
    
    @RequestMapping("/mpPlanPmM/deleteMpPlanPmM.do")
    public String deleteMpPlanPmM(
            MpPlanPmMVO mpPlanPmMVO,
            @ModelAttribute("searchVO") MpPlanPmMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPlanPmMService.deleteMpPlanPmM(mpPlanPmMVO);
        status.setComplete();
        return "forward:/mpPlanPmM/MpPlanPmMList.do";
    }
	*/
}
