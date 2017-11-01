package nds.tmm.common.TMCOBD10.vo;

import nds.mpm.common.vo.SearchCommonVO;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TMCOBD10VO extends SearchCommonVO {

	private static final long serialVersionUID = 1L;

	/** 검색조건 */
	private String searchCondition = "";

	/** 검색Keyword */
	private String searchKeyword = "";

	/** 검색사용여부 */
	private String searchUseYn = "";

	/** 현재페이지 */
	private int pageIndex = 1;

	/** 페이지갯수 */
	private int pageUnit = 10;

	/** 페이지사이즈 */
	private int pageSize = 10;

	/** firstIndex */
	private int firstIndex = 1;

	/** lastIndex */
	private int lastIndex = 1;

	/** recordCountPerPage */
	private int recordCountPerPage = 10;

	private String bbsCode;
	private String bbsName;
	private String corpCode;
	private String bbsMemo;
	private String writeAuth;
	private String replyYn;
	private String commentYn;
	private String attachYn;
	private String attachCnt;
	private String useYn;
	private String deleYn;
	private String mdUser;
	private String mdDate;
	private String crUser;
	private String crDate;
	private String crDate2;
	private String crUserName;
	private String searchMode;
	private String codeId;
	private String codeName;

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getSearchUseYn() {
		return searchUseYn;
	}

	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public String getBbsCode() {
		return bbsCode;
	}

	public void setBbsCode(String bbsCode) {
		this.bbsCode = bbsCode;
	}

	public String getBbsName() {
		return bbsName;
	}

	public void setBbsName(String bbsName) {
		this.bbsName = bbsName;
	}

	public String getCorpCode() {
		return corpCode;
	}

	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}

	public String getBbsMemo() {
		return bbsMemo;
	}

	public void setBbsMemo(String bbsMemo) {
		this.bbsMemo = bbsMemo;
	}

	public String getWriteAuth() {
		return writeAuth;
	}

	public void setWriteAuth(String writeAuth) {
		this.writeAuth = writeAuth;
	}

	public String getReplyYn() {
		return replyYn;
	}

	public void setReplyYn(String replyYn) {
		this.replyYn = replyYn;
	}

	public String getCommentYn() {
		return commentYn;
	}

	public void setCommentYn(String commentYn) {
		this.commentYn = commentYn;
	}

	public String getAttachYn() {
		return attachYn;
	}

	public void setAttachYn(String attachYn) {
		this.attachYn = attachYn;
	}

	public String getAttachCnt() {
		return attachCnt;
	}

	public void setAttachCnt(String attachCnt) {
		this.attachCnt = attachCnt;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getDeleYn() {
		return deleYn;
	}

	public void setDeleYn(String deleYn) {
		this.deleYn = deleYn;
	}

	public String getMdUser() {
		return mdUser;
	}

	public void setMdUser(String mdUser) {
		this.mdUser = mdUser;
	}

	public String getMdDate() {
		return mdDate;
	}

	public void setMdDate(String mdDate) {
		this.mdDate = mdDate;
	}

	public String getCrUser() {
		return crUser;
	}

	public void setCrUser(String crUser) {
		this.crUser = crUser;
	}

	public String getCrDate() {
		return crDate;
	}

	public void setCrDate(String crDate) {
		this.crDate = crDate;
	}

	public String getCrDate2() {
		return crDate2;
	}

	public void setCrDate2(String crDate2) {
		this.crDate2 = crDate2;
	}

	public String getCrUserName() {
		return crUserName;
	}

	public void setCrUserName(String crUserName) {
		this.crUserName = crUserName;
	}

	public String getSearchMode() {
		return searchMode;
	}

	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

}
