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
public class OpenapiConfirmDetailController {
 
    public static final String KEY = "XVDRBSRzu3xzEuhxcLgYaQ6TKfYD2gaVEgc%2FgvLomZ6M%2BLkuYV1soAIhVKQTTaRWbZ2M2R0VUR9Ubitwx3MoAg%3D%3D";
    public static final String URL = "http://data.ekape.or.kr/openapi-data/service/user/grade/confirm/pigPart";
    
    /** RoleService */
	@Resource(name = "openapiService")
	private OpenapiService openapiService;

    /**
     * 등급확인결과 상세(부위확인서) 저장
     * @return String
     * @throws Exception
     */
    @RequestMapping("/openApi/openapiConfirmDetail.do")
    public String openapiConfirmDetail() throws Exception {
         
        return "/openApi/openapiConfirmDetail";
    }
   
    /**
     * 등급확인결과(확인서) 검색 결과
     * @param searchTO
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/openApi/ConfirmDetailList.do", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain;charset=UTF-8")
    public @ResponseBody String ConfirmDetailList(@ModelAttribute OpenapiSearchTO searchTO) throws Exception {
         
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 요청 URL 생성
        StringBuilder queryUrl = new StringBuilder();
        queryUrl.append(URL);
        queryUrl.append("?serviceKey=");
        queryUrl.append(KEY);
        queryUrl.append("&issueNo=");
        queryUrl.append(searchTO.getIssueNo());  // 이력번호
        queryUrl.append("&issueDate=");
        queryUrl.append(searchTO.getIssueDate());  // 이력번호

 
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

            	OpenapiVO.setIssueNo(searchTO.getIssueNo());               
            	OpenapiVO.setIssueDate(searchTO.getIssueDate());         	         

            	OpenapiVO.setMeatType(element.select("meatType").text());             //부위구분
            	OpenapiVO.setCutmeatCode(element.select("cutmeatCode").text());       //부위코드  	         
            	OpenapiVO.setCutmeatName(element.select("cutmeatName").text());       //부위명        
            	OpenapiVO.setCutmeatWeight(element.select("cutmeatWeight").text());   //부위중량      	         
            	OpenapiVO.setCutmeatRate(element.select("cutmeatRate").text());       //비율  	         
            	
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
     * 등급판정결과 저장(돼지)
     * @param searchTO
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/openApi/openapiConfirmDetailSave.do", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain;charset=UTF-8")
    public @ResponseBody String openapiConfirmDetailSave(@ModelAttribute OpenapiSearchTO searchTO) throws Exception {
         
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 요청 URL 생성
        StringBuilder queryUrl = new StringBuilder();
        queryUrl.append(URL);
        queryUrl.append("?serviceKey=");
        queryUrl.append(KEY);
        queryUrl.append("&issueNo=");
        queryUrl.append(searchTO.getIssueNo());  // 이력번호
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

            	OpenapiVO.setIssueNo(searchTO.getIssueNo());               
            	OpenapiVO.setIssueDate(searchTO.getIssueDate());         	         

            	OpenapiVO.setMeatType(element.select("meatType").text());             //부위구분
            	OpenapiVO.setCutmeatCode(element.select("cutmeatCode").text());       //부위코드  	         
            	OpenapiVO.setCutmeatName(element.select("cutmeatName").text());       //부위명        
            	OpenapiVO.setCutmeatWeight(element.select("cutmeatWeight").text());   //부위중량      	         
            	OpenapiVO.setCutmeatRate(element.select("cutmeatRate").text());       //비율  	         
            	
            	OpenapiVO.setResultcode(successYn.select("resultcode").text());       //성공코드
            	OpenapiVO.setResultmsg(successYn.select("resultmsg").text());	      //성공메세지
                
            	OpenapiVO.setOffi_syst("openAPI");                                    //대상시스템
            	OpenapiVO.setIntr_usid("if_dod_1040");                                //인터페이스ID
            	OpenapiVO.setIntr_name("등급판정결과상세연계");                                //인터페이스명

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

                openapiService.insertConfirmDetail(list);
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
            OpenapiVO.setIntr_usid("if_dod_1040");                                    //인터페이스ID(로그)
            OpenapiVO.setIntr_name("등급판정결과상세연계실패");                                  //인터페이스명(로그)
            
            list.add(OpenapiVO);

            paramMap.put("list", list);
            openapiService.insertLog(list);
        }
        
        String mav = (new Gson()).toJson(paramMap);

        return(mav);
        
    }
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        		OpenapiConfirmDetailController.class);
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            context.close();
        }
    }
 
 
}