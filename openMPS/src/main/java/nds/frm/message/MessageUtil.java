package nds.frm.message;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Vector;

import nds.frm.exception.FrameException;
import nds.frm.exception.MainException;
import nds.frm.util.StringUtil;
import nds.frm.util.XmlUtil;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;




public class MessageUtil {
	public static final String MESSAGE = "message";					// Root Element
    public static final String MESSAGE_CODE = "message_code";       // node key
    public static final String MESSAGE_CONTENT = "message_content"; // node value

    public MessageUtil(){
        super();
    }

    /**
     * 특정 xml파일 이름을 받아서 해당 xml파일을 오픈하여 HashMap형태로 리턴
     *
     * @param location          XML파일의 이름
     * @param inputStream       XML파일의 위치 경로
     * @return HashMap          XML Node 정보를 담고 있는 HashMap
     * @throws java.lang.Exception
     */
    public static HashMap loadMessageDefinitions(String location, InputStream inputStream)
        throws Exception{
        Element root = XmlUtil.loadDocument(location, inputStream);
        return getMessageDefinitions(root);
    }

    /**
     * roor Element 정보를 입력받아 하위 Node 정보를 HashMap에 담아 리턴하는 메소드
     *
     * @param root Element  XML root Element
     * @return HashMap      XML Node 정보를 담고 있는 HashMap
     */
    private static HashMap getMessageDefinitions(Element root){
        HashMap<String, String> messages = new HashMap<String, String>();
        messages.put(MESSAGE, XmlUtil.getTagValue(root, MESSAGE));
        NodeList list = root.getElementsByTagName(MESSAGE);
        for(int loop = 0; loop < list.getLength(); loop++){
            Node node = list.item(loop);
            if(node != null){
                String message_id = XmlUtil.getSubTagValue(node, MESSAGE_CODE);
                String message_content = XmlUtil.getSubTagValue(node, MESSAGE_CONTENT);
                if(message_id.length() > 0 && !messages.containsKey(message_id)) {
                    messages.put(message_id, message_content);
                } else{
                    System.out.println("*** Non Fatal errror: Query [" + MESSAGE_CODE + "]" + " defined more than once in query definitions file");
                }
            }
        }

        return messages;
    }
    
    /**
     * 코드 정보를 입력받아 XML정보에 등록된 메세지를 리턴해주는 메소드
     *
     * @param msg       코드 정보
     * @return String   코드 정보에 매핑되는 메세지
     */
    public static String messageMapping(String msgCode) throws MainException{
        String strReturn = "";
        try {
            strReturn = MessageXmlVO.getMessageXml(msgCode.trim());
        }
        catch (Exception ex) {
        	throw new FrameException("FRAME001", new Object[] {"코드 정보를 입력받아 XML정보에 등록된 메세지를 리턴해주는 메소드"});
        }
        return strReturn;
    }

    /**
     * 코드 정보를 입력받아 XML정보에 등록된 메세지를 리턴해주는 메소드
     *
     * @param msg       코드 정보
     * @return String   코드 정보에 매핑되는 메세지
     */
    public static String messageMapping(String msgCode, Object[] msgArgu) throws MainException{
        String strReturn = "";
        String strFormatMessage = "";
        try {
            strReturn = MessageXmlVO.getMessageXml(msgCode.trim());
            MessageFormat messageFormat = new MessageFormat(strReturn);
            strFormatMessage = messageFormat.format(msgArgu);
            return strFormatMessage;
        }
        catch (Exception ex) {
        	throw new FrameException("FRAME001", new Object[] {"코드 정보를 입력받아 XML정보에 등록된 메세지를 리턴해주는 메소드"});
        }
    }

    /**
     * 에러정보와 구분자를 입력받아 XML정보에 맞게 메세지 파싱 작업을 하는 메소드
     *
     * @param msg       에러문자열
     * @param gubun     구분자
     * @return HashMap  파싱한 에러코드와 에러메세지가 담겨있는 배열
     */
    public static HashMap messageMapping(String msg, char gubun) throws MainException{
        String code = "";
        String message = "";
        HashMap<String, String> map = new HashMap<String, String>();
        try{
        	if (msg != null) {
	            Vector vc = StringUtil.split(msg.trim(), gubun);
	            code = vc.elementAt(0).toString();
	
	            if (code != null) {
	                message = messageMapping(code);
	                if (message == null)
	                    message = msg;
	            }
	
	            /*
	             - jdk1.3버전에서는 runtime exception에 대한 경우 그 내용을 return 해 주는 API를 지원하지 않아
	                '관리자에게 문의하세요'라는 메세지로 대체.
	             - jdk1.4버전에서는 java.lang Class Throwable 패키지의 getStackTrace()라는 API제공(이후 개발시 반영사항)
	             */
	        }
	        else {
	            code = "예외 발생";
	            message = "관리자에게 문의하십시오.";
	        }
        }
        catch (Exception ex) {
        	throw new FrameException("FRAME001", new Object[] {"에러정보와 구분자를 입력받아 XML정보에 맞게 메세지 파싱 작업을 하는 메소드"});
        }
        map.put("code", code);
        map.put("message", message);

        return map;
    }
}
