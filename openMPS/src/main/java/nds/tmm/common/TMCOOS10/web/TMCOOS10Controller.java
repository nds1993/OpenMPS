package nds.tmm.common.TMCOOS10.web;

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
import nds.tmm.common.TMCOOS10.service.TMCOOS10Service;
import nds.tmm.common.TMCOOS10.vo.TMCOOS10DefaultVO;

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
 * @Class Name : TMCOOS10Controller.java
 * @Description : TMCOOS10 Controller class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/tmm/TMCOOS10")
public class TMCOOS10Controller extends TMMBaseController {
	
	private static final Logger _logger = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	protected CorsFilter		_filter;	

    @Resource(name = "TMCOOS10Service")
    private TMCOOS10Service TMCOOS10Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * tm_corpxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TMCOOS10DefaultVO
	 * @return "/TMCOOS10/TMCOOS10List"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOOS10List(@ModelAttribute("searchVO") TMCOOS10DefaultVO searchVO, 
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	List<?> TMCOOS10List = TMCOOS10Service.selectTMCOOS10List(searchVO);
    	
    	PageSet pageSet = new PageSet();
        pageSet.setResult(TMCOOS10List);
		result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/code",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectCodeTMCOOS10List(@ModelAttribute("searchVO") TMCOOS10DefaultVO searchVO, 
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	List<?> TMCOOS10List = TMCOOS10Service.selectTMCOOS10List(searchVO);
    	
    	PageSet pageSet = new PageSet();
        pageSet.setResult(TMCOOS10List);
		result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }    
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addTMCOOS10(
    		@RequestBody List<EgovMap> listTMCOOS10VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	
    	if(listTMCOOS10VO != null)
    	{
    		for(EgovMap vo : listTMCOOS10VO)
        	{
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	TMCOOS10Service.insertTMCOOS10(ilist);
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOOS10(
    		@RequestBody EgovMap TMCOOS10VO
            ) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	result.setExtraData(TMCOOS10Service.selectTMCOOS10(TMCOOS10VO));
    	return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public ResponseEntity<ResultEx> updateTMCOOS10(
    		@RequestBody EgovMap TMCOOS10VO,
            SessionStatus status)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        TMCOOS10Service.updateTMCOOS10(TMCOOS10VO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteTMCOOS10(
    		@RequestBody EgovMap TMCOOS10VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOOS10VO.put("crUser",sess.getUser().getId());
    	TMCOOS10VO.put("mdUser",sess.getUser().getId());
    	
        TMCOOS10Service.deleteTMCOOS10(TMCOOS10VO);
        
        return _filter.makeCORSEntity( result );
    }

}
