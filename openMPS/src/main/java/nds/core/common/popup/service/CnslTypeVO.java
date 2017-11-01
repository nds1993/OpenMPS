package nds.core.common.popup.service;

public class CnslTypeVO {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String orderByClause;
    /**
     * PageNavigator : start Page
     */
    private int startNo;

    /**
     * PageNavigator : end Page
     */
    private int endNo;

    /**
     * 상담유형 searchVO & resultVO
     */
    private String lcls;          // searchVO

    private String mcls;          // searchVO

    private String scls;

    private String userId;         // searchVO
    
    private String schMode;
    
    private String schCnslCatNm;  // searchVO
    
    private String schType;
    
    private String lclsNm;
    
    private String mclsNm;
    
    private String sclsNm;
    
    private String depCd;

    private String useYn;         // searchVO

    private String regUser;

    private String regDttm;

    private String updtUser;

    private String updtDttm;
    
    private String needsType;

    private String cnslCatNm;
    
    private String sidx;
    
    private String sord;

    
	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getLcls() {
		return lcls;
	}

	public void setLcls(String lcls) {
		this.lcls = lcls;
	}

	public String getMcls() {
		return mcls;
	}

	public void setMcls(String mcls) {
		this.mcls = mcls;
	}

	public String getScls() {
		return scls;
	}

	public void setScls(String scls) {
		this.scls = scls;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}

	public String getSchMode() {
		return schMode;
	}

	public void setSchMode(String schMode) {
		this.schMode = schMode;
	}

	public String getSchCnslCatNm() {
		return schCnslCatNm;
	}

	public void setSchCnslCatNm(String schCnslCatNm) {
		this.schCnslCatNm = schCnslCatNm;
	}

	public String getSchType() {
		return schType;
	}

	public void setSchType(String schType) {
		this.schType = schType;
	}

	public String getLclsNm() {
		return lclsNm;
	}

	public void setLclsNm(String lclsNm) {
		this.lclsNm = lclsNm;
	}

	public String getMclsNm() {
		return mclsNm;
	}

	public void setMclsNm(String mclsNm) {
		this.mclsNm = mclsNm;
	}

	public String getSclsNm() {
		return sclsNm;
	}

	public void setSclsNm(String sclsNm) {
		this.sclsNm = sclsNm;
	}

	public String getDepCd() {
		return depCd;
	}

	public void setDepCd(String depCd) {
		this.depCd = depCd;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getRegUser() {
		return regUser;
	}

	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}

	public String getRegDttm() {
		return regDttm;
	}

	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}

	public String getUpdtUser() {
		return updtUser;
	}

	public void setUpdtUser(String updtUser) {
		this.updtUser = updtUser;
	}

	public String getUpdtDttm() {
		return updtDttm;
	}

	public void setUpdtDttm(String updtDttm) {
		this.updtDttm = updtDttm;
	}

	public String getNeedsType() {
		return needsType;
	}

	public void setNeedsType(String needsType) {
		this.needsType = needsType;
	}

	public String getCnslCatNm() {
		return cnslCatNm;
	}

	public void setCnslCatNm(String cnslCatNm) {
		this.cnslCatNm = cnslCatNm;
	}
    
}
