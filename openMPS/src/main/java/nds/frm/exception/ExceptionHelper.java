package nds.frm.exception;

import java.io.StringWriter;
import java.io.PrintWriter;

import com.ibatis.common.jdbc.exception.NestedSQLException;

public class ExceptionHelper {
    /**
     * System Exception을 처리 하기 위한 Default 메세지 코드
     */
    private static final String SYS_EXCEPTION_CODE = "SYS000";

    public ExceptionHelper() {
    }

    /**
     * Exception 처리시 공통적인 에러부분을 처리하는 함수
     * <pre>
     *  Throwable과 Trace정보를 파라미터로 받아서 예외처리를 한다
     * </pre>
     * @param tw
     * @param traceInfo
     * @return MainException
     */
    public static MainException getException(Throwable tw , String traceInfo){
		if (tw.getCause() instanceof NestedSQLException) {
			NestedSQLException nex = (NestedSQLException)tw.getCause();
			String strSQLErrCode =  nex.getSQLState();
			if(strSQLErrCode.equals("23000")){
				return new BizException(nex.getSQLState(),new Object[] {""});
			}else{
				return new MainException(SYS_EXCEPTION_CODE, traceInfo, tw);		
			}
		}
		else if (tw instanceof BizException) {
            return new BizException(tw, traceInfo);
        }
        else {
            StringWriter sw = new StringWriter();
            tw.printStackTrace(new PrintWriter(sw, true));
            return new MainException(SYS_EXCEPTION_CODE, traceInfo, tw);
        }
    } 
    
    /**
     * Exception 처리시 공통적인 에러부분을 처리하는 함수
     * <pre>
     *  Throwable과 Trace정보를 파라미터로 받아서 예외처리를 한다
     * </pre>
     * @param tw
     * @param traceInfo
     * @return MainException
     */
    public static MainException getException(Throwable tw , String traceInfo , String strErrCode , Object[] arrParam){
		if (tw.getCause() instanceof NestedSQLException) {
			NestedSQLException nex = (NestedSQLException)tw.getCause();
            String strSQLErrCode   =  nex.getSQLState();
			//String strSQLErrCode   =  "";
            String strCause        =  nex.getCause().getMessage();
            if(strCause.indexOf("ORA-") != -1){
                strSQLErrCode = strCause.substring(strCause.indexOf("ORA-"), strCause.indexOf("ORA-")+9);
                return new BizException(strSQLErrCode, arrParam, tw);
            }
            if(strSQLErrCode.equals("23000")){
                return new BizException(nex.getSQLState(),new Object[] {""}, tw);
			}else{
				return new MainException(SYS_EXCEPTION_CODE, traceInfo, tw);		
			}
		    /*
		    TODO 개발 로그 보기
            return new MainException(SYS_EXCEPTION_CODE, traceInfo, tw);
            */        

		}
		else if (tw instanceof BizException) {
            return new BizException(tw, traceInfo);
        }
        else {
            StringWriter sw = new StringWriter();
            tw.printStackTrace(new PrintWriter(sw, true));
            if(strErrCode!= null && !strErrCode.equals(SYS_EXCEPTION_CODE)){
            	return new MainException(strErrCode, arrParam);
            }else{
            	return new MainException(SYS_EXCEPTION_CODE, traceInfo, tw);
            }
        }
    }      
}
