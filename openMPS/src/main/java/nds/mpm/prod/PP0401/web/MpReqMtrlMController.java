package nds.mpm.prod.PP0401.web;

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
import nds.mpm.prod.PP0401.service.MpReqMtrlMService;
import nds.mpm.prod.PP0401.vo.MpReqMtrlMVO;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  MpBarProMController
 *
 * @author MPM TEAM
 * @since 2017.08.24
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 자재소요량 산출
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
@RequestMapping("/mpm/{corpCode}/pp0401/mpreqmtrlm")
public class MpReqMtrlMController extends TMMBaseController{

    @Resource(name = "mpReqMtrlMService")
    private MpReqMtrlMService mpReqMtrlMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_req_mtrl_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpReqMtrlMDefaultVO
	 * @return "/mpReqMtrlM/MpReqMtrlMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{workDate}/{plantCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpReqMtrlMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("workDate") String workDate,
    		@PathVariable("plantCode") String plantCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        String searchCondition = req.getParameter("searchCondition");
        String searchCondition2 = req.getParameter("searchCondition2");
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpReqMtrlMVO searchVO = new MpReqMtrlMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	searchVO.setGrupPlant(plantCode);
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setSearchCondition2(searchCondition2);
    	
        List<?> mpReqMtrlMList = mpReqMtrlMService.selectMpReqMtrlMList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpReqMtrlMService.selectMpReqMtrlMListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpReqMtrlMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/{workDate}/{plantCode}/export",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectExMpReqMtrlMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("workDate") String workDate,
    		@PathVariable("plantCode") String plantCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        String searchCondition = req.getParameter("searchCondition");
        String searchCondition2 = req.getParameter("searchCondition2");
    	
    	MpReqMtrlMVO searchVO = new MpReqMtrlMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	searchVO.setGrupPlant(plantCode);
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setSearchCondition2(searchCondition2);
    	
        List<EgovMap> mpReqMtrlMList = mpReqMtrlMService.selectMpReqMtrlMList(searchVO);

