/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-7-3 10:21:43
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-7-3 10:21:43
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.operation.suggesttype.service;

import nds.core.common.common.service.CommonObject;

public class SuggestTypeVO extends CommonObject {
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
     * 제안유형 resultVO
     */
    private String lcls;
    private String mcls;
    private String scls;
    private String lvl;
    private String typeCatNm;
    private String useYn;
    private String type01;
    private String type02;
    private String type03;
    private String type04;
    private String type05;
    private String regUser;
    private String regDttm;
    private String updtUser;
    private String updtDttm;
    private String ord;
    private String code;
    private String type;
    
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
	public String getLcls() {
		return lcls;
	}
	public void setLcls(String lcls) {
		this.lcls = lcls;
	}
	public String getMcls() {
		return mcls;
	}
	public void setMcls(String mcls) {
		this.mcls = mcls;
	}
	public String getScls() {
		return scls;
	}
	public void setScls(String scls) {
		this.scls = scls;
	}
	public String getLvl() {
		return lvl;
	}
	public void setLvl(String lvl) {
		this.lvl = lvl;
	}
	public String getTypeCatNm() {
		return typeCatNm;
	}
	public void setTypeCatNm(String typeCatNm) {
		this.typeCatNm = typeCatNm;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getType01() {
		return type01;
	}
	public void setType01(String type01) {
		this.type01 = type01;
	}
	public String getType02() {
		return type02;
	}
	public void setType02(String type02) {
		this.type02 = type02;
	}
	public String getType03() {
		return type03;
	}
	public void setType03(String type03) {
		this.type03 = type03;
	}
	public String getType04() {
		return type04;
	}
	public void setType04(String type04) {
		this.type04 = type04;
	}
	public String getType05() {
		return type05;
	}
	public void setType05(String type05) {
		this.type05 = type05;
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
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
}