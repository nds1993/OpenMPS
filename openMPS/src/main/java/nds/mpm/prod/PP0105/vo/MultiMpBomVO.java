package nds.mpm.prod.PP0105.vo;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

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
public class MultiMpBomVO {
    private static final long serialVersionUID = 1L;
    
    private MpBomHVO head;
    private List<EgovMap> detail;
    
    private String dsType;
	public MpBomHVO getHead() {
		return head;
	}
	public void setHead(MpBomHVO head) {
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
