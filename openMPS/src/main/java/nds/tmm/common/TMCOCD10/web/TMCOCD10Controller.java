package nds.tmm.common.TMCOCD10.web;

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
import nds.tmm.common.TMCOCD10.service.TMCOCD10Service;
import nds.tmm.common.TMCOCD10.vo.TMCOCD10VO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name : TMCOCD10Controller.java
 *
 * @author MPM TEAM
 * @since 2017.06.05
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 공통코드관리( TMCOCD10 )
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.07.17	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/tmm/{corpCode}/tmcocd10/tmcodexm")
public class TMCOCD10Controller extends TMMBaseController {
	
	private static final Logger _logger = LoggerFactory.getLogger(TMCOCD10Controller.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "TMCOCD10Service")
    private TMCOCD10Service TMCOCD10Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * BASE_INFO 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TMCOCD10DefaultVO
	 * @return PageSet
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResultEx> selectTMCOCD10List(
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
    	
    	TMCOCD10VO searchVO = new TMCOCD10VO();
    	searchVO.setCorpCode(corpCode);
    	
    	searchVO.setPageSize(pageSize);
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setSearchCondition(searchCondition);
    	searchVO.setSearchKeyword(searchKeyword);
    	
    	PageSet pageSet = new PageSet();
    	
    	List<?> TMCOCD10List = TMCOCD10Service.selectTMCOCD10List(searchVO);
    	
    	int totalRecordCount = TMCOCD10Service.selectTMCOCD10ListTotCnt(searchVO);
    	
    	pageSet.setTotalRecordCount(totalRecordCount);
    	pageSet.setResult(TMCOCD10List);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultEx> addTMCOCD10(
    		@PathVariable("corpCode") String corpCode,
    		@RequestBody List<TMCOCD10VO> tMCOCD10VOs,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	int nRet = 0;
    	
    	List ilist = new ArrayList();
    	//중복검색
    	if(tMCOCD10VOs != null)
    	{
    		for(TMCOCD10VO vo : tMCOCD10VOs)
        	{
    			vo.setCorpCode(corpCode);
    			if(sess != null)
    			{
    				vo.setCrUser(sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
        if((nRet = TMCOCD10Service.insertTMCOCD10(ilist)) < 0)
        {
        	result = new ResultEx( Consts.ResultCode.RC_ERROR );
        	result.setResultCode(nRet);
        }
        
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/updateTMCOCD10.do",method=RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<ResultEx> updateTMCOCD10(
    		@PathVariable("corpCode") String corpCode,
    		@RequestBody List<Object> tMCOCD10VOs,
    		SessionStatus status)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	TMCOCD10VO tMCOCD10VO = new TMCOCD10VO();
    	
        TMCOCD10Service.updateTMCOCD10(tMCOCD10VOs);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/deleteTMCOCD10.do",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteTMCOCD10(
    		@RequestBody List<Object> tMCOCD10VOs,
            SessionStatus status)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        TMCOCD10Service.deleteTMCOCD10(tMCOCD10VOs);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
}
