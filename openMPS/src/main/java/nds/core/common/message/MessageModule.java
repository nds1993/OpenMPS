package nds.core.common.message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import nds.core.common.approvalhandling.service.ApprovalHandlingService;
import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.UserSession;
import nds.core.common.message.service.MessageConfService;
import nds.core.common.message.service.MessageService;
import nds.core.operation.approval.service.ApprovalVO;
import nds.core.operation.emailTmpl.service.EmailTmplService;
import nds.core.operation.msgtmpl.service.MsgTmplService;
import nds.core.operation.smstmpl.service.SmsTmplService;
import nds.core.operation.smstmpl.service.SmsTmplVO;
import nds.core.request.cooperation.service.CooperationService;
import nds.core.request.cooperation.service.CooperationVO;
import nds.core.userdep.user.service.UserService;
import nds.core.userdep.user.service.UserVO;
import nds.frm.config.StaticConfig;
import nds.frm.util.StringUtil;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import sun.misc.BASE64Encoder;





/**
 * <b>class : MessageModule </b><br>
 * <b>Class Description</b><br>
 * 메시지발송 통합모듈<br/>
 * - 지원 발송매체 : SMS/MMS , EMAIL_DB방식 , EMAIL_SMTP방식 , 메신저 <br/>
 * <b>History</b><br>
 * <pre>: 2015.04.20 </pre>
 * @author <a href="mailto:kblim@nds.co.kr">임기범</a><br>
 * @version 1.0<br><br>
<pre>
<b>모듈의 사용법은 아래와 같다.</b><br/>
======================================================================
 1. SMS/MMS
    (SMS는 첨부파일이 있거나 발송내용이 80byte 이상이면 자동으로 MMS로 발송된다.)
    발송내용 우선순위 : 템플릿 내용 > 직접 입력한 내용
======================================================================
MessageBean bean = messageModule.getMessageBean(MSGTYPE.SMS);   // 메시지 정보를 담을 MessageBean 생성.
bean.setTit("SMS111제목입니다.");                               // 발송할 sms제목을 지정.
bean.setCntn("SMS111내용입니다.");                              // 발송할 sms내용을 지정.
bean.setMsgFrom("0211112222");                                  // 발신자 번호를 지정 
bean.setMsgTo("01011112222");                                   // 수신번호 지정
bean.setCstNo("11111111");                                      // 고객번호
bean.setRegUser(cstNo);                                         // 등록자정보
bean.setUpdtUser(cstNo);                                        // 수정자정보

============ 아래의 셋팅은 첨부파일이 있는 MMS를 발송하는경우 추가셋팅  ==============        	
bean.setAtchFile1("D:\\attachFile\\test.jpg");                  // 첨부파일 FULL경로
bean.setAtchType1(MMSATCHTYPE.IMG);                             // 첨부파일 타입 - enum으로 지정하며 IMG(이미지) ,TXT(텍스트) , ADO , MVO ) 를 지원한다.
    
messageModule.sendMessage(bean);                                // 발송

======================================================================
 2. EMAIL
    발송내용 우선순위 : 템플릿 내용 > 직접 입력한 내용
======================================================================
MessageBean bean = messageModule.getMessageBean(MSGTYPE.EMAIL_SMTP);   // 메시지 정보를 담을 MessageBean 생성.( DB인터페이스 인경우 MSGTYPE.EMAIL_DB 로 지정 )
bean.setTit("EMAIL 제목입니다.");                               // 발송할 EMAIL 제목을 지정.
bean.setCntn("EMAIL 내용입니다.");                              // 발송할 EMAIL 내용을 지정.
bean.setMsgFrom("admin@nds.co.kr");                         // 발송 EMAIL 주소를 지정.
bean.setMsgFromNm("(주)레드소프트");                            // 발송자명을 지정
bean.setMsgTo("test@nds.co.kr");                            // 수신자 EMAIL 주소를 지정
bean.setMsgToNm("임기범");                                      // 수신자명을 지정
bean.setCstNo("11111111");                                      // 고객번호
bean.setRegUser(cstNo);                                         // 등록자정보
bean.setUpdtUser(cstNo);                                        // 수정자정보  

messageModule.sendMessage(bean);
</pre>
 */


@Component
public class MessageModule {

	public enum MSGTYPE { SMS, LMS , EMAIL_DB , EMAIL_SMTP , PUSH , CCM , MESSINGER };

	public enum MMSATCHTYPE { IMG , TXT , ADO , MVO  };
	
	// O : MMS 이면서 smsTitle 이 있는경우.
	// X : MMS 이면서 smsTitle 이 없는경우.
	// N : MMS 가 아닌경우. 
	public enum MMSCHK { O , X , N  };

    /** smstmplService */
    @Resource(name = "smstmplService")
    private SmsTmplService smstmplService;

    /** emailtmplService */
    @Resource(name = "emailTmplService")
    private EmailTmplService emailTmplService;
    
    /** emailtmplService */
    @Resource(name = "msgTmplService")
    private MsgTmplService msgTmplService;
	
	/** messageService */
    @Resource(name = "messageService")
    private MessageService messageService;

