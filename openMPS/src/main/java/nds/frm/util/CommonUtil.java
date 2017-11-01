package nds.frm.util;

//java API
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import nds.frm.property.Property;




/**
 * <b>class : </b> CommonUtil
 * <b>Class Description</b><br>
 * 공통 Util Class
 * <b>History</b><br>
 * <pre>      : 2009.06.11 초기작성(임진식)</pre>
 * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
 * @version 1.0
 */
public class CommonUtil {

    /**
     * 객체들을 복사해주는 메소드
     *
     * @param objects       복사 대상 객체가 담겨있는 배열
     * @return Object[]     복사된 객체들이 담겨진 배열
     */
    public static Object[] clone(Object[] objects) {
        int length = objects.length;
        Class c = objects.getClass().getComponentType();
        Object array = Array.newInstance(c, length);
        for (int i = 0; i < length; i++) {
            Array.set(array, i, clone(objects[i]));
        }
        return (Object[]) array;
    }

    /**
     * 객체를 복사해주는 메소드
     *
     * @param object      복사 대상 객체
     * @return Object     복사된 객체
     */
    public static Object clone(Object object) {
        Class c = object.getClass();
        Object newObject = null;
        try {
            newObject = c.newInstance();
        }
        catch (Exception e) {
            return null;
        }
        Field[] field = c.getFields();
        for (int i = 0; i < field.length; i++) {
            try {
                Object f = field[i].get(object);
                field[i].set(newObject, f);
            }
            catch (Exception e) {
            }
        }
        return newObject;
    }

    /**
     * Vector에 담긴 객체들을 복사해주는 메소드
     * @param objects   복사 대상 객체가 담겨있는 Vector
     * @return Vector   복사된 객체들이 담겨진 Vector
     */
    public static Vector clone(Vector objects) {
        Vector<Object> newObjects = new Vector<Object>();
        Enumeration e = objects.elements();
        while (e.hasMoreElements()) {
            Object o = e.nextElement();
            newObjects.addElement(clone(o));
        }
        return newObjects;
    }

