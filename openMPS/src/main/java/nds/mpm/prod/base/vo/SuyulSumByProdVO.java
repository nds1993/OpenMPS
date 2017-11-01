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
public class SuyulSumByProdVO{
    private static final long serialVersionUID = 1L;
    
    /**
     * select p.meat_yn, sum(냉장박스) jangbox1
     * , sum(냉장중량) jangweig1
     * , sum(냉동박스) dongbox1, 
		 sum(냉동중량) dongweig1
		 , sum(냉장박스)+sum(냉동박스) sumbox1
		 , sum(냉장중량)+sum(냉동중량) sumweig1, 
		 sum(누계박스) nubox1, sum(누계중량) nuweig1 
     * 
     * */
    private String meatYn;
    private long jangbox1;
    private long jangweig1;
    private long dongbox1;
    private long dongweig1;
    private long sumbox1;
    private long sumweig1;
    private long nuweig1;
	public String getMeatYn() {
		return meatYn;
	}
	public void setMeatYn(String meatYn) {
		this.meatYn = meatYn;
	}
	public long getJangbox1() {
		return jangbox1;
	}
	public void setJangbox1(long jangbox1) {
		this.jangbox1 = jangbox1;
	}
	public long getJangweig1() {
		return jangweig1;
	}
	public void setJangweig1(long jangweig1) {
		this.jangweig1 = jangweig1;
	}
	public long getDongbox1() {
		return dongbox1;
	}
	public void setDongbox1(long dongbox1) {
		this.dongbox1 = dongbox1;
	}
	public long getDongweig1() {
		return dongweig1;
	}
	public void setDongweig1(long dongweig1) {
		this.dongweig1 = dongweig1;
	}
	public long getSumbox1() {
		return sumbox1;
	}
	public void setSumbox1(long sumbox1) {
		this.sumbox1 = sumbox1;
	}
	public long getSumweig1() {
		return sumweig1;
	}
	public void setSumweig1(long sumweig1) {
		this.sumweig1 = sumweig1;
	}
	public long getNuweig1() {
		return nuweig1;
	}
	public void setNuweig1(long nuweig1) {
		this.nuweig1 = nuweig1;
	}
    
    
}
