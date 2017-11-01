package nds.core.common.common.service;

import java.util.ArrayList;
import java.util.List;

public class GwApprovalVO {
	private String sdProtID;			//createDoc(고정값)
	private String sdUserID;			//기안자 사번
	private String sdUserName;			//기안자 성명
	private String sdJobID;				//업무절차ID
	private String sdDocID;				//문서번호
	private String sdXmlDocument;		//XML문서
	private String sdCreateDate;		//등록일자
	private String sdModiDate;			//수정일자
	private String sdCreateSystem;		//생성시스템(Source 시스템코드)
	private String sdCreateServer;		//생성서버(Source 서버)
	private String sdDeptCode;			//부서코드
	private String sdDocSubject;		//결재문서 제목
	private String sdDestinationSystem;	//SmartFlow XF(전송대상시스템)
	private String sdDocStatus;			//결재상태
	private String sdHtmlDocument;		//결재양식 html
	private String sdIntKey;			//전처리 데이타 키값
	//private String sdFileSeq;			//파일Seq
	//private String sdFileID;			//파일ID
	//private String sdFilePath;			//파일경로
	private int sdFileCnt;			//파일갯수
	
	private String [] sdFileSeq;			//파일Seq
	private String [] sdFileID;			//파일ID
	private String [] sdFilePath;			//파일경로
	private String sdGwServer;			// 그룹웨어 서버
	private String sdGwPort;			// 그룹웨어 포트;
	private String sdGwStartUrl;		// 그룹웨어 시작(서블릿)URL;
	private String sdGwOpenUrl;			// 그룹웨어 팝업URL;

	private String subject;
	private String documentType;
	private String documentStatus;
	private String documentID;
	private String work_item;
	private String curr_seqNum;
	private String curr_actorType;
	private String curr_subject;
	private String curr_id;
	private String curr_name;
	private String curr_deptID;
	private String curr_deptName;
	private String curr_parentOrgID;
	private String curr_parentOrgName;
	private String curr_rank;
	private String curr_role;
	private String curr_caste;
	private String curr_kind;
	private String curr_notProcessReason;
	private String curr_status;
	private String curr_processedTime;
	
	private String seq;
	private String name;
	private String url;
	private List<Type> list = new ArrayList<Type>();

	private String acepno;
	private String statCd;
	
	/* <seqNum>0</seqNum>
	 * <actorType>100</actorType>
	 * <person>
	 * 	<id>302238</id>
	 * 	<name>김추곤</name>
	 *	<deptID>5050</deptID>
	 *	<deptName>시스템운영팀</deptName>
	 *	<parentOrgID>smartflow</parentOrgID>
	 *	<parentOrgName>서강대학교</parentOrgName>
	 *	<rank>차장</rank>
	 *	<role>직원</role>
	 *	<caste>직원</caste>
	 * </person>
	 * <kind>기안</kind>
	 * <notProcessReason>
	 * </notProcessReason>
	 * <status>기안</status>
	 * <arrivedTime>2007/11/14 15:18:12</arrivedTime>
	 * <readTime>
	 * </readTime>
	 * <processedTime>2007/11/14 15:18:12</processedTime>
	 * <comment></comment>
	*/
	
