package nds.tmm.common.TMCOUR30.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.tmm.common.TMCOUR30.service.TMCOUR30Service;
import nds.tmm.common.TMCOUR30.vo.TMCOUR30DefaultVO;
import nds.tmm.common.TMCOUR30.vo.TMCOUR30VO;

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


/**
 * @Class Name :  TMCOUR30Controller
 *
 * @author MPM TEAM 
 * @since 2017.06.07
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 프로그램(메뉴) 관리 ( TMCOUR30 )
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
@RequestMapping("/tmm/{corpCode}/tmcour30/tmmenuxm")
@SessionAttributes(types=TMCOUR30VO.class)
public class TMCOUR30Controller extends TMMBaseController{
	
	private static final Logger _logger = LoggerFactory.getLogger(TMCOUR30Controller.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "TMCOUR30Service")
    private TMCOUR30Service TMCOUR30Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * tm_menuxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TMCOUR30DefaultVO
	 * @return "/TMCOUR30/TMCOUR30List"
	 * @exception Exception
	 */
    @RequestMapping(value="/{upCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOUR30List(
    		@PathVariable("upCode") String upCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));
    	
    	String searchCondition = req.getParameter("searchCondition");
    	String searchKeyword = req.getParameter("searchKeyword");
    	
    	TMCOUR30VO searchVO = new TMCOUR30VO();
    	searchVO.setUpCode(upCode);
    	
    	searchVO.setPageSize(pageSize);
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setSearchKeyword(searchKeyword);
    	
    	PageSet pageSet = new PageSet();
    	
        List<?> TMCOUR30List = TMCOUR30Service.selectTMCOUR30List(searchVO);
    	
        int totCnt = TMCOUR30Service.selectTMCOUR30ListTotCnt(searchVO);
    	
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(TMCOUR30List);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
	 * tm_menuxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TMCOUR30DefaultVO
	 * @return "/TMCOUR30/TMCOUR30List"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOUR30TreeList(
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );

    	String systemCode = req.getParameter("systemCode");
    	String programYn = req.getParameter("programYn");
    	
    	TMCOUR30VO searchVO = new TMCOUR30VO();
    	searchVO.setSystemCode(systemCode);
    	searchVO.setProgramYn(programYn);
    	
        List<?> TMCOUR30List = TMCOUR30Service.selectTMCOUR30TreeList(searchVO);
    	
        PageSet pageSet = new PageSet();
    	
        if(TMCOUR30List != null)
        	pageSet.setTotalRecordCount(TMCOUR30List.size());
    	pageSet.setResult(TMCOUR30List);
		
    	result.setExtraData(pageSet);
        
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/TMCOUR30/addTMCOUR30View.do")
    public String addTMCOUR30View(
            @ModelAttribute("searchVO") TMCOUR30DefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("TMCOUR30VO", new TMCOUR30VO());
        return "/TMCOUR30/TMCOUR30Register";
    }
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addTMCOUR30(
    		@RequestBody List<TMCOUR30VO> tMCOUR30VOs,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
        
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	MPUserSession sess = getSession(req);
    	
    	if(tMCOUR30VOs != null)
    	{
    		for(TMCOUR30VO vo : tMCOUR30VOs)
        	{
    			if(sess != null)
    			{
            		vo.setCrUser(sess.getUser().getId());
    			}
        	}
    	}
    	
    	int nRet = 0;
        if((nRet = TMCOUR30Service.insertTMCOUR30(tMCOUR30VOs)) < 0)
        {
        	result = new ResultEx( Consts.ResultCode.RC_ERROR );
        	result.setResultCode(nRet);
        }
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/updateTMCOUR30View.do")
    public String updateTMCOUR30View(
            @RequestParam("menuCode") java.lang.String menuCode ,
            @ModelAttribute("searchVO") TMCOUR30DefaultVO searchVO, Model model)
            throws Exception {
        TMCOUR30VO TMCOUR30VO = new TMCOUR30VO();
        TMCOUR30VO.setMenuCode(menuCode);
        // 변수명은 CoC 에 따라 TMCOUR30VO
        model.addAttribute(selectTMCOUR30(TMCOUR30VO, searchVO));
        return "/TMCOUR30/TMCOUR30Register";
    }

    @RequestMapping("/selectTMCOUR30.do")
    public @ModelAttribute("TMCOUR30VO")
    TMCOUR30VO selectTMCOUR30(
            TMCOUR30VO TMCOUR30VO,
            @ModelAttribute("searchVO") TMCOUR30DefaultVO searchVO) throws Exception {
        return TMCOUR30Service.selectTMCOUR30(TMCOUR30VO);
    }

    @RequestMapping("/updateTMCOUR30.do")
    public ResponseEntity<ResultEx> updateTMCOUR30(
    		@RequestBody List<Object> tMCOUR30VOs,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		SessionStatus status)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	TMCOUR30Service.updateTMCOUR30(tMCOUR30VOs);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping("/deleteTMCOUR30.do")
    public ResponseEntity<ResultEx> deleteTMCOUR30(
    		@RequestBody List<Object> tMCOUR30VOs,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		SessionStatus status)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	TMCOUR30Service.deleteTMCOUR30(tMCOUR30VOs);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }

}
