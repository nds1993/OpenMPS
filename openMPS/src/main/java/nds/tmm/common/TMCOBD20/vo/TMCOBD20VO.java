package nds.tmm.common.TMCOBD20.vo;

import nds.mpm.common.vo.SearchCommonVO;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.web.multipart.MultipartFile;

public class TMCOBD20VO extends SearchCommonVO {

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

	private String contId;
	private String bbsCode;
	private String title;
	private String content;
	private String contReyn;
	private String upcontId;
	private String contLv;
	private String sortOrder;
	private String readCnt;
	private String notiYn;
	private String periodYn;
	private String beginDate;
	private String endDate;
	private String secretYn;
	private String contPwd;
	private String fileId;
	private String deleYn;
	private String mdUser;
	private String mdDate;
	private String crUser;
	private String crDate;
	private String contGrp;
	private String reTitle;

	private String crDate2;
	private String crUserName;
	private String searchMode;
	private String codeId;
	private String codeName;
	private String bbsName;
	private String periodBeginEnd;

	private String readId;
	private String readUser;
	private String readUsnm;
	private String readDate;

	private String comtCode;
	private String comment;
	private String crName;

	private String fileBasePath = "/upload/";
	private MultipartFile file;
	private String fileNo;
	private String filePath;
	private String saveFlnm;
	private String originFlnm;
	private String fileExt;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContReyn() {
		return contReyn;
	}

	public void setContReyn(String contReyn) {
		this.contReyn = contReyn;
	}

	public String getUpcontId() {
		return upcontId;
	}

	public void setUpcontId(String upcontId) {
		this.upcontId = upcontId;
	}

	public String getContLv() {
		return contLv;
	}

	public void setContLv(String contLv) {
		this.contLv = contLv;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(String readCnt) {
		this.readCnt = readCnt;
	}

	public String getNotiYn() {
		return notiYn;
	}

	public void setNotiYn(String notiYn) {
		this.notiYn = notiYn;
	}

	public String getPeriodYn() {
		return periodYn;
	}

	public void setPeriodYn(String periodYn) {
		this.periodYn = periodYn;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSecretYn() {
		return secretYn;
	}

	public void setSecretYn(String secretYn) {
		this.secretYn = secretYn;
	}

	public String getContPwd() {
		return contPwd;
	}

	public void setContPwd(String contPwd) {
		this.contPwd = contPwd;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
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

	public String getBbsName() {
		return bbsName;
	}

	public void setBbsName(String bbsName) {
		this.bbsName = bbsName;
	}

	public String getContGrp() {
		return contGrp;
	}

	public void setContGrp(String contGrp) {
		this.contGrp = contGrp;
	}

	public String getReTitle() {
		return reTitle;
	}

	public void setReTitle(String reTitle) {
		this.reTitle = reTitle;
	}

	public String getPeriodBeginEnd() {
		return periodBeginEnd;
	}

	public void setPeriodBeginEnd(String periodBeginEnd) {
		this.periodBeginEnd = periodBeginEnd;
	}

	public String getReadUser() {
		return readUser;
	}

	public void setReadUser(String readUser) {
		this.readUser = readUser;
	}

	public String getReadId() {
		return readId;
	}

	public void setReadId(String readId) {
		this.readId = readId;
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

	public String getComtCode() {
		return comtCode;
	}

	public void setComtCode(String comtCode) {
		this.comtCode = comtCode;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCrName() {
		return crName;
	}

	public void setCrName(String crName) {
		this.crName = crName;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileBasePath() {
		return fileBasePath;
	}

	public void setFileBasePath(String fileBasePath) {
		this.fileBasePath = fileBasePath;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getSaveFlnm() {
		return saveFlnm;
	}

	public void setSaveFlnm(String saveFlnm) {
		this.saveFlnm = saveFlnm;
	}

	public String getOriginFlnm() {
		return originFlnm;
	}

	public void setOriginFlnm(String originFlnm) {
		this.originFlnm = originFlnm;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getIsFile() {
		return isFile;
	}

	public void setIsFile(String isFile) {
		this.isFile = isFile;
	}

}
