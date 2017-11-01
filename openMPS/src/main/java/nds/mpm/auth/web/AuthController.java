package nds.mpm.auth.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.auth.service.AuthService;
import nds.mpm.auth.vo.AuthVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name : UserInfoController.java
 * @Description : UserInfo Controller class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/auth")
public class AuthController extends TMMBaseController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	protected CorsFilter		_filter;

    @Resource(name = "authService")
    private AuthService authService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * 권한 부서 목록
	 * @exception Exception
	 */
    @RequestMapping(value="/dept",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectUserInfoList(
    		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	AuthVO searchVO = new AuthVO();
    	searchVO.setCorpCode(corpCode);
    	
        List<?> deptList = authService.selectDeptList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(deptList != null)
    	pageSet.setTotalRecordCount(deptList.size());
    	pageSet.setResult(deptList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
	 * 권한 부서 목록
	 * @exception Exception
	 */
    @RequestMapping(value="/user",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectUserList(
    		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	String partCode = req.getParameter("partCode");
		
    	AuthVO searchVO = new AuthVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setPartCode(partCode);
    	
        List<?> userList = authService.selectUserList(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(userList != null)
    	pageSet.setTotalRecordCount(userList.size());
    	pageSet.setResult(userList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
	 * 세션 유저 
	 * @exception Exception
	 */
    @RequestMapping(value="/profile",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectUserProfile(
    		@PathVariable("corpCode") String corpCode,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
    	MPUserSession sess = getSession(req);
		
    	result.setExtraData(sess.getUser());
    	
    	return _filter.makeCORSEntity( result );
    } 

}
