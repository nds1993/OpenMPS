package nds.mpm.prod.PP0201.vo;

/**
 * @Class Name : MpYieldInfoMVO.java
 * @Description : MpYieldInfoM VO class
 * @Modification Information
 *
 * @author M
 * @since M
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpYieldInfoMVO extends MpYieldInfoMDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** corp_code */
    private java.lang.String corpCode;
    
    private java.lang.String grupPlant;
    public java.lang.String getGrupPlant() {
		return grupPlant;
	}

	public void setGrupPlant(java.lang.String grupPlant) {
		this.grupPlant = grupPlant;
	}

	/** plant_code */
    private java.lang.String plantCode;
    
    /** work_date */
    private String workDate;
    
    private int seq;
    
    /** suyul_dusu */
    private java.math.BigDecimal suyulDusu;
    
    /** suyul_wei1 */
    private java.math.BigDecimal suyulWei1;
    
    /** suyul_wei2 */
    private java.math.BigDecimal suyulWei2;
    
    /** jiyuk_dusu */
    private java.math.BigDecimal jiyukDusu;
    
    /** dusu1 */
    private java.math.BigDecimal dusu1;
    
    /** dusu2 */
    private java.math.BigDecimal dusu2;
    
    /** dusu3 */
    private java.math.BigDecimal dusu3;
    
    private java.math.BigDecimal docheQty1;
    private java.math.BigDecimal docheQty2;
    private java.math.BigDecimal docheQty3;
    
    /** memo */
    private java.lang.String memo;
    
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
    
    public String getWorkDate() {
        return this.workDate;
    }
    
    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }
    
    public java.math.BigDecimal getSuyulDusu() {
        return this.suyulDusu;
    }
    
    public void setSuyulDusu(java.math.BigDecimal suyulDusu) {
        this.suyulDusu = suyulDusu;
    }
    
    public java.math.BigDecimal getSuyulWei1() {
        return this.suyulWei1;
    }
    
    public void setSuyulWei1(java.math.BigDecimal suyulWei1) {
        this.suyulWei1 = suyulWei1;
    }
    
    public java.math.BigDecimal getSuyulWei2() {
        return this.suyulWei2;
    }
    
    public void setSuyulWei2(java.math.BigDecimal suyulWei2) {
        this.suyulWei2 = suyulWei2;
    }
    
    public java.math.BigDecimal getJiyukDusu() {
        return this.jiyukDusu;
    }
    
    public void setJiyukDusu(java.math.BigDecimal jiyukDusu) {
        this.jiyukDusu = jiyukDusu;
    }
    
    public java.math.BigDecimal getDusu1() {
        return this.dusu1;
    }
    
    public void setDusu1(java.math.BigDecimal dusu1) {
        this.dusu1 = dusu1;
    }
    
    public java.math.BigDecimal getDusu2() {
        return this.dusu2;
    }
    
    public void setDusu2(java.math.BigDecimal dusu2) {
        this.dusu2 = dusu2;
    }
    
    public java.math.BigDecimal getDusu3() {
        return this.dusu3;
    }
    
    public void setDusu3(java.math.BigDecimal dusu3) {
        this.dusu3 = dusu3;
    }
    
    public java.lang.String getMemo() {
        return this.memo;
    }
    
    public void setMemo(java.lang.String memo) {
        this.memo = memo;
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

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public java.math.BigDecimal getDocheQty1() {
		return docheQty1;
	}

	public void setDocheQty1(java.math.BigDecimal docheQty1) {
		this.docheQty1 = docheQty1;
	}

	public java.math.BigDecimal getDocheQty2() {
		return docheQty2;
	}

	public void setDocheQty2(java.math.BigDecimal docheQty2) {
		this.docheQty2 = docheQty2;
	}

	public java.math.BigDecimal getDocheQty3() {
		return docheQty3;
	}

	public void setDocheQty3(java.math.BigDecimal docheQty3) {
		this.docheQty3 = docheQty3;
	}
    
}
