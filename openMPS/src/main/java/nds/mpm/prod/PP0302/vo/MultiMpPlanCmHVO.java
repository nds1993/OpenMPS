package nds.mpm.prod.PP0302.vo;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;


/**
 * @Class Name : MpPlanCmHVO.java
 * @Description : MpPlanCmH VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MultiMpPlanCmHVO {
    private static final long serialVersionUID = 1L;
    
    MpPlanCmHVO head;
    List<EgovMap> detail;
    private String dsType;
	public MpPlanCmHVO getHead() {
		return head;
	}
	public void setHead(MpPlanCmHVO head) {
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
