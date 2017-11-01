package nds.tmm.common.TMCOOS50.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.user.web.UserInfoController;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.prod.PP0101.vo.TmPlatxmVO;
import nds.tmm.common.TMCOOS50.vo.TMCOOS50DefaultVO;
import nds.tmm.common.TMCOOS50.service.TMCOOS50Service;
import nds.tmm.common.TMCOOS50.vo.TMCOOS50DefaultVO;
import nds.tmm.common.TMCOOS50.vo.TMCOOS50VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOOS50Controller.java
 * @Description : TMCOOS50 Controller class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.06
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/tmm/TMCOOS50")
public class TMCOOS50Controller extends TMMBaseController {

	private static final Logger _logger = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	protected CorsFilter		_filter;		
	
    @Resource(name = "TMCOOS50Service")
    private TMCOOS50Service TMCOOS50Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * tm_orguxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TMCOOS50DefaultVO
	 * @return "/TMCOOS50/TMCOOS50List"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx>  selectTMCOOS50List(@ModelAttribute("searchVO") TMCOOS50DefaultVO searchVO,
    		HttpServletRequest req,
    		HttpServletResponse res,    		
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	List<?> TMCOOS50List = TMCOOS50Service.selectTMCOOS50List(searchVO);
    	
    	PageSet pageSet = new PageSet();
        pageSet.setResult(TMCOOS50List);
		result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    	
    } 
    
    @RequestMapping(value="/code",method=RequestMethod.GET)
    public ResponseEntity<ResultEx>  selectCodeTMCOOS50List(@ModelAttribute("searchVO") TMCOOS50DefaultVO searchVO,
    		HttpServletRequest req,
    		HttpServletResponse res,    		
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	List<?> TMCOOS50List = TMCOOS50Service.selectTMCOOS50List(searchVO);
    	
    	PageSet pageSet = new PageSet();
        pageSet.setResult(TMCOOS50List);
		result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addTMCOOS50(
    		@RequestBody List<EgovMap> listTMCOOS50VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	
    	if(listTMCOOS50VO != null)
    	{
    		for(EgovMap vo : listTMCOOS50VO)
        	{
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	TMCOOS50Service.insertTMCOOS50(ilist);
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/dup",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> checkDupTMCOOS50(
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	String corpCode = req.getParameter("corpCode");
    	String headCode = req.getParameter("headCode");
        
    	TMCOOS50DefaultVO searchVO = new TMCOOS50DefaultVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setHeadCode(headCode);
    	
    	result.setExtraData((TMCOOS50Service.checkDupTMCOOS50_S(searchVO) > 0 ? "Y":"N"));
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOOS50(
    		@RequestBody EgovMap TMCOOS50VO
            ) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	result.setExtraData(TMCOOS50Service.selectTMCOOS50(TMCOOS50VO));
    	return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public ResponseEntity<ResultEx> updateTMCOOS50(
    		@RequestBody EgovMap TMCOOS50VO,
            SessionStatus status)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        TMCOOS50Service.updateTMCOOS50(TMCOOS50VO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteTMCOOS50(
    		@RequestBody EgovMap TMCOOS50VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOOS50VO.put("crUser",sess.getUser().getId());
    	TMCOOS50VO.put("mdUser",sess.getUser().getId());
    	
        TMCOOS50Service.deleteTMCOOS50(TMCOOS50VO);
        
        return _filter.makeCORSEntity( result );
    }

}
