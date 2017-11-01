package nds.tmm.common.TMCOOS60.web;

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
import nds.tmm.common.TMCOOS60.vo.TMCOOS60DefaultVO;
import nds.tmm.common.TMCOOS60.service.TMCOOS60Service;
import nds.tmm.common.TMCOOS60.vo.TMCOOS60DefaultVO;
import nds.tmm.common.TMCOOS60.vo.TMCOOS60VO;

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
 * @Class Name : TMCOOS60Controller.java
 * @Description : TMCOOS60 Controller class
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
@RequestMapping("/tmm/TMCOOS60")
public class TMCOOS60Controller extends TMMBaseController {

	private static final Logger _logger = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	protected CorsFilter		_filter;	
	
    @Resource(name = "TMCOOS60Service")
    private TMCOOS60Service TMCOOS60Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * tm_teamxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TMCOOS60DefaultVO
	 * @return "/TMCOOS60/TMCOOS60List"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOOS60List(@ModelAttribute("searchVO") TMCOOS60DefaultVO searchVO,
    		HttpServletRequest req,
    		HttpServletResponse res,    		
    		ModelMap model)
            throws Exception {

    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	String headCode = req.getParameter("headCode");
    	searchVO.setHeadCode(headCode);
		
    	List<?> TMCOOS60List = TMCOOS60Service.selectTMCOOS60List(searchVO);
    	
    	PageSet pageSet = new PageSet();
        pageSet.setResult(TMCOOS60List);
		result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );    	
    	
    } 
    
    @RequestMapping(value="/code",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectCodeTMCOOS60List(@ModelAttribute("searchVO") TMCOOS60DefaultVO searchVO,
    		HttpServletRequest req,
    		HttpServletResponse res,    		
    		ModelMap model)
            throws Exception {

    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	String headCode = req.getParameter("headCode");
    	searchVO.setHeadCode(headCode);
		
    	List<?> TMCOOS60List = TMCOOS60Service.selectTMCOOS60List(searchVO);
    	
    	PageSet pageSet = new PageSet();
        pageSet.setResult(TMCOOS60List);
		result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );    	
    	
    } 
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addTMCOOS60(
    		@RequestBody List<EgovMap> listTMCOOS60VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	
    	if(listTMCOOS60VO != null)
    	{
    		for(EgovMap vo : listTMCOOS60VO)
        	{
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	TMCOOS60Service.insertTMCOOS60(ilist);
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOOS60(
    		@RequestBody EgovMap TMCOOS60VO
            ) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	result.setExtraData(TMCOOS60Service.selectTMCOOS60(TMCOOS60VO));
    	return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public ResponseEntity<ResultEx> updateTMCOOS60(
    		@RequestBody EgovMap TMCOOS60VO,
            SessionStatus status)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        TMCOOS60Service.updateTMCOOS60(TMCOOS60VO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteTMCOOS60(
    		@RequestBody EgovMap TMCOOS60VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOOS60VO.put("crUser",sess.getUser().getId());
    	TMCOOS60VO.put("mdUser",sess.getUser().getId());
    	
        TMCOOS60Service.deleteTMCOOS60(TMCOOS60VO);
        
        return _filter.makeCORSEntity( result );
    }

}