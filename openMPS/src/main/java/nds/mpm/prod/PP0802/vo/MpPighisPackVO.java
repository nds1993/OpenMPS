/**
 * *테스트용*
 * 이력 신고 테스트를 위해 생성한 축산물 이력 포장실적 테이블(MP_PIGHIS_PACK_M) 객체 class
 * 
 * *개발시 제거해야 합니다!!!
 */
package nds.mpm.prod.PP0802.vo;

public class MpPighisPackVO {

	String corp_code;
	String pack_date;
	String bunch_no;
	String base_part_code;
	String base_part_name;
	double pack_weig;
	String memo;
	String api_time;
	String dele_yn;
	String md_user;
	String md_date;
	String cr_user;
	String cr_date;
	
	public String getCorp_code() {
		return corp_code;
	}
	public void setCorp_code(String corp_code) {
		this.corp_code = corp_code;
	}
	public String getPack_date() {
		return pack_date;
	}
	public void setPack_date(String pack_date) {
		this.pack_date = pack_date;
	}
	public String getBunch_no() {
		return bunch_no;
	}
	public void setBunch_no(String bunch_no) {
		this.bunch_no = bunch_no;
	}
	public String getBase_part_code() {
		return base_part_code;
	}
	public void setBase_part_code(String base_part_code) {
		this.base_part_code = base_part_code;
	}
	public String getBase_part_name() {
		return base_part_name;
	}
	public void setBase_part_name(String base_part_name) {
		this.base_part_name = base_part_name;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getApi_time() {
		return api_time;
	}
	public void setApi_time(String api_time) {
		this.api_time = api_time;
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
	public String getMd_date() {
		return md_date;
	}
	public void setMd_date(String md_date) {
		this.md_date = md_date;
	}
	public String getCr_user() {
		return cr_user;
	}
	public void setCr_user(String cr_user) {
		this.cr_user = cr_user;
	}
	public String getCr_date() {
		return cr_date;
	}
	public void setCr_date(String cr_date) {
		this.cr_date = cr_date;
	}
	public double getPack_weig() {
		return pack_weig;
	}
	public void setPack_weig(double pack_weig) {
		this.pack_weig = pack_weig;
	}
	
	
}
