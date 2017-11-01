package nds.core.common.message;

import nds.core.common.message.MessageModule.MMSATCHTYPE;
import nds.core.common.message.MessageModule.MSGTYPE;
import nds.frm.util.StringUtil;

public class MessageBean {
	
	private String msgType;         // 메시지 타입
	private String tit;             // 메시지 제목
	private String cntn;            // 메시지 내용
	private String msgFrom;         // 발송자
	private String msgTo;           // 수신자
	private String stat = "01";      // 상태 ( 0:발송대기 , 1:발송성공 , 2:발송실패, 9:batch )
	private String regUser;         // 등록자
	private String regDttm;         // 등록일시
	private String updtUser;        // 수정자
	private String updtDttm;        // 수정일시
	private String msgNo;           // 매시지 번호
	private String inoutDvn;        // 내외부구분
	private String msgFromNm;       // 발송자명
	private String msgToNm;         // 수신자명
	private String vocId;           // vocId
	private String acepno;          // acepno
	private String sendNo;          // 발신번호
	private String srvcTmplNo;      // 설문템플릿번호
	private String cstNo;           // 고객번호
	private String workDvn;         // 작업구분
	private String tgtUser;         // 대상자
	private String procStat;        // 처리상태(voc)
	
	private String mailDvn;         // 메일 구분
	private String ccmDvn;         // 메일 구분
	private String chnlId;          // 채널아이디
	
	private String mailTit;
	private String mailCntn;
	private String mailRegDttm;
	private String mailCstNm;
	private String mailRegNm;
	private String mailTgtUser;
	
	private MMSATCHTYPE atchType1; // 첨부파일타입1
	private MMSATCHTYPE atchType2; // 첨부파일타입2
	private MMSATCHTYPE atchType3; // 첨부파일타입3
	private MMSATCHTYPE atchType4; // 첨부파일타입4
	private MMSATCHTYPE atchType5; // 첨부파일타입5
	private String atchFile1;     // 첨부파일1
	private String atchFile2;     // 첨부파일2
	private String atchFile3;     // 첨부파일3
	private String atchFile4;     // 첨부파일4
	private String atchFile5;     // 첨부파일5
	
	public MessageBean(){}
	
	public MessageBean(MSGTYPE msgtype){
		this.msgType = msgtype.toString();
	}
	
	
	
	
	public String getWorkDvn() {
		return workDvn;
	}

	public void setWorkDvn(String workDvn) {
		this.workDvn = workDvn;
	}

	
	
	
	/**
	 * 대상자를 리턴
	 * @return
	 */
	public String getTgtUser() {
		return tgtUser;
	}

	/**
	 * 대상자를 지정
	 * @param tgtUser
	 */
	public void setTgtUser(String tgtUser) {
		this.tgtUser = tgtUser;
	}

	/**
	 * 처리상태(voc)를 리턴
	 * @return
	 */
	public String getProcStat() {
		return procStat;
	}

	/**
	 * 처리상태(voc)를 설정
	 * @param procStat
	 */
	public void setProcStat(String procStat) {
		this.procStat = procStat;
	}

	/**
	 * 고객번호를 리턴
	 * @return String
	 */
	public String getCstNo() {
		return cstNo;
	}

