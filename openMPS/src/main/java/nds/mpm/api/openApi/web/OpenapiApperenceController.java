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
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Controller
public class OpenapiApperenceController {
 
    public static final String KEY = "";	//발급필요
    public static final String URL = "http://data.ekape.or.kr/openapi-data/service/user/grade/auct/pigApperence";
    
    /** RoleService */
	@Resource(name = "openapiService")
	private OpenapiService openapiService;

	/**
     * 돼지 권역별 경락가격현황
     * @return String
     * @throws Exception
     */
    @RequestMapping("api/openApi/openapiApperence.do")
    public String openapiApperence() throws Exception {
         
        return "api/openApi/openapiApperence";
    }
   
    /**
     * 돼지 권역별 경락가격현황
     * @param searchTO
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "api/openApi/openapiApperenceList.do", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain;charset=UTF-8")
    public @ResponseBody String openapiApperenceList(@ModelAttribute OpenapiSearchTO searchTO) throws Exception {
         
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 요청 URL 생성
        StringBuilder queryUrl = new StringBuilder();
        queryUrl.append(URL);
        queryUrl.append("?serviceKey=");
        queryUrl.append(KEY);
        queryUrl.append("&baseYmd=");                   // 경매일자
        queryUrl.append(searchTO.getBaseYmd());   
        
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

            	OpenapiVO.setBaseYmd(element.select("baseYmd").text());               //기준연월일
            	OpenapiVO.setBeforeYmd(element.select("beforeYmd").text());           //기준연월일 이전 경매연월일
            	OpenapiVO.setJudgeKindCd(element.select("judgeKindCd").text());       //가축의 종류
            	OpenapiVO.setJudgeKindNm(element.select("judgeKindNm").text());       //가축명
            	OpenapiVO.setSkinYn(element.select("skinYn").text());                 //탕박/박피구분
            	OpenapiVO.setSkinNm(element.select("skinNm").text());                 //탕박/박피구분명
            	OpenapiVO.setLocalNm(element.select("localNm").text());               //권역명
            	OpenapiVO.setLocalCode(element.select("localCode").text());           //권역코드
            	OpenapiVO.setAuctCnt(element.select("auctCnt").text());               //경매 두수
            	OpenapiVO.setAuctAmt(element.select("auctAmt").text());               //경매 금액
            	OpenapiVO.setDiffAuctAmt(element.select("diffAuctAmt").text());       //전일 경매 금액차이
                
            	OpenapiVO.setResultcode(successYn.select("resultcode").text());       //성공코드
            	OpenapiVO.setResultmsg(successYn.select("resultmsg").text());	      //성공메세지
                
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
     * 돼지 권역별 경락가격현황 저장
     * @param searchTO
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "api/openApi/openapiApperenceSave.do", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain;charset=UTF-8")
        public @ResponseBody String openapiApperenceSave() throws Exception {
         
        Map<String, Object> paramMap = new HashMap<String, Object>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());

        StringBuilder queryUrl = new StringBuilder();
        queryUrl.append(URL);
        queryUrl.append("?serviceKey=");
        queryUrl.append(KEY);
        queryUrl.append("&baseYmd=");        //경매일자    
        queryUrl.append(strToday);    
 
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

            	OpenapiVO.setBaseYmd(element.select("baseYmd").text());               //기준연월일
            	OpenapiVO.setBeforeYmd(element.select("beforeYmd").text());           //기준연월일 이전 경매연월일
            	OpenapiVO.setJudgeKindCd(element.select("judgeKindCd").text());       //가축의 종류
            	OpenapiVO.setJudgeKindNm(element.select("judgeKindNm").text());       //가축명
            	OpenapiVO.setSkinYn(element.select("skinYn").text());                 //탕박/박피구분
            	OpenapiVO.setSkinNm(element.select("skinNm").text());                 //탕박/박피구분명
            	OpenapiVO.setLocalNm(element.select("localNm").text());               //권역명
            	OpenapiVO.setLocalCode(element.select("localCode").text());           //권역코드
            	OpenapiVO.setAuctCnt(element.select("auctCnt").text());               //경매 두수
            	OpenapiVO.setAuctAmt(element.select("auctAmt").text());               //경매 금액
            	OpenapiVO.setDiffAuctAmt(element.select("diffAuctAmt").text());       //전일 경매 금액차이

            	OpenapiVO.setOffi_syst("openAPI");                                    //대상시스템
            	OpenapiVO.setIntr_usid("if_dod_1080");                                //인터페이스ID
            	OpenapiVO.setIntr_name("권역별경락가격현황연계");                               //인터페이스명

            	OpenapiVO.setResultcode(successYn.select("resultcode").text());       //성공코드
            	OpenapiVO.setResultmsg(successYn.select("resultmsg").text()); 	      //성공메세지

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

                openapiService.insertApperence(list);
        		openapiService.insertLog(list);
            	
            }
            
            // 요청 결과가 정상이 아닐 경우 에러 내용을 담는다.
        } else {
            org.jsoup.select.Elements successYn = document.select("header");
            
            List<OpenapiVO> list = new ArrayList<OpenapiVO>();
            OpenapiVO OpenapiVO = null;
            
            OpenapiVO = new OpenapiVO();
        	
            OpenapiVO.setOffi_syst("openAPI");                                        //대상시스템(로그)        
            OpenapiVO.setIntr_usid("if_dod_1080");                                    //인터페이스ID(로그)
            OpenapiVO.setIntr_name("권역별경락가격현황연계");                                   //인터페이스명(로그)
            OpenapiVO.setResultcode(successYn.select("resultcode").text());           //성공코드
            OpenapiVO.setResultmsg(successYn.select("resultmsg").text());	          //성공메세지
            
            list.add(OpenapiVO);

            paramMap.put("list", list);
    		openapiService.insertLog(list);
        }
        
        String mav = (new Gson()).toJson(paramMap);

        return(mav);
    }
    
    /**
     * 돼지 권역별 경락가격현황 저장 스케줄(하루 2회)
     * @param searchTO
     * @return String
     * @throws Exception
     */
/*	@SuppressWarnings("unused")
	private AtomicInteger counter = new AtomicInteger(0);

	// 오후 6시 1회 
	   @Scheduled(cron = "0 35 14 * * *")
	public void cronSche1() throws Exception{ 
    	openapiApperenceSave();
		} 

    // 오후 8시 1회 
	   @Scheduled(cron = "0 37 14 * * *")
	public void cronSche2() throws Exception{ 
    	openapiApperenceSave();
		} 
   
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        		OpenapiApperenceController.class);
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            context.close();
        }
    }*/
}