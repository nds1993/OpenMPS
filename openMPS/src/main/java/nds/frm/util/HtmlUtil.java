package nds.frm.util;

/**
 * <b>class : </b> HtmlUtil
 * <b>Class Description</b><br>
 * HTML 코드를 생성하는 Class
 * <b>History</b><br>
 * <pre>      : 2009.06.11 초기작성(임진식)</pre>
 * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
 * @version 1.0
 */
public class HtmlUtil {

    public HtmlUtil() {}

    /**
     *
     * 입력된 스트링(s)을 HTML 형태로 변환한다.
     *
     * <pre>
     *
     * [사용 예제]
     *
     * java2Html("\r\n \r\n")
     * ===> <br>
     *			<br>
     *
     * </pre>
     *
     * @param s
     * @return java.lang.String
     */
    public static String java2Html(String s) {
        if (s == null)
            return "";

        StringBuffer buf = new StringBuffer();
        char[] c = s.toCharArray();
        int len = c.length;
        for (int i = 0; i < len; i++) {
            if (c[i] == '&')
                buf.append("&amp;");
            else if (c[i] == '<')
                buf.append("&lt;");
            else if (c[i] == '>')
                buf.append("&gt;");
            else if (c[i] == '"')
                buf.append("&quot;");
            else if (c[i] == '\'')
                buf.append("&#039;");
            else if (c[i] == '\n')
                buf.append("<br>");
            else
                buf.append(c[i]);
        }
        return buf.toString();
    }

    /**
     *
     * 입력된 스트링(s)에서 carriage return 과 new line을 제거한 스트링을 반환한다.
     *
     * @param s
     * @return java.lang.String
     */
    public static String removeCRLF(String str) {
        return StringUtil.stringRemove(str, "\r\n\"");
    }

    /**
     *
     * 입력된 스트링(s)에서 carriage return 과 new line을 제거한 스트링을 반환한다.
     *
     * @param s
     * @return java.lang.String
     */
    public static String convertGridData(String str) {
        String tmpString = "";
        tmpString = str.replaceAll("\n" , "gridEnter");
        tmpString = tmpString.replaceAll("\r" , "");
        return tmpString;
    }
}