/**
 * 축산물 이력제 사이트 매입/매출/포장실적 신고 결과 정보
 *
 */
package nds.mpm.prod.PP0801.vo;

public class OpenapiPighisResultVO {
	
	//매입, 매출, 포장실적 공통
	String checkYn; // 성공 Y, 실패 N
	String returnCd; //결과 코드
	String checkMsg; //결과 메세지
	String pigNo; //실패 시 도체번호 (성공시에는 없음)
	
	public String getCheckYn() {
		return checkYn;
	}
	public void setCheckYn(String checkYn) {
		this.checkYn = checkYn;
	}
	public String getReturnCd() {
		return returnCd;
	}
	public void setReturnCd(String returnCd) {
		this.returnCd = returnCd;
	}
	public String getCheckMsg() {
		return checkMsg;
	}
	public void setCheckMsg(String checkMsg) {
		this.checkMsg = checkMsg;
	}
	public String getPigNo() {
		return pigNo;
	}
	public void setPigNo(String pigNo) {
		this.pigNo = pigNo;
	}
}
