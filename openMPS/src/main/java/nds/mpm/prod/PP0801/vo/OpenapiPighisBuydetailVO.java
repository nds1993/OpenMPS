/**
 * 축산물 이력제 사이트 매입실적 신고 Detail(부위정보)
 * 
 */
package nds.mpm.prod.PP0801.vo;

public class OpenapiPighisBuydetailVO {
	//부위정보(Detail)
	String inType;	//부위정보구분(T2(지육))
	String inYmd;	//매입일자
	String inputTypeNm;	//이력/묶음구분명
	String lotNo;	//묶음번호
	String pigNo;	//이력번호
	String butCheryNo;	//도체번호
	String partCd;	//부위코드
	String partNm;	//부위명
	String inWeight;	//중량
	String gradeCd;	//등급
	
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
	public String getInWeight() {
		return inWeight;
	}
	public void setInWeight(String inWeight) {
		this.inWeight = inWeight;
	}
	public String getGradeCd() {
		return gradeCd;
	}
	public void setGradeCd(String gradeCd) {
		this.gradeCd = gradeCd;
	}
	
	
}
