package nds.frm.listener;

import java.io.Serializable;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.springframework.jdbc.core.JdbcTemplate;

public class LoginManager implements HttpSessionBindingListener, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static LoginManager loginManager = null;

	// 로그인한 접속자를 담기위한 해시테이블
    private static Hashtable<HttpSession, String> loginUsers = new Hashtable<HttpSession, String>();

    // 로그인한 접속자의 IP를 담기위한 해시테이블
    private static Hashtable<String, String> loginUsrIP = new Hashtable<String, String>();

    /*
     * 싱글톤 패턴 사용
     */
    public static synchronized LoginManager getInstance(){
        if(loginManager == null){
            loginManager = new LoginManager();
        }
        return loginManager;
    }
    
    /*
     * 이 메소드는 세션이 연결되을때 호출된다.(session.setAttribute("login", this))
     * Hashtable에 세션과 접속자 아이디를 저장한다.
     */
    public void valueBound(HttpSessionBindingEvent event) {
        String[] element = event.getName().split("[;]");

        loginUsers.put(event.getSession(), element[0]);
        loginUsrIP.put(element[0], element[1]);
        
        LogManager.getRootLogger().info(element[0] + "님이 " + element[1] + " 에서 접속하셨습니다.");
        LogManager.getRootLogger().info("현재 접속자 수 : " +  getUserCount());
     }
        
     /*
      * 이 메소드는 세션이 끊겼을때 호출된다.(invalidate)
      * Hashtable에 저장된 로그인한 정보를 제거해 준다.
      */
     public void valueUnbound(HttpSessionBindingEvent event) {    	     	
    	 String[] element = event.getName().split("[;]");
    	 
    	/*//접속종료 로그 처리
         Context initCtxt;
         JdbcTemplate jt;
         try {
             
            initCtxt = new InitialContext();
            Context envCtxt = (Context)initCtxt.lookup("java:comp/env");
            DataSource ds = (DataSource)envCtxt.lookup("jdbc/voc");
            jt = new JdbcTemplate(ds);
            StringBuffer sql = new StringBuffer(1024);
            sql.append("update CLT_LOGIN_LOG              ");
            sql.append("   set CNCT_END_DD = TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS')  ");
            sql.append("     , CNCT_YN = 'N'     ");
            sql.append("where USER_ID = ?         ");
            sql.append("  AND CNCT_YN = 'Y'      ");
            sql.append("  AND LOGIN_YN = 'Y'     ");
            
            jt.update(sql.toString(), new Object[] {element[0]});

         } catch (Exception e) {
         	//logger.error(e.getMessage());
        	 e.printStackTrace();
         }*/

         loginUsers.remove(event.getSession());
         loginUsrIP.remove(element[0]);

         LogManager.getRootLogger().info("  " + element[0] + "님이 로그아웃 하셨습니다.");
         LogManager.getRootLogger().info("현재 접속자 수 : " +  getUserCount());
     }
        
     /*
      * 입력받은 아이디를 해시테이블에서 삭제. 
      * @param userID 사용자 아이디
      * @return void
      */ 
     public void removeSession(String userId){
         Enumeration e = loginUsers.keys();
         HttpSession session = null;
         while(e.hasMoreElements()){
             session = (HttpSession)e.nextElement();
             if(loginUsers.get(session).equals(userId)){
                 //세션이 invalidate될때 HttpSessionBindingListener를 
                 //구현하는 클레스의 valueUnbound()함수가 호출된다.
                 session.invalidate();
             }
         }
     }
          
     /*
      * 사용자가 입력한 ID, PW가 맞는지 확인하는 메소드
      * @param userID 사용자 아이디
      * @param userPW 사용자 패스워드
      * @return boolean ID/PW가 일치하는 지 여부
      */
     public boolean isValid(String userId, String userPw){
    	 LogManager.getRootLogger().debug("LoginManager database biz logic!!!");
         /*
          * 이부분에 Database 로직이 들어간다.
          */
         return true;
     }

    /*
     * 해당 아이디의 동시 사용을 막기위해서 
     * 이미 사용중인 아이디인지를 확인한다.
     * @param loginKey 로그인키
     * @return boolean 이미 사용 중인 경우 true, 사용중이 아니면 false
     */
    public boolean isUsing(String loginKey){
        String[] element = loginKey.split("[;]");
        return loginUsers.containsValue(element[0]);
    }

    /*
     * 해당 아이디의 동시 사용을 막기위해서 
     * 이미 사용중인 아이디인지를 확인한다.
     * @param loginKey 로그인키
     * @return boolean 이미 사용 중인 경우 true, 사용중이 아니면 false
     */
    public boolean isDuplicateIP(String loginKey){
        String[] element = loginKey.split("[;]");
        String ip = "";
        if(loginUsrIP.containsKey(element[0])) {
            ip = (String) loginUsrIP.get(element[0]);
            if(ip.equals(element[1])) {
                return true;
            }
        }
        return false;
    }

    /*
     * 로그인을 완료한 사용자의 아이디를 세션에 저장하는 메소드
     * @param session 세션 객체
     * @param loginKey 로그인키
     */
    public void setSession(HttpSession session, String loginKey){
        //이순간에 Session Binding이벤트가 일어나는 시점
        //name값으로 loginKey, value값으로 자기자신(HttpSessionBindingListener를 구현하는 Object)
        session.setAttribute(loginKey, this);//login에 자기자신을 집어넣는다.
    }
          
    /*
      * 입력받은 세션Object로 아이디를 리턴한다.
      * @param session : 접속한 사용자의 session Object
      * @return String : 접속자 아이디
     */
    public String getUserID(HttpSession session){
        return (String)loginUsers.get(session);
    }

    /*
     * 입력받은 세션Object로 IP를 리턴한다.
     * @param session : 접속한 사용자의 session Object
     * @return String : 접속자 IP
    */
   public String getUserIP(HttpSession session){
       return (String)loginUsrIP.get(session);
   }
   
    /*
     * 현재 접속한 총 사용자 수
     * @return int  현재 접속자 수
     */
    public int getUserCount(){
        return loginUsers.size();
    }     
     
    /*
     * 현재 접속중인 모든 사용자 아이디를 출력
     * @return void
     */
    public void printloginUsers(){
        Enumeration e = loginUsers.keys();
        HttpSession session = null;
        LogManager.getRootLogger().info("===========================================");
        int i = 0;
        while(e.hasMoreElements()){
            session = (HttpSession)e.nextElement();
            LogManager.getRootLogger().info((++i) + ". 접속자 : " +  loginUsers.get(session));
        }
        LogManager.getRootLogger().info("===========================================");
    }
     
    /*
     * 현재 접속중인 모든 사용자리스트를 리턴
     * @return list
     */
    public Collection getUsers(){
        Collection collection = loginUsers.values();
        return collection;
    }    
}
