package nds.mpm.sales.SD0401.vo;

import java.util.List;

import nds.mpm.sales.SD0405.vo.MpOrderHVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpOrderHVO.java
 * @Description : MpOrderH VO class
 * @Modification Information
 *
 * @author m
 * @since m
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MultiSD0401VO {
    private static final long serialVersionUID = 1L;
    
    MpOrderHVO head;
    List<EgovMap> detail;
    
    String dsType;

	public MpOrderHVO getHead() {
		return head;
	}

	public void setHead(MpOrderHVO head) {
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
