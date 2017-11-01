/**
 * 축산물 이력제 매입/매출/포장실적 신고 공통
 * 
 * url, 계정, ApiKey 등을 담을 객체
 * 
 */
package nds.mpm.prod.PP0801.vo;

public class OpenapiCommonInfoVO {

	//
	String siteUrl; //OpenAPI URL
	String userId; //사용자ID
	String apiKey; //API 인증키
	String typeCode; //type (매입실적, 매출실적에서 사용함)
	
	public String getSiteUrl() {
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
}
