package nds.frm.message;

import java.io.Serializable;
import java.util.HashMap;

public class MessageXmlVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3032930788089221932L;
	private static HashMap hashMap;
	
	public MessageXmlVO(HashMap hMap) {
		super();
	    hashMap = hMap;
	}
	
	public static String getMessageXml(String key){
	    return (String)hashMap.get(key);
	}
}