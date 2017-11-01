package nds.mpm.prod.PP0105.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.prod.PP0105.service.MpBomHService;
import nds.mpm.prod.PP0105.vo.MpBomDVO;
import nds.mpm.prod.PP0105.vo.MpBomHDefaultVO;
import nds.mpm.prod.PP0105.vo.MpBomHVO;
import nds.mpm.prod.PP0105.vo.MultiMpBomVO;

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
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  MpBomHController
 *
 * @author MPM TEAM
 * @since 2017.08.21
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : BOM 등록 ( PP0105 )
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
@RequestMapping("/mpm/{corpCode}/pp0105/mpbomh")
public class MpBomHController extends TMMBaseController{

    @Resource(name = "mpBomHService")
    private MpBomHService mpBomHService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
     * 
	 * bom 헤더정보 조회.
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpBomHList(
    		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
     	
    	String plantCode = req.getParameter("plantCode");
     	String bomCode = req.getParameter("bomCode");
     	int bomVer = Integer.parseInt(StringUtils.defaultIfEmpty((String)req.getParameter("bomVer"),"0"));
     	
     	MpBomHVO searchVO = new MpBomHVO();
     	searchVO.setCorpCode(corpCode);
     	searchVO.setPlantCode(plantCode);
     	searchVO.setBomCode(bomCode);
     	if(bomVer != 0)
     		searchVO.setBomVer(bomVer);
     	searchVO.setUseYn("Y");
     	
        List<?> mpBomDList = mpBomHService.selectMpBomHList(searchVO);

     	PageSet pageSet = new PageSet();
         
        int totCnt = mpBomHService.selectMpBomHListTotCnt(searchVO);
     	pageSet.setTotalRecordCount(totCnt);
     	pageSet.setResult(mpBomDList);
 		
     	result.setExtraData(pageSet);
     	
     	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/form/{plantCode}/{bomCode}",method=RequestMethod.GET)
    public  ResponseEntity<ResultEx> selectMpPlanSetupM(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("bomCode") String bomCode,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
        
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int bomVer = Integer.parseInt(StringUtils.defaultIfEmpty(StringUtils.trim((String)req.getParameter("bomVer")),"0"));
    	
    	EgovMap searchVO = new EgovMap();
     	searchVO.put("corpCode",corpCode);
     	searchVO.put("plantCode",plantCode);
     	searchVO.put("bomCode",bomCode);
     	searchVO.put("bomVer",bomVer);
     	
     	result.setExtraData(mpBomHService.selectMpBomH(searchVO));
     	
     	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/hist",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectHistMpBomHList(
    		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
     	
    	String plantCode = req.getParameter("plantCode");
     	String bomCode = req.getParameter("bomCode");
     	int bomVer = Integer.parseInt(StringUtils.defaultIfEmpty((String)req.getParameter("bomVer"),"0"));
     	
     	MpBomHVO searchVO = new MpBomHVO();
     	searchVO.setCorpCode(corpCode);
     	searchVO.setPlantCode(plantCode);
     	searchVO.setBomCode(bomCode);
     	
     	System.out.println("bomVer :: " + bomVer);
     	
     	if(bomVer != 0)
     		searchVO.setBomVer(bomVer);
     	
        List<?> mpBomDList = mpBomHService.selectMpBomHList(searchVO);

     	PageSet pageSet = new PageSet();
         
        int totCnt = mpBomHService.selectMpBomHListTotCnt(searchVO);
     	pageSet.setTotalRecordCount(totCnt);
     	pageSet.setResult(mpBomDList);
 		
     	result.setExtraData(pageSet);
     	
     	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/dup/{plantCode}/{proCode}",method=RequestMethod.GET)
    public  ResponseEntity<ResultEx> selectCheckDupMpPlanSetupM(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("plantCode") String plantCode,
    		@PathVariable("proCode") String proCode,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
        
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpBomHVO searchVO = new MpBomHVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPlantCode(plantCode);
    	searchVO.setProCode(proCode);
    	
     	result.setExtraData(mpBomHService.checkDupMpBomHCnt(searchVO) > 0 ? "Y" : "N");
     	
     	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpBomH(
    		@PathVariable("corpCode") String corpCode,
            @RequestBody MultiMpBomVO multiMpBomVO,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(multiMpBomVO != null)
    	{
    		MpBomHVO head = multiMpBomVO.getHead();
    		head.setCorpCode(corpCode);
    		head.setCrUser(sess.getUser().getId());
    		head.setMdUser(sess.getUser().getId());
    		List<EgovMap> detail = multiMpBomVO.getDetail();
    		for(EgovMap vo : detail)
        	{
    			vo.put("corpCode", corpCode);
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	result = mpBomHService.insertMpBomH(multiMpBomVO);
        return _filter.makeCORSEntity( result );
    }
    /**
    @RequestMapping("/mpBomH/addMpBomHView.do")
    public String addMpBomHView(
            @ModelAttribute("searchVO") MpBomHDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpBomHVO", new MpBomHVO());
        return "/mpBomH/MpBomHRegister";
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
    }
	**/
}
