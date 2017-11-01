package nds.tmm.common.TMCOUR50.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.tmm.common.TMCOUR50.service.TMCOUR50Service;
import nds.tmm.common.TMCOUR50.vo.TMCOUR50DefaultVO;
import nds.tmm.common.TMCOUR50.vo.TMCOUR50VO;

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
 * @Class Name :  TMCOUR50Controller
 *
 * @author MPM TEAM
 * @since 2017.07.20
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 그룹별메뉴관리( TMCOUR50 )
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
@RequestMapping("/tmm/{corpCode}/tmcour50/tmauthxm")
public class TMCOUR50Controller {

	private static final Logger _logger = LoggerFactory.getLogger(TMCOUR50Controller.class);
	
	@Autowired
	protected CorsFilter		_filter;
	
    @Resource(name = "TMCOUR50Service")
    private TMCOUR50Service TMCOUR50Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * tm_authxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TMCOUR50DefaultVO
	 * @return "/TMCOUR50/TMCOUR50List"
	 * @exception Exception
	 */
    @RequestMapping(value="/{groupCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOUR50List(
    		@PathVariable("groupCode") String groupCode,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String searchCondition = req.getParameter("searchCondition");
    	String searchKeyword = req.getParameter("searchKeyword");
    	
    	TMCOUR50VO searchVO = new TMCOUR50VO();
    	
    	searchVO.setPageSize(pageSize);
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setSearchKeyword(searchKeyword);
    	
    	searchVO.setGroupCode(groupCode);
    	
    	PageSet pageSet = new PageSet();
    	
    	List<?> TMCOUR50List = TMCOUR50Service.selectTMCOUR50List(searchVO);
    	
    	int totCnt = TMCOUR50Service.selectTMCOUR50ListTotCnt(searchVO);
    	
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(TMCOUR50List);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping("/addTMCOUR50View.do")
    public String addTMCOUR50View(
            @ModelAttribute("searchVO") TMCOUR50DefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("TMCOUR50VO", new TMCOUR50VO());
        return "/TMCOUR50/TMCOUR50Register";
    }
    
    @RequestMapping(value="/{groupCode}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addTMCOUR50(
    		@PathVariable("corpCode") java.lang.String corpCode ,
    		@PathVariable("groupCode") String groupCode,
            @RequestBody List<TMCOUR50VO> tMCOUR50VOs,
            SessionStatus status)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        List ilist = new ArrayList();
    	if(tMCOUR50VOs != null)
    	{
    		for(TMCOUR50VO vo : tMCOUR50VOs)
        	{
    			vo.setCorpCode(corpCode);
    			vo.setGroupCode(groupCode);
        		ilist.add(vo);
        	}
    	}
        TMCOUR50Service.insertTMCOUR50(ilist);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/TMCOUR50/updateTMCOUR50View.do")
    public String updateTMCOUR50View(
            @RequestParam("corpCode") java.lang.String corpCode ,
            @RequestParam("groupCode") java.lang.String groupCode ,
            @RequestParam("menuCode") java.lang.String menuCode ,
            @ModelAttribute("searchVO") TMCOUR50DefaultVO searchVO, Model model)
            throws Exception {
        TMCOUR50VO TMCOUR50VO = new TMCOUR50VO();
        TMCOUR50VO.setCorpCode(corpCode);
        TMCOUR50VO.setGroupCode(groupCode);
        TMCOUR50VO.setMenuCode(menuCode);
        // 변수명은 CoC 에 따라 TMCOUR50VO
        model.addAttribute(selectTMCOUR50(TMCOUR50VO, searchVO));
        return "/TMCOUR50/TMCOUR50Register";
    }

    @RequestMapping("/selectTMCOUR50.do")
    public @ModelAttribute("TMCOUR50VO")
    TMCOUR50VO selectTMCOUR50(
            TMCOUR50VO TMCOUR50VO,
            @ModelAttribute("searchVO") TMCOUR50DefaultVO searchVO) throws Exception {
        return TMCOUR50Service.selectTMCOUR50(TMCOUR50VO);
    }

    @RequestMapping("/updateTMCOUR50.do")
    public String updateTMCOUR50(
            TMCOUR50VO TMCOUR50VO,
            @ModelAttribute("searchVO") TMCOUR50DefaultVO searchVO, SessionStatus status)
            throws Exception {
        TMCOUR50Service.updateTMCOUR50(TMCOUR50VO);
        status.setComplete();
        return "forward:/TMCOUR50/TMCOUR50List.do";
    }
    
    @RequestMapping("/deleteTMCOUR50.do")
    public String deleteTMCOUR50(
            TMCOUR50VO TMCOUR50VO,
            @ModelAttribute("searchVO") TMCOUR50DefaultVO searchVO, SessionStatus status)
            throws Exception {
        TMCOUR50Service.deleteTMCOUR50(TMCOUR50VO);
        status.setComplete();
        return "forward:/TMCOUR50/TMCOUR50List.do";
    }

}