	public String[] getSdFileSeq() {
		return sdFileSeq;
	}
	public void setSdFileSeq(String[] sdFileSeq) {
		this.sdFileSeq = sdFileSeq;
	}
	public String[] getSdFileID() {
		return sdFileID;
	}
	public void setSdFileID(String[] sdFileID) {
		this.sdFileID = sdFileID;
	}
	/**
	 * @return the sdJobID
	 */
	public String getSdJobID() {
		return sdJobID;
	}
	/**
	 * @param sdJobID the sdJobID to set
	 */
	public void setSdJobID(String sdJobID) {
		this.sdJobID = sdJobID;
	}
	/**
	 * @return the sdDocID
	 */
	public String getSdDocID() {
		return sdDocID;
	}
	/**
	 * @param sdDocID the sdDocID to set
	 */
	public void setSdDocID(String sdDocID) {
		this.sdDocID = sdDocID;
	}
	/**
	 * @return the sdXmlDocument
	 */
	public String getSdXmlDocument() {
		return sdXmlDocument;
	}
	/**
	 * @param sdXmlDocument the sdXmlDocument to set
	 */
	public void setSdXmlDocument(String sdXmlDocument) {
		this.sdXmlDocument = sdXmlDocument;
	}
	/**
	 * @return the sdCreateDate
	 */
	public String getSdCreateDate() {
		return sdCreateDate;
	}
	/**
	 * @param sdCreateDate the sdCreateDate to set
	 */
	public void setSdCreateDate(String sdCreateDate) {
		this.sdCreateDate = sdCreateDate;
	}
	/**
	 * @return the sdModiDate
	 */
	public String getSdModiDate() {
		return sdModiDate;
	}
	/**
	 * @param sdModiDate the sdModiDate to set
	 */
	public void setSdModiDate(String sdModiDate) {
		this.sdModiDate = sdModiDate;
	}
	/**
	 * @return the sdCreateSystem
	 */
	public String getSdCreateSystem() {
		return sdCreateSystem;
	}
	/**
	 * @param sdCreateSystem the sdCreateSystem to set
	 */
	public void setSdCreateSystem(String sdCreateSystem) {
		this.sdCreateSystem = sdCreateSystem;
	}
	/**
	 * @return the sdCreateServer
	 */
	public String getSdCreateServer() {
		return sdCreateServer;
	}
	/**
	 * @param sdCreateServer the sdCreateServer to set
	 */
	public void setSdCreateServer(String sdCreateServer) {
		this.sdCreateServer = sdCreateServer;
	}
	/**
	 * @return the sdDeptCode
	 */
	public String getSdDeptCode() {
		return sdDeptCode;
	}
	/**
	 * @param sdDeptCode the sdDeptCode to set
	 */
	public void setSdDeptCode(String sdDeptCode) {
		this.sdDeptCode = sdDeptCode;
	}
	/**
	 * @return the sdDocSubject
	 */
	public String getSdDocSubject() {
		return sdDocSubject;
	}
	/**
	 * @param sdDocSubject the sdDocSubject to set
	 */
	public void setSdDocSubject(String sdDocSubject) {
		this.sdDocSubject = sdDocSubject;
	}
	/**
	 * @return the sdDestinationSystem
	 */
	public String getSdDestinationSystem() {
		return sdDestinationSystem;
	}
	/**
	 * @param sdDestinationSystem the sdDestinationSystem to set
	 */
	public void setSdDestinationSystem(String sdDestinationSystem) {
		this.sdDestinationSystem = sdDestinationSystem;
	}
	/**
	 * @return the sdDocStatus
	 */
	public String getSdDocStatus() {
		return sdDocStatus;
	}
	/**
	 * @param sdDocStatus the sdDocStatus to set
	 */
	public void setSdDocStatus(String sdDocStatus) {
		this.sdDocStatus = sdDocStatus;
	}
	/**
	 * @return the sdHtmlDocument
	 */
	public String getSdHtmlDocument() {
		return sdHtmlDocument;
	}
	/**
	 * @param sdHtmlDocument the sdHtmlDocument to set
	 */
	public void setSdHtmlDocument(String sdHtmlDocument) {
		this.sdHtmlDocument = sdHtmlDocument;
	}
	/**
	 * @return the sdGwServer
	 */
	public String getSdGwServer() {
		return sdGwServer;
	}
	/**
	 * @param sdGwServer the sdGwServer to set
	 */
	public void setSdGwServer(String sdGwServer) {
		this.sdGwServer = sdGwServer;
	}
	/**
	 * @return the sdGwPort
	 */
	public String getSdGwPort() {
		return sdGwPort;
	}
	/**
	 * @param sdGwPort the sdGwPort to set
	 */
	public void setSdGwPort(String sdGwPort) {
		this.sdGwPort = sdGwPort;
	}
	/**
	 * @return the sdGwStartUrl
	 */
	public String getSdGwStartUrl() {
		return sdGwStartUrl;
	}
	/**
	 * @param sdGwStartUrl the sdGwStartUrl to set
	 */
	public void setSdGwStartUrl(String sdGwStartUrl) {
		this.sdGwStartUrl = sdGwStartUrl;
	}
	/**
	 * @return the sdGwOpenUrl
	 */
	public String getSdGwOpenUrl() {
		return sdGwOpenUrl;
	}
	/**
	 * @param sdGwOpenUrl the sdGwOpenUrl to set
	 */
	public void setSdGwOpenUrl(String sdGwOpenUrl) {
		this.sdGwOpenUrl = sdGwOpenUrl;
	}

