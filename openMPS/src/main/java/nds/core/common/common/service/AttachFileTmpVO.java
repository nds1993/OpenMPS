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


public class AttachFileTmpVO extends CommonObject {
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

    private String atchFileChngName;

    private String atchOtxtFileName;

    private String atchFileSize;

    private String atchFileExt;

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

    public String getAtchFileChngName() {
        return atchFileChngName;
    }

    public void setAtchFileChngName(String atchFileChngName) {
        this.atchFileChngName = atchFileChngName;
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

    public String getAtchFileExt() {
        return atchFileExt;
    }

    public void setAtchFileExt(String atchFileExt) {
        this.atchFileExt = atchFileExt;
    }
}