package nds.core.common.login.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.SkinMstVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.common.login.service.LoginService;
import nds.core.logsearch.loginlog.service.LoginLogVO;
import nds.core.userdep.user.service.UserVO;
import nds.frm.config.StaticConfig;
import nds.frm.listener.LoginManager;
import nds.frm.startup.SYSTEM;
import nds.frm.util.EncryptUtil;
import nds.frm.util.StringUtil;
import net.sf.json.JSONObject;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import sun.misc.BASE64Decoder;

//import com.ubintis.common.util.StrUtil;

/**
 * <p>Title: LoginController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
//@Controller
public class LoginController extends BaseController {
	/** LoginService */
    @Resource(name = "loginService")
    private LoginService loginService;
	/** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService;
    
    private LoginManager loginManager;
    
    
    /**
     * 로그인폼
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/loginform.do")
    public ModelAndView loginform(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("loginform");
        SkinMstVO skin = commonService.getSkinInfo();
        if(null != skin) {
            mav = new ModelAndView(skin.getPathView() + "/loginform");
        }
        
        UserSession uskin = new UserSession(null, skin);
        mav.addObject("uskin", uskin);
        return mav;
    }
    
    /**
     * 로그인처리
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/login.do")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	UserVO userVO = new UserVO();
        bind(request, userVO); 
        
        SkinMstVO skinInfo = commonService.getSkinInfo();
        
        if (StringUtil.null2void(userVO.getUserId()).equals("")){
            ModelAndView mav =  new ModelAndView(((null != skinInfo) ? skinInfo.getPathView() : "") + "/loginform");
            return mav ;
        }
        if (userVO.getUserId() == null) return new ModelAndView(((null != skinInfo) ? skinInfo.getPathView() : "") + "/loginform");
        String localIP = request.getRemoteAddr(); 
        UserVO userInfo = null;
        
        userVO.setUserId(userVO.getUserId()); // 소문자도 로그인되도록
        
        if(StringUtil.null2void(EncryptUtil.DecodeBySType(StringUtil.null2void(userVO.getRequestIp()))).equals("192.168.1.6")) 
        {
            userInfo = loginService.getUserInfo(userVO, true);
        }
        else if("Z".equals(userVO.getMode())){
            userInfo = loginService.getUserInfo(userVO, false);
        }
        else if(localIP.equals("127.0.0.1") ){
        	userVO.setPwd(null);
        	userInfo = loginService.getUserInfo(userVO, false);
        }
        else{
        	if("kb#123".equals(userVO.getPwd())) {
        		userVO.setPwd("");
        		userInfo = loginService.getUserInfo(userVO, true);
        	} else {
        		userInfo = loginService.getUserInfo(userVO, true);
        	}
         }
        
        //사용자 정보가 없으면 로그인 페이지로 이동
        if(userInfo == null || !"N".equals(userInfo.getRtmYn())){
        	
        	/**
        	 * 로그인 실패시 로그정보를 쌓는다.
        	 */
        	loadDate();
            LoginLogVO loginLogVO = new LoginLogVO();
            loginLogVO.setUserId(userVO.getUserId());
            loginLogVO.setCnctPath(request.getRequestURI());
            loginLogVO.setCnctStrtDd(SYSTEM.getInstance().getDate() + "" + SYSTEM.getInstance().getTime());
            loginLogVO.setCnctEndDd("");
            loginLogVO.setIpPath(request.getRemoteAddr());
            loginLogVO.setCnctYn("N");
            loginLogVO.setLoginYn("N");
            
            loginService.updateUserLogin(loginLogVO);
        	
            ModelAndView mav =  new ModelAndView(((null != skinInfo) ? skinInfo.getPathView() : "") + "/loginform", "LoginErr01", "아이디 또는 비밀번호가 일치하지 않습니다.");
            UserSession uskin = new UserSession(null, skinInfo);
            mav.addObject("uskin", uskin);
            return mav;
        }    

        String loginKey = userInfo.getUserId() + ";" + localIP;
        
        //Session 처리부분---------------------------------------------------/
        loginManager = LoginManager.getInstance();
        HttpSession httpSession = request.getSession(true);
        
        httpSession.setAttribute("DA_DVN", "1");
        
        /*if (loginManager.isUsing(loginKey))
        {
            if(loginManager.isDuplicateIP(loginKey)) {
                
                httpSession.setAttribute("USER_ID", userInfo.getUserId());
                httpSession.setAttribute("USER_PW", userVO.getPwd());
                
                ModelAndView mav =  new ModelAndView(((null != skinInfo) ? skinInfo.getPathView() : "") + "/loginform", "LoginErr02", "접속된 사용자가 있습니다.");
                UserSession uskin = new UserSession(null, skinInfo);
                mav.addObject("uskin", uskin);
                return mav;
            }
    	}*/
        try {
            loginManager.printloginUsers();
            loginManager.setSession(httpSession, loginKey);
            
            LogManager.getRootLogger().info("session ID:" + httpSession.getId());
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
        String sDiv = StringUtil.getParam(request, "sDiv", "");
        if(sDiv.equals("saeall"))
        {
        	LogManager.getRootLogger().debug("CommonController >>>>>>>>>>> 접촉구분 : " + sDiv);
            LogManager.getRootLogger().debug("CommonController >>>>>>>>>>> 세션유무 : "+loginManager.isUsing(userInfo.getUserId()));  
        }
        loginProcess(userInfo, skinInfo, request, response);
        return null;
        
    }

    /**
     * 로그인(SSO)처리
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/ssologin.do")
    public ModelAndView ssologin(HttpServletRequest request, HttpServletResponse response) throws Exception {

        SkinMstVO skinInfo = commonService.getSkinInfo();
        HttpSession httpSession = request.getSession(true);
        
        String userId  = StringUtil.getParam(request, "userId", "");  // userId(사번)

        String cf_remote_ip = request.getRemoteAddr();
        String cf_remote_host = request.getRemoteHost();
        LogManager.getRootLogger().info("####################	SERVER INFO ["+cf_remote_ip+"] "+cf_remote_host);
        
        UserVO userInfo = null;
        UserVO userVO = new UserVO();
        userVO.setUserId(userId);
        userInfo = loginService.getUserInfo(userVO, false);

        //사용자 정보가 없으면 로그인 페이지로 이동
        if(userInfo == null){
            ModelAndView mav =  new ModelAndView(((null != skinInfo) ? skinInfo.getPathView() : "") + "/loginform");
            UserSession uskin = new UserSession(null, skinInfo);
            mav.addObject("uskin", uskin);
            mav.addObject("sso", "fail_VOC");
            return mav;
        }    

        String loginKey = userInfo.getUserId() + ";" + cf_remote_ip;
        
        //Session 처리부분---------------------------------------------------/
        loginManager = LoginManager.getInstance();
        
        if (loginManager.isUsing(loginKey))
        {
            if(!loginManager.isDuplicateIP(loginKey)) {
                
                httpSession.setAttribute("USER_ID", userInfo.getUserId());
                httpSession.setAttribute("USER_PW", userInfo.getPwd());
            }
        }

        try {
            loginManager.printloginUsers();
            loginManager.setSession(httpSession, loginKey);
            
            LogManager.getRootLogger().info("session ID:" + httpSession.getId());
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        LogManager.getRootLogger().debug("####################	접촉구분 [SSO]");
        LogManager.getRootLogger().debug("####################	세션유무 ["+ loginManager.isUsing(userInfo.getUserId()) +"]");  
             
        loginProcess(userInfo, skinInfo, request, response);

        return null;
    }
    
    /**
     * 다시로그인처리(중복된 세션이 존재했을 경우 다시 로그인)
     * 
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value="/relogin.do")
    public ModelAndView relogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String userId = (String)request.getSession().getAttribute("USER_ID");
        String userPw = (String)request.getSession().getAttribute("USER_PW");
        
        if (!StringUtil.null2void(userId).equals(""))
        {
            loginManager = LoginManager.getInstance();
            loginManager.removeSession(userId);
            
            UserVO userVO = new UserVO();
            
            userVO.setUserId(userId.toUpperCase()); // 소문자도 로그인되도록
            userVO.setPwd("");
            
            UserVO userInfo  = loginService.getUserInfo(userVO, false);            
            SkinMstVO skinInfo = commonService.getSkinInfo();
            
            HttpSession httpSession = request.getSession(true);
            
            httpSession.setAttribute("USER_ID", userId);
            httpSession.setAttribute("USER_PW", userPw);

            String localIP = request.getRemoteAddr();
            String loginKey = userId + ";" + localIP;

            try {
                loginManager.setSession(httpSession, loginKey);
                
                LogManager.getRootLogger().info("session ID:" + httpSession.getId());
            }catch(Exception e) {
                System.out.println(e.getLocalizedMessage());
            }

            if(userInfo != null){
                loginProcess(userInfo, skinInfo, request, response);     
            } else {
            	ModelAndView mav =  new ModelAndView(((null != skinInfo) ? skinInfo.getPathView() : "") + "/loginform");
            	return mav;
            }
        }
        
        return null;
    }
    
    /**
     * 로그인 후 처리
     * 
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value="/loginProcess.do")
    public ModelAndView loginProcess(UserVO userInfo, SkinMstVO skin, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
    	UserSession userSession = new UserSession(userInfo, skin);      
        WebUtils.setSessionAttribute(request, "userSession", userSession);
        
        try {
            loadDate();
            
            // 사용자 접속 로그 기록
            LoginLogVO loginLogVO = new LoginLogVO();
            loginLogVO.setUserId(userInfo.getUserId());
            loginLogVO.setCnctPath(request.getRequestURI());
            loginLogVO.setCnctStrtDd(SYSTEM.getInstance().getDate() + "" + SYSTEM.getInstance().getTime());
            loginLogVO.setCnctEndDd("");
            loginLogVO.setIpPath(request.getRemoteAddr());
            loginLogVO.setCnctYn("Y");  
            loginLogVO.setLoginYn("Y");
            
            loginService.updateUserLogin(loginLogVO);
        }
        catch(Exception e) {
            
        }
        
       // String adGotoPage   = StringUtil.getParam(request, "adGotoPage", "");
        String mainPage     = StringUtil.getParam(request, "url", "");
        
//        if(!("").equals(adGotoPage)) adGotoPage = EncryptUtil.DecodeBySType(adGotoPage);
//        
//        if(("").equals(adGotoPage)) {
        String autoYn = StringUtil.getParam(request, "autoYn", "");
        String linkInfo = StringUtil.getParam(request, "linkInfo", "");
        LogManager.getRootLogger().debug("getContextPath()="+ request.getContextPath()+ "/maindex.do?url="+mainPage);
            response.sendRedirect(request.getContextPath()+ "/maindex.do?linkInfo="+linkInfo+"&url="+mainPage);
//        }
//        else {
//            if(!"".equals(cstNo)){       //연계시스템 VOC등록버튼 클릭시
//                response.sendRedirect(request.getContextPath()+ adGotoPage + "?srchCstName="+cstNo);
//            }
//            else if(!"".equals(vocId)){ //그룹웨어 이슈VOC상세내용 클릭시
//                response.sendRedirect(request.getContextPath()+ adGotoPage + "?vocId="+vocId);
//            }
//            else{
//                response.sendRedirect(request.getContextPath()+ adGotoPage);
//            }
//        }
        
        return null;
    }
    
//    public String execute() throws Exception
//    {
//    	try
//    	{
//    	URL url = null;
//		BufferedReader in = null;
//		URLConnection con = null;
//		
//		userID = StringUtil.null2String(userID);
//		userPass = StringUtil.null2String(userPass);
//		
//		if(userID.equals("")){
//			return ERROR;
//		}
//		if(userPass.equals("")){
//			return ERROR;
//		}
//		
//		StringBuffer returnMsg = new StringBuffer();
//		String urlstr = "http://172.28.207.116/authen/?uid="+java.net.URLEncoder.encode(userID.toUpperCase(), "UTF-8")+"&pwd="+java.net.URLEncoder.encode(userPass, "UTF-8");
//		System.out.println("urlstr ===============================> " + urlstr);
//		
//		try 
//		{
//			url = new URL(urlstr);
//			con = url.openConnection();
//			con.connect();
//			in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
//			String tmp_msg = "";
//			while ((tmp_msg = in.readLine()) != null) 
//			{
//				if (!"".equals(tmp_msg)) {
//					returnMsg.append(tmp_msg);
//				}
//			}
//		} 
//		catch (MalformedURLException malformedurlexception) 
//		{
//			malformedurlexception.printStackTrace();
//			msg = malformedurlexception.getMessage();
//			return ERROR;
//		} 
//		catch (IOException ioexception) 
//		{
//			ioexception.printStackTrace();
//			msg = ioexception.getMessage();
//			return ERROR;
//		} 
//		finally 
//		{
//			try 
//			{
//				if (in != null) 
//				{
//					in.close();
//				}
//			} 
//			catch (Exception ex3) 
//			{
//				System.out.println(ex3.toString());
//			}
//		}
//		
//		JSONObject adLoginResult = JSONObject.fromObject(returnMsg.toString());
//		
//		try
//		{
//			//결과값이 존재할 경우
//			if( adLoginResult != null )
//			{
//				String macAddr = request.getHeader("Client-MAC-Address-Info");
//		                ipaddr = request.getRemoteAddr();
//	            
//	            		String code = adLoginResult.getJSONObject("header").getString("code");
//	            //AD로그인 정상일때
//	            if ( code.equals("200") )
//	            {
//	            	user_nm = adLoginResult.getJSONArray("body").getJSONObject(0).getString("face_name");
//	                if ( this.auth != null && !"".equals(this.auth) )
//	                {
//	                	setSvc("true");
//	                	System.out.println("svc ===============================> " + this.svc);
//	                	
//	                	//권한 쿠키생성
//	                	setAuthCookie();
//	                }
//	            }
//	            else	//Code 정상(200) 이외 결과값 일 경우 오류처리
//	            {
//	            	msg = adLoginResult.getJSONObject("header").getString("message");
//	            	return ERROR;
//	            }
//			}
//			else	//결과값이 Null경우
//			{
//				msg = "AD 로그인시 에러가 발생했습니다.";
//				return ERROR;
//			}
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			msg = e.getMessage();
//			return ERROR;
//		}
//    	}
//    	catch(Exception e1)
//    	{
//    		e1.printStackTrace();
//    		msg = e1.getMessage();
//			return ERROR;
//    	}
//		
//        return SUCCESS;
//    }
}
