package nds.tmm.common.TMCOBD30.vo;

import nds.mpm.common.vo.SearchCommonVO;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TMCOBD30VO extends SearchCommonVO {

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

	private String faqId;
	private String faqTitle;
	private String question;
	private String answer;
	private String corpCode;
	private String fileId;
	private String readCnt;
	private String sortOrder;
	private String deleYn;
	private String mdUser;
	private String mdDate;
	private String crUser;
	private String crDate;
	private String crUserName;
	private String crDate2;
	private String searchMode;

	private String readId;
	private String contId;
	private String bbsCode;
	private String readUser;
	private String readUsnm;
	private String readDate;

	private String isFile;

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

	public String getFaqId() {
		return faqId;
	}

	public void setFaqId(String faqId) {
		this.faqId = faqId;
	}

	public String getFaqTitle() {
		return faqTitle;
	}

	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCorpCode() {
		return corpCode;
	}

	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(String readCnt) {
		this.readCnt = readCnt;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
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

	public String getCrUserName() {
		return crUserName;
	}

	public void setCrUserName(String crUserName) {
		this.crUserName = crUserName;
	}

	public String getCrDate2() {
		return crDate2;
	}

	public void setCrDate2(String crDate2) {
		this.crDate2 = crDate2;
	}

	public String getSearchMode() {
		return searchMode;
	}

	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}

	public String getReadId() {
		return readId;
	}

	public void setReadId(String readId) {
		this.readId = readId;
	}

	public String getContId() {
		return contId;
	}

	public void setContId(String contId) {
		this.contId = contId;
	}

	public String getBbsCode() {
		return bbsCode;
	}

	public void setBbsCode(String bbsCode) {
		this.bbsCode = bbsCode;
	}

	public String getReadUser() {
		return readUser;
	}

	public void setReadUser(String readUser) {
		this.readUser = readUser;
	}

	public String getReadUsnm() {
		return readUsnm;
	}

	public void setReadUsnm(String readUsnm) {
		this.readUsnm = readUsnm;
	}

	public String getReadDate() {
		return readDate;
	}

	public void setReadDate(String readDate) {
		this.readDate = readDate;
	}

	public String getIsFile() {
		return isFile;
	}

	public void setIsFile(String isFile) {
		this.isFile = isFile;
	}

}
