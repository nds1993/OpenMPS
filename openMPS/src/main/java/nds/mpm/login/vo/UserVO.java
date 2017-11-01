package nds.mpm.login.vo;



import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import nds.mpm.common.user.vo.UserInfoVO;


/**
 */
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String id;
	private String name;
	private String pwd;
	private String corpCode;
	private String corpName;
	private String ofceCode;
	private String ofceName;
	private String deptCode;
	private String deptName;
	private String teamCode;
	private String teamName;
	private String dutyCode;
	
	private String headCode;
	
	private String phone;
    
    /** mobile */
    private String mobile;
    
    private String addr;
    
    private String email;
    
    
	public String getDutyCode() {
		return dutyCode;
	}
	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getCorpCode() {
		return corpCode;
	}
	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getOfceCode() {
		return ofceCode;
	}
	public void setOfceCode(String ofceCode) {
		this.ofceCode = ofceCode;
	}
	public String getOfceName() {
		return ofceName;
	}
	public void setOfceName(String ofceName) {
		this.ofceName = ofceName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHeadCode() {
		return headCode;
	}
	public void setHeadCode(String headCode) {
		this.headCode = headCode;
	}
	
	
    	

}
