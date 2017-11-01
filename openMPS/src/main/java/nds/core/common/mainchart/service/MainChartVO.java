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
package nds.core.common.mainchart.service;

import nds.core.common.common.service.CommonObject;

public class MainChartVO extends CommonObject {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * main searchVO & resultVO
     */
    private String userId;
    
    private String widgetsId;   // searchVO
    
    private String widgetsNm;
    
    private String conCd;
    
    private String conNm;
    
    private String graphBaseCd; // searchVO
    
    private String graphRang;   // searchVO
    
    private String yn;
    
    private String rangValue;
    
    private String baseValue;

    private String regUser;

    private String regDttm;

    private String updtUser;

    private String updtDttm;
    
    private String sortNo;
    
    /**
     * PageNavigator : start Page
     */
    private int startNo;

    /**
     * PageNavigator : end Page
     */
    private int endNo;
    
    /**
     * 이슈VOC resultVO
     */
    private String vocId;
    
    private String tit;
    
    private String asgnDd;
    
    private String dvnId;
    
    
    /**
     * 월별 처리건수 searchVO
     */
    
    private String depCd;
    
    private String dvnYM;
    
    
    /**
     * 월별 처리건수 resultVO
     */
    private String yyyymm;
    
    private String month;
    
    private String needs01;
    
    private String needs02;

    private String needs03;
    
    private String needs04;
    
    private String needs05;

    private String needsTypeCd;
    
    private String needsTypeNm;

    

    /**
     * VOC 현황 resultVO
     */
    
    private int needsCnt;
    
    private int needsNotCnt;
    
    private String needsTotCnt;
    
    /**
     * 웨젯 공통 searchVO
     */
    
    
    /**
     * 웨젯 공통 resultVO
     */
    private String valueCd;
    
    private String valueNm;
    
    private String data1;
    
    private String data2;
    
    private String data3;
    
    private String data4;
    
    private String data5;
    
    private String data6;
    
    private String data7;
    
    private String data8;
    
    private String data9;
    
    private String data10;
    
    private String total;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWidgetsId() {
		return widgetsId;
	}

	public void setWidgetsId(String widgetsId) {
		this.widgetsId = widgetsId;
	}

	public String getWidgetsNm() {
		return widgetsNm;
	}

	public void setWidgetsNm(String widgetsNm) {
		this.widgetsNm = widgetsNm;
	}

	public String getConCd() {
		return conCd;
	}

	public void setConCd(String conCd) {
		this.conCd = conCd;
	}

	public String getConNm() {
		return conNm;
	}

	public void setConNm(String conNm) {
		this.conNm = conNm;
	}

	public String getGraphBaseCd() {
		return graphBaseCd;
	}

	public void setGraphBaseCd(String graphBaseCd) {
		this.graphBaseCd = graphBaseCd;
	}

	public String getGraphRang() {
		return graphRang;
	}

	public void setGraphRang(String graphRang) {
		this.graphRang = graphRang;
	}

	public String getYn() {
		return yn;
	}

	public void setYn(String yn) {
		this.yn = yn;
	}

	public String getRangValue() {
		return rangValue;
	}

	public void setRangValue(String rangValue) {
		this.rangValue = rangValue;
	}

	public String getBaseValue() {
		return baseValue;
	}

	public void setBaseValue(String baseValue) {
		this.baseValue = baseValue;
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

	public String getSortNo() {
		return sortNo;
	}

	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
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

	public String getVocId() {
		return vocId;
	}

	public void setVocId(String vocId) {
		this.vocId = vocId;
	}

	public String getTit() {
		return tit;
	}

	public void setTit(String tit) {
		this.tit = tit;
	}

	public String getAsgnDd() {
		return asgnDd;
	}

	public void setAsgnDd(String asgnDd) {
		this.asgnDd = asgnDd;
	}

	public String getDvnId() {
		return dvnId;
	}

	public void setDvnId(String dvnId) {
		this.dvnId = dvnId;
	}

	public String getDepCd() {
		return depCd;
	}

	public void setDepCd(String depCd) {
		this.depCd = depCd;
	}

	public String getDvnYM() {
		return dvnYM;
	}

	public void setDvnYM(String dvnYM) {
		this.dvnYM = dvnYM;
	}

	public String getYyyymm() {
		return yyyymm;
	}

	public void setYyyymm(String yyyymm) {
		this.yyyymm = yyyymm;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getNeeds01() {
		return needs01;
	}

	public void setNeeds01(String needs01) {
		this.needs01 = needs01;
	}

	public String getNeeds02() {
		return needs02;
	}

	public void setNeeds02(String needs02) {
		this.needs02 = needs02;
	}

	public String getNeeds03() {
		return needs03;
	}

	public void setNeeds03(String needs03) {
		this.needs03 = needs03;
	}

	public String getNeeds04() {
		return needs04;
	}

	public void setNeeds04(String needs04) {
		this.needs04 = needs04;
	}

	public String getNeeds05() {
		return needs05;
	}

	public void setNeeds05(String needs05) {
		this.needs05 = needs05;
	}

	public String getNeedsTypeCd() {
		return needsTypeCd;
	}

	public void setNeedsTypeCd(String needsTypeCd) {
		this.needsTypeCd = needsTypeCd;
	}

	public String getNeedsTypeNm() {
		return needsTypeNm;
	}

	public void setNeedsTypeNm(String needsTypeNm) {
		this.needsTypeNm = needsTypeNm;
	}

	public int getNeedsCnt() {
		return needsCnt;
	}

	public void setNeedsCnt(int needsCnt) {
		this.needsCnt = needsCnt;
	}

	public int getNeedsNotCnt() {
		return needsNotCnt;
	}

	public void setNeedsNotCnt(int needsNotCnt) {
		this.needsNotCnt = needsNotCnt;
	}

	public String getNeedsTotCnt() {
		return needsTotCnt;
	}

	public void setNeedsTotCnt(String needsTotCnt) {
		this.needsTotCnt = needsTotCnt;
	}

	public String getValueCd() {
		return valueCd;
	}

	public void setValueCd(String valueCd) {
		this.valueCd = valueCd;
	}

	public String getValueNm() {
		return valueNm;
	}

	public void setValueNm(String valueNm) {
		this.valueNm = valueNm;
	}

	public String getData1() {
		return data1;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	public String getData3() {
		return data3;
	}

	public void setData3(String data3) {
		this.data3 = data3;
	}

	public String getData4() {
		return data4;
	}

	public void setData4(String data4) {
		this.data4 = data4;
	}

	public String getData5() {
		return data5;
	}

	public void setData5(String data5) {
		this.data5 = data5;
	}

	public String getData6() {
		return data6;
	}

	public void setData6(String data6) {
		this.data6 = data6;
	}

	public String getData7() {
		return data7;
	}

	public void setData7(String data7) {
		this.data7 = data7;
	}

	public String getData8() {
		return data8;
	}

	public void setData8(String data8) {
		this.data8 = data8;
	}

	public String getData9() {
		return data9;
	}

	public void setData9(String data9) {
		this.data9 = data9;
	}

	public String getData10() {
		return data10;
	}

	public void setData10(String data10) {
		this.data10 = data10;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

    
}