package nds.mpm.prod.PP0107.vo;

import java.sql.Timestamp;

import nds.mpm.common.vo.SearchCommonVO;

/**
 * @Class Name : TmPlatxmVO.java
 * @Description : TmPlatxm VO class
 * @Modification Information
 *
 * @author MPM
 * @since 2017
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class TmPlatWarhxmVO extends SearchCommonVO{
    private static final long serialVersionUID = 1L;
    
    String corp_code;
    String  plant_code;
    String  wh_code;
    String  etc01;
    String  etc02;
    String  etc03;
    String  etc04;
    String  etc05;
    String memo;
    String  use_yn;
    String  dele_yn;
    String  md_user;
    Timestamp  md_date;
    String  cr_user;
    Timestamp  cr_date;
	public String getCorp_code() {
		return corp_code;
	}
	public void setCorp_code(String corp_code) {
		this.corp_code = corp_code;
	}
	public String getPlant_code() {
		return plant_code;
	}
	public void setPlant_code(String plant_code) {
		this.plant_code = plant_code;
	}
	public String getWh_code() {
		return wh_code;
	}
	public void setWh_code(String wh_code) {
		this.wh_code = wh_code;
	}
	public String getEtc01() {
		return etc01;
	}
	public void setEtc01(String etc01) {
		this.etc01 = etc01;
	}
	public String getEtc02() {
		return etc02;
	}
	public void setEtc02(String etc02) {
		this.etc02 = etc02;
	}
	public String getEtc03() {
		return etc03;
	}
	public void setEtc03(String etc03) {
		this.etc03 = etc03;
	}
	public String getEtc04() {
		return etc04;
	}
	public void setEtc04(String etc04) {
		this.etc04 = etc04;
	}
	public String getEtc05() {
		return etc05;
	}
	public void setEtc05(String etc05) {
		this.etc05 = etc05;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getDele_yn() {
		return dele_yn;
	}
	public void setDele_yn(String dele_yn) {
		this.dele_yn = dele_yn;
	}
	public String getMd_user() {
		return md_user;
	}
	public void setMd_user(String md_user) {
		this.md_user = md_user;
	}
	public Timestamp getMd_date() {
		return md_date;
	}
	public void setMd_date(Timestamp md_date) {
		this.md_date = md_date;
	}
	public String getCr_user() {
		return cr_user;
	}
	public void setCr_user(String cr_user) {
		this.cr_user = cr_user;
	}
	public Timestamp getCr_date() {
		return cr_date;
	}
	public void setCr_date(Timestamp cr_date) {
		this.cr_date = cr_date;
	}
    
    
}