    /** smsInterface */
    @Resource(name = "smsInterface")
    private SmsInterface smsInterface;

    /** emailInterface */
    @Resource(name = "emailInterface")
    private EmailInterface emailInterface;
    
    /** emailInterface */
    @Resource(name = "messingerInterface")
    private MessingerInterface messingerInterface;
    
    /** messageConfService */
    @Resource(name = "messageConfService")
    private MessageConfService messageConfService;
    
    /** CooperationService */
    @Resource(name = "cooperationService")
    private CooperationService cooperationService;
    
	/** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService;
    
    /** CommonService */
    @Resource(name = "userService")
    private UserService userService;
    
    /** ApprovalHandlingService */
    @Resource(name = "approvalHandlingService")
    private ApprovalHandlingService approvalHandlingService;
    
    
    /**<pre>
     * SMS/EMAIL/메신저 메시지 를 발송한다.
     * 인자로 reqId, reqType, tretDeptCd, tretEmpno를 받아 해당 협조요청의 정보를 조회 후 처리상태에 따라 sms/email/메신저
     * 템플릿을 조회하여 자동 발송한다.
     * </pre>
     * @param reqId, reqType, tretDeptCd, tretEmpno 
     * @param request
     * @throws Exception
     */
    public void sendAutoMessageHelp(String reqId, String reqType, String tretDeptCd, String tretEmpno, HttpServletRequest request ) throws Exception  {
    	
    	HashMap map = new HashMap();
    	
    	UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
    	String empNo = userSession.getLogin().getUserEmpno();
    	String empNm = userSession.getLogin().getUserNm();

    	CooperationVO cooperationVO = new CooperationVO();
    	cooperationVO.setReqId(reqId);
    	cooperationVO.setReqType(reqType);
    	cooperationVO.setTretDeptCd(tretDeptCd);
    	cooperationVO.setTretEmpno(tretEmpno);
    	
    	CooperationVO helpResult = new CooperationVO();
    	
    	if("01".equals(reqType)) { // VOC
    		// 해당 제안상세를 조회한다.
    		helpResult = cooperationService.selectReqVoc(cooperationVO);
    	} else if("02".equals(reqType)) {
    		// 해당 제안상세를 조회한다.
        	helpResult = cooperationService.selectReq(cooperationVO);
    	}
    	
    	MessageConfBean msgConfBean = new MessageConfBean();
    	msgConfBean.setProcStat(helpResult.getTretCmpltYn());
    	msgConfBean.setTypeDvn("H");
    	msgConfBean.setCdId("F157");
    	
    	// 자동알림설정 목록을 조회 
    	List<MessageConfBean> msgConfList = messageConfService.selectMessageConfList(msgConfBean);

    	// 발송대상자..
    	List<UserVO> targetList = null;
    	
    	for(int x=0;x<msgConfList.size();x++) {
    		
    		MessageConfBean result = (MessageConfBean)msgConfList.get(x);
    		
    		/************************************************
			 * SMS 발송
			 ***********************************************/
    		if ("Y".equals(StringUtil.null2void(result.getSmsUseYn()) ) ) 
    		{
    			MessageBean messageBean = new MessageBean(MSGTYPE.SMS);
				
				messageBean.setMsgFrom( StaticConfig.SMS_FROM );           // 발신자
				messageBean.setMsgFromNm( StaticConfig.SMS_FROM_NM );      // 발신자명
				messageBean.setRegUser(empNo);                             // 등록자
				messageBean.setUpdtUser(empNo);                            // 수정자
				messageBean.setWorkDvn("N");                               // 작업구분
				messageBean.setVocId(reqId+"|"+reqType+"|"+tretDeptCd+"|"+tretEmpno);  // 키값
				messageBean.setTgtUser(result.getTgtUser());               // 대상자   -  F157
				messageBean.setProcStat(helpResult.getTretCmpltYn());           // 처리상태
				
    			// 템플릿 정보가 없다면 조회.
    			if ( null == map.get("S"+result.getProcStat()+result.getInoutDvn()) ) {
    				
    				SmsTmplVO smsVO = new SmsTmplVO();
        			smsVO.setProcStat(result.getProcStat());
        			smsVO.setInoutDvn(result.getInoutDvn());
        			// 협조요청인 경우
        			smsVO.setTypeDvn("H");
        			
        			map.put("S"+result.getProcStat()+result.getInoutDvn(), smstmplService.selectBySmsTmplInfo(smsVO));
    			}
	    			
    			// 템플릿이 있다면 발송 시작..
    			if (null != map.get("S"+result.getProcStat()+result.getInoutDvn()) )
    			{
    				SmsTmplVO smsTmplInfo = (SmsTmplVO)map.get("S"+result.getProcStat()+result.getInoutDvn());
    				messageBean.setTit(smsTmplInfo.getTmplNm());               // SMS 제목
    				
    				// 템플릿 내용업음.
					if ("".equals(StringUtil.null2void(smsTmplInfo.getCntn())) ) {
						messageBean.setStat("03");
	    				messageService.insertMessage(messageBean);
	    				
	    				continue;
					}
    				
					messageBean.setCntn(smsTmplInfo.getCntn());                // 발송내용

    				if ("I".equals(result.getInoutDvn())) {
    					/************************************************
    					 * 내부발송인경우 대상자 체크 후 propMst에서 대상자 조회하여 발송한다.
    					 ***********************************************/
    					// 01	요청자 , 02	담당자 
    					// 요청자
    					if ( "01".equals(result.getTgtUser() ) ) {
    						// 대상목록 ArrayList를 초기화 함.
		    				targetList = new ArrayList<UserVO>();
    						
    						// 처리자의 정보를 조회한다.
    						UserVO userVO = new UserVO();
    						userVO.setUserId(helpResult.getReqEmpno());
    						UserVO resultUserVO = commonService.getUserInfo(userVO , false);
    						
    						if (null!= resultUserVO){
    							targetList.add(resultUserVO);
    						}
    					}
    					// 담당자
    					else if ( "02".equals(result.getTgtUser() ) ) {
    						// 대상목록 ArrayList를 초기화 함.
		    				targetList = new ArrayList<UserVO>();
    						
    						// 처리자의 정보를 조회한다.
    						UserVO userVO = new UserVO();
    						userVO.setUserId(helpResult.getTretEmpno());
    						UserVO resultUserVO = commonService.getUserInfo(userVO , false);
    						
    						if (null!= resultUserVO){
    							targetList.add(resultUserVO);
    						}
    					}
    					
    					// 메시지 발송
	    				for(int qq = 0; qq< targetList.size(); qq++ ) {
	    					
	    					messageBean.setMsgTo(targetList.get(qq).getPhoneNum());
	    					sendSms(messageBean);
	    				}
    				}
    			}
    			else{  // 템플릿 없음.
    				messageBean.setStat("02");
    				messageService.insertMessage(messageBean);
    			}
    		}
    		
    		
    		/************************************************
			 * 이메일 발송
			 ***********************************************/
    		if ("Y".equals(StringUtil.null2void(result.getEmailUseYn()) ) ) {
    			
				MessageBean messageBean = new MessageBean(MSGTYPE.EMAIL_DB);
				
				messageBean.setMsgFrom( StaticConfig.EMAIL_FROM );         // 발신자
				messageBean.setMsgFromNm( StaticConfig.EMAIL_FROM_NM );    // 발신자명
				messageBean.setRegUser(empNo);                             // 등록자
				messageBean.setUpdtUser(empNo);                            // 수정자
				messageBean.setWorkDvn("N");                               // 작업구분 ( N: nomal , B: batch )
				messageBean.setVocId(reqId+"|"+reqType+"|"+tretDeptCd+"|"+tretEmpno);  // 키값
				messageBean.setTgtUser(result.getTgtUser());               // 대상자   -  F157
				messageBean.setProcStat(helpResult.getTretCmpltYn());           // 처리상태
				
				// 템플릿 정보가 없다면 조회.
    			if ( null == map.get("E"+result.getProcStat()+result.getInoutDvn()) ) {
    				
    				SmsTmplVO smsVO = new SmsTmplVO();
        			smsVO.setProcStat(result.getProcStat());
        			smsVO.setInoutDvn(result.getInoutDvn());
        			// 협조요청인 경우
        			smsVO.setTypeDvn("H");
        			
        			map.put("E"+result.getProcStat()+result.getInoutDvn(), smstmplService.selectBySmsTmplInfo(smsVO));
    			}
    			
    			if (null != map.get("E"+result.getProcStat()+result.getInoutDvn()) )
    			{
    				SmsTmplVO emailTmplInfo = (SmsTmplVO)map.get("E"+result.getProcStat()+result.getInoutDvn());
    				
    				messageBean.setTit(emailTmplInfo.getTmplNm());   // EMAIL 제목
    				messageBean.setCntn(emailTmplInfo.getCntn());    // 이메일 내용
    				
    				// 템플릿 내용업음.
    				if ("".equals(StringUtil.null2void(emailTmplInfo.getCntn())) ) {
    					
    					messageBean.setStat("03");
    					messageService.insertMessage(messageBean);
    						
    					continue;
    				}
    				
    				if ("I".equals(result. getInoutDvn())) {

    					/************************************************
    					 * 내부발송인경우 대상자 체크 후 needMst에서 대상자 조회하여 발송한다.
    					 ***********************************************/
    					// 01	요청자 , 02	담당자 
    					// 요청자
    					if ( "01".equals(result.getTgtUser() ) ) {
    						// 대상목록 ArrayList를 초기화 함.
		    				targetList = new ArrayList<UserVO>();
    						
    						// 처리자의 정보를 조회한다.
    						UserVO userVO = new UserVO();
    						userVO.setUserId(helpResult.getReqEmpno());
    						UserVO resultUserVO = commonService.getUserInfo(userVO , false);
    						
    						if (null!= resultUserVO){
    							targetList.add(resultUserVO);
    						}
    					}
    					// 담당자
    					else if ( "02".equals(result.getTgtUser() ) ) {
    						// 대상목록 ArrayList를 초기화 함.
		    				targetList = new ArrayList<UserVO>();
    						
    						// 처리자의 정보를 조회한다.
    						UserVO userVO = new UserVO();
    						userVO.setUserId(helpResult.getTretEmpno());
    						UserVO resultUserVO = commonService.getUserInfo(userVO , false);
    						
    						if (null!= resultUserVO){
    							targetList.add(resultUserVO);
    						}
    					}
    				
    					// 메시지 발송
	    				for(int qq = 0; qq< targetList.size(); qq++ ) {
							messageBean.setMsgTo( targetList.get(qq).getEmail() );
    						messageBean.setMsgToNm( targetList.get(qq).getUserNm() );	
    						
    						messageBean.setMailTgtUser(targetList.get(qq).getUserEmpno());
    						
    						if("01".equals(reqType)) { // VOC
    				    		messageBean.setMailTit(helpResult.getQstnTit());
        						messageBean.setMailCntn(StringUtil.enterToBr(helpResult.getQstnCntn()));
        						messageBean.setMailCstNm(helpResult.getReqEmpnm());
        						messageBean.setMailRegDttm(StringUtil.getDateTimeForm(helpResult.getReqDttm()));
    				    	} else if("02".equals(reqType)) {
    				        	messageBean.setMailTit(helpResult.getPropTit());
        						messageBean.setMailCntn(StringUtil.textToHtml(helpResult.getPropCntn(), true));
        						messageBean.setMailCstNm(helpResult.getReqEmpnm());
        						messageBean.setMailRegDttm(StringUtil.getDateTimeForm(helpResult.getReqDttm()));
    				        	
    				    	}
    						messageBean.setMailRegNm("요청자");
    						
    						sendEmailSmtp(messageBean);
	    				}
    				}
    				
    			}else{  // 템플릿 없음.
    				messageBean.setStat("02");
    				messageService.insertMessage(messageBean);
    			}
    		}
    	}
    	
    	if (null!= targetList ) {
	    	targetList.clear();
	    	targetList = null;
    	}
    	
    	if (null!= map ) {
	    	map.clear();
	    	map = null;
    	}
    }
    
