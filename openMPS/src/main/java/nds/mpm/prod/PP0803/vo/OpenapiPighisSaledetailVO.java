/**
 * 축산물 이력제 사이트 매출실적 신고 detail(부위정보)
 * 
 */
package nds.mpm.prod.PP0803.vo;

public class OpenapiPighisSaledetailVO {

	//부위정보
	String outType; //부위정보구분(T2(지육))
	String outYmd; //반출일자
	String outputTypeNm; //이력/묶음구분명
	String pigNo; //이력번호
	String lotNo; //묶음번호
	String butCheryNo; //도체번호
	String partCd; //부위코드
	String partNm; //부위명
	String outWeight; //중량
	String gradeCd; //등급
	
	
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
	public String getOutputTypeNm() {
		return outputTypeNm;
	}
	public void setOutputTypeNm(String outputTypeNm) {
		this.outputTypeNm = outputTypeNm;
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
	public String getButCheryNo() {
		return butCheryNo;
	}
	public void setButCheryNo(String butCheryNo) {
		this.butCheryNo = butCheryNo;
	}
	public String getPartCd() {
		return partCd;
	}
	public void setPartCd(String partCd) {
		this.partCd = partCd;
	}
	public String getPartNm() {
		return partNm;
	}
	public void setPartNm(String partNm) {
		this.partNm = partNm;
	}
	public String getOutWeight() {
		return outWeight;
	}
	public void setOutWeight(String outWeight) {
		this.outWeight = outWeight;
	}
	public String getGradeCd() {
		return gradeCd;
	}
	public void setGradeCd(String gradeCd) {
		this.gradeCd = gradeCd;
	}	
	
}
