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
public class OpenapiGradeDetailController {
 
    public static final String KEY = "";	//발급필요
    public static final String URL = "http://data.ekape.or.kr/openapi-data/service/user/grade/liveauct/pig";
    
    /** RoleService */
	@Resource(name = "openapiService")
	private OpenapiService openapiService;

	/**
     * 축산물 실시간 돼지도체 등급별경매현황정보
     * @return String
     * @throws Exception
     */
    @RequestMapping("/openApi/openapiGradeDetail.do")
    public String openapiGradeDetail() throws Exception {
         
        return "/openApi/openapiGradeDetail";
    }
   
    /**
     * 축산물 실시간 돼지도체 등급별경매현황정보 검색 결과
     * @param searchTO
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/openApi/openapiGradeDetailList.do", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain;charset=UTF-8")
    public @ResponseBody String openapiGradeDetailList(@ModelAttribute OpenapiSearchTO searchTO) throws Exception {
         
        Map<String, Object> paramMap = new HashMap<String, Object>();
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

            	OpenapiVO.setAbattCode(element.select("abattCode").text());             //작업장코드
            	OpenapiVO.setAbattDate(element.select("abattDate").text());             //도축일자
            	OpenapiVO.setAbattNo(element.select("abattNo").text());                 //도축번호
            	OpenapiVO.setAbattNm(element.select("abattNm").text());                 //도축시장명
            	OpenapiVO.setAuctDate(element.select("auctDate").text());               //경매일자
            	OpenapiVO.setAuctRank(element.select("auctRank").text());               //순번
            	OpenapiVO.setAuctTime(element.select("auctTime").text());               //시간
            	OpenapiVO.setEtc(element.select("etc").text());                         //기타
            	OpenapiVO.setGradeCd(element.select("gradeCd").text());                 //등급코드
            	OpenapiVO.setGradeNm(element.select("gradeNm").text());                 //등급명
            	OpenapiVO.setSkinYn(element.select("skinYn").text());                   //돈피구분
            	OpenapiVO.setSkinYnNm(element.select("skinYnNm").text());               //도축방법 구분
            	OpenapiVO.setJudgeSexCd(element.select("judgeSexCd").text());           //도축성별
            	OpenapiVO.setJudgeSexNm(element.select("judgeSexNm").text());           //도축성별이름
            	OpenapiVO.setLeftWeight(element.select("leftWeight").text());           //좌지육중량
            	OpenapiVO.setRightWeight(element.select("rightWeight").text());         //우지육중량
            	OpenapiVO.setAuctAmt(element.select("auctAmt").text());                 //경매금액
            	OpenapiVO.setWeight(element.select("weight").text());                   //중량
                
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
    @RequestMapping(value = "/openApi/openapiGradeDetailSave.do", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain;charset=UTF-8")
        public @ResponseBody String openapiGradeDetailSave() throws Exception {
         
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());

        StringBuilder queryUrl = new StringBuilder();
        queryUrl.append(URL);
        queryUrl.append("?serviceKey=");
        queryUrl.append(KEY);
        queryUrl.append("&abattCd=0320");            // 도매시장코드  0320 openMPS
        queryUrl.append("&auctDate=");
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

            	OpenapiVO.setAbattCode(element.select("abattCode").text());             //작업장코드
            	OpenapiVO.setAbattDate(element.select("abattDate").text());             //도축일자
            	OpenapiVO.setAbattNo(element.select("abattNo").text());                 //도축번호
            	OpenapiVO.setAbattNm(element.select("abattNm").text());                 //도축시장명
            	OpenapiVO.setAuctDate(element.select("auctDate").text());               //경매일자
            	OpenapiVO.setAuctRank(element.select("auctRank").text());               //순번
            	OpenapiVO.setAuctTime(element.select("auctTime").text());               //시간
            	OpenapiVO.setEtc(element.select("etc").text());                         //기타
            	OpenapiVO.setGradeCd(element.select("gradeCd").text());                 //등급코드
            	OpenapiVO.setGradeNm(element.select("gradeNm").text());                 //등급명
            	OpenapiVO.setSkinYn(element.select("skinYn").text());                   //돈피구분
            	OpenapiVO.setSkinYnNm(element.select("skinYnNm").text());               //도축방법 구분
            	OpenapiVO.setJudgeSexCd(element.select("judgeSexCd").text());           //도축성별
            	OpenapiVO.setJudgeSexNm(element.select("judgeSexNm").text());           //도축성별이름
            	OpenapiVO.setLeftWeight(element.select("leftWeight").text());           //좌지육중량
            	OpenapiVO.setRightWeight(element.select("rightWeight").text());         //우지육중량
            	OpenapiVO.setAuctAmt(element.select("auctAmt").text());                 //경매금액
            	OpenapiVO.setWeight(element.select("weight").text());                   //중량
                
            	OpenapiVO.setResultcode(successYn.select("resultcode").text());         //성공코드
            	OpenapiVO.setResultmsg(successYn.select("resultmsg").text());	        //성공메세지
                
            	OpenapiVO.setOffi_syst("openAPI");                                      //대상시스템
            	OpenapiVO.setIntr_usid("if_dod_1070");                                  //인터페이스ID
            	OpenapiVO.setIntr_name("등급별경매현황정보상세연계");                               //인터페이스명

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

                openapiService.insertGradeDetail(list);
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
            OpenapiVO.setIntr_usid("if_dod_1070");                                       //인터페이스ID(로그)
            OpenapiVO.setIntr_name("등급별경매현황정보상세연계실패");                                 //인터페이스명(로그)
            
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
	   @Scheduled(cron = "0 06 15 * * *")
	public void cronSche1() throws Exception{ 
    	openapiGradeDetailSave();
		} 

    // 오후 8시 1회 
	   @Scheduled(cron = "0 09 15 * * *")
	public void cronSche2() throws Exception{ 
    	openapiGradeDetailSave();
		} 

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        		OpenapiGradeDetailController.class);
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            context.close();
        }
    }*/
 
}