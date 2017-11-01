package nds.mpm.prod.PP0504.web;

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
import nds.mpm.prod.PP0504.service.MpBarProMService;
import nds.mpm.prod.PP0504.vo.MpBarProMVO;

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
 * 화면명 : 생산실적조회 및 조정 ( PP0504 )
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
@RequestMapping("/mpm/{corpCode}/pp0504/mpbarprom")
public class MpBarProMController  extends TMMBaseController{

    @Resource(name = "mpBarProMService")
    private MpBarProMService mpBarProMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_bar_pro_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpBarProMDefaultVO
	 * strtDate YYYYMMDD
	 * lastDate YYYYMMDD
	 * @return "/mpBarProM/MpBarProMList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{strtDate}/{lastDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpBarProMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        String searchCondition = req.getParameter("searchCondition");
        String searchCondition2 = req.getParameter("searchCondition2");
        
        String plantCode = req.getParameter("plantCode");
        String largeCode = req.getParameter("largeCode");
        String proCode = req.getParameter("proCode");
        String barCode = req.getParameter("barCode");
        String cartonNo = req.getParameter("cartonNo");
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpBarProMVO searchVO = new MpBarProMVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setSearchCondition2(searchCondition2);
    	
    	searchVO.setPlantCode(plantCode);
    	searchVO.setLargeCode(largeCode);
    	searchVO.setProCode(proCode);
    	searchVO.setBarCode(barCode);
    	searchVO.setCartonNo(cartonNo);
    	
    	
        List<?> mpBarProMList = mpBarProMService.selectMpBarProMList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpBarProMService.selectMpBarProMListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpBarProMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    /**
     * excel
     * 
     * */
    @RequestMapping(value="/{strtDate}/{lastDate}/export",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectExMpBarProMList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
        
        String searchCondition = StringUtils.defaultIfEmpty(req.getParameter("searchCondition"),"date");
        String searchCondition2 = req.getParameter("searchCondition2");
        
        String plantCode = req.getParameter("plantCode");
        String largeCode = req.getParameter("largeCode");
        String proCode = req.getParameter("proCode");
        String barCode = req.getParameter("barCode");
        String cartonNo = req.getParameter("cartonNo");
    	
    	
    	MpBarProMVO searchVO = new MpBarProMVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setStrtDate(StringUtils.remove(strtDate,"-"));
    	searchVO.setLastDate(StringUtils.remove(lastDate,"-"));
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setSearchCondition2(searchCondition2);
    	
    	searchVO.setPlantCode(plantCode);
    	searchVO.setLargeCode(largeCode);
    	searchVO.setProCode(proCode);
    	searchVO.setBarCode(barCode);
    	searchVO.setCartonNo(cartonNo);
    	
    	
        List<EgovMap> mpBarProMList = mpBarProMService.selectMpBarProMList(searchVO);
        String[] columns = PP.PP05041;
        String sheetName = PP.PP05041_NM;
        
        if("plant".equals(searchCondition))
        {
        	columns = PP.PP05042;
            sheetName = PP.PP05042_NM;
        }
        else if("nong".equals(searchCondition))
        {
        	columns = PP.PP05043;
            sheetName = PP.PP05043_NM;
        }
        
    	ExcelUtil.createExcelDownloadEgovMapList(res, jsession, mpBarProMList, columns, PP.PP0504_TYPE, sheetName);
    	
    	return null;
    } 
    
    /***
	 * corpCode
	 * strtDate YYYYMMDD
	 * lastDate YYYYMMDD
	 * 
	 * */
    @RequestMapping(value="/{strtDate}/{lastDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> removeMpBarProM(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
            @RequestBody List<EgovMap> MpBarProMVOs,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(MpBarProMVOs != null)
    	{
    		for(EgovMap vo : MpBarProMVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("strtDate",StringUtils.remove(strtDate,"-"));
    			vo.put("lastDate",StringUtils.remove(lastDate,"-"));
    			
    			if(sess != null)
    			{
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	result = mpBarProMService.insertMpBarProM(ilist);
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{strtDate}/{lastDate}/restore",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> restoreMpBarProM(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
            @RequestBody List<EgovMap> MpBarProMVOs,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	if(MpBarProMVOs != null)
    	{
    		for(EgovMap vo : MpBarProMVOs)
        	{
    			vo.put("dsType","U");
        	}
    	}
    	
    	return removeMpBarProM(
        		corpCode,
        		strtDate,
        		lastDate,
                MpBarProMVOs,
                req,
        		res);
    }
    
    @RequestMapping(value="/{strtDate}/{lastDate}/delete",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> removeProcMpBarProM(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("strtDate") String strtDate,
    		@PathVariable("lastDate") String lastDate,
            @RequestBody List<EgovMap> MpBarProMVOs,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	if(MpBarProMVOs != null)
    	{
    		for(EgovMap vo : MpBarProMVOs)
        	{
    			vo.put("dsType","D");
        	}
    	}
    	
    	return removeMpBarProM(
        		corpCode,
        		strtDate,
        		lastDate,
                MpBarProMVOs,
                req,
        		res);
    }
    
    /***
    @RequestMapping("/mpBarProM/updateMpBarProMView.do")
    public String updateMpBarProMView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("plantCode") java.lang.String plantCode ,
            @RequestParam("barCode") java.lang.String barCode ,
            @ModelAttribute("searchVO") MpBarProMDefaultVO searchVO, Model model)
            throws Exception {
        MpBarProMVO mpBarProMVO = new MpBarProMVO();
        mpBarProMVO.setCorpCode(corpCode);
        mpBarProMVO.setPlantCode(plantCode);
        mpBarProMVO.setBarCode(barCode);
        // 변수명은 CoC 에 따라 mpBarProMVO
        model.addAttribute(selectMpBarProM(mpBarProMVO, searchVO));
        return "/mpBarProM/MpBarProMRegister";
    }

    @RequestMapping("/mpBarProM/selectMpBarProM.do")
    public @ModelAttribute("mpBarProMVO")
    MpBarProMVO selectMpBarProM(
            MpBarProMVO mpBarProMVO,
            @ModelAttribute("searchVO") MpBarProMDefaultVO searchVO) throws Exception {
        return mpBarProMService.selectMpBarProM(mpBarProMVO);
    }

    @RequestMapping("/mpBarProM/updateMpBarProM.do")
    public String updateMpBarProM(
            MpBarProMVO mpBarProMVO,
            @ModelAttribute("searchVO") MpBarProMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpBarProMService.updateMpBarProM(mpBarProMVO);
        status.setComplete();
        return "forward:/mpBarProM/MpBarProMList.do";
    }
    
    @RequestMapping("/mpBarProM/deleteMpBarProM.do")
    public String deleteMpBarProM(
            MpBarProMVO mpBarProMVO,
            @ModelAttribute("searchVO") MpBarProMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpBarProMService.deleteMpBarProM(mpBarProMVO);
        status.setComplete();
        return "forward:/mpBarProM/MpBarProMList.do";
    }
    @RequestMapping("/mpBarProM/addMpBarProMView.do")
    public String addMpBarProMView(
            @ModelAttribute("searchVO") MpBarProMDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpBarProMVO", new MpBarProMVO());
        return "/mpBarProM/MpBarProMRegister";
    }
    
	**/
}