    /**
     * EMAIL을 발송한다.
     * @param bean
     * @throws Exception
     */
    private void sendEmail(MessageBean bean) throws Exception {
    	
		String cntn = StringUtil.null2void(bean.getCntn());
		bean.setWorkDvn("N");                               // 작업구분 ( N: nomal , B: batch )
		
		// 발송내용업음.
		if ("".equals(cntn) ) {
			bean.setStat("05");
		}
		// 발송제목.
		else if ("".equals(StringUtil.null2void(bean.getCntn())) ) {
			bean.setStat("04");
		}
		// 수신자 email주소 번호 없음
		else if ("".equals(StringUtil.null2void(bean.getMsgTo())) ) {
			bean.setStat("06");
		}
		// 수신자명 없음
		else if ("".equals(StringUtil.null2void(bean.getMsgToNm())) ) {
			bean.setStat("13");
		}
		else{
			// email발송 후 성공/실패에 따른 상태값을 갱신한다.
			// 1 : 발송성공 , 16 : 발송실패
			try
			{
				LogManager.getRootLogger().debug("[sendEmail]" + bean.getCntn());
				bean.setStat("16");
				
				// 메시지 발송.
//				String sendNo = null;
				
//				if ( MSGTYPE.EMAIL_DB.toString().equals(bean.getMsgType()) ) {  // DB 이메일 발송
//					sendNo = emailInterface.dbSendEmail(msgNo);
//				}
//				else{
//					sendNo = emailInterface.smtpSendEmail(msgNo);
//				}
				/*if(!"03".equals(bean.getChnlId())) {
					java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd"); 
			        String today = formatter.format(new java.util.Date());
					
			        LogManager.getRootLogger().debug("################## " + today);
			        
					URLConnection conn = null;
					String urlAddr = StaticConfig.EMAIL_URL;
					
					String menu_id = "";
					
					if("01".equals(bean.getChnlId())) {
						menu_id = "1182";
					} else if("02".equals(bean.getChnlId())) {
						menu_id = "1190";
					} else if("03".equals(bean.getChnlId())) {
						menu_id = "1192";
					} else if("04".equals(bean.getChnlId())) {
						menu_id = "1182";
					} else if("05".equals(bean.getChnlId())) {
						menu_id = "1190";
					}
					
					try{
						String data = URLEncoder.encode("QNA_NAME", "EUC-KR") + "=" + URLEncoder.encode(StringUtil.replacAsterisk(bean.getMsgToNm()), "EUC-KR");
				        data += "&" + URLEncoder.encode("QNA_EMAIL", "EUC-KR") + "=" + URLEncoder.encode(bean.getMsgTo(), "EUC-KR");
				        data += "&" + URLEncoder.encode("QNA_SUBJECT", "EUC-KR") + "=" + URLEncoder.encode("schRegChnl=" + bean.getChnlId() + "|vocId=" + bean.getVocId() + "|menu_id=" + menu_id, "EUC-KR");
				        data += "&" + URLEncoder.encode("QNA_CONTENT", "EUC-KR") + "=" + URLEncoder.encode(bean.getCntn(), "EUC-KR");
				        data += "&" + URLEncoder.encode("QNA_DATE", "EUC-KR") + "=" + URLEncoder.encode(today, "EUC-KR");
				        
				        data += "&" + URLEncoder.encode("ANS_CONTENT", "EUC-KR") + "=" + URLEncoder.encode(bean.getTit(), "EUC-KR"); // 메일제목
				        data += "&" + URLEncoder.encode("ANS_ID", "EUC-KR") + "=" + URLEncoder.encode(bean.getMailDvn(), "EUC-KR"); // 구분값
				        
				        
				        LogManager.getRootLogger().debug("################## " + data);
				        
				        //********************************************
				        //	데이타를 보낸다.
				        //********************************************
				        URL url = new URL(urlAddr);
				        conn = url.openConnection();
				        conn.setDoOutput(true);
				        conn.setConnectTimeout(2000); //2초
				        conn.setReadTimeout(2000);			        
				        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
				        wr.write(data);
				        wr.flush();
						//요청 URL
				        
				        //*******************************************
				        //	데이타를 받을경우 처리
				        //*******************************************
				        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				        String line;
				        StringBuffer returnData = new StringBuffer();
				        while ((line = rd.readLine()) != null) {
				            returnData.append(line);	      
				        }
				        
				        if(returnData.toString().indexOf("result=1") > -1){
				        	bean.setStat("01");
				        }
				        LogManager.getRootLogger().debug("[Email Return]" + returnData.toString());
				        wr.close();
				        rd.close();
				        
					}catch(Exception e){
						e.printStackTrace();
						bean.setStat("16");
					}
				} else {
					LogManager.getRootLogger().debug("[sendEmail] 전자민원 발송안함");
				}*/
				
				
				
//				HttpURLConnection connection = null;
//				InputStream is;
//				InputStreamReader isr;
//				BufferedReader br;
//				StringBuffer sb = new StringBuffer();
//				
//				try{
//					
//					String data = StaticConfig.EMAIL_URL+"?"+URLEncoder.encode("QNA_NAME", "EUC-KR") + "=" + URLEncoder.encode(StringUtil.replacAsterisk(bean.getMsgToNm()), "EUC-KR");
//			        data += "&" + URLEncoder.encode("QNA_EMAIL", "EUC-KR") + "=" + URLEncoder.encode("rhyun@nds.co.kr", "EUC-KR");
//			        data += "&" + URLEncoder.encode("QNA_SUBJECT", "EUC-KR") + "=" + URLEncoder.encode(bean.getTit(), "EUC-KR");
//			        data += "&" + URLEncoder.encode("QNA_CONTENT", "EUC-KR") + "=" + URLEncoder.encode(bean.getCntn(), "EUC-KR");
//			        //data += "&" + URLEncoder.encode("QNA_CONTENT_2", "EUC-KR") + "=" + URLEncoder.encode(qnaContent2, "EUC-KR");
//			        //data += "&" + URLEncoder.encode("QNA_DATE", "EUC-KR") + "=" + URLEncoder.encode(bean.getRegDttm(), "EUC-KR");
//			        //data += "&" + URLEncoder.encode("ANS_CONTENT", "EUC-KR") + "=" + URLEncoder.encode(ansContent, "EUC-KR");
//			        //data += "&" + URLEncoder.encode("ANS_CONTENT_2", "EUC-KR") + "=" + URLEncoder.encode(ansContent2, "EUC-KR");
//			        //data += "&" + URLEncoder.encode("ANS_ID", "EUC-KR") + "=" + URLEncoder.encode(bean.getRegUser(), "EUC-KR"); // 세션ID
//					
//					URL url = new URL(data); //요청 URL
//					//문자열로 URL표현     
//					LogManager.getRootLogger().debug("URL : "+url.toExternalForm());
//					connection = (HttpURLConnection)url.openConnection();
//					//요청방식
//					connection.setRequestMethod("POST");
//					connection.setRequestProperty("Content-Type", "application/json");
//					connection.setDoOutput(true);
//					connection.setConnectTimeout(2000); //2초
//					connection.setReadTimeout(2000);
//					
//					is = connection.getInputStream();
//					isr = new InputStreamReader(is);
//					br = new BufferedReader(isr);
//					
//					String line = null;
//					
//					while((line = br.readLine()) !=null){
//						LogManager.getRootLogger().debug("###############   line while ###################");
//						LogManager.getRootLogger().debug(line);
//						sb.append(line).append("\n");
//					}
//					br.close();
//				}catch(ConnectException e){
//					e.printStackTrace();
//				}catch(Exception e){
//					e.printStackTrace();
//				}finally{
//					if(connection != null){
//						connection.disconnect();
//					}
//				}
				
//				URLConnection conn = null;
//				InputStream is;
//				InputStreamReader isr;
//				BufferedReader br;
//				String urlAddr = StaticConfig.EMAIL_URL;
//				
//				try{
//					String data = URLEncoder.encode("QNA_NAME", "EUC-KR") + "=" + URLEncoder.encode(StringUtil.replacAsterisk(bean.getMsgToNm()), "EUC-KR");
//			        data += "&" + URLEncoder.encode("QNA_EMAIL", "EUC-KR") + "=" + URLEncoder.encode("rhyun@nds.co.kr", "EUC-KR");
//			        data += "&" + URLEncoder.encode("QNA_SUBJECT", "EUC-KR") + "=" + URLEncoder.encode(bean.getTit(), "EUC-KR");
//			        data += "&" + URLEncoder.encode("QNA_CONTENT", "EUC-KR") + "=" + URLEncoder.encode(bean.getCntn(), "EUC-KR");
//			        //data += "&" + URLEncoder.encode("QNA_CONTENT_2", "EUC-KR") + "=" + URLEncoder.encode(qnaContent2, "EUC-KR");
//			        //data += "&" + URLEncoder.encode("QNA_DATE", "EUC-KR") + "=" + URLEncoder.encode(bean.getRegDttm(), "EUC-KR");
//			        //data += "&" + URLEncoder.encode("ANS_CONTENT", "EUC-KR") + "=" + URLEncoder.encode(ansContent, "EUC-KR");
//			        //data += "&" + URLEncoder.encode("ANS_CONTENT_2", "EUC-KR") + "=" + URLEncoder.encode(ansContent2, "EUC-KR");
//			        //data += "&" + URLEncoder.encode("ANS_ID", "EUC-KR") + "=" + URLEncoder.encode(bean.getRegUser(), "EUC-KR"); // 세션ID
//			        
//			        //********************************************
//			        //	데이타를 보낸다.
//			        //********************************************
//			        URL url = new URL(urlAddr);
//			        conn = url.openConnection();
//			        conn.setDoOutput(true);
//			        conn.setConnectTimeout(2000); //2초
//			        conn.setReadTimeout(2000);			        
////			        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
////			        wr.write(data);
////			        wr.flush();
//					//요청 URL
//			        
//					
//					is = conn.getInputStream();
//					isr = new InputStreamReader(is);
//					br = new BufferedReader(isr);
//					
//					String line = null;
//					
//					while((line = br.readLine()) !=null){
//						LogManager.getRootLogger().debug("###############   line while ###################");
//						LogManager.getRootLogger().debug("["+ line + "]");
//						sb.append(line).append("\n");
//					}
//					br.close();
//			        
//			        
//			        bean.setStat("01");
//				}catch(Exception e){
//					e.printStackTrace();
//					bean.setStat("16");
//				}
			}
			catch(Exception e) 
			{
				e.printStackTrace();
				bean.setStat("16");
			}			
		}
		messageService.insertMessage(bean);
    }
    

    /**
     * 내부직원에게 SMTP로 EMAIL을 발송한다.
     * @param bean
     * @throws Exception
     */
    private void sendEmailSmtp(MessageBean bean) throws Exception {
    	
		String cntn = StringUtil.null2void(bean.getCntn());
		bean.setWorkDvn("N");                               // 작업구분 ( N: nomal , B: batch )
		
		// 발송내용업음.
		if ("".equals(cntn) ) {
			bean.setStat("05");
		}
		// 발송제목.
		else if ("".equals(StringUtil.null2void(bean.getCntn())) ) {
			bean.setStat("04");
		}
		// 수신자 email주소 번호 없음
		else if ("".equals(StringUtil.null2void(bean.getMsgTo())) ) {
			bean.setStat("06");
		}
		// 수신자명 없음
		else if ("".equals(StringUtil.null2void(bean.getMsgToNm())) ) {
			bean.setStat("13");
		}
		else{
			// email발송 후 성공/실패에 따른 상태값을 갱신한다.
			// 1 : 발송성공 , 16 : 발송실패
			try
			{
				BASE64Encoder encoder = new BASE64Encoder();
				String tempByte1 = encoder.encode(bean.getMailTgtUser().getBytes());
				String uid = encoder.encode(tempByte1.getBytes());
				
				LogManager.getRootLogger().debug("[sendEmail]" + bean.getCntn());
				
				try{
//					java.util.Properties props = System.getProperties();
//					
//					props.put("mail.smtp.starttls", "false");
//					props.put("mail.smtp.host", "172.28.202.109"); 
//					props.put("mail.smtp.auth", "false");
//					props.put("mail.smtp.port", "25"); 
//					
//					Session session = Session.getInstance(props);
//					
//					Message msg = new MimeMessage(session);
//
//					msg.setContent(msg, "text/html; charset=EUC-KR");
//					InternetAddress ia = new InternetAddress(bean.getMsgFrom());
//
//					String fromnm = bean.getMsgFromNm();
//					fromnm = new String(fromnm.getBytes("UTF-8") , "UTF-8"  );
//					fromnm =  MimeUtility.encodeText(fromnm, "UTF-8", "Q");
//					ia.setPersonal(fromnm);
//					
//					
//					msg.setFrom(ia);
////					msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse("rhyun@nds.co.kr", false));
////					msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse("smiletac@kbsec.co.kr", false));
//					msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(bean.getMsgTo(), false));
//					
//					msg.setSubject(MimeUtility.encodeText(bean.getTit(), "UTF-8", "Q"));
//					
//					StringBuffer sb = new StringBuffer();
//					
////					<blockquote style='background: url("http://static.se2.naver.com/static/img/bg_b1.png"); margin: 0px 0px 30px; padding: 10px; border: 2px solid rgb(229, 229, 229); color: rgb(136, 136, 136); _background: none; _zoom: 1;' class="se2_quote10">
//					
//					sb.append("<table width='666' cellpadding='0' cellspacing='0' border='0' style='border-width: 2px 0px 0px 0px; border-style: solid solid none none; border-color: rgb(204, 204, 204) rgb(204, 204, 204) currentColor currentColor;'>");
//					sb.append("<tbody>");
//					sb.append("<tr>");
//					sb.append("<td align='center' style='font-family: 맑은 고딕; border-width: 0px 0px 1px 0px; border-style: none none solid solid; border-color: currentColor currentColor rgb(204, 204, 204) rgb(204, 204, 204); width: 100px; height: 30px; background-color: rgb(235, 235, 235);'>등록일자</td>");
//					sb.append("<td style='font-family: 맑은 고딕; border-width: 0px 0px 1px 1px; border-style: none none solid solid; border-color: currentColor currentColor rgb(204, 204, 204) rgb(204, 204, 204); width: 200px; height: 30px; background-color: rgb(255, 255, 255);'>"+bean.getMailRegDttm()+"</td>");
//					sb.append("<td align='center' style='font-family: 맑은 고딕; border-width: 0px 0px 1px 0px; border-style: none none solid solid; border-color: currentColor currentColor rgb(204, 204, 204) rgb(204, 204, 204); width: 100px; height: 30px; background-color: rgb(235, 235, 235);'>"+bean.getMailRegNm()+"</td>");
//					sb.append("<td style='font-family: 맑은 고딕; border-width: 0px 0px 1px 1px; border-style: none none solid solid; border-color: currentColor currentColor rgb(204, 204, 204) rgb(204, 204, 204); width: 200px; height: 30px; background-color: rgb(255, 255, 255);'>"+bean.getMailCstNm()+"</td>");
//					sb.append("</tr>");
//					sb.append("<tr>");
//					sb.append("<td align='center' style='font-family: 맑은 고딕; border-width: 0px 0px 1px 0px; border-style: none none solid solid; border-color: currentColor currentColor rgb(204, 204, 204) rgb(204, 204, 204); width: 100px; height: 30px; background-color: rgb(235, 235, 235);'>제목</td>");
//					sb.append("<td style='font-family: 맑은 고딕; border-width: 0px 0px 1px 1px; border-style: none none solid solid; border-color: currentColor currentColor rgb(204, 204, 204) rgb(204, 204, 204); width: 100px; height: 30px; background-color: rgb(255, 255, 255);' colspan='3'>"+bean.getMailTit()+"</td>");
//					sb.append("</tr>");
//					sb.append("<tr>");
//					sb.append("<td style='font-family: 맑은 고딕; padding: 5px; vertical-align:top; border-width: 0px 0px 2px 0px; border-style: none none solid solid; border-color: currentColor currentColor rgb(204, 204, 204) rgb(204, 204, 204); width: 100px; height: 30px; background-color: rgb(255, 255, 255);' colspan='4'>"+bean.getMailCntn()+"</td>");
//					sb.append("</tr>");
//					sb.append("</tbody>");
//					sb.append("</table>");
//					
//					
//					sb.append("<br><div style='color: blue; font-family: 맑은 고딕; font-size: 12px;'><b>※ 통합VOC시스템에 접속 후 좌측상단의 '나의 업무현황'을 이용하여 처리하시기 바랍니다.</b></div><br>");
//
//					LogManager.getRootLogger().debug("ssologin=======================" + uid);
//					msg.setContent("<div style='width: 666px; height: 30px;'><img src='http://voc.kbsec.co.kr/html/images/mail/mail_header.png' alt='농심' /></div><br><span style='font-family: 맑은 고딕;'>" + StringUtil.enterToBr(bean.getCntn()) + "</span><br><br>" + sb.toString() + "<b><a style='font-family: 맑은 고딕; color: blue; font-size: 12px;' href='http://voc.kbsec.co.kr'><u>통합 VOC시스템 바로가기</u></a></b><br><br><hr color='#4A452A' size='15' width='666' align='left'/>" , "text/html;charset=KSC5601");
//					msg.setHeader("X-Mailer", "sendhtml");
//					msg.setSentDate(new Date());
//
//					Transport.send(msg);

			        bean.setStat("01");
				}catch(Exception e){
					e.printStackTrace();
					bean.setStat("16");
				}
				
			}
			catch(Exception e) 
			{
				e.printStackTrace();
				bean.setStat("16");
			}			
		}
		messageService.insertMessage(bean);
    }
    
    
    /**
     * SMS/MMS를 발송한다.
     * - 발송내용 byte 수가 80byte이상 또는 첨부파일정보가 있는경우 mms로 발송함.
     * - 발송내용의 우선순위는 템플릿의 내용 > 직접입력 임.
     * @param bean
     * @throws Exception
     */
    private void sendSms(MessageBean bean) throws Exception  {
    	
		// 템플릿내용 또는 직접입력한 발송내용에 대하여 개인화 작업을 한다.
		String cntn = StringUtil.null2void(bean.getCntn());
		bean.setWorkDvn("N");                               // 작업구분 ( N: nomal , B: batch )

		// 발송내용업음.
		if ("".equals(cntn) ) {
			bean.setStat("05");
		}
		// 수신자 SMS 번호 없음
		else if ("".equals(StringUtil.null2void(bean.getMsgTo())) ) {
			bean.setStat("07");
		}
		// 발신자 SMS번호 없음
		else if ("".equals(StringUtil.null2void(bean.getMsgFrom())) ) {
			bean.setStat("10");
		}
		else{
			try
			{
				LogManager.getRootLogger().debug("[sendSms]" + bean.getCntn());
				// 메시지 발송.
				// sms발송 후 성공/실패에 따른 상태값을 갱신한다.
	    		// 1 : 발송성공 , 16 : 발송실패
	    		try
	    		{
	    			if(!"03".equals(bean.getChnlId())) {
	    				bean.setMsgTo(bean.getMsgTo());
		    			String sendNo = smsInterface.sendMsg(bean);
		    			
		    			bean.setSendNo(sendNo);
		    			bean.setStat("01");
	    			} else {
	    				LogManager.getRootLogger().debug("[sendSms] 전자민원 발송안함");
	    			}
	    			
	    		}
	    		catch(Exception e) 
	    		{
	    			bean.setStat("16");
	    		}
			}
			catch(Exception e) 
			{
				// S(M)MS 검증에러
				bean.setStat("17");
			}
		}
		
		messageService.insertMessage(bean);
    }
    
    
    /**
     * push를 발송한다.
     * @param bean
     * @throws Exception
     */
    private void sendMessenger(MessageBean bean) throws Exception  {
    	
		// 템플릿내용 또는 직접입력한 발송내용에 대하여 개인화 작업을 한다.
		String cntn = StringUtil.null2void(bean.getCntn());
		bean.setWorkDvn("N");                               // 작업구분 ( N: nomal , B: batch )

		// 발송내용업음.
		if ("".equals(cntn) ) {
			bean.setStat("05");
		}
//		// 발송제목.
//		else if ("".equals(StringUtil.null2void(bean.getCntn())) ) {
//			bean.setStat("4");
//		}
		// 수신자 메신저번호 없음
//		else if ("".equals(StringUtil.null2void(bean.getMsgTo())) ) {
//			bean.setStat("08");
//		}
//		// 발신자 메신저번호 없음
		if ("".equals(StringUtil.null2void(bean.getCstNo())) ) {
			bean.setStat("11");
		}
//		// 발신자명 없음
//		else if ("".equals(StringUtil.null2void(bean.getMsgFromNm())) ) {
//			bean.setStat("12");
//		}			
//		// 수신자명 없음
//		else if ("".equals(StringUtil.null2void(bean.getMsgToNm())) ) {
//			bean.setStat("13");
//		}
		
		else{
			// sms발송 후 성공/실패에 따른 상태값을 갱신한다.
			// 1 : 발송성공 , 16 : 발송실패
			try
			{
				LogManager.getRootLogger().debug("[sendMessenger]" + bean.getCntn());
				// 메시지 발송.
//				String sendNo = messingerInterface.sendMsg(msgNo);
				/*if(!"03".equals(bean.getChnlId())) {
					
					URLConnection conn = null;
					String urlAddr = StaticConfig.PUSH_URL;
					
					try{
						String data = URLEncoder.encode("user_id", "EUC-KR") + "=" + URLEncoder.encode(bean.getCstNo(), "EUC-KR");
				        data += "&" + URLEncoder.encode("msg", "EUC-KR") + "=" + URLEncoder.encode(bean.getCntn(), "EUC-KR");
				        data += "&" + URLEncoder.encode("board_no", "EUC-KR") + "=" + URLEncoder.encode("99", "EUC-KR");
	
				        LogManager.getRootLogger().debug("################## " + data);
				        //********************************************
				        //	데이타를 보낸다.
				        //********************************************
				        URL url = new URL(urlAddr);
				        conn = url.openConnection();
				        conn.setRequestProperty("Cookie", "HOST_SYSTEM=VOC");
				        conn.setDoOutput(true);
				        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
				        wr.write(data);
				        wr.flush();
						//요청 URL
				        
				        //*******************************************
				        //	데이타를 받을경우 처리
				        //*******************************************
				        
				        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				        String line;
				        StringBuffer returnData = new StringBuffer();
				        while ((line = rd.readLine()) != null) {
				            returnData.append(line);	      
				        }
				        
				        if(returnData.toString().indexOf("result=1") > -1){
				        	
				        }
				        LogManager.getRootLogger().debug("[PUSH Return]" + returnData.toString());
				        wr.close();
				        rd.close();
				        
				        bean.setStat("01");
					}catch(ConnectException e){
						e.printStackTrace();
						bean.setStat("16");
					}catch(Exception e){
						e.printStackTrace();
						bean.setStat("16");
					}
				} else {
    				LogManager.getRootLogger().debug("[sendMessenger] 전자민원 발송안함");
    			}*/
			}
			catch(Exception e) 
			{
				e.printStackTrace();
				bean.setStat("16");
			}			
		}
		messageService.insertMessage(bean);
    }
}
