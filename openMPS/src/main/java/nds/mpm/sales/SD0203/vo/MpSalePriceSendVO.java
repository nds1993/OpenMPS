package nds.mpm.sales.SD0203.vo;

import nds.mpm.common.vo.CommonFileVO;

/**
 * @Class Name : MpSalePriceVO.java
 * @Description : MpSalePrice VO class
 * @Modification Information
 *
 * @author d
 * @since d
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MpSalePriceSendVO extends CommonFileVO{
    private static final long serialVersionUID = 1L;
    
    private String custCode;
    private String custName;
    private String email ;
    private String faxNo ;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
    
    
    
}
