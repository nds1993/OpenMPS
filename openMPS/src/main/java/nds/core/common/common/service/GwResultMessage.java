package nds.core.common.common.service;

import java.util.ArrayList;
import java.util.List;

public class GwResultMessage {
	private String documentType;
	private String documentStatus;
	private String documentID;
	private String work_item;
	private String curr_actorType;
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

	/* [gw_result.ns?protID=createDoc&docID=HR112-100020096000012823&Document=]�� ��b �׽�Ʈ �� �ѱ��� ��8�� �7��.
	 * <exchangeableDocument>
	 * 	<documentInformation>
	 * 		<documentType>HR112</documentType>
	 * 		<documentID>HR112-100020096000030789</documentID>
	 * 		<createTime>2009/07/07 10:19:09</createTime>
	 * 		<modifiedTime>2009/07/07 10:19:09</modifiedTime>
	 * 		<createSystem>INTHR</createSystem>
	 * 		<createServer>172.25.1.102</createServer>
	 * 		<gccOrganizationCode />
	 * 		<creator>emp_name</creator>
	 * 		<subject>document(6000030789)</subject>
	 * 	</documentInformation>
	 * 	<processInformation>
	 * 		<destinationSystem>SmartFlow XF</destinationSystem>
	 * 		<recipient>2001294</recipient>
	 * 		
	 * 		
	 * 	<documentStatus>processed</documentStatus>
	 *  <workflow>
	 *  	<CurrentNFSancLineInfo>
	 *  		<seqNum>1</seqNum>
	 *  		<actorType>100</actorType>
	 *  		<person>
	 *  			<id>9011284</id>
	 *  			<name>emp_name</name>
	 *  			<deptID>1370</deptID>
	 *  			<deptName>plant</deptName>
	 *  			<parentOrgID>nongshim</parentOrgID>
	 *  			<parentOrgName>nongshim</parentOrgName>
	 *  			<rank>3</rank><role>G2</role><caste>damdang</caste>
	 *  		</person>
	 *  		<kind>123</kind>
	 *  		<notProcessReason></notProcessReason>
	 *  		<status>123</status>
	 *  		<arrivedTime>2009/07/08 07:41:03</arrivedTime>
	 *  		<readTime></readTime>
	 *  		<processedTime>2009/07/08 07:41:03</processedTime>
	 *  		<comment></comment>
	 *  	</CurrentNFSancLineInfo>
	 *  	<ExtNFSancLineInfo>
	 *  		<seqNum>0</seqNum>
	 *  		<actorType>100</actorType>
	 *  		<person>
	 *  			<id>2001294</id>
	 *  			<name>emp_name</name>
	 *  			<deptID>2971</deptID>
	 *  			<deptName>plant</deptName>
	 *  			<parentOrgID>nongshim</parentOrgID>
	 *  			<parentOrgName>nongshim</parentOrgName>
	 *  			<rank>1</rank><role>2</role><caste>3</caste>
	 *  		</person>
	 *  		<kind>123</kind>
	 *  		<notProcessReason></notProcessReason>
	 *  		<status>123</status>
	 *  		<arrivedTime>2009/07/07 10:21:23</arrivedTime>
	 *  		<readTime></readTime>
	 *  		<processedTime>2009/07/07 10:21:23</processedTime>
	 *  		<comment></comment>
	 *  	</ExtNFSancLineInfo>
	 *  	<ExtNFSancLineInfo>
	 *  		<seqNum>1</seqNum>.....</ExtNFSancLineInfo>
	 *  </workflow>
	 *  </processInformation>
	 * 	<documentDescription>
	 * 		<work>
	 * 			<work_manage>
	 * 				<job_name>ȸ����ǥ (6000030789)</job_name>
	 * 			</work_manage>
	 * 			<work_ref />
	 * 			<work_item work_item_code="001" work_item_name="��������" />
	 * 			<work_item work_item_code="002" work_item_name="��ǥ����">20090707</work_item>
	 * 			<work_item work_item_code="003" work_item_name="��ǥ��ȣ">6000030789</work_item>
	 * 			<work_item work_item_code="004" work_item_name="�ۼ��μ�">�ƻ�ǰ�����</work_item>
	 * 		</work>
	 * 	</documentDescription>
	 * 	<documentBody />
	 * 	<attachments />
	 * </exchangeableDocument>
	 */
	
	private String seq;
	private String name;
	private String url;
	private List<Type> list = new ArrayList<Type>();
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
	@SuppressWarnings("unchecked")
	public List getList() {
		return list;
	}
	
	public void setList(String sKey, String sValue) {
		//list.add(new Type(sKey, sValue, "", "") );
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
	 * @return the actorType
	 */
	public String getCurr_actorType() {
		return curr_actorType;
	}
	/**
	 * @param actorType the actorType to set
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
}
