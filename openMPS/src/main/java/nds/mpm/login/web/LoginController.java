package nds.mpm.login.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.web.BaseController;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.login.service.LoginService;
import nds.mpm.login.vo.LoginVO;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.login.vo.UserVO;
import nds.util.SHA256Util;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

/**
 * @Class Name : LoginController.java
 *
 * @author MPM TEAM
 * @since 2017.07.26
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 로그인
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.07.26	 			최초생성 - 로그인 작성자 세션자동 입력 mpstester. 로그인 서비스 임시.
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Controller
public class LoginController extends BaseController {
	
	private static final Logger _logger = LoggerFactory.getLogger(LoginController.class);
	
	//user service
	@Resource(name = "MPMLOGINService")
    private LoginService mPMLOGINService;
	
	@Autowired
	protected CorsFilter		_filter;
    /**
     * 로그인처리
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/ctoken",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> login(
    		@RequestBody UserVO loguser,
    		HttpServletRequest req,
    		HttpServletResponse response) throws Exception {

    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	LoginVO userSearch = new LoginVO();
    	userSearch.setUserCode(loguser.getId());
    	LoginVO user = mPMLOGINService.selectTMCOUR10(userSearch);
    	
    	/**
    	 * login DAO call
    	 * 
    	 * */
    	_logger.debug("encrypt 1111 :: " + new SHA256Util().getEncrypt("1111"));
    	
    	MPUserSession userSession = null;  
    	if(loguser !=null
    			&& loguser.getPwd() != null 
    			&& user.getUserPass() != null
    			&& loguser.getPwd().toUpperCase().equals(user.getUserPass().toUpperCase()))
    	{
    		/***
    		 * session setting
    		 * 
    		 * 
    		*/
    		userSession = new MPUserSession(loguser.getId(),loguser.getName(),null);  
    		UserVO logUser = new UserVO();
    		
    		logUser.setCorpCode(user.getCorpCode());
    		logUser.setId(user.getUserCode());
    		logUser.setName(user.getUserName());
    		logUser.setTeamCode(user.getTeamCode());
    		logUser.setTeamName(user.getTeamName());
        	logUser.setOfceCode(user.getOfceCode());
        	logUser.setOfceName(user.getOfceName());
        	logUser.setDutyCode(user.getDutyCode());
        	logUser.setDeptCode(user.getDeptCode());
        	logUser.setHeadCode(user.getHeadCode());
        	userSession.setUser(logUser);
        	
        	_logger.debug(" user :: " + new ObjectMapper().writeValueAsString(userSession));
        	
        	WebUtils.setSessionAttribute(req, "mpuserSession", userSession);
    		 
    	}
    	else
    	{
    		/***
    		 * login fail
    		 */ 
    		return _filter.makeCORSEntity( new ResultEx( Consts.ResultCode.RC_DENINED ) );
    	}
    	
    	/**
    	 * TEST always pass
    	 
    	MPUserSession userSession = new MPUserSession(loguser.getId(),loguser.getName(),null);      
    	UserVO logUser = userSession.getUser();
    	
    	if(user != null)
    	{
    		logUser.setName(user.getUserName());
    		logUser.setTeamName(user.getTeamName());
        	logUser.setOfceCode(user.getOfceCode());
        	userSession.setUser(logUser);
        	
        	_logger.debug(" user :: " + new ObjectMapper().writeValueAsString(userSession));
    	}
    	else
    	{
    		logUser = new UserVO();
    		logUser.setId("handon");
    		logUser.setName("TESTER");
        	logUser.setOfceCode("50");
        	userSession.setUser(logUser);
    	}
    	
    	WebUtils.setSessionAttribute(req, "mpuserSession", userSession);
    	*/
    	result.setExtraData(userSession);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/rtoken",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> releaseToken(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        WebUtils.setSessionAttribute(request, "mpuserSession", null);
    	
    	return _filter.makeCORSEntity( result );
    }
}
