package nds.frm.property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import nds.frm.exception.MainException;




/**
 * Property파일을 읽는 기능
 *   주의)Properties파일에 한글이 들어간 경우 깨져서 나올수 있으므로 한글을 사용하는 않는 것을 원칙으로 함.
 * @author drogonking
 */
public class PropertyUtil implements IPropertyUtil{		
	private ResourceBundle configInfo;
	private String propertyPath;
	
	public PropertyUtil(){
		propertyPath = Property.BIZBUILDERPROPERTY;
	}
	
	/**
	 * 비즈빌더의 정의된 위치의 Properties의 정보를 읽어서 해당 값을 Enumeration에 담아 리턴
	 * @return Enumeration
	 * @throws MainException
	 */
	public Enumeration getTTFrameProp() throws MainException{		
		Enumeration enumeration = null;
		try{
			configInfo = ResourceBundle.getBundle(propertyPath);			
			enumeration = configInfo.getKeys();
		}catch(Exception ex){
			
		}
		return enumeration;
	}

	/**
	 * 비즈빌더의 정의된 위치의 Properties의 정보를 읽어서 해당 키값에 맞는 값을 리턴한다.
	 * @param attrName
	 * @return String
	 * @throws MainException
	 */
	public String getTTFrameProp(String attrName){		
		configInfo = ResourceBundle.getBundle(propertyPath);
		
		// 해당 키값에 맞는 정보를 리턴한다.
		return configInfo.getString(attrName);		
	}
	
	/**
	 * 파일명(경로+파일명)을 받아서 Properties객체를 리턴
	 * @param filename
	 * @return
	 * @throws MainException
	 */
	public Properties getProperties(String filename) throws MainException{
		Properties props = new Properties();
		try{
			props.load(new FileInputStream(filename));
		}catch(FileNotFoundException fex){
			
		}catch(IOException iex){
			
		}catch(Exception ex){
			
		}
		return props;		
	}	
}
