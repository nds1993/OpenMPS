package nds.frm.util;

//java API
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import nds.core.common.common.service.NotiUserInfoVO;
import nds.frm.exception.MainException;


/**
 * <b>class : </b> StringUtil
 * <b>Class Description</b><br>
 * String에 관한 처리를 담당하는 Util Class
 * <b>History</b><br>
 * <pre>      : 2009.06.11 초기작성(임진식)</pre>
 * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
 * @version 1.0
 */
public class StringUtil {

    public StringUtil() {}

    public static String getGetterMethodName(String property) {
        StringBuffer sb = new StringBuffer();

        sb.append(property);
        if (Character.isLowerCase(sb.charAt(0))) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }

        sb.insert(0, "get");

        return sb.toString();
    }

    public static String getSetterMethodName(String property) {
        StringBuffer sb = new StringBuffer();

        sb.append(property);
        if (Character.isLowerCase(sb.charAt(0))) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }

        sb.insert(0, "set");

        return sb.toString();
    }
    
    /**
     * CAMELCASE 문자열로 변환해주는 메소드
     * 
     * @param inputString 대상문자열
     * @param firstCharacterUppercase 첫째자리 문자 대문자 표기유무
     */
    public static String getCamelCaseString(String inputString, boolean firstCharacterUppercase) {
        StringBuffer sb = new StringBuffer();

        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);

            switch (c) {
            case '_':
            case '-':
                if (sb.length() > 0) {
                    nextUpperCase = true;
                }
                break;

            default:
                if (nextUpperCase) {
                    sb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
                break;
            }
        }

        if (firstCharacterUppercase) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }

        return sb.toString();
    }

    /**
     * request key값이 Null일때 "" 값으로 치환해주는 메소드
     *
     * @param req     HttpServletRequest
     * @param key   request 받을 값
     * @return          치환된 문자열
     */    
    public static String getParam(HttpServletRequest req, String key){
        return getParam(req,key,"");
    }
    
    /**
     * request key값이 Null일때 initValue 값으로 치환해주는 메소드
     *
     * @param req     HttpServletRequest
     * @param key	request 받을 값
     * @param initValue	치환될 문자	 
     * @return          치환된 문자열
     */    
    public static String getParam(HttpServletRequest req, String key, String initValue)
    {
    	String value = req.getParameter(key);
    	if (value == null || value.equals(""))
    		return initValue;
    	else
    		return value;
    }
    
    /**
     * request key값이 Null일때 initValue 값으로 치환해주는 메소드
     *
     * @param req     HttpServletRequest
     * @param key   request 받을 값
     * @param initValue 치환될 문자   
     * @return          치환된 문자열
     */    
    public static String getParamCvtEucKr(HttpServletRequest req, String key, String initValue)
    {
        String value = req.getParameter(key);
        try
        {
            if (value == null || value.equals(""))
                return initValue;
            else
                return new String(value.getBytes("8859_1"),"euc-kr");
        }
        catch(Exception e)
        {
            return "";
        }
    }
    
    /**
     * request key값이 Null일때 initValue 값으로 치환해주는 메소드
     *
     * @param req     HttpServletRequest
     * @param key   request 받을 값
     * @param initValue 치환될 문자   
     * @return          치환된 문자열
     */    
    public static String getParamCvtUtf8(HttpServletRequest req, String key, String initValue)
    {
        String value = req.getParameter(key);
        try
        {
            if (value == null || value.equals(""))
                return initValue;
            else
                return new String(value.getBytes("8859_1"),"UTF-8");
        }
        catch(Exception e)
        {
            return "";
        }
    }
    
    /**
     * null 값을 "" 형태로 치환해주는 메소드
     *
     * @param param     Object 유형의 치환 대상
     * @return          치환된 문자열
     */
    public static String null2void(Object param) {
        if (param == null)
            return "";
        if ( ( (String) param).trim().equals(""))
            return "";
        else
            return trim( ( (String) param).trim());
    }

    /**
     * null 값을 "" 형태로 치환해주는 메소드
     *
     * @param param     Object 유형의 치환 대상
     * @return          치환된 문자열
     */
    public static String null2String(Object param, String str) {
        if (param == null)
            return str;
        if ( ( (String) param).trim().equals(""))
            return str;
        else
            return trim( ( (String) param).trim());
    }
    
    /**
     * null값을 0으로 치환해주는 메소드
     *
     * @param param     Object 유형의 치환 대상
     * @return          치환된 숫자
     */
    public static String null2zeroString(Object param) {
        if (param == null)
            return "0";
        if ( ( (String) param).trim().equals(""))
            return "0";
        else
            return (String) param;
    }

    /**
     * null값을 0으로 치환해주는 메소드
     *
     * @param param     Object 유형의 치환 대상
     * @return          치환된 숫자
     */
    public static int null2zeroint(Object param) {
        if (param == null)
            return 0;
        if ( ( (String) param).trim().equals(""))
            return 0;
        else
            return Integer.parseInt( (String) param);
    }

    /**
     * null값을 0으로 치환해주는 메소드
     *
     * @param param     Object 유형의 치환 대상
     * @return          치환된 숫자
     */
    public static Integer null2zeroInt(Object param) {
        if (param == null)
            return Integer.getInteger("0");
        if ( ( (String) param).trim().equals(""))
            return Integer.getInteger("0");
        else
            return Integer.getInteger( (String) param);
    }

    /**
     * null값을 0으로 치환해주는 메소드
     *
     * @param param     Object 유형의 치환 대상
     * @return          치환된 숫자
     */
    public static long null2zerolong(Object param) {
        if (param == null)
            return 0;
        if ( ( (String) param).trim().equals(""))
            return 0;
        else
            return Long.parseLong( (String) param);
    }

    /**
     * null값을 0으로 치환해주는 메소드
     *
     * @param param     Object 유형의 치환 대상
     * @return          치환된 숫자
     */
    public static Long null2zeroLong(Object param) {
        if (param == null) {
            return Long.getLong("0");
        }

        if ( ( (String) param).trim().equals("")) {
            return Long.getLong("0");
        }
        else {
            return Long.getLong( (String) param);
        }
    }

    /**
     * 입력받은 문자열을 세자리마다 끊어 화폐단위표기형식으로 변환해주는 메소드
     *
     * @param str   처리대상 문자열
     * @return      화폐단위형식표기로 치환되어진 문자열
     */
    public static String getMoneyForm(String str) {
        if (str == null)
            return "";

        int offset = str.indexOf(".");
        String work_str = "";
        String nonwork_str = "";

        if (offset > 0) {
            work_str = str.substring(0, offset);
            nonwork_str = str.substring(offset, str.length());
        }
        else {
            work_str = str;
        }

        DecimalFormat df = new DecimalFormat("###,##0.##");
        double d = 0.0D;

        try {
            d = Double.valueOf(work_str).doubleValue();
        }
        catch (Exception e) {
            d = 0.0D;
        }
        return df.format(d) + nonwork_str;
    }
    
    /**
     * 입력받은 문자열을 세자리마다 끊어 화폐단위표기형식으로 변환해주는 메소드
     * 값이 없을 경우 "-"을 리턴
     * @param str   처리대상 문자열
     * @return      화폐단위형식표기로 치환되어진 문자열
     */
    public static String getNumberForm(String str) {
        if (str == null || str == "")
            return "-";
        
        int offset = str.indexOf(".");
        String work_str = "";
        String nonwork_str = "";

        if (offset > 0) {
            work_str = str.substring(0, offset);
            nonwork_str = str.substring(offset, str.length());
        }
        else {
            work_str = str;
        }

        DecimalFormat df = new DecimalFormat("###,##0.##");
        double d = 0.0D;

        try {
            d = Double.valueOf(work_str).doubleValue();
        }
        catch (Exception e) {
            d = 0.0D;
        }
        return df.format(d) + nonwork_str;
    }    

    /**
     * 소숫점이 들어간 str을 받아서 소숫점 아래 자리를 cnt수로 맞춰주는 메소드
     *
     * @param str       소숫점을 포함한 치환대상
     * @param cnt       치환이 될 소숫점 아래 자릿수
     * @return          str를 소숫점 아래 cnt자리로 맞춘 문자열
     */
    public static String getRateForm(String str, int cnt) {
        if (str == null || str.trim().equals("")) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        if (str.indexOf(".") < 0) {
            sb.append(".");
            for (; cnt > 0; cnt--) {
                sb.append("0");
            }
        }
        else {
            for (; cnt >= str.length() - str.indexOf("."); cnt--) {
                sb.append("0");
                //System.out.println(cnt);
            }
        }
        return sb.toString();
    }

    /**
     * 문자열을 입력받아 문자열 앞의 0을 제거하는 메소드
     *
     * @param str       치환대상 문자열
     * @return String   문자열의 앞에 포함된 0을 제거한 문자열
     */
    public static String rmZero(String str) {
        if (str == null)
            return "";

        char indexChr = ' ';
        int index = 0;
        while (index < str.length()) {
            if (str.charAt(index) == '0') {
                index++;
                continue;
            }
            indexChr = str.charAt(index);
            break;
        }
        if (index < str.length())
            return str.substring(indexChr != '.' ? index : index - 1);
        else
            return "0";
    }

    /**
     * 입력받은 문자열을 날짜 형식으로 변경시켜주는 메소드
     * @param acct      치환 대상 문자열
     * @return          날짜 형식으로 치환된 문자열
     */
    public static String getDateForm(String strDate) {
        if (strDate == null)
            return "";

        strDate = strDate.trim();
        if (strDate.length() < 8)
            return strDate;
        else
            return putDash(strDate.substring(0, 8), 4, 2);
    }
    
    /**
     * 입력받은 문자열을 날짜 형식으로 변경시켜주는 메소드
     * @param acct      치환 대상 문자열
     * @return          날짜 형식으로 치환된 문자열
     */
    public static String getDateFormSlash(String strDate) {
        if (strDate == null)
            return "";

        strDate = strDate.trim();
        if (strDate.length() < 8)
            return strDate;
        else
            return putChar(strDate.substring(0, 8), "/" ,4, 2);
    }    

    /**
     * 입력받은 문자열을 시간 형식으로 변경시켜주는 메소드
     * @param acct      치환 대상 문자열
     * @return          시간 형식으로 치환된 문자열
     */
    public static String getTimeForm(String strTime) {
        if (strTime == null)
            return "";

        strTime = strTime.trim();
        if (strTime.length() < 6)
            return strTime;
        else
            return putChar(strTime.substring(0, 6), ":", 2, 2);
    }


    /**
     * 입력받은 문자열을 시간 형식으로 변경시켜주는 메소드
     * @param acct      치환 대상 문자열
     * @return          시간 형식으로 치환된 문자열
     */
    public static String getTimeMinForm(String strTime) {
        if (strTime == null)
            return "";

        strTime = strTime.trim();
        if (strTime.length() < 4)
            return strTime;
        else
            return putChar(strTime.substring(0, 4), ":", 2, 2);
    }
    
    /**
     * 입력받은 문자열을 시간 형식으로 변경시켜주는 메소드
     * @param acct      치환 대상 문자열
     * @return          시간 형식으로 치환된 문자열
     */
    public static String getDateTimeForm(String strDateTime) {
        if (strDateTime == null)
            return "";

        strDateTime = strDateTime.trim();
        if (strDateTime.length() < 14)
            return strDateTime;
        else
        	strDateTime = putDash(strDateTime.substring(0, 8), 4, 2) + " " + putChar(strDateTime.substring(8), ":", 2, 2); 
        	
        return strDateTime;
    }        
    
    public static String getDateTimeFormGaro(String strDateTime) {
        if (strDateTime == null)
            return "";

        strDateTime = strDateTime.trim();
        if (strDateTime.length() < 14)
            return strDateTime;
        else
            strDateTime = putDash(strDateTime.substring(0, 8), 4, 2) + " (" + putChar(strDateTime.substring(8), ":", 2, 2)+")"; 
            
        return strDateTime;
    }
    
    /**
     * 입력받은 문자열을 주민등록번호 형식으로 변경시켜주는 메소드
     * @param acct      치환 대상 문자열
     * @return          주민등록번호 형식으로 치환된 문자열
     */
    public static String getRegNoForm(String regno) {
        if (regno == null)
            return "";

        regno = regno.trim();
        if (regno.length() < 13)
            return regno;
        else
            return putDash(regno, 6);
    }

    /**
     * 입력받은 문자열을 주민등록번호 형식으로 변경시켜주고 뒤 6자리를 *처리하는 메소드
     * @param acct      치환 대상 문자열
     * @return          주민등록번호 형식으로 치환된 문자열
     */
    public static String getRegNoFormMark(String regno) {
        if (regno == null)
            return "";

        regno = regno.trim();
        if (regno.length() < 13) {
            return regno;
        }
        else {
            String retVal = putDash(regno, 6);
            retVal = retVal.substring(0,8) + "******";
            return retVal;
        }
    }
    
    /**
     * 입력받은 문자열을 사업자번호 형식으로 변경시켜주는 메소드
     * @param acct      치환 대상 문자열
     * @return          사업자번호 형식으로 치환된 문자열
     */
    public static String getCustNoForm(String custno) {
        if (custno == null)
            return "";

        custno = custno.trim();
        if (custno.length() < 10)
            return custno;
        else if (custno.length() == 13)
            return getRegNoFormMark(custno);
        else
            return putDash(custno, 3, 2);
    }
    
    /**
     * 입력받은 문자열을 은행계좌번호 형식으로 변경시켜주는 메소드
     * @param acct      치환 대상 문자열
     * @return          은행계좌번호 형식으로 치환된 문자열
     */
    public static String getAcctForm(String acct) {
        if (acct == null)
            return "";

        acct = acct.trim();
        if (acct.length() < 12)
            return acct;
        else
            return putDash(acct, 3, 7, 2);
    }

    /**
     * 입력받은 문자열을 카드번호 형식으로 변경시켜주는 메소드
     * @param card      치환대상 문자열
     * @return          카드번호 형식으로 치환된 문자열
     */
    public static String getCardForm(String card) {
        if (card == null)
            return "";

        card = card.trim();
        if (card.length() < 16)
            return card;
        else
            return putDash(card, 4, 4, 4);
    }

    /**
     * 입력받은 문자열을 전화번호 형식으로 변경시켜주는 메소드
     * @param card      치환대상 문자열
     * @return          전화번호 형식으로 치환된 문자열
     */
    public static String getTelForm(String tel) {
        if (tel == null)
            return "";

        tel = tel.trim().replaceAll("-", "").replaceAll(" ", "");
        int telnum = tel.length();
        
        if (telnum < 7)
            return tel;
        else
        {
            String tel1 = "";
            String tel2 = "";
            String tel3 = "";        
            
            if (tel.startsWith("02"))
            {
                tel1 = tel.substring(0, 2);
                tel2 = tel.substring(2, tel.length()-4);
                tel3 = tel.substring(tel.length()-4);
            }
            else
            {
                tel1 = tel.substring(0, 3);
                tel2 = tel.substring(3, tel.length()-4);
                tel3 = tel.substring(tel.length()-4);                
            }
            
            tel ="";
            
            if (!tel1.equals("")) tel += tel1;
            if (!tel2.equals(""))
            {
                if (!tel1.equals("")) tel += "-";
                
                tel += tel2;
            }
            if (!tel3.equals(""))
            {
                if (!tel2.equals("")) tel += "-";
                else
                {
                    if (!tel1.equals("")) tel += "-";
                }
                tel += tel3;
            }
        	
            return tel;
        }
    }
    
    /**
     * 입력받은 문자열을 우편번호 형식으로 변경시켜주는 메소드
     * @param card      치환대상 문자열
     * @return          우편번호 형식으로 치환된 문자열
     */
    public static String getPostForm(String post) {
        if (post == null)
            return "";

        post = post.trim();
        if (post.length() < 6)
            return post;
        else
            return putDash(post, 3);
    }    
    
    /**
     * 입력받은 문자열을 접수번호 형식으로 변경시켜주는 메소드
     * @param card      치환대상 문자열
     * @return          접수번호 형식으로 치환된 문자열
     */
    public static String getAcepnoForm(String acepno) {
        if (acepno == null)
            return "";

        acepno = acepno.trim();
        if (acepno.length() < 11)
            return acepno;
        else
            return putDash(acepno, 6);
    }    
    
    /**
     * str의 offset 자리 뒤에 '-'문자를 넣어주는 메소드
     *
     * @param str       치환대상 문자열
     * @param offset    치환대상 문자열 '-'문자를 넣어줄 위치
     * @return          str의 offset자리 뒤에 '-'를 첨가시킨 문자열
     */
    public static String putDash(String str, int offset) {
        if (str == null)
            return "";

        if (str.length() < offset || offset <= 0)
            return str;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == offset) {
                sb.append("-");
            }
            if (c != ' ') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * str의 offset1 자리뒤와 offset1+cnt2자리 뒤에 '-'문자를 넣어주는 메소드
     * @param str       치환대상 문자열
     * @param offset1   첫번째 '-'가 들어갈 위치
     * @param cnt2      offset1 뒤에 두번째 '-'가 들어갈 위치
     * @return          str에 offset1, cnt2 자리 뒤에 '-'를 추가한 문자열
     */
    public static String putDash(String str, int offset1, int cnt2) {
        return putChar(str, "-", offset1, cnt2);
    }
    
    /**
     * str의 offset1 자리뒤와 offset1+cnt2자리 뒤에 지정된 문자를 넣어주는 메소드
     * @param str       치환대상 문자열
     * @param pchar     추가할 문자
     * @param offset1   첫번째 지정된 문자가 들어갈 위치
     * @param cnt2      offset1 뒤에 두번째 지정된 문자가 들어갈 위치
     * @return          str에 offset1, cnt2 자리 뒤에 지정된 문자를 추가한 문자열
     */
    public static String putChar(String str, String pchar, int offset1, int cnt2) {
        if (str == null)
            return "";

        int offset2 = offset1 + cnt2;
        if (str.length() < offset2 || offset2 <= 0)
            return str;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == offset1 || i == offset2)
                sb.append(pchar);
            if (c != ' ')
                sb.append(c);
        }
        return sb.toString();
    }    

    /**
     * str의 offset1 자리뒤와 offset1+cnt2자리 뒤, offset1+cnt2+cnt3자리뒤에 '-'문자를 넣어주는 메소드
     * @param str       치환대상 문자열
     * @param offset1   첫번째 '-'가 들어갈 위치
     * @param cnt2      offset1 뒤에 두번째 '-'가 들어갈 위치
     * @param cnt3      cnt3 뒤에 세번째 '-'가 들어갈 위치
     * @return          str에 offset1, cnt2, cnt3위치 뒤에 '-'를 추가한 문자열
     */
    public static String putDash(String str, int offset1, int cnt2, int cnt3) {
        if (str == null)
            return "";

        int offset2 = offset1 + cnt2;
        int offset3 = offset2 + cnt3;
        if (str.length() < offset3 || offset3 <= 0)
            return str;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == offset1 || i == offset2 || i == offset3)
                sb.append("-");
            if (c != ' ')
                sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 문자열 앞뒤의 공백을 잘라주는 메소드
     * @param s     치환대상 문자열
     * @return      공백을 잘라낸 문자열
     */
    public static String trim(String s) {
        if (s == null)
            return "";

        int st = 0;
        char val[] = s.toCharArray();
        int count = val.length;
        int len;
        for (len = count; st < len && (val[st] <= ' ' || val[st] == '\u3000'); st++) {
            ;
        }
        for (; st < len && (val[len - 1] <= ' ' || val[len - 1] == '\u3000'); len--) {
            ;
        }
        return st <= 0 && len >= count ? s : s.substring(st, len);
    }

    /**
     * 입력받은 문자열을 length가 될때까지 빈 공백을 붙여주는 메소드
     * @param str       치환대상 문자열
     * @param length    공백을 덧붙여 완성할 문자열의 길이
     * @return          공백을 붙여 length의 길이가 된 문자열
     */
    public static String getANstring(String str, int length) {
        if (str == null)
            return "";

        for (int i = length - str.length(); i > 0; i--) {
            str = str + " ";
        }
        return str;
    }

    /**
     * 입력받은 숫자를 length가 될때까지 빈 공백을 붙여주는 메소드
     * @param intstr        치환대상 숫자열
     * @param length        공백을 덧붙여 완성할 숫자열의 길이
     * @return              공백을 붙여 length의 길이가 된 문자열
     */
    public static String getNstring(int intstr, int length) {
        String str = Integer.toString(intstr);
        for (int i = length - str.length(); i > 0; i--) {
            str = "0" + str;
        }
        return str;
    }

    /**
     * 계좌번호 형식의 문자열에서 '-'문자를 제거해주는 메소드
     * @param str       치환 대상 문자열
     * @return String   '-'가 제거된 문자열
     */
    public static String delDashAccNo(String str) {
        String temp = null;
        str = str.trim();
        int len = str.length();
        switch (len) {
            case 17: // '\021'
                temp = str.substring(0, 3) + str.substring(4, 10) +
                    str.substring(11, 13) + str.substring(14, 17);
                break;
            case 13: // '\r'
                temp = str.substring(0, 3) + str.substring(4, 6) +
                    str.substring(7, 13);
                break;
            case 14: // '\016'
            case 15: // '\017'
            case 16: // '\020'
            default:
                temp = str;
                break;
        }
        return temp;
    }

    /**
     * 화폐단위 표기법대로 문자열 사이에 ','를 추가해주는 메소드
     *
     * @param str       치환 대상 문자열
     * @return String   화폐단위 표기법대로 ','가 추가된 문자열
     */
    public static String addComma(String str) {
        String temp = null;
        if (str == null) {
            temp = "0";
        }
        else {
            double change = Double.valueOf(str.trim()).doubleValue();
            DecimalFormat decimal = new DecimalFormat("###,###,###,###");
            temp = decimal.format(change);
        }
        return temp;
    }

    /**
     * 소숫점이 포함된 문자열의 소숫점 앞자리를 화폐단위 표기법대로 치환해주는 메소드
     * @param str       치환 대상 문자열
     * @return String   화폐단위 표기법대로 소숫점 앞자리에 ','가 추가된 문자열
     */
    public static String eRateFormat(String str) {
        String temp = null;
        if (str == null) {
            temp = "0";
        }
        else {
            double change = Double.valueOf(str.trim()).doubleValue();
            DecimalFormat decimal = new DecimalFormat("###,###,###.##");
            temp = decimal.format(change);
        }
        return temp;
    }

    /**
     * 문자열에서 ','를 제거해주는 메소드
     *
     * @param str       치환대상 문자열
     * @return String   ','를 제거한 문자열
     */
    public static String delComma(String str) {
        if (str == null) {
            return "";
        }
        StringBuffer dest = new StringBuffer();
        for (int i = 0; i < str.length(); ) {
            int c = str.charAt(i);
            switch (c) {
                default:
                    dest.append( (char) c);
                    // fall through
                case 44: // ','
                    i++;
                    break;
            }
        }
        return dest.toString();
    }

    /**
     * 문자열에서 '-'를 제거해주는 문자열
     * @param s         치환대상 문자열
     * @return String   '-'를 제거한 문자열
     */
    public static String delDash(String s) {
        if (s == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '-') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 문자열에서 '.'를 제거해주는 문자열
     * @param str       치환대상 문자열
     * @return String   '.'를 제거한 문자열
     */
    public static String delDot(String str) {
        if (str == null) {
            return "";
        }
        StringBuffer dest = new StringBuffer();
        for (int i = 0; i < str.length(); ) {
            int c = str.charAt(i);
            switch (c) {
                default:
                    dest.append( (char) c);
                    // fall through
                case 46: // '.'
                    i++;
                    break;
            }
        }
        return dest.toString();
    }

    /**
     * 문자열에서 ' '를 제거해주는 문자열
     * @param s       치환대상 문자열
     * @return String   ' '를 제거한 문자열
     */
    public static String delSpace(String s) {
        if (s == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 문자열에서 특정 char를 제거해주는 메소드
     * @param str       치환대상 문자열
     * @return String   '-',' ',':'이 제거된 문자열
     */
    public static String delChar(String str) {
        if (str == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != '-' && c != ' ' && c != ':') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 공백을 &nbsp태그로 치환해주는 메소드
     * @param str       치환대상 문자열
     * @return String   공백을 &nbsp로 치환한 문자열
     */
    public static String nullToNbsp(String str) {
        String ret = null;
        try {
            ret = str;
            if (str == null)
                ret = "&nbsp;";
        }
        catch (NullPointerException e) {
            ret = "&nbsp;";
            return ret;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Enter를 br태그로 변경해주는 메소드
     *
     * @param str       치환대상 문자열
     * @return String   Enter가 br태그로 변경된 문자열
     */
    public static String enterToBr(String str) {
        String ret = "";
        try {
            for (StringTokenizer st = new StringTokenizer(str, "\n"); st.hasMoreTokens(); ) {
                ret = ret + st.nextToken() + "<br>";
            }
            return ret;
        }
        catch (Exception e) {
            return str;
        }
    }

    /**
     * 라인마다 Enter후 스페이스 넣기 메소드
     *
     * @param str       치환대상 문자열
     * @return String   Enter가 br태그로 변경된 문자열
     */
    public static String enterToSpacePadding(String str, int size) {
        String ret = "";
        try {
            for (StringTokenizer st = new StringTokenizer(str, "\n"); st.hasMoreTokens(); ) {
                ret = ret + st.nextToken();
                for(int i=0; i<size; i++) ret += "&nbsp;";
            }
            return ret;
        }
        catch (Exception e) {
            return str;
        }
    }
    
    /**
     * Enter를 br태그로 변경해주는 메소드
     *
     * @param str       치환대상 문자열
     * @return String   Enter가 br태그로 변경된 문자열
     */
    public static String enterToBrPadding(String str, int size) {
        String ret = "";
        try {
            for (StringTokenizer st = new StringTokenizer(str, "\n"); st.hasMoreTokens(); ) {
                ret = ret + st.nextToken() + "<br>";
                for(int i=0; i<size; i++) ret += "&nbsp;";
            }
            return ret;
        }
        catch (Exception e) {
            return str;
        }
    }
    
    /**
     * br태그를 개행문자로 변경해주는 메소드
     *
     * @param str       치환대상 문자열
     * @return String   변경된 문자열
     */
    public static String brToEnter(String str) {
        String ret = "";
        try {
            for (StringTokenizer st = new StringTokenizer(str, "<br>"); st.hasMoreTokens(); ) {
                ret = ret + st.nextToken() + "\r\n";
            }
            return ret;
        }
        catch (Exception e) {
            return str;
        }
    }
    
    public static String brToEnterReplace(String str){
    	str =str.replaceAll("<br>", "\r\n").replaceAll("<BR>", "\r\n").replaceAll("&lt;br&gt;", "\r\n").replaceAll("&lt;BR&gt;", "\r\n");
    	return str;
    }

    /**
     * 공백을 nbsp;로 변경해주는 메소드
     *
     * @param str       치환대상 문자열
     * @return String   변경된 문자열
     */
    public static String spaceToNbsp(String str) {
        try {
            for (int i = 0; i < str.length(); ) {
                if (str.substring(i, i + 1).equals(" ") || str.substring(i, i + 1).equals("-"))
                    str = str.substring(0, i) + "&nbsp;" + str.substring(i + 1, str.length());
                else
                    i++;
            }
            return str;
        }
        catch (Exception e) {
            return str;
        }
    }

    
    /**
     * 문자열에 포함된 공백을 제거해주는 메소드
     * @param param     치환 대상 문자열
     * @return String   공백이 제거된 문자열
     */
    public static String mTrim(String param) {
        try {
            for (int i = 0; i < param.length(); ) {
                if (param.substring(i, i + 1).equals(" ") || param.substring(i, i + 1).equals("-"))
                    param = param.substring(0, i) + param.substring(i + 1, param.length());
                else
                    i++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return param;
    }

    /**
     * 전화번호를 입력받아 v_flag 단계에 해당하는 값을 리턴해주는 메소드
     * @param v_telno       처리대상 문자열
     * @param v_flag        리턴을 원하는 문자열 단계
     * @return String       v_flag 단계에 해당하는 문자열
     */
    public static String getTelSep(String v_telno, int v_flag) {
        String telno = "";
        String d_telno = "";
        String r_telno = "";
        String telno1 = "";
        String telno2 = "";
        String telno3 = "";
        try {
            if (v_telno != null) {
                telno = mTrim(v_telno);
                if (telno.length() >= 9) {
                    d_telno = telno.substring(0, 2);
                    if (d_telno.equals("02")) {
                        telno1 = telno.substring(0, 2);
                    }
                    else {
                        telno1 = telno.substring(0, 3);
                    }
                    telno2 = telno.substring(telno1.length(),
                                             telno.length() - 4);
                    telno3 = telno.substring(telno.length() - 4, telno.length());
                    if (v_flag == 1) {
                        r_telno = telno1;
                    }
                    else
                    if (v_flag == 2) {
                        r_telno = telno2;
                    }
                    else
                    if (v_flag == 3) {
                        r_telno = telno3;
                    }
                    else
                    if (v_flag == 4) {
                        r_telno = telno1 + "-" + telno2 + "-" + telno3;
                    }
                }
                else {
                    r_telno = telno;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return r_telno;
    }

    /**
     * 입력받은 문자열에서 out_len의 길이만큼 문자열을 잘라 리턴하는 메소드
     *
     * @param str           치환 대상 문자열
     * @param out_len       잘라낼 문자열의 길이
     * @return String       잘라낸 문자열
     */
    public static String getFixLen(String str, int out_len) {
        if (str == null || str.trim().equals("") || str.equals(null)) {
            return "\uC77C\uAD04\uC1A1\uAE08";
        }
        byte input[] = str.getBytes();
        
        int in_len = input.length;
        if (in_len > out_len)
            return bytesToText(input, 0, out_len);
        else
            return str;
    }
    
    /**
     * 입력받은 문자열에서 out_len의 길이만큼 문자열을 잘라 리턴하는 메소드
     *
     * @param str           치환 대상 문자열
     * @param out_len       잘라낼 문자열의 길이
     * @return String       잘라낸 문자열
     */
    public static String getFixLenDot(String str, int out_len) {
        if (str == null || str.trim().equals("") || str.equals(null)) {
            return "\uC77C\uAD04\uC1A1\uAE08";
        }
        byte input[] = str.getBytes();
        
        int in_len = input.length;
        if (in_len > out_len){
            return bytesToText(input, 0, out_len) + "...";
        }
        else
        {
            return str;
        }
    }    

    /**
     * byte를 문자열로 치환해주는 메소드
     *
     * @param b             바이트 배열
     * @param bytesStart
     * @param textLength
     * @return String       문자열로 치환된 결과값
     */
    public static String bytesToText(byte b[], int bytesStart, int textLength) {
        int len = 0;
        int i;
        for (i = bytesStart; i < b.length && len < textLength; i++) {
            if (b[i] >= 0 && b[i] <= 255) {
                len++;
                continue;
            }
            if ( ( -95 > b[i] || b[i] > -84) && ( -80 > b[i] || b[i] > -56) &&
                ( -54 > b[i] || b[i] > -3)) {
                break;
            }
            i++;
            try {
                if ( -95 <= b[i] && b[i] <= -2) {
                    len++;
                    continue;
                }
                i--;
            }
            catch (Exception e) {
                i--;
            }
            break;
        }
        if (i == bytesStart) {
            return "";
        }
        else {
            return new String(b, bytesStart, i);
        }
    }

    /**
     * 입력받은 문자열의 숫자.영어.문자 여부를 비교하는 메소드
     *
     * @param input     치환대상 문자열
     * @return String   검사 결과
     */
    public static String isEngDigitOrLetter(String input) {
        if (input == null || input.equals(""))
            return "";

        byte binput[] = input.getBytes();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (!Character.isDigit(ch) && !Character.isLetter(ch) && !Character.isWhitespace(ch)) {
                return "";
            }
        }
        for (int i = 0; i < binput.length; i++) {
            if ( (binput[i] < 0 || binput[i] > 255) &&
                ( -95 <= binput[i] && binput[i] <= -84 ||
                 -80 <= binput[i] && binput[i] <= -56 ||
                 -54 <= binput[i] && binput[i] <= -3)) {
                return "";
            }
        }
        return input;
    }

    /**
     * 문자열 속의 특정 값을 대체문자로 변경해주는 메소드
     *
     * @param in        치환 대상 문자열
     * @param from      치환 대상 문자
     * @param to        치환 될 문자
     * @return String   치환된 문자열
     */
    public static String replace(String in, String from, String to) {
        StringBuffer sb = new StringBuffer(in.length() * 2);
        String posString = in.toLowerCase();
        String cmpString = from.toLowerCase();
        int i = 0;
        for (boolean done = false; i < in.length() && !done; ) {
            int start = posString.indexOf(cmpString, i);
            if (start == -1) {
                done = true;
            }
            else {
                sb.append(in.substring(i, start) + to);
                i = start + from.length();
            }
        }
        if (i < in.length()) {
            sb.append(in.substring(i));
        }
        return sb.toString();
    }

    /**
     * 입력받은 문자열을 정의한 문자열로 변환해주는 메소드
     * @param sTagString    치환 대상 문자열
     * @return String       치환 결과 문자열
     */
    public static String replaceStr(String sTagString) {
        String sTextString = null;
        sTextString = replace(sTagString, "\"", "&quot;");
        sTextString = replace(sTextString, "& ", "&amp; ");
        sTextString = replace(sTextString, "<", "&lt;");
        sTextString = replace(sTextString, ">", "&gt;");
        sTextString = replace(sTextString, "\n", "<br>\n");
        return sTextString;
    }

    /**
     * 구분값 사이에 값이 없을 때 존재하는 값만 리턴
     *
     * @param strSplit_String       치환 대상 문자열
     * @param chrGubun_Character    구분값
     * @return Vector               구분값을 기준으로 나눈 문자열을 담고 있는 벡터
     */
    public static Vector split(String strSplit_String, char chrGubun_Character) {
        int intFrom = 0; // substring시에 From 자리
        int intTo = 0; // substing시에 To 자리
        int index = 0;
        Vector<String> vc = new Vector<String>();
        for (intTo = 0; intTo < strSplit_String.length(); intTo++) {
            if (strSplit_String.charAt(intTo) == chrGubun_Character) {
                if (intFrom < intTo) {
                    vc.addElement(strSplit_String.substring(intFrom, intTo));
                    index++;
                    intFrom = intTo + 1;
                }
            }
        }
        vc.addElement(strSplit_String.substring(intFrom, strSplit_String.length()));
        return vc;
    }

    /**
     * 구분값 사이에 값이 없을 때 공백값도 같이 리턴
     *
     * @param strSplit_String       치환 대상 문자열
     * @param chrGubun_Character    구분값
     * @return Vector               구분값을 기준으로 나눈 문자열을 담고 있는 벡터
     */
    public static Vector null2split(String strSplit_String, char chrGubun_Character) {
        int intFrom = 0; // substring시에 From 자리
        int intTo = 0; // substing시에 To 자리
        int index = 0;
        Vector<String> vc = new Vector<String>();
        for (intTo = 0; intTo < strSplit_String.length(); intTo++) {
            if (strSplit_String.charAt(intTo) == chrGubun_Character) {
                vc.addElement(strSplit_String.substring(intFrom, intTo));
                index++;
                intFrom = intTo + 1;
            }
        }
        vc.addElement(strSplit_String.substring(intFrom, strSplit_String.length()));
        return vc;
    }

//    /**
//     *
//     * 입력된 스트링에서 구분자(delimiter)에 나열된 모든 문자를 기준으로 문자열을 분리하고 분리된 문자열을 배열에 할당하여 반환한다.
//     *
//     * <pre>
//     *
//     * [사용 예제]
//     *
//     * split2Array("ABCDEABCDE", "BE")
//     * ===> A
//     *         CD
//     *         A
//     *         CD
//     *
//     * </pre>
//     *
//     * @param strTarget
//     * @param delimiter
//     * @return java.lang.String[]
//     * @roseuid 403A9A6E036B
//     */
//    public static String[] split(String strTarget, String delimiter) {
//        if(strTarget == null) return null;
//
//        StringTokenizer st = new StringTokenizer(strTarget, delimiter);
//        String[] names = new String[st.countTokens()];
//        for(int i = 0; i < names.length; i++) {
//            names[i] = st.nextToken().trim();
//        }
//
//        return names;
//    }
    
    /**
     * 
     * 입력된 스트링에서 구분자에 나열된 모든 문자를 기준으로 문자열을 분리하고 분리된 문자열을 배열에 할당하여 반환한다.
     * 
     * <pre>
     * 
     * [사용예제]
     * 
     * split2List("ABCDEABCDE", "BE")
     * ===> A
     *         CD
     *         A
     *         CD
     *
     * </pre>
     * 
     * @param strTarget
     * @param delimiter
     * @return java.util.ArrayList<String>
     */
    public static ArrayList<String> split2List(String strTarget, String delimiter){
    	if(strTarget == null) return null;
    	
    	StringTokenizer st = new StringTokenizer(strTarget, delimiter);
        ArrayList<String> values =new ArrayList<String>();
        
        for(int i = 0; i <= st.countTokens(); i++) {
        	values.add(st.nextToken().trim());
        }
        
        return values;
    }
//    /**
//     *
//     * 입력된 스트링에서 구분자(delimiter)를 하나의 단어로 인식하고 이 단어를 기준으로 문자열을 분리, 분리된 문자열을 배열에 할당하여 반환한다.
//     *
//     * <pre>
//     *
//     * [사용 예제]
//     *
//     * split2Array("AA-BBB--DDDD", "-",true)
//     * ===> AA
//     *         BBB
//     *
//     *         DDDD
//     *
//     * split2Array("AA-BBB--DDDD", "-", false);
//     * ===> AA
//     *         BBB
//      *         DDDD
//     *
//     * split2Array("ABCDEABCDE", "BE", true)
//     * ===> ABCDEABCDE
//     *
//     * </pre>
//     *
//     * @param strTarget
//     * @param delimiter 구분자(delimiter)로 인식할 단어로서 결과 문자열에는 포함되지 않는다.
//     * @param isIncludedNull 구분자로 구분된 문자열이 Null일 경우 결과값에 포함여부 ( true : 포함, false : 포함하지 않음. )
//     * @return java.lang.String[]
//     * @roseuid 403A9A6E0399
//     */
//    public static String[] split(String strTarget, String delimiter, boolean isIncludedNull) {       
//        String[] resultStrArray = null;
//
//        try {
//            Vector<String> v =  new Vector<String>();
//
//            String strCheck = new String(strTarget);
//            while (strCheck.length() != 0) {
//                int begin = strCheck.indexOf(delimiter);
//                if (begin == -1) {
//                    v.add(strCheck);
//                    break;
//                } else {
//                    int end = begin + delimiter.length();
//                    //	StringTokenizer는 구분자가 연속으로 중첩되어 있을 경우 공백 문자열을 반환하지 않음.
//                    // 따라서 아래와 같이 작성함.
//                    if (isIncludedNull) {
//                        v.add(strCheck.substring(0, begin));
//                        strCheck = strCheck.substring(end);
//                        if (strCheck.length() == 0 ) {
//                            v.add(strCheck);
//                            break;
//                        }
//                    } else{
//                        if (! CommonUtil.isEmpty(strCheck.substring(0, begin))){
//                            v.add(strCheck.substring(0, begin));
//                        }
//                        strCheck = strCheck.substring(end);
//                    }
//
//                }
//            }
//
//            String[] tempString = new String[0];
//            resultStrArray = (String[]) v.toArray(tempString);
//
//        } catch (Exception e) {
//            return resultStrArray;
//        }
//
//        return resultStrArray;
//    }

    /**
     *
     * 입력된 스트링에서 제거할 문자(elimination)에 나열한 모든 문자를 제거한다.
     *
     * <pre>
     *
     * [사용 예제]
     *
     * stringRemove("02)2344-5555", "-# /)(:;")	===> 0223445555
     * stringRemove("ABCDEABCDE", "BE")		===> ACDACD
     *
     * </pre>
     * @param strTarget
     * @param elimination
     * @return java.lang.String
     */
    public static String stringRemove(String strTarget, String elimination) {
        if (strTarget == null || strTarget.length() == 0 || elimination == null)
            return strTarget;
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(strTarget, elimination);
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken());
        }
        return sb.toString();
    }

    /**
     *
     * 입력한 문자열 앞뒤에  특정문자를 Left Padding한 문자열을 반환한다.
     *
     * <pre>
     *
     * [사용 예제]
     *
     * padLeft("AAAAAA", 'Z', 10) )	===> ZZZZAAAAAA
     *
     * </pre>
     *
     * @param str
     * @param padValue
     * @param length
     * @param value
     * @return java.lang.String
     */
    public static String padLeft(String value, char padValue, int length) {

        if (value == null)
            value = "";

        byte[] orgByte = value.getBytes();
        int orglength = orgByte.length;

        if (orglength < length) { //add Padding character
            byte[] paddedBytes = new byte[length];

            int padlength = length - orglength;

            for (int i = 0; i < padlength; i++) {
                paddedBytes[i] = (byte) padValue;
            }

            System.arraycopy(orgByte, 0, paddedBytes, padlength, orglength);

            return new String(paddedBytes);
        }
        else if (orglength > length) { //주어진 길이보다 남는다면, 주어진 길이만큼만 잘른다.
            byte[] paddedBytes = new byte[length];
            System.arraycopy(orgByte, 0, paddedBytes, 0, length);
            return new String(paddedBytes);
        }

        return new String(orgByte);
    }

    /**
     *
     * 입력한 문자열 앞뒤에  특정문자를 Right Pading한 문자열을 반환한다.
     *
     * <pre>
     *
     * [사용 예제]
     *
     * padRight("AAAAAA", 'Z', 10) )	===> AAAAAAZZZZ
     *
     * </pre>
     *
     * @param str
     * @param padValue
     * @param length
     * @param value
     * @return java.lang.String
     */
    public static String padRight(String value, char padValue, int length) {

        if (value == null)
            value = "";

        byte[] orgByte = value.getBytes();
        int orglength = orgByte.length;

        if (orglength < length) { //add Padding character
            byte[] paddedBytes = new byte[length];

            System.arraycopy(orgByte, 0, paddedBytes, 0, orglength);
            while (orglength < length) {
                paddedBytes[orglength++] = (byte) padValue;
            }
            return new String(paddedBytes);
        }
        else if (orglength > length) { //주어진 길이보다 남는다면, 주어진 길이만큼만 잘른다
            byte[] paddedBytes = new byte[length];
            System.arraycopy(orgByte, 0, paddedBytes, 0, length);
            return new String(paddedBytes);
        }

        return new String(orgByte);
    }

    /**
     * "(쌍따옴표)를 Chr(34)로 변환하여 리턴한다.(화면에서의 오류방지를 위해서)
     * @param value
     * @return String
     * @throws MainException
     */
    public static String replaceQuotToChr(String value){
        String tmpValue = "";
        String strGubun = "Chr(34)";
        String chrRootGubun = "\"";
        
        if (value != null) {
            tmpValue = value.replaceAll(chrRootGubun, strGubun);
        }
        return tmpValue;       
    }

    /**
     * '(홋따옴표)를 "(쌍따옴표)로 변환하여 리턴한다.(SQL오류 방지를 위해서)
     * @param value
     * @return String
     * @throws MainException
     */
    public static String replaceQuotToDoubleQuot(String value){
        String tmpValue = "";
        String strGubun = "''";
        String chrRootGubun = "'";

        if (value != null) {
            tmpValue = value.replaceAll(chrRootGubun, strGubun);
        }
        return tmpValue;
    }
    
    /**
     * 배열값으로 select/radio/check 아이템을 만들어주는 메소드
     * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
     *
     * @param itemType   만들고자하는 아이템명 select/radio/check
     * @param itemArray	   아이템을 만들 데이터 
     * @param itemName	  아이템명
     * @param  selectedValue 선택된 값
     * @param action     action 값
     * @param style      스타일정보
     * @param selectType 추가구성 아이템옵션 
     * @param shape      모양(edit, read, none)
     * @return           아이템html 문자열
     */      
    public static String makeItem(String itemType, String[][] itemArray, String itemName, String selectedValue, String action, String style, String selectType, String shape )
    {   
    	String rtn = "";
    	String s_val = "";
    	String s_name = "";
    	boolean chkItemType = false;
    	
    	if (itemType.toLowerCase().equals("select"))
    		chkItemType = true;
    	
    	if (chkItemType) {
            if("EDIT".equals(shape.toUpperCase())) {
                rtn += ("<script type='text/javascript' language='javascript'>NSelectEdit(\"" + itemName + "\", \"<select id='"+itemName+"' name='"+itemName+"' "+action+" "+style+" >");
            }
            else if("NONE".equals(shape.toUpperCase())) {
                rtn += ("<select id='"+itemName+"' name='"+itemName+"' "+action+" "+style+" >");
            }
            else {
                rtn += ("<script type='text/javascript' language='javascript'>NSelect(\"" + itemName + "\", \"<select id='"+itemName+"' name='"+itemName+"' "+action+" "+style+" >");
            }
        }

		if (chkItemType)
		{    		
    		if (!selectType.equals(""))
    		{
                if(selectType.equals("ALL"))
                    rtn +=("<option value='%' ");
                else
                    rtn += ("<option value='' ");
    			
    			if (selectedValue.equals(""))
    				rtn += ("selected ");
    			
    			rtn += (">");
    			
    			if (selectType.equals("S"))
    				rtn += ("-선택-");
    			else if (selectType.equals("A") || selectType.equals("ALL"))
    				rtn += ("-전체-");
                else if (selectType.equals("T"))
                	rtn += ("직접입력");
    			
    			rtn += ("</option>");
    		}
		}
		else
		{		
    		if (!selectType.equals(""))
    		{
                rtn +=("<input type='"+itemType.toLowerCase()+"' id='"+itemName+"' name='"+itemName+"' value='' ");
    			
    			if (selectedValue.equals(""))
    				rtn += ("checked ");
    			
    			rtn += (">");
    			
    			if (selectType.equals("S"))
    				rtn += ("선택");
    			else if (selectType.equals("A"))
    				rtn += ("전체");
    		}    			
		}
		
    	for (int i=0; i< itemArray.length; i++)
    	{
    		s_val = itemArray[i][0];
    		s_name = itemArray[i][1];
    		
    		if (chkItemType) rtn += ("<option value='"+s_val+"' ");
    		else rtn += ("<input type='"+itemType.toLowerCase()+"' id='"+itemName+"' name='"+itemName+"' value='"+s_val+"' ");
    		    		
    		if (selectedValue.equals(s_val))
    		{
    			if (chkItemType) rtn += (" selected ");
    			else rtn += (" checked ");    			
    		}
    		if (chkItemType) rtn += ("> "+s_name+"</option>");
    		else rtn += ( action+ ">" + s_name);    		
    	}

        if (chkItemType) {
            if("EDIT".equals(shape.toUpperCase())) {
                rtn +=("</select>\", \"\", \"#ffffff\", \"#D5F7FF\", \"1px solid #CCCCCC\", \"\");</script>");
            }
            else if("NONE".equals(shape.toUpperCase())) {
                rtn += ("</select>");
            }
            else {
                rtn +=("</select>\", \"\", \"#ffffff\", \"#D5F7FF\", \"1px solid #CCCCCC\", \"\");</script>");
            }
        }

    	return rtn;    	
    }  
    
    /**
     * 배열값으로 select/radio/check 아이템을 만들어주는 메소드
     * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
     *
     * @param itemType   만들고자하는 아이템명 select/radio/check
     * @param itemArray	   아이템을 만들 데이터 
     * @param itemName	  아이템명
     * @param  selectedValue 선택된 값
     * @param action     action 값
     * @param style      스타일정보
     * @param selectType 추가구성 아이템옵션 
     * @return           아이템html 문자열
     */      
    public static String makeItem(String itemType, String[][] itemArray, String itemName, String selectedValue, String action, String style, String selectType )
    {    	
    	return makeItem(itemType, itemArray, itemName, selectedValue, action, style, selectType, "none");    	
    }    

    /**
     * 배열값으로 select/radio/check 아이템을 만들어주는 메소드
     * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
     *
     * @param itemType     만들고자하는 아이템명 select/radio/check
     * @param itemArray    아이템을 만들 데이터 
     * @param itemName  아이템명
     * @param  selectedValue 선택된 값
     * @param action action 값
     * @param style   스타일정보
     * @return          아이템html 문자열
     */      
    public static String makeItem(String itemType, String[][] itemArray, String itemName, String selectedValue, String action, String style)
    {       
        return makeItem(itemType, itemArray, itemName, selectedValue, action, style, "", "none");       
    }    

    /**
     * 입력 데이타를 해당 길이만큼 자른 문자열을 반환한다.
     * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
     * 
     * @param sourceString 입력문자열
     * @param limitLength 문자열길이
     * @param overStr 입력문자열이 문자열길이를 초과했을 때 구분해주는 문자열
     * @return String 잘린문자열
     * 
     */
    public static String sliceStr(String sourceString, int limitLength, String overStr)
    {
      String result = new String(sourceString);
      
      int srcByteLength = result.getBytes().length;
      int clipLength = result.length();

      while ( result.getBytes().length > limitLength ) {
        result  = result.substring(0, --clipLength);
      }
      
      if (srcByteLength > limitLength) {
    	  result += overStr;
      }
      
      return result;
    }    
    
    
    /**
     * 입력 데이타를 해당 길이만큼 문자가 깨지지 않도록 앞부분을 바이트로 잘라서 반환한다.
     * @param str 입력문자열
     * @param len 문자열길이
     * @return String 잘린문자열
     */
    public static String sliceStr(String str, int len)
    {
      String result = new String(str);
      int charlen = result.length();
      int bytelen = result.getBytes().length;
      while ( bytelen > len )
      {
        result  = result.substring(0, --charlen);
        bytelen = result.getBytes().length;
      }
      return result;
    }

    /**
     * source를 arrayLen 크기만큼 잘라서 dadaSize 사이즈의 배열로 만듬
     * @param source 입력문자열
     * @param arrayLen 배열사이즈
     * @param dadaSize 문자열길이
     * @return String[] 잘린문자배열
     */
    public static String[] cutLength(String source, int arrayLen, int dadaSize)
    {
      String[] aryContents = null;
      try
      {
        aryContents = new String[arrayLen];

        String contents = source;
        for (int i=0 ; i<aryContents.length ; i++)
        {
          aryContents[i] = "";
        }

        byte[] src = contents.getBytes();
        byte[] cut = null;
        byte[] tar = null;

        for (int i=0 ; (i<aryContents.length) && (src.length>0) ; i++)
        {
          aryContents[i] = sliceStr(contents, dadaSize);

          cut = aryContents[i].getBytes();
          tar = new byte[src.length - cut.length];
          System.arraycopy(src, cut.length, tar, 0, tar.length);

          contents = new String(tar);
          src = contents.getBytes();
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      return aryContents;
    }
    
     
    //-----------------------------------------
    // Html 특수 문자 해결
    // value=" abc"def " 경우 def 이하의 문자는 표시가 안됨
    //-----------------------------------------
    public static String replaceQuotToChr(Object pObject) {
        if (pObject == null) {
            return "";
        }

        String value = (String)pObject;

        return value.replaceAll("\"", "&quot;");
    }
    
    /**
     * 주민등록번호를 OOOO년 OO월 OO일 형식으로 리턴한다.
     * @author 남미희
     * @param regNo 주민등록번호 ex(6912011010101)
     * @return String 결과문자열 ex(1969년 12월 01일)
     * 
     */
    public static String getRegNoDateForm(String regNo)
    {
    	String result = new String();
    	regNo = regNo.replaceAll("-", "").trim();

		if(regNo.length() > 6){
			String gender = regNo.substring(6,7);
	        if(gender.equals("1") || gender.equals("2") || 
	        		gender.equals("5") || gender.equals("6")){
				result = "19";
			}else if(gender.equals("3") || gender.equals("4")){
				result = "20";
			}
			result += regNo.substring(0,2) + "년 ";
			result += regNo.substring(2,4) + "월 ";
			result += regNo.substring(4,6) + "일";
		}
      
        return result;
    }
    
    /**
     * 월수를 OO년 OO월 형식으로 리턴한다.
     * @author 남미희
     * @param value 월수 ex(100)
     * @return String 결과문자열 ex(8년 4개월)
     * 
     */
    public static String getYyMmForm(String value)
    {
    	if (value == null || value.equals(""))
            return "";

    	String result = new String();
    	for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (!Character.isDigit(ch)) {
                return "";
            }
        }
        
    	int value_int = Integer.parseInt(value,10);
    	if(value_int == 0) return "";
    	else{
    		int yy = value_int/12;
    		int mm = value_int%12;
    		if(yy > 0)
    			result = String.valueOf(yy) + "년 ";
    		if(mm > 0)
    			result += String.valueOf(mm) + "개월";
    	}
      
        return result;
    }

    /**
     * 계산서 발행시 공란수를 리턴한다
     * @author 전영찬
     * @param 금액
     * @return 공란수
     * 
     */
    public static int getSpaceCount(String value) {   	
    	int result = 0;
        value = value.trim();
        if (value.length() > 0) {
        	result = 11 - value.length();
        }
        return result;
    }

    /**
     * 입력받은 문자열을 날짜 형식(년, 월, 일)으로 변경시켜주는 메소드
     * ymdType-> N -> 2008.03.01 , ymdType-> 2008년 3월 1일
     * @param acct      치환 대상 문자열
     * @return          날짜 형식으로 치환된 문자열
     */
    public static String getYmdDateForm(String strDate, String ymdType) {
        String monthNum = null;
        String dayNum   = null;

        if (strDate == null)
            return "";

        strDate = strDate.trim();
        if (strDate.length() < 8){
            return strDate;
        }else{

            if("0".equals(strDate.substring(4, 5))){
                monthNum = " "+strDate.substring(5, 6);
            }else{
                monthNum = " "+strDate.substring(4, 6);
            }
            if("0".equals(strDate.substring(6, 7))){
                dayNum   = " "+strDate.substring(7, 8);
            }else{
                dayNum   = " "+strDate.substring(6, 8);
            }

            if("N".equals(ymdType)){
                return strDate.substring(0, 4)+". " +strDate.substring(4, 6)+". " +strDate.substring(6,8);
            }else{
                return strDate.substring(0, 4)+"년 " +monthNum+"월 " +dayNum+"일";
            }
        }
    }

    /**
     * 주민번호로 성별을 구분한다.
     * @param value
     * @return 성별
     */
    public static String getSex(String value){
        String rtnVal = "";
        //1,3,9    2,4,0
        String tmpVal = StringUtil.null2void(value);
        int gbnVal = -1;
        
        if(!("").equals(tmpVal)){
            
            try{            
                gbnVal = Integer.parseInt(tmpVal.substring(6, 7));
            } catch(Exception ex) { }
            
            switch(gbnVal){
            case 1:
            case 3:
            case 9:
                rtnVal = "남";
                break;
            case 2:
            case 4:
            case 0:
                rtnVal = "여";
                break;
            default:
                rtnVal = "";
                break;
            }
        }
        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:  " + gbnVal + " > " +rtnVal);
        return rtnVal;
    }
    
    /**
     * cnt자리수로 반올림해서 리턴
     * @param str       소숫점을 포함한 치환대상
     * @param cnt       치환이 될 소숫점 아래 자릿수
     * @return          str를 소숫점 아래 cnt자리로 맞춘 문자열
     */
    public static String getRoundUp(String str, int cnt) {
        String rtnVal = "";
        String rtnFormat = "0.";
        
        if(cnt > 0) {
            for(int i = 0 ; i < cnt ; i++){
                rtnFormat += "0";
            }
        } else {
            rtnFormat = "0.0";
        }
        
        if (str == null || str.trim().equals("")) {
            return "";
        }
        
        try{
            DecimalFormat f1 = new DecimalFormat(rtnFormat); 
            rtnVal = f1.format(Double.parseDouble(str));
        } catch(Exception e){
            rtnVal = "0.0E";
        }
        
        return rtnVal;
    }
    
    /**
     * cnt자리수로 내림해서 리턴
     * -2:십단위, -1:원단위, 0:소수점 1자리
     * 1:소수점 2자리, 2:소수점 3자리, 3:소수점 4자리, 4:소수점 5자리 처리
     * @param str       소숫점을 포함한 치환대상
     * @param cnt       치환이 될 소숫점 아래 자릿수
     * @return          str를 소숫점 아래 cnt자리로 맞춘 문자열
     */
    public static String getFloorUp(String str, int cnt) {
    	double nCalcVal = Double.parseDouble(str);
		if(cnt < 0) {
			cnt = -(cnt);
			str = Integer.toString((int)(Math.floor(nCalcVal / Math.pow(10, cnt)) * Math.pow(10, cnt)));
		} else {
			str = Double.toString(Math.floor(nCalcVal * Math.pow(10, cnt)) / Math.pow(10, cnt));
		}
        return str;
    }

    /*
     * Integer 캐스팅
     * 예외발생시 return -1
     */
    public static int parseInt(String s) {
    	return parseInt(s, -1);
    }
    /*
     * Integer 캐스팅 예외처리
     */
    public static int parseInt(String s, int i) {
		int j = i;
		try {
		    j = Integer.parseInt(s);
		} catch (Exception _ex) {
		    j = i;
		}
		return j;
    }

    /*
     * Long 캐스팅
     * 예외발생시 return -1
     */
    public static long parseLong(String s) {
        return parseLong(s, -1);
    }
    /*
     * Long 캐스팅 예외처리
     */
    public static long parseLong(String s, long i) {
        long j = i;
        try {
            j = Long.parseLong(s);
        } catch (Exception _ex) {
            j = i;
        }
        return j;
    }

    /*
     * Double 캐스팅
     * 예외발생시 return -1
     */
    public static double parseDouble(String s) {
        return parseDouble(s, -1);
    }
    /*
     * Double 캐스팅 예외처리
     */
    public static double parseDouble(String s, double i) {
        double j = i;
        try {
            j = Double.parseDouble(s);
        } catch (Exception _ex) {
            j = i;
        }
        return j;
    }
    
    /*
     * 
     * @param string 데이터
     * @return int
     */
    public static int stLength(String string) {
        byte[] by = string.getBytes();
        return by.length;
    }
    
    /*
     * 
     * @param string 데이터
     * @param length 입력최대값
     * @return boolean
     */
    public static boolean isChkLength(String string, int length) {
        boolean isOver = false;
        if(stLength(string) > length) {
            isOver = true;
        }
        return isOver;
    }   
    /*
     * 
     * @param string 데이터
     * @param length 입력최대값
     * @return int
     */
    public static int chkLength(String string, int length) {
        int i_Over = 0;
        if(isChkLength(string,length)) {
            i_Over++;
        }
        return i_Over;
    }
    /**
     * dateFormat 6자리,8자리,14자리 모두가능
     * @param value
     * @param format
     * @return
     */
    public static String dateFormat(String value, String format) {
        if (value == null || value.length() <= 5 || parseLong(value) == -1)
            return value;
        StringBuffer sb = null;
        String separator = format;

        try {
            sb = new StringBuffer();

            sb.append(value.substring(0, 4))
             .append(separator)
             .append(value.substring(4, 6));
            
            if (value.length() >= 8) {           
                sb.append(separator)
                  .append(value.substring(6, 8));
            }
            if (value.length() >= 14) {                
                sb.append(" ")
                  .append(value.substring(8, 10))
                  .append(":")
                  .append(value.substring(10, 12))
                  .append(":")
                  .append(value.substring(12, 14));
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }

        if (sb != null)
            return sb.toString();
        else
            return value;
    }    
    
    
    /*
     * 
     * @param string 데이터
     * @param 셀렉트 옵션명, 옵션코드, 구분자
     * @return String
     */
    public static String selOptionSplit(String gubun, String nm, String cd, String spl) {
        String rtnVal = "";
        String arr_nm[] = nm.split(spl);
        String arr_cd[] = cd.split(spl);
        if(nm != null && !nm.equals("") && cd !=null && !cd.equals("")){
            if(gubun.equals("popup")){
                for(int i=0; i<arr_nm.length; i++){
                    rtnVal += arr_nm[i]+"</br>";
                }            
            }else{
                for(int i=0; i<arr_nm.length; i++){
                    rtnVal += "<option value=\""+arr_cd[i]+"\">"+arr_nm[i]+"</option>";
                }
            }
        }

        return rtnVal;
    }    
    
    /*
     * 
     * @param string 데이터
     * @param 셀렉트 옵션명, 옵션코드, 구분자
     * @return String
     */
    public static String selCheckSplit(String gubun,String nm, int no, String cd, String spl, Map map) {
        String rtnVal = "";
        String arr_cd[] = cd.split(spl);
        if(nm != null && !nm.equals("") && cd !=null && !cd.equals("")){   
            if(gubun.equals("popup")){
                for(int i=1; i<6; i++){

                    for(int k=0; k < arr_cd.length; k++){
                        if(("0"+i).equals(arr_cd[k])){ rtnVal +=map.get("0"+i)+"<br>";}
                    }

                }                  
                
            }else{
                for(int i=1; i<6; i++){
                    rtnVal +="<input type='checkbox' id='"+nm+no+"' name='"+nm+no+"' value='0"+i+"' ";
                    for(int k=0; k < arr_cd.length; k++){
                        if(("0"+i).equals(arr_cd[k])){ rtnVal +=" checked ";}
                    }
                    rtnVal +=" class='checkbox'  >"+map.get("0"+i)+" ";
                }                
            }
            
        }


        return rtnVal;
    }
    
    
    /*
     * 
     * @param string 데이터
     * @param 셀렉트 옵션명, 옵션코드, 구분자 
     * @return String
     */
    public static String selGjCheckSplit(String gubun,String nm, int no, String cd, String spl, Map map) {
        String rtnVal = "";
        String arr_cd[] = cd.split(spl);
        String useYmdChk[] = "2,3,4,5,6,7,1".split(",");
        String bookCstDvnChk[] = "A001,A002,A003".split(",");
        int cnt = 1;

        if(nm != null && !nm.equals("") && cd !=null && !cd.equals("")){ 
            
            if(gubun.equals("popup")){
                if(nm.equals("useYmdChk")){
                    for(int n=0; n < useYmdChk.length; n++){ 
                        for(int i=0; i < arr_cd.length; i++){ 
                            if(arr_cd[i].equals(useYmdChk[n])){
                                rtnVal += map.get(arr_cd[i]);
                                if(cnt < arr_cd.length){
                                rtnVal += ", ";
                                cnt++;
                                }
                            }
                        }
                    }                    
                }else if(nm.equals("bookCstDvnChk")){
                    for(int n=0; n < bookCstDvnChk.length; n++){ 
                        for(int i=0; i < arr_cd.length; i++){ 
                            if(arr_cd[i].equals(bookCstDvnChk[n])){
                                rtnVal += map.get(arr_cd[i]);
                                if(cnt < arr_cd.length){
                                    rtnVal += ", ";
                                    cnt++;
                                    }
                            }
                        }
                    } 
                }
            }else{
                if(nm.equals("useYmdChk")){
                    for(int n=0; n < useYmdChk.length; n++){
                    	
                        rtnVal +="<input type='checkbox' id='"+nm+"' name='"+nm+"' value='"+useYmdChk[n]+"' class='checkbox' " ;
                        for(int i=0; i < arr_cd.length; i++){
                            if(arr_cd[i].equals(useYmdChk[n])){
                                rtnVal +=" checked ";
                            }                        
                        }
                        rtnVal +=        ">"+map.get(useYmdChk[n])+" ";
                    	
                    } 
                }else if(nm.equals("bookCstDvnChk")){
                    for(int n=0; n < bookCstDvnChk.length; n++){ 
                    	if(!bookCstDvnChk[n].equals("A001")) {
	                        rtnVal +="<input type='radio' onclick=\"chk_mili('bookCstDvnChk');fnDisabledYn(this.checked);\" id='"+nm+"' name='"+nm+"' value='"+bookCstDvnChk[n]+"' class='checkbox' " ;
	                        for(int i=0; i < arr_cd.length; i++){
	                            if(arr_cd[i].equals(bookCstDvnChk[n])){
	                                rtnVal +=" checked ";
	                            }                        
	                        }
	                        rtnVal +=        ">"+map.get(bookCstDvnChk[n])+" ";
                    	}
                    }                   
                }
            }
        }
        return rtnVal;
    }    
    
    /**
    * 파일 확장자에 맞는 아이콘
    * @param 파일명
    * @return Result String 파일 아이콘 태그
    * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
    */
    public static String getFileIconImage (String fileName)
    {
        String imageTag = "";       
        try
        {
            if(fileName==null||fileName.equals("")) return "";
            
            fileName=fileName.toLowerCase().trim();
            if(fileName.endsWith(".jpeg")) imageTag+="jpeg";
            else if(fileName.endsWith(".jpg")) imageTag+="jpg";
            else if(fileName.endsWith(".gif")) imageTag+="gif";
            else if(fileName.endsWith(".avi")) imageTag+="avi";
            else if(fileName.endsWith(".mp3")) imageTag+="mp3";
            else if(fileName.endsWith(".mp2")) imageTag+="mp2";
            else if(fileName.endsWith(".exe")) imageTag+="exe";
            else if(fileName.endsWith(".aif")) imageTag+="aif";
            else if(fileName.endsWith(".aifc")) imageTag+="aifc";
            else if(fileName.endsWith(".aiff")) imageTag+="aiff";
            else if(fileName.endsWith(".arj")) imageTag+="arj";
            else if(fileName.endsWith(".au")) imageTag+="au";
            else if(fileName.endsWith(".bat")) imageTag+="bat";
            else if(fileName.endsWith(".bmp")) imageTag+="bmp";
            else if(fileName.endsWith(".cdf")) imageTag+="cdf";
            else if(fileName.endsWith(".cgi")) imageTag+="cgi";
            else if(fileName.endsWith(".com")) imageTag+="exe";
            else if(fileName.endsWith(".compressed")) imageTag+="compressed";
            else if(fileName.endsWith(".css")) imageTag+="css";
            else if(fileName.endsWith(".default")) imageTag+="default";
            else if(fileName.endsWith(".device")) imageTag+="device";
            else if(fileName.endsWith(".dif")) imageTag+="dif";
            else if(fileName.endsWith(".dll")) imageTag+="dll";
            else if(fileName.endsWith(".doc")) imageTag+="doc";
            else if(fileName.endsWith(".dv")) imageTag+="dv";
            else if(fileName.endsWith(".eml")) imageTag+="eml";
            else if(fileName.endsWith(".gz")) imageTag+="gz";
            else if(fileName.endsWith(".html")) imageTag+="html";
            else if(fileName.endsWith(".htm")) imageTag+="htm";
            else if(fileName.endsWith(".hwp")) imageTag+="hwp";
            else if(fileName.endsWith(".iff")) imageTag+="iff";
            else if(fileName.endsWith(".image")) imageTag+="image";
            else if(fileName.endsWith(".jfif")) imageTag+="jfif";
            else if(fileName.endsWith(".js")) imageTag+="js";
            else if(fileName.endsWith(".lhz")) imageTag+="lhz";
            else if(fileName.endsWith(".lzh")) imageTag+="lzh";
            else if(fileName.endsWith(".mac")) imageTag+="mac";
            else if(fileName.endsWith(".midi")) imageTag+="midi";
            else if(fileName.endsWith(".mov")) imageTag+="mov";
            else if(fileName.endsWith(".movie")) imageTag+="movie";
            else if(fileName.endsWith(".nws")) imageTag+="nws";
            else if(fileName.endsWith(".pcx")) imageTag+="pcx";
            else if(fileName.endsWith(".png")) imageTag+="png";
            else if(fileName.endsWith(".ppt")) imageTag+="ppt";
            else if(fileName.endsWith(".ps")) imageTag+="ps";
            else if(fileName.endsWith(".qif")) imageTag+="qif";
            else if(fileName.endsWith(".qt")) imageTag+="qt";
            else if(fileName.endsWith(".qtif")) imageTag+="qtif";
            else if(fileName.endsWith(".qti")) imageTag+="qti";
            else if(fileName.endsWith(".ra")) imageTag+="ra";
            else if(fileName.endsWith(".ram")) imageTag+="ram";
            else if(fileName.endsWith(".rar")) imageTag+="rar";
            else if(fileName.endsWith(".rle")) imageTag+="rle";
            else if(fileName.endsWith(".rv")) imageTag+="rv";
            else if(fileName.endsWith(".sound")) imageTag+="sound";
            else if(fileName.endsWith(".spl")) imageTag+="spl";
            else if(fileName.endsWith(".swf")) imageTag+="swf";
            else if(fileName.endsWith(".sys")) imageTag+="sys";
            else if(fileName.endsWith(".tar")) imageTag+="tar";
            else if(fileName.endsWith(".text")) imageTag+="text";
            else if(fileName.endsWith(".tga")) imageTag+="tga";
            else if(fileName.endsWith(".tgz")) imageTag+="tgz";
            else if(fileName.endsWith(".tif")) imageTag+="tif";
            else if(fileName.endsWith(".tiff")) imageTag+="tiff";
            else if(fileName.endsWith(".txt")) imageTag+="txt";
            else if(fileName.endsWith(".wav")) imageTag+="wav";
            else if(fileName.endsWith(".wmf")) imageTag+="wmf";
            else if(fileName.endsWith(".xlsx")) imageTag+="xlsx";
            else if(fileName.endsWith(".xls")) imageTag+="xls";
            else if(fileName.endsWith(".z")) imageTag+="z";
            else if(fileName.endsWith(".zip")) imageTag+="zip";
            else if(fileName.endsWith(".pdf")) imageTag+="pdf";
            else if(fileName.endsWith(".gul")) imageTag+="gul";
            else  imageTag+="unknown";
            
            imageTag+=".gif";
            imageTag = "/html/images/file/" + imageTag;

        }
        catch (Exception e)
        {
        }
        return imageTag;            
    }
    
    public static String getBetweenString(String str, String strDiv1, String strDiv2){
        
        int intChk1 = 0;
        int intChk2 = 0;
        
        intChk1 = str.indexOf(strDiv1);
        intChk2 = str.indexOf(strDiv2);
                
        return str.substring(intChk1, intChk2);

    }
    
    public static String UTF8HangleCut(String szText, String szKey, int nLength, int nPrev, boolean isNotag, boolean isAdddot){  
        /*
          함수 사용은
          UTF8HangleCut(대상문자열, 시작위치로할키워드, 자를길이, 키워드위치에서얼마나이전길이만큼포함할것인가, 태그를없앨것인가, 긴문자일경우"..."을추가할것인가)
          처럼 하시면 되겠습니다.
      
          [예]
          "가나다라" 에서 2바이트까지 자르고 싶을경우 strCut("가나다라", null, 2, 0, true, true); 처럼 하시면 됩니다.
           => 결과 : "가"
          "가나다라" 에서 "다"라는 키워드 기준에서 2바이트까지 자르고싶을경우 strCut("가나다라", "다", 2, 0, true, true); 처럼 하시면 됩니다.
           => 결과 : "다"
          "가나다라" 에서 "라"라는 키워드 기준으로 그 이전의 4바이트까지 포함하여 6바이트까지 자르고 싶을 경우 strCut("가나다라", "라", 6, 4, true, true); 처럼 하시면 됩니다.
           => 결과 : "나다라"
          "가나다라" 에서 3바이트를 자를 경우
           => 결과 : "가"
          "가a나다라" 에서 3바이트를 자를 경우
           => 결과 : "가a"
          "가나다라" 에서 "나" 키워드 기준으로 이전 1바이트 포함하여 4바이트까지 자를 경우
           => 결과 : "나"
        
        */

        String r_val = szText;
        int oF = 0, oL = 0, rF = 0, rL = 0; 
        int nLengthPrev = 0;
        Pattern p = Pattern.compile("<(/?)([^<>]*)?>", Pattern.CASE_INSENSITIVE);  // 태그제거 패턴
           
        if(isNotag) {r_val = p.matcher(r_val).replaceAll("");}  // 태그 제거
        r_val = r_val.replaceAll("&amp;", "&");
        r_val = r_val.replaceAll("(!/|\r|\n|&nbsp;)", "");  // 공백제거
         
        try {
            byte[] bytes = r_val.getBytes("UTF-8");     // 바이트로 보관 
            if(szKey != null && !szKey.equals("")) {
                nLengthPrev = (r_val.indexOf(szKey) == -1)? 0: r_val.indexOf(szKey);  // 일단 위치찾고
                nLengthPrev = r_val.substring(0, nLengthPrev).getBytes("MS949").length;  // 위치까지길이를 byte로 다시 구한다
                nLengthPrev = (nLengthPrev-nPrev >= 0)? nLengthPrev-nPrev:0;    // 좀 앞부분부터 가져오도록한다.
            }
        
            // x부터 y길이만큼 잘라낸다. 한글안깨지게.
            int j = 0;
        
            if(nLengthPrev > 0) while(j < bytes.length) {
                if((bytes[j] & 0x80) != 0) {
                    oF+=2; rF+=3; if(oF+2 > nLengthPrev) {break;} j+=3;
                } else {if(oF+1 > nLengthPrev) {break;} ++oF; ++rF; ++j;}
            }
          
            j = rF;
        
            while(j < bytes.length) {
                if((bytes[j] & 0x80) != 0) {
                    if(oL+2 > nLength) {break;} oL+=2; rL+=3; j+=3;
                } else {if(oL+1 > nLength) {break;} ++oL; ++rL; ++j;}
            }
        
            r_val = new String(bytes, rF, rL, "UTF-8");  // charset 옵션
        
            if(isAdddot && rF+rL+3 <= bytes.length) {
                //r_val+="...";
            }  // ...을 붙일지말지 옵션 
        } catch(UnsupportedEncodingException e){ e.printStackTrace(); }   
            
        return r_val;
    }

    /*
     * Clob 를 String 으로 변경
     */
    public static String clobToString(Clob clob) throws SQLException, IOException {
        if (clob == null) return "";
        
        StringBuffer strOut = new StringBuffer();
        String str = "";
        BufferedReader br = new BufferedReader(clob.getCharacterStream());
        while ((str = br.readLine()) != null) {
            strOut.append(((strOut.length() != 0) ? "\n" : "") + str);
        }
        return strOut.toString();
    }

    /**
     * 문자열을 바이트로 자르기
     * @param str
     * @param i
     * @return
     */
    public static String substringB(String str, int i) {
        if(str == null)
            return "";
        String tmp = str;
        int slen = 0, blen = 0;
        char c;
        if(tmp.getBytes().length > i) {
            while(blen + 1 < i) {
                c = tmp.charAt(slen);
                blen++;
                slen++;
                if(c > 127)
                    blen++; // 2-byte character..
            }
            tmp = tmp.substring(0, slen);
        }
        return tmp;
    }
    
    /**
     * 입력 데이타를 해당 길이만큼 문자가 깨지지 않도록 앞부분을 인코딩바이트에따라 잘라서 반환한다.
     * @param str 입력문자열
     * @param len 자를 문자열길이
     * @return String 잘린문자열
     */
    public static String sliceStrEncoding(String str, int len)
    {
      String result = new String(str);
      String s = "";
      int bytelen = 0;
      for(int i = 0; i < len; i++){
          s = result.substring(i,i+1);
          if(1 == s.getBytes().length){
              bytelen = bytelen + 1;
          }else if(3 == s.getBytes().length){
              bytelen = bytelen + 3;
              
          }else {
              bytelen = bytelen + 2;
          }
      }
      
      return sliceStr(result, bytelen);
    }
    
    /**
     * href태그의 주소를 클릭 반응 URL로 변경
     * @param strIn
     * @param strFrom
     * @param strTo
     * @return
     */
    public static String replaceClickUrl(String strIn, String strFrom, String strTo){
        int intStart = 0; // 해당 태그의 시작위치
        int intEnd = 0;   // 해당 태그의 종료위치
        int intPosition = 0; // 현재 token의 시작위치
        int intReplaceLength = strTo.length(); // 바꾸고자 하는 문자열 길이
        int intNowLength = 0; // 바꾸고자 하는 대상의 문자열 길이
        String startTag[] = {"<a ","<area ","<A ","<AREA "}; // 찾고자 하는 문자열
        String endTag[] = {">",">",">",">"}; // 찾고자 하는 문자열
        String preString = "";
        String nowString  = "";
        String endString = "";
        String retrunString = strIn;
        
        for (int i =0; i<startTag.length; i++){
            intPosition = 0;
            while(true) {
                intStart = retrunString.indexOf(startTag[i], intPosition); // 해당 링크 태그의 시작 위치를 찾는다.
                intEnd = retrunString.indexOf(endTag[i], intStart); // 해당 링크 태그의 시작 위치를 찾는다.
                if(intStart == -1) break;
                
                preString = retrunString.substring(0, intStart);
                nowString = retrunString.substring(intStart, intEnd+1);
                intNowLength = nowString.length();                
                endString = retrunString.substring(intEnd+1);
                
                retrunString = preString + nowString.replace(strFrom,strTo) + endString;
                
                intPosition = intEnd+(intReplaceLength-intNowLength);                
            }
        }
        
        return retrunString;
    } 
  
    /**
     * 특수문자 변환(<,>,&,\)
     * bl=true 수정화면에서 사용, bl=false 리스트 및 상세
     * @param str, bl
     * @return
     */
    public static String textToHtml(String str, Boolean bl) throws Exception {
        if(str == null || "".equals(str)){
        	str = "";
        }
        else{
        	str = str.trim();
        	str = str.replaceAll("&#60;"   , "<");
        	str = str.replaceAll("&lt;"    , "<");
        	str = str.replaceAll("&#62;"   , ">");
        	str = str.replaceAll("&gt;"    , ">");
        	str = str.replaceAll("&amp;"   , "&");
        	str = str.replaceAll("&#35;"   , "#");
        	str = str.replaceAll("&#38;"   , "&");
        	str = str.replaceAll("&#34;", "\"");
        	str = str.replaceAll("&#40;", "(");
        	str = str.replaceAll("&#41;", ")");
        	str = str.replaceAll("&quot;", "'");
        	str = str.replaceAll("\\|", " ");
        	
        	if(bl){
//        		str = enterToBr(str);
        	}
        	else{
        		str = enterToBrPadding(str,1);
        	}
//        	System.out.println("##################\r\n"+ str);
        }
        return str;
    }
    
    /**
     * 현재 날짜에 하루를 더한 날짜 계산 
     * @param yymmdd
     * @return yymmdd
     */
    public static String nextDate(String yymmdd){
    	String date = "";
    	String strYear = "";
    	String strMonth = "";
    	String strDay = "";
    	
    	int year = Integer.parseInt(yymmdd.substring(0, 4));
    	int month = Integer.parseInt(yymmdd.substring(4,6));
    	int day = Integer.parseInt(yymmdd.substring(6,8));
    	
    	if(month >= 8){
    		if(month%2 == 0){
    			if(day == 31){
    				if(month == 12){
    					year += 1;
    					month = 1;
    					day = 1;
    				}else{
    					month += 1;
    					day = 1;
    				}
    			}else{
    				day += 1;
    			}
    		}else if(month%2 == 1){
				if(day == 30){
					if(month == 12){
    					year += 1;
    					month = 1;
    					day = 1;
    				}else{
    					month += 1;
    					day = 1;
    				}
				}else{
					day += 1;
				}
			}
    	}else{
    		if(month%2 == 1){
    			if(day == 31){
    				month += 1;
    				day = 1;
    			}else{
    				day += 1;
    			}
    		}else if(month%2 == 0){
				if(month == 2){
					if(day == 28){
						month += 1;
						day = 1;
					}
				}else{
					if(day == 30){
        				month += 1;
        				day = 1;
    				}else{
    					day += 1;
    				}
				}
			}
    	}
    	strYear = String.valueOf(year);
    	
    	if(month < 10){
    		strMonth = "0"+month;
    	}else{
    		strMonth = String.valueOf(month);
    	}
    	
    	if(day < 10){
    		strDay = "0"+day;
    	}else{
    		strDay = String.valueOf(day);
    	}
    	
    	date = strYear+strMonth+strDay;
    	
    	return date;
    }
    
    /**
     * 가운데 글자 *치환
     * @param String
     * @return String
     */
    public static String replacAsterisk(String data){
    	try{
    		String retVal = "";
    		String lastVal = "";
    		
    		int resLen = data.length();
    		
    		if(resLen > 1){
    			retVal = data.substring(0, 1);
    			lastVal = data.substring(resLen-1, resLen);
    			
    			for(int i=0; i<(resLen - 1); i++){
    				if(resLen == (retVal.length()+1)){
    					retVal = retVal + lastVal;
    				}else{
    					retVal = retVal + "*";
    				}
    			}
    		}else{
    			retVal = data.substring(0, 1) + "*";
    		}
    		
    		return retVal;
    	}catch (Exception e) {
    		return data;
		}
    }

	/**
	*	특정 문자열로 구분된 문자열을 분리하여 배열로 리턴한다.
	*	@param	str	string separated string
	*	@param	strDelim		separating string
	*	@return	String[]
	*/
	public static String[] getTokenArray(String str, String strDelim)
	{
		if (str == null || str.length() == 0) return null;
		StringTokenizer st = new StringTokenizer(str, strDelim);

		String arrToken [];
		arrToken = new String[st.countTokens()];

		int i = 0;
		while (st.hasMoreTokens())
		{
			arrToken[i] = st.nextToken();
			i++;
		}

		return arrToken;
	}
	

    public static String mapToString(Map<String, String> map, String delim) {
        StringBuilder sb = new StringBuilder();
        Iterator<Entry<String, String>> iter = map.entrySet().iterator();
        SortedSet<String> keys = new TreeSet<String>(map.keySet());
        
        int i = 0;
        for (String key : keys) { 
           String value = map.get(key);
           sb.append(key);
           sb.append(delim);
           sb.append(value);
           
           i++;
           if(keys.size() != i){
               sb.append(';');
           }
        }
        return sb.toString();

    }
    


    public static String convertNotiMessage(NotiUserInfoVO record) {
    	String cntn = "" ;
		try {
			cntn = textToHtml(record.getTmplCntn(), true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	cntn = cntn.replaceAll("[$]접수번호[$]", getAcepnoForm(record.getAcepno()));
    	cntn = cntn.replaceAll("[$]공장명[$]", null2void(record.getManfFactNm()));
    	cntn = cntn.replaceAll("[$]제품명[$]", null2void(record.getProdNm()));
    	cntn = cntn.replaceAll("[$]클레임유형[$]", null2void(record.getTypeNm()));
    	cntn = cntn.replaceAll("[$]처리등급[$]", null2void(record.getProsGrand()));
    	cntn = cntn.replaceAll("[$]처리시간[$]", getTimeMinForm(record.getVisitTime()));
    	cntn = cntn.replaceAll("[$]수거여부[$]", null2void(record.getSampleYn()));
    	cntn = cntn.replaceAll("[$]처리자명[$]", null2void(record.getClerkNm()));
    	cntn = cntn.replaceAll("[$]접수등급[$]", null2void(record.getAcepGradNm()));
    	cntn = cntn.replaceAll("[$]접수시간[$]", getTimeMinForm(record.getAcepTime()));
    	cntn = cntn.replaceAll("[$]처리지점명[$]", null2void(record.getProsDeptNm()));
    	
        return cntn;

    }
}