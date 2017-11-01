/**
 * 축산물 이력제 사이트 매출실적 신고 Header (매출정보)
 * 
 */
package nds.mpm.prod.PP0803.vo;

public class OpenapiPighisSaleheaderVO implements Cloneable  {

	//매출정보
	String outType; //매출정보구분(T1(지육))
	String outYmd; //반출일자
	String outTypeNm; //이력/묶음구분명
	String pigNo; //이력번호
	String lotNo; //묶음번호
	String gradeCd; //등급
	String outCorpNo; //판매처사업자등록번호
	String outCorpNm; //판매처상호
	String outCorpOwnerNm; //대표자
	String outCorpTel; //연락처
	String outCorpAddr; //판매처주소
	String outputPlaceNm; //판매처유형
	String processGbCd; //포장처리구분
	String totWeight; //총중량
	
	public String getOutType() {
		return outType;
	}
	public void setOutType(String outType) {
		this.outType = outType;
	}
	public String getOutYmd() {
		return outYmd;
	}
	public void setOutYmd(String outYmd) {
		this.outYmd = outYmd;
	}
	public String getOutTypeNm() {
		return outTypeNm;
	}
	public void setOutTypeNm(String outTypeNm) {
		this.outTypeNm = outTypeNm;
	}
	public String getPigNo() {
		return pigNo;
	}
	public void setPigNo(String pigNo) {
		this.pigNo = pigNo;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getGradeCd() {
		return gradeCd;
	}
	public void setGradeCd(String gradeCd) {
		this.gradeCd = gradeCd;
	}
	public String getOutCorpNo() {
		return outCorpNo;
	}
	public void setOutCorpNo(String outCorpNo) {
		this.outCorpNo = outCorpNo;
	}
	public String getOutCorpNm() {
		return outCorpNm;
	}
	public void setOutCorpNm(String outCorpNm) {
		this.outCorpNm = outCorpNm;
	}
	public String getOutCorpOwnerNm() {
		return outCorpOwnerNm;
	}
	public void setOutCorpOwnerNm(String outCorpOwnerNm) {
		this.outCorpOwnerNm = outCorpOwnerNm;
	}
	public String getOutCorpTel() {
		return outCorpTel;
	}
	public void setOutCorpTel(String outCorpTel) {
		this.outCorpTel = outCorpTel;
	}
	public String getOutCorpAddr() {
		return outCorpAddr;
	}
	public void setOutCorpAddr(String outCorpAddr) {
		this.outCorpAddr = outCorpAddr;
	}
	public String getOutputPlaceNm() {
		return outputPlaceNm;
	}
	public void setOutputPlaceNm(String outputPlaceNm) {
		this.outputPlaceNm = outputPlaceNm;
	}
	public String getProcessGbCd() {
		return processGbCd;
	}
	public void setProcessGbCd(String processGbCd) {
		this.processGbCd = processGbCd;
	}
	public String getTotWeight() {
		return totWeight;
	}
	public void setTotWeight(String totWeight) {
		this.totWeight = totWeight;
	}
	
	//객체 복사용
	public OpenapiPighisSaleheaderVO clone() throws CloneNotSupportedException {
		OpenapiPighisSaleheaderVO header = (OpenapiPighisSaleheaderVO)super.clone();
		
		header.outType = this.outType; //매출정보구분(T1(지육))
		header.outYmd = this.outYmd; //반출일자
		header.outTypeNm = this.outTypeNm; //이력/묶음구분명
		header.pigNo = this.pigNo; //이력번호
		header.lotNo = this.lotNo; //묶음번호
		header.gradeCd = this.gradeCd; //등급
		header.outCorpNo = this.outCorpNo; //판매처사업자등록번호
		header.outCorpNm = this.outCorpNm; //판매처상호
		header.outCorpOwnerNm = this.outCorpOwnerNm; //대표자
		header.outCorpTel = this.outCorpTel; //연락처
		header.outCorpAddr = this.outCorpAddr; //판매처주소
		header.outputPlaceNm = this.outputPlaceNm; //판매처유형
		header.processGbCd = this.processGbCd; //포장처리구분
		header.totWeight = this.totWeight; //총중량
		
		return header;
	}
}
