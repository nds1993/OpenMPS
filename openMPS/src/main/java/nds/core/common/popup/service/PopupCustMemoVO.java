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
package nds.core.common.popup.service;

import nds.core.common.common.service.CommonObject;

public class PopupCustMemoVO extends CommonObject {
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

    /**
     * Memo searchVO & resultVO
     */
    private String seqNo;
    
    private String cstNo;       // searchVO
    
    private String makeUser;
    
    private String makeUserNm;
    
    private String makeDepNm;
    
    private String cntn;
    
    private String mark;

    private String regUser;

    private String regDttm;

    private String updtUser;

    private String updtDttm;

    
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

    
    public String getSeqNo() {
        return seqNo;
    }

    
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    
    public String getCstNo() {
        return cstNo;
    }

    
    public void setCstNo(String cstNo) {
        this.cstNo = cstNo;
    }

    
    public String getMakeUser() {
        return makeUser;
    }

    
    public void setMakeUser(String makeUser) {
        this.makeUser = makeUser;
    }

    
    public String getMakeUserNm() {
        return makeUserNm;
    }

    
    public void setMakeUserNm(String makeUserNm) {
        this.makeUserNm = makeUserNm;
    }

    
    public String getCntn() {
        return cntn;
    }

    
    public void setCntn(String cntn) {
        this.cntn = cntn;
    }

    
    public String getMark() {
        return mark;
    }

    
    public void setMark(String mark) {
        this.mark = mark;
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

    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public void setMakeDepNm(String makeDepNm) {
        this.makeDepNm = makeDepNm;
    }


    public String getMakeDepNm() {
        return makeDepNm;
    }
    
    
    
}