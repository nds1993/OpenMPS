package nds.mpm.api.openApi.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import nds.mpm.api.openApi.service.OpenapiSearchTO;
import nds.mpm.api.openApi.service.OpenapiService;
import nds.mpm.api.openApi.service.OpenapiVO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
@Controller
public class OpenapiGradeController {
 
    public static final String KEY = "XVDRBSRzu3xzEuhxcLgYaQ6TKfYD2gaVEgc%2FgvLomZ6M%2BLkuYV1soAIhVKQTTaRWbZ2M2R0VUR9Ubitwx3MoAg%3D%3D";
    public static final String URL = "http://data.ekape.or.kr/openapi-data/service/user/grade/liveauct/pigGrade";
    
    /** RoleService */
	@Resource(name = "openapiService")
	private OpenapiService openapiService;

	/**
     * 축산물 실시간 돼지도체 등급별경매현황정보
     * @return String
     * @throws Exception
     */
    @RequestMapping("/openApi/openapiGrade.do")
    public String openapiGrade() throws Exception {
         
        return "/openApi/openapiGrade";
    }
   
    /**
     * 축산물 실시간 돼지도체 등급별경매현황정보 검색 결과
     * @param searchTO
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/openApi/openapiGradelist.do", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain;charset=UTF-8")
    public @ResponseBody String openapiGradelist(@ModelAttribute OpenapiSearchTO searchTO) throws Exception {
         
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());

        // 요청 URL 생성
        StringBuilder queryUrl = new StringBuilder();
        queryUrl.append(URL);
        queryUrl.append("?serviceKey=");
        queryUrl.append(KEY);
        queryUrl.append("&abattCd=0320");            // 도매시장코드  0320 openMPS
        queryUrl.append("&skinYn=Y");
        queryUrl.append("&auctDate=");
        queryUrl.append(searchTO.getAuctDate());
       
        // document 선언
        Document document = Jsoup.connect(queryUrl.toString()).get();

        String errorCode = document.select("resultcode").text();
        
        // 요청 결과가 정상일 경우 내용을 파싱하여 List 에 담는다.
        if ( "00".equals(errorCode)) {
            org.jsoup.select.Elements elements = document.select("item");
            org.jsoup.select.Elements successYn = document.select("header");
            
            List<OpenapiVO> list = new ArrayList<OpenapiVO>();
            OpenapiVO OpenapiVO = null;
            
            for(Element element : elements){
            	OpenapiVO = new OpenapiVO();

            	OpenapiVO.setAuctDate(element.select("auctDate").text());               //경매일자
            	OpenapiVO.setAbattCode(element.select("abattCode").text());             //도매시장코드
            	OpenapiVO.setSkinYn(element.select("skinYn").text());                   //박피여부
            	OpenapiVO.setJudgeSexCd(element.select("judgeSexCd").text());           //성별코드
            	OpenapiVO.setAbattNm(element.select("abattNm").text());                 //도매시장명
            	OpenapiVO.setSkinNm(element.select("skinNm").text());                   //돈피구분
            	OpenapiVO.setJudgeSexNm(element.select("judgeSexNm").text());           //성별
            	OpenapiVO.setAuct_1Amt(element.select("auct_1Amt").text());             //1+등급 경매 가격
            	OpenapiVO.setAuct_2Amt(element.select("auct_2Amt").text());             //1등급 경매 가격
            	OpenapiVO.setAuct_3Amt(element.select("auct_3Amt").text());             //2등급 경매 가격
            	OpenapiVO.setAuctEAmt(element.select("auctEAmt").text());               //등외등급 경매 가격
            	OpenapiVO.setAuctEExceptAmt(element.select("auctEExceptAmt").text());   //등외제외 평균 경매 가격
            	OpenapiVO.setTotalAuctAmt(element.select("totalAuctAmt").text());       //평균 경매 가격
            	OpenapiVO.setTotalAuctCnt(element.select("totalAuctCnt").text());       //경매 두수
                
            	OpenapiVO.setResultcode(successYn.select("resultcode").text());         //성공코드
            	OpenapiVO.setResultmsg(successYn.select("resultmsg").text());	        //성공메세지
                
                list.add(OpenapiVO);
            }
             
            paramMap.put("list", list);
             
        // 요청 결과가 정상이 아닐 경우 에러 내용을 담는다.
        } else {
            String errorMessage = document.select("resultmsg").text();
             
            paramMap.put("resultcode", errorCode);
            paramMap.put("resultmsg", errorMessage);
        }
        
        String mav = (new Gson()).toJson(paramMap);
        	
        return(mav);
    }

	/**
     * 축산물 실시간 돼지도체 등급별경매현황정보 저장
     * @param searchTO
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/openApi/openapiGradeListSave.do", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain;charset=UTF-8")
    public @ResponseBody String openapiGradeListSave() throws Exception {
         
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());

        StringBuilder queryUrl = new StringBuilder();
        queryUrl.append(URL);
        queryUrl.append("?serviceKey=");
        queryUrl.append(KEY);
        queryUrl.append("&abattCd=0320");            // 도매시장코드  0320 openMPS
        queryUrl.append("&skinYn=Y");				 // 탕박 'Y'
        queryUrl.append("&auctDate=");
        queryUrl.append(strToday);                   // 날짜
 
        // document 선언
        Document document = Jsoup.connect(queryUrl.toString()).get();
        
        String errorCode = document.select("resultcode").text();
      
      // 요청 결과가 정상일 경우 내용을 파싱하여 List 에 담는다.
      if ( "00".equals(errorCode)) {
            org.jsoup.select.Elements elements = document.select("item");
            org.jsoup.select.Elements successYn = document.select("header");
            
            List<OpenapiVO> list = new ArrayList<OpenapiVO>();
            OpenapiVO OpenapiVO = null;
            
            for(Element element : elements){
            	OpenapiVO = new OpenapiVO();

            	OpenapiVO.setAuctDate(element.select("auctDate").text());               //경매일자
            	OpenapiVO.setAbattCode(element.select("abattCode").text());             //도매시장코드
            	OpenapiVO.setSkinYn(element.select("skinYn").text());                   //박피여부
            	OpenapiVO.setJudgeSexCd(element.select("judgeSexCd").text());           //성별코드
            	OpenapiVO.setAbattNm(element.select("abattNm").text());                 //도매시장명
            	OpenapiVO.setSkinNm(element.select("skinNm").text());                   //돈피구분
            	OpenapiVO.setJudgeSexNm(element.select("judgeSexNm").text());           //성별
            	OpenapiVO.setAuct_1Amt(element.select("auct_1Amt").text());             //1+등급 경매 가격
            	OpenapiVO.setAuct_2Amt(element.select("auct_2Amt").text());             //1등급 경매 가격
            	OpenapiVO.setAuct_3Amt(element.select("auct_3Amt").text());             //2등급 경매 가격
            	OpenapiVO.setAuctEAmt(element.select("auctEAmt").text());               //등외등급 경매 가격
            	OpenapiVO.setAuctEExceptAmt(element.select("auctEExceptAmt").text());   //등외제외 평균 경매 가격
            	OpenapiVO.setTotalAuctAmt(element.select("totalAuctAmt").text());       //평균 경매 가격
            	OpenapiVO.setTotalAuctCnt(element.select("totalAuctCnt").text());       //경매 두수
                
            	OpenapiVO.setResultcode(successYn.select("resultcode").text());         //성공코드
            	OpenapiVO.setResultmsg(successYn.select("resultmsg").text());	        //성공메세지
                 
            	OpenapiVO.setOffi_syst("openAPI");                                       //대상시스템
            	OpenapiVO.setIntr_usid("if_dod_1060");                                   //인터페이스ID
            	OpenapiVO.setIntr_name("등급별경매현황정보연계");                                  //인터페이스명

                list.add(OpenapiVO);
            }
            
            if(list.size() < 1){ 
            	
            	OpenapiVO = new OpenapiVO();
            	
            	OpenapiVO.setOffi_syst("openAPI");                                    //대상시스템
            	OpenapiVO.setIntr_usid("if_dod_1080");                                //인터페이스ID
            	OpenapiVO.setIntr_name("권역별경락가격현황연계");                               //인터페이스명
            	OpenapiVO.setResultcode("errore");							          //성공코드
            	OpenapiVO.setResultmsg("nodata");                            	      //성공메세지
            	
            	list.add(OpenapiVO);
            
                paramMap.put("list", list);

        		openapiService.insertLog(list);
            }else{

            	paramMap.put("list", list);

                openapiService.insertGrade(list);
        		openapiService.insertLog(list);
            }
        // 요청 결과가 정상이 아닐 경우 에러 내용을 담는다.
        } else {
            org.jsoup.select.Elements successYn = document.select("header");
            
            List<OpenapiVO> list = new ArrayList<OpenapiVO>();
            OpenapiVO OpenapiVO = null;
            
            OpenapiVO = new OpenapiVO();
        	
            OpenapiVO.setResultcode(successYn.select("resultcode").text());              //성공코드
            OpenapiVO.setResultmsg(successYn.select("resultmsg").text());	             //성공메세지
        	
            OpenapiVO.setOffi_syst("openAPI");                                           //대상시스템(로그)        
            OpenapiVO.setIntr_usid("if_dod_1060");                                       //인터페이스ID(로그)
            OpenapiVO.setIntr_name("등급별경매현황정보연계실패");                                    //인터페이스명(로그)
            
            list.add(OpenapiVO);

            paramMap.put("list", list);
    		openapiService.insertLog(list);
        }
        
        String mav = (new Gson()).toJson(paramMap);

        return(mav);
        
    }

    /**
     * 축산물 실시간 돼지도체 등급별경매현황정보 저장 스케줄(하루 2회)
     * @param searchTO
     * @return String
     * @throws Exception
     */
/*	@SuppressWarnings("unused")
	private AtomicInteger counter = new AtomicInteger(0);

	// 오후 6시 1회 
	   @Scheduled(cron = "0 07 15 * * *")
	public void cronSche1() throws Exception{ 
    	openapiGradeListSave();
		} 

    // 오후 8시 1회 
	   @Scheduled(cron = "0 09 15 * * *")
	public void cronSche2() throws Exception{ 
    	openapiGradeListSave();
		} 

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        		OpenapiGradeController.class);
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            context.close();
        }
    }*/
    
}