    /**
     * obj1와 obj2를 attr과 비교해서 그 결과값을 int로 리턴하는 메소드
     *
     * @param obj1      Object
     * @param obj2      Object
     * @param attr      비교대상 attr
     * @return int      비교 결과 값
     */
    private static int compareTo(Object obj1, Object obj2, String attr) {
        int ret = 0;
        Class c1 = obj1.getClass();
        Class c2 = obj2.getClass();
        if (c1.isPrimitive() || c2.isPrimitive()) {
            return ret;
        }
        Field[] f1 = c1.getFields();
        Field[] f2 = c2.getFields();
        int idx1 = -1, idx2 = -1;
        String type1 = null;
        String type2 = null;
        try {
            for (int i = 0; i < f1.length; i++) {
                if (f1[i].getName().equals(attr)) {
                    type1 = f1[i].getType().getName();
                    idx1 = i;
                    break;
                }
            }
            for (int i = 0; i < f1.length; i++) {
                if (f2[i].getName().equals(attr)) {
                    type2 = f2[i].getType().getName();
                    idx2 = i;
                    break;
                }
            }
            if (idx1 == -1 || idx2 == -1)
                return ret;
            if (type1 == null || type2 == null)
                return ret;
            if (!type1.equals(type2))
                return ret;
            if (type1.equals("java.lang.String"))
                ret = ( (String) f1[idx1].get(obj1)).compareTo( (String) f2[idx2].get(obj2));
            else if (type1.equals("int"))
                ret = f1[idx1].getInt(obj1) - f2[idx2].getInt(obj2);
            else if (type1.equals("double"))
                ret = (new Double(f1[idx1].getDouble(obj1))).compareTo(new Double(f2[idx2].getDouble(obj2)));

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * str을 입력받아 byte로 치환해주는 메소드
     * @param str       치환 대상 문자열
     * @param len       길이
     * @return byte     바이트 값으로 치환된 문자 배열
     */
    public static byte[] fixByteLength(String str, int len) {
        byte[] b = new byte[len];
        byte[] o = str.getBytes();
        int fix = (b.length < o.length) ? b.length : o.length;
        System.arraycopy(o, 0, b, 0, fix);
        return b;
    }

    /**
     * 입력받은 str을 off에서 len만큼 잘라서 그 값을 리턴해주는 메소드
     * @param str       치환 대상 문자열
     * @param len       문자열을 자를 길이 값
     * @return String   치환대상 문자열의 len만큼의 문자열
     */
    public static String fixLength(String str, int len) {
        return fixLength(str, 0, len);
    }

    /**
     * 입력받은 str을 off에서 len만큼 잘라서 그 값을 리턴해주는 메소드
     * @param str       치환 대상 문자열
     * @param off       시작 문자열의 자리값
     * @param len       시작 문자열에서 자를 길이 값
     * @return String   치환대상 문자열의 off에서 len만큼의 문자열
     */
    public static String fixLength(String str, int off, int len) {
        int str_len = str.length();
        if (str_len <= off || len <= 0) {
            return "";
        }
        if (str_len - off < len) {
            return str.substring(off);
        }
        return str.substring(off, len + off);
    }

    public static boolean isNumber(Object element)
    {
        if (element instanceof Number)
            return true;
        else
            return false;
    }

    public static boolean isString(Object element)
    {
        if (element instanceof String)
            return true;
        else
            return false;
    }

    /**
     *
     * 입력한 값이 null 또는 null String 일 경우 true를 return 한다.
     *
     * <pre>
     *
     * [사용 예제]
     *
     * ValidationUtil.isEmpty("")		===> true
     * ValidationUtil.isEmpty(null)	===> true
     * ValidationUtil.isEmpty("1")		===> false
     *
     * </pre>
     *
     * @param value
     * @return boolean
     */
    public static boolean isEmpty(String value)
    {
        if (value == null || "".equals(value.trim()))
            return true;
        return false;
    }
    
    /**
     * HashMap에 특정 키값이 있는지를 체크
     * @param map
     * @param value
     * @return boolean
     */
    public static boolean isHashKey(HashMap map , String value)
    {
    	if (map == null) return false;
    	if (value == null || "".equals(value.trim())) return false;    	
    	return map.containsKey(value);
    }    

    /**
     *
     * 입력한 Array의 값이 입력한 Key 와 첫번째로 같은 index를 return 한다.
     *
     * <pre>
     *
     * [사용 예제]
     *
     *	String [] a = new String[3];
     *	a[0] = new String("ccc");
     *	a[1] = new String("bbb");
     *	a[2] = new String("aaa");
     *
     *	searchArray(a,"aaa")	===> 2
     *
     *	String [] a = new String[3];
     *	a[0] = new String("ccc");
     *	a[1] = new String("bbb");
     *	a[2] = new String("aaa");
     *
     *	searchArray(a,"a")	===> -1
     *
     * </pre>
     *
     * @param array
     * @param key
     * @return int
     */
    public static int searchArray(Object [] array, Object key) {
        if (key==null || array == null)
            return -1;
        for (int i = 0; i < array.length; i++) {
            if (key.equals(array[i]))
                return i;
        }
        return -1;
    }

    /**
     * Object를 null로 fix해주는 문자열
     *
     * @param o     Object
     */
    public static void fixNull(Object o) {
        if (o == null) {
            return;
        }
        Class c = o.getClass();
        if (c.isPrimitive()) {
            return;
        }
        Field[] fields = c.getFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                Object f = fields[i].get(o);
                Class fc = fields[i].getType();
                if (fc.getName().equals("java.lang.String")) {
                    if (f == null) {
                        fields[i].set(o, "");
                    }
                    else {
                        fields[i].set(o, f);
                    }
                }
            }
            catch (Exception e) {
            }
        }
    }

