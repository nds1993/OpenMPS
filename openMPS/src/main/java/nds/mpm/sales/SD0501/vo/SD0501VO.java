package nds.mpm.sales.SD0501.vo;

import nds.mpm.common.vo.SearchCommonVO;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.web.multipart.MultipartFile;

public class SD0501VO extends SearchCommonVO {

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

	private String servId;
	private String corpCode;
	private String rqstType;
	private String servTitle;
	private String servCont;
	private String fileId;
	private String rqfnDate;
	private String applUser;
	private String procStatus;
	private String procStatusName;
	private String rqstUser;
	private String crDate;
	private String crDate2;
	private String mdDate;
	private String deleYn;

	private String procUser;
	private String procUserName;
	private String procCont;
	private String procDate;
	private String finishDate;

	private String procMode;

	private MultipartFile file;

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

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getServId() {
		return servId;
	}

	public void setServId(String servId) {
		this.servId = servId;
	}

	public String getCorpCode() {
		return corpCode;
	}

	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}

	public String getRqstType() {
		return rqstType;
	}

	public void setRqstType(String rqstType) {
		this.rqstType = rqstType;
	}

	public String getServTitle() {
		return servTitle;
	}

	public void setServTitle(String servTitle) {
		this.servTitle = servTitle;
	}

	public String getServCont() {
		return servCont;
	}

	public void setServCont(String servCont) {
		this.servCont = servCont;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getRqfnDate() {
		return rqfnDate;
	}

	public void setRqfnDate(String rqfnDate) {
		this.rqfnDate = rqfnDate;
	}

	public String getApplUser() {
		return applUser;
	}

	public void setApplUser(String applUser) {
		this.applUser = applUser;
	}

	public String getProcStatus() {
		return procStatus;
	}

	public void setProcStatus(String procStatus) {
		this.procStatus = procStatus;
	}

	public String getRqstUser() {
		return rqstUser;
	}

	public void setRqstUser(String rqstUser) {
		this.rqstUser = rqstUser;
	}

	public String getCrDate() {
		return crDate;
	}

	public void setCrDate(String crDate) {
		this.crDate = crDate;
	}

	public String getMdDate() {
		return mdDate;
	}

	public void setMdDate(String mdDate) {
		this.mdDate = mdDate;
	}

	public String getDeleYn() {
		return deleYn;
	}

	public void setDeleYn(String deleYn) {
		this.deleYn = deleYn;
	}

	public String getCrDate2() {
		return crDate2;
	}

	public void setCrDate2(String crDate2) {
		this.crDate2 = crDate2;
	}

	public String getProcStatusName() {
		return procStatusName;
	}

	public void setProcStatusName(String procStatusName) {
		this.procStatusName = procStatusName;
	}

	public String getProcUser() {
		return procUser;
	}

	public void setProcUser(String procUser) {
		this.procUser = procUser;
	}

	public String getProcCont() {
		return procCont;
	}

	public void setProcCont(String procCont) {
		this.procCont = procCont;
	}

	public String getProcDate() {
		return procDate;
	}

	public void setProcDate(String procDate) {
		this.procDate = procDate;
	}

	public String getProcUserName() {
		return procUserName;
	}

	public void setProcUserName(String procUserName) {
		this.procUserName = procUserName;
	}

	public String getProcMode() {
		return procMode;
	}

	public void setProcMode(String procMode) {
		this.procMode = procMode;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
