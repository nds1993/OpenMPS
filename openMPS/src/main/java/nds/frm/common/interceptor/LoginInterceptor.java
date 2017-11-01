package nds.frm.common.interceptor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.Authority;
import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.SkinMstVO;
import nds.core.common.common.service.UserMenu;
import nds.core.common.common.service.UserSession;
import nds.core.logsearch.systemlog.service.SystemLogService;
import nds.core.logsearch.systemlog.service.SystemLogVO;
import nds.core.userdep.user.service.UserVO;
import nds.frm.startup.SYSTEM;
import nds.frm.util.EncryptUtil;
import nds.frm.util.StringUtil;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;


/**
 * <p>Title: LoginInterceptor</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.18 초기작성(김태혼)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Controller
public class LoginInterceptor extends HandlerInterceptorAdapter {

   
	/** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService;
    
    /** SystemLogService */
    @Resource(name = "systemLogService")
    private SystemLogService  systemLogService ;
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String urlPath = request.getServletPath().replaceFirst("/WEB-INF/jsp", "").replaceFirst(".do", "");
        String urlExtn = urlPath.substring(urlPath.lastIndexOf(".") + 1);

        
        String strTmpUrl = request.getServletPath();
        
        String contsId = "";
        @SuppressWarnings("unused")
        String btnId   = "";
        if(strTmpUrl.equals("/rest")){
        	String urlRest = request.getRequestURI();
        	contsId = urlRest.substring(0,urlRest.lastIndexOf("/")).replace("/rest", "");
        	contsId = contsId.substring(contsId.lastIndexOf("/")+1);
        	btnId = StringUtil.getParam(request, "bid", "검색");
        }
        int intLoc0 = strTmpUrl.lastIndexOf(".");
        int intLoc1 = strTmpUrl.lastIndexOf("/");
        if(-1 != intLoc0) {
            contsId = strTmpUrl.substring(intLoc1 + 1, intLoc0);
            btnId = StringUtil.getParam(request, "bid", "검색");
        }
        
        // 예외로그를 위한 컨텐츠, 버튼명 세션생성
        WebUtils.setSessionAttribute(request, "session_pid", contsId);
        WebUtils.setSessionAttribute(request, "session_bid", btnId);


        loadDate();

        String url   = request.getServletPath();
        String param = request.getQueryString();

        String nextURL = "";
        String redirectURL = "";
        if (param != null) {
            nextURL = EncryptUtil.EncodeBySType(url + "?" + param);
        } else {
            nextURL = EncryptUtil.EncodeBySType(url);
        }
        
        if ("xml".equals(urlExtn)) {
            redirectURL = request.getContextPath() + "/loginform.do?adGotoPage=" + nextURL + "&stat=1";
        } 
        else {
            redirectURL = request.getContextPath() + "/loginform.do?adGotoPage=" + nextURL + "&stat=1";
        }

        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        
        if (userSession == null) {

            //TODO 개발자 웹표준 검증
            boolean inspection = false;

            if(inspection) {
                UserVO userVO = new UserVO();
                userVO.setUserId("dev00");
                userVO.setPwd("!@#");
                
                SkinMstVO skinInfo = commonService.getSkinInfo();
                UserVO userInfo = commonService.getUserInfo(userVO, true);
                if(userInfo == null){
                    response.sendRedirect(redirectURL);
                    return false;
                }
                
                userSession = new UserSession(userInfo, skinInfo);      
                WebUtils.setSessionAttribute(request, "userSession", userSession);
            }
            else {
                response.sendRedirect(redirectURL);
                return false;
            }
        }

        String userId = userSession.getLogin().getUserId();
        if(!"".equals(contsId) && !"xml".equals(urlExtn) && !"text".equals(urlExtn)) {
			//시스템 기록
			/*SystemLogVO log = new SystemLogVO();
			log.setContsId(contsId);
			log.setBtnId(btnId);
			log.setUseCntn(contsId+" "+ btnId);
			log.setIpPath(request.getRemoteAddr());
			log.setRegUser(userSession.getLogin().getUserNm());
			systemLogService.useLogWrite(log);*/
        	
        }
        if (!EncryptUtil.DecodeBySType(StringUtil.getParam(request, "authType", "")).equals("byPass")) {
            request.getSession(true).setAttribute("__cert_page", redirectURL);

            // 권한체크를 한다.
            Authority authority = new Authority();
            List<String> roleList = new ArrayList<String>();

            //System.out.println("Check Authority : " + urlPath);

            //TODO 권한변경시 메뉴권한 세션자료 삭제처리
             /*
             Enumeration enumer = request.getSession().getAttributeNames();
             while(enumer.hasMoreElements()){
             String obj = (String) enumer.nextElement();
             if(obj.indexOf(userId) > -1) {
             System.out.println(obj);
             }
             }           
             */

            // 세션정보에 권한정보가 존재하면 재이용한다.
            String URL_PATH = request.getServletPath().replaceFirst("/WEB-INF/jsp", "");
            if(strTmpUrl.equals("/rest")){
            	String urlRest = request.getRequestURI();
            	URL_PATH = urlRest.replace("/rest","");
            	URL_PATH = URL_PATH.substring(0, URL_PATH.lastIndexOf("/"))+URL_PATH.substring(URL_PATH.lastIndexOf("."));
            	LogManager.getRootLogger().debug(URL_PATH);
            }
            List list = (List) WebUtils.getSessionAttribute(request, userId + URL_PATH);
            
          //  System.out.println("ContsID SJ TEST:" + contsId);
           // System.out.println("URL_PATH SJ TEST :" + URL_PATH);
            if (list == null) {
                UserMenu key = new UserMenu();
                key.setRootConts("voc");
                key.setId(userId);
                key.setDepCd(userSession.getLogin().getDepCd());
                key.setContsId(contsId);
                key.setUrlPath(URL_PATH);
                list = commonService.selectMenuInfo(key);
                request.getSession(true).setAttribute(userId + URL_PATH, list);
            }

            if (list != null && list.size() > 0) {
                UserMenu menu = (UserMenu) list.get(0);

                if(menu.getRoleCd() != null){
                	String[] roles = menu.getRoleCd().split("[;]");
                	
                	for (int i = 0; i < roles.length; i++) {
                		roleList.add(roles[i]);
                		if (!authority.getROLE_CD().containsKey(roles[i])) {
                			authority.getROLE_CD().put(roles[i], "Y");
                		}
                	}
                }
                authority.setROLE_LIST(roleList);

                String[] buttonNames = StringUtil.null2void(menu.getBtnNm()).split("[;]");
                for (int i = 0; i < buttonNames.length; i++) {
                    if (!authority.getBUTTON_NM().containsKey(buttonNames[i])) {
                        authority.getBUTTON_NM().put(buttonNames[i], "Y");
//                        System.out.println("========["+ buttonNames[i] + "]==========");
                    }
                }

                String[] imageCodes = StringUtil.null2void(menu.getImgeCd()).split("[|]");
                for (int i = 0; i < imageCodes.length; i++) {
                    if (!authority.getBUTTON_CD().containsKey(buttonNames[i])) {
                        authority.getBUTTON_CD().put(buttonNames[i], imageCodes[i]);
//                        System.out.println("========["+ imageCodes[i] + "]==========");
                    }
                }
                
                String[] buttonIds = StringUtil.null2void(menu.getBtnId()).split("[;]");
                for (int i = 0; i < buttonIds.length; i++) {
                    if (!authority.getBUTTON_ID().containsKey(buttonNames[i])) {
                        authority.getBUTTON_ID().put(buttonNames[i], buttonIds[i]);
                    }
                }
                request.getSession().setAttribute(contsId, authority);
                //System.out.println("Authority  contsId: " + contsId);

            } else {
                //TODO 권한없음 페이지
                //System.out.println("Authority Not Found: " + urlPath);
            	//if(contsId.equals("crmindex")){
	            //	ModelAndView mav = new ModelAndView("authError");
	            //	throw new ModelAndViewDefiningException(mav);
            	//}
            }
        } else {
            //System.out.println("byPass Authority : " + urlPath);
        }
        
        return true;

    }

    public void loadDate() {
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = SYSTEM.getInstance().getConnection();

            String strQuery = "";
            //ORACLE 일경우
            //strQuery = strQuery + " SELECT TO_CHAR(SYSDATE,'YYYYMMDD'), TO_CHAR(SYSDATE,'YYYY'), TO_CHAR(SYSDATE,'MM'), TO_CHAR(SYSDATE,'DD'), TO_CHAR(SYSDATE,'HH24MISS'), TO_CHAR(SYSDATE,'AM'), TO_CHAR(SYSDATE,'HH12'), TO_CHAR(SYSDATE,'MI'), TO_CHAR(SYSDATE,'SS') ";
            //strQuery = strQuery + " , CASE TO_CHAR( SYSDATE, 'D') WHEN '1' THEN '일' WHEN '2' THEN '월' WHEN '3' THEN '화' WHEN '4' THEN '수' WHEN '5' THEN '목' WHEN '6' THEN '금' WHEN '7' THEN '토' END CASE, TO_CHAR(SYSDATE,'HH24') ";
            //strQuery = strQuery + " FROM DUAL ";
            
            //POSTGRESQL 일경우
            strQuery = strQuery + " SELECT TO_CHAR(NOW(),'YYYYMMDD'), TO_CHAR(NOW(),'YYYY'), TO_CHAR(NOW(),'MM'), TO_CHAR(NOW(),'DD'), TO_CHAR(NOW(),'HH24MISS'), TO_CHAR(NOW(),'AM'), TO_CHAR(NOW(),'HH12'), TO_CHAR(NOW(),'MI'), TO_CHAR(NOW(),'SS') ";
            strQuery = strQuery + " , CASE TO_CHAR(NOW(), 'D') WHEN '1' THEN '일' WHEN '2' THEN '월' WHEN '3' THEN '화' WHEN '4' THEN '수' WHEN '5' THEN '목' WHEN '6' THEN '금' WHEN '7' THEN '토' END, TO_CHAR(NOW(),'HH24') ";
            
            //System.out.println(">>>>>>>>  \r\n"+strQuery);
            
            stmt = conn.createStatement();
            stmt.execute(strQuery);
            ResultSet rs = stmt.getResultSet();

            if (rs.next()) {
                SYSTEM system = SYSTEM.getInstance();
                system.setDate(rs.getString(1));
                system.setYear(rs.getString(2));
                system.setMonth(rs.getString(3));
                system.setDay(rs.getString(4));
                system.setTime(rs.getString(5));
                system.setAmpm(rs.getString(6));
                system.setHour(rs.getString(7));
                system.setMinute(rs.getString(8));
                system.setSecond(rs.getString(9));
                system.setYoil(rs.getString(10));
                system.setHour24(rs.getString(11));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (null != stmt)
                    stmt.close();
                if (null != conn)
                    conn.close();
                conn = null;
            } catch (Exception e) {
            }
        }
    }
}