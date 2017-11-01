package nds.core.messageModule.email.service;

import nds.core.common.message.BaseEmailVO;

public class DBEmailVO extends BaseEmailVO {

	private String msgFromNm;   // 발신자명
	private String msgToNm;       // 수신자명
	
	
	public String getMsgFromNm() {
		return msgFromNm;
	}
	public void setMsgFromNm(String msgFromNm) {
		this.msgFromNm = msgFromNm;
	}
	public String getMsgToNm() {
		return msgToNm;
	}
	public void setMsgToNm(String msgToNm) {
		this.msgToNm = msgToNm;
	}
	
}