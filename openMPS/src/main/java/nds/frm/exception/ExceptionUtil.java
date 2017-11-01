package nds.frm.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
    public ExceptionUtil() {
        super();
    }
    
    /**
     * StackTrace내용을 String으로 변환하여 반환
     * @return String
     */
    public static String captureStackTrace(Throwable tw) {
        StringWriter sw = new StringWriter();
        tw.printStackTrace(new PrintWriter(sw, true));
        return sw.toString();
    }
}
