package nds.mpm.api.openApi.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

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

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Configuration
@EnableScheduling
@Controller
public class OpenapiController {
 
    public static final String KEY = "";	//발급필요
    public static final String URL = "http://data.ekape.or.kr/openapi-data/service/user/grade/auct/pigGrade";
    
    /** RoleService */
	@Resource(name = "openapiService")
	private OpenapiService openapiService;

    /**
     * 돼지도체 경락가격 화면 (저장)
     * @return String
     * @throws Exception
     */
    @RequestMapping("/openApi/openapiGradeSave.do")
    public String openapiGradeSave() throws Exception {
         
        return "/openApi/openapiGradeSave";
    }
   
    
    public static Date getYesterday ( Date today ) {
    	 if ( today == null ) throw new IllegalStateException ( "today is null" );
    	 Date yesterday = new Date ( );
    	 yesterday.setTime ( today.getTime ( ) - ( (long) 1000 * 60 * 60 * 24 ) );
    	 return yesterday;
    	}
    
    /**
     * 돼지도체 경락가격 검색 결과
     * @param searchTO
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/openApi/list.do", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain;charset=UTF-8")
    public @ResponseBody String list(@ModelAttribute OpenapiSearchTO searchTO) throws Exception {
    	
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        SimpleDateFormat day = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        String strToday 	= day.format(cal.getTime());
        //add(Calendar.DATE, -1);
        //String strYesterday =  DateUtil.addDays(strToday, -1, "yyyyMMdd");

        int currentHour = cal.get(Calendar.HOUR_OF_DAY);
        
        // 요청 URL 생성
        StringBuilder queryUrl = new StringBuilder();
        queryUrl.append(URL);
        queryUrl.append("?serviceKey=");
        queryUrl.append(KEY);
        queryUrl.append("&skinYn=Y");
        queryUrl.append("&sexCd=025001");

        if(currentHour > 20)
        {
            queryUrl.append("&startYmd=");
            queryUrl.append(strToday);  // 시작일
            queryUrl.append("&endYmd=");
            queryUrl.append(strToday);    // 종료일
        }else{
            queryUrl.append("&startYmd=");
            queryUrl.append(strToday);  // 시작일
            queryUrl.append("&endYmd=");
            queryUrl.append(strToday);    // 종료일
        }

        // document 선언
        Document document = Jsoup.connect(queryUrl.toString()).get();

        String errorCode = document.select("resultcode").text();
        
        // 요청 결과가 정상일 경우 내용을 파싱하여 List 에 담는다.
        if ( "00".equals(errorCode)) {
            org.jsoup.select.Elements elements = document.select("item");
            
            List<OpenapiVO> list = new ArrayList<OpenapiVO>();
            OpenapiVO OpenapiVO = null;
            
            for(Element element : elements){
            	OpenapiVO = new OpenapiVO();

            	OpenapiVO.setGradeType(element.select("gradeType").text());          //등급구분
            	OpenapiVO.setStartYmd(element.select("startYmd").text());            //시작일
            	OpenapiVO.setEndYmd(element.select("endYmd").text());                //종료일
            	OpenapiVO.setGradeCd(element.select("gradeCd").text());              //등급구분
            	OpenapiVO.setGradeNm(element.select("gradeNm").text());              //등급명
            	OpenapiVO.setSkinYn(element.select("skinYn").text());			     //박피/탕박구분
            	OpenapiVO.setSkinNm(element.select("skinNm").text());			     //박피/탕박
            	OpenapiVO.setJudgeSexCd(element.select("judeSexCd").text());		 //성별코드
            	OpenapiVO.setJudgeSexNm(element.select("judeSexNm").text());		 //성별

            	OpenapiVO.setC_0320Amt(element.select("c_0320Amt").text());		     //가격(openMPS)
            	OpenapiVO.setC_0320Cnt(element.select("c_0320Cnt").text());		     //두수(openMPS)
            	OpenapiVO.setC_0302Amt(element.select("c_0302Amt").text());		     //가격(협신식품)
            	OpenapiVO.setC_0302Cnt(element.select("c_0302Cnt").text());		     //두수(협신식품)
            	OpenapiVO.setC_1301Amt(element.select("c_1301Amt").text());		     //가격(삼성식품)
            	OpenapiVO.setC_1301Cnt(element.select("c_1301Cnt").text());		     //두수(삼성식품)
            	OpenapiVO.setC_0323Amt(element.select("c_0323Amt").text());		     //가격(농협부천)
            	OpenapiVO.setC_0323Cnt(element.select("c_0323Cnt").text());		     //두수(농협부천)
            	OpenapiVO.setCCityAmt(element.select("CCityAmt").text()); 		     //가격(수도권)
            	OpenapiVO.setCCityCnt(element.select("CCityCnt").text());		     //두수(수도권)

            	OpenapiVO.setC_0513Amt(element.select("c_0513Amt").text());		     //가격(농협음성)
            	OpenapiVO.setC_0513Cnt(element.select("c_0513Cnt").text());		     //두수(농협음성)
                
            	OpenapiVO.setC_1005Amt(element.select("c_1005Amt").text());		     //가격(김해축공)
            	OpenapiVO.setC_1005Cnt(element.select("c_1005Cnt").text());		     //두수(김해축공)
            	OpenapiVO.setC_0202Amt(element.select("c_0202Amt").text());		     //가격(부경축공)
            	OpenapiVO.setC_0202Cnt(element.select("c_0202Cnt").text());		     //두수(부경축공)
            	OpenapiVO.setC_0201Amt(element.select("c_0201Amt").text());		     //가격(동원산업)
            	OpenapiVO.setC_0201Cnt(element.select("c_0201Cnt").text());		     //두수(동원산업)
            	OpenapiVO.setC_1201Amt(element.select("c_1201Amt").text());		     //가격(신흥산업)
            	OpenapiVO.setC_1201Cnt(element.select("c_1201Cnt").text());		     //두수(신흥산업)
            	OpenapiVO.setC_0905Amt(element.select("c_0905Amt").text());		     //가격(농협고령)
            	OpenapiVO.setC_0905Cnt(element.select("c_0905Cnt").text());		     //두수(농협고령)
            	OpenapiVO.setCEastAmt(element.select("CEastAmt").text()); 		     //가격(수도권)
            	OpenapiVO.setCEastCnt(element.select("CEastCnt").text());		     //두수(수도권)

            	OpenapiVO.setC_0714Amt(element.select("c_0714Amt").text());		     //가격(익산)
            	OpenapiVO.setC_0714Cnt(element.select("c_0714Cnt").text());		     //두수(익산)
            	OpenapiVO.setC_0809Amt(element.select("c_0809Amt").text());		     //가격(농협나주)
            	OpenapiVO.setC_0809Cnt(element.select("c_0809Cnt").text());		     //두수(농협나주)
            	OpenapiVO.setC_1401Amt(element.select("c_1401Amt").text());		     //가격(삼호축산)
            	OpenapiVO.setC_1401Cnt(element.select("c_1401Cnt").text());		     //두수(삼호축산)
            	OpenapiVO.setCWestAmt(element.select("CWestAmt").text()); 		     //가격(호남권)
            	OpenapiVO.setCWestCnt(element.select("CWestCnt").text());		     //두수(호남권)
                
            	OpenapiVO.setC_1101Amt(element.select("c_1101Amt").text());          //가격(제주축협)
            	OpenapiVO.setC_1101Cnt(element.select("c_1101Cnt").text());          //두수(제주축협)
                
            	OpenapiVO.setCTotAmt(element.select("CTotAmt").text());              //가격(전체)
            	OpenapiVO.setCTotCnt(element.select("CTotCnt").text());              //두수(전체)
            	OpenapiVO.setC_1101eTotAmt(element.select("c_1101eTotAmt").text());  //가격(제주제외전체)
            	OpenapiVO.setC_1101eTotCnt(element.select("c_1101eTotCnt").text());  //두수(제주제외전체)
                
                list.add(OpenapiVO);
            }
             
            paramMap.put("list", list);
             
        // 요청 결과가 정상이 아닐 경우 에러 내용을 담는다. IF_LOGTXM (insert log)
        } else {
            String errorMessage = document.select("resultmsg").text();
             
            paramMap.put("resultcode", errorCode);
            paramMap.put("resultmsg", errorMessage);
        }
        
        String mav = (new Gson()).toJson(paramMap);
        	
        return(mav);
    }

	/**
     * 돼지도체 경락가격 저장(지육시세등록)
     * @param searchTO
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/openApi/save.do", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain;charset=UTF-8")
      public @ResponseBody String save(@ModelAttribute OpenapiSearchTO searchTO) throws Exception {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        /* skinYn = Y 박피, N 탕박   */
        String arrSkin[] = {"Y","N"};
        /* judgeSexCd = 025001 암, 025002 수, 025003 거세, 025004 기타 */
        String arrSexP[] = {"025001","025002","025003","025004",""};
//        String arrExcept[] = {"Y","N"}; 
        /* 등외등급제외구분 Y포함, N제외 */
        
