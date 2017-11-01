package nds.tmm.common.TMCOCD30.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.tmm.common.TMCOCD10.vo.TMCOCD10VO;
import nds.tmm.common.TMCOCD30.service.TmMesgxmService;
import nds.tmm.common.TMCOCD30.vo.TmMesgxmDefaultVO;
import nds.tmm.common.TMCOCD30.vo.TmMesgxmVO;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Class Name : TmMesgxmController.java
 * @Description : TmMesgxm Controller class
 * @Modification Information
 *
 * @author OpenMPS Team
 * @since 2017. 7. 18
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/tmm/{corpCode}/tmcocd30")
public class TmMesgxmController extends TMMBaseController
{
	@Autowired
	protected CorsFilter _filter;
	
    @Resource(name = "tmMesgxmService")
    private TmMesgxmService tmMesgxmService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * tm_mesgxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TmMesgxmDefaultVO
	 * @return "/tmMesgxm/TmMesgxmList"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTmMesgxmList(HttpServletRequest req)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"1"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"), ""+propertiesService.getInt("pageSize")));
    	
    	TmMesgxmDefaultVO searchVO = new TmMesgxmDefaultVO();
    	
    	/** EgovPropertyService.sample */
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(pageSize);
    	searchVO.setSearchKeyword( req.getParameter("searchKeyword") );
    	
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List<?> tmMesgxmList = tmMesgxmService.selectTmMesgxmList(searchVO);
        int totCnt = tmMesgxmService.selectTmMesgxmListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        
        PageSet pageSet = new PageSet();
        pageSet.setTotalRecordCount( totCnt );
        pageSet.setPageSize( paginationInfo.getPageSize() );
        pageSet.setPageIndex( paginationInfo.getCurrentPageNo() );
        pageSet.setStartOrd( paginationInfo.getFirstRecordIndex() );
    	pageSet.setResult(tmMesgxmList);
		
    	result.setExtraData(pageSet);
        
        return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addTmMesgxm(
    		HttpServletRequest req,
    		@RequestBody List<TmMesgxmVO> tmMesgxmVOs
            )
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	int nRet = 0;
    	
    	List ilist = new ArrayList();
    	//중복검색
    	if(tmMesgxmVOs != null)
    	{
    		for(TmMesgxmVO vo : tmMesgxmVOs)
        	{
    			if(sess != null)
    			{
    				vo.setCrUser(sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	tmMesgxmService.insertTmMesgxm(ilist);
        return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/{mesgCode}",method=RequestMethod.PUT)
    public ResponseEntity<ResultEx> updateTmMesgxm(
    		@PathVariable("mesgCode") String mesgCode,
    		@RequestBody TmMesgxmVO tmMesgxmVO,
            SessionStatus status)
            throws Exception {
    	ResultEx result = null;
    	try
    	{
    		if( mesgCode.equals( tmMesgxmVO.getMesgCode() ) )
    		{
    			tmMesgxmService.updateTmMesgxm(tmMesgxmVO);
	    		result = new ResultEx( Consts.ResultCode.RC_OK );
    		}
    		else
    		{
    			result = new ResultEx( Consts.ResultCode.RC_INVALID_PARAMS );
    		}
    	}
    	catch(Exception e)
    	{
    		result = new ResultEx( Consts.ResultCode.RC_EXCEPTION );
    		result.setExtraData(e.getMessage());
    	}
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{mesgCode}",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteTmMesgxm(
    		@PathVariable("mesgCode") String mesgCode,
    		@RequestBody TmMesgxmVO tmMesgxmVO,
            SessionStatus status)
            throws Exception {
    	ResultEx result = null;
    	try
    	{
    		if( mesgCode.equals( tmMesgxmVO.getMesgCode() ) )
    		{
	    		tmMesgxmService.deleteTmMesgxm(tmMesgxmVO);
	    		result = new ResultEx( Consts.ResultCode.RC_OK );
    		}
    		else
    		{
    			result = new ResultEx( Consts.ResultCode.RC_INVALID_PARAMS );
    		}
    	}
    	catch(Exception e)
    	{
    		result = new ResultEx( Consts.ResultCode.RC_EXCEPTION );
    		result.setExtraData(e.getMessage());
    	}
    	status.setComplete();
        return _filter.makeCORSEntity( result );
    }

}
