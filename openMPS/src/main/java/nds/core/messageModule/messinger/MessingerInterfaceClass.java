package nds.core.messageModule.messinger;

import javax.annotation.Resource;

import nds.core.common.message.BaseMessingerVO;
import nds.core.common.message.MessageBean;
import nds.core.common.message.MessingerInterface;
import nds.core.common.message.service.BaseMessingerService;
import nds.core.common.message.service.MessageService;

import org.springframework.stereotype.Component;

@Component("messingerInterface")
public class MessingerInterfaceClass implements MessingerInterface {
	
	
    /** messingerImplService */
    @Resource(name = "interfaceMassingerService")
    private BaseMessingerService interfaceMassingerService;
    
    
	/** messageService */
    @Resource(name = "messageService")
    private MessageService messageService;
	
    /**
     * 메신저 발송
     */
	public String sendMsg(String msgNo) throws Exception  {

		// 쿼리부분 추가
		MessageBean messageBean = messageService.selectMessageInfo(msgNo);
		
		BaseMessingerVO vo = new BaseMessingerVO();
		vo.setMsgFrom(messageBean.getMsgFrom());
		vo.setMsgTo(messageBean.getMsgTo());
		vo.setMsgCntn(messageBean.getCntn());
		
		return interfaceMassingerService.sendMessinger(vo);
		
	}
}