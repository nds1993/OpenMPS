/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2009-6-11 10:23:05
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* CHANGE REVISION
* ------------------------------
* AUTHOR   : NDS
* DATE     : 2009-6-11 10:23:05
* REVISION : 1.0   First release.
* -------------------------------
* CLASS DESCRIPTION
* -------------------------------
*
*/
package nds.core.common.common.service;

public class AttachFileVO extends CommonObject {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * PageNavigator : start Page
     */
    private int startNo;

    /**
     * PageNavigator : end Page
     */
    private int endNo;

    private String atchDataPath;

    private String atchOtxtFileName;

    private String atchFileSize;

    private String sendErrMsg;

    private String atchFileCat;

    private String atchFileExt;
    
	private String atchFileChngName;

    private String contsId;

    private String docRegNo;
    
    private String atchFilePath;
   
    private String hpTransferYn;
    
    private String regUser;
    
    private String updtUser;
    
    private String keyVal01;
    private String keyVal02;
    private String keyVal03;
    private String keyVal04;
    
    
    
    
    public String getKeyVal01() {
		return keyVal01;
	}

	public void setKeyVal01(String keyVal01) {
		this.keyVal01 = keyVal01;
	}

	public String getKeyVal02() {
		return keyVal02;
	}

	public void setKeyVal02(String keyVal02) {
		this.keyVal02 = keyVal02;
	}

	public String getKeyVal03() {
		return keyVal03;
	}

	public void setKeyVal03(String keyVal03) {
		this.keyVal03 = keyVal03;
	}

	public String getKeyVal04() {
		return keyVal04;
	}

	public void setKeyVal04(String keyVal04) {
		this.keyVal04 = keyVal04;
	}

	public int getStartNo() {
        return startNo;
    }

    public void setStartNo(int startNo) {
        this.startNo = startNo;
    }

    public int getEndNo() {
        return endNo;
    }

    public void setEndNo(int endNo) {
        this.endNo = endNo;
    }

    public String getAtchDataPath() {
        return atchDataPath;
    }

    public void setAtchDataPath(String atchDataPath) {
        this.atchDataPath = atchDataPath;
    }

    public String getAtchOtxtFileName() {
        return atchOtxtFileName;
    }

    public void setAtchOtxtFileName(String atchOtxtFileName) {
        this.atchOtxtFileName = atchOtxtFileName;
    }

    public String getAtchFileSize() {
        return atchFileSize;
    }

    public void setAtchFileSize(String atchFileSize) {
        this.atchFileSize = atchFileSize;
    }

    public String getSendErrMsg() {
        return sendErrMsg;
    }

    public void setSendErrMsg(String sendErrMsg) {
        this.sendErrMsg = sendErrMsg;
    }

    public String getAtchFileCat() {
        return atchFileCat;
    }

    public void setAtchFileCat(String atchFileCat) {
        this.atchFileCat = atchFileCat;
    }

    public String getAtchFileExt() {
        return atchFileExt;
    }

    public void setAtchFileExt(String atchFileExt) {
        this.atchFileExt = atchFileExt;
    }

	public String getAtchFileChngName() {
		return atchFileChngName;
	}

	public void setAtchFileChngName(String atchFileChngName) {
		this.atchFileChngName = atchFileChngName;
	}

	public String getContsId() {
		return contsId;
	}

	public void setContsId(String contsId) {
		this.contsId = contsId;
	}

	public String getDocRegNo() {
		return docRegNo;
	}

	public void setDocRegNo(String docRegNo) {
		this.docRegNo = docRegNo;
	}

	public String getAtchFilePath() {
		return atchFilePath;
	}

	public void setAtchFilePath(String atchFilePath) {
		this.atchFilePath = atchFilePath;
	}

	public String getUpdtUser() {
		return updtUser;
	}

	public void setUpdtUser(String updtUser) {
		this.updtUser = updtUser;
	}

	public String getRegUser() {
		return regUser;
	}

	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}

	public String getHpTransferYn() {
		return hpTransferYn;
	}

	public void setHpTransferYn(String hpTransferYn) {
		this.hpTransferYn = hpTransferYn;
	}
	
	
	
}