package nds.core.common.message;

import nds.core.common.message.service.BaseMessageVO;


public class BaseSmsVO extends BaseMessageVO {

	
	private String contSeq;   // mms번호
	
	private String atchType1;
	private String atchType2;
	private String atchType3;
	private String atchType4;
	private String atchType5;
	private String atchFile1;
	private String atchFile2;
	private String atchFile3;
	private String atchFile4;
	private String atchFile5;
	private int atchCnt = 0;
	
	
	public int getMmsDvn() {
		return (getMsgCntn().getBytes().length>80 || atchCnt>1 )?1:0;
	}
	public String getContSeq() {
		return contSeq;
	}
	public void setContSeq(String contSeq) {
		this.contSeq = contSeq;
	}
	public String getFileCnt() {
		return atchCnt+"";
	}
	public String getAtchType1() {
		return atchType1;
	}
	public void setAtchType1(String atchType1) {
		this.atchType1 = atchType1;
	}
	public String getAtchType2() {
		return atchType2;
	}
	public void setAtchType2(String atchType2) {
		this.atchType2 = atchType2;
	}
	public String getAtchType3() {
		return atchType3;
	}
	public void setAtchType3(String atchType3) {
		this.atchType3 = atchType3;
	}
	public String getAtchType4() {
		return atchType4;
	}
	public void setAtchType4(String atchType4) {
		this.atchType4 = atchType4;
	}
	public String getAtchType5() {
		return atchType5;
	}
	public void setAtchType5(String atchType5) {
		this.atchType5 = atchType5;
	}
	public String getAtchFile1() {
		return atchFile1;
	}
	public void setAtchFile1(String atchFile1) {
		this.atchFile1 = atchFile1;
		if (null!=this.atchFile1) this.atchCnt++;
	}
	public String getAtchFile2() {
		return atchFile2;
	}
	public void setAtchFile2(String atchFile2) {
		this.atchFile2 = atchFile2;
		if (null!=this.atchFile2) this.atchCnt++;
	}
	public String getAtchFile3() {
		return atchFile3;
	}
	public void setAtchFile3(String atchFile3) {
		this.atchFile3 = atchFile3;
		if (null!=this.atchFile3) this.atchCnt++;
	}
	public String getAtchFile4() {
		return atchFile4;
	}
	public void setAtchFile4(String atchFile4) {
		this.atchFile4 = atchFile4;
		if (null!=this.atchFile4) this.atchCnt++;
	}
	public String getAtchFile5() {
		return atchFile5;
	}
	public void setAtchFile5(String atchFile5) {
		this.atchFile5 = atchFile5;
		if (null!=this.atchFile5) this.atchCnt++;
	}
	
	public void setMsgCntn(String msgCntn) {
		this.atchCnt++;
		super.setMsgCntn(msgCntn);
	}
	
	

	
	
	
}
