package nds.core.common.common.service;

import java.io.Serializable;

import nds.frm.config.StaticConfig;



/**
 * <b>class : CommonObject </b>
 * <b>Class Description</b><br>
 * 
 * <b>History</b><br>
 * <pre>      : 2009.06.11 초기작성(임진식)</pre>
 * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
 * @version 1.0
 */
public class CommonObject implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommonObject(){
        this.debug = StaticConfig.PRODUCT_MODE;
    }
	
	private String seqno;

	/**
	 * paging을 위한 변수
	 */
	private int startNo;
	
	private int endNo;
	
	/**
	 * grid sorting을 위한 변수
	 */
    private String sidx;
    
    private String sord;
	
	/**
	 * 사용자 기본 변수
	 */
    private String userDeptCode;

    private String userAuthCd;

    private String userType;

    private String debug;


    public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
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

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getUserDeptCode() {
        return userDeptCode;
    }

    public void setUserDeptCode(String userDeptCode) {
        this.userDeptCode = userDeptCode;
    }

    public String getUserAuthCd() {
        return userAuthCd;
    }

    public void setUserAuthCd(String userAuthCd) {
        this.userAuthCd = userAuthCd;
    }
    
    public String getUserType() {
        return userType;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }
}