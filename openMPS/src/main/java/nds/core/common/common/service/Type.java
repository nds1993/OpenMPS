package nds.core.common.common.service;

public class Type {


	private String code;	// 코드
	private String name;	// 코드명
	private String note;	// 비고
	
	public Type(String code, String name, String note) {
		this.name = name;
		this.code = code;
		this.note = note;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}

