package nds.core.systemsettings.roleuser.service;

import nds.core.common.common.service.CommonObject;

public class UserVO extends CommonObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String usrTp;
	
	private String usrId;			//로그인 유저아이디
	private String usrNm;
	
    private String cstNo;
	private String cstNm;			//고객명	
    private String empNo;			//사번
    private String depCd;
    private String depNm;			//부서명
    private String depNmFull;      //풀-부서명
    private String innerYn;        //내부직원여부
    private String wkgd;
    private String pos;
    private String insCstType;
    
    //한우리 KID
    private String gwId;    
    //한우리에서 VOC로그인 IP
    private String requestIp;

    private String gen;
    private String birdt;
    private String chrgWrk;
    private String workStat;
    private String innrNo;
    private String id;
    private String pwd;
    private String ssoId;
    private String roleInfo;
    private String chmnEmail; // 로그인한 사람의 이메일 주소
    private String newPwd;

    private String regUser;
    private String regDd;
    private String updtUser;
    private String updtDd;

    private String userId;			//새올ID
    private String userSn;			//주민번호
    
    //사용자별 캠페인 건수 조회
    private int userCampReq;		//요청건수
    private int userCampRej;		//반송건수
    private int userCampIng;		//진행건수
    private int admCampMng;		//관리자확인요청건수
    
    public String getCstNo() {
        return cstNo;
    }

    public void setCstNo(String cstNo) {
        this.cstNo = cstNo;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getDepCd() {
        return depCd;
    }

    public void setDepCd(String depCd) {
        this.depCd = depCd;
    }

    public String getWkgd() {
        return wkgd;
    }

    public void setWkgd(String wkgd) {
        this.wkgd = wkgd;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getInsCstType() {
        return insCstType;
    }

    public void setInsCstType(String insCstType) {
        this.insCstType = insCstType;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getBirdt() {
        return birdt;
    }

    public void setBirdt(String birdt) {
        this.birdt = birdt;
    }

    public String getChrgWrk() {
        return chrgWrk;
    }

    public void setChrgWrk(String chrgWrk) {
        this.chrgWrk = chrgWrk;
    }

    public String getWorkStat() {
        return workStat;
    }

    public void setWorkStat(String workStat) {
        this.workStat = workStat;
    }

    public String getInnrNo() {
        return innrNo;
    }

    public void setInnrNo(String innrNo) {
        this.innrNo = innrNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    public String getSsoId() {
        return ssoId;
    }
    
    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public String getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(String roleInfo) {
        this.roleInfo = roleInfo;
    }

    public String getRegUser() {
        return regUser;
    }

    public void setRegUser(String regUser) {
        this.regUser = regUser;
    }

    public String getRegDd() {
        return regDd;
    }

    public void setRegDd(String regDd) {
        this.regDd = regDd;
    }

    public String getUpdtUser() {
        return updtUser;
    }

    public void setUpdtUser(String updtUser) {
        this.updtUser = updtUser;
    }

    public String getUpdtDd() {
        return updtDd;
    }

    public void setUpdtDd(String updtDd) {
        this.updtDd = updtDd;
    }

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getCstNm() {
		return cstNm;
	}

	public void setCstNm(String cstNm) {
		this.cstNm = cstNm;
	}

	public String getUsrNm() {
		return usrNm;
	}

	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}

	public String getDepNm() {
		return depNm;
	}

	public void setDepNm(String depNm) {
		this.depNm = depNm;
	}
	
    public String getDepNmFull() {
        return depNmFull;
    }
    
    public void setDepNmFull(String depNmFull) {
        this.depNmFull = depNmFull;
    }
    
    public String getInnerYn() {
        return innerYn;
    }
    
    public void setInnerYn(String innerYn) {
        this.innerYn = innerYn;
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserSn() {
		return userSn;
	}

	public void setUserSn(String userSn) {
		this.userSn = userSn;
	}

	public int getAdmCampMng() {
		return admCampMng;
	}

	public void setAdmCampMng(int admCampMng) {
		this.admCampMng = admCampMng;
	}

	public int getUserCampIng() {
		return userCampIng;
	}

	public void setUserCampIng(int userCampIng) {
		this.userCampIng = userCampIng;
	}

	public int getUserCampRej() {
		return userCampRej;
	}

	public void setUserCampRej(int userCampRej) {
		this.userCampRej = userCampRej;
	}

	public int getUserCampReq() {
		return userCampReq;
	}

	public void setUserCampReq(int userCampReq) {
		this.userCampReq = userCampReq;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

    public String getGwId() {
        return gwId;
    }

    public void setGwId(String gwId) {
        this.gwId = gwId;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }
    
    public String getChmnEmail() {
        return chmnEmail;
    }
    
    public void setChmnEmail(String chmnEmail) {
        this.chmnEmail = chmnEmail;
    }
    
    public String getUsrTp() {
        return usrTp;
    }
    
    public void setUsrTp(String usrTp) {
        this.usrTp = usrTp;
    }
    
}