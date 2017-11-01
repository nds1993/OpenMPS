package nds.tmm.common.TMCOOS20.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.user.web.UserInfoController;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.tmm.common.TMCOOS10.vo.TMCOOS10VO;
import nds.tmm.common.TMCOOS20.service.TMCOOS20Service;
import nds.tmm.common.TMCOOS20.vo.TMCOOS20DefaultVO;
import nds.tmm.common.TMCOOS20.vo.TMCOOS20VO;

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
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name : TMCOOS20Controller.java
 * @Description : TMCOOS20 Controller class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/TMCOOS20")
public class TMCOOS20Controller {

	private static final Logger _logger = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	protected CorsFilter		_filter;	

	@Resource(name = "TMCOOS20Service")
    private TMCOOS20Service TMCOOS20Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * tm_platxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TMCOOS20DefaultVO
	 * @return "/TMCOOS20/TMCOOS20List"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOOS20List(@ModelAttribute("searchVO") TMCOOS20DefaultVO searchVO,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	List<?> TMCOOS20List = TMCOOS20Service.selectTMCOOS20List(searchVO);
    	
    	PageSet pageSet = new PageSet();
        pageSet.setResult(TMCOOS20List);
		result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    	
    } 
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addTMCOOS20(
    		@RequestBody TMCOOS20VO TMCOOS20VO,
            SessionStatus status)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	TMCOOS20Service.insertTMCOOS20(TMCOOS20VO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOOS20(
    		@RequestBody TMCOOS20VO TMCOOS20VO
            ) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	result.setExtraData(TMCOOS20Service.selectTMCOOS20(TMCOOS20VO));
    	return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public ResponseEntity<ResultEx> updateTMCOOS20(
    		@RequestBody TMCOOS20VO TMCOOS20VO,
            SessionStatus status)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        TMCOOS20Service.updateTMCOOS20(TMCOOS20VO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteTMCOOS20(
    		@RequestBody TMCOOS20VO TMCOOS20VO,
            SessionStatus status)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        TMCOOS20Service.deleteTMCOOS20(TMCOOS20VO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }

}
