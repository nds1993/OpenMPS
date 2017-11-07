package nds.mpm.api.openApi.web;

import java.util.ArrayList;
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
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;


@Controller
public class OpenapiConfirmNoController {
 
    public static final String KEY = "";	//발급필요
    public static final String URL = "http://data.ekape.or.kr/openapi-data/service/user/grade/confirm/issueNo";
    
    /** RoleService */
	@Resource(name = "openapiService")
	private OpenapiService openapiService;

    /**
     * 축산물등급판정확인서 발급번호정보 저장
     * @return String
     * @throws Exception
     */
    @RequestMapping("/openApi/openapiConfirmNo.do")
    public String openapiConfirmNo() throws Exception {
         
        return "/openApi/openapiConfirmNo";
    }
   
    /**
     * 축산물등급판정확인서(확인서) 발급번호정보
     * @param searchTO
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/openApi/ConfirmNoList.do", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain;charset=UTF-8")
    public @ResponseBody String ConfirmNoList(@ModelAttribute OpenapiSearchTO searchTO) throws Exception {
         
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 요청 URL 생성
        StringBuilder queryUrl = new StringBuilder();
        queryUrl.append(URL);
        queryUrl.append("?serviceKey=");
        queryUrl.append(KEY);
        queryUrl.append("&issueDate=");
        queryUrl.append(searchTO.getIssueDate());     // 이력번호

 
        // document 선언
        Document document = Jsoup.connect(queryUrl.toString()).get();

        String errorCode = document.select("resultcode").text();
        
        // 요청 결과가 정상일 경우 내용을 파싱하여 List 에 담는다.
        if ( "00".equals(errorCode) ) {
            org.jsoup.select.Elements elements = document.select("item");
            org.jsoup.select.Elements successYn = document.select("header");
            
            List<OpenapiVO> list = new ArrayList<OpenapiVO>();
            OpenapiVO OpenapiVO = null;
            
            for(Element element : elements){
            	OpenapiVO = new OpenapiVO();

            	OpenapiVO.setAnimalNo(element.select("animalNo").text());		    //이럭번호         	         
            	OpenapiVO.setJudgeKindNm(element.select("judgeKindNm").text());     //가축명      
            	OpenapiVO.setAbattNm(element.select("abattNm").text());             //도축장명         
            	OpenapiVO.setAbattDate(element.select("abattDate").text());         //도축일자 	         
            	OpenapiVO.setJudgeDate(element.select("judgeDate").text());         //판정일자 	         
            	OpenapiVO.setIssueDate(element.select("issueDate").text());         //확인서발급일자 	         
            	OpenapiVO.setIssueNo(element.select("issueNo").text());             //확인서발급번호         
            	
            	OpenapiVO.setResultcode(successYn.select("resultcode").text());     //성공코드
            	OpenapiVO.setResultmsg(successYn.select("resultmsg").text());	    //성공메세지
                
            	
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
     *  축산물등급판정확인서(확인서) 발급번호정보 저장
     * @param searchTO
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/openApi/ConfirmNoSave.do", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain;charset=UTF-8")
    public @ResponseBody String ConfirmNoSave(@ModelAttribute OpenapiSearchTO searchTO) throws Exception {
         
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 요청 URL 생성
        StringBuilder queryUrl = new StringBuilder();
        queryUrl.append(URL);
        queryUrl.append("?serviceKey=");
        queryUrl.append(KEY);
        queryUrl.append("&issueDate=");
        queryUrl.append(searchTO.getIssueDate());  // 이력번호
 
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

            	OpenapiVO.setAnimalNo(element.select("animalNo").text());		    //이럭번호         	         
            	OpenapiVO.setJudgeKindNm(element.select("judgeKindNm").text());     //가축명      
            	OpenapiVO.setAbattNm(element.select("abattNm").text());             //도축장명         
            	OpenapiVO.setAbattDate(element.select("abattDate").text());         //도축일자 	         
            	OpenapiVO.setJudgeDate(element.select("judgeDate").text());         //판정일자 	         
            	OpenapiVO.setIssueDate(element.select("issueDate").text());         //확인서발급일자 	         
            	OpenapiVO.setIssueNo(element.select("issueNo").text());             //확인서발급번호         
            	
            	OpenapiVO.setResultcode(successYn.select("resultcode").text());     //성공코드
            	OpenapiVO.setResultmsg(successYn.select("resultmsg").text());	    //성공메세지
                
            	OpenapiVO.setOffi_syst("openAPI");                                  //대상시스템
            	OpenapiVO.setIntr_usid("if_dod_1050");                              //인터페이스ID
            	OpenapiVO.setIntr_name("등급판정확인서발급정보연계");                          //인터페이스명

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

                openapiService.insertConfirmNo(list);
                openapiService.insertLog(list);
            }
        // 요청 결과가 정상이 아닐 경우 에러 내용을 담는다.
        } else {
            org.jsoup.select.Elements successYn = document.select("header");
            
            List<OpenapiVO> list = new ArrayList<OpenapiVO>();
            OpenapiVO OpenapiVO = null;
            
            OpenapiVO = new OpenapiVO();
        	
            OpenapiVO.setResultcode(successYn.select("resultcode").text());           //성공코드
            OpenapiVO.setResultmsg(successYn.select("resultmsg").text());	          //성공메세지
        	
            OpenapiVO.setOffi_syst("openAPI");                                        //대상시스템(로그)        
            OpenapiVO.setIntr_usid("if_dod_1050");                                    //인터페이스ID(로그)
            OpenapiVO.setIntr_name("등급판정확인서발급정보연계실패");                              //인터페이스명(로그)
            
            list.add(OpenapiVO);

            paramMap.put("list", list);
            openapiService.insertLog(list);
        }
        
        String mav = (new Gson()).toJson(paramMap);

        return(mav);
        
    }
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        		OpenapiConfirmNoController.class);
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            context.close();
        }
    }
 
}