package nds.mpm.prod.base.vo;

/**
 * @Class Name : MpYildxmVO.java
 * @Description : MpYildxm VO class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class SuyulTotalSumVO{
    private static final long serialVersionUID = 1L;
    
    /**
     * dusu, coalesce(sum(suyul_wei1),0) wei1, coalesce(sum(suyul_wei2),0) wei2, 
		       coalesce(sum(jiyuk_dusu),0) jiyuk, coalesce(sum(sum_dusu),0) sum_dusu, coalesce(sum(sum_wei1),0) sum_wei1, 
		       coalesce(sum(sum_wei2),0) sum_wei2, coalesce(sum(sum_jiyuk),0) sum_jiyuk
     * 
     * */
    private long dusu;
    private long wei1;
    private long wei2;
    private long jiyuk;
    private long sumDusu;
    private long sumWei1;
    private long sumWei2;
    private long sumJiyuk;
	public long getDusu() {
		return dusu;
	}
	public void setDusu(long dusu) {
		this.dusu = dusu;
	}
	public long getWei1() {
		return wei1;
	}
	public void setWei1(long wei1) {
		this.wei1 = wei1;
	}
	public long getWei2() {
		return wei2;
	}
	public void setWei2(long wei2) {
		this.wei2 = wei2;
	}
	public long getJiyuk() {
		return jiyuk;
	}
	public void setJiyuk(long jiyuk) {
		this.jiyuk = jiyuk;
	}
	public long getSumDusu() {
		return sumDusu;
	}
	public void setSumDusu(long sumDusu) {
		this.sumDusu = sumDusu;
	}
	public long getSumWei1() {
		return sumWei1;
	}
	public void setSumWei1(long sumWei1) {
		this.sumWei1 = sumWei1;
	}
	public long getSumWei2() {
		return sumWei2;
	}
	public void setSumWei2(long sumWei2) {
		this.sumWei2 = sumWei2;
	}
	public long getSumJiyuk() {
		return sumJiyuk;
	}
	public void setSumJiyuk(long sumJiyuk) {
		this.sumJiyuk = sumJiyuk;
	}
	
}
