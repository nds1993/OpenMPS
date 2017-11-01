package nds.core.messageModule.sms;

import javax.annotation.Resource;

import nds.core.common.message.MessageBean;
import nds.core.common.message.SmsInterface;
import nds.core.common.message.service.BaseSmsService;

import org.springframework.stereotype.Component;

@Component("smsInterface")
public class SmsInterfaceClass implements SmsInterface {
	
    /** smstmplService */
    @Resource(name = "interfaceSmsService")
    private BaseSmsService interfaceSmsService;
	
    /**
     * SMS 발송
     */
	public String sendMsg(MessageBean vo) throws Exception  {
		
		return interfaceSmsService.sendSms(vo);
		
	}
}