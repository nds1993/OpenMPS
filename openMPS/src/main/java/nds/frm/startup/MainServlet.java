package nds.frm.startup;

import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import nds.frm.message.MessageUtil;
import nds.frm.message.MessageXmlVO;



public class MainServlet extends HttpServlet{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String messagePath;
    protected InputStream messageStream;
    
    /**
     * 초기화 함수로서 메세지 정보를 읽어서 메모리에 저장
     * @param
     * @return
     */
    public void init() throws ServletException {
        super.init();
        try {
        	// 공통코드 데이터 가져오는 부분(주석처리)
            /*InitService initSVC = new InitServiceImpl();
            initSVC.loadEnvData();*/

            messagePath = getInitParameter("messagedefinitions");
            messageStream = getServletContext().getResourceAsStream(messagePath);
            getMessageManager();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * 메세지정보를 XML 파일로 부터 읽어서 메모리에 로딩
     *
     * @param
     * @return
     */
    private void getMessageManager() throws Exception {
        HashMap hashMap = MessageUtil.loadMessageDefinitions(messagePath, messageStream);
		@SuppressWarnings("unused")
        MessageXmlVO messageXmlVO = new MessageXmlVO(hashMap);        
    }       
}
