package nds.mpm.sales.SD0205.vo;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpDiscPriceVO.java
 * @Description : MpDiscPrice VO class
 * @Modification Information
 *
 * @author n
 * @since n
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MultiMpDiscPriceVO {
    private static final long serialVersionUID = 1L;
    
    List<EgovMap> head;
    List<EgovMap> detail;
    
    String dsType;
    
    
	public List<EgovMap> getHead() {
		return head;
	}
	public void setHead(List<EgovMap> head) {
		this.head = head;
	}
	public List<EgovMap> getDetail() {
		return detail;
	}
	public void setDetail(List<EgovMap> detail) {
		this.detail = detail;
	}
	public String getDsType() {
		return dsType;
	}
	public void setDsType(String dsType) {
		this.dsType = dsType;
	}
    
    
}
