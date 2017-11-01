package nds.frm.util;

/**
 * <p>Title: 암호화 처리를 위한 Utility</p>
 * <p>Description: 암호화 처리를 위한 Utility</p>
 * <p><b>Histroy<b></p>
 * <pre>      : 2009.06.11 초기작성(임진식)</pre>
 * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
 * @version 1.0
 */
public class EncryptUtil {
    public EncryptUtil() {
    }

    /**
     * 특정값을 암호화시켜서 그 암호화된 값을 리턴
     * @param strData String
     * @return String
     * 예제)	String User_id = "";
	 *      try{User_id = EncodeBySType(userId);} catch(Exception e) {User_id = null;}
     */
    public static String EncodeBySType(String strData){
        String strRet = null;
        strRet = Encrypt.com_Encode("xx:" + strData + ":nds");
        return strRet;
    }

    /**
     * 암호화된 값을 풀어서 값을 리턴
     * @param strData String
     * @return String
     * 예제)	String User_id = "";
	 *      try{User_id = DecodeBySType(userId);} catch(Exception e) {User_id = null;}
     */
    public static String DecodeBySType(String strData){
        String strRet = null;
        int e=0, d=0;
        strRet = Encrypt.com_Decode(strData);
        e = strRet.indexOf("xx:");
        d = strRet.indexOf(":nds");
        if(e > -1 && d > -1)strRet = strRet.substring(e+3, d);
        return strRet;
	}
}
