package nds.core.common.common.service;

public class Message {
	private String gwServer;
	private String gwPort;
	private String gwOpenUrl;
	private String status;
	private String code;
	private String message;
	private String userID;
	private String jobID;
	private String docID;
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
	 * @return the jobID
	 */
	public String getJobID() {
		return jobID;
	}
	/**
	 * @param jobID the jobID to set
	 */
	public void setJobID(String jobID) {
		this.jobID = jobID;
	}
	/**
	 * @return the docID
	 */
	public String getDocID() {
		return docID;
	}
	/**
	 * @param docID the docID to set
	 */
	public void setDocID(String docID) {
		this.docID = docID;
	}
	/**
	 * @return the gwServer
	 */
	public String getGwServer() {
		return gwServer;
	}
	/**
	 * @param gwServer the gwServer to set
	 */
	public void setGwServer(String gwServer) {
		this.gwServer = gwServer;
	}
	/**
	 * @return the gwPort
	 */
	public String getGwPort() {
		return gwPort;
	}
	/**
	 * @param gwPort the gwPort to set
	 */
	public void setGwPort(String gwPort) {
		this.gwPort = gwPort;
	}
	/**
	 * @return the gwStartUrl
	 */
	public String getGwOpenUrl() {
		return gwOpenUrl;
	}
	/**
	 * @param gwStartUrl the gwStartUrl to set
	 */
	public void setGwOpenUrl(String gwOpenUrl) {
		this.gwOpenUrl = gwOpenUrl;
	}
}
