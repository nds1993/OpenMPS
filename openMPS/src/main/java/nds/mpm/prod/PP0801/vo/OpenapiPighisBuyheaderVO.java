/**
 * 축산물 이력제 사이트 매입실적 신고 Header(매입정보)
 * 
 */
package nds.mpm.prod.PP0801.vo;

public class OpenapiPighisBuyheaderVO implements Cloneable {
	
	//매입정보(Header)
	String inType;	//매입정보구분(T1(지육))
	String inYmd;	//매입일자
	String inputTypeNm;	//이력/묶음구분명
	String lotNo;	//묶음번호
	String pigNo;	//이력번호
	String gradeCd;	//등급
	String inCorpNo;	//매입처사업자등록번호
	String inCorpNm;	//매입처상호
	String inCorpOwnerNm;	//대표자
	String inCorpTel;	//연락처
	String inCorpAddr;	//매입처주소
	String inputPlaceNm;	//매입처유형
	String pigTypeNm;	//지육/정육구분명
	String inTypeNm;	//매입구분(자체매입분/외부매입분)
	String totWeight;	//총중량
	
	
	public String getInType() {
		return inType;
	}
	public void setInType(String inType) {
		this.inType = inType;
	}
	public String getInYmd() {
		return inYmd;
	}
	public void setInYmd(String inYmd) {
		this.inYmd = inYmd;
	}
	public String getInputTypeNm() {
		return inputTypeNm;
	}
	public void setInputTypeNm(String inputTypeNm) {
		this.inputTypeNm = inputTypeNm;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getPigNo() {
		return pigNo;
	}
	public void setPigNo(String pigNo) {
		this.pigNo = pigNo;
	}
	public String getGradeCd() {
		return gradeCd;
	}
	public void setGradeCd(String gradeCd) {
		this.gradeCd = gradeCd;
	}
	public String getInCorpNo() {
		return inCorpNo;
	}
	public void setInCorpNo(String inCorpNo) {
		this.inCorpNo = inCorpNo;
	}
	public String getInCorpNm() {
		return inCorpNm;
	}
	public void setInCorpNm(String inCorpNm) {
		this.inCorpNm = inCorpNm;
	}
	public String getInCorpOwnerNm() {
		return inCorpOwnerNm;
	}
	public void setInCorpOwnerNm(String inCorpOwnerNm) {
		this.inCorpOwnerNm = inCorpOwnerNm;
	}
	public String getInCorpTel() {
		return inCorpTel;
	}
	public void setInCorpTel(String inCorpTel) {
		this.inCorpTel = inCorpTel;
	}
	public String getInCorpAddr() {
		return inCorpAddr;
	}
	public void setInCorpAddr(String inCorpAddr) {
		this.inCorpAddr = inCorpAddr;
	}
	public String getInputPlaceNm() {
		return inputPlaceNm;
	}
	public void setInputPlaceNm(String inputPlaceNm) {
		this.inputPlaceNm = inputPlaceNm;
	}
	public String getPigTypeNm() {
		return pigTypeNm;
	}
	public void setPigTypeNm(String pigTypeNm) {
		this.pigTypeNm = pigTypeNm;
	}
	public String getInTypeNm() {
		return inTypeNm;
	}
	public void setInTypeNm(String inTypeNm) {
		this.inTypeNm = inTypeNm;
	}
	public String getTotWeight() {
		return totWeight;
	}
	public void setTotWeight(String totWeight) {
		this.totWeight = totWeight;
	}
	
	//객체 복사용
	public OpenapiPighisBuyheaderVO clone() throws CloneNotSupportedException {
		OpenapiPighisBuyheaderVO header = (OpenapiPighisBuyheaderVO)super.clone();
		header.inType = this.inType;	//매입정보구분(T1(지육))
		header.inYmd = this.inYmd;	//매입일자
		header.inputTypeNm = this.inputTypeNm;	//이력/묶음구분명
		header.lotNo = this.lotNo;	//묶음번호
		header.pigNo = this.pigNo;	//이력번호
		header.gradeCd = this.gradeCd;	//등급
		header.inCorpNo = this.inCorpNo;	//매입처사업자등록번호
		header.inCorpNm = this.inCorpNm;	//매입처상호
		header.inCorpOwnerNm = this.inCorpOwnerNm;	//대표자
		header.inCorpTel = this.inCorpTel;	//연락처
		header.inCorpAddr = this.inCorpAddr;	//매입처주소
		header.inputPlaceNm = this.inputPlaceNm;	//매입처유형
		header.pigTypeNm = this.pigTypeNm;	//지육/정육구분명
		header.inTypeNm = this.inTypeNm;	//매입구분(자체매입분/외부매입분)
		header.totWeight = this.totWeight;	//총중량
		
		return header;
	}
	
}
