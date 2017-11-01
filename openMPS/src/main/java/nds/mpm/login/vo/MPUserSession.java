package nds.mpm.login.vo;



import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 */
public class MPUserSession implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public MPUserSession(String userId, String userName, String pwd) {
    	
    	UserVO usr = new UserVO();
    	usr.setId(userId);
    	usr.setName(userName);
    	usr.setPwd(pwd);
    	
		this.user = usr;
	}
    
    private UserVO user;

    private Map<String, String> auth = new HashMap<String, String>();
    
	public Map<String, String> getAuth() {
		return auth;
	}
	public void setAuth(Map<String, String> auth) {
		this.auth = auth;
	}
	public UserVO getUser() {
		return user;
	}
	public void setUser(UserVO user) {
		this.user = user;
	}

}
