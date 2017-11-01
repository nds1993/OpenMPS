package nds.tmm.common.TMCOOS60.vo;

import java.io.Serializable;

import nds.mpm.common.vo.SearchCommonVO;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @Class Name : TMCOOS60DefaultVO.java
 * @Description : TMCOOS60 Default VO class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.06
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */
public class TMCOOS60DefaultVO extends SearchCommonVO {

	private static final long serialVersionUID = 1L;

	/** 팀코드 */
	private String searchTeamCode = "";

	/** 팀명 */
	private String searchTeamName = "";

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

	public String getSearchTeamCode() {
		return searchTeamCode;
	}

	public void setSearchTeamCode(String searchTeamCode) {
		this.searchTeamCode = searchTeamCode;
	}

	public String getSearchTeamName() {
		return searchTeamName;
	}

	public void setSearchTeamName(String searchTeamName) {
		this.searchTeamName = searchTeamName;
	}

}
