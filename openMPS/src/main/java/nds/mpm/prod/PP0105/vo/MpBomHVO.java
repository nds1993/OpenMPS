package nds.mpm.prod.PP0105.vo;

/**
 * @Class Name : MpBomHVO.java
 * @Description : MpBomH VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpBomHVO extends MpBomHDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    /** plant_code */
    private java.lang.String plantCode;
    
    /** bom_code */
    private java.lang.String bomCode;
    
    /** bom_ver */
    private int bomVer;
    
    /** pro_code */
    private java.lang.String proCode;
    private java.lang.String proName;
    
    /** tot_weig */
    private double totWeig;
    
    /** box_weig */
    private double boxWeig;
    
    /** film_weig */
    private double filmWeig;
    
    /** etc_weig */
    private double etcWeig;
    
    /** change_list */
    private java.lang.String changeList;
    
    /** memo */
    private java.lang.String memo;
    
    /** use_yn */
    private java.lang.String useYn;
    
    /** dele_yn */
    private java.lang.String deleYn;
    
    /** md_user */
    private java.lang.String mdUser;
    
    /** md_date */
    private java.sql.Timestamp mdDate;
    
    /** cr_user */
    private java.lang.String crUser;
    
    /** cr_date */
    private java.sql.Timestamp crDate;
    
    public java.lang.String getCorpCode() {
        return this.corpCode;
    }
    
    public void setCorpCode(java.lang.String corpCode) {
        this.corpCode = corpCode;
    }
    
    public java.lang.String getPlantCode() {
        return this.plantCode;
    }
    
    public void setPlantCode(java.lang.String plantCode) {
        this.plantCode = plantCode;
    }
    
    public java.lang.String getBomCode() {
        return this.bomCode;
    }
    
    public void setBomCode(java.lang.String bomCode) {
        this.bomCode = bomCode;
    }
    
    public int getBomVer() {
        return this.bomVer;
    }
    
    public void setBomVer(int bomVer) {
        this.bomVer = bomVer;
    }
    
    public String getProCode() {
        return this.proCode;
    }
    
    public void setProCode(java.lang.String proCode) {
        this.proCode = proCode;
    }
    
    
    
    public double getTotWeig() {
		return totWeig;
	}

	public void setTotWeig(double totWeig) {
		this.totWeig = totWeig;
	}

	public double getBoxWeig() {
		return boxWeig;
	}

	public void setBoxWeig(double boxWeig) {
		this.boxWeig = boxWeig;
	}

	public double getFilmWeig() {
		return filmWeig;
	}

	public void setFilmWeig(double filmWeig) {
		this.filmWeig = filmWeig;
	}

	public double getEtcWeig() {
		return etcWeig;
	}

	public void setEtcWeig(double etcWeig) {
		this.etcWeig = etcWeig;
	}

	public java.lang.String getChangeList() {
        return this.changeList;
    }
    
    public void setChangeList(java.lang.String changeList) {
        this.changeList = changeList;
    }
    
    public java.lang.String getMemo() {
        return this.memo;
    }
    
    public void setMemo(java.lang.String memo) {
        this.memo = memo;
    }
    
    public java.lang.String getUseYn() {
        return this.useYn;
    }
    
    public void setUseYn(java.lang.String useYn) {
        this.useYn = useYn;
    }
    
    public java.lang.String getDeleYn() {
        return this.deleYn;
    }
    
    public void setDeleYn(java.lang.String deleYn) {
        this.deleYn = deleYn;
    }
    
    public java.lang.String getMdUser() {
        return this.mdUser;
    }
    
    public void setMdUser(java.lang.String mdUser) {
        this.mdUser = mdUser;
    }
    
    public java.sql.Timestamp getMdDate() {
        return this.mdDate;
    }
    
    public void setMdDate(java.sql.Timestamp mdDate) {
        this.mdDate = mdDate;
    }
    
    public java.lang.String getCrUser() {
        return this.crUser;
    }
    
    public void setCrUser(java.lang.String crUser) {
        this.crUser = crUser;
    }
    
    public java.sql.Timestamp getCrDate() {
        return this.crDate;
    }
    
    public void setCrDate(java.sql.Timestamp crDate) {
        this.crDate = crDate;
    }

	public java.lang.String getProName() {
		return proName;
	}

	public void setProName(java.lang.String proName) {
		this.proName = proName;
	}
    
}
