package nds.mpm.sales.SD0405.vo;

import java.util.Comparator;
import java.util.Map;

/**
 * @Class Name : MpOrderUploadVO.java
 * @Description : MpOrderUpload VO class
 * @Modification Information
 *
 * @author ㅇ
 * @since ㅇ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class OrderListComparator implements Comparator<Map>{
	
	private String sort_key1;
	private String sort_key2;
	
	public OrderListComparator(String sort_key1, String sort_key2)
	{
		this.sort_key1 = sort_key1;
		this.sort_key2 = sort_key2;
	}
    public int compare(Map row1, Map row2)
    {
    	int ret = 0;
    	String ordrCust1 = (String)row1.get(this.sort_key1);
    	String ordrCust2 = (String)row2.get(this.sort_key1);
    	String proCode1 = (String)row1.get(this.sort_key2);
    	String proCode2 = (String)row2.get(this.sort_key2);
    	
    	//System.out.println("ordrCust1 : " + ordrCust1);
    	//System.out.println("ordrCust2 : " + ordrCust2);
    	
    	ret = ordrCust1.compareTo(ordrCust2);
    	if( ret == 0 )
    	{
    		ret = proCode1.compareTo(proCode2);
    	}
    	return ret;
    }
    
}
