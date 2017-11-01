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
package nds.core.operation.notice.service;

import java.io.Serializable;

import nds.core.common.common.service.CommonObject;

public class NoticeVO extends CommonObject implements Serializable{
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
     * 공지사항 searchVO
     */
    private String schStartDd = "00000000";
    
    private String schEndDd = "99999999";
    
    /**
     * 공지사항 searchVO & resultVO
     */
    private String schTitCntn; 
    private String contsId;
    private String dbrdCd;           // searchVO

    private String docNo;            // searchVO
    
    private String upDocNo;

    private String tit;              // searchVO

    private String cntn;

    private String inqCnt	= 	"0";

    private String downCnt	=	"0";
    
    private String atchfileCnt = "0";

    private String openYn;
    
    private String inoutDvn;
    
    private String outDvn;

    private String regUser;

    private String regDttm;

    private String updtUser;

    private String updtDttm;
    
    private String regUserNm;
    
    private String updtUserNm;
    
    private String depth;
    
    private String searchNum;  //총 조회건수 설정
    
    //comment
	private String cmntNo;

    private String regDd;

    private String updtDd;
    
    private String sidx;
    private String sord;

    private String fileYn;
    
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

    public String getDbrdCd() {
		return dbrdCd;
	}

	public void setDbrdCd(String dbrdCd) {
		this.dbrdCd = dbrdCd;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getSchStartDd() {
		return schStartDd;
	}

	public void setSchStartDd(String schStartDd) {
		this.schStartDd = schStartDd;
	}

	public String getSchEndDd() {
		return schEndDd;
	}

	public void setSchEndDd(String schEndDd) {
		this.schEndDd = schEndDd;
	}

	public String getSchTitCntn() {
		return schTitCntn;
	}

	public void setSchTitCntn(String schTitCntn) {
		this.schTitCntn = schTitCntn;
	}

	public String getContsId() {
		return contsId;
	}

	public void setContsId(String contsId) {
		this.contsId = contsId;
	}

	public String getUpDocNo() {
        return upDocNo;
    }

    public void setUpDocNo(String upDocNo) {
        this.upDocNo = upDocNo;
    }

    public String getTit() {
        return tit;
    }

    public void setTit(String tit) {
        this.tit = tit;
    }

    public String getCntn() {
        return cntn;
    }

    public void setCntn(String cntn) {
        this.cntn = cntn;
    }

    public String getInqCnt() {
        return inqCnt;
    }

    public void setInqCnt(String inqCnt) {
        this.inqCnt = inqCnt;
    }

    public String getDownCnt() {
        return downCnt;
    }

    public void setDownCnt(String downCnt) {
        this.downCnt = downCnt;
    }

    public String getOpenYn() {
        return openYn;
    }

    public void setOpenYn(String openYn) {
        this.openYn = openYn;
    }

    
    public String getInoutDvn() {
		return inoutDvn;
	}

	public void setInoutDvn(String inoutDvn) {
		this.inoutDvn = inoutDvn;
	}

	
	public String getOutDvn() {
		return outDvn;
	}

	public void setOutDvn(String outDvn) {
		this.outDvn = outDvn;
	}

	public String getRegUser() {
        return regUser;
    }

    public void setRegUser(String regUser) {
        this.regUser = regUser;
    }

    public String getUpdtUser() {
        return updtUser;
    }

    public void setUpdtUser(String updtUser) {
        this.updtUser = updtUser;
    }

	public String getRegUserNm() {
		return regUserNm;
	}

	public void setRegUserNm(String regUserNm) {
		this.regUserNm = regUserNm;
	}
	
    public String getUpdtUserNm() {
		return updtUserNm;
	}

	public void setUpdtUserNm(String updtUserNm) {
		this.updtUserNm = updtUserNm;
	}

	public void setRegDttm(String regDttm) {
        this.regDttm = regDttm;
    }

    public String getRegDttm() {
        return regDttm;
    }

    public void setUpdtDttm(String updtDttm) {
        this.updtDttm = updtDttm;
    }

    public String getUpdtDttm() {
        return updtDttm;
    }

    public void setAtchfileCnt(String atchfileCnt) {
        this.atchfileCnt = atchfileCnt;
    }

    public String getAtchfileCnt() {
        return atchfileCnt;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getDepth() {
        return depth;
    }

	public String getSearchNum() {
		return searchNum;
	}

	public void setSearchNum(String searchNum) {
		this.searchNum = searchNum;
	}

	public String getCmntNo() {
		return cmntNo;
	}

	public void setCmntNo(String cmntNo) {
		this.cmntNo = cmntNo;
	}

	public String getRegDd() {
		return regDd;
	}

	public void setRegDd(String regDd) {
		this.regDd = regDd;
	}

	public String getUpdtDd() {
		return updtDd;
	}

	public void setUpdtDd(String updtDd) {
		this.updtDd = updtDd;
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

	public String getFileYn() {
		return fileYn;
	}

	public void setFileYn(String fileYn) {
		this.fileYn = fileYn;
	}
    
}