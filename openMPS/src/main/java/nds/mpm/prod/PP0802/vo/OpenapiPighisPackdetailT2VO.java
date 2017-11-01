/**
 * 축산물 이력제 사이트 포장실적 신고 detail(부위정보)
 * 
 */
package nds.mpm.prod.PP0802.vo;

public class OpenapiPighisPackdetailT2VO {
	
	//부위정보(T2)	
	String inType; //부위정보구분(T2)
	String packingYmd; //포장일자
	String pig_lot_No; //이력번호
	String partCd; //부위코드
	String partNm; //부위명
	String packingWeight;	//중량
	
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

	public String getPackingWeight() {
		return packingWeight;
	}

	public void setPackingWeight(String packingWeight) {
		this.packingWeight = packingWeight;
	}
}
