package nds.core.common.popup.service;

import nds.core.common.common.service.CommonObject;

public class PopupVendorVO extends CommonObject{

	private static final long serialVersionUID = 1L;


	
	
	private String vendorNm;
	
	private String vendorCd;


	public String getVendorNm() {
		return vendorNm;
	}


	public void setVendorNm(String vendorNm) {
		this.vendorNm = vendorNm;
	}


	public String getVendorCd() {
		return vendorCd;
	}


	public void setVendorCd(String vendorCd) {
		this.vendorCd = vendorCd;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
