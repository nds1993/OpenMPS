package nds.core.common.message;

/**
 * 메신저 발송 인터페이스
 * @author redrabbit
 *
 */
public interface MessingerInterface {
	
	public String sendMsg(String msgNo) throws Exception;
}