/**
 * 축산물 이력제 사이트 포장실적 신고 detail(이력/묶음 구성정보)
 * 
 */
package nds.mpm.prod.PP0802.vo;

public class OpenapiPighisPackdetailT3VO {
	
	//이력.묶음 구성 정보(T3)
	String inType; //이력/묶음구성정보구분(T3)
	String packingYmd; //포장일자
	String pig_lot_No; //이력번호
	String butCheryNo; //도체번호

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

	public String getButCheryNo() {
		return butCheryNo;
	}

	public void setButCheryNo(String butCheryNo) {
		this.butCheryNo = butCheryNo;
	}
	
}
