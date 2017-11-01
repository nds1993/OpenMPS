package nds.core.systemsettings.service.service;

import nds.core.common.common.service.CommonObject;

public class VocServiceVO extends CommonObject {
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

    private String orderByClause;

    private String servNo;

    private String servDvn;

    private String servNm;

    private String servStat;

    private String servStop;

    private Object servDescrpt;
    
    private String scdlForm;

    private String srvrStat;

    private String regUser;

    private String regDd;

    private String updtUser;

    private String updtDd;
    
    private String statCd;
    
    private String servStopSt;
    

    
    
    public String getServStopSt() {
		return servStopSt;
	}

	public void setServStopSt(String servStopSt) {
		this.servStopSt = servStopSt;
	}

	public String getStatCd() {
		return statCd;
	}

	public void setStatCd(String statCd) {
		this.statCd = statCd;
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

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public String getServNo() {
        return servNo;
    }

    public void setServNo(String servNo) {
        this.servNo = servNo;
    }
    
    public String getServDvn() {
        return servDvn;
    }
    
    public void setServDvn(String servDvn) {
        this.servDvn = servDvn;
    }

    public String getServNm() {
        return servNm;
    }

    public void setServNm(String servNm) {
        this.servNm = servNm;
    }

    public String getServStat() {
        return servStat;
    }

    public void setServStat(String servStat) {
        this.servStat = servStat;
    }

    public String getServStop() {
        return servStop;
    }

    public void setServStop(String servStop) {
        this.servStop = servStop;
    }

    public Object getServDescrpt() {
        return servDescrpt;
    }

    public void setServDescrpt(Object servDescrpt) {
        this.servDescrpt = servDescrpt;
    }

    public String getScdlForm() {
        return scdlForm;
    }

    public void setScdlForm(String scdlForm) {
        this.scdlForm = scdlForm;
    }
    
    public String getSrvrStat() {
        return srvrStat;
    }
    
    public void setSrvrStat(String srvrStat) {
        this.srvrStat = srvrStat;
    }

    public String getRegUser() {
        return regUser;
    }

    public void setRegUser(String regUser) {
        this.regUser = regUser;
    }

    public String getRegDd() {
        return regDd;
    }

    public void setRegDd(String regDd) {
        this.regDd = regDd;
    }

    public String getUpdtUser() {
        return updtUser;
    }

    public void setUpdtUser(String updtUser) {
        this.updtUser = updtUser;
    }

    public String getUpdtDd() {
        return updtDd;
    }

    public void setUpdtDd(String updtDd) {
        this.updtDd = updtDd;
    }
}