	/**
	 * 고객번호를 지정
	 * @param cstNo
	 */
	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
	}

	/**
	 * 설문템플릿 번호를 리턴
	 * @return String
	 */
	public String getSrvcTmplNo() {
		return srvcTmplNo;
	}

	/**
	 * 설문템플릿 번호를 지정
	 * @param srvcTmplNo
	 */
	public void setSrvcTmplNo(String srvcTmplNo) {
		this.srvcTmplNo = srvcTmplNo;
	}

	/**
	 * 발송번호를 리턴
	 * @return String
	 */
	public String getSendNo() {
		return sendNo;
	}

	/**
	 * 발송번호를 지정
	 * @param sendNo
	 */
	public void setSendNo(String sendNo) {
		this.sendNo = sendNo;
	}

	/**
	 * mms 첫번째 첨부파일타입을 리턴 
	 * @return String
	 */
	public String getAtchType1() {
		return (atchType1==null)?null:atchType1.toString();
	}
	
	/**
	 * mms 첫번째 첨부파일타입을 지정
	 * @param atchType1
	 */
	public void setAtchType1(MMSATCHTYPE atchType1) {
		this.atchType1 = atchType1;
	}

	/**
	 * mms 두번째 첨부파일타입을 리턴 
	 * @return String
	 */
	public String getAtchType2() {
		return (atchType2==null)?null:atchType2.toString();
	}

	/**
	 * mms 두번째 첨부파일타입을 지정
	 * @param atchType2
	 */
	public void setAtchType2(MMSATCHTYPE atchType2) {
		this.atchType2 = atchType2;
	}

	/**
	 * mms 세번째 첨부파일타입을 리턴 
	 * @return String
	 */
	public String getAtchType3() {
		return (atchType3==null)?null:atchType3.toString();
	}

	/**
	 * mms 세번째 첨부파일타입을 지정
	 * @param atchType3
	 */
	public void setAtchType3(MMSATCHTYPE atchType3) {
		this.atchType3 = atchType3;
	}

	/**
	 * mms 네번째 첨부파일타입을 리턴 
	 * @return String
	 */
	public String getAtchType4() {
		return (atchType4==null)?null:atchType4.toString();
	}

	/**
	 * mms 네번째 첨부파일타입을 지정
	 * @param atchType4
	 */
	public void setAtchType4(MMSATCHTYPE atchType4) {
		this.atchType4 = atchType4;
	}

	/**
	 * mms 다섯번째 첨부파일타입을 리턴 
	 * @return String
	 */
	public String getAtchType5() {
		return (atchType5==null)?null:atchType5.toString();
	}

	/**
	 * mms 다섯번째 첨부파일타입을 지정
	 * @param atchType5
	 */
	public void setAtchType5(MMSATCHTYPE atchType5) {
		this.atchType5 = atchType5;
	}

	/**
	 * mms 첫번째 첨부파일 경로를 리턴한다.
	 * @return String
	 */
	public String getAtchFile1() {
		return atchFile1;
	}

	/**
	 * mms 첫번째 첨부파일의 full경로를 지정한다.
	 * @param atchFile1
	 */
	public void setAtchFile1(String atchFile1) {
		this.atchFile1 = atchFile1;
	}

	/**
	 * mms 두번째 첨부파일경로를 리턴한다.
	 * @return String
	 */
	public String getAtchFile2() {
		return atchFile2;
	}

	/**
	 * mms 두번째 첨부파일의 full경로를 지정한다.
	 * @param atchFile2
	 */
	public void setAtchFile2(String atchFile2) {
		this.atchFile2 = atchFile2;
	}
	/**
	 * mms 세번째 첨부파일경로를 리턴한다.
	 * @return String
	 */
	public String getAtchFile3() {
		return atchFile3;
	}

	/**
	 * mms 세번째 첨부파일의 full경로를 지정한다.
	 * @param atchFile3
	 */
	public void setAtchFile3(String atchFile3) {
		this.atchFile3 = atchFile3;
	}

	/**
	 * mms 네번째 첨부파일경로를 리턴한다.
	 * @return String
	 */
	public String getAtchFile4() {
		return atchFile4;
	}

	/**
	 * mms 네번째 첨부파일의 full경로를 지정한다.
	 * @param atchFile4
	 */
	public void setAtchFile4(String atchFile4) {
		this.atchFile4 = atchFile4;
	}

	/**
	 * mms 다섯번째 첨부파일경로를 리턴한다.
	 * @return String
	 */
	public String getAtchFile5() {
		return atchFile5;
	}

	/**
	 * mms 다섯번째 첨부파일의 full경로를 지정한다.
	 * @param atchFile5
	 */
	public void setAtchFile5(String atchFile5) {
		this.atchFile5 = atchFile5;
	}

	/**
	 * 메시지 발송자명을 리턴한다.
	 * @return String
	 */
	public String getMsgFromNm() {
		return msgFromNm;
	}

	/**
	 * 메시지 발송자명을 지정한다.
	 * @param msgFromNm
	 */
	public void setMsgFromNm(String msgFromNm) {
		this.msgFromNm = msgFromNm;
	}

	/**
	 * 메시지 수신자명을 리턴한다.
	 * @return String
	 */
	public String getMsgToNm() {
		return msgToNm;
	}

	/**
	 * 메시지 수신자명을 지정한다.
	 * @param msgToNm
	 */
	public void setMsgToNm(String msgToNm) {
		this.msgToNm = msgToNm;
	}

	/**
	 * vocid 를 리턴한다.
	 * @return String
	 */
	public String getVocId() {
		return vocId;
	}

	/**
	 * vocid 를 지정한다.
	 * @param vocId
	 */
	public void setVocId(String vocId) {
		this.vocId = vocId;
	}

	public String getAcepno() {
		return acepno;
	}

	public void setAcepno(String acepno) {
		this.acepno = acepno;
	}

	/**
	 * 템플릿의 내/외부 구분을 리턴한다.
	 * @return String
	 */
	public String getInoutDvn() {
		return inoutDvn;
	}

	/**
	 * 템플릿의 내/외부 구분을 지정한다.
	 * @param inoutDvn
	 */
	public void setInoutDvn(String inoutDvn) {
		this.inoutDvn = inoutDvn;
	}

	/**
	 * 메시지 번호를 리턴한다.
	 * @return String
	 */
	public String getMsgNo() {
		return msgNo;
	}

	/**
	 * 메시지 번호를 지정한다.
	 * @param msgNo
	 */
	public void setMsgNo(String msgNo) {
		this.msgNo = msgNo;
	}


	/**
	 * 메시지 제목을 리턴한다.
	 * @return String
	 */
	public String getTit() {
		return tit;
	}
	
	/**
	 * 메시지 제목을 지정한다.
	 * @param tit
	 */
	public void setTit(String tit) {
		this.tit = tit;
	}
	
	/**
	 * 메시지 내용을 리턴한다.
	 * @return String
	 */
	public String getCntn() {
		return cntn;
	}
	
	/**
	 * 메시지 내용을 지정한다.
	 * @param cntn
	 */
	public void setCntn(String cntn) {
		this.cntn = cntn;
	}
	
	/**
	 * 메시지 발송정보(휴대폰번호/이메일/메신저사번등..) 를 리턴한다.
	 * @return String
	 */
	public String getMsgFrom() {
		return msgFrom;
	}
	
	/**
	 * 메시지 발송정보(휴대폰번호/이메일/메신저사번등..) 를 지정한다.
	 * @param msgFrom
	 */
	public void setMsgFrom(String msgFrom) {
		this.msgFrom = StringUtil.null2void(msgFrom).replaceAll("-", "");;
	}
	
	/**
	 * 메시지 수신정보(휴대폰번호/이메일/메신저사번등..) 를 리턴한다.
	 * @return String
	 */
	public String getMsgTo() {
		return msgTo;
	}
	
	/**
	 * 메시지 수신정보(휴대폰번호/이메일/메신저사번등..) 를 지정한다.
	 * @param msgTo
	 */
	public void setMsgTo(String msgTo) {
		this.msgTo = StringUtil.null2void(msgTo).replaceAll("-", "");
	}
	
	/**
	 * 메시지 처리상태를 리턴한다.
	 * @return String
	 */
	public String getStat() {
		return stat;
	}
	
	/**
	 * 메시지 처리상태를 지정한다.
	 * @param stat
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}
	
	/**
	 * 등록자를 리턴한다.
	 * @return String
	 */
	public String getRegUser() {
		return regUser;
	}
	
	/**
	 * 등록자를 지정한다.
	 * @param regUser
	 */
	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}
	
	/**
	 * 등록일시를 리턴한다.
	 * @return String (yyyymmddhh24miss)
	 */
	public String getRegDttm() {
		return regDttm;
	}
	
	/**
	 * 등록일시를 지정한다.
	 * @param regDttm
	 */
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	
	/**
	 * 수정자를 리턴한다.
	 * @return String
	 */
	public String getUpdtUser() {
		return updtUser;
	}
	
	/**
	 * 수정자를 지정한다.
	 * @param updtUser
	 */
	public void setUpdtUser(String updtUser) {
		this.updtUser = updtUser;
	}
	
	/**
	 * 수정시간을 리턴한다.
	 * @return String
	 */
	public String getUpdtDttm() {
		return updtDttm;
	}
	
	/**
	 * 수정시간을 지정한다.
	 * @param updtDttm
	 */
	public void setUpdtDttm(String updtDttm) {
		this.updtDttm = updtDttm;
	}
	
	/**
	 * 메시지 타입을 리턴한다.
	 * @return String (MessageModule에 지정된  enum(MMSATCHTYPE)의 String 형을 리턴 )
	 */
	public String getMsgType() {
		return msgType;
	}

	public String getMailDvn() {
		return mailDvn;
	}

	public void setMailDvn(String mailDvn) {
		this.mailDvn = mailDvn;
	}

	public String getCcmDvn() {
		return ccmDvn;
	}

	public void setCcmDvn(String ccmDvn) {
		this.ccmDvn = ccmDvn;
	}

	public String getChnlId() {
		return chnlId;
	}

	public void setChnlId(String chnlId) {
		this.chnlId = chnlId;
	}

	public String getMailTit() {
		return mailTit;
	}

	public void setMailTit(String mailTit) {
		this.mailTit = mailTit;
	}

	public String getMailCntn() {
		return mailCntn;
	}

	public void setMailCntn(String mailCntn) {
		this.mailCntn = mailCntn;
	}

	public String getMailRegDttm() {
		return mailRegDttm;
	}

	public void setMailRegDttm(String mailRegDttm) {
		this.mailRegDttm = mailRegDttm;
	}

	public String getMailCstNm() {
		return mailCstNm;
	}

	public void setMailCstNm(String mailCstNm) {
		this.mailCstNm = mailCstNm;
	}

	public String getMailRegNm() {
		return mailRegNm;
	}

	public void setMailRegNm(String mailRegNm) {
		this.mailRegNm = mailRegNm;
	}

	public String getMailTgtUser() {
		return mailTgtUser;
	}

	public void setMailTgtUser(String mailTgtUser) {
		this.mailTgtUser = mailTgtUser;
	}
	
}