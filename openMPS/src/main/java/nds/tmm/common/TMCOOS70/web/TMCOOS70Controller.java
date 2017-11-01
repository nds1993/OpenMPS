package nds.tmm.common.TMCOOS70.web;

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
import nds.tmm.common.TMCOOS70.service.TMCOOS70Service;
import nds.tmm.common.TMCOOS70.vo.TMCOOS70DefaultVO;

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
 * @Class Name : TMCOOS70Controller.java
 * @Description : TMCOOS70 Controller class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/tmm/TMCOOS70")
public class TMCOOS70Controller extends TMMBaseController {

	private static final Logger _logger = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	protected CorsFilter		_filter;	
	
    @Resource(name = "TMCOOS70Service")
    private TMCOOS70Service TMCOOS70Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * tm_deptxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TMCOOS70DefaultVO
	 * @return "/TMCOOS70/TMCOOS70List"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOOS70List(@ModelAttribute("searchVO") TMCOOS70DefaultVO searchVO,
    		HttpServletRequest req,
    		HttpServletResponse res,    		
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	List<?> TMCOOS70List = TMCOOS70Service.selectTMCOOS70List(searchVO);
    	
    	PageSet pageSet = new PageSet();
        pageSet.setResult(TMCOOS70List);
		result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );

    } 
    
    @RequestMapping(value="/code",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectCodeTMCOOS70List(@ModelAttribute("searchVO") TMCOOS70DefaultVO searchVO,
    		HttpServletRequest req,
    		HttpServletResponse res,    		
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	List<?> TMCOOS70List = TMCOOS70Service.selectTMCOOS70List(searchVO);
    	
    	PageSet pageSet = new PageSet();
        pageSet.setResult(TMCOOS70List);
		result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );

    } 
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addTMCOOS70(
    		@RequestBody List<EgovMap> listTMCOOS70VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	
    	if(listTMCOOS70VO != null)
    	{
    		for(EgovMap vo : listTMCOOS70VO)
        	{
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	TMCOOS70Service.insertTMCOOS70(ilist);
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOOS70(
    		@RequestBody EgovMap TMCOOS70VO
            ) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	result.setExtraData(TMCOOS70Service.selectTMCOOS70(TMCOOS70VO));
    	return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public ResponseEntity<ResultEx> updateTMCOOS70(
    		@RequestBody EgovMap TMCOOS70VO,
            SessionStatus status)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        TMCOOS70Service.updateTMCOOS70(TMCOOS70VO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteTMCOOS70(
    		@RequestBody EgovMap TMCOOS70VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOOS70VO.put("crUser",sess.getUser().getId());
    	TMCOOS70VO.put("mdUser",sess.getUser().getId());
    	
        TMCOOS70Service.deleteTMCOOS70(TMCOOS70VO);
        
        return _filter.makeCORSEntity( result );
    }

}
