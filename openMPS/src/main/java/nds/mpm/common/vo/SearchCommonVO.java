/**
 * 
 */
package nds.mpm.common.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 
 *
 */
public class SearchCommonVO implements Serializable
{
	public SearchCommonVO()
	{
		
	}
	/** 검색조건2 */
    private String searchCondition2 = null;
    /** 검색조건3 */
    private String searchCondition3 = null;
    private String searchCondition4 = null;
    /** 검색Keyword2 */
    private String searchKeyword2 = null;
	
	private String strtDate;
    private String lastDate;
    
    private String bizCode;
    private String corpCode;
    private String custCode;
    private String custName;
    private String largeCode;
    
    private String proCode;
    private List<String> proCodes;
    
    private String userCode;
    private String headCode;
    private String partCode;
    private String teamCode;
    
    private String salesman;
    //직책
    private String ofceCode;
    /***
     * 권한레벨값. ibatis level 조건공통. 
     * 1 > 2 > 3 ...
     * default level3
     */
    private int salesmanLevel = 3;
    
    private int	pageIndex;
    private int	pageSize;
    private int	startOrder;
    
    private String custArrString;
    private List<String> custCodes;
    
    private String dsType;
    
    
    private String junStrtDate;
    private String junLastDateBe;
    private String dangStrtDateNo;
    private String dangLastDateNo;
    
    
    
	public String getPartCode() {
		return partCode;
	}
	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}
	public String getSearchCondition4() {
		return searchCondition4;
	}
	public void setSearchCondition4(String searchCondition4) {
		this.searchCondition4 = searchCondition4;
	}
	public String getHeadCode() {
		return headCode;
	}
	public void setHeadCode(String headCode) {
		this.headCode = headCode;
	}
	public String getLargeCode() {
		return largeCode;
	}
	public void setLargeCode(String largeCode) {
		this.largeCode = largeCode;
	}
	public String getBizCode() {
		return bizCode;
	}
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	public String getCorpCode() {
		return corpCode;
	}
	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}
	public String getSearchCondition2() {
		return searchCondition2;
	}
	public void setSearchCondition2(String searchCondition2) {
		this.searchCondition2 = searchCondition2;
	}
	public String getSearchKeyword2() {
		return searchKeyword2;
	}
	public void setSearchKeyword2(String searchKeyword2) {
		this.searchKeyword2 = searchKeyword2;
	}
	
	public String getStrtDate() {
		return strtDate;
	}
	public void setStrtDate(String strtDate) {
		this.strtDate = strtDate;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public String getSearchCondition3() {
		return searchCondition3;
	}
	public void setSearchCondition3(String searchCondition3) {
		this.searchCondition3 = searchCondition3;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public int getStartOrder() {
		return startOrder;
	}
	public void setStartOrder(int startOrder) {
		this.startOrder = startOrder;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		
		//System.out.println("pageIndex :: " + pageIndex);
		//System.out.println("this.pageSize :: " + this.pageSize);
		
		if(pageIndex > 0 && this.pageSize > 0)
		{
			this.startOrder = (pageIndex - 1) * this.pageSize;
		}
		//System.out.println("this.startOrder :: " + this.startOrder);
		
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getCustArrString() {
		return custArrString;
	}
	public void setCustArrString(String custArrString) {
		if(custArrString != null)
		{
			String[] codes = custArrString.split("[|]");
			List<String> setcodes = new ArrayList();
			for(String item : codes)
			{
				setcodes.add(item);
			}
			
			this.custCodes = setcodes;
		}
		this.custArrString = custArrString;
	}
	public List<String> getCustCodes() {
		return custCodes;
	}
	public void setCustCodes(List<String> custCodes) {
		this.custCodes = custCodes;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getDsType() {
		return dsType;
	}
	public void setDsType(String dsType) {
		this.dsType = dsType;
	}
	public String getJunStrtDate() {
		return junStrtDate;
	}
	public void setJunStrtDate(String junStrtDate) {
		this.junStrtDate = junStrtDate;
	}
	public String getJunLastDateBe() {
		return junLastDateBe;
	}
	public void setJunLastDateBe(String junLastDateBe) {
		this.junLastDateBe = junLastDateBe;
	}
	public String getDangStrtDateNo() {
		return dangStrtDateNo;
	}
	public void setDangStrtDateNo(String dangStrtDateNo) {
		this.dangStrtDateNo = dangStrtDateNo;
	}
	public String getDangLastDateNo() {
		return dangLastDateNo;
	}
	public void setDangLastDateNo(String dangLastDateNo) {
		this.dangLastDateNo = dangLastDateNo;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public String getOfceCode() {
		return ofceCode;
	}
	public void setOfceCode(String ofceCode) {
		this.ofceCode = ofceCode;
	}
	public int getSalesmanLevel() {
		return salesmanLevel;
	}
	public void setSalesmanLevel(int salesmanLevel) {
		this.salesmanLevel = salesmanLevel;
	}
	public List<String> getProCodes() {
		return proCodes;
	}
	public void setProCodes(List<String> proCodes) {
		this.proCodes = proCodes;
	}
    
    
}
