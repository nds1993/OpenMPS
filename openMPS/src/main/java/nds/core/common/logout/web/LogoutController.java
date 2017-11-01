package nds.core.common.logout.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.common.login.service.LoginService;
import nds.core.common.logout.service.LogoutService;
import nds.core.logsearch.loginlog.service.LoginLogVO;
import nds.frm.config.StaticConfig;
import nds.frm.listener.LoginManager;
import nds.frm.startup.SYSTEM;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: LogoutController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Controller
public class LogoutController extends BaseController {
	/** logoutService */
    @Resource(name = "loginService")
    private LoginService loginService;
    @Resource(name = "logoutService")
    private LogoutService logoutService;
    
    private LoginManager loginManager;
    
    /**
     * 로그오프처리
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/logoff.do")
    public ModelAndView logoff(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try
        {
            loadDate();
            
            UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");             
            
            if (null != userSession)
            {
            	try {
	                /**
	                 * 사용자 정보 업데이트 (접속여부(N),최근접속일자)
	                 */
	                String userCnctLogNo = (String)request.getSession().getAttribute("userCnctLogNo");
	
	                LoginLogVO loginLogVO = new LoginLogVO();
	                loginLogVO.setUserId(userSession.getLogin().getUserId());
	                loginLogVO.setCnctLogNo(userCnctLogNo);
	                loginLogVO.setCnctEndDd(SYSTEM.getInstance().getDate() + "" + SYSTEM.getInstance().getTime());
	                loginLogVO.setCnctYn("N");
	                logoutService.updateUserLogout(loginLogVO);
            	}
            	catch(Exception e) {
            		System.out.println("logoff.Exception");
            	}
            }
            else {
                response.sendRedirect(request.getContextPath()+ "/loginform.do");
            }

            if(null != loginManager) {
                loginManager.removeSession(userSession.getLogin().getUserId());
                request.getSession().removeAttribute("userSession");
                request.getSession().removeAttribute("USER_ID");
                request.getSession().removeAttribute("USER_PW");        
                // 예외로그를 위한 세션 삭제
                request.getSession().removeAttribute("session_pid");
                request.getSession().removeAttribute("session_bid");
                request.getSession().invalidate();
            } else {
            	request.getSession().removeAttribute("userSession");
                request.getSession().removeAttribute("USER_ID");
                request.getSession().removeAttribute("USER_PW");        
                // 예외로그를 위한 세션 삭제
                request.getSession().removeAttribute("session_pid");
                request.getSession().removeAttribute("session_bid");
                request.getSession().invalidate();
            }
            
            if(StaticConfig.PRODUCT_MODE.equals("debug")){
                response.sendRedirect(request.getContextPath()+ "/loginform.do?");
            }
            else{
//                response.sendRedirect("/?out=Y");
            	response.sendRedirect(request.getContextPath()+ "/loginform.do?out=Y");
            }
            
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        return null;
    }

}
