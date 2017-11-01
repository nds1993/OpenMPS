package nds.core.messageModule.email;

import java.util.Date;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import nds.core.common.message.EmailInterface;
import nds.core.common.message.MessageBean;
import nds.core.common.message.service.BaseEmailService;
import nds.core.common.message.service.MessageService;
import nds.core.messageModule.email.service.DBEmailVO;

import org.springframework.stereotype.Component;

@Component("emailInterface")
public class DBEmailInterfaceClass implements EmailInterface {
	
	
    /** emailtmplService */
    @Resource(name = "interfaceEmailService")
    private BaseEmailService interfaceEmailService;
    
	/** messageService */
    @Resource(name = "messageService")
    private MessageService messageService;
    
	
    /**
     * EMAIL 발송
     */
	public String dbSendEmail(String msgNo) throws Exception  {

		// 쿼리부분 추가
		MessageBean messageBean = messageService.selectMessageInfo(msgNo);
		
		DBEmailVO vo = new DBEmailVO();
		vo.setMsgTit(messageBean.getTit());
		vo.setMsgFrom(messageBean.getMsgFrom());
		vo.setMsgTo(messageBean.getMsgTo());
		vo.setMsgCntn(messageBean.getCntn());
		vo.setMsgFromNm(messageBean.getMsgFromNm());
		vo.setMsgToNm(messageBean.getMsgToNm());
		
		return interfaceEmailService.sendEmail(vo);
		
	}

	/**
	 * SMTP로 이메일 발송
	 */
	public String smtpSendEmail(String msgNo) throws Exception {

		
		// 쿼리부분 추가
		MessageBean messageBean = messageService.selectMessageInfo(msgNo);
		
		try
		{
			java.util.Properties props = System.getProperties();
//			props.put("mail.smtp.host", "210.93.149.28"); 
//			props.put("mail.smtp.auth", "true");
			
			props.put("mail.smtp.host", "192.168.123.150"); 
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "25"); 
			
			MyAuthenticator auth = new MyAuthenticator("kblim","dlarlqja");
			
			Session session = Session.getInstance(props, auth);
			
//			session.setDebug(true);
			
			Message msg = new MimeMessage(session);

			msg.setContent(msg, "text/html; charset=EUC-KR");
			InternetAddress ia = new InternetAddress(messageBean.getMsgFrom());

			String fromnm = messageBean.getMsgFromNm();
			fromnm = new String(fromnm.getBytes("UTF-8") , "UTF-8"  );
			fromnm =  MimeUtility.encodeText(fromnm, "UTF-8", "Q");
			ia.setPersonal(fromnm);
			
			
			msg.setFrom(ia);
			msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(messageBean.getMsgTo(), false));

//          참조/숨은참조는 맊음.
//			if(cc != null)
//				msg.setRecipients(javax.mail.Message.RecipientType.CC, InternetAddress.parse(cc, false));
//			if(bcc != null)
//				msg.setRecipients(javax.mail.Message.RecipientType.BCC, InternetAddress.parse(bcc, false));

			
			msg.setSubject(MimeUtility.encodeText(messageBean.getTit(), "UTF-8", "Q"));
			
			msg.setContent(messageBean.getCntn() , "text/html;charset=KSC5601");
			msg.setHeader("X-Mailer", "sendhtml");
			msg.setSentDate(new Date());

			Transport.send(msg);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;

	}
	
	public final class MyAuthenticator extends javax.mail.Authenticator {

	    private String id;
	    private String pw;

	    public MyAuthenticator(String id, String pw) {
	        this.id = id;
	        this.pw = pw;
	    }

	    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
	        return new javax.mail.PasswordAuthentication(id, pw);
	    }

	}
	
}