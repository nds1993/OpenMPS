/**
 * *테스트용*
 * 이력 신고 테스트를 위해 생성한 축산물 이력 매출실적 테이블(MP_PIGHIS_SALE_M) 객체 class
 * 
 * *개발시 제거해야 합니다!!!
 */
package nds.mpm.prod.PP0803.vo;

public class MpPighisSaleVO implements Comparable<MpPighisSaleVO> {

	String corp_code;
	String out_code;
	String sale_date;
	String his_bunch_no;
	String cust_code;
	String cust_name;
	String cust_regno;
	String cust_owner;
	String cust_phone;
	String cust_addr;
	String no_gubun;
	String base_part_code;
	String base_part_name;
	double sale_weig;
	int sale_qty;
	String lc_yn;
	String memo;
	String api_time;
	String dele_yn;
	String md_user;
	String md_date;
	String cr_user;
	String cr_date;
	String gagong_no;
	
	public String getGagong_no() {
		return gagong_no;
	}
	public void setGagong_no(String gagong_no) {
		this.gagong_no = gagong_no;
	}
	public String getCorp_code() {
		return corp_code;
	}
	public void setCorp_code(String corp_code) {
		this.corp_code = corp_code;
	}
	public String getOut_code() {
		return out_code;
	}
	public void setOut_code(String out_code) {
		this.out_code = out_code;
	}
	public String getSale_date() {
		return sale_date;
	}
	public void setSale_date(String sale_date) {
		this.sale_date = sale_date;
	}
	public String getHis_bunch_no() {
		return his_bunch_no;
	}
	public void setHis_bunch_no(String his_bunch_no) {
		this.his_bunch_no = his_bunch_no;
	}
	public String getCust_code() {
		return cust_code;
	}
	public void setCust_code(String cust_code) {
		this.cust_code = cust_code;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_regno() {
		return cust_regno;
	}
	public void setCust_regno(String cust_regno) {
		this.cust_regno = cust_regno;
	}
	public String getCust_owner() {
		return cust_owner;
	}
	public void setCust_owner(String cust_owner) {
		this.cust_owner = cust_owner;
	}
	public String getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}
	public String getCust_addr() {
		return cust_addr;
	}
	public void setCust_addr(String cust_addr) {
		this.cust_addr = cust_addr;
	}
	public String getNo_gubun() {
		return no_gubun;
	}
	public void setNo_gubun(String no_gubun) {
		this.no_gubun = no_gubun;
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
	public double getSale_weig() {
		return sale_weig;
	}
	public void setSale_weig(double sale_weig) {
		this.sale_weig = sale_weig;
	}
	public int getSale_qty() {
		return sale_qty;
	}
	public void setSale_qty(int sale_qty) {
		this.sale_qty = sale_qty;
	}
	public String getLc_yn() {
		return lc_yn;
	}
	public void setLc_yn(String lc_yn) {
		this.lc_yn = lc_yn;
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
	
	// ArrayList에서 정렬을 위해 Override함.
	// 축산물 이력제 신고 시 ArrayList가 정렬이 되지 않았다면 
	// 판매 총중량이 다르게 중복된 자료가 올라갈 소지가 있음. 
	// 판매일자, 이력번호, 판매처사업자 등록번호를 비교
	@Override
	public int compareTo(MpPighisSaleVO o) {
		// TODO Auto-generated method stub
		if(this.sale_date.compareTo(o.getSale_date()) >= 0 
			&& this.his_bunch_no.compareTo(o.getHis_bunch_no()) > 0 
			&& this.cust_regno.compareTo(o.getCust_regno()) >= 0){
			
			return 1;
		}else if(this.sale_date.compareTo(o.getSale_date()) < 0 
				   || this.his_bunch_no.compareTo(o.getHis_bunch_no()) < 0
				   || this.cust_regno.compareTo(o.getCust_regno()) < 0){
			
			return -1;
		}			
		return 0;
	}
}
