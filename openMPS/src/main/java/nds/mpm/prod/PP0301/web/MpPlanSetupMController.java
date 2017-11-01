package nds.mpm.prod.PP0301.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.prod.PP0201.vo.MpYieldInfoMVO;
import nds.mpm.prod.PP0201.web.MpYieldInfoMController;
import nds.mpm.prod.PP0301.service.MpPlanSetupMService;
import nds.mpm.prod.PP0301.vo.MpPlanSetupMDefaultVO;
import nds.mpm.prod.PP0301.vo.MpPlanSetupMVO;

/**
 * @Class Name :  MpPlanSetupMController
 *
 * @author MPM TEAM
 * @since 2017.08.01
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 생산계획Setup( PP0301 )
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
@RequestMapping("/mpm/{corpCode}/pp0301/mpplansetupm")
public class MpPlanSetupMController  extends TMMBaseController{
	
	private static final Logger _logger = LoggerFactory.getLogger(MpPlanSetupMController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "mpPlanSetupMService")
    private MpPlanSetupMService mpPlanSetupMService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * mp_plan_setup_m 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 MpPlanSetupMDefaultVO
	 * @return "/mpPlanSetupM/MpPlanSetupMList"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectMpPlanSetupMList(
    		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	MpPlanSetupMVO searchVO = new MpPlanSetupMVO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> mpPlanSetupMList = mpPlanSetupMService.selectMpPlanSetupMList(searchVO);

    	PageSet pageSet = new PageSet();
        
        int totCnt = mpPlanSetupMService.selectMpPlanSetupMListTotCnt(searchVO);
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(mpPlanSetupMList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping("/mpPlanSetupM/addMpPlanSetupMView.do")
    public String addMpPlanSetupMView(
            @ModelAttribute("searchVO") MpPlanSetupMDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("mpPlanSetupMVO", new MpPlanSetupMVO());
        return "/mpPlanSetupM/MpPlanSetupMRegister";
    }
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addMpPlanSetupM(
    		@PathVariable("corpCode") String corpCode,
            @RequestBody List<EgovMap> mpPlanSetupMVOs,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
        
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	//중복검색tmPlatxmVOs
    	if(mpPlanSetupMVOs != null)
    	{
    		for(EgovMap vo : mpPlanSetupMVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("workPlan",(String)vo.get("workPlan"));
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	mpPlanSetupMService.insertMpPlanSetupM(ilist);
    	
    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(mpPlanSetupMVOs.size());
    	pageSet.setResult(mpPlanSetupMVOs);
    	
    	result.setExtraData(pageSet);
    	
        return _filter.makeCORSEntity( result );
    }
    /***
    @RequestMapping("/mpPlanSetupM/updateMpPlanSetupMView.do")
    public String updateMpPlanSetupMView(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("plantCode") java.lang.String plantCode ,
            @RequestParam("proLcode") java.lang.String proLcode ,
            @ModelAttribute("searchVO") MpPlanSetupMDefaultVO searchVO, Model model)
            throws Exception {
        MpPlanSetupMVO mpPlanSetupMVO = new MpPlanSetupMVO();
        mpPlanSetupMVO.setCorpCode(corpCode);
        mpPlanSetupMVO.setPlantCode(plantCode);
        mpPlanSetupMVO.setProLcode(proLcode);
        // 변수명은 CoC 에 따라 mpPlanSetupMVO
        model.addAttribute(selectMpPlanSetupM(mpPlanSetupMVO, searchVO));
        return "/mpPlanSetupM/MpPlanSetupMRegister";
    }

    @RequestMapping("/mpPlanSetupM/selectMpPlanSetupM.do")
    public @ModelAttribute("mpPlanSetupMVO")
    MpPlanSetupMVO selectMpPlanSetupM(
            MpPlanSetupMVO mpPlanSetupMVO,
            @ModelAttribute("searchVO") MpPlanSetupMDefaultVO searchVO) throws Exception {
        return mpPlanSetupMService.selectMpPlanSetupM(mpPlanSetupMVO);
    }

    @RequestMapping("/mpPlanSetupM/updateMpPlanSetupM.do")
    public String updateMpPlanSetupM(
            MpPlanSetupMVO mpPlanSetupMVO,
            @ModelAttribute("searchVO") MpPlanSetupMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPlanSetupMService.updateMpPlanSetupM(mpPlanSetupMVO);
        status.setComplete();
        return "forward:/mpPlanSetupM/MpPlanSetupMList.do";
    }
    
    @RequestMapping("/mpPlanSetupM/deleteMpPlanSetupM.do")
    public String deleteMpPlanSetupM(
            MpPlanSetupMVO mpPlanSetupMVO,
            @ModelAttribute("searchVO") MpPlanSetupMDefaultVO searchVO, SessionStatus status)
            throws Exception {
        mpPlanSetupMService.deleteMpPlanSetupM(mpPlanSetupMVO);
        status.setComplete();
        return "forward:/mpPlanSetupM/MpPlanSetupMList.do";
    }
	**/
}