        List<OpenapiVO> listAll = new ArrayList<OpenapiVO>();

        	for (int p = 0; p < arrSkin.length; p++) {

		        for (int i = 0; i < arrSexP.length; i++) {
					
		        // 요청 URL 생성
		        StringBuilder queryUrl = new StringBuilder();
		        queryUrl.append(URL);
		        queryUrl.append("?serviceKey=");
		        queryUrl.append(KEY);
		        queryUrl.append("&skinYn=");
		        queryUrl.append(arrSkin[p]);
		        queryUrl.append("&sexCd="); 
		        queryUrl.append(arrSexP[i]); 
		        queryUrl.append("&startYmd=");
		        queryUrl.append("20170911");
//		        queryUrl.append(searchTO.getStrtDate());        			// 시작일
		        queryUrl.append("&endYmd=");
		        queryUrl.append("20170911");
//		        queryUrl.append(searchTO.getLastDate());        			// 시작일
		        queryUrl.append("&egradeExceptYn=Y");
		        
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
		
		            	OpenapiVO.setGradeType(element.select("gradeType").text());         //등급구분
		            	OpenapiVO.setStartYmd(element.select("startYmd").text());           //시작일
		            	OpenapiVO.setEndYmd(element.select("endYmd").text());               //종료일
		            	OpenapiVO.setGradeCd(element.select("gradeCd").text());             //등급구분
		            	OpenapiVO.setGradeNm(element.select("gradeNm").text());             //등급명
		            	OpenapiVO.setSkinYn(element.select("skinYn").text());			    //박피/탕박구분
		            	OpenapiVO.setSkinNm(element.select("skinNm").text());			    //박피/탕박
		            	OpenapiVO.setJudgeSexCd(element.select("judgeSexCd").text());	    //성별코드
		            	OpenapiVO.setJudgeSexNm(element.select("judgeSexNm").text());	    //성별
		
		            	OpenapiVO.setC_0320Amt(element.select("c_0320Amt").text());		    //가격(openMPS)
		            	OpenapiVO.setC_0320Cnt(element.select("c_0320Cnt").text());		    //두수(openMPS)
		            	OpenapiVO.setC_0302Amt(element.select("c_0302Amt").text());		    //가격(협신식품)
		            	OpenapiVO.setC_0302Cnt(element.select("c_0302Cnt").text());		    //두수(협신식품)
		            	OpenapiVO.setC_1301Amt(element.select("c_1301Amt").text());		    //가격(삼성식품)
		            	OpenapiVO.setC_1301Cnt(element.select("c_1301Cnt").text());		    //두수(삼성식품)
		            	OpenapiVO.setC_0323Amt(element.select("c_0323Amt").text());		    //가격(농협부천)
		            	OpenapiVO.setC_0323Cnt(element.select("c_0323Cnt").text());		    //두수(농협부천)
		            	OpenapiVO.setCCityAmt(element.select("CCityAmt").text()); 		    //가격(수도권)
		            	OpenapiVO.setCCityCnt(element.select("CCityCnt").text());		    //두수(수도권)
		
		            	OpenapiVO.setC_0513Amt(element.select("c_0513Amt").text());		    //가격(농협음성)
		            	OpenapiVO.setC_0513Cnt(element.select("c_0513Cnt").text());		    //두수(농협음성)
		                
		            	OpenapiVO.setC_1005Amt(element.select("c_1005Amt").text());		    //가격(김해축공)
		            	OpenapiVO.setC_1005Cnt(element.select("c_1005Cnt").text());		    //두수(김해축공)
		            	OpenapiVO.setC_0202Amt(element.select("c_0202Amt").text());		    //가격(부경축공)
		            	OpenapiVO.setC_0202Cnt(element.select("c_0202Cnt").text());		    //두수(부경축공)
		            	OpenapiVO.setC_0201Amt(element.select("c_0201Amt").text());		    //가격(동원산업)
		            	OpenapiVO.setC_0201Cnt(element.select("c_0201Cnt").text());		    //두수(동원산업)
		            	OpenapiVO.setC_1201Amt(element.select("c_1201Amt").text());		    //가격(신흥산업)
		            	OpenapiVO.setC_1201Cnt(element.select("c_1201Cnt").text());		    //두수(신흥산업)
		            	OpenapiVO.setC_0905Amt(element.select("c_0905Amt").text());		    //가격(농협고령)
		            	OpenapiVO.setC_0905Cnt(element.select("c_0905Cnt").text());		    //두수(농협고령)
		            	OpenapiVO.setCEastAmt(element.select("CEastAmt").text()); 		    //가격(수도권)
		            	OpenapiVO.setCEastCnt(element.select("CEastCnt").text());		    //두수(수도권)
		
		            	OpenapiVO.setC_0714Amt(element.select("c_0714Amt").text());		    //가격(익산)
		            	OpenapiVO.setC_0714Cnt(element.select("c_0714Cnt").text());		    //두수(익산)
		            	OpenapiVO.setC_0809Amt(element.select("c_0809Amt").text());		    //가격(농협나주)
		            	OpenapiVO.setC_0809Cnt(element.select("c_0809Cnt").text());		    //두수(농협나주)
		            	OpenapiVO.setC_1401Amt(element.select("c_1401Amt").text());		    //가격(삼호축산)
		            	OpenapiVO.setC_1401Cnt(element.select("c_1401Cnt").text());		    //두수(삼호축산)
		            	OpenapiVO.setCWestAmt(element.select("CWestAmt").text()); 		    //가격(호남권)
		            	OpenapiVO.setCWestCnt(element.select("CWestCnt").text());		    //두수(호남권)
		                 
		            	OpenapiVO.setC_1101Amt(element.select("c_1101Amt").text());         //가격(제주축협)
		            	OpenapiVO.setC_1101Cnt(element.select("c_1101Cnt").text());         //두수(제주축협)
		                
		            	OpenapiVO.setCTotAmt(element.select("CTotAmt").text());             //가격(전체)
		            	OpenapiVO.setCTotCnt(element.select("CTotCnt").text());             //두수(전체)
		            	OpenapiVO.setC_1101eTotAmt(element.select("c_1101eTotAmt").text()); //가격(제주제외전체)
		            	OpenapiVO.setC_1101eTotCnt(element.select("c_1101eTotCnt").text()); //두수(제주제외전체)
		                
		            	OpenapiVO.setResultcode(successYn.select("resultcode").text());     //성공코드
		            	OpenapiVO.setResultmsg(successYn.select("resultmsg").text());	    //성공메세지
		
		            	OpenapiVO.setOffi_syst("openAPI");                                  //대상시스템
		            	OpenapiVO.setIntr_usid("if_dod_1010");                              //인터페이스ID
		            	OpenapiVO.setIntr_name("돼지도체경락가격연계");                              //인터페이스명
		                
		                list.add(OpenapiVO);
		            }
		            
		            if(list.size() < 1){ 
		            	
		            	OpenapiVO = new OpenapiVO();
		            	
		            	OpenapiVO.setOffi_syst("openAPI");                                    //대상시스템
		            	OpenapiVO.setIntr_usid("if_dod_1080");                                //인터페이스ID
		            	OpenapiVO.setIntr_name("권역별경락가격현황연계");                               //인터페이스명
		            	OpenapiVO.setResultcode("error");							          //성공코드
		            	OpenapiVO.setResultmsg("nodata");                            	      //성공메세지
		            	
		            	list.add(OpenapiVO);
		            
		               // paramMap.put("list", list);
		
		        		openapiService.insertLog(list);
		            }else{
		
		            	//paramMap.put("list", list);
		
		        		openapiService.insertOpenapi(list);
			
		            }
		             listAll.add(OpenapiVO); //20170926
		    	// 요청 결과가 정상이 아닐 경우 에러 내용을 담는다.
		        } else {
		            org.jsoup.select.Elements successYn = document.select("header");
		            
		            List<OpenapiVO> list = new ArrayList<OpenapiVO>();
		            OpenapiVO OpenapiVO = null;
		            
		            OpenapiVO = new OpenapiVO();
		        	
		            OpenapiVO.setResultcode(successYn.select("resultcode").text());         //성공코드
		            OpenapiVO.setResultmsg(successYn.select("resultmsg").text());	        //성공메세지
		        	
		            OpenapiVO.setOffi_syst("openAPI");                                      //대상시스템(로그)        
		            OpenapiVO.setIntr_usid("if_dod_1010");                                  //인터페이스ID(로그)
		            OpenapiVO.setIntr_name("돼지도체경락가격연계실패");                                //인터페이스명(로그)
		            
		            list.add(OpenapiVO);
		
		           // paramMap.put("list", list);
		    		openapiService.insertLog(list);
		    		
		    	  	listAll.add(OpenapiVO);//20170926
		       }
		    }
        }       
        	
   		openapiService.insertLog(listAll);
	
        paramMap.put("list", listAll);
        String mav = (new Gson()).toJson(paramMap);

        return(mav);
        
    }

    
	/**
     * 돼지도체 경락가격 저장(지육시세등록)
     * @param searchTO
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/openApi/saveApi.do", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain;charset=UTF-8")
      public @ResponseBody String saveApi(@ModelAttribute OpenapiSearchTO searchTO) throws Exception {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        /* skinYn = Y 박피, N 탕박   */
        String arrSkin[] = {"Y","N"};
        /* judgeSexCd = 025001 암, 025002 수, 025003 거세, 025004 기타 */
        String arrSexP[] = {"025001","025002","025003","025004",""};
        
        List<OpenapiVO> listAll = new ArrayList<OpenapiVO>();

        	for (int p = 0; p < arrSkin.length; p++) {

		        for (int i = 0; i < arrSexP.length; i++) {
					
		        // 요청 URL 생성
		        StringBuilder queryUrl = new StringBuilder();
		        queryUrl.append(URL);
		        queryUrl.append("?serviceKey=");
		        queryUrl.append(KEY);
		        queryUrl.append("&skinYn=");
		        queryUrl.append(arrSkin[p]);
		        queryUrl.append("&sexCd="); 
		        queryUrl.append(arrSexP[i]); 
		        queryUrl.append("&startYmd=");
		        queryUrl.append(searchTO.getStrtDate());        			// 시작일
		        queryUrl.append("&endYmd=");
		        queryUrl.append(searchTO.getLastDate());        			// 시작일

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
		
		            	OpenapiVO.setGradeType(element.select("gradeType").text());         //등급구분
		            	OpenapiVO.setStartYmd(element.select("startYmd").text());           //시작일
		            	OpenapiVO.setEndYmd(element.select("endYmd").text());               //종료일
		            	OpenapiVO.setGradeCd(element.select("gradeCd").text());             //등급구분
		            	OpenapiVO.setGradeNm(element.select("gradeNm").text());             //등급명
		            	OpenapiVO.setSkinYn(element.select("skinYn").text());			    //박피/탕박구분
		            	OpenapiVO.setSkinNm(element.select("skinNm").text());			    //박피/탕박
		            	OpenapiVO.setJudgeSexCd(element.select("judgeSexCd").text());	    //성별코드
		            	OpenapiVO.setJudgeSexNm(element.select("judgeSexNm").text());	    //성별
		
		            	OpenapiVO.setC_0320Amt(element.select("c_0320Amt").text());		    //가격(openMPS)
		            	OpenapiVO.setC_0320Cnt(element.select("c_0320Cnt").text());		    //두수(openMPS)
		            	OpenapiVO.setC_0302Amt(element.select("c_0302Amt").text());		    //가격(협신식품)
		            	OpenapiVO.setC_0302Cnt(element.select("c_0302Cnt").text());		    //두수(협신식품)
		            	OpenapiVO.setC_1301Amt(element.select("c_1301Amt").text());		    //가격(삼성식품)
		            	OpenapiVO.setC_1301Cnt(element.select("c_1301Cnt").text());		    //두수(삼성식품)
		            	OpenapiVO.setC_0323Amt(element.select("c_0323Amt").text());		    //가격(농협부천)
		            	OpenapiVO.setC_0323Cnt(element.select("c_0323Cnt").text());		    //두수(농협부천)
		            	OpenapiVO.setCCityAmt(element.select("CCityAmt").text()); 		    //가격(수도권)
		            	OpenapiVO.setCCityCnt(element.select("CCityCnt").text());		    //두수(수도권)
		
		            	OpenapiVO.setC_0513Amt(element.select("c_0513Amt").text());		    //가격(농협음성)
		            	OpenapiVO.setC_0513Cnt(element.select("c_0513Cnt").text());		    //두수(농협음성)
		                
		            	OpenapiVO.setC_1005Amt(element.select("c_1005Amt").text());		    //가격(김해축공)
		            	OpenapiVO.setC_1005Cnt(element.select("c_1005Cnt").text());		    //두수(김해축공)
		            	OpenapiVO.setC_0202Amt(element.select("c_0202Amt").text());		    //가격(부경축공)
		            	OpenapiVO.setC_0202Cnt(element.select("c_0202Cnt").text());		    //두수(부경축공)
		            	OpenapiVO.setC_0201Amt(element.select("c_0201Amt").text());		    //가격(동원산업)
		            	OpenapiVO.setC_0201Cnt(element.select("c_0201Cnt").text());		    //두수(동원산업)
		            	OpenapiVO.setC_1201Amt(element.select("c_1201Amt").text());		    //가격(신흥산업)
		            	OpenapiVO.setC_1201Cnt(element.select("c_1201Cnt").text());		    //두수(신흥산업)
		            	OpenapiVO.setC_0905Amt(element.select("c_0905Amt").text());		    //가격(농협고령)
		            	OpenapiVO.setC_0905Cnt(element.select("c_0905Cnt").text());		    //두수(농협고령)
		            	OpenapiVO.setCEastAmt(element.select("CEastAmt").text()); 		    //가격(수도권)
		            	OpenapiVO.setCEastCnt(element.select("CEastCnt").text());		    //두수(수도권)
		
		            	OpenapiVO.setC_0714Amt(element.select("c_0714Amt").text());		    //가격(익산)
		            	OpenapiVO.setC_0714Cnt(element.select("c_0714Cnt").text());		    //두수(익산)
		            	OpenapiVO.setC_0809Amt(element.select("c_0809Amt").text());		    //가격(농협나주)
		            	OpenapiVO.setC_0809Cnt(element.select("c_0809Cnt").text());		    //두수(농협나주)
		            	OpenapiVO.setC_1401Amt(element.select("c_1401Amt").text());		    //가격(삼호축산)
		            	OpenapiVO.setC_1401Cnt(element.select("c_1401Cnt").text());		    //두수(삼호축산)
		            	OpenapiVO.setCWestAmt(element.select("CWestAmt").text()); 		    //가격(호남권)
		            	OpenapiVO.setCWestCnt(element.select("CWestCnt").text());		    //두수(호남권)
		                 
		            	OpenapiVO.setC_1101Amt(element.select("c_1101Amt").text());         //가격(제주축협)
		            	OpenapiVO.setC_1101Cnt(element.select("c_1101Cnt").text());         //두수(제주축협)
		                
		            	OpenapiVO.setCTotAmt(element.select("CTotAmt").text());             //가격(전체)
		            	OpenapiVO.setCTotCnt(element.select("CTotCnt").text());             //두수(전체)
		            	OpenapiVO.setC_1101eTotAmt(element.select("c_1101eTotAmt").text()); //가격(제주제외전체)
		            	OpenapiVO.setC_1101eTotCnt(element.select("c_1101eTotCnt").text()); //두수(제주제외전체)
		                
		            	OpenapiVO.setResultcode(successYn.select("resultcode").text());     //성공코드
		            	OpenapiVO.setResultmsg(successYn.select("resultmsg").text());	    //성공메세지
		
		            	OpenapiVO.setOffi_syst("openAPI");                                  //대상시스템
		            	OpenapiVO.setIntr_usid("if_dod_1010");                              //인터페이스ID
		            	OpenapiVO.setIntr_name("돼지도체경락가격연계");                              //인터페이스명
		                
		                list.add(OpenapiVO);
		            }
		            
		            if(list.size() < 1){ 
		            	
		            	OpenapiVO = new OpenapiVO();
		            	
		            	OpenapiVO.setOffi_syst("openAPI");                                    //대상시스템
		            	OpenapiVO.setIntr_usid("if_dod_1080");                                //인터페이스ID
		            	OpenapiVO.setIntr_name("권역별경락가격현황연계");                               //인터페이스명
		            	OpenapiVO.setResultcode("error");							          //성공코드
		            	OpenapiVO.setResultmsg("nodata");                            	      //성공메세지
		            	
		            	list.add(OpenapiVO);
		            
		               // paramMap.put("list", list);
		
		        		openapiService.insertLog(list);
		            }else{
		
		            	//paramMap.put("list", list);
		
		        		openapiService.insertOpenapi(list);
			
		            }
		             listAll.add(OpenapiVO); //20170926
		    	// 요청 결과가 정상이 아닐 경우 에러 내용을 담는다.
		        } else {
		            org.jsoup.select.Elements successYn = document.select("header");
		            
		            List<OpenapiVO> list = new ArrayList<OpenapiVO>();
		            OpenapiVO OpenapiVO = null;
		            
		            OpenapiVO = new OpenapiVO();
		        	
		            OpenapiVO.setResultcode(successYn.select("resultcode").text());         //성공코드
		            OpenapiVO.setResultmsg(successYn.select("resultmsg").text());	        //성공메세지
		        	
		            OpenapiVO.setOffi_syst("openAPI");                                      //대상시스템(로그)        
		            OpenapiVO.setIntr_usid("if_dod_1010");                                  //인터페이스ID(로그)
		            OpenapiVO.setIntr_name("돼지도체경락가격연계실패");                                //인터페이스명(로그)
		            
		            list.add(OpenapiVO);
		
		           // paramMap.put("list", list);
		    		openapiService.insertLog(list);
		    		
		    	  	listAll.add(OpenapiVO);//20170926
		       }
		    }
        }       
        	
   		openapiService.insertLog(listAll);
	
        paramMap.put("list", listAll);
        String mav = (new Gson()).toJson(paramMap);

        return(mav);
        
    }
    /**
     * 돼지도체 경락가격 저장 스케줄(하루 2회)
     * @param searchTO
     * @return String
     * @throws Exception
     */
/*	@SuppressWarnings("unused")
	private AtomicInteger counter = new AtomicInteger(0);

	// 오후 6시 1회 
	   @Scheduled(cron = "0 09 15 * * *")
	public void cronSche1() throws Exception{ 
    	save();
		} 

    // 오후 8시 1회 
	   @Scheduled(cron = "0 07 15 * * *")
	public void cronSche2() throws Exception{ 
    	save();
		} 

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        		OpenapiController.class);
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            context.close();
        }
    }
 */
}