    /**
     * 모든 공백을 Null
     * @param o
     */
    public static void fixNullAll(Object o) {
        if (o == null) {
            return;
        }
        Class c = o.getClass();
        if (c.isPrimitive()) {
            return;
        }
        if (c.isArray()) {
            int length = Array.getLength(o);
            for (int i = 0; i < length; i++) {
                Object element = Array.get(o, i);
                fixNullAll(element);
            }
        }
        else {
            Field[] fields = c.getFields();
            for (int i = 0; i < fields.length; i++) {
                try {
                    Object f = fields[i].get(o);
                    Class fc = fields[i].getType();
                    if (fc.isPrimitive()) {
                        continue;
                    }
                    if (fc.getName().equals("java.lang.String")) {
                        if (f == null) {
                            fields[i].set(o, "");
                        }
                        else {
                            fields[i].set(o, f);
                        }
                    }
                    else if (f != null) {
                        fixNullAll(f);
                    }
                    else {} // Some Object, but it's null.
                }
                catch (Exception e) {
                }
            }
        }
    }

    /**
     * Object를 입력받아 문자열 앞뒤의 공백을 제거하고 Null로 fix해주는 메소드
     *
     * @param o     Object
     */
    public static void fixNullAndTrim(Object o) {
        if (o == null) {
            return;
        }
        Class c = o.getClass();
        if (c.isPrimitive()) {
            return;
        }
        Field[] fields = c.getFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                Object f = fields[i].get(o);
                Class fc = fields[i].getType();
                if (fc.getName().equals("java.lang.String")) {
                    if (f == null) {
                        fields[i].set(o, "");
                    }
                    else {
                        String item = trim( (String) f);
                        fields[i].set(o, item);
                    }
                }
            }
            catch (Exception e) {
            }
        }
    }

    /**
     * Object를 입력받아 모든문자열을 제거하고 Null로 fix해주는 메소드
     *
     * @param o Object
     */
    public static void fixNullAndTrimAll(Object o) {
        if (o == null) {
            return;
        }
        Class c = o.getClass();
        if (c.isPrimitive()) {
            return;
        }
        if (c.isArray()) {
            int length = Array.getLength(o);
            for (int i = 0; i < length; i++) {
                Object element = Array.get(o, i);
                fixNullAndTrimAll(element);
            }
        }
        else {
            Field[] fields = c.getFields();
            for (int i = 0; i < fields.length; i++) {
                try {
                    Object f = fields[i].get(o);
                    Class<?> fc = fields[i].getType();
                    if (fc.isPrimitive()) {
                        continue;
                    }
                    if (fc.getName().equals("java.lang.String")) {
                        if (f == null) {
                            fields[i].set(o, "");
                        }
                        else {
                            String item = trim( (String) f);
                            fields[i].set(o, item);
                        }
                    }
                    else if (f != null) {
                        fixNullAndTrimAll(f);
                    }
                    else {} // Some Object, but it's null.
                }
                catch (Exception e) {
                }
            }
        }
    }

    /**
     * request에서 parameter이름의 값을 추출하여 넘겨주는 메소드
     *
     * @param request           HttpServletRequest
     * @param parameterName     파라미터 이름
     * @param defaultValue      기본 값
     * @return Object           request에서 parameter 이름으로 추출한 결과 값
     */
    public static Object getAttribute(HttpServletRequest request,
                                      String parameterName, Object defaultValue) {
        Object paramValue = request.getAttribute(parameterName);
        if (paramValue == null) {
            paramValue = defaultValue;
        }
        return paramValue;
    }

    /**
     * request에서 parameter이름의 값을 추출하여 넘겨주는 메소드
     *
     * @param request               HttpServletRequest
     * @param parameterName         파라미터 이름
     * @param isParameterRequired   boolean
     * @return Object               request에서 parameter 이름으로 추출한 결과 값
     * @throws java.lang.Exception
     */
    public static Object getAttribute(HttpServletRequest request,
                                      String parameterName,
                                      boolean isParameterRequired) throws Exception {
        Object paramValue = request.getAttribute(parameterName);
        if ( (isParameterRequired) && (paramValue == null))
            throw new Exception("Parameter " + parameterName + " was not specified.");
        return paramValue;
    }

    /**
     * 세션에서 parameter이름의 값을 추출하여 넘겨주는 메소드
     *
     * @param session           HttpSession
     * @param parameterName     파라미터 이름
     * @param defaultValue      기본 값
     * @return Object           세션에서 parameter 이름으로 추출한 결과 값
     */
    public static Object getAttribute(HttpSession session, String parameterName,
                                      Object defaultValue) {
        Object paramValue = session.getAttribute(parameterName);
        if (paramValue == null) {
            paramValue = defaultValue;
        }
        return paramValue;
    }

    /**
     * 세션에서 parameter이름의 값을 추출하여 넘겨주는 메소드
     *
     * @param session               HttpSession
     * @param parameterName         파라미터 이름
     * @param isParameterRequired   boolean
     * @return Object               세션에서 parameter 이름으로 추출한 결과 값
     * @throws java.lang.Exception
     */
    public static Object getAttribute(HttpSession session, String parameterName,
                                      boolean isParameterRequired) throws Exception {
        Object paramValue = session.getAttribute(parameterName);
        if ( (isParameterRequired) && (paramValue == null))
            throw new Exception("Parameter " + parameterName + " was not specified.");
        return paramValue;
    }

    /**
     * HashMap을 입력받아 HashMap의 Key값들을 추출해내는 메소드
     *
     * @param map       HashMap
     * @return String   HashMap의 Key값들을 담은 배열
     */
    public static String[] getHashMapKeys(HashMap map) {
        if (map == null)
            return null;

        String[] ret = new String[map.size()];
        int inc = 0;
        for (Iterator i = map.keySet().iterator(); i.hasNext(); ) {
            ret[inc++] = (String) i.next();
        }
        return ret;
    }

    /**
     * request에서 parameter 이름으로 검색한 결과값을 넘겨주는 메소드
     *
     * @param request           HttpServletRequest
     * @param parameterName     파라미터 이름
     * @param defaultValue      기본 값
     * @return String           파라미터 이름에 대한 값
     */
    public static String getParameter(HttpServletRequest request,
                                      String parameterName, String defaultValue) {
        String paramValue = request.getParameter(parameterName);
        if (paramValue == null || paramValue.length() == 0)
            paramValue = defaultValue;
        return paramValue;
    }

    /**
     * request에서 parameter 이름으로 검색한 결과값을 넘겨주는 메소드
     *
     * @param request               HttpServletRequest
     * @param parameterName         파라미터 이름
     * @param isParameterRequired   boolean
     * @return String               파라미터 이름에 대한 값
     * @throws java.lang.Exception
     */
    public static String getParameter(HttpServletRequest request,
                                      String parameterName,
                                      boolean isParameterRequired) throws Exception {
        String paramValue = request.getParameter(parameterName);
        if ( (isParameterRequired) && (paramValue == null))
            throw new Exception("Parameter " + parameterName + " was not specified.");
        return paramValue;
    }

    /**
     * request에서 parameter 이름으로 검색한 결과값을 넘겨주는 메소드
     *
     * @param request           HttpServletRequest
     * @param parameterName     파라미터 이름
     * @param defaultValue      기본 값
     * @return String           파라미터이름을 가지고 있는 값들을 담은 배열
     */
    public static String[] getParameterValues(HttpServletRequest request,
                                              String parameterName,
                                              String[] defaultValue) {
        String[] paramValue = request.getParameterValues(parameterName);
        if (paramValue == null)
            paramValue = defaultValue;
        return paramValue;
    }

    /**
     * request에서 parameter 이름으로 검색한 결과값을 넘겨주는 메소드
     *
     * @param request                   HttpServletRequest
     * @param parameterName             파라미터 이름
     * @param isParameterRequired       boolean
     * @return String                   파라미터이름을 가지고 있는 값들을 담은 배열
     * @throws java.lang.Exception
     */
    public static String[] getParameterValues(HttpServletRequest request,
                                              String parameterName,
                                              boolean isParameterRequired) throws Exception {

        String[] paramValue = request.getParameterValues(parameterName);
        if ( (isParameterRequired) && (paramValue == null))
            throw new Exception("Parameter " + parameterName + " was not specified.");
        return paramValue;
    }
    
    /**
     * 해당 클래스의 setter함수를 리턴
     * @param methodClass
     * @param key
     * @return Method
     */
    public static Method getMethodSetter(Class methodClass , String key , String type){
		Method method = null;
		Class[] paramType = null;
        try {  
    		if(type.equals("String")) {
    			paramType = new Class[]{String.class};
    		} else if(type.equals("java.math.BigDecimal")){
    			paramType = new Class[]{BigDecimal.class};
    		} else if(type.equals("Double")){
    			paramType = new Class[]{Double.class};		
    		} else if(type.equals("Float")){			
    			paramType = new Class[]{Float.class};
    		} else if(type.equals("Integer")){			
    			paramType = new Class[]{Integer.class};
    		} else if(type.equals("Long")){			
    			paramType = new Class[]{Long.class};
    		} else if(type.equals("Date")) {
    			paramType = new Class[]{java.util.Date.class};
    		}
    		
        	//method = methodClass.getMethod("setCode",null);
        	String strMethodNm = getSetterName(key);
			method = methodClass.getMethod(strMethodNm, paramType);			
        }
        catch (NoSuchMethodException nsme) {
            // 메소드 찾기 에러 발생
        }	    	
    	return method;
    }
    
    /**
     * 해당 클래스의 getter함수를 리턴
     * @param methodClass
     * @param key
     * @return Method
     */
    public static Method getMethodGetter(Class methodClass , String key){
		Method method = null;
        try {  
        	Class[] paramType = new Class[0];
			method = methodClass.getMethod(getGetterName(key), paramType);			
        }
        catch (NoSuchMethodException nsme) {
            // 메소드 찾기 에러 발생
        }	    	
    	return method;
    }    
    
    /**
     * 해당 변수명을 가지고 setter함수명으로 리턴한다.
     * @param value
     * @return String
     */
    public static String getSetterName(String value){  
    	StringBuffer sb = new StringBuffer();
    	sb.append("set");
    	sb.append(getFirstUpperString(ConvertUtil.convertDBToJava(value)));    	
    	return sb.toString();
    }
    
    /**
     * 해당 변수명을 가지고 getter함수명으로 리턴한다.
     * @param value
     * @return String
     */
    public static String getGetterName(String value){  
    	StringBuffer sb = new StringBuffer();
    	sb.append("get");
    	sb.append(getFirstUpperString(ConvertUtil.convertDBToJava(value)));    	
    	return sb.toString();
    }    
    
    /**
     * 문자열의 첫글자를 대문자로 변환하여 리턴
     * 주로 getter , setter 함수를 찾고자 할 경우 사용
     * @param value
     * @return String
     */
    public static String getFirstUpperString(String value){    	
    	StringBuffer sb = new StringBuffer();
    	char[] c = value.toCharArray();
    	for (int i = 0; i < c.length; i++) {
    		if(i<1){
    			sb.append( (c[i] + "").toUpperCase());
    		}else{
    			sb.append(c[i] + "");
    		}
        }
    	return sb.toString();
    }

    /**
     * 문자열을 입력받아 문자열에 포함된 숫자만 추출해서 반환하는 메소드
     * @param str       검색대상 문자열
     * @return String   추출한 숫자만 담고 있는 문자열
     */
    public static String getRawDigit(String str) {
        char[] c = str.toCharArray();
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < c.length; i++) {
            if (Character.isDigit(c[i])) {
                buff.append(c[i]);
            }
        }
        return buff.toString();
    }

    /**
     * Set에 담긴 값들을 Object의 배열 형태로 리턴하는 메소드
     * @param set           Set
     * @return Object       Set에 담긴 값들을 담은 메소드
     */
    public static Object[] getSetValues(Set set) {
        if (set == null)
            return null;

        Object[] ret = new Object[set.size()];
        int inc = 0;
        for (Iterator i = set.iterator(); i.hasNext(); ) {
            ret[inc++] = i.next();
        }
        return ret;
    }

    /**
     * Throwable을 입력받아 Stack형식으로 담긴 자취를 담은 문자열을 반환하는 메소드
     *
     * @param e             Throwable
     * @return String       Throwable의 값을 담은 문자열
     */
    public static String getStackTrace(Throwable e) {
        java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
        java.io.PrintWriter writer = new java.io.PrintWriter(bos);
        e.printStackTrace(writer);
        writer.flush();
        return bos.toString();
    }

    /**
     * 파일 이름을 받아서 시스템의 분리기호로 치환된 파일 이름을 리턴해주는 메세지
     * @param fileName      치환대상 파일이름
     * @return String       시스템의 분리기호로 치환된 파일이름
     */
    public static String getSystemFileName(String fileName) {
        String separator = System.getProperty("file.separator");
        fileName = replaceAll(fileName, "\\", separator);
        fileName = replaceAll(fileName, "/", separator);
        return fileName;
    }

    /**
     * 정렬 메소드
     *
     * @param objs
     * @param compareAttr
     * @param order
     */
    public static void quickSort(Object[] objs, String compareAttr, String order) {
        quickSort(objs, compareAttr, order, 0, objs.length);
    }

    /**
     * 정렬 메소드
     *
     * @param obj
     * @param compareAttr
     * @param order
     * @param low
     * @param high
     */
    private static void quickSort(Object[] obj, String compareAttr, String order, int low, int high) {
        if (low >= high)
            return;

        int i, count = low;
        Object temp, pibot = obj[low];
        for (i = low + 1; i < high; i++) {
            if ( (order.equals("ASC") && compareTo(pibot, obj[i], compareAttr) > 0) ||
                (order.equals("DESC") && compareTo(pibot, obj[i], compareAttr) < 0)) {
                temp = obj[i];
                for (int j = i - 1; j >= low; j--) {
                    obj[j + 1] = obj[j];
                }
                obj[low] = temp;
                count++;
            }
        }
        quickSort(obj, compareAttr, order, count + 1, i);
        quickSort(obj, compareAttr, order, low, count);
    }

    /**
     * 해당 문자열(str)의 off부터 leng의 사이의 문자를 replace로 변화해 주는 메소드
     *
     * @param str           치환 대상 문자열
     * @param off           치환 대상 시작점
     * @param len           치환 대상 끝점
     * @param replace       치환될 문자
     * @return String       치환대상 문자열을 치환될 문자열로 바꾼 문자열
     */
    public static String replace(String str, int off, int len, String replace) {
        StringBuffer buff = new StringBuffer(str);
        buff.replace(off, off + len, replace);
        return buff.toString();
    }

    /**
     * 해당 문자열(str1)의 특정 문자열(str2)를 off와 str2사이에서 찾아서 원하는 문자열(replace)로 바꾸기
     *
     * @param str1          치환 대상 문자열
     * @param off           치환 대상 문자를 찾을 시작점
     * @param str2          치환 대상 문자
     * @param replace       치환될 문자
     * @return String       치환 대상 문자열을 치환될 문자열로 바꾼 문자열
     */
    public static String replace(String str1, int off, String str2,
                                 String replace) {
        off = str1.indexOf(str2, off);
        if (off == -1)
            return str1;

        StringBuffer buff = new StringBuffer(str1);
        buff.replace(off, off + str2.length(), replace);
        return buff.toString();
    }

    /**
     * 해당 문자열(str1)의 특정 문자열(str2)를 문자열(replace)로 바꾸기
     *
     * @param str1          치환 대상 문자열
     * @param str2          치환 대상 문자
     * @param replace       치환될 문자
     * @return String       치환 대상 문자열을 치환될 문자열로 바꾼 문자열
     */
    public static String replace(String str1, String str2, String replace) {
        return replace(str1, 0, str2, replace);
    }

    /**
     * 해당 문자열(str1)의 특정 문자열(str2)를 off와 str2사이에서 찾아서 원하는 문자열(replace)로 모두 바꾸기
     *
     * @param str1          치환 대상 문자열
     * @param off           치환 대상 문자를 찾을 시작점
     * @param str2          치환 대상 문자
     * @param replace       치환될 문자
     * @return String       치환 대상 문자열을 치환될 문자열로 바꾼 문자열
     */
    public static String replaceAll(String str1, int off, String str2,
                                    String replace) {
        if (str1 == null || str2 == null || replace == null)
            return str1;

        off = str1.indexOf(str2, off);
        StringBuffer buff = new StringBuffer(str1);
        while (off != -1) {
            buff.replace(off, off + str2.length(), replace);
            str1 = buff.toString();
            if (off + str2.length() < str1.length())
                off = str1.indexOf(str2, off + str2.length() + 1);
            else
                off = -1;
        }
        return str1;
    }

    /**
     * 해당 문자열(str1)의 특정 문자열(str2)를 찾아서 원하는 문자열(replace)로 모두 바꾸기
     *
     * @param str1          치환 대상 문자열
     * @param str2          치환 대상 문자
     * @param replace       치환될 문자
     * @return String       치환 대상 문자열을 치환될 문자열로 바꾼 문자열
     */
    public static String replaceAll(String str1, String str2, String replace) {
        return replaceAll(str1, 0, str2, replace);
    }

    /**
     * 해당 문자를 우편 번호 형식으로 바꾸기
     *
     * @param zipCD     우편번호 형식으로 치환할 문자열
     * @return String   우편번호 형식으로 치환된 문자열
     */
    public static String toFormatZip(String zipCD) {
        if (zipCD == null || zipCD.length() != 6) {
            return zipCD;
        }
        return zipCD.substring(0, 3) + "-" + zipCD.substring(3);
    }
    
    /**
     * 해당 문자의 공백제거
     *
     * @param s         공백 제거 대상 문자열
     * @return String   공백이 제거된 문자열
     */
    public static String trim(String s) {
        int st = 0;
        char[] val = s.toCharArray();
        int count = val.length;
        int len = count;
        while ( (st < len) && ( (val[st] <= ' ') || (val[st] == '　'))) {
            st++;
        }
        while ( (st < len) && ( (val[len - 1] <= ' ') || (val[len - 1] == '　'))) {
            len--;
        }
        return ( (st > 0) || (len < count)) ? s.substring(st, len) : s;
    }
    
    public static Object getTypeObject(String type, Object value){
    	Object rtnObject = null;
		if(type.equals("String")) {
			rtnObject = (String)value;
		} else if(type.equals("java.math.BigDecimal")){
			rtnObject = ConvertUtil.castBigDecimal(value);
		} else if(type.equals("Double")){
			rtnObject = ConvertUtil.castDouble(value);			
		} else if(type.equals("Float")){			
			rtnObject = ConvertUtil.castFloat(value);
		} else if(type.equals("Integer")){			
			rtnObject = ConvertUtil.castInteger(value);
		} else if(type.equals("Long")){			
			rtnObject = ConvertUtil.castLong(value);
		} else if(type.equals("java.util.Date")) {
			try {
				rtnObject = Property.defaultDateFormat.parse((String)value);
			} catch(ParseException pe) {
			}
		} else {
			rtnObject = value;
		}
		return rtnObject;
    }
}