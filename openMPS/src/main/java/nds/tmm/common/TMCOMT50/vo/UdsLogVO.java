package nds.tmm.common.TMCOMT50.vo;

/**
 * @Class Name : UdsLogVO.java
 * @Description : UdsLog VO class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.08.14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class UdsLogVO extends UdsLogDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** cmid */
    private java.lang.String cmid;

    /** umid */
    private java.lang.String umid;

    /** msg_type */
//    private java.lang.Integer msgType;
    private int msgType;

    /** status */
//    private java.lang.Integer status;
    private java.lang.String status;

    /** request_time */
    private java.sql.Timestamp requestTime;
    
    /** send_time */
    private java.sql.Timestamp sendTime;

    /** report_time */
    private java.sql.Timestamp reportTime;

    /** dest_phone */
    private java.lang.String destPhone;

    /** dest_name */
    private java.lang.String destName;

    /** send_phone */
    private java.lang.String sendPhone;

    /** send_name */
    private java.lang.String sendName;

    /** subject */
    private java.lang.String subject;

    /** msg_body */
    private java.lang.String msgBody;

    /** wap_url */
    private java.lang.String wapUrl;

    /** cover_flag */
    private java.lang.Integer coverFlag;

    /** sms_flag */
    private java.lang.Integer smsFlag;

    /** reply_flag */
    private java.lang.Integer replyFlag;

    /** retry_cnt */
    private java.lang.Integer retryCnt;

    /** fax_file */
    private java.lang.String faxFile;

    /** vxml_file */
    private java.lang.String vxmlFile;

    /** call_status */
    private java.lang.Integer callStatus;

    /** use_page */
    private java.lang.Integer usePage;

    /** use_time */
    private java.lang.Integer useTime;

    /** sn_result */
    private java.lang.Integer snResult;

    /** wap_info */
    private java.lang.String wapInfo;

    /** cinfo */
    private java.lang.String cinfo;

	public java.lang.String getCmid() {
		return cmid;
	}

	public void setCmid(java.lang.String cmid) {
		this.cmid = cmid;
	}

	public java.lang.String getUmid() {
		return umid;
	}

	public void setUmid(java.lang.String umid) {
		this.umid = umid;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	public java.sql.Timestamp getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(java.sql.Timestamp requestTime) {
		this.requestTime = requestTime;
	}

	public java.sql.Timestamp getSendTime() {
		return sendTime;
	}

	public void setSendTime(java.sql.Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public java.sql.Timestamp getReportTime() {
		return reportTime;
	}

	public void setReportTime(java.sql.Timestamp reportTime) {
		this.reportTime = reportTime;
	}

	public java.lang.String getDestPhone() {
		return destPhone;
	}

	public void setDestPhone(java.lang.String destPhone) {
		this.destPhone = destPhone;
	}

	public java.lang.String getDestName() {
		return destName;
	}

	public void setDestName(java.lang.String destName) {
		this.destName = destName;
	}

	public java.lang.String getSendPhone() {
		return sendPhone;
	}

	public void setSendPhone(java.lang.String sendPhone) {
		this.sendPhone = sendPhone;
	}

	public java.lang.String getSendName() {
		return sendName;
	}

	public void setSendName(java.lang.String sendName) {
		this.sendName = sendName;
	}

	public java.lang.String getSubject() {
		return subject;
	}

	public void setSubject(java.lang.String subject) {
		this.subject = subject;
	}

	public java.lang.String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(java.lang.String msgBody) {
		this.msgBody = msgBody;
	}

	public java.lang.String getWapUrl() {
		return wapUrl;
	}

	public void setWapUrl(java.lang.String wapUrl) {
		this.wapUrl = wapUrl;
	}

	public java.lang.Integer getCoverFlag() {
		return coverFlag;
	}

	public void setCoverFlag(java.lang.Integer coverFlag) {
		this.coverFlag = coverFlag;
	}

	public java.lang.Integer getSmsFlag() {
		return smsFlag;
	}

	public void setSmsFlag(java.lang.Integer smsFlag) {
		this.smsFlag = smsFlag;
	}

	public java.lang.Integer getReplyFlag() {
		return replyFlag;
	}

	public void setReplyFlag(java.lang.Integer replyFlag) {
		this.replyFlag = replyFlag;
	}

	public java.lang.Integer getRetryCnt() {
		return retryCnt;
	}

	public void setRetryCnt(java.lang.Integer retryCnt) {
		this.retryCnt = retryCnt;
	}

	public java.lang.String getFaxFile() {
		return faxFile;
	}

	public void setFaxFile(java.lang.String faxFile) {
		this.faxFile = faxFile;
	}

	public java.lang.String getVxmlFile() {
		return vxmlFile;
	}

	public void setVxmlFile(java.lang.String vxmlFile) {
		this.vxmlFile = vxmlFile;
	}

	public java.lang.Integer getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(java.lang.Integer callStatus) {
		this.callStatus = callStatus;
	}

	public java.lang.Integer getUsePage() {
		return usePage;
	}

	public void setUsePage(java.lang.Integer usePage) {
		this.usePage = usePage;
	}

	public java.lang.Integer getUseTime() {
		return useTime;
	}

	public void setUseTime(java.lang.Integer useTime) {
		this.useTime = useTime;
	}

	public java.lang.Integer getSnResult() {
		return snResult;
	}

	public void setSnResult(java.lang.Integer snResult) {
		this.snResult = snResult;
	}

	public java.lang.String getWapInfo() {
		return wapInfo;
	}

	public void setWapInfo(java.lang.String wapInfo) {
		this.wapInfo = wapInfo;
	}

	public java.lang.String getCinfo() {
		return cinfo;
	}

	public void setCinfo(java.lang.String cinfo) {
		this.cinfo = cinfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
