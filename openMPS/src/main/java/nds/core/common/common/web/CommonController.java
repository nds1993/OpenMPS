package nds.core.common.common.web;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.PropTypeVO;
import nds.core.common.common.service.UserMenu;
import nds.core.common.common.service.UserSession;
import nds.core.common.main.service.MainService;
import nds.core.operation.notice.service.NoticeService;
import nds.core.operation.notice.service.NoticeVO;
import nds.frm.config.StaticConfig;
import nds.frm.util.EncryptUtil;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <p>Title: CommonController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Controller
public class CommonController extends BaseController {
	
	/** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService;
    @Resource(name = "mainService")
    private MainService mainService;
    @Resource(name = "noticeService")
    private NoticeService noticeService;
    
    public static boolean topNotiListReloadble = true;
    public static ArrayList<NoticeVO> topNotiList = null;
    

    @RequestMapping(value="/test.do")
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("test");
        
        int a=1,b=1;
        
        for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(a%2==1){
					System.out.print(b+"\t");
					b++;
				}else{
					System.out.print("\t");
				}
				a++;
			}
			System.out.println();
		}
        int count = 0;
        
        for (int i =1; i < 13; i++) {
			if(count !=3){
				System.out.print(i+"\t");
				count++;
				if(count==3){
					System.out.println();
				}
			}else{
				System.out.print("\t"+i+"\t");
				if(i%5==0){
					System.out.println();
					count=0;
				}
			}
		}
        for (int i=1; i<13; i++){
        	if(i<=3){
        		System.out.print(i+"\t");
            	if(i==3){
            		System.out.println();
            	}	
        	}else if(i>3 && i<=5){
        		System.out.print("\t"+i);
        		if(i==5){
        			System.out.println("\t");
        		}
        	}else if(i>5 && i<=8){
            	System.out.print(i+"\t");
        		if(i==8){
        			System.out.println("\t");
        		}
        	}else if(i>8 && i<=10){
        		System.out.print("\t"+i);
        		if(i==10){
        			System.out.println("\t");
        		}
        	}else{
            	System.out.print(i+"\t");
        		if(i==13){
        			System.out.println("\t");
        		}
        	}
        }
        
        int i, num=1;
        int checkNum=0;
        
        for(i=0; i<14;i++){
        	if(checkNum++%2==1){
        		for (int j = 1; j < 4; j++) {
					System.out.print(num);
					System.out.println("\t\t");
					num++;
				}
        	}else{
        		for (int j = 0; j < 2; j++) {
					System.out.print("\t");
					System.out.print(num);
					System.out.print("\t");
					num++;
				}
        	}
        	if(num==14) break;
        }
        
        
        Map<String, List> map = new HashMap<String, List>();
        
        List cdF008 = getCommonCodeList("F008");
        
        map.put("F008",cdF008);
        
        
        List f008 = map.get("F008"); 
        
        if(f008!=null){
        	if(f008.size()==1){
        		//set
        	}else{
        		//popup
        	}
        }
        
        return mav;
    }
    

    /**
     * maindex 페이지
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/maindex.do")
    public ModelAndView maindex(HttpServletRequest request, HttpServletResponse response) throws Exception {

        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        UserSession uskin = new UserSession(userSession.getLogin(), commonService.getSkinInfo(userSession.getSkinNo()));
       
    	List<UserMenu> result = null;
    	UserMenu key = new UserMenu();
    	key.setId(userSession.getLogin().getUserId());
    	key.setDepCd(userSession.getLogin().getDepCd());
    	key.setMenuInclYn("Y");
    	key.setMenuLevelFr("4");
    	key.setMenuLevelTo("4"); 
    	
    	ModelAndView mav = new ModelAndView("maindex");
    	mav.addObject("result", result);
    	mav.addObject("uskin", uskin);
    	return mav;

    }

    /**
     * voctopmenu 페이지
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/voctopmenu.do")
    public ModelAndView voctopmenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BASE64Encoder encoder = new BASE64Encoder();
		
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        String userEmpno = userSession.getLogin().getUserEmpno();

		String tempByte1 = encoder.encode(userEmpno.getBytes());
		String tempByte2 = encoder.encode(tempByte1.getBytes());
        UserSession uskin = new UserSession(userSession.getLogin(), commonService.getSkinInfo(userSession.getSkinNo()));

        ModelAndView mav = new ModelAndView(uskin.getSkinViewFolder() + "/voctopmenu");
//        System.out.println("#################### 암호화["+ tempByte2+"]");
//		BASE64Decoder decoder = new BASE64Decoder();
//		byte[] tempByte3 = decoder.decodeBuffer(tempByte2);
//		byte[] tempByte4 = decoder.decodeBuffer(new String(tempByte3));
//		String uid = new String(tempByte4);
//		System.out.println("#################### 복호화[" + uid + "]");
		
        mav.addObject("uskin", uskin);
        mav.addObject("uid", tempByte2);
        
        return mav;
    }

    /**
     * TOP 공지사항 조회
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/topNotiInfo_ajax.do")
    public ModelAndView topNotiInfo_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	String seqNo     = StringUtil.getParam(request, "seqNo", "0");
        String searchNum = StringUtil.getParam(request, "searchNum", "5");
        
		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
		NoticeVO resultVO = new NoticeVO();
		
		NoticeVO searchVO = new NoticeVO();
		searchVO.setDbrdCd(StaticConfig.VOC_BOARD_NOTICE);
		searchVO.setSearchNum(searchNum);
		
		if(topNotiListReloadble){
			topNotiList = noticeService.selectTopNotiInfo(searchVO);
		}

		if(topNotiList != null && topNotiList.size() > 0){
			for(int i=0; i < topNotiList.size() ;i++){
				NoticeVO noticeVO = (NoticeVO) topNotiList.get(i);
				if( i == Integer.parseInt(seqNo)){
					resultVO.setDocNo(noticeVO.getDocNo());
					resultVO.setTit(noticeVO.getTit());
				}
			}
			topNotiListReloadble = false;
		}
		else{
			topNotiListReloadble = true;
		}
		
        mav.addObject("result", resultVO);
        return mav; 
    }
    
    
    /**
     * 파일업로드폼
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/co_uploadform.do")
    public ModelAndView fileuploadForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("co_uploadform");
        return mav;
    }
    
    /**
     * 권한오류 페이지
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/authError.do")
    public ModelAndView authError(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("authError");
        if(EncryptUtil.DecodeBySType(StringUtil.getParam(request, "authmsg", "")).equals("voc.xxxxxx.xx.kr")) {
            mav.addObject("mail", "Y");
        }

        UserSession uskin = new UserSession(null, commonService.getSkinInfo());
        mav.addObject("uskin", uskin);
        return mav;
    }
    
    /**
     * 그리드 컬럼저장
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/columnSave.do")
    public void columnSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String menuColumn = StringUtil.getParam(request, "menuColumn", "");    // hidden column
    	String menuColumnOrd = StringUtil.getParam(request, "menuColumnOrd", "");    // move column
    	
    	String pid = StringUtil.getParam(request, "pid", "");
        
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String cstNo = userSession.getLogin().getUserEmpno();
    	
    	MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();
    	
        myMenuColumnVO.setUserId(cstNo);
        myMenuColumnVO.setRegUser(cstNo);
        myMenuColumnVO.setUpdtUser(cstNo);
        myMenuColumnVO.setMenuId(pid);
        myMenuColumnVO.setMenuColumn(menuColumn);
        myMenuColumnVO.setMenuColumnOrd(menuColumnOrd);
        
        if(!"".equals(pid)){
        	commonService.insertMenuColumn(myMenuColumnVO);
        }
    }

    /**
     * 제안 유형 (중) 조회
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value="/propTypeMcls.do")
    public ModelAndView propTypeMcls(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
		String lcls = StringUtil.getParam(request, "lcls", "");
		
		PropTypeVO propTypeVO = new PropTypeVO();
		propTypeVO.setLcls(lcls);
		
		List<PropTypeVO> list = commonService.selectPropTypeMcls(propTypeVO);
		
		mav.addObject("result", list);
		return mav;
    }

    /**
     * 제안 결과 유형 (중) 조회
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value="/propResultTypeMcls.do")
    public ModelAndView propResultTypeMcls(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
		String lcls = StringUtil.getParam(request, "lcls", "");
		
		PropTypeVO propTypeVO = new PropTypeVO();
		propTypeVO.setLcls(lcls);
		
		List<PropTypeVO> list = commonService.selectPropResultTypeMcls(propTypeVO);
		
		mav.addObject("result", list);
		return mav;
    }
    
    /**
     * 고객조회
     * @param searchMode
     * @param params
     * @return
     */
	public String getCustList(String searchMode, String params){
		System.out.println("################################  고객정보 조회 START");
		
		HttpURLConnection connection = null;
		InputStream is;
		InputStreamReader isr;
		BufferedReader br;
		StringBuffer sb = new StringBuffer();
		String urlAddr = "";
		
		try{
			if("List".equals(searchMode)){
				//urlAddr = StaticConfig.VOC_CUST_SEARCH_ETC+ params;
			}else if("Detail".equals(searchMode)){
				//urlAddr = StaticConfig.VOC_CUST_SEARCH_ID+ params;
			}
			
			URL url = new URL(urlAddr); //요청 URL
			//문자열로 URL표현     
			System.out.println("URL : "+url.toExternalForm());
			connection = (HttpURLConnection)url.openConnection();
			//요청방식
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Cookie", "HOST_SYSTEM=VOC");
			connection.setDoOutput(true);
			connection.setConnectTimeout(2000); //2초
			connection.setReadTimeout(2000);
			
			is = connection.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			String line = null;
			
			while((line = br.readLine()) !=null){
				System.out.println("###############   line while ###################");
				System.out.println("["+ line + "]");
				sb.append(line).append("\n");
			}
			br.close();
		}catch(ConnectException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(connection != null){
				connection.disconnect();
			}
		}
		System.out.println("################################  고객정보 조회 END");
		return sb.toString();
	}
}