        String[] columns = PP.PP0401;
        String sheetName = PP.PP0401_NM;
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpReqMtrlMList, columns, PP.PP0401_TYPE, sheetName);
    	
    	return null;
    } 
    /**
     * 저장 
     * 
     * */
    @RequestMapping(value="/{workDate}/{grupPlant}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpReqMtrlM(
            @PathVariable("corpCode") String corpCode,
            @PathVariable("workDate") String workDate,
            @PathVariable("grupPlant") String grupPlant,
            MpReqMtrlMVO searchVO,
    		@RequestBody List<EgovMap> tmPlatxmVOs,
    		HttpServletRequest req,
    		HttpServletResponse res
    		)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(tmPlatxmVOs != null)
    	{
    		for(EgovMap vo : tmPlatxmVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("workDate",StringUtils.remove(workDate,"-"));
    			vo.put("grupPlant",grupPlant);
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	mpReqMtrlMService.insertMpReqMtrlM(searchVO, ilist, false);
        return _filter.makeCORSEntity( result );
    }
    
    /**
     * 소요량산출 
     * 
     * */
    @RequestMapping(value="/calc/{workDate}/{plantCode}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addCalcMpReqMtrlM(
            @PathVariable("corpCode") String corpCode,
            @PathVariable("workDate") String workDate,
            @PathVariable("plantCode") String plantCode,
    		HttpServletRequest req,
    		HttpServletResponse res
    		)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
    	MPUserSession sess = getSession(req);
    	
    	MpReqMtrlMVO searchVO = new MpReqMtrlMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	searchVO.setGrupPlant(plantCode);
    	
    	result = callCalcMtrlM( req,  "N",  searchVO);
    	
        return _filter.makeCORSEntity( result );
    }
    
    /**
     * 소요량산출 
     * 
     * */
    @RequestMapping(value="/calc/{workDate}/{plantCode}/{forceD}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addCalcMpReqMtrlM(
            @PathVariable("corpCode") String corpCode,
            @PathVariable("workDate") String workDate,
            @PathVariable("plantCode") String plantCode,
            @PathVariable("forceD") String forceD,
    		HttpServletRequest req,
    		HttpServletResponse res
    		)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
    	MpReqMtrlMVO searchVO = new MpReqMtrlMVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setWorkDate(StringUtils.remove(workDate,"-"));
    	searchVO.setGrupPlant(plantCode);
    	
    	boolean isDelete = false;
    	
    	result = callCalcMtrlM( req,  forceD,  searchVO);
    	
        return _filter.makeCORSEntity( result );
    }
    
    public ResultEx callCalcMtrlM(HttpServletRequest req, String forceD, MpReqMtrlMVO searchVO) throws Exception{
    	
    	MPUserSession sess = getSession(req);
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	if(!"Y".equals(forceD))
    	{
    		//해당 계획일자 산출데이터 존재체크 
        	if(mpReqMtrlMService.checkMpReqMtrlMCnt(searchVO) > 0)
        	{
        		return new ResultEx( Consts.ResultCode.RC_DUPLICATE);
        	}
    	}
    	else if("Y".equals(forceD))
    	{
    	}

    	//산출목록 조회. 
    	List<EgovMap> calcList = mpReqMtrlMService.selectCalculateMpReqMtrlMList(searchVO);
    	
    	//산출저장.
    	if(calcList != null && calcList.size() == 0)
    	{
    		result.setResultCode(Consts.ResultCode.RC_FIND_NOT_FOUND.getCode());
    		result.setMsg(Consts.ResultCode.RC_FIND_NOT_FOUND.getDesc());
    	}
    	else
    	{
    		
    		for(EgovMap vo : calcList)
    		{
    			vo.put("dsType", "C");
    			vo.put("crUser",sess.getUser().getId());
    		}
    		
    		mpReqMtrlMService.insertMpReqMtrlM(searchVO, calcList, true);
    		
    		List<?> mpReqMtrlMList = mpReqMtrlMService.selectMpReqMtrlMList(searchVO);
    		
    		PageSet pageSet = new PageSet();
            
        	pageSet.setTotalRecordCount(mpReqMtrlMList.size());
        	pageSet.setResult(mpReqMtrlMList);
    		
        	result.setExtraData(pageSet);
    	}
    	
    	return result;
    }
   
    
    /*@RequestMapping("/mpReqMtrlM/addMpReqMtrlMView.do")
    public String addMpReqMtrlMView(
            @ModelAttribute("searchVO") MpReqMtrlMDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpReqMtrlMVO", new MpReqMtrlMVO());
        return "/mpReqMtrlM/MpReqMtrlMRegister";
    }
    
    @RequestMapping("/mpReqMtrlM/addMpReqMtrlM.do")
    public String addMpReqMtrlM(
            MpReqMtrlMVO mpReqMtrlMVO,
            @ModelAttribute("searchVO") MpReqMtrlMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpReqMtrlMService.insertMpReqMtrlM(mpReqMtrlMVO);
        status.setComplete();
        return "forward:/mpReqMtrlM/MpReqMtrlMList.do";
    }
    
    @RequestMapping("/mpReqMtrlM/updateMpReqMtrlMView.do")
    public String updateMpReqMtrlMView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("plantCode") java.lang.String plantCode ,
            @RequestParam("workDate") java.lang.String workDate ,
            @RequestParam("mtrlCode") java.lang.String mtrlCode ,
            @RequestParam("reqSource") java.lang.String reqSource ,
            @RequestParam("proCode") java.lang.String proCode ,
            @ModelAttribute("searchVO") MpReqMtrlMDefaultVO searchVO, Model model)
            throws Exception {
        MpReqMtrlMVO mpReqMtrlMVO = new MpReqMtrlMVO();
        mpReqMtrlMVO.setCorpCode(corpCode);
        mpReqMtrlMVO.setPlantCode(plantCode);
        mpReqMtrlMVO.setWorkDate(workDate);
        mpReqMtrlMVO.setMtrlCode(mtrlCode);
        mpReqMtrlMVO.setReqSource(reqSource);
        mpReqMtrlMVO.setProCode(proCode);
        // 변수명은 CoC 에 따라 mpReqMtrlMVO
        model.addAttribute(selectMpReqMtrlM(mpReqMtrlMVO, searchVO));
        return "/mpReqMtrlM/MpReqMtrlMRegister";
    }

    @RequestMapping("/mpReqMtrlM/selectMpReqMtrlM.do")
    public @ModelAttribute("mpReqMtrlMVO")
    MpReqMtrlMVO selectMpReqMtrlM(
            MpReqMtrlMVO mpReqMtrlMVO,
            @ModelAttribute("searchVO") MpReqMtrlMDefaultVO searchVO) throws Exception {
        return mpReqMtrlMService.selectMpReqMtrlM(mpReqMtrlMVO);
    }

    @RequestMapping("/mpReqMtrlM/updateMpReqMtrlM.do")
    public String updateMpReqMtrlM(
            MpReqMtrlMVO mpReqMtrlMVO,
            @ModelAttribute("searchVO") MpReqMtrlMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpReqMtrlMService.updateMpReqMtrlM(mpReqMtrlMVO);
        status.setComplete();
        return "forward:/mpReqMtrlM/MpReqMtrlMList.do";
    }
    
    @RequestMapping("/mpReqMtrlM/deleteMpReqMtrlM.do")
    public String deleteMpReqMtrlM(
            MpReqMtrlMVO mpReqMtrlMVO,
            @ModelAttribute("searchVO") MpReqMtrlMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpReqMtrlMService.deleteMpReqMtrlM(mpReqMtrlMVO);
        status.setComplete();
        return "forward:/mpReqMtrlM/MpReqMtrlMList.do";
    }*/

}
