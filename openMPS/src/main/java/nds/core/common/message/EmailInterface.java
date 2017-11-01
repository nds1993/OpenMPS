package nds.core.common.message;

/**
 * EMAIL 발송 인터페이스
 * @author redrabbit
 *
 */
public interface EmailInterface {

	public String dbSendEmail(String msgNo) throws Exception;
	
	public String smtpSendEmail(String msgNo) throws Exception;
}
