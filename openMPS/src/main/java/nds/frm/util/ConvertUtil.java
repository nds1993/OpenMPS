package nds.frm.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * <p>Title: 비즈빌더3.0</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 일소프트</p>
 * @author 일소프트
 * @version 3.0
 */
public class ConvertUtil{
    // Convert File Type
    private final static int JAVA = 1;
    private final static int UPPER = 2;
    private final static int LOWER = 3;
    
    /**
     * 한글 
     */
    private static final String LANGUAGE_KOR = "KOR";
    
    /**
     * 영문
     */
    private static final String LANGUAGE_ENG = "ENG";
    
    /**
     * 한자 
     */
    private static final String LANGUAGE_CHN = "CHN";
    
    /**
     * 한글 금액 접두어 
     */
    private static final String LANGUAGE_KOR_PRE = "금";
    
    /**
     * 영문 금액 접두어 
     */
    private static final String LANGUAGE_ENG_PRE = "THE SUM OF KOREAN WON";
    
    /**
     * 한자 금액 접두어 
     */
    private static final String LANGUAGE_CHN_PRE = "金";
    
    /**
      * 한글 금액 접미어 
     */
    private static final String LANGUAGE_KOR_POST = "원정";
    
    /**
     * 영문 금액 접두어 
     */
    private static final String LANGUAGE_ENG_POST = "ONLY.";
    
    /**
     * 한자 금액 접두어 
     */
    private static final String LANGUAGE_CHN_POST = "";    
    
	public ConvertUtil() {
	  
	}
	
	public static String convert(String value) {
		int intCase = 1;
		return convert(value,intCase);		
	}
	
    public static String convert(String value,int intCase) {    	
    	String rtnValue = null;
        switch (intCase) {
            case ConvertUtil.JAVA :
            	rtnValue = ConvertUtil.convertDBToJava(value);
                break;
            case ConvertUtil.UPPER :
            	rtnValue = ConvertUtil.convertUpper(value);
                break;            
            case ConvertUtil.LOWER :
            	rtnValue = ConvertUtil.convertLower(value);
                break;            
            default :
            	rtnValue = value;
                break;
        }
        return rtnValue;              
    }   	
	
