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
import nds.core.userdep.user.service.UserVO;
import nds.frm.exception.UnknownUserException;
import nds.frm.startup.SYSTEM;
import nds.frm.util.EncryptUtil;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;


/**
 * <p>Title: LoginInterceptor</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Controller
public class RestInterceptor extends HandlerInterceptorAdapter {

   
	/** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService;
    
    @SuppressWarnings("rawtypes")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        if (userSession == null) {

            boolean inspection = false;
            if(inspection) {
                //TODO 개발자 웹표준 검증
                UserVO userVO = new UserVO();
                userVO.setUserId("dev00");
                userVO.setPwd("!@#");
                
                SkinMstVO skinInfo = commonService.getSkinInfo();
                UserVO userInfo = commonService.getUserInfo(userVO, true);
                if(userInfo == null){
                    return false;
                }
                
                userSession = new UserSession(userInfo, skinInfo);

                WebUtils.setSessionAttribute(request, "userSession", userSession);
            }
            else {
            	throw new UnknownUserException("세션정보가 없습니다.");
            }
        }

        loadDate();

        String userId = userSession.getLogin().getUserId();
        if (!EncryptUtil.DecodeBySType(StringUtil.getParam(request, "authType", "")).equals("byPass")) {

    		String contsId = ""; 
        	if(null != request.getPathInfo()) {
        		contsId = new AntPathMatcher().extractUriTemplateVariables("/{contsId}/{restId}/**", request.getPathInfo()).get("contsId"); 
        	}
            // 세션정보에 권한정보가 존재하면 재이용한다.
            List list = (List) WebUtils.getSessionAttribute(request, userId + contsId);
        	if (list == null) {
                UserMenu key = new UserMenu();
                key.setRootConts("voc");
                key.setId(userId);
                key.setDepCd(userSession.getLogin().getDepCd());
                key.setContsId(contsId);
                list = commonService.selectMenuInfo(key);
                request.getSession(true).setAttribute(userId + contsId, list);
            }

            if (!(list != null && list.size() > 0)) {
            	//throw new AccessDeniedException("접근 권한이 없습니다.");
            }
        }
        
        return true;

    }

    @Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    	//System.out.println("RestInterceptor postHandle : --------------------------------------------------------------------------");
    	//modelAndView.addObject("success", true);
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        if (userSession != null) {

            String userId = userSession.getLogin().getUserId();
        	String contsId = ""; 
			String restId  = ""; 
	    	if(null != request.getPathInfo()) {
	    		contsId = new AntPathMatcher().extractUriTemplateVariables("/{contsId}/{restId}/**", request.getPathInfo()).get("contsId"); 
	    		restId = new AntPathMatcher().extractUriTemplateVariables("/{contsId}/{restId}/**", request.getPathInfo()).get("restId"); 
	    	}
	        String btnId   = StringUtil.getParam(request, "bid", "검색");
			//System.out.println("RestInterceptor contsId : [ " + contsId + " ], restId : [ " + restId + " ], btnId : [ " + btnId + " ]   " + "PathInfo : " + request.getPathInfo());
	    	
	        // TODO 삭제 부분 
	        if(!"".equals(contsId) && !"".equals(restId)) {
	            // 거래로그 기록
//	            SysUseLogVO log = new SysUseLogVO();
//	            log.setContsId(contsId);
//	            log.setBtnId(btnId);
//	            log.setUseCntn(contsId+" "+btnId);
//	            log.setIpPath(request.getRemoteAddr());
//	            log.setRegUser(userSession.getLogin().getUserId());
//	            
//	            commonService.insertSysUseLog(log);
	        }
	        
            // 세션정보에 권한정보가 존재하면 재이용한다.
            List list = (List) WebUtils.getSessionAttribute(request, userId + contsId);
        	if (list == null) {
        		//System.out.println("없음");
                
                UserMenu key = new UserMenu();
                key.setRootConts("voc");
                key.setId(userId);
                key.setDepCd(userSession.getLogin().getDepCd());
                key.setContsId(contsId);
                list = commonService.selectMenuInfo(key);
                request.getSession(true).setAttribute(userId + contsId, list);

            }

            // 권한체크를 한다.
            Authority authority = new Authority();
            List<String> roleList = new ArrayList<String>();

            if (list != null && list.size() > 0) {
                UserMenu menu = (UserMenu) list.get(0);

                String[] roles = menu.getRoleCd().split("[;]");

                for (int i = 0; i < roles.length; i++) {
                    roleList.add(roles[i]);
                    if (!authority.getROLE_CD().containsKey(roles[i])) {
                        authority.getROLE_CD().put(roles[i], "Y");
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
                //System.out.println("Authority  : " + authority);
               // System.out.println("contsId  : " + contsId);
            }
            
        }
    	
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