package nds.core.common.common.service;


/**
 * <b>class : UserMenu </b>
 * <b>Class Description</b><br>
 * 
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public class UserMenu extends CommonObject {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String contsId;

    private String contsNm;

    private String upContsId;

    private String menuInclYn;

    private String openYn;

    private String imgePath;

    private String imgeOverPath;

    private String urlPath;

    private String hlpPath;

    private String menuOrd;
    
    private String menuLevel;

    private String roleCd;

    private String btnNm;

    private String btnId;
    
    private String imgeCd;
    
    private String id;
    private String depCd;

    private String rootConts;
    private String menuLevelFr;
    private String menuLevelTo;
    
    public String getBtnId() {
        return btnId;
    }
    public void setBtnId(String btnId) {
        this.btnId = btnId;
    }
    public String getBtnNm() {
        return btnNm;
    }
    public void setBtnNm(String btnNm) {
        this.btnNm = btnNm;
    }
    public String getImgeCd() {
		return imgeCd;
	}
	public void setImgeCd(String imgeCd) {
		this.imgeCd = imgeCd;
	}
	public String getContsId() {
        return contsId;
    }
    public void setContsId(String contsId) {
        this.contsId = contsId;
    }
    public String getContsNm() {
        return contsNm;
    }
    public void setContsNm(String contsNm) {
        this.contsNm = contsNm;
    }
    public String getImgePath() {
        return imgePath;
    }
    public void setImgePath(String imgePath) {
        this.imgePath = imgePath;
    }
    public String getMenuInclYn() {
        return menuInclYn;
    }
    public void setMenuInclYn(String menuInclYn) {
        this.menuInclYn = menuInclYn;
    }
    public String getMenuLevel() {
        return menuLevel;
    }
    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel;
    }
    public String getMenuLevelFr() {
        return menuLevelFr;
    }
    public void setMenuLevelFr(String menuLevelFr) {
        this.menuLevelFr = menuLevelFr;
    }
    public String getMenuLevelTo() {
        return menuLevelTo;
    }
    public void setMenuLevelTo(String menuLevelTo) {
        this.menuLevelTo = menuLevelTo;
    }
    public String getMenuOrd() {
        return menuOrd;
    }
    public void setMenuOrd(String menuOrd) {
        this.menuOrd = menuOrd;
    }
    public String getOpenYn() {
        return openYn;
    }
    public void setOpenYn(String openYn) {
        this.openYn = openYn;
    }
    public String getRoleCd() {
        return roleCd;
    }
    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd;
    }
    public String getRootConts() {
        return rootConts;
    }
    public void setRootConts(String rootConts) {
        this.rootConts = rootConts;
    }
    public String getUpContsId() {
        return upContsId;
    }
    public void setUpContsId(String upContsId) {
        this.upContsId = upContsId;
    }
    public String getUrlPath() {
        return urlPath;
    }
    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
    public String getImgeOverPath() {
        return imgeOverPath;
    }
    public void setImgeOverPath(String imgeOverPath) {
        this.imgeOverPath = imgeOverPath;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDepCd() {
        return depCd;
    }
    public void setDepCd(String depCd) {
        this.depCd = depCd;
    }
    public String getHlpPath() {
        return hlpPath;
    }
    public void setHlpPath(String hlpPath) {
        this.hlpPath = hlpPath;
    }




}