package nds.frm.exception;

/**
 * <b>class : </b> BizException
 * <b>Class Description</b><br>
 * Business예외를 처리하는 Exception Class
 * <b>History</b><br>
 * <pre>      : 2009.06.11 초기작성(임진식)</pre>
 * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
 * @version 1.0
 */
public class BizException extends MainException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message = null;
    //private Throwable cause = null;

    /**
     * BizBuilderException의 Default 생성자
     */
    public BizException() {
        super();
    }

    /**
     * BizException의 생성자
     * @param String msgDesc
     */
    public BizException(final String msgDesc) {
        super(msgDesc);
    }

    /**
     * BizException의 생성자
     * @param String msgCode
     * @param String msgDesc
     */
    public BizException(final String msgCode,final String msgDesc) {
        super(msgCode);
        this.message = msgDesc;
    }

    /**
     * BizException을 최초로 발생시킬 때 사용되는 생성자.
     * <pre>
     * 	메시지내에 {}부분이 있는 경우에 사용한다.
     * </pre>
     * @param msgCode 메세지 코드
     * @param msgArgu 메세지의 {}를 대체할 파라미터
     * @since 2.0
     */
    public BizException(final String msgCode, final Object[] msgArgu) {
        super(msgCode, msgArgu);
    }

    /**
     * Exception을 최초로 발생시킬 때 사용되는 생성자.
     * <pre>
     * 	메시지내에 {}부분이 있는 경우에 사용한다.
     * </pre>
     * @param msgCode 메세지 코드
     * @param msgArgu 메세지의 {}를 대체할 파라미터
     * @since 2.0
     */
    public BizException(final String msgCode,final Throwable preException) {
        super(msgCode, preException);
    }

    /**
     * Exception을 최초로 발생시킬 때 사용되는 생성자.
     * <pre>
     * 	메시지내에 {}부분이 있는 경우에 사용한다.
     * </pre>
     * @param msgCode 메세지 코드
     * @param msgArgu 메세지의 {}를 대체할 파라미터
     * @param preException Throwable
     * @since 2.0
     */
    public BizException(final String msgCode, final Object[] msgArgu, final Throwable preException) {
        super(msgCode, msgArgu, preException);
    }

    /**
     * Exception을 최초로 발생시킬 때 사용되는 생성자.
     * <pre>
     * 	메시지내에 {}부분이 있는 경우에 사용한다.
     * </pre>
     * @param preException Throwable
     * @param traceInfo Trace정보
     * @since 2.0
     */
    public BizException(final Throwable preException, final String traceInfo) {
        super(preException, traceInfo);
    }

    /**
     * Message를 반납하는 Getter
     *
     * @return String
     */
    public String getMessage() {
        return this.message;
    }
}
