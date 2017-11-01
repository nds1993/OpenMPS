package nds.tmm.common.TMCOUR60.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.tmm.common.TMCOUR50.vo.TMCOUR50VO;
import nds.tmm.common.TMCOUR60.service.TMCOUR60Service;
import nds.tmm.common.TMCOUR60.vo.TMCOUR60DefaultVO;
import nds.tmm.common.TMCOUR60.vo.TMCOUR60VO;

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
 * @Class Name : TMCOUR60Controller.java
 * @Description : TMCOUR60 Controller class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/tmm/{corpCode}/tmcour60")
@SessionAttributes(types=TMCOUR60VO.class)
public class TMCOUR60Controller {
	
	private static final Logger _logger = LoggerFactory.getLogger(TMCOUR60Controller.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "TMCOUR60Service")
    private TMCOUR60Service TMCOUR60Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * tm_grusrx 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TMCOUR60DefaultVO
	 * @return "/TMCOUR60/TMCOUR60List"
	 * @exception Exception
	 */
    @RequestMapping(value="/tmgrusrx/{groupCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOUR60List(
    		@PathVariable("corpCode") String corpCode,
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
    	
    	TMCOUR60VO searchVO = new TMCOUR60VO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setGroupCode(groupCode);
    	
    	searchVO.setPageSize(pageSize);
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setSearchKeyword(searchKeyword);
    	
    	PageSet pageSet = new PageSet();
    	
    	List<?> TMCOUR60List = TMCOUR60Service.selectTMCOUR60List(searchVO);
    	
    	int totCnt = TMCOUR60Service.selectTMCOUR60ListTotCnt(searchVO);
    	
    	pageSet.setTotalRecordCount(totCnt);
    	pageSet.setResult(TMCOUR60List);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/tmuserxm/{groupCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOUR60UserList_D(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("groupCode") String groupCode,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	TMCOUR60VO searchVO = new TMCOUR60VO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setGroupCode(groupCode);
    	
    	PageSet pageSet = new PageSet();
    	
    	List<?> TMCOUR60List = TMCOUR60Service.selectTMCOUR60UserList_D(searchVO);
    	
    	if(TMCOUR60List != null)
    		pageSet.setTotalRecordCount(TMCOUR60List.size());
    	pageSet.setResult(TMCOUR60List);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/tmgrusrx",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addTMCOUR60(
    		@PathVariable("corpCode") String corpCode,
            @RequestBody List<TMCOUR60VO> tMCOUR60VOs,
            SessionStatus status)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        List ilist = new ArrayList();
    	if(tMCOUR60VOs != null)
    	{
    		for(TMCOUR60VO vo : tMCOUR60VOs)
        	{
    			vo.setCorpCode(corpCode);
        		ilist.add(vo);
        	}
    	}
        TMCOUR60Service.insertTMCOUR60(ilist);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }

}
