package nds.core.systemsettings.extention.service;

import nds.core.common.common.service.CommonObject;

public class ExtentionVO extends CommonObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fileExt;
	private String enFileExt;
	private String regUser;
    private String regDttm;
    private String updtUser;
    private String updtDttm;
	    

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getEnFileExt() {
		return enFileExt;
	}

	public void setEnFileExt(String enFileExt) {
		this.enFileExt = enFileExt;
	}

	public String getRegUser() {
		return regUser;
	}

	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}

	public String getRegDttm() {
		return regDttm;
	}

	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}

	public String getUpdtUser() {
		return updtUser;
	}

	public void setUpdtUser(String updtUser) {
		this.updtUser = updtUser;
	}

	public String getUpdtDttm() {
		return updtDttm;
	}

	public void setUpdtDttm(String updtDttm) {
		this.updtDttm = updtDttm;
	}
	
	

}
