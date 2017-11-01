/**
 * 축산물 이력제 사이트 포장실적 신고 Header (포장정보)
 * 
 */
package nds.mpm.prod.PP0802.vo;

public class OpenapiPighisPackheaderVO implements Cloneable {
	//포장정보(T1)
	String inType; //포장정보구분(T1)
	String packingYmd; //포장일자
	String pig_lot_No; //이력번호
	String brand; //브랜드
	String inCropNm; //매입처상호
	String inCropNo; //매입처사업자등록번호
	String gradeCd; //등급코드
	String callCd; //신고구분
	
	public String getInType() {
		return inType;
	}
	public void setInType(String inType) {
		this.inType = inType;
	}
	public String getPackingYmd() {
		return packingYmd;
	}
	public void setPackingYmd(String packingYmd) {
		this.packingYmd = packingYmd;
	}
	public String getPig_lot_No() {
		return pig_lot_No;
	}
	public void setPig_lot_No(String pig_lot_No) {
		this.pig_lot_No = pig_lot_No;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getInCropNm() {
		return inCropNm;
	}
	public void setInCropNm(String inCropNm) {
		this.inCropNm = inCropNm;
	}
	public String getInCropNo() {
		return inCropNo;
	}
	public void setInCropNo(String inCropNo) {
		this.inCropNo = inCropNo;
	}
	public String getGradeCd() {
		return gradeCd;
	}
	public void setGradeCd(String gradeCd) {
		this.gradeCd = gradeCd;
	}
	public String getCallCd() {
		return callCd;
	}
	public void setCallCd(String callCd) {
		this.callCd = callCd;
	}
	
	@Override
	public OpenapiPighisPackheaderVO clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		OpenapiPighisPackheaderVO header = (OpenapiPighisPackheaderVO)super.clone();
		header.inType = this.inType; //포장정보구분(T1)
		header.packingYmd = this.packingYmd; //포장일자
		header.pig_lot_No = this.pig_lot_No; //이력번호
		header.brand = this.brand; //브랜드
		header.inCropNm = this.inCropNm; //매입처상호
		header.inCropNo = this.inCropNo; //매입처사업자등록번호
		header.gradeCd = this.gradeCd; //등급코드
		header.callCd = this.callCd; //신고구분.
		
		return header;
	}
}