	/**
	 * @return the documentType
	 */
	public String getDocumentType() {
		return documentType;
	}
	/**
	 * @param documentType the documentType to set
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	/**
	 * @return the documentStatus
	 */
	public String getDocumentStatus() {
		return documentStatus;
	}
	/**
	 * @param documentStatus the documentStatus to set
	 */
	public void setDocumentStatus(String documentStatus) {
		this.documentStatus = documentStatus;
	}
	/**
	 * @return the documentID
	 */
	public String getDocumentID() {
		return documentID;
	}
	/**
	 * @param documentID the documentID to set
	 */
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}
	/**
	 * @return the work_item
	 */
	public String getWork_item() {
		return work_item;
	}
	/**
	 * @param work_item the work_item to set
	 */
	public void setWork_item(String work_item) {
		this.work_item = work_item;
	}
	/**
	 * @return the curr_actorType
	 */
	public String getCurr_actorType() {
		return curr_actorType;
	}
	/**
	 * @param curr_actorType the curr_actorType to set
	 */
	public void setCurr_actorType(String curr_actorType) {
		this.curr_actorType = curr_actorType;
	}
	/**
	 * @return the curr_id
	 */
	public String getCurr_id() {
		return curr_id;
	}
	/**
	 * @param curr_id the curr_id to set
	 */
	public void setCurr_id(String curr_id) {
		this.curr_id = curr_id;
	}
	/**
	 * @return the curr_name
	 */
	public String getCurr_name() {
		return curr_name;
	}
	/**
	 * @param curr_name the curr_name to set
	 */
	public void setCurr_name(String curr_name) {
		this.curr_name = curr_name;
	}
	/**
	 * @return the curr_deptID
	 */
	public String getCurr_deptID() {
		return curr_deptID;
	}
	/**
	 * @param curr_deptID the curr_deptID to set
	 */
	public void setCurr_deptID(String curr_deptID) {
		this.curr_deptID = curr_deptID;
	}
	/**
	 * @return the curr_deptName
	 */
	public String getCurr_deptName() {
		return curr_deptName;
	}
	/**
	 * @param curr_deptName the curr_deptName to set
	 */
	public void setCurr_deptName(String curr_deptName) {
		this.curr_deptName = curr_deptName;
	}
	/**
	 * @return the curr_parentOrgID
	 */
	public String getCurr_parentOrgID() {
		return curr_parentOrgID;
	}
	/**
	 * @param curr_parentOrgID the curr_parentOrgID to set
	 */
	public void setCurr_parentOrgID(String curr_parentOrgID) {
		this.curr_parentOrgID = curr_parentOrgID;
	}
	/**
	 * @return the curr_parentOrgName
	 */
	public String getCurr_parentOrgName() {
		return curr_parentOrgName;
	}
	/**
	 * @param curr_parentOrgName the curr_parentOrgName to set
	 */
	public void setCurr_parentOrgName(String curr_parentOrgName) {
		this.curr_parentOrgName = curr_parentOrgName;
	}
	/**
	 * @return the curr_rank
	 */
	public String getCurr_rank() {
		return curr_rank;
	}
	/**
	 * @param curr_rank the curr_rank to set
	 */
	public void setCurr_rank(String curr_rank) {
		this.curr_rank = curr_rank;
	}
	/**
	 * @return the curr_role
	 */
	public String getCurr_role() {
		return curr_role;
	}
	/**
	 * @param curr_role the curr_role to set
	 */
	public void setCurr_role(String curr_role) {
		this.curr_role = curr_role;
	}
	/**
	 * @return the curr_caste
	 */
	public String getCurr_caste() {
		return curr_caste;
	}
	/**
	 * @param curr_caste the curr_caste to set
	 */
	public void setCurr_caste(String curr_caste) {
		this.curr_caste = curr_caste;
	}
	/**
	 * @return the curr_kind
	 */
	public String getCurr_kind() {
		return curr_kind;
	}
	/**
	 * @param curr_kind the curr_kind to set
	 */
	public void setCurr_kind(String curr_kind) {
		this.curr_kind = curr_kind;
	}
	/**
	 * @return the curr_notProcessReason
	 */
	public String getCurr_notProcessReason() {
		return curr_notProcessReason;
	}
	/**
	 * @param curr_notProcessReason the curr_notProcessReason to set
	 */
	public void setCurr_notProcessReason(String curr_notProcessReason) {
		this.curr_notProcessReason = curr_notProcessReason;
	}
	/**
	 * @return the curr_status
	 */
	public String getCurr_status() {
		return curr_status;
	}
	/**
	 * @param curr_status the curr_status to set
	 */
	public void setCurr_status(String curr_status) {
		this.curr_status = curr_status;
	}
	/**
	 * @return the curr_processedTime
	 */
	public String getCurr_processedTime() {
		return curr_processedTime;
	}
	/**
	 * @param curr_processedTime the curr_processedTime to set
	 */
	public void setCurr_processedTime(String curr_processedTime) {
		this.curr_processedTime = curr_processedTime;
	}
	/**
	 * @return the sdProtID
	 */
	public String getSdProtID() {
		return sdProtID;
	}
	/**
	 * @param sdProtID the sdProtID to set
	 */
	public void setSdProtID(String sdProtID) {
		this.sdProtID = sdProtID;
	}
	/**
	 * @return the sdUserID
	 */
	public String getSdUserID() {
		return sdUserID;
	}
	/**
	 * @param sdUserID the sdUserID to set
	 */
	public void setSdUserID(String sdUserID) {
		this.sdUserID = sdUserID;
	}
	/**
	 * @return the sdUserName
	 */
	public String getSdUserName() {
		return sdUserName;
	}
	/**
	 * @param sdUserName the sdUserName to set
	 */
	public void setSdUserName(String sdUserName) {
		this.sdUserName = sdUserName;
	}
	
	
	/**
	 * @return the sdIntKey
	 */
	public String getSdIntKey() {
		return sdIntKey;
	}
	/**
	 * @param sdIntKey the sdIntKey to set
	 */
	public void setSdIntKey(String sdIntKey) {
		this.sdIntKey = sdIntKey;
	}
	/**
	 * @return the seq
	 */
	public String getSeq() {
		return seq;
	}
	/**
	 * @param seq the seq to set
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the list
	 */
	
	/**
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List getList() {
		return list;
	}
	
	public void setList(String sKey, String sValue) {
		list.add(new Type(sKey, sValue, "") );
	}
	/*
	public List<Type> getList() {
		return list;
	}

	public void setList(List<Type> list) {
		this.list = list;
	}
	*/
	/**
	 * @return the curr_subject
	 */
	public String getCurr_subject() {
		return curr_subject;
	}
	/**
	 * @param curr_subject the curr_subject to set
	 */
	public void setCurr_subject(String curr_subject) {
		this.curr_subject = curr_subject;
	}
	/**
	 * @return the curr_seqNum
	 */
	public String getCurr_seqNum() {
		return curr_seqNum;
	}
	/**
	 * @param curr_seqNum the curr_seqNum to set
	 */
	public void setCurr_seqNum(String curr_seqNum) {
		this.curr_seqNum = curr_seqNum;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<Type> list) {
		this.list = list;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	public String[] getSdFilePath() {
		return sdFilePath;
	}
	public void setSdFilePath(String[] sdFilePath) {
		this.sdFilePath = sdFilePath;
	}
	/**
	 * @return the sdFileCnt
	 */
	public int getSdFileCnt() {
		return sdFileCnt;
	}
	/**
	 * @param sdFileCnt the sdFileCnt to set
	 */
	public void setSdFileCnt(int sdFileCnt) {
		this.sdFileCnt = sdFileCnt;
	}
	public String getAcepno() {
		return acepno;
	}
	public void setAcepno(String acepno) {
		this.acepno = acepno;
	}
	public String getStatCd() {
		return statCd;
	}
	public void setStatCd(String statCd) {
		this.statCd = statCd;
	}
	
	
	


}
