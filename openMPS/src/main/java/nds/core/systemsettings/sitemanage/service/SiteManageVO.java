package nds.core.systemsettings.sitemanage.service;

import nds.core.common.common.service.CommonObject;

public class SiteManageVO extends CommonObject{
	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	    /**
	     * PageNavigator : start Page
	     */
	    private int startNo;

	    /**
	     * PageNavigator : end Page
	     */
	    
	    private String siteCd;
	    
	    private String siteNm;
	    
	    private String url;
	    
	    private String useYn;
	    
	    private String temp01;
	    
	    private String temp02;
	    
	    private String temp03;
	    
	    private String regUser;
	    
	    private String regDttm;
	    
	    private String updtUser;
	    
	    private String updtDttm;

		public int getStartNo() {
			return startNo;
		}

		public void setStartNo(int startNo) {
			this.startNo = startNo;
		}

		public String getSiteCd() {
			return siteCd;
		}

		public void setSiteCd(String siteCd) {
			this.siteCd = siteCd;
		}

		public String getSiteNm() {
			return siteNm;
		}

		public void setSiteNm(String siteNm) {
			this.siteNm = siteNm;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getUseYn() {
			return useYn;
		}

		public void setUseYn(String useYn) {
			this.useYn = useYn;
		}

		public String getTemp01() {
			return temp01;
		}

		public void setTemp01(String temp01) {
			this.temp01 = temp01;
		}

		public String getTemp02() {
			return temp02;
		}

		public void setTemp02(String temp02) {
			this.temp02 = temp02;
		}

		public String getTemp03() {
			return temp03;
		}

		public void setTemp03(String temp03) {
			this.temp03 = temp03;
		}

		public String getRegUser() {
			return regUser;
		}

		public void setRegUser(String regUser) {
			this.regUser = regUser;
		}

		public String getRegDttm() {
			return regDttm;
		}

		public void setRegDttm(String regDttm) {
			this.regDttm = regDttm;
		}

		public String getUpdtUser() {
			return updtUser;
		}

		public void setUpdtUser(String updtUser) {
			this.updtUser = updtUser;
		}

		public String getUpdtDttm() {
			return updtDttm;
		}

		public void setUpdtDttm(String updtDttm) {
			this.updtDttm = updtDttm;
		}
}
