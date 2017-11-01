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
package nds.core.common.main.service;

import nds.core.common.common.service.CommonObject;

public class NeedsStatsVO extends CommonObject {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    /**
     * 웨젯 공통 searchVO
     */
    private String userId;
    
    private String depCd;
    
    private String dvnYM;
    
    
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
    
    private String total;

    
    /**
     * 웨젯 공통 설정VO
     */
    private String color;
    
    private String graphColor;
    
    

    
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

	public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getDepCd() {
        return depCd;
    }
    
    public void setDepCd(String depCd) {
        this.depCd = depCd;
    }

    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public void setDvnYM(String dvnYM) {
        this.dvnYM = dvnYM;
    }


    public String getDvnYM() {
        return dvnYM;
    }


    public void setData1(String data1) {
        this.data1 = data1;
    }


    public String getData1() {
        return data1;
    }


    public void setData2(String data2) {
        this.data2 = data2;
    }


    public String getData2() {
        return data2;
    }

    public void setValueNm(String valueNm) {
        this.valueNm = valueNm;
    }

    public String getValueNm() {
        return valueNm;
    }

    public void setValueCd(String valueCd) {
        this.valueCd = valueCd;
    }

    public String getValueCd() {
        return valueCd;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setData3(String data3) {
        this.data3 = data3;
    }

    public String getData3() {
        return data3;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setData4(String data4) {
        this.data4 = data4;
    }

    public String getData4() {
        return data4;
    }

    public void setGraphColor(String graphColor) {
        this.graphColor = graphColor;
    }

    public String getGraphColor() {
        return graphColor;
    }

}