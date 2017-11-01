package nds.tmm.common.TMCOUR10.web;

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
import nds.mpm.sales.SD0101.vo.MpSalesmanCustVO;
import nds.tmm.common.TMCOUR10.service.TMCOUR10Service;
import nds.tmm.common.TMCOUR10.vo.TMCOUR10DefaultVO;
import nds.tmm.common.TMCOUR10.vo.TMCOUR10VO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOUR10Controller.java
 * @Description : TMCOUR10 Controller class
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
@RequestMapping("/tmm/{corpCode}/tmcour10")
public class TMCOUR10Controller extends TMMBaseController  {

	private static final Logger _logger = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	protected CorsFilter		_filter;	
	
    @Resource(name = "TMCOUR10Service")
    private TMCOUR10Service TMCOUR10Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * tm_userxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TMCOUR10DefaultVO
	 * @return "/TMCOUR10/TMCOUR10List"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOUR10List(
    	@PathVariable("corpCode") String corpCode,
    	@ModelAttribute("searchVO") TMCOUR10DefaultVO searchVO,
		HttpServletRequest req,
		HttpServletResponse res, 
		ModelMap model)
        throws Exception {

		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
		searchVO.setCorpCode(corpCode);
		
		List<?> TMCOUR10List = TMCOUR10Service.selectTMCOUR10List(searchVO);
    	
    	PageSet pageSet = new PageSet();
        pageSet.setResult(TMCOUR10List);
		result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );    	
    	
    } 
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addTMCOUR10(
    		@RequestBody List<EgovMap> listTMCOUR10VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	
    	if(listTMCOUR10VO != null)
    	{
    		for(EgovMap vo : listTMCOUR10VO)
        	{
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	TMCOUR10Service.insertTMCOUR10(ilist);
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/updateUserPass",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> updateUserPass(
    		@ModelAttribute("TMCOUR10VO") TMCOUR10VO TMCOUR10VO,
    		HttpServletRequest req,
    		HttpServletResponse res,
            SessionStatus status)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOUR10VO.setCrUser(sess.getUser().getId());
    	TMCOUR10VO.setMdUser(sess.getUser().getId());
    	
    	TMCOUR10Service.updateUserPass(TMCOUR10VO);
    	status.setComplete();
    	
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOUR10(
    		@RequestBody EgovMap TMCOUR10VO
            ) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	result.setExtraData(TMCOUR10Service.selectTMCOUR10(TMCOUR10VO));
    	return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public ResponseEntity<ResultEx> updateTMCOUR10(
    		@RequestBody EgovMap TMCOUR10VO,
            SessionStatus status)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        TMCOUR10Service.updateTMCOUR10(TMCOUR10VO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteTMCOUR10(
    		@RequestBody EgovMap TMCOUR10VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOUR10VO.put("crUser",sess.getUser().getId());
    	TMCOUR10VO.put("mdUser",sess.getUser().getId());
    	
    	TMCOUR10Service.deleteTMCOUR10(TMCOUR10VO);
        
        return _filter.makeCORSEntity( result );
    }

}
