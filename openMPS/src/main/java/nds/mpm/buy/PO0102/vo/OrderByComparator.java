package nds.mpm.buy.PO0102.vo;

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
public class OrderByComparator implements Comparator<Map>{
	
	private String sort_key1;
	private String sort_key2;
	private String sort_key3;
	private String sort_key4;
	
	public OrderByComparator(String sort_key1, String sort_key2, String sort_key3, String sort_key4)
	{
		this.sort_key1 = sort_key1;
		this.sort_key2 = sort_key2;
		this.sort_key3 = sort_key3;
		this.sort_key4 = sort_key4;
	}
    public int compare(Map row1, Map row2)
    {
    	int ret = 0;
    	String buyType1 = (String)row1.get(this.sort_key1);
    	String buyType2 = (String)row2.get(this.sort_key1);
    	String custCode1 = (String)row1.get(this.sort_key2);
    	String custCode2 = (String)row2.get(this.sort_key2);
    	String brandCode1 = (String)row1.get(this.sort_key3);
    	String brandCode2 = (String)row2.get(this.sort_key3);
    	String repCust1 = (String)row1.get(this.sort_key4);
    	String repCust2 = (String)row2.get(this.sort_key4);
    	
    	ret = buyType1.compareTo(buyType2);
    	if( ret == 0 )
    	{
    		ret = custCode1.compareTo(custCode2);
    		if( ret == 0 )
        	{
        		ret = brandCode1.compareTo(brandCode2);
        		if( ret == 0 )
            	{
            		ret = repCust1.compareTo(repCust2);
            		
            	}
        	}
    	}
    	return ret;
    }
    
}
