package nds.frm.exception;

public class FrameException extends MainException{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2753962877037614987L;

	/**
     * Exception의 종류를 구분하기 위한 헤더 문자열.
     */
    //private String MESSAGE_HEADER = "[ FrameWork 예외 발생 ]";	
    
    private String message = null;
    //private Throwable cause = null;

    /**
     * BizBuilderException의 Default 생성자
     */
    public FrameException() {
        super();
    }

    /**
     * BizException의 생성자
     * @param String msgDesc
     */
    public FrameException(final String msgDesc) {
        super(msgDesc);
    }

    /**
     * BizException의 생성자
     * @param String msgCode
     * @param String msgDesc
     */
    public FrameException(final String msgCode,final String msgDesc) {
        super(msgCode);
        this.message = msgDesc;
    }

    /**
     * BizException을 최초로 발생시킬 때 사용되는 생성자.
     * <pre>
     * 	메시지내에 {}부분이 있는 경우에 사용한다.
     * </pre>
     * @param msgCode 메시지 코드
     * @param msgArgu 메시지의 {}를 대체할 파라미터
     * @since 2.0
     */
    public FrameException(final String msgCode, final Object[] msgArgu) {
        super(msgCode, msgArgu);
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
