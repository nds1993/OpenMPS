package nds.mpm.common.util;


public class EnvUtil {
	
	public static String getEnv(String key){
		
		return java.lang.System.getProperty(key);

	}
	
	public static void setEnv(String key, String value){
		
		java.lang.System.setProperty(key, value);

	}
}
