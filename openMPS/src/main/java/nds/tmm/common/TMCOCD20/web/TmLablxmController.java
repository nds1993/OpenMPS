package nds.tmm.common.TMCOCD20.web;

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
import nds.tmm.common.TMCOCD20.service.TmLablxmService;
import nds.tmm.common.TMCOCD20.vo.TmLablxmDefaultVO;
import nds.tmm.common.TMCOCD20.vo.TmLablxmVO;

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
 * @Class Name : TmLablxmController.java
 * @Description : TmLablxm Controller class
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
@RequestMapping("/tmm/{corpCode}/tmcocd20")
public class TmLablxmController extends TMMBaseController 
{
	@Autowired
	protected CorsFilter		_filter;
	
    @Resource(name = "tmLablxmService")
    private TmLablxmService tmLablxmService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * tm_lablxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TmLablxmDefaultVO
	 * @return "/tmLablxm/TmLablxmList"
	 * @exception Exception
	 */
    @RequestMapping(value="/{menuCode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTmLablxmList(
    		@PathVariable("menuCode") String menuCode,
    		HttpServletRequest req)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"1"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"10"));

    	TmLablxmDefaultVO searchVO = new TmLablxmDefaultVO();
    	
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(pageSize);
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		searchVO.setMenuCode(menuCode);
		
        List<?> tmLablxmList = tmLablxmService.selectTmLablxmList(searchVO);
        int totCnt = tmLablxmService.selectTmLablxmListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        
        PageSet pageSet = new PageSet();
        pageSet.setTotalRecordCount( totCnt );
        pageSet.setPageSize( paginationInfo.getPageSize() );
        pageSet.setPageIndex( paginationInfo.getCurrentPageNo() );
        pageSet.setStartOrd( paginationInfo.getFirstRecordIndex() );
    	pageSet.setResult(tmLablxmList);
		
    	result.setExtraData(pageSet);
        
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addTmLablxm(
    		HttpServletRequest req,
    		@RequestBody List<TmLablxmVO> tmLablxmVOs)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	int nRet = 0;
    	
    	List ilist = new ArrayList();
    	//중복검색
    	if(tmLablxmVOs != null)
    	{
    		for(TmLablxmVO vo : tmLablxmVOs)
        	{
    			if(sess != null)
    			{
    				vo.setCrUser(sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
        tmLablxmService.insertTmLablxm(tmLablxmVOs);
        
        return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/{labelCode}",method=RequestMethod.PUT)
    public ResponseEntity<ResultEx> updateTmLablxm(
    		@PathVariable("labelCode") String labelCode,
    		@RequestBody TmLablxmVO tmLablxmVO,
            SessionStatus status)
            throws Exception 
    {
    	if( labelCode.equals( tmLablxmVO.getLabelCode() ) == false )
    	{
    		return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_INVALID_PARAMS ) );
    	}
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        tmLablxmService.updateTmLablxm(tmLablxmVO);
        status.setComplete();
        
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{labelCode}",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteTmLablxm(
    		@PathVariable("labelCode") String labelCode,
    		@RequestBody TmLablxmVO tmLablxmVO,
            SessionStatus status)
            throws Exception 
    {
    	if( labelCode.equals( tmLablxmVO.getLabelCode() ) == false )
    	{
    		return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_INVALID_PARAMS ) );
    	}
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        tmLablxmService.deleteTmLablxm(tmLablxmVO);
        status.setComplete();
        
        return _filter.makeCORSEntity( result );
    }
}
