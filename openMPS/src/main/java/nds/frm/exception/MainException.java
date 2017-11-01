package nds.frm.exception;

import java.util.Vector;

import nds.frm.message.MessageUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




public class MainException  extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    static Logger logger = LogManager.getLogger(MainException.class);

	/**
     * Exception의 종류를 구분하기 위한 헤더 문자열.
     */
    private String msgHeader = "[ 예외적 상황 발생 ]";

    /**
     * 메시지 코드
     */
    private String msgCode = null;

    /**
     * 메시지내용
     */
    private String msgDesc = null;

    /**
     * 메시지의 {}부분을 대체할 Argument
     */
    private Object[] msgArgu = null;

    /**
     * Trace정보
     */
    private Vector<String> traceInfo = null;

    /**
     * Stacktrace내용
     */
    private String msgStacktrace = null;

    /**
     * 메시지헤더, 메시지코드 및 메시지내용을 구분하기 위한 Delimiter.
     */
    private final String MESSAGE_DELIMITER = new Character( (char) 0x8).toString();

    /**
     * 기본생성자
     */
    public MainException() {
        super();
    }

    /**
     * Exception을 최초로 발생시킬때 사용되는 생성자.
     * <pre>
     * 	메시지내에 {}부분이 없는 경우에 사용한다.
     * </pre>
         @param msgCode 메시지조회를 위한 코드
         @since 2.0
     */
    public MainException(final String msgCode) {
        this.setMsgCode(msgCode);
    }

    /**
     * Exception을 최초로 발생시킬 때 사용되는 생성자.
     * <pre>
     * 	메시지내에 {}부분이 있는 경우에 사용한다.
     * </pre>
     @param msgCode 메시지 코드
     @param msgArgu 메시지의 {}를 대체할 파라미터
     @since 2.0
     */
    public MainException(final String msgCode, final Object[] msgArgu) {
        this.setMsgCode(msgCode);
        this.setMsgArgu(msgArgu);
    }

    /**
     * Exception을 최초로 발생시킬 때 사용되는 생성자.
     * <pre>
     * 	메시지내에 {}부분이 있는 경우에 사용한다.
     * </pre>
     * @param msgCode 메시지 코드
     * @param msgArgu 메시지의 {}를 대체할 파라미터
     * @param preException Thorwable
     * @since 2.0
     */
    public MainException(final String msgCode, final Object[] msgArgu, final Throwable preException) {
        this.setMsgCode(msgCode);
        this.setMsgArgu(msgArgu);
        this.setMsgStacktrace(ExceptionUtil.captureStackTrace(preException));

        if (preException instanceof MainException) {
            this.traceInfo = ( (MainException) preException).getTraceInfo();
        }
        else {
            this.traceInfo = new Vector<String>();
            this.traceInfo.addElement(preException.getClass().getName() + " : " +
                                      preException.getMessage());
        }
    }

    /**
     * Exception을 최초로 발생시킬 때 사용되는 생성자.
     * <pre>
     * 	메시지내에 {}부분이 있는 경우에 사용한다.
     * </pre>
     @param msgCode 메시지 코드
     @param msgArgu 메시지의 {}를 대체할 파라미터
     @since 2.0
     */
    public MainException(final String msgCode, final Throwable preException) {
        if (this.getMsgCode() == null || this.getMsgCode().equals("")) {
            this.setMsgCode(msgCode);
        }

        if (this.getMsgCode() == null || this.getMsgCode().equals("")) {
            this.setMsgCode(msgCode);
        }

        if (this.getMsgStacktrace() == null || this.getMsgStacktrace().equals("")) {
            this.setMsgStacktrace(ExceptionUtil.captureStackTrace(preException));
        }

        if (preException instanceof MainException) {
            this.traceInfo = ( (MainException) preException).getTraceInfo();
        }
        else {
            this.traceInfo = new Vector<String>();
            this.traceInfo.addElement(preException.getClass().getName() + " : " +
                                      preException.getMessage());
        }
    }

    /**
     * Exception을 최초로 발생시킬 때 사용되는 생성자.
     * <pre>
     * 	메시지내에 {}부분이 있는 경우에 사용한다.
     * </pre>
     @param msgCode 메시지 코드
     @param msgArgu 메시지의 {}를 대체할 파라미터
     @since 2.0
     */
    public MainException(final Throwable preException, final String traceInfo) {
        if (preException instanceof MainException) {
            this.msgCode = ( (MainException) preException).getMsgCode();
            this.msgArgu = ( (MainException) preException).getMsgArgu();
            this.traceInfo = ( (MainException) preException).getTraceInfo();
            this.msgStacktrace = ( (MainException) preException).
                getMsgStacktrace();

            if (this.traceInfo == null)
                this.traceInfo = new Vector<String>();
        }
        else {
            this.traceInfo = new Vector<String>();
            this.traceInfo.addElement(preException.getClass().getName() + " : " +
                                      preException.getMessage());
        }
        this.traceInfo.addElement(traceInfo);
    }

    /**
     * Exception을 최초로 발생시킬 때 사용되는 생성자.
     * <pre>
     * 	메시지내에 {}부분이 있는 경우에 사용한다.
     * </pre>
     @param msgCode 메시지 코드
     @param msgArgu 메시지의 {}를 대체할 파라미터
     @since 2.0
     */
    public MainException(final String msgCode, final String traceInfo,
                         final Throwable preException) {
        if (this.getMsgCode() == null || this.getMsgCode().equals("")) {
            this.setMsgCode(msgCode);
        }

        if (this.getMsgStacktrace() == null ||
            this.getMsgStacktrace().equals("")) {
            this.setMsgStacktrace(ExceptionUtil.captureStackTrace(preException));
        }

        if (preException instanceof MainException) {
            this.traceInfo = ( (MainException) preException).getTraceInfo();

            if (this.traceInfo == null)
                this.traceInfo = new Vector<String>();
        }
        else {
            this.traceInfo = new Vector<String>();
            this.traceInfo.addElement(preException.getClass().getName() + " : " +
                                      preException.getMessage());
        }
        this.traceInfo.addElement(traceInfo);
    }
    
    /**
     * 메시지를 조회하여 msgDesc변수에 set함수
     * 자체 헤더를 이용하는 경우
     * @return void
     */
    public void setMsgDesc() {
        String tmpMessage = null;
        String tmpMsgStacktrace = null;
        try {
                if (this.msgArgu != null) {
                    // 메세지코드,메세지 Argument로 메시지 가져오기
                    tmpMessage = MessageUtil.messageMapping(this.msgCode, this.msgArgu);
                }
                else {
                    // 메세지코드로 메시지 가져오기
                    tmpMessage = MessageUtil.messageMapping(this.msgCode);
                }

                // 시스템에러이면 Trace정보를 제일 처음 부분에 보여주면서 처리한다.
                if(this.getMsgStacktrace() == null) {
                    tmpMsgStacktrace = this.getMsgStacktrace();
                }else{
                    tmpMsgStacktrace = this.getMsgStacktrace();
                }

                if(this.msgCode.equals("SYS000")){
                     if (this.getTraceInfo() != null && this.getTraceInfo().size() > 0) {
                         tmpMsgStacktrace = this.getTraceInfo().get(0).toString() + tmpMsgStacktrace;
                     }
                }

        }
        catch (Throwable t) {
            tmpMessage = "CODE [" + this.msgCode + "]에 대한 Message를 조회할 수 없습니다.\r\n" + t.getMessage();
        }

        // 로그 기록하기
        String strMessage = "";
        String strSpace = "                                ";
        strMessage = strMessage + "타이틀 :  " + this.getMsgHeader() + "\r\n";
        if (this.getMsgCode() == null) {
            strMessage = strMessage + strSpace + "에러코드 :  \r\n";
        }
        else {
            strMessage = strMessage + strSpace + "에러코드 :  " + this.getMsgCode() + "\r\n";
        }
        strMessage = strMessage + strSpace + "에러내용 :  " + tmpMessage + "\r\n";

        if (tmpMsgStacktrace == null) {
            strMessage = strMessage + strSpace + "에러상세내용 :  \r\n";
        }
        else {
            strMessage = strMessage + strSpace + "에러상세내용 :  " + tmpMsgStacktrace + "\r\n";
        }
        
        logger.error(strMessage);
        //System.out.println(strMessage);
        //Logging4J.logWrite(MainException.class, Logging4JMode.ERROR_LEVEL, strMessage);
        

        // Request에 넘길 값을 만들기
        this.msgDesc = new StringBuffer().append(this.getMsgHeader())
            .append(MESSAGE_DELIMITER)
            .append( (this.getMsgCode() == null) ? "" : this.getMsgCode())
            .append(MESSAGE_DELIMITER)
            .append(tmpMessage)
            .append(MESSAGE_DELIMITER)
            .append(tmpMsgStacktrace)
            .toString();
    }

    /**
     * msgHeader Getter함수
     */
    public String getMsgHeader() {
        return msgHeader;
    }

    /**
     * msgHeader Setter함수
     */
    public void setMsgHeader(String msgHeader) {
        this.msgHeader = msgHeader;
    }

    /**
     * msgCode Getter함수
     */
    public String getMsgCode() {
        return msgCode;
    }

    /**
     * msgCode Setter함수
     */
    private void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    /**
     * traceInfo Getter함수
     */
    public Vector<String> getTraceInfo() {
        return traceInfo;
    }

    /**
     * traceInfo Setter함수
     */
    public void setTraceInfo(Vector<String> traceInfo) {
        this.traceInfo = traceInfo;
    }

    /**
     * msgArgu Getter함수
     */
    public Object[] getMsgArgu() {
        return this.msgArgu;
    }

    /**
     * msgArgu Setter함수
     */
    private void setMsgArgu(Object[] msgArgu) {
        this.msgArgu = msgArgu;
    }

    /**
     * msgDesc Getter함수
     */
    public String getMsgDesc() {
        return msgDesc;
    }

    /**
     * msgStacktrace Setter함수
     */
    private void setMsgStacktrace(String msgStacktrace) {
        this.msgStacktrace = msgStacktrace;
    }
    
    /**
     * msgStacktrace Getter함수
     */
    public String getMsgStacktrace() {
        return msgStacktrace;
    }

}