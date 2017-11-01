package nds.mpm.common.util;

public class EncodeUtil
{	
	/**
	 * String to Unicode
	 * 
	 * @param str		입력 문자열
	 * @return 			Unicode 반환 문자열
	 */
	public static String charToUnicode(String str)
	{
	    StringBuffer sb = new StringBuffer();

	    for (int i=0; i<str.length(); i++) {
	        char ch = str.charAt(i);

	        if ((ch >= 0x0020) && (ch <= 0x007e)) {
	            sb.append(ch);
	        } else {
	            sb.append("\\u");
	            String hex = Integer.toHexString(str.charAt(i) & 0xFFFF);

	            for (int j=0; j<(4-hex.length()); j++) {
	                sb.append("0");
	            }
	            sb.append(hex.toLowerCase());
	        }
	    }

	    return sb.toString();
	}
	
	/**
	 * 제공되는 byte 배열을 16 진수 문자열로 변환한다.
	 * 
	 * @param aInput	byte 배열
	 * @return			16 진수 문자열
	 */
	public static String hexEncode(byte[] aInput)
	{
	    StringBuilder	result = new StringBuilder();
	    byte			hexa = 0;
	    
	    for (int i = 0; i < aInput.length; ++i)
	    {
	    	hexa = aInput[i];
	    	result.append(_hexDigits[ ( hexa & 0xf0 ) >> 4 ]);
	    	result.append(_hexDigits[ hexa & 0x0f ]);
	    }
	    
	    return result.toString();
	}
	
	private final static char[]		_hexDigits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
}