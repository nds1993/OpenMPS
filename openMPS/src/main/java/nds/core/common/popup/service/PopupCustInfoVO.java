/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2012-6-20 10:22:58
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* Change Revision
* ------------------------------
* Author   : NDS
* Date     : 2012-6-20 10:22:58
* Revision : 1.0   First release.
* -------------------------------
* Class Description
* -------------------------------
*
*/
package nds.core.common.popup.service;

import nds.core.common.common.service.CommonObject;

public class PopupCustInfoVO extends CommonObject{
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
     * 고객정보 searchVO & resultVO
     */
    private String cstNo;
    private String cstNm;           // searchVO
//    private String email;
    private String telno;
    private String hphon;
    private String gen;
    private String birdt;           // searchVO
    private String age;
    private String homepageId;
    private String postNo;
    private String addr;
    private String delYn;
    private String cstCoun;

    /**
     * KB고객조회 searchVO
     */
    private String kor_nm;
    private String hp_no;
    private String email;
    /**
     * KB고객조회 resultVO
     */
    private String user_id;
    private String birth_dt;
    private String cust_level;
    private String sgb;
    private String post;
    
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

	public String getCstNo() {
		return cstNo;
	}

	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
	}

	public String getCstNm() {
		return cstNm;
	}

	public void setCstNm(String cstNm) {
		this.cstNm = cstNm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getHphon() {
		return hphon;
	}

	public void setHphon(String hphon) {
		this.hphon = hphon;
	}

	public String getGen() {
		return gen;
	}

	public void setGen(String gen) {
		this.gen = gen;
	}

	public String getBirdt() {
		return birdt;
	}

	public void setBirdt(String birdt) {
		this.birdt = birdt;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getHomepageId() {
		return homepageId;
	}

	public void setHomepageId(String homepageId) {
		this.homepageId = homepageId;
	}

	public String getPostNo() {
		return postNo;
	}

	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getCstCoun() {
		return cstCoun;
	}

	public void setCstCoun(String cstCoun) {
		this.cstCoun = cstCoun;
	}

	public String getKor_nm() {
		return kor_nm;
	}

	public void setKor_nm(String kor_nm) {
		this.kor_nm = kor_nm;
	}

	public String getHp_no() {
		return hp_no;
	}

	public void setHp_no(String hp_no) {
		this.hp_no = hp_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getBirth_dt() {
		return birth_dt;
	}

	public void setBirth_dt(String birth_dt) {
		this.birth_dt = birth_dt;
	}

	public String getCust_level() {
		return cust_level;
	}

	public void setCust_level(String cust_level) {
		this.cust_level = cust_level;
	}

	public String getSgb() {
		return sgb;
	}

	public void setSgb(String sgb) {
		this.sgb = sgb;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}
    
}