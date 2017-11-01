package nds.frm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncyptShaUtil {
    
    public static String encryptSHA(String password)
    { 
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            
            for (int i = 0; i < byteData.length; i++) {
             sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sb.toString();
    }
}