	/**
     * DB의 컬럼명을 자바타입의 문자열을 변환한다.
     * 규칙)
     *  1. 모든 문자를 소문자로 변경
     *  2. _다음의 문자는 대문자로 변경
     *  3. _는 제거
     *  예제)"CUSTOMER_CODE" -> "customerCode"
     * @param dbFieldName
     * @return String
     */
	public static String convertDBToJava(String dbFieldName) {
        if (dbFieldName == null){
        	// 예외처리(인자값으로 넘어온 것이 Null입니다.");
        	//throw new FrameException();
        }
        
        StringBuffer stringBuffer = new StringBuffer();
        StringTokenizer stringTokenizer = new StringTokenizer(dbFieldName.trim(), "_");
        
        // 첫문자를 소문자로
        if (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            stringBuffer.append(token.toLowerCase());
        }
        
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (token.length() == 1) {
            	stringBuffer.append(token.toUpperCase());
            } else {
            	stringBuffer.append(token.substring(0, 1).toUpperCase());
            	stringBuffer.append(token.substring(1).toLowerCase());
            }
        }
        return stringBuffer.toString();
    }	
    
    /**
     * 문자열을 소문자로 변환한다.
     * @param value
     * @return String
     */
    public static String convertLower(String value) {
    	String rtnValue = "";
        if (value == null) rtnValue = "";
        else rtnValue = value.toLowerCase();
        return rtnValue;
    }
    /**
     * 문자열을 대문자로 변환한다.
     * @param value
     * @return String
     */
    public static String convertUpper(String value) {
    	String rtnValue = "";
        if (value == null) rtnValue = "";
        else rtnValue = value.toUpperCase();
        return rtnValue;
    }
    
    /**
     * 
     * Obejct Type 을 int Type으로 Casting하여 Return한다.
     * 
     * @param value
     * @return int
     * @roseuid 40394A4902C0
     */
    public static int castint(Object value) 
    {
		int out = 0;
		if (value == null || "".equals(value)) {
			out = 0;
		} else if (value instanceof Number) {
			out = ((Number) value).intValue();
		} else {
			try {
				out = Integer.parseInt(value.toString().trim());
			} catch (Exception e) {
				//throw new FrameException(MSG_SE00022, new String[] {value.toString(),"Number"}); 	
			}
		}
		return out;     
    }
    
    /**
     * 
     * Obejct Type 을 Integer Type으로 Casting하여 Return한다.
     * 
     * @param value
     * @return java.lang.Integer
     * @roseuid 40394A4902CF
     */
    public static Integer castInteger(Object value)  
    {
		Integer out = null;
		if (value == null || "".equals(value)) {
			out = new Integer(0);
		} else if (value instanceof Integer) {
			out = (Integer) value;
		} else if (value instanceof Number) {
			out = new Integer(((Number) value).intValue());
		} else {
			try {
				out = new Integer(value.toString().trim());
			} catch (Exception e) {
				//throw new FrameException(MSG_SE00022, new String[] {value.toString(),"Number"}); 	
			}
		}
		return out;     
    }
    
    /**
     * 
     * Obejct Type 을 long Type으로 Casting하여 Return한다.
     * 
	 * @param value
     * @return long
     * @roseuid 40394A4902EE
     */
    public static long castlong(Object value) 
    {
		long out = 0;
		if (value == null || "".equals(value)) {
			out = 0;
		} else if (value instanceof Number) {
			out = ((Number) value).longValue();
		} else {
			try {
				out = Long.parseLong(value.toString().trim());
			} catch (Exception e) {
				//throw new FrameException(MSG_SE00022, new String[] {value.toString(),"Number"}); 	
			}
		}
		return out;     
    }
    
    /**
     * 
     * Obejct Type 을 Long Type으로 Casting하여 Return한다.
     * 
     * @param value
     * @return java.lang.Long
     * @roseuid 40394A49030D
     */
    public static Long castLong(Object value)  
    {
		Long out = null;
		if (value == null || "".equals(value)) {
			out = new Long(0);
		} else if (value instanceof Long) {
			out = (Long) value;
		} else if (value instanceof Number) {
			out = new Long(((Number) value).longValue());
		} else {
			try {
				out = new Long(value.toString().trim());
			} catch (Exception e) {
				//throw new FrameException(MSG_SE00022, new String[] {value.toString(),"Number"}); 	
			}
		}
		return out;     
    }
    
    /**
     * 
     * Obejct Type 을 Long Type으로 Casting하여 Return한다.
     * 
     * @param value
     * @return float
     * @roseuid 40394A49032C
     */
    public static float castfloat(Object value) 
    {
		float out = 0;
		if (value == null || "".equals(value)) {
			out = 0;
		} else if (value instanceof Number) {
			out = ((Number) value).floatValue();
		} else {
			try {
				out = Float.parseFloat(value.toString().trim());
			} catch (Exception e) {
				//throw new FrameException(MSG_SE00022, new String[] {value.toString(),"Number"}); 	
			}
		}
		return out;     
    }
    
    /**
     * 
     * Obejct Type 을 Float Type으로 Casting하여 Return한다.
     * 
     * @param value
     * @return java.lang.Float
     * @roseuid 40394A49038A
     */
    public static Float castFloat(Object value)  
    {
		Float out = null;
		if (value == null || "".equals(value)) {
			out = new Float(0);
		} else if (value instanceof Float) {
			out = (Float) value;
		} else if (value instanceof Number) {
			out = new Float(((Number) value).floatValue());
		} else {
			try {
				out = new Float(value.toString().trim());
			} catch (Exception e) {
				//throw new FrameException(MSG_SE00022, new String[] {value.toString(),"Number"}); 	
			}
		}
		return out;     
    }
    
    /**
     * 
     * Obejct Type 을 double Type으로 Casting하여 Return한다.
     * 
     * @param value
     * @return double
     * @roseuid 40394A4903C9
     */
    public static double castdouble(Object value) 
    {
		double out = 0;
		if (value == null || "".equals(value)) {
			out = 0;
		} else if (value instanceof Number) {
			out = ((Number) value).doubleValue();
		} else {
			try {
				out = Double.parseDouble(value.toString().trim());
			} catch (Exception e) {
				//throw new FrameException(MSG_SE00022, new String[] {value.toString(),"Number"}); 	
			}
		}
		return out;     
    }
    
    /**
     * 
     * Obejct Type 을 Double Type으로 Casting하여 Return한다.
     * 
     * @param value
     * @return java.lang.Double
     * @roseuid 40394A4A000F
     */
    public static Double castDouble(Object value)  
    {
		Double out = null;
		if (value == null || "".equals(value)) {
			out = new Double(0);
		} else if (value instanceof Double) {
			out = (Double) value;
		} else if (value instanceof Number) {
			out = new Double(((Number) value).doubleValue());
		} else {
			try {
				out = new Double(value.toString().trim());
			} catch (Exception e) {
				//throw new FrameException(MSG_SE00022, new String[] {value.toString(),"Number"}); 	
			}
		}
		return out;     
    }
    
    /**
     * 
     * Obejct Type 을 BigDecimal Type으로 Casting하여 Return한다.
     * 
     * @param value
     * @return java.math.BigDecimal
     * @roseuid 40394A4A006D
     */
    public static BigDecimal castBigDecimal(Object value)  
    {
		BigDecimal out = null;
		if (value == null || "".equals(value)) {
			out = new BigDecimal(0);
		} else if (value instanceof BigDecimal) {
			out = (BigDecimal) value;
		} else if (value instanceof Double) {
			out = new BigDecimal(  new DecimalFormat("#0.0#################").format(((Double) value).doubleValue())  );
		} else {
			try {
				out = new BigDecimal(value.toString().trim());
			} catch (Exception e) {
				//throw new FrameException(MSG_SE00022, new String[] {value.toString(),"Number"}); 	
			}
		}
		return out;     
    }
    
    /**
     * 
     * Obejct Type 을 String Type으로 Casting하여 Return한다.
     * 
     * @param value
     * @return java.lang.String
     * @roseuid 40394A4A00DA
     */
    public static String castString(Object value) 
    {
		String out = null;
		if (value == null || "".equals(value)) {
			out = "";
		} else if (value instanceof Double) {
			out = new DecimalFormat("#0.0#################").format((Double) value);
		} else {
			out = value.toString();
		}
		return out;     
    }
    
    /**
     * 
     *  Format(yyyy-MM-dd)된 입력 Obejct Type의 값을 Date Type으로 Casting하여 Return한다.
     * 
     * <pre>
     * 
     * [사용 예제]
     * 
     * ConvertUtil.castDate("2002-11-23 11:33:02.001")	===> 2002-11-23
     * ConvertUtil.castDate("2002-11-23")						===> 2002-11-23
     *  
     * </pre>
     * 
     * @param value
     * @return java.sql.Date
     * @roseuid 40394A4A00FB
     */
    public static java.sql.Date castDate(Object value) 
    {
		java.sql.Date out = null;
		if (value == null || "".equals(value)) {
			out = new java.sql.Date(0);
		} else if (value instanceof Number) {
			out = new java.sql.Date(((Number) value).longValue());
		} else if (value instanceof java.util.Date) {
			out = new java.sql.Date(((java.util.Date) value).getTime());
		} else {
			try {
				String v = value.toString().trim();
				//포멧 yyyy-mm-dd
				if (v.length() > 10)
					v = v.substring(0, 10);
				out = java.sql.Date.valueOf(v);
			} catch (Exception e) {
				//throw new FrameException(MSG_SE00022, new String[] {value.toString(),"Date"}); 	
			}
		}
		return out;     
    }
    
    /**
     * 
     *  Format(hh:mm:ss)된 입력 Obejct Type의 값을 Time Type으로 Casting하여 Return한다.
     * 
     * @param value
     * @return java.sql.Time
     * @roseuid 40394A4A0128
     */
    public static java.sql.Time castTime(Object value) 
    {
		java.sql.Time out = null;
		if (value == null || "".equals(value)) {
			out = new java.sql.Time(0);
		} else if (value instanceof Number) {
			out = new java.sql.Time(((Number) value).longValue());
		} else if (value instanceof java.util.Date) {
			out = new java.sql.Time(((java.util.Date) value).getTime());
		} else {
			try {
				String v = value.toString().trim();
				//포멧 hh:mm:ss
				if (v.length() >= 19)
					v = v.substring(11, 19);
				else if (v.length() > 8)
					v = v.substring(0, 8);
				out = java.sql.Time.valueOf(v);
			} catch (Exception e) {
				//throw new FrameException(MSG_SE00022, new String[] {value.toString(),"Time"}); 	
			}
		}
		return out;     
    }
    
    /**
     *    
     * Format(yyyy-MM-dd hh:mm:ss.fffffffff)된 입력 Obejct Type의 값을 Timestamp Type으로 Casting하여 Return한다.
     * 
     * <pre>
     * 
     * [사용 예제]
     * CConvertUtil.castTimestamp("2004-05-01 11:33:02.001")	===> 2004-05-01 11:33:02.001
	 *
     * </pre>			
     * 
     * @param value
     * @return java.sql.Timestamp
     * @roseuid 40394A4A0157
     */
    public static java.sql.Timestamp castTimestamp(Object value) 
    {
		java.sql.Timestamp out = null;
		if (value == null || "".equals(value)) {
			out = new java.sql.Timestamp(0);
		} else if (value instanceof Number) {
			out = new java.sql.Timestamp(((Number) value).longValue());
		} else if (value instanceof java.sql.Timestamp) {
			out = (java.sql.Timestamp) value;
		} else if (value instanceof java.util.Date) {
			out = new java.sql.Timestamp(((java.util.Date) value).getTime());
		} else {
			try {
				String v = value.toString().trim();
				if (v.length() == 10) {
					v = v + " 09:00:00";
				}	
				//포멧 yyyy-MM-dd hh:mm:ss.fffffffff
				out = java.sql.Timestamp.valueOf(v);
			} catch (Exception e) {
				//throw new FrameException(MSG_SE00022, new String[] {value.toString(),"Timestamp"}); 	
			}
		}
		return out;     
    }
		
    /**
     * 
     * 입력 Object Type의 값을 요청한 Type으로 Casting하여 Return한다.
     * 
     * <pre>
     * 
     * [사용 예제]
     * 
     * ConvertUtil.castType("java.lang.String", new Integer(123))  ===> 123
     * 
     * </pre>
     * 
     * @param type
     * @param value
     * @return java.lang.Object
     * @roseuid 40394A4A01A5
     */
    public static Object castType(String type, Object value)  
    {
		if ( type == null || type.equals("java.lang.String") ) {
			return castString(value);
		} else if ( type.equals("int") ) {
			return castInteger(value);
		} else if ( type.equals("java.lang.Integer") ) {
			return castInteger(value);
		} else if ( type.equals("Integer") ) {
			return castInteger(value);
		} else if ( type.equals("float") ) {
			return castFloat(value);
		} else if ( type.equals("java.lang.Float") ) {
			return castFloat(value);
		} else if ( type.equals("Float") ) {
			return castFloat(value);
		} else if ( type.equals("double") ) {
			return castDouble(value);
		} else if ( type.equals("java.lang.Double") ) {
			return castDouble(value);
		} else if ( type.equals("Double") ) {
			return castDouble(value);
		} else if ( type.equals("long") ) {
			return castLong(value);
		} else if ( type.equals("java.lang.Long") ) {
			return castLong(value);
		} else if ( type.equals("Long") ) {
			return castLong(value);
		} else if ( type.equals("java.math.BigDecimal") ) {
			return castBigDecimal(value);
		} else if ( type.equals("BigDecimal") ) {
			return castBigDecimal(value);
		} else if ( type.equals("java.sql.Date") ) {
			return castDate(value);
		} else if ( type.equals("java.sql.Time") ) {
			return castTime(value);
		} else if ( type.equals("java.sql.Timestamp") ) {
			return castTimestamp(value);
		} 
		return value;     
    }
 
	/**
	 *
	 * 영문 인코딩(8859-1)을  한글 인코딩(ksc5601)으로 Convert하여 Return한다.
	 * 
	 * <pre>
	 * 
	 * [사용 예제]
	 * 
	 *	String value = new String("금융사업부".getBytes(), "8859_1");
	 * convertEng2Kor(value)
	 * ===> 금융사업부
	 * 
	 * </pre>
	 * 
     * @param value
     * @return java.lang.String
     * @roseuid 403998700119
	 */
	public static String convertEng2Kor(String value) 
	{
		if(value == null) return value;
		try{
			return new String(value.getBytes("8859_1"), "ksc5601");
		}catch(Exception e){
			value = null;
		}
		return value;
	} 

	/**
	 *
	 * 한글 인코딩(ksc5601)을 영문 인코딩(8859-1)으로 Convert하여 Return한다.
	 * 
	 * <pre>
	 * 
	 * [사용 예제]
	 * 
	 * String value = new String("Financial Component".getBytes(), "ksc5601");
	 *	convertKor2Eng(value)
	 * ===> Financial Component
	 * 
	 * </pre>
	 * 
     * @param value
     * @return java.lang.String
     * @roseuid 403998700280
	 */
	public static String convertKor2Eng(String value) 
	{
		if(value == null) return value;
		try{
			return new String(value.getBytes("ksc5601"), "8859_1");
		}catch(Exception e){
			value = null;
		}
		return value;
	} 
 
	/**
	 *
	 * 입력한 값을 %(Percent)값으로 Convert하고  %(Percent)형식으로 return한다.
	 * 
	 * <pre>
	 * 
	 * [사용 예제]
	 * 
	 * ConvertUtil.convertPercent("3.14")  ===> 314%
	 * 
	 * </pre>
	 * 
	 * @param str
	 * @param value
	 * @return java.lang.String
	 * @roseuid 40394A4A035B
	 */
	public static String convertPercent(String value) 
	{
		double number = (new Double(value)).doubleValue();
		return ConvertUtil.convertPercent(number);     
	}
    
	/**
	 *
	 * 입력한 값을 %(Percent)값으로 Convert하고  %(Percent)형식으로 return한다.
	 * 
	 * <pre>
	 * 
	 * [사용 예제]
	 * 
	 * ConvertUtil.convertPercent(3.14)  ===> 314%
	 * 
	 * </pre>
	 * 
	 * @param num
	 * @param value
	 * @return java.lang.String
	 * @roseuid 40394A4A038A
	 */
	public static String convertPercent(double value) 
	{
		NumberFormat format = NumberFormat.getPercentInstance();
		return format.format(value);     
	}
	
	/**
	 *    
	 * yyyyMMdd 또는 yyyyMMddhhmmss  Format 으로 입력된 String 값을 Timestamp Type으로 Convert하여 Return한다.
	 * 
	 * <pre>
	 * 
	 * [사용 예제]
	 * CConvertUtil.convertString2Timestamp("20040501")			===> 2004-05-01 14:37:32.531
	 *	CConvertUtil.convertString2Timestamp("20040501113302")	===> 2004-05-01 11:33:02.547
	 *
	 * </pre>			
	 * 
	 * @param value
	 * @return java.sql.Timestamp
	 * 
	 */
	public static java.sql.Timestamp convertString2Timestamp(String value)
	{
		java.sql.Timestamp out = null;
		if (value == null) {
			out = new java.sql.Timestamp(0);
		} else {
			try {
				String v = value.toString().trim();
				if (v.length() == 8) {  // yyyyMMdd
					
					//int dateObjLength = v.length();
					
					String yearString = v.substring(0, 4);
					String monthString = v.substring(4, 6);
					String dayString = v.substring(6, 8);
					int year = Integer.parseInt(yearString);
					int month = Integer.parseInt(monthString) - 1;
					int day = Integer.parseInt(dayString);
					Calendar cal = new GregorianCalendar();
					cal.set(year, month, day);
					out = new Timestamp(cal.getTime().getTime());
										
				}else if (v.length() == 14) {  // yyyyMMddHHmmss
					String yearString = v.substring(0, 4);
					String monthString = v.substring(4, 6);
					String dayString = v.substring(6, 8);
					String hourString = v.substring(8, 10);
					String minString = v.substring(10, 12);
					String secString = v.substring(12, 14);
					int year = Integer.parseInt(yearString);
					int month = Integer.parseInt(monthString) - 1;
					int day = Integer.parseInt(dayString);
					int hour = Integer.parseInt(hourString);
					int min = Integer.parseInt(minString);
					int sec = Integer.parseInt(secString);
					Calendar cal = new GregorianCalendar();
					cal.set(year, month, day, hour, min, sec);
					out = new Timestamp(cal.getTime().getTime());					
					
				} 
			} catch (Exception e) {
				out = new java.sql.Timestamp(0);
			}
		}
		return out;     
	}
	    
    /**
     * 
     * 숫자의 원화금액을 해당언어의 문자로 Convert하고 금액에 해당하는 접두어와 접미어를 붙여 Return한다.	
     * 
     * <pre>
     * 
     * [사용 예제]
     * 
     * - 한자로 변환
     * convertWonMoney2Language("123456789","CHN","","")	===> 金 壹億貳阡參百四拾五萬六阡七百八拾九 
     * default prefix : 金
     * default postfix : 없음 
     * 
     * - 영문자로 변환
     * convertWonMoney2Language("12345","ENG","","")   		===> THE SUM OF KOREAN WON  TWELVE THOUSAND THREE HUNDRED FOURTY FIVE ONLY.
     * default prefix : THE SUM OF KOREAN WON
     * default postfix : ONLY.
     *  
     * - 한글로 변환
     * convertWonMoney2Language("1234567","KOR","","")		===> 금 일백이십삼만사천오백육십칠 원정
     * default prefix : 금 
     * default postfix : 원정
     * 
     * </pre>
     * @param money
     * @param language
     * @param prefix
     * @param postfix
     * @return java.lang.String
     * @roseuid 40394A4A01C5
     */
    public static String convertWonMoney2Language(String money, String language, String prefix, String postfix)  
    {
		
			String moneyChar =  null;
			if (LANGUAGE_KOR.equals(language)) { 
				if ( CommonUtil.isEmpty(prefix) ) 	prefix = LANGUAGE_KOR_PRE; 
				if ( CommonUtil.isEmpty(postfix) ) 	postfix = LANGUAGE_KOR_POST;			
				moneyChar= convertNumeral2Korean(money);
				
			} else if (LANGUAGE_ENG.equals(language)) {
				if ( CommonUtil.isEmpty(prefix) ) 	prefix = LANGUAGE_ENG_PRE;
				if ( CommonUtil.isEmpty(postfix) ) 	postfix = LANGUAGE_ENG_POST;	
				moneyChar= convertNumeral2English(money);
				
			} else if (LANGUAGE_CHN.equals(language)) {
				if ( CommonUtil.isEmpty(prefix) ) 	prefix = LANGUAGE_CHN_PRE;
				if ( CommonUtil.isEmpty(postfix) ) 	postfix = LANGUAGE_CHN_POST;					
				moneyChar= convertNumeral2Chinese(money);
				
			} else if (CommonUtil.isEmpty(language)) {						
                //TODO 기타 언어 추가 
				if ( CommonUtil.isEmpty(prefix) ) 	prefix = LANGUAGE_KOR_PRE; 
				if ( CommonUtil.isEmpty(postfix) ) 	postfix = LANGUAGE_KOR_POST;			
				moneyChar= convertNumeral2Korean(money);						
			}

			StringBuffer formattedMoney = new StringBuffer(prefix);
			formattedMoney.append(" ");
			formattedMoney.append(moneyChar);
			formattedMoney.append(" ");
			formattedMoney.append(postfix);	
			
			return formattedMoney.toString();     
    }
    
    /**
     * 	
     * 숫자의 원화금액을 한글로 Convert 한다.		
     * 
     * <pre>
     * 
     * [사용 예제]
     *
     * convertNumeral2Korean("123456789")		===> 일억이천삼백사십오만육천칠백팔십구
     * 
     * </pre>
     * 
     * @param money
     * @return java.lang.String
     * @roseuid 40394A4A01F4
     */
    public static String convertNumeral2Korean(String money)  
    {

		String[] bigMonetaryUnit = { "만", "억", "조", "경" };
		String[] monetaryUnit = { "천", "백", "십"  };

		StringBuffer tempMoney = new StringBuffer();
		String rtnMoney = null;
		String [] moneyArray = {};
		String tempString = null;

		int moneyLength = money.length();
		int unitPos = money.length();
		int arrInx = (moneyLength%4 == 0) ?  moneyLength/4 : moneyLength/4+1;

		moneyArray = new String [arrInx];
		
		//4자리 마다 끊어서 배열 생성
		for (int inx = 0; unitPos>0; inx++) {
			if (unitPos-4 >0) {
				tempString = money.substring(unitPos-4, unitPos);
				moneyArray[inx] = tempString;
				unitPos = unitPos-4;								 
			} else {
				tempString = money.substring(0, unitPos);
				moneyArray[inx] = StringUtil.padLeft(tempString, '0', 4);
				unitPos = 0;
			}
		}

		//숫자를 한글로 변경 및 단위 추가
		for (int jnx = arrInx-1; jnx>=0; jnx--) {
			String tempMon = moneyArray[jnx];
			int tempMonLen = tempMon.length();
			char num = 0;
			
			for (int knx=0;knx<tempMonLen;knx++){
				num = tempMon.charAt(knx);
				tempMoney.append(convertBaseMonetary2Korean(num));
				
				if (num != '0') {
					switch (knx) {
						case 0 : //천 단위
							tempMoney.append(monetaryUnit[knx]);
							break;
						case 1 : //백 단위
							tempMoney.append(monetaryUnit[knx]);
							break;
						case 2 : //십 단위
							tempMoney.append(monetaryUnit[knx]);
							break;
						default :
							break;
					}
				}
			}
			
			//만단위 이상 단위 붙이기
			if (ConvertUtil.castint(tempMon)>0 && jnx>0) {
				tempMoney.append(bigMonetaryUnit[jnx-1]);
			}
		}

		rtnMoney = tempMoney.toString();

		return rtnMoney;     
    }
    
    /**
     * @param num
     * @return java.lang.String
     * @roseuid 40394A4A0213
     */
    private static String convertBaseMonetary2Korean(char num) 
    {

		String[] baseMonetaryUnit =
			{ "", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구" };

		String rtnBaseMonetary = null;

		switch (num) {
			case '0' :
				rtnBaseMonetary = baseMonetaryUnit[0];
				break;
			case '1' :
				rtnBaseMonetary = baseMonetaryUnit[1];
				break;
			case '2' :
				rtnBaseMonetary = baseMonetaryUnit[2];
				break;
			case '3' :
				rtnBaseMonetary = baseMonetaryUnit[3];
				break;
			case '4' :
				rtnBaseMonetary = baseMonetaryUnit[4];
				break;
			case '5' :
				rtnBaseMonetary = baseMonetaryUnit[5];
				break;
			case '6' :
				rtnBaseMonetary = baseMonetaryUnit[6];
				break;
			case '7' :
				rtnBaseMonetary = baseMonetaryUnit[7];
				break;
			case '8' :
				rtnBaseMonetary = baseMonetaryUnit[8];
				break;
			case '9' :
				rtnBaseMonetary = baseMonetaryUnit[9];
				break;
			default :
				break;
		}
		return rtnBaseMonetary;     
    }
    
    /**
     * 	
     * 숫자의 원화금액을 한자로 Convert 한다.		
     * 
     * <pre>
     * 
     * [사용 예제]
     * 
     * convertNumeral2Chinese("123456789")	===> 壹億貳阡參百四拾五萬六阡七百八拾九
     * 
     * </pre>
     * 
     * @param money
     * @return java.lang.String
     * @roseuid 40394A4A0232
     */
    public static String convertNumeral2Chinese(String money)  
    {

		String[] bigMonetaryUnit = { "萬", "億", "兆", "京" };
		String[] monetaryUnit = { "阡", "百", "拾"  };

		String rtnMoney = null;
		StringBuffer tempMoney = new StringBuffer();
		String [] moneyArray = {};
		String tempString = null;

		int moneyLength = money.length();
		int unitPos = money.length();
		int arrInx = (moneyLength%4 == 0) ?  moneyLength/4 : moneyLength/4+1;

		moneyArray = new String [arrInx];
		
		//4자리 마다 끊어서 배열 생성
		for (int inx = 0; unitPos>0; inx++) {
			if (unitPos-4 >0) {
				tempString = money.substring(unitPos-4, unitPos);
				moneyArray[inx] = tempString;
				unitPos = unitPos-4;								 
			} else {
				tempString = money.substring(0, unitPos);
				moneyArray[inx] = StringUtil.padLeft(tempString, '0', 4);
				unitPos = 0;
			}
		}

		//숫자를 한자로 변경 및 단위 추가
		for (int jnx = arrInx-1; jnx>=0; jnx--) {
			String tempMon = moneyArray[jnx];
			int tempMonLen = tempMon.length();
			char num = 0;
			
			for (int knx=0;knx<tempMonLen;knx++){
				num = tempMon.charAt(knx);
				tempMoney.append(convertBaseMonetary2Chinese(num));
				
				if (num != '0') {
					switch (knx) {
						case 0 : //천 단위
							tempMoney.append(monetaryUnit[knx]);
							break;
						case 1 : //백 단위
							tempMoney.append(monetaryUnit[knx]);
							break;
						case 2 : //십 단위
							tempMoney.append(monetaryUnit[knx]);
							break;
						default :
							break;
					}
				}
			}
			
			//만단위 이상 단위 붙이기
			if (ConvertUtil.castint(tempMon)>0 && jnx>0) {
				tempMoney.append(bigMonetaryUnit[jnx-1]);
			}
		}

		rtnMoney = tempMoney.toString();

		return rtnMoney;     
    }
    
    /**
     * @param num
     * @return java.lang.String
     * @roseuid 40394A4A0243
     */
    private static String convertBaseMonetary2Chinese(char num) 
    {

		String[] baseMonetaryUnit =
			{ "", "壹", "貳", "參", "四", "五", "六", "七", "八", "九" };

		String rtnBaseMonetary = null;

		switch (num) {
			case '0' :
				rtnBaseMonetary = baseMonetaryUnit[0];
				break;
			case '1' :
				rtnBaseMonetary = baseMonetaryUnit[1];
				break;
			case '2' :
				rtnBaseMonetary = baseMonetaryUnit[2];
				break;
			case '3' :
				rtnBaseMonetary = baseMonetaryUnit[3];
				break;
			case '4' :
				rtnBaseMonetary = baseMonetaryUnit[4];
				break;
			case '5' :
				rtnBaseMonetary = baseMonetaryUnit[5];
				break;
			case '6' :
				rtnBaseMonetary = baseMonetaryUnit[6];
				break;
			case '7' :
				rtnBaseMonetary = baseMonetaryUnit[7];
				break;
			case '8' :
				rtnBaseMonetary = baseMonetaryUnit[8];
				break;
			case '9' :
				rtnBaseMonetary = baseMonetaryUnit[9];
				break;
			default :
				break;
		}
		return rtnBaseMonetary;     
    }
    
    /**
     * 	
     * 숫자의 원화금액을 영문자로 Convert 한다.		
     * 
     * <pre>
     *
     * convertNumeral2English("123456789")		
     * ===>  ONE HUNDRED TWENTY THREE MILLION FOUR HUNDRED FIFTY SIX THOUSAND SEVEN HUNDRED EIGHTY NINE
     * 
     * </pre>
     * 
     * @param money
     * @return java.lang.String
     * @roseuid 40394A4A0271
     */
    public static String convertNumeral2English(String money)  
    {

		String[] bigMonetaryUnit =
			{
				" HUNDRED",
				" THOUSAND",
				" MILLION",
				" BILLION",
				" TRILLION",
				" QUADRILLION",
				" QUINTILLION" };

		String rtnMoney = null;
		StringBuffer tempMoney = new StringBuffer();
		String [] moneyArray = {};
		String tempString = null;

		int moneyLength = money.length();
		int unitPos = money.length();
		int arrInx = (moneyLength%3 == 0) ?  moneyLength/3 : moneyLength/3+1;

		moneyArray = new String [arrInx];
		
		//3자리 마다 끊어서 배열 생성
		for (int inx = 0; unitPos>0; inx++) {
			if (unitPos-3 >0) {
				tempString = money.substring(unitPos-3, unitPos);
				moneyArray[inx] = tempString;
				unitPos = unitPos-3;								 
			} else {
				tempString = money.substring(0, unitPos);
				moneyArray[inx] =  StringUtil.padLeft(tempString, '0', 3);
				unitPos = 0;
			}
		}

		//숫자를 영문으로 변경 및 단위 추가
		for (int jnx = arrInx-1; jnx>=0; jnx--) {
			String tempMon = moneyArray[jnx];
			int tempMonLen = tempMon.length();
			char num = 0;
			
			for (int knx=0;knx<tempMonLen;knx++){
				num = tempMon.charAt(knx);
				if (knx == 0){ //HUNDRED 단위
					tempMoney.append(convertBaseMonetary2English(num));
					if (num != '0') tempMoney.append(bigMonetaryUnit[0]);
				} else if (knx == 1){ //십단위
					if (num == '1') { //1X인 경우 
						tempMoney.append(convertTeenMonetary2English(tempMon.charAt(knx+1)));
					} else if (num == '0'){ // 0인 경우
						tempMoney.append(convertBaseMonetary2English(tempMon.charAt(knx+1)));
					} else { //10보다 큰 경우
						tempMoney.append(convertDecadeMonetary2English(num));
						tempMoney.append(convertBaseMonetary2English(tempMon.charAt(knx+1)));
					}
				}
			}
			//천단위 이상 단위 붙이기
			if (ConvertUtil.castint(tempMon)>0 && jnx>0) {
				tempMoney.append(bigMonetaryUnit[jnx]);
			}
		}		

		rtnMoney = tempMoney.toString();

		return rtnMoney;     
    }
    
    /**
     * @param num
     * @return java.lang.String
     * @roseuid 40394A4A0281
     */
    private static String convertBaseMonetary2English(char num) 
    {

		String[] baseMonetaryUnit =
			{
				"",
				" ONE",
				" TWO",
				" THREE",
				" FOUR",
				" FIVE",
				" SIX",
				" SEVEN",
				" EIGHT",
				" NINE" };

		String rtnBaseMonetary = null;

		switch (num) {
			case '0' :
				rtnBaseMonetary = baseMonetaryUnit[0];
				break;
			case '1' :
				rtnBaseMonetary = baseMonetaryUnit[1];
				break;
			case '2' :
				rtnBaseMonetary = baseMonetaryUnit[2];
				break;
			case '3' :
				rtnBaseMonetary = baseMonetaryUnit[3];
				break;
			case '4' :
				rtnBaseMonetary = baseMonetaryUnit[4];
				break;
			case '5' :
				rtnBaseMonetary = baseMonetaryUnit[5];
				break;
			case '6' :
				rtnBaseMonetary = baseMonetaryUnit[6];
				break;
			case '7' :
				rtnBaseMonetary = baseMonetaryUnit[7];
				break;
			case '8' :
				rtnBaseMonetary = baseMonetaryUnit[8];
				break;
			case '9' :
				rtnBaseMonetary = baseMonetaryUnit[9];
				break;
			default :
				break;
		}
		return rtnBaseMonetary;     
    }
    
    /**
     * @param num
     * @return java.lang.String
     * @roseuid 40394A4A02AF
     */
    private static String convertTeenMonetary2English(char num) 
    {

		String[] teenMonetaryUnit =
			{
				" TEN",
				" ELEVEN",
				" TWELVE",
				" THIRTEEN",
				" FOURTEEN",
				" FIFTHTEEN",
				" SIXTEEN",
				" SEVENTEEN",
				" EIGHTEEN",
				" NINETEEN" };

		String rtnTeenMonetary = null;

		switch (num) {
			case '0' :
				rtnTeenMonetary = teenMonetaryUnit[0];
				break;
			case '1' :
				rtnTeenMonetary = teenMonetaryUnit[1];
				break;
			case '2' :
				rtnTeenMonetary = teenMonetaryUnit[2];
				break;
			case '3' :
				rtnTeenMonetary = teenMonetaryUnit[3];
				break;
			case '4' :
				rtnTeenMonetary = teenMonetaryUnit[4];
				break;
			case '5' :
				rtnTeenMonetary = teenMonetaryUnit[5];
				break;
			case '6' :
				rtnTeenMonetary = teenMonetaryUnit[6];
				break;
			case '7' :
				rtnTeenMonetary = teenMonetaryUnit[7];
				break;
			case '8' :
				rtnTeenMonetary = teenMonetaryUnit[8];
				break;
			case '9' :
				rtnTeenMonetary = teenMonetaryUnit[9];
				break;
			default :
				break;
		}
		return rtnTeenMonetary;     
    }
    
    /**
     * @param num
     * @return java.lang.String
     * @roseuid 40394A4A02CE
     */
    private static String convertDecadeMonetary2English(char num) 
    {

		String[] decadeMonetaryUnit =
			{
				" TWENTY",
				" THIRTY",
				" FOURTY",
				" FIFTY",
				" SIXTY",
				" SEVENTY",
				" EIGHTY",
				" NINETY" };

		String rtnDecadeMonetary = null;

		switch (num) {
			case '2' :
				rtnDecadeMonetary = decadeMonetaryUnit[0];
				break;
			case '3' :
				rtnDecadeMonetary = decadeMonetaryUnit[1];
				break;
			case '4' :
				rtnDecadeMonetary = decadeMonetaryUnit[2];
				break;
			case '5' :
				rtnDecadeMonetary = decadeMonetaryUnit[3];
				break;
			case '6' :
				rtnDecadeMonetary = decadeMonetaryUnit[4];
				break;
			case '7' :
				rtnDecadeMonetary = decadeMonetaryUnit[5];
				break;
			case '8' :
				rtnDecadeMonetary = decadeMonetaryUnit[6];
				break;
			case '9' :
				rtnDecadeMonetary = decadeMonetaryUnit[7];
				break;
			default :
				break;
		}
		return rtnDecadeMonetary;     
    }
    
    
	/**
	 * 
	 * Format 되지 않은 입력한 값을 요청한 Format으로 Formatting한다.
	 * 
     * <pre>
     * 
     * [사용 예제] 
	 * format(new Double("1234"), "##,###,###" )	===> 1,234
	 * format("123456", "###-###" )							===> 123-456
	 * 
	 * </pre>
     * 
	 * @param object
	 * @param format	 
     * @return java.lang.String
	 * @roseuid 40394A4B00AB
	 */
	public static String format(Object object, String format) 
	{
	  if (object == null)
		  return "";
	  if (format == null)
		  return object.toString();
	  //데이타가 문자열인경우 ###-###형태를 사용합니다.
	  if (object instanceof String) {
		  String s = ( (String) object).trim();
		  int j = 0;
		  StringBuffer sb = new StringBuffer();
		  for (int i = 0; i < format.length(); i++) {
			  if (format.charAt(i) == '#') {
				  if (j >= s.length())
					  return sb.toString();
				  sb.append(s.charAt(j++));
			  }
			  else {
				  sb.append(format.charAt(i));
			  }
		  }
		  return sb.toString();
	  }
	  if (object instanceof Number || object instanceof BigDecimal) {
		  DecimalFormat df = new DecimalFormat(format);
		  return df.format(object);
	  }
	  return object.toString();     
	}
    
	/**
	 * 
	 * 입력한 Date 값을 요청한 Format으로 Formatting한다.
	 * 
     * <pre>
     * 
     * [사용 예제] 
     * 
	 * formatDate(new java.util.Date(),"yyyy년MM월dd일")	===> 2004년02월23일
	 * 
	 * </pre>
     * 
	 * @param date
	 * @param format
	 * @return java.lang.String
	 * @roseuid 40394A4B005D
	 */
	public static String formatDate(java.util.Date date, String format) 
	{
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);    
	}
    
	/**
	 * 
	 * yyyyMMdd로 Format된  입력 String 값을 default Format (yyyy/MM/dd) 으로 Formatting한다.
	 * 
     * <pre>
     * 
     * [사용 예제] 
     * 
	 * CConvertUtil.formatDate("20040225")
	 * 
	 * </pre>
	 * 
	 * @param value
	 * @return java.lang.String
	 */

	public static String formatDate(String value) 
	{
 		return formatDate(value,"yyyy/MM/dd");
	}

	/**
	 * 
	 * yyyyMMdd로 Format 된 입력 String 값을 요청한 Format으로 Formatting한다.
	 * 
	 * <pre>
	 * 
	 * [사용 예제] 
	 * 
	 * formatDate("20040225","yyyy년MM월dd일")	===> 2004년02월25일
	 * formatDate("20040225","yyyy/MM/dd")			===> 2004/02/25
	 * 
	 * </pre>
	 * 
	 * @param value
	 * @param format
	 * @return java.lang.String
	 */	
	public static String formatDate(String value, String format) 	
	{
		return formatTimestamp(convertString2Timestamp(value), format, java.util.Locale.KOREA);  
	}


	/**
	 * 
	 *  yyyyMMdd Format으로 입력된 날짜가 yyyyMMdd Format 인지 확인하고, 적합하면 java.util.Date Type을 Return한다.
	 * 
	 * <pre>
	 * 
	 * [사용 예제]
	 * 
	 * check("20040225")	===> Wed Feb 25 00:00:00 KST 2004
	 * 
	 * </pre>
	 * 
	 * @param date 
	 * @
	 */
	public static java.util.Date formatValidDate(String date)  {
		return formatValidDate(date, "yyyyMMdd");
	}
	
	/**
	 * 
	 * 입력된 날짜가 입력한 Format 에 적합한지 확인하고, 적합하면 java.util.Date Type을 Return한다.
	 * 
	 * <pre>
	 * 
	 * [사용 예제]
	 * 
	 * check("2004-02-26","yyyy-MM-dd")	===> Thu Feb 26 00:00:00 KST 2004
	 * 
	 * </pre>
	 * 
	 * @param date 
	 * @param format
	 * @
	 */
	public static java.util.Date formatValidDate(String date, String format)  {
		
		if (date == null || format == null)	return null;
			
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
		java.util.Date formattedDate = null;
		
		try {

			formattedDate = formatter.parse(date);

		} catch (java.text.ParseException e) {
			//throw new FrameException(MSG_SE00022, new String[] {date,format}); 
		}

		if (! formatter.format(formattedDate).equals(date)) {
			//throw new FrameException(MSG_SE00023, new String[]{"날짜"}); 
		}

		return formattedDate;
		
	}
	/**
	 *
	 * Timestamp 형식의 날짜를 입력한 Format 과 Locale 에 따라  Formatting 한다.
	 * 
	 * <pre>
	 * 
	 * [사용 예제]
	 * 
	 * formatTimestamp("2004-02-25 15:45:31.156","yyyy년MM월dd일",Locale)	===> 2004년02월25일
	 * 
	 * </pre>
	 * 
	 * @param timestamp Timestamp 형식의 날짜
	 * @param format SimpleDateFormat 에 적용할 format	
	 * @param locale 국가별 LOCALE
	 * @return java.lang.String
	 */		
	public static String formatTimestamp(Timestamp timestamp, String format, java.util.Locale locale) {

		SimpleDateFormat formatter = new SimpleDateFormat(format, locale);
		return formatter.format(timestamp); 

	}
	  
    /**
	 * 		
	 * 입력한 값을 $ 로 Formatting한다.
	 * 
     * <pre>
     * 
     * [사용 예제] 
     * 
	 * formatDollarSymbol(1400.02)	===> $1,400.02
	 * formatDollarSymbol(1400)		===> $1,400.00
	 * 
	 * </pre>
	 * 
     * @param num
     * @param value
     * @return java.lang.String
     * @roseuid 40394A4B002E
     */
    public static String formatDollarSymbol(double value) 
    {
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
		return format.format(value);     
    }

	/**
	 * 		
	 * 입력한 값을 $ 로 Formatting팅한다.
	 * 
     * <pre>
     * 
     * [사용 예제] 
     * 
	 * formatDollarSymbol("1400.02")	===> $1,400.02
	 * formatDollarSymbol("1400")		===> $1,400.00
	 * 
	 * </pre>
	 * 
	 * @param str
	 * @param value
	 * @return java.lang.String
	 * @roseuid 40394A4B000F
	 */
	public static String formatDollarSymbol(String value) 
	{
		double number = (new Double(value)).doubleValue();			
		return formatDollarSymbol(number);     
	}
    
	/**
	 * 		
	 * 입력한 Number를 요청한 Format으로 Formatting하고 전달된 Length만큼 Right로 정렬한다.
	 * 
     * <pre>
     * 
     * [사용 예제] 
     * 
	 * formatNumber(new Long("123456"), "*#,###,###,###", 15)	===>        *123,456
	 * 
	 * </pre>
	 * 
	 * @param number
	 * @param format
	 * @param length
	 * @return java.lang.String
	 * @roseuid 40394A4B0138
	 */
	public static String formatNumber(Number number, String format, int length) 
	{
	  String retStr = "";
	  if (number == null || number.toString().equals("")) {
	  }
	  else {
		  double dValue = number.doubleValue();
		  if (format == null || format.equals("")) {
			  retStr = number.toString();
		  }
		  else {
			  DecimalFormat df = new DecimalFormat(format);
			  retStr = df.format(dValue);
		  }
	  }
	  return justify(retStr, length, "R");     
	}
	   
    /**
	 * 		
	 * 입력된 스트링["국가번호","지역번호","국+전화번호","내선번호"]을 전화번호 Format으로 Return한다.
	 * 
     * <pre>
     * 
     * [사용 예제] 
     * 
	 * formatPhoneNo("82","02","63636565","0001")	===>    82-02-6363-6565(0001)
	 * 
	 * </pre>
	 * 
     * @param country
     * @param area
     * @param number
     * @param inline
     * @return java.lang.String
     * @roseuid 40394A4B00DA
     */
    public static String formatPhoneNo(String country, String area, String number, String inline) 
    {
	  StringBuffer sb = new StringBuffer();
	  boolean first = true;
	  if (country != null)
		  country = country.trim();
	  if (area != null)
		  area = area.trim();
	  if (number != null)
		  number = number.trim();
	  if (inline != null)
		  inline = inline.trim();
		  //if (JFCheck.isEmpty(country) == false) {
	  if ( (country != null) && !country.trim().equals("")) {
		  sb.append(country);
		  first = false;
	  }
	  //if (JFCheck.isEmpty(area) == false) {
	  if ( (area != null) && !area.trim().equals("")) {
		  if (first == false)
			  sb.append("-");
		  sb.append(area);
		  first = false;
	  }
	  //if (JFCheck.isEmpty(number) == false) {
	  if ( (number != null) && !number.trim().equals("")) {
		  if (first == false)
			  sb.append("-");
		  if (number.length() > 4) {
			  sb.append(number.substring(0, number.length() - 4));
			  sb.append("-");
			  sb.append(number.substring(number.length() - 4));
		  }
		  else {
			  sb.append(number);
		  }
		  first = false;
	  }
	  //if (JFCheck.isEmpty(inline) == false) {
	  if ( (inline != null) && !inline.trim().equals("")) {
		  sb.append("(" + inline + ")");
	  }
	  return sb.toString();     
    }

	/**
	 * 		
	 * 입력한 값을 원화로 Formatting 한다 ("\" 표시).
	 * 
	 * <pre>
	 * 
	 * [사용 예제] 
	 * 
	 * formatWonSymbol(105)	===> ￦105
	 * 
	 * </pre>
	 * 
	 * @param num
	 * @param value
	 * @return java.lang.String
	 * @roseuid 40394A4A03C8
	 */
	public static String formatWonSymbol(double value) 
	{
		NumberFormat format = NumberFormat.getCurrencyInstance();
		return format.format(value);     
	}
	   
	/**
	 * 		
	 * 입력한 값을 원화로 Formatting 한다 ("\" 표시).
	 * 
     * <pre>
     * 
     * [사용 예제] 
     * 
	 * formatWonSymbol("105")	===> ￦105
	 * 
	 * </pre>
	 * 
	 * @param str
	 * @param value
	 * @return java.lang.String
	 * @roseuid 40394A4A03A9
	 */
	public static String formatWonSymbol(String value) 
	{
		double number = (new Double(value)).doubleValue();
		return ConvertUtil.formatWonSymbol(number);     
	}
        
    /**
     * 
     * 입력한 문자열을 입력한 Length만큼 정렬한다.
     * Length의 기본 값은 0 이고, Length가 0 이면 data의 원래 크기가 적용된다.
     * 정렬의 기본 값은 Left 이다    
     * 
     * <pre>
     * 
     * [사용 예제] 
     * 
	 * justify("*123,456", 15, "R")	===>  bbbbbbb*123,456
	 * 
	 * </pre>
     * 		
     * @param data
     * @param length
     * @param justify
     * @return java.lang.String
     * @roseuid 40394A4B0186
     */
    private static String justify(String data, int length, String justify) 
    {

	  byte[] byteData = data.getBytes();
	  int dataLen = byteData.length;
	  if (length == 0) {
		  length = dataLen;
	  }
	  int target_position = 0;
	  byte[] newByte = new byte[length];
	  for (int inx = 0; inx < newByte.length; inx++)
		  newByte[inx] = 32;
	  int differ = length - dataLen;
	  if (differ > 0) {
		  length = dataLen;
		  if (justify == null || justify.equals("")) {
		  }
		  else if (justify.equals("R")) {
			  target_position = differ;
		  }
		  else if (justify.equals("M")) {
			  target_position = differ / 2;
		  }
	  }
	  System.arraycopy(byteData, 0, newByte, target_position, length);
	  return new String(newByte);     
    }
  
    /**
     * 
     * 입력한 값이 null 이면 null String 으로 Convert하여 Retrun 한다.    
     * 
     * <pre>
     * 
	 * </pre>
     * 		
     * @param obj
     * @return java.lang.String
     */
	public static String NVL(Object obj){
		if(obj != null) return obj.toString();
		else return "";
	}

	/**
	 * 
	 * 입력한 값이 null 이면 defaultVaue 로 Convert하여 Retrun 한다.    
	 * 
	 * <pre>
	 * 
	 * </pre>
	 * 		
	 * @param obj
	 * @param defaultVaue
	 * @return java.lang.String
	 */
	public static String NVL(Object obj,String defaultVaue){
		if(obj != null) return obj.toString();
		else return defaultVaue;
	}

	/**
	 * 
	 * 입력한 값이 null 이면 defaultVaue 로 Convert하여 Retrun 한다.    
	 * 
	 * <pre>
	 * 
	 * </pre>
	 * 		
	 * @param obj
	 * @param defaultVaue
	 * @return java.lang.String
	 */
	public static int NVL(Object obj, int defaultVaue){
		if(obj != null) {
			try{
				return Integer.parseInt(obj.toString());
			}catch(NumberFormatException e){
				return defaultVaue;
			}
		}
		else return defaultVaue;
	}
	
	/**
	 * 
	 * 입력한 값이 null 이면 defaultVaue 로 Convert하여 Retrun 한다.    
	 * 
	 * <pre>
	 * 
	 * </pre>
	 * 		
	 * @param obj
	 * @param defaultVaue
	 * @return java.lang.String
	 */
	public static double NVL(Object obj,double defaultVaue){
		if(obj != null) {
			try{
				return Double.parseDouble(obj.toString());
			}catch(NumberFormatException e){
				return defaultVaue;
			}
		}
		else return defaultVaue;
	}     
}