package nds.core.common.common.service;



import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import nds.core.userdep.user.service.UserVO;
import nds.frm.config.StaticConfig;
import nds.frm.util.StringUtil;




/**
 * <b>class : UserSession </b>
 * <b>Class Description</b><br>
 * 로그인 사용자의 세션정보를 관리하는 Class
 * <b>History</b><br>
 * <pre>      : 2013.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public class UserSession implements Serializable {

    private static final long serialVersionUID = 1L;

    public UserSession(UserVO userVO, SkinMstVO skin) {
		this.userVO = userVO;
		this.skinNo           = skin.getSkinNo();

		this.siteName         = skin.getSiteTit();
        this.siteDomain       = skin.getSiteDomn();
        this.siteCompany      = skin.getCoName();
        this.chmnName         = skin.getChmnName();
        this.chmnTel          = skin.getChmnTel();

        this.skinViewFolder   = skin.getPathView();
		this.skinImageFolder  = skin.getPathImge();
		this.skinCSSFolder    = skin.getPathCss();
		
		//ROLE setting
		
		if(null != userVO){
			this.setAuth(userVO.getAuth());	
		}
		
	}
    
    private UserVO userVO;

    private String skinNo           = "";
    
    private String siteName         = "";
    private String siteDomain       = "";
    private String siteCompany      = "";
    private String chmnName         = "";
    private String chmnTel          = "";
    
    private String skinViewFolder   = "";
    private String skinImageFolder  = "";
    private String skinCSSFolder    = "";
    
    private Map<String, String> auth = new HashMap<String, String>();
    
	public UserVO getLogin() {
		return this.userVO;
	}
	public void setLogin(UserVO userVO) {
		this.userVO = userVO;
	}
    public String getSkinNo() {
        return skinNo;
    }

    public void setSkinNo(String skinNo) {
        this.skinNo = skinNo;
    }

    public String getSiteName() {
        if("".equals(StringUtil.null2void(this.siteName))) this.siteName = StaticConfig.APP_TITLE;
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteDomain() {
        if("".equals(StringUtil.null2void(this.siteDomain))) this.siteDomain = StaticConfig.APP_DOMAIN;
        return siteDomain;
    }

    public void setSiteDomain(String siteDomain) {
        this.siteDomain = siteDomain;
    }

    public String getSiteCompany() {
        if("".equals(StringUtil.null2void(this.siteCompany))) this.siteCompany = StaticConfig.APP_COMPANY;
        return siteCompany;
    }

    public void setSiteCompany(String siteCompany) {
        this.siteCompany = siteCompany;
    }

    public String getChmnName() {
        if("".equals(StringUtil.null2void(this.chmnName))) this.chmnName = StaticConfig.APP_CHMN_NAME;
        return chmnName;
    }

    public void setChmnName(String chmnName) {
        this.chmnName = chmnName;
    }

    public String getChmnTel() {
        if("".equals(StringUtil.null2void(this.chmnTel))) this.chmnTel = StaticConfig.APP_CHMN_CALL;
        return chmnTel;
    }

    public void setChmnTel(String chmnTel) {
        this.chmnTel = chmnTel;
    }
    
    public String getSkinViewFolder() {
        if("".equals(StringUtil.null2void(this.skinViewFolder))) this.skinViewFolder = StaticConfig.SKIN_VIEW;
        return skinViewFolder;
    }

    public void setSkinViewFolder(String skinViewFolder) {
        this.skinViewFolder = skinViewFolder;
    }

    public String getSkinImageFolder() {
        if("".equals(StringUtil.null2void(this.skinImageFolder))) this.skinImageFolder = StaticConfig.SKIN_IMAGE;
        return skinImageFolder;
    }

    public void setSkinImageFolder(String skinImageFolder) {
        this.skinImageFolder = skinImageFolder;
    }

    public String getSkinCSSFolder() {
        if("".equals(StringUtil.null2void(this.skinCSSFolder))) this.skinCSSFolder = StaticConfig.SKIN_CSS;
        return skinCSSFolder;
    }

    public void setSkinCSSFolder(String skinCSSFolder) {
        this.skinCSSFolder = skinCSSFolder;
    }
	public Map<String, String> getAuth() {
		return auth;
	}
	public void setAuth(Map<String, String> auth) {
		this.auth = auth;
	}

}
