/**
 * 
 */
package nds.mpm.common.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.exc.UnrecognizedPropertyException;

/**
 * JSON 관련 유용한 기능들을 제공한다.
 * @author dbongman
 *
 */
public class JsonUtil 
{
	/**
	 * 지정한 json 객체의 값에 접근하기 위해서 Map 으로 변환한다.
	 * 
	 * @param jsonObj		java object 
	 * @param publicOnly	
	 * @return
	 */
	public static Map<String,Object> convertToMap(Object jsonObj, boolean publicOnly)
	{
	    Class<? extends Object>	c1 = jsonObj.getClass();
	    Map<String, Object>		map = new HashMap<String, Object>();
	    Field[]					fields = c1.getDeclaredFields();
	    
	    for(int i = 0; i < fields.length; i++) 
	    {
	    	String name = fields[i].getName();
	    	try
	    	{
	    		if( publicOnly )
	    		{
	    			if(Modifier.isPublic(fields[i].getModifiers())) 
	    			{
	    				Object value;
	    				value = fields[i].get(jsonObj);
	    				map.put(name, value);
	    			}
	    		}
	    		else 
	    		{
	    			// 모든 필드 대상
	    			fields[i].setAccessible(true);
	    			Object value = fields[i].get(jsonObj);
	    			map.put(name, value);
	    		}
	    	}
	    	catch(IllegalArgumentException | IllegalAccessException e)
	    	{
	    		e.printStackTrace();
			}
	    } // end for
	    
	    return map;
	}
	
	public static String convertToJson(Object jsonObj)
	{
		ObjectMapper	om = new ObjectMapper();
		String			jsonStr = null;
		 
		try 
		{
			jsonStr = om.writeValueAsString( jsonObj );
		} 
		catch( JsonGenerationException e )
		{
			e.printStackTrace();
		} 
		catch( JsonMappingException e )
		{
		    e.printStackTrace();
		}
		catch( IOException e )
		{
		    e.printStackTrace();
		}
		
		return jsonStr;		
	}
	
	public static <T> T convertToObject(String jsonSrc, Class<T> type)
	{
		ObjectMapper	mapper = new ObjectMapper();
		T 				obj = null;
		
		try 
		{
			obj = mapper.readValue( jsonSrc, type );
		}
		catch(UnrecognizedPropertyException e)
		{
			e.printStackTrace();
		}
		catch(JsonParseException e)
		{
			e.printStackTrace();
		}
		catch(JsonMappingException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return obj;
	}
}
