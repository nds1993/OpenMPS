package nds.core.common.message.service;

public abstract class BaseMessageVO {
 
	private String msgSeq;    // 메시지번호
	private String msgTit;    // 메시지 제목
	private String msgCntn;   // 메시지내용
	private String msgFrom;   // 발신자
	private String msgTo;     // 수신자
	private String stat;      // 상태
	
	private String vocId;      // VOC ID
	private String homepageId; // 고객 HOMEPAGE ID
	
	public String getMsgSeq() {
		return msgSeq;
	}
	public void setMsgSeq(String msgSeq) {
		this.msgSeq = msgSeq;
	}
	public String getMsgTit() {
		return msgTit;
	}
	public void setMsgTit(String msgTit) {
		this.msgTit = msgTit;
	}
	public String getMsgCntn() {
		return msgCntn;
	}
	public void setMsgCntn(String msgCntn) {
		this.msgCntn = msgCntn;
	}
	public String getMsgFrom() {
		return msgFrom;
	}
	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}
	public String getMsgTo() {
		return msgTo;
	}
	public void setMsgTo(String msgTo) {
		this.msgTo = msgTo;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getVocId() {
		return vocId;
	}
	public void setVocId(String vocId) {
		this.vocId = vocId;
	}
	public String getHomepageId() {
		return homepageId;
	}
	public void setHomepageId(String homepageId) {
		this.homepageId = homepageId;
	}
	
}
