package nds.tmm.common.TMCOUR40.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.tmm.common.TMCOUR30.web.TMCOUR30Controller;
import nds.tmm.common.TMCOUR40.service.TMCOUR40Service;
import nds.tmm.common.TMCOUR40.vo.TMCOUR40DefaultVO;
import nds.tmm.common.TMCOUR40.vo.TMCOUR40VO;

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

/**
 * @Class Name :  TMCOUR40Controller
 *
 * @author MPM TEAM
 * @since 2017.06.07
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 그룹코드관리( TMCOUR40 )
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
@RequestMapping("/tmm/{corpCode}/tmcour40/tmgrupxm")
public class TMCOUR40Controller {
	
	private static final Logger _logger = LoggerFactory.getLogger(TMCOUR30Controller.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "TMCOUR40Service")
    private TMCOUR40Service TMCOUR40Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * tm_grupxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TMCOUR40DefaultVO
	 * @return "/TMCOUR40/TMCOUR40List"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOUR40List(
    		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String searchCondition = req.getParameter("searchCondition");
    	String searchKeyword = req.getParameter("searchKeyword");
    	
    	TMCOUR40VO searchVO = new TMCOUR40VO();
    	
    	searchVO.setPageSize(pageSize);
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setSearchKeyword(searchKeyword);
    	
    	PageSet pageSet = new PageSet();
    	
        List<?> TMCOUR40List = TMCOUR40Service.selectTMCOUR40List(searchVO);
    	
        int totCnt = TMCOUR40Service.selectTMCOUR40ListTotCnt(searchVO);
    	
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(TMCOUR40List);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="/{groupName}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOUR40GroupNameList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("groupName") String groupName,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String searchCondition = req.getParameter("searchCondition");
    	String searchKeyword = req.getParameter("searchKeyword");
    	
    	TMCOUR40VO searchVO = new TMCOUR40VO();
    	
    	searchVO.setPageSize(pageSize);
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setSearchKeyword(searchKeyword);
    	
    	searchVO.setGroupName(groupName);
    	
    	PageSet pageSet = new PageSet();
    	
        List<?> TMCOUR40List = TMCOUR40Service.selectTMCOUR40List(searchVO);
    	
        int totCnt = TMCOUR40Service.selectTMCOUR40ListTotCnt(searchVO);
    	
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(TMCOUR40List);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/TMCOUR40/addTMCOUR40View.do")
    public String addTMCOUR40View(
    		@PathVariable("corpCode") String corpCode, 
    		Model model)
            throws Exception {
        model.addAttribute("TMCOUR40VO", new TMCOUR40VO());
        return "/TMCOUR40/TMCOUR40Register";
    }
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addTMCOUR40(
            @PathVariable("corpCode") String corpCode, 
            @RequestBody List<TMCOUR40VO> tMCOUR40VOs, 
            SessionStatus status)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	List ilist = new ArrayList();
    	if(tMCOUR40VOs != null)
    	{
    		for(TMCOUR40VO vo : tMCOUR40VOs)
        	{
    			vo.setCorpCode(corpCode);
        		ilist.add(vo);
        	}
    	}
        TMCOUR40Service.insertTMCOUR40(ilist);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/TMCOUR40/updateTMCOUR40View.do")
    public String updateTMCOUR40View(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("gorupCode") java.lang.String groupCode ,
            @ModelAttribute("searchVO") TMCOUR40DefaultVO searchVO, Model model)
            throws Exception {
        TMCOUR40VO TMCOUR40VO = new TMCOUR40VO();
        TMCOUR40VO.setCorpCode(corpCode);
        TMCOUR40VO.setGroupCode(groupCode);
        // 변수명은 CoC 에 따라 TMCOUR40VO
        model.addAttribute(selectTMCOUR40(TMCOUR40VO, searchVO));
        return "/TMCOUR40/TMCOUR40Register";
    }

    @RequestMapping("/selectTMCOUR40.do")
    public @ModelAttribute("TMCOUR40VO")
    TMCOUR40VO selectTMCOUR40(
            TMCOUR40VO TMCOUR40VO,
            @ModelAttribute("searchVO") TMCOUR40DefaultVO searchVO) throws Exception {
        return TMCOUR40Service.selectTMCOUR40(TMCOUR40VO);
    }

    @RequestMapping("/updateTMCOUR40.do")
    public ResponseEntity<ResultEx> updateTMCOUR40(
    		@RequestBody List<Object> tMCOUR40VOs, 
            SessionStatus status)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        TMCOUR40Service.updateTMCOUR40(tMCOUR40VOs);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/deleteTMCOUR40.do")
    public ResponseEntity<ResultEx> deleteTMCOUR40(
    		@RequestBody List<Object> tMCOUR40VOs, 
            SessionStatus status)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        TMCOUR40Service.deleteTMCOUR40(tMCOUR40VOs);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }

}
