package nds.core.common.message;



/**
 * SMS 발송 인터페이스
 * @author redrabbit
 *
 */
public interface SmsInterface {
	
	public String sendMsg(MessageBean vo) throws Exception;